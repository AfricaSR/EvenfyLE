package view;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.net.URL;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Event;
import model.User;

import javafx.scene.input.MouseEvent;

import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;

public class EventController implements Initializable {

	User user = new User();

	ArrayList<TableEvents> events = new ArrayList<TableEvents>();

	ObservableList<TableEvents> listEvents = FXCollections.observableArrayList(events);

	@FXML
	private AnchorPane root;
	@FXML
	private Button download;
	@FXML
	private TableView<TableEvents> eventsTable;
	@FXML
	private TableColumn<String, TableEvents> codeEvent;
	@FXML
	private TableColumn<String, TableEvents> titleEvent;
	@FXML
	private TableColumn<String, TableEvents> dateEvent;
	@FXML
	private TableColumn<Integer, TableEvents> attendsEvent;

	// Esta función busca en la lista de eventos y comienza la generación de un
	// fichero xml del mismo
	@FXML
	public void download(MouseEvent event) throws IOException {

		TableEvents l = (TableEvents) eventsTable.getSelectionModel().getSelectedItem();

		for (int i = 0; i < this.user.getEvents().size(); i++) {

			if (l.getCodeEvent().equals(this.user.getEvents().get(i).getCode())) {

				jaxbObjectToXML(this.user.getEvents().get(i));

			}

		}
	}

	// Obtiene toda la información del usuario desde la pantalla de login
	public void setUser(User u) throws ParseException {

		this.user = u;

		// Inicializa la tabla de eventos transformando los valores del array de eventos
		// en objetos TableEvent
		for (int i = 0; i < this.user.getEvents().size(); i++) {

			events.add(new TableEvents((Event) this.user.getEvents().get(i)));

		}

		// Luego asigna los valores a los campos de las columnas de las tablas
		codeEvent.setCellValueFactory(new PropertyValueFactory<>("codeEvent"));

		titleEvent.setCellValueFactory(new PropertyValueFactory<>("titleEvent"));

		dateEvent.setCellValueFactory(new PropertyValueFactory<>("dateEvent"));

		attendsEvent.setCellValueFactory(new PropertyValueFactory<>("attendsEvent"));

		// Finalmente instancia una colección observable, que serán los datos que podrá
		// visualizar el usuario
		listEvents = FXCollections.observableArrayList(events);

		// Asigna la colección a la tabla
		eventsTable.setItems(listEvents);

		// Con esto se prevee que haya un registro seleccionado antes de poder pulsar el
		// botón de descarga
		eventsTable.setRowFactory(tv -> {

			TableRow<TableEvents> row = new TableRow<>();

			row.setOnMouseClicked(event -> {

				if (event.getClickCount() == 1 && (!row.isEmpty()) && (!row.isDisabled())) {

					download.setDisable(false);

				}

			});

			return row;

		});

	}

	/*
	 * Función que formatea el objeto Event y lo convierte en un documento XML
	 */
	private static void jaxbObjectToXML(Event e) throws IOException {

		try {

			JAXBContext jaxbContext = JAXBContext.newInstance(Event.class);

			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

			StringWriter sw = new StringWriter();

			jaxbMarshaller.setProperty("jaxb.encoding", "ASCII");

			jaxbMarshaller.marshal(e, sw);

			String xmlContent = sw.toString();

			// Ahora genera un objeto que preguntará al usuario dónde desea guardar su
			// fichero
			FileChooser fileChooser = new FileChooser();

			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("d-M-yy");

			LocalDateTime now = LocalDateTime.now();

			fileChooser.setInitialFileName("Invitaciones - " + e.getTitle() + " - " + dtf.format(now) + ".xml");

			fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("XML", "*.xml"));

			File file = fileChooser.showSaveDialog(new Stage());

			if (file != null) {

				file.createNewFile();

				FileWriter wr = new FileWriter(file.getPath());

				wr.write(xmlContent);

				wr.close();
				
				Alert alert = new Alert(AlertType.CONFIRMATION, "Archivo generado correctamente.", ButtonType.OK);
				
				alert.setHeaderText("Éxito");
		    	
		    	alert.showAndWait();

			}

		} catch (JAXBException ex) {

			ex.printStackTrace();
			
			Alert alert = new Alert(AlertType.ERROR, "Ha ocurrido un error.", ButtonType.OK);
	    	
	    	alert.showAndWait();

		}

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
	}
}

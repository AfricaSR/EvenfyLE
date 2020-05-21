package view;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;

import javafx.scene.control.PasswordField;

import javafx.scene.input.MouseEvent;

import javafx.scene.layout.BorderPane;

import model.User;
import imp.User_Imp;

public class LoginController implements Initializable  {
	
	User user = new User();
	
	User_Imp loginUser = new User_Imp();

	@FXML
	private BorderPane root;
	@FXML
	private AnchorPane main;
	@FXML
	private Button login;
	@FXML
	private TextField email;
	@FXML
	private PasswordField password;
	
	//Comprueba que el usuario esté registrado en la base de datos
	@FXML
	public void login(MouseEvent event) throws ParseException {
		
		try {
			
			user = loginUser.loginUser(email.getText(), password.getText());
			
		} catch (Exception e) {
			
			Alert alert = new Alert(AlertType.WARNING, "El servicio no está disponible en estos momentos", ButtonType.OK);
	    	
	    	alert.showAndWait();
			
		}
		
		//Si está registrado, carga la interfaz de Eventos, pasándole el usuario que ha encontrado y sus eventos
		if (user != null) {
			
			try {
				
				FXMLLoader loader = new FXMLLoader();
				
				root.getChildren().remove(this.main);
				
				this.main = (AnchorPane)loader.load(getClass().getResource("Event.fxml").openStream());
				
				EventController ec = (EventController)loader.getController();
				
				ec.setUser(user);
				
				root.setCenter(this.main);
				
			} catch (IOException e) {
				
				Alert alert = new Alert(AlertType.WARNING, "Ha ocurrido un error", ButtonType.OK);
		    	
		    	alert.showAndWait();
				
			}
	//Si no lo encuentra manda un mensaje de error 		
	} else {
		
		Alert alert = new Alert(AlertType.WARNING, "Usuario o Contraseña no válidos", ButtonType.OK);
    	
    	alert.showAndWait();
		
	}
}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}
	
}

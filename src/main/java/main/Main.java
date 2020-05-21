package main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;

public class Main extends Application  {

	//El Main sólo se encarga de invocar la pantalla de login
	public static void main(String[] args) {
		
		launch(args);
		
	}
	
	//Al inciar la app se cargan las vistas configuradas en el paquete de vistas
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		try {
			
			BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("/view/Login.fxml"));
			
			Scene scene = new Scene(root);
			
			primaryStage.setScene(scene);
			
			primaryStage.show();
			
		} catch(Exception e) {
			
			e.printStackTrace();
			
		}
		
	}

}

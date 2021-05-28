package controller;

import java.sql.Connection;
import java.sql.DriverManager;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class Main extends Application {
	
	public static Connection conn;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/view/Scene1.fxml"));
			Scene scene = new Scene(root,400,400);
			scene.getStylesheets().add(getClass().getResource("/view/application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() throws Exception {
		try {
			String driver = "com.mysql.jdbc.Driver";
			String url = "jdbc:MYSQL://localhost/ecole";
			String username = "root";
			String password = "";
			Class.forName(driver);
			   
			conn = DriverManager.getConnection(url,username,password);
			System.out.println("Connected to database");
			return conn;
		} catch(Exception e) {
			System.out.println(e);
		}
		  
		return null;
	}
	
	public static void main(String[] args) throws Exception {
//		BDD connexion
		getConnection();
		
//		SceneBuilder starting
        launch(args);
	}
}

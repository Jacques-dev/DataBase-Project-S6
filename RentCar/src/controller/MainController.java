package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainController extends Main implements Initializable {
	
	public Stage stage;
	public Scene scene;
	
	public void goHome(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("/view/Home.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	public void goToClientArea(ActionEvent event) throws IOException, SQLException {
		Parent root = FXMLLoader.load(getClass().getResource("/view/ClientLocationArea.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	public void goToConnexionArea(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("/view/ConnexionArea.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}
	
	
		
		

}

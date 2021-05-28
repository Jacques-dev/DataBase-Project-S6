package controller;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MainController extends Main {
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	public void switchToScene2(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("/view/Scene2.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	public void switchToScene1(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("/view/Scene1.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	
	@FXML
	private Button btn;
	
	public void exit(ActionEvent event) throws SQLException {
		
		Statement s = conn.createStatement();
        String sql = "Select * From ELEVES; ";
        
        ResultSet rs = s.executeQuery(sql);
        
        int i = 1 ;
        while(rs.next()){
            System.out.println("----- Ligne N� : " + i++ + "-----");
            System.out.println(rs.getString("NOM")  +  " - " +rs.getString("PRENOM"));
        }
		
		System.out.println("au revoir");
		System.exit(0);
	}
	
	public void validation(ActionEvent event) {
		System.out.println("c'est valid�");
	}
	
	
}

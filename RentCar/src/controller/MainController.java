package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class MainController extends Main implements Initializable {
	
	public Stage stage;
	public Scene scene;
	public static String actualscene;
	
	
	@FXML private Label clientGoldName;
	@FXML private Label clientGoldNbLocation;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		String sql = "Select count(*) From retour";
		String sql2 = "SELECT nom, prenom, COUNT(*) from utilisateur natural join `retour` group by idUtilisateur"; 
		
		try {
			
	        PreparedStatement stat = conn.prepareStatement(sql);
	        ResultSet rs = stat.executeQuery();
	         
	        String totalLocation = "";
	        while(rs.next()) { 
				totalLocation = rs.getString(1);
	        }
	        
	        stat = conn.prepareStatement(sql2);
	        rs = stat.executeQuery();
	        String nom = "", prenom = "";
	        String nbLocations = "";
	        
	        while(rs.next()) { 
	        	nom = rs.getString(1);
	        	prenom = rs.getString(2);
	        	nbLocations = rs.getString(3);
	        	break;
	        }
	        
	        clientGoldName.setText(prenom + " " + nom);
	        clientGoldNbLocation.setText(nbLocations+"/"+totalLocation);
	        
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	
	
	
	
	public void goHome(ActionEvent event) throws IOException {
		actualscene = "Home";
		Parent root = FXMLLoader.load(getClass().getResource("/view/Home.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	public void goToClientArea(ActionEvent event) throws IOException, SQLException {
		actualscene = "ClientLocationArea";
		Parent root = FXMLLoader.load(getClass().getResource("/view/ClientLocationArea.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	public void goToConnexionArea(ActionEvent event) throws IOException {
		actualscene = "ConnexionArea";
		Parent root = FXMLLoader.load(getClass().getResource("/view/ConnexionArea.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

}
package controller;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ConnexionController extends MainController {
	
	@FXML private TextField identifiant;
	@FXML private PasswordField mot_de_passe;
	@FXML private Label lbletat;
	
	@FXML
	public void login(ActionEvent event) {
		
		PreparedStatement stat = null;
		ResultSet rs = null;
		String sql_gerant = "SELECT idUtilisateur FROM employe WHERE login = ? AND motDePasse = ? AND idUtilisateur in (SELECT IdUtilisateur FROM gerant)";
		String sql_chauffeur = "SELECT idUtilisateur FROM employe WHERE login = ? AND motDePasse = ? AND idUtilisateur in (SELECT IdUtilisateur FROM chauffeur)";

		try {
			stat = conn.prepareStatement(sql_gerant);
			stat.setString(1, identifiant.getText().toString());
			stat.setString(2, mot_de_passe.getText().toString());
			rs = stat.executeQuery();

			if (rs.next()) {
				goToGerantAreaClient(event);
			} 
		} catch(Exception e) {
			
		}
		
		try {
			stat = conn.prepareStatement(sql_chauffeur);
			stat.setString(1, identifiant.getText().toString());
			stat.setString(2, mot_de_passe.getText().toString());
			rs = stat.executeQuery();
			if (rs.next()) {
				goToChauffeurArea(event);
	
			}
		} catch(Exception e) {
			
		}
		
	}
	
	
	public void goToGerantAreaClient(ActionEvent event) throws IOException {
		actualscene = "gestionClient";
		Parent root = FXMLLoader.load(getClass().getResource("/view/GerantArea_gestionClient.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	public void goToChauffeurArea(ActionEvent event) throws IOException {
		actualscene = "ChauffeurArea";
		Parent root = FXMLLoader.load(getClass().getResource("/view/ChauffeurArea.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
}

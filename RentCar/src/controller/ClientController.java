package controller;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.ProgramFidelite;
import model.Utilisateur;
import model.Vehicule;

public class ClientController extends MainController {

	@FXML private TableView<ProgramFidelite> programFideliteTable;
	@FXML private TableColumn<ProgramFidelite, Integer> idProgrammeFidelite;
	@FXML private TableColumn<ProgramFidelite, Integer> duree;
	@FXML private TableColumn<ProgramFidelite, String> description;
	@FXML private TableColumn<ProgramFidelite, Float> prix;
	
	
	public ObservableList<ProgramFidelite> programs = FXCollections.observableArrayList();
	
	public void printPrograms(ActionEvent event) throws SQLException {
        String sql = "Select * From programmefidelite";
        PreparedStatement stat = conn.prepareStatement(sql);
        ResultSet rs = stat.executeQuery();
        
        while(rs.next()) {
        	programs.add(new ProgramFidelite(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getFloat(4)));
        }
        
        idProgrammeFidelite.setCellValueFactory(new PropertyValueFactory<ProgramFidelite, Integer>("id"));
        duree.setCellValueFactory(new PropertyValueFactory<ProgramFidelite, Integer>("duree"));
        description.setCellValueFactory(new PropertyValueFactory<ProgramFidelite, String>("description"));
        prix.setCellValueFactory(new PropertyValueFactory<ProgramFidelite, Float>("prix"));
		
        programFideliteTable.setItems(programs);
	}
	
	@FXML private TableView<Vehicule> vehiculeTable;
	@FXML private TableColumn<Vehicule, String> matricule;
	@FXML private TableColumn<Vehicule, String> marque;
	@FXML private TableColumn<Vehicule, String> modele;
	@FXML private TableColumn<Vehicule, Integer> kilometrage;
	@FXML private TableColumn<Vehicule, Boolean> climatisation;
	@FXML private TableColumn<Vehicule, String> typeBoiteDeVitesse;
	@FXML private TableColumn<Vehicule, String> type;
	
	
	public ObservableList<Vehicule> vehicules = FXCollections.observableArrayList();
	
	public void printVehicules(ActionEvent event) throws SQLException {
        String sql = "Select * From vehicule WHERE matricule not in (select matricule from loue)";
        PreparedStatement stat = conn.prepareStatement(sql);
        ResultSet rs = stat.executeQuery();
        
        while(rs.next()) {
        	vehicules.add(new Vehicule(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getBoolean(5), rs.getString(6), rs.getString(7)));
        }
        
        matricule.setCellValueFactory(new PropertyValueFactory<Vehicule, String>("matricule"));
        marque.setCellValueFactory(new PropertyValueFactory<Vehicule, String>("marque"));
        modele.setCellValueFactory(new PropertyValueFactory<Vehicule, String>("modele"));
        kilometrage.setCellValueFactory(new PropertyValueFactory<Vehicule, Integer>("kilometrage"));
        climatisation.setCellValueFactory(new PropertyValueFactory<Vehicule, Boolean>("climatisation"));
        typeBoiteDeVitesse.setCellValueFactory(new PropertyValueFactory<Vehicule, String>("typeBoiteDeVitesse"));
        type.setCellValueFactory(new PropertyValueFactory<Vehicule, String>("type"));
		
        vehiculeTable.setItems(vehicules);
        print_clients(event);
	}
	
	@FXML private TextField filterField;
	@FXML private TableView<Utilisateur> clientTable;
	@FXML private TableColumn<Utilisateur, Integer> idUtilisateur;
	@FXML private TableColumn<Utilisateur, String> nom;
	@FXML private TableColumn<Utilisateur, String> prenom;
	
	
	public ObservableList<Utilisateur> clients = FXCollections.observableArrayList();
	
	@FXML
	public void print_clients(ActionEvent event) throws SQLException {
		String sql = "Select * FROM utilisateur NATURAL JOIN client";
        PreparedStatement stat = conn.prepareStatement(sql);
        ResultSet rs = stat.executeQuery();
        
        while(rs.next()) {
        	clients.add(new Utilisateur(rs.getInt(1), rs.getString(2), rs.getString(3)));
        }
        
        idUtilisateur.setCellValueFactory(new PropertyValueFactory<Utilisateur, Integer>("id"));
		nom.setCellValueFactory(new PropertyValueFactory<Utilisateur, String>("nom"));
		prenom.setCellValueFactory(new PropertyValueFactory<Utilisateur, String>("prenom"));
		
		FilteredList<Utilisateur> filteredData = new FilteredList<>(clients, b -> true);
		
		filterField.textProperty().addListener((observableValue, oldValue, newValue) -> {
			filteredData.setPredicate(utilisateur -> {
				
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (utilisateur.getNom().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true;
				} else if (utilisateur.getPrenom().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true;
				} else if (String.valueOf(utilisateur.getId()).indexOf(lowerCaseFilter) != -1) {
					return true;
				} else {
					return false;
				}
			});
		});
		
		SortedList<Utilisateur> sortedData = new SortedList<>(filteredData);
		sortedData.comparatorProperty().bind(clientTable.comparatorProperty());
		
		clientTable.setItems(clients);
	}
	
	
	
	@FXML private TextField input_matricule;
	@FXML private TextField input_idProgramFidelite;
	@FXML private TextField input_idUtilisateur;
	@FXML private CheckBox input_assurance;
	@FXML private CheckBox input_tauxReduction;
	@FXML private TextField input_duree;
	@FXML private Label lbletat;
	
	
	@FXML public void loue(ActionEvent event) {
		PreparedStatement stat = null;
		ResultSet rs = null;
		String sql = "INSERT INTO loue (matricule, idUtilisateur, assurance, duree, datePriseDeReservation) VALUES (?, ?, ?, ?, ?)";

		try {
			stat = conn.prepareStatement(sql);
			stat.setString(1, input_matricule.getText().toString());
			stat.setString(2, input_idUtilisateur.getText().toString());
			stat.setString(3, input_assurance.getText().toString());
			stat.setString(4, input_duree.getText().toString());
			stat.setString(5, java.time.LocalDate.now().toString());
			rs = stat.executeQuery();

			lbletat.setText("Location enregistré");
		} catch(Exception e) {
			lbletat.setText("Location erreur");
		}
	}
	
	@FXML public void reserve(ActionEvent event) {
		PreparedStatement stat = null;
		ResultSet rs = null;
		String sql = "INSERT INTO reserve (matricule, idUtilisateur, dateReservation, datePriseDeReservation) VALUES (?, ?, ?, ?)";

		try {
			stat = conn.prepareStatement(sql);
			stat.setString(1, input_matricule.getText().toString());
			stat.setString(2, input_idUtilisateur.getText().toString());
			stat.setString(3, input_duree.getText().toString());
			stat.setString(4, java.time.LocalDate.now().toString());
			rs = stat.executeQuery();

			lbletat.setText("Réservation enregistré");
		} catch(Exception e) {
			lbletat.setText("Réservation erreur");
		}
	}
	
	@FXML public void retourne(ActionEvent event) {
		
	}
	
	@FXML public void souscrit(ActionEvent event) {
		PreparedStatement stat = null;
		ResultSet rs = null;
		String sql = "INSERT INTO reserve (idUtilisateur, idProgrammeFidelite, dateSouscription, tauxReduction) VALUES (?, ?, ?, ?)";

		try {
			stat = conn.prepareStatement(sql);
			stat.setString(1, input_matricule.getText().toString());
			stat.setString(2, input_idUtilisateur.getText().toString());
			stat.setString(3, input_duree.getText().toString());
			stat.setString(4, input_tauxReduction.getText().toString());
			rs = stat.executeQuery();

			lbletat.setText("Souscription enregistré");
		} catch(Exception e) {
			lbletat.setText("Souscription erreur");
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public void goToClientLocationArea(ActionEvent event) throws IOException, SQLException {
		Parent root = FXMLLoader.load(getClass().getResource("/view/ClientLocationArea.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	public void goToClientFideliteArea(ActionEvent event) throws IOException, SQLException {
		Parent root = FXMLLoader.load(getClass().getResource("/view/ClientFideliteArea.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
}

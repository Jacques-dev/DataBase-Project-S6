package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.ProgramFidelite;
import model.Utilisateur;
import model.Vehicule;

public class ClientController extends MainController implements Initializable {

	@FXML private TableView<ProgramFidelite> programFideliteTable;
	@FXML private TableColumn<ProgramFidelite, Integer> idProgrammeFidelite;
	@FXML private TableColumn<ProgramFidelite, Integer> duree;
	@FXML private TableColumn<ProgramFidelite, String> description;
	@FXML private TableColumn<ProgramFidelite, Float> prix;
	
	
	public ObservableList<ProgramFidelite> programs = FXCollections.observableArrayList();
	
	public void printPrograms(ActionEvent event) throws SQLException {
		programFideliteTable.getItems().clear();
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
        
        //programFideliteTable.getItems().clear();
        programFideliteTable.setItems(programs);
        
        print_clients(event);
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
		vehiculeTable.getItems().clear();
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
	
	public void printVehicules() throws SQLException {
		vehiculeTable.getItems().clear();
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
        
//        print_clients();
	}
	
	public void printVehiculesLoue(ActionEvent event) throws SQLException {
		vehiculeTable.getItems().clear();
        String sql = "Select * From vehicule WHERE matricule in (select matricule from loue)";
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
		
        //vehiculeTable.getItems().clear();
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
		clientTable.getItems().clear();
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
	@FXML private TextField input_idUtilisateur;
	@FXML private CheckBox input_assurance;
	@FXML private TextField input_duree;
	
	@FXML private DatePicker input_date;
	
	@FXML private TextField input_idProgramFidelite;
	@FXML private CheckBox input_tauxReduction;
	
	@FXML private ChoiceBox<Integer> input_etat = new ChoiceBox<Integer>();
	@FXML private Label input_etat_label;
	private Integer[] listOfEtat = {1,2,3,4,5};
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		input_etat.getItems().addAll(listOfEtat);
		input_etat.setOnAction(this::updateEtatLabel);
		try {
			printVehicules();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void updateEtatLabel(ActionEvent event) {
		String etat = input_etat.getValue().toString();
		input_etat_label.setText(etat);
	}
	
	@FXML private Label lbletat;
	
	
	@FXML public void loue(ActionEvent event) {
		vehiculeTable.getItems().clear();
		String sql = ("INSERT INTO loue (matricule, idUtilisateur, assurance, duree, datePriseDeReservation) VALUES (?, ?, ?, ?, ?)");
		
		try {
			PreparedStatement pst = conn.prepareStatement(sql);
			
			pst.setString(1, input_matricule.getText().toString());
			pst.setInt(2, Integer.valueOf(input_idUtilisateur.getText().toString()));
			pst.setBoolean(3, Boolean.valueOf(input_assurance.getText().toString()));
			pst.setInt(4, Integer.valueOf(input_duree.getText().toString()));
			pst.setString(5, java.time.LocalDate.now().toString());

	        pst.executeUpdate();
	        
			lbletat.setText("Location enregistré");
		} catch(Exception e) {
			lbletat.setText("Location erreur");
		}
		try {
			printVehicules();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@FXML public void reserve(ActionEvent event) {
		String sql = "INSERT INTO reserve (matricule, idUtilisateur, dateReservation, datePriseDeReservation) VALUES (?, ?, ?, ?)";

		try {
			PreparedStatement pst = conn.prepareStatement(sql);
			
			pst.setString(1, input_matricule.getText().toString());
			pst.setInt(2, Integer.valueOf(input_idUtilisateur.getText().toString()));
			String date = input_date.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
			pst.setString(3, date.toString());
			pst.setString(4, java.time.LocalDate.now().toString());
			
			pst.executeUpdate();

			lbletat.setText("Réservation enregistré");
		} catch(Exception e) {
			lbletat.setText("Réservation erreur");
		}
	}
	
	@FXML public void retourne(ActionEvent event) {
		String sql = "INSERT INTO retourne (matricule, idUtilisateur, date, etatOrigine) VALUES (?, ?, ?, ?)";
		String sql2 = "DELETE FROM loue WHERE matricule = ? AND idUtilisateur = ?";

		try {
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, input_matricule.getText().toString());
			pst.setInt(2, Integer.valueOf(input_idUtilisateur.getText().toString()));
			pst.setString(3, java.time.LocalDate.now().toString());
			pst.setInt(4, Integer.valueOf(input_etat_label.getText().toString()));
			
			
			PreparedStatement pst2 = conn.prepareStatement(sql2);
			pst2.setString(1, input_matricule.getText().toString());
			pst2.setInt(2, Integer.valueOf(input_idUtilisateur.getText().toString()));
			
			
			pst2.executeUpdate();
			pst.executeUpdate();

			lbletat.setText("Retour enregistré");
		} catch(Exception e) {
			lbletat.setText("Retour erreur");
		}
	}
	
	@FXML public void souscrit(ActionEvent event) {
		String sql = "INSERT INTO souscrire (idUtilisateur, idProgrammeFidelite, dateSouscription, tauxReduction) VALUES (?, ?, ?, ?)";

		try {
			PreparedStatement pst = conn.prepareStatement(sql);
			
			pst.setInt(1, Integer.valueOf(input_idUtilisateur.getText().toString()));
			pst.setInt(2, Integer.valueOf(input_idProgramFidelite.getText().toString()));
			pst.setString(3, java.time.LocalDate.now().toString());
			pst.setBoolean(4, Boolean.valueOf(input_tauxReduction.getText().toString()));

			pst.executeUpdate();

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
		//print_clients(event);
	}
	
	public void goToClientFideliteArea(ActionEvent event) throws IOException, SQLException {
		Parent root = FXMLLoader.load(getClass().getResource("/view/ClientFideliteArea.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
		//printPrograms(event);
	}
	
	public void goToClientRetourArea(ActionEvent event) throws IOException, SQLException {
		Parent root = FXMLLoader.load(getClass().getResource("/view/ClientRetourArea.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
		//printVehiculesLoue(event);
	}
}

package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.ProgramFidelite;
import model.Utilisateur;
import model.Vehicule;

public class MainController extends Main implements Initializable {
	
	public Stage stage;
	public Scene scene;
	
	public void goToClientArea(ActionEvent event) throws IOException, SQLException {
		Parent root = FXMLLoader.load(getClass().getResource("/view/ClientArea.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
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
        String sql = "Select * From vehicule";
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
	}
	
	@FXML private Button staffMemberButton;
	
	public void goTo_connexion_to_StaffMemberArea(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("/view/Connexion_to_StaffMemberArea.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	public void goTo_GerantArea(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("/view/GerantArea.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	public void goTo_ChauffeurArea(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("/view/ChauffeurArea.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	@FXML private TableView<Utilisateur> client_table;
	@FXML private TableColumn<Utilisateur, String> nom;
	@FXML private TableColumn<Utilisateur, String> prenom;
	@FXML private TableColumn<Utilisateur, String> email;
	@FXML private TableColumn<Utilisateur, String> telephone;
	@FXML private TableColumn<Utilisateur, String> idAdresse;
	@FXML private TableColumn<Utilisateur, Integer> idUtilisateur;
	
	public ObservableList<Utilisateur> clients = FXCollections.observableArrayList();
	
	@FXML
	public void print_clients(ActionEvent event) throws SQLException {
        String sql = "Select * FROM utilisateur NATURAL JOIN client";
        PreparedStatement stat = conn.prepareStatement(sql);
        ResultSet rs = stat.executeQuery();
        
        while(rs.next()) {
        	clients.add(new Utilisateur(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6)));
        }
        
        idUtilisateur.setCellValueFactory(new PropertyValueFactory<Utilisateur, Integer>("id"));
        
		nom.setCellValueFactory(new PropertyValueFactory<Utilisateur, String>("nom"));
		prenom.setCellValueFactory(new PropertyValueFactory<Utilisateur, String>("prenom"));
		email.setCellValueFactory(new PropertyValueFactory<Utilisateur, String>("email"));
		telephone.setCellValueFactory(new PropertyValueFactory<Utilisateur, String>("telephone"));
		idAdresse.setCellValueFactory(new PropertyValueFactory<Utilisateur, String>("idAdresse"));
		
		client_table.setItems(clients);
	}
	
	@FXML 
	ObservableList<Utilisateur> clientselected, allclient; 

	public void deleteButtonClicked(ActionEvent event) throws SQLException {
		
        
		allclient = client_table.getItems();
		clientselected = client_table.getSelectionModel().getSelectedItems();
		System.out.println(clientselected);
		//String sql = "DELETE From utilisateur where idUtilisateur = ?";
		/*try {
			stat = conn.prepareStatement(sql);
			stat.setString(1, clientselected.getId());
		}*/
        //PreparedStatement stat = conn.prepareStatement(sql);
        //ResultSet rs = stat.executeQuery();
		clientselected.forEach(allclient::remove);
		
	}
	
	
	
	@FXML
	TextField input_nom = new TextField();
	@FXML
	TextField input_prenom = new TextField();
	@FXML
	TextField input_email = new TextField();
	@FXML
	TextField input_telephone = new TextField();
	@FXML
	TextField input_adresse = new TextField();
	
	
	public void addButtonClicked(ActionEvent event) throws SQLException {
		/* String sql = "Select max(idUtilisateur) FROM utilisateur NATURAL JOIN client";
	     PreparedStatement stat = conn.prepareStatement(sql);
	     ResultSet rs = stat.executeQuery();
	     System.out.println(rs.getInt(1));*/
		 /*String sql = "INSERT INTO utilisateur (nom,prenom,email,telephone,idAdresse) Values (?,?,?,?,?)";
		 PreparedStatement stat = conn.prepareStatement(sql);
		 stat.setString(1, input_nom.getText().toString());
		 stat.setString(2, input_prenom.getText().toString());
		 stat.setString(3, input_email.getText().toString());
		 stat.setString(4, input_telephone.getText().toString());
		 stat.setString(5, input_adresse.getText().toString());
	     
	     stat.executeQuery();*/
	
        Utilisateur nv_utilisateur = new Utilisateur(0, input_nom.getText(), input_prenom.getText(), input_email.getText(), input_telephone.getText(), input_adresse.getText());

        //System.out.println(nv_utilisateur);
        
        client_table.getItems().add(nv_utilisateur);
        
        input_nom.clear();
        input_prenom.clear();
        input_email.clear();
        input_telephone.clear();
        input_adresse.clear();
		
	}
	
	
	
	@FXML private Button btn_back;
	
	public void goHome(ActionEvent event) throws IOException {
		
		Parent root = FXMLLoader.load(getClass().getResource("/view/Home.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
		
	}
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}
	
	
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
					goTo_GerantArea(event);
					
				} 
			} catch( Exception e) {
				
			}
			try {
				stat = conn.prepareStatement(sql_chauffeur);
				stat.setString(1, identifiant.getText().toString());
				stat.setString(2, mot_de_passe.getText().toString());
				rs = stat.executeQuery();
				if (rs.next()) {
					goTo_ChauffeurArea(event);
		
				}
			} catch( Exception e) {
				
			}
			
			
		}
	
		@FXML private Button btn_deconnexion;
		public void deconnexion(ActionEvent event) throws IOException {
			
			Parent root = FXMLLoader.load(getClass().getResource("/view/Home.fxml"));
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
			
		}
		
		@FXML
		public void go_to_gestion_client(ActionEvent event) throws IOException {
			
			Parent root = FXMLLoader.load(getClass().getResource("/view/GerantArea.fxml"));
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
			
		}
		@FXML
		public void go_to_gestion_vehicule(ActionEvent event) throws IOException {
			
			Parent root = FXMLLoader.load(getClass().getResource("/view/GerantArea_gestionVehicule.fxml"));
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
			
		}
		@FXML
		public void go_to_gestion_finance(ActionEvent event) throws IOException {
			
			Parent root = FXMLLoader.load(getClass().getResource("/view/GerantArea_gestionFinance.fxml"));
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
			
		}

}

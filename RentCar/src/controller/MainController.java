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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Utilisateur;
import model.Vehicule;

public class MainController extends Main implements Initializable {
	
	private Stage stage;
	private Scene scene;
	
	@FXML private Button clientButton;
	
	public void goToClientArea(ActionEvent event) throws IOException, SQLException {
		Parent root = FXMLLoader.load(getClass().getResource("/view/ClientArea.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
		
		printVehicules(event);
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
	
	@FXML
	public void printVehicules(ActionEvent event) throws SQLException {
        String sql = "Select * From vehicule";
        PreparedStatement stat = conn.prepareStatement(sql);
        ResultSet rs = stat.executeQuery();
        
        while(rs.next()) {
        	vehicules.add(new Vehicule(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getBoolean(5), rs.getString(6), rs.getString(7)));
        }
        System.out.println(this.matricule);
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
	
	
	public void goToStaffMemberArea(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("/view/StaffMemberArea.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	
	
	
	@FXML private TableView<Utilisateur> utilisateurTable;
	@FXML private TableColumn<Utilisateur, Integer> idUtilisateur;
	@FXML private TableColumn<Utilisateur, String> nom;
	@FXML private TableColumn<Utilisateur, String> prenom;
	@FXML private TableColumn<Utilisateur, String> email;
	@FXML private TableColumn<Utilisateur, String> telephone;
	@FXML private TableColumn<Utilisateur, String> idAdresse;
	
	public ObservableList<Utilisateur> utilisateurs = FXCollections.observableArrayList();
	
	@FXML
	public void printUsers(ActionEvent event) throws SQLException {
        String sql = "Select * From utilisateur";
        PreparedStatement stat = conn.prepareStatement(sql);
        ResultSet rs = stat.executeQuery();
        
        while(rs.next()) {
        	utilisateurs.add(new Utilisateur(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6)));
        }
        
        idUtilisateur.setCellValueFactory(new PropertyValueFactory<Utilisateur, Integer>("id"));
		nom.setCellValueFactory(new PropertyValueFactory<Utilisateur, String>("nom"));
		prenom.setCellValueFactory(new PropertyValueFactory<Utilisateur, String>("prenom"));
		email.setCellValueFactory(new PropertyValueFactory<Utilisateur, String>("email"));
		telephone.setCellValueFactory(new PropertyValueFactory<Utilisateur, String>("telephone"));
		idAdresse.setCellValueFactory(new PropertyValueFactory<Utilisateur, String>("idAdresse"));
		
		utilisateurTable.setItems(utilisateurs);
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}
}

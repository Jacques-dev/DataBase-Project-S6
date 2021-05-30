package controller;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Utilisateur;

public class GerantController extends MainController {

	
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
	
	@FXML 
	ObservableList<Utilisateur> clientselected, allclient; 

	public void deleteButtonClicked(ActionEvent event) throws SQLException, IOException {
		
		allclient = client_table.getItems();
		clientselected = client_table.getSelectionModel().getSelectedItems();
		System.out.println(clientselected.get(0).getId());
		String sql = ("DELETE FROM utilisateur where idUtilisateur = ? ");
		
		PreparedStatement pst = conn.prepareStatement(sql);
		
		pst.setInt(1, clientselected.get(0).getId());
		System.out.println(pst);
		
        pst.executeUpdate();
        go_to_gestion_client(event);
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
	@FXML
	public void go_to_gestion_client(ActionEvent event) throws IOException {
		
		Parent root = FXMLLoader.load(getClass().getResource("/view/GerantArea_gestionClient.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
}

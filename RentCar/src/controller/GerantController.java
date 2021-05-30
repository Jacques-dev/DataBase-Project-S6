package controller;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;


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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Utilisateur;
import model.Vehicule;

public class GerantController extends MainController {

	
	@FXML TextField input_nom = new TextField();
	@FXML TextField input_prenom = new TextField();
	@FXML TextField input_email = new TextField();
	@FXML TextField input_telephone = new TextField();
	@FXML TextField input_adresse = new TextField();
	public void addButtonClicked(ActionEvent event) throws SQLException {
			
		 String sql = "INSERT INTO utilisateur (nom,prenom,email,telephone,idAdresse) Values (?,?,?,?,?)";
		 
		 PreparedStatement stat = conn.prepareStatement(sql);
	     
		 
		 stat.setString(1, input_nom.getText().toString());
		 stat.setString(2, input_prenom.getText().toString());
		 stat.setString(3, input_email.getText().toString());
		 stat.setString(4, input_telephone.getText().toString());
		 stat.setString(5, input_adresse.getText().toString());
	     
	     stat.executeUpdate();
	         
	     String sql_id ="SELECT idUtilisateur FROM utilisateur WHERE email = ?";
	     
	     PreparedStatement stat_id = conn.prepareStatement(sql_id);
	     stat_id.setString(1, input_email.getText().toString());
	     ResultSet rs = stat_id.executeQuery();
	     
	     String sql_client = "INSERT INTO client (idUtilisateur) Values (?)";

	     PreparedStatement stat_a = conn.prepareStatement(sql_client);

	     if (rs.next()) {
	    	 stat_a.setInt(1, rs.getInt(1));
	     }

		 stat_a.executeUpdate();
        
        input_nom.clear();
        input_prenom.clear();
        input_email.clear();
        input_telephone.clear();
        input_adresse.clear();
		
	}
	
	
	@FXML TextField input_v_matricule = new TextField();
	@FXML TextField input_v_marque = new TextField();
	@FXML TextField input_v_modele = new TextField();
	@FXML TextField input_v_kilometrage = new TextField();
	@FXML TextField input_v_climatisation = new TextField();
	@FXML TextField input_v_BoiteDeVitesse = new TextField();
	@FXML TextField input_v_type = new TextField();
	public void addButtonClicked_vehicule(ActionEvent event) throws SQLException {
		
		 vehicule_table.getItems().clear();
		
		 String sql = "INSERT INTO vehicule (matricule, marque, modele, kilometrage, climatisation, typeBoiteDeVitesse, type) Values (?,?,?,?,?,?,?)";
		 
		 PreparedStatement stat = conn.prepareStatement(sql);
	     
		 stat.setString(1, input_v_matricule.getText().toString());
		 stat.setString(2, input_v_marque.getText().toString());
		 stat.setString(3, input_v_modele.getText().toString());
		 stat.setInt(4, Integer.valueOf(input_v_kilometrage.getText().toString()));
		 stat.setBoolean(5, Boolean.valueOf(input_v_climatisation.getText().toString()));
		 stat.setString(6, input_v_BoiteDeVitesse.getText().toString());
		 stat.setString(7, input_v_type.getText().toString());
 
	     stat.executeUpdate();     
       
	     input_v_matricule.clear();
	     input_v_marque.clear();
	     input_v_modele.clear();
	     input_v_kilometrage.clear();
	     input_v_climatisation.clear();
	     input_v_BoiteDeVitesse.clear();
	     input_v_type.clear();	
	     
	     print_vehicules(event);
	}
	
	public void getSelected_v(MouseEvent event) throws IOException{
		int index = vehicule_table.getSelectionModel().getSelectedIndex();
		vehiculeselected = vehicule_table.getSelectionModel().getSelectedItems();
		
		input_v_matricule.setText(matricule.getCellData(index).toString());
		input_v_marque.setText(marque.getCellData(index).toString());
		input_v_modele.setText(modele.getCellData(index).toString());
		input_v_kilometrage.setText(kilometrage.getCellData(index).toString());
		input_v_climatisation.setText(climatisation.getCellData(index).toString());
		input_v_BoiteDeVitesse.setText(typeBoiteDeVitesse.getCellData(index).toString());
		input_v_type.setText(type.getCellData(index).toString());
	}
	
	public void getSelected(MouseEvent event) throws IOException{
		int index = client_table.getSelectionModel().getSelectedIndex();
		clientselected = client_table.getSelectionModel().getSelectedItems();
		
		input_nom.setText(nom.getCellData(index).toString());
		input_prenom.setText(prenom.getCellData(index).toString());
		input_email.setText(email.getCellData(index).toString());
		input_telephone.setText(telephone.getCellData(index).toString());
		input_adresse.setText(idAdresse.getCellData(index).toString());
	}
	
	
	@FXML 
	ObservableList<Utilisateur> clientselected, allclient; 
	public void deleteButtonClicked(ActionEvent event) throws SQLException, IOException {
		
		allclient = client_table.getItems();
		clientselected = client_table.getSelectionModel().getSelectedItems();
		//System.out.println(clientselected.get(0).getId());
		String sql = ("DELETE FROM utilisateur where idUtilisateur = ? ");
		String sql_client = ("DELETE FROM client where idUtilisateur = ? ");
		
		PreparedStatement pst = conn.prepareStatement(sql);
		PreparedStatement pst_client = conn.prepareStatement(sql_client);
		
		pst.setInt(1, clientselected.get(0).getId());
		pst_client.setInt(1, clientselected.get(0).getId());
		//System.out.println(pst);
		pst.executeUpdate();
        pst_client.executeUpdate();
        //go_to_gestion_client(event);
        print_clients(event);
	}
	
	@FXML 
	ObservableList<Vehicule> vehiculeselected, allvehicule; 
	public void deleteButtonClicked_v(ActionEvent event) throws SQLException, IOException {
		
		allvehicule = vehicule_table.getItems();
		vehiculeselected = vehicule_table.getSelectionModel().getSelectedItems();
		//System.out.println(vehiculeselected.get(0).getMatricule());
		String sql = ("DELETE FROM vehicule where matricule = ? ");
		
		PreparedStatement pst = conn.prepareStatement(sql);
		
		pst.setString(1, vehiculeselected.get(0).getMatricule());
		//System.out.println(pst);
		
        pst.executeUpdate();
        //go_to_gestion_client(event);
        print_vehicules(event);
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
		client_table.getItems().clear();
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
	
	
	public void print_clients_loueur(ActionEvent event) throws SQLException{
		client_table.getItems().clear();
        String sql = "Select utilisateur.* FROM utilisateur NATURAL JOIN client NATURAL JOIN loue GROUP BY idUtilisateur";
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
	
	@FXML TextField input_client_matricule_recherche = new TextField();
	public void rechercher_client_matricule(ActionEvent event) throws SQLException{
		
		client_table.getItems().clear();
        String sql = "Select utilisateur.* FROM utilisateur NATURAL JOIN loue WHERE matricule = ?";
        PreparedStatement stat = conn.prepareStatement(sql);
        stat.setString(1, input_client_matricule_recherche.getText().toString());
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
	
	
	
	public void updateClientInfo(ActionEvent event) throws SQLException{
		
		//System.out.println("Balise A");
		int index = client_table.getSelectionModel().getSelectedIndex();
		String idUtilisateur_concerne = idUtilisateur.getCellData(index).toString();
		String sql = "UPDATE utilisateur SET nom = ? ,prenom = ? ,email = ?, telephone = ?, idAdresse = ? WHERE idUtilisateur = ?";
		 
		 PreparedStatement stat = conn.prepareStatement(sql);
	     
		 //System.out.println("Balise B");
		 
		 stat.setString(1, input_nom.getText().toString());
		 stat.setString(2, input_prenom.getText().toString());
		 stat.setString(3, input_email.getText().toString());
		 stat.setString(4, input_telephone.getText().toString());
		 stat.setString(5, input_adresse.getText().toString());
		 stat.setInt(6, Integer.valueOf(idUtilisateur_concerne));
	     stat.executeUpdate();
	     client_table.getItems().clear();
	     print_clients(event);
	}
	
	
	public void updateVehiculeInfo(ActionEvent event) throws SQLException{
		
		//System.out.println("Balise A");
		int index = vehicule_table.getSelectionModel().getSelectedIndex();
		String matricule_concerne = matricule.getCellData(index).toString();
		String sql = "UPDATE vehicule SET matricule = ? ,marque = ? ,modele = ?, kilometrage = ?, climatisation = ?, typeBoiteDeVitesse = ?, type = ?  WHERE matricule = ?";
		 
		 PreparedStatement stat = conn.prepareStatement(sql);
	     
		 //System.out.println("Balise B");
		 
		 stat.setString(1, input_v_matricule.getText().toString());
		 stat.setString(2, input_v_marque.getText().toString());
		 stat.setString(3, input_v_modele.getText().toString());
		 stat.setInt(4, Integer.valueOf(input_v_kilometrage.getText().toString()));
		 stat.setBoolean(5, Boolean.valueOf(input_v_climatisation.getText().toString()));
		 stat.setString(6, input_v_BoiteDeVitesse.getText().toString());
		 stat.setString(7, input_v_type.getText().toString());
		 stat.setString(8, String.valueOf(matricule_concerne));
	     stat.executeUpdate();
	     vehicule_table.getItems().clear();
	     print_vehicules(event);
	}
	
	

	
	
	@FXML private TableView<Vehicule> vehicule_table;
	
	@FXML private TableColumn<Vehicule, String> matricule;
	@FXML private TableColumn<Vehicule, String> marque;
	@FXML private TableColumn<Vehicule, String> modele;
	@FXML private TableColumn<Vehicule, Integer> kilometrage;
	@FXML private TableColumn<Vehicule, Boolean> climatisation;
	@FXML private TableColumn<Vehicule, String> typeBoiteDeVitesse;
	@FXML private TableColumn<Vehicule, String> type;
	public ObservableList<Vehicule> vehicules = FXCollections.observableArrayList();
	@FXML
	public void print_vehicules(ActionEvent event) throws SQLException {
		vehicule_table.getItems().clear();
        String sql = "Select * FROM vehicule";
        PreparedStatement stat = conn.prepareStatement(sql);
        ResultSet rs = stat.executeQuery();
        
        while(rs.next()) {
        	vehicules.add(new Vehicule(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getBoolean(5), rs.getString(6),rs.getString(7)));
        }
        
        matricule.setCellValueFactory(new PropertyValueFactory<Vehicule, String>("matricule"));
        marque.setCellValueFactory(new PropertyValueFactory<Vehicule, String>("marque"));
        modele.setCellValueFactory(new PropertyValueFactory<Vehicule, String>("modele"));
		kilometrage.setCellValueFactory(new PropertyValueFactory<Vehicule, Integer>("kilometrage"));
		climatisation.setCellValueFactory(new PropertyValueFactory<Vehicule, Boolean>("climatisation"));
		typeBoiteDeVitesse.setCellValueFactory(new PropertyValueFactory<Vehicule, String>("typeBoiteDeVitesse"));
		type.setCellValueFactory(new PropertyValueFactory<Vehicule, String>("type"));
		
		vehicule_table.setItems(vehicules);
	}
	
	@FXML TextField input_marque_recherche = new TextField();
	public void print_vehicules_par_marque(ActionEvent event) throws SQLException {
		vehicule_table.getItems().clear();
        String sql = "Select * FROM vehicule where marque = ?";
	     
        PreparedStatement stat = conn.prepareStatement(sql);
        stat.setString(1, input_marque_recherche.getText().toString());
        ResultSet rs = stat.executeQuery();
        
        while(rs.next()) {
        	vehicules.add(new Vehicule(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getBoolean(5), rs.getString(6),rs.getString(7)));
        }
        
        matricule.setCellValueFactory(new PropertyValueFactory<Vehicule, String>("matricule"));
        marque.setCellValueFactory(new PropertyValueFactory<Vehicule, String>("marque"));
        modele.setCellValueFactory(new PropertyValueFactory<Vehicule, String>("modele"));
		kilometrage.setCellValueFactory(new PropertyValueFactory<Vehicule, Integer>("kilometrage"));
		climatisation.setCellValueFactory(new PropertyValueFactory<Vehicule, Boolean>("climatisation"));
		typeBoiteDeVitesse.setCellValueFactory(new PropertyValueFactory<Vehicule, String>("typeBoiteDeVitesse"));
		type.setCellValueFactory(new PropertyValueFactory<Vehicule, String>("type"));
		
		vehicule_table.setItems(vehicules);
	}

	public void print_vehicule_loue(ActionEvent event) throws SQLException{
		vehicule_table.getItems().clear();
        String sql = "Select * FROM vehicule where matricule in (SELECT matricule FROM loue)";
        PreparedStatement stat = conn.prepareStatement(sql);
        ResultSet rs = stat.executeQuery();
        
        while(rs.next()) {
        	vehicules.add(new Vehicule(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getBoolean(5), rs.getString(6),rs.getString(7)));
        }
        
        matricule.setCellValueFactory(new PropertyValueFactory<Vehicule, String>("matricule"));
        marque.setCellValueFactory(new PropertyValueFactory<Vehicule, String>("marque"));
        modele.setCellValueFactory(new PropertyValueFactory<Vehicule, String>("modele"));
		kilometrage.setCellValueFactory(new PropertyValueFactory<Vehicule, Integer>("kilometrage"));
		climatisation.setCellValueFactory(new PropertyValueFactory<Vehicule, Boolean>("climatisation"));
		typeBoiteDeVitesse.setCellValueFactory(new PropertyValueFactory<Vehicule, String>("typeBoiteDeVitesse"));
		type.setCellValueFactory(new PropertyValueFactory<Vehicule, String>("type"));
		
		vehicule_table.setItems(vehicules);
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

package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Facture;
import model.Utilisateur;
import model.Vehicule;

public class GerantController extends MainController implements Initializable {

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		try {
			switch (actualscene) {
			
			case "gestionVehicule":
				print_vehicules();
				break;
			case "gestionFinance":

				break;
			case "gestionClient":
				print_clients();
				break;
			case "gestionFacture":
				print_factures();
				break;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	

//  ------------------------------------------------------------------------------------------------------------------------------- //
	//  -----------------------------------------------------GESTION Finance ------------------------------------------------- //
	
	@FXML private DatePicker input_date_un;
	@FXML private DatePicker input_date_deux;
	@FXML private Label recette; 
	
	
	public void print_chiffre_daffaire(ActionEvent event) throws SQLException{
		
		String dateA = input_date_un.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")).toString();
		String dateB = input_date_deux.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")).toString();

		float tarif = 0;
		float duree_effective; 
		float frais_remise = 0;
		float taux_reduction = 0;
		float tarif_total = 0;
		
		String sql_r ="SELECT idUtilisateur, matricule, tarif, caution, dureeEffective, frais_remise, tauxReduction, datePriseDeReservation FROM vehicule NATURAL JOIN categorievehicule NATURAL JOIN loue NATURAL JOIN facture NATURAL JOIN souscrire WHERE datePriseDeReservation BETWEEN ? AND ? ";
		
		PreparedStatement stat = conn.prepareStatement(sql_r);
		
		stat.setDate(1, Date.valueOf(dateA));
		stat.setDate(2, Date.valueOf(dateB));
		System.out.println(stat);
	    ResultSet rs = stat.executeQuery();
	    while(rs.next()) {
	    	//System.out.println("tarf = " + rs.getInt(3) +" / caution = " + rs.getInt(4) +" / dureeEffective = "+ rs.getInt(5)+" / frais_remise = "+ rs.getInt(6) +" / taux de reduction = "+ rs.getFloat(7) );
	    	tarif = Float.valueOf(rs.getInt(3));
	    	duree_effective = Float.valueOf(rs.getInt(5));
	    	frais_remise = Float.valueOf(rs.getInt(6));
	    	taux_reduction = Float.valueOf(rs.getInt(7));
	    	tarif_total += (tarif*duree_effective + frais_remise)*(1 - taux_reduction);
	    	//System.out.println(tarif_total);
	    }
	    recette.setText(String.valueOf(tarif_total));
	    
	}	
	
//-------------------------------------------------------------------------------------------------------------------------------//
	//-----------------------------------------------------GESTION Facture -------------------------------------------------//
	@FXML ObservableList<Facture> factureselected, allfacture;
	
	@FXML TextField input_duree_effective = new TextField();
	@FXML TextField input_consommation = new TextField();
	@FXML TextField input_etat_vehicule = new TextField();
	@FXML TextField input_idAgence = new TextField();
	@FXML TextField input_idClient = new TextField();
	@FXML TextField input_FraisRemise = new TextField();
	
	@FXML private TableView<Facture> facture_table;
	@FXML private TableColumn<Facture, Integer> f_idFacture;
	@FXML private TableColumn<Facture, Integer> f_dureeEffective;
	@FXML private TableColumn<Facture, Float> f_consommationCarburant;
	@FXML private TableColumn<Facture, Integer> f_etatVehicule;
	@FXML private TableColumn<Facture, Integer> f_idAgence;
	@FXML private TableColumn<Facture, Integer> f_idClient;
	@FXML private TableColumn<Facture, Integer> f_fraisRemise;
	
	ObservableList<Facture> factures = FXCollections.observableArrayList();
	
	public void print_factures(ActionEvent event) throws SQLException {
		print_factures();
	}
	
	public void print_factures() throws SQLException {
		facture_table.getItems().clear();
        String sql = "Select * FROM facture";
        //String nb_facture_sql ="SELECT count(*) from facture";
        PreparedStatement stat = conn.prepareStatement(sql);
        //PreparedStatement stat_nb_facture = conn.prepareStatement(nb_facture_sql);
        ResultSet rs = stat.executeQuery();
        //ResultSet nb_facture = stat_nb_facture.executeQuery();
        
        //System.out.println(nb_facture.getInt(0));
        
        while(rs.next()) {
        	factures.add(new Facture(rs.getInt(1), rs.getInt(2),rs.getFloat(3) , rs.getInt(4), rs.getInt(5),rs.getInt(6) , rs.getInt(7)));
        }
        
        f_idFacture.setCellValueFactory(new PropertyValueFactory<Facture, Integer>("idFacture"));

        f_dureeEffective.setCellValueFactory(new PropertyValueFactory<Facture, Integer>("dureeEffective"));
    
        f_consommationCarburant.setCellValueFactory(new PropertyValueFactory<Facture, Float>("consomationCarburant"));

        f_etatVehicule.setCellValueFactory(new PropertyValueFactory<Facture, Integer>("etatVehicule"));

        f_idAgence.setCellValueFactory(new PropertyValueFactory<Facture, Integer>("idAgence"));

        f_idClient.setCellValueFactory(new PropertyValueFactory<Facture, Integer>("idUtilisateur"));
  
        f_fraisRemise.setCellValueFactory(new PropertyValueFactory<Facture, Integer>("frais_remise"));
 
		facture_table.setItems(factures);
	}
	
	public void getSelected_f(MouseEvent event) throws IOException{
		int index = facture_table.getSelectionModel().getSelectedIndex();
		factureselected = facture_table.getSelectionModel().getSelectedItems();
		
		input_duree_effective.setText(f_dureeEffective.getCellData(index).toString());
		input_consommation.setText(f_consommationCarburant.getCellData(index).toString());
		input_etat_vehicule.setText(f_etatVehicule.getCellData(index).toString());
		input_idAgence.setText(f_idAgence.getCellData(index).toString());
		input_idClient.setText(f_idClient.getCellData(index).toString());
		input_FraisRemise.setText(f_fraisRemise.getCellData(index).toString());
	}

	public void updateFactureInfo(ActionEvent event) throws SQLException{
		int index = facture_table.getSelectionModel().getSelectedIndex();
		String idFacture_concerne = f_idFacture.getCellData(index).toString();
		String sql = "UPDATE facture SET dureeEffective = ? ,consomationCarburant = ? ,etatVehicule = ?, idAgence = ?, idUtilisateur = ?, frais_remise = ? WHERE idFacture = ?";
		 
		PreparedStatement stat = conn.prepareStatement(sql);
		
		stat.setInt(1, Integer.valueOf(input_duree_effective.getText().toString()));
		stat.setFloat(2, Float.valueOf(input_consommation.getText().toString()));
		stat.setInt(3, Integer.valueOf(input_etat_vehicule.getText().toString()));
		stat.setInt(4, Integer.valueOf(input_idAgence.getText().toString()));
		stat.setInt(5, Integer.valueOf(input_idClient.getText().toString()));
		stat.setInt(6, Integer.valueOf(input_FraisRemise.getText().toString()));
		stat.setInt(7, Integer.valueOf(idFacture_concerne));
	    stat.executeUpdate();
	    print_factures(event);
	}
	
	//-------------------------------------------------------------------------------------------------------------------------------//
	//-----------------------------------------------------GESTION CLIENT--------------------------------------------------------//
	
	@FXML TextField input_nom = new TextField();
	@FXML TextField input_prenom = new TextField();
	@FXML TextField input_email = new TextField();
	@FXML TextField input_telephone = new TextField();
	@FXML TextField input_adresse = new TextField();
	
	@FXML private TableView<Utilisateur> client_table;
	@FXML private TableColumn<Utilisateur, String> nom;
	@FXML private TableColumn<Utilisateur, String> prenom;
	@FXML private TableColumn<Utilisateur, String> email;
	@FXML private TableColumn<Utilisateur, String> telephone;
	@FXML private TableColumn<Utilisateur, String> idAdresse;
	@FXML private TableColumn<Utilisateur, Integer> idUtilisateur;
	
	ObservableList<Utilisateur> clients = FXCollections.observableArrayList();
	
	@FXML ObservableList<Utilisateur> clientselected, allclient; 
	
	@FXML TextField input_client_matricule_recherche = new TextField();
	
	public void print_clients(ActionEvent event) throws SQLException {
		print_clients();
	}
	
	public void print_clients() throws SQLException {
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
	
	public void addButtonClicked(ActionEvent event) throws SQLException {
		
		 //client_table.getItems().clear();
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
        print_clients(event);
	}
	
	public void updateClientInfo(ActionEvent event) throws SQLException{
		
		int index = client_table.getSelectionModel().getSelectedIndex();
		String idUtilisateur_concerne = idUtilisateur.getCellData(index).toString();
		String sql = "UPDATE utilisateur SET nom = ? ,prenom = ? ,email = ?, telephone = ?, idAdresse = ? WHERE idUtilisateur = ?";
		 
		PreparedStatement stat = conn.prepareStatement(sql);
		 
		stat.setString(1, input_nom.getText().toString());
		stat.setString(2, input_prenom.getText().toString());
		stat.setString(3, input_email.getText().toString());
		stat.setString(4, input_telephone.getText().toString());
		stat.setString(5, input_adresse.getText().toString());
		stat.setInt(6, Integer.valueOf(idUtilisateur_concerne));
	    stat.executeUpdate();
	    print_clients(event);
	}
	
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
        client_table.getItems().clear();
        print_clients(event);
	}
	
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
	
	public void getSelected(MouseEvent event) throws IOException{
		int index = client_table.getSelectionModel().getSelectedIndex();
		clientselected = client_table.getSelectionModel().getSelectedItems();
		
		input_nom.setText(nom.getCellData(index).toString());
		input_prenom.setText(prenom.getCellData(index).toString());
		input_email.setText(email.getCellData(index).toString());
		input_telephone.setText(telephone.getCellData(index).toString());
		input_adresse.setText(idAdresse.getCellData(index).toString());
	}
	
	public void print_clients_non_loueur(ActionEvent event) throws SQLException{
		client_table.getItems().clear();
        String sql = "SELECT utilisateur.* FROM utilisateur NATURAL JOIN client WHERE utilisateur.idUtilisateur not in (SELECT idUtilisateur from loue) ";
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
	
//-------------------------------------------------------------------------------------------------------------------------------//
	//-----------------------------------------------------GESTION VEHICULE -------------------------------------------------//
	
	@FXML private TableView<Vehicule> vehicule_table;
	@FXML private TableColumn<Vehicule, String> matricule;
	@FXML private TableColumn<Vehicule, String> marque;
	@FXML private TableColumn<Vehicule, String> modele;
	@FXML private TableColumn<Vehicule, Integer> kilometrage;
	@FXML private TableColumn<Vehicule, Boolean> climatisation;
	@FXML private TableColumn<Vehicule, String> typeBoiteDeVitesse;
	@FXML private TableColumn<Vehicule, String> type;
	@FXML private TableColumn<Vehicule, Integer> idAgence_v;
	
	@FXML TextField input_v_matricule = new TextField();
	@FXML TextField input_v_marque = new TextField();
	@FXML TextField input_v_modele = new TextField();
	@FXML TextField input_v_kilometrage = new TextField();
	@FXML TextField input_v_climatisation = new TextField();
	@FXML TextField input_v_BoiteDeVitesse = new TextField();
	@FXML TextField input_v_type = new TextField();
	@FXML TextField input_v_idAgence = new TextField();
	
	@FXML TextField input_marque_recherche = new TextField();
	
	@FXML ObservableList<Vehicule> vehiculeselected, allvehicule; 
	
	ObservableList<Vehicule> vehicules = FXCollections.observableArrayList();
	
	
	public void updateVehiculeInfo(ActionEvent event) throws SQLException {
		
		int index = vehicule_table.getSelectionModel().getSelectedIndex();
		String matricule_concerne = matricule.getCellData(index).toString();
		String sql = "UPDATE vehicule SET matricule = ? ,marque = ? ,modele = ?, kilometrage = ?, climatisation = ?, typeBoiteDeVitesse = ?, type = ?, idAgence = ?  WHERE matricule = ?";
		 
		PreparedStatement stat = conn.prepareStatement(sql); 
		 
		stat.setString(1, input_v_matricule.getText().toString());
		stat.setString(2, input_v_marque.getText().toString());
		stat.setString(3, input_v_modele.getText().toString());
		stat.setInt(4, Integer.valueOf(input_v_kilometrage.getText().toString()));
		stat.setBoolean(5, Boolean.valueOf(input_v_climatisation.getText().toString()));
		stat.setString(6, input_v_BoiteDeVitesse.getText().toString());
		stat.setString(7, input_v_type.getText().toString());
		stat.setString(8, input_v_idAgence.getText().toString());
		stat.setString(9, String.valueOf(matricule_concerne));
		stat.executeUpdate();
		
		try {
			vehicule_table.getItems().clear();
			print_vehicules(event);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void print_vehicules(ActionEvent event) throws SQLException {
		vehicule_table.getItems().clear();
		print_vehicules();
	}
	
	public void print_vehicules() throws SQLException {
		
		String sql = "Select * FROM vehicule";
        PreparedStatement stat = conn.prepareStatement(sql);
        ResultSet rs = stat.executeQuery();
        
        while(rs.next()) {
        	vehicules.add(new Vehicule(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getBoolean(5), rs.getString(6),rs.getString(7),rs.getInt(8)));
        }
        
        matricule.setCellValueFactory(new PropertyValueFactory<Vehicule, String>("matricule"));
        marque.setCellValueFactory(new PropertyValueFactory<Vehicule, String>("marque"));
        modele.setCellValueFactory(new PropertyValueFactory<Vehicule, String>("modele"));
		kilometrage.setCellValueFactory(new PropertyValueFactory<Vehicule, Integer>("kilometrage"));
		climatisation.setCellValueFactory(new PropertyValueFactory<Vehicule, Boolean>("climatisation"));
		typeBoiteDeVitesse.setCellValueFactory(new PropertyValueFactory<Vehicule, String>("typeBoiteDeVitesse"));
		type.setCellValueFactory(new PropertyValueFactory<Vehicule, String>("type"));
		idAgence_v.setCellValueFactory(new PropertyValueFactory<Vehicule, Integer>("idAgence"));
		
		vehicule_table.setItems(vehicules);
	}
	
	public void addButtonClicked_vehicule(ActionEvent event) throws SQLException {
		
		 vehicule_table.getItems().clear();
		
		 String sql = "INSERT INTO vehicule (matricule, marque, modele, kilometrage, climatisation, typeBoiteDeVitesse, type, idAgence) Values (?,?,?,?,?,?,?,?)";
		 
		 PreparedStatement stat = conn.prepareStatement(sql);
	     
		 stat.setString(1, input_v_matricule.getText().toString());
		 stat.setString(2, input_v_marque.getText().toString());
		 stat.setString(3, input_v_modele.getText().toString());
		 stat.setInt(4, Integer.valueOf(input_v_kilometrage.getText().toString()));
		 stat.setBoolean(5, Boolean.valueOf(input_v_climatisation.getText().toString()));
		 stat.setString(6, input_v_BoiteDeVitesse.getText().toString());
		 stat.setString(7, input_v_type.getText().toString());
		 stat.setString(8, input_v_idAgence.getText().toString());
		 
 
	     stat.executeUpdate();     
	     
	     print_vehicules(event);
	}
	
	public void print_vehicule_loue(ActionEvent event) throws SQLException{
		vehicule_table.getItems().clear();
        String sql = "Select * FROM vehicule where matricule in (SELECT matricule FROM loue)";
        PreparedStatement stat = conn.prepareStatement(sql);
        ResultSet rs = stat.executeQuery();
        
        while(rs.next()) {
        	vehicules.add(new Vehicule(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getBoolean(5), rs.getString(6),rs.getString(7),rs.getInt(8)));
        }
        
        matricule.setCellValueFactory(new PropertyValueFactory<Vehicule, String>("matricule"));
        marque.setCellValueFactory(new PropertyValueFactory<Vehicule, String>("marque"));
        modele.setCellValueFactory(new PropertyValueFactory<Vehicule, String>("modele"));
		kilometrage.setCellValueFactory(new PropertyValueFactory<Vehicule, Integer>("kilometrage"));
		climatisation.setCellValueFactory(new PropertyValueFactory<Vehicule, Boolean>("climatisation"));
		typeBoiteDeVitesse.setCellValueFactory(new PropertyValueFactory<Vehicule, String>("typeBoiteDeVitesse"));
		type.setCellValueFactory(new PropertyValueFactory<Vehicule, String>("type"));
		idAgence_v.setCellValueFactory(new PropertyValueFactory<Vehicule, Integer>("idAgence"));
		
		vehicule_table.setItems(vehicules);
	}
	
	public void deleteButtonClicked_v(ActionEvent event) throws SQLException, IOException {
		
		allvehicule = vehicule_table.getItems();
		vehiculeselected = vehicule_table.getSelectionModel().getSelectedItems();
		//System.out.println(vehiculeselected.get(0).getMatricule());
		String sql = ("DELETE FROM vehicule where matricule = ? ");
		
		PreparedStatement pst = conn.prepareStatement(sql);
		
		pst.setString(1, vehiculeselected.get(0).getMatricule());
		//System.out.println(pst);
		
        pst.executeUpdate();
//        go_to_gestion_client(event);
        vehicule_table.getItems().clear();
        print_vehicules(event);
	}
	
	public void print_vehicules_par_marque(ActionEvent event) throws SQLException {
		vehicule_table.getItems().clear();
        String sql = "Select * FROM vehicule where marque = ?";
	     
        PreparedStatement stat = conn.prepareStatement(sql);
        stat.setString(1, input_marque_recherche.getText().toString());
        ResultSet rs = stat.executeQuery();
        
        while(rs.next()) {
        	vehicules.add(new Vehicule(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getBoolean(5), rs.getString(6),rs.getString(7), rs.getInt(8)));
        }
        
        matricule.setCellValueFactory(new PropertyValueFactory<Vehicule, String>("matricule"));
        marque.setCellValueFactory(new PropertyValueFactory<Vehicule, String>("marque"));
        modele.setCellValueFactory(new PropertyValueFactory<Vehicule, String>("modele"));
		kilometrage.setCellValueFactory(new PropertyValueFactory<Vehicule, Integer>("kilometrage"));
		climatisation.setCellValueFactory(new PropertyValueFactory<Vehicule, Boolean>("climatisation"));
		typeBoiteDeVitesse.setCellValueFactory(new PropertyValueFactory<Vehicule, String>("typeBoiteDeVitesse"));
		type.setCellValueFactory(new PropertyValueFactory<Vehicule, String>("type"));
		idAgence_v.setCellValueFactory(new PropertyValueFactory<Vehicule, Integer>("idAgence"));
		
		vehicule_table.setItems(vehicules);
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
		input_v_idAgence.setText(idAgence_v.getCellData(index).toString());
	}
	
	//--------------------------------------------------------------------------------------------------------//
	
	
	@FXML public void go_to_gestion_vehicule(ActionEvent event) throws IOException {
		actualscene = "gestionVehicule";
		Parent root = FXMLLoader.load(getClass().getResource("/view/GerantArea_gestionVehicule.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	@FXML public void go_to_gestion_finance(ActionEvent event) throws IOException {
		actualscene = "gestionFinance";
		Parent root = FXMLLoader.load(getClass().getResource("/view/GerantArea_gestionFinance.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
		
	}
	
	@FXML public void go_to_gestion_client(ActionEvent event) throws IOException {
		actualscene = "gestionClient";
		Parent root = FXMLLoader.load(getClass().getResource("/view/GerantArea_gestionClient.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	@FXML public void go_to_gestion_facture(ActionEvent event) throws IOException {
		actualscene = "gestionFacture";
		Parent root = FXMLLoader.load(getClass().getResource("/view/GerantArea_gestionFacture.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
}

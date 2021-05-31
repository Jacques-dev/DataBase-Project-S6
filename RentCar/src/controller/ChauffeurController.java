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
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import model.Circuit;
import model.Vehicule;

public class ChauffeurController extends MainController implements Initializable {

	
	@Override 
	public void initialize(URL url, ResourceBundle rb) {
	
		try {
			print_vehicules();
			print_circuits();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//print_circuits();
			
	
	}
	
	@FXML private TableView<Vehicule> vehicule_table;
	@FXML private TableColumn<Vehicule, String> matricule;
	@FXML private TableColumn<Vehicule, String> marque;
	@FXML private TableColumn<Vehicule, String> modele;
	@FXML private TableColumn<Vehicule, Integer> kilometrage;
	@FXML private TableColumn<Vehicule, Boolean> climatisation;
	@FXML private TableColumn<Vehicule, String> typeBoiteDeVitesse;
	@FXML private TableColumn<Vehicule, String> type;
	@FXML private TableColumn<Vehicule, Integer> idAgence_v;
	
	@FXML private TableView<Circuit> circuit_table;
	@FXML private TableColumn<Circuit, Integer> id_circuit;
	@FXML private TableColumn<Circuit, Integer> id_AgenceDepart;
	@FXML private TableColumn<Circuit, Integer> id_AgenceArrivee;
	@FXML private TableColumn<Circuit, String> chemin_a;
	@FXML private TableColumn<Circuit, String> chemin_b;
	@FXML private TableColumn<Circuit, String> chemin_c;
	

	@FXML Label lbl_marque = new Label();
	@FXML Label lbl_matricule = new Label();
	@FXML Label lbl_modele = new Label();
	@FXML TextField input_nouvelleAgence = new TextField();
	@FXML TextField input_AgenceDepart = new TextField();
	@FXML TextField input_AgenceArrivee = new TextField();
	
	
	
	@FXML ObservableList<Vehicule> vehiculeselected, allvehicule; 
	
	ObservableList<Vehicule> vehicules = FXCollections.observableArrayList();
	
	@FXML ObservableList<Circuit> circuitselected, allcircuits; 
	
	ObservableList<Circuit> circuits = FXCollections.observableArrayList();
	
	public void getSelected_v(MouseEvent event) throws IOException{
		int index = vehicule_table.getSelectionModel().getSelectedIndex();
		vehiculeselected = vehicule_table.getSelectionModel().getSelectedItems();
		
		lbl_matricule.setText(matricule.getCellData(index).toString());
		lbl_marque.setText(marque.getCellData(index).toString());
		lbl_modele.setText(modele.getCellData(index).toString());
		input_nouvelleAgence.setText(idAgence_v.getCellData(index).toString());
	}
	
	public void updateVehiculeInfo(ActionEvent event) throws SQLException {
		
		int index = vehicule_table.getSelectionModel().getSelectedIndex();
		String matricule_concerne = matricule.getCellData(index).toString();
		String sql = "UPDATE vehicule SET idAgence = ?  WHERE matricule = ?";
		 
		PreparedStatement stat = conn.prepareStatement(sql); 
		 
		stat.setString(1, input_nouvelleAgence.getText().toString());
		stat.setString(2, String.valueOf(matricule_concerne));
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
	
	public void print_circuits(ActionEvent event) throws SQLException {
		//circuit_table.getItems().clear();
		print_circuits();
	}
	
	public void print_circuits() throws SQLException {
		circuit_table.getItems().clear();
		String sql = "Select * FROM circuit";
        PreparedStatement stat = conn.prepareStatement(sql);
        ResultSet rs = stat.executeQuery();
        
        while(rs.next()) {
        	circuits.add(new Circuit(rs.getInt(1),rs.getInt(2),rs.getInt(3),rs.getString(4),rs.getString(5),rs.getString(6)));
        }
        System.out.println(stat);
        id_circuit.setCellValueFactory(new PropertyValueFactory<Circuit, Integer>("idCircuit"));
        id_AgenceDepart.setCellValueFactory(new PropertyValueFactory<Circuit, Integer>("id_Agence_a"));
        id_AgenceArrivee.setCellValueFactory(new PropertyValueFactory<Circuit, Integer>("id_Agence_b"));
        chemin_a.setCellValueFactory(new PropertyValueFactory<Circuit, String>("cheminA"));
        chemin_b.setCellValueFactory(new PropertyValueFactory<Circuit, String>("cheminB"));
        chemin_c.setCellValueFactory(new PropertyValueFactory<Circuit, String>("cheminC"));
		circuit_table.setItems(circuits);
	}
	

	public void research_circuit_between_a_b() throws SQLException{
		circuit_table.getItems().clear();
		
		String sql = "Select * FROM circuit WHERE idAgence_depart = ? and idAgence_arrive = ?";
        PreparedStatement stat = conn.prepareStatement(sql);
		stat.setString(1, input_AgenceDepart.getText().toString());
		stat.setString(2, input_AgenceArrivee.getText().toString());
		
		System.out.println(stat);
        ResultSet rs = stat.executeQuery();
        System.out.println(stat);
        
        while(rs.next()) {
        	circuits.add(new Circuit(rs.getInt(1),rs.getInt(2),rs.getInt(3),rs.getString(4),rs.getString(5),rs.getString(6)));
        }
        
        id_circuit.setCellValueFactory(new PropertyValueFactory<Circuit, Integer>("idCircuit"));
        id_AgenceDepart.setCellValueFactory(new PropertyValueFactory<Circuit, Integer>("id_Agence_a"));
        id_AgenceArrivee.setCellValueFactory(new PropertyValueFactory<Circuit, Integer>("id_Agence_b"));
        chemin_a.setCellValueFactory(new PropertyValueFactory<Circuit, String>("cheminA"));
        chemin_b.setCellValueFactory(new PropertyValueFactory<Circuit, String>("cheminB"));
        chemin_c.setCellValueFactory(new PropertyValueFactory<Circuit, String>("cheminC"));
		circuit_table.setItems(circuits);
	}
}

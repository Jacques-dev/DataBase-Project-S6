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
import javafx.scene.input.MouseEvent; 
import javafx.stage.Stage; 
import model.Devis; 
import model.Facture; 
import model.ProgramFidelite; 
import model.Utilisateur; 
import model.Vehicule; 
 
public class ClientController extends MainController implements Initializable { 
 
	 
	@Override 
	public void initialize(URL url, ResourceBundle rb) { 
		input_etat.getItems().addAll(listOfEtat); 
		input_etat.setOnAction(this::updateEtatLabel); 
		try { 
			switch (actualscene) { 
			case "ClientLocationArea": 
				printVehicules(); 
				printClients(); 
				break; 
			case "ClientRetourArea": 
				printVehiculesLoue(); 
				printClients(); 
				break; 
			case "ClientFideliteArea": 
				printPrograms(); 
				printClients(); 
				break; 
			case "ClientDevis": 
				lbl_MontantReduction.setText(String.valueOf(devis.getMontantReduction())); 
				lbl_dureePrevueDeLocation.setText(String.valueOf(devis.getDurreePrevueLocation())); 
				lbl_idAgence.setText(String.valueOf(devis.getIdAgence())); 
				lbl_idUtilisateur.setText(String.valueOf(devis.getIdUtilisateur())); 
				lbl_matricule.setText(String.valueOf(devis.getMatricule())); 
				break; 
			case "ClientFacture": 
				lbl_dureeEffective.setText(String.valueOf(facture.getDureeEffective())); 
				lbl_consomationCarburant.setText(String.valueOf(facture.getConsomationCarburant())); 
				lbl_etatVehicule.setText(String.valueOf(facture.getEtatVehicule())); 
				lbl_idAgence.setText(String.valueOf(facture.getIdAgence())); 
				lbl_idUtilisateur.setText(String.valueOf(facture.getIdUtilisateur())); 
				lbl_fraisRemise.setText(String.valueOf(facture.getFrais_remise())); 
				break; 
			} 
		} catch (SQLException e) { 
			e.printStackTrace(); 
		} 
	} 
 
	 
	@FXML private TableView<ProgramFidelite> programFideliteTable; 
	@FXML private TableColumn<ProgramFidelite, Integer> idProgrammeFidelite; 
	@FXML private TableColumn<ProgramFidelite, Integer> duree; 
	@FXML private TableColumn<ProgramFidelite, String> description; 
	@FXML private TableColumn<ProgramFidelite, Float> prix; 
	 
	public ObservableList<ProgramFidelite> programs = FXCollections.observableArrayList(); 
	 
	public void printPrograms() throws SQLException { 
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
	@FXML private TableColumn<Vehicule, Integer> idAgence;
	 
	 
	public ObservableList<Vehicule> vehicules = FXCollections.observableArrayList(); 
	 
	public void printVehicules() throws SQLException { 
        String sql = "Select * From vehicule WHERE matricule not in (select matricule from loue)"; 
        PreparedStatement stat = conn.prepareStatement(sql); 
        ResultSet rs = stat.executeQuery(); 
         
        while(rs.next()) {
            vehicules.add(new Vehicule(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getBoolean(5), rs.getString(6), rs.getString(7), rs.getInt(8)));
        }

        matricule.setCellValueFactory(new PropertyValueFactory<Vehicule, String>("matricule"));
        marque.setCellValueFactory(new PropertyValueFactory<Vehicule, String>("marque"));
        modele.setCellValueFactory(new PropertyValueFactory<Vehicule, String>("modele"));
        kilometrage.setCellValueFactory(new PropertyValueFactory<Vehicule, Integer>("kilometrage"));
        climatisation.setCellValueFactory(new PropertyValueFactory<Vehicule, Boolean>("climatisation"));
        typeBoiteDeVitesse.setCellValueFactory(new PropertyValueFactory<Vehicule, String>("typeBoiteDeVitesse"));
        type.setCellValueFactory(new PropertyValueFactory<Vehicule, String>("type"));
        idAgence.setCellValueFactory(new PropertyValueFactory<Vehicule, Integer>("idAgence"));

        vehiculeTable.setItems(vehicules);
	} 
	 
	public void printVehiculesLoue() throws SQLException {
        String sql = "Select * From vehicule WHERE matricule in (select matricule from loue)";
        PreparedStatement stat = conn.prepareStatement(sql);
        ResultSet rs = stat.executeQuery();

        while(rs.next()) {
            vehicules.add(new Vehicule(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getBoolean(5), rs.getString(6), rs.getString(7), rs.getInt(8)));
        }

        matricule.setCellValueFactory(new PropertyValueFactory<Vehicule, String>("matricule"));
        marque.setCellValueFactory(new PropertyValueFactory<Vehicule, String>("marque"));
        modele.setCellValueFactory(new PropertyValueFactory<Vehicule, String>("modele"));
        kilometrage.setCellValueFactory(new PropertyValueFactory<Vehicule, Integer>("kilometrage"));
        climatisation.setCellValueFactory(new PropertyValueFactory<Vehicule, Boolean>("climatisation"));
        typeBoiteDeVitesse.setCellValueFactory(new PropertyValueFactory<Vehicule, String>("typeBoiteDeVitesse"));
        type.setCellValueFactory(new PropertyValueFactory<Vehicule, String>("type"));
        idAgence.setCellValueFactory(new PropertyValueFactory<Vehicule, Integer>("idAgence"));

        vehiculeTable.setItems(vehicules);
    }
	 
	@FXML private TextField filterField; 
	@FXML private TableView<Utilisateur> clientTable; 
	@FXML private TableColumn<Utilisateur, Integer> idUtilisateur; 
	@FXML private TableColumn<Utilisateur, String> nom; 
	@FXML private TableColumn<Utilisateur, String> prenom; 
	 
	 
	public ObservableList<Utilisateur> clients = FXCollections.observableArrayList(); 
	 
	@FXML 
	public void printClients() throws SQLException { 
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
 
	private static Devis devis; 
	@FXML private Label lbl_MontantReduction; 
	@FXML private Label lbl_dureePrevueDeLocation; 
	@FXML private Label lbl_idAgence; 
	@FXML private Label lbl_idUtilisateur; 
	@FXML private Label lbl_matricule; 
	private void printDevis() throws IOException { 
		actualscene = "ClientDevis"; 
		Parent root = FXMLLoader.load(getClass().getResource("/view/Devis.fxml")); 
		Stage newWindow = new Stage(); 
		Scene scene = new Scene(root); 
		newWindow.setTitle("Devis"); 
		newWindow.setScene(scene); 
		newWindow.show(); 
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
	 
	public void updateEtatLabel(ActionEvent event) { 
		String etat = input_etat.getValue().toString(); 
		input_etat_label.setText(etat); 
	} 
	 
	@FXML private Label lbletat; 
	 
	 
	 
	private void createDevis(int duree) throws IOException, SQLException { 
		String sql = "Select * from souscrire where idUtilisateur = ?";
		PreparedStatement pst = conn.prepareStatement(sql); 
		pst.setInt(1, Integer.valueOf(input_idUtilisateur.getText().toString())); 
		ResultSet rs = pst.executeQuery(); 
 
		int montantReduction = 0; 
		if (rs.next()) { 
			montantReduction = 10; 
		} 
 
		sql = "INSERT INTO devis (montantReduction, dureePrevueLocation, idAgence, idUtilisateur, matricule) VALUES (?, ?, ?, ?, ?)"; 
 
		pst = conn.prepareStatement(sql); 
		 
		int idUtilisateur = Integer.valueOf(input_idUtilisateur.getText().toString()); 
		String matricule = input_matricule.getText().toString(); 
 
		pst.setInt(1, montantReduction); 
		pst.setInt(2, duree); 
		pst.setInt(3, 1); 
		pst.setInt(4, idUtilisateur); 
		pst.setString(5, matricule); 
		 
		pst.executeUpdate(); 
		 
		devis = new Devis(montantReduction, duree, 1, idUtilisateur, matricule); 
 
		printDevis(); 
	} 
	 
	private static Facture facture; 
	@FXML private Label lbl_dureeEffective; 
	@FXML private Label lbl_consomationCarburant; 
	@FXML private Label lbl_etatVehicule; 
	@FXML private Label lbl_fraisRemise; 
	private void printFacture() throws IOException { 
		actualscene = "ClientFacture"; 
		Parent root = FXMLLoader.load(getClass().getResource("/view/Facture.fxml")); 
		Stage newWindow = new Stage(); 
		Scene scene = new Scene(root); 
		newWindow.setTitle("Facture"); 
		newWindow.setScene(scene); 
		newWindow.show(); 
	} 
	 
	private void createFacture(String dateOrigineLocation, String mat, int assurance, int frais_remise) throws IOException, SQLException { 
		 
        String sql = "INSERT INTO facture (dureeEffective, consomationCarburant, etatVehicule, idAgence, idUtilisateur, frais_remise) VALUES (?, ?, ?, ?, ?, ?)"; 
		PreparedStatement pst = conn.prepareStatement(sql); 
		 
		int idUtilisateur = Integer.valueOf(input_idUtilisateur.getText().toString()); 
		float consomationCarburant = (float)(Math.random() * ( 50 - 10 )); 
		int etatOrigine = 5; 
		 
		int duree = 0; 
		String dateFinLocation = input_date.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")); 
		
		int dateFinLocationMois = Integer.valueOf(dateFinLocation.split("-")[1]);
		int dateOrigineLocationMois = Integer.valueOf(dateOrigineLocation.split("-")[1]);
		int dateFinLocationJours = Integer.valueOf(dateFinLocation.split("-")[2]); 
		int dateOrigineLocationJours = Integer.valueOf(dateOrigineLocation.split("-")[2]); 
		
		
		duree = 30 * (dateFinLocationMois - dateOrigineLocationMois) + Math.abs(dateFinLocationJours - dateOrigineLocationJours);
		System.out.println(duree);
		 
		pst.setInt(1, duree); 
		pst.setFloat(2, consomationCarburant); 
		pst.setInt(3, etatOrigine); 
		pst.setInt(4, 1); 
		pst.setInt(5, idUtilisateur); 
		pst.setInt(6, frais_remise); 
		 
		 
		pst.executeUpdate(); 
		 
		facture = new Facture(duree, consomationCarburant, etatOrigine, 1, idUtilisateur, frais_remise); 
		printFacture(); 
	} 
	 
	@FXML public void loue(ActionEvent event) { 
		 
		String sql = ("INSERT INTO loue (matricule, idUtilisateur, assurance, duree, datePriseDeReservation) VALUES (?, ?, ?, ?, ?)"); 
		 
		try { 
			PreparedStatement pst = conn.prepareStatement(sql); 
			 
			pst.setString(1, input_matricule.getText().toString()); 
			pst.setInt(2, Integer.valueOf(input_idUtilisateur.getText().toString())); 
			pst.setBoolean(3, input_assurance.isSelected()); 
			pst.setInt(4, Integer.valueOf(input_duree.getText().toString())); 
			pst.setString(5, java.time.LocalDate.now().toString()); 
			 
	        pst.executeUpdate(); 
	         
			lbletat.setText("Location enregistr�"); 
			 
	        createDevis(Integer.valueOf(input_duree.getText().toString())); 
		} catch(Exception e) { 
			lbletat.setText("Location erreur"); 
		} 
		 
		try { 
			vehiculeTable.getItems().clear(); 
			printVehicules(); 
		} catch (SQLException e) { 
			e.printStackTrace(); 
		} 
	} 
 
	@FXML public void reserve(ActionEvent event) { 
		vehiculeTable.getItems().clear(); 
		String sql = "INSERT INTO reserve (matricule, idUtilisateur, dateReservation, datePriseDeReservation) VALUES (?, ?, ?, ?)"; 
 
		try { 
			PreparedStatement pst = conn.prepareStatement(sql); 
			 
			pst.setString(1, input_matricule.getText().toString()); 
			pst.setInt(2, Integer.valueOf(input_idUtilisateur.getText().toString())); 
			String date = input_date.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")); 
			pst.setString(3, date.toString()); 
			pst.setString(4, java.time.LocalDate.now().toString()); 
			 
			pst.executeUpdate(); 
 
			lbletat.setText("R�servation enregistr�"); 
		} catch(Exception e) { 
			lbletat.setText("R�servation erreur"); 
		} 
		 
		try { 
			printVehicules(); 
		} catch (SQLException e) { 
			e.printStackTrace(); 
		} 
	} 
	 
	@FXML public void retourne(ActionEvent event) throws IOException { 
		vehiculeTable.getItems().clear(); 
		String sql = "INSERT INTO retour (matricule, idUtilisateur, date, etatOrigine) VALUES (?, ?, ?, ?)"; 
		String sql2 = "DELETE FROM loue WHERE matricule = ? AND idUtilisateur = ?"; 
		String dateOrigine = ""; 
 
		try { 
			PreparedStatement pst = conn.prepareStatement(sql); 
			pst.setString(1, input_matricule.getText().toString()); 
			pst.setInt(2, Integer.valueOf(input_idUtilisateur.getText().toString())); 
			String date = input_date.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")); 
			pst.setString(3, date.toString()); 
			pst.setInt(4, Integer.valueOf(input_etat_label.getText().toString())); 
			 
			pst.executeUpdate(); 
			 
			try { 
				PreparedStatement pst2 = conn.prepareStatement(sql2); 
				pst2.setString(1, input_matricule.getText().toString()); 
				pst2.setInt(2, Integer.valueOf(input_idUtilisateur.getText().toString())); 
				 
				sql = "Select duree, datePriseDeReservation from loue where idUtilisateur = ? and matricule = ?"; 
				pst = conn.prepareStatement(sql); 
				pst.setInt(1, Integer.valueOf(input_idUtilisateur.getText().toString())); 
				pst.setString(2, input_matricule.getText().toString()); 
				ResultSet rs = pst.executeQuery(); 
				 
				if (rs.next()) { 
					dateOrigine = rs.getString(2); 
				} 
				 
				pst2.executeUpdate(); 
				 
				lbletat.setText("Retour enregistr�"); 
			} catch (SQLException e) { 
				lbletat.setText("Retour erreur (suppression)"); 
			} 
		} catch(Exception e) { 
			lbletat.setText("Retour erreur (insertion)"); 
		} 
		 
		try { 
			String sql3 = "Select assurance FROM loue where idUtilisateur = ? and matricule = ?"; 
			PreparedStatement pst3 = conn.prepareStatement(sql3); 
			pst3.setInt(1, Integer.valueOf(input_idUtilisateur.getText().toString())); 
			pst3.setString(2, input_matricule.getText().toString()); 
			 
			ResultSet rs = pst3.executeQuery(); 
			 
			int assurance = 0; 
			int etatOrigine = 5; 
			int etatFin = ((int)(Math.random() * ( 5 - 1 ))); 
			int frais_remise = ((etatOrigine - etatFin )* 20); 
			 
	        while (rs.next()) { 
	        	if (rs.getInt(1) == 1) { 
	        		assurance = 0; 
	    		} else { 
	    			assurance = frais_remise; 
	    		} 
	        } 
	         
			createFacture(dateOrigine, input_matricule.getText().toString(), assurance, frais_remise); 
		} catch (SQLException e) { 
			e.printStackTrace(); 
		} 
		 
		try { 
			printVehiculesLoue(); 
		} catch (SQLException e) { 
			e.printStackTrace(); 
		} 
		 
	} 
	 
	@FXML public void souscrit(ActionEvent event) { 
		String sql = "INSERT INTO souscrire (idUtilisateur, idProgrammeFidelite, dateSouscription, tauxReduction) VALUES (?, ?, ?, ?)"; 
 
		try { 
			PreparedStatement pst = conn.prepareStatement(sql); 
			 
			pst.setInt(1, Integer.valueOf(input_idUtilisateur.getText().toString())); 
			System.out.println(pst); 
			pst.setInt(2, Integer.valueOf(input_idProgramFidelite.getText().toString())); 
			System.out.println(pst); 
			pst.setString(3, java.time.LocalDate.now().toString()); 
			System.out.println(pst); 
			float i; 
			if (input_tauxReduction.isSelected()) { 
				i = 25; 
			} else { 
				i = 0; 
			} 
			pst.setFloat(4, i); 
			System.out.println(pst); 
 
			pst.executeUpdate(); 
 
			lbletat.setText("Souscription enregistr�"); 
		} catch(Exception e) { 
			lbletat.setText("Souscription erreur"); 
		} 
	} 
 
	 
	public void goToClientLocationArea(ActionEvent event) throws IOException, SQLException { 
		actualscene = "ClientLocationArea"; 
		Parent root = FXMLLoader.load(getClass().getResource("/view/ClientLocationArea.fxml")); 
		stage = (Stage)((Node)event.getSource()).getScene().getWindow(); 
		scene = new Scene(root); 
		stage.setScene(scene); 
		stage.show(); 
	} 
	 
	public void goToClientFideliteArea(ActionEvent event) throws IOException, SQLException { 
		actualscene = "ClientFideliteArea"; 
		Parent root = FXMLLoader.load(getClass().getResource("/view/ClientFideliteArea.fxml")); 
		stage = (Stage)((Node)event.getSource()).getScene().getWindow(); 
		scene = new Scene(root); 
		stage.setScene(scene); 
		stage.show(); 
	} 
	 
	public void goToClientRetourArea(ActionEvent event) throws IOException, SQLException { 
		actualscene = "ClientRetourArea"; 
		Parent root = FXMLLoader.load(getClass().getResource("/view/ClientRetourArea.fxml")); 
		stage = (Stage)((Node)event.getSource()).getScene().getWindow(); 
		scene = new Scene(root); 
		stage.setScene(scene); 
		stage.show(); 
	} 
	 
	public void getSelected_v_a_louer(MouseEvent event) throws IOException{ 
		int index = vehiculeTable.getSelectionModel().getSelectedIndex(); 
		//vehicule = vehiculeTable.getSelectionModel().getSelectedItems(); 
		input_matricule.setText(matricule.getCellData(index).toString()); 
		 
	} 
	public void getSelected_id_loueur(MouseEvent event) throws IOException{ 
		int index = clientTable.getSelectionModel().getSelectedIndex(); 
		//vehicule = vehiculeTable.getSelectionModel().getSelectedItems(); 
		input_idUtilisateur.setText(idUtilisateur.getCellData(index).toString()); 
	} 
	public void getSelected_id_program(MouseEvent event) throws IOException{ 
		int index = programFideliteTable.getSelectionModel().getSelectedIndex(); 
		//vehicule = vehiculeTable.getSelectionModel().getSelectedItems(); 
		input_idProgramFidelite.setText(idProgrammeFidelite.getCellData(index).toString()); 
	} 
} 
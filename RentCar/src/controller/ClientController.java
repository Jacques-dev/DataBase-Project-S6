package controller;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.ProgramFidelite;

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

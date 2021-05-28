package controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MainController extends Main {
	
	
	@FXML
	private Button btn;
	
	public void exit(ActionEvent event) throws SQLException {
		
		Statement s = conn.createStatement();
        String sql = "Select * From ELEVES; ";
        
        ResultSet rs = s.executeQuery(sql);
        
        int i = 1 ;
        while(rs.next()){
            System.out.println("----- Ligne N° : " + i++ + "-----");
            System.out.println(rs.getString("NOM")  +  " - " +rs.getString("PRENOM"));
        }
		
		System.out.println("au revoir");
		System.exit(0);
	}
	
	
}

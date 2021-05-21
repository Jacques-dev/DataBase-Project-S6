package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MainController {
	
	
	@FXML
	private Button btn;
	
	public void exit(ActionEvent event) {
		System.out.println("au revoir");
		System.exit(0);
	}
	
	
}

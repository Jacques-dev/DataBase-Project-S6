package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MainController {
	
	
	@FXML
	private Button btn;
	
	public void exit(ActionEvent event) {
		System.out.println("salut");
		System.exit(0);
	}
	
	
}

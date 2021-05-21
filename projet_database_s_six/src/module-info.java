module Project {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics;
	requires javafx.base;
	
	opens controller to javafx.graphics, javafx.fxml;
}

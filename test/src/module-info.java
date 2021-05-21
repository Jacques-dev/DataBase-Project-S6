module test {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.base;
	
	opens controller to javafx.graphics, javafx.fxml;
}

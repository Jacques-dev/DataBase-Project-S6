module test {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.base;
	
	requires java.sql;
	requires javafx.graphics;
	
	opens controller to javafx.graphics, javafx.fxml;
}

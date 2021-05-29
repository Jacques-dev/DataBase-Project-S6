module test {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.base;
	
	requires java.sql;
	requires javafx.graphics;
	
	exports model;
	
	opens controller to javafx.graphics, javafx.fxml;
}

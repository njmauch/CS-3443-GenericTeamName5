package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class MainController {
	
	@FXML
	private Label lblLoginStatus;
	@FXML
	private TextField txtfUserName;
	@FXML
	private TextField txtfPassword;
	
	
	
	
	@FXML
	public void Login(ActionEvent event) throws Exception {
		
		if (txtfUserName.getText().equals("admin") && txtfPassword.getText().equals("admin")){
			lblLoginStatus.setText("Login successful");
			
			Parent root = FXMLLoader.load(getClass().getResource("/application/MainPage.fxml"));
			Stage primaryStage = new Stage();
			Scene scene = new Scene(root,600,600);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			
			//clear username and password
			txtfUserName.setText("");
			txtfPassword.setText("");

		}
		
		else {
			lblLoginStatus.setText("Incorrect username or password");

		}
		
	}
	
	public void CreateNewUser(ActionEvent event) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("/application/NewUser.fxml"));
		Stage primaryStage = new Stage();
		Scene scene = new Scene(root,600,600);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();
		
		//clear username and password
		txtfUserName.setText("");
		txtfPassword.setText("");
	}

}

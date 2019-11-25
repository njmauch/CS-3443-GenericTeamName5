package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;
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
		try{
			boolean usernameExists = false;
			boolean pwMatches = false;
			//open csv file to check username and password
			FileReader appcsv = new FileReader("financeapp_v1.csv");
			Scanner scnr = new Scanner(appcsv);
			//read line by line
			while( scnr.hasNext()){
				String input = scnr.nextLine();
				//Split the string using ',' as the delim
				String[] details = input.split(",");
				//if username exists and password matches file, set booleans and close file
				if(txtfUserName.getText().equals(details[0]) && txtfPassword.getText().equals(details[1]))
				{
					usernameExists = true;
					pwMatches = true;
					appcsv.close();
					scnr.close();
					break;
				}
			}
			

		
		if (usernameExists && pwMatches){
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
		catch (Exception e)
		{
			System.out.println("Error opening file");
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

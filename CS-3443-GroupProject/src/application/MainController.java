package application;

import java.io.File;
import java.util.Scanner;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
	public Button bttnLogin;
	@FXML
	public Button bttnNewUser;
	
	
	
	
	public void Login(ActionEvent event) throws Exception {
		boolean usernameExists = false;
		boolean pwMatches = false;
		String username = "";
		try{
			
			//open csv file to check username and password
			//FileReader appcsv = new FileReader("financeapp_v1.csv");
			File appcsv = new File("financeapp_v1.csv");
			if(appcsv.createNewFile())
			{
				System.out.println("File created");
			}
			else
			{
				System.out.println("File already exists");
			}
			
			Scanner scnr = new Scanner(appcsv);
			//read line by line
			while(scnr.hasNext()){
				String input = scnr.nextLine();
				//Split the string using ',' as the delim
				String[] details = input.split(",");
				//if username exists and password matches file, set booleans and close file
				if(txtfUserName.getText().equals(details[0]) && txtfPassword.getText().equals(details[1]))
				{
					usernameExists = true;
					pwMatches = true;
					//appcsv.close();
					scnr.close();
					break;
				}
			}
			//end of try
		}
		catch (Exception e)
		{
			System.out.println(e);
		}

		
		if (usernameExists && pwMatches){
			lblLoginStatus.setText("Login successful");
			System.out.println("Login successful");
			
			username = txtfUserName.getText();
			loadNextController(username);
			//clear username and password
			txtfUserName.setText("");
			txtfPassword.setText("");
		
		}
		
		else 
		{
			lblLoginStatus.setText("Incorrect username or password");
		}
		
		
	}
	
	public void loadNextController(String userName)
	{
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/MainPage.fxml"));
			Parent root = loader.load();
			//get controller for welcome
			WelcomeController welcomeController = loader.getController();
			//pass username
			welcomeController.setWelcome(userName);
			
			Stage stage = new Stage();
			stage.setScene(new Scene(root));
			stage.setTitle("Welcome Page");
			stage.show();
			}
			catch (Exception ex)
			{
				System.out.println("load next controller " + ex);
			}
	}
	
	public void CreateNewUser(ActionEvent event) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("/application/NewUser.fxml"));
		Stage primaryStage = new Stage();
		Scene scene = new Scene(root,250,300);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();
		
		//clear username and password
		txtfUserName.setText("");
		txtfPassword.setText("");
	}

}

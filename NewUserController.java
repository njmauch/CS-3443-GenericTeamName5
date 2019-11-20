package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class NewUserController implements Initializable {
	
	@FXML
	private Button bttnregister;
	@FXML
	private TextField tfaccountname;
	@FXML
	private TextField tfpassword;
	@FXML
	private TextField tfconfirmpassword;
	@FXML
	private TextField tfusername;
	@FXML
	private ComboBox<String> cbaccounttype;
	ObservableList<String> list = FXCollections.observableArrayList("Checkings", "Savings");
	
	@FXML
	public Label lblstatus;
	
	//Variables to save data from fxml text fields
	public String username;
	public String pass;
	public String checkpass;
	public String accountname;
	public String accounttype;
	
	public void Register(ActionEvent event) {
		lblstatus.setText("");
		//save all data from text fields
		//need to add error handling for incomplete fields
		username = tfusername.getText();
		pass = tfpassword.getText();
		checkpass = tfconfirmpassword.getText();
		accountname = tfaccountname.getText();
		accounttype = cbaccounttype.getValue();
		//verify username is unique and that pass and check pass are the same, otherwise have user try again
		if (pass.equals(checkpass) && !username.equals("admin")) {
			//print to test if it is successful, deleteme
			//start test
			System.out.println("User registration successful");
			lblstatus.setText("User registration successful");
			System.out.println(username + " " + pass + " " + checkpass + " " + accountname + " " + accounttype);
			//end test
			
			//load next fxml
			
			//reset all string variables
			username = "";
			pass = "";
			checkpass = "";
			accountname = "";
			accounttype = "";
			
			
			//reset all text fields
			tfusername.setText("");
			tfpassword.setText("");
			tfconfirmpassword.setText("");
			tfaccountname.setText("");
			
			
		}
		else {
			lblstatus.setText("User registration failed");
			//reset all string variables
			username = "";
			pass = "";
			checkpass = "";
			accountname = "";
			accounttype = "";
			//reset all text fields
			tfusername.setText("");
			tfpassword.setText("");
			tfconfirmpassword.setText("");
			tfaccountname.setText("");
		}
		
	}
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		cbaccounttype.setItems(list);		
	}

}
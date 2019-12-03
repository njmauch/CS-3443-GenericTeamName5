package application;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.stage.Stage;

public class WelcomeController implements Initializable{
	
	@FXML
	public Label lblWelcome;
	@FXML
	public String username;
	@FXML
	private Button bttnGo;
	@FXML
	public ComboBox<String> combobox;
	ObservableList<String> list = FXCollections.observableArrayList("Account", "Paycheck calculator", "Spending breadown");
	
	ObservableList<String> tips = FXCollections.observableArrayList("Pay off smaller debt first",
			"Cut down on groceries by planning your meals and using grocery pickup",
			"Cancel automatic subscriptions and memberships",
			"Buy generic",
			"Cut ties with cable",
			"Save money automatically, by rounding up",
			"Spend extra or unexpected income wisely, such as toward debt",
			"Reduce energy costs, turn the heat down when you're not home",
			"Unsubscribe from emails, you won't be tempted by email promotions",
			"Borrow—don’t buy, see if you can borrow from your friends/family first",
			"Pack lunch and eat at home",
			"Ask about discounts (and pay in cash)",
			"Take advantage of your retirement savings plan",
			"Lower your cell phone bill, cut back on unnecessary data/warranties",
			"Try a spending freeze",
			"DIY . . . everything!",
			"Skip the coffee shop",
			"The library is your friend",
			"Sell everything (that doesn’t bring you joy)");
	
	@FXML
	public Label lblTips;
	
	@FXML
	private Button bttnNewTip;
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		combobox.setItems(list);
	}
	
	public void setWelcome(String username1){
		username = username1;
		lblWelcome.setText("Welcome " + username + "!");
		Random rand = new Random(); 
		int randy = rand.nextInt(19);
		lblTips.setText(tips.get(randy));
	}
	
	public void getNewTip(ActionEvent event) {
		Random rand = new Random(); 
		int randy = rand.nextInt(19);
		lblTips.setText(tips.get(randy));
	}
	
	public void buttonAction(ActionEvent event) {
		String textFromCombo = combobox.getValue();
		
		//call to load account
		if(textFromCombo.equals("Account")) 
		{
			loadAccountController(username);
		}
		
		//call to paycheck calculator
		if(textFromCombo.equals("Paycheck calculator")) 
		{
			loadPaycheckCalcController(username);
			//System.out.println("Call to load paycheck calculator");
		}
		//call to spending breakdown
		if(textFromCombo.equals("Spending breadown")) 
		{
			loadSpendingBreakdownController(username);	
		}
		
		
	}
	
	//method to load the account transactions
	public void loadAccountController(String userName)
	{
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/Account.fxml"));
			Parent root = loader.load();
			//get controller for welcome
			AccountController accountController = loader.getController();
			//pass username
			accountController.Welcome(userName);
			
			Stage stage = new Stage();
			stage.setScene(new Scene(root));
			stage.setTitle("Account Page");
			stage.show();
			}
			catch (Exception ex)
			{
				System.out.println("load next controller " + ex);
			}
	}
	//method to load the paycheck calculator
	public void loadPaycheckCalcController(String userName)
	{
		try {
			//UPDATE THIS LINE
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/PaycheckCalculator.fxml"));
			//
			//
			//
			Parent root = loader.load();
			//get controller for welcome
			PaycheckController paycheckController = loader.getController();
			//pass username
			paycheckController.setWelcome(userName);
			
			Stage stage = new Stage();
			stage.setScene(new Scene(root));
			stage.setTitle("Paycheck Calculator");
			stage.show();
			}
			catch (Exception ex)
			{
				System.out.println("load next controller " + ex);
			}
	}
	
	//Method to load the spending breakdown
	public void loadSpendingBreakdownController(String userName)
	{
		try {
			//UPDATE THIS LINE
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/Spending.fxml"));
			//
			//
			//
			Parent root = loader.load();
			//get controller for welcome
			SpendingController spendingController = loader.getController();
			//pass username
			spendingController.startUp(userName);
			
			Stage stage = new Stage();
			stage.setScene(new Scene(root));
			stage.setTitle("Spending breakdown");
			stage.show();
			}
			catch (Exception ex)
			{
				System.out.println("load next controller " + ex);
			}
	}

}

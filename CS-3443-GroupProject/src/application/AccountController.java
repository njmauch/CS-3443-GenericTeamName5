package application;

import javafx.event.ActionEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
/********************************************************
 * 
 * @author nathanmauch
 * AccountController
 * This controller controls the initialization the Account Scene
 * On the first load of a new account it will ask user for their 
 * initial account balance. Once balance is set it will ask to 
 * enter deposits and withdrawals and keeps track of customers
 * transactions and banking history.  
 */
public class AccountController implements Initializable {
	@FXML
	public ListView<AccountTransactions> myAccountInfo;
	@FXML
	public Label welcomeName;
	@FXML
	public Label goToLabel;
	@FXML
	public Button initialButton;
	@FXML
	public Button paycheckButton;
	@FXML
	public Button spendButton;
	@FXML
	public Button submitTran;
	@FXML
	public Label initialText;
	@FXML
	public Label currBalance;
	@FXML
	public TextField initialAmount;
	@FXML
	public Label enterTran;
	@FXML
	public Label depWith;
	@FXML
	public Label date;
	@FXML
	public Label description;
	@FXML
	public Label amountLabel;
	@FXML
	public TextField dateTran;
	@FXML
	public TextField amountTran;
	
	public static float accountBalance;
	public String fileName;
	public String userIdentity;
	//Array list of transactions that are used to be displayed in list view 
	public ArrayList<AccountTransactions> accountTransactions;
	NumberFormat formatter = NumberFormat.getCurrencyInstance();
	
	@FXML
	public ComboBox<String> combobox1;
	@FXML
	public ComboBox<String> combobox2;
	//List of combo boxes used for withdrawals and deposits
	ObservableList<String> list1 = FXCollections.observableArrayList("Withdrawal", "Deposit");
	ObservableList<String> list2 = FXCollections.observableArrayList("Rent/Mortgage", "Utilities", "Bills", "Groceries", "Food", "Other", "Paycheck", "General Deposit");
	/**
	 * 
	 * @param userName
	 * Takes in user name from calling scene and either opens or creates a file to store
	 * account transactions.
	 */
	public void Welcome (String userName) {
		//Creation of file name for customer
		userIdentity = userName;
		this.accountTransactions = new ArrayList<AccountTransactions>();
		fileName = userName + ".csv";
		File file = new File(fileName);
		try {
			//if the first time entering the application hide the transaction
			//menu until initial balance entered.
			if(file.createNewFile()) {
				enterTran.setVisible(false);
				goToLabel.setVisible(false);
				paycheckButton.setVisible(false);
				spendButton.setVisible(false);
				depWith.setVisible(false);
				date.setVisible(false);
				description.setVisible(false);
				amountLabel.setVisible(false);
				amountTran.setVisible(false);
				combobox1.setVisible(false);
				combobox2.setVisible(false);
				dateTran.setVisible(false);
				submitTran.setVisible(false);
				welcomeName.setText("Welcome " + userName + "!");
				initialButton.setVisible(true);
				initialText.setVisible(true);
				initialAmount.setVisible(true);
			}
			else {
				//If existing user welcome user and load previous transactions into list view
				welcomeName.setText("Welcome back " + userName + "!");
				loadTranTable();
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 
	 * @throws FileNotFoundException
	 * loadTranTable loads the transactions of the customer and displays them to the list view
	 */
	public void loadTranTable() throws FileNotFoundException {
		accountTransactions.clear();
		File file = new File(fileName);
		Scanner inputStream = new Scanner(file);
		//Read through the customers file inserting the transactions into an ArrayList
		while(inputStream.hasNextLine()) {
			String data = inputStream.nextLine();
			String[] values = data.split(",");
			AccountTransactions tempAccTrans = new AccountTransactions(values[0], values[1], Float.parseFloat(values[2]), Float.parseFloat(values[3]), Float.parseFloat(values[4]));
			accountTransactions.add(tempAccTrans);
			ObservableList<AccountTransactions> accountTrans = FXCollections.observableArrayList(accountTransactions);
			myAccountInfo.setItems(accountTrans);
			AccountTransactions lastTran = accountTransactions.get(accountTransactions.size()-1);
			float currentBalance = lastTran.getBalance();
			currBalance.setText(String.valueOf(formatter.format(currentBalance)));
		}
		inputStream.close();
	}
	/**
	 * 
	 * @param event Action event button
	 * @throws FileNotFoundException
	 * actionButton 3 handles the reading of the new transaction being entered into the
	 * profile and then updates the account balance and saves the transaction for the customer
	 */
	public void actionButton3(ActionEvent event) throws FileNotFoundException {
		//Gets the last transaction entered to get the current balance
		AccountTransactions lastTran = accountTransactions.get(accountTransactions.size()-1);
		float currentBalance = lastTran.getBalance();
		//This keeps track of the amount possibly saved of the customer
		float saved = lastTran.getSaved();
		//used to store values of transaction to be saved in customers file
		StringBuilder str = new StringBuilder();
		//The type of transaction (withdrawal or deposit)
		String tran = combobox1.getSelectionModel().getSelectedItem();
		//The amount of the transaction
		float amountTrans = Float.parseFloat(amountTran.getText());
		//The type of transaction (rent, bill, food, etc)
		String type = combobox2.getSelectionModel().getSelectedItem();
		//The date of the transaction
		String dateTrans = dateTran.getText();
		//This calculates the amount possibly saved if you rounded the transaction
		//up to the next full dollar amount
		float tempNum = (float) Math.ceil(amountTrans);
		float tempSaved = tempNum - amountTrans;
		saved = saved + tempSaved;
		//If transaction is withdrawal subtract value from total balance
		if(tran.equals("Withdrawal"))
		{
			currentBalance = currentBalance - amountTrans;
			currBalance.setText(String.valueOf(currentBalance));
		}
		//If it is a deposit add amount to total balance
		else
		{
			currentBalance = currentBalance + amountTrans;
			currBalance.setText(String.valueOf(currentBalance));
		}
		//Create string to be added to the customers file of transactions
		str.append(dateTrans);
		str.append(",");
		str.append(type);
		str.append(",");
		str.append(String.valueOf(amountTrans));
		str.append(",");
		str.append(String.valueOf(currentBalance));
		str.append(",");
		str.append(String.valueOf(saved));
		str.append("\n");
		//Save the transaction to the customers file
		File file = new File(fileName);
		try {
			FileWriter fr = new FileWriter(file, true);
			fr.write(str.toString());
			fr.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		loadTranTable();
	}
	/**
	 * 
	 * @param event Action event button
	 * @throws FileNotFoundException
	 * actionButton2 is only present when the customer is accessing their account
	 * for the first time.  Prompts user for initial balance and saves that balance to 
	 * the customers file
	 * 
	 */
	public void actionButton2(ActionEvent event) throws FileNotFoundException {
		
		StringBuilder str = new StringBuilder();
		//gets the initial balance from customer
		accountBalance = Float.parseFloat(initialAmount.getText());
		currBalance.setText(String.valueOf(formatter.format(accountBalance)));
		//hides the initial account items from the scene
		initialButton.setVisible(false);
		initialText.setVisible(false);
		initialAmount.setVisible(false);
		//Creates entry for the initial balance in the customers file
		//and writes that as initial balance transaction
		LocalDate localDate = LocalDate.now();
		DateTimeFormatter.ofPattern("MM/dd/yyyy").format(localDate);
		str.append(localDate.toString());
		str.append(",");
		str.append("Initial Balance");
		str.append(",");
		str.append(String.valueOf(accountBalance));
		str.append(",");
		str.append(String.valueOf(accountBalance));
		str.append(",");
		str.append("0");
		str.append("\n");
		File file = new File(fileName);
		try {
			FileWriter fr = new FileWriter(file, true);
			fr.write(str.toString());
			fr.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//Makes the transaction entry visible to the user
		enterTran.setVisible(true);
		depWith.setVisible(true);
		date.setVisible(true);
		description.setVisible(true);
		amountLabel.setVisible(true);
		amountTran.setVisible(true);
		combobox1.setVisible(true);
		combobox2.setVisible(true);
		dateTran.setVisible(true);
		submitTran.setVisible(true);
		goToLabel.setVisible(true);
		spendButton.setVisible(true);
		paycheckButton.setVisible(true);
		//loads the transaction into the list view
		loadTranTable();
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		//sets initial items in scene to not visible
		initialButton.setVisible(false);
		initialText.setVisible(false);
		initialAmount.setVisible(false);
		combobox1.setItems(list1);
		combobox2.setItems(list2);
	}
	/**
	 * toString sets the format of the transactions that are being displayed
	 * in the list view
	 */
	public String toString()
	{
		String stringFormat = null;
		for(int i = 0; i < accountTransactions.size(); i++)
		{
			stringFormat += accountTransactions.get(i).toString();
		}
		return stringFormat;
	}
	/**
	 * 
	 * @param event
	 * actionButton 4 is responsible for loading the Spending controller and loading the 
	 * Spending scene to show the user their spending break down
	 */
	public void actionButton4(ActionEvent event) 
	{
		try {
			//loads the fxml file to load the scene
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/Spending.fxml"));
			Parent root = loader.load();
			//loads the controller to allow parameter to be passed
			SpendingController spendingController = loader.getController();
			//passes user name to the scene
			spendingController.StartUp(userIdentity);
			//Shows scene to screen
			Stage stage = new Stage();
			stage.setScene(new Scene(root));
			stage.setTitle("Spending Breakdown");
			stage.show();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 
	 * @param event
	 * action button 5 is responsible for loading the PaycheckCalculator controller and loading
	 * Paycheck Calculator scene to show the paycheck calculator
	 */
	public void actionButton5(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/PaycheckCalculator.fxml"));

			Parent root = loader.load();
			//get controller for welcome
			PaycheckController paycheckController = loader.getController();
			//pass username
			paycheckController.setWelcome(userIdentity);
			
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
}

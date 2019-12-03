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
	
	public ArrayList<AccountTransactions> accountTransactions;
	NumberFormat formatter = NumberFormat.getCurrencyInstance();
	
	@FXML
	public ComboBox<String> combobox1;
	@FXML
	public ComboBox<String> combobox2;
	
	ObservableList<String> list1 = FXCollections.observableArrayList("Withdrawal", "Deposit");
	ObservableList<String> list2 = FXCollections.observableArrayList("Rent/Mortgage", "Utilities", "Bills", "Groceries", "Food", "Other", "Paycheck", "General Deposit");
	
	public void Welcome (String userName) {
		userIdentity = userName;
		this.accountTransactions = new ArrayList<AccountTransactions>();
		fileName = userName + ".csv";
		File file = new File(fileName);
		try {
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
				welcomeName.setText("Welcome back " + userName + "!");
				loadTranTable();
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void loadTranTable() throws FileNotFoundException {
		accountTransactions.clear();
		File file = new File(fileName);
		Scanner inputStream = new Scanner(file);
		
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
	public void actionButton3(ActionEvent event) throws FileNotFoundException {
		AccountTransactions lastTran = accountTransactions.get(accountTransactions.size()-1);
		float currentBalance = lastTran.getBalance();
		float saved = lastTran.getSaved();
		StringBuilder str = new StringBuilder();
		String tran = combobox1.getSelectionModel().getSelectedItem();
		float amountTrans = Float.parseFloat(amountTran.getText());
		String type = combobox2.getSelectionModel().getSelectedItem();
		String dateTrans = dateTran.getText();
		float tempNum = (float) Math.ceil(amountTrans);
		float tempSaved = tempNum - amountTrans;
		saved = saved + tempSaved;
		if(tran.equals("Withdrawal"))
		{
			currentBalance = currentBalance - amountTrans;
			currBalance.setText(String.valueOf(currentBalance));
		}
		else
		{
			currentBalance = currentBalance + amountTrans;
			currBalance.setText(String.valueOf(currentBalance));
		}
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
	public void actionButton2(ActionEvent event) throws FileNotFoundException {
		StringBuilder str = new StringBuilder();
		accountBalance = Float.parseFloat(initialAmount.getText());
		currBalance.setText(String.valueOf(formatter.format(accountBalance)));
		initialButton.setVisible(false);
		initialText.setVisible(false);
		initialAmount.setVisible(false);
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
		loadTranTable();
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		initialButton.setVisible(false);
		initialText.setVisible(false);
		initialAmount.setVisible(false);
		combobox1.setItems(list1);
		combobox2.setItems(list2);
	}
	public String toString()
	{
		String stringFormat = null;
		for(int i = 0; i < accountTransactions.size(); i++)
		{
			stringFormat += accountTransactions.get(i).toString();
		}
		return stringFormat;
	}
	public void actionButton4(ActionEvent event) {
		try {
			//UPDATE THIS LINE
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/Spending.fxml"));

			Parent root = loader.load();
			//get controller for welcome
			SpendingController spendingController = loader.getController();
			//pass username
			spendingController.startUp(userIdentity);
			
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
	public void actionButton5(ActionEvent event) {
		try {
			//UPDATE THIS LINE
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

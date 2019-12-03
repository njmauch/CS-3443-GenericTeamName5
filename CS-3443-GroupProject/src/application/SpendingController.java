package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class SpendingController implements Initializable {
	public float rent;
	public float utilities;
	public float bills;
	public float groceries;
	public float total;
	public float food;
	public float luxuries;
	public String user;
	public ArrayList<AccountTransactions> accountSpendTransactions;
	@FXML
	ObservableList<PieChart.Data> pieChartData;
	@FXML
	public PieChart spendChart = new PieChart();
	@FXML
	public Label savedLabel;
	public String fileName;
	
	NumberFormat formatter = NumberFormat.getCurrencyInstance();
	
	public void StartUp (String userName) throws FileNotFoundException
	{
		this.user = userName;
		this.accountSpendTransactions = new ArrayList<AccountTransactions>();
		fileName = userName + ".csv";
		File file = new File(fileName);
		Scanner inputStream = new Scanner(file);
		
		while(inputStream.hasNextLine()) {
			String data = inputStream.nextLine();
			String[] values = data.split(",");
			AccountTransactions tempSpendTrans = new AccountTransactions(values[0], values[1], Float.parseFloat(values[2]), Float.parseFloat(values[3]), Float.parseFloat(values[4]));
			accountSpendTransactions.add(tempSpendTrans);
			
		}
		inputStream.close();
		rent = 0;
		utilities = 0;
		bills = 0;
		groceries = 0;
		total = 0;
		food = 0;
		luxuries = 0;
		
		AccountTransactions lastTran = accountSpendTransactions.get(accountSpendTransactions.size()-1);
		float currentBalance = lastTran.getBalance();
		total += currentBalance;
		float possibleSaved = lastTran.getSaved();
		savedLabel.setText(formatter.format(possibleSaved));
		
		for(int i = 0; i < accountSpendTransactions.size(); i++)
		{
			AccountTransactions tempTrans = accountSpendTransactions.get(i);
			if(tempTrans.getTransactionDescription().equals("Rent/Mortgage"))
			{
				rent += tempTrans.getAmount();
			}
			else if(tempTrans.getTransactionDescription().equals("Utilities"))
			{
				utilities += tempTrans.getAmount();
			}
			else if(tempTrans.getTransactionDescription().equals("Bills"))
			{
				bills += tempTrans.getAmount();
			}
			else if(tempTrans.getTransactionDescription().equals("Groceries"))
			{
				groceries += tempTrans.getAmount();
			}
			else if(tempTrans.getTransactionDescription().equals("Food"))
			{
				food += tempTrans.getAmount();
			}
			else if(tempTrans.getTransactionDescription().equals("Other"))
			{
				luxuries += tempTrans.getAmount();
			}		
		}
		total = rent + utilities + bills + groceries + food + luxuries;
		pieChartData = FXCollections.observableArrayList(
				new PieChart.Data("Rent\\Mortgage", (rent/total)),
				new PieChart.Data("Utilities", (utilities/total)),
				new PieChart.Data("Bills", (bills/total)),
				new PieChart.Data("Groceries", (groceries/total)),
				new PieChart.Data("Food", (food/total)),
				new PieChart.Data("Luxuries", (luxuries/total)),
				new PieChart.Data("Available Balance", (currentBalance/total)));
		spendChart.setData(pieChartData);
		spendChart.setTitle("Spending Breakdown");
		spendChart.setLegendSide(Side.LEFT);
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub		
	}
	public void actionButton1(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/Account.fxml"));
			Parent root = loader.load();
			//get controller for welcome
			AccountController accountController = loader.getController();
			//pass username
			accountController.Welcome(user);
			
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
	public void actionButton2(ActionEvent event) {
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
			paycheckController.setWelcome(user);
			
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

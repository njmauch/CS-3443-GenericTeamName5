package application;

import java.util.Random;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
/**
 * 
 * @author Tyler Frank
 * Paycheck Controller is in charge of generating an estimated amount of you
 * next paycheck based on hours and hourly rate.  
 */
public class PaycheckController {
	//List of tips that randomly display to screen to help customer save money
	ObservableList<String> tips = FXCollections.observableArrayList("Pay off smaller debt first",
			"Cut down on groceries by planning your meals and using grocery pickup",
			"Cancel automatic subscriptions and memberships",
			"Buy generic",
			"Cut ties with cable",
			"Save money automatically, by rounding up",
			"Spend extra or unexpected income wisely, such as toward debt",
			"Reduce energy costs, turn the heat down when you're not home",
			"Unsubscribe from emails, you won't be tempted by email promotions",
			"Borrow�don�t buy, see if you can borrow from your friends/family first",
			"Pack lunch and eat at home",
			"Ask about discounts (and pay in cash)",
			"Take advantage of your retirement savings plan",
			"Lower your cell phone bill, cut back on unnecessary data/warranties",
			"Try a spending freeze",
			"DIY . . . everything!",
			"Skip the coffee shop",
			"The library is your friend",
			"Sell everything (that doesn�t bring you joy)");
	
	@FXML
	public Label lblTips;
	
	@FXML
	private TextField tfEnterHours;
	@FXML
	private TextField tfEnterWage;
	@FXML
	private TextField tfBeforeTax;
	@FXML
	private TextField tfAfterTax;
	@FXML
	private Button bttnCalculate;
	@FXML
	private Button bttnReset;
	
	public void setWelcome(String username1){
		Random rand = new Random(); 
		int randy = rand.nextInt(19);
		lblTips.setText(tips.get(randy));
	}
	
	public void reset(ActionEvent event) {
		tfEnterHours.setText("");
		tfEnterWage.setText("");
		tfBeforeTax.setText("");
		tfAfterTax.setText("");
	}
	/**
	 * 
	 * @param event
	 * Takes in parameters of hours worked and hourly rate then displays
	 * estimate of next check before and after taxes
	 */
	public void calculate(ActionEvent event) {
		
		double hoursWorked;
		double hourlyRate;
		double beforeTax;
		String safterTax;
		String sBeforeTax;
		
		hoursWorked = Double.valueOf(tfEnterHours.getText());
		hourlyRate = Double.valueOf(tfEnterWage.getText());
		
		beforeTax = hoursWorked * hourlyRate;
		sBeforeTax = String.format("%.2f", hoursWorked * hourlyRate);
		safterTax = String.format("%.2f",beforeTax * 0.85);
		
		tfBeforeTax.setText("$" + sBeforeTax);
		tfAfterTax.setText("$" + safterTax);
	}
	
}

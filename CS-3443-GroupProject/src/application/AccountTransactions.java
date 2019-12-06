package application;

import java.text.NumberFormat;
/**
 * 
 * @author nathanmauch
 * AccountTransactions is a model class that creates the array list needed for the
 * account and spending controller.  It takes the values that are passed to it from 
 * reading the customers transaction file and inserts them into an array list
 */
public class AccountTransactions {
	private String date;
	private String transactionDescription;
	private float amount;
	private float balance;
	private float saved;
	NumberFormat formatter = NumberFormat.getCurrencyInstance();
	/**
	 * 
	 * @param date Date of the transaction
	 * @param transactionDescription Type of transaction
	 * @param amount Amount of transaction
	 * @param balance Current Balance
	 * @param saved Possible saved amount
	 */
	public AccountTransactions(String date, String transactionDescription, float amount, float balance, float saved) {
		this.date = date;
		this.transactionDescription = transactionDescription;
		this.amount = amount;
		this.balance = balance;
		this.saved = saved;
	}
	/*
	 * 
	 * Getter and setters for the values in the array accountTransactions
	 */
	public String getDate()
	{
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getTransactionDescription() {
		return transactionDescription;
	}
	public void setTransactionDescriptions(String transactionDescription) {
		this.transactionDescription = transactionDescription;
	}
	public float getAmount() {
		return amount;
	}
	public void setAmount(float amount) {
		this.amount = amount;
	}
	public float getBalance() {
		return balance;
	}
	public void setBalance(float balance) {
		this.balance = balance;
	}
	public void setSaved(float saved) {
		this.saved = saved;
	}
	public float getSaved() {
		return saved;
	}
	
	/**
	 * toString formats the accounts transactions in a format to be properly displayed
	 * to the screen.
	 */
	public String toString() {
		int temp;
		String stringFormat = date;
		temp = 36 - date.length();
		for(int i=0; i<temp; i++)
		{
			stringFormat += " ";
		}
		
		stringFormat += transactionDescription;
		temp = 105 - transactionDescription.length();
		for(int i=0; i<temp; i++)
		{
			stringFormat += " ";
		}

		stringFormat += formatter.format(amount);
		temp = 50 - String.valueOf(balance).length();
		for(int i=0; i<temp; i++)
		{
			stringFormat += " ";
		}

		stringFormat += formatter.format(balance);
		return stringFormat;
	}
}

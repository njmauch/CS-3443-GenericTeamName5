package application;

import java.text.NumberFormat;

public class AccountTransactions {
	private String date;
	private String transactionDescription;
	private float amount;
	private float balance;
	private float saved;
	NumberFormat formatter = NumberFormat.getCurrencyInstance();
	
	public AccountTransactions(String date, String transactionDescription, float amount, float balance, float saved) {
		this.date = date;
		this.transactionDescription = transactionDescription;
		this.amount = amount;
		this.balance = balance;
		this.saved = saved;
	}
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

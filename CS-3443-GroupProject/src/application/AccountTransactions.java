package application;

public class AccountTransactions {
	private String date;
	private String transactionDescription;
	private float amount;
	private static float balance;
	
	public AccountTransactions(String date, String transactionDescription, float amount, float balance) {
		this.date = date;
		this.transactionDescription = transactionDescription;
		this.amount = amount;
		AccountTransactions.balance = balance;
	}
	public String getDate()
	{
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String transactionDescription() {
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
		AccountTransactions.balance = balance;
	}
	
	public String toString() {
		return (this.date + "\t" + this.transactionDescription + "\t" + String.valueOf(this.amount) + "\t\t" + String.valueOf(this.balance));
	}
}

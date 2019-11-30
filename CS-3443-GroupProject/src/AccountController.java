

import javafx.event.ActionEvent;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class AccountController implements Initializable {
	@FXML
	public ListView<AccountTransactions> myAccountInfo;
	@FXML
	public Label welcomeName;
	@FXML
	public Button initialButton;
	@FXML
	public Label initialText;
	@FXML
	public Label currBalance;
	@FXML
	public TextField initialAmount;
	
	public static float accountBalance;
	
	public static ArrayList<AccountTransactions> accountTransactions;

	public void Welcome (String userName) {
		
		AccountController.accountTransactions = new ArrayList<AccountTransactions>();
		String fileName = userName + ".csv";
		File file = new File(fileName);
		try {
			if(file.createNewFile()) {
				welcomeName.setText("Welcome " + userName + "!");
				initialButton.setVisible(true);
				initialText.setVisible(true);
				initialAmount.setVisible(true);
			}
			else {
				welcomeName.setText("Welcome back " + userName + "!");
				
				Scanner inputStream = new Scanner(file);
				
				while(inputStream.hasNextLine()) {
					String data = inputStream.nextLine();
					String[] values = data.split(",");
					AccountTransactions tempAccTrans = new AccountTransactions(values[0], values[1], Float.parseFloat(values[2]), Float.parseFloat(values[3]));
					accountTransactions.add(tempAccTrans);
					ObservableList<AccountTransactions> accountTrans = FXCollections.observableArrayList(accountTransactions);
					myAccountInfo.setItems(accountTrans);
				}
				inputStream.close();
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void actionButton3(ActionEvent event) {
		AccountTransactions lastTran = accountTransactions.get(accountTransactions.size()-1);
		float currBalance = lastTran.getBalance();
	}
	public void actionButton2(ActionEvent event) {
		accountBalance = Float.parseFloat(initialAmount.getText());
		currBalance.setText(String.valueOf(accountBalance));
		initialButton.setVisible(false);
		initialText.setVisible(false);
		initialAmount.setVisible(false);
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		initialButton.setVisible(false);
		initialText.setVisible(false);
		initialAmount.setVisible(false);
	}
}

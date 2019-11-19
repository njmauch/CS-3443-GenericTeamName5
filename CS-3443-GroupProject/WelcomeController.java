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

public class WelcomeController implements Initializable{
	
	@FXML
	public Label lblWelcome;
	@FXML
	public ComboBox<String> combobox;
	ObservableList<String> list = FXCollections.observableArrayList("Thing1", "Thing2", "Thing3");
	@FXML
	private Button bttnWelcome;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		combobox.setItems(list);
	}
	
	public void comboChanged() {
		
		lblWelcome.setText(combobox.getValue());
	}
	
	public void buttonAction(ActionEvent event) {
		//combobox.getItems().addAll("Jeff", "BILL", "bob");
		String textFromCombo = combobox.getValue();
	}

}

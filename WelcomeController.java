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
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;

public class WelcomeController implements Initializable{
	
	@FXML
	public Label lblWelcome;
	@FXML
	public ComboBox<String> combobox;
	ObservableList<String> list = FXCollections.observableArrayList("Thing1", "Thing2", "Thing3");
	@FXML
	private Button bttnWelcome;
	
	@FXML
	private ListView<String> listview;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		combobox.setItems(list);
		//listview.setItems(list);
		
		//listview.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
	}
	
	public void comboChanged() {
		
		lblWelcome.setText(combobox.getValue());
	}
	
	public void buttonAction(ActionEvent event) {
		//combobox.getItems().addAll("Jeff", "BILL", "bob");
		//listview.getItems().addAll("Jeff", "BILL", "bob");
		String textFromCombo = combobox.getValue();
		System.out.println(textFromCombo);
		//ObservableList<String> names;
		//names = listview.getSelectionModel().getSelectedItems();
		//for(String name: names){
		//	System.out.println(name);}
		
	}

}

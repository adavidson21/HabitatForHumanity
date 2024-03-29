package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.CreateAccountModel;

public class CreateAccountController{
	private CreateAccountModel createAccountModel = new CreateAccountModel();
	Main main = new Main();
	Stage prevStage;

	@FXML
	private Label messageLbl;
	@FXML
	private Button clearBtn;
	@FXML
	private Button createAccountBtn;
	@FXML
	private Button backBtn;
	@FXML
	private TextField userField;
	@FXML
	private PasswordField passField;
	@FXML
	private TextField fNameField;
	@FXML
	private TextField lNameField;
	@FXML
	private PasswordField rePassField;
	@FXML
	private TextField emailField;

	public void createAccount(ActionEvent event) throws SQLException {
		if (checkFieldsForCompletion() == false) {
			messageLbl.setText("All fields must be completed.");
		} else if (createAccountModel.checkPassword(passField.getText(), rePassField.getText()) == false) {
			messageLbl.setText("Both passwords must match.");
		} else if (createAccountModel.checkPasswordCriteria(passField.getText()) == false) {
			messageLbl.setText("Your password must be at least 5 characters long and contain one uppercase letter.");
		} else if (createAccountModel.checkEmailFormat(emailField.getText()) == false) {
			messageLbl.setText("The email must be in the correct format.");
		} else if (createAccountModel.checkEmail(emailField.getText()) == true) {
			messageLbl.setText("This email you entered is already in use. Please try another.");
		} else if (createAccountModel.checkUsername(userField.getText()) == true) {
			messageLbl.setText("The username you entered is already in use. Please try another.");
		} else {
			createAccountModel.createNewAccount(userField.getText(), passField.getText(), fNameField.getText(),
					lNameField.getText(), emailField.getText());

			messageLbl.setText("Your account has been created. Return to login.");
			clearAllFields();

		}
	}

	public void back(ActionEvent event) throws IOException {
		messageLbl.setText("Back Button Pressed");
		main.changeScene("LoginView.fxml", messageLbl);
	}

	public void clear(ActionEvent event) {
		clearAllFields();
	}

	public void clearAllFields() {
		userField.clear();
		passField.clear();
		fNameField.clear();
		lNameField.clear();
		rePassField.clear();
		emailField.clear();
	}

	public boolean checkFieldsForCompletion() {
		if (userField.getText().isEmpty() || passField.getText().isEmpty() || rePassField.getText().isEmpty()
				|| fNameField.getText().isEmpty() || lNameField.getText().isEmpty() || emailField.getText().isEmpty()) {
			messageLbl.setText("All Fields Must Be Completed.");
			return false;
		}
		return true;
	}

}

package client.controller;

import client.Client;
import client.MainApp;
import common.messages.Message;
import common.messages.MessageType;
import common.messages.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.apache.log4j.Logger;

import java.io.IOException;


/**
 * Description:
 * Author: Angus Liu
 * Date: 2018/5/12
 * Version: v1.0
 */
public class LoginController {
    private static final Logger logger = Logger.getLogger(LoginController.class);

    @FXML
    private TextField signInAccountTextfield;

    @FXML
    private PasswordField signInPasswordField;

    @FXML
    private Button signInButton;

    private MainApp mainApp;

    @FXML
    private void initialize() {
//       signInButton.
    }

    public void setMainApp(MainApp mainApp){
        this.mainApp = mainApp;
    }

    public void signInButtonAction(ActionEvent actionEvent) {
        logger.info("signInAccountTextfield信息！[ " + signInAccountTextfield.getCharacters() + " ]");
        logger.info("signInPasswordField信息！[ " + signInPasswordField.getCharacters() + " ]");
        try {
            Client.clientLaunch();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Message message = new Message();
        message.setType(MessageType.SIGN_IN);
        User user = new User(signInAccountTextfield.getCharacters(), signInPasswordField.getCharacters());
        message.setUser(user);
        Client.sendMessage(message);
    }
}

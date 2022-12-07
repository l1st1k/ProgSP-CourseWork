package com.example.courseworkc.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.example.courseworkc.UserApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class UserSignInController {

    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;
    @FXML
    private Button connectbutton;
    @FXML
    private TextField ip_adress;
    @FXML
    private TextField loginfield;
    @FXML
    private PasswordField password_field;
    @FXML
    private TextField port;
    @FXML
    private Button registration_button;
    @FXML
    private Button signinbutton;
    @FXML
    void initialize() {
        UserClient client = new UserClient();

        connectbutton.setOnAction(event->{
            String ip_adr = ip_adress.getText();
            int port_ = Integer.parseInt(port.getText());
            System.out.println(client.Check_Connect(ip_adr,port_));
        });

        registration_button.setOnAction(event->{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(UserApplication.class.getResource("UserRegistration.fxml"));

            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setTitle("Coursework - Alexandr Listvanovich - 010101");
            stage.setScene(new Scene(root));
            stage.showAndWait();
        });

        signinbutton.setOnAction(event->{
            String ip_adr = ip_adress.getText();
            int port_ = Integer.parseInt(port.getText());
            UserClient userClient = new UserClient();

            if(userClient.signIn(ip_adr,port_,loginfield.getText(),password_field.getText()) == 1){
                System.out.println("Successfully signed in!");
                signinbutton.getScene().getWindow().hide();
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(UserApplication.class.getResource("UserApp.fxml"));

                try {
                    loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Parent root = loader.getRoot();
                Stage stage = new Stage();
                stage.setTitle("Coursework - Alexandr Listvanovich - 010101");
                stage.setScene(new Scene(root));
                stage.showAndWait();
            }
            else {
                System.out.println("Something went wrong during authorization. Check your credentials!");
            }
        });

    }

}
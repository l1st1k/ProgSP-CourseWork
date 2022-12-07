package com.example.courseworkc.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.example.courseworkc.AdminApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AdminController {

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
    private PasswordField passwordfield;

    @FXML
    private TextField port;

    @FXML
    private Button signinbutton;

    @FXML
    void initialize() {

        AdminClient client = new AdminClient();

        connectbutton.setOnAction(event->{
            String ip_adr = ip_adress.getText();
            int port_ = Integer.parseInt(port.getText());
            System.out.println(client.Check_Connect(ip_adr,port_));
        });

        signinbutton.setOnAction(event->{
//            String ip_adr = ip_adress.getText();
//            int port_ = Integer.parseInt(port.getText());
            // TODO return defaults
            String ip_adr = "localhost";
            int port_ = 8081;


            if(client.signIn(ip_adr,port_,"1","1") == 1){
                System.out.println("Connected successfully!");
                signinbutton.getScene().getWindow().hide();
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(AdminApplication.class.getResource("AdminApp.fxml"));

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

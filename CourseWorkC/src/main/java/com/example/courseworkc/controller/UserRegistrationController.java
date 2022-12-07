package com.example.courseworkc.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class UserRegistrationController {
    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;
    @FXML
    private TextField email;
    @FXML
    private ComboBox<String> locationradiobutton;
    @FXML
    private TextField login;
    @FXML
    private RadioButton man;
    @FXML
    private TextField name;
    @FXML
    private PasswordField password;
    @FXML
    private Button regbutton;
    @FXML
    private TextField surname;
    @FXML
    private RadioButton woman;
    @FXML
    void initialize() {

        ObservableList<String> regions = FXCollections.observableArrayList("Минск","Минская область",
                "Гомельская область","Могилевская область","Витебская область","Гродненская область","Брестская область");
        locationradiobutton.setItems(regions);

        regbutton.setOnAction(event ->{
            String UserName,UserSurname,UserLogin,UserPassword,UserEmail,UserGender,UserLocation;
            UserName = name.getText();
            UserSurname = surname.getText();
            UserLogin = login.getText();
            UserPassword = password.getText();
            UserEmail = email.getText();
            UserGender = man.isSelected()? "мужчина": "женщина";
            UserLocation = locationradiobutton.getValue();
            UserClient userClient = new UserClient();
            userClient.AddUser(UserName,UserSurname,UserLogin,UserPassword,UserEmail,UserGender,UserLocation);
            System.out.println("Successfully added new User - " + UserName + " " + UserSurname);

            // closing the window
            ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
        });

    }

}

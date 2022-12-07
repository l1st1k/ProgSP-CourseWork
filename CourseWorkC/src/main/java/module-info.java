module com.example.courseworkc {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.apache.commons.codec;


    opens com.example.courseworkc to javafx.fxml;
    opens com.example.courseworkc.controller to javafx.fxml;
    opens com.example.courseworkc.classes to javafx.base;

    exports com.example.courseworkc;
    exports com.example.courseworkc.controller;
}
package seng202.group10.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuButton;
import javafx.stage.Stage;

import java.io.IOException;

public class ViewController {
    @FXML
    private MenuButton dropdownView;

    public void setViewTable() throws IOException {
        System.out.println("Table");
        Stage stage;
        Parent root;
        root = FXMLLoader.load(getClass().getResource("table.fxml"));
        stage = (Stage) dropdownView.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void setViewMap() throws IOException {
        System.out.println("Map");
        Stage stage;
        Parent root;
        root = FXMLLoader.load(getClass().getResource("main.fxml"));
        stage = (Stage) dropdownView.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}

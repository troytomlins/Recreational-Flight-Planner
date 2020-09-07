package seng202.group10.controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import seng202.group10.model.Airline;
import seng202.group10.model.Airport;

import java.util.ArrayList;

public class AirportTabController {

    @FXML
    public TableView airportTable;
    @FXML
    private TableColumn cityCol;
    @FXML
    private TableColumn latCol;
    @FXML
    private TableColumn lngCol;
    @FXML
    private TableColumn altCol;
    @FXML
    private TableColumn tzCol;
    @FXML
    private TableColumn distCol;
    @FXML
    private TableColumn tzdbCol;
    @FXML
    private TableColumn nameCol;
    @FXML
    private TableColumn iataCol;
    @FXML
    private TableColumn icaoCol;
    @FXML
    private TableColumn countryCol;

    public void updateTable(ArrayList<Airport> data) {
        airportTable.setEditable(true);

        nameCol.setCellValueFactory(new PropertyValueFactory<Airline, String>("name"));
        airportTable.setItems(FXCollections.observableList(data));
    }
}

package com.example.hms4;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;

public class AdminController {
    @FXML
    private Button logout;
    @FXML
    private Button back;
    @FXML
    private Button adpt;
    @FXML
    private Button edpi;
    @FXML
    private Button addr;
    @FXML
    private Button eddi;
    @FXML
    private Button mdi;
    @FXML
    private Button dpa;
    @FXML
    private Button dda;
    @FXML
    private Label userId;

    public void adminLogOut(ActionEvent event) throws IOException {
        HelloApplication m = new HelloApplication();
        m.changescene("hello-view.fxml");
    }
    public void addPt(ActionEvent event) throws IOException {
        HelloApplication m = new HelloApplication();
        m.changescene("addPt.fxml");
    }
    public void editPI(ActionEvent event) throws IOException {
        HelloApplication m = new HelloApplication();
        m.changescene("editPI.fxml");
    }
    public void addDR(ActionEvent event) throws IOException {
        HelloApplication m = new HelloApplication();
        m.changescene("addDR.fxml");
    }
    public void editDI(ActionEvent event) throws IOException {
        HelloApplication m = new HelloApplication();
        m.changescene("editDI.fxml");
    }
    public void mediInfo(ActionEvent event) throws IOException {
        HelloApplication m = new HelloApplication();
        m.changescene("medicines.fxml");
    }
    public void dispPA(ActionEvent event) throws IOException {
        HelloApplication m = new HelloApplication();
        m.changescene("dispPat.fxml");
    }
    public void dispDA(ActionEvent event) throws IOException {
        HelloApplication m = new HelloApplication();
        m.changescene("dispDoc.fxml");
    }
    public void goBack() throws IOException{
    HelloApplication m = new HelloApplication();
        m.changescene("hello-view.fxml");
    }
}



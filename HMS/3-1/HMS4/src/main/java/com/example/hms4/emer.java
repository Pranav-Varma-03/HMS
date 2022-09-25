package com.example.hms4;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class emer {
    @FXML
    private TextField tfName;
    @FXML
    private TextField tfAge;
    @FXML
    private TextField tfGender;
    @FXML
    private TextField tfPhNo;
    @FXML
    private TextField tfRel;
    @FXML
    private TextField tfPhNoRel;
    @FXML
    private TextField tfHealth;
    @FXML
    private Button btOk;
    @FXML
    private Label lbId;

    public void adminLogOut() throws IOException {
        HelloApplication m = new HelloApplication();
        m.changescene("hello-view.fxml");
    }

    public void goBack() throws IOException {
        HelloApplication m = new HelloApplication();
        m.changescene("hello-view.fxml");
    }

    public void saveInfo(ActionEvent event) throws IOException {
        String query = "INSERT INTO EMERGENCY(NAME,AGE,GENDER,PHONE_NUM,PATIENT_REL,PH_NO_REL,PRIOR_HEALTH) VALUES('" + tfName.getText() + "'," + tfAge.getText() + ",'" + tfGender.getText() + "','" + tfPhNo.getText() + "','" + tfRel.getText() + "','" + tfPhNoRel.getText() + "','" + tfHealth.getText() + "');";
        executeQuery(query);
        BookAptPage();
    }
    public void BookAptPage() throws IOException {
        HelloApplication m = new HelloApplication();

        dbConnection EmerConn = new dbConnection();
        Connection conn = EmerConn.getconnection();

        String query = "SELECT * FROM EMERGENCY ORDER BY PATIENT_ID DESC ;";
        Statement st;
        ResultSet rs;
        try {
            st = conn.createStatement();
            rs = st.executeQuery(query);
            rs.next();
            lbId.setText(rs.getString("PATIENT_ID"));
            PatientController patientController = new PatientController();
            patientController.setEPAT_USERID(rs.getString("PATIENT_ID"));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        m.changescene("EBookAppt.fxml");
    }

    private void executeQuery(String query) {
        dbConnection addEmerConn = new dbConnection();
        Connection conn = addEmerConn.getconnection();
        Statement st;
        try {
            st = conn.createStatement();
            st.executeUpdate(query);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}

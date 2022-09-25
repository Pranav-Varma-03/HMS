package com.example.hms4;
import com.example.hms4.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.*;

public class addPt {

    @FXML
    private Button done;
    @FXML
    private Button btBack;
    @FXML
    private Label lbSaved;
    @FXML
    private Button logout;
    @FXML
    private TextField tfName;
    @FXML
    private TextField tfAge;
    @FXML
    private TextField tfGender;
    @FXML
    private TextField tfPhoneNum;
    @FXML
    private TextField tfAdhaar;
    @FXML
    private TextField tfPassword;
    public void adminLogOut(ActionEvent event) throws IOException {
        HelloApplication m = new HelloApplication();
        m.changescene("hello-view.fxml");
    }

    public void saveInfo(ActionEvent event) throws IOException, SQLException {
        String query = "INSERT INTO PATIENT(PASSWORD,NAME,AGE,GENDER,PHONE_NUM,AADHAAR) VALUES('" + tfPassword.getText() + "','" + tfName.getText() + "'," + tfAge.getText() + ",'" + tfGender.getText() + "','" + tfPhoneNum.getText() + "','" + tfAdhaar.getText() + "');";
        executeQuery(query);
        dbConnection PatConn = new dbConnection();
        Connection conn = PatConn.getconnection();

        query = "SELECT * FROM PATIENT ORDER BY PATIENT_ID DESC ;";
        Statement st;
        ResultSet rs;
        try {
            st = conn.createStatement();
            rs = st.executeQuery(query);
            rs.next();
            query = "INSERT INTO LOGINCRED(USER_ID,PASSWORD,ROLE) VALUES('" + rs.getString("PATIENT_ID") + "','" + tfPassword.getText() + "','Patient');";
            executeQuery(query);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        if((event.getSource() == done)) {
            lbSaved.setText("Added Patient Successfully");
            tfPhoneNum.setText("");
            tfPassword.setText("");
            tfGender.setText("");
            tfAge.setText("");
            tfName.setText("");
            tfAdhaar.setText("");

        }
    }
    private void executeQuery(String query)  {
        dbConnection addPtConn = new dbConnection();
        Connection conn = addPtConn.getconnection();
        Statement st;
        try{
            st = conn.createStatement();
            st.executeUpdate(query);
        }catch (Exception ex){
            ex.printStackTrace();
        }

    }
    public void adminHome(ActionEvent event) throws IOException {
        HelloApplication m = new HelloApplication();
        m.changescene("admin.fxml");
    }
}

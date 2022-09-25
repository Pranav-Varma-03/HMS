package com.example.hms4;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class editPI {
    @FXML
    private Button save;
    @FXML
    private TextField tfPatId;
    @FXML
    private TextField tfPassword;
    @FXML
    private TextField tfPhoneNum;
    @FXML
    private Button btBack;
    @FXML
    private Label lbSaved1;
    @FXML
    private Label lbSaved2;

    public ObservableList<Patient> getPatList() {
        ObservableList<Patient> loginList = FXCollections.observableArrayList();
        dbConnection patConn = new dbConnection();
        Connection conn = patConn.getconnection();
        String query = "SELECT * FROM PATIENT";
        Statement st;
        ResultSet rs;
        try {
            st = conn.createStatement();
            rs = st.executeQuery(query);
            Patient patCredDetails;
            while (rs.next()) {
                patCredDetails = new Patient(rs.getInt("PATIENT_ID"), rs.getString("PASSWORD"), rs.getString("NAME"), rs.getInt("AGE"), rs.getString("GENDER"), rs.getString("PHONE_NUM"), rs.getString("AADHAAR"), rs.getInt("ACTIVITY_STAT"), rs.getInt("DOC_ID"), rs.getString("DISEASE"), rs.getString("TESTREP_FILE"), rs.getString("MEDREP_FILE"));
                loginList.add(patCredDetails);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return loginList;
    }

    public void savePI(ActionEvent event) throws IOException {
        if ((event.getSource() == save) && (checkCred() == 1)) {
            String query = "UPDATE PATIENT SET PHONE_NUM = '"+ tfPhoneNum.getText() +  "' WHERE  PATIENT_ID = '" + tfPatId.getText() +"';";
            executeQuery(query);
            lbSaved1.setText("Details Edited Successfully..");
            lbSaved2.setText("");
        }
        else {
            lbSaved2.setText("Wrong Credentials, Please Re Enter");
            lbSaved1.setText("");
        }
//        HelloApplication m = new HelloApplication();
//        m.changescene("admin.fxml");
    }

    public void adminLogOut(ActionEvent event) throws IOException {
        HelloApplication m = new HelloApplication();
        m.changescene("hello-view.fxml");
    }

    public void adminHome(ActionEvent event) throws IOException {
        HelloApplication m = new HelloApplication();
        m.changescene("admin.fxml");
    }

    public int checkCred() {
        int k=0;
        ObservableList<Patient> currentList = getPatList();
        for (int i = 0; i < currentList.size(); i++) {
            Patient patCredDetails = currentList.get(i);
            if ((patCredDetails.getPatId() == Integer.parseInt(tfPatId.getText())) && patCredDetails.getPatPwd().equals(tfPassword.getText())) {
                k = 1;
                break;
            } else {
                k = 0;
            }
        }
    return k;
    }
    private void executeQuery(String query)  {
        dbConnection patConn = new dbConnection();
        Connection conn = patConn.getconnection();
        Statement st;
        try{
            st = conn.createStatement();
            st.executeUpdate(query);
        }catch (Exception ex){
            ex.printStackTrace();
        }

    }
}

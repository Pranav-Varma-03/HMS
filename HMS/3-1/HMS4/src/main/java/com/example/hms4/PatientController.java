package com.example.hms4;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class PatientController implements Initializable {
    public static String PAT_USERID;

    public static String getPAT_USERID() {
        return PAT_USERID;
    }

    public void setPAT_USERID(String USERID) {
        PatientController.PAT_USERID = USERID;
    }
    public static String EPAT_USERID;

    public static String getEPAT_USERID() {
        return EPAT_USERID;
    }

    public void setEPAT_USERID(String USERID) {
        PatientController.EPAT_USERID = USERID;
    }
    @FXML
    private Button btBookAp;
    @FXML
    private Button btBack;
    @FXML
    private Button btLogout;
    @FXML
    private Label lbPatId;
    @FXML
    private Label lbDocId;
    @FXML
    private Label lbMedDt;
    @FXML
    private Label lbDsDt;
    @FXML
    private Label lbTsDt;

    public void initialize(URL url, ResourceBundle rb){
        // TO DO
        loadPatDetails();
        lbPatId.setText(PAT_USERID);
//        LogIn USERID = new LogIn();
//        System.out.println(USERID.getuserName());
    }

    public void patBookApt() throws IOException{
        HelloApplication m = new HelloApplication();
        m.changescene("bookAppt.fxml");
    }
    public void adminLogOut() throws IOException{
        HelloApplication m = new HelloApplication();
        m.changescene("hello-view.fxml");
    }
    public void goBack() throws IOException {
        HelloApplication m = new HelloApplication();
        m.changescene("hello-view.fxml");
    }
    public void loadPatDetails(){
        dbConnection patConn = new dbConnection();
        Connection conn = patConn.getconnection();
        String query = "SELECT * FROM PATIENT WHERE PATIENT_ID = '" +PAT_USERID + "';";
        Statement st;
        ResultSet rs;
        try {
            st = conn.createStatement();
            rs = st.executeQuery(query);
            rs.next();
            lbDocId.setText(rs.getString("DOC_ID")) ;
            lbMedDt.setText(rs.getString("MEDREP_FILE"));
            lbDsDt.setText(rs.getString("DISEASE")) ;
            lbTsDt.setText(rs.getString("TESTREP_FILE")) ;

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}

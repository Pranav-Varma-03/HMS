package com.example.hms4;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class DoctorController implements Initializable{

    public static String USERID;

    public static String getUSERID() {
        return USERID;
    }

    public void setUSERID(String USERID) {
        DoctorController.USERID = USERID;
    }
    @FXML
    private Button btSave ;
    @FXML
    private TextField tfPatId ;
    @FXML
    private TextField tfDisease ;
    @FXML
    private TextField tfMed ;
    @FXML
    private TextField tfTests ;
    @FXML
    private Label lbSave;
    @FXML
    private TableView<Patient> tvPat;
    @FXML
    private TableColumn<Patient,Integer> colPatId;
    @FXML
    private TableColumn<Patient,String> colPatName;
    @FXML
    private TableColumn<Patient,Integer> colPatAge;
    @FXML
    private TableColumn<Patient,String> colPatDisease;
    @FXML
    private TableColumn<Patient,String> colPatMed;
    @FXML
    private TableColumn<Patient,String> colPatTest;
    @FXML
    private Label lbDocId;

    @Override
    public void initialize(URL url, ResourceBundle rb){
        // TO DO
        showPats();
        lbDocId.setText(USERID);
//        LogIn USERID = new LogIn();
//        System.out.println(USERID.getuserName());
    }
    @FXML
    private void handleMouseAction(MouseEvent event){
//        System.out.println("mouse Clicked");
        Patient currentPat = tvPat.getSelectionModel().getSelectedItem();
        tfPatId.setText(""+currentPat.getPatId());
        tfDisease.setText(currentPat.getPatDisease());
        tfMed.setText(currentPat.getPatMedRep());
        tfTests.setText(currentPat.getPatTestRep());
//        tfTests.setText(String.valueOf(Integer.parseInt(lbDocId.getText())+1));
    }
    public void adminLogOut(ActionEvent event) throws IOException {
        HelloApplication m = new HelloApplication();
        m.changescene("hello-view.fxml");
    }
    public void saveData(ActionEvent event) throws IOException {
        if (event.getSource() == btSave) {
            String query = "UPDATE PATIENT SET DISEASE = '"+ tfDisease.getText() + "', TESTREP_FILE = '" + tfTests.getText() + "', MEDREP_FILE = '"+tfMed.getText() + "' WHERE  PATIENT_ID = '" + tfPatId.getText() +"';";
            executeQuery(query);
            actPatLogout();
        }
    }
    public void actPatLogout(){
        String query = "UPDATE PATIENT SET ACTIVITY_STAT = 0 WHERE PATIENT_ID = '" + tfPatId.getText() + "';";
        executeQuery(query);
        lbSave.setText("Patient details have been Updated ");
        showPats();
    }
    public ObservableList<Patient> getActPatList() {
//        String DOCID = String.valueOf(Integer.parseInt(lbDocId.getText()));
        ObservableList<Patient> loginList = FXCollections.observableArrayList();
        dbConnection patConn = new dbConnection();
        Connection conn = patConn.getconnection();
        String query = "SELECT * FROM PATIENT WHERE ACTIVITY_STAT = 1 AND DOC_ID = '" + USERID + "';";

//        String query = "SELECT * FROM PATIENT WHERE ACTIVITY_STAT = 1"; // AND DOC_ID = '" + lbDocId.getText() + "';";
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
    public void showPats(){
        ObservableList<Patient> list = getActPatList();

        colPatId.setCellValueFactory(new PropertyValueFactory<Patient, Integer>("patId"));
        colPatName.setCellValueFactory(new PropertyValueFactory<Patient, String>("patName"));
        colPatAge.setCellValueFactory(new PropertyValueFactory<Patient, Integer>("patAge"));
        colPatDisease.setCellValueFactory(new PropertyValueFactory<Patient, String>("patDisease"));
        colPatMed.setCellValueFactory(new PropertyValueFactory<Patient, String>("patMedRep"));
        colPatTest.setCellValueFactory(new PropertyValueFactory<Patient, String>("patTestRep"));
        tvPat.setItems(list);
    }
}

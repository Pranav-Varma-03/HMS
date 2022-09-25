package com.example.hms4;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class DisplayDocController implements Initializable{
    @FXML
    private Button btBack;
    @FXML
    private Button btlogout;
    @FXML
    private CheckBox cbAvailNow;
    @FXML
    private CheckBox cbGen;
    @FXML
    private CheckBox cbNGen;
    @FXML
    private CheckBox cbAll;
    @FXML
    private TableView<Doctor> tvDoc;
    @FXML
    private TableColumn<Doctor,Integer> colDocId;
    @FXML
    private TableColumn<Doctor,String> colDocName;
    @FXML
    private TableColumn<Doctor,String> colSpec;
    @FXML
    private TableColumn<Doctor,String> colTimeSlot;
    @FXML
    private TableColumn<Doctor,Integer> colWorkStat;
    @FXML
    private TableColumn<Doctor,String> colPhNo;
    public void initialize(URL url, ResourceBundle rb){
        showDocs();
    }

    public void goBack() throws IOException{
        HelloApplication m = new HelloApplication();
        m.changescene("admin.fxml");
    }
    public void adminLogOut() throws IOException{
        HelloApplication m = new HelloApplication();
        m.changescene("hello-view.fxml");
    }
    public void cbGen() throws IOException{
        if(cbGen.isSelected()) {
            showGenDocs();
            cbNGen.setSelected(false);
            cbAll.setSelected(false);
        }
    }
    public void cbNGen() throws IOException{
        if(cbNGen.isSelected()) {
            showSpecDocs();
            cbGen.setSelected(false);
            cbAll.setSelected(false);
        }
    }
    public void cbAvailNow() throws IOException{
        if(cbAvailNow.isSelected()) {
            showActDocs();
            cbAll.setSelected(false);
        }
    }
    public void cbAllDoc() throws IOException{
       if(cbAll.isSelected()) {
           showDocs();
           cbAvailNow.setSelected(false);
           cbGen.setSelected(false);
           cbNGen.setSelected(false);
       }
    }
    public ObservableList<Doctor> getDocList() {
        ObservableList<Doctor> loginList = FXCollections.observableArrayList();
        dbConnection docConn = new dbConnection();
        Connection conn = docConn.getconnection();
        String query = "SELECT * FROM DOCTOR ";
        Statement st;
        ResultSet rs;
        try {
            st = conn.createStatement();
            rs = st.executeQuery(query);
            Doctor docCredDetails;
            while (rs.next()) {
                docCredDetails = new Doctor(rs.getInt("DOC_ID"), rs.getString("PASSWORD"), rs.getString("NAME"),  rs.getString("GENDER"),rs.getString("SPECIALIZATION"),rs.getString("TIME_SLOT"),rs.getString("PHONE_NUM"), rs.getString("CATEGORY"), rs.getInt("AGE"),rs.getInt("WORKING_STAT"));
                loginList.add(docCredDetails);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return loginList;
    }
    public ObservableList<Doctor> getActDocList() {
        ObservableList<Doctor> loginList = FXCollections.observableArrayList();
        dbConnection docConn = new dbConnection();
        Connection conn = docConn.getconnection();
        String query = "SELECT * FROM DOCTOR WHERE WORKING_STAT=1";
        Statement st;
        ResultSet rs;
        try {
            st = conn.createStatement();
            rs = st.executeQuery(query);
            Doctor docCredDetails;
            while (rs.next()) {
                docCredDetails = new Doctor(rs.getInt("DOC_ID"), rs.getString("PASSWORD"), rs.getString("NAME"),  rs.getString("GENDER"),rs.getString("SPECIALIZATION"),rs.getString("TIME_SLOT"),rs.getString("PHONE_NUM"), rs.getString("CATEGORY"), rs.getInt("AGE"),rs.getInt("WORKING_STAT"));
                loginList.add(docCredDetails);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return loginList;
    }
    public ObservableList<Doctor> getSpecDocList() {
        ObservableList<Doctor> loginList = FXCollections.observableArrayList();
        dbConnection docConn = new dbConnection();
        Connection conn = docConn.getconnection();
        String query = "SELECT * FROM DOCTOR WHERE CATEGORY = 'NGEN'";
        Statement st;
        ResultSet rs;
        try {
            st = conn.createStatement();
            rs = st.executeQuery(query);
            Doctor docCredDetails;
            while (rs.next()) {
                docCredDetails = new Doctor(rs.getInt("DOC_ID"), rs.getString("PASSWORD"), rs.getString("NAME"),  rs.getString("GENDER"),rs.getString("SPECIALIZATION"),rs.getString("TIME_SLOT"),rs.getString("PHONE_NUM"), rs.getString("CATEGORY"), rs.getInt("AGE"),rs.getInt("WORKING_STAT"));
                loginList.add(docCredDetails);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return loginList;
    }
    public ObservableList<Doctor> getGenDocList() {
        ObservableList<Doctor> loginList = FXCollections.observableArrayList();
        dbConnection docConn = new dbConnection();
        Connection conn = docConn.getconnection();
        String query = "SELECT * FROM DOCTOR WHERE CATEGORY = 'GEN'";
        Statement st;
        ResultSet rs;
        try {
            st = conn.createStatement();
            rs = st.executeQuery(query);
            Doctor docCredDetails;
            while (rs.next()) {
                docCredDetails = new Doctor(rs.getInt("DOC_ID"), rs.getString("PASSWORD"), rs.getString("NAME"),  rs.getString("GENDER"),rs.getString("SPECIALIZATION"),rs.getString("TIME_SLOT"),rs.getString("PHONE_NUM"), rs.getString("CATEGORY"), rs.getInt("AGE"),rs.getInt("WORKING_STAT"));
                loginList.add(docCredDetails);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return loginList;
    }
    public void showDocs(){
        ObservableList<Doctor> list = getDocList();

        colDocId.setCellValueFactory(new PropertyValueFactory<Doctor, Integer>("docId"));
        colDocName.setCellValueFactory(new PropertyValueFactory<Doctor, String>("docName"));
        colSpec.setCellValueFactory(new PropertyValueFactory<Doctor, String>("docSpec"));
        colTimeSlot.setCellValueFactory(new PropertyValueFactory<Doctor, String>("docTime"));
        colWorkStat.setCellValueFactory(new PropertyValueFactory<Doctor, Integer>("docWorkStat"));
        colPhNo.setCellValueFactory(new PropertyValueFactory<Doctor, String>("docPhoneNum"));

        tvDoc.setItems(list);
    }
    public void showGenDocs(){
        ObservableList<Doctor> list = getGenDocList();

        colDocId.setCellValueFactory(new PropertyValueFactory<Doctor, Integer>("docId"));
        colDocName.setCellValueFactory(new PropertyValueFactory<Doctor, String>("docName"));
        colSpec.setCellValueFactory(new PropertyValueFactory<Doctor, String>("docSpec"));
        colTimeSlot.setCellValueFactory(new PropertyValueFactory<Doctor, String>("docTime"));
        colWorkStat.setCellValueFactory(new PropertyValueFactory<Doctor, Integer>("docWorkStat"));
        colPhNo.setCellValueFactory(new PropertyValueFactory<Doctor, String>("docPhoneNum"));

        tvDoc.setItems(list);
    }
    public void showSpecDocs(){
        ObservableList<Doctor> list = getSpecDocList();

        colDocId.setCellValueFactory(new PropertyValueFactory<Doctor, Integer>("docId"));
        colDocName.setCellValueFactory(new PropertyValueFactory<Doctor, String>("docName"));
        colSpec.setCellValueFactory(new PropertyValueFactory<Doctor, String>("docSpec"));
        colTimeSlot.setCellValueFactory(new PropertyValueFactory<Doctor, String>("docTime"));
        colWorkStat.setCellValueFactory(new PropertyValueFactory<Doctor, Integer>("docWorkStat"));
        colPhNo.setCellValueFactory(new PropertyValueFactory<Doctor, String>("docPhoneNum"));

        tvDoc.setItems(list);
    }
    public void showActDocs(){
        ObservableList<Doctor> list = getActDocList();

        colDocId.setCellValueFactory(new PropertyValueFactory<Doctor, Integer>("docId"));
        colDocName.setCellValueFactory(new PropertyValueFactory<Doctor, String>("docName"));
        colSpec.setCellValueFactory(new PropertyValueFactory<Doctor, String>("docSpec"));
        colTimeSlot.setCellValueFactory(new PropertyValueFactory<Doctor, String>("docTime"));
        colWorkStat.setCellValueFactory(new PropertyValueFactory<Doctor, Integer>("docWorkStat"));
        colPhNo.setCellValueFactory(new PropertyValueFactory<Doctor, String>("docPhoneNum"));

        tvDoc.setItems(list);
    }
}

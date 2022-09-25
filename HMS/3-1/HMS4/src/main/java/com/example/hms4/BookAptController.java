package com.example.hms4;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

public class BookAptController implements Initializable {
      @FXML
      private Button btBook;
    @FXML
    private Button btBack;
    @FXML
    private Button btLogout;
    @FXML
    private TextField tfDrId;
    @FXML
    private CheckBox cbGen;
    @FXML
    private CheckBox cbSpec;
    @FXML
    private CheckBox cbBoth;
    @FXML
    private TableView<Doctor> tvDocList;
    @FXML
    private TableColumn<Doctor,Integer> colDocId;
    @FXML
    private TableColumn<Doctor,String> colDocName;
    @FXML
    private TableColumn<Doctor,String> colSpec;
    @FXML
    private TableColumn<Doctor,String> colTimeSlot;
    @FXML
    private Label lbSaved;

    @FXML
    private void handleMouseAction(MouseEvent event){
//        System.out.println("mouse Clicked");
        Doctor currentDoc = tvDocList.getSelectionModel().getSelectedItem();
        tfDrId.setText(""+currentDoc.getDocId());
    }
    @Override
    public void initialize(URL url, ResourceBundle rb){
        // TO DO
        showDocs();
    }
      
       public void adminLogOut() throws IOException{
           HelloApplication m = new HelloApplication();
           m.changescene("hello-view.fxml");
       }
       public void goBack() throws IOException{
           HelloApplication m = new HelloApplication();
           m.changescene("patient.fxml");
       }

    public void bookApts(){
        PatientController patConstructor = new PatientController();

        String query = "UPDATE PATIENT SET DOC_ID = '"+ tfDrId.getText() + "' WHERE PATIENT_ID= '"+ patConstructor.PAT_USERID + "';";
        executeQuery(query);
        lbSaved.setText("Appointment Booked Successfully,\n" + "Please Logout.!!");
        turnActOn();
        tfDrId.setText("");
    }
    public void turnActOn(){
        PatientController patConstructor = new PatientController();

        dbConnection patConn = new dbConnection();
        Connection conn = patConn.getconnection();
        String query = "UPDATE PATIENT SET ACTIVITY_STAT = 1 WHERE PATIENT_ID = '" + patConstructor.PAT_USERID + "';";
        executeQuery(query);

    }
    public void showDocs(){
        ObservableList<Doctor> list = getDocList();

        colDocId.setCellValueFactory(new PropertyValueFactory<Doctor, Integer>("docId"));
        colDocName.setCellValueFactory(new PropertyValueFactory<Doctor, String>("docName"));
        colSpec.setCellValueFactory(new PropertyValueFactory<Doctor, String>("docSpec"));
        colTimeSlot.setCellValueFactory(new PropertyValueFactory<Doctor, String>("docTime"));

        tvDocList.setItems(list);
    }
    public void showGenDocs(){
        ObservableList<Doctor> list = getGenDocList();

        colDocId.setCellValueFactory(new PropertyValueFactory<Doctor, Integer>("docId"));
        colDocName.setCellValueFactory(new PropertyValueFactory<Doctor, String>("docName"));
        colSpec.setCellValueFactory(new PropertyValueFactory<Doctor, String>("docSpec"));
        colTimeSlot.setCellValueFactory(new PropertyValueFactory<Doctor, String>("docTime"));

        tvDocList.setItems(list);
    }
    public void showSpecDocs(){
        ObservableList<Doctor> list = getSpecDocList();

        colDocId.setCellValueFactory(new PropertyValueFactory<Doctor, Integer>("docId"));
        colDocName.setCellValueFactory(new PropertyValueFactory<Doctor, String>("docName"));
        colSpec.setCellValueFactory(new PropertyValueFactory<Doctor, String>("docSpec"));
        colTimeSlot.setCellValueFactory(new PropertyValueFactory<Doctor, String>("docTime"));

        tvDocList.setItems(list);
    }
    public ObservableList<Doctor> getDocList() {
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
    public ObservableList<Doctor> getGenDocList() {
        ObservableList<Doctor> loginList = FXCollections.observableArrayList();
        dbConnection docConn = new dbConnection();
        Connection conn = docConn.getconnection();
        String query = "SELECT * FROM DOCTOR WHERE CATEGORY = 'GEN' AND WORKING_STAT=1";
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
        String query = "SELECT * FROM DOCTOR WHERE CATEGORY = 'NGEN' AND WORKING_STAT=1";
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

    public void bookBoth() throws IOException{
        if(cbBoth.isSelected()) {
            showDocs();
            cbSpec.setSelected(false);
            cbGen.setSelected(false);
        }
    }
    public void bookGen() throws IOException{
        if(cbGen.isSelected()) {
            showGenDocs();
            cbSpec.setSelected(false);
            cbBoth.setSelected(false);
        }
    }
    public void bookSpec() throws IOException{
        if(cbSpec.isSelected()) {
            showSpecDocs();
            cbGen.setSelected(false);
            cbBoth.setSelected(false);
        }
    }

}

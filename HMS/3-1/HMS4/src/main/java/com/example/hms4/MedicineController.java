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

public class MedicineController implements Initializable {
    @FXML
    private TextField tfMedName;
    @FXML
    private TextField tfMedId;
    @FXML
    private TextField tfExpDt;
    @FXML
    private CheckBox cbExp;
    @FXML
    private CheckBox cbNExp;
    @FXML
    private CheckBox cbBothExpNexp;
    @FXML
    private Button btAdd;
    @FXML
    private Button Back;
    @FXML
    private Button btRemove;
    @FXML
    private TableView<Medicine> tvMedList;
    @FXML
    private TableColumn<Medicine,Integer> colMedId;
    @FXML
    private TableColumn<Medicine,String> colName;
    @FXML
    private TableColumn<Medicine,String> colExpDt;
    @FXML
    private TableColumn<Medicine,Integer> colActStat;

    @Override
    public void initialize(URL url, ResourceBundle rb){
        showMed();
    }

    public void adminLogOut() throws IOException{
         HelloApplication m = new HelloApplication();
         m.changescene("hello-view.fxml");
    }
    @FXML
    private void handleMouseAction(MouseEvent event){
        Medicine currentMed = tvMedList.getSelectionModel().getSelectedItem();
        tfMedId.setText(String.valueOf(currentMed.getMedId()));
    }
    public ObservableList<Medicine> getMedList() {
//        String DOCID = String.valueOf(Integer.parseInt(lbDocId.getText()));
        ObservableList<Medicine> loginList = FXCollections.observableArrayList();
        dbConnection medConn = new dbConnection();
        Connection conn = medConn.getconnection();
        String query = "SELECT * FROM MEDICINE;";

//        String query = "SELECT * FROM PATIENT WHERE ACTIVITY_STAT = 1"; // AND DOC_ID = '" + lbDocId.getText() + "';";
        Statement st;
        ResultSet rs;
        try {
            st = conn.createStatement();
            rs = st.executeQuery(query);
            Medicine medList;
            while (rs.next()) {
                medList = new Medicine(rs.getInt("MED_ID"),rs.getString("NAME"), rs.getString("EXPIRY"), rs.getInt("ACTIVITY_STAT"));
                loginList.add(medList);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return loginList;
    }
    public ObservableList<Medicine> getActMedList() {
//        String DOCID = String.valueOf(Integer.parseInt(lbDocId.getText()));
        ObservableList<Medicine> loginList = FXCollections.observableArrayList();
        dbConnection medConn = new dbConnection();
        Connection conn = medConn.getconnection();
        String query = "SELECT * FROM MEDICINE WHERE ACTIVITY_STAT = 1;";

//        String query = "SELECT * FROM PATIENT WHERE ACTIVITY_STAT = 1"; // AND DOC_ID = '" + lbDocId.getText() + "';";
        Statement st;
        ResultSet rs;
        try {
            st = conn.createStatement();
            rs = st.executeQuery(query);
            Medicine medList;
            while (rs.next()) {
                medList = new Medicine(rs.getInt("MED_ID"),rs.getString("NAME"), rs.getString("EXPIRY"), rs.getInt("ACTIVITY_STAT"));
                loginList.add(medList);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return loginList;
    }
    public ObservableList<Medicine> getExpMedList() {
//        String DOCID = String.valueOf(Integer.parseInt(lbDocId.getText()));
        ObservableList<Medicine> loginList = FXCollections.observableArrayList();
        dbConnection medConn = new dbConnection();
        Connection conn = medConn.getconnection();
        String query = "SELECT * FROM MEDICINE WHERE ACTIVITY_STAT = 0;";

//        String query = "SELECT * FROM PATIENT WHERE ACTIVITY_STAT = 1"; // AND DOC_ID = '" + lbDocId.getText() + "';";
        Statement st;
        ResultSet rs;
        try {
            st = conn.createStatement();
            rs = st.executeQuery(query);
            Medicine medList;
            while (rs.next()) {
                medList = new Medicine( rs.getInt("MED_ID"),rs.getString("NAME"), rs.getString("EXPIRY"), rs.getInt("ACTIVITY_STAT"));
                loginList.add(medList);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return loginList;
    }
    public void addMeds() throws IOException{
        String query = "INSERT INTO MEDICINE(NAME,EXPIRY) VALUES('" + tfMedName.getText() + "','" + tfExpDt.getText() + "');";
        executeQuery(query);
        showMed();
    }
    private void executeQuery(String query)  {
        dbConnection MedConn = new dbConnection();
        Connection conn = MedConn.getconnection();
        Statement st;
        try{
            st = conn.createStatement();
            st.executeUpdate(query);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
    public void goBack() throws IOException {
        HelloApplication m = new HelloApplication();
        m.changescene("admin.fxml");
    }
    public void showMed() {
        ObservableList<Medicine> list = getMedList();

        colMedId.setCellValueFactory(new PropertyValueFactory<Medicine, Integer>("medId"));
        colName.setCellValueFactory(new PropertyValueFactory<Medicine, String>("medName"));
        colExpDt.setCellValueFactory(new PropertyValueFactory<Medicine, String>("medExpDate"));
        colActStat.setCellValueFactory(new PropertyValueFactory<Medicine, Integer>("medActStat"));
        tvMedList.setItems(list);
    }
    public void showExpMeds() throws IOException{
        if(cbExp.isSelected()) {
            ObservableList<Medicine> list = getExpMedList();

            colMedId.setCellValueFactory(new PropertyValueFactory<Medicine, Integer>("medId"));
            colName.setCellValueFactory(new PropertyValueFactory<Medicine, String>("medName"));
            colExpDt.setCellValueFactory(new PropertyValueFactory<Medicine, String>("medExpDate"));
            colActStat.setCellValueFactory(new PropertyValueFactory<Medicine, Integer>("medActStat"));
            tvMedList.setItems(list);

            cbNExp.setSelected(false);
            cbBothExpNexp.setSelected(false);
        }
    }
    public void showNExpMeds() throws IOException{
        if(cbNExp.isSelected()) {

            ObservableList<Medicine> list = getActMedList();

            colMedId.setCellValueFactory(new PropertyValueFactory<Medicine, Integer>("medId"));
            colName.setCellValueFactory(new PropertyValueFactory<Medicine, String>("medName"));
            colExpDt.setCellValueFactory(new PropertyValueFactory<Medicine, String>("medExpDate"));
            colActStat.setCellValueFactory(new PropertyValueFactory<Medicine, Integer>("medActStat"));
            tvMedList.setItems(list);

            cbExp.setSelected(false);
            cbBothExpNexp.setSelected(false);
        }
    }
    public void showBothMeds() throws IOException{
        if(cbBothExpNexp.isSelected()) {
            ObservableList<Medicine> list = getMedList();

            colMedId.setCellValueFactory(new PropertyValueFactory<Medicine, Integer>("medId"));
            colName.setCellValueFactory(new PropertyValueFactory<Medicine, String>("medName"));
            colExpDt.setCellValueFactory(new PropertyValueFactory<Medicine, String>("medExpDate"));
            colActStat.setCellValueFactory(new PropertyValueFactory<Medicine, Integer>("medActStat"));
            tvMedList.setItems(list);

            cbExp.setSelected(false);
            cbNExp.setSelected(false);
        }
    }
    public void removeMeds() throws IOException{
        String query = "DELETE FROM MEDICINE WHERE MED_ID = '" + tfMedId.getText() + "';";
        executeQuery(query);
        showMed();
    }

}

package com.example.hms4;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class DispPatController implements Initializable {
    @FXML
    private Button btback;
    @FXML
    private Button btlogout;
    @FXML
    private TableView<PatDocJoin> tvPatList;
    @FXML
    private TableColumn<PatDocJoin,Integer> colPatID;
    @FXML
    private TableColumn<PatDocJoin,String> colName;
    @FXML
    private TableColumn<PatDocJoin,Integer> colAge;
    @FXML
    private TableColumn<PatDocJoin,Integer> colDocId;
    @FXML
    private TableColumn<PatDocJoin,String> colTOA;
    @FXML
    private TableColumn<PatDocJoin,Integer> colActStat;
    @FXML
    private TableColumn<PatDocJoin,String> colPhNo;

    @Override
    public void initialize(URL url, ResourceBundle rb){
       showPats();
    }

    public void adminLogOut() throws IOException{
        HelloApplication m = new HelloApplication();
        m.changescene("hello-view.fxml");
    }
    public void goBack() throws IOException{
        HelloApplication m = new HelloApplication();
        m.changescene("admin.fxml");
    }
    public ObservableList<PatDocJoin> getPatList() {

        ObservableList<PatDocJoin> loginList = FXCollections.observableArrayList();
        dbConnection patConn = new dbConnection();
        Connection conn = patConn.getconnection();
        String query = "SELECT PATIENT.PATIENT_ID, PATIENT.NAME, PATIENT.AGE,PATIENT.DOC_ID,DOCTOR.TIME_SLOT,PATIENT.ACTIVITY_STAT,PATIENT.PHONE_NUM FROM PATIENT INNER JOIN DOCTOR ON PATIENT.DOC_ID=DOCTOR.DOC_ID;";

        Statement st;
        ResultSet rs;
        try {
            st = conn.createStatement();
            rs = st.executeQuery(query);
            PatDocJoin patCredDetails;
            while (rs.next()) {
                patCredDetails = new PatDocJoin(rs.getInt("PATIENT_ID"), rs.getString("NAME"), rs.getInt("AGE   "), rs.getInt("DOC_ID"), rs.getString("TIME_SLOT"), rs.getInt("ACTIVITY_STAT"), rs.getString("PHONE_NUM"));
                loginList.add(patCredDetails);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return loginList;
    }
    public void showPats(){
        ObservableList<PatDocJoin> list = getPatList();

        colPatID.setCellValueFactory(new PropertyValueFactory<PatDocJoin, Integer>("patId"));
        colName.setCellValueFactory(new PropertyValueFactory<PatDocJoin, String>("patName"));
        colAge.setCellValueFactory(new PropertyValueFactory<PatDocJoin, Integer>("patAge"));
        colDocId.setCellValueFactory(new PropertyValueFactory<PatDocJoin, Integer>("patDocId"));
        colTOA.setCellValueFactory(new PropertyValueFactory<PatDocJoin, String>("docTimeOfApt"));
        colActStat.setCellValueFactory(new PropertyValueFactory<PatDocJoin, Integer>("patActStat"));
        colPhNo.setCellValueFactory(new PropertyValueFactory<PatDocJoin, String>("patPhNo"));
        tvPatList.setItems(list);
    }
}

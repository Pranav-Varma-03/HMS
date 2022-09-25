package com.example.hms4;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class editDI {
    @FXML
    private Button btLogOut;
    @FXML
    private Button btBack;
    @FXML
    private Button btVerify;
    @FXML
    private Button btRm;
    @FXML
    private Button btSave;
    @FXML
    private TextField tfDocId;
    @FXML
    private PasswordField pfDocPwd;
    @FXML
    private TextField tfPhnNo;
    @FXML
    private Label lbOldTime;
    @FXML
    private Label lbOldPhnNo;
    @FXML
    private Label lbVerify;

    @FXML
    private TextField tfNewTime;
    @FXML
    private Label lbMessage;

//    @Override
//    public void initialize(URL url, ResourceBundle rb){
//       loadOldTime();
//    }
    public void verifyCred(ActionEvent event){
        if(checkCred() == 1 ){
            lbVerify.setText("Verified.!! Proceed to Edit Details");
            loadOldTime();
        }
        else{
            lbVerify.setText("Wrong Credentials, Re Enter Doctor Details");
        }
    }
    public void loadOldTime(){
        dbConnection docConn = new dbConnection();
        Connection conn = docConn.getconnection();
        String query = "SELECT * FROM DOCTOR WHERE DOC_ID = '" + tfDocId.getText() + "';";
        Statement st;
        ResultSet rs;
        try {
            st = conn.createStatement();
            rs = st.executeQuery(query);
            rs.next();
            lbOldTime.setText(rs.getString("TIME_SLOT")) ;
            lbOldPhnNo.setText(rs.getString("PHONE_NUM"));

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public ObservableList<Doctor> getDocList() {
        ObservableList<Doctor> loginList = FXCollections.observableArrayList();
        dbConnection docConn = new dbConnection();
        Connection conn = docConn.getconnection();
        String query = "SELECT * FROM DOCTOR";
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
//    public void saveInfo() throws IOException {
//        if(tfpwd.getText().toString().equals("232")){
//            HelloApplication m = new HelloApplication();
//            m.changescene("hello-view.fxml");
//        }
//        else {
//            wrong.setText("Data you entered is wrong.");
//        }
//    }
    public void updateTimeSlots(){
        if(tfNewTime.getText() == ""){
            tfNewTime.setText(lbOldTime.getText());
        }
        if(tfPhnNo.getText() == ""){
            tfPhnNo.setText(lbOldPhnNo.getText());
        }
    }
public void saveInfo(ActionEvent event) throws IOException {
        updateTimeSlots();
    if ((event.getSource() == btSave) && (checkCred() == 1)) {
        String query = "UPDATE DOCTOR SET PHONE_NUM = '"+ tfPhnNo.getText() + "', TIME_SLOT= '"+ tfNewTime.getText() +"' WHERE  DOC_ID = '" + tfDocId.getText() +"';";
        executeQuery(query);
        lbMessage.setText("Details Edited Successfully..");
        lbVerify.setText("");
    }
    else {
        lbMessage.setText("Wrong Credentials, Please Re Enter");
        lbVerify.setText("");
    }
//        HelloApplication m = new HelloApplication();
//        m.changescene("admin.fxml");
}
    public void removeDoc(ActionEvent event) throws IOException {
        updateTimeSlots();
        if ((event.getSource() == btSave) && (checkCred() == 1)) {
            String query = "UPDATE DOCTOR SET WORKING_STAT = 0 WHERE  DOC_ID = '" + tfDocId.getText() +"';";
            executeQuery(query);
            lbMessage.setText("Details Edited Successfully..");
            lbVerify.setText("");
        }
        else {
            lbMessage.setText("Wrong Credentials, Please Re Enter");
            lbVerify.setText("");
        }
//        HelloApplication m = new HelloApplication();
//        m.changescene("admin.fxml");
    }
    public void adminHome(ActionEvent event) throws IOException {
        HelloApplication m = new HelloApplication();
        m.changescene("admin.fxml");
    }
    public void adminLogOut(ActionEvent event) throws IOException {
        HelloApplication m = new HelloApplication();
        m.changescene("hello-view.fxml");
    }
    public int checkCred() {
        int k=0;
        ObservableList<Doctor> currentList = getDocList();
        for (int i = 0; i < currentList.size(); i++) {
            Doctor docCredDetails = currentList.get(i);
            if ((docCredDetails.getDocId() == Integer.parseInt(tfDocId.getText())) && docCredDetails.getDocPwd().equals(pfDocPwd.getText())) {
                k = 1;
                break;
            } else {
                k = 0;
            }
        }
        return k;
    }
    private void executeQuery(String query)  {
        dbConnection docConn = new dbConnection();
        Connection conn = docConn.getconnection();
        Statement st;
        try{
            st = conn.createStatement();
            st.executeUpdate(query);
        }catch (Exception ex){
            ex.printStackTrace();
        }

    }
//    public void removeDr(ActionEvent event) throws IOException {
//        HelloApplication m = new HelloApplication();
//        m.changescene("hello-view.fxml");
//    }
}

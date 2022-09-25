package com.example.hms4;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class addDR {
    @FXML
    private Button btAddDr;
    @FXML
    private TextField tfName;
    @FXML
    private TextField tfAge;
    @FXML
    private TextField tfPhNo;
    @FXML
    private TextField tfGender;
    @FXML
    private CheckBox cbCatGen;
    @FXML
    private CheckBox cbCatNgen;
    @FXML
    private TextField tfSpec;
    @FXML
    private CheckBox cbTimeS1; // 6 to 12
    @FXML
    private CheckBox cbTimeS2; // 12 to 18
    @FXML
    private CheckBox cbTimeS3; // 18 to 24
    @FXML
    private CheckBox cbTimeS4; // 24 to 6
    @FXML
    private TextField tfSetPwd;
    private String time;
    private String cat;
    @FXML
    private Label lbSaved;
    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getCat() {
        return cat;
    }

    public void setCat(String cat) {
        this.cat = cat;
    }

    public void adminLogOut(ActionEvent event) throws IOException {
        HelloApplication m = new HelloApplication();
        m.changescene("hello-view.fxml");
    }
    public void goBack() throws IOException {
        HelloApplication m = new HelloApplication();
        m.changescene("admin.fxml");
    }
    public void catCheckBox(ActionEvent event){

         if(cbCatGen.isSelected()){
             setCat("GEN");
             cbCatNgen.setSelected(false);
         }
        else if(cbCatNgen.isSelected()){
             setCat("NGEN");
             cbCatGen.setSelected(false);
         }
    }
    public void timeCheckBox(){

        if(cbTimeS1.isSelected()){
            setTime("6:00 to 12:00");
            cbTimeS2.setSelected(false);
            cbTimeS3.setSelected(false);
            cbTimeS4.setSelected(false);
        }
        else if(cbTimeS2.isSelected()){
            setTime("12:00 to 18:00");
            cbTimeS1.setSelected(false);
            cbTimeS3.setSelected(false);
            cbTimeS4.setSelected(false);
        }
        else if(cbTimeS3.isSelected()){
            setTime("18:00 to 24:00");
            cbTimeS2.setSelected(false);
            cbTimeS1.setSelected(false);
            cbTimeS4.setSelected(false);
        }
        else if(cbTimeS4.isSelected()){
            setTime("24:00 to 6:00");
            cbTimeS2.setSelected(false);
            cbTimeS3.setSelected(false);
            cbTimeS1.setSelected(false);
        }
    }

    public void btAddDr(ActionEvent event) throws IOException {
        String query = "INSERT INTO DOCTOR(PASSWORD,NAME,AGE,GENDER,PHONE_NUM,SPECIALIZATION,TIME_SLOT,CATEGORY) VALUES('" + tfSetPwd.getText() + "','" + tfName.getText() + "'," + tfAge.getText() + ",'" + tfGender.getText() + "','" + tfPhNo.getText() + "','" + tfSpec.getText() + "','" + getTime() + "','" + getCat() + "');";
        executeQuery(query);
        dbConnection PatConn = new dbConnection();
        Connection conn = PatConn.getconnection();

        query = "SELECT * FROM DOCTOR ORDER BY DOC_ID DESC ;";
        Statement st;
        ResultSet rs;
        try {
            st = conn.createStatement();
            rs = st.executeQuery(query);
            rs.next();
            query = "INSERT INTO LOGINCRED(USER_ID,PASSWORD,ROLE) VALUES('" + rs.getString("DOC_ID") + "','" + tfSetPwd.getText() + "','Doctor');";
            executeQuery(query);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        if((event.getSource() == btAddDr)) {
            lbSaved.setText("Added Doctor Successfully");
        }
    }
    private void executeQuery(String query)  {
        dbConnection addDocConn = new dbConnection();
        Connection conn = addDocConn.getconnection();
        Statement st;
        try{
            st = conn.createStatement();
            st.executeUpdate(query);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}

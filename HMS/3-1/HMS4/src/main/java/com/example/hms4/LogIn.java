package com.example.hms4;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.*;
import java.io.IOException;

//import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Node;


public class LogIn {

    @FXML
    private Button bLogin;
    @FXML
    private TextField Username;
    @FXML
    private PasswordField Password;
    @FXML
    private  Button bEmer;
    @FXML
    private Label wrong;
//    private String userName;
private Parent root;
private Stage stage;
private Scene scene;
    public String getuserName() {

        return Username.getText();
    }
    @FXML
    private void adminlogin(ActionEvent event) throws IOException {
        if (Username.getText().equals("") || Password.getText().equals("")) {
            wrong.setText("Please Enter Credentials");
        }
        else{
            checkLogin(event);
        }
    }
    public void emerPage() throws IOException {
        HelloApplication m = new HelloApplication();
        m.changescene("emer.fxml");
    }
//    public void checkLogin() throws IOException {
//        HelloApplication m = new HelloApplication();
//        if(Username.getText().toString().equals("admin") && Password.getText().toString().equals("232")) {
//            wrong.setText("Success");
//            m.changescene("admin.fxml");
//
//        }
//        else if (Username.getText().isEmpty() && Password.getText().isEmpty()) {
//            wrong.setText("Please enter some data.");
//        }
//        else if ( Password.getText().isEmpty()) {
//            wrong.setText("Please enter Password.");
//        }
//        else {
//            wrong.setText("Data you entered is wrong.");
//        }
//    }
//    public Connection getconnection(){
//        Connection conn;
//        try{
//            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hmsdb_1","root","MysqlML@323");
//            return conn;
//        }catch(Exception ex){
//            System.out.println("Error: " + ex.getMessage());
//            return null;
//        }
//    }

    public ObservableList<loginCred> getLoginList(){
        ObservableList<loginCred> loginList = FXCollections.observableArrayList();
        dbConnection loginConn = new dbConnection();
        Connection conn = loginConn.getconnection();
        String query = "SELECT * FROM LOGINCRED";
        Statement st;
        ResultSet rs;
        try{
            st = conn.createStatement();
            rs = st.executeQuery(query);
            loginCred loginCredDetails;
            while(rs.next()){
                loginCredDetails = new loginCred(rs.getInt("USER_ID"),rs.getString("PASSWORD"),rs.getString("ROLE"));
                loginList.add(loginCredDetails);
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return loginList;
    }

    public void checkLogin(ActionEvent event) throws IOException {
        HelloApplication m = new HelloApplication();
        ObservableList<loginCred> currentList = getLoginList();

        String userName = Username.getText();

        for ( int i = 0; i< currentList.size(); i++){
            loginCred loginCredDetails = currentList.get(i);
            if((loginCredDetails.getUSER_ID() == Integer.parseInt(Username.getText()))&& (loginCredDetails.getPASSWORD().equals(Password.getText())) && (loginCredDetails.getROLE().equals("Doctor"))){
                wrong.setText("Success");
//            m.changescene("doctor.fxml");
//                FXMLLoader loader = new FXMLLoader(getClass().getResource("doctor.fxml"));
//                root = loader.load();
//
//                DoctorConstructor doctorConstructor = loader.getController();
//                doctorConstructor.displayName(userName);
//
//                stage = (Stage)((Node)event.getSource()).getScene().getWindow();
//                scene = new Scene(root);
//                stage.setScene(scene);
//                stage.show();
                DoctorController doctorConstructor = new DoctorController();
                doctorConstructor.setUSERID(userName);
                m.changescene("doctor.fxml");
            }
            else if((loginCredDetails.getUSER_ID() == Integer.parseInt(Username.getText()))&& (loginCredDetails.getPASSWORD().equals(Password.getText())) && (loginCredDetails.getROLE().equals("Patient"))){
                PatientController patConstructor = new PatientController();
                patConstructor.setPAT_USERID(userName);
                m.changescene("patient.fxml");
                wrong.setText("Success");
            }
            else if((loginCredDetails.getUSER_ID() == Integer.parseInt(Username.getText()))&& (loginCredDetails.getPASSWORD().equals(Password.getText())) && (loginCredDetails.getROLE().equals("Admin"))){
                m.changescene("admin.fxml");
                wrong.setText("Success");
            }
            else{
                wrong.setText("Wrong Credentials, Try again");
            }
        }
    }
}


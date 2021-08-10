package com.example.offshore_employees_v3;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.sql.Connection;

public class Login_Controller {

    @FXML
    private AnchorPane anchor_main;

    @FXML
    private Label lbl_invalid_credentials;

    @FXML
    private Label lbl_program_authenticating;

    @FXML
    private Button btn_continue_to_program;

    @FXML
    private Label lbl_connected;

    Connection conn = null;
    String DB_URL = "jdbc:sqlserver://INDDC-VM56;database=Migrant_Employees;integratedSecurity=true;responseBuffering=adaptive";     // Database URL

    @FXML
    void continue_to_program(MouseEvent event) {
        Main.createNewScene(event, "Employee_Dashboard.fxml");

    }

    void take_login(){
        //Method checks the validity of the user's credentials based on their access level to the database.

    }

    @FXML
    void intialize(){
      Access_Full_Employee_DB db = new Access_Full_Employee_DB();
      System.out.println(db.establish_connection());
           if(db.establish_connection()) {
               lbl_connected.setText("Connected");
               lbl_connected.setVisible(true);
               btn_continue_to_program.setVisible(true);
               lbl_program_authenticating.setVisible(false);
           }else{
            lbl_connected.setVisible(true);
            lbl_connected.setText("Cannot Connect");
            lbl_program_authenticating.setVisible(false);
        }

    }




}

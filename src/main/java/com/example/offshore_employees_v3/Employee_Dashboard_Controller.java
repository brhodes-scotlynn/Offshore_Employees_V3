//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.example.offshore_employees_v3;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class Employee_Dashboard_Controller {
    @FXML
    private Button btn_time_sheet;

    @FXML
    private Button btn_job_codes;

    @FXML
    private Button btn_employe_list;

    @FXML
    private Button btn_reports;

    @FXML
    private Button btn_quit;

    @FXML
    private ImageView imageview_scot;

    @FXML
    private ImageView imageview_clock;

    @FXML
    private ImageView imageview_codes;


    @FXML
    private ImageView imageview_people;

    @FXML
    private ImageView imageview_reports;

    @FXML
    private ImageView imageview_quit;

    public Employee_Dashboard_Controller() {
    }

    @FXML
    void go_to_employee_list(MouseEvent event) {
        Main.createNewScene(event, "Employee_List_Screen.fxml");

    }

    @FXML
    void go_to_job_codes(MouseEvent event) {
        Main.createNewScene(event, "Assign_Job_Screen.fxml");

    }

    @FXML
    void go_to_reports(MouseEvent event) {
        Main.createNewScene(event, "Reports_Screen.fxml");

    }

    @FXML
    void go_to_create_employee(MouseEvent event) {
        Main.createNewScene(event, "Create_Employee_Screen.fxml");

    }

    @FXML
    void quit_program(MouseEvent event) {
        Main.exit_program(event);

    }



    @FXML
    public void initialize(){


    }

}

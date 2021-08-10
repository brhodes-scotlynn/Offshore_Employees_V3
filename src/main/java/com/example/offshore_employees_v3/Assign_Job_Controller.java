package com.example.offshore_employees_v3;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;


public class Assign_Job_Controller {

    @FXML
    private Button btn_go_home;

    @FXML
    private Button btn_quit;

    @FXML
    private TextField txtfield_find_employee;

    @FXML
    private Button btn_go_find_employee;

    @FXML
    private Label lbl_employee_found;

    @FXML
    private TableView<Work_Hours> tblview_work_hours;

    @FXML
    private TableColumn<Work_Hours, Integer> col_employee_id;

    @FXML
    private TableColumn<Work_Hours, LocalDate> col_work_date;

    @FXML
    private TableColumn<?, ?> col_work_hours;

    @FXML
    private TableColumn<?, ?> col_job_name;

    @FXML
    private TextField txtfield_employee_id;

    @FXML
    private TextField txtfield_worked_hours;

    @FXML
    private ChoiceBox<String> choicebox_job_name;

    @FXML
    private DatePicker datepicker_date_of_work;

    @FXML
    private Button btn_add_to_work_hours;

    @FXML
    private Label lbl_work_hours_added_successfully;

    @FXML
    private Button btn_delete_selected_hours;

    Access_Full_Employee_DB db = new Access_Full_Employee_DB();
    int employee_id = 0;

    @FXML
    void add_to_work_hours(MouseEvent event) {
        tblview_work_hours.getItems().clear();
        DateTimeFormatter today_format = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        lbl_work_hours_added_successfully.setVisible(false);
        int hours_id = db.get_largest_work_hour_id();
        String hours_worked = txtfield_worked_hours.getText();
        String date_worked = datepicker_date_of_work.getValue().format(today_format).toString();
        String job_name = choicebox_job_name.getValue();
        try{
            Double check_for_double = Double.parseDouble(hours_worked);

            db.insert_to_work_hours(employee_id, hours_id, hours_worked, date_worked, job_name.substring(0,1), job_name);
            populate_work_hours_table();
            lbl_work_hours_added_successfully.setVisible(true);
        }catch(NumberFormatException | SQLException np){
            lbl_employee_found.setText("Please check that you entered a correct value for 'Hours Worked'");
            hours_worked = "0.0";
        }
    }

    @FXML
    void find_employee(MouseEvent event) {
        lbl_work_hours_added_successfully.setVisible(false);
        Large_Employee current_employee = null;
        try{
            employee_id = Integer.parseInt(txtfield_find_employee.getText());
            if(employee_id<db.get_largest_employee_id() && employee_id>0){
                lbl_employee_found.setText("Employee has been found: #"+employee_id);
            }else{
                lbl_employee_found.setText("Invalid Employee ID number: #"+employee_id);
            }
            txtfield_employee_id.setText(Integer.toString(employee_id));
        }catch(NumberFormatException np){
            lbl_employee_found.setText("Employee cannot be found.");
            txtfield_employee_id.setText("No ID found");
        }


    }

    @FXML
    void find_employee_ENTER(KeyEvent event) {
        if(event.getCode() == KeyCode.ENTER){
            int employee_id = 0;
            Large_Employee current_employee = null;
            try{
                employee_id = Integer.parseInt(txtfield_find_employee.getText());
                if(employee_id<db.get_largest_employee_id() && employee_id>0){
                    lbl_employee_found.setText("Employee has been found: #"+employee_id);
                }else{
                    lbl_employee_found.setText("Invalid Employee ID number: #"+employee_id);
                }
                ArrayList<Large_Employee> current_employee_list = db.search_for_employee_by_scotlynnID(employee_id);
                current_employee = current_employee_list.get(0);
            }catch(NumberFormatException np){
                lbl_employee_found.setText("Employee cannot be found.");
            }
        }
    }

    void populate_search_choicebox(){
        choicebox_job_name.getItems().add("Watermelon");
        choicebox_job_name.getItems().add("Corn");
        choicebox_job_name.getItems().add("Quarantine");
        choicebox_job_name.getItems().add("Pumpkin");
        choicebox_job_name.getItems().add("Other");
        choicebox_job_name.getItems().add("Ginseng");
        choicebox_job_name.getItems().add("Asparagus");
        choicebox_job_name.setValue("Other");
    }

    void populate_work_hours_table(){
        col_employee_id.setCellValueFactory(new PropertyValueFactory<>("employee_id"));
        col_work_date.setCellValueFactory(new PropertyValueFactory<>("date_worked_as_date"));
        col_work_hours.setCellValueFactory(new PropertyValueFactory<>("hours_worked"));
        col_job_name.setCellValueFactory(new PropertyValueFactory<>("job_name"));
       tblview_work_hours.getItems().addAll(db.get_all_work_hours_list_no_date());
        tblview_work_hours.getSortOrder().add(col_work_date);
    }

    @FXML
    void initialize(){
        populate_search_choicebox();
        populate_work_hours_table();
    }

    @FXML
    void go_home(MouseEvent event) { Main.createNewScene(event, "Employee_Dashboard.fxml"); }

    @FXML
    void quit_program(MouseEvent event) {
        Main.exit_program(event);
    }

    @FXML
    void delete_hours(MouseEvent event) {
       Work_Hours deleted_hours = tblview_work_hours.getSelectionModel().getSelectedItem();
       db.delete_work_hour(deleted_hours.hours_id);
       tblview_work_hours.getItems().clear();
       populate_work_hours_table();

    }



}

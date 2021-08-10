package com.example.offshore_employees_v3;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Employee_List_Controller {

    @FXML
    private AnchorPane btn_update_employee_info;

    @FXML
    private ChoiceBox<String> choicebox_search_type;

    @FXML
    private TextField txtfield_search_criteria;

    @FXML
    private Button btn_search_list;

    @FXML
    private Button btn_reset;

    @FXML
    private Button btn_home;

    @FXML
    private Button btn_quit;

    @FXML
    Button btn_update_info;

    @FXML
    Button btn_update_mexico_info;

    @FXML
    private Tab tab_view_edit;

    @FXML
    private TextField txtfield_employee_id;

    @FXML
    private TextField txtfield_mex_code;

    @FXML
    private TextField txtfield_given_name;

    @FXML
    private TextField txtfield_visa;

    @FXML
    private TextField txtfield_medical_ins;

    @FXML
    private TextField txtfield_bank_id;

    @FXML
    private TextField txtfield_surname;

    @FXML
    private TextField txtfield_passport_num;

    @FXML
    private TextField txtfield_health_card;

    @FXML
    private DatePicker datepicker_dob;

    @FXML
    private DatePicker datepicker_arrival_date;

    @FXML
    private DatePicker datepicker_departure_date;

    @FXML
    private DatePicker datepicker_hire_date;

    @FXML
    private DatePicker datepicker_passport_expiration;

    @FXML
    private TextField txtfield_nickname;

    @FXML
    private TextField txtfield_ssn;

    @FXML
    private TextField txtfield_bank_account;

    @FXML
    private TextField txtfield_rate;

    @FXML
    private TextField txtfield_email;

    @FXML
    private TextField txtfield_email_password;

    @FXML
    private TextField txtfield_quarantine_loc;

    @FXML
    private TextField txtfield_house_number;

    @FXML
    private TextField txtfield_house_name;

    @FXML
    private TextField txtfield_house_address;

    @FXML
    private TextField txtfield_corp_cell_type;

    @FXML
    private TextField txtfield_corp_cell_number;

    @FXML
    private DatePicker datepicker_dose_1;

    @FXML
    private DatePicker datepicker_dose2;

    @FXML
    private TextField txtfield_bed_id;

    @FXML
    private TextArea txtarea_notes;

    @FXML
    private CheckBox checkbox_active;

    @FXML
    private TextField txtfield_mobile;

    @FXML
    private Tab tab_mexico;

    @FXML
    private TextField txtfield_mex_contact_name;

    @FXML
    private TextField txtfield_mex_phone;

    @FXML
    private TextField txtfield_mex_address;

    @FXML
    private TextField txtfield_mex_municipality;

    @FXML
    private TextField txtfield_mex_neighborhood;

    @FXML
    private TextField txtfield_mex_state;

    @FXML
    private TextField txtfield_mex_postal_code;


    @FXML
    private Tab tab_attachments;

    @FXML
    private Button btn_attach_work_permit;

    @FXML
    private Button btn_view_work_permit;

    @FXML
    private Button btn_attach_benefit_card;

    @FXML
    private Button btn_view_benefit_card;

    @FXML
    private Button btn_attach_contract;

    @FXML
    private Button btn_view_contract;

    @FXML
    private Button btn_attach_withhold_agreement;

    @FXML
    private Button btn_view_withhold_agreement;

    @FXML
    private Button btn_attach_vaccine_1;

    @FXML
    private Button btn_view_vaccine_1;

    @FXML
    private Button btn_attach_vaccine_2;

    @FXML
    private Button btn_view_dose_2;

    @FXML
    private TableView<Attachments> tblview_attachments;

    @FXML
    private TableColumn<?, ?> col_attachments;

    @FXML
    private TableColumn<?, ?> col_file_name;

    @FXML
    private Tab tab_table_search;

    @FXML
    private TableView<Large_Employee> tblview_employee_list;

    @FXML
    private TableColumn<Large_Employee, Integer> col_employee_id;

    @FXML
    private TableColumn<?, ?> col_mex_code;

    @FXML
    private TableColumn<?, ?> col_given_name;

    @FXML
    private TableColumn<?, ?> col_surname;

    @FXML
    private TableColumn<?, ?> col_job_col;

    @FXML
    private Button btn_select_employee;

    @FXML
    Label lbl_file_selected;

    @FXML
    Label lbl_search_error;

    @FXML
    private Label lbl_dob_incorrect;


    FileChooser fileChooser = new FileChooser();

    String search_criteria = "";

    @FXML
    void attach_benefit_card(MouseEvent event) throws IOException, InvalidFormatException {
        Access_Full_Employee_DB db = new Access_Full_Employee_DB();
        fileChooser.setTitle("Attach Benefit Card");
        Stage file_stage = Main.createFileChooserStage(event, "Employee_List_Screen.fxml");
        String network_location = "\\\\Inddc-vm56\\mssqlserver\\FileDBStreamDirectory\\FileDBFileStreamDirectory\\Employee_Attachments\\BenefitCard_";
        String employee_id = Integer.toString(Main.current_large_employee.employeeID);

        File file = fileChooser.showOpenDialog(file_stage);
        XWPFDocument document = new XWPFDocument(OPCPackage.open((file)));
        document.write(new FileOutputStream(network_location+employee_id+".docx"));
        String insert_name = "BenefitCard_" + employee_id+".docx";

        document.close();

        try{
        String sql_update ="UPDATE ATTACHMENTS SET BENEFIT_CARD = '"
                + insert_name
                + "' WHERE EMPLOYEE_ID = '"
                + Main.current_large_employee.employeeID+ "'";
        db.update_table(sql_update);
        Main.current_large_employee.setBenefitCard(insert_name);
            lbl_file_selected.setText("");
        }catch(NullPointerException np){
            lbl_file_selected.setText("No file was selected for 'Benefit Card'");
        }
        populate_attachments_table(Main.current_large_employee);
    }

    @FXML
    void attach_employment_contract(MouseEvent event) throws IOException, InvalidFormatException {
        Access_Full_Employee_DB db = new Access_Full_Employee_DB();
        fileChooser.setTitle("Attach Employment Agreement");
        Stage file_stage = Main.createFileChooserStage(event, "Employee_List_Screen.fxml");
        String network_location = "\\\\Inddc-vm56\\mssqlserver\\FileDBStreamDirectory\\FileDBFileStreamDirectory\\Employee_Attachments\\Employment_Contract_";
        String employee_id = Integer.toString(Main.current_large_employee.employeeID);

        File file = fileChooser.showOpenDialog(file_stage);
        XWPFDocument document = new XWPFDocument(OPCPackage.open((file)));
        document.write(new FileOutputStream(network_location + employee_id+".docx"));
        String insert_name = "Employment_Contract_" + employee_id + ".docx";
        document.close();

        try {
            String sql_update = "UPDATE ATTACHMENTS SET EMPLOYMENT_CONTRACT = '"
                    + insert_name
                    + "' WHERE EMPLOYEE_ID = '"
                    + Main.current_large_employee.employeeID + "'";
            db.update_table(sql_update);
            Main.current_large_employee.setEmploymentAgreement(insert_name);
            lbl_file_selected.setText("");
        } catch (NullPointerException np) {
            lbl_file_selected.setText("No file was selected for 'Employment Contract'");
        }
        populate_attachments_table(Main.current_large_employee);
    }

    @FXML
    void attach_vaccine_dose_1(MouseEvent event) throws IOException, InvalidFormatException {
        Access_Full_Employee_DB db = new Access_Full_Employee_DB();
        fileChooser.setTitle("Attach Vaccine Dose #1");
        Stage file_stage = Main.createFileChooserStage(event, "Employee_List_Screen.fxml");
        String network_location = "\\\\Inddc-vm56\\mssqlserver\\FileDBStreamDirectory\\FileDBFileStreamDirectory\\Employee_Attachments\\Vaccine_Dose1_";
        String employee_id = Integer.toString(Main.current_large_employee.employeeID);

        File file = fileChooser.showOpenDialog(file_stage);
        XWPFDocument document = new XWPFDocument(OPCPackage.open((file)));
        document.write(new FileOutputStream(network_location + employee_id+".docx"));
        String insert_name = "Vaccine_Dose1_" + employee_id + ".docx";
        document.close();

        try {
            String sql_update = "UPDATE COVID_INFO SET DOSE1_ATTACHMENT = '"
                    + insert_name
                    + "' WHERE EMPLOYEE_ID = '"
                    + Main.current_large_employee.employeeID + "'";
            db.update_table(sql_update);
            Main.current_large_employee.setCovidDose1Attach(insert_name);
            lbl_file_selected.setText("");
        }catch(NullPointerException np){
            lbl_file_selected.setText("No file was selected for 'Vaccine Dose #1'");
        }
        populate_attachments_table(Main.current_large_employee);

    }

    @FXML
    void attach_vaccine_dose_2(MouseEvent event) throws InvalidFormatException, IOException {
        Access_Full_Employee_DB db = new Access_Full_Employee_DB();
        fileChooser.setTitle("Attach Vaccine Dose #2");
        Stage file_stage = Main.createFileChooserStage(event, "Employee_List_Screen.fxml");
        String network_location = "\\\\Inddc-vm56\\mssqlserver\\FileDBStreamDirectory\\FileDBFileStreamDirectory\\Employee_Attachments\\Vaccine_Dose2_";
        String employee_id = Integer.toString(Main.current_large_employee.employeeID);

        File file = fileChooser.showOpenDialog(file_stage);
        XWPFDocument document = new XWPFDocument(OPCPackage.open((file)));
        document.write(new FileOutputStream(network_location + employee_id+".docx"));
        String insert_name = "Vaccine_Dose2_" + employee_id + ".docx";
        document.close();

        try {
            String sql_update = "UPDATE COVID_INFO SET DOSE2_ATTACHMENT = '"
                    + insert_name
                    + "' WHERE EMPLOYEE_ID = '"
                    + Main.current_large_employee.employeeID + "'";
            db.update_table(sql_update);
            Main.current_large_employee.setCovidDose2Attach(insert_name);
            lbl_file_selected.setText("");
        }catch(NullPointerException np){
            lbl_file_selected.setText("No file was selected for 'Vaccine Dose #2");
        }
        populate_attachments_table(Main.current_large_employee);
    }

    @FXML
    void attach_withhold_agreement(MouseEvent event) throws IOException, InvalidFormatException {
        Access_Full_Employee_DB db = new Access_Full_Employee_DB();
        fileChooser.setTitle("Attach Withhold Agreement");
        Stage file_stage = Main.createFileChooserStage(event, "Employee_List_Screen.fxml");
        String network_location = "\\\\Inddc-vm56\\mssqlserver\\FileDBStreamDirectory\\FileDBFileStreamDirectory\\Employee_Attachments\\Withhold_Agreement_";
        String employee_id = Integer.toString(Main.current_large_employee.employeeID);

        File file = fileChooser.showOpenDialog(file_stage);
        XWPFDocument document = new XWPFDocument(OPCPackage.open((file)));
        document.write(new FileOutputStream(network_location + employee_id+".docx"));
        String insert_name = "Withhold_Agreement_" + employee_id + ".docx";
        document.close();

        try {
            String sql_update = "UPDATE ATTACHMENTS SET WITHHOLD_AGREEMENT = '"
                    + insert_name
                    + "' WHERE EMPLOYEE_ID = '"
                    + Main.current_large_employee.employeeID + "'";
            db.update_table(sql_update);
            Main.current_large_employee.setWithholdAgreement(insert_name);
            lbl_file_selected.setText("");
        }catch(NullPointerException np){
            lbl_file_selected.setText("No file was selected for 'Withhold Agreement'");
        }
        populate_attachments_table(Main.current_large_employee);
    }

    @FXML
    void attach_work_permit(MouseEvent event) throws InvalidFormatException, IOException {
        Access_Full_Employee_DB db = new Access_Full_Employee_DB();
        fileChooser.setTitle("Attach Work permit");
        Stage file_stage = Main.createFileChooserStage(event, "Employee_List_Screen.fxml");
        String network_location = "\\\\Inddc-vm56\\mssqlserver\\FileDBStreamDirectory\\FileDBFileStreamDirectory\\Employee_Attachments\\Work_Permit_";
        String employee_id = Integer.toString(Main.current_large_employee.employeeID);

        File file = fileChooser.showOpenDialog(file_stage);
        XWPFDocument document = new XWPFDocument(OPCPackage.open((file)));
        document.write(new FileOutputStream(network_location + employee_id+".docx"));
        String insert_name = "Work_Permit_" + employee_id + ".docx";
        document.close();

        try {
            String sql_update = "UPDATE ATTACHMENTS SET WORK_PERMIT = '"
                    + insert_name
                    + "' WHERE EMPLOYEE_ID = '"
                    + Main.current_large_employee.employeeID + "'";
            db.update_table(sql_update);
            Main.current_large_employee.setWorkPermit(insert_name);
            lbl_file_selected.setText("");
        }catch(NullPointerException np){
            lbl_file_selected.setText("No file was selected for 'Work Permit'");
        }
        populate_attachments_table(Main.current_large_employee);
    }

    @FXML
    void go_home(MouseEvent event) { Main.createNewScene(event, "Employee_Dashboard.fxml"); }

    @FXML
    void quit_program(MouseEvent event) {
        Main.exit_program(event);
    }

    @FXML
    void reset_list(MouseEvent event) { tblview_employee_list.getItems().clear(); }

    @FXML
    void search_list(MouseEvent event) {
        tblview_attachments.getItems().removeAll();
        Access_Full_Employee_DB db = new Access_Full_Employee_DB();
        search_criteria = txtfield_search_criteria.getText();
        if(search_criteria.equals("")){
            lbl_search_error.setText("PLEASE ENTER A SEARCH CRITERIA");
            tblview_attachments.getItems().clear();
        }
        else {
            int employeeID = 1;
            tblview_employee_list.getItems().clear();
            if (choicebox_search_type.getValue().equals("Employee Name")) {
                tblview_employee_list.getItems().addAll(db.search_for_employee_by_name(search_criteria.toUpperCase()));
            } else if (choicebox_search_type.getValue().equals("Scotlynn ID")) {
                try {
                    int id = Integer.parseInt(search_criteria);
                    tblview_employee_list.getItems().addAll(db.search_for_employee_by_scotlynnID(id));
                } catch (NumberFormatException nfe) {
                    System.out.println("Enter a correct ID number");
                }
            } else if (choicebox_search_type.getValue().equals("Job Code")) {
                ArrayList<Large_Employee> employees_by_jobs_list = db.search_for_employee_by_job_name(search_criteria.toUpperCase());
                tblview_employee_list.getItems().addAll((employees_by_jobs_list));
            } else {
                tblview_employee_list.getItems().addAll(db.search_for_employee_by_mex_code(search_criteria.toUpperCase()));
            }
            txtfield_search_criteria.clear();
            set_fields_to_first_result();
        }


    }

    void set_fields_to_first_result(){
        tblview_attachments.getItems().removeAll();
        Access_Full_Employee_DB db = new Access_Full_Employee_DB();
        tblview_employee_list.getSelectionModel().selectFirst();
        lbl_file_selected.setText("");
        Main.current_large_employee = tblview_employee_list.getSelectionModel().getSelectedItem();
        Large_Employee selected_employee = tblview_employee_list.getSelectionModel().getSelectedItem();
        try {
            populate_textfields(selected_employee);
            populate_attachments_table(selected_employee);
            lbl_search_error.setText("");
        }catch(NullPointerException np){
            lbl_search_error.setText("PLEASE ENTER A SEARCH CRITERIA");
            tblview_attachments.getItems().removeAll();
        }
    }

    @FXML
    void select_employee_from_table(MouseEvent event) {
        tblview_attachments.getItems().removeAll();
        Access_Full_Employee_DB db = new Access_Full_Employee_DB();
        lbl_file_selected.setText("");
        tblview_employee_list.getSelectionModel().setSelectionMode(
                SelectionMode.MULTIPLE);
        int empID = tblview_employee_list.getSelectionModel().getSelectedItem().employeeID;
        Large_Employee selected_employee = db.get_employee_info_by_id_using_view(empID);
        Main.current_large_employee = selected_employee;

        try {
            populate_textfields(selected_employee);
            populate_attachments_table(selected_employee);
            lbl_search_error.setText("");
        }catch(NullPointerException np){
            lbl_search_error.setText("PLEASE ENTER A SEARCH CRITERIA");
        }
    }

    void populate_textfields(Large_Employee selected_employee){
        tblview_attachments.getItems().clear();

        int current_employee_id = selected_employee.employeeID;
        DateTimeFormatter date_formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        txtfield_employee_id.setText(Integer.toString(selected_employee.employeeID));
        txtfield_mex_code.setText(selected_employee.mex_code);
        txtfield_given_name.setText(selected_employee.givenName);
        LocalDate dob_date, arrival_date, departure_date, dose1_date, dose2_date, passport_exp, hire_date = null;

        //Parsing DOB to the datepicker to be displayed
        String dateOfBirth = selected_employee.dateOfBirth;
        if(dateOfBirth.length() > 3) {
            try {
                dob_date = LocalDate.parse(dateOfBirth, date_formatter);
                datepicker_dob.setValue(dob_date);
            } catch (NullPointerException np) {
                System.out.println("No date of birth for employee found");
            }
        }else{System.out.println("There is no date of birth");}

        //Parsing Passport Expiration Date to the datepicker to be displayed
        String passportExp = selected_employee.passportExpiration;
        if(passportExp.length() >3) {
        try {
            passport_exp = LocalDate.parse(passportExp, date_formatter);
            datepicker_passport_expiration.setValue(passport_exp);
        }catch(NullPointerException np){
            System.out.println("No passport expiration for employee found");
        }
        }else{System.out.println("There is no passport expiration");}


        // Parsing Arrival Time to the datepicker to be displayed.
        String arrivalDate = selected_employee.arrivalDate;
        if(arrivalDate.length() > 3) {
            try {
                arrival_date = LocalDate.parse(arrivalDate, date_formatter);
                datepicker_arrival_date.setValue(arrival_date);

            } catch (NullPointerException npe) {
                System.out.println("No Arrival Date for that employee");
            }
        }else{System.out.println("There is no arrival date");}

        //Parsing Hire Date to the datepicker to be displayed
        String hireDate = selected_employee.hireDate;
        if(hireDate.length() > 3) {
            try {
                hire_date = LocalDate.parse(hireDate, date_formatter);
                datepicker_hire_date.setValue(hire_date);
            } catch (NullPointerException np) {
                System.out.println("No hire date for employee found");
            }
        }else{
            System.out.println("There is no hire date");
        }


        //Parsing covid dose 1 date to be displayed
        String covidDose1 =selected_employee.covidDoseDate1;
        if(covidDose1.length() > 3){
        try {
            dose1_date = LocalDate.parse(covidDose1, date_formatter);
            datepicker_dose_1.setValue(dose1_date);
        }catch(NullPointerException np){
            System.out.println("No vaccine dose 1 date for employee found");
        }}else{System.out.println("There is no covid dose 1");}

        //Parsing covid dose 1 date to be displayed
        String covidDose2 = selected_employee.covidDoseDate2;
        if(covidDose2.length() >  3) {
            try {
                dose2_date = LocalDate.parse(covidDose2, date_formatter);
                datepicker_dose2.setValue(dose2_date);
            } catch (NullPointerException np) {
                System.out.println("No vaccine dose 2 date for employee found");
            }
        }else{System.out.println("There is no vaccine 2 dose");}


        txtfield_medical_ins.setText(selected_employee.medInsurance);
        txtfield_ssn.setText(selected_employee.SII);
        txtfield_email.setText(selected_employee.txtEmail);
        txtfield_bank_id.setText(selected_employee.bankID);
        txtfield_rate.setText(selected_employee.pay_rate);
        txtfield_surname.setText(selected_employee.surName);
        txtfield_visa.setText(selected_employee.visaNumber);
        txtfield_health_card.setText(selected_employee.healthCardNum);
        txtfield_nickname.setText(selected_employee.nickName);
        txtfield_email_password.setText(selected_employee.emailPassword);
        txtfield_bank_account.setText(selected_employee.bankAccountNum);
        if(selected_employee.still_active.equals("TRUE")) {
            checkbox_active.setSelected(true);
        }
        else{
            checkbox_active.setSelected(false);
        }
        txtfield_mobile.setText(selected_employee.txtMobile);
        txtfield_quarantine_loc.setText(selected_employee.quarantineLocation);
        txtfield_house_number.setText(selected_employee.houseNum);
        txtfield_house_name.setText(selected_employee.houseName);
        txtfield_house_address.setText(selected_employee.houseAddress);
        txtfield_bed_id.setText(selected_employee.bedID);
        txtfield_corp_cell_type.setText(selected_employee.corpPhoneType);
        txtfield_corp_cell_number.setText(selected_employee.corpPhoneNumber);
        txtarea_notes.clear();
        txtarea_notes.appendText(selected_employee.txtNotes);
        txtfield_mex_contact_name.setText(selected_employee.mexContactName);
        txtfield_mex_phone.setText(selected_employee.mexPhone);
        txtfield_mex_address.setText(selected_employee.mexStreetAddress);
        txtfield_mex_municipality.setText(selected_employee.mexMunicipality);
        txtfield_mex_neighborhood.setText(selected_employee.mexNeighbourhood);
        txtfield_mex_state.setText(selected_employee.mexState);
        txtfield_mex_postal_code.setText(selected_employee.mexPostalCode);
    }

    @FXML
    void view_benefit_card(MouseEvent event) {
        String employee_id = Integer.toString(Main.current_large_employee.employeeID);
        String location_url = "\\\\Inddc-vm56\\mssqlserver\\FileDBStreamDirectory\\FileDBFileStreamDirectory\\Employee_Attachments\\BenefitCard_"+employee_id+".docx";
        File file = new File(location_url);
        Desktop desktop = Desktop.getDesktop();
        try{
            if(file.exists()){
                lbl_file_selected.setText("");
                desktop.open(file);
            }
            else{
                lbl_file_selected.setText("There is no file for this attachemnt");
            }
        }catch(Exception e){
            System.out.println("There is not file for this attachment");
        }

    }

    @FXML
    void view_employment_contract(MouseEvent event) {
        String employee_id = Integer.toString(Main.current_large_employee.employeeID);
        String location_url = "\\\\Inddc-vm56\\mssqlserver\\FileDBStreamDirectory\\FileDBFileStreamDirectory\\Employee_Attachments\\Employment_Contract_"+employee_id+".docx";
        File file = new File(location_url);
        Desktop desktop = Desktop.getDesktop();
        try{
            if(file.exists()){
                lbl_file_selected.setText("");
                desktop.open(file);
            }
            else{
                lbl_file_selected.setText("There is no file for this attachemnt");
            }
        }catch(Exception e){
            System.out.println("There is not file for this attachment");
        }

    }

    @FXML
    void view_vaccine_dose_1(MouseEvent event) {
        String employee_id = Integer.toString(Main.current_large_employee.employeeID);
        String location_url = "\\\\Inddc-vm56\\mssqlserver\\FileDBStreamDirectory\\FileDBFileStreamDirectory\\Employee_Attachments\\Vaccine_Dose1_"+employee_id+".docx";
        File file = new File(location_url);
        Desktop desktop = Desktop.getDesktop();
        try{
            if(file.exists()){
                lbl_file_selected.setText("");
                desktop.open(file);
            }
            else{
                lbl_file_selected.setText("There is no file for this attachemnt");
            }
        }catch(Exception e){
            System.out.println("There is not file for this attachment");
        }

    }

    @FXML
    void view_vaccine_dose_2(MouseEvent event) {
        String employee_id = Integer.toString(Main.current_large_employee.employeeID);
        String location_url = "\\\\Inddc-vm56\\mssqlserver\\FileDBStreamDirectory\\FileDBFileStreamDirectory\\Employee_Attachments\\Vaccine_Dose2_"+employee_id+".docx";
        File file = new File(location_url);

        Desktop desktop = Desktop.getDesktop();
        try{
            if(file.exists()){
                lbl_file_selected.setText("");
                desktop.open(file);
            }
            else{
                lbl_file_selected.setText("There is no file for this attachemnt");
            }
        }catch(Exception e){
            System.out.println("There is not file for this attachment");
        }

    }

    @FXML
    void view_withhold_agreement(MouseEvent event) {
        Access_Full_Employee_DB db = new Access_Full_Employee_DB();
        String employee_id = Integer.toString(Main.current_large_employee.employeeID);
        String location_url = "\\\\Inddc-vm56\\mssqlserver\\FileDBStreamDirectory\\FileDBFileStreamDirectory\\Employee_Attachments\\Withhold_Agreement_"+employee_id+".docx";
        File file = new File(location_url);
        Desktop desktop = Desktop.getDesktop();
        try{
            if(file.exists()){
                lbl_file_selected.setText("");
                desktop.open(file);
            }
            else{
                lbl_file_selected.setText("There is no file for this attachemnt");
            }
        }catch(Exception e){
            System.out.println("There is not file for this attachment");
        }

    }

    @FXML
    void view_work_permit(MouseEvent event) {
        Access_Full_Employee_DB db = new Access_Full_Employee_DB();
        String employee_id = Integer.toString(Main.current_large_employee.employeeID);
        String location_url = "\\\\Inddc-vm56\\mssqlserver\\FileDBStreamDirectory\\FileDBFileStreamDirectory\\Employee_Attachments\\Work_Permit_"+employee_id+".docx";
        File file = new File(location_url);
        Desktop desktop = Desktop.getDesktop();
        try{
            if(file.exists()){
                lbl_file_selected.setText("");
                desktop.open(file);
            }
            else{
                lbl_file_selected.setText("There is no file for this attachemnt");
            }
        }catch(Exception e){
            System.out.println("There is not file for this attachment");
        }

    }

    void populate_search_choicebox(){
        // Populates the choice box in Employee Info tab
        choicebox_search_type.getItems().add("Scotlynn ID");
        choicebox_search_type.getItems().add("Employee Name");
        choicebox_search_type.getItems().add("Mexican Employment Code");
        choicebox_search_type.getItems().add("Job Code");
        choicebox_search_type.setValue("Scotlynn ID");
    }

    void populate_search_table(){
        Access_Full_Employee_DB db = new Access_Full_Employee_DB();
        col_employee_id.setCellValueFactory(new PropertyValueFactory<>("employeeID"));
        col_mex_code.setCellValueFactory(new PropertyValueFactory<>("mex_code"));
        col_given_name.setCellValueFactory(new PropertyValueFactory<>("givenName"));
        col_surname.setCellValueFactory(new PropertyValueFactory<>("surName"));
        col_job_col.setCellValueFactory(new PropertyValueFactory<>("jobName"));
        tblview_employee_list.getItems().addAll(db.get_tblview_employee_search_list());
        tblview_employee_list.getSortOrder().add(col_employee_id);

    }

    void populate_attachments_table(Large_Employee current_employee){
        Access_Full_Employee_DB db = new Access_Full_Employee_DB();
        tblview_attachments.getItems().clear();
        col_attachments.setCellValueFactory(new PropertyValueFactory<>("attachmentName"));
        col_file_name.setCellValueFactory(new PropertyValueFactory<>("fileName"));
        ArrayList<Attachments> attachments_list = new ArrayList();
        try {
                    attachments_list.add(new Attachments("Work Permit", Main.current_large_employee.workPermit));
                    attachments_list.add(new Attachments("Employment Contract", Main.current_large_employee.employmentAgreement));
                    attachments_list.add(new Attachments("Benefit Card", Main.current_large_employee.benefitCard));
                    attachments_list.add(new Attachments("Withhold Agreement", Main.current_large_employee.withholdAgreement));
                    attachments_list.add(new Attachments("Vaccine Dose 1", Main.current_large_employee.covidDose1Attach));
                    attachments_list.add(new Attachments("Vaccine Dose 2", Main.current_large_employee.covidDose2Attach));
            tblview_attachments.getItems().addAll(attachments_list);
            }

        catch(NullPointerException np){
            lbl_file_selected.setText("Please select an employee for attachment");
            }

    }

    @FXML
    void update_employee_info(MouseEvent event){
        Access_Full_Employee_DB db = new Access_Full_Employee_DB();
        Large_Employee selected_employee = Main.current_large_employee;
        int current_employee_id = selected_employee.employeeID;
        String passportExp="";
        String arrivalDate="";
        String depatureDate="";
        String hireDate="";
        String mexCode = txtfield_mex_code.getText();
        String givenName = txtfield_given_name.getText();
        String dateOfBirth ="";
        String passportNum = txtfield_passport_num.getText();


        if(datepicker_dob.getValue().isAfter(LocalDate.now())){
            lbl_dob_incorrect.setText("Invalid Date");
        }
        else{
            lbl_dob_incorrect.setText("");
            dateOfBirth = datepicker_dob.getValue().toString();
        }


        try{
         passportExp= datepicker_passport_expiration.getValue().toString();
        }catch(NullPointerException npe){
            System.out.println("No value for passport exp");
        }
        try{
         arrivalDate= datepicker_arrival_date.getValue().toString();
        }catch(NullPointerException npe){
            System.out.println("No value for arrival date");
        }
        try{
         depatureDate= datepicker_departure_date.getValue().toString();
        }catch(NullPointerException npe){
            System.out.println("No value for departure date");
        }
        String medicalIns = txtfield_medical_ins.getText();
        String sii = txtfield_ssn.getText();
        String txtEmail = txtfield_email.getText();
        String bankID = txtfield_bank_id.getText();
        try{
         hireDate= datepicker_hire_date.getValue().toString();
        }catch(NullPointerException npe){
            System.out.println("No value for hire date");
        }
        String payRate = txtfield_rate.getText();
        String surname = txtfield_surname.getText();
        String visaNum = txtfield_visa.getText();
        String healthCard = txtfield_health_card.getText();
        String nickName = txtfield_nickname.getText();
        String emailPassword = txtfield_email_password.getText();
        String bankAccount = txtfield_bank_account.getText();

        String stillActive="";
        if(checkbox_active.equals(true)){
            stillActive = "TRUE";
        }
        else{
            stillActive = "FALSE";
        }

        String txtMobile = txtfield_mobile.getText();
        String quarantineLocation = txtfield_quarantine_loc.getText();
        String houseNum = txtfield_house_number.getText();
        String houseName = txtfield_house_name.getText();
        String houseAddress = txtfield_house_address.getText();
        String bedID = txtfield_bed_id.getText();
        String corpCellType = txtfield_corp_cell_type.getText();
        String corpCellNum = txtfield_corp_cell_number.getText();
        String txtNotes = txtarea_notes.getText();
        String covid1Record="";
        try {
             covid1Record= datepicker_dose_1.getValue().toString();
        }catch(NullPointerException npe){
            System.out.println("No value for covid 1 record");
        }
        String covid2Record="";
        try{
         covid2Record = datepicker_dose2.getValue().toString();
        }catch(NullPointerException npe){
            System.out.println("No value for covid 2 record");
        }


        // Update statements
        String employee_info_update ="UPDATE EMPLOYEE_INFO SET MEX_EMPLOYMENT_CODE = '"
                + mexCode
                + "' ,GIVEN_NAME = '"
                + givenName
                + "' ,SURNAME = '"
                + surname
                + "' ,DOB = '"
                + dateOfBirth
                + "' ,PASSPORT_NUMBER = '"
                + passportNum
                + "' ,PASSPORT_EXP = '"
                + passportExp
                + "' ,SSN = '"
                + sii
                + "' ,VISA_NUMBER = '"
                + visaNum
                + "' ,NICKNAME = '"
                + nickName
                + "' ,NOTES = '"
                + txtNotes
                + "'WHERE EMPLOYEE_ID = '"
                + Main.current_large_employee.employeeID+ "'";
        db.update_table(employee_info_update);

        String bank_info_update ="UPDATE BANK_INFO SET BANK_TRANSIT_NUMBER = '"
                + bankID
                + "' ,BANK_ACCOUNT_NUMBER = '"
                + bankAccount
                + "'WHERE EMPLOYEE_ID = '"
                + Main.current_large_employee.employeeID+ "'";
        db.update_table(bank_info_update);

        String contact_info_update ="UPDATE CONTACT_INFO SET MOBILE_PHONE = '"
                + txtMobile
                + "' ,EMAIL = '"
                + txtEmail
                + "' ,DEFAULT_EMAIL_PASSWORD = '"
                + emailPassword
                + "' ,CORPORATE_CELL_TYPE = '"
                + corpCellType
                + "' ,CORPORATE_CELL_NUMBER = '"
                + corpCellNum
                + "'WHERE EMPLOYEE_ID = '"
                + Main.current_large_employee.employeeID+ "'";
        db.update_table(contact_info_update);

        String covid_info_update ="UPDATE COVID_INFO SET QUARANTINE_LOCATION = '"
                + quarantineLocation
                + "' ,DOSE1_RECORD_DATE = '"
                +   covid1Record
                + "' ,DOSE2_RECORD_DATE = '"
                +   covid2Record
                + "'WHERE EMPLOYEE_ID = '"
                + Main.current_large_employee.employeeID+ "'";
        db.update_table(bank_info_update);

        String employment_info_update ="UPDATE EMPLOYMENT_INFO SET HIRE_DATE = '"
                + hireDate
                + "' ,ARRIVALE_DATE = '"
                + arrivalDate
                + "' ,DEPARTURE_DATE = '"
                + depatureDate
                + "' ,RATE = '"
                + payRate
                + "' ,ACTIVE = '"
                + stillActive
                + "'WHERE EMPLOYEE_ID = '"
                + Main.current_large_employee.employeeID+ "'";
        db.update_table(contact_info_update);

        String health_info_update ="UPDATE HEALTH_INFORMATION SET HEALTH_CARD_NUMBER = '"
                + healthCard
                + "' ,MED_INSURANCE = '"
                + medicalIns
                + "'WHERE EMPLOYEE_ID = '"
                + Main.current_large_employee.employeeID+ "'";
        db.update_table(bank_info_update);

        String housing_info_update ="UPDATE HOUSING_INFO SET HOUSE_NUM = '"
                + houseNum
                + "' ,HOUSE_NAME = '"
                + houseName
                + "' ,HOUSE_ADDRESS = '"
                + houseAddress
                + "' ,BED_ID = '"
                + bedID
                + "'WHERE EMPLOYEE_ID = '"
                + Main.current_large_employee.employeeID+ "'";
        db.update_table(contact_info_update);


    }

    @FXML
    void update_mex_contact_info(MouseEvent event){
        Access_Full_Employee_DB db = new Access_Full_Employee_DB();
        String mexContactName = txtfield_mex_contact_name.getText();
        String mexPhone = txtfield_mex_phone.getText();
        String mexAddres = txtfield_mex_address.getText();
        String mexMunicipality = txtfield_mex_municipality.getText();
        String mexNeighbourhood = txtfield_mex_neighborhood.getText();
        String mexState = txtfield_mex_state.getText();
        String mexPostalCode = txtfield_mex_postal_code.getText();


        String sql_update ="UPDATE MEX_CONTACT_INFO SET MEX_CONTACT_NAME = '"
                + mexContactName
                + "' ,MEX_MUNICIPALITY = '"
                + mexMunicipality
                + "' ,MEX_PHONE = '"
                + mexPhone
                + "' ,MEX_NEIGHBORHOOD = '"
                + mexNeighbourhood
                + "' ,MEX_POSTAL_CODE = '"
                + mexPostalCode
                + "' ,MEX_STREET_ADDRESS = '"
                + mexAddres
                + "' ,MEX_STATE = '"
                + mexState
                + "'WHERE EMPLOYEE_ID = '"
                + Main.current_large_employee.employeeID+ "'";
        db.update_table(sql_update);
    }


    @FXML
    void search_list_ENTER(KeyEvent event) {
        if(event.getCode() == KeyCode.ENTER){
            tblview_attachments.getItems().removeAll();
            Access_Full_Employee_DB db = new Access_Full_Employee_DB();
            String search_criteria = "";
            search_criteria = txtfield_search_criteria.getText();
            if(search_criteria.equals("")){
                lbl_search_error.setText("PLEASE ENTER A SEARCH CRITERIA");
                tblview_attachments.getItems().removeAll();
            }
            else {
                int employeeID = 1;
                tblview_employee_list.getItems().clear();
                if (choicebox_search_type.getValue().equals("Employee Name")) {
                    tblview_employee_list.getItems().addAll(db.search_for_employee_by_name(search_criteria.toUpperCase()));
                } else if (choicebox_search_type.getValue().equals("Scotlynn ID")) {
                    try {
                        int id = Integer.parseInt(search_criteria);
                        tblview_employee_list.getItems().addAll(db.search_for_employee_by_scotlynnID(id));
                    } catch (NumberFormatException nfe) {
                        System.out.println("Enter a correct ID number");
                    }
                } else if (choicebox_search_type.getValue().equals("Job Code")) {
                    ArrayList<Large_Employee> employees_by_jobs_list = db.search_for_employee_by_job_name(search_criteria.toUpperCase());
                    tblview_employee_list.getItems().addAll((employees_by_jobs_list));
                } else {
                    tblview_employee_list.getItems().addAll(db.search_for_employee_by_mex_code(search_criteria.toUpperCase()));
                }
                txtfield_search_criteria.clear();
                set_fields_to_first_result();
            }
        }

    }

    @FXML
    void initialize(){

        if(Main.current_large_employee != null){
            populate_textfields(Main.current_large_employee);
            populate_attachments_table(Main.current_large_employee);
        }else{
            populate_search_table();
        }


        populate_search_choicebox();
    }

}

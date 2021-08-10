//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.example.offshore_employees_v3;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class Create_Employee_Controller {
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
    private Label lbl_dob_incorrect;
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
    private Button btn_create_employee;
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
    private Button btn_update_mexico_info;
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
    private Label lbl_file_selected;
    @FXML
    private Button btn_home;
    @FXML
    private Button btn_quit;
    @FXML
    private TextArea txtarea_new_employee_info;

    @FXML Label lbl_employee_created;


    FileChooser fileChooser = new FileChooser();

    String search_criteria = "";
    int current_employee_id = 0;

    public Create_Employee_Controller() {
    }

    @FXML
    void initialize() {
        Access_Full_Employee_DB db = new Access_Full_Employee_DB();
        this.current_employee_id = db.get_largest_employee_id();
        this.txtfield_employee_id.setText(Integer.toString(this.current_employee_id));
        lbl_employee_created.setVisible(false);
    }

    @FXML
    void attach_benefit_card(MouseEvent event) throws InvalidFormatException, IOException {
        Access_Full_Employee_DB db = new Access_Full_Employee_DB();
        fileChooser.setTitle("Attach Benefit Card");
        Stage file_stage = Main.createFileChooserStage(event, "Create_Employee_Screen.fxml");
        File file = fileChooser.showOpenDialog(file_stage);
        String network_location = "\\\\Inddc-vm56\\mssqlserver\\FileDBStreamDirectory\\FileDBFileStreamDirectory\\Employee_Attachments\\BenefitCard_";
        String employee_id = Integer.toString(Main.current_large_employee.employeeID);
        XWPFDocument document = new XWPFDocument(OPCPackage.open((file)));
        document.write(new FileOutputStream(network_location+employee_id+".docx"));
        document.close();
        String insert_name = "BenefitCard_" + current_employee_id+".docx";
        try{
            String sql_update ="UPDATE ATTACHMENTS SET BENEFIT_CARD = '"
                    + insert_name
                    + "' WHERE EMPLOYEE_ID = '"
                    + current_employee_id+ "'";
            db.update_table(sql_update);
            Main.current_large_employee.setBenefitCard(insert_name);
            lbl_file_selected.setText("");
        }catch(NullPointerException np){
            lbl_file_selected.setText("No file was selected for 'Benefit Card'");
        }
        populate_attachments_table(Main.current_large_employee);
        this.txtarea_new_employee_info.clear();
        this.txtarea_new_employee_info.appendText(Main.current_large_employee.toString());
    }

    @FXML
    void attach_employment_contract(MouseEvent event) throws InvalidFormatException, IOException {
        Access_Full_Employee_DB db = new Access_Full_Employee_DB();
        fileChooser.setTitle("Attach Employment Agreement");
        Stage file_stage = Main.createFileChooserStage(event, "Create_Employee_Screen.fxml");
        File file = fileChooser.showOpenDialog(file_stage);
        String network_location = "\\\\Inddc-vm56\\mssqlserver\\FileDBStreamDirectory\\FileDBFileStreamDirectory\\Employee_Attachments\\Employment_Contract_";
        String employee_id = Integer.toString(Main.current_large_employee.employeeID);
        XWPFDocument document = new XWPFDocument(OPCPackage.open((file)));
        document.write(new FileOutputStream(network_location+employee_id+".docx"));
        document.close();
        String insert_name = "Employment_Agreement_" + current_employee_id+".docx";
        try{

            String sql_update ="UPDATE ATTACHMENTS SET EMPLOYMENT_CONTRACT = '"
                    + insert_name
                    + "' WHERE EMPLOYEE_ID = '"
                    + Main.current_large_employee.employeeID+ "'";
            db.update_table(sql_update);
            Main.current_large_employee.setEmploymentAgreement(insert_name);
            lbl_file_selected.setText("");
        }catch(NullPointerException np){
            lbl_file_selected.setText("No file was selected for 'Employment Contract'");
        }
        populate_attachments_table(Main.current_large_employee);
        this.txtarea_new_employee_info.clear();
        this.txtarea_new_employee_info.appendText(Main.current_large_employee.toString());
    }

    @FXML
    void attach_vaccine_dose_1(MouseEvent event) throws IOException, InvalidFormatException {
        Access_Full_Employee_DB db = new Access_Full_Employee_DB();
        fileChooser.setTitle("Attach Vaccine Dose #1");
        Stage file_stage = Main.createFileChooserStage(event, "Create_Employee_Screen.fxml");
        File file = fileChooser.showOpenDialog(file_stage);
        String network_location = "\\\\Inddc-vm56\\mssqlserver\\FileDBStreamDirectory\\FileDBFileStreamDirectory\\Employee_Attachments\\Covid_Dose1_";
        String employee_id = Integer.toString(Main.current_large_employee.employeeID);
        XWPFDocument document = new XWPFDocument(OPCPackage.open((file)));
        document.write(new FileOutputStream(network_location+employee_id+".docx"));
        document.close();
        String insert_name = "Covid_Dose1_" + current_employee_id+".docx";
        try{
            String sql_update ="UPDATE COVID_INFO SET DOSE1_ATTACHMENT = '"
                    + insert_name
                    + "' WHERE EMPLOYEE_ID = '"
                    + Main.current_large_employee.employeeID+ "'";
            db.update_table(sql_update);
            Main.current_large_employee.setCovidDose1Attach(insert_name);
            lbl_file_selected.setText("");
        }catch(NullPointerException np){
            lbl_file_selected.setText("No file was selected for 'Vaccine Dose #1'");
        }
        populate_attachments_table(Main.current_large_employee);
        this.txtarea_new_employee_info.clear();
        this.txtarea_new_employee_info.appendText(Main.current_large_employee.toString());

    }

    @FXML
    void attach_vaccine_dose_2(MouseEvent event) throws InvalidFormatException, IOException {
        Access_Full_Employee_DB db = new Access_Full_Employee_DB();
        fileChooser.setTitle("Attach Vaccine Dose #2");
        Stage file_stage = Main.createFileChooserStage(event, "Create_Employee_Screen.fxml");
        File file = fileChooser.showOpenDialog(file_stage);
        String network_location = "\\\\Inddc-vm56\\mssqlserver\\FileDBStreamDirectory\\FileDBFileStreamDirectory\\Employee_Attachments\\Covid_Dose2_";
        String employee_id = Integer.toString(Main.current_large_employee.employeeID);
        XWPFDocument document = new XWPFDocument(OPCPackage.open((file)));
        document.write(new FileOutputStream(network_location+employee_id+".docx"));
        document.close();
        String insert_name = "Covid_Dose2_" + current_employee_id+".docx";
        try{
            String sql_update ="UPDATE COVID_INFO SET DOSE2_ATTACHMENT = '"
                    + insert_name
                    + "' WHERE EMPLOYEE_ID = '"
                    + Main.current_large_employee.employeeID+ "'";
            db.update_table(sql_update);
            Main.current_large_employee.setCovidDose2Attach(insert_name);
            lbl_file_selected.setText("");
        }catch(NullPointerException np){
            lbl_file_selected.setText("No file was selected for 'Vaccine Dose #2");
        }
        populate_attachments_table(Main.current_large_employee);
        this.txtarea_new_employee_info.clear();
        this.txtarea_new_employee_info.appendText(Main.current_large_employee.toString());
    }

    @FXML
    void attach_withhold_agreement(MouseEvent event) throws IOException, InvalidFormatException {
        Access_Full_Employee_DB db = new Access_Full_Employee_DB();
        fileChooser.setTitle("Attach Withhold Agreement");
        Stage file_stage = Main.createFileChooserStage(event, "Create_Employee_Screen.fxml");
        File file = fileChooser.showOpenDialog(file_stage);
        String network_location = "\\\\Inddc-vm56\\mssqlserver\\FileDBStreamDirectory\\FileDBFileStreamDirectory\\Employee_Attachments\\Withhold_Agreement_";
        String employee_id = Integer.toString(Main.current_large_employee.employeeID);
        XWPFDocument document = new XWPFDocument(OPCPackage.open((file)));
        document.write(new FileOutputStream(network_location+employee_id+".docx"));
        document.close();
        String insert_name = "Withhold_Agreenment_" + current_employee_id+".docx";
        try{

            String sql_update ="UPDATE ATTACHMENTS SET WITHHOLD_AGREEMENT = '"
                    + insert_name
                    + "' WHERE EMPLOYEE_ID = '"
                    + Main.current_large_employee.employeeID+ "'";
            db.update_table(sql_update);
            Main.current_large_employee.setWithholdAgreement(insert_name);
            lbl_file_selected.setText("");
        }catch(NullPointerException np){
            lbl_file_selected.setText("No file was selected for 'Withhold Agreement'");
        }
        populate_attachments_table(Main.current_large_employee);
        this.txtarea_new_employee_info.clear();
        this.txtarea_new_employee_info.appendText(Main.current_large_employee.toString());

    }

    @FXML
    void attach_work_permit(MouseEvent event) throws InvalidFormatException, IOException {
        Access_Full_Employee_DB db = new Access_Full_Employee_DB();
        fileChooser.setTitle("Attach Work permit");
        Stage file_stage = Main.createFileChooserStage(event, "Create_Employee_Screen.fxml");
        File file = fileChooser.showOpenDialog(file_stage);
        String network_location = "\\\\Inddc-vm56\\mssqlserver\\FileDBStreamDirectory\\FileDBFileStreamDirectory\\Employee_Attachments\\Work_Permit_";
        String employee_id = Integer.toString(Main.current_large_employee.employeeID);
        XWPFDocument document = new XWPFDocument(OPCPackage.open((file)));
        document.write(new FileOutputStream(network_location+employee_id+".docx"));
        document.close();
        String insert_name = "Work_Permit_" + current_employee_id+".docx";
        try {
            String sql_update = "UPDATE ATTACHMENTS SET WORK_PERMIT = '"
                    +insert_name
                    + "' WHERE EMPLOYEE_ID = '"
                    + Main.current_large_employee.employeeID + "'";
            db.update_table(sql_update);
            Main.current_large_employee.setWorkPermit(insert_name);
            lbl_file_selected.setText("");
        }catch(NullPointerException np){
            lbl_file_selected.setText("No file was selected for 'Work Permit'");
        }
        populate_attachments_table(Main.current_large_employee);
        this.txtarea_new_employee_info.clear();
        this.txtarea_new_employee_info.appendText(Main.current_large_employee.toString());

    }

    @FXML
    void go_home(MouseEvent event) {
        Main.createNewScene(event, "Employee_Dashboard.fxml");
    }

    @FXML
    void quit_program(MouseEvent event) {
        Main.exit_program(event);
    }

    @FXML
    void create_employee(MouseEvent event) throws SQLException {
        Access_Full_Employee_DB db = new Access_Full_Employee_DB();
        String passportExp = "";
        String arrivalDate = "";
        String depatureDate = "";
        String hireDate = "";
        String mexCode = this.txtfield_mex_code.getText();
        String givenName = this.txtfield_given_name.getText();
        String dateOfBirth = "";
        String passportNum = this.txtfield_passport_num.getText();

        try {
            if (((LocalDate)this.datepicker_dob.getValue()).isAfter(LocalDate.now())) {
                this.lbl_dob_incorrect.setText("Invalid Date");
            } else {
                this.lbl_dob_incorrect.setText("");
                dateOfBirth = ((LocalDate)this.datepicker_dob.getValue()).toString();
            }
        } catch (NullPointerException var41) {
            System.out.println("no date selected for dob");
        }

        try {
            passportExp = ((LocalDate)this.datepicker_passport_expiration.getValue()).toString();
        } catch (NullPointerException var40) {
            System.out.println("No value for passport exp");
        }

        try {
            arrivalDate = ((LocalDate)this.datepicker_arrival_date.getValue()).toString();
        } catch (NullPointerException var39) {
            System.out.println("No value for arrival date");
        }

        try {
            depatureDate = ((LocalDate)this.datepicker_departure_date.getValue()).toString();
        } catch (NullPointerException var38) {
            System.out.println("No value for departure date");
        }

        String medicalIns = this.txtfield_medical_ins.getText();
        String sii = this.txtfield_ssn.getText();
        String txtEmail = this.txtfield_email.getText();
        String bankID = this.txtfield_bank_id.getText();

        try {
            hireDate = ((LocalDate)this.datepicker_hire_date.getValue()).toString();
        } catch (NullPointerException var37) {
            System.out.println("No value for hire date");
        }

        String payRate = this.txtfield_rate.getText();
        String surname = this.txtfield_surname.getText();
        String visaNum = this.txtfield_visa.getText();
        String healthCard = this.txtfield_health_card.getText();
        String nickName = this.txtfield_nickname.getText();
        String emailPassword = this.txtfield_email_password.getText();
        String bankAccount = this.txtfield_bank_account.getText();
        String stillActive = "";
        if (this.checkbox_active.isSelected()) {
            stillActive = "TRUE";
        } else {
            stillActive = "FALSE";
        }

        String txtMobile = this.txtfield_mobile.getText();
        String quarantineLocation = this.txtfield_quarantine_loc.getText();
        String houseNum = this.txtfield_house_number.getText();
        String houseName = this.txtfield_house_name.getText();
        String houseAddress = this.txtfield_house_address.getText();
        String bedID = this.txtfield_bed_id.getText();
        String corpCellType = this.txtfield_corp_cell_type.getText();
        String corpCellNum = this.txtfield_corp_cell_number.getText();
        String txtNotes = this.txtarea_notes.getText();
        String covid1Record = "";

        try {
            covid1Record = ((LocalDate)this.datepicker_dose_1.getValue()).toString();
        } catch (NullPointerException var36) {
            System.out.println("No value for covid 1 record");
        }

        String covid2Record = "";

        try {
            covid2Record = ((LocalDate)this.datepicker_dose2.getValue()).toString();
        } catch (NullPointerException var35) {
            System.out.println("No value for covid 2 record");
        }

        db.insert_to_covid_info_without_attachments(this.current_employee_id, quarantineLocation, covid1Record, covid2Record);
        db.insert_to_employee_info(this.current_employee_id, mexCode, givenName, surname, dateOfBirth, passportNum, passportExp, sii, visaNum, nickName, txtNotes);
        db.insert_to_bank_info(this.current_employee_id, bankID, bankAccount);
        db.insert_to_contact_info(this.current_employee_id, txtMobile, txtEmail, emailPassword, corpCellType, corpCellNum);
        db.insert_to_employment_info(this.current_employee_id, hireDate, arrivalDate, depatureDate, payRate, stillActive);
        db.insert_to_health_info(this.current_employee_id, healthCard, medicalIns);
        db.insert_to_housing_info(this.current_employee_id, houseNum, houseName, houseAddress, bedID);
        db.insert_to_mex_contact_info(this.current_employee_id, "", "", "", "", "", "", "");
        db.insert_to_attachments(this.current_employee_id, "", "", "", "");
        Main.current_large_employee = db.get_employee_info_by_id_using_view(this.current_employee_id);
        this.txtarea_new_employee_info.appendText(Main.current_large_employee.toString());
        lbl_employee_created.setVisible(true);
    }

    @FXML
    void update_mex_contact_info(MouseEvent event) {
        Access_Full_Employee_DB db = new Access_Full_Employee_DB();
        String mexContactName = this.txtfield_mex_contact_name.getText();
        String mexPhone = this.txtfield_mex_phone.getText();
        String mexAddres = this.txtfield_mex_address.getText();
        String mexMunicipality = this.txtfield_mex_municipality.getText();
        String mexNeighbourhood = this.txtfield_mex_neighborhood.getText();
        String mexState = this.txtfield_mex_state.getText();
        String mexPostalCode = this.txtfield_mex_postal_code.getText();
        String sql_update = "UPDATE MEX_CONTACT_INFO SET MEX_CONTACT_NAME = '" + mexContactName + "' ,MEX_MUNICIPALITY = '" + mexMunicipality + "' ,MEX_PHONE = '" + mexPhone + "' ,MEX_NEIGHBORHOOD = '" + mexNeighbourhood + "' ,MEX_POSTAL_CODE = '" + mexPostalCode + "' ,MEX_STREET_ADDRESS = '" + mexAddres + "' ,MEX_STATE = '" + mexState + "'WHERE EMPLOYEE_ID = '" + this.current_employee_id + "'";
        db.update_table(sql_update);
        this.txtarea_new_employee_info.clear();
        this.txtarea_new_employee_info.appendText(Main.current_large_employee.toString());
    }

    void populate_attachments_table(Large_Employee current_employee) {
        new Access_Full_Employee_DB();
        this.tblview_attachments.getItems().clear();
        this.col_attachments.setCellValueFactory(new PropertyValueFactory("attachmentName"));
        this.col_file_name.setCellValueFactory(new PropertyValueFactory("fileName"));
        ArrayList attachments_list = new ArrayList();

        try {
            attachments_list.add(new Attachments("Work Permit", Main.current_large_employee.workPermit));
            attachments_list.add(new Attachments("Employment Contract", Main.current_large_employee.employmentAgreement));
            attachments_list.add(new Attachments("Benefit Card", Main.current_large_employee.benefitCard));
            attachments_list.add(new Attachments("Withhold Agreement", Main.current_large_employee.withholdAgreement));
            attachments_list.add(new Attachments("Vaccine Dose 1", Main.current_large_employee.covidDose1Attach));
            attachments_list.add(new Attachments("Vaccine Dose 2", Main.current_large_employee.covidDose2Attach));
            this.tblview_attachments.getItems().addAll(attachments_list);
        } catch (NullPointerException var5) {
            this.lbl_file_selected.setText("Please select an employee for attachment");
        }

    }
}

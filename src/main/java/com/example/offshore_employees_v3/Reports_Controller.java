package com.example.offshore_employees_v3;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.usermodel.*;
import org.apache.xmlbeans.XmlException;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRow;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Reports_Controller {

    @FXML
    private TextField txtfield_employee_number;


    @FXML
    private Button btn_go;

    @FXML
    private DatePicker datepicker_start;

    @FXML
    private DatePicker datepicker_end;


    @FXML
    private TableView<Work_Hours> tblview_work_hours;

    @FXML
    private TableColumn<?, ?> col_HW_empID;

    @FXML
    private TableColumn<?, ?> col_HW_name;

    @FXML
    private TableColumn<?, ?> col_HW_hours;

    @FXML
    private TableColumn<?, ?> col_HW_days_worked;

    @FXML
    private TableColumn<?, ?> col_HW_cost;

    @FXML
    private Label lbl_hours;

    @FXML
    private Label lbl_cost;

    @FXML
    private Label lbl_JC_hours;

    @FXML
    private Label lbl_JC_cost;


    @FXML
    private TableView<Job_Costing> tblview_job_costing;

    @FXML
    private TableColumn<?, ?> col_JC_hours;

    @FXML
    private TableColumn<?, ?> col_JC_cost;

    @FXML
    private TableColumn<?, ?> col_JC_date;

    @FXML
    private TableColumn<?, ?> col_job_name;

    @FXML
    private TableView<Job_Costing> tblview_job_costing_by_day;

    @FXML
    private TableColumn<?, ?> col_JCBD_date;

    @FXML
    private TableColumn<?, ?> col_JCBD_hours;


    @FXML
    private Button btn_work_hours;

    @FXML
    private Button btn_work_hours_employee;

    @FXML
    private Button btn_export_work_hours;

    @FXML
    private Button btn_export_employee_work_hours;

    @FXML
    private Label lbl_JCBD_hours;

    @FXML
    private Label lbl_JCBD_cost;

    @FXML
    private Button btn_job_costing;

    @FXML
    private Button btn_job_costing_by_day;

    @FXML
    private Button btn_export_job_costing;

    @FXML
    private Button btn_export_job_costing_by_day;


    @FXML
    private Label lbl_work_hours_date_range;

    @FXML
    private Button btn_daily_covid_signoff;

    @FXML
    private Button btn_covid_email_forms;

    @FXML
    private Button btn_export_croptracker;

    @FXML
    private Rectangle rectangle_forms;

    @FXML
    private Label lbl_forms_choice;

    @FXML
    private Label lbl_no_employee_hours;

    @FXML
    private Label lbl_pick_start_date;

    @FXML
    private Label lbl_covid_email_forms;

    @FXML
    private Label lbl_current_employee;

    @FXML
    private Label lbl_selected_employee;

    @FXML
    private Label lbl_current_employee_number;

    @FXML private Label lbl_no_employee_selected;

    @FXML
    private Button btn_print_covid_email;

    @FXML
    private DatePicker datepicker_covid_start_date;


    @FXML
    private Button btn_print_covid_signoff;

    @FXML
    private Button btn_home;

    @FXML
    private Button btn_quit;

    @FXML
    private Label lbl_form_created;

    @FXML
    private Label lbl_report_created;

    @FXML
    private Label lbl_house_id;

    @FXML
    private ComboBox<String> combobox_house_id;

    // Instantiate employee_id to 1, incase no employee is selected, the program won't crash.
    String employee_id = "";

    DecimalFormat decimal_format = new DecimalFormat("0.00");


    // If no dates are specified, then the date range will go back 1 year from today.
    LocalDate current_end_date = LocalDate.now();
    LocalDate current_start_date = current_end_date.minusYears(1);
    DateTimeFormatter today_format = DateTimeFormatter.ofPattern("MM/dd/yyyy");
    String start_date_formatted = current_start_date.format(today_format);
    String end_date_formatted = current_end_date.format(today_format);

    // Global database object to use in all methods in class.
    Access_Full_Employee_DB db = new Access_Full_Employee_DB();




    @FXML
    void get_covid_email_forms(MouseEvent event) throws InvalidFormatException, IOException {

        // Depending on which forms you are trying to create will determine which components will be visible.
        lbl_report_created.setVisible(false);
        lbl_form_created.setText("");
        //Set the covid daily sign off components to invisible
        lbl_pick_start_date.setVisible(false);
        lbl_forms_choice.setVisible(false);
        rectangle_forms.setVisible(false);
        datepicker_covid_start_date.setVisible(false);
        btn_print_covid_signoff.setVisible(false);
        lbl_house_id.setVisible(false);
        combobox_house_id.setVisible(false);


        // Set covid email componets to visible
        rectangle_forms.setVisible(true);
        lbl_covid_email_forms.setVisible(true);
        lbl_current_employee_number.setVisible(true);
        lbl_current_employee.setVisible(true);
        btn_print_covid_email.setVisible(true);
    }



    void print_covid_email_form_by_id(int empID) throws IOException, InvalidFormatException {
        lbl_report_created.setVisible(false);
        Large_Employee selected_employee = db.get_employee_info_by_id_using_view(empID);
        XWPFDocument document = new XWPFDocument(OPCPackage.open("CovidEmailForms.docx"));
        for (XWPFParagraph p : document.getParagraphs()) {
            List<XWPFRun> runs = p.getRuns();
            if (runs != null) {
                for (XWPFRun r : runs) {
                    String text = r.getText(0);
                    if (text != null && text.contains("Given_Name")) {
                        text = text.replace("Given_Name", selected_employee.givenName);
                        r.setText(text, 0);
                    } else if (text != null && text.contains("Surname")) {
                        text = text.replace("Surname", selected_employee.surName);
                        r.setText(text, 0);
                    } else if (text != null && text.contains("ScotlynnID")) {
                        text = text.replace("ScotlynnID", Integer.toString(selected_employee.employeeID));
                        r.setText(text, 0);
                    } else if (text != null && text.contains("Mex_ID_Code")) {
                        text = text.replace("Mex_ID_Code", selected_employee.mex_code);
                        r.setText(text, 0);
                    } else if (text != null && text.contains("arrivalDate")) {
                        text = text.replace("arrivalDate", selected_employee.arrivalDate);
                        r.setText(text, 0);
                    }
                }
            }
        }
        try {
            document.write(new FileOutputStream("\\\\Inddc-vm56\\mssqlserver\\FileDBStreamDirectory\\FileDBFileStreamDirectory\\Covid_Email_Forms\\CovidEmailForm_" + selected_employee.employeeID + ".docx"));
        }catch(FileNotFoundException FNF){
            lbl_form_created.setText("FORM COULD NOT BE CREATED FOR EMPLOYEE #:  "+employee_id);
        }

        String url_location = "\\\\Inddc-vm56\\mssqlserver\\FileDBStreamDirectory\\FileDBFileStreamDirectory\\Covid_Email_Forms\\CovidEmailForm_" + selected_employee.employeeID + ".docx";
        File file = new File(url_location);
        Desktop desktop = Desktop.getDesktop();
        try{
            if(file.exists()){
                desktop.open(file);
            }
            else{
                System.out.println("Cannot open this file");
            }
        }catch(Exception e){
            System.out.println("Cannot open this file");
        }
    }



    void print_daily_symptom_signature(String start_screen_date, String houseID) throws IOException, InvalidFormatException, XmlException {
        lbl_report_created.setVisible(false);
        ArrayList<Large_Employee> sign_off_list = db.get_covid_screening_list(houseID);
        XWPFDocument document = new XWPFDocument(OPCPackage.open("CoVid_Daily_Screening.docx"));
        int counter = 2;

            for(Large_Employee x : sign_off_list) {
                XWPFTable tbl = document.getTableArray(0);
                XWPFTableRow oldRow = tbl.getRow(1);
                CTRow ctrow = null;
                try{
                    ctrow = CTRow.Factory.parse(oldRow.getCtRow().newInputStream());
                }catch (XmlException | IOException e) {e.printStackTrace();}
                XWPFTableRow newRow = new XWPFTableRow(ctrow, tbl);
                newRow.getCell(0).setText(x.givenName + " " + x.surName);
                newRow.getCell(1).setText(Integer.toString(x.employeeID));
                newRow.getCell(2).setText(x.mex_code);
                newRow.getCell(3).setText(x.bedID);
                tbl.addRow(newRow, counter);
                counter++;
            }
        for (XWPFParagraph p : document.getParagraphs()) {
            List<XWPFRun> runs = p.getRuns();
            if (runs != null) {
                for (XWPFRun r : runs) {
                    String text = r.getText(0);
                    if (text != null && text.contains("house_id")) {
                        text = text.replace("house_id", houseID);
                        r.setText(text, 0);
                    } else if (text != null && text.contains("date_selected_today")) {
                        text = text.replace("date_selected_today", start_screen_date);
                        r.setText(text, 0);
                    }
                }
            }
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, MMM dd, yyyy");
        LocalDate formDate = LocalDate.parse(start_screen_date, formatter);
        String newDate = formDate.toString();
        String temporary_file = "\\\\Inddc-vm56\\mssqlserver\\FileDBStreamDirectory\\FileDBFileStreamDirectory\\Covid_Sign_Off_Forms\\CoVid_Screening_" + houseID + "_"+ newDate+ ".docx";
        document.write(new FileOutputStream(temporary_file));
        document.close();
        String url_location = "\\\\Inddc-vm56\\mssqlserver\\FileDBStreamDirectory\\FileDBFileStreamDirectory\\Covid_Sign_Off_Forms\\CoVid_Screening_" + houseID + "_"+ newDate+ ".docx";
        File file = new File(url_location);
        Desktop desktop = Desktop.getDesktop();
        try{
            if(file.exists()){
                desktop.open(file);
            }
            else{
                System.out.println("Cannot open this file");
            }
        }catch(Exception e){
            System.out.println("Cannot open this file");
        }

    }



    @FXML
    void get_daily_covid_sign_off(MouseEvent event) throws IOException, InvalidFormatException {
        lbl_report_created.setVisible(false);
        lbl_form_created.setText("");
        //Set the covid email forms to invisible
        rectangle_forms.setVisible(false);
        lbl_covid_email_forms.setVisible(false);
        lbl_current_employee_number.setVisible(false);
        lbl_current_employee.setVisible(false);
        btn_print_covid_email.setVisible(false);

        //Set covid daily sign off components to visible
        lbl_pick_start_date.setVisible(true);
        lbl_forms_choice.setVisible(true);
        rectangle_forms.setVisible(true);
        datepicker_covid_start_date.setVisible(true);
        btn_print_covid_signoff.setVisible(true);
        lbl_house_id.setVisible(true);
        combobox_house_id.setVisible(true);
    }

    @FXML
    void get_total_work_hours(MouseEvent event) {
        lbl_form_created.setText("");
        lbl_report_created.setVisible(false);

        // The label is changed to 'ALL' because this internal report includes all of the employees
        lbl_selected_employee.setText("EMPLOYEE ID SELECTED:  ALL");

        // Tableview should be reset everytime to avoid overlapping
        tblview_work_hours.getItems().clear();

        // The Date fomatter needs to be adjusted to a date that can be stored into a file.
        DateTimeFormatter today_format = DateTimeFormatter.ofPattern("MM.dd.yyyy");

        // The global variables for start and end date are preset to a year before today unless specified.
        String start_date = current_start_date.format(today_format);
        String end_date = current_end_date.format(today_format);

        // Cost and hour labels need to be reset for the new data.
        lbl_hours.setText("");
        lbl_cost.setText("");

        // An ArrayList containing all the hours worked by all the employees based on date range.
        ArrayList<Work_Hours> all_hours_worked = db.get_all_work_hours_list(current_start_date, current_end_date);

        // Adds the objects in the ArrayList to the tableview
        tblview_work_hours.getItems().addAll(all_hours_worked);

        // Total cost and total hours are calculated by iterating through the ArrayList
        // total_cost = sum(pay_rate * hours)
        // total_hours = sum(all hours worked)
        double total_cost = 0.0;
        double total_hours = 0.0;
        for(int i = 0; i< all_hours_worked.size();i++){
            total_cost += all_hours_worked.get(i).cost;
            total_hours += all_hours_worked.get(i).hours_worked;
        }

        // Since doubles are being incremented, the total cost needs to be rounded again to 2 decimal places.
        BigDecimal bd = BigDecimal.valueOf(total_cost);
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        total_cost = bd.doubleValue();

        // Sets the total cost and total hours labels to their values
        lbl_hours.setText(Double.toString(total_hours)+" hrs");
        lbl_cost.setText("$ "+Double.toString(total_cost));
    }


    @FXML
    void get_work_hours_by_employee(MouseEvent event) {
        // Reset the labels and make sure other unrelated components aren't visible
        lbl_report_created.setVisible(false);
        lbl_form_created.setText("");
        lbl_hours.setText("");
        lbl_cost.setText("");

        // Clear the tableview each time new data is requested
        tblview_work_hours.getItems().clear();

        // Instantiate employee id variable prior to the try-catch so it can be used out of the catch
        int emp_id =1;
        try{
            // If the employee id entered into the textfield is not a number, it will prompt the user to enter a new one
            emp_id = Integer.parseInt(employee_id);

            // label is set letting the user know what employee id is currently in use
            lbl_no_employee_selected.setText("");


        }catch(NumberFormatException np){
             // If a number is not entered into the textfield, NumberFormatException will inform user
            lbl_no_employee_selected.setText("NO EMPLOYEE SELECTED");
            lbl_selected_employee.setTextFill(Color.DARKRED);
            lbl_selected_employee.setText("");
        }

        // An ArrayList that will hold the selected employees work hours based on the date range
        ArrayList<Work_Hours> employee_hours_worked = new ArrayList();

        // A try catch incase the global variable for employee id is not an integer, or the dates are not LocalDates
        try {
            employee_hours_worked = db.get_work_hours_by_id(Integer.parseInt(employee_id), current_start_date, current_end_date);
            lbl_no_employee_selected.setText("");
        }catch(NumberFormatException np){
            lbl_no_employee_selected.setText("NO EMPLOYEE SELECTED");
            lbl_selected_employee.setText("");
            }

        try {
            if (employee_hours_worked.size() == 0 & (Integer.parseInt(employee_id)) != 0) {
                lbl_no_employee_hours.setText("THAT EMPLOYEE DOES NOT HAVE ANY HOURS");
            }
            else {
                lbl_no_employee_hours.setText("");
                tblview_work_hours.getItems().addAll(employee_hours_worked);
                double total_cost = 0.0;
                double total_hours = 0.0;
                for (int i = 0; i < employee_hours_worked.size(); i++) {
                    total_cost += employee_hours_worked.get(i).cost;
                    total_hours += employee_hours_worked.get(i).hours_worked;
                }
                // Since doubles are being incremented, the total cost needs to be rounded again to 2 decimal places.
                BigDecimal bd = BigDecimal.valueOf(total_cost);
                bd = bd.setScale(2, RoundingMode.HALF_UP);
                total_cost = bd.doubleValue();
                lbl_hours.setText(Double.toString(total_hours) + " Hrs");
                lbl_cost.setText("$ " + Double.toString(total_cost));
            }
        }catch(NumberFormatException np){
            lbl_no_employee_selected.setText("NO EMPLOYEE SELECTED");
            lbl_selected_employee.setText("NO EMPLOYEE SELECTED");
            lbl_selected_employee.setTextFill(Color.DARKRED);
        }
    }

    /*
    Method: go_home
    Will take the user back to main dashboard when the "Home" button is clicked
     */
    @FXML
    void go_home(MouseEvent event) {
        lbl_report_created.setVisible(false);
        lbl_form_created.setText("");
        Main.createNewScene(event, "Employee_Dashboard.fxml");
    }

    /*
Method: go_search_for_employee
Once the "Go" button is clicked, it will take the user's input and store it into global variable employee ID.
Also checks that the user's input was a number that can be found in the database
 */
    @FXML
    void go_search_for_employee(MouseEvent event) {
        lbl_report_created.setVisible(false);
        lbl_form_created.setText("");

        // Global variable employee id is set to the value in the textfield.
        employee_id = txtfield_employee_number.getText();
        try{
            // Assures that the input from the user is a number before assigning it to the labels
            int test_employee_id = Integer.parseInt(employee_id);

            // Then assures that the number entered is within the range of the employee id's in the database
            if(test_employee_id < db.get_largest_employee_id() && test_employee_id > 0) {
                //Set current employee number to the label
                lbl_current_employee_number.setText(employee_id);
                lbl_selected_employee.setText("EMPLOYEE ID SELECTED:  #" + employee_id);
                lbl_selected_employee.setTextFill(Color.DARKGREEN);

                // Clears the textfield, but sets prompt text to the current employee being used
                txtfield_employee_number.clear();
                txtfield_employee_number.promptTextProperty().setValue(employee_id);
            }else{
                lbl_selected_employee.setText("THAT NUMBER IS NOT VALID: #" + employee_id);
                lbl_selected_employee.setTextFill(Color.DARKRED);
            }
        }catch (NumberFormatException np){
            // If the input isn ot a number, no employee will be selected and user will be prompted to try again
            lbl_selected_employee.setText("NO EMPLOYEE SELECTED");
            lbl_selected_employee.setTextFill(Color.DARKRED);
        }
    }


    /*
Method: search_employee_ENTER
Has the same functionality as the method above (go_search_for_employee), but instead of pressing the 'Go' button
the enter key is pressed.
*/
    @FXML
    void search_employee_ENTER(KeyEvent event) {
        lbl_report_created.setVisible(false);
        lbl_form_created.setText("");
        if(event.getCode() == KeyCode.ENTER){
            // Global variable employee id is set to the value in the textfield.
            employee_id = txtfield_employee_number.getText();
            try{
                // Assures that the input from the user is a number before assigning it to the labels
                int test_employee_id = Integer.parseInt(employee_id);

                // Then assures that the number entered is within the range of the employee id's in the database
                if(test_employee_id < db.get_largest_employee_id() && test_employee_id > 0) {
                    //Set current employee number to the label
                    lbl_current_employee_number.setText(employee_id);
                    lbl_selected_employee.setText("EMPLOYEE ID SELECTED:  #" + employee_id);
                    lbl_selected_employee.setTextFill(Color.DARKGREEN);

                    // Clears the textfield, but sets prompt text to the current employee being used
                    txtfield_employee_number.clear();
                    txtfield_employee_number.promptTextProperty().setValue(employee_id);
                }else{
                    lbl_selected_employee.setText("THAT NUMBER IS NOT VALID: #" + employee_id);
                    lbl_selected_employee.setTextFill(Color.DARKRED);
                }
            }catch (NumberFormatException np){
                // If the input isn ot a number, no employee will be selected and user will be prompted to try again
                lbl_selected_employee.setText("NO EMPLOYEE SELECTED");
                lbl_selected_employee.setTextFill(Color.DARKRED);
            }
        }
    }


    /*
Method: print_daily_covid_signoff
By pressing the button "Create Form" under the Covid Sign-off forms tab, this method will generate a form
for the houseID that is selected. If no houseID is selected, the user will be prompted to select one.
Also, if the user has not selected a date from the datepicker, they will be prompted to pick one.
*/
    @FXML
    void print_daily_covid_signoff(MouseEvent event) throws IOException, InvalidFormatException, XmlException {

        // Set the label that informs the user of the report being complete to invisible.
        lbl_report_created.setVisible(false);
        lbl_form_created.setText("Just a moment...");
        lbl_selected_employee.setText("");
        String houseID = "";
        LocalDate start_screening_date = null;


        start_screening_date = datepicker_covid_start_date.getValue();
        houseID = combobox_house_id.getValue();
         if(houseID == null && start_screening_date == null){
            lbl_selected_employee.setText("NO HOUSE ID OR DATE WAS SELECTED");
            lbl_selected_employee.setTextFill(Color.DARKRED);
        }
         else  if(start_screening_date == null){
            lbl_selected_employee.setText("NO DATE WAS SELECTED");
            lbl_selected_employee.setTextFill(Color.DARKRED);
            // Label is generated if the form could not be created
        }
        else if(houseID == null) {
            lbl_selected_employee.setText("NO HOUSE ID WAS SELECTED");
            lbl_selected_employee.setTextFill(Color.DARKRED);
        }
        else{
            // Select the start date for the covid screening forms
            lbl_selected_employee.setText("");
            lbl_selected_employee.setTextFill(Color.DARKGREEN);
        }

     if(houseID == null || start_screening_date == null){
         lbl_form_created.setText("FORM COULD NOT BE CREATED");
     }else{
         // The date should be put into a string so it can be formatted to the appropriate format
         String print_date = start_screening_date.toString();

         // EEEE = day of week, MMM = month in abrev form(i.e. Jan), dd= day, yyyy = year
         DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, MMM dd, yyyy");
         // The forms will be printed based on the house ID's of the employees
         houseID = combobox_house_id.getValue();


         print_date = start_screening_date.format(formatter);
         print_daily_symptom_signature(print_date, houseID);

         // Label is generated once the form is done being created.
         lbl_form_created.setText("Form has been created for House #:  " + houseID);
     }

    }




    /*
Method: print_covid_email_forms
By pressing the button "Create Form" under the Covid/Email forms tab, this method will generate a form
for the employee that is selected. If no employee ID is selected, the user will be prompted to select one.
*/
    @FXML
    void print_covid_email_forms(MouseEvent event) throws IOException, InvalidFormatException {
        lbl_report_created.setVisible(false);
        lbl_form_created.setText("");
        lbl_no_employee_selected.setText("");
        try {
            int empID = Integer.parseInt(employee_id);
            print_covid_email_form_by_id(empID);
            lbl_form_created.setText("Form has been created for Employee #:  "+ empID);
        }
        catch(NumberFormatException np){
            lbl_form_created.setText("FORM COULD NOT BE CREATED");
           // lbl_no_employee_selected.setText("NO EMPLOYEE SELECTED");
            lbl_selected_employee.setText("NO EMPLOYEE SELECTED");
            lbl_selected_employee.setTextFill(Color.DARKRED);
        }

    }

    /*
    Method: quit_program
    When the 'Quit' button is clicked, the program will exit.
     */
    @FXML
    void quit_program(MouseEvent event) {
        lbl_report_created.setVisible(false);
        Main.exit_program(event);
    }


    /*
    Method: populate_work_hours_tableview
    This is called when the screen is initialized.
    The columns of the tableview are set the variable names in the Work_Hours class, so the appropriate value
    is stored in the correct column.
     */
    void populate_work_hours_tableview(){
        lbl_report_created.setVisible(false);
        // Initialize work_hours tableview
        col_HW_empID.setCellValueFactory(new PropertyValueFactory<>("employee_id"));
        col_HW_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        col_HW_hours.setCellValueFactory(new PropertyValueFactory<>("hours_worked"));
        col_HW_days_worked.setCellValueFactory(new PropertyValueFactory<>("date_worked"));
        col_HW_cost.setCellValueFactory(new PropertyValueFactory<>("cost"));
    }

    /*
Method: populate_job_costing_tableview
This method populates the tableview that holds the job costing data.
 */
    void populate_job_costing_tableview(){
        col_JC_hours.setCellValueFactory(new PropertyValueFactory<>("hours"));
        col_JC_cost.setCellValueFactory(new PropertyValueFactory<>("cost"));
        col_JC_date.setCellValueFactory(new PropertyValueFactory<>("date"));
        col_job_name.setCellValueFactory(new PropertyValueFactory<>("job_name"));
    }


    /*
Method: populate_job_costing_by_day_tableview
This method instantiates the tableview for job costings by day.
The job names are nested into the hours column.
 */
    void populate_job_costing_by_day_tableview(){
        lbl_report_created.setVisible(false);
        // Initialize work_hours tableview
        lbl_report_created.setVisible(false);
        tblview_job_costing_by_day.setEditable(true);
        col_JCBD_date.setCellValueFactory(new PropertyValueFactory<>("date"));
        TableColumn col_asparagus = new TableColumn("Asparagus");
        TableColumn col_corn = new TableColumn("Corn");
        TableColumn col_pumpkin = new TableColumn("Pumpkin");
        TableColumn col_ginseng = new TableColumn("Ginseng");
        TableColumn col_other = new TableColumn("Other");
        TableColumn col_cost = new TableColumn("Total");
        //col_JCBD_hours.setCellValueFactory(new PropertyValueFactory<>("hours"));
        col_asparagus.setCellValueFactory(new PropertyValueFactory<>("asparagus"));
        col_corn.setCellValueFactory(new PropertyValueFactory<>("corn"));
        col_pumpkin.setCellValueFactory(new PropertyValueFactory<>("pumpkin"));
        col_ginseng.setCellValueFactory(new PropertyValueFactory<>("ginseng"));
        col_other.setCellValueFactory(new PropertyValueFactory<>("other"));
        col_cost.setCellValueFactory(new PropertyValueFactory<>("hours"));
        col_JCBD_hours.getColumns().addAll(col_asparagus, col_corn, col_pumpkin, col_ginseng, col_other, col_cost);
    }

    /*
    Method: populate_houseid_choicebox
    This method is called when the screen is initialized.
    The choicebox will be populated with the house ID numbers that are currently stored in the database.
     */
    void populate_houseid_choicebox(){
        lbl_report_created.setVisible(false);
        Access_Full_Employee_DB db = new Access_Full_Employee_DB();
        ArrayList<String> house_is_list = db.get_house_id_numbers();
        ArrayList<Integer> order_house_list = new ArrayList<>();
        combobox_house_id.setPromptText("House ID #");

        for(String x : house_is_list){
            if(!x.equals("")){
                order_house_list.add(Integer.parseInt(x));
            }else{
                System.out.println("Not a Number");
            }
        }
        Collections.sort(order_house_list);
        for(Integer x : order_house_list){
            combobox_house_id.getItems().add(Integer.toString(x));
        }
    }

    /*
    Method: initialize()
    This method is automatically called when the screen is called. It should initialize all of the components that need
    to be initialized prior to the user accessing them.
     */
    @FXML
    void initialize(){
        lbl_report_created.setVisible(false);
        Main.current_large_employee = null;
        lbl_pick_start_date.setVisible(false);
        lbl_forms_choice.setVisible(false);
        datepicker_covid_start_date.setVisible(false);
        btn_print_covid_signoff.setVisible(false);
        rectangle_forms.setVisible(false);
        lbl_covid_email_forms.setVisible(false);
        lbl_current_employee.setVisible(false);
        lbl_current_employee_number.setVisible(false);
        btn_print_covid_email.setVisible(false);
        populate_houseid_choicebox();
        populate_work_hours_tableview();
        rectangle_forms.setVisible(false);
        combobox_house_id.setVisible(false);
        populate_job_costing_tableview();
        populate_job_costing_by_day_tableview();

    }

    /*
    Method: set_start_date() and set_end_date
    This method will get the date range based on the user's selection. Once the user clicks a date, a label will be
    generated containing the data range. No button needs to be pressed.
    If no date range is selected from these date pickers, then the default date will start one year ago today and end
    today.
     */
    @FXML
    void set_start_date(ActionEvent event){
        lbl_form_created.setText("");
        DateTimeFormatter today_format = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        current_start_date = datepicker_start.getValue();
        start_date_formatted = current_start_date.format(today_format);
        lbl_work_hours_date_range.setText("From:     "+ start_date_formatted + "     To:     ");
    }
    @FXML
    void set_end_date(ActionEvent event){
        lbl_form_created.setText("");
        DateTimeFormatter today_format = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        current_end_date = datepicker_end.getValue();
        end_date_formatted= current_end_date.format(today_format);
        lbl_work_hours_date_range.setText("From:     "+ start_date_formatted + "     To:     " +end_date_formatted);
    }


    @FXML
    void get_job_costing(MouseEvent event) {
        tblview_job_costing_by_day.getItems().clear();
        tblview_job_costing_by_day.getItems().clear();
        ArrayList<Job_Costing> all_job_costings = db.get_job_costing();
        tblview_job_costing.getItems().addAll(all_job_costings);
        double total_cost = 0.0, total_hours = 0.0;
        for(Job_Costing x : all_job_costings){
            total_cost += x.cost;
            total_hours += x.hours;
        }
        BigDecimal bd = BigDecimal.valueOf(total_cost);
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        total_cost = bd.doubleValue();
        lbl_JCBD_cost.setText("$" + total_cost);
        lbl_JCBD_hours.setText(total_hours + "Hrs");
        lbl_JC_cost.setText("Hrs");
        lbl_JC_hours.setText("$");
        }


    @FXML
    void get_job_costing_by_day(MouseEvent event) {
        Job_Costing selected_job_cost = null;
        ArrayList<Job_Costing> stored_job_costings = new ArrayList<>();
        if(tblview_job_costing.getSelectionModel().getSelectedItem() == null){
            tblview_job_costing.getSelectionModel().selectFirst();
        }else{
            selected_job_cost = tblview_job_costing.getSelectionModel().getSelectedItem();
            String selected_date = selected_job_cost.date;
            ArrayList<Job_Costing> job_costing_by_day = db.get_job_costing_by_date(selected_date);
            double asparagus = 0.0,
                    corn= 0.0,
                    pumpkin= 0.0,
                    ginseng= 0.0,
                    other= 0.0,
                    total = 0.0;
            int counter = 1;
            ArrayList<Job_Costing> all_job_costings = new ArrayList<>();
            for(Job_Costing x : job_costing_by_day){
                asparagus += x.asparagus;
                corn += x.corn;
                pumpkin += x.pumpkin;
                ginseng += x.ginseng;
                other += x.other;
            }
            total = asparagus + corn + pumpkin + ginseng + other;
            selected_job_cost.setAsparagus(asparagus);
            selected_job_cost.setCorn(corn);
            selected_job_cost.setPumpkin(pumpkin);
            selected_job_cost.setGinseng(ginseng);
            selected_job_cost.setOther(other);
            selected_job_cost.setHours(total);
            tblview_job_costing_by_day.getItems().addAll(selected_job_cost);
        }
        tblview_job_costing_by_day.getSelectionModel().setSelectionMode(
                SelectionMode.MULTIPLE);
        stored_job_costings.addAll(tblview_job_costing_by_day.getItems());
        double total_cost = 0.0, total_hours = 0.0;
        for(Job_Costing x : stored_job_costings){
            total_cost += x.cost;
            total_hours += x.hours;
        }
        BigDecimal bd = BigDecimal.valueOf(total_cost);
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        total_cost = bd.doubleValue();
        lbl_JC_cost.setText("$" + total_cost);
        lbl_JC_hours.setText(total_hours + "Hrs");
    }


    @FXML
    void export_employees_to_cropTracker(MouseEvent event) {
        lbl_report_created.setVisible(false);
        Main.createNewScene(event, "Crop_Tracker_Screen.fxml");

    }

    @FXML
    void export_total_work_hours(MouseEvent event) throws IOException, InvalidFormatException {
        lbl_report_created.setVisible(false);
        lbl_form_created.setText("");

        // Create .docx document for the new report being generated
        XWPFDocument document = new XWPFDocument(OPCPackage.open("All_Work_Hours.docx"));

        // Go through the top to find where to populate the date range
        for(XWPFParagraph p : document.getParagraphs()){
            List<XWPFRun> runs = p.getRuns();
            if(runs != null){
                for(XWPFRun r: runs){
                    String text = r.getText(0);
                    if(text != null && text.contains("start_date")){
                        text = text.replace("start_date", start_date_formatted);
                        r.setText(text, 0);
                    }else if(text != null & text.contains("end_date")){
                        text = text.replace("end_date", end_date_formatted);
                        r.setText(text, 0);
                    }
                }
            }
        }

        // Format the date range for file name
        DateTimeFormatter today_format = DateTimeFormatter.ofPattern("MM.dd.yyyy");
        String start_date = current_start_date.format(today_format);
        String end_date = current_end_date.format(today_format);



        lbl_selected_employee.setText("EMPLOYEE ID SELECTED:  ALL");
        lbl_selected_employee.setTextFill(Color.DARKGREEN);



        ArrayList<Work_Hours> all_hours_worked = db.get_all_work_hours_list(current_start_date, current_end_date);
        lbl_no_employee_selected.setText("");


        int counter = 1;
        double total_cost = 0.0, total_hours = 0.0;
        XWPFTable tbl = document.getTableArray(0);
        XWPFTableRow oldRow = tbl.getRow(1);
        CTRow ctrow = null;

        for(Work_Hours x : all_hours_worked) {
            try{
                ctrow = CTRow.Factory.parse(oldRow.getCtRow().newInputStream());
            }catch (XmlException | IOException e) {e.printStackTrace();}
            XWPFTableRow newRow = new XWPFTableRow(ctrow, tbl);
            newRow.getCell(0).setText(Integer.toString(x.employee_id));
            newRow.getCell(1).setText(x.name);
            newRow.getCell(2).setText(Double.toString(x.hours_worked));
            newRow.getCell(3).setText(x.date_worked);
            newRow.getCell(4).setText("$"+Double.toString((x.cost)));
            total_cost += x.cost;
            total_hours += x.hours_worked;
            tbl.addRow(newRow, counter);
            counter++;
        }
        XWPFTableRow final_row = tbl.getRow(counter);
        final_row.getCell(0).setText("GRAND TOTAL: ");
        final_row.getCell(2).setText(total_hours + " Hrs");
        BigDecimal bd = BigDecimal.valueOf(total_cost);
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        total_cost = bd.doubleValue();
        final_row.getCell(4).setText("$"+ total_cost);
        //tbl.addRow(final_row, counter);

        try{
            FileOutputStream out = new FileOutputStream(new File("\\\\Inddc-vm56\\mssqlserver\\FileDBStreamDirectory\\FileDBFileStreamDirectory\\Work_Hours\\total_work_hours_"+start_date+"_to_"+end_date+".docx"));
            document.write(out);
            out.close();
            lbl_report_created.setVisible(true);

        }
        catch(FileNotFoundException f){
            lbl_form_created.setText("FORM COULD NOT BE CREATED FOR ALL WORK HOURS");
        }
        String location_url = "\\\\Inddc-vm56\\mssqlserver\\FileDBStreamDirectory\\FileDBFileStreamDirectory\\Work_Hours\\total_work_hours_"+start_date+"_to_"+end_date+".docx";
        File file = new File(location_url);
        Desktop desktop = Desktop.getDesktop();
        try{
            if(file.exists()){
                desktop.open(file);
            }
            else{
                lbl_form_created.setText("FORM COULD NOT BE OPENED");
            }
        }catch(Exception e){
            System.out.println("Can't open form");
        }
    }


    @FXML
    void export_work_hours_by_employee(MouseEvent event) throws IOException, InvalidFormatException, XmlException {
        lbl_report_created.setVisible(false);
        lbl_form_created.setText("");

        // Create .docx document for the new report being generated
        XWPFDocument document = new XWPFDocument(OPCPackage.open("Work_Hours_By_Employee.docx"));

        // Go through the top to find where to populate the date range
        for(XWPFParagraph p : document.getParagraphs()){
            List<XWPFRun> runs = p.getRuns();
            if(runs != null){
                for(XWPFRun r: runs){
                    String text = r.getText(0);
                    if(text != null && text.contains("start_date")){
                        text = text.replace("start_date", start_date_formatted);
                        r.setText(text, 0);
                    }else if(text != null & text.contains("end_date")){
                        text = text.replace("end_date", end_date_formatted);
                        r.setText(text, 0);
                    }
                }
            }
        }

        // Format the date range for file name
        DateTimeFormatter today_format = DateTimeFormatter.ofPattern("MM.dd.yyyy");
        String start_date = current_start_date.format(today_format);
        String end_date = current_end_date.format(today_format);

        int emp_id =0;
        try{
            emp_id = Integer.parseInt(employee_id);
            if(emp_id < db.get_largest_employee_id() && emp_id > 0){
                lbl_selected_employee.setText("EMPLOYEE ID SELECTED:  #"+ emp_id);
                lbl_selected_employee.setTextFill(Color.DARKGREEN);
            }else{
                lbl_selected_employee.setText("THAT NUMBER IS NOT VALID: #" + employee_id);
                lbl_selected_employee.setTextFill(Color.DARKRED);
            }
        }catch(NumberFormatException np){
            lbl_selected_employee.setText("NO EMPLOYEE SELECTED");
            lbl_selected_employee.setTextFill(Color.DARKRED);
        }

        ArrayList<Work_Hours> employee_hours_worked = new ArrayList<>();
        lbl_no_employee_selected.setText("");

        employee_hours_worked = db.get_work_hours_by_id(emp_id, current_start_date, current_end_date);

        int counter = 1;
        double total_cost = 0.0, total_hours = 0.0;
        XWPFTable tbl = document.getTableArray(0);
        XWPFTableRow oldRow = tbl.getRow(1);
        CTRow ctrow = null;

        for(Work_Hours x : employee_hours_worked) {
            try{
                ctrow = CTRow.Factory.parse(oldRow.getCtRow().newInputStream());
            }catch (XmlException | IOException e) {e.printStackTrace();}
            XWPFTableRow newRow = new XWPFTableRow(ctrow, tbl);
            newRow.getCell(0).setText(Integer.toString(x.employee_id));
            newRow.getCell(1).setText(x.name);
            newRow.getCell(2).setText(Double.toString(x.hours_worked));
            newRow.getCell(3).setText(x.date_worked);
            newRow.getCell(4).setText("$"+Double.toString((x.cost)));
            total_cost += x.cost;
            total_hours += x.hours_worked;
            tbl.addRow(newRow, counter);
            counter++;
        }
        XWPFTableRow final_row = tbl.getRow(counter);
        final_row.getCell(0).setText("GRAND TOTAL: ");
        final_row.getCell(2).setText(total_hours + " Hrs");
        BigDecimal bd = BigDecimal.valueOf(total_cost);
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        total_cost = bd.doubleValue();
        final_row.getCell(4).setText("$"+ total_cost);
        //tbl.addRow(final_row, counter);

        try{
            FileOutputStream out = new FileOutputStream(new File("\\\\Inddc-vm56\\mssqlserver\\FileDBStreamDirectory\\FileDBFileStreamDirectory\\Work_Hours\\Employee_"+employee_id+"_work_hours_"+start_date+"_to_"+end_date+".docx"));
            document.write(out);
            out.close();
            lbl_report_created.setVisible(true);
        }
        catch(FileNotFoundException f){
            lbl_form_created.setText("FORM COULD NOT BE CREATED FOR EMPLOYEE #:  "+employee_id);
        }

        String location_url = "\\\\Inddc-vm56\\mssqlserver\\FileDBStreamDirectory\\FileDBFileStreamDirectory\\Work_Hours\\Employee_"+employee_id+"_work_hours_"+start_date+"_to_"+end_date+".docx";
        File file = new File(location_url);
        Desktop desktop = Desktop.getDesktop();
        try{
            if(file.exists()){
                desktop.open(file);
            }
            else{
                lbl_form_created.setText("FORM COULD NOT BE OPENED");
            }
        }catch(Exception e){
            System.out.println("Can't open form");
        }
    }




    @FXML
    void export_job_costing(MouseEvent event) throws InvalidFormatException, IOException {
        lbl_report_created.setVisible(false);
        lbl_form_created.setText("");

        // Create .docx document for the new report being generated
        XWPFDocument document = new XWPFDocument(OPCPackage.open("job_costing.docx"));

        // Go through the top to find where to populate the date range
        for(XWPFParagraph p : document.getParagraphs()){
            List<XWPFRun> runs = p.getRuns();
            if(runs != null){
                for(XWPFRun r: runs){
                    String text = r.getText(0);
                    if(text != null && text.contains("start_date")){
                        text = text.replace("start_date", start_date_formatted);
                        r.setText(text, 0);
                    }else if(text != null & text.contains("end_date")){
                        text = text.replace("end_date", end_date_formatted);
                        r.setText(text, 0);
                    }
                }
            }
        }

        // Format the date range for file name
        DateTimeFormatter today_format = DateTimeFormatter.ofPattern("MM.dd.yyyy");
        String start_date = current_start_date.format(today_format);
        String end_date = current_end_date.format(today_format);


        lbl_selected_employee.setText("EMPLOYEE ID SELECTED:  ALL");
        lbl_selected_employee.setTextFill(Color.DARKGREEN);

        ArrayList<Job_Costing> all_job_costings = db.get_job_costing();
        lbl_no_employee_selected.setText("");


        int counter = 1;
        double total_cost = 0.0, total_hours = 0.0;


        XWPFTable tbl = document.getTableArray(0);
        XWPFTableRow oldRow = tbl.getRow(1);
        CTRow ctrow = null;

        for(Job_Costing x : all_job_costings) {
            try{
                ctrow = CTRow.Factory.parse(oldRow.getCtRow().newInputStream());
            }catch (XmlException | IOException e) {e.printStackTrace();}
            XWPFTableRow newRow = new XWPFTableRow(ctrow, tbl);
            newRow.getCell(0).setText(x.job_name);
            newRow.getCell(1).setText(Double.toString(x.hours));
            newRow.getCell(2).setText("$"+Double.toString((x.cost)));
            total_cost += x.cost;
            total_hours += x.hours;
            tbl.addRow(newRow, counter);
            counter++;
        }

        XWPFTableRow final_row = tbl.getRow(counter);
        final_row.getCell(0).setText("GRAND TOTAL: ");
        final_row.getCell(1).setText(total_hours + " Hrs");
        BigDecimal bd = BigDecimal.valueOf(total_cost);
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        total_cost = bd.doubleValue();
        final_row.getCell(2).setText("$"+ total_cost);
        //tbl.addRow(final_row, counter);


        try{
            FileOutputStream out = new FileOutputStream(new File("\\\\Inddc-vm56\\mssqlserver\\FileDBStreamDirectory\\FileDBFileStreamDirectory\\Job_Costing\\total_job_costing_"+start_date+"_to_"+end_date+".docx"));
            document.write(out);
            out.close();
            lbl_report_created.setVisible(true);
        }
        catch(FileNotFoundException f){
            lbl_form_created.setText("FORM COULD NOT BE CREATED FOR ALL JOB COSTINGS");
        }
        String location_url = "\\\\Inddc-vm56\\mssqlserver\\FileDBStreamDirectory\\FileDBFileStreamDirectory\\Job_Costing\\total_job_costing_"+start_date+"_to_"+end_date+".docx";
        File file = new File(location_url);
        Desktop desktop = Desktop.getDesktop();
        try{
            if(file.exists()){
                desktop.open(file);
            }
            else{
                lbl_form_created.setText("FORM COULD NOT BE OPENED");
            }
        }catch(Exception e){
            System.out.println("Can't open form");
        }
    }

    @FXML
    void export_job_costing_by_day(MouseEvent event) throws InvalidFormatException, IOException {
        lbl_report_created.setVisible(false);
        lbl_form_created.setText("");

        Job_Costing selected_job_cost = null;
        ArrayList<Job_Costing> stored_job_costings = new ArrayList<>();
        if(tblview_job_costing.getSelectionModel().getSelectedItem() == null){
            tblview_job_costing.getSelectionModel().selectFirst();
        }else {
            selected_job_cost = tblview_job_costing.getSelectionModel().getSelectedItem();
            String selected_date = selected_job_cost.date;
        }
        tblview_job_costing_by_day.getSelectionModel().setSelectionMode(
                SelectionMode.MULTIPLE);
        double total_cost = 0.0, total_hours = 0.0;


        // Create .docx document for the new report being generated
        XWPFDocument document = new XWPFDocument(OPCPackage.open("job_costing_by_day.docx"));


        lbl_selected_employee.setText("EMPLOYEE ID SELECTED:  ALL");
        lbl_selected_employee.setTextFill(Color.DARKGREEN);

        stored_job_costings.addAll(tblview_job_costing_by_day.getItems());
        lbl_no_employee_selected.setText("");

        int counter = 2;

        XWPFTable tbl = document.getTableArray(0);
        XWPFTableRow oldRow = tbl.getRow(2);
        CTRow ctrow = null;

        for(Job_Costing x : stored_job_costings) {
            try{
                ctrow = CTRow.Factory.parse(oldRow.getCtRow().newInputStream());
            }catch (XmlException | IOException e) {e.printStackTrace();}
            XWPFTableRow newRow = new XWPFTableRow(ctrow, tbl);
            newRow.getCell(0).setText(x.date);
            newRow.getCell(1).setText(Double.toString(x.asparagus));
            newRow.getCell(2).setText(Double.toString(x.corn));
            newRow.getCell(3).setText(Double.toString(x.pumpkin));
            newRow.getCell(4).setText(Double.toString(x.ginseng));
            newRow.getCell(5).setText(Double.toString(x.other));
            newRow.getCell(6).setText(Double.toString(x.hours));
            total_cost += x.cost;
            total_hours += x.hours;
            tbl.addRow(newRow, counter);
            counter++;
        }

        XWPFTableRow final_row = tbl.getRow(counter);
        final_row.getCell(0).setText("GRAND TOTAL: ");
        final_row.getCell(5).setText(total_hours + " Hrs");
        BigDecimal bd = BigDecimal.valueOf(total_cost);
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        total_cost = bd.doubleValue();
        final_row.getCell(6).setText("$"+ total_cost);

        DateTimeFormatter today_format = DateTimeFormatter.ofPattern("MM.dd.yyyy");
        DateTimeFormatter today_time = DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT);
        String start_date = LocalDate.now().format(today_format)+"_"+ LocalTime.now().format(today_time);
        start_date = start_date.replace(":", ".");
        start_date = start_date.replace(" ", "");


        try{
            FileOutputStream out = new FileOutputStream(new File("\\\\Inddc-vm56\\mssqlserver\\FileDBStreamDirectory\\FileDBFileStreamDirectory\\Job_Costing\\job_costing_by_day_"+start_date+".docx"));
            document.write(out);
            out.close();
            lbl_report_created.setVisible(true);
        }
        catch(FileNotFoundException f){
            lbl_form_created.setText("FORM COULD NOT BE CREATED FOR ALL JOB COSTINGS");
        }

        tblview_job_costing_by_day.getItems().clear();
        lbl_JC_cost.setText("");
        lbl_JC_hours.setText("");

        String location_url = "\\\\Inddc-vm56\\mssqlserver\\FileDBStreamDirectory\\FileDBFileStreamDirectory\\Job_Costing\\job_costing_by_day_"+start_date+".docx";
        File file = new File(location_url);
        Desktop desktop = Desktop.getDesktop();
        try{
            if(file.exists()){
                desktop.open(file);
            }
            else{
                lbl_form_created.setText("FORM COULD NOT BE OPENED");
            }
        }catch(Exception e){
            System.out.println("Can't open form");
        }

    }


}

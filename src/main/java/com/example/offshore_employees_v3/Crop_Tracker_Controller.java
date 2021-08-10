package com.example.offshore_employees_v3;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class Crop_Tracker_Controller {

    @FXML
    private Button btn_home;

    @FXML
    private Button btn_quit;

    @FXML
    private TableView<Large_Employee> tblview_view_crop_tracker;

    @FXML
    private TableColumn<Large_Employee, Integer> col_empid_view;

    @FXML
    private TableColumn<?, ?> col_name_view;

    @FXML
    private Button btn_view_crop_tracker;

    @FXML
    private TextArea txtarea_crop_tracker_info;

    @FXML
    private TableView<Large_Employee> tblview_edit_crop_tracker;

    @FXML
    private TableColumn<Large_Employee, Integer> col_empid_edit;

    @FXML
    private TableColumn<?, ?> col_name_edit;

    @FXML
    private Button btn_edit_crop_tracker;

    @FXML
    private Button btn_export_croptracker;

    @FXML
    private Label lbl_form_exported;

    Access_Full_Employee_DB db = new Access_Full_Employee_DB();

    @FXML
    void edit_crop_tracker(MouseEvent event) throws IOException {
        Main.current_large_employee = tblview_edit_crop_tracker.getSelectionModel().getSelectedItem();
        Main.createNewScene(event, "Employee_List_Screen.fxml");

    }

    @FXML
    void go_home(MouseEvent event) throws IOException {
        Main.createNewScene(event, "Employee_Dashboard.fxml");
    }


    @FXML
    void quit_program(MouseEvent event) {
        Main.exit_program(event);
    }

    @FXML
    void view_crop_tracker(MouseEvent event) {
        lbl_form_exported.setVisible(false);
        txtarea_crop_tracker_info.clear();
        Large_Employee selected_to_view = tblview_view_crop_tracker.getSelectionModel().getSelectedItem();
        Main.current_large_employee = tblview_view_crop_tracker.getSelectionModel().getSelectedItem();
        txtarea_crop_tracker_info.appendText(selected_to_view.toString());

    }

    void populate_view_employees_tableview(){
        col_empid_view.setCellValueFactory(new PropertyValueFactory<>("employeeID"));
        col_name_view.setCellValueFactory(new PropertyValueFactory<>("name"));
        tblview_view_crop_tracker.getSortOrder().add(col_empid_view);
        tblview_view_crop_tracker.getItems().addAll(db.get_employee_info_list_using_view());

    }

    void populate_edit_employees_tableview(){
        col_empid_edit.setCellValueFactory(new PropertyValueFactory<>("employeeID"));
        col_name_edit.setCellValueFactory(new PropertyValueFactory<>("name"));
        tblview_edit_crop_tracker.getSortOrder().add(col_empid_edit);
        tblview_edit_crop_tracker.getItems().addAll(db.get_employee_info_list_using_view());
    }

    @FXML
    void initialize(){
        populate_edit_employees_tableview();
        populate_view_employees_tableview();
        lbl_form_exported.setVisible(false);
    }


    @FXML
    void export_crop_tracker(MouseEvent event) throws IOException {
        DateTimeFormatter today_format = DateTimeFormatter.ofPattern("MM.dd.yyyy");
        LocalDate current_start_date = LocalDate.now();
        DateTimeFormatter today_time = DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT);
        String start_date = current_start_date.format(today_format)+"_"+ LocalTime.now().format(today_time);
        start_date = start_date.replace(":", ".");
        start_date = start_date.replace(" ", "");


        // Create a new excel workbook for the report
        XSSFWorkbook workbook = new XSSFWorkbook();


        // It does not matter what this file is names as it is temporary
        XSSFSheet spreadsheet = workbook.createSheet("Total_Work_Hours.xlsx");

        CellStyle cellstyle = spreadsheet.getWorkbook().createCellStyle();
        cellstyle.setWrapText(true);
        cellstyle.setAlignment(HorizontalAlignment.CENTER);


        CellStyle headerstyle = spreadsheet.getWorkbook().createCellStyle();
        headerstyle.setBorderBottom(BorderStyle.THICK);
        headerstyle.setFillBackgroundColor(IndexedColors.GREY_40_PERCENT.getIndex());




        // This is the header row of the excel sheet being created
        XSSFRow header_row = spreadsheet.createRow(0);
        header_row.createCell(0).setCellValue(("Employee ID"));
        header_row.createCell(1).setCellValue(("First Name"));
        header_row.createCell(2).setCellValue(("Last Name"));
        header_row.createCell(3).setCellValue(("Telephone"));
        header_row.createCell(4).setCellValue(("Email"));
        header_row.createCell(5).setCellValue(("Country"));
        header_row.createCell(6).setCellValue(("Region"));
        header_row.createCell(7).setCellValue(("City"));
        header_row.createCell(8).setCellValue(("Address 1"));
        header_row.createCell(9).setCellValue(("Address 2"));
        header_row.createCell(10).setCellValue(("Pay Schedule"));
        header_row.createCell(11).setCellValue(("Work Crew"));
        header_row.createCell(12).setCellValue(("Contact First Name"));
        header_row.createCell(13).setCellValue(("Contact Last Name"));
        header_row.createCell(14).setCellValue(("Contact Telephone"));
        header_row.createCell(15).setCellValue(("Contact Email"));
        header_row.createCell(16).setCellValue(("Contact Country"));
        header_row.createCell(17).setCellValue(("Contact Region"));
        header_row.createCell(18).setCellValue(("Contact City"));
        header_row.createCell(19).setCellValue(("Contact Address"));
        header_row.createCell(20).setCellValue(("Additional Information"));



        for(int i =0; i<21;i++){
            spreadsheet.autoSizeColumn(i);
            header_row.getCell(i).setCellStyle(headerstyle);
        }


        // Instantiate the first row for the data being inputted into the excel report
        XSSFRow row;

        // The data in the CropTracker needs to be mapped so it can be placed in cells correctly
        Map<String, Large_Employee[]> employee_data = new TreeMap<String, Large_Employee[]>();

        // This array list will hold all the data from the view being called from the database
        ArrayList<Large_Employee> all_croptracker_employees = db.get_employee_info_list_using_view();


        // For loop to iterate through the ArrayList containing the databases information
        for(int i = 0; i< all_croptracker_employees.size();i++){
            // (in for loop): the integer key needs to be casted to a string then mapped to data
            String i_string = Integer.toString(i);
            employee_data.put(i_string, new Large_Employee[]{all_croptracker_employees.get(i)});
        }


        // Sets the keyid in the Mapped data to the newly created String above
        Set<String> keyid = employee_data.keySet();

        // Should start at row:1 because there is already a header row on row 0
        int rowid=1;

        // Walking through all the String keys created to get the Mapped data
        for(String key : keyid){
            // Begins inputting data on the next row (rowid++) by incrementing
            row = spreadsheet.createRow(rowid++);

            Large_Employee[] total_employees = employee_data.get(key);

            for(Large_Employee x : total_employees){
                // cellid needs to be declared here so it can keep getting reset to 0 at end of row
                int cellid = 0;
                // Inputs data to the next cell by incrementing (cellid++)
                XSSFCell cell = row.createCell(cellid++);


                cell.setCellValue((int) x.employeeID);
                cell = row.createCell(cellid++);


                cell.setCellValue((String) x.givenName);
                cell = row.createCell(cellid++);

                cell.setCellValue((String) x.surName);
                cell = row.createCell(cellid++);

                cell.setCellValue((String) x.txtMobile);
                cell = row.createCell(cellid++);

                cell.setCellValue((String) x.txtEmail);
                cell = row.createCell(cellid++);

                cell.setCellValue((String) null);
                cell = row.createCell(cellid++);

                cell.setCellValue((String) null);
                cell = row.createCell(cellid++);

                cell.setCellValue((String) x.city);
                cell = row.createCell(cellid++);

                cell.setCellValue((String) x.houseAddress);
                cell = row.createCell(cellid++);

                cell.setCellValue((String) null);
                cell = row.createCell(cellid++);

                cell.setCellValue((String) "Default");
                cell = row.createCell(cellid++);

                cell.setCellValue((String) x.houseName);
                cell = row.createCell(cellid++);

                cell.setCellValue((String) x.mexContactName);
                cell = row.createCell(cellid++);

                cell.setCellValue((String) null);
                cell = row.createCell(cellid++);

                cell.setCellValue((String) x.mexPhone);
                cell = row.createCell(cellid++);

                cell.setCellValue((String) x.txtEmail);
                cell = row.createCell(cellid++);

                cell.setCellValue((String) null);
                cell = row.createCell(cellid++);

                cell.setCellValue((String) x.mexNeighbourhood);
                cell = row.createCell(cellid++);

                cell.setCellValue((String) x.mexMunicipality);
                cell = row.createCell(cellid++);

                cell.setCellValue((String) x.mexStreetAddress);
                cell = row.createCell(cellid++);

                cell.setCellValue((String) x.txtNotes);

            }
        }

        // This stores the file into the database file system under the File directory: Work_Hours
        FileOutputStream out = new FileOutputStream(new File("\\\\Inddc-vm56\\mssqlserver\\FileDBStreamDirectory\\FileDBFileStreamDirectory\\CropTracker\\Crop_Tracker"+start_date+".xlsx"));
        workbook.write(out);
        out.close();

        lbl_form_exported.setVisible(true);

    }
}

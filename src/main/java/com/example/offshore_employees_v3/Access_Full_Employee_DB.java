package com.example.offshore_employees_v3;


import java.io.UnsupportedEncodingException;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Access_Full_Employee_DB {
    String DB_URL = "jdbc:jtds:sqlserver://INDDC-VM56/Migrant_Employees;integratedSecurity=true;responseBuffering=adaptive";
    String username = Main.current_user;
    String password = Main.current_pass;
    String driver = "net.sourceforge.jtds.jdbc.Driver";
    Connection conn = null;
    Statement stmt = null;
    PreparedStatement ps = null;



    // This method will store employee's attachments to the database.
    public void insert_to_attachments(int employeeID,
                                        String workPermit,
                                        String employmentAgreement,
                                        String benefitCard,
                                        String withholdAgreement) throws SQLException {
        try {
            Class.forName(driver);

            conn = DriverManager.getConnection(DB_URL);


            stmt = conn.createStatement();
            String sql = "INSERT INTO attachments VALUES('"
                    + employeeID
                    + "','"
                    + workPermit
                    + "','"
                    + employmentAgreement
                    + "','"
                    + benefitCard
                    + "','"
                    + withholdAgreement
                    + "');";
            stmt.executeUpdate(sql);
            stmt.close();
            conn.close();
        }catch (SQLException | ClassNotFoundException se){
            se.printStackTrace();
        }
    }
    public void update_table(String sql){
        try {
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(DB_URL, username, password);
            stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            stmt.close();
            conn.close();
        }catch (SQLException | ClassNotFoundException se){
            se.printStackTrace();
        }

    }

    // This method will store employee's banking information to the database.
    public void insert_to_bank_info(int employeeID,
                                      String bankTransitNum,
                                      String bankAccountNum) throws SQLException {
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(DB_URL, username, password);
            stmt = conn.createStatement();
            String sql = "INSERT INTO bank_info VALUES('"
                    + employeeID
                    + "','"
                    + bankTransitNum
                    + "','"
                    + bankAccountNum
                    + "');";
            stmt.executeUpdate(sql);
            stmt.close();
            conn.close();
        }catch (SQLException | ClassNotFoundException se){
            se.printStackTrace();
        }
    }

    // This method will store an employee's contact info to the database.
    public void insert_to_contact_info(int employeeID,
                                        String txtMobile,
                                        String txtEmail,
                                        String emailPassword,
                                        String corpCellType,
                                        String corpCellNum) throws SQLException {
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(DB_URL, username, password);
            stmt = conn.createStatement();
            String sql = "INSERT INTO contact_info VALUES('"
                    + employeeID
                    + "','"
                    + txtMobile
                    + "','"
                    + txtEmail
                    + "','"
                    + emailPassword
                    + "','"
                    + corpCellType
                    + "','"
                    + corpCellNum
                    + "');";
            stmt.executeUpdate(sql);
            stmt.close();
            conn.close();
        }catch (SQLException | ClassNotFoundException se){
            se.printStackTrace();
        }
    }

    // This method will store an employee's covid info to the database.
    public void insert_to_covid_info(int employeeID,
                                       String quarantineLocation,
                                       String dose1Attachment,
                                       String dose2Attachment,
                                       String dose1RecordDate,
                                       String dose2RecordDate) throws SQLException {
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(DB_URL, username, password);
            stmt = conn.createStatement();
            String sql = "INSERT INTO covid_info VALUES('"
                    + employeeID
                    + "','"
                    + quarantineLocation
                    + "','"
                    + dose1Attachment
                    + "','"
                    + dose2Attachment
                    + "','"
                    + dose1RecordDate
                    + "','"
                    + dose2RecordDate
                    + "');";
            stmt.executeUpdate(sql);
            stmt.close();
            conn.close();
        }catch (SQLException | ClassNotFoundException se){
            se.printStackTrace();
        }
    }

    // This method will store an employee's covid info to the database.
    public void insert_to_covid_info_without_attachments(int employeeID,
                                     String quarantineLocation,
                                     String dose1RecordDate,
                                     String dose2RecordDate) throws SQLException {
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(DB_URL, username, password);
            stmt = conn.createStatement();
            String blank_covid_attach1 = "";
            String blank_covid_attach2 = "";
            String sql = "INSERT INTO covid_info VALUES('"
                    + employeeID
                    + "','"
                    + quarantineLocation
                    + "','"
                    + blank_covid_attach1
                    + "','"
                    + blank_covid_attach2
                    + "','"
                    + dose1RecordDate
                    + "','"
                    + dose2RecordDate
                    + "');";
            stmt.executeUpdate(sql);
            stmt.close();
            conn.close();
        }catch (SQLException | ClassNotFoundException se){
            se.printStackTrace();
        }
    }


    // This method will add a new employee to the database.
    public void insert_to_employee_info(int employeeID,
                                        String mexCode,
                                        String givenName,
                                        String empSurname,
                                        String dateOfBirth,
                                        String passportNum,
                                        String passportExp,
                                        String SII,
                                        String visaNumber,
                                        String nick_name,
                                        String txtNotes) throws SQLException {
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(DB_URL, username, password);
            stmt = conn.createStatement();
            String sql = "INSERT INTO employee_info VALUES('"
                    + employeeID
                    + "','"
                    + mexCode
                    + "','"
                    + givenName
                    + "','"
                    + empSurname
                    + "','"
                    + dateOfBirth
                    + "','"
                    + passportNum
                    + "','"
                    + passportExp
                    + "','"
                    + SII
                    + "','"
                    + visaNumber
                    + "','"
                    + nick_name
                    + "','"
                    + txtNotes
                    + "');";
            stmt.executeUpdate(sql);
            stmt.close();
            conn.close();
        }catch (SQLException | ClassNotFoundException se){
            se.printStackTrace();
        }
    }


    // This method will store an employee's employment info to the database.
    public void insert_to_employment_info(int employeeID,
                                     String hireDate,
                                     String arrivalDate,
                                     String departureDate,
                                     String payRate,
                                     String stillActive) throws SQLException {
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(DB_URL, username, password);
            stmt = conn.createStatement();
            payRate = "$"+payRate;
            String sql = "INSERT INTO employment_info VALUES('"
                    + employeeID
                    + "','"
                    + hireDate
                    + "','"
                    + arrivalDate
                    + "','"
                    + departureDate
                    + "','"
                    + payRate
                    + "','"
                    + stillActive
                    + "');";
            stmt.executeUpdate(sql);
            stmt.close();
            conn.close();
        }catch (SQLException | ClassNotFoundException se){
            se.printStackTrace();
        }
    }


    // This method will store employee's banking information to the database.
    public void insert_to_health_info(int employeeID,
                                    String healthCardnum,
                                    String medInsurance) throws SQLException {
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(DB_URL, username, password);
            stmt = conn.createStatement();
            String sql = "INSERT INTO health_information VALUES('"
                    + employeeID
                    + "','"
                    + healthCardnum
                    + "','"
                    + medInsurance
                    + "');";
            stmt.executeUpdate(sql);
            stmt.close();
            conn.close();
        }catch (SQLException | ClassNotFoundException se){
            se.printStackTrace();
        }
    }

    // This method will store employee's attachments to the database.
    public void insert_to_housing_info(int employeeID,
                                      String houseNum,
                                      String houseName,
                                      String houseAddress,
                                      String bedID) throws SQLException {
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(DB_URL, username, password);
            stmt = conn.createStatement();
            String sql = "INSERT INTO housing_info VALUES('"
                    + employeeID
                    + "','"
                    + houseNum
                    + "','"
                    + houseName
                    + "','"
                    + houseAddress
                    + "','"
                    + bedID
                    + "');";
            stmt.executeUpdate(sql);
            stmt.close();
            conn.close();
        }catch (SQLException | ClassNotFoundException se){
            se.printStackTrace();
        }
    }
    // This method will store the employee's mex contact info to the database.
    public void insert_to_mex_contact_info(int employeeID,
                                        String mexContactName,
                                        String mexMunicipality,
                                        String mexPhone,
                                        String mexNeighborhood,
                                        String mexPostalCode,
                                        String mexStreetAddress,
                                        String mexState) throws SQLException {
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(DB_URL, username, password);
            stmt = conn.createStatement();
            String sql = "INSERT INTO mex_contact_info VALUES('"
                    + employeeID
                    + "','"
                    + mexContactName
                    + "','"
                    + mexMunicipality
                    + "','"
                    + mexPhone
                    + "','"
                    + mexNeighborhood
                    + "','"
                    + mexPostalCode
                    + "','"
                    + mexStreetAddress
                    + "','"
                    + mexState
                    + "');";
            stmt.executeUpdate(sql);
            stmt.close();
            conn.close();
        }catch (SQLException | ClassNotFoundException se){
            se.printStackTrace();
        }
    }

    // This method will store worked hours to the database.
    public void insert_to_work_hours(int employeeID,
                                      int hoursID,
                                      String hoursWorked,
                                      String dateWorked,
                                      String jobCode,
                                     String jobName) throws SQLException {
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(DB_URL, username, password);
            stmt = conn.createStatement();
            String sql = "INSERT INTO work_hours VALUES('"
                    + employeeID
                    + "','"
                    + hoursID
                    + "','"
                    + hoursWorked
                    + "','"
                    + dateWorked
                    + "','"
                    + jobCode
                    + "','"
                    + jobName
                    + "');";
            stmt.executeUpdate(sql);
            stmt.close();
            conn.close();
        }catch (SQLException | ClassNotFoundException se){
            se.printStackTrace();
        }
    }


    // Searches the database for a list of employees containing this number in their Scotlynn ID
    public ArrayList<Large_Employee> search_for_employee_by_scotlynnID(int scot_id){
        ArrayList<Integer> employees_by_name = new ArrayList();
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(DB_URL);
            stmt = conn.createStatement();
            String sql =
                    "SELECT max_empID FROM TBLVIEW_EMPLOYEE_LIST WHERE max_empID like '"
                            + "%"+ scot_id +"%"
                            + "'";
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                employees_by_name.add(rs.getInt(1));
            }
            stmt.close();
            conn.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        ArrayList<Large_Employee> employee_list = new ArrayList();
        for(Integer x : employees_by_name){
            employee_list.add(get_employee_info_by_id_using_view(x));
        }
        return employee_list;
    }

    // Searches the database for a list of employees containing this number in their Scotlynn ID
    public int search_for_one_employee_with_scot_id(int scot_id){
        int employees_by_name = 0;
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(DB_URL);
            stmt = conn.createStatement();
            String sql =
                    "SELECT * FROM TBLVIEW_EMPLOYEE_LIST WHERE max_empID like '"
                            + "%"+ scot_id +"%"
                            + "'";
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                employees_by_name = rs.getInt("max_empID");
            }
            stmt.close();
            conn.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return employees_by_name;
    }

    // Searches the database for a list of employees containing these characters in their mex_code
    public ArrayList<Large_Employee> search_for_employee_by_mex_code(String mexCode){
        ArrayList<Integer> employees_by_name = new ArrayList();
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(DB_URL, username, password);
            stmt = conn.createStatement();

            String sql =
                    "SELECT max_empID FROM TBLVIEW_EMPLOYEE_LIST WHERE UPPER(MEX_EMPLOYMENT_CODE) like '"
                            + "%"+ mexCode +"%"
                            + "'";
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                employees_by_name.add(rs.getInt(1));
            }
            stmt.close();
            conn.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        ArrayList<Large_Employee> employee_list = new ArrayList();
        for(Integer x : employees_by_name){
            employee_list.add(get_employee_info_by_id_using_view(x));
        }
        return employee_list;
    }

    // Searches the database for a list of employees containing these characters in their job_code
    public ArrayList<Large_Employee> search_for_employee_by_job_name(String jobname){
        ArrayList<Integer> employees_by_name = new ArrayList();
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(DB_URL, username, password);
            stmt = conn.createStatement();

            String sql =
                    "SELECT max_empID FROM TBLVIEW_EMPLOYEE_LIST WHERE UPPER(max_job_name) LIKE '"
                          + "%"  + jobname +"%"
                            + "'";
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                employees_by_name.add(rs.getInt(1));
            }
            stmt.close();
            conn.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        ArrayList<Large_Employee> employee_list = new ArrayList();
        for(Integer x : employees_by_name){
            employee_list.add(get_employee_info_by_id_using_view(x));
        }
        return employee_list;
    }

    // Searches the database for a list of employees containing these characters in their name
    public ArrayList<Large_Employee> search_for_employee_by_name(String name){
        ArrayList<Integer> employees_by_name = new ArrayList();
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(DB_URL, username, password);
            stmt = conn.createStatement();
            String sql =
                    "SELECT max_empID FROM TBLVIEW_EMPLOYEE_LIST WHERE UPPER(GIVEN_NAME) like '"
                            + "%"+ name +"%"
                            + "'OR UPPER(SURNAME) like'"
                            + "%"+ name +"%"
                            + "'";
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                employees_by_name.add(rs.getInt(1));   //Getting the Employee ID from this result set
            }
            stmt.close();
            conn.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        ArrayList<Large_Employee> employee_list = new ArrayList();
        for(Integer x : employees_by_name){
            employee_list.add(get_employee_info_by_id_using_view(x));                 //Using a different method to get info
        }
        return employee_list;
    }



    public ArrayList<Large_Employee> get_tblview_employee_search_list(){
        ArrayList<Large_Employee> employee_info_list = new ArrayList<>();
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(DB_URL, username, password);
            stmt = conn.createStatement();
            String sql = "SELECT distinct * FROM TBLVIEW_EMPLOYEE_LIST";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                employee_info_list.add(new Large_Employee(
                        rs.getString("MEX_EMPLOYMENT_CODE"),           //mex_code
                        rs.getString("GIVEN_NAME"),        //given_name
                        rs.getString("SURNAME"),        //surname
                        rs.getInt("max_empID"),           //employee_id
                        rs.getString("max_job_name")        //most_frequent_job_name
                ));
            }
            stmt.close();
            conn.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return employee_info_list;
    }

    public ArrayList<String> get_house_id_numbers(){
        ArrayList<String> house_id_list = new ArrayList<>();
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(DB_URL, username, password);
            stmt = conn.createStatement();
            String sql = "SELECT DISTINCT(HOUSE_NUM) FROM COVID_SCREENING_VIEW";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                house_id_list.add(
                        rs.getString("HOUSE_NUM")
                );
            }
            stmt.close();
            conn.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return house_id_list;
    }


    public ArrayList<Large_Employee> get_covid_screening_list(String houseID){
        ArrayList<Large_Employee> employee_info_list = new ArrayList<>();
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(DB_URL, username, password);
            stmt = conn.createStatement();
            String sql = "SELECT * FROM COVID_SCREENING_VIEW WHERE HOUSE_NUM='" + houseID + "'";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                employee_info_list.add(new Large_Employee(
                        rs.getString("MEX_EMPLOYMENT_CODE"),
                        rs.getString("GIVEN_NAME"),
                        rs.getString("SURNAME"),
                        rs.getString("BED_ID"),
                        rs.getInt("EMPLOYEE_ID"),
                        rs.getString("HOUSE_NUM")
                ));
            }
            stmt.close();
            conn.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return employee_info_list;
    }


    public ArrayList<Large_Employee> get_employee_info_list_using_view(){
        ArrayList<Large_Employee> employee_info_list = new ArrayList<>();
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(DB_URL, username, password);
            stmt = conn.createStatement();
            String sql = "SELECT * FROM EMPLOYEE_FULL_INFO";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                employee_info_list.add(new Large_Employee(
                        rs.getInt("EMPLOYEE_ID"),        //employee_id
                        rs.getString("HIRE_DATE"),        //hire_date
                        rs.getString("RATE"),       //rate
                        rs.getString("ACTIVE"),        //active
                        rs.getString("MEX_EMPLOYMENT_CODE"),        //mex_employment_code
                        rs.getString("BANK_TRANSIT_NUMBER"),         //bank_transit_number
                        rs.getString("BANK_ACCOUNT_NUMBER"),       //bank_account_number
                        rs.getString("GIVEN_NAME"),        //given_name
                        rs.getString("SURNAME"),        //surname
                        rs.getString("DOB"),        //DOB
                        rs.getString("PASSPORT_NUMBER"),        //passport_number
                        rs.getString("PASSPORT_EXP"),        //passport_exp
                        rs.getString("SSN"),       //ssn
                        rs.getString("VISA_NUMBER"),       //visa_number
                        rs.getString("HEALTH_CARD_NUMBER"),       //health_card_num
                        rs.getString("ARRIVAL_DATE"),       //arrival_date
                        rs.getString("DEPARTURE_DATE"),        //departure_date
                        rs.getString("EMAIL"),        //email
                        rs.getString("MED_INSURANCE"),         //med_insurance
                        rs.getString("MOBILE_PHONE"),        //mobile_phone
                        rs.getString("NICKNAME"),       //nickname
                        rs.getString("NOTES"),       //notes
                        rs.getString("BED_ID"),        //bed_id
                        rs.getString("HOUSE_NUM"),        //house_num
                        rs.getString("HOUSE_NAME"),        //house_name
                        rs.getString("HOUSE_ADDRESS"),        //house_address
                        rs.getString("WORK_PERMIT"),           //work_permit
                        rs.getString("BENEFIT_CARD"),        //benefit_card
                        rs.getString("EMPLOYMENT_CONTRACT"),           //employment_contract
                        rs.getString("WITHHOLD_AGREEMENT"),        //withhold_agreement
                        rs.getString("QUARANTINE_LOCATION"),        //quarantine_location
                        rs.getString("DEFAULT_EMAIL_PASSWORD"),        //default_email_password
                        rs.getString("DOSE1_ATTACHMENT"),        //dose1_attachment
                        rs.getString("DOSE2_ATTACHMENT"),        //dose2_attachment
                        rs.getString("DOSE1_RECORD_DATE"),        //dose1_record_date
                        rs.getString("DOSE2_RECORD_DATE"),        //dose2_record_date
                        rs.getString("CORPORATE_CELL_NUMBER"),        //corp_cell_num
                        rs.getString("CORPORATE_CELL_TYPE")        //corporate_cell_type
                        )
                );
            }
            stmt.close();
            conn.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return employee_info_list;
    }

    public Large_Employee get_employee_info_by_id_using_view(int employeeID){
        Large_Employee current_employee = null;
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(DB_URL, username, password);
            stmt = conn.createStatement();
            String sql =  "SELECT * FROM EMPLOYEE_FULL_INFO WHERE EMPLOYEE_ID='" + employeeID + "'";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                current_employee = new Large_Employee(
                                rs.getInt("EMPLOYEE_ID"),        //employee_id
                                rs.getString("HIRE_DATE"),        //hire_date
                                rs.getString("RATE"),       //rate
                                rs.getString("ACTIVE"),        //active
                                rs.getString("MEX_EMPLOYMENT_CODE"),        //mex_employment_code
                                rs.getString("BANK_TRANSIT_NUMBER"),         //bank_transit_number
                                rs.getString("BANK_ACCOUNT_NUMBER"),       //bank_account_number
                                rs.getString("GIVEN_NAME"),        //given_name
                                rs.getString("SURNAME"),        //surname
                                rs.getString("DOB"),        //DOB
                                rs.getString("PASSPORT_NUMBER"),        //passport_number
                                rs.getString("PASSPORT_EXP"),        //passport_exp
                                rs.getString("SSN"),       //ssn
                                rs.getString("VISA_NUMBER"),       //visa_number
                                rs.getString("HEALTH_CARD_NUMBER"),       //health_card_num
                                rs.getString("ARRIVAL_DATE"),       //arrival_date
                                rs.getString("DEPARTURE_DATE"),        //departure_date
                                rs.getString("EMAIL"),        //email
                                rs.getString("MED_INSURANCE"),         //med_insurance
                                rs.getString("MOBILE_PHONE"),        //mobile_phone
                                rs.getString("NICKNAME"),       //nickname
                                rs.getString("NOTES"),       //notes
                                rs.getString("BED_ID"),        //bed_id
                                rs.getString("HOUSE_NUM"),        //house_num
                                rs.getString("HOUSE_NAME"),        //house_name
                                rs.getString("HOUSE_ADDRESS"),        //house_address
                                rs.getString("WORK_PERMIT"),           //work_permit
                                rs.getString("BENEFIT_CARD"),        //benefit_card
                                rs.getString("EMPLOYMENT_CONTRACT"),           //employment_contract
                                rs.getString("WITHHOLD_AGREEMENT"),        //withhold_agreement
                                rs.getString("QUARANTINE_LOCATION"),        //quarantine_location
                                rs.getString("DEFAULT_EMAIL_PASSWORD"),        //default_email_password
                                rs.getString("DOSE1_ATTACHMENT"),        //dose1_attachment
                                rs.getString("DOSE2_ATTACHMENT"),        //dose2_attachment
                                rs.getString("DOSE1_RECORD_DATE"),        //dose1_record_date
                                rs.getString("DOSE2_RECORD_DATE"),        //dose2_record_date
                                rs.getString("CORPORATE_CELL_NUMBER"),        //corp_cell_num
                                rs.getString("CORPORATE_CELL_TYPE")        //corporate_cell_type

                );
            }

            stmt.close();
            conn.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return current_employee;
    }






    public ArrayList<Work_Hours> get_all_work_hours_list(LocalDate start_date, LocalDate end_date){
        ArrayList<Work_Hours> work_hours_list = new ArrayList<>();
        DateTimeFormatter today_format = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(DB_URL);
            stmt = conn.createStatement();
            String sql = "SELECT * FROM TBLVIEW_WORK_HOURS";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                        int employeeID = rs.getInt("EMPLOYEE_ID");                             //employee_id
                        String givenName = rs.getString("GIVEN_NAME");                             //given_name
                        String surname = rs.getString("SURNAME");                          //surname
                        int hoursID = rs.getInt("HOURS_ID");                          //hours_id
                        double hoursWorked = Double.parseDouble(rs.getString("HOURS_WORKED"));    //hours_worked
                        String dateWorked = rs.getString("DATE_WORKED");                          //date_worked
                        String jobName = rs.getString("JOB_NAME");                          //job_name
                        double payRate = Double.parseDouble(rs.getString("RATE").substring(1));             //rate
                LocalDate date_worked_parsed = LocalDate.parse(dateWorked, today_format);
                if(date_worked_parsed.isAfter(start_date) && date_worked_parsed.isBefore(end_date)){
                    work_hours_list.add(new Work_Hours(employeeID, givenName, surname, hoursID, hoursWorked, dateWorked, jobName, payRate));
                }
                else{
                    System.out.print("\nDate: "+ dateWorked + " is not in the range");
                }
            }
            stmt.close();
            conn.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return work_hours_list;
    }


    public ArrayList<Work_Hours> get_all_work_hours_list_no_date(){
        ArrayList<Work_Hours> work_hours_list = new ArrayList<>();
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(DB_URL);
            stmt = conn.createStatement();
            String sql = "SELECT * FROM TBLVIEW_WORK_HOURS";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int employeeID = rs.getInt("EMPLOYEE_ID");                             //employee_id
                String givenName = rs.getString("GIVEN_NAME");                             //given_name
                String surname = rs.getString("SURNAME");                          //surname
                int hoursID = rs.getInt("HOURS_ID");                          //hours_id
                double hoursWorked = Double.parseDouble(rs.getString("HOURS_WORKED"));    //hours_worked
                String dateWorked = rs.getString("DATE_WORKED");                          //date_worked
                String jobName = rs.getString("JOB_NAME");                          //job_name
                double payRate = Double.parseDouble(rs.getString("RATE").substring(1));             //rate
                work_hours_list.add(new Work_Hours(employeeID, givenName, surname, hoursID, hoursWorked, dateWorked, jobName, payRate));

            }
            stmt.close();
            conn.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return work_hours_list;
    }




    //int employee_id, String date_worked, String name, double hours_worked, double cost
    public ArrayList<Work_Hours> get_work_hours_by_id(int emp_id, LocalDate start_date, LocalDate end_date){
        DateTimeFormatter today_format = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        ArrayList<Work_Hours> work_hours_list = new ArrayList<>();
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(DB_URL, username, password);
            stmt = conn.createStatement();
            String sql = "SELECT * FROM TBLVIEW_WORK_HOURS WHERE EMPLOYEE_ID='" + emp_id + "'";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int employeeID = rs.getInt("EMPLOYEE_ID");                             //employee_id
                String givenName = rs.getString("GIVEN_NAME");                             //given_name
                String surname = rs.getString("SURNAME");                          //surname
                int hoursID = rs.getInt("HOURS_ID");                          //hours_id
                double hoursWorked = Double.parseDouble(rs.getString("HOURS_WORKED"));    //hours_worked
                String dateWorked = rs.getString("DATE_WORKED");                          //date_worked
                String jobName = rs.getString("JOB_NAME");                          //job_name
                double payRate = Double.parseDouble(rs.getString("RATE").substring(1));             //rate
                LocalDate date_worked_parsed = LocalDate.parse(dateWorked, today_format);
                if(date_worked_parsed.isAfter(start_date) && date_worked_parsed.isBefore(end_date)){
                    work_hours_list.add(new Work_Hours(employeeID, givenName, surname, hoursID, hoursWorked, dateWorked, jobName, payRate));
                }
                else{
                    //System.out.print("\nDate: "+ dateWorked + " is not in the range");
                }
            }
            stmt.close();
            conn.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return work_hours_list;
    }


    //Establishes the connection in Login Screen
    public boolean establish_connection(){
        boolean isValid = false;
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(DB_URL, username, password);
            stmt = conn.createStatement();
            String sql = "SELECT EMPLOYEE_ID FROM HEALTH_INFORMATION";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                rs.getInt(1);
                isValid = conn.isClosed();
            }
            stmt.close();
            conn.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return isValid;
    }


    //Establishes the connection in Login Screen
    public int get_largest_employee_id(){
        int employee_id = 0;
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(DB_URL, username, password);
            stmt = conn.createStatement();
            String sql = "SELECT MAX(EMPLOYEE_ID) FROM EMPLOYEE_INFO";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                employee_id = rs.getInt(1);
            }
            stmt.close();
            conn.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return employee_id+1;
    }

    //Establishes the connection in Login Screen
    public int get_largest_work_hour_id(){
        int hours_id = 0;
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(DB_URL, username, password);
            stmt = conn.createStatement();
            String sql = "SELECT MAX(HOURS_ID) FROM WORK_HOURS";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                hours_id = rs.getInt(1);
            }
            stmt.close();
            conn.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return hours_id+1;
    }

    //Establishes the connection in Login Screen
    public ArrayList<String> get_job_names(){
        ArrayList<String> job_names = new ArrayList<>();
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(DB_URL, username, password);
            stmt = conn.createStatement();
            String sql = "SELECT DISTINCT (JOB_NAME) FROM WORK_HOURS";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                job_names.add(rs.getString("JOB_NAME"));
            }
            stmt.close();
            conn.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return job_names;
    }



   String get_file(String filename) throws UnsupportedEncodingException {
       String file_name = "";
       try {
           Class.forName(driver);
           conn = DriverManager.getConnection(DB_URL, username, password);
           stmt = conn.createStatement();
           String sql = "SELECT * FROM Employee_Attachments WHERE NAME ='" + filename + "'";
           ResultSet rs = stmt.executeQuery(sql);
           while (rs.next()) {
                file_name = rs.getString("name");
           }
           rs.close();
               stmt.close();
               conn.close();
       } catch (SQLException | ClassNotFoundException e) {
           e.printStackTrace();
       }
       return file_name;
   }



       public void insert_to_employee_attachments(String fileStream, String name) throws SQLException {
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(DB_URL, username, password);
            stmt = conn.createStatement();
            String sql = "INSERT INTO Employee_Attachments (FILE_STREAM, NAME) VALUES(CONVERT(VARBINARY, '"
                    + fileStream
                    + "') ,'"
                    + name
                    + "');";
            stmt.executeUpdate(sql);
            stmt.close();
            conn.close();
        }catch (SQLException | ClassNotFoundException se){
            se.printStackTrace();
        }
    }


    //Gets data for job costing object
    public ArrayList<Job_Costing> get_job_costing(){
        ArrayList<Job_Costing> find_job_costin = new ArrayList<>();
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(DB_URL, username, password);
            stmt = conn.createStatement();
            String sql = "SELECT * FROM JOB_COSTING_VIEW";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                find_job_costin.add(new Job_Costing(
                        rs.getString("DATE_WORKED"),
                        rs.getString("JOB_NAME"),
                        Double.parseDouble(rs.getString("HOURS_WORKED")),
                        Double.parseDouble(rs.getString("RATE").substring(1))
                ));
            }
            stmt.close();
            conn.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return find_job_costin;
    }


    //Gets data for job costing object
    public ArrayList<Job_Costing> get_distinct_date_job_costing(){
        ArrayList<Job_Costing> find_job_costin = new ArrayList<>();
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(DB_URL, username, password);
            stmt = conn.createStatement();
            String sql = "SELECT * FROM JOB_COSTING_TOTAL_FOR_DAY_VIEW";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                find_job_costin.add(new Job_Costing(                                // date worked
                        rs.getString("DATE_WORKED"),
                        Double.parseDouble(rs.getString("Expr1")),      // hours worked
                        Double.parseDouble(rs.getString("RATE").substring(1))  //pay rate of employee
                ));
            }
            stmt.close();
            conn.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return find_job_costin;
    }

    //Gets data for job costing object
    public ArrayList<Job_Costing> get_job_costing_by_date(String selected_date){
        ArrayList<Job_Costing> find_job_costin = new ArrayList<>();
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(DB_URL, username, password);
            stmt = conn.createStatement();
            String sql = "SELECT * FROM JOB_COSTING_VIEW WHERE DATE_WORKED ='" + selected_date + "'";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                find_job_costin.add(new Job_Costing(
                        rs.getString("DATE_WORKED"),
                        rs.getString("JOB_NAME"),
                        Double.parseDouble(rs.getString("HOURS_WORKED")),
                        Double.parseDouble(rs.getString("RATE").substring(1))
                ));
            }
            stmt.close();
            conn.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return find_job_costin;
    }

    public void delete_work_hour(int hour_id){
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(DB_URL, username, password);
            stmt = conn.createStatement();
            String sql = "DELETE FROM WORK_HOURS WHERE HOURS_ID ='" + hour_id + "'";
            stmt.executeUpdate(sql);
            stmt.close();
            conn.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }





}









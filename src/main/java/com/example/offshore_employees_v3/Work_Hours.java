package com.example.offshore_employees_v3;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Work_Hours {
    int employee_id, hours_id;
    String date_worked, job_code, name, job_name, given_name, surname;
    double hours_worked, cost, rate;
    LocalDate date_worked_as_date;
    DateTimeFormatter today_format = DateTimeFormatter.ofPattern("MM/dd/yyyy");



    // Constructor is used for method: public ArrayList<Work_Hours> get_work_hours_by_id(int emp_id)
    // Also used for:  public ArrayList<Work_Hours> get_all_work_hours_list(LocalDate start_date, LocalDate end_date)
    public Work_Hours(int employee_id,
                      String given_name,
                      String surname,
                      int hours_id,
                      double hours_worked,
                      String date_worked,
                      String job_name,
                      double rate) {
        this.employee_id = employee_id;
        this.given_name = given_name;
        this.surname = surname;
        this.name = given_name + " " +surname;
        this.hours_id = hours_id;
        this.date_worked = date_worked;
        this.hours_worked = hours_worked;
        this.job_name = job_name;
        this.rate = rate;
        this.date_worked_as_date = LocalDate.parse(date_worked, today_format);

        // The double for cost needs to be rounded up to the 2nd decimal place
        BigDecimal bd = BigDecimal.valueOf(hours_worked*rate);
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        this.cost = bd.doubleValue();
    }


    public int getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(int employee_id) {
        this.employee_id = employee_id;
    }

    public int getHours_id() {
        return hours_id;
    }

    public void setHours_id(int hours_id) {
        this.hours_id = hours_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate_worked() {
        return date_worked;
    }

    public void setDate_worked(String date_worked) {
        this.date_worked = date_worked;
    }

    public String getJob_code() {
        return job_code;
    }

    public void setJob_code(String job_code) {
        this.job_code = job_code;
    }

    public double getHours_worked() {
        return hours_worked;
    }

    public void setHours_worked(double hours_worked) {
        this.hours_worked = hours_worked;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getJob_name() {
        return job_name;
    }

    public void setJob_name(String job_name) {
        this.job_name = job_name;
    }

    public String getGiven_name() {
        return given_name;
    }

    public void setGiven_name(String given_name) {
        this.given_name = given_name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public LocalDate getDate_worked_as_date() {
        return date_worked_as_date;
    }

    public void setDate_worked_as_date(LocalDate date_worked_as_date) {
        this.date_worked_as_date = date_worked_as_date;
    }

    @Override
    public String toString() {
        return "Work_Hours{" +
                "employee_id=" + employee_id +
                ", hours_id=" + hours_id +
                ", date_worked='" + date_worked + '\'' +
                ", job_code='" + job_code + '\'' +
                ", name='" + name + '\'' +
                ", job_name='" + job_name + '\'' +
                ", given_name='" + given_name + '\'' +
                ", surname='" + surname + '\'' +
                ", hours_worked=" + hours_worked +
                ", cost=" + cost +
                ", rate=" + rate +
                '}';
    }
}

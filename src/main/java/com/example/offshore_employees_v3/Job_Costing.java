package com.example.offshore_employees_v3;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

public class Job_Costing {
    String date, job_name;
    double hours, cost, pay_rate, job_cost;
    double asparagus, corn, pumpkin, ginseng, other;
    double[] all_job_hours;


    public Job_Costing(String date, String job_name, double hours, double pay_rate) {
        job_name = job_name.toUpperCase();
        switch(job_name){
            case ("ASPARAGUS"):
                this.asparagus = hours;
                break;
            case ("CORN"):
                this.corn = hours;
                break;
            case("PUMPKIN"):
                this.pumpkin = hours;
                break;
            case("GINSENG"):
                this.ginseng = hours;
                break;
            case("OTHER"):
                this.other = hours;
                break;
            default:
                this.other = hours;
                break;
        }
        this.job_name = job_name.substring(0,1) + job_name.substring(1).toLowerCase();
        this.date = date;
        this.pay_rate = pay_rate;
        this.hours = hours;
        BigDecimal bd = BigDecimal.valueOf(hours*pay_rate);
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        this.cost = bd.doubleValue();
    }

    public Job_Costing(String date, double hours, double pay_rate) {
        this.date = date;
        this.pay_rate = pay_rate;
        this.hours = hours;
        BigDecimal bd = BigDecimal.valueOf(hours*pay_rate);
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        this.cost = bd.doubleValue();
    }

    public LocalDate get_date_local(String date){
        DateTimeFormatter today_format = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        LocalDate cast_to_date = LocalDate.parse(date, today_format);
        return cast_to_date;
    }





    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getJob_name() {
        return job_name;
    }

    public void setJob_name(String job_name) {
        this.job_name = job_name;
    }

    public double getHours() {
        return hours;
    }

    public void setHours(double hours) {
        this.hours = hours;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public double getPay_rate() {
        return pay_rate;
    }

    public void setPay_rate(double pay_rate) {
        this.pay_rate = pay_rate;
    }


    public double getAsparagus() {
        return asparagus;
    }

    public void setAsparagus(double asparagus) {
        this.asparagus = asparagus;
    }

    public double getCorn() {
        return corn;
    }

    public void setCorn(double corn) {
        this.corn = corn;
    }

    public double getPumpkin() {
        return pumpkin;
    }

    public void setPumpkin(double pumpkin) {
        this.pumpkin = pumpkin;
    }

    public double getGinseng() {
        return ginseng;
    }

    public void setGinseng(double ginseng) {
        this.ginseng = ginseng;
    }

    public double getOther() {
        return other;
    }

    public void setOther(double other) {
        this.other = other;
    }

    public double[] getAll_job_hours() {
        return all_job_hours;
    }

    public void setAll_job_hours(double[] all_job_hours) {
        this.all_job_hours = all_job_hours;
    }

    @Override
    public String toString() {
        return "Job_Costing{" +
                "date='" + date + '\'' +
                ", job_name='" + job_name + '\'' +
                ", hours=" + hours +
                ", cost=" + cost +
                ", pay_rate=" + pay_rate +
                ", asparagus=" + asparagus +
                ", corn=" + corn +
                ", pumpkin=" + pumpkin +
                ", ginseng=" + ginseng +
                ", other=" + other +
                ", all_job_hours=" + Arrays.toString(all_job_hours) +
                '}';
    }
}

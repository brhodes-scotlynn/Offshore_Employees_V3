package com.example.offshore_employees_v3;

public class Attachments {
    int employeeID;
    String attachmentName;
    String fileName;


    public Attachments(int employeeID, String attachmentName, String fileName) {
        this.employeeID = employeeID;
        this.attachmentName = attachmentName;
        this.fileName = fileName;
    }

    public Attachments(String attachmentName, String fileName) {
        this.attachmentName = attachmentName;
        this.fileName = fileName;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public String getAttachmentName() {
        return attachmentName;
    }

    public void setAttachmentName(String attachmentName) {
        this.attachmentName = attachmentName;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}

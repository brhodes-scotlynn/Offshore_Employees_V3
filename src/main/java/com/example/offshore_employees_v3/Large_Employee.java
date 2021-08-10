package com.example.offshore_employees_v3;

public class Large_Employee {
    int employeeID;
    String hireDate,
            pay_rate,
            still_active,
            mex_code,
            bankID,
            bankAccountNum,
            givenName,
            surName,
            dateOfBirth,
            passportNumber,
            passportExpiration,
            SII,
            visaNumber,
            healthCardNum,
            arrivalDate,
            departureDate,
            txtEmail,
            medInsurance,
            txtMobile,
            nickName,
            txtNotes,
            bedID,
            houseNum,
            houseName,
            houseAddress,
            workPermit,
            benefitCard,
            employmentAgreement,
            withholdAgreement,
            quarantineLocation,
            mexContactName,
            mexMunicipality,
            mexPhone,
            mexNeighbourhood,
            mexPostalCode,
            mexStreetAddress,
            mexState,
            emailPassword,
            covidDose1Attach,
            covidDose2Attach,
            covidDoseDate1,
            covidDoseDate2,
            corpPhoneNumber,
            corpPhoneType,
            city,
            jobName,
            jobCode,
            name;



    /*
    Methods that use this constructor:
    - public ArrayList<Large_Employee> get_employee_info_list_using_view()
    -  public Large_Employee get_employee_info_by_id_using_view(int employeeID)
     */

    public Large_Employee(int employeeID,
                          String hireDate,
                          String pay_rate,
                          String still_active,
                          String mex_code,
                          String bankID,
                          String bankAccountNum,
                          String givenName,
                          String surName,
                          String dateOfBirth,
                          String passportNumber,
                          String passportExpiration,
                          String SII,
                          String visaNumber,
                          String healthCardNum,
                          String arrivalDate,
                          String departureDate,
                          String txtEmail,
                          String medInsurance,
                          String txtMobile,
                          String nickName,
                          String txtNotes,
                          String bedID,
                          String houseNum,
                          String houseName,
                          String houseAddress,
                          String workPermit,
                          String benefitCard,
                          String employmentAgreement,
                          String withholdAgreement,
                          String quarantineLocation,
                          String emailPassword,
                          String covidDose1Attach,
                          String covidDose2Attach,
                          String covidDoseDate1,
                          String covidDoseDate2,
                          String corpPhoneNumber,
                          String corpPhoneType) {
        this.employeeID = employeeID;
        this.hireDate = hireDate;
        this.pay_rate = pay_rate;
        this.still_active = still_active;
        this.mex_code = mex_code;
        this.bankID = bankID;
        this.bankAccountNum = bankAccountNum;
        this.givenName = givenName;
        this.surName = surName;
        this.name = givenName +" "+surName;
        this.dateOfBirth = dateOfBirth;
        this.passportNumber = passportNumber;
        this.passportExpiration = passportExpiration;
        this.SII = SII;
        this.visaNumber = visaNumber;
        this.healthCardNum = healthCardNum;
        this.arrivalDate = arrivalDate;
        this.departureDate = departureDate;
        this.txtEmail = txtEmail;
        this.medInsurance = medInsurance;
        this.txtMobile = txtMobile;
        this.nickName = nickName;
        this.txtNotes = txtNotes;
        this.bedID = bedID;
        this.houseNum = houseNum;
        this.houseName = houseName;
        this.houseAddress = houseAddress;
        this.workPermit = workPermit;
        this.benefitCard = benefitCard;
        this.employmentAgreement = employmentAgreement;
        this.withholdAgreement = withholdAgreement;
        this.quarantineLocation = quarantineLocation;
        this.emailPassword = emailPassword;
        this.covidDose1Attach = covidDose1Attach;
        this.covidDose2Attach = covidDose2Attach;
        this.covidDoseDate1 = covidDoseDate1;
        this.covidDoseDate2 = covidDoseDate2;
        this.corpPhoneNumber = corpPhoneNumber;
        this.corpPhoneType = corpPhoneType;
    }
    // Constructor to store Employee into Employee_Info table
    public Large_Employee(int employeeID,
                          String mex_code,
                          String givenName,
                          String surName,
                          String dateOfBirth,
                          String passportNumber,
                          String passportExpiration,
                          String SII,
                          String visaNumber,
                          String nickName,
                          String txtNotes) {
        this.employeeID = employeeID;
        this.mex_code = mex_code;
        this.givenName = givenName;
        this.surName = surName;
        this.name = givenName +" "+surName;
        this.dateOfBirth = dateOfBirth;
        this.passportNumber = passportNumber;
        this.passportExpiration = passportExpiration;
        this.SII = SII;
        this.visaNumber = visaNumber;
        this.nickName = nickName;
        this.txtNotes = txtNotes;
    }




    // Constructor for storing employee into Employment_Info table
    public Large_Employee(int employeeID,
                          String hireDate,
                          String arrivalDate,
                          String departureDate,
                          String pay_rate,
                          String still_active) {
        this.employeeID = employeeID;
        this.hireDate = hireDate;
        this.pay_rate = pay_rate;
        this.still_active = still_active;
        this.arrivalDate = arrivalDate;
        this.departureDate = departureDate;
    }


    // Constructor for storing employee into Employee List tableview
    public Large_Employee(
                          String mex_code,
                          String givenName,
                          String surName,
                          int employeeID,
                          String jobName) {
        this.employeeID = employeeID;
        this.mex_code = mex_code;
        this.givenName = givenName;
        this.surName = surName;
        this.name = givenName +" "+surName;
        this.jobName = jobName;
    }

    // Constuctor for storing employee into Attachments table
    public Large_Employee(
                          String workPermit,
                          String employmentAgreement,
                          int employeeID,
                          String benefitCard,
                          String withholdAgreement) {
        this.employeeID = employeeID;
        this.workPermit = workPermit;
        this.benefitCard = benefitCard;
        this.employmentAgreement = employmentAgreement;
        this.withholdAgreement = withholdAgreement;
    }

    // Constuctor for getting an arraylist of attachemnts
    public Large_Employee(
            String workPermit,
            String employmentAgreement,
            int employeeID,
            String benefitCard,
            String withholdAgreement,
            String covidDose1Attach,
            String covidDose2Attach) {
        this.employeeID = employeeID;
        this.workPermit = workPermit;
        this.benefitCard = benefitCard;
        this.employmentAgreement = employmentAgreement;
        this.withholdAgreement = withholdAgreement;
        this.covidDose1Attach = covidDose1Attach;
        this.covidDose2Attach = covidDose2Attach;
    }

    // Constuctor for storing employee into housing_info table
    public Large_Employee(
                          String houseNum,
                          int employeeID,
                          String houseName,
                          String houseAddress,
                          String bedID) {
        this.employeeID = employeeID;
        this.houseNum = houseNum;
        this.houseName = houseName;
        this.houseAddress = houseAddress;
        this.bedID = bedID;
    }


    //Constuctor for storing employee into Bank_Info table
    public Large_Employee(int employeeID,
                          String bankID,
                          String bankAccountNum) {
        this.employeeID = employeeID;
        this.bankID = bankID;
        this.bankAccountNum = bankAccountNum;
    }

    //Constuctor for storing employee into Health_Info table
    public Large_Employee(
                          String healthCardNum,
                          String medInsurance,
                          int employeeID) {
        this.employeeID = employeeID;
        this.healthCardNum = healthCardNum;
        this.medInsurance = medInsurance;
    }


    //Constuctor for storing employee into Contact_Info table
    public Large_Employee(
                          String txtMobile,
                          String txtEmail,
                          String emailPassword,
                          String corpPhoneType,
                          String corpPhoneNumber,
                          int employeeID){
        this.txtMobile = txtMobile;
        this.txtEmail = txtEmail;
        this.emailPassword = emailPassword;
        this.corpPhoneType = corpPhoneType;
        this.corpPhoneNumber = corpPhoneNumber;
        this.employeeID = employeeID;
    }

    //Constuctor for storing employee into Contact_Info table
    public Large_Employee(
            String quarantineLocation,
            int employeeID,
            String covidDose1Attach,
            String covidDose2Attach,
            String covidDoseDate1,
            String covidDoseDate2
            ){
        this.quarantineLocation = quarantineLocation;
        this.covidDose1Attach = covidDose1Attach;
        this.covidDose2Attach = covidDose2Attach;
        this.covidDoseDate1 = covidDoseDate1;
        this.covidDoseDate2 = covidDoseDate2;
        this.employeeID = employeeID;
    }



    // Constructor for storing employee into Mex_Contact_Info table
    public Large_Employee(int employeeID,
                          String mexContactName,
                          String mexMunicipality,
                          String mexPhone,
                          String mexNeighbourhood,
                          String mexPostalCode,
                          String mexStreetAddress,
                          String mexState) {
        this.employeeID = employeeID;
        this.mexContactName = mexContactName;
        this.mexMunicipality = mexMunicipality;
        this.mexPhone = mexPhone;
        this.mexNeighbourhood = mexNeighbourhood;
        this.mexPostalCode = mexPostalCode;
        this.mexStreetAddress = mexStreetAddress;
        this.mexState = mexState;
    }

    public Large_Employee(int employeeID,
                          String mex_code,
                          String givenName,
                          String surName,
                          String dateOfBirth,
                          String passportNumber,
                          String passportExpiration,
                          String SII,
                          String visaNumber,
                          String nickName,
                          String txtNotes,
                          String jobName) {
        this.employeeID = employeeID;
        this.mex_code = mex_code;
        this.givenName = givenName;
        this.surName = surName;
        this.name = givenName +" "+surName;
        this.dateOfBirth = dateOfBirth;
        this.passportNumber = passportNumber;
        this.passportExpiration = passportExpiration;
        this.SII = SII;
        this.visaNumber = visaNumber;
        this.nickName = nickName;
        this.txtNotes = txtNotes;
        this.jobName = jobName;
    }

    /*
     Constructor used for method:
     public ArrayList<Large_Employee> get_covid_screening_list()
     */
    public Large_Employee(String mex_code, String givenName, String surName, String bedID, int employeeID, String houseNum) {
        this.mex_code = mex_code;
        this.givenName = givenName;
        this.surName = surName;
        this.name = givenName +" "+surName;
        this.bedID = bedID;
        this.employeeID = employeeID;
        this.houseNum = houseNum;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public String getHireDate() {
        return hireDate;
    }

    public void setHireDate(String hireDate) {
        this.hireDate = hireDate;
    }

    public String getPay_rate() {
        return pay_rate;
    }

    public void setPay_rate(String pay_rate) {
        this.pay_rate = pay_rate;
    }

    public String getStill_active() {
        return still_active;
    }

    public void setStill_active(String still_active) {
        this.still_active = still_active;
    }

    public String getMex_code() {
        return mex_code;
    }

    public void setMex_code(String mex_code) {
        this.mex_code = mex_code;
    }

    public String getBankID() {
        return bankID;
    }

    public void setBankID(String bankID) {
        this.bankID = bankID;
    }

    public String getBankAccountNum() {
        return bankAccountNum;
    }

    public void setBankAccountNum(String bankAccountNum) {
        this.bankAccountNum = bankAccountNum;
    }

    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public String getPassportExpiration() {
        return passportExpiration;
    }

    public void setPassportExpiration(String passportExpiration) {
        this.passportExpiration = passportExpiration;
    }

    public String getSSN() {
        return SII;
    }

    public void setSSN(String SII) {
        this.SII = SII;
    }

    public String getVisaNumber() {
        return visaNumber;
    }

    public void setVisaNumber(String visaNumber) {
        this.visaNumber = visaNumber;
    }

    public String getHealthCardNum() {
        return healthCardNum;
    }

    public void setHealthCardNum(String healthCardNum) {
        this.healthCardNum = healthCardNum;
    }

    public String getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(String arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }

    public String getTxtEmail() {
        return txtEmail;
    }

    public void setTxtEmail(String txtEmail) {
        this.txtEmail = txtEmail;
    }

    public String getMedInsurance() {
        return medInsurance;
    }

    public void setMedInsurance(String medInsurance) {
        this.medInsurance = medInsurance;
    }

    public String getTxtMobile() {
        return txtMobile;
    }

    public void setTxtMobile(String txtMobile) {
        this.txtMobile = txtMobile;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getTxtNotes() {
        return txtNotes;
    }

    public void setTxtNotes(String txtNotes) {
        this.txtNotes = txtNotes;
    }

    public String getBedID() {
        return bedID;
    }

    public void setBedID(String bedID) {
        this.bedID = bedID;
    }

    public String getHouseNum() {
        return houseNum;
    }

    public void setHouseNum(String houseNum) {
        this.houseNum = houseNum;
    }

    public String getHouseName() {
        return houseName;
    }

    public void setHouseName(String houseName) {
        this.houseName = houseName;
    }

    public String getHouseAddress() {
        return houseAddress;
    }

    public void setHouseAddress(String houseAddress) {
        this.houseAddress = houseAddress;
    }

    public String getWorkPermit() {
        return workPermit;
    }

    public void setWorkPermit(String workPermit) {
        this.workPermit = workPermit;
    }

    public String getBenefitCard() {
        return benefitCard;
    }

    public void setBenefitCard(String benefitCard) {
        this.benefitCard = benefitCard;
    }

    public String getEmploymentAgreement() {
        return employmentAgreement;
    }

    public void setEmploymentAgreement(String employmentAgreement) {
        this.employmentAgreement = employmentAgreement;
    }

    public String getWithholdAgreement() {
        return withholdAgreement;
    }

    public void setWithholdAgreement(String withholdAgreement) {
        this.withholdAgreement = withholdAgreement;
    }

    public String getQuarantineLocation() {
        return quarantineLocation;
    }

    public void setQuarantineLocation(String quarantineLocation) {
        this.quarantineLocation = quarantineLocation;
    }

    public String getMexContactName() {
        return mexContactName;
    }

    public void setMexContactName(String mexContactName) {
        this.mexContactName = mexContactName;
    }

    public String getMexMunicipality() {
        return mexMunicipality;
    }

    public void setMexMunicipality(String mexMunicipality) {
        this.mexMunicipality = mexMunicipality;
    }

    public String getMexPhone() {
        return mexPhone;
    }

    public void setMexPhone(String mexPhone) {
        this.mexPhone = mexPhone;
    }

    public String getMexNeighbourhood() {
        return mexNeighbourhood;
    }

    public void setMexNeighbourhood(String mexNeighbourhood) {
        this.mexNeighbourhood = mexNeighbourhood;
    }

    public String getMexPostalCode() {
        return mexPostalCode;
    }

    public void setMexPostalCode(String mexPostalCode) {
        this.mexPostalCode = mexPostalCode;
    }

    public String getMexStreetAddress() {
        return mexStreetAddress;
    }

    public void setMexStreetAddress(String mexStreetAddress) {
        this.mexStreetAddress = mexStreetAddress;
    }

    public String getMexState() {
        return mexState;
    }

    public void setMexState(String mexState) {
        this.mexState = mexState;
    }

    public String getEmailPassword() {
        return emailPassword;
    }

    public void setEmailPassword(String emailPassword) {
        this.emailPassword = emailPassword;
    }

    public String getCovidDose1Attach() {
        return covidDose1Attach;
    }

    public void setCovidDose1Attach(String covidDose1Attach) {
        this.covidDose1Attach = covidDose1Attach;
    }

    public String getCovidDose2Attach() {
        return covidDose2Attach;
    }

    public void setCovidDose2Attach(String covidDose2Attach) {
        this.covidDose2Attach = covidDose2Attach;
    }

    public String getCovidDoseDate1() {
        return covidDoseDate1;
    }

    public void setCovidDoseDate1(String covidDoseDate1) {
        this.covidDoseDate1 = covidDoseDate1;
    }

    public String getCovidDoseDate2() {
        return covidDoseDate2;
    }

    public void setCovidDoseDate2(String covidDoseDate2) {
        this.covidDoseDate2 = covidDoseDate2;
    }

    public String getCorpPhoneNumber() {
        return corpPhoneNumber;
    }

    public void setCorpPhoneNumber(String corpPhoneNumber) {
        this.corpPhoneNumber = corpPhoneNumber;
    }

    public String getCorpPhoneType() {
        return corpPhoneType;
    }

    public void setCorpPhoneType(String corpPhoneType) {
        this.corpPhoneType = corpPhoneType;
    }

    public String getSII() {
        return SII;
    }

    public void setSII(String SII) {
        this.SII = SII;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getJobCode() {
        return jobCode;
    }

    public void setJobCode(String jobCode) {
        this.jobCode = jobCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return
                "Scotlynn ID: " + employeeID +
                " \nHire Date:  " + hireDate +
                " \nPay Rate:  " + pay_rate +
                " \nStill Active:  " + still_active +
                " \nMex Emp Code:  " + mex_code +
                " \nBank Transit #:  " + bankID +
                " \nBank Account #:  " + bankAccountNum +
                " \nGiven Name:  " + givenName +
                " \nSurname:  " + surName +
                " \nDOB:  " + dateOfBirth +
                " \nPassport #:  " + passportNumber +
                " \nPassport Exp:  " + passportExpiration +
                " \nSSN:  " + SII +
                " \nVisa #:  " + visaNumber +
                " \nHealth Card #:  " + healthCardNum +
                " \nArrival Date:  " + arrivalDate +
                " \nDeparture Date:  " + departureDate +
                " \nEmail:  " + txtEmail +
                " \nMed Insurance:  " + medInsurance +
                " \nMobile #: " + txtMobile +
                " \nNickname:  " + nickName +
                " \nNotes:  " + txtNotes +
                " \nBed ID#:  " + bedID +
                " \nHouse #:  " + houseNum +
                " \nHouse Name:  " + houseName +
                " \nHouse Address:  " + houseAddress +
                " \nCity:  " + city +
                " \nWork Permit:  " + workPermit +
                " \nBenefit Card:  " + benefitCard +
                " \nEmployment Contract:  " + employmentAgreement +
                " \nWithhold Agreement:  " + withholdAgreement +
                " \nQuarantine Location:  " + quarantineLocation +
                " \nMex Contact Name:  " + mexContactName +
                " \nMex Municipality:  " + mexMunicipality +
                " \nMex Phone:  " + mexPhone +
                " \nMex Neighbourhood:  " + mexNeighbourhood +
                " \nMex Postal Code:  " + mexPostalCode +
                " \nMex Street Address:  " + mexStreetAddress +
                " \nMex State:  '" + mexState +
                " \nDefault Email Password:  " + emailPassword +
                " \nCovid Dose 1 Attach: " + covidDose1Attach +
                " \nCovid Dose 2 Attach:  " + covidDose2Attach +
                " \nCovid Dose 1 Date:  " + covidDoseDate1 +
                " \nCovid Dose 2 Date:  " + covidDoseDate2 +
                " \nCorp Phone Number:  " + corpPhoneNumber +
                " \nCorp Phone Type:  " + corpPhoneType
                ;
    }
}

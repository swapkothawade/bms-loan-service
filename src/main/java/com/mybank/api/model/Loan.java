package com.mybank.api.model;

import java.time.LocalDate;


public class Loan {

    private String loanid;
    private String username;

    public double getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(double loanAmount) {
        this.loanAmount = loanAmount;
    }

    public float getRateOfInterest() {
        return rateOfInterest;
    }

    public void setRateOfInterest(float rateOfInterest) {
        this.rateOfInterest = rateOfInterest;
    }

    private String loanType;
    private double loanAmount;
    private LocalDate loanApplicationDate;
     private float rateOfInterest;
    private int loanDurationInMonth;

    public String getLoanid() {
        return loanid;
    }

    public void setLoanid(String loanid) {
        this.loanid = loanid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLoanType() {
        return loanType;
    }

    public void setLoanType(String loanType) {
        this.loanType = loanType;
    }



    public LocalDate getLoanApplicationDate() {
        return loanApplicationDate;
    }

    public void setLoanApplicationDate(LocalDate loanApplicationDate) {
        this.loanApplicationDate = loanApplicationDate;
    }



    public int getLoanDurationInMonth() {
        return loanDurationInMonth;
    }

    public void setLoanDurationInMonth(int loanDurationInMonth) {
        this.loanDurationInMonth = loanDurationInMonth;
    }
}

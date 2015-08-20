package com.example2;

import java.util.Calendar;

public class ScheduledPayments {
String AccName;
    String AccBSB;
    String AccAccount;
    String CustomerNumber;
    String ReferenceNumber;
    Double Amount;
    String PayRule;
    String PayPriority;
    String PayBefore;
    String Paystatus;
    String ID;
    String Key;

    public ScheduledPayments(String accname, String accbsb, String accaccount, String referenceNumber, Double amount, String payRule, String payPriority, String payBefore, String paystatus) {
        Calendar calendar = Calendar.getInstance();
        AccName = accname;
        AccBSB = accbsb;
        AccAccount = accaccount;
        CustomerNumber = "AwesomeMelon";
        ReferenceNumber = referenceNumber;
        Amount = amount;
        PayRule = payRule;
        PayPriority = payPriority;
        PayBefore = payBefore;
        Paystatus = paystatus;
    }

    public ScheduledPayments() {
    }

    public String getAccName() {
        return AccName;
    }

    public String getAccBSB() {
        return AccBSB;
    }

    public String getAccAccount() {
        return AccAccount;
    }

    public String getCustomerNumber() {
        return CustomerNumber;
    }

    public String getReferenceNumber() {
        return ReferenceNumber;
    }

    public Double getAmount() {
        return Amount;
    }

    public String getPayRule() {
        return PayRule;
    }

    public String getPayPriority() {
        return PayPriority;
    }

    public String getPayBefore() {
        return PayBefore;
    }

    public String getPaystatus() {
        return Paystatus;
    }

    public void setAccName(String accName) {
        AccName = accName;
    }

    public void setAccBSB(String accBSB) {
        AccBSB = accBSB;
    }

    public void setAccAccount(String accAccount) {
        AccAccount = accAccount;
    }

    public void setCustomerNumber(String customerNumber) {
        CustomerNumber = customerNumber;
    }

    public void setReferenceNumber(String referenceNumber) {
        ReferenceNumber = referenceNumber;
    }

    public void setAmount(Double amount) {
        Amount = amount;
    }

    public void setPayRule(String payRule) {
        PayRule = payRule;
    }

    public void setPayPriority(String payPriority) {
        PayPriority = payPriority;
    }

    public void setPayBefore(String payBefore) {
        PayBefore = payBefore;
    }

    public void setPaystatus(String paystatus) {
        Paystatus = paystatus;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getKey() {
        return Key;
    }

    public void setKey(String key) {
        Key = key;
    }
}





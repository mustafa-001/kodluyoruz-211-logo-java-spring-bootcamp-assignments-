package com.fatura.entities;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Customer {
    private String name;
    private ZonedDateTime signupDate;
    private List<Invoice> invoices;


    public Customer(String name, ZonedDateTime signupDate, ArrayList<Invoice> invoices) {
        this.name = name;
        this.signupDate = signupDate;
        this.invoices = invoices;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ZonedDateTime getSignupDate() {
        return signupDate;
    }

    public void setSignupDate(ZonedDateTime signupDate) {
        this.signupDate = signupDate;
    }

    public List<Invoice> getInvoices() {
        return invoices;
    }

    public void setInvoices(List<Invoice> invoices) {
        this.invoices = invoices;
    }

    @Override
    public String toString() {
        return "Customer [invoices=" + invoices + ", name=" + name + ", signupDate=" + signupDate.format(DateTimeFormatter.BASIC_ISO_DATE) + "]";
    }
}

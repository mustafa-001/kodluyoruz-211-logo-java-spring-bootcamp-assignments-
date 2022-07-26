package com.fatura.entities.factories;

import com.fatura.entities.Company;
import com.fatura.entities.Customer;

import java.time.ZonedDateTime;
import java.util.ArrayList;

public class CompanyFactory {
    public  static Company newCompany(String name, String field){
        return new Company(name, field);
    }
}

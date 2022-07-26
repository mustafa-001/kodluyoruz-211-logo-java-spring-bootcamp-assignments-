package com.fatura.entities.factories;

import com.fatura.database.CustomerDatabase;
import com.fatura.entities.Customer;

import java.time.ZonedDateTime;
import java.util.ArrayList;

//Create customers with default date and invoice list values.
public class CustomerFactory {
    public  static Customer newCustomer(String name){
        return new Customer(name, ZonedDateTime.now(), new ArrayList<>());
    }
}

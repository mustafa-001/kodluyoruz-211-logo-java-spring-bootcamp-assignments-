package com.fatura.database;

import com.fatura.entities.Customer;

import java.time.ZonedDateTime;
import java.util.ArrayList;

public  final class CustomerDatabase extends  Database<Customer> {
    public CustomerDatabase(ArrayList<Customer> items) {
        super(items);
    }

    @Override
    public void add(Customer item) {
        super.add(item);
    }

    //Overload on this class's add method.
    public void add(String name, ZonedDateTime time){
        var c = new Customer(name, time, new ArrayList<>());
        this.add(c);
    }
}

package com.fatura.database;

import com.fatura.entities.Customer;

import java.time.ZonedDateTime;
import java.util.ArrayList;

//A singleton CustomerDatabase.
public final class CustomerDatabase extends Database<Customer> {
    private static CustomerDatabase thisInstance;

    //Create an instance of this class if it does not exist. Return that instance.
    public static synchronized CustomerDatabase getInstance() {
        if (thisInstance == null) {
            thisInstance = new CustomerDatabase();
        }
        return thisInstance;
    }

    private CustomerDatabase() {
        super(new ArrayList<>());
    }

    @Override
    public void add(Customer item) {
        super.add(item);
    }

    //Overload on this class's add method.
    public void add(String name, ZonedDateTime time) {
        var c = new Customer(name, time, new ArrayList<>());
        this.add(c);
    }
}

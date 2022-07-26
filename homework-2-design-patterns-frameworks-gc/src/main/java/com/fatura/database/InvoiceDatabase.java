package com.fatura.database;

import com.fatura.entities.Invoice;

import java.util.ArrayList;
import java.util.List;

public final class InvoiceDatabase extends Database<Invoice> {
    private static InvoiceDatabase thisInstance;

    private InvoiceDatabase() {
        super(new ArrayList<>());
    }
    //Create an instance of this class if it does not exist. Return that instance.
    public static synchronized InvoiceDatabase getInstance() {
        if (thisInstance == null) {
            thisInstance = new InvoiceDatabase();
        }
        return thisInstance;
    }

    @Override
    public void add(Invoice item) {
        super.add(item);
    }
}

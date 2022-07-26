package com.fatura.database;

import com.fatura.entities.Invoice;

import java.util.ArrayList;

public  final class InvoiceDatabase extends Database<Invoice>{
    public InvoiceDatabase(ArrayList<Invoice> items) {
        super(items);
    }

    @Override
    public void add(Invoice item) {
        super.add(item);
    }
}

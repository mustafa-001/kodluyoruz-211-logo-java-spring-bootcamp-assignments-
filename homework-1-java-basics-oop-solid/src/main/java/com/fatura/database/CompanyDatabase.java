package com.fatura.database;

import com.fatura.entities.Company;

import java.util.ArrayList;

public final class CompanyDatabase extends Database<Company> {
    public CompanyDatabase(ArrayList<Company> items) {
        super(items);
    }
}

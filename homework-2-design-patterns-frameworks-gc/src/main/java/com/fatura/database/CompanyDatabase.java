package com.fatura.database;

import com.fatura.entities.Company;

import java.util.ArrayList;

public final class CompanyDatabase extends Database<Company> {
    private static CompanyDatabase thisInstance;

    //Create an instance of this class if it does not exist. Return that instance.
    public static synchronized CompanyDatabase getInstance() {
        if (thisInstance == null) {
            thisInstance = new CompanyDatabase();
        }
        return thisInstance;
    }

    private CompanyDatabase() {
        super(new ArrayList<>());
    }
}

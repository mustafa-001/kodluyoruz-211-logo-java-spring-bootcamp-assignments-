package com.fatura.database;

import com.fatura.entities.Company;
import com.fatura.entities.Customer;
import com.fatura.entities.Invoice;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
public class DataGenerator {

    public static void Initialize() {

        var customers = new ArrayList<Customer>(List.of(
                new Customer("Mehmet Şahin", ZonedDateTime.now(), new ArrayList<Invoice>()),
                new Customer("Ali Serçe", ZonedDateTime.now(), new ArrayList<Invoice>()),
                new Customer("Mustafa Kartal", ZonedDateTime.now(), new ArrayList<Invoice>()),
                new Customer("Ahmet Leylek", ZonedDateTime.now(), new ArrayList<Invoice>())
        ));
        var companies = new ArrayList<Company>(List.of(
                new Company("ABC İnşaat", "İnşaat"),
                new Company("XYZ Gıda", "Gıda")
        ));
        var invoices = new ArrayList<Invoice>(List.of(
                new Invoice(ZonedDateTime.now().minusDays(4), new BigDecimal(10000), companies.get(0)),
                new Invoice(ZonedDateTime.now().minusDays(5), new BigDecimal(1000), companies.get(1)),
                new Invoice(ZonedDateTime.now().minusDays(6), new BigDecimal(100), companies.get(1))
        ));
    }

}

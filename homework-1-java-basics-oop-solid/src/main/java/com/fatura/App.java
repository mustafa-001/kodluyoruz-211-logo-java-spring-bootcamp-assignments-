package com.fatura;

import com.fatura.database.CompanyDatabase;
import com.fatura.database.CustomerDatabase;
import com.fatura.database.InvoiceDatabase;
import com.fatura.entities.Company;
import com.fatura.entities.Customer;
import com.fatura.entities.Invoice;

import java.math.BigDecimal;
import java.time.Month;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) {
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

        customers.get(1).getInvoices().add(invoices.get(1));

        var customerDb = new CustomerDatabase(customers);
        var companiesDb = new CompanyDatabase(companies);
        var invoicesDb = new InvoiceDatabase(invoices);


        homeworkRequirements(customerDb, companiesDb, invoicesDb);
    }

    public static void homeworkRequirements(CustomerDatabase customerDatabase, CompanyDatabase companyDatabase, InvoiceDatabase invoiceDatabase) {

        System.out.println("----------------------------");
        System.out.println("Tüm müşteriler:");
        customerDatabase.getAll()
                .stream()
                .forEach(System.out::println);

        //Yeni müşteri oluşturma.
        System.out.println("----------------------------");
        System.out.println("2 yeni müşteri ekleniyor:");
        customerDatabase.add("Mehmet Ceylan", ZonedDateTime.now());
        customerDatabase.add("Hatice Kırlangıç", ZonedDateTime.now());
        System.out.println("Tüm müşteriler:");
        customerDatabase.getAll()
                .stream()
                .forEach(System.out::println);

        System.out.println("----------------------------");
        System.out.println("İçerisinde C harfi bulunan müsteriler:");
        customerDatabase.filter(it -> it.getName().contains("C") || it.getName().contains("c")).forEach(it -> System.out.println(it.getName()));

        List<Customer> customersInJune = customerDatabase.filter(it -> it.getSignupDate().getMonth().equals(Month.JUNE) && it.getSignupDate().getYear() == 2022);
        BigDecimal totalAmountByUsersInJune = customersInJune
                .stream()
                .flatMap(invoices -> invoices.getInvoices().stream())
                .map(Invoice::getAmount)
                .reduce(new BigDecimal(0), BigDecimal::add);
        System.out.println("----------------------------");
        System.out.print("Haziran ayında kayıt olan müsterilerin faturalarının toplam tutarı:\t");
        System.out.println(totalAmountByUsersInJune.toString());

        System.out.println("----------------------------");
        System.out.println("Sistemdeki tüm faturalar:");
        invoiceDatabase.getAll().forEach(System.out::println);

        System.out.println("----------------------------");
        System.out.println("Sistemdeki 1500TL üstündeki faturalar:");
        invoiceDatabase.filter(it -> it.getAmount().intValue() > 1500).forEach(System.out::println);

        System.out.println("----------------------------");
        System.out.println("Sistemdeki 1500TL üstündeki faturaların ortalaması:");
        List<Invoice> invoicesBiggerThan1500 = invoiceDatabase.filter(it -> it.getAmount().intValue() > 1500);
        System.out.println(invoicesBiggerThan1500
                .stream()
                .map(Invoice::getAmount)
                .reduce(new BigDecimal(0), BigDecimal::add)
                .floatValue() / invoicesBiggerThan1500.size()
        );

        System.out.println("----------------------------");
        System.out.println("Sistemdeki 500TL üstündeki faturalara sahip müşterilerin isimleri:");
        customerDatabase
                .filter(customer -> customer
                        .getInvoices()
                        .stream()
                        .anyMatch(it -> it.getAmount().intValue() > 500)
                )
                .forEach(it -> System.out.println(it.getName()));

        System.out.println("----------------------------");
        System.out.println("Haziran ayı faturalarının ortalaması 750TL altı olan firmaların sektörleri:");
        List<Invoice> invoices;
        for (var company : companyDatabase.getAll()) {
            invoices = invoiceDatabase.filter(it -> it.getCompany().equals(company));
            float average = invoices.stream().map(Invoice::getAmount).reduce(new BigDecimal(0), BigDecimal::add).floatValue() / invoices.size();
            if (average < 750) {
                System.out.println(company.getField());
            }
        }

    }
}

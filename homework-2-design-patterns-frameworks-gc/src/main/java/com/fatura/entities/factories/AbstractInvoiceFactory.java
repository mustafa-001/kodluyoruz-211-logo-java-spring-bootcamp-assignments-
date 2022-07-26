package com.fatura.entities.factories;

import com.fatura.entities.Company;
import com.fatura.entities.Invoice;
import com.fatura.entities.PaidInvoice;
import com.fatura.entities.UnpaidInvoice;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

public class AbstractInvoiceFactory {

    //Based on the payment date decide which Invoice type to create.
    public Invoice get(ZonedDateTime date, BigDecimal amount, Company company, ZonedDateTime paymentDate){
        //If payment date is in the past return already paid object.
        if (paymentDate.isBefore(date)){
            return  new PaidInvoice(date, amount, company, paymentDate);
        } else {
            return  new UnpaidInvoice(date, amount, company, paymentDate);
        }
    }
}

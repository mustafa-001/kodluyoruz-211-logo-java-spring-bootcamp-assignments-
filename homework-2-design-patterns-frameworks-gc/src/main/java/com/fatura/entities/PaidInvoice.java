package com.fatura.entities;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

public class PaidInvoice extends Invoice {
    private ZonedDateTime paymentDate;
    public PaidInvoice(ZonedDateTime date, BigDecimal amount, Company company, ZonedDateTime paymentDate) {
        super(date, amount, company);
        this.paymentDate = paymentDate;
    }

    public ZonedDateTime getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(ZonedDateTime paymentDate) {
        this.paymentDate = paymentDate;
    }
}

package com.fatura.entities;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

public class UnpaidInvoice extends Invoice {
    private ZonedDateTime dueDate;
    public UnpaidInvoice(ZonedDateTime date, BigDecimal amount, Company company, ZonedDateTime dueDate) {
        super(date, amount, company);
        this.dueDate = dueDate;
    }

    public ZonedDateTime getDueDate() {
        return dueDate;
    }

    public void setDueDate(ZonedDateTime dueDate) {
        this.dueDate = dueDate;
    }
}

package com.logo.model;

import com.logo.model.enums.UnitType;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Currency;

//Common model for Product and Service in isbasi app.
//İşbaşı uygulamasındaki Ürün ve Hizmetler kısmındaki Ürün ve Hizmetler için ortak model.
@Data
abstract class ProductOrService {
    protected int id;
    protected String name;
     UnitType unitType;
    //KDV(Katma Değer Vergisi)/VAT(Value Added Tax)
    protected BigDecimal vatRate;
    protected Currency currency;
    //without VAT
    protected BigDecimal salesPrice;
    //without VAT
    protected BigDecimal purchasePrice;
    //tevkifat
    protected BigDecimal withholdingRatePercent;

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    protected boolean isActive;
    //ÖTV, ÖİV or similar other taxes.
    protected BigDecimal CESSRate;
}

package com.movies_payment.dto;

import com.movies_payment.entity.Payment;

import java.time.LocalDateTime;

public class PaymentDtoPaymentMapper {
    public static Payment toEntity(PaymentDto paymentDto){
        var result = new Payment();
        result.setUserId(paymentDto.getUserId());
        result.setPaymentTime(LocalDateTime.now());
        result.setPaymentType(paymentDto.getPaymentType());
        return result;
    }
}

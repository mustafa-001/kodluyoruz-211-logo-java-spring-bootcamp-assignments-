package com.movies_payment.entity;

import com.movies_payment.dto.PaymentType;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

/**
 * Entity representing a payment saved to database.
 */
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long paymentId;
    private Long userId;
    private PaymentType paymentType;
    private LocalDateTime paymentTime;

    public Long getUserId() {
        return this.userId;
    }
    public void setPaymentId(Long paymentId) {
        this.paymentId = paymentId;
    }
    public void setUserId(Long customerId) {
        this.userId = customerId;
    }
    public PaymentType getPaymentType() {
        return paymentType;
    }
    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }
    public LocalDateTime getPaymentTime() {
        return paymentTime;
    }
    public void setPaymentTime(LocalDateTime paymentTime) {
        this.paymentTime = paymentTime;
    }
    @Override
    public String toString() {
        return "Payment [paymentId=" + paymentId + ", paymentTime=" + paymentTime + ", paymentType=" + paymentType
                + ", userId=" + userId + "]";
    }
    public Long getPaymentId() {
        return paymentId;
    }
}

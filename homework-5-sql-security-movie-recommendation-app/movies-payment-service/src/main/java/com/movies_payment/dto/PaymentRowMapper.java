package com.movies_payment.dto;

import com.movies_payment.dto.PaymentType;
import com.movies_payment.entity.Payment;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class PaymentRowMapper implements RowMapper<Payment> {
    @Override
    public Payment mapRow(ResultSet rs, int rowNum) throws SQLException {
        Payment result = new Payment();
        result.setPaymentId(rs.getLong("payment_id"));
        result.setPaymentTime(rs.getTimestamp("payment_date").toLocalDateTime());
        result.setPaymentType(PaymentType.valueOf(rs.getInt("payment_type")));
        result.setUserId(rs.getLong("user_id"));
        return result;
    }
}

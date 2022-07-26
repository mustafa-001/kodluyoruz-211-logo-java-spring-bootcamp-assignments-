package com.movies_payment;

import com.movies_payment.dto.PaymentDto;
import com.movies_payment.dto.PaymentDtoPaymentMapper;
import com.movies_payment.dto.PaymentType;
import com.movies_payment.entity.Payment;
import com.movies_payment.repository.PaymentRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MoviesPaymentServiceApplication {

	public MoviesPaymentServiceApplication(PaymentRepository paymentRepository) {
		var samplePayment = new PaymentDto();
		samplePayment.setPaymentType(PaymentType.TWELVE_MONTHS);
		samplePayment.setUserId(1L);
		paymentRepository.save(PaymentDtoPaymentMapper.toEntity(samplePayment));
	}


	public static void main(String[] args) {
		SpringApplication.run(MoviesPaymentServiceApplication.class, args);
	}

}

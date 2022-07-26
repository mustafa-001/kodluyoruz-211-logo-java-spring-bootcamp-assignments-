package com.movies_payment.listener;

import com.movies_payment.dto.PaymentDto;
import com.movies_payment.dto.PaymentDtoPaymentMapper;
import com.movies_payment.repository.PaymentRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PaymentRequestListener {
	@Autowired
	private PaymentRepository paymentRepository;
	private final Logger log = LoggerFactory.getLogger(PaymentRequestListener.class);

	@RabbitListener(queues = "movies.payment.request")
	public boolean paymentRequestListener(PaymentDto paymentDto) {
		log.info(" [x] Received request for " + paymentDto);
		//By default returned resul is always Ok.
		var result = true;
		log.info(" [.] Returned " + result);
		log.info("Added to database {}",  paymentRepository.save(PaymentDtoPaymentMapper.toEntity(paymentDto)));
		return result;
	}

}

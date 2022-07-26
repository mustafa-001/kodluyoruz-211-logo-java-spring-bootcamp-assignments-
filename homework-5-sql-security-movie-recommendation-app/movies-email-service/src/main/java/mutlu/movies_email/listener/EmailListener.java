package mutlu.movies_email.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mutlu.movies_email.dto.EmailDto;
import mutlu.movies_email.repository.EmailRepository;


@Service
public class EmailListener {
	private final Logger log = LoggerFactory.getLogger(EmailListener.class);

	@Autowired
	EmailRepository emailRepository;
	/**
	 * Listens on queue named movies.email and sends an email given in the emailDto.
	 * @param emailDto
	 */
	@RabbitListener(queues = "movies.email")
	public void emailListener(EmailDto emailDto) {
		log.info("{}", emailDto);
		sendEmail(emailDto);
		emailRepository.save(emailDto);
	}

	private void sendEmail(EmailDto emailDto) {
		System.out.println("Sayın "+ emailDto.getName()+ "\n"+ "Sisteme "+ emailDto.getTitle()+ " adlı film eklenmiştir. " + emailDto.getText() );
	}

}

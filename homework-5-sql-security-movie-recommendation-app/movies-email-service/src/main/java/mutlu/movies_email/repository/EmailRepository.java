package mutlu.movies_email.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import mutlu.movies_email.dto.EmailDto;

public interface EmailRepository extends MongoRepository<EmailDto, String> {


}
package mutlu.movies.service;

import mutlu.movies.dto.ChangePasswordDto;
import mutlu.movies.dto.ChangeUsernameDto;
import mutlu.movies.dto.LoginCredentialsDto;
import mutlu.movies.dto.PaymentDto;
import mutlu.movies.entity.User;
import mutlu.movies.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AmqpTemplate rabbitTemplate;

    Logger log = LoggerFactory.getLogger(UserService.class);

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, AmqpTemplate rabbitTemplate) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.rabbitTemplate = rabbitTemplate;
    }

    /**
     * Creates and saves to database a new User entity with it's password hashed with {@link PasswordEncoder}.
     */
    public User create(User request) {
        request.setPasswordHash(passwordEncoder.encode(request.getPasswordHash()));
        log.info("Saving new user: {}", request);
        return userRepository.save(request);
    }

    public Optional<User> getByUserId(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        log.debug("User for id {} is {}", userId, user.orElse(null));
        return user;
    }

    public User update(User request, Long movieId) {
        return request;
    }

    public void delete(Long userId) {
        userRepository.deleteById(userId);
    }

    /**
     * Tries to log user in, if successful returns that User entity.
     */
    public User login(LoginCredentialsDto credentialsDto) {
        return login(credentialsDto.getUsername(), credentialsDto.getPassword());
    }

    /**
     * Tries to log user in, if successful returns that User entity.
     */
    private User login(String username, String passwordHash) {
        var userOpt = userRepository.findByUsername(username);
        if (userOpt.isPresent()) {
            var user = userOpt.get();
            if (passwordEncoder.matches(passwordHash, user.getPasswordHash())) {
                log.info("User logged in {}", user);
                return user;
            } else {
                log.info("Wrong password when logging in. Username: {}", username);
                throw new RuntimeException("Username or password is wrong.");
            }
        } else {
            log.info("User doesn't exists: {}", username);
            throw new RuntimeException("Username or password is wrong.");
        }
    }

    /**
     * If logging user in is successfull (to make sure credentials right)
     * changes user's username.
     */
    public User changeUsername(ChangeUsernameDto usernameDto) {
        //Authenticate the user.
        var user = login(usernameDto.getUsername(), usernameDto.getPassword());
        log.info("Changing username for {} to {}", user.getUsername(), usernameDto.getNewUsername());
        user.setUsername(usernameDto.getNewUsername());
        log.debug("Username before flush: {}", user.getUsername());
        userRepository.flush();
        log.debug("Username after flush: {}", user.getUsername());
        return user;
    }

    /**
     * Compares {@link ChangePasswordDto}'s newPassword field's hash values and logs user.
     * If both is successful changes user's passwordHash fiels to newPassword's hash value.
     */
    public User changePassword(ChangePasswordDto changePasswordDto) {
        //Authenticate the user.
        var user = login(changePasswordDto.getUsername(), changePasswordDto.getPassword());
        log.info("Changing password for {}: ", user.getUsername());
        if (changePasswordDto.getFirstNewPassword().equals(changePasswordDto.getSecondNewPassword())) {
            user.setPasswordHash(passwordEncoder.encode(changePasswordDto.getFirstNewPassword()));
            log.debug("Password hash before flush {}", userRepository.findById(user.getUserId()).get().getPasswordHash());
            userRepository.flush();
            log.debug("Password hash after flush {}", userRepository.findById(user.getUserId()).get().getPasswordHash());
        } else {
            throw new RuntimeException("Passwords does not match.");
        }
        return user;
    }

    /**
     * Makes a request to payment service with PaymentDetails and if response is true
     * update said user's premiumUntil field to {@link mutlu.movies.dto.PaymentType} months later.
     * For synchronous request using RabbitMQ Remote Procedure Call. {@link <a href="https://www.rabbitmq.com/tutorials/tutorial-six-spring-amqp.html">...</a>}
     */
    public User makePayment(PaymentDto paymentDetailsDto) {
        var userOpt = userRepository.findById(paymentDetailsDto.getUserId());
        log.info("Making payment for {} with details {}.", userOpt.orElse(null), paymentDetailsDto);
        if (userOpt.isPresent()) {
            var user = userOpt.get();
            //send request to payment service and wait for verification.
            log.info("Sending request to payment service");
            var reply = (Boolean) rabbitTemplate.convertSendAndReceive("movies.payment.request", paymentDetailsDto);
            log.info("Reply from payment service: {} ", reply);
            if (reply == null) {
                log.info("No response came from payment service.");
                throw new RuntimeException("An error occurred when processing payment.");
            } else if (reply) {
                log.debug("userId: {}, premiumUntil: {}", user.getUserId(), user.getPremiumUntil());
                user.setPremiumUntil(LocalDateTime.now().plusMonths(paymentDetailsDto.getPaymentType().getValue()));
                userRepository.flush();
                log.info("Updated user to {}", user);
            } else {
                log.info("Payment service didn't authorize payment.");
                throw new RuntimeException("An error occurred when processing payment.");
            }
            return user;
        } else {
            log.debug("User with userId {} doesn't exists.", paymentDetailsDto.getUserId());
            throw new RuntimeException("Username or password is wrong.");
        }
    }
}

package mutlu.movies.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import mutlu.movies.dto.ChangePasswordDto;
import mutlu.movies.dto.ChangeUsernameDto;
import mutlu.movies.dto.LoginCredentialsDto;
import mutlu.movies.dto.PaymentDto;
import mutlu.movies.entity.User;
import mutlu.movies.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    static private final String userExample = """
            {
              "userId": 0,
              "username": "User 1",
              "email": "user@email.com",
              "passwordHash": "şifre",
              "premiumUntil": "2022-01-01T12:13:55.085Z"
            }""";
    static private final String loginDtoExample = """
            {
            'username':	'User 1',
            'password':	'şifre'
            }""";
    static private final String paymentDtoExample = """
            {
               "userId": 1,
               "paymentType": "SIX_MONTHS"
             }""";
    static private final String changeUsernameDtoExample = """
            {
              "username": "username1",
              "newUsername": "New Username",
              "password": "şifre"
            }""";
    static private final String changePasswordDtoExample = """
            {
               "username": "username1",
               "password": "şifre",
               "firstNewPassword": "yeni şifre",
               "secondNewPassword": "yeni şifre"
             }""";

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @Operation(summary = "Get the user with given Id.")
    @GetMapping("/{userId}")
    public Optional<User> getById(@PathVariable Long userId) {
        return userService.getByUserId(userId);
    }

    @Operation(summary = "Add a new user.")
    @PostMapping
    public User add(@RequestBody
                    @io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content(examples = @ExampleObject(value = userExample),
                            mediaType = MediaType.APPLICATION_JSON_VALUE))
                    User request) {
        return userService.create(request);
    }

    @Operation(summary = "Login.")
    @PostMapping("/login")
    public User login(@RequestBody
                      @io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content(examples = @ExampleObject(value = loginDtoExample),
                              mediaType = MediaType.APPLICATION_JSON_VALUE))
                      LoginCredentialsDto creditenalsDto) {
        return userService.login(creditenalsDto);
    }

    @Operation(summary = "Change username.")
    @PostMapping("/changeUsername")
    public User changeUsername(@RequestBody
                               @io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content(examples = @ExampleObject(value = changeUsernameDtoExample),
                                       mediaType = MediaType.APPLICATION_JSON_VALUE))
                               ChangeUsernameDto changeUsernameDto) {
        return userService.changeUsername(changeUsernameDto);
    }

    @Operation(summary = "Change password.")
    @PostMapping("/changePassword")
    public User changePassword(@RequestBody
                               @io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content(examples = @ExampleObject(value = changePasswordDtoExample),
                                       mediaType = MediaType.APPLICATION_JSON_VALUE))
                               ChangePasswordDto changePasswordDto) {
        return userService.changePassword(changePasswordDto);
    }


    @Operation(summary = "Make a payment.")
    @PostMapping("/payment")
    public User makePayment(@RequestBody
                            @io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content(examples = @ExampleObject(value = paymentDtoExample),
                                    mediaType = MediaType.APPLICATION_JSON_VALUE))
                            PaymentDto paymentDetailsDto) {
        return userService.makePayment(paymentDetailsDto);
    }

    @Operation(summary = "Delete the user with given Id.")
    @DeleteMapping("/{userId}")
    public void delete(@PathVariable @Parameter(example = "1") Long userId) {
        userService.delete(userId);
    }
}

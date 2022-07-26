package mutlu.movies.dto;

/**
 * Dto to send from client when requesting a login.
 * {@link mutlu.movies.controller.UserController#login(LoginCredentialsDto)}
 */
public class LoginCredentialsDto {
    private String username;
    private String password;

    public LoginCredentialsDto() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}


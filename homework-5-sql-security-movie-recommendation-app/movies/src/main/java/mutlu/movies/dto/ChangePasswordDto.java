package mutlu.movies.dto;

/**
 * Dto to be send from client on password change requests.
 * {@link mutlu.movies.controller.UserController#changePassword(ChangePasswordDto)}
 */
public class ChangePasswordDto {
    private String username;
    private String password;
    private String firstNewPassword;
    private String secondNewPassword;

    public ChangePasswordDto() {
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstNewPassword() {
        return firstNewPassword;
    }

    public void setFirstNewPassword(String firstNewPassword) {
        this.firstNewPassword = firstNewPassword;
    }

    public String getSecondNewPassword() {
        return secondNewPassword;
    }

    public void setSecondNewPassword(String secondNewPassword) {
        this.secondNewPassword = secondNewPassword;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}

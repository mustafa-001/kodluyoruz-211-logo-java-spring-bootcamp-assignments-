package mutlu.movies.dto;

/**
 * Dto to be send from client on username change requests.
 * {@link mutlu.movies.controller.UserController#changeUsername(ChangeUsernameDto)}
 */
public class ChangeUsernameDto {
    private String username;
    private String newUsername;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNewUsername() {
        return newUsername;
    }

    public void setNewUsername(String newUsername) {
        this.newUsername = newUsername;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

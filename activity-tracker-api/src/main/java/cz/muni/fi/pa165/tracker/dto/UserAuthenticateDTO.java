package cz.muni.fi.pa165.tracker.dto;

import javax.validation.constraints.NotNull;

/**
 * DTO for user authenticate.
 *
 * @author Petr Ondřejková
 * @version 09.11. 2016
 */
public class UserAuthenticateDTO {
    @NotNull
    private Long userId;

    @NotNull
    private String password;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

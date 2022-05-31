package ch.bbw.pg.member;

import ch.bbw.pg.security.ValidPassword;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * @author Philipp Gatzka
 * @version 31.05.2022
 */
@Getter
@Setter
@ToString
public class RegisterMember {

    @NotEmpty(message = "username may not be empty")
    @Size(min = 5, max = 51, message = "username: length 0 - 50 required")
    private String username;

    @NotEmpty(message = "firstname may not be empty")
    @Size(min = 2, max = 25, message = "firstname: length 0 - 50 required")
    private String firstname;

    @NotEmpty(message = "lastname may not be empty")
    @Size(min = 2, max = 25, message = "lastname: length 0 - 50 required")
    private String lastname;

    @NotEmpty(message = "password may not be empty")
    @ValidPassword
    private String password;

    @NotEmpty(message = "confirmation may not be empty")
    private String confirmation;

    private String errorMessage;


    public void setFirstname(String firstname) {
        this.firstname = firstname;
        this.username = updateUsername();
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
        this.username = updateUsername();
    }

    private String updateUsername() {
        return (this.firstname + "." + this.lastname).toLowerCase();
    }
}

package ch.bbw.pg.member;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * @author Philipp Gatzka
 * @version 31.05.2022
 */
@Entity
@Table(name = "member")
public class Member {
    @Id
    @GeneratedValue(generator = "generatorMember", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "generatorMember", initialValue = 20)
    private Long id;

    @NotEmpty(message = "firstname may not be empty")
    @Size(min = 2, max = 512, message = "firstname: Length 2 - 25 required")
    private String firstname;

    @NotEmpty(message = "lastname may not be empty")
    @Size(min = 2, max = 20, message = "lastname: Length 2 - 25 required")
    private String lastname;

    @NotEmpty(message = "password may not be empty")
    private String password;

    @NotEmpty(message = "username may not be empty")
    @Size(min = 5, max = 51, message = "password: Length 1 - 51 required")
    private String username;

    private String authority;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    @Override
    public String toString() {
        return "Member{id=" + id + ", firstname='" + firstname + "', lastname='" + lastname + "', password='" + password + "', username='" + username + "', authority='" + authority + "'}";
    }
}

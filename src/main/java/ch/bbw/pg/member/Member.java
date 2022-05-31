package ch.bbw.pg.member;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * @author Philipp Gatzka
 * @version 31.05.2022
 */
@Entity
@Table(name = "member")
@Getter
@Setter
@ToString
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

    public static Member fromRegisterMember(RegisterMember registerMember, String hashedPassword) {
        Member member = new Member();
        member.setFirstname(registerMember.getFirstname());
        member.setLastname(registerMember.getLastname());
        member.setUsername(registerMember.getUsername());
        member.setAuthority("member");
        member.setPassword(hashedPassword);
        return member;
    }

}

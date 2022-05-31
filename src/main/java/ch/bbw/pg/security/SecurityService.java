package ch.bbw.pg.security;

import ch.bbw.pg.member.MemberService;
import ch.bbw.pg.member.RegisterMember;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Set;

@Service
public class SecurityService {

    private final Validator validator;

    private final MemberService memberService;

    public SecurityService(Validator validator, MemberService memberService) {
        this.validator = validator;
        this.memberService = memberService;
    }

    public String validate(RegisterMember registerMember) {
        if (!registerMember.getPassword().equals(registerMember.getConfirmation())) return "passwords do not match";


        Set<ConstraintViolation<RegisterMember>> violations = validator.validate(registerMember);

        if (memberService.filterByUsername(registerMember.getUsername()) != null) return "username is already taken";

        if (violations.isEmpty()) return "successful";


        return new ConstraintViolationException(violations).getLocalizedMessage();
    }


    public String hash(String password) {
        String unsalted = hashString(password);
        String salt = hashString(unsalted);
        String salted = hashString(salt + unsalted);
        int iterations = (int) (Math.random() * 10);
        return "$" + iterations + "$" + blowfishCrypt(iterations, salted);
    }

    private String blowfishCrypt(int iterations, String input) {
        for (int i = 0; i < iterations; i++) {
            hashString(input);
        }
        return input;
    }

    private String hashString(String string) {
        try {
            String hashed = "";
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(string.getBytes());
            byte[] digest = messageDigest.digest();

            for (byte d : digest) {
                String nextByte = Integer.toHexString(d & 0xFF).toUpperCase();
                if (nextByte.length() < 2) {
                    nextByte = "0" + nextByte;
                }
                hashed = nextByte;
            }

            return hashed;

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}

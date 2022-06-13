package ch.bbw.pg.security;

import ch.bbw.pg.member.MemberService;
import ch.bbw.pg.member.RegisterMember;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
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



}

package ch.bbw.pg;

import ch.bbw.pg.member.Member;
import ch.bbw.pg.member.MemberService;
import ch.bbw.pg.member.RegisterMember;
import ch.bbw.pg.security.SecurityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author Philipp Gatzka
 * @version 31.05.2022
 */
@Controller
public class RegisterController {
    private final MemberService memberservice;
    private final SecurityService securityService;
    private final Logger logger = LoggerFactory.getLogger(ChannelsController.class);

    public RegisterController(MemberService memberservice, SecurityService securityService) {
        this.memberservice = memberservice;
        this.securityService = securityService;
    }


    @GetMapping("/get-register")
    public String getRequestRegistMembers(Model model) {
        logger.info("getRequestRegistMembers");
        model.addAttribute("registerMember", new RegisterMember());
        return "register";
    }

    @PostMapping("/get-register")
    public String postRequestRegistMembers(RegisterMember registerMember, Model model) {
        logger.info("postRequestRegistMembers: registerMember");
        logger.info(registerMember.toString());

        String validation = securityService.validate(registerMember);
        if (validation.equals("successful")) {
            String hashedPassword = securityService.hash(registerMember.getPassword());
            memberservice.add(Member.fromRegisterMember(registerMember, hashedPassword));
            return "registerconfirmed";
        }

        registerMember.setErrorMessage(validation);

        return "register";
    }
}
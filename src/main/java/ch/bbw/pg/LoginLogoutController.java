package ch.bbw.pg;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Philipp Gatzka
 * @version 13.06.2022
 */
@Controller
public class LoginLogoutController {

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/logout")
    public String logout(){
        return "logout";
    }


}

package ch.bbw.pg;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import ch.bbw.pg.member.MemberService;
import ch.bbw.pg.member.RegisterMember;
/**
 * RegisterController
 * @author Peter Rutschmann
 * @version 26.03.2020
 */
@Controller
public class RegisterController {
	@Autowired
	MemberService memberservice;

	@GetMapping("/get-register")
	public String getRequestRegistMembers(Model model) {
		System.out.println("getRequestRegistMembers");
		model.addAttribute("registerMember", new RegisterMember());
		return "register";
	}
	
	@PostMapping("/get-register")
	public String postRequestRegistMembers(RegisterMember registerMember, Model model) {
		System.out.println("postRequestRegistMembers: registerMember");
		System.out.println(registerMember);

		//Hier ergänzen
		
		return "registerconfirmed";
	}
}
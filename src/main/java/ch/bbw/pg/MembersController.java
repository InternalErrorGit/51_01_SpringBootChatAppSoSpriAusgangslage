package ch.bbw.pg;

import ch.bbw.pg.member.Member;
import ch.bbw.pg.member.MemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Philipp Gatzka
 * @version 31.05.2022
 */
@Controller
public class MembersController {
    private final Logger logger = LoggerFactory.getLogger(ChannelsController.class);
    private final MemberService memberservice;

    public MembersController(MemberService memberservice) {
        this.memberservice = memberservice;
    }

    @GetMapping("/get-members")
    public String getRequestMembers(Model model) {
        logger.info("getRequestMembers");
        model.addAttribute("members", memberservice.getAll());
        return "members";
    }

    @GetMapping("/edit-member")
    public String editMember(@RequestParam(name = "id", required = true) long id, Model model) {
        Member member = memberservice.getById(id);
        logger.info("editMember get: " + member);
        model.addAttribute("member", member);
        return "editmember";
    }

    @PostMapping("/edit-member")
    public String editMember(Member member, Model model) {
        logger.info("editMember post: edit member" + member);
        Member value = memberservice.getById(member.getId());
        value.setAuthority(member.getAuthority());
        logger.info("editMember post: update member" + value);
        memberservice.update(value);
        return "redirect:/get-members";
    }

    @GetMapping("/delete-member")
    public String deleteMember(@RequestParam(name = "id", required = true) long id, Model model) {
        logger.info("deleteMember: " + id);
        memberservice.deleteById(id);
        return "redirect:/get-members";
    }
}

package ch.bbw.pg;

import ch.bbw.pg.member.Member;
import ch.bbw.pg.member.MemberService;
import ch.bbw.pg.message.Message;
import ch.bbw.pg.message.MessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Date;

/**
 * @author Philipp Gatzka
 * @version 31.05.2022
 */
@Controller
public class ChannelsController {

    private final MessageService messageservice;

    private final MemberService memberservice;

    private final Logger logger = LoggerFactory.getLogger(ChannelsController.class);

    public ChannelsController(MessageService messageservice, MemberService memberservice) {
        this.messageservice = messageservice;
        this.memberservice = memberservice;
    }

    @GetMapping("/get-channel")
    public String getRequestChannel(Model model) {
        logger.info("getRequestChannel");

        model.addAttribute("messages", messageservice.getAll());

        Message message = new Message();
        message.setContent("Der zweite Pfeil trifft immer.");
        logger.info("message: " + message);
        model.addAttribute("message", message);
        return "channel";
    }

    @PostMapping("/add-message")
    public String postRequestChannel(Model model, @ModelAttribute @Valid Message message, BindingResult bindingResult) {
        logger.info("postRequestChannel(): message: " + message.toString());
        if (bindingResult.hasErrors()) {
            logger.info("postRequestChannel(): has Error(s): " + bindingResult.getErrorCount());
            model.addAttribute("messages", messageservice.getAll());
            return "channel";
        }
        // Hack solange es kein authenticated member hat
        Member tmpMember = memberservice.getById(4L);
        message.setAuthor(tmpMember.getFirstname() + " " + tmpMember.getLastname());
        message.setOrigin(new Date());
        logger.info("message: " + message);
        messageservice.add(message);

        return "redirect:/get-channel";
    }
}

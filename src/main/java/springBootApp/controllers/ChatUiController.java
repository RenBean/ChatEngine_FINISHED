package springBootApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;
import springBootApp.entities.Chat;
import springBootApp.entities.ChatDAO;

import java.sql.Timestamp;

@Controller
@RequestMapping("/chatui/")
public class ChatUiController {

    @Autowired
    private ChatDAO chatDAO;

    @RequestMapping(value="/")
    public String chatUi(ModelMap model) {
        model.addAttribute("allChats",chatDAO.findAll());
        return "chatui/chat";
    }

    @RequestMapping(value="add")
    public View addChat(String newChat) {
        try {
            Chat chat = new Chat();
            chat.setMessage(newChat);
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            chat.setSender(auth.getName());
            chat.setWhenSent(new Timestamp(System.currentTimeMillis()));
            chatDAO.save(chat);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new RedirectView("/chatui/");
    }
}

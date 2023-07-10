package br.com.businesstec.springauthentication.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import br.com.businesstec.springauthentication.model.User;

@Controller
@RequestMapping("/index")
public class IndexController {

    @GetMapping
    public String home(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("userId", user.getId());
        return "index";
    }

}

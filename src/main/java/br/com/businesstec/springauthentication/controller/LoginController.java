package br.com.businesstec.springauthentication.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class LoginController {

    
    @GetMapping("/login")
    public String login() {
        return "login";
    }
    
    @GetMapping("login-error")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "login";
    }
    
    @GetMapping("login-cadastro")
    public String loginCadastro(Model model) {
        model.addAttribute("loginCadastro", true);
        return "login";
    }
    
    
}

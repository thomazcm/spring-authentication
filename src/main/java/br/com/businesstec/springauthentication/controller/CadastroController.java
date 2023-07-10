package br.com.businesstec.springauthentication.controller;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import br.com.businesstec.springauthentication.endpoint.form.UserForm;
import br.com.businesstec.springauthentication.model.Profile;
import br.com.businesstec.springauthentication.model.User;
import br.com.businesstec.springauthentication.repository.ProfileRepository;
import br.com.businesstec.springauthentication.repository.UserRepository;

@Controller
@RequestMapping("/cadastro")
public class CadastroController {

    private static final String ROLE_USER = "ROLE_USER";
    private final UserRepository userRepository;
    private final ProfileRepository profileRepository;
    private final BCryptPasswordEncoder encoder;

    public CadastroController(UserRepository userRepository, ProfileRepository profileRepository, BCryptPasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.profileRepository = profileRepository;
        this.encoder = encoder;
    }
    
    @GetMapping
    public String cadastro(UserForm userForm) {
        return "cadastro";
    }

    @PostMapping
    public String createUser(UserForm userForm) {
        
        String userEmail = userForm.getEmail();
        String userPassword = encoder.encode(userForm.getPassword());
        Profile userProfile = profileRepository.findByName(ROLE_USER)
                .orElseThrow(() -> new RuntimeException("Error creating user: profile not found!"));

        var newUser = userRepository.save(new User(userEmail, userPassword));
        newUser.addProfile(userProfile);
        return "redirect:/login-cadastro";
    }


}

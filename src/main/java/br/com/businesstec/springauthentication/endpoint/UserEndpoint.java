package br.com.businesstec.springauthentication.endpoint;

import java.net.URI;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import br.com.businesstec.springauthentication.endpoint.dto.UserDto;
import br.com.businesstec.springauthentication.endpoint.form.UserForm;
import br.com.businesstec.springauthentication.model.Profile;
import br.com.businesstec.springauthentication.model.User;
import br.com.businesstec.springauthentication.repository.ProfileRepository;
import br.com.businesstec.springauthentication.repository.UserRepository;

@RestController
@RequestMapping("/users")
public class UserEndpoint {

    private static final String ROLE_USER = "ROLE_USER";
    private final UserRepository userRepository;
    private final ProfileRepository profileRepository;

    public UserEndpoint(UserRepository userRepository, ProfileRepository profileRepository) {
        this.userRepository = userRepository;
        this.profileRepository = profileRepository;
    }

    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserForm form, BCryptPasswordEncoder encoder) {

        String userEmail = form.getEmail();
        String userPassword = encoder.encode(form.getPassword());
        Profile userProfile = profileRepository.findByName(ROLE_USER)
                .orElseThrow(() -> new RuntimeException("Error creating user: profile not found!"));
        
        var newUser = userRepository.save(new User(userEmail, userPassword));
        newUser.addProfile(userProfile);
        
        URI uri = UriComponentsBuilder.fromPath("/user/{id}").buildAndExpand(newUser.getId()).toUri();
        return ResponseEntity.created(uri).body(new UserDto(newUser));
    }

}

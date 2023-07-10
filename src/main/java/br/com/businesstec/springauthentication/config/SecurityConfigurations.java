package br.com.businesstec.springauthentication.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import br.com.businesstec.springauthentication.repository.UserRepository;

@EnableWebSecurity
@Configuration
public class SecurityConfigurations {

    private final AuthenticationService authenticationService;

    public SecurityConfigurations(AuthenticationService authenticationService, UserRepository userRepository) {
        this.authenticationService = authenticationService;
    }

    @Bean
    public SecurityFilterChain filterChan(HttpSecurity http) throws Exception {
        return http
                .authorizeRequests()
                .antMatchers("/auth/**", "/login/**").permitAll()
                .antMatchers(HttpMethod.POST, "/users/**").permitAll()
                .anyRequest().authenticated()
                .and().formLogin(form -> {
                    form.defaultSuccessUrl("/index", true)
                    .loginPage("/loginPage")
                    .failureUrl("/loginPage")
                    .permitAll();
                })
                .httpBasic()
                .and().csrf().disable()
                .build();
    }

    public void configureGlobal(AuthenticationManagerBuilder auth, BCryptPasswordEncoder encoder) throws Exception {
        auth.userDetailsService(authenticationService).passwordEncoder(encoder);
    }
    
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    

}

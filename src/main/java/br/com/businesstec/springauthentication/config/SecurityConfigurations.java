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
import org.springframework.security.web.SecurityFilterChain;
import br.com.businesstec.springauthentication.repository.UserRepository;

@EnableWebSecurity
@Configuration
public class SecurityConfigurations {

    private static final String ADMIN = "ADMIN";
    private final AuthenticationService authenticationService;

    public SecurityConfigurations(AuthenticationService authenticationService, UserRepository userRepository) {
        this.authenticationService = authenticationService;
    }

    @Bean
    public SecurityFilterChain filterChan(HttpSecurity http) throws Exception {
        return http
                .authorizeRequests()
                .antMatchers("/login/**", "/login-cadastro", "/logout/**", "/cadastro/**").permitAll() // Requests que podem ser acessadas sem autenticação
                .antMatchers(HttpMethod.POST, "/users-api").permitAll() // Cadastro de novos usuários
                .antMatchers(HttpMethod.POST, "/users-api/admin").hasRole(ADMIN) // Cadastro de novos administradores apenas por administradores
                .anyRequest().authenticated()
                .and().formLogin(form -> { // Configurações do endpoint que o Spring fará o login
                    form.defaultSuccessUrl("/index", true) // para onde redireciona após logado
                            .loginPage("/login")
                            .failureUrl("/login-error")
                            .permitAll();
                })
                .logout(logout -> { // Já configura automaticamente um endpoint que encerra a sessão e redireciona
                    logout.logoutUrl("/logout")
                            .logoutSuccessUrl("/login");
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
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}

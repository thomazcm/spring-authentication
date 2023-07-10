package br.com.businesstec.springauthentication.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.businesstec.springauthentication.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String username);

}

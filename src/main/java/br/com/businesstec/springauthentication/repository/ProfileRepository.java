package br.com.businesstec.springauthentication.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import br.com.businesstec.springauthentication.model.Profile;

public interface ProfileRepository extends JpaRepository<Profile, Long>{

    Optional<Profile> findByName(String name);
    
}

package br.com.businesstec.springauthentication.config;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import br.com.businesstec.springauthentication.model.Profile;

@Component
public class ProfileDataInitializer implements CommandLineRunner {
    
    private static final String ROLE_USER = "ROLE_USER";
    private static final String ROLE_ADMIN = "ROLE_ADMIN";
    
    @Autowired
    private EntityManager em;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        if (em.createQuery("SELECT p FROM Profile p WHERE p.name = :name")
                .setParameter("name", ROLE_USER)
                .getResultList().isEmpty()) {
            Profile userRole = new Profile();
            userRole.setName(ROLE_USER);
            em.persist(userRole);
        }
        
        if (em.createQuery("SELECT p FROM Profile p WHERE p.name = :name")
                .setParameter("name", ROLE_ADMIN)
                .getResultList().isEmpty()) {
            Profile adminRole = new Profile();
            adminRole.setName(ROLE_ADMIN);
            em.persist(adminRole);
        }
    }
}
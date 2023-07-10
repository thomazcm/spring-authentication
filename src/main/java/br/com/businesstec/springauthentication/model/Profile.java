package br.com.businesstec.springauthentication.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import org.springframework.security.core.GrantedAuthority;

@Entity
public class Profile implements GrantedAuthority {
    
    private static final long serialVersionUID = -1L;
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    
    @ManyToMany(mappedBy = "profiles")
    private Set<User> users = new HashSet<>();
    
    public void addUser(User user) {
        users.add(user);
    }
        
    
    @Override
    public String getAuthority() {
        return name;
    }


    public String getName() {
        return this.name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public Long getId() {
        return this.id;
    }


    public Set<User> getUsers() {
        return this.users;
    }

}

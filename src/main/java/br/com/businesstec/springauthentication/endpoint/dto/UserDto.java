package br.com.businesstec.springauthentication.endpoint.dto;

import br.com.businesstec.springauthentication.model.User;

public class UserDto {
    
    private String email;

    public UserDto(User user) {
        this.email = user.getEmail();
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    
    
}

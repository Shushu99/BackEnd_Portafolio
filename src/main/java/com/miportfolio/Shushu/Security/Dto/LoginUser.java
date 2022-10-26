package com.miportfolio.Shushu.Security.Dto;

import javax.validation.constraints.NotBlank;

public class LoginUser {
    @NotBlank
    private String nombreUser;
    @NotBlank
    private String password;

    public String getNombreUser() {
        return nombreUser;
    }

    public void setNombreUser(String nombreUser) {
        this.nombreUser = nombreUser;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
   
}

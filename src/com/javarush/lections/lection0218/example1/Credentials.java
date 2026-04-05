package com.javarush.lections.lection0218.example1;

import java.util.Set;

class Credentials{
    private final Set<Role> roles;

    public Credentials(Set<Role> roles) {
        this.roles = roles;
    }
    public Set<Role> getRoles(){
        return roles;
    }
}

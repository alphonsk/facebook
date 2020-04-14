package com.george.facebook.model;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @NotNull(message = "{user.username.notnull}")
    @Size(min = 4, max = 20, message = "{user.username.size}" )
    private String username;

    @NotNull(message = "{user.password.notnull}")
    @Size(min = 4, message = "{user.password.size}" )
    private String password;

    private String authority;

    private int enabled;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public int getEnabled() {
        return enabled;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }
}


//@Entity
//public class User {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
////    @GeneratedValue(strategy = GenerationType.AUTO)
//    @Column(name = "id")
//    private long id;
//    @Column( unique = true)
//    private String email;
//    private String password;
//    private String authority;
//
//
//
//    public User(String authority, String email, String password) {
//        this.authority = authority;
//        this.email = email;
//        this.password = password;
//    }
////    private String confirmPassword;
////    private Date date;
//
//
//    public User() {
//    }
//
//    public long getId() {
//        return id;
//    }
//
//    public void setId(long id) {
//        this.id = id;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public String getAuthority() {
//        return authority;
//    }
//
//    public void setAuthority(String authority) {
//        this.authority = authority;
//    }
//
//    //
//}
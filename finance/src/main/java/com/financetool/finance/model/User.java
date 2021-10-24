package com.financetool.finance.model;

import javax.persistence.*;
import javax.validation.constraints.Email;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer userId;
    private String firstName;
    private String lastName;

    @Column(name="email", unique = true, nullable = false)
    @Email
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    public Integer getId() {
        return userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public RoleType getRoleType() {
        return roleType;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRoleType(RoleType roleType) {
        this.roleType = roleType;
    }
}

package com.financetool.finance.dto;

import com.financetool.finance.model.RoleType;

import javax.management.relation.Role;
import java.util.Set;

public class UserOutDto {
    private Integer id;
    private String firstName;
    private String lastName;
    public String email;
    public RoleType roleType;

    public Integer getId() {
        return id;
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

    public RoleType getRoleType() {
        return roleType;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public void setRoleType(RoleType roleType) {
        this.roleType = roleType;
    }

}

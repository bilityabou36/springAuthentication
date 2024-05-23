package com.unknowCoder.model;


import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name="roles")
public class Role implements GrantedAuthority {

    public Role(Integer roleId) {
        this.roleId = roleId;
    }

    public Role(int i, String user) {
    }
    // No-argument constructor
    public Role() {
    }
    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name= "role_id")
    private Integer roleId;
    private String authority;

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public Role(String authority) {
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return this.authority;
    }
}

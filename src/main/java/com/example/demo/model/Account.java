package com.example.demo.model;

import org.hibernate.annotations.ColumnDefault;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Entity
@Table(name = "accounts")
@Data
//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Account {

    @Id
    private Long id;

    @OneToOne(cascade = CascadeType.ALL, targetEntity = User.class)
    private User user;

    @NotBlank
    @Column(unique = true)
    private String username;

    @Column(length = 60)
    @NotBlank
    private String password;

//    private Set<? extends GrantedAuthority> grantedAuthorities;

    @NotBlank
    @Column(columnDefinition = "bool default true")
    private boolean isAccountNonExpired;

    @NotBlank
    @ColumnDefault("true")
    private boolean isAccountNonLocked;

    @NotBlank
    private boolean isCredentialsNonExpired;

    @NotBlank
    private boolean isEnabled;
}


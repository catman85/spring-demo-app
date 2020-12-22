package com.example.demo.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "accounts")
@Data
//@Builder // if we use this pattern the default bools won't work
public class Account {
    @Id
    @GeneratedValue(generator = "user_generator")
    @SequenceGenerator(
            name = "user_generator",
            sequenceName = "user_sequence",
            initialValue = 1
    )
    @Column(name = "id")
    private Long id;

    @OneToOne(cascade = CascadeType.ALL, targetEntity = User.class)
    private User user;

    @Column(unique = true)
    private String username;

    @Column(length = 60)
    private String password;

    private String role;

    private boolean isAccountNonExpired = true;

    private boolean isAccountNonLocked = true;

    private boolean isCredentialsNonExpired = true;

    private boolean isEnabled = true;
}


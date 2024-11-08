package com.org.java4.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "USERS", schema = "dbo")
public class Users {
    @Id
    @Column(name = "ID", nullable = false, length = 14)
    private String id;

    @Column(name = "PASSWORD", nullable = false, length = 50)
    private String password;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "FULLNAME", length = 50)
    private String fullname;

    @Column(name = "ADMIN")
    private Boolean admin;

    @OneToMany(mappedBy = "userid")
    private Set<Favorite> favorites = new LinkedHashSet<>();

    @OneToMany(mappedBy = "userid")
    private Set<Share> shares = new LinkedHashSet<>();

}
package com.org.java4.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "VIDEO", schema = "dbo")
public class Video {
    @Id
    @Column(name = "ID", nullable = false, length = 14)
    private String id;

    @Nationalized
    @Column(name = "TITLE")
    private String title;

    @Nationalized
    @Column(name = "POSTER")
    private String poster;

    @Column(name = "VIEWS")
    private Integer views;

    @Nationalized
    @Lob
    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "ACTIVE")
    private Boolean active;

    @OneToMany(mappedBy = "videoid")
    private Set<Favorite> favorites = new LinkedHashSet<>();

    @OneToMany(mappedBy = "videoid")
    private Set<Share> shares = new LinkedHashSet<>();

}
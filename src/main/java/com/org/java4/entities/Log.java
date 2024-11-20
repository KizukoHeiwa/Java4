package com.org.java4.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "LOGS", schema = "dbo")
public class Log {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Integer id;

    @Column(name = "URI")
    private String uri;

    @Column(name = "\"TIME\"")
    private Instant time;

    @Nationalized
    @Column(name = "USERNAME", length = 50)
    private String username;

}
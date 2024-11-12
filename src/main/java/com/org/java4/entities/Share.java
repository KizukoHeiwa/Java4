package com.org.java4.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "SHARE", schema = "dbo")
public class Share {
    @Id
    @Column(name = "ID", nullable = false, length = 14)
    private String id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "USERID", nullable = false)
    private Users userid;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "VIDEOID", nullable = false)
    private Video videoid;

    @Column(name = "EMAILS")
    private String emails;

    @Column(name = "SHAREDATE")
    private LocalDate sharedate;

}
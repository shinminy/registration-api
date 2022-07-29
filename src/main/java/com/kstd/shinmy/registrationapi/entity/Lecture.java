package com.kstd.shinmy.registrationapi.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "lecture")
@Getter
@Setter
@ToString
public class Lecture implements Serializable {
    private static final long serialVersionUID = 8983460909789131541L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long lectureId;

    @Column(nullable = false)
    private int capacity;

    @Column(nullable = false)
    private Date startDate;

    private String lecturer;

    private String hall;

    private String subject;
}

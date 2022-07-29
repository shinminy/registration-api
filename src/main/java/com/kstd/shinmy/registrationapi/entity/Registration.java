package com.kstd.shinmy.registrationapi.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "registration")
@Getter
@Setter
@ToString
public class Registration implements Serializable {
    private static final long serialVersionUID = 4069397604643809566L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long registrationId;

    private long lectureId;

    private String employeeIdNumber;
}

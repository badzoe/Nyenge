package com.springboot.crud.springBootCRUDApp.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="employees")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private int id;

    private String firstname;

    private String lastname;

    private String email;

    private String dob;

    private String gender;
}

package com.example.springboot.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "TB_ADRESSES")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AdressModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false, unique = true)
    private String name;

    @OneToOne(mappedBy = "adress")
    private UserModel user;
}

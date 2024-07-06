package com.example.springboot.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "TB_ROLES")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RoleModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column
    private String name;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserModel users;
}

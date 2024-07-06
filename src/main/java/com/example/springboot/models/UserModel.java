package com.example.springboot.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "TB_USERS")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(unique = true, nullable = false)
    private String name;

    @OneToOne()
    @JoinColumn(name = "address_id")
    private AdressModel adress;

    @OneToMany(mappedBy = "users")
    private Set<RoleModel> roles = new HashSet<>();

    @ManyToMany()
    @JoinTable(
            name = "TB_USER_PROJECTS",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "projects_id")
    )
    private Set<ProjectModel> projects = new HashSet<>();
}

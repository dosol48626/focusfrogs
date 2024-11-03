package com.dosol.focusfrogs.domain;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(unique = true)
    private String username;

    private String password;

    private String role;
}

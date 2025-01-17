package org.example.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nome obrigatório")
    private String nome;

    @Column(unique = true, nullable = false)
    @NotBlank(message = "Email obrigatório")
    @Email(message = "Email inválido")
    private String email;

    private LocalDate nascimento;
}

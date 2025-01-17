package org.example.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Entity
@Data
public class Noticia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Título obrigatório")
    private String titulo;

    @NotBlank(message = "Descrição obrigatória")
    @Column(length = 1000)
    private String descricao;

    private String link;

    private boolean processada = false;
}

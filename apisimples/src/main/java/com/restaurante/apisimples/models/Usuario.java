package com.restaurante.apisimples.models;

// import java.sql.Date;
// import java.util.UUID;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "TB_USUARIOS")
@Getter
@Setter
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_usuario;
    private String nome;
    
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate data_nascimento;
    private String email;
    private String cpf;

}

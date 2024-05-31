package com.restaurante.apisimples.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.restaurante.apisimples.models.Usuario;

@Repository
public interface Repositorio extends CrudRepository<Usuario, Long> {
    
}

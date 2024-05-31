package com.restaurante.apisimples.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.hibernate.mapping.Array;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.restaurante.apisimples.models.Usuario;
import com.restaurante.apisimples.repository.Repositorio;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@CrossOrigin(origins = "*")
public class Control {

    @Autowired
    private Repositorio myRepo;
    
    @PostMapping("/")
    public ResponseEntity<Object> cadastrar(@RequestBody Usuario usuarioNovo) {
        Usuario usuario = new Usuario();
        BeanUtils.copyProperties(usuarioNovo, usuario);
        try {
            return new ResponseEntity<>(myRepo.save(usuario), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Requisição ruim: " + e, HttpStatus.BAD_REQUEST);
        }
    }
    
    @GetMapping("/")
    public ResponseEntity<Object> listar(){        
        return new ResponseEntity<>(myRepo.findAll(), HttpStatus.OK);        
    }

    @GetMapping("/{id_usuario}")
    public ResponseEntity<Object> achar(@PathVariable long id_usuario) {
        Optional<Usuario> usuarioOpt = myRepo.findById(id_usuario);
        if (usuarioOpt.isPresent()) {
            Usuario usuario = usuarioOpt.get();
            return new ResponseEntity<>(usuario, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>("Usuário não encontrado.", HttpStatus.NOT_FOUND);
        }
    }
    

    @PutMapping("/{id_usuario}")
    public ResponseEntity<Object> putMethodName(@PathVariable long id_usuario, @RequestBody Usuario usuarioAtualizado) {
        Optional<Usuario> usuarioOpt = myRepo.findById(id_usuario);
        if(usuarioOpt.isPresent()){
            Usuario usuarioAntigo = usuarioOpt.get();

            ArrayList<String> atributos = new ArrayList<String>();
            atributos.add("id_usuario");
            if(usuarioAtualizado.getNome() == null) { atributos.add("nome"); }
            if(usuarioAtualizado.getCpf() == null) { atributos.add("cpf"); }
            if(usuarioAtualizado.getEmail() == null) { atributos.add("email"); }
            if(usuarioAtualizado.getData_nascimento() == null) { atributos.add("data_nascimento"); }

            String[] atributosArray = atributos.toArray(new String[0]);

            BeanUtils.copyProperties(usuarioAtualizado, usuarioAntigo, atributosArray);
            return new ResponseEntity<>(myRepo.save(usuarioAntigo), HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("Usuário não encontrado.", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id_usuario}")
    public ResponseEntity<Object> remover(@PathVariable long id_usuario){
        Optional<Usuario> usuarioOpt = myRepo.findById(id_usuario);
        if(usuarioOpt.isPresent()){
            Usuario usuario = usuarioOpt.get();
            myRepo.deleteById(id_usuario);
            return new ResponseEntity<>(usuario, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("Usuário não encontrado.", HttpStatus.NOT_FOUND);
        }
    }

}

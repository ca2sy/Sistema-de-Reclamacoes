package com.reclamacoes.sistema_reclamacoes.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.reclamacoes.sistema_reclamacoes.model.UsuarioModel;
import com.reclamacoes.sistema_reclamacoes.repository.UsuarioRepository;

public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    private PasswordEncoder passwordEncoder;


    public UsuarioService(){
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public UsuarioModel registrarUsuario(String cpf, String nome, String senha){
        String senhaCriptografada = passwordEncoder.encode(senha);
        UsuarioModel usuario = new UsuarioModel(cpf, nome, senhaCriptografada);
        return usuarioRepository.save(usuario);
    }   

    public Optional<UsuarioModel> buscarPorCpf(String cpf){
        return (usuarioRepository.findByCpf(cpf));
    }
}

package com.reclamacoes.sistema_reclamacoes.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.reclamacoes.sistema_reclamacoes.model.UsuarioModel;
import com.reclamacoes.sistema_reclamacoes.repository.UsuarioRepository;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public UsuarioModel registrarUsuario(String cpf, String nome, String senha) {
        String senhaCriptografada = passwordEncoder.encode(senha);
        UsuarioModel usuario = new UsuarioModel(cpf, nome, senhaCriptografada);
        return usuarioRepository.save(usuario);
    }

    public Optional<UsuarioModel> buscarPorCpf(String cpf) {
        return usuarioRepository.findByCpf(cpf);
    }
}

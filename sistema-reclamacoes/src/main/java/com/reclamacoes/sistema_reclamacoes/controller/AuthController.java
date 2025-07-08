package com.reclamacoes.sistema_reclamacoes.controller;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.reclamacoes.sistema_reclamacoes.model.UsuarioModel;
import com.reclamacoes.sistema_reclamacoes.security.JwtUtil;
import com.reclamacoes.sistema_reclamacoes.service.UsuarioService;

import jakarta.validation.Valid;
@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UsuarioService usuarioService;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/registro")
public ResponseEntity<?> register(@Valid @RequestBody UsuarioModel usuario) {
    if(usuario.getCpf() == null || usuario.getSenha() == null) {
        return ResponseEntity.badRequest().body("CPF e senha são obrigatórios");
    }
    
    UsuarioModel usuarioRegistrado = usuarioService.registrarUsuario(
        usuario.getCpf(),
        usuario.getNome(),
        usuario.getSenha()
    );
    return ResponseEntity.ok(usuarioRegistrado);
}

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> request) {
        Optional<UsuarioModel> usuario = usuarioService.buscarPorCpf(request.get("cpf"));
        
        if(usuario.isPresent() && 
           passwordEncoder.matches(request.get("senha"), usuario.get().getSenha())) {
            String token = JwtUtil.generateToken(usuario.get().getCpf());
            return ResponseEntity.ok(Map.of("token", token));
        }
        return ResponseEntity.status(401).body("Credenciais inválidas.");
    }
}
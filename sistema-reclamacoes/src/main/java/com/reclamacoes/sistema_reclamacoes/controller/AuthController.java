package com.reclamacoes.sistema_reclamacoes.controller;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.reclamacoes.sistema_reclamacoes.model.UsuarioModel;
import com.reclamacoes.sistema_reclamacoes.security.JwtUtil;
import com.reclamacoes.sistema_reclamacoes.service.UsuarioService;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/registro")
    public ResponseEntity<?> register (@RequestBody Map<String, String> request){
        UsuarioModel usuario = usuarioService.registrarUsuario(request.get("cpf"), request.get("nome"), "senha");
        return ResponseEntity.ok(usuario);
    }

    @PostMapping("/login")
    public ResponseEntity<?>login(@RequestBody Map<String, String> request){
        Optional<UsuarioModel> usuario = usuarioService.buscarPorCpf(request.get("cpf"));
        if(usuario.isPresent() && usuario.get().getSenha().equals(request.get("senha"))){
            String token = JwtUtil.generateToken(usuario.get().getCpf());
            return ResponseEntity.ok(Map.of("token", token));
        }
        return ResponseEntity.status(401).body("Credenciais invalidas.");
    }
}

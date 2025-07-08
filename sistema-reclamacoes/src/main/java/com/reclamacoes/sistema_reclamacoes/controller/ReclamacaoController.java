package com.reclamacoes.sistema_reclamacoes.controller;

import com.reclamacoes.sistema_reclamacoes.model.ReclamacaoModel;
import com.reclamacoes.sistema_reclamacoes.model.UsuarioModel;
import com.reclamacoes.sistema_reclamacoes.repository.UsuarioRepository;
import com.reclamacoes.sistema_reclamacoes.service.ReclamacaoService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/reclamacoes")
public class ReclamacaoController {

    @Autowired
    private ReclamacaoService reclamacaoService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping
    public ResponseEntity<?> criarReclamacao(@Valid @RequestBody ReclamacaoModel reclamacao) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            
            if (authentication == null || authentication.getName() == null) {
                return ResponseEntity.status(401).body("Token inválido ou expirado");
            }
            
            String cpf = authentication.getName();

            UsuarioModel usuario = usuarioRepository.findByCpf(cpf)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

            ReclamacaoModel novaReclamacao = reclamacaoService.criarReclamacao(reclamacao, usuario.getId());

            return ResponseEntity.ok(novaReclamacao);

        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erro interno: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarReclamacaoPorId(@PathVariable UUID id) {
        Optional<ReclamacaoModel> reclamacao = reclamacaoService.buscarReclamacaoPorId(id);
        
        if (reclamacao.isPresent()) {
            return ResponseEntity.ok(reclamacao.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/minhas-reclamacoes")
    public ResponseEntity<?> listarReclamacoesDoUsuario() {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String cpf = authentication.getName();
            
            UsuarioModel usuario = usuarioRepository.findByCpf(cpf)
                    .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

            List<ReclamacaoModel> reclamacoes = reclamacaoService.listarReclamacoesDoUsuario(usuario.getId());
            return ResponseEntity.ok(reclamacoes);

        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erro interno: " + e.getMessage());
        }
    }

    @DeleteMapping("/{titulo}")
    public ResponseEntity<?> deletarReclamacao(@PathVariable String titulo) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String cpf = authentication.getName();
            
            UsuarioModel usuario = usuarioRepository.findByCpf(cpf)
                    .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

            reclamacaoService.deletarReclamacaoDoUsuario(titulo, usuario.getId());
            return ResponseEntity.ok().build();

        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erro: " + e.getMessage());
        }
    }

    @PutMapping("/responder/{idReclamacao}/usuario/{usuarioId}")
    public ResponseEntity<?> marcarComoRespondida(
            @PathVariable UUID idReclamacao,
            @PathVariable UUID usuarioId) {
        try {
            ReclamacaoModel reclamacao = reclamacaoService.marcarComoRespondida(idReclamacao, usuarioId);
            return ResponseEntity.ok(reclamacao);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erro: " + e.getMessage());
        }
    }
}
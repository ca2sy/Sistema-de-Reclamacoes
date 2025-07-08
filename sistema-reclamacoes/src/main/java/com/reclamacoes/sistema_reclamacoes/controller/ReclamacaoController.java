package com.reclamacoes.sistema_reclamacoes.controller;

import com.reclamacoes.sistema_reclamacoes.model.ReclamacaoModel;
import com.reclamacoes.sistema_reclamacoes.model.UsuarioModel;
import com.reclamacoes.sistema_reclamacoes.repository.UsuarioRepository;
import com.reclamacoes.sistema_reclamacoes.security.JwtUtil;
import com.reclamacoes.sistema_reclamacoes.service.ReclamacaoService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<?> criarReclamacao(
            @Valid @RequestBody ReclamacaoModel reclamacao,
            HttpServletRequest request) {
        
        try {
            String token = request.getHeader("Authorization").replace("Bearer ", "");
            String cpf = JwtUtil.extractCpf(token);

            UsuarioModel usuario = usuarioRepository.findByCpf(cpf)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
            
 
            ReclamacaoModel novaReclamacao = reclamacaoService.criarReclamacao(reclamacao, usuario.getId());
            
            return ResponseEntity.ok(novaReclamacao);
            
        } catch (Exception e) {
            return ResponseEntity.status(401).body("Acesso não autorizado: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public Optional<ReclamacaoModel> buscarReclamacaoPorId(@PathVariable UUID id) {
        return reclamacaoService.buscarReclamacaoPorId(id);
    }


@GetMapping("/minhas-reclamacoes") 
public ResponseEntity<?> listarReclamacoesDoUsuario(HttpServletRequest request) {
    try {
     
        String token = request.getHeader("Authorization").replace("Bearer ", "");
        String cpf = JwtUtil.extractCpf(token);
        UsuarioModel usuario = usuarioRepository.findByCpf(cpf)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

      
        List<ReclamacaoModel> reclamacoes = reclamacaoService.listarReclamacoesDoUsuario(usuario.getId());
        return ResponseEntity.ok(reclamacoes);

    } catch (Exception e) {
        return ResponseEntity.status(401).body("Acesso não autorizado");
    }
}

@DeleteMapping("/{titulo}")
public ResponseEntity<?> deletarReclamacao(
        @PathVariable String titulo,
        HttpServletRequest request) {
    try {
  
        String token = request.getHeader("Authorization").replace("Bearer ", "");
        String cpf = JwtUtil.extractCpf(token);
        UsuarioModel usuario = usuarioRepository.findByCpf(cpf)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        reclamacaoService.deletarReclamacaoDoUsuario(titulo, usuario.getId());
        return ResponseEntity.ok().build();

    } catch (Exception e) {
        return ResponseEntity.status(401).body("Acesso não autorizado: " + e.getMessage());
    }
}


    @PutMapping("/responder/{idReclamacao}/usuario/{usuarioId}")
    public ReclamacaoModel marcarComoRespondida(
            @PathVariable UUID idReclamacao,
            @PathVariable UUID usuarioId) {
        return reclamacaoService.marcarComoRespondida(idReclamacao, usuarioId);
    }
}

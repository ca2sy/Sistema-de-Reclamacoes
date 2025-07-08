package com.reclamacoes.sistema_reclamacoes.controller;

import com.reclamacoes.sistema_reclamacoes.model.ReclamacaoModel;
import com.reclamacoes.sistema_reclamacoes.service.ReclamacaoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/reclamacoes")
public class ReclamacaoController {

    @Autowired
    private ReclamacaoService reclamacaoService;

    @PostMapping("/{usuarioId}")
    public ReclamacaoModel criarReclamacao(
            @RequestBody ReclamacaoModel reclamacao,
            @PathVariable UUID usuarioId) {
        return reclamacaoService.criarReclamacao(reclamacao, usuarioId);
    }


    @GetMapping("/{id}")
    public Optional<ReclamacaoModel> buscarReclamacaoPorId(@PathVariable UUID id) {
        return reclamacaoService.buscarReclamacaoPorId(id);
    }

    @GetMapping("/usuario/{usuarioId}")
    public List<ReclamacaoModel> listarReclamacoesDoUsuario(@PathVariable UUID usuarioId) {
        return reclamacaoService.listarReclamacoesDoUsuario(usuarioId);
    }


    @DeleteMapping("/{titulo}/usuario/{usuarioId}")
    public void deletarReclamacao(
            @PathVariable String titulo,
            @PathVariable UUID usuarioId) {
        reclamacaoService.deletarReclamacaoDoUsuario(titulo, usuarioId);
    }


    @PutMapping("/responder/{idReclamacao}/usuario/{usuarioId}")
    public ReclamacaoModel marcarComoRespondida(
            @PathVariable UUID idReclamacao,
            @PathVariable UUID usuarioId) {
        return reclamacaoService.marcarComoRespondida(idReclamacao, usuarioId);
    }
}

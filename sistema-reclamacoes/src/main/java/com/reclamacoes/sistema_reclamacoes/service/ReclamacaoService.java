package com.reclamacoes.sistema_reclamacoes.service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

import com.reclamacoes.sistema_reclamacoes.model.ReclamacaoModel;
import com.reclamacoes.sistema_reclamacoes.model.UsuarioModel;
import com.reclamacoes.sistema_reclamacoes.repository.ReclamacaoRepository;
import com.reclamacoes.sistema_reclamacoes.repository.UsuarioRepository;

@Service
public class ReclamacaoService {

    @Autowired
    private ReclamacaoRepository reclamacaoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;


    @Transactional
    public ReclamacaoModel criarReclamacao(ReclamacaoModel reclamacao, UUID usuarioId) {
        UsuarioModel usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        
        reclamacao.setUsuario(usuario);
        reclamacao.setRespondida(false);
        reclamacao.setData(new Date(System.currentTimeMillis()));
        
        return reclamacaoRepository.save(reclamacao);
    }

    public Optional<ReclamacaoModel> buscarReclamacaoPorId(UUID id) {
        return reclamacaoRepository.findById(id);
    }

    public List<ReclamacaoModel> listarReclamacoesDoUsuario(UUID idDoUsuario) {
        return reclamacaoRepository.findByUsuarioId(idDoUsuario);
    }

    @Transactional
    public void deletarReclamacaoDoUsuario(String titulo, UUID idDoUsuario) {
     ReclamacaoModel reclamacao = reclamacaoRepository.findByTitulo(titulo)
            .orElseThrow(() -> new RuntimeException("Reclamação não encontrada"));

    // Valida se o usuário é dono da reclamação
    if (!reclamacao.getUsuario().getId().equals(idDoUsuario)) {
        throw new RuntimeException("Você não tem permissão para excluir esta reclamação");
    }

        if (reclamacao.isRespondida()) {
            throw new RuntimeException("Não é possível deletar uma reclamação respondida.");
        }

        reclamacaoRepository.delete(reclamacao);
    }

    @Transactional
    public ReclamacaoModel marcarComoRespondida(UUID idDaReclamacao, UUID idDoUsuario) {

        Optional<ReclamacaoModel> reclamacaoOptional = reclamacaoRepository.findById(idDaReclamacao);

        if (reclamacaoOptional.isEmpty()) {
            throw new RuntimeException("Reclamação não encontrada.");
        }

        ReclamacaoModel reclamacao = reclamacaoOptional.get();

        if (reclamacao.isRespondida()) {
            throw new RuntimeException("Esta reclamação já foi respondida.");
        }

        reclamacao.setRespondida(true);
        return reclamacaoRepository.save(reclamacao);
    }
}

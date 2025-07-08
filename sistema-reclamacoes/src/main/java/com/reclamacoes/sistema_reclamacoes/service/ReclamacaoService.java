package com.reclamacoes.sistema_reclamacoes.service;

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
    public ReclamacaoModel criarReclamacao(ReclamacaoModel novaReclamacao, UUID idDoUsuario) {

        Optional<UsuarioModel> usuarioOptional = usuarioRepository.findById(idDoUsuario);

        if (usuarioOptional.isEmpty()) {
            throw new RuntimeException("Usuário não encontrado.");
        }

        novaReclamacao.setUsuario(usuarioOptional.get());

        return reclamacaoRepository.save(novaReclamacao);
    }

    public Optional<ReclamacaoModel> buscarReclamacaoPorId(UUID id) {
        return reclamacaoRepository.findById(id);
    }

    public List<ReclamacaoModel> listarReclamacoesDoUsuario(UUID idDoUsuario) {
        return reclamacaoRepository.findByUsuarioId(idDoUsuario);
    }

    @Transactional
    public void deletarReclamacaoDoUsuario(String titulo, UUID idDoUsuario) {

        Optional<ReclamacaoModel> reclamacaoOptional = reclamacaoRepository.findByTitulo(titulo);

        if (reclamacaoOptional.isEmpty()) {
            throw new RuntimeException("Reclamação não encontrada.");
        }

        ReclamacaoModel reclamacao = reclamacaoOptional.get();

        if (!reclamacao.getUsuario().getId().equals(idDoUsuario)) {
            throw new RuntimeException("Você não pode deletar uma reclamação de outro usuário.");
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

package com.reclamacoes.sistema_reclamacoes.repository;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import com.reclamacoes.sistema_reclamacoes.model.UsuarioModel;

public interface UsuarioRepository extends JpaRepository<UsuarioModel, UUID> {

    Optional<UsuarioModel> findByCpf(String cpf);
}

package com.reclamacoes.sistema_reclamacoes.repository;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import com.reclamacoes.sistema_reclamacoes.model.usuarioModel;

public interface UsuarioRepository extends JpaRepository<usuarioModel, UUID> {

    usuarioModel findByCpf(String cpf);
}

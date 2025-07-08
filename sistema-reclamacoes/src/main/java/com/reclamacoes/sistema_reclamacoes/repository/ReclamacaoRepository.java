package com.reclamacoes.sistema_reclamacoes.repository;

import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import com.reclamacoes.sistema_reclamacoes.model.reclamacaoModel;

public interface ReclamacaoRepository extends JpaRepository<reclamacaoModel, UUID>{
 

    //esse método lista as reclamações realizadas por um usuário específico RF-04
      List<reclamacaoModel> findByUsuarioId(UUID usuarioId); 
    
}

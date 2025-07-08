package com.reclamacoes.sistema_reclamacoes.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import com.reclamacoes.sistema_reclamacoes.model.ReclamacaoModel;

import jakarta.transaction.Transactional;

public interface ReclamacaoRepository extends JpaRepository<ReclamacaoModel, UUID>{
 

    //RF-04
      List<ReclamacaoModel> findByUsuarioId(UUID usuarioId); 
    
      //optional nao deixa dar erro por causa de null, caso nao exista reclamações com esse titulo
    Optional<ReclamacaoModel> findByTitulo(String titulo);  
    
    @Transactional
    void deleteByTitulo(String titulo);

}

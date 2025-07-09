package com.reclamacoes.sistema_reclamacoes.model;

import java.sql.Date;
import java.util.UUID;

import org.springframework.data.annotation.CreatedDate;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tb_reclamacao")
public class ReclamacaoModel {

    // atributos
    // chave primaria: id
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    //RF-02.2
    @Column(nullable = false, unique = true, length = 100)
    @NotBlank(message = "Título é obrigatório")
    @Size(max = 100, message = "Título deve ter até 100 caracteres")
    private String titulo;

    //RF-02.3
    @NotBlank(message = "Descrição é obrigatória")
    @Size(max = 600, message = "Descrição deve ter até 600 caracteres")
    @Column(nullable = false, unique = false, length = 600)
    private String descricao;

    //RF-02.4
    @CreatedDate
    @Column(nullable = false, updatable = false)
    private Date data;

    @Column(nullable = false, unique = false)
    private boolean respondida;

    // uma coluna a mais e ela será uma chave estrangeira ligada a outra entidade,
    // no caso, usuário
    @ManyToOne
    @JoinColumn(name = "usuario_id")
     @JsonBackReference
    private UsuarioModel usuario;


    public ReclamacaoModel() {
    }

    // getters e setters
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getCpfUsuario() {
    return this.usuario.getCpf();
}

    public boolean isRespondida() {
        return respondida;
    }

    public void setRespondida(boolean respondida) {
        this.respondida = respondida;
    }

    public UsuarioModel getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioModel usuario) {
        this.usuario = usuario;
    }

    public void setData(Date data) {
        this.data = data;
    }


}

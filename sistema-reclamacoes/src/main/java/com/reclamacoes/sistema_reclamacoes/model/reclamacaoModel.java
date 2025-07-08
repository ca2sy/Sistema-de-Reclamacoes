package com.reclamacoes.sistema_reclamacoes.model;

import java.sql.Date;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_reclamacao")
public class reclamacaoModel {

    // atributos
    // chave primaria: id
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false, unique = false)
    private String titulo;

    @Column(nullable = false, unique = false)
    private String descricao;

    @Column(nullable = false, unique = false)
    private Date data;

    @Column(nullable = false, unique = false)
    private boolean respondida;

    // uma coluna a mais e ela será uma chave estrangeira ligada a outra entidade,
    // no caso, usuário
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private usuarioModel usuario;

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

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public usuarioModel getUsuario() {
        return usuario;
    }

    public void setUsuario(usuarioModel usuario) {
        this.usuario = usuario;
    }

    public String getCpfUsuario() {
    return this.usuario.getCpf();
}

}

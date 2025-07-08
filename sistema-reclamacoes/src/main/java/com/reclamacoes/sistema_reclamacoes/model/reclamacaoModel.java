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
public class ReclamacaoModel {

    // atributos
    // chave primaria: id
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    //RF-02.2
    @Column(nullable = false, unique = false, length = 100)
    private String titulo;

    //RF-02.3
    @Column(nullable = false, unique = false, length = 600)
    private String descricao;

    //RF-02.4
    @Column(nullable = false, unique = false)
    private Date data;

    @Column(nullable = false, unique = false)
    private boolean respondida;

    // uma coluna a mais e ela será uma chave estrangeira ligada a outra entidade,
    // no caso, usuário
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private UsuarioModel usuario;


    
    public ReclamacaoModel(UUID id, String titulo, String descricao, Date data, UsuarioModel usuario) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.data = data;
        this.respondida = false;
        this.usuario = usuario;
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

    public ReclamacaoModel orElseThrow(Object object) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'orElseThrow'");
    }



}

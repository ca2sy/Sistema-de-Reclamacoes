package com.reclamacoes.sistema_reclamacoes.model;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_usuario")
public class UsuarioModel {

    //atributos/colunas da tabela
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false, unique = true)
    private String cpf;

    @Column(nullable = false, unique = false)
    private String nome;

    @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY)
    //carregamento "preguiçoso", pra não aparecer um monte de reclamação de uma vez
    private Set<ReclamacaoModel> reclamacoes = new HashSet<>();

    @Column(nullable = false)
    private String senha;

    
    public UsuarioModel(String cpf, String nome, String senhaCriptografada) {
        this.cpf = cpf;
        this.nome = nome;
        this.senha = senhaCriptografada;
    }

    public UsuarioModel() {}  

    //getters e setters
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Set<ReclamacaoModel> getReclamacoes() {
        return reclamacoes;
    }

    public void setReclamacoes(Set<ReclamacaoModel> reclamacoes) {
        this.reclamacoes = reclamacoes;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }


}

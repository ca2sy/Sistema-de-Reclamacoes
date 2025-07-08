package com.reclamacoes.sistema_reclamacoes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing 
public class SistemaReclamacoesApplication {

	public static void main(String[] args) {
		SpringApplication.run(SistemaReclamacoesApplication.class, args);
	}

}

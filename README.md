# Sistema de Reclamações

## Sobre o Projeto

Iniciei lendo o documento de requisitos para entender o que deveria ser feito. Esse foi o primeiro passo fundamental para o desenvolvimento do projeto.

## Backend

Decidi começar pelo backend usando Spring Boot, já que foi o que mais tive contato até agora, mesmo que não faça tanto tempo que o aprendi. Para iniciar o projeto, utilizei o Spring Initializr.

Para criar as *models* e os *repositories*, me baseei em outros projetos que já tinha feito e em um vídeo no YouTube que foi muito útil:  
https://youtu.be/Ca30sv9EbLo?si=IN_A8bpFr-CZ8ynS

Além disso, usei um livro em PDF, chamado de "SpringBoot versão 3x: da API REST aos microserviços"  que me ajudou tanto nessa parte quanto na criação dos *controllers* e *services*.  

Como aprendi recentemente, ainda não tenho costume de fazer tudo 100% sozinha, então estou sempre pesquisando exemplos para me basear e conseguir fazer o proposto.

## Segurança (JWT)

Depois, foquei na segurança, que precisava ser feita com JWT. Nunca tinha trabalhado com autenticação antes, então foi um desafio. Para aprender pelo menos a base, segui alguns vídeos e artigos recomendados:  

- [O que é JSON Web Tokens (Alura)](https://www.alura.com.br/artigos/o-que-e-json-web-tokens?srsltid=AfmBOooXNG4829x9eiFcoMOrd37wYebhJm_ALwAfAXdgJ7iI1P5I7IuA)  
- [Entendendo tokens JWT (Medium)](https://medium.com/tableless/entendendo-tokens-jwt-json-web-token-413c6d1397f6)  
- [Passo a passo para configurar JWT no Spring (Medium)](https://medium.com/@felipeacelinoo/protegendo-sua-api-rest-com-spring-security-e-autenticando-usu%C3%A1rios-com-token-jwt-em-uma-aplica%C3%A7%C3%A3o-d70e5b0331f9)  
- [Vídeo tutorial JWT Spring Boot](https://youtu.be/SqU9v_V32RA?si=zBngDMUyWFIbr3vK)  

No começo, deu muitos erros, era a primeira vez mexendo com isso e mesmo com os vídeos, foi complicado. Continuei pesquisando e tentando corrigir, até que consegui resolver os problemas, que eram erros de digitação e configurações de autenticação. Acredito que aprendi bastante sobre autenticação, mas ainda quero reforçar o conceito com mais prática, pois ainda está muito raso na minha mente.

Após a concluir essa parte, passei a testar a API usando o Thunder Client no VS Code, e quando tudo funcionou, comecei a trabalhar no frontend.

## Frontend

No frontend, segui uma série de vídeos sobre React, pois era um assunto recente que estava aprendendo. Pedi ajuda algumas vezes, mas consegui fazer funcionar. 

O último desafio foi o fetch para as reclamações não funcionar. Tive muita dificuldade para encontrar o problema, fiz vários debugs e percebi que o erro estava na configuração do sistema (system config). Após corrigir, finalizei a parte visual do projeto.

Gostaria de ter usado Tailwind CSS, mas ainda não aprendi esse conceito direito. Por isso, preferi usar CSS tradicional, que uso mais no dia a dia. Mas isso não significa que não vá aprender Tailwind no futuro!

## Considerações Finais

Fiz de tudo para realizar, pesquisei bastante porque não gosto de entregar algo não funcional e adoro aprender e praticar. Geralmente não descanso enquanto não estiver tudo funcionando como deveria.

 Reconheço que ainda preciso praticar muito, muito mesmo, para conseguir programar de forma mais independente, mas acredito que isso vem com o tempo e a prática e eu não irei desistir até conseguir chegar onde eu pretendo.

# Projeto E-commerce com Spring Boot para a fase 5 da pós-graduação FIAP

Este projeto de demonstração E-commerce é construído com o framework Spring Boot, aproveitando várias tecnologias e bibliotecas poderosas para fornecer uma base sólida para a construção de uma aplicação de comércio eletrônico. A
lém das funcionalidades básicas de e-commerce, este projeto inclui funcionalidades específicas de Login e Registro de Usuário, Gestão de Itens, Carrinho de Compras e Simulação de Pagamentos.

## Funcionalidades

- **Login e Registro de Usuário**: Utilizando as ferramentas do Spring Security, os usuários podem se cadastrar e fazer login no sistema, garantindo a autenticação e autorização de forma segura.

- **Gestão de Itens**: Através de uma tela dedicada, usuários administradores têm acesso ao controle de cadastro e manutenção de itens, incluindo a gestão de preços.

- **Carrinho de Compras**: Os usuários podem adicionar e remover itens de um carrinho de compras persistente, que é associado ao usuário logado, melhorando a experiência de compra.

- **Pagamentos (Simulação)**: Implementação de uma tela que simula o processo de pagamento, permitindo aos usuários visualizar os itens no carrinho e concluir uma compra fictícia, sem a necessidade de integração com formas de pagamento reais.

## Tecnologias Utilizadas

- **Spring Boot**: Facilita a criação de aplicações stand-alone baseadas em Spring com configurações mínimas.
- **Spring Data JPA**: Para integração com bancos de dados usando Java Persistence API (JPA).
- **Spring Security**: Para autenticação e autorização robustas, garantindo a segurança da aplicação.
- **Spring Web**: Para construir aplicações web, incluindo aplicações RESTful usando Spring MVC.
- **Lombok**: Para reduzir o boilerplate code em objetos Java, como getters, setters, e construtores.
- **H2 Database**: Um banco de dados em memória para prototipagem e testes.
- **Java JWT**: Para gerar e validar tokens JWT, facilitando a implementação de autenticação baseada em tokens.
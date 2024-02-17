# Projeto E-commerce com Spring Boot

## Visão Geral
Este projeto de E-commerce é desenvolvido utilizando o Spring Boot e demonstra a aplicação de diversas tecnologias e bibliotecas do ecossistema Spring. Ele faz parte da fase 5 da pós-graduação da FIAP, focando em funcionalidades essenciais para um sistema de comércio eletrônico, incluindo Login/Registro de Usuários, Gestão de Itens, Carrinho de Compras e Simulação de Pagamentos.

## Funcionalidades Principais
- **Login e Registro de Usuário**: Segurança com Spring Security para autenticação e autorização.
- **Gestão de Itens**: Interface administrativa para gerenciamento de itens disponíveis para venda.
- **Carrinho de Compras**: Permite aos usuários adicionar e remover itens, com persistência de dados associada ao usuário logado.
- **Pagamentos (Simulação)**: Uma tela de simulação de pagamento para concluir a compra sem necessidade de integração real com serviços de pagamento.

## Tecnologias Utilizadas
- **Spring Framework**: Utilizando Spring Boot, Spring Data JPA, Spring Security, e Spring Web.
- **Lombok**: Para redução de código boilerplate em entidades Java e DTOs.
- **H2 Database**: Banco de dados em memória para facilitar testes e desenvolvimento.
- **Java JWT**: Suporte para autenticação via tokens JWT.

## API Gateway
O projeto inclui um API Gateway configurado com Spring Cloud Gateway, facilitando a comunicação entre os clientes e os microserviços através de um ponto de entrada unificado.

### Configuração
- **Porta do Servidor**: 8080, modificável via `application.properties`.
- **Nome da Aplicação**: Configurado como `gateway-service`.
- **Integração com Eureka**: Para descoberta de serviços dinâmica.
- **Configuração de Rotas**: Automatiza a descoberta e o roteamento para microserviços registrados no Eureka.

### Uso
1. Inicie o servidor Eureka.
2. Execute os microserviços de acordo com as rotas configuradas.
3. Inicie a aplicação `gateway-service`.

As requisições para `localhost:8080/{service_path}` serão redirecionadas para os microserviços correspondentes, permitindo uma arquitetura limpa e eficiente.
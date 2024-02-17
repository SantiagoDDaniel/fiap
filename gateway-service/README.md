# API Gateway

Este projeto implementa um **API Gateway** para um sistema de microserviços usando Spring Cloud Gateway. O Gateway gerencia as requisições dos clientes, encaminhando-as para os microserviços desejados.

## Configuração

### Configuração do Servidor

O servidor é configurado para operar na porta 8080. Para alterar a porta, edite o arquivo `application.properties` e altere a propriedade `server.port`.

properties server.port=8080
### Nome da Aplicação

O nome da aplicação é configurado como `gateway-service`.

properties spring.application.name=gateway-service

### Servidor Eureka

A aplicação se registra em um servidor Eureka para descoberta de serviços. A URL do servidor Eureka é configurada da seguinte forma:

properties eureka.client.service-url.default-zone=http://localhost:8761/eureka/

### Gateway Configuration

O Spring Cloud Gateway está configurado para habilitar automaticamente a descoberta de rotas de microserviços registrados no servidor Eureka.

properties spring.cloud.gateway.discovery.locator.enabled=true


### Rotas

Rotas são definidas para direcionar requisições para os respectivos microserviços. Como exemplo, vejamos a rota configurada para o serviço `product-service`:

properties spring.cloud.gateway.routes[0].id=product-service 
spring.cloud.gateway.routes[0].uri=lb://product-service 
spring.cloud.gateway.routes[0].predicates[0]=Path=/products/**

## Como Usar

1. Inicie o servidor Eureka.
2. Inicie os outros microserviços que foram definidos na configuração de rota.
3. Inicie a aplicação `gateway-service`.

Agora, é possível fazer requisições para `localhost:8080/{service_path}`, substituindo `{service_path}` pelo caminho desejado, por exemplo, `/products` para o serviço `product-service`, `/carts` para `cart-service`, e assim por diante.

## Conclusão

Com essa configuração, o Gateway se encarrega de direcionar as requisições de entrada para os microserviços adequados, permitindo balanceamento de carga entre múltiplas instâncias de cada microserviço.
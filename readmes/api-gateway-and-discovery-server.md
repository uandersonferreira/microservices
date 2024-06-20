> OBSERVATION: Conteúdo extraído do vídeo: "Eureka Netflix y Spring Cloud Gateway - Microservicios con Spring Boot", 
> do canal do youtuber: Uncle Dave's Code (https://www.youtube.com/@uncledavescode)
> da playlist: "Microservicios con Spring Boot"

# Introdução ao Spring Cloud Gateway e Eureka Netflix

Olá a todos, anteriormente, implementamos os três
microserviços fundamentais de nossa aplicação. Hoje, começaremos adicionando componentes
adicionais ao sistema. Primeiro, vamos adicionar o "Spring Cloud Gateway" e depois
integraremos o "Eureka Netflix". Vamos começar!

### Spring Cloud Gateway

O "Spring Cloud Gateway" fornece uma solução flexível e escalável para lidar com desafios comuns
em arquiteturas de microserviços, como roteamento, segurança, transformação de dados e controle
de tráfego. Hoje, focaremos principalmente no **roteamento**.

### Eureka Netflix

O "Eureka Netflix" oferece uma solução confiável e escalável para registro e descoberta de serviços
em ambientes de microserviços. Ajuda os serviços a se encontrarem dinamicamente e facilita a administração
e o balanceamento de carga em um ambiente distribuído.

## Criação do Projeto API Gateway

Vamos criar o projeto "api-gateway" utilizando o "Spring Initializr".

1. **Acessar start.spring.io**:
    - Na seção "Project", selecione "Maven".
    - Na seção "Language", selecione "Java".
    - A versão do Spring Boot será 3.0.6.
    - No campo "Group", digite "br.com.uanderson".
    - No campo "Artifact", digite "api-gateway".
    - Certifique-se de ajustar o "Package name" para que seja válido (substitua hifens por underscores).
    - Configure o "Packaging" como "Jar" e a versão do Java como 17.
    - As dependências serão: Spring Boot DevTools, Lombok, Spring Configuration Processor e Spring Cloud Gateway.
    - Clique em "Generate" para gerar o projeto.
    - Baixe o arquivo ZIP gerado e descompacte-o no diretório do projeto pai junto com os outros microserviços.

2. **Configurar POMs**:
    - No POM do projeto pai, adicione o novo módulo "api-gateway".
    - No POM do projeto "api-gateway", atualize a tag "parent" com as informações do projeto pai:
      ```xml
      <parent>
          <groupId>br.com.uanderson</groupId>
          <artifactId>microservices</artifactId>
          <version>1.0.0-SNAPSHOT</version>
      </parent>
      ```
    - Mova a seção "dependencyManagement" para o POM do projeto pai junto com a propriedade da versão do Spring Cloud.

## Configuração do Spring Cloud Gateway

Definimos o nome da aplicação e configuramos o acesso através da porta 8080.

### Definir Rotas

Definimos as rotas para acessar os microserviços de inventário, pedidos e produtos.

1. **Inventário**:
   ```yaml
   routes:
   - id: inventory-service
     uri: http://localhost:8083
     predicates:
     - Path=/api/inventory/** #url do endpoint definido no controller que irá responder
   ```

2. **Pedidos**:
   ```yaml
   - id: order-service
     uri: http://localhost:8082
     predicates:
     - Path=/api/order/** #url do endpoint definido no controller que irá responder
   ```

3. **Produtos**:
   ```yaml
   - id: product-service
     uri: http://localhost:8081
     predicates:
     - Path=/api/product/** #url do endpoint definido no controller que irá responder
   ```

## Testes no Postman

Subimos o servidor junto com os outros microserviços e testamos as
chamadas através da API Gateway no Postman:

1. **Listagem de Produtos**: Modifique a porta para `8080`.
2. **Inventário**: Modifique a porta para `8080`.
3. **Pedidos**: Modifique a porta para `8080` e verifique as chamadas.

- E possível notar que agora o Gateway está responsável pelo gerenciamento 
das rotas (endpoints/urls).

## Introdução ao Eureka Netflix

Vamos criar o projeto para o servidor Eureka utilizando o "Spring Initializr".

1. **Acessar start.spring.io**:
    - Modifique o nome do artefato conforme necessário.
    - Remova a dependência do Spring Cloud Gateway.
    - Adicione a dependência do Eureka Server.
    - Gere o projeto e extraia os arquivos na pasta do projeto pai.
    - Configure os POMs do projeto pai e do projeto "discovery-server" como fizemos anteriormente.

### Configuração do Servidor Eureka

Adicionamos a anotação `@EnableEurekaServer` e configuramos as propriedades 
necessárias no arquivo `application.yml`:

```yaml
server:
  port: 8761

eureka:
  instance:
    hostname: localhost
    prefer-ip-address: false
  client:
    register-with-eureka: false
    fetch-registry: false
    service-url:
      defaultZone: http://localhost:8761/eureka/
```

## Configuração dos Microserviços com Eureka Client

Adicionamos a dependência do Eureka Client nos projetos de API Gateway, Inventário,
Pedidos e Produtos. Configuramos cada microserviço para se registrar no servidor Eureka
e iniciamos todos os serviços.

## Configuração de Rotas Dinâmicas na API Gateway

Modificamos as rotas na API Gateway para usar o prefixo `lb://` em vez de URLs estáticas.

1. **Inventário**:
   ```yaml
   routes:
   - id: inventory-service
     uri: lb://inventory-service
     predicates:
     - Path=/api/inventory
   ```

2. **Pedidos**:
   ```yaml
   - id: order-service
     uri: lb://order-service
     predicates:
     - Path=/api/order
   ```

3. **Produtos**:
   ```yaml
   - id: product-service
     uri: lb://product-service
     predicates:
     - Path=/api/product
   ```

### Testes de Balanceamento de Carga

Testamos o balanceamento de carga executando múltiplas instâncias dos microserviços
e verificamos como o balanceador alterna entre elas.

## Conclusão

Em resumo vimos como o Spring Cloud Gateway permite gerenciar e rotear solicitações de forma centralizada,
além de oferecer balanceamento de carga. E Com o Eureka Netflix, simplificamos o registro e descoberta
de serviços, permitindo uma comunicação dinâmica e escalável entre microserviços.


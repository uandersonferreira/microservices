> **OBSERVATION:** Conteúdo gerado com auxilio de Inteligência Artificial, considere consultar a documentação
> oficial da tecnologia para saber mais. :)

### Reference Documentation
* [Gateway](https://docs.spring.io/spring-cloud-gateway/docs/current/reference/html/)


## O que é um API Gateway?

Um API Gateway é um componente que atua como ponto de entrada único para um conjunto de microservices. Ele recebe todas as solicitações dos clientes e as encaminha para os serviços apropriados no back-end. Pense no API Gateway como uma "fachada" para seus microservices, lidando com todas as responsabilidades de roteamento e gerenciamento de solicitações.

### Por que usar um API Gateway?

#### 1. **Roteamento Centralizado**
- **Função**: Direciona as solicitações de entrada para os microservices específicos com base em regras de roteamento configuradas.
- **Benefício**: Simplifica a arquitetura do cliente, pois este só precisa conhecer o endpoint do gateway e não os endpoints individuais de cada microservice.

#### 2. **Segurança e Autenticação**
- **Função**: Implementa mecanismos de segurança, como autenticação e autorização, para proteger seus microservices.
- **Benefício**: Centraliza a lógica de segurança, evitando a necessidade de implementá-la repetidamente em cada microservice.

#### 3. **Balanceamento de Carga**
- **Função**: Distribui as solicitações de forma equilibrada entre instâncias dos microservices.
- **Benefício**: Melhora a escalabilidade e a disponibilidade dos serviços.

#### 4. **Agregação de Respostas**
- **Função**: Combina as respostas de múltiplos microservices em uma única resposta para o cliente.
- **Benefício**: Reduz a complexidade do cliente e melhora a eficiência da rede, minimizando a quantidade de chamadas de rede necessárias.

#### 5. **Limitação de Taxa (Rate Limiting) e Controle de Tráfego**
- **Função**: Limita o número de solicitações que um cliente pode fazer em um período de tempo.
- **Benefício**: Protege seus serviços contra abusos e ataques DDoS.

#### 6. **Monitoramento e Logging**
- **Função**: Centraliza o logging e monitoramento das solicitações.
- **Benefício**: Facilita a análise de desempenho e a resolução de problemas, proporcionando visibilidade sobre o comportamento do sistema.

### Implementação com Spring Cloud Gateway

O Spring Cloud Gateway é uma biblioteca que facilita a criação de um API Gateway em projetos Spring Boot. Ele é construído sobre o projeto Spring WebFlux e usa componentes do Reactor para processamento reativo.

#### Dependência Maven
Você já está utilizando a dependência correta:
```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-gateway</artifactId>
</dependency>
```

#### Configuração Básica application.yml

A configuração de um API Gateway com Spring Cloud Gateway pode ser feita através do `application.yml` ou programaticamente.

**Exemplo usando `application.yml`:**
```yaml
spring:
  cloud:
    gateway:
      routes:
      - id: service1
        uri: http://localhost:8081
        predicates:
        - Path=/service1/**
      - id: service2
        uri: http://localhost:8082
        predicates:
        - Path=/service2/**
```

**Exemplo programático:**
```java
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("service1", r -> r.path("/service1/**")
                        .uri("http://localhost:8081"))
                .route("service2", r -> r.path("/service2/**")
                        .uri("http://localhost:8082"))
                .build();
    }
}
```

### Propriedades de Configuração dos Serviços

1. **ID da Rota**
    - **Propriedade:** `spring.cloud.gateway.routes[<index>].id`
    - **Conceito:** Define um identificador único para a rota. Isso ajuda a identificar e gerenciar a rota específica dentro do gateway.

2. **URI de Destino**
    - **Propriedade:** `spring.cloud.gateway.routes[<index>].uri`
    - **Conceito:** Especifica o URI de destino para onde as requisições devem ser encaminhadas. `lb://` indica que o URI utiliza o load balancer do Spring Cloud para resolver o nome do serviço registrado no Eureka.

3. **Predicados de Rota**
    - **Propriedade:** `spring.cloud.gateway.routes[<index>].predicates[<index>]`
    - **Conceito:** Define as condições (predicados) que as requisições devem atender para serem roteadas através dessa rota. Por exemplo, `Path=/api/inventory/**` especifica que a rota deve ser usada para caminhos que correspondem ao padrão `/api/inventory/**`.

- `routes[<index>]`: Refere-se à rota na posição <index> na lista de rotas.
O `<index>` é um número que começa em `0` e aumenta para cada nova rota.

### Exemplo de Configuração para um Serviço application.properties
ex: Para um serviço de inventário, as configurações são:
```properties
spring.cloud.gateway.routes[0].id=inventory-service
spring.cloud.gateway.routes[0].uri=lb://inventory-service
spring.cloud.gateway.routes[0].predicates[0]=Path=/api/inventory/**
```


### Conclusão

Um API Gateway é um componente crucial em uma arquitetura de microservices, pois fornece uma série de 
funcionalidades que centralizam e simplificam a gestão de solicitações e respostas. Ele não apenas 
facilita o desenvolvimento e a manutenção dos serviços, mas também melhora a segurança, escalabilidade 
e observabilidade do sistema. Utilizando o Spring Cloud Gateway, você pode configurar um gateway de 
forma eficiente e flexível, aproveitando as vantagens do ecossistema Spring.


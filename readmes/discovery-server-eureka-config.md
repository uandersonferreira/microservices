> **OBSERVATION:** Conteúdo gerado com auxilio de Inteligência Artificial, considere consultar a documentação
> oficial da tecnologia para saber mais. :)

### Reference Documentation

* [Eureka Server](https://docs.spring.io/spring-cloud-netflix/docs/current/reference/html/#spring-cloud-eureka-server)
* [Service Registration and Discovery with Eureka and Spring Cloud](https://spring.io/guides/gs/service-registration-and-discovery/)

### O que é um Discovery Server?

Um Discovery Server é um componente que facilita o registro e a descoberta de serviços em uma arquitetura de microservices. Ele permite que os microservices descubram dinamicamente a localização uns dos outros sem a necessidade de configurar manualmente os endereços dos serviços. Um dos sistemas mais populares para esse propósito é o Eureka, desenvolvido pela Netflix.

### Por que usar um Discovery Server?

#### 1. **Registro Dinâmico de Serviços**
- **Função**: Os microservices se registram no Discovery Server quando iniciam, informando seu endereço e outras informações relevantes.
- **Benefício**: Elimina a necessidade de configurações estáticas, facilitando a escalabilidade e a flexibilidade.

#### 2. **Descoberta de Serviços**
- **Função**: Permite que um microservice descubra a localização de outros microservices consultando o Discovery Server.
- **Benefício**: Facilita a comunicação entre serviços, especialmente em ambientes dinâmicos onde os endereços IP podem mudar.

#### 3. **Failover e Redundância**
- **Função**: Proporciona informações sobre múltiplas instâncias de um serviço, permitindo balanceamento de carga e failover automático.
- **Benefício**: Melhora a resiliência e a disponibilidade dos serviços.

#### 4. **Desacoplamento de Serviços**
- **Função**: Os serviços não precisam conhecer os endereços uns dos outros diretamente, apenas o Discovery Server.
- **Benefício**: Reduz o acoplamento entre serviços, facilitando a manutenção e evolução do sistema.

### Implementação com Spring Cloud Netflix Eureka

Eureka é um serviço de registro e descoberta desenvolvido pela Netflix, amplamente utilizado em arquiteturas de microservices.

#### Dependência Maven

Você precisa adicionar a dependência do Eureka Server ao seu projeto:

```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-eureka-server</artifactId>
</dependency>
```

#### Configuração do Eureka Server

1. **Adicionar a anotação `@EnableEurekaServer` à classe principal da aplicação:**

```java
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurekaServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(EurekaServerApplication.class, args);
    }
}
```

2. **Configurar o `application.yml` ou `application.properties`:**

```yaml
server:
  port: 8761

eureka:
  client:
    register-with-eureka: false
    fetch-registry: false
  server:
    enable-self-preservation: false
```

#### Configuração dos Clientes Eureka

Para que os microservices se registrem no Eureka Server, você precisa adicionar a dependência `spring-cloud-starter-netflix-eureka-client` a cada microservice:

```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
</dependency>
```

1. **Adicionar a anotação `@EnableEurekaClient` à classe principal de cada microservice:**

```java
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceApplication.class, args);
    }
}
```

2. **Configurar o `application.yml` ou `application.properties` de cada microservice:**

```yaml
eureka:
  client:
    service-url:
      defaultZone: http://localhost:8761/eureka/
```

### Informações Enviadas pelos Microservices ao Registrar-se no Eureka Server

1. **Nome do Serviço (Service Name)**
   - **Descrição**: Identificador único para o microservice.
   - **Exemplo**: `"USER-SERVICE"`, `"ORDER-SERVICE"`.

2. **Instância do Serviço (Instance ID)**
   - **Descrição**: Identificador único para a instância específica do microservice. Isso é útil para distinguir entre diferentes instâncias do mesmo serviço.
   - **Exemplo**: `"USER-SERVICE-1"`, `"ORDER-SERVICE-2"`.

3. **URL Base (Home Page URL)**
   - **Descrição**: URL da página inicial do serviço.
   - **Exemplo**: `"http://localhost:8080"`.

4. **Status de Saúde (Health Check URL)**
   - **Descrição**: URL usada pelo Eureka Server para verificar o status de saúde do microservice.
   - **Exemplo**: `"http://localhost:8080/actuator/health"`.

5. **Metadata**
   - **Descrição**: Informações adicionais que podem ser usadas para fornecer mais contexto ou configurar como o microservice deve ser tratado.
   - **Exemplo**: Dados personalizados como `"version":"1.0"`, `"region":"us-west"`.

6. **Porta (Port)**
   - **Descrição**: A porta na qual o microservice está escutando.
   - **Exemplo**: `8080`.

7. **IP Address**
   - **Descrição**: O endereço IP da instância do microservice.
   - **Exemplo**: `"192.168.1.10"`.

8. **Secure Port (Porta Segura)**
   - **Descrição**: A porta segura (HTTPS) na qual o microservice está escutando, se aplicável.
   - **Exemplo**: `8443`.

9. **Status do Serviço (Service Status)**
   - **Descrição**: Indica o status operacional atual do serviço, como "UP", "DOWN", "STARTING", "OUT_OF_SERVICE".
   - **Exemplo**: `"UP"`.

10. **Data Center Info**
   - **Descrição**: Informação sobre o data center em que a instância está sendo executada.
   - **Exemplo**: `"Amazon AWS"`, `"MyOwnDC"`.

### Configuração do Cliente Eureka

A maior parte dessas informações é configurada automaticamente pelo Spring Cloud Eureka Client, mas algumas podem ser personalizadas conforme necessário no arquivo `application.yml` ou `application.properties` de cada microservice.

Exemplo de configuração personalizada no `application.yml` de um microservice:

```yaml
eureka:
  instance:
    hostname: localhost
    instance-id: ${spring.application.name}:${spring.application.instance_id:${random.value}}
    prefer-ip-address: true
    lease-renewal-interval-in-seconds: 30
    lease-expiration-duration-in-seconds: 90
    metadata-map:
      version: 1.0
      region: us-west
  client:
    service-url:
      defaultZone: http://localhost:8761/eureka/
```

### Conclusão

O Discovery Server é um componente essencial em arquiteturas de microservices,
pois permite o registro e a descoberta dinâmica de serviços, simplificando a 
comunicação e aumentando a resiliência e a escalabilidade do sistema. Utilizando
o Spring Cloud Netflix Eureka, você pode configurar um servidor de descoberta 
robusto e eficiente, bem como registrar seus microservices de maneira simples 
e integrada.

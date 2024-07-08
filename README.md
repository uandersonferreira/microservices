# 📦 Projeto de Microsserviços com Spring Boot

Bem-vindo ao meu projeto de Microsserviços com Spring Boot, desenvolvido com base no curso gratuito do canal [Uncle Dave's Code](https://youtube.com/playlist?list=PLlYjHWCxjWmAt5hE3OEaemlWkZBZa7w4e&si=aui3-Huf5hn-GYeJ). 🚀

## 📝 Descrição do Projeto

Nessa série sobre microsserviços, embarcaremos em uma jornada empolgante para construir uma aplicação escalável e robusta utilizando o poder do Spring Boot. Ao longo do caminho, você dominará as técnicas essenciais para projetar e implementar microsserviços independentes que se unem para formar um sistema funcional coeso.

### Aprenda:
- **Modularidade e Decomposição**: Domine a arte de dividir a lógica de negócios em microsserviços autônomos e interconectáveis.
- **Comunicação Inter-serviços**: Explore as melhores práticas para comunicação eficiente entre microsserviços, garantindo um sistema coeso e robusto.
- **Desenvolvimento Ágil com Spring Boot**: Aproveite os recursos poderosos do Spring Boot, como injeção de dependência e gerenciamento automático de configuração, para acelerar o desenvolvimento e a eficiência do seu projeto.

## 🎥 Aulas do Curso
1. **Aula 01**: Criando um aplicativo escalável – microsserviços com Spring Boot
2. **Aula 02**: Eureka Netflix e Spring Cloud Gateway – Microsserviços com Spring Boot
3. **Aula 03**: Spring Security, Keycloak e Resilience4j – Microsserviços com Spring Boot
4. **Aula 04**: Apache Kafka – Microsserviços com Spring Boot
5. **Aula 05**: Rastreamento e Monitoramento – Microsserviços com Spring Boot

## 🛠️ Tecnologias Utilizadas
- **Java 17** ☕ : Linguagem de programação utilizada.
- **Maven** 📦: Gerenciamento de dependências e build.
- **Spring Boot 3+** 🍃: Framework para construção de microsserviços.
- **Spring Security 6+** 🔒: Segurança e autenticação. [Documentação 📚](https://docs.spring.io/spring-security/site/docs/current/reference/html5/)
- **Eureka Netflix** 🌐: Descoberta de serviços. [Documentação 📚](https://cloud.spring.io/spring-cloud-netflix/reference/html/)
- **Spring Cloud Gateway** 🛡️: API Gateway. [Documentação 📚](https://docs.spring.io/spring-cloud-gateway/docs/current/reference/html/)
- **Keycloak** 🔑: Servidor de autenticação e autorização. [Documentação 📚](https://www.keycloak.org/documentation.html)
- **Resilience4j** ⚡: Tolerância a falhas. [Documentação 📚](https://resilience4j.readme.io/docs)
- **Apache Kafka** 📦: Sistema de mensagens distribuídas. [Documentação 📚](https://kafka.apache.org/documentation/)
- **Zipkin** 🔍: Rastreamento distribuído. [Documentação 📚](https://zipkin.io/pages/quickstart)
- **Prometheus** 📊: Monitoramento de infraestrutura. [Documentação 📚](https://prometheus.io/docs/introduction/overview/)
- **Grafana** 📈: Visualização de métricas. [Documentação 📚](https://grafana.com/docs/)
- 🐳 **Docker** para gerenciamento de containers.

## 🧩 Módulos/Microsserviços
- `discovery-server`
- `api-gateway`
- `inventory-service`
- `orders-service`
- `products-service`
- `notification-service`

## 🗄️ Bancos de Dados no Docker
- **db-inventory**: Postgres para Inventário
- **db-orders**: MySQL para Pedidos
- **db-products**: Postgres para Produtos
- **db-keycloak**: Postgres para Keycloak

## 🌐 Aplicações no Docker
- **keycloak**: Servidor de autenticação e autorização
- **zookeeper**: Coordenação de serviços distribuídos
- **kafka**: Servidor de mensagens distribuídas
- **zipkin**: Rastreamento distribuído
- **prometheus**: Monitoramento de infraestrutura
- **grafana**: Painel de visualização de métricas

## 🔧 Configurações

### `api-gateway`
Esta configuração define um API Gateway que atua como um único ponto de entrada para uma arquitetura de microsserviços. Ele utiliza Eureka para descoberta de serviço, Keycloak para autenticação e Zipkin para rastreamento. O API Gateway também expõe endpoints do actuator para seu próprio monitoramento de saúde.

### `discovery-server`
Esta configuração define um Eureka server autônomo que atua como um registro central para microsserviços em sua aplicação. Ele usa autenticação básica para proteger o acesso e expõe endpoints do actuator para monitoramento de saúde. Também está configurado para usar Zipkin para rastreamento distribuído.

## ⚖️ Balanceamento de Carga e Circuit Breaker
O projeto inclui configurações de balanceamento de carga e circuit breaker utilizando Resilience4j, garantindo a alta disponibilidade e resiliência do sistema. Todas as requisições passam pelo API Gateway, que distribui a carga entre os serviços disponíveis e utiliza circuit breaker para lidar com falhas temporárias.

## 📝 Pré-requisitos
Antes de começar, você vai precisar ter instalado em sua máquina as seguintes ferramentas:
- [Git](https://git-scm.com)
- [Java 17](https://www.oracle.com/java/technologies/javase-jdk17-downloads.html)
- [Docker](https://www.docker.com/get-started)
- [Maven](https://maven.apache.org)

Além disso, é bom ter um editor para trabalhar com o código, como o [IntelliJ IDEA](https://www.jetbrains.com/idea/download) ou [VSCode](https://code.visualstudio.com).

## 🚀 Como Executar o Projeto

```bash
# Clone este repositório
$ git clone https://github.com/uandersonferreira/microservices

# Acesse a pasta do projeto no terminal/cmd
$ cd microservices

# Execute o Docker Compose para iniciar os serviços
$ docker-compose up

# Navegue até a pasta de cada módulo e execute o Maven para iniciar os microsserviços
$ cd discovery-server
$ mvn spring-boot:run

# Repita o comando acima para cada um dos microsserviços:
# api-gateway, inventory-service, orders-service, products-service, notification-service
# Ou utilize uma IDE para facilitar a execução
```
## 📂 Repositório do Projeto
Você pode acessar o código fonte do projeto no [GitHub](https://github.com/uandersonferreira/microservices).

## 🎉 Agradecimentos
Um agradecimento especial ao canal [Uncle Dave's Code](https://youtube.com/playlist?list=PLlYjHWCxjWmAt5hE3OEaemlWkZBZa7w4e&si=aui3-Huf5hn-GYeJ) pelo excelente curso gratuito que serviu de base para este projeto! 🙌

---
Feito com ❤️ por [Uanderson Ferreira de Oliveira]

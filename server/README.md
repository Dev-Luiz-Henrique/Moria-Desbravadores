# API Moria Desbravadores

Este projeto é uma aplicação RESTful desenvolvida em **Java 17**, utilizando **Spring Boot** com princípios de **Domain-Driven Design (DDD)**. Ele utiliza **JWT** para autenticação e segue as melhores práticas de organização de código, testes, documentação e configuração para um ambiente Docker.

##  Estrutura do Projeto

O projeto está organizado com base em três diretórios principais, que agrupam os diferentes aspectos do sistema de forma funcional e modular:

### **1. Configurations**
   - Contém as configurações técnicas e de infraestrutura essenciais para o funcionamento do sistema. Exemplos de elementos incluídos:
     - **Configurações de segurança**: Configurações de CORS, autenticação JWT e regras de acesso.
     - **Internacionalização**: Configuração de mensagens e suporte a diferentes localidades (i18n).
     - **Inicialização de dados**: Classes ou scripts responsáveis por carregar dados iniciais no ambiente da aplicação.

### **2. Domains**
   - Este diretório é o núcleo funcional do sistema, organizado em módulos que refletem áreas de negócios específicas (ex.: `auth`, `evento`, `membro`). Cada módulo pode conter:
     - **Entidades**: Classes que representam os dados principais e comportamentos do domínio.
     - **DTOs**: Objetos de transferência de dados que desacoplam o domínio de outras camadas.
     - **Repositórios**: Interfaces para persistência e manipulação dos dados do domínio.
     - **Serviços**: Implementações de regras de negócio específicas para cada módulo.
     - **Controladores**: Classes responsáveis por expor endpoints REST que interagem com o domínio.
     - **Mappers**: Utilitários para conversão entre entidades e DTOs.
     - **Enums e Exceções**: Definições de constantes e erros personalizados associados ao domínio.

### **3. Shared**
   - Agrupa componentes e recursos genéricos que podem ser usados por todos os módulos do sistema. Inclui:
     - **Enums**: Definições reutilizáveis (ex.: `EntityType`, `MessageKey`).
     - **Exceções**: Classes de erro genéricas aplicáveis em diferentes contextos.
     - **Utilitários**: Ferramentas auxiliares, como manipuladores de datas ou mensagens.

> Esta organização facilita a compreensão e manutenção do projeto, além de promover uma modularidade que respeita a separação de responsabilidades.

## Tecnologias e Ferramentas

- **Java 17**
- **Spring Boot**
  - **Spring Security**: Gerenciamento de autenticação e autorização com JWT.
  - **Spring Data JPA**: Para persistência e integração com MySQL.
  - **Spring Validation**: Para validação de dados de entrada nas requisições.
  - **Spring Web**: Criação de endpoints RESTful.
- **MapStruct**: Facilita o mapeamento entre DTOs e entidades de domínio.
- **Swagger**: Para geração e visualização da documentação dos endpoints.
- **JUnit** e **Mockito**: Para testes unitários e de integração.
- **Maven**: Gerenciamento de dependências e ciclo de vida do projeto.
- **Docker**: Empacotamento e execução da aplicação em containers.

##  Documentação

### Swagger

A documentação dos endpoints está disponível automaticamente via **Swagger UI**. Para acessá-la, basta executar o projeto e acessar:

```
http://localhost:8080/swagger-ui.html
```

### Javadoc

O código é documentado com **Javadoc**, permitindo a geração de documentação técnica detalhada. Para gerar a documentação localmente, execute:

```bash
mvn javadoc:javadoc
```

Os arquivos HTML serão gerados na pasta `target/site/apidocs`.

##  Testes

O projeto inclui um conjunto abrangente de testes para garantir a confiabilidade e qualidade do código:

- **Testes Unitários**: Foco na camada de domínio e nos casos de uso.
- **Testes de Integração**: Garantem que o fluxo completo (incluindo banco de dados) funciona como esperado.

Para executar os testes:

```bash
mvn test
```

##  Autenticação e Segurança

O sistema utiliza **JWT (JSON Web Tokens)** para autenticação e proteção das rotas. Os detalhes incluem:

- **Rotas públicas**: Apenas a rota de login é pública.
- **Rotas protegidas**: Todas as demais rotas exigem um token JWT válido.

Os tokens são validados em cada requisição, e respostas apropriadas são retornadas em caso de erros, como:

- `401 Unauthorized`: Token inválido ou ausente.
- `403 Forbidden`: Acesso negado.

##  Tratamento de Erros

O sistema possui uma estratégia global para tratar erros, utilizando `@ControllerAdvice` para capturar exceções e retorná-las de forma padronizada no formato JSON.

Exemplos de respostas de erro:

- **400 Bad Request**: Erros de validação.
- **401 Unauthorized**: Problemas de autenticação.
- **404 Not Found**: Recursos inexistentes.
- **500 Internal Server Error**: Erros inesperados.

##  Configuração do MySQL

As configurações do banco de dados estão centralizadas no arquivo `application.properties`. Exemplo:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/nome_do_banco
spring.datasource.username=usuario
spring.datasource.password=senha
spring.jpa.hibernate.ddl-auto=update
```

##  Como Executar a API Localmente

1. Clone o repositório:

```bash
git clone <URL_DO_REPOSITORIO>
```

2. Configure o banco de dados no arquivo `application.properties`.

3. Execute o projeto:

```bash
mvn spring-boot:run
```

4. Acesse a aplicação:
   - Endpoints REST: `http://localhost:8080`
   - Swagger UI: `http://localhost:8080/swagger-ui.html`

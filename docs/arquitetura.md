# Arquitetura do Sistema

## 1. Visão Geral
O sistema "Moria Desbravadores" utiliza uma arquitetura cliente-servidor de três camadas (“three-tier”), composta por:

1. **Camada de Apresentação (Front-end)**: Responsável pela interface com o usuário.
2. **Camada de Negócios (Back-end)**: Contém a lógica do sistema e gerencia as regras de negócio.
3. **Camada de Dados (Banco de Dados)**: Armazena e gerencia os dados da aplicação.

Essa estrutura garante separação de responsabilidades, escalabilidade e manutenção simplificada.

## 2. Detalhamento das Camadas

### 2.1. Camada de Apresentação (Front-end)
- **Tecnologia Utilizada**: [React](https://reactjs.org/).
- **Funções**:
  - Exibir informações ao usuário.
  - Coletar entradas do usuário e enviar ao back-end via APIs RESTful.
- **Hospedagem**: Servidor web dedicado.
- **Destaques**:
  - Design responsivo para dispositivos móveis e desktops.
  - Experiência rica e dinâmica.

### 2.2. Camada de Negócios (Back-end)
- **Tecnologia Utilizada**: [Spring Boot](https://spring.io/projects/spring-boot).
- **Funções**:
  - Implementar regras de negócio e processar requisições.
  - Gerenciar a segurança utilizando JWT.
  - Intermediar comunicação entre o front-end e o banco de dados.
- **Hospedagem**: Servidor web dedicado.

### 2.3. Camada de Dados (Banco de Dados)
- **Tecnologia Utilizada**: [MySQL](https://www.mysql.com/).
- **Funções**:
  - Armazenar e gerenciar dados do sistema.
  - Operar utilizando ORM (Hibernate) para simplificar manipulações CRUD.
- **Hospedagem**: Servidor de banco de dados dedicado.

## 3. Integração Entre Camadas
- **Front-end ⇔ Back-end**: Comunicação via APIs RESTful utilizando HTTPS.
- **Back-end ⇔ Banco de Dados**: Operações seguras e eficientes via JPA (Java Persistence API).

## 4. Benefícios da Arquitetura

- **Separação de Responsabilidades**: Cada camada possui uma função clara.
- **Escalabilidade**: Camadas podem ser dimensionadas independentemente.
- **Manutenção Simplificada**: Atualizações em uma camada não impactam as demais.
- **Segurança**: Acesso ao banco de dados restrito ao back-end, protegendo contra injeções SQL.
- **Flexibilidade**: Permite substituição de tecnologias em qualquer camada sem impacto global.

## 5. Tecnologias Principais
- **Front-end**: React.
- **Back-end**: Spring Boot.
- **Banco de Dados**: MySQL.
- **Hospedagem**: Servidores dedicados.
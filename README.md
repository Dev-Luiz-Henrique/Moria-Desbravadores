# Moria Desbravadores - Sistema de Gerenciamento do Clube de Desbravadores

## Introdução

O **Meu Moriá** é um sistema projetado para atender às necessidades do Clube de Desbravadores – Moriá, com foco no gerenciamento de membros, organização de eventos e controle financeiro. Este README apresenta uma visão geral do sistema, seus objetivos, funcionalidades e organização do repositório.

## Caracterização do Sistema

### Objetivos

O sistema foi desenvolvido para:

- **Gerenciar Membros**: Facilitar o cadastro, edição e visualização de membros, incluindo desbravadores, voluntários e responsáveis.
- **Controlar o Fluxo Financeiro**: Automatizar o controle de mensalidades e gerenciar recursos financeiros alocados para eventos.
- **Organizar Eventos**: Proporcionar ferramentas para criação, divulgação, inscrição e monitoramento de participações em eventos.

### Escopo

O **Moria Desbravadores** centraliza e automatiza os processos administrativos do Clube de Desbravadores, promovendo eficiência, segurança e acessibilidade. O escopo do sistema é definido pelos seguintes aspectos:

1. **Gestão de Membros**:
   - Cadastro de membros, incluindo informações pessoais, categorias (desbravadores, voluntários, responsáveis) e status.
   - Controle de permissões e autenticação para garantir acesso seguro às informações.
   - Histórico detalhado de participação e atividades dos membros.

2. **Organização de Eventos**:
   - Criação e gerenciamento de eventos públicos e privados.
   - Definição de público-alvo para eventos, como categorias específicas de membros.
   - Controle de inscrições e geração de relatórios de participação.
   - Avaliação pós-evento para coleta de feedback e melhorias futuras.

3. **Controle Financeiro**:
   - Registro de pagamentos de mensalidades e gestão de recursos financeiros alocados para eventos.
   - Emissão de relatórios financeiros para acompanhamento detalhado das receitas e despesas.
   - Monitoramento de orçamentos para diferentes atividades e eventos do clube.

4. **Interface e Acessibilidade**:
   - Design intuitivo e responsivo para garantir uma experiência amigável em dispositivos diversos.
   - Implementação de padrões de acessibilidade para atender a usuários com diferentes necessidades.

5. **Segurança e Privacidade**:
   - Proteção dos dados dos usuários através de autenticação robusta e criptografia.
   - Controle de acesso baseado em funções, garantindo que cada usuário veja apenas as informações relevantes para seu perfil.

Ao alinhar as funcionalidades às necessidades reais do Clube de Desbravadores – Moriá, o sistema busca otimizar o tempo dos gestores, engajar membros e promover uma administração eficiente e transparente.

## Estrutura do Repositório

Links para os principais tópicos explicativos:

- **`docs/`**: Contém documentação geral sobre o sistema e seus fluxos, incluindo [Requisitos](docs/requisitos.md), [Arquitetura](docs/arquitetura.md), [Diagramas](docs/diagram.md), [Fluxograma de Processos](docs/fluxos.md), [Protótipos de Interface](docs/interfaces.md) e [Plano de Testes](docs/testes.md).
- **`front-end/`**: Implementação do cliente.
- **`server/`**: Backend com endpoints e lógica de negócios.

## Funcionalidades

### Gerenciamento de Membros

- Cadastro, edição e visualização detalhada.
- Controle de permissões e autenticação de usuários.
- Monitoramento de status e histórico.

### Controle Financeiro

- Registro de mensalidades e recursos de eventos.
- Geração de relatórios financeiros detalhados.
- Configuração e monitoramento de orçamentos.

### Organização de Eventos

- Criação e definição de público-alvo.
- Registro e controle de participações.
- Histórico de eventos e avaliações.

## Tecnologias Utilizadas

### **Backend**
- **Java 17** - Linguagem principal da aplicação backend.
- **Spring Boot 3.3.4** - Framework para construção de aplicações web e microsserviços.
  - **Spring Web** - Para construção de APIs REST.
  - **Spring Security** - Gerenciamento de autenticação e autorização.
  - **Spring Data JPA** - Abstração para persistência de dados com o banco relacional.
  - **Spring Validation** - Validação de dados de entrada.
- **JWT (JSON Web Token)** - Autenticação baseada em token.
- **MapStruct** - Framework para mapeamento de objetos DTO.
- **Lombok** - Redução de boilerplate no código.
- **Maven** - Gerenciamento de dependências e build da aplicação.
- **MySQL Connector** - Conector para o banco de dados MySQL.

### **Frontend**
- **React 18** - Biblioteca para construção de interfaces de usuário reativas.
- **React Router DOM** - Gerenciamento de rotas no frontend.
- **Axios** - Cliente HTTP para comunicação com a API.
- **Vite** - Ferramenta para build e desenvolvimento rápido.
- **ESLint** - Linter para manter qualidade do código.
- **JWT-Decode** - Decodificação de tokens JWT no cliente.
- **SCSS (Sass)** - Pré-processador CSS para melhor organização de estilos.

### **Banco de Dados**
- **MySQL** - Banco de dados relacional utilizado para armazenamento persistente de dados.

### **Ferramentas de Desenvolvimento**
- **Docker** - Para containerização da aplicação.
- **Postman** - Testes manuais da API REST.
- **Swagger/OpenAPI** - Documentação automatizada da API.
- **JUnit** - Framework de testes unitários.
- **Mockito** - Mocking de dependências em testes.

## Contribuições

Contribuições são bem-vindas!  
Sinta-se à vontade para abrir issues e enviar pull requests com melhorias, correções de bugs ou novas funcionalidades.  
Certifique-se de seguir as boas práticas de desenvolvimento e as diretrizes do projeto.  

## Agradecimentos

Agradecemos a todas as pessoas que contribuíram para este projeto, seja com código, sugestões, testes ou feedback.  

### Contribuidores

Gostaríamos de reconhecer o esforço das seguintes pessoas que ajudaram no desenvolvimento do projeto:

- [Luiz Henrique de Santana](https://github.com/Dev-Luiz-Henrique) - Desenvolvedor Fullstack
- [Gabriel Cruz](https://github.com/gabrielg-cruz) - Desenvolvedor Fullstack
- [Daniel Leite Pereira](https://github.com/danielleiteee) - Desenvolvedor Frontend
- [Wesley Paulow](https://github.com/wpaulow) - Desenvolvedor Backend
- [Diego de Paula](https://github.com/Odiiego) - Desenvolvedor Frontend
- [Nicole Carvalho Fukushima](https://github.com/nicolefukushima) - UX/UI Designer

Se você deseja ser reconhecido como colaborador, sinta-se à vontade para adicionar seu nome nesta lista.

## Licença

Este projeto está licenciado sob a [Creative Commons Attribution-NonCommercial 4.0 International](LICENSE).  
Isso significa que você pode usar, compartilhar e modificar o projeto, **desde que não seja para fins comerciais**.  
Confira os detalhes completos da licença [aqui](https://creativecommons.org/licenses/by-nc/4.0/legalcode).


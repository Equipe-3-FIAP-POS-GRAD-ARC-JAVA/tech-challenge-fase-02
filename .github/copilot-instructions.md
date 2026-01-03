# Documento de Arquitetura e Requisitos para o Sistema de Restaurante

Este documento serve como guia para o desenvolvimento: descreve os requisitos funcionais e não funcionais, o modelo de dados e as regras de negócio essenciais.
O documento deve seguir as diretrizes do arquivo `docs/ADJ_Fase 2 - Tech Challenge.pdf` fornecido.

---
# O problema
O problema
Na nossa região, um grupo de restaurantes decidiu contratar estudantes para construir um sistema de gestão para seus estabelecimentos. Essa decisão foi motivada pelo alto custo de sistemas individuais, o que levou os restaurantes a se unirem para desenvolver um sistema único e compartilhado. Esse sistema permitirá que os clientes escolham restaurantes com base na comida oferecida, em vez de se basearem na qualidade do sistema de gestão.
O objetivo é criar um sistema robusto que permita a todos os restaurantes gerenciar eficientemente suas operações, enquanto os clientes poderão consultar informações, deixar avaliações e fazer pedidos online. Devido à limitação de recursos financeiros, foi acordado que a entrega do sistema será realizada em fases, garantindo que cada etapa seja desenvolvida de forma cuidadosa e eficaz.
A divisão em fases possibilitará uma implementação gradual e controlada, permitindo ajustes e melhorias contínuas conforme o sistema for sendo utilizado e avaliado pelos restaurantes e clientes.

# Objetivo
Essa fase expande o sistema ao incluir a gestão dos tipos de usuários, cadastro de restaurantes e cardápios, reforçando práticas de desenvolvimento e estruturação de código limpo. Além disso, são incluídos requisitos técnicos para garantir que o sistema mantenha alta qualidade e organização, com suporte para documentação, testes automatizados e infraestrutura Docker para uma execução integrada.

## Parte 1: Requisitos Funcionais (RF)

### RF01: Gestão de Tipos de Usuário
- Implementar CRUD para tipos de usuário, distinguindo entre "Dono de Restaurante" e "Cliente".
- Campos: Nome do Tipo de Usuário.
- Associar usuários existentes a tipos de usuário.

### RF02: Cadastro de Restaurantes
- Implementar CRUD para restaurantes.
- Campos: Nome, Endereço, Tipo de Cozinha, Horário de Funcionamento, Dono do Restaurante (usuário associado).

### RF03: Cadastro Cardápio
- Implementar CRUD para cardapio e itens do cardápio.
- Campos: Nome, Descrição, Preço, Disponibilidade para Consumo Apenas no Local, Caminho da Foto do Prato.

### RF04: Consultas e Avaliações (Futuro)
- Permitir que clientes consultem informações dos restaurantes.
- Permitir avaliações e pedidos online (a serem implementados em fases futuras).

### RF05: Gestão de Operações (Futuro)
- Permitir que donos gerenciem operações dos restaurantes eficientemente (expansão futura).

---
## Requisitos Não Funcionais

### RNF01: Qualidade do Código
- Uso adequado das práticas de desenvolvimento do Spring Boot.
- Código organizado e documentado.

### RNF02: Documentação
- Descrição detalhada do projeto, incluindo arquitetura, endpoints da API e instruções de configuração e execução.

### RNF03: Testes
- Testes unitários com cobertura de 80%.
- Testes de integração para garantir funcionamento dos componentes.

### RNF04: Infraestrutura
- Arquivo docker-compose.yml configurado para subir a aplicação Java e o banco de dados.

### RNF05: Arquitetura
- Organizar o código em camadas (Domain, Application, Infrastructure) seguindo Clean Architecture.

### RNF06: Repositório
- Repositório de código aberto (GitHub, GitLab) para acesso aos professores.

### RNF07: Testes de API
- Collections do Postman ou similar para testar os endpoints da API.

### RNF09: Controle de Qualidade de Commits
- Nunca commitar código sem revisão humana. Todos os commits devem ser revisados por pelo menos um desenvolvedor antes de serem enviados ao repositório.

### Commit Message Guidelines
Sempre gere mensagens de commit em inglês e comitar os arquivos em grupos lógicos seguindo a especificação Conventional Commits:

**Formato**
```
<type>[optional scope]: <description>

[optional body]

[optional footer(s)]
```

**Tipos de Commit**
*   `feat`: Uma nova feature
*   `fix`: Uma correção de bug
*   `docs`: Mudanças apenas na documentação
*   `style`: Mudanças que não afetam o significado do código (espaços em branco, formatação, etc.)
*   `refactor`: Uma mudança de código que não corrige um bug nem adiciona uma feature
*   `test`: Adicionando testes ausentes ou corrigindo testes existentes
*   `chore`: Mudanças no processo de build ou ferramentas auxiliares

**Exemplos**
```
feat(user): add user registration endpoint
fix(account): correct balance calculation in transaction
docs(readme): update architecture documentation
style(domain): format value objects for consistency
refactor(persistence): improve repository adapter structure
test(user): add Cucumber scenarios for email validation
chore(docker): update compose configuration
```
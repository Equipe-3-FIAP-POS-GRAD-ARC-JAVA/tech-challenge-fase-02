# Docker Setup - Restaurant API

## 📋 Visão Geral

Esta aplicação Spring Boot está totalmente dockerizada com PostgreSQL 15 e suporta múltiplos ambientes através de profiles.

## 🚀 Início Rápido

### Pré-requisitos
- Docker (versão 20.10+)
- Docker Compose (versão 2.0+)

### Executar a Aplicação

1. **Clone o repositório e navegue até a pasta do projeto**

2. **Copie o arquivo de exemplo de variáveis de ambiente (opcional)**
   ```bash
   cp .env.example .env
   ```

3. **Inicie os serviços**
   ```bash
   docker-compose up -d
   ```

   Isso irá:
   - Construir a imagem da aplicação Java
   - Iniciar o banco de dados PostgreSQL
   - Iniciar a aplicação Spring Boot
   - Aguardar o banco ficar saudável antes de iniciar a aplicação

4. **Verifique o status dos serviços**
   ```bash
   docker-compose ps
   ```

5. **Acesse a aplicação**
   - API: http://localhost:8080
   - Swagger UI: http://localhost:8080/swagger-ui.html
   - Health Check: http://localhost:8080/actuator/health
   - API Docs: http://localhost:8080/v3/api-docs

## ⚙️ Configuração

### Variáveis de Ambiente

As seguintes variáveis podem ser configuradas no arquivo `.env`:

| Variável | Descrição | Valor Padrão |
|----------|-----------|--------------|
| `DB_HOST` | Host do banco de dados | `postgres` |
| `DB_PORT` | Porta do banco de dados | `5432` |
| `DB_NAME` | Nome do banco de dados | `restaurant` |
| `DB_USER` | Usuário do banco de dados | `postgres` |
| `DB_PASSWORD` | Senha do banco de dados | `password` |
| `SPRING_PROFILES_ACTIVE` | Profile ativo do Spring | `dev` |
| `SERVER_PORT` | Porta da aplicação | `8080` |
| `JPA_DDL_AUTO` | Modo DDL do Hibernate | `update` |
| `JPA_SHOW_SQL` | Mostrar SQL no console | `true` |

### Profiles Disponíveis

#### `dev` (Desenvolvimento)
- Logs detalhados (DEBUG)
- Show SQL habilitado
- Health checks com detalhes completos
- DDL auto mode: `update`

#### `prod` (Produção)
- Logs otimizados (INFO/WARN)
- Show SQL desabilitado
- Health checks com autorização
- DDL auto mode: `validate`
- Connection pool otimizado (Hikari)

Para mudar o profile:
```bash
export SPRING_PROFILES_ACTIVE=prod
docker-compose up -d
```

## 🔧 Comandos Úteis

### Visualizar logs
```bash
# Todos os serviços
docker-compose logs -f

# Apenas a aplicação
docker-compose logs -f app

# Apenas o banco de dados
docker-compose logs -f postgres
```

### Reconstruir a aplicação
```bash
docker-compose up -d --build app
```

### Parar os serviços
```bash
docker-compose down
```

### Parar e remover volumes (limpa o banco de dados)
```bash
docker-compose down -v
```

### Executar comandos Maven no container
```bash
# Rodar testes
docker-compose exec app ./mvnw test

# Gerar relatório de cobertura
docker-compose exec app ./mvnw jacoco:report
```

## 🏗️ Arquitetura Docker

### Serviços

#### `postgres`
- Imagem: `postgres:15`
- Porta: `5432` (mapeada para host)
- Volume persistente: `postgres_data`
- Health check: verifica se o PostgreSQL está pronto

#### `app`
- Build: Multi-stage com Maven + Eclipse Temurin JDK 21
- Porta: `8080` (mapeada para host)
- Usuário não-root: `app` (uid 2000)
- Depende de: `postgres` (aguarda health check)
- Resource limits:
  - CPU: 0.5-1.0 cores
  - Memory: 512MB-1GB

### Network
- Rede dedicada: `restaurant-network` (bridge)
- Comunicação interna entre serviços

## 🔍 Health Checks

### Aplicação
- Endpoint: `http://localhost:8080/actuator/health`
- Intervalo: 30s
- Timeout: 10s
- Start period: 40s (aguarda inicialização do Spring Boot)

### Banco de Dados
- Comando: `pg_isready -U postgres`
- Intervalo: 10s
- Timeout: 5s

## 📦 Build da Imagem

O Dockerfile utiliza multi-stage build:

1. **Stage 1 - Builder**
   - Base: `maven:3.9-eclipse-temurin-21`
   - Compila o projeto com `mvn package -DskipTests`
   - Gera o JAR executável

2. **Stage 2 - Runtime**
   - Base: `eclipse-temurin:21-jre-noble`
   - Apenas JRE (imagem menor)
   - Executa com usuário não-root
   - Expõe porta 8080

### Otimizações
- `.dockerignore` exclui arquivos desnecessários (target/, .git/, etc.)
- Cache de layers do Maven
- Imagem final otimizada (~300MB vs ~1GB com JDK)

## 🧪 Testando a API

### Com curl
```bash
# Health check
curl http://localhost:8080/actuator/health

# Listar endpoints
curl http://localhost:8080/actuator

# Exemplo de requisição (ajuste conforme seus endpoints)
curl http://localhost:8080/api/restaurants
```

### Com Postman
Importe a collection disponível em: `docs/postman/Restaurant-API.postman_collection.json`

## 🐛 Troubleshooting

### A aplicação não inicia
1. Verifique os logs: `docker-compose logs app`
2. Confirme que o PostgreSQL está saudável: `docker-compose ps`
3. Verifique as variáveis de ambiente: `docker-compose config`

### Erro de conexão com o banco
- Confirme que `DB_HOST=postgres` (não `localhost`)
- Aguarde o health check do PostgreSQL completar

### Porta já em uso
```bash
# Altere a porta no .env
SERVER_PORT=8081
docker-compose up -d
```

### Limpar tudo e recomeçar
```bash
docker-compose down -v
docker-compose up -d --build
```

## 📚 Recursos Adicionais

- [Spring Boot Docker Documentation](https://spring.io/guides/gs/spring-boot-docker/)
- [Docker Compose Documentation](https://docs.docker.com/compose/)
- [PostgreSQL Docker Image](https://hub.docker.com/_/postgres)

## 🔒 Segurança

**⚠️ IMPORTANTE**: O arquivo `.env.example` contém valores padrão apenas para desenvolvimento local.

Para **produção**:
1. **NUNCA** commite o arquivo `.env` com credenciais reais
2. Use Docker secrets ou sistemas de gerenciamento de secrets
3. Mude todas as senhas padrão
4. Configure `SPRING_PROFILES_ACTIVE=prod`
5. Considere usar um reverse proxy (nginx) na frente da aplicação

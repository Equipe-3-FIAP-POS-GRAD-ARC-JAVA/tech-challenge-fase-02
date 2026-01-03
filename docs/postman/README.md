# Restaurant API - Postman Collection

Esta collection contém todos os endpoints disponíveis na API de Gestão de Restaurantes.

## Importação

1. Abra o Postman
2. Clique em "Import"
3. Selecione o arquivo `Restaurant-API.postman_collection.json`
4. A collection será importada com todas as requisições organizadas

## Variáveis de Ambiente

A collection utiliza as seguintes variáveis:

- `baseUrl`: URL base da API (padrão: `http://localhost:8080`)
- `restaurantId`: ID do restaurante (preenchido automaticamente após criação)
- `menuId`: ID do menu (preenchido automaticamente após criação)
- `foodId`: ID do alimento (preenchido automaticamente após criação)
- `foodTypeId`: ID do tipo de alimento (preenchido automaticamente após criação)

## Estrutura da Collection

### 1. Restaurant
Operações CRUD para restaurantes:
- **Create Restaurant**: Cria um novo restaurante
- **List All Restaurants**: Lista todos os restaurantes
- **Get Restaurant By Id**: Busca restaurante por ID
- **Update Restaurant**: Atualiza um restaurante existente
- **Delete Restaurant**: Remove um restaurante

### 2. Menu
Operações CRUD para cardápios:
- **Create Menu**: Cria um novo cardápio vinculado a um restaurante
- **List All Menus**: Lista todos os cardápios
- **Get Menu By Id**: Busca cardápio por ID
- **Get Menus By Restaurant**: Lista cardápios de um restaurante específico
- **Update Menu**: Atualiza um cardápio existente
- **Delete Menu**: Remove um cardápio

### 3. Food Type
Operações CRUD para tipos de alimento:
- **Create Food Type**: Cria um novo tipo (ex: Entrada, Prato Principal, Sobremesa)
- **List All Food Types**: Lista todos os tipos
- **Get Food Type By Id**: Busca tipo por ID
- **Update Food Type**: Atualiza um tipo existente
- **Delete Food Type**: Remove um tipo

### 4. Food
Operações CRUD para alimentos:
- **Create Food**: Cria um novo alimento no cardápio
- **List All Foods**: Lista todos os alimentos
- **Get Food By Id**: Busca alimento por ID
- **Get Foods By Menu**: Lista alimentos de um cardápio específico
- **Update Food**: Atualiza um alimento existente
- **Delete Food**: Remove um alimento

## Fluxo de Uso Recomendado

Para testar a API completa, siga esta ordem:

1. **Criar Food Type** → armazena `foodTypeId`
2. **Criar Restaurant** → armazena `restaurantId`
3. **Criar Menu** (usando `restaurantId`) → armazena `menuId`
4. **Criar Food** (usando `menuId` e `foodTypeId`) → armazena `foodId`
5. Testar outros endpoints de consulta, atualização e deleção

## Scripts Automatizados

As requisições de criação incluem scripts que:
- **Pre-request**: Geram nomes únicos usando timestamp
- **Test**: Armazenam automaticamente os IDs nas variáveis de collection
- **Validação**: Verificam se os objetos foram criados com sucesso

## Exemplos de Payloads

### Restaurant
```json
{
  "name": "Meu Restaurante",
  "address": {
    "street": "Avenida Paulista",
    "number": "1000",
    "city": "São Paulo",
    "neighborhood": "Bela Vista",
    "country": "Brasil",
    "state": "SP",
    "zipCode": "01310-100"
  }
}
```

### Food Type
```json
{
  "typeFood": "Prato Principal"
}
```

### Menu
```json
{
  "restaurantId": "uuid-do-restaurante"
}
```

### Food
```json
{
  "menuId": "uuid-do-menu",
  "name": "Feijoada Completa",
  "description": "Feijoada tradicional brasileira",
  "foodTypeId": "uuid-do-tipo",
  "price": 45.90,
  "imageURL": "https://example.com/feijoada.jpg"
}
```

## Códigos de Status HTTP

- `200 OK`: Operação bem-sucedida (GET, PUT)
- `201 Created`: Recurso criado com sucesso (POST)
- `204 No Content`: Recurso removido com sucesso (DELETE)
- `404 Not Found`: Recurso não encontrado
- `500 Internal Server Error`: Erro interno do servidor

## Executando a Aplicação

Antes de usar a collection, certifique-se de que a aplicação está rodando:

```bash
# Iniciar apenas o banco de dados
docker compose up -d postgres

# Executar a aplicação
./mvnw spring-boot:run
```

Ou usar Docker completo:

```bash
# Iniciar aplicação + banco de dados
docker compose up --build
```

A API estará disponível em: `http://localhost:8080`

## Documentação da API

Para mais detalhes sobre os endpoints, consulte:
- Swagger UI: `http://localhost:8080/swagger-ui.html` (se configurado)
- Código fonte dos controllers em: `src/main/java/.../infra/web/controller/`

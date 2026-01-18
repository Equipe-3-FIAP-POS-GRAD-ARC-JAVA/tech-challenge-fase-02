# Restaurant API - Postman Collection

Esta collection contém todos os endpoints disponíveis na API de Gestão de Restaurantes.

## Importação

1. Abra o Postman
2. Clique em "Import"
3. Selecione o arquivo `Restaurant-API.postman_collection.json`
4. A collection será importada com todas as requisições organizadas

## Variáveis de Ambiente

A collection utiliza as seguintes variáveis:

### Variáveis de IDs (geradas automaticamente)
- `userId`: ID do usuário (preenchido automaticamente após criação)
- `ownerId`: ID do proprietário (preenchido automaticamente após criação de Owner)
- `restaurantId`: ID do restaurante (preenchido automaticamente após criação)
- `menuId`: ID do menu (preenchido automaticamente após criação)
- `foodId`: ID do alimento (preenchido automaticamente após criação)
- `foodTypeId`: ID do tipo de alimento (preenchido automaticamente após criação)
- `openingHoursId`: ID do horário de funcionamento (preenchido automaticamente após criação)
- `ownerRestaurantId`: ID da ligação owner-restaurant (preenchido automaticamente após vinculação)

### Variáveis com Valores Padrão (podem ser personalizadas)
- `baseUrl`: `http://localhost:8080` - URL base da API
- `userName`: `Emerson Silva` - Nome do proprietário
- `userEmail`: `emerson@mail.com` - Email do proprietário
- `userLogin`: `emerson.silva` - Login do proprietário
- `customerName`: `João Santos` - Nome do cliente
- `customerEmail`: `joao.santos@mail.com` - Email do cliente
- `customerLogin`: `joao.santos` - Login do cliente
- `restaurantName`: `Restaurante Sabor Brasileiro` - Nome do restaurante
- `foodTypeName`: `Prato Principal` - Tipo de alimento
- `foodName`: `Feijoada Completa` - Nome do alimento

**Nota:** Os scripts pre-request sobrescrevem alguns valores padrão com timestamps para garantir unicidade durante os testes automatizados.

## Estrutura da Collection

### 1. Users
Operações CRUD para usuários (Donos e Clientes):
- **Create User (Owner)**: Cria um novo usuário com papel OWNER
- **Create User (Customer)**: Cria um novo usuário com papel CUSTOMER
- **List All Users**: Lista todos os usuários
- **Get User By Id**: Busca usuário por ID
- **Update User**: Atualiza um usuário existente
- **Delete User**: Remove um usuário

### 2. Restaurant
Operações CRUD para restaurantes:
- **Create Restaurant**: Cria um novo restaurante
- **List All Restaurants**: Lista todos os restaurantes
- **Get Restaurant By Id**: Busca restaurante por ID
- **Update Restaurant**: Atualiza um restaurante existente
- **Delete Restaurant**: Remove um restaurante

### 3. Menu
Operações CRUD para cardápios:
- **Create Menu**: Cria um novo cardápio vinculado a um restaurante
- **List All Menus**: Lista todos os cardápios
- **Get Menu By Id**: Busca cardápio por ID
- **Get Menus By Restaurant**: Lista cardápios de um restaurante específico
- **Update Menu**: Atualiza um cardápio existente
- **Delete Menu**: Remove um cardápio

### 4. Food Type
Operações CRUD para tipos de alimento:
- **Create Food Type**: Cria um novo tipo (ex: Entrada, Prato Principal, Sobremesa)
- **List All Food Types**: Lista todos os tipos
- **Get Food Type By Id**: Busca tipo por ID
- **Update Food Type**: Atualiza um tipo existente
- **Delete Food Type**: Remove um tipo

### 5. Food
Operações CRUD para alimentos:
- **Create Food**: Cria um novo alimento no cardápio
- **List All Foods**: Lista todos os alimentos
- **Get Food By Id**: Busca alimento por ID
- **Get Foods By Menu**: Lista alimentos de um cardápio específico
- **Update Food**: Atualiza um alimento existente
- **Delete Food**: Remove um alimento

### 6. Opening Hours
Operações para horários de funcionamento:
- **Create Opening Hours**: Cria horário de funcionamento para um restaurante
- **Get Opening Hours By Restaurant**: Lista horários de um restaurante específico
- **Update Opening Hours**: Atualiza horário de funcionamento existente

### 7. Owner Restaurant
Operações para vincular proprietários a restaurantes:
- **Link Owner to Restaurant**: Vincula um proprietário a um restaurante
- **Get Restaurants By Owner**: Lista restaurantes de um proprietário específico
- **Get Owners By Restaurant**: Lista proprietários de um restaurante específico
- **Unlink Owner from Restaurant**: Remove vínculo entre proprietário e restaurante

## Fluxo de Uso Recomendado

Para testar a API completa, siga esta ordem:

1. **Criar User (Owner)** → armazena `ownerId`
2. **Criar User (Customer)** → armazena `userId`
3. **Criar Food Type** → armazena `foodTypeId`
4. **Criar Restaurant** → armazena `restaurantId`
5. **Link Owner to Restaurant** (usando `ownerId` e `restaurantId`) → armazena `ownerRestaurantId`
6. **Criar Opening Hours** (usando `restaurantId`) → armazena `openingHoursId`
7. **Criar Menu** (usando `restaurantId`) → armazena `menuId`
8. **Criar Food** (usando `menuId` e `foodTypeId`) → armazena `foodId`
9. Testar outros endpoints de consulta, atualização e deleção

## Scripts Automatizados

As requisições de criação incluem scripts que:
- **Pre-request**: Geram nomes únicos usando timestamp
- **Test**: Armazenam automaticamente os IDs nas variáveis de collection
- **Validação**: Verificam se os objetos foram criados com sucesso

## Exemplos de Payloads

### User (Owner)
```json
{
  "name": "João Silva",
  "email": "joao@example.com",
  "login": "joaosilva",
  "password": "password123",
  "active": true,
  "role": "OWNER_RESTAURANT"
}
```

### User (Customer)
```json
{
  "name": "Maria Santos",
  "email": "maria@example.com",
  "login": "mariasantos",
  "password": "password123",
  "active": true,
  "role": "CUSTOMER"
}
```

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
  "description": "Feijoada com todos os acompanhamentos tradicionais",
  "foodTypeId": "uuid-do-tipo",
  "price": 45.90,
  "imageURL": "https://example.com/feijoada.jpg"
}
```

### Opening Hours
```json
{
  "restaurantId": "uuid-do-restaurante",
  "weekday": "MONDAY",
  "opensAt": "08:00:00",
  "closesAt": "22:00:00",
  "isClosed": false
}
```

**Dias da semana válidos:**
- MONDAY
- TUESDAY
- WEDNESDAY
- THURSDAY
- FRIDAY
- SATURDAY
- SUNDAY

### Owner Restaurant Link
```json
{
  "ownerId": "uuid-do-owner",
  "restaurantId": "uuid-do-restaurante"
}
```

## Tipos de Usuário

A API suporta três tipos de usuários através do enum `Role`:
- **ADMIN**: Administrador do sistema
- **OWNER_RESTAURANT**: Proprietário de restaurante
- **CUSTOMER**: Cliente

## Notas Importantes

1. **IDs**: Todos os IDs são UUIDs gerados automaticamente
2. **Timestamps**: Scripts pre-request geram valores únicos automaticamente
3. **Validação**: Scripts de teste verificam status HTTP e estrutura das respostas
4. **Ordem**: Siga o fluxo recomendado para garantir que as dependências sejam criadas
5. **Horários**: Use formato `HH:mm:ss` para horários de abertura e fechamento
6. **CEP**: Deve estar no formato `00000-000`
7. **Estados**: Use siglas de 2 letras (ex: SP, RJ, MG)

## Troubleshooting

### Erro 404 - Not Found
- Verifique se os IDs nas variáveis estão preenchidos
- Execute a requisição de criação antes de buscar por ID

### Erro 400 - Bad Request
- Verifique o formato dos dados no payload
- Certifique-se de que todos os campos obrigatórios estão preenchidos
- Valide formatos de data/hora, CEP e UUIDs

### Variáveis Não Preenchidas
- Execute as requisições de listagem para popular as variáveis
- Scripts de test populam automaticamente após criação bem-sucedida

## Executando Testes Automatizados

Para executar toda a collection:

1. Clique no botão "Run" na collection
2. Selecione todas as requisições
3. Clique em "Run Restaurant Management API"
4. Observe os resultados dos testes automatizados

## Suporte

Para dúvidas ou problemas:
1. Verifique a documentação da API
2. Consulte os logs do console no Postman
3. Revise o código fonte dos controllers

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

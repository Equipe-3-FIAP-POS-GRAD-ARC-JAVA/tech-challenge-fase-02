# Projeto: Sistema de Gestão de Restaurantes - Tech Challenge Fase 02
  
## Equipe: Lista dos nomes e RMs dos alunos
  
| Nome | RM |
| --- | --- |
| Emerson Pereira da Silva | RM367268 |
| Levi Aparecido do Santos | RM369031 |
| Luiz Octavio Tassinari Saraiva | RM367408|
| Rhayana Lacerda Gomes | RM367798 |
| Vinicius Padovam Valentim | RM367199|

Repositório do projeto: https://github.com/Equipe-3-FIAP-POS-GRAD-ARC-JAVA/tech-challenge-fase-02

# 1. Introdução

# 1.1 Descrição do problema

Na nossa região, um grupo de restaurantes decidiu contratar estudantes para construir um sistema de gestão para seus estabelecimentos. Essa decisão foi motivada pelo alto custo de sistemas individuais, o que levou os restaurantes a se unirem para desenvolver um sistema único e compartilhado. Esse sistema permitirá que os clientes escolham restaurantes com base na comida oferecida, em vez de se basearem na qualidade do sistema de gestão. O objetivo é criar um sistema robusto que permita a todos os restaurantes gerenciar eficientemente suas operações, enquanto os clientes poderão consultar  informações,  deixar  avaliações  e  fazer  pedidos  online.  Devido  à limitação de  recursos financeiros,  foi  acordado  que  a  entrega  do  sistema  será realizada  em  fases,  garantindo  que  cada  etapa  seja  desenvolvida  de  forma cuidadosa e eficaz. 
A divisão em fases possibilitará uma implementação gradual e controlada, permitindo ajustes e melhorias contínuas conforme o sistema for sendo utilizado e avaliado pelos restaurantes e clientes. 

# 1.2 Objetivo desta fase

Essa fase  expande  o sistema ao  incluir a gestão  dos  tipos  de usuários, cadastro de restaurantes e cardápios, reforçando práticas de desenvolvimento e estruturação de código limpo. Além disso, são incluídos requisitos técnicos para garantir que o sistema mantenha alta qualidade e organização, com suporte para documentação, testes automatizados e infraestrutura Docker para uma execução integrada.


# 2. Arquitetura do Sistema

# 2.1 Visão Geral da Arquitetura
O sistema foi desenvolvido utilizando a arquitetura limpa (Clean Architecture), que promove a separação clara entre as diferentes camadas do aplicativo. A arquitetura é dividida em duas camadas principais: Core e Infra.

# 2.2 Camada Core
A camada Core é responsável pelas regras de negócio do sistema e é independente de qualquer framework ou tecnologia externa.

# 2.3 Camada Infra
A camada Infra é responsável por adaptar as regras de negócio para funcionar com frameworks e tecnologias externas, como o Spring Boot e o banco de dados.

# 2.4 Modelagem do Banco de Dados

O banco de dados foi modelado para suportar as funcionalidades do sistema, incluindo tabelas para usuários, restaurantes e cardápios.

![Modelagem do Banco de Dados](./ddl.png)

```sql
CREATE TABLE public.food_types (
	id uuid NOT NULL,
	created_at timestamptz(6) NOT NULL,
	deleted_at timestamptz(6) NULL,
	updated_at timestamptz(6) NULL,
	type_food varchar(255) NULL,
	CONSTRAINT food_types_pkey PRIMARY KEY (id)
);

CREATE TABLE public.restaurants (
	id uuid NOT NULL,
	created_at timestamptz(6) NOT NULL,
	deleted_at timestamptz(6) NULL,
	updated_at timestamptz(6) NULL,
	city varchar(255) NULL,
	country varchar(255) NULL,
	neighborhood varchar(255) NULL,
	"number" varchar(255) NULL,
	state varchar(255) NULL,
	street varchar(255) NULL,
	code varchar(255) NULL,
	"name" varchar(255) NULL,
	CONSTRAINT restaurants_pkey PRIMARY KEY (id)
);

CREATE TABLE public.users (
	id uuid NOT NULL,
	created_at timestamptz(6) NOT NULL,
	deleted_at timestamptz(6) NULL,
	updated_at timestamptz(6) NULL,
	is_active bool NOT NULL,
	email varchar(180) NOT NULL,
	login varchar(80) NOT NULL,
	"name" varchar(120) NOT NULL,
	"password" varchar(255) NOT NULL,
	"role" varchar(40) NOT NULL,
	CONSTRAINT uk6dotkott2kjsp8vw4d0m25fb7 UNIQUE (email),
	CONSTRAINT ukow0gan20590jrb00upg3va2fn UNIQUE (login),
	CONSTRAINT users_pkey PRIMARY KEY (id),
	CONSTRAINT users_role_check CHECK (((role)::text = ANY ((ARRAY['ADMIN'::character varying, 'OWNER_RESTAURANT'::character varying, 'CUSTOMER'::character varying])::text[])))
);

CREATE TABLE public.menus (
	id uuid NOT NULL,
	created_at timestamptz(6) NOT NULL,
	deleted_at timestamptz(6) NULL,
	updated_at timestamptz(6) NULL,
	restaurant_id uuid NULL,
	CONSTRAINT menus_pkey PRIMARY KEY (id),
	CONSTRAINT fk49thmnytvo47ttv4ggtwo9rrj FOREIGN KEY (restaurant_id) REFERENCES public.restaurants(id)
);

CREATE TABLE public.opening_hours (
	id uuid NOT NULL,
	created_at timestamptz(6) NOT NULL,
	deleted_at timestamptz(6) NULL,
	updated_at timestamptz(6) NULL,
	closes_at time(6) NULL,
	is_closed bool NOT NULL,
	opens_at time(6) NULL,
	weekday varchar(10) NOT NULL,
	restaurant_id uuid NOT NULL,
	CONSTRAINT opening_hours_pkey PRIMARY KEY (id),
	CONSTRAINT opening_hours_weekday_check CHECK (((weekday)::text = ANY ((ARRAY['MONDAY'::character varying, 'TUESDAY'::character varying, 'WEDNESDAY'::character varying, 'THURSDAY'::character varying, 'FRIDAY'::character varying, 'SATURDAY'::character varying, 'SUNDAY'::character varying])::text[]))),
	CONSTRAINT fk5dgcr0nmedj2g2ru4lqdld48n FOREIGN KEY (restaurant_id) REFERENCES public.restaurants(id)
);

CREATE TABLE public.owner_restaurant (
	id_relation uuid NOT NULL,
	id_client uuid NOT NULL,
	id_restaurant uuid NOT NULL,
	CONSTRAINT owner_restaurant_pkey PRIMARY KEY (id_relation),
	CONSTRAINT uk_owner_restaurant UNIQUE (id_client, id_restaurant),
	CONSTRAINT fkag9mbhqvrr4swkfxudglw33wo FOREIGN KEY (id_restaurant) REFERENCES public.restaurants(id),
	CONSTRAINT fkji28sngubhm32phnmv8augscd FOREIGN KEY (id_client) REFERENCES public.users(id)
);

CREATE TABLE public.foods (
	id uuid NOT NULL,
	created_at timestamptz(6) NOT NULL,
	deleted_at timestamptz(6) NULL,
	updated_at timestamptz(6) NULL,
	description varchar(255) NULL,
	image_url varchar(255) NULL,
	"name" varchar(255) NULL,
	price numeric(38, 2) NULL,
	food_type_id uuid NULL,
	menu_id uuid NULL,
	CONSTRAINT foods_pkey PRIMARY KEY (id),
	CONSTRAINT fkfn4qwtat9ihgf22e8ky8806o9 FOREIGN KEY (menu_id) REFERENCES public.menus(id),
	CONSTRAINT fkteg3f0l6cndtbn3nsbr1tmxcl FOREIGN KEY (food_type_id) REFERENCES public.food_types(id)
);

```

# 3. Descrição dos Endpoints da API

Os endpoints da API foram projetados para permitir a gestão completa dos usuários, restaurantes, cardápios e alimentos. A seguir estão os endpoints disponíveis:

## 3.1 Users (Usuários)

Operações CRUD para gestão de usuários (Proprietários, Clientes e Administradores).

| Endpoint | Método | Descrição | Controller |
|----------|--------|-----------|------------|
| `/api/users` | POST | Criar novo usuário (ADMIN, OWNER_RESTAURANT ou CUSTOMER) | UserController |
| `/api/users` | GET | Listar todos os usuários | UserController |
| `/api/users/{id}` | GET | Buscar usuário por ID | UserController |
| `/api/users/{id}` | PUT | Atualizar dados de um usuário | UserController |
| `/api/users/{id}` | DELETE | Remover um usuário | UserController |

## 3.2 Restaurants (Restaurantes)

Operações CRUD para gestão de restaurantes.

| Endpoint | Método | Descrição | Controller |
|----------|--------|-----------|------------|
| `/api/restaurants` | POST | Criar novo restaurante | RestaurantController |
| `/api/restaurants` | GET | Listar todos os restaurantes | RestaurantController |
| `/api/restaurants/{id}` | GET | Buscar restaurante por ID | RestaurantController |
| `/api/restaurants/{id}` | PUT | Atualizar dados de um restaurante | RestaurantController |
| `/api/restaurants/{id}` | DELETE | Remover um restaurante | RestaurantController |

## 3.3 Menus (Cardápios)

Operações CRUD para gestão de cardápios vinculados aos restaurantes.

| Endpoint | Método | Descrição | Controller |
|----------|--------|-----------|------------|
| `/api/menus` | POST | Criar novo cardápio para um restaurante | MenuController |
| `/api/menus` | GET | Listar todos os cardápios | MenuController |
| `/api/menus/{id}` | GET | Buscar cardápio por ID | MenuController |
| `/api/menus/restaurant/{restaurantId}` | GET | Listar todos os cardápios de um restaurante | MenuController |
| `/api/menus/{id}` | PUT | Atualizar um cardápio | MenuController |
| `/api/menus/{id}` | DELETE | Remover um cardápio | MenuController |

## 3.4 Food Types (Tipos de Alimento)

Operações CRUD para gestão de tipos/categorias de alimentos (ex: Entrada, Prato Principal, Sobremesa).

| Endpoint | Método | Descrição | Controller |
|----------|--------|-----------|------------|
| `/api/food-types` | POST | Criar novo tipo de alimento | FoodTypeController |
| `/api/food-types` | GET | Listar todos os tipos de alimento | FoodTypeController |
| `/api/food-types/{id}` | GET | Buscar tipo de alimento por ID | FoodTypeController |
| `/api/food-types/{id}` | PUT | Atualizar um tipo de alimento | FoodTypeController |
| `/api/food-types/{id}` | DELETE | Remover um tipo de alimento | FoodTypeController |

## 3.5 Foods (Alimentos/Pratos)

Operações CRUD para gestão de alimentos/pratos nos cardápios.

| Endpoint | Método | Descrição | Controller |
|----------|--------|-----------|------------|
| `/api/foods` | POST | Adicionar novo alimento ao cardápio | FoodController |
| `/api/foods` | GET | Listar todos os alimentos | FoodController |
| `/api/foods/{id}` | GET | Buscar alimento por ID | FoodController |
| `/api/foods/menu/{menuId}` | GET | Listar todos os alimentos de um cardápio | FoodController |
| `/api/foods/{id}` | PUT | Atualizar dados de um alimento | FoodController |
| `/api/foods/{id}` | DELETE | Remover um alimento | FoodController |

## 3.6 Opening Hours (Horários de Funcionamento)

Operações para gestão de horários de funcionamento dos restaurantes.

| Endpoint | Método | Descrição | Controller |
|----------|--------|-----------|------------|
| `/api/opening-hours` | POST | Criar horário de funcionamento para um restaurante | OpeningHoursController |
| `/api/opening-hours/restaurant/{restaurantId}` | GET | Listar horários de funcionamento de um restaurante | OpeningHoursController |
| `/api/opening-hours/{id}` | PUT | Atualizar horário de funcionamento | OpeningHoursController |

## 3.7 Owner Restaurant (Vínculo Proprietário-Restaurante)

Operações para gestão do relacionamento entre proprietários e restaurantes.

| Endpoint | Método | Descrição | Controller |
|----------|--------|-----------|------------|
| `/api/owners/restaurants` | POST | Vincular proprietário a um restaurante | OwnerRestaurantController |
| `/api/owners/{ownerId}/restaurants/{restaurantId}` | DELETE | Desvincular proprietário de um restaurante | OwnerRestaurantController |
| `/api/owners/{ownerId}/restaurants` | GET | Listar todos os restaurantes de um proprietário | OwnerRestaurantController |
| `/api/restaurants/{restaurantId}/owners` | GET | Listar todos os proprietários de um restaurante | OwnerRestaurantController |

## 3.8 Tratamento de Erros
A API implementa um sistema centralizado e robusto de tratamento de erros, retornando mensagens claras e códigos HTTP apropriados para facilitar a identificação e resolução de problemas durante as operações.

### 3.8.1 GlobalExceptionHandler 
Um manipulador global de exceções captura erros não tratados, garantindo que todas as exceções sejam processadas de maneira uniforme. Isso inclui a captura de exceções genéricas e específicas, permitindo uma resposta consistente para o cliente.

### 3.8.2 ControllerAdvice 
Um manipulador de exceções específico para controladores utiliza a anotação `@ControllerAdvice` para interceptar exceções lançadas pelos controladores REST. Isso permite mapear exceções específicas para respostas HTTP adequadas, como 400 Bad Request, 404 Not Found, e 500 Internal Server Error.

### 3.8.3 Custom Error Responses
A API retorna respostas de erro personalizadas que incluem detalhes úteis, como mensagens de erro descritivas, códigos de erro e timestamps. Isso ajuda os desenvolvedores a entenderem rapidamente a natureza do problema e a tomar as medidas corretivas necessárias.
```json
{
  "timestamp": "2024-10-01T12:00:00Z",
  "status": 404,
  "error": "Not Found",
  "message": "Restaurant with ID '1234' not found",
  "path": "/api/restaurants/1234"
}
```

### 3.8.4 IllegalArgumentException (Validação de Negócio)
Lançada em casos de violação de regras de negócio:

|Situação	|Mensagem	|Local|
|Email duplicado	|"Email already exists"	|CreateUserImpl|
|Login duplicado	|"Login already exists"	|CreateUserImpl|
|Proprietário já vinculado	|"Owner already linked to restaurant"	|OwnerRestaurantGatewayAdapter|
|Usuário não é proprietário	|"User is not OWNER_RESTAURANT"	|OwnerRestaurantGatewayAdapter|

### 3.8.5 Códigos de Status HTTP Retornados
Códigos de status HTTP retornados pela API em casos de erro:

|Status	|Situação	|Exemplo|
|404	|Recurso não encontrado	|Usuário, Restaurante, Menu, etc. não existe|
|400	|Dados inválidos	|Email/Login duplicado, validação de regra de negócio|
|500	|Erro interno	|Exceções não tratadas (padrão)|

# 4. Configuração do Projeto

## 4.1 Execução do Projeto

### 4.1.1 Usando Docker Compose
1. **Clone o repositório e navegue até a pasta do projeto**

2. **Copie o arquivo de exemplo de variáveis de ambiente (opcional)**
   ```bash
   cp .env.example .env
   ```

3. **Inicie os serviços**
   ```bash
   docker compose up -d
   ```

4. **Finalize os serviços**
   ```bash
   docker compose down
   ```

   Isso irá:
   - Construir a imagem da aplicação Java
   - Iniciar o banco de dados PostgreSQL
   - Iniciar a aplicação Spring Boot
   - Aguardar o banco ficar saudável antes de iniciar a aplicação
  
### 4.1.2 Usando o maven

```bash
## 1. Iniciar apenas PostgreSQL
docker compose up -d postgres
  
## 2. Executar Spring Boot localmente (com hot reload)
./mvnw spring-boot:run -Dspring-boot.devtools.restart.enabled=true
  
## 3. Para debug com breakpoints
./mvnw spring-boot:run \
  -Dspring-boot.devtools.restart.enabled=true \
  -Dspring-boot.run.jvmArguments='-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005'
```

# 5. Qualidade do Código

## 5.1 Arquitetura Limpa (Clean Architecture)

O projeto implementa os princípios da Clean Architecture, garantindo separação clara de responsabilidades e independência de frameworks.

### 5.1.1 Organização em Camadas

#### Camada Core (Regras de Negócio)
Localizada no pacote `br.com.fiap.challenge.restaurant.core`, contém toda a lógica de negócio e é completamente independente de frameworks:

- **Domain Entities** (`core/domain/entity/`): Entidades de domínio puras (POJOs) sem anotações de framework
- **Value Objects** (`core/domain/valueobject/`): Objetos imutáveis como `ZipCode` e `State` com validação de negócio
- **Use Cases** (`core/usecase/`): Implementações dos casos de uso organizados por domínio (user, restaurant, menu, food, etc.)
- **Gateway Interfaces** (`core/gateway/`): Interfaces que definem portas (ports) para comunicação com a camada externa
- **DTOs** (`core/dto/`): Objetos de transferência de dados usando Java Records (imutáveis)

#### Camada Infrastructure (Adaptadores)
Localizada no pacote `br.com.fiap.challenge.restaurant.infra`, contém todas as adaptações para frameworks e tecnologias externas:

- **JPA Entities** (`infra/entity/`): Entidades de persistência com anotações JPA, separadas das entidades de domínio
- **Gateway Adapters** (`infra/gateway/`): Implementações dos gateways que adaptam para o Spring Data JPA
- **Controllers** (`infra/web/controller/`): Controladores REST que lidam apenas com requisições HTTP
- **API Interfaces** (`infra/web/api/`): Contratos de API com documentação OpenAPI/Swagger
- **Repositories** (`infra/repository/`): Repositórios Spring Data JPA
- **Configuration** (`infra/config/`): Classes de configuração Spring

### 5.1.2 Regra de Dependência

O projeto segue estritamente a regra de dependência da Clean Architecture:
- **Core não depende de Infra**: A camada Core define interfaces (gateways) e a camada Infra as implementa
- **Fluxo de dependência**: `Infra → Core` (nunca o contrário)
- **Inversão de Dependência**: Use cases dependem de abstrações (interfaces Gateway), não de implementações concretas

### 5.1.3 Separação de Modelos

O projeto mantém dois modelos de entidades separados:

**Domain Entities** (Core):
```java
// Exemplo: br.com.fiap.challenge.restaurant.core.domain.entity.Restaurant
// POJOs puros sem anotações de framework
public class Restaurant extends BaseEntity {
    private String name;
    private Address address;
    // Lógica de negócio pura
}
```

**JPA Entities** (Infrastructure):
```java
// Exemplo: br.com.fiap.challenge.restaurant.infra.entity.Restaurant
@Entity
@Table(name = "restaurants")
public class Restaurant extends BaseEntity {
    @Column(name = "name")
    private String name;
    
    @Embedded
    private Address address;
    // Anotações de persistência
}
```

Os **Gateway Adapters** fazem a conversão entre esses modelos.

## 5.2 Princípios SOLID

### 5.2.1 Single Responsibility Principle (SRP)

Cada classe tem uma única responsabilidade bem definida:

- **Use Cases**: Cada use case realiza uma única operação de negócio
  - Exemplo: `CreateUserImpl` apenas cria usuários
  - Exemplo: `UpdateRestaurantImpl` apenas atualiza restaurantes

- **Controllers**: Apenas lidam com requisições HTTP, delegando lógica de negócio para use cases
  - Exemplo: `UserController`

- **Gateway Adapters**: Apenas convertem entre modelos de domínio e persistência
  - Exemplo: `RestaurantGatewayAdapter`

### 5.2.2 Open/Closed Principle (OCP)

O sistema é aberto para extensão, mas fechado para modificação:

- **Interfaces de Use Case**: Novos comportamentos podem ser adicionados implementando novas interfaces sem modificar código existente
- **Gateway Interfaces**: Diferentes implementações podem ser criadas (ex: trocar banco de dados) sem alterar a camada Core
- **API Interfaces**: Novos endpoints podem ser adicionados através de novas interfaces

### 5.2.3 Liskov Substitution Principle (LSP)

Implementações podem ser substituídas por suas interfaces sem quebrar o sistema:

- **Gateway Implementations**: Qualquer implementação de `RestaurantGateway` pode ser usada pelos use cases
- **Use Case Implementations**: Qualquer implementação de `CreateRestaurant` pode ser injetada nos controllers

### 5.2.4 Interface Segregation Principle (ISP)

Interfaces são específicas e focadas:

- **Use Cases de Método Único**: Cada interface de use case possui apenas um método `execute()`
  - Exemplo: `CreateFood`
  - Exemplo: `UpdateMenu`

- **Gateway Interfaces Especializadas**: Cada gateway tem métodos específicos para seu domínio
  - `FoodGateway`: Apenas operações de Food
  - `MenuGateway`: Apenas operações de Menu

- **Base Use Case Interfaces**: Interfaces base genéricas para diferentes assinaturas
  - `UseCase<P, R>`: Para casos com input e output
  - `UnitUseCase<I>`: Para casos com input sem output
  - `NullaryUseCase<O>`: Para casos sem input com output

### 5.2.5 Dependency Inversion Principle (DIP)

Módulos de alto nível não dependem de módulos de baixo nível - ambos dependem de abstrações:

- **Use Cases dependem de Gateways**: Use cases (alto nível) dependem de interfaces Gateway (abstrações), não de repositórios (baixo nível)
  
  Exemplo em `LinkOwnerRestaurantImpl`:
  ```java
  public class LinkOwnerRestaurantImpl implements LinkOwnerRestaurant {
      private final OwnerRestaurantGateway gateway; // Dependência de abstração
      
      public LinkOwnerRestaurantImpl(OwnerRestaurantGateway gateway) {
          this.gateway = gateway;
      }
  }
  ```

- **Controllers dependem de Use Cases**: Controllers (alto nível) dependem de interfaces de use cases (abstrações)
  
- **Configuração via Spring**: As implementações concretas são configuradas através de beans Spring em classes `@Configuration`
  
  Exemplo em `UseCaseFoodConfig`:
  ```java
  @Configuration
  public class UseCaseFoodConfig {
      @Bean
      public CreatedFood createdFood(FoodGateway foodGateway) {
          return new CreatedFoodImpl(foodGateway);
      }
  }
  ```

## 5.3 Práticas de Desenvolvimento Spring Boot

### 5.3.1 Injeção de Dependências

- **Constructor Injection**: Todo o projeto utiliza injeção por construtor (não há uso de `@Autowired` em campos)
- **Imutabilidade**: Dependências são declaradas como `final`
- **Configuração Explícita**: Beans são criados através de métodos `@Bean` em classes `@Configuration`

### 5.3.2 Anotações e Estereótipos

- `@RestController`: Controllers REST
- `@Service`: Não utilizado (use cases são configurados via `@Bean`)
- `@Repository`: Repositórios Spring Data JPA
- `@Component`: Gateway adapters
- `@Configuration`: Classes de configuração de beans

### 5.3.3 Separação de Responsabilidades

- **API Interfaces**: Contratos de API com documentação OpenAPI separados da implementação
- **DTOs como Records**: Uso de Java Records para DTOs imutáveis
- **Exception Handling**: Tratamento centralizado de exceções com `@ControllerAdvice`

# 6. Estratégia de Testes e Cobertura


# 7. Collections para Teste

# 8. Documentação Técnica

# 9. Repositório do Código

https://github.com/Equipe-3-FIAP-POS-GRAD-ARC-JAVA/tech-challenge-fase-02

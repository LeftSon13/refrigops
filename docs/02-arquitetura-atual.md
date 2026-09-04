> Nota editorial de saneamento histórico: referências a uma operação específica foram abstraídas. Esta nota não representa uma decisão tomada na data original do documento.

> Exemplos JSON substituídos editorialmente por dados fictícios DEMO, sem correspondência com uma instalação real.

# Arquitetura atual

## 1. Escopo e data do snapshot técnico

**[CONFIRMADO — REPOSITÓRIO]** Auditada no checkout local em 2026-08-29:

```text
diretório raiz do repositório `refrigops`
```

Branch e commit:

```text
main
938480a Merge pull request #5 from LeftSon13/feature/equipment-validation
```

A árvore de trabalho estava limpa e `main` acompanhava `origin/main`.

Esse bloco é o snapshot da aplicação que serve de base à documentação, não o estado volátil da branch documental. Em 2026-09-03, o código foi confrontado novamente na branch `docs/documentacao-operacional`; ela permanecia baseada em `938480a` e não continha alterações em `src/`, `pom.xml`, `compose.yaml` ou configurações da aplicação. O estado mais recente da branch e das validações fica em [`11-contexto-atual.md`](11-contexto-atual.md).

## 2. Stack confirmada

**[CONFIRMADO — REPOSITÓRIO]**

- Java 21;
- Spring Boot 4.0.7;
- Maven Wrapper;
- Spring Web MVC;
- Spring Data JPA e Hibernate;
- Jakarta Bean Validation;
- Spring Boot Flyway;
- PostgreSQL 17;
- Docker Compose para o banco de desenvolvimento;
- Testcontainers 2.0.5, conforme histórico de execução;
- JUnit 5 e MockMvc;
- Git e GitHub.

## 3. Organização do código

**[CONFIRMADO — REPOSITÓRIO]**

```text
src/main/java/dev/joaov/refrigops/
├── RefrigopsApplication.java
├── controller/
│   ├── EquipmentController.java
│   └── dto/CreateEquipmentRequest.java
├── domain/equipment/
│   ├── Equipment.java
│   ├── EquipmentRepository.java
│   ├── EquipmentStatus.java
│   └── EquipmentType.java
└── service/EquipmentService.java

src/main/resources/
├── application.properties
└── db/migration/V1__create_equipment_table.sql

src/test/java/dev/joaov/refrigops/
├── RefrigopsApplicationTests.java
├── TestcontainersConfiguration.java
├── controller/EquipmentControllerTest.java
└── domain/equipment/EquipmentRepositoryTest.java
```

## 4. Fluxo de criação

**[CONFIRMADO — REPOSITÓRIO]**

```text
POST /api/equipment
        ↓
CreateEquipmentRequest
        ↓
@Valid / Bean Validation
        ↓
EquipmentController
        ↓
EquipmentService.create(...)
        ↓
new Equipment
        ↓
status = STOPPED
active = true
        ↓
EquipmentRepository.save(...)
        ↓
PostgreSQL
```

Entrada JSON confirmada:

```json
{
  "code": "DEMO-COMP-01",
  "name": "Compressor Fictício 01",
  "type": "COMPRESSOR",
  "location": "Área Demonstrativa A"
}
```

## 5. Fluxo de listagem

**[CONFIRMADO — REPOSITÓRIO]**

```text
GET /api/equipment
        ↓
EquipmentController.findAll()
        ↓
EquipmentService.findAll()
        ↓
EquipmentRepository.findAll()
        ↓
List<Equipment>
```

## 6. Camadas e responsabilidades atuais

### Controller

Recebe HTTP, desserializa o DTO, dispara a validação e chama o Service.

### DTO de entrada

`CreateEquipmentRequest` representa os dados aceitos na criação e contém validações de presença.

### Service

Constrói a entidade, aplica padrões iniciais e delega a persistência.

### Domain/Entity

`Equipment` é simultaneamente a representação persistida e o objeto retornado pelo Service/Controller.

### Repository

`EquipmentRepository` estende `JpaRepository<Equipment, Long>`.

### Database

O Flyway cria a tabela e o Hibernate valida sua compatibilidade.

## 7. Modelo persistido

**[CONFIRMADO — REPOSITÓRIO]**

Tabela `equipment`:

| Coluna | Tipo | Restrições |
|---|---|---|
| `id` | `BIGSERIAL` | chave primária |
| `code` | `VARCHAR(50)` | obrigatório, único |
| `name` | `VARCHAR(100)` | obrigatório |
| `type` | `VARCHAR(30)` | obrigatório |
| `status` | `VARCHAR(30)` | obrigatório |
| `active` | `BOOLEAN` | obrigatório |
| `location` | `VARCHAR(100)` | obrigatório |

Enums são persistidos por nome usando `EnumType.STRING`.

## 8. Configuração de desenvolvimento

**[CONFIRMADO — REPOSITÓRIO]**

O `compose.yaml` define PostgreSQL 17:

```text
host: localhost
porta: 5433
banco: refrigops
usuário: refrigops
```

As mesmas credenciais de desenvolvimento aparecem em `application.properties` e no Compose. Elas são locais e previsíveis; não devem ser reutilizadas em ambientes reais.

JPA:

```properties
spring.jpa.hibernate.ddl-auto=validate
spring.jpa.open-in-view=false
```

## 9. Testes e isolamento

**[CONFIRMADO — REPOSITÓRIO]**

As três classes que carregam o contexto Spring importam `TestcontainersConfiguration`. Ela cria `PostgreSQLContainer("postgres:17")` com banco `refrigops_test` e usa `@ServiceConnection` para substituir a conexão normal.

Isso evita que os testes usem o banco de desenvolvimento em `localhost:5433`.

## 10. Dívidas e limites confirmados

### Entidade exposta pela API

O Controller retorna:

```java
Equipment
List<Equipment>
```

Isso acopla contrato HTTP e persistência. A próxima Issue recomendada é criar um DTO de resposta.

### Status HTTP de criação

O POST retorna atualmente HTTP 200. A adoção de 201 Created é uma decisão separada e ainda pendente.

### Erros

Não existe tratamento global de erros documentado. As respostas de Bean Validation usam o comportamento padrão do Spring.

### Duplicidade

O banco exige `code` único, mas não existe regra explícita de aplicação nem resposta HTTP padronizada para conflito.

### Operações disponíveis

Só existem listagem e criação. Não há busca por ID, atualização, desativação ou histórico.

### Contrato e documentação de API

Não há OpenAPI/Swagger nem contrato executável. O comportamento auditado está descrito em [`09-contrato-api-atual.md`](09-contrato-api-atual.md), incluindo as dívidas que ainda impedem tratá-lo como contrato público definitivo.

### Segurança de aplicação

Não há autenticação, autorização ou perfis confirmados. Isso é aceitável apenas para estágio local de aprendizado, não para implantação operacional.

## 11. Diagrama de contexto técnico atual

```text
cliente HTTP
    │
    ▼
EquipmentController
    │
    ▼
EquipmentService
    │
    ▼
EquipmentRepository
    │
    ▼
PostgreSQL 17

Desenvolvimento: Docker Compose / localhost:5433
Testes: Testcontainers / porta temporária
```

## 12. Direção arquitetural

**[DECISÃO]** Manter arquitetura simples e modular dentro de uma única aplicação enquanto o domínio é descoberto. Não adotar microsserviços sem pressão concreta de escala, autonomia ou implantação.

Próximas melhorias devem ser justificadas por comportamento e responsabilidade, não por quantidade de padrões.

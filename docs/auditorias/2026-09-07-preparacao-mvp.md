# RefrigOps — Auditoria para preparação da MVP

**Checkpoint:** observado antes do rewrite; equivalente saneado: `76b7ca4316419dd66dc902b5b3271fce57fd9c19`
**Validação local:** noite de 06/09/2026, horário de Brasília.
**Escopo:** repositório, histórico Git disponível, documentação, configuração, build, testes, execução isolada, banco temporário e consulta pontual a avisos oficiais de segurança.

As classificações abaixo distinguem implementação comprovada, documentação, inconsistências e pontos não determinados. Informações operacionais foram conferidas como registros documentais; não houve inspeção da instalação nem validação técnica de instrumentos, procedimentos ou grandezas físicas.

## 1. Resumo executivo

O RefrigOps está no estágio de **fundação técnica funcional, com backend parcial e descoberta de produto documentada**.

O que está comprovado:

- cadastro e listagem de equipamentos;
- persistência em PostgreSQL;
- criação do schema por Flyway;
- validação de presença dos campos da requisição;
- DTO de resposta separado da entidade;
- testes integrados com banco descartável;
- geração do JAR executável;
- inicialização da aplicação e atendimento HTTP em ambiente isolado.

Os principais problemas encontrados são:

1. **A documentação está parcialmente atrasada:** ainda recomenda implementar `EquipmentResponse`, já integrado à `main`.
2. **Entradas inválidas chegam ao banco:** código duplicado e textos maiores que as colunas produzem HTTP 500.
3. **O cadastro não representa observação operacional:** `STOPPED` é um valor inicial definido pelo código, sem horário ou evidência de medição.
4. **Não existem os módulos operacionais:** ronda, leitura, ocorrência, operador, turno e histórico.
5. **Não há segurança de acesso nem preparação suficiente para piloto operacional.**

A arquitetura existente é pequena e aproveitável. Não há evidência que justifique reescrevê-la ou adotar microsserviços.

## 2. Estado Git auditado

| Item | Resultado |
|---|---|
| Repositório | diretório raiz do checkout `refrigops` |
| Branch atual | `main` |
| HEAD | observado antes do rewrite; equivalente saneado: `76b7ca4316419dd66dc902b5b3271fce57fd9c19` |
| Último commit | Merge da PR #9, `feature/equipment-response` |
| Working tree e staging | Limpos no início e no encerramento |
| Arquivos não rastreados, fora dos ignorados | Nenhum |
| Branch local adicional | `feature/equipment-response`, em commit observado antes do rewrite (equivalente saneado: `f43cddb`) |
| Remoto | [LeftSon13/refrigops](https://github.com/LeftSon13/refrigops) |
| Verificação remota | `refs/heads/main` aponta para o mesmo HEAD |
| Tags | Nenhuma encontrada localmente ou na consulta remota |
| Histórico local acessível | 22 commits |

A referência local `origin/feature/equipment-response` ainda existe, mas a consulta ao remoto retornou somente `main`. Portanto, essa referência remota local está desatualizada; não foi removida nesta auditoria.

Marcos relevantes:

```text
checkpoint pré-rewrite (equivalente saneado: 5b85711) → validação do cadastro
checkpoint pré-rewrite (equivalente saneado: e45c899) → fundação documental
checkpoint pré-rewrite (equivalente saneado: 3808fa9) → merge da revisão documental
checkpoint pré-rewrite (equivalente saneado: 326455f) → implementação de EquipmentResponse
checkpoint pré-rewrite (equivalente saneado: 4430a79) → cobertura do contrato JSON
checkpoint pré-rewrite (equivalente saneado: f43cddb) → atualização documental da resposta pública
checkpoint pré-rewrite (equivalente saneado: 76b7ca4) → merge da entrega de EquipmentResponse
```

**CONFIRMADO:** não havia trabalho local versionado pendente em risco.

Não foram criados branches, commits, Issues ou PRs. Não houve push, merge, reset ou alteração de referências.

Foram gerados somente artefatos locais ignorados pelo Git, dentro de `target/`: build, relatórios, logs e uma verificação temporária de execução. Esses arquivos podem desaparecer em um futuro `clean`.

## 3. Stack real

| Componente | Versão confirmada |
|---|---|
| Java declarado | 21 |
| Java usado nesta validação | Eclipse Temurin 21.0.12 |
| Java encontrado inicialmente no PATH | Oracle Java 25.0.2 |
| Spring Boot | 4.0.7 |
| Spring Framework | 7.0.8 |
| Spring Data JPA | 4.0.6 |
| Hibernate ORM | 7.2.19.Final |
| Hibernate Validator | 9.0.1.Final |
| Flyway | 11.14.1 |
| PostgreSQL | Imagem `postgres:17`; execução temporária em 17.10 |
| Driver PostgreSQL | 42.7.11 |
| Tomcat embarcado | 11.0.22 |
| Maven | 3.9.16 |
| Maven Wrapper | 3.3.4 |
| Testcontainers | 2.0.5 |
| JUnit Jupiter | 6.0.3 |

O projeto usa Spring MVC, Jakarta Validation, JPA, MockMvc e injeção por construtor.

**INCONSISTENTE:** a documentação cita JUnit 5, enquanto as dependências efetivamente resolvidas nesta execução contêm JUnit Jupiter 6.0.3.

As versões foram conferidas no [pom.xml](../../pom.xml), na configuração do Wrapper, no JAR gerado e nos relatórios da execução.

Não foram encontrados frontend, Dockerfile da aplicação, pipeline versionado de CI/CD, autenticação, OpenAPI ou Actuator.

## 4. Arquitetura atual

A aplicação é um único backend Spring Boot, organizado em camadas.

```text
refrigops/
├── pom.xml
├── mvnw / mvnw.cmd
├── .mvn/wrapper/
├── compose.yaml                 → PostgreSQL de desenvolvimento
├── src/main/java/dev/joaov/refrigops/
│   ├── RefrigopsApplication
│   ├── controller/
│   │   ├── EquipmentController
│   │   └── dto/
│   │       ├── CreateEquipmentRequest
│   │       └── EquipmentResponse
│   ├── service/
│   │   └── EquipmentService
│   └── domain/equipment/
│       ├── Equipment
│       ├── EquipmentRepository
│       ├── EquipmentType
│       └── EquipmentStatus
├── src/main/resources/
│   ├── application.properties
│   └── db/migration/V1__create_equipment_table.sql
├── src/test/java/
│   ├── TestcontainersConfiguration
│   ├── RefrigopsApplicationTests
│   ├── EquipmentControllerTest
│   └── EquipmentRepositoryTest
└── docs/
    ├── produto, operação, arquitetura, domínio e roadmap
    ├── adr/
    ├── historias-usuario/
    └── pesquisa/
```

Responsabilidades:

| Parte | Responsabilidade atual |
|---|---|
| Controller | Receber HTTP, validar entrada, chamar Service e converter a resposta |
| DTO de entrada | Definir os quatro campos aceitos no cadastro |
| Service | Construir o equipamento e aplicar os valores iniciais |
| Entity | Representar os dados persistidos |
| Repository | Executar operações JPA |
| DTO de saída | Definir os sete campos públicos da API |
| Flyway | Criar o schema |
| Hibernate | Mapear entidades e validar compatibilidade de schema |

**CONFIRMADO:** o Controller não acessa o Repository diretamente. O Service não depende de DTO HTTP.

O mapeamento `EquipmentResponse.from(Equipment)` depende explicitamente da entidade. Para este tamanho de aplicação, é uma solução simples e coerente: não há necessidade comprovada de biblioteca de mapeamento ou camada adicional.

Evidências: [Controller](../../src/main/java/dev/joaov/refrigops/controller/EquipmentController.java), [Service](../../src/main/java/dev/joaov/refrigops/service/EquipmentService.java) e [DTO de resposta](../../src/main/java/dev/joaov/refrigops/controller/dto/EquipmentResponse.java).

## 5. Inventário funcional

| Capacidade | Situação | Limite |
|---|---|---|
| Cadastrar equipamento | **CONFIRMADO** | POST com quatro campos |
| Listar equipamentos | **CONFIRMADO** | Retorna todos, sem filtros ou ordenação definida |
| Resposta pública por DTO | **CONFIRMADO** | Já integrada à `main` |
| Validação de entrada | **PARCIALMENTE CONFIRMADO** | Presença validada; tamanhos não |
| Unicidade do código | **PARCIALMENTE CONFIRMADO** | Banco impede repetição exata; API retorna 500 |
| Gestão completa de equipamentos | **PARCIALMENTE CONFIRMADO** | Não há consulta individual, edição ou desativação |
| Compressores, recipientes e condensadores | **PARCIALMENTE CONFIRMADO** | São valores de enum, sem módulos especializados |
| Estados operacionais | **PARCIALMENTE CONFIRMADO** | Enum e campo existem; transições e histórico não |
| Rondas e roteiros | **DOCUMENTADO, MAS NÃO IMPLEMENTADO** | Sem classes, endpoints ou tabelas |
| Leituras e unidades | **DOCUMENTADO, MAS NÃO IMPLEMENTADO** | Apenas propostas de domínio |
| Ocorrências e anomalias | **DOCUMENTADO, MAS NÃO IMPLEMENTADO** | Sem persistência ou fluxo |
| Operadores, turnos e passagem de turno | **DOCUMENTADO, MAS NÃO IMPLEMENTADO** | Sem identidade ou autoria |
| Histórico e correções auditáveis | **DOCUMENTADO, MAS NÃO IMPLEMENTADO** | Não há timestamps de negócio |
| Manutenção | **DOCUMENTADO, MAS NÃO IMPLEMENTADO** | `MAINTENANCE` não constitui módulo de manutenção |
| Interface mobile/desktop | **DOCUMENTADO, MAS NÃO IMPLEMENTADO** | Não existe frontend |
| Integração com máquinas | **DOCUMENTADO, MAS NÃO IMPLEMENTADO** | Pesquisa e visão futura |

**IMPLEMENTADO, MAS NÃO DOCUMENTADO COMO COMPORTAMENTO ATUAL:** as respostas 500 para duplicidade e excesso de tamanho, a preservação de espaços no código e o tratamento observado de campos extras na requisição.

Não identifiquei classes inteiras abandonadas ou camadas artificiais. Existem valores de enum preparados para capacidades futuras, o que não comprova implementação dessas capacidades.

## 6. Modelo de dados

A migration define **uma única tabela de negócio**, `equipment`.

| Coluna | Tipo | Restrições |
|---|---|---|
| `id` | `BIGSERIAL` | Chave primária |
| `code` | `VARCHAR(50)` | Obrigatório e único |
| `name` | `VARCHAR(100)` | Obrigatório |
| `type` | `VARCHAR(30)` | Obrigatório |
| `status` | `VARCHAR(30)` | Obrigatório |
| `active` | `BOOLEAN` | Obrigatório |
| `location` | `VARCHAR(100)` | Obrigatório |

No banco temporário foram confirmados:

- `equipment`;
- `flyway_schema_history`;
- sequência para geração do ID;
- índice único da chave primária;
- índice único de `code`;
- migration V1 aplicada com sucesso.

```text
equipment
└── nenhum relacionamento persistido

flyway_schema_history
└── histórico técnico das migrations
```

Não existem FKs, relações entre entidades, timestamps de cadastro, autoria, histórico de estados ou auditoria de alterações de negócio.

`flyway_schema_history` registra evolução do schema; não registra ações de operadores.

**Discrepâncias e limites:**

- A migration estabelece tamanhos que não aparecem como validações no DTO.
- A entidade não explicita `length`, `nullable` e `unique` com `@Column`.
- Os enums são persistidos como texto, mas o SQL não restringe os valores permitidos com `CHECK`.
- O banco exige os campos, mas não impede strings em branco.
- Os padrões `STOPPED` e `active=true` estão no Service, não como defaults SQL.
- `location` é texto livre, sem tabela de salas ou áreas.

O Hibernate aceitou o schema criado pela V1. Isso não significa que todas as regras de integridade estejam espelhadas nas três camadas.

**NÃO DETERMINADO:** estado, conteúdo, possíveis alterações manuais e histórico de migrations do banco persistente de desenvolvimento. Ele não foi iniciado nem consultado.

Evidências: [migration V1](../../src/main/resources/db/migration/V1__create_equipment_table.sql), [Equipment](../../src/main/java/dev/joaov/refrigops/domain/equipment/Equipment.java) e `target/auditoria-mvp-runtime.log` (artefato temporário da auditoria, não versionado).

## 7. Fluxos atuais

### Criação

```text
POST /api/equipment
  ↓
CreateEquipmentRequest
  ↓
Desserialização + @Valid
  ↓
EquipmentController.create()
  ↓
EquipmentService.create()
  ├── copia code, name, type e location
  ├── define status = STOPPED
  └── define active = true
  ↓
EquipmentRepository.save()
  ↓
PostgreSQL
  ↓
EquipmentResponse.from()
  ↓
HTTP 200 + JSON
```

A única regra de inicialização está corretamente no Service.

**Atenção de domínio:** `STOPPED` representa uma escolha do cadastro atual. Não comprova que um equipamento físico esteja parado.

### Listagem

```text
GET /api/equipment
  ↓
EquipmentController.findAll()
  ↓
EquipmentService.findAll()
  ↓
EquipmentRepository.findAll()
  ↓
PostgreSQL
  ↓
List<Equipment>
  ↓
Conversão de cada item para EquipmentResponse
  ↓
HTTP 200 + array JSON
```

Não existe paginação, filtro por ativo, tipo ou localização, nem ordenação contratada.

### Erros

- Campos obrigatórios em branco ou tipo inválido são rejeitados pela camada HTTP.
- Duplicidade e excesso de tamanho chegam ao banco.
- Não há tratamento próprio dessas exceções.
- O tratamento padrão resulta em HTTP 500 nos casos reproduzidos.

Evidência: [DTO de entrada](../../src/main/java/dev/joaov/refrigops/controller/dto/CreateEquipmentRequest.java).

## 8. Testes, build e execução

### Suíte existente

| Classe | Quantidade | Resultado atual |
|---|---:|---|
| `EquipmentControllerTest` | 4 | Passaram |
| `EquipmentRepositoryTest` | 1 | Passou |
| `RefrigopsApplicationTests` | 1 | Passou |
| **Total** | **6** | **0 falhas, 0 erros, 0 ignorados** |

São testes integrados: todas essas classes carregam Spring e usam PostgreSQL temporário. Não há testes unitários isolados de Service.

Comando principal executado:

```powershell
$env:JAVA_HOME = '<CAMINHO_DO_JDK_21>'
$env:Path = "$env:JAVA_HOME\bin;$env:Path"

.\mvnw.cmd verify --batch-mode --no-transfer-progress
```

Resultado:

```text
Tests run: 6
Failures: 0
Errors: 0
Skipped: 0
BUILD SUCCESS
```

O JAR foi gerado em `target/refrigops-0.0.1-SNAPSHOT.jar`.

O Maven reutilizou classes compiladas existentes. Para complementar essa evidência, todos os fontes da aplicação foram recompilados com `javac --release 21` em um diretório temporário separado. Essa recompilação também passou.

### Execução HTTP real

Uma instância temporária iniciou em `127.0.0.1`, porta dinâmica, usando a configuração existente de Testcontainers e os fontes recompilados. Foram usados somente dados fictícios.

| Verificação | Resultado observado |
|---|---|
| GET em banco vazio | 200 e `[]` |
| Cadastro válido | 200, ID gerado e sete campos públicos |
| Código exatamente duplicado | **500** |
| Código com 51 caracteres | **500** |
| Nome com 101 caracteres | **500** |
| Código somente com espaços | 400 |
| Tipo inexistente, `PUMP` | 400 |
| Código com espaços nas extremidades | 200; espaços preservados |
| Envio adicional de `status` e `active` | Ignorados; Service preservou seus padrões |
| GET após cadastros | 200 e registros persistidos |
| GET `/api/equipment/1` | 404 |
| Preflight de origem externa | 200, sem `Access-Control-Allow-Origin` |

Erros relevantes:

```text
duplicate key value violates unique constraint "equipment_code_key"
value too long for type character varying(50)
value too long for type character varying(100)
```

A aplicação encerrou normalmente. Os containers temporários foram removidos ao fim da execução.

### Cobertura e fragilidades

Os testes existentes cobrem presença de campos, tipo nulo, criação válida, valores iniciais, conteúdo JSON, persistência básica e carregamento do contexto.

Lacunas:

- duplicidade e limites de tamanho;
- validação independente de cada campo textual;
- corpo malformado e tipo desconhecido na suíte permanente;
- normalização de código;
- contrato do corpo de erro;
- ausência de campos internos na resposta;
- regras de estados, quando forem implementadas.

Fragilidades específicas:

1. **GET:** as asserções usam `hasItem` separadamente por campo. Valores corretos em objetos diferentes poderiam satisfazer o teste.
2. **Repository:** salvar e buscar dentro do mesmo contexto de persistência não prova integralmente uma recarga independente do banco.
3. **Validação textual:** um único teste envia todos os textos vazios; a remoção da validação de apenas um campo poderia passar despercebida.
4. **Isolamento entre métodos:** os testes do Controller acumulam registros no banco do contexto. Hoje usam códigos diferentes e não houve falha por ordem, mas há estado compartilhado.

`contextLoads` é relevante: verifica a montagem da aplicação e sua infraestrutura. Não foi desativado.

Não foi calculado percentual de cobertura. As verificações HTTP adicionais são evidências desta auditoria, não novos testes adicionados à suíte.

### Problemas de ambiente encontrados

- A consulta inicial ao GitHub falhou no ambiente restrito; funcionou fora dele.
- O Wrapper inicialmente apresentou `Não é possível indexar em uma matriz nula`; funcionou com Java 21 fora do ambiente restrito.
- O Docker inicialmente estava indisponível; após sua inicialização, a suíte passou.

Esses incidentes não foram classificados como defeitos funcionais do RefrigOps.

Evidências: `target/auditoria-mvp-verify.log` e `target/auditoria-mvp-runtime.log` (artefatos temporários da auditoria, não versionados), além dos [testes do Controller](../../src/test/java/dev/joaov/refrigops/controller/EquipmentControllerTest.java).

## 9. Código × documentação

Foram lidos os **34 documentos Markdown versionados**. A verificação de destinos de links locais encontrou **46 links e nenhum arquivo de destino ausente**. Âncoras internas e disponibilidade de todos os links externos não foram validadas.

### Matriz por tema

| Tema | Código | Documentação | Situação |
|---|---|---|---|
| Equipamentos | Cadastro e listagem | Descreve base e evolução | Parcialmente alinhada |
| Resposta pública | DTO implementado | Alguns documentos ainda propõem criá-lo | **INCONSISTENTE** |
| Compressores | Valor de enum | Conceitos operacionais abstratos | Documentação além do modelo atual |
| Recipientes e condensadores | Valores de enum | Contexto e fontes de leitura | Sem comportamento especializado |
| Estados | Campo, enum e padrão inicial | Semântica e transições pendentes | Parcial |
| Rondas | Ausente | Fluxo provisório detalhado | Documentado, não implementado |
| Leituras | Ausente | Unidade, origem, horários e qualidade | Documentado, não implementado |
| Ocorrências | Ausente | Separação de medições e eventos | Documentado, não implementado |
| Operadores e turnos | Ausente | Autoria e responsabilidades em descoberta | Documentado, não implementado |
| Manutenção | Apenas enum | Exemplos e necessidades históricas | Sem módulo |
| Passagem de turno | Ausente | Resumo, continuidade e pendências | Documentado, não implementado |
| Autenticação | Ausente | Pré-condição de piloto | Pendente |
| Dashboard | Ausente | Visualização futura | Fora do estado atual |
| Testes | 6 testes | Referências a 5 e JUnit 5 | Snapshot desatualizado |

### Avaliação dos documentos

| Documento ou conjunto | Finalidade | Resultado da revisão |
|---|---|---|
| `README.md` | Entrada e execução | Instruções básicas coerentes; histórico não inclui a entrega atual |
| `AGENTS.md` e `CONTRIBUTING.md` | Segurança, ensino e contribuição | Coerentes com o processo; não comprovam configurações de proteção no GitHub |
| `00-documento-mestre-produto.md` | Visão e proposta de valor | Útil para descoberta; não é especificação aprovada da MVP; próximo DTO já entregue |
| `01-contexto-operacional.md` | Relatos da operação | Não descreve funcionalidades implementadas; mantém limites e perguntas |
| `02-arquitetura-atual.md` | Snapshot técnico | Ainda descreve entidade exposta, árvore sem DTO de saída e JUnit 5 |
| `03-regras-negocio-e-dominio.md` | Regras atuais e propostas | Separa bem várias hipóteses; estados permanecem sem definição definitiva |
| `04-medicoes-unidades-e-fontes.md` | Contexto dos dados | Relevante para futuro módulo; nada disso está persistido hoje |
| `05-riscos-seguranca-e-limites.md` | Limites e riscos | Ainda lista acoplamento direto do contrato HTTP à entidade, já resolvido |
| `06-mvp-roadmap-e-criterios.md` | Evolução por resultados | Continua listando DTO e testes JSON como entregas futuras |
| `07-testes-ambientes-e-operacao.md` | Testes e operação | Histórico de cinco testes; não inclui o cenário atual de GET |
| `08-historico-decisoes-e-aprendizados.md` | Evolução e aprendizagem | Preserva história útil; termina antes da entrega atual |
| `09-contrato-api-atual.md` | Contrato HTTP | É o documento técnico mais alinhado; faltam resultados agora comprovados dos erros |
| `11-contexto-atual.md` | Continuidade | Ainda manda revisar PR já integrada e mistura checkpoints documentais e técnicos |
| `12-guia-revisao-e-lacunas.md` | Perguntas de descoberta | Continua útil, mas mantém perguntas já resolvidas sobre DTO e instruções do README |
| ADRs 0001–0003 | Incrementos, Flyway e Testcontainers | Decisões aceitas e coerentes com código/histórico |
| ADR-0004 | Separação HTTP × entidade | Implementada, mas ainda marcada como proposta |
| ADR-0005 | Medições contextualizadas | Proposta futura, sem implementação |
| US-001 | Cadastro | Resposta pública aparece indevidamente como pendência |
| US-002 | Listagem | Ainda informa `List<Equipment>` e ausência de teste JSON |
| US-003 | Resposta pública | Critérios implementados; situação de revisão está atrasada |
| Índices de ADRs e histórias | Navegação | Links funcionam; status de ADR-0004 e US-003 está atrasado |

Fontes privadas de descoberta operacional foram consultadas somente para identificar necessidades abstratas. Nenhum inventário, fornecedor, configuração local, sala, regime, fotografia ou procedimento integra a baseline pública.

Os documentos públicos preservam apenas conclusões genéricas sobre rastreabilidade, diversidade de pontos, ausência de leitura, autoria e continuidade. Qualquer validação com dados de uma instalação deverá ocorrer em ambiente privado e autorizado.

Há repetição de conceitos e perguntas entre documentos de produto, domínio, pesquisa e revisão. Isso ajuda a contextualizar, mas já produziu pendências antigas em documentos secundários.

**Direção recomendada:** preservar registros históricos datados e centralizar estado corrente e pendências vigentes. Não reescrever acontecimentos antigos como se sempre tivessem sido conhecidos.

Evidências principais: [arquitetura atual](../02-arquitetura-atual.md), [contexto atual](../11-contexto-atual.md), [contrato HTTP](../09-contrato-api-atual.md) e [ADR-0004](../adr/0004-separar-contratos-http-entidades.md).

## 10. Avaliação arquitetural

| Aspecto | Avaliação |
|---|---|
| Simplicidade e legibilidade | Boas para o tamanho atual |
| Separação de responsabilidades | Adequada; Controller, Service e Repository têm funções claras |
| Coesão | Boa dentro do único domínio implementado |
| Acoplamento | HTTP separado da entidade na resposta; Service continua ligado a JPA, de forma aceitável neste estágio |
| Extensibilidade | Base aproveitável; domínio operacional ainda precisa ser definido |
| Testabilidade | Integração real funcionando; falta cobertura focada das regras e erros |
| Consistência | Principal fragilidade entre validação HTTP, restrições SQL e documentação |
| Segurança | Insuficiente para acesso operacional compartilhado |
| Observabilidade | Logs padrão; sem saúde, métricas ou auditoria de negócio próprias |
| Manutenção | Código pequeno; documentação exige sincronização |

### Problemas e direções

| Problema | Impacto | Prioridade | Possível direção |
|---|---|---|---|
| Duplicidade retorna 500 | Cliente não distingue conflito de falha interna | Antes do piloto | Contrato de conflito e tratamento específico, preservando a constraint |
| Tamanhos não validados na API | Dados rejeitados tardiamente pelo banco | Antes do piloto | Limites alinhados à migration e testes de fronteira |
| Estado inicial sem contexto temporal | Consumidor pode interpretar cadastro como condição física atual | Antes do fluxo operacional | Definir semântica de cadastro, observação e coleta |
| Sem identidade/autorização | Não há controle de acesso nem autoria confiável | Antes de uso real | Autenticação e permissões mínimas |
| Documentação atrasada | Próximos trabalhos podem repetir entregas | Antes do planejamento | Atualizar checkpoint e status das decisões |
| Código sem normalização | Códigos visualmente semelhantes podem coexistir | Importante | Decidir regras de espaços e diferenciação de letras |
| Testes parcialmente permissivos | Algumas regressões podem não ser detectadas | Importante | Asserções por objeto e cenários independentes |
| Listagem ilimitada | Pode dificultar uso conforme volume | Conforme necessidade | Definir ordenação, filtros e paginação com o piloto |

Não há transação declarada no Service. Isso não torna o fluxo atual defeituoso: ele realiza uma gravação por operação. Quando houver gravações relacionadas de ronda, leituras e eventos, a atomicidade deverá ser definida no caso de uso.

## 11. Dívida técnica priorizada

### Bloqueante antes de uma MVP operacional

- Resolver respostas de erro para duplicidade e tamanho.
- Definir a semântica do estado do equipamento.
- Implementar identidade, permissões e autoria conforme o piloto.
- Definir configuração segura do ambiente e acesso ao banco.
- Viabilizar recuperação dos registros com backup e restauração verificados.
- Tornar o registro operacional rastreável: horários, origem e correções.

**Antes de planejar a MVP**, também é necessário corrigir o checkpoint documental e aprovar seu recorte.

Ronda e leitura ainda inexistentes são **capacidades a construir**, não defeitos escondidos no código atual.

### Importante

- Fortalecer testes dos contratos e das fronteiras.
- Definir normalização de código.
- Revisar dependências e avisos de segurança.
- Configurar CI.
- Definir ordenação e consultas necessárias.
- Melhorar mensagens de erro e tratamento dos logs.
- Registrar decisões de domínio ainda provisórias.

### Pós-MVP ou dependente de necessidade

- OpenAPI automatizado.
- Filtros e relatórios avançados.
- Observabilidade mais extensa.
- Otimização de desempenho sem problema medido.
- Internacionalização.
- Bibliotecas de mapeamento.
- Reorganizações amplas de pacotes.

Mudar POST de 200 para 201 é uma decisão legítima de contrato, mas não é, isoladamente, um bloqueio de produto.

## 12. Segurança

### Credenciais e arquivos

**CONFIRMADO:**

- Compose e `application.properties` contêm credenciais fixas de desenvolvimento.
- A configuração dos testes usa credenciais previsíveis para o banco descartável.
- Não há `.env` na raiz ou entre arquivos rastreados.
- `.env` e `.env.local` não estão protegidos pelo `.gitignore`.

A inspeção dos arquivos atuais e a busca por padrões de tokens/chaves privadas nos 22 commits acessíveis não encontraram outros segredos reconhecíveis.

Essa busca não equivale a uma certificação de ausência de segredos, nem verifica objetos Git inacessíveis e serviços externos.

Evidências: [Compose](../../compose.yaml), [propriedades](../../src/main/resources/application.properties) e [.gitignore](../../.gitignore).

### Acesso e exposição

- GET e POST funcionaram sem autenticação.
- Não há autorização por perfil.
- O Compose publica `5433:5432` sem restringir o endereço de escuta a loopback.
- Não existe configuração versionada de HTTPS ou restrição de endereço HTTP.
- A acessibilidade efetiva por outras máquinas depende do ambiente e não foi testada.
- Não há política CORS própria. A verificação não recebeu permissão de origem externa; isso não substitui autenticação.

### Validação e logs

Os DTOs limitam os campos de entrada e saída. O envio de `status` e `active` não alterou os padrões aplicados pelo Service.

Por outro lado, a falha de duplicidade escreveu o código do equipamento no log, acompanhado de SQL e stack trace. Na auditoria, era um código fictício; com dados reais, o mesmo mecanismo pode registrar identificadores operacionais.

As respostas HTTP observadas não incluíram SQL nem stack trace.

### Dependências

A consulta foi pontual, baseada nas versões empacotadas e em avisos oficiais; não foi executada análise automatizada completa de todas as dependências transitivas.

- **Spring Data JPA 4.0.6:** está na faixa do CVE-2026-47834. O aviso exige consultas nativas com ordenação controlada externamente; esses caminhos não existem no Repository atual. A correção indicada para essa linha é 4.0.7. [Aviso oficial Spring](https://spring.io/security/cve-2026-47834/).
- **pgJDBC 42.7.11:** está na faixa do CVE-2026-54291, relativo a `channelBinding=require`, corrigido em 42.7.12. Essa opção não está configurada no projeto auditado. [Aviso oficial pgJDBC](https://jdbc.postgresql.org/security/).
- **Tomcat 11.0.22:** está em faixas de avisos corrigidos posteriormente, incluindo CVE-2026-68763, relativo a HTTP/2. Não há configuração de HTTP/2 no checkout; não foi demonstrada exploração na aplicação. [Avisos oficiais Tomcat](https://tomcat.apache.org/security-11.html).

**Conclusão:** há manutenção de dependências a planejar antes do piloto. Presença de versão afetada e exploração efetiva são classificações diferentes.

### Informação industrial

A documentação pública deve conter somente conceitos abstratos e exemplos sintéticos. Qualquer material derivado de uma instalação precisa de revisão própria e não é considerado automaticamente liberado para divulgação.

Visibilidade atual do repositório, permissões organizacionais e autorização de publicação **não foram determinadas**.

## 13. Estado atual do produto

**Classificação: fundação técnica funcional com backend parcial.**

Justificativa:

- existe uma operação técnica completa de HTTP até banco;
- há testes, migration e build funcionando;
- o único domínio persistido é equipamento;
- o problema operacional central ainda não pode ser resolvido pelo software;
- não existe interface para o operador executar seu trabalho;
- o recorte da MVP continua documentalmente provisório.

Portanto, “cadastro de equipamentos funcionando” é uma afirmação comprovada. “MVP de operação industrial pronta” não é.

A base pode continuar evoluindo; o próximo passo exige principalmente decisões de produto e domínio.

## 14. Fronteira sugerida da MVP

**Proposta para aprovação, não requisito já decidido:** validar uma ronda manual de escopo pequeno, com consulta posterior do histórico.

Recorte sugerido:

- área piloto e conjunto pequeno de equipamentos;
- roteiro e campos aplicáveis previamente validados;
- identificação de quem registra;
- leitura com unidade, fonte e horários pertinentes;
- distinção entre medido, não realizado, não aplicável e equipamento parado;
- encerramento ou interrupção;
- consulta histórica;
- ocorrência separada da leitura, quando necessária;
- resumo simples para continuidade entre turnos;
- interface adequada ao uso móvel e consulta em tela maior.

Essa direção deriva do [documento de produto](../00-documento-mestre-produto.md), do [roadmap](../06-mvp-roadmap-e-criterios.md) e dos [aprendizados abstratos de descoberta](../pesquisa/aprendizados-abstratos-descoberta-operacional.md).

### Limites de crescimento

| Capacidade | Classificação sugerida |
|---|---|
| Registro manual consultando IHM/instrumento | Essencial ao recorte proposto, com fonte explícita |
| Integração automática com máquinas/IHM | Visão futura |
| IoT, sensores novos e coleta em tempo real | Visão futura |
| Cloud | Opção futura de implantação; não requisito funcional |
| Notificações avançadas | Úteis pós-MVP |
| IA e manutenção preditiva | Visão futura; sem implementação ou definição suficiente |
| Aplicativo mobile nativo | Não exigido pela preferência mobile; avaliar depois |
| Dashboards complexos | Pós-MVP |
| Integrações corporativas | Visão futura |
| Gestão completa de manutenção | Pós-MVP |
| Tarefas futuras, restrições e confirmação de ciência | Descoberta específica; não incluir automaticamente |
| Cálculos de não condensáveis | Fora da MVP proposta; método não aprovado |

A preferência documentada por mobile não determina tecnologia nativa. A escolha de interface deve considerar conectividade e dispositivos reais.

## 15. Blocos funcionais da futura MVP

```text
MVP proposta
├── Identidade e acesso
│   └── quem registra, consulta e corrige
├── Equipamentos do piloto
│   └── identificação e dados necessários à coleta
├── Roteiro e pontos de coleta
│   └── ordem, aplicabilidade, unidades e fontes
├── Execução da ronda
│   └── início, andamento, conclusão e interrupção
├── Leituras e observações
│   └── valor ou ausência justificada
├── Ocorrências
│   └── registros próprios, relacionados quando necessário
├── Histórico e continuidade
│   └── consulta, correções e resumo simples
└── Interface e operação do piloto
    └── uso móvel, recuperação e tratamento de indisponibilidade
```

Esses blocos não representam tabelas ou serviços já aprovados. São capacidades para orientar a descoberta e o Documento Mestre.

A menor arquitetura coerente continua sendo uma aplicação Spring Boot, PostgreSQL e uma interface compartilhando a mesma API. Não há necessidade demonstrada de mensageria, event sourcing ou múltiplos backends.

## 16. Mapa de aprendizado

| Componente real ou futuro | Conceitos a estudar |
|---|---|
| API de equipamentos | REST, DTOs, status HTTP e contrato público |
| Validação e erros | Bean Validation, exceções específicas e conflitos |
| EquipmentService | Responsabilidade de aplicação e regras de inicialização |
| EquipmentRepository | JPA, contexto de persistência e transações |
| Schema atual e evolução | PostgreSQL, constraints, índices e Flyway |
| Ronda e leituras | Relacionamentos, integridade e ciclo de vida |
| Unidade, origem e horários | Modelagem de domínio e qualidade de dados |
| Correções e histórico | Rastreabilidade e preservação de informação |
| Identidade | Autenticação, autorização e autoria |
| Testes | Unitário, integração, MockMvc e Testcontainers |
| Interface | Integração frontend/backend e usabilidade |
| Ambiente | Docker, configuração e recuperação |
| Entrega | Issue, branch, commit, PR, review e CI |

Cada incremento deve permitir explicar: **qual problema resolve, por que a regra pertence àquela camada, como foi implementada e qual teste demonstra o resultado**.

## 17. Decisões que dependem de você

Antes de fechar a MVP:

1. **Finalidade da primeira entrega:** demonstração de portfólio ou piloto real com operadores.
2. **Problema prioritário:** ronda, consulta histórica ou continuidade entre turnos.
3. **Recorte:** área, equipamentos e usuários participantes.
4. **Critério de sucesso:** qual resultado demonstrará utilidade.
5. **Campos do piloto:** quais são obrigatórios, opcionais ou inaplicáveis.
6. **Validação técnica:** quem confirma unidades, rótulos e fontes.
7. **Semântica dos estados:** cadastro ativo, condição observada e resultado da coleta.
8. **Autoria e correção:** quem registra, revisa e altera dados.
9. **Conectividade:** funcionamento online suficiente ou necessidade comprovada de offline.
10. **Passagem de turno:** resumo simples basta ou tarefas futuras são parte indispensável do primeiro problema.
11. **Governança:** dados permitidos, acesso, retenção e uso em portfólio.
12. **Infraestrutura do piloto:** local de execução, responsável e recuperação.

Há informações já relatadas sobre rota, controladores e equipamentos. Essas decisões devem aproveitar os registros existentes, pedindo apenas as confirmações que faltam.

Detalhes de pressão, setpoint, equivalência entre siglas, alarmes e procedimentos não podem ser inferidos do código.

## 18. Próxima etapa recomendada

O próximo resultado deve ser **um Documento Mestre da MVP com escopo aprovado**, usando esta auditoria como checkpoint técnico.

A sequência recomendada é:

```text
Auditoria atual
  ↓
Decisões de produto e recorte do piloto
  ↓
Documento Mestre da MVP
  ↓
Arquitetura mínima para esse recorte
  ↓
Backlog de incrementos pequenos
```

O Documento Mestre deverá fixar:

- problema e usuário prioritários;
- fluxo completo a validar;
- critérios de sucesso;
- requisitos funcionais e de qualidade;
- regras confirmadas e dúvidas restantes;
- limites da primeira entrega;
- responsabilidades e condições do piloto.

O documento de produto já existente deve continuar como visão ampla. A especificação da MVP precisa representar um recorte deliberado dessa visão.

Depois, a arquitetura deverá definir somente o necessário: relações entre dados, contratos, transações, acesso, autoria, histórico e estratégia de testes.

A rastreabilidade pode começar com identificadores simples:

```text
Requisito
  → decisão ou ADR, quando necessária
  → Issue
  → código
  → teste
  → documentação
```

Cada Issue futura deverá conter um resultado observável, critérios de aceite, limites de escopo, validação e objetivo de aprendizagem.

O ciclo de entrega deve incorporar sua revisão e compreensão antes do merge:

```text
Issue → implementação → testes → PR → revisão técnica
      → sessão de aprendizagem → compreensão → merge
      → próxima Issue
```

Não é necessário refazer `EquipmentResponse`. A entrega já existe, está integrada e foi validada nesta auditoria.

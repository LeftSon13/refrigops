# Histórico de decisões e aprendizados

## 1. Linha do tempo conceitual

**[HISTÓRICO — CONVERSA]**

```text
experiência na operação industrial
        ↓
percepção de registros fragmentados
        ↓
ideia de automatizar leituras
        ↓
hipóteses de IHM, sensores, nuvem e análise
        ↓
reconhecimento dos limites de integração e segurança
        ↓
valorização da ronda e percepção humana
        ↓
visão de sistema de apoio operacional
        ↓
início técnico com Equipment
```

## 2. Linha do tempo técnica

### Inicialização

```text
dce1a5d chore: initialize RefrigOps project
```

### PR #1 — persistência e API inicial

```text
473477b feat: add equipment persistence foundation
306e937 test: verify equipment repository persistence
311e0df feat: expose equipment listing endpoint
dbde207 feat: enforce default equipment state on creation
7bb621b refactor: desacopla service do DTO da camada controller
1ac18d9 Merge pull request #1
```

Aprendizados:

- entidade, Repository, Service e Controller;
- migration com Flyway;
- enum textual;
- padrões de criação;
- Service não depender do DTO do Controller;
- branches e PR como parte do portfólio.

### Issue #2 / PR #3 — Testcontainers

```text
d6edbf4 build: adiciona dependências do Testcontainers
3127cc9 test: isola testes de integração com PostgreSQL temporário
a2dee6a Merge pull request #3
```

Motivação:

Os testes que carregavam Spring podiam usar o datasource de desenvolvimento. A solução criou PostgreSQL temporário compartilhado por todos esses testes.

Aprendizados:

- isolamento;
- banco descartável;
- `@ServiceConnection`;
- Flyway em testes;
- `contextLoads` também precisa do ambiente correto.

### Issue #4 / PR #5 — Bean Validation

```text
3cf48ab feat: valida dados no cadastro de equipamentos
b794a14 test: cobre validação do cadastro de equipamentos
5b85711 Merge pull request #5
```

Aprendizados:

- RED → GREEN;
- `@NotBlank`, `@NotNull` e `@Valid`;
- HTTP 400;
- MockMvc com stack real;
- cadastro válido precisa continuar funcionando;
- review detecta regressões fora do cenário testado.

## 3. Decisões duradouras

- incrementos pequenos e demonstráveis;
- GitHub com Issue, branch, commits, PR e review;
- merge normal quando os commits separados contam uma evolução útil;
- Flyway é responsável pelo schema;
- Hibernate valida;
- Testcontainers isola testes;
- DTO de entrada pertence à camada HTTP;
- Service não depende do DTO do Controller;
- entidade JPA não deve definir automaticamente o contrato público;
- documentação deve separar fato, história, hipótese e decisão;
- segurança e fidelidade aos dados antes de automação.

## 4. Incidentes de aprendizado

### Edição por PowerShell

Ocorreram sobrescritas incompletas, chaves ausentes e problema de encoding ao usar comandos de escrita. Isso motivou:

- UTF-8 explícito;
- mudanças menores;
- conferência do arquivo;
- teste limpo;
- revisão do diff antes do commit.

### Build incremental

Uma execução indicou “Nothing to compile”, mostrando que um build aparente pode não provar que o arquivo recém-editado foi recompilado. A resposta foi usar `clean test` quando necessário e confrontar o conteúdo real.

### Regressão de `@GetMapping`

Durante a validação do POST, o `@GetMapping` chegou a desaparecer. O teste focado no POST não detectou. Isso reforçou a necessidade de suíte ampla e review de diff.

### Banco de desenvolvimento

Um teste que persiste pode alterar dados reais se usar a configuração padrão. O Testcontainers foi adotado como proteção arquitetural.

### “Percentual de ar”

A investigação mostrou que reproduzir a fórmula do formulário não valida seu significado físico. Também revelou mistura de unidades e entrada ajustada para gerar resultado esperado.

## 5. Próxima decisão histórica esperada

Criar DTO de resposta para Equipment, após decidir quais campos pertencem ao contrato público.

Essa decisão deve ser registrada em ADR quando implementada.

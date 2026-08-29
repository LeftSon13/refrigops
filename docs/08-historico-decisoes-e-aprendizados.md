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
993c026 chore: initialize RefrigOps project
```

### PR #1 — persistência e API inicial

```text
62c97e6 feat: add equipment persistence foundation
450cac9 test: verify equipment repository persistence
be66caa feat: expose equipment listing endpoint
9bf6f13 feat: enforce default equipment state on creation
19605e2 refactor: desacopla service do DTO da camada controller
c1f3992 Merge pull request #1
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
6c5c2ba build: adiciona dependências do Testcontainers
de0b35c test: isola testes de integração com PostgreSQL temporário
1107e89 Merge pull request #3
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
5914dae feat: valida dados no cadastro de equipamentos
cd6a81c test: cobre validação do cadastro de equipamentos
938480a Merge pull request #5
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

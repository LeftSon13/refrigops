# Testes, ambientes e operação

## 1. Ambientes conhecidos

### Desenvolvimento

**[CONFIRMADO — REPOSITÓRIO]**

PostgreSQL 17 via Docker Compose:

```text
container: refrigops-postgres
host: localhost
porta: 5433
banco: refrigops
usuário: refrigops
```

A aplicação usa as propriedades em `src/main/resources/application.properties`.

### Testes automatizados

**[CONFIRMADO — REPOSITÓRIO]**

PostgreSQL 17 temporário via Testcontainers e `@ServiceConnection`:

```text
banco: refrigops_test
porta: alocada dinamicamente
migration: Flyway V1
```

### Homologação e produção

**[PENDENTE]** Não existem ambientes documentados nem implantação confirmada.

## 2. Testes existentes

### `RefrigopsApplicationTests`

Carrega o contexto Spring usando Testcontainers.

### `EquipmentRepositoryTest`

Persiste e recupera um equipamento real no PostgreSQL temporário e verifica todos os campos.

### `EquipmentControllerTest`

Usa MockMvc com Spring completo e PostgreSQL temporário.

Cenários:

```text
campos textuais vazios → HTTP 400
type nulo              → HTTP 400
request válida         → HTTP 200
```

## 3. Última execução histórica

**[HISTÓRICO — REPOSITÓRIO]**

```text
Tests run: 5
Failures: 0
Errors: 0
Skipped: 0
BUILD SUCCESS
```

### Tentativa de reconfirmação em 2026-08-29

Foi executado:

```powershell
.\mvnw.cmd clean test --batch-mode --no-transfer-progress
```

Resultado:

```text
Tests run: 5
Failures: 0
Errors: 5
BUILD FAILURE
```

Causa raiz observada antes do carregamento dos contextos:

```text
Could not find a valid Docker environment
```

O Testcontainers não conseguiu iniciar o PostgreSQL temporário porque o Docker não estava disponível para o processo. Portanto, essa execução **não confirma nem refuta o comportamento do código**. A suíte precisa ser repetida com Docker Desktop disponível.

Nenhum teste foi desativado e nenhuma configuração foi alterada para contornar o isolamento.

### Reexecução com Docker disponível

Depois que o Docker Desktop foi iniciado, o mesmo comando foi executado novamente.

Resultado confirmado em 2026-08-29:

```text
Tests run: 5
Failures: 0
Errors: 0
Skipped: 0
BUILD SUCCESS
```

O log confirmou:

- Testcontainers 2.0.5 conectado ao Docker Desktop;
- PostgreSQL 17.10 temporário;
- bancos `refrigops_test` em portas dinâmicas;
- Flyway validando e aplicando a migration V1;
- testes de Controller, Repository e `contextLoads` aprovados.

Portanto, a suíte atual está reconfirmada e o isolamento permanece funcionando.

## 4. Estratégia de testes

### Teste focado durante desenvolvimento

Executar a menor suíte que reproduz o comportamento alterado.

### Suíte completa antes do PR

Executar Maven test/verify com Docker disponível.

### Revisão do diff

Executar `git diff --check` e revisar todos os arquivos.

### Evidência no PR

Registrar o comando e o resultado real, sem afirmar validações não executadas.

## 5. Pirâmide de testes futura

**[HIPÓTESE]**

- testes unitários para regras puras;
- testes de Controller mais focados para contrato HTTP;
- testes de persistência/integrados com Testcontainers;
- poucos testes de fluxo completo;
- testes de interface quando ela existir.

Não substituir os testes integrados atuais antes de preservar sua cobertura.

## 6. Dados de teste

- usar nomes e códigos fictícios;
- evitar dados reais de planta;
- gerar valores previsíveis;
- manter testes independentes;
- evitar depender da ordem de execução;
- não apontar para banco persistente.

## 7. Migrations

**[DECISÃO]** O Flyway controla a evolução do schema.

- não editar uma migration já aplicada para “corrigir” ambiente existente;
- criar nova migration para mudança incremental;
- testar migration em banco vazio e, quando aplicável, atualização a partir da versão anterior;
- manter `ddl-auto=validate` para detectar divergência;
- documentar mudança de dados quando houver transformação.

## 8. Docker e Testcontainers

Docker Desktop é pré-requisito atual para a suíte integrada. Se estiver desligado, o erro deve ser tratado como problema de ambiente, não motivo para remover o isolamento.

O container de Testcontainers é descartável; portas diferentes entre execuções são esperadas.

## 9. Configuração e segredos

As credenciais atuais são apenas locais. Antes de outro ambiente:

- usar variáveis ou mecanismo seguro;
- não commitar segredos;
- separar perfis;
- restringir rede e permissões;
- rotacionar credenciais;
- revisar logs.

## 10. Operação futura

Antes de implantação, documentar:

- processo de build;
- configuração por ambiente;
- migration e rollback operacional;
- health checks;
- logs estruturados;
- métricas;
- backup e restauração;
- retenção;
- atualização;
- resposta a incidentes;
- responsáveis;
- suporte a indisponibilidade/offline.

## 11. Checklist antes de PR

- [ ] branch correta;
- [ ] Issue e escopo revisados;
- [ ] teste focado executado;
- [ ] suíte completa executada;
- [ ] `git diff --check` limpo;
- [ ] diff revisado;
- [ ] migrations revisadas;
- [ ] nenhum segredo ou dado real;
- [ ] documentação atualizada quando necessário;
- [ ] resultado real registrado no PR.

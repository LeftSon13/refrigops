# ADR-0003 — Testes Spring usam PostgreSQL temporário

- Status: Aceita
- Evidência: `TestcontainersConfiguration` importada nos testes Spring

## Contexto

Testes que carregavam o contexto podiam usar o datasource de desenvolvimento, persistindo dados fora do escopo do teste e criando dependência de estado local.

## Decisão

Usar PostgreSQL 17 temporário via Testcontainers e `@ServiceConnection` em todas as classes de teste que carregam Spring.

## Consequências positivas

- isolamento;
- banco compatível com produção pretendida;
- migrations validadas;
- testes repetíveis;
- banco de desenvolvimento protegido.

## Consequências negativas

- Docker é necessário;
- testes são mais lentos;
- primeira execução pode baixar imagens;
- falhas de ambiente Docker precisam ser distinguidas de falhas da aplicação.

## Alternativas não adotadas

- usar o PostgreSQL persistente de desenvolvimento;
- substituir por banco em memória com comportamento diferente;
- desativar `contextLoads`.

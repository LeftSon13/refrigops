# Registros de decisões arquiteturais (ADRs)

ADRs registram decisões relevantes, seu contexto, alternativas e consequências. Eles não substituem documentação de código nem lista de tarefas.

## Estados

- `Proposta`: ainda não aprovada;
- `Aceita`: decisão vigente;
- `Substituída`: outra ADR passou a valer;
- `Rejeitada`: analisada e não adotada.

## Índice

- [ADR-0001 — Evoluir em incrementos verticais pequenos](0001-incrementos-verticais-pequenos.md)
- [ADR-0002 — Flyway controla o schema e Hibernate valida](0002-flyway-controla-schema.md)
- [ADR-0003 — Testes Spring usam PostgreSQL temporário](0003-testcontainers-postgresql.md)
- [ADR-0004 — Separar contratos HTTP das entidades](0004-separar-contratos-http-entidades.md)
- [ADR-0005 — Tratar medições com valor, unidade e origem](0005-medicoes-com-contexto.md)

As ADRs 0001–0003 descrevem decisões já presentes no histórico/código. A ADR-0004 é proposta para a próxima Issue. A ADR-0005 é uma decisão de modelagem documental para módulos futuros.

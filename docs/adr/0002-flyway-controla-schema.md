# ADR-0002 — Flyway controla o schema e Hibernate valida

- Status: Aceita
- Evidência: `V1__create_equipment_table.sql` e `ddl-auto=validate`

## Contexto

Deixar o ORM criar/alterar tabelas automaticamente reduz controle sobre a evolução e dificulta reprodução entre ambientes.

## Decisão

Usar Flyway para migrations versionadas e Hibernate com:

```properties
spring.jpa.hibernate.ddl-auto=validate
```

## Consequências positivas

- evolução explícita;
- banco reproduzível;
- histórico do schema;
- divergência detectada no início da aplicação;
- testes executam as mesmas migrations.

## Consequências negativas

- toda mudança persistente requer migration;
- migrations aplicadas não devem ser reescritas;
- mudanças de dados exigem mais cuidado.

## Alternativas não adotadas

- `ddl-auto=create` ou `update`;
- SQL manual sem versionamento;
- confiar apenas no mapeamento JPA.

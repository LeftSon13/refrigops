# ADR-0004 — Separar contratos HTTP das entidades

- Status: Proposta
- Próxima Issue recomendada: Desacoplar respostas da API da entidade Equipment

## Contexto

`EquipmentController` retorna atualmente `Equipment` e `List<Equipment>`. A entidade JPA define, por consequência, o JSON público.

Isso permite que uma mudança de persistência altere o contrato HTTP sem decisão explícita.

## Decisão proposta

Criar `EquipmentResponse` e mapear:

```text
Equipment → EquipmentResponse → JSON
```

Manter `CreateEquipmentRequest` como contrato de entrada.

## Campos candidatos

```text
id
code
name
type
status
active
location
```

A inclusão de cada campo deve ser decidida antes da implementação.

## Consequências positivas

- contrato explícito;
- persistência e API evoluem separadamente;
- testes JSON ficam claros;
- campos internos podem permanecer ocultos.

## Consequências negativas

- código de mapeamento;
- risco de duplicar representações;
- necessidade de decidir onde o mapeamento pertence.

## Fora do escopo desta decisão

- mudar 200 para 201;
- padronizar todos os erros;
- introduzir biblioteca automática de mapeamento;
- alterar a entidade ou migration;
- criar front-end.

## Critério de aceitação da ADR

Revisar o código atual, escolher campos públicos, implementar via Issue e marcar a ADR como aceita após merge.

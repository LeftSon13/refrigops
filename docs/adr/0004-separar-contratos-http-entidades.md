# ADR-0004 — Separar contratos HTTP das entidades

- Status: Aceita
- Implementada pela Issue #8 e integrada pela PR #9 em `c8a2802`

## Contexto

Antes da Issue #8, `EquipmentController` retornava `Equipment` e `List<Equipment>`. A entidade JPA definia, por consequência, o JSON público.

Isso permite que uma mudança de persistência altere o contrato HTTP sem decisão explícita.

## Decisão

Criar `EquipmentResponse` e mapear:

```text
Equipment → EquipmentResponse → JSON
```

Manter `CreateEquipmentRequest` como contrato de entrada.

## Campos públicos adotados

```text
id
code
name
type
status
active
location
```

Esses campos foram preservados no contrato de criação e listagem coberto pelos testes do Controller.

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

## Evidência de adoção

- `EquipmentResponse` representa a resposta pública;
- `EquipmentResponse.from(Equipment)` realiza o mapeamento explícito na fronteira HTTP;
- GET e POST retornam DTO, não a entidade JPA;
- Service e Repository permanecem independentes dos DTOs HTTP;
- a PR #9 foi merged em 2026-09-07 no commit `c8a2802`.

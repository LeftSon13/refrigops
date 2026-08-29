# ADR-0001 — Evoluir em incrementos verticais pequenos

- Status: Aceita
- Data histórica aproximada: agosto de 2026

## Contexto

O RefrigOps é simultaneamente descoberta de um domínio industrial, produto em formação e projeto de aprendizado. Criar muitas entidades e camadas sem comportamento visível dificultaria compreensão e validação.

## Decisão

Desenvolver um resultado por vez, atravessando as camadas necessárias e finalizando seu ciclo com teste, Git e review.

Fluxo:

```text
problema → Issue → branch → comportamento → teste → commits → PR → review → merge
```

## Consequências positivas

- aprendizado ligado a comportamento real;
- regressões mais fáceis de localizar;
- histórico compreensível;
- escopo de PR menor;
- decisões de domínio podem amadurecer antes de abstrações.

## Consequências negativas

- algumas duplicações temporárias podem permanecer;
- arquitetura não é “completa” antecipadamente;
- exige disciplina para não misturar melhorias.

## Alternativas não adotadas

- modelar todo o domínio antes de implementar;
- criar múltiplos módulos e microsserviços desde o início;
- trabalhar diretamente na `main` sem Issues/PRs.

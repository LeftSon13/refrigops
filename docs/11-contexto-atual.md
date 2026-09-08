# Contexto atual

> Atualizado em 2026-09-07 para a entrega documental da Issue #11 / MVP-ISSUE-001.

## Fase

Incorporação da baseline aprovada da MVP demonstrativa v0.1 ao repositório. Esta entrega é exclusivamente documental e antecede qualquer nova funcionalidade da MVP.

## Repositório e checkpoint técnico

**[CONFIRMADO — REPOSITÓRIO E GITHUB]**

```text
repositório: LeftSon13/refrigops
base: origin/main
HEAD da base: bd63b82a19b9fba9b641fd70c06b1a3ab907de6e
último merge do repositório: PR #71 — remoção da credencial fixa do PostgreSQL
último merge funcional: PR #9 — EquipmentResponse
Issue desta entrega: #11 — MVP-ISSUE-001
branch da entrega: docs/issue-11-baseline-rebuild
```

Em 2026-09-07, a referência `origin/main` foi atualizada para `bd63b82`, merge da PR #71. A entrega da Issue #11 foi reconstruída diretamente dessa base em uma nova branch, sem incorporar a tentativa local anterior à sua ancestralidade.

## Baseline da MVP v0.1

**[DECISÃO DE PRODUTO VERSIONADA]**

A fonte específica da MVP demonstrativa v0.1 passa a ser o [Documento Mestre da MVP](mvp/documento-mestre-v0.1.md), apoiado por:

- [EPIC e backlog](mvp/epic-e-backlog-v0.1.md);
- [mapa de publicação no GitHub](mvp/mapa-publicacao-github-v0.1.md);
- [EPIC #10](https://github.com/LeftSon13/refrigops/issues/10);
- [Issue #11](https://github.com/LeftSon13/refrigops/issues/11).

O documento amplo de produto permanece útil para visão e histórico. Em caso de divergência sobre o recorte desta MVP, prevalecem a baseline aprovada, as decisões rastreadas, a Issue atual e o comportamento comprovado pelo código.

## Estado funcional confirmado

O backend implementa somente a fundação de `Equipment`:

- entidade, tipos e estados de equipamento;
- `EquipmentRepository` e `EquipmentService`;
- `CreateEquipmentRequest` com validações de presença;
- `EquipmentResponse` como contrato público de saída;
- `GET /api/equipment` e `POST /api/equipment`;
- estado inicial `STOPPED` e `active = true`;
- migration V1 controlada pelo Flyway;
- PostgreSQL de desenvolvimento via Compose;
- testes Spring com PostgreSQL temporário via Testcontainers.

O Controller converte explicitamente `Equipment` em `EquipmentResponse`. Service e Repository continuam independentes dos DTOs HTTP.

## Funcionalidades ainda não existentes

Não estão implementados:

- autenticação, perfis de turno ou identidades de operador;
- programação, execução ou conclusão de rondas;
- pontos de medição, leituras ou ausências justificadas;
- ocorrências, correções, histórico funcional ou passagem de turno;
- frontend da MVP;
- integrações automáticas, telemetria ou controle industrial.

A existência desses conceitos na baseline ou no backlog representa planejamento, não comportamento entregue.

## Testes e evidências

A PR #9 registrou, com Java 21 e PostgreSQL temporário:

```text
Tests run: 6
Failures: 0
Errors: 0
Skipped: 0
BUILD SUCCESS
```

Esse resultado pertence ao checkpoint de 2026-09-07. A Issue #11 não altera código, configuração, migration ou testes automatizados; suas verificações obrigatórias são links, diff e consistência textual.

## Documentação desta entrega

A Issue #11:

- incorpora a baseline e o backlog em `docs/mvp/`;
- preserva a auditoria técnica como relatório datado em `docs/auditorias/`;
- mantém fora da documentação pública o antigo contexto de continuidade que contém detalhes operacionais específicos;
- separa prompts operacionais em `prompts/`;
- marca a ADR-0004 como aceita após a implementação e o merge da PR #9;
- corrige referências atuais que ainda tratavam `EquipmentResponse` como trabalho futuro.

Materiais históricos com contexto operacional específico não integram a árvore pública. Uma eventual versão educacional deverá ser outro documento, totalmente reescrito e sintético.

## Segurança e limites

O RefrigOps continua sendo sistema de apoio e registro. Esta entrega não introduz comandos, setpoints, alarmes, cálculos físicos, estimativas operacionais ou integração com equipamentos.

Dados de desenvolvimento da MVP devem permanecer fictícios. A experiência profissional pode inspirar o problema, mas equipamentos, layout, operação, horários, pessoas, instrumentos, configurações, valores e procedimentos de instalações reais não fazem parte da documentação pública. Nenhuma documentação substitui procedimentos, intertravamentos, instrumentos certificados ou profissionais habilitados.

## Pendências e bloqueios

- A Issue #11 precisa passar por PR, revisão técnica, sessão de aprendizagem e autorização de merge.
- A #12 / MVP-ISSUE-002 permanece bloqueada até o merge da #11.
- As decisões técnicas T-01 a T-05 serão tratadas somente nas Issues arquiteturais previstas.
- O refinamento sobre tentativas inválidas de PIN permanece reservado à Issue #26.

## Próximo passo permitido

Concluir o ciclo da Issue #11 sem merge automático. Somente depois do merge e da sincronização da `main` poderá começar a #12 / MVP-ISSUE-002, em outra branch e outra Pull Request.

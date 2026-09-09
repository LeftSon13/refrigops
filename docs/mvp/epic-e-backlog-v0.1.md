# RefrigOps — EPIC e backlog da MVP demonstrativa v0.1

**Fonte principal:** [`documento-mestre-v0.1.md`](documento-mestre-v0.1.md), baseline aprovada em 07/09/2026.
**Estado:** planejamento publicado sob a EPIC #10; não autoriza implementação em lote nem dispensa a revisão individual de cada Issue.
**Checkpoint técnico consultado:** `main` em um checkpoint pré-rewrite (equivalente saneado: `76b7ca4`), limpa no início desta decomposição.
**Escopo:** EPIC principal e 59 Issues provisórias, `MVP-ISSUE-001` a `MVP-ISSUE-059`.

## A. Resumo executivo

O backlog divide a MVP em 12 grupos e 59 resultados pequenos. O refinamento separa decisão arquitetural de implementação para T-04, T-01, T-02 e T-03; mantém T-05 como refinamento próprio da ADR-0005; divide ciclos de perfil e identidade; e remove a regra não aprovada de reset automático das tentativas de PIN após sucesso.

Cada Issue é uma proposta de trabalho independente. Sua aprovação acontece individualmente, antes de implementação. Números reais de GitHub, endpoints novos, nomes de migrations e detalhes técnicos abertos serão definidos somente no refinamento da respectiva Issue.

Issues funcionais anteriores à consolidação final podem criar fixtures sintéticas mínimas para seus testes e demonstrações. Nenhuma usa dados reais da planta, nenhuma cria uma funcionalidade de seed própria e nenhuma substitui a consolidação idempotente da MVP-ISSUE-054.

Estado confirmado do software: existem `Equipment`, Repository, Service, `CreateEquipmentRequest`, `EquipmentResponse`, GET/POST `/api/equipment`, Bean Validation básica, migration V1 e testes Spring/Testcontainers. Não existem autenticação, perfis de turno, identidades de operador, programação, rondas, medições, ocorrências, histórico funcional ou frontend da MVP.

Inconsistência documental encontrada: a ADR-0004 ainda está como “Proposta”, embora `EquipmentResponse` já esteja implementado na `main`. A MVP-ISSUE-001 corrige o registro sem refazer a funcionalidade.

## B. EPIC — RefrigOps — MVP demonstrativa v0.1

### Problema

As rondas e ocorrências precisam ser recuperáveis sem confundir valor medido, ausência, condição observada e acontecimento do turno. O software atual cobre apenas o cadastro inicial de Equipment.

### Objetivo

Entregar uma aplicação web responsiva, com dados fictícios, em que uma equipe entra pelo perfil compartilhado do turno; o operador confirma sua identidade por PIN; assume, executa, conclui ou deixa uma ronda para retomada; registra medições, ausências e ocorrências; e consulta continuidade e histórico rastreável.

### Usuários e fluxo

- Administrador: prepara Equipment, pontos, roteiro, perfis, horários, metas, credenciais e identidades.
- Equipe do turno: autentica-se por perfil compartilhado.
- Operador: seleciona o nome ativo, confirma PIN e assume ações com autoria.
- Fluxo: preparar → autenticar → visualizar turno/rondas → assumir → coletar → concluir ou deixar pendente → consultar histórico/continuidade → corrigir ou acompanhar ocorrência com rastreabilidade.

### Limites

Dados e equipamentos são fictícios. Não há login individual cotidiano, offline, PWA, IoT, IA, frontend separado, microserviços, controle industrial, escala 6x2 completa, cálculo físico, alarmes ou piloto real. Propostas técnicas T-01 a T-05 continuam sujeitas ao refinamento indicado nas Issues.

### Rastreabilidade

REQ-MVP-001 a REQ-MVP-012; DEC-P-01 a DEC-P-45; CS-01 a CS-12; ADR-0001 a ADR-0005.

### Definition of Done da EPIC

- [ ] Todas as Issues necessárias foram aprovadas, implementadas, testadas, revisadas e merged individualmente.
- [ ] REQ-MVP-001 a REQ-MVP-012 possuem evidência.
- [ ] CS-01 a CS-12 possuem resultado registrado; CS-09 segue DEC-P-42.
- [ ] Fluxo completo funciona em Chrome desktop e viewport móvel.
- [ ] Dados fictícios, limitações e ausência de validação industrial aparecem claramente.
- [ ] Backup restaurável reproduz a demonstração em banco separado.
- [ ] Documentação versionada reflete o comportamento realmente entregue.
- [ ] Nenhuma funcionalidade pós-MVP foi incorporada silenciosamente.

### Relação com as Issues filhas

A EPIC agrupa e rastreia as Issues abaixo; não possui implementação própria. Cada Issue filha entrega um comportamento observável e segue: Issue aprovada → Codex implementa → testes → PR → revisão técnica → sessão de aprendizagem → compreensão → merge → próxima Issue.

## C. Mapa do backlog

| Grupo | Issues | Resultado acumulado |
|---|---|---|
| 0. Baseline e contexto | 001 | Fontes técnicas alinhadas à baseline aprovada |
| 1. Contrato de Equipment | 002–007 | Contrato validado e T-04 decidida antes da evolução do estado legado |
| 2. Acesso e identidade mínimos | 008–020 | T-01, login administrativo/turno, PIN e ciclo de identidades em incrementos separados |
| 3. Preparação da ronda | 021–029 | Pontos, programação, T-02, rondas previstas e ciclo do perfil |
| 4. Iniciar e visualizar execução | 030–033 | Turno visível, snapshot implementado e assunção concorrente segura |
| 5. Primeiro resultado de coleta | 034–038 | T-05 e primeira medição, ausência, contexto e condição observada |
| 6. Gravação confiável | 039–043 | T-03, idempotência, correções, revisões e concorrência |
| 7. Progresso e desfecho | 044–047 | Pendência, retomada, conclusão e encerramento por turno |
| 8. Ocorrências | 048–050 | Criação, acompanhamento e resolução rastreáveis |
| 9. Consulta e continuidade | 051–053 | Histórico, continuidade e revisões consultáveis |
| 10. Demonstração reproduzível | 054–058 | Dataset consolidado, responsividade, execução e recuperação |
| 11. Avaliação e fechamento | 059 | CS-01 a CS-12 executados e DoD avaliada |

### Índice compacto das Issues

| Issue | Grupo | Entrega |
|---|---|---|
| MVP-ISSUE-001 | 0 | Incorporar a baseline aprovada ao contexto versionado |
| MVP-ISSUE-002 | 1 | Rejeitar textos de Equipment maiores que as colunas |
| MVP-ISSUE-003 | 1 | Padronizar erros de validação com ProblemDetail |
| MVP-ISSUE-004 | 1 | Remover espaços externos do código de Equipment |
| MVP-ISSUE-005 | 1 | Retornar 409 para código de Equipment duplicado |
| MVP-ISSUE-006 | 1 | Decidir e documentar a transição de Equipment.status |
| MVP-ISSUE-007 | 1 | Implementar a estratégia aprovada para Equipment.status |
| MVP-ISSUE-008 | 2 | Decidir e documentar a arquitetura de acesso T-01 |
| MVP-ISSUE-009 | 2 | Entregar o login e logout administrativo mínimo conforme T-01 |
| MVP-ISSUE-010 | 2 | Cadastrar um perfil compartilhado de turno |
| MVP-ISSUE-011 | 2 | Entrar pelo perfil compartilhado do turno |
| MVP-ISSUE-012 | 2 | Alterar a senha de um perfil sem derrubar sessões existentes |
| MVP-ISSUE-013 | 2 | Desativar perfil e invalidar suas sessões imediatamente |
| MVP-ISSUE-014 | 2 | Cadastrar identidade de operador com PIN |
| MVP-ISSUE-015 | 2 | Confirmar identidade ativa por seleção e PIN |
| MVP-ISSUE-016 | 2 | Bloquear identidade após cinco PINs inválidos |
| MVP-ISSUE-017 | 2 | Redefinir PIN e reativar identidade bloqueada |
| MVP-ISSUE-018 | 2 | Bloquear e reativar identidade manualmente |
| MVP-ISSUE-019 | 2 | Renomear identidade preservando autoria histórica |
| MVP-ISSUE-020 | 2 | Excluir identidade somente quando nunca utilizada |
| MVP-ISSUE-021 | 3 | Cadastrar pontos de medição e ordenar o roteiro |
| MVP-ISSUE-022 | 3 | Configurar horário e quantidade coletiva de rondas |
| MVP-ISSUE-023 | 3 | Impedir sobreposição na programação do mesmo perfil |
| MVP-ISSUE-024 | 3 | Decidir e documentar o contexto congelado da ronda T-02 |
| MVP-ISSUE-025 | 3 | Aplicar alterações de horário e meta somente a turnos futuros |
| MVP-ISSUE-026 | 3 | Disponibilizar as rondas previstas de cada turno |
| MVP-ISSUE-027 | 3 | Reativar perfil somente para turnos futuros |
| MVP-ISSUE-028 | 3 | Renomear perfil preservando nome histórico |
| MVP-ISSUE-029 | 3 | Excluir perfil somente quando nunca utilizado |
| MVP-ISSUE-030 | 4 | Mostrar o turno atual e suas rondas disponíveis |
| MVP-ISSUE-031 | 4 | Implementar o snapshot de contexto da ronda conforme T-02 |
| MVP-ISSUE-032 | 4 | Permitir que um operador assuma uma ronda prevista |
| MVP-ISSUE-033 | 4 | Resolver concorrência ao assumir a mesma ronda |
| MVP-ISSUE-034 | 5 | Refinar o modelo mínimo de medição T-05 |
| MVP-ISSUE-035 | 5 | Salvar e reler uma medição válida |
| MVP-ISSUE-036 | 5 | Registrar unidade, fonte e horários da medição |
| MVP-ISSUE-037 | 5 | Registrar ausência justificada sem valor numérico |
| MVP-ISSUE-038 | 5 | Registrar condição observada separada do resultado |
| MVP-ISSUE-039 | 6 | Decidir e documentar gravação confiável T-03 |
| MVP-ISSUE-040 | 6 | Tornar a gravação de resultado idempotente conforme T-03 |
| MVP-ISSUE-041 | 6 | Corrigir o próprio registro com motivo e revisão |
| MVP-ISSUE-042 | 6 | Permitir correção administrativa com autoria preservada |
| MVP-ISSUE-043 | 6 | Evitar revisões concorrentes silenciosas |
| MVP-ISSUE-044 | 7 | Deixar a ronda pendente e retomá-la pelo mesmo operador |
| MVP-ISSUE-045 | 7 | Concluir ronda somente com todos os pontos resolvidos |
| MVP-ISSUE-046 | 7 | Encerrar execuções incompletas no horário exato do turno |
| MVP-ISSUE-047 | 7 | Padronizar conflitos das transições da ronda |
| MVP-ISSUE-048 | 8 | Criar ocorrência separada da leitura |
| MVP-ISSUE-049 | 8 | Registrar acompanhamento por outro operador |
| MVP-ISSUE-050 | 8 | Resolver ocorrência com evento histórico |
| MVP-ISSUE-051 | 9 | Consultar histórico detalhado de rondas |
| MVP-ISSUE-052 | 9 | Mostrar continuidade do turno em uma visão operacional |
| MVP-ISSUE-053 | 9 | Exibir revisões e eventos em linha do tempo |
| MVP-ISSUE-054 | 10 | Preparar o conjunto fictício reproduzível da demonstração |
| MVP-ISSUE-055 | 10 | Consolidar a experiência responsiva e os erros do fluxo completo |
| MVP-ISSUE-056 | 10 | Documentar e validar a execução local reproduzível |
| MVP-ISSUE-057 | 10 | Gerar e verificar backup local da demonstração |
| MVP-ISSUE-058 | 10 | Restaurar a demonstração em banco separado |
| MVP-ISSUE-059 | 11 | Executar os critérios de sucesso e fechar a baseline entregue |

## D. Ordem e dependências

```text
001 → 002 → 003 → 004 → 005 → 006 → 007
                      └→ 008 → 009 ─┬→ 010 → 011 ─┬→ 012
                                    │             └→ 013
                                    └→ 014 → 015 → 016 ─┬→ 017
                                                        └→ 018

005 → 021
010 → 022 → 023
007 + 021 + 023 → 024 → 025
013 + 025 → 026 ─┬→ 027
                  ├→ 028
                  └→ 029
011 + 026 → 030
007 + 021 + 024 + 025 + 026 → 031
015 + 030 + 031 → 032 → 033
032 → 019 → 020

031 → 034 → 035 → 036 → 037 → 038
034 + 035 → 039 → 040 → 041 → 042 → 043
032 + 035 → 044 → 045 → 046 → 047
038 + 047 → 048 → 049 → 050
043 + 046 + 050 → 051 → 052 → 053
020 + 029 + 053 → 054 → 055 → 056 → 057 → 058 → 059
```

Os ramos indicados como paralelos nas próprias Issues podem avançar após suas dependências. A ordem deliberadamente posiciona algumas Issues de número menor depois de uma ação autoral real: 019 e 020 só conseguem provar preservação e bloqueio após a 032. Isso mantém a numeração agrupada por assunto sem inventar fixtures estruturais para entidades que ainda não existem.

## E. Issues detalhadas

### Regra transversal — dados fictícios durante o desenvolvimento

Cada Issue funcional pode criar somente a fixture ou o dado sintético mínimo necessário aos próprios testes e à sua demonstração observável. Equipamentos/pontos, configuração de turno e valor de medição devem ser fictícios e identificados como exemplos; dados reais da planta não entram no desenvolvimento. Esses dados incrementais não substituem o conjunto final: a MVP-ISSUE-054 consolida o cenário inteiro de forma idempotente e reproduzível. Não se cria uma funcionalidade de seed separada em cada Issue.

### Grupo 0. Baseline e contexto

#### MVP-ISSUE-001 — Incorporar a baseline aprovada ao contexto versionado

**Grupo:** 0. Baseline e contexto.

**Problema:** o Documento Mestre aprovado é externo ao checkout; `docs/11-contexto-atual.md` ainda descreve a Issue #8 como pendente, e a ADR-0004 ainda aparece como proposta apesar do DTO já merged.
**Resultado esperado:** documentação versionada aponta para a baseline, registra pelo equivalente saneado `76b7ca4` o checkpoint observado antes do rewrite e alinha ADR-0004 ao código atual.
**Contexto:** elimina divergência antes de decompor qualquer comportamento técnico.
**Rastreabilidade:** REQ-MVP-001 a REQ-MVP-012; DEC-P-01 a DEC-P-45; ADR-0004; `docs/09-contrato-api-atual.md`; `docs/11-contexto-atual.md`.
**Comportamento atual:** documentação parcialmente desatualizada; código de `EquipmentResponse` existe.
**Mudança esperada:** copiar/incorporar a baseline em local versionado aprovado e corrigir apenas fatos técnicos obsoletos.
**Critérios de aceite:** links internos válidos; ADR-0004 reflete implementação existente; contexto atual distingue baseline, código existente e próximo passo.
**Fora de escopo:** código, migrations, GitHub Issues, alteração das DEC-P.
**Persistência:** nenhuma alteração. **API:** nenhuma alteração. **Interface:** nenhuma alteração. **Segurança:** nenhuma alteração.
**Testes obrigatórios:** verificação de links, diff e consistência textual.
**Evidência de conclusão:** diff exclusivamente documental e mapa de fontes.
**Dependências:** depende de nenhuma; desbloqueia 002–059; pode ser feita em paralelo com nenhuma; bloqueio técnico: nenhum; ADR antes da implementação: não.
**Objetivo de aprendizagem:** diferença entre produto aprovado, evidência de código e histórico documental.
**Perguntas para a sessão de aprendizagem:** Qual fonte governa o escopo? Por que ADR-0004 estava defasada? O que permanece aberto?
**Definition of Done da Issue:** [ ] contexto alinhado [ ] links verificados [ ] nenhum código alterado [ ] PR documental revisável [ ] sessão possível.

### Grupo 1. Contrato de Equipment

#### MVP-ISSUE-002 — Rejeitar textos de Equipment maiores que as colunas

**Grupo:** 1. Contrato de Equipment.

**Problema:** DTO valida presença, mas não os limites de `code`, `name` e `location`; o banco pode responder com erro tardio.
**Resultado esperado:** entradas acima de 50/100/100 caracteres retornam 400 antes da persistência.
**Contexto:** alinha contrato HTTP à V1 sem editar a migration aplicada.
**Rastreabilidade:** REQ-MVP-002, REQ-MVP-011; CS-02, CS-10; ADR-0002, ADR-0004; seção 16.2.
**Comportamento atual:** `@NotBlank`/`@NotNull`; limites não validados no DTO.
**Mudança esperada:** acrescentar validação de tamanho e mensagens por campo.
**Critérios de aceite:** cada campo excedente gera 400; valor no limite é aceito; nada é persistido na falha.
**Fora de escopo:** formato global de erro, duplicidade, normalização, status 201.
**Persistência:** não altera banco. **API:** preserva GET/POST e sucesso 200. **Interface:** sem mudança. **Segurança:** sem mudança.
**Testes obrigatórios:** HTTP parametrizado ou casos focados para os três limites.
**Evidência de conclusão:** suíte verde e respostas 400 demonstradas.
**Dependências:** depende de MVP-ISSUE-001; desbloqueia 003; pode ser feita em paralelo com análise da T-04 (006); bloqueio técnico: nenhum; ADR antes da implementação: não.
**Objetivo de aprendizagem:** Bean Validation versus constraint física.
**Perguntas para a sessão de aprendizagem:** Por que validar antes do banco? Onde os limites vêm? Qual teste evita regressão?
**Definition of Done da Issue:** [ ] validações [ ] testes [ ] contrato documentado [ ] escopo restrito [ ] PR revisável [ ] sessão possível.

#### MVP-ISSUE-003 — Padronizar erros de validação com ProblemDetail

**Grupo:** 1. Contrato de Equipment.

**Problema:** o formato do 400 depende do padrão do Spring e não é contrato estável.
**Resultado esperado:** validações de Equipment retornam `ProblemDetail` com código `VALIDATION_ERROR` e campos úteis.
**Contexto:** cria uma fundação mínima que erros futuros podem reutilizar incrementalmente.
**Rastreabilidade:** REQ-MVP-002, REQ-MVP-011; CS-10; seção 16.3.
**Comportamento atual:** HTTP 400 sem corpo contratado. **Mudança esperada:** tratamento central apenas para validação/desserialização necessária.
**Critérios de aceite:** campo inválido é identificável; JSON é consistente; erro não vira 500 nem HTML.
**Fora de escopo:** mapear todos os erros, autenticação, conflito de código.
**Persistência:** nenhuma. **API:** altera somente corpo de 400. **Interface:** pode exibir mensagem junto ao campo futuramente. **Segurança:** não expor stack trace.
**Testes obrigatórios:** MockMvc para corpo e content type. **Evidência de conclusão:** resposta 400 capturada e suíte verde.
**Dependências:** depende de MVP-ISSUE-002; desbloqueia 004–005 e contratos futuros; pode ser feita em paralelo com análise T-01 (008); bloqueio técnico: nenhum; ADR antes da implementação: não.
**Objetivo de aprendizagem:** exceções MVC e contrato de erro. **Perguntas para a sessão de aprendizagem:** O que torna o erro estável? Por que não capturar toda exceção? O que não deve vazar?
**Definition of Done da Issue:** [ ] handler focado [ ] testes [ ] documento de contrato atualizado [ ] PR pequena [ ] sessão possível.

#### MVP-ISSUE-004 — Remover espaços externos do código de Equipment

**Grupo:** 1. Contrato de Equipment.

**Problema:** códigos com espaços externos são persistidos literalmente.
**Resultado esperado:** novo cadastro aplica `trim()` antes de verificar/persistir o código.
**Contexto:** implementa DEC-P-44 sem renomear dados existentes.
**Rastreabilidade:** REQ-MVP-002; DEC-P-44; CS-02; ADR-0004.
**Comportamento atual:** Service salva o texto recebido. **Mudança esperada:** normalização em regra de aplicação, preservando caixa.
**Critérios de aceite:** `" DEMO-COMP-01 "` é salvo/retornado como `"DEMO-COMP-01"`; código só com espaços continua 400; dados antigos não são alterados.
**Fora de escopo:** uppercase, regex industrial, migração de dados, conflito 409.
**Persistência:** sem migration. **API:** resposta mostra código normalizado. **Interface:** sem requisito próprio. **Segurança:** não aplicável.
**Testes obrigatórios:** Service/HTTP e releitura. **Evidência de conclusão:** POST e GET demonstram o mesmo código normalizado.
**Dependências:** depende de MVP-ISSUE-003; desbloqueia 005; pode ser feita em paralelo com 006 e 008; bloqueio técnico: nenhum; ADR antes da implementação: não.
**Objetivo de aprendizagem:** invariantes e fronteira de normalização. **Perguntas para a sessão de aprendizagem:** Por que no Service? Por que preservar caixa? Por que não alterar V1?
**Definition of Done da Issue:** [ ] trim aplicado [ ] testes [ ] DEC rastreada [ ] sem migração silenciosa [ ] PR revisável [ ] sessão possível.

#### MVP-ISSUE-005 — Retornar 409 para código de Equipment duplicado

**Grupo:** 1. Contrato de Equipment.

**Problema:** a constraint única existe, mas duplicidade pode emergir como 500 sem código estável.
**Resultado esperado:** conflito específico retorna 409 `EQUIPMENT_CODE_CONFLICT`, inclusive após `trim()`.
**Contexto:** diferencia erro do cliente de falha interna e preserva defesa no banco.
**Rastreabilidade:** REQ-MVP-002, REQ-MVP-011; DEC-P-44; CS-02, CS-10; ADR-0002, ADR-0004.
**Comportamento atual:** UNIQUE na V1; sem consulta/resposta específica. **Mudança esperada:** detecção clara e tradução focada da violação concorrente.
**Critérios de aceite:** segundo cadastro normalizado igual retorna 409; primeiro permanece; outra violação inesperada não vira falso conflito.
**Fora de escopo:** atualização, busca, case-insensitive, 201.
**Persistência:** preserva constraint; sem editar V1. **API:** novo erro 409. **Interface:** mensagem mínima quando tela existir. **Segurança:** sem detalhes SQL.
**Testes obrigatórios:** Repository/Testcontainers e MockMvc; caso de colisão após trim. **Evidência de conclusão:** 409 e apenas um registro.
**Dependências:** depende de MVP-ISSUE-004; desbloqueia 006, 008 e 021; pode ser feita em paralelo com nenhuma além dos ramos indicados; bloqueio técnico: nenhum; ADR antes da implementação: não.
**Objetivo de aprendizagem:** constraint, concorrência e HTTP 409. **Perguntas para a sessão de aprendizagem:** Por que consultar e manter UNIQUE? Como distinguir integridade inesperada? Qual teste usa PostgreSQL?
**Definition of Done da Issue:** [ ] conflito tratado [ ] testes [ ] contrato atualizado [ ] sem captura genérica indevida [ ] PR revisável [ ] sessão possível.

#### MVP-ISSUE-006 — Decidir e documentar a transição de Equipment.status

**Grupo:** 1. Contrato de Equipment.

**Problema:** o campo legado `Equipment.status` mistura cadastro com uma condição física sem origem temporal.
**Resultado esperado:** T-04 é resolvida e documentada antes de qualquer alteração de schema, domínio ou contrato.
**Contexto:** a decisão precisa preservar linhas existentes sem inventar que um valor legado é uma observação de ronda.
**Rastreabilidade:** REQ-MVP-002, REQ-MVP-006; CS-05; T-04; seções 11.1, 15.4 e 15.6; ADR futura de T-04.
**Comportamento atual:** `status` é obrigatório na V1 e novos cadastros recebem `STOPPED`. **Mudança esperada:** documentar significado legado, destino/nulabilidade da coluna, compatibilidade, tratamento de linhas existentes, efeito em DTO/API e relação com condição observada.
**Critérios de aceite:** as alternativas e consequências estão registradas; a estratégia escolhida não reclassifica dados antigos nem inventa horários; o efeito no contrato é explícito; a decisão é aprovada antes da Issue 007.
**Fora de escopo:** migration, Java, DTO, endpoint e observação operacional.
**Persistência:** não altera banco. **API:** não altera contrato; apenas documenta o impacto futuro. **Interface:** nenhuma mudança visível. **Segurança:** nenhuma mudança funcional.
**Testes obrigatórios:** revisão de consistência contra V1, entidade, DTO e exemplos históricos. **Evidência de conclusão:** ADR/refinamento aprovado com matriz de compatibilidade.
**Dependências:** depende de MVP-ISSUE-005; desbloqueia 007; pode ser feita em paralelo com 008; bloqueio técnico: T-04; ADR antes da implementação: sim, registrar/aprovar a decisão duradoura.
**Objetivo de aprendizagem:** separar decisão arquitetural de execução. **Perguntas para a sessão de aprendizagem:** Que fato o campo legado representa? Qual opção preserva dados antigos? O DTO deve manter compatibilidade? Por que condição observada precisa de horário?
**Definition of Done da Issue:** [ ] T-04 decidida [ ] impacto em schema/domínio/API registrado [ ] dados legados tratados [ ] ADR aprovada quando aplicável [ ] nenhum código alterado [ ] sessão realizada.

#### MVP-ISSUE-007 — Implementar a estratégia aprovada para Equipment.status

**Grupo:** 1. Contrato de Equipment.

**Problema:** o cadastro continua aplicando a semântica legada enquanto T-04 aprovada ainda não foi implementada.
**Resultado esperado:** cadastro, persistência e contrato passam a seguir exatamente a decisão arquitetural da Issue 006.
**Contexto:** esta Issue executa uma decisão já revisada; não escolhe outra estratégia durante a implementação.
**Rastreabilidade:** REQ-MVP-002, REQ-MVP-006, REQ-MVP-011; CS-05, CS-10; T-04; ADR aprovada na MVP-ISSUE-006.
**Comportamento atual:** a entidade, V1, Service e DTO representam o comportamento legado. **Mudança esperada:** aplicar migration evolutiva quando aprovada, ajustar domínio, Service e DTO/API e preservar compatibilidade nos limites decididos.
**Critérios de aceite:** novo cadastro não presume condição física; linhas antigas permanecem interpretáveis; GET/POST seguem o contrato aprovado; evolução desde V1 é testada; nenhum arquivo V1 é editado.
**Fora de escopo:** inventar condição passada, criar observação de ronda, rever T-04 ou adicionar estado industrial.
**Persistência:** exige nova migration somente se a decisão aprovada exigir; nunca edita V1. **API:** ajusta apenas os contratos definidos na ADR. **Interface:** rótulos deixam clara a ausência de condição observada. **Segurança:** não aplicável além de não expor detalhes internos em erro.
**Testes obrigatórios:** migration desde V1, Repository, Service e HTTP. **Evidência de conclusão:** cenários de cadastro/releitura e evolução do schema executados.
**Dependências:** depende de MVP-ISSUE-006; desbloqueia 024, 031 e 038; pode ser feita em paralelo com 008–020; bloqueio técnico: decisão T-04 da 006; ADR antes da implementação: já aprovada na 006.
**Objetivo de aprendizagem:** implementar uma ADR com migração compatível. **Perguntas para a sessão de aprendizagem:** Como a migration preserva as linhas? Qual teste comprova compatibilidade? Onde cadastro e condição se separam?
**Definition of Done da Issue:** [ ] decisão da 006 respeitada [ ] migration quando necessária [ ] domínio/API ajustados [ ] testes passando [ ] docs atualizados [ ] PR pequena [ ] sessão possível.

### Grupo 2. Acesso e identidade mínimos

#### MVP-ISSUE-008 — Decidir e documentar a arquitetura de acesso T-01

**Grupo:** 2. Acesso e identidade mínimos.

**Problema:** a baseline propõe mesma origem e sessão, mas persistência de credenciais, cookies, CSRF, 401/403 e revogação ainda não foram fechados.
**Resultado esperado:** T-01 produz uma arquitetura revisada que as Issues de acesso implementarão sem decisões arquiteturais implícitas.
**Contexto:** administração, perfil compartilhado e identidade por PIN são conceitos aprovados, mas precisam de uma fronteira técnica comum.
**Rastreabilidade:** REQ-MVP-001, REQ-MVP-010, REQ-MVP-011; DEC-P-07, DEC-P-18, DEC-P-25, DEC-P-26; CS-01, CS-10, CS-12; T-01; seção 15.2/15.6.
**Comportamento atual:** não existe autenticação nem frontend da MVP. **Mudança esperada:** definir mesma origem, HTML/CSS/JS servido pela aplicação, sessão no servidor, autenticação, persistência administrativa, cookie, CSRF, 401/403 e revogação dos perfis.
**Critérios de aceite:** o desenho cobre todos os pontos; diferencia administrador, perfil de turno e identidade; explica expiração/revogação; registra alternativas; ADR é aprovada se a escolha for duradoura.
**Fora de escopo:** Spring Security, login funcional, telas, credenciais reais, JWT, Redis ou provedor externo.
**Persistência:** não altera banco. **API:** não cria endpoints; define responsabilidades e códigos de resposta. **Interface:** documenta o fluxo de telas sem implementá-lo. **Segurança:** ameaças e proteções proporcionais ficam registradas.
**Testes obrigatórios:** revisão de cenários anônimo/admin/turno, CSRF, logout, expiração e revogação. **Evidência de conclusão:** diagrama e ADR/refinamento T-01 aprovados.
**Dependências:** depende de MVP-ISSUE-003, MVP-ISSUE-005; desbloqueia 009–020; pode ser feita em paralelo com 021–023; bloqueio técnico: T-01; ADR antes da implementação: sim, se a decisão for duradoura.
**Objetivo de aprendizagem:** modelar autenticação antes de configurar filtros. **Perguntas para a sessão de aprendizagem:** Quem a sessão identifica? Como 401 difere de 403? Como CSRF protege? Como a desativação revoga sessões?
**Definition of Done da Issue:** [ ] T-01 decidida [ ] ameaças e fluxos cobertos [ ] ADR aprovada quando aplicável [ ] nenhum código alterado [ ] sessão realizada.

#### MVP-ISSUE-009 — Entregar o login e logout administrativo mínimo conforme T-01

**Grupo:** 2. Acesso e identidade mínimos.

**Problema:** não existe acesso administrativo protegido.
**Resultado esperado:** um administrador consegue entrar, manter sessão, acessar área protegida e sair conforme T-01 aprovada.
**Contexto:** é o primeiro comportamento funcional de segurança e a base dos cadastros administrativos.
**Rastreabilidade:** REQ-MVP-001, REQ-MVP-010, REQ-MVP-011; CS-01, CS-10, CS-12; T-01; ADR aprovada na MVP-ISSUE-008.
**Comportamento atual:** NÃO EXISTE. **Mudança esperada:** configurar Spring Security, credencial administrativa, login/logout, sessão, cookie, CSRF e respostas mínimas sem ampliar a arquitetura.
**Critérios de aceite:** anônimo não acessa administração; credencial válida abre sessão; inválida falha sem detalhes; logout invalida; CSRF protege mutações; segredo não entra no Git/logs.
**Fora de escopo:** perfil de turno, PIN, gestão completa de administradores, OAuth/JWT.
**Persistência:** segue a decisão T-01; migration somente se já aprovada nela. **API:** implementa contratos de entrada, saída e erros definidos. **Interface:** formulário e saída mínimos. **Segurança:** Spring Security, hash, sessão, cookie e CSRF conforme ADR.
**Testes obrigatórios:** Spring Security/MockMvc para anônimo, login, falha, logout e CSRF; smoke de interface. **Evidência de conclusão:** fluxo executado no Chrome e suíte verde.
**Dependências:** depende de MVP-ISSUE-008; desbloqueia 010, 014 e 042; pode ser feita em paralelo com 021; bloqueio técnico: decisão T-01 da 008; ADR antes da implementação: já aprovada na 008.
**Objetivo de aprendizagem:** filtros de segurança, sessão e CSRF. **Perguntas para a sessão de aprendizagem:** Qual filtro autentica? Onde a sessão vive? Por que logout invalida? Qual teste prova CSRF?
**Definition of Done da Issue:** [ ] T-01 respeitada [ ] login/logout [ ] proteção mínima [ ] testes [ ] segredo fora do Git [ ] docs [ ] PR pequena [ ] sessão possível.

#### MVP-ISSUE-010 — Cadastrar um perfil compartilhado de turno

**Grupo:** 2. Acesso e identidade mínimos.

**Problema:** o administrador não consegue criar o perfil usado pela equipe do turno.
**Resultado esperado:** perfil nasce ativo, com nome e senha obrigatórios, e pode ser consultado administrativamente.
**Contexto:** representa acesso coletivo; não representa operador nem ocorrência diária do turno.
**Rastreabilidade:** REQ-MVP-001, REQ-MVP-003; DEC-P-07, DEC-P-20, DEC-P-30, DEC-P-31, DEC-P-32; CS-01.
**Comportamento atual:** NÃO EXISTE. **Mudança esperada:** cadastro autorizado com nome único conforme regra refinada, senha protegida e estado ativo inicial.
**Critérios de aceite:** administrador cria perfil válido; senha ausente é rejeitada; usuário de turno ou anônimo não cria; resposta nunca devolve hash/senha.
**Fora de escopo:** horário, recorrência, login, identidades e escala 6x2.
**Persistência:** exige nova migration, unicidade/índices definidos no refinamento. **API:** cadastro e consulta administrativos mínimos. **Interface:** formulário administrativo simples. **Segurança:** papel administrativo e hash da senha.
**Testes obrigatórios:** validação, autorização, Repository/Testcontainers e HTTP. **Evidência de conclusão:** cadastro e releitura sem segredo.
**Dependências:** depende de MVP-ISSUE-009; desbloqueia 011–013 e 022; pode ser feita em paralelo com 014; bloqueio técnico: nenhum; ADR antes da implementação: não.
**Objetivo de aprendizagem:** entidade de acesso e hash de credencial. **Perguntas para a sessão de aprendizagem:** Por que perfil não é operador? O que o banco deve garantir? Por que o hash não aparece?
**Definition of Done da Issue:** [ ] migration [ ] regra/autorização [ ] testes [ ] contrato/docs [ ] PR pequena [ ] sessão possível.

#### MVP-ISSUE-011 — Entrar pelo perfil compartilhado do turno

**Grupo:** 2. Acesso e identidade mínimos.

**Problema:** a equipe ainda não consegue iniciar uma sessão pelo perfil ativo.
**Resultado esperado:** a tela lista perfis ativos e abre sessão do turno após senha válida em cada nova sessão.
**Contexto:** reduz atrito operacional sem atribuir ações automaticamente a uma pessoa.
**Rastreabilidade:** REQ-MVP-001, REQ-MVP-010; DEC-P-07, DEC-P-18, DEC-P-31; CS-01; T-01.
**Comportamento atual:** NÃO EXISTE. **Mudança esperada:** seleção de perfil ativo, autenticação por senha e contexto de sessão com id do perfil.
**Critérios de aceite:** somente ativos aparecem; senha correta abre sessão; senha errada não revela detalhes; fechar/iniciar nova sessão exige senha novamente; sessão não contém autoria individual.
**Fora de escopo:** lembrar senha, PIN, programação e troca automática de turno.
**Persistência:** sem nova tabela além de mecanismo de sessão escolhido. **API:** lista pública mínima e entrada/saída do turno. **Interface:** seleção, senha e feedback. **Segurança:** limitação básica de exposição e CSRF no login conforme T-01.
**Testes obrigatórios:** Spring Security/HTTP e interface do fluxo principal. **Evidência de conclusão:** login e logout demonstrados.
**Dependências:** depende de MVP-ISSUE-010; desbloqueia 012–013, 015 e 030; pode ser feita em paralelo com 014; bloqueio técnico: nenhum; ADR antes da implementação: não.
**Objetivo de aprendizagem:** principal compartilhado e contexto de sessão. **Perguntas para a sessão de aprendizagem:** O que a sessão identifica? Por que isso não prova autoria? Por que perfis inativos somem?
**Definition of Done da Issue:** [ ] login de turno [ ] testes [ ] interface mínima [ ] sem autoria implícita [ ] docs [ ] PR revisável [ ] sessão possível.

#### MVP-ISSUE-012 — Alterar a senha de um perfil sem derrubar sessões existentes

**Grupo:** 2. Acesso e identidade mínimos.

**Problema:** a credencial compartilhada não possui manutenção administrativa.
**Resultado esperado:** administrador troca a senha para novas entradas, preservando sessões já abertas.
**Contexto:** aplica deliberadamente o ciclo definido pela DEC-P-25.
**Rastreabilidade:** REQ-MVP-001, REQ-MVP-011; DEC-P-25, DEC-P-32; CS-01, CS-10.
**Comportamento atual:** NÃO EXISTE. **Mudança esperada:** troca autorizada do hash; nova senha vale em autenticações posteriores.
**Critérios de aceite:** antiga falha após troca; nova funciona; sessão anterior continua válida; segredo não é registrado.
**Fora de escopo:** recuperação automática, senha temporária, invalidação geral.
**Persistência:** atualiza hash e, se adotado, metadado de alteração; sem histórico do segredo. **API:** contratos afetados conforme esta Issue. **Interface:** ação administrativa focada. **Segurança:** autorização e CSRF.
**Testes obrigatórios:** integração de duas sessões e HTTP. **Evidência de conclusão:** sequência antes/depois demonstrada.
**Dependências:** depende de MVP-ISSUE-011; desbloqueia manutenção da credencial; pode ser feita em paralelo com 014–018; bloqueio técnico: nenhum; ADR antes da implementação: não.
**Objetivo de aprendizagem:** credenciais versus sessões existentes. **Perguntas para a sessão de aprendizagem:** Por que a sessão sobrevive? O que muda no banco? Qual teste prova as duas sessões?
**Definition of Done da Issue:** [ ] troca segura [ ] regra de sessão testada [ ] docs [ ] PR pequena [ ] sessão possível.

#### MVP-ISSUE-013 — Desativar perfil e invalidar suas sessões imediatamente

**Grupo:** 2. Acesso e identidade mínimos.

**Problema:** não há como retirar acesso de um perfil comprometido ou inativo.
**Resultado esperado:** desativação administrativa bloqueia novos logins e todas as requisições de sessões abertas daquele perfil.
**Contexto:** aplica DEC-P-24, DEC-P-26 sem apagar histórico.
**Rastreabilidade:** REQ-MVP-001, REQ-MVP-003, REQ-MVP-011; DEC-P-24, DEC-P-26; CS-01, CS-10; T-01.
**Comportamento atual:** NÃO EXISTE. **Mudança esperada:** estado inativo é verificado também após autenticação, com invalidação imediata coerente.
**Critérios de aceite:** perfil inativo some da seleção; sessão aberta perde acesso na próxima requisição; histórico permanece; administrador continua capaz de consultar o perfil.
**Fora de escopo:** reativação da programação, exclusão, operadores.
**Persistência:** usa estado do perfil e mecanismo de invalidação definido em T-01; possível índice. **API:** contratos afetados conforme esta Issue. **Interface:** ação administrativa e erro de sessão inválida. **Segurança:** revogação e CSRF.
**Testes obrigatórios:** duas sessões, desativação e tentativa imediata; integração/HTTP. **Evidência de conclusão:** sessão perde acesso sem reiniciar aplicação.
**Dependências:** depende de MVP-ISSUE-011; desbloqueia 026–029; pode ser feita em paralelo com 012 e 014–018; bloqueio técnico: estratégia de revogação definida em T-01; ADR antes da implementação: não.
**Objetivo de aprendizagem:** revogação de sessão. **Perguntas para a sessão de aprendizagem:** Por que checar após login? O que “imediatamente” significa em HTTP? Onde preservar histórico?
**Definition of Done da Issue:** [ ] desativação [ ] revogação testada [ ] histórico preservado [ ] docs [ ] PR revisável [ ] sessão possível.

#### MVP-ISSUE-014 — Cadastrar identidade de operador com PIN

**Grupo:** 2. Acesso e identidade mínimos.

**Problema:** ações pessoais ainda não podem ter autor individual.
**Resultado esperado:** administrador cria identidade ativa com nome e PIN curto obrigatório, armazenado com proteção adequada.
**Contexto:** separa acesso coletivo do turno de autoria individual.
**Rastreabilidade:** REQ-MVP-001, REQ-MVP-009, REQ-MVP-011; DEC-P-07, DEC-P-09, DEC-P-19, DEC-P-37, DEC-P-38; CS-01, CS-08, CS-10.
**Comportamento atual:** NÃO EXISTE. **Mudança esperada:** identidade nasce ativa e pode ser listada pelo administrador sem expor PIN.
**Critérios de aceite:** somente admin cria; nome/PIN inválidos são rejeitados; PIN/hash nunca é retornado; identidade ativa fica disponível para uso futuro.
**Fora de escopo:** login individual, associação fixa a turno, tentativa de PIN, autoria de ronda.
**Persistência:** nova migration; hash do PIN; constraint para identidade conforme refinamento. **API:** contratos afetados conforme esta Issue. **Interface:** cadastro administrativo mínimo. **Segurança:** autorização, hash e não exposição.
**Testes obrigatórios:** validação, autorização, persistência PostgreSQL e HTTP. **Evidência de conclusão:** criação/releitura sem segredo.
**Dependências:** depende de MVP-ISSUE-009; desbloqueia 015–020 e 032; pode ser feita em paralelo com 010–013 e 021–023; bloqueio técnico: nenhum; ADR antes da implementação: não.
**Objetivo de aprendizagem:** identidade, segredo curto e defesa no armazenamento. **Perguntas para a sessão de aprendizagem:** Por que PIN não é login? Como protegê-lo? Qual limite pertence ao contrato?
**Definition of Done da Issue:** [ ] migration [ ] cadastro [ ] testes [ ] segredo protegido [ ] docs [ ] PR revisável [ ] sessão possível.

#### MVP-ISSUE-015 — Confirmar identidade ativa por seleção e PIN

**Grupo:** 2. Acesso e identidade mínimos.

**Problema:** a sessão compartilhada ainda não consegue atribuir uma ação a uma pessoa.
**Resultado esperado:** dentro de uma sessão de turno, o usuário seleciona nome ativo, informa PIN e recebe confirmação de autoria limitada à ação/fluxo definido.
**Contexto:** implementa a ergonomia aprovada sem criar login individual.
**Rastreabilidade:** REQ-MVP-001, REQ-MVP-004, REQ-MVP-007, REQ-MVP-009; DEC-P-07, DEC-P-09, DEC-P-41; CS-01, CS-02, CS-07, CS-08.
**Comportamento atual:** NÃO EXISTE. **Mudança esperada:** lista apenas ativos, valida PIN e fornece identidade verificada sem persistir PIN no cliente.
**Critérios de aceite:** ativos aparecem; inativos não; PIN correto confirma id/nome; incorreto falha genericamente; recarregar não inventa autoria.
**Fora de escopo:** bloqueio por tentativas, assumir ronda, correção e ocorrência.
**Persistência:** pode exigir registro seguro de tentativas/última verificação conforme refinamento; não guarda PIN puro. **API:** contratos afetados conforme esta Issue. **Interface:** lista e confirmação mínima. **Segurança:** sessão de turno obrigatória, resposta genérica, CSRF.
**Testes obrigatórios:** segurança/HTTP e interface; PIN válido/inválido/inativo. **Evidência de conclusão:** seleção e confirmação demonstradas.
**Dependências:** depende de MVP-ISSUE-011, MVP-ISSUE-014; desbloqueia 016–020, 032 e 048–050; pode ser feita em paralelo com 022–023; bloqueio técnico: T-01 aprovada; ADR antes da implementação: não.
**Objetivo de aprendizagem:** autenticação contextual e autoria. **Perguntas para a sessão de aprendizagem:** O que o PIN prova? Por quanto tempo vale? Por que não é usuário de login?
**Definition of Done da Issue:** [ ] confirmação [ ] testes [ ] interface [ ] sem segredo exposto [ ] docs [ ] PR revisável [ ] sessão possível.

#### MVP-ISSUE-016 — Bloquear identidade após cinco PINs inválidos

**Grupo:** 2. Acesso e identidade mínimos.

**Problema:** tentativas de PIN não têm limite e a política de acumulação antes do quinto erro ainda não foi decidida.
**Resultado esperado:** após refinamento explícito, a quinta tentativa inválida aplicável bloqueia a identidade até redefinição administrativa.
**Contexto:** DEC-P-19 define o limite e o desbloqueio, mas não define se um sucesso anterior ao quinto erro zera falhas acumuladas.
**Rastreabilidade:** REQ-MVP-001, REQ-MVP-011; DEC-P-19, DEC-P-39; CS-01, CS-10.
**Comportamento atual:** NÃO EXISTE. **Mudança esperada:** implementar contagem e bloqueio transacionais somente após decidir a regra de acumulação, sem presumir reset por sucesso nem o comportamento contrário.
**Critérios de aceite:** cinco tentativas inválidas segundo a política aprovada bloqueiam; identidade bloqueada não confirma PIN; só redefinição administrativa desbloqueia; concorrência não contorna o limite; sucesso segue exatamente o refinamento registrado.
**Fora de escopo:** tempo de desbloqueio, rate limit global, autoatendimento ou decidir silenciosamente a regra pendente.
**Persistência:** exige estado/contador ou tabela de segurança e controle concorrente conforme refinamento. **API:** erro estável sem revelar o PIN. **Interface:** aviso de bloqueio sem expor informação desnecessária. **Segurança:** tentativas e estado protegidos; PIN nunca registrado.
**Testes obrigatórios:** integração PostgreSQL, HTTP e concorrência focada, incluindo o cenário de sucesso antes do quinto erro conforme a decisão futura. **Evidência de conclusão:** sequência de tentativas, bloqueio e desbloqueio administrativo demonstrada.
**REFINAMENTO DE PRODUTO/TÉCNICO NECESSÁRIO ANTES DA IMPLEMENTAÇÃO:** definir se uma confirmação válida de PIN antes do quinto erro zera as tentativas inválidas acumuladas ou se a contagem permanece até outra condição. Não criar DEC-P nesta etapa.
**Dependências:** depende de MVP-ISSUE-015; desbloqueia 017–018; pode ser feita em paralelo com 12–13; bloqueio técnico: regra de acumulação de tentativas pendente; ADR antes da implementação: não; exige refinamento de produto/técnico.
**Objetivo de aprendizagem:** política de tentativas e contador transacional. **Perguntas para a sessão de aprendizagem:** Uma confirmação válida de PIN antes do quinto erro zera as tentativas inválidas acumuladas, ou a contagem permanece até alguma outra condição? O que a quinta falha altera? Qual teste cobre concorrência?
**Definition of Done da Issue:** [ ] refinamento aprovado [ ] nenhuma regra implícita [ ] cinco erros bloqueiam [ ] redefinição desbloqueia [ ] concorrência testada [ ] docs [ ] PR revisável.

#### MVP-ISSUE-017 — Redefinir PIN e reativar identidade bloqueada

**Grupo:** 2. Acesso e identidade mínimos.

**Problema:** identidade bloqueada não possui recuperação administrativa.
**Resultado esperado:** administrador define novo PIN; contador é zerado e identidade volta ativa.
**Contexto:** DEC-P-39 torna redefinição o caminho de recuperação.
**Rastreabilidade:** REQ-MVP-001, REQ-MVP-011; DEC-P-39, DEC-P-40; CS-01, CS-10.
**Comportamento atual:** NÃO EXISTE. **Mudança esperada:** ação administrativa troca hash e reativa; operador não altera o próprio PIN.
**Critérios de aceite:** admin redefine; operador/sessão de turno não; PIN antigo falha; novo funciona; histórico permanece.
**Fora de escopo:** PIN temporário, autoatendimento, envio de segredo.
**Persistência:** atualiza hash/estado/contador; preserva referências históricas. **API:** contratos afetados conforme esta Issue. **Interface:** ação administrativa focada. **Segurança:** autorização e CSRF.
**Testes obrigatórios:** autorização e ciclo bloquear→redefinir→confirmar. **Evidência de conclusão:** fluxo completo demonstrado.
**Dependências:** depende de MVP-ISSUE-016; desbloqueia recuperação de identidade; pode ser feita em paralelo com 018; bloqueio técnico: nenhum; ADR antes da implementação: não.
**Objetivo de aprendizagem:** recuperação segura de credencial. **Perguntas para a sessão de aprendizagem:** O que deve ser zerado? Por que o operador não troca? O histórico muda?
**Definition of Done da Issue:** [ ] redefinição [ ] autorização [ ] testes [ ] histórico preservado [ ] docs [ ] PR revisável [ ] sessão possível.

#### MVP-ISSUE-018 — Bloquear e reativar identidade manualmente

**Grupo:** 2. Acesso e identidade mínimos.

**Problema:** administrador não controla disponibilidade de uma identidade sem trocar o PIN.
**Resultado esperado:** bloqueio manual impede confirmação; reativação restaura o uso conforme regra aprovada.
**Contexto:** cobre afastamento ou risco operacional sem excluir autoria passada.
**Rastreabilidade:** REQ-MVP-001, REQ-MVP-011; DEC-P-33, DEC-P-34; CS-01, CS-10.
**Comportamento atual:** NÃO EXISTE. **Mudança esperada:** comandos administrativos idempotentes com estado claro e histórico preservado.
**Critérios de aceite:** bloqueada some da lista ativa e não valida PIN; reativada volta; repetir comando não corrompe estado.
**Fora de escopo:** apagar ações, trocar PIN automaticamente, vincular a turnos.
**Persistência:** usa estado da identidade; preserva FKs. **API:** contratos afetados conforme esta Issue. **Interface:** ações administrativas. **Segurança:** somente admin.
**Testes obrigatórios:** autorização, transições e HTTP. **Evidência de conclusão:** bloquear/reativar pela tela.
**Dependências:** depende de MVP-ISSUE-015, MVP-ISSUE-016; desbloqueia gestão da identidade; pode ser feita em paralelo com 017; bloqueio técnico: nenhum; ADR antes da implementação: não.
**Objetivo de aprendizagem:** ciclo de vida e operações idempotentes. **Perguntas para a sessão de aprendizagem:** Bloqueio manual e automático são iguais? O que uma repetição deve retornar?
**Definition of Done da Issue:** [ ] transições [ ] testes [ ] interface [ ] histórico preservado [ ] docs [ ] PR revisável [ ] sessão possível.

#### MVP-ISSUE-019 — Renomear identidade preservando autoria histórica

**Grupo:** 2. Acesso e identidade mínimos.

**Problema:** alterar o nome atual pode reescrever a forma como ações passadas são apresentadas.
**Resultado esperado:** novas ações usam o novo nome e registros históricos continuam exibindo o nome existente quando foram realizados.
**Contexto:** DEC-P-35 protege a compreensão da autoria sem impedir manutenção cadastral.
**Rastreabilidade:** REQ-MVP-001, REQ-MVP-008, REQ-MVP-009; DEC-P-35; CS-01, CS-08.
**Comportamento atual:** a identidade possui nome atual; após a primeira ação autoral não há fluxo de renomeação seguro. **Mudança esperada:** renomear administrativamente e usar o snapshot de autor já definido para não alterar ações existentes.
**Critérios de aceite:** admin renomeia; ação anterior mostra nome antigo; ação posterior mostra novo; id da identidade permanece; outro perfil não renomeia.
**Fora de escopo:** exclusão, fusão, reatribuição de autoria ou alteração do texto histórico.
**Persistência:** atualiza nome cadastral; preserva snapshots/referências históricas. **API:** ação administrativa focada. **Interface:** formulário/lista administrativos mínimos. **Segurança:** somente administrador e CSRF.
**Testes obrigatórios:** integração antes/depois, HTTP/Security e consulta histórica. **Evidência de conclusão:** duas ações da mesma identidade exibem os nomes de seus respectivos momentos.
**Dependências:** depende de MVP-ISSUE-015, MVP-ISSUE-032; desbloqueia 020 e histórico; pode ser feita em paralelo com 27–29; bloqueio técnico: snapshot de autoria T-02; ADR antes da implementação: não.
**Objetivo de aprendizagem:** identidade estável e snapshot de autoria. **Perguntas para a sessão de aprendizagem:** Por que o id não muda? Onde o nome histórico vive? Qual consulta prova a preservação?
**Definition of Done da Issue:** [ ] renomeação [ ] histórico preservado [ ] autorização [ ] testes [ ] docs [ ] PR pequena [ ] sessão possível.

#### MVP-ISSUE-020 — Excluir identidade somente quando nunca utilizada

**Grupo:** 2. Acesso e identidade mínimos.

**Problema:** exclusão física pode destruir ou invalidar referências de autoria.
**Resultado esperado:** somente identidade sem ação registrada pode ser excluída; nos demais casos a API retorna conflito e orienta bloqueio.
**Contexto:** DEC-P-36 reserva bloqueio para identidades que já possuem histórico.
**Rastreabilidade:** REQ-MVP-001, REQ-MVP-008, REQ-MVP-009, REQ-MVP-011; DEC-P-33, DEC-P-36; CS-01, CS-08, CS-10.
**Comportamento atual:** não existe exclusão protegida por ações autorais. **Mudança esperada:** implementar verificação de uso e FKs sem cascade destrutivo; permitir exclusão apenas quando nenhuma relação histórica existir.
**Critérios de aceite:** identidade nunca usada é excluída; identidade autora recebe 409; nenhum registro é apagado; interface oferece bloqueio como alternativa.
**Fora de escopo:** anonimização, cascade, apagar ações, fundir identidades.
**Persistência:** FKs restritivas e consulta/constraint de uso; preservação obrigatória. **API:** ação administrativa e conflito estável. **Interface:** confirmação e orientação para bloquear. **Segurança:** somente administrador e CSRF.
**Testes obrigatórios:** PostgreSQL com identidade usada/não usada, HTTP/Security e ausência de cascade. **Evidência de conclusão:** tentativa de exclusão mantém a ação histórica intacta.
**Dependências:** depende de MVP-ISSUE-019, MVP-ISSUE-032; desbloqueia integridade histórica; pode ser feita em paralelo com 27–29; bloqueio técnico: nenhum; ADR antes da implementação: não.
**Objetivo de aprendizagem:** integridade referencial e exclusão segura. **Perguntas para a sessão de aprendizagem:** Qual FK protege o histórico? Por que bloquear é diferente de excluir? Por que 409?
**Definition of Done da Issue:** [ ] exclusão restrita [ ] bloqueio orientado [ ] FKs [ ] testes [ ] docs [ ] PR pequena [ ] sessão possível.

### Grupo 3. Preparação da ronda

#### MVP-ISSUE-021 — Cadastrar pontos de medição e ordenar o roteiro

**Grupo:** 3. Preparação da ronda.

**Problema:** Equipment não possui pontos coletáveis nem uma ordem de ronda.
**Resultado esperado:** administrador cadastra pontos ativos com nome, tipo de resultado e unidade esperada, e define uma ordem reproduzível para os equipamentos fictícios.
**Contexto:** prepara dois compressores e um recipiente, incluindo pressão em todos e frequência em um compressor.
**Rastreabilidade:** REQ-MVP-002, REQ-MVP-003; DEC-P-02, DEC-P-14, DEC-P-15; CS-02; ADR-0001; seção 11.1.
**Comportamento atual:** NÃO EXISTE. **Mudança esperada:** ponto pertence a Equipment e o roteiro fornece ordenação; administração e consulta mínimas.
**Critérios de aceite:** pontos obrigatórios podem ser cadastrados; unidade/tipo inválido é rejeitado; ordem é estável; equipamento inexistente falha; recipiente participa do roteiro.
**Fora de escopo:** valores reais, alarmes, fórmulas, leitura e escala 6x2.
**Persistência:** nova migration, FKs, unicidade e índice de ordenação. **API:** contratos administrativos/consulta a refinar. **Interface:** formulário/lista mínimos. **Segurança:** escrita administrativa; leitura conforme sessão.
**Testes obrigatórios:** Repository/Testcontainers, HTTP e ordenação. **Evidência de conclusão:** roteiro consultado com os pontos previstos.
**Dados sintéticos mínimos:** usar apenas os equipamentos/pontos fictícios necessários para demonstrar cadastro e ordem; não usar dados reais nem criar um seed funcional separado.
**Dependências:** depende de MVP-ISSUE-005; desbloqueia 024, 031, 034 e 054; pode ser feita em paralelo com 008–020; bloqueio técnico: T-04 antes da condição observada; ADR antes da implementação: não.
**Objetivo de aprendizagem:** relacionamento JPA e integridade referencial. **Perguntas para a sessão de aprendizagem:** Quem é dono do ponto? Onde garantir ordem? Por que unidade pertence ao contexto?
**Definition of Done da Issue:** [ ] migration [ ] cadastro/ordem [ ] testes [ ] docs [ ] PR pequena [ ] sessão possível.

#### MVP-ISSUE-022 — Configurar horário e quantidade coletiva de rondas

**Grupo:** 3. Preparação da ronda.

**Problema:** perfil de turno não possui programação reutilizável.
**Resultado esperado:** administrador define nome operacional, início, término e quantidade positiva de rondas diárias; término pode cair no dia seguinte.
**Contexto:** cada perfil possui configuração recorrente, sem criação manual diária nem escala 6x2.
**Rastreabilidade:** REQ-MVP-003; DEC-P-05, DEC-P-06, DEC-P-08, DEC-P-16, DEC-P-20, DEC-P-21; CS-02.
**Comportamento atual:** NÃO EXISTE. **Mudança esperada:** programação associada ao perfil, interpretação explícita de travessia da meia-noite e validação.
**Critérios de aceite:** configuração válida é relida; quantidade zero/negativa é rejeitada; início e término iguais são rejeitados; turno noturno mantém o perfil e a data operacional definida no refinamento.
**Fora de escopo:** operador fixo, intervalos individuais, 6x2, feriados.
**Persistência:** nova migration ou evolução da tabela do perfil; constraints básicas. **API:** contratos afetados conforme esta Issue. **Interface:** edição administrativa mínima. **Segurança:** somente admin.
**Testes obrigatórios:** unidade de cálculo temporal, PostgreSQL e HTTP. **Evidência de conclusão:** um turno diurno e um noturno exibidos.
**Dados sintéticos mínimos:** usar uma configuração fictícia mínima de turno para testes e demonstração da própria Issue; ela não substitui o dataset final.
**Dependências:** depende de MVP-ISSUE-010; desbloqueia 023–026; pode ser feita em paralelo com 014–020 e 021; bloqueio técnico: nenhum; ADR antes da implementação: não.
**Objetivo de aprendizagem:** intervalos que cruzam datas. **Perguntas para a sessão de aprendizagem:** Qual data identifica o turno noturno? Como calcular término? O que a quantidade representa?
**Definition of Done da Issue:** [ ] programação [ ] travessia testada [ ] validações [ ] docs [ ] PR revisável [ ] sessão possível.

#### MVP-ISSUE-023 — Impedir sobreposição na programação do mesmo perfil

**Grupo:** 3. Preparação da ronda.

**Problema:** alterações podem criar intervalos simultâneos para um mesmo perfil.
**Resultado esperado:** sobreposição é rejeitada com conflito claro, inclusive atravessando meia-noite.
**Contexto:** protege a interpretação do perfil sem impor restrição entre perfis diferentes.
**Rastreabilidade:** REQ-MVP-003, REQ-MVP-011; DEC-P-17, DEC-P-21; CS-02, CS-10.
**Comportamento atual:** NÃO EXISTE. **Mudança esperada:** regra de intervalo no Service e defesa persistente viável conforme refinamento.
**Critérios de aceite:** intervalos sobrepostos do mesmo perfil retornam 409; limites apenas encostados seguem regra explicitada; perfis diferentes podem coincidir; caso noturno é coberto.
**Fora de escopo:** impedir sobreposição entre perfis, escala de operadores.
**Persistência:** possível constraint PostgreSQL ou controle transacional; índice se necessário. **API:** contratos afetados conforme esta Issue. **Interface:** erro de conflito junto ao formulário. **Segurança:** somente admin altera.
**Testes obrigatórios:** unidade de intervalos, integração PostgreSQL e HTTP. **Evidência de conclusão:** matriz de casos e erro visível.
**Dependências:** depende de MVP-ISSUE-022; desbloqueia 024–026; pode ser feita em paralelo com 14–20; bloqueio técnico: nenhum; constraint deve ser refinada localmente; ADR antes da implementação: condicional apenas se a solução for transversal.
**Objetivo de aprendizagem:** intervalos, invariantes e concorrência. **Perguntas para a sessão de aprendizagem:** O que é sobreposição? Por que perfis diferentes podem coincidir? O banco ajuda como?
**Definition of Done da Issue:** [ ] regra [ ] casos noturnos [ ] concorrência adequada [ ] docs [ ] PR revisável [ ] sessão possível.

#### MVP-ISSUE-024 — Decidir e documentar o contexto congelado da ronda T-02

**Grupo:** 3. Preparação da ronda.

**Problema:** sem uma decisão arquitetural, mudanças de cadastro podem reinterpretar turnos e execuções existentes.
**Resultado esperado:** T-02 define o snapshot mínimo e sua vigência antes das implementações que preservam contexto.
**Contexto:** a baseline exige histórico interpretável e evita tanto referência mutável quanto duplicação excessiva.
**Rastreabilidade:** REQ-MVP-003, REQ-MVP-004, REQ-MVP-005, REQ-MVP-006, REQ-MVP-008; DEC-P-11, DEC-P-28, DEC-P-35; CS-02, CS-03, CS-05, CS-08; T-02; ADR-0005 relacionada.
**Comportamento atual:** não existe modelo persistente de turno, execução ou snapshot. **Mudança esperada:** definir quando congelar e quais campos de Equipment, MeasurementPoint, unidade, fonte, ordem, nomes históricos e programação são copiados; definir vigência e limites da duplicação.
**Critérios de aceite:** momento e campos estão explícitos; mudanças futuras não afetam execução existente; nomes históricos são preservados; relação com ADR-0005 é coerente; decisão aprovada antes das Issues 025 e 031.
**Fora de escopo:** migration, JPA, Service, endpoint ou tela.
**Persistência:** não altera banco. **API:** não cria contrato funcional; registra impacto futuro. **Interface:** nenhuma mudança visível. **Segurança:** nenhuma mudança funcional.
**Testes obrigatórios:** revisão por exemplos antes/depois para cadastro, ponto, unidade, fonte, ordem, operador e perfil. **Evidência de conclusão:** ADR T-02 aprovada com tabela de campos e momento do snapshot.
**Dependências:** depende de MVP-ISSUE-007, MVP-ISSUE-021, MVP-ISSUE-023; desbloqueia 025, 031 e 034; pode ser feita em paralelo com 016–020; bloqueio técnico: T-02; ADR antes da implementação: sim, registrar/aprovar a decisão.
**Objetivo de aprendizagem:** snapshots e temporalidade de domínio. **Perguntas para a sessão de aprendizagem:** Quando congelar? Quais campos precisam ser copiados? O que continua referenciado? Como evitar duplicação excessiva?
**Definition of Done da Issue:** [ ] T-02 decidida [ ] campos/momento/vigência definidos [ ] ADR aprovada [ ] relação com ADR-0005 registrada [ ] nenhum código alterado [ ] sessão realizada.

#### MVP-ISSUE-025 — Aplicar alterações de horário e meta somente a turnos futuros

**Grupo:** 3. Preparação da ronda.

**Problema:** editar a configuração pode reescrever o contexto de turnos já gerados.
**Resultado esperado:** mudanças de horário e quantidade afetam apenas ocorrências futuras; horário e meta usados permanecem congelados no histórico.
**Contexto:** atende DEC-P-11 e prepara a vigência da programação; a renomeação do perfil pertence exclusivamente à MVP-ISSUE-028.
**Rastreabilidade:** REQ-MVP-003, REQ-MVP-004, REQ-MVP-008; DEC-P-11; CS-02, CS-06, CS-08; T-02.
**Comportamento atual:** NÃO EXISTE. **Mudança esperada:** versionamento efetivo ou snapshot é escolhido sem alterar ocorrências existentes.
**Critérios de aceite:** editar horário ou meta não muda turno passado/aberto conforme o corte aprovado; o próximo turno usa valores novos; o histórico exibe horário e meta originais.
**Fora de escopo:** editar turno em execução, recalcular metas antigas, event sourcing.
**Persistência:** exige preservação histórica; migration conforme modelo T-02. **API:** contratos afetados conforme esta Issue. **Interface:** edição administrativa mostra vigência futura. **Segurança:** somente admin.
**Testes obrigatórios:** integração com antes/depois e relógio controlado. **Evidência de conclusão:** duas ocorrências exibem configurações distintas.
**Dependências:** depende de MVP-ISSUE-023, MVP-ISSUE-024; desbloqueia 026–031; pode ser feita em paralelo com 016–020; bloqueio técnico: decisão T-02 da 024; ADR antes da implementação: não.
**Objetivo de aprendizagem:** configuração versionada e snapshot. **Perguntas para a sessão de aprendizagem:** O que fica congelado? Por que não consultar sempre o perfil atual? Qual é o corte de vigência?
**Definition of Done da Issue:** [ ] T-02 coerente [ ] futuro apenas [ ] histórico testado [ ] docs [ ] PR revisável [ ] sessão possível.

#### MVP-ISSUE-026 — Disponibilizar as rondas previstas de cada turno

**Grupo:** 3. Preparação da ronda.

**Problema:** a programação não se materializa em execuções disponíveis.
**Resultado esperado:** cada ocorrência diária do turno recebe a quantidade coletiva configurada de rondas `PREVISTA`, sem operação manual diária.
**Contexto:** `PREVISTA` significa ainda não assumida; os nomes técnicos continuam candidatos de implementação.
**Rastreabilidade:** REQ-MVP-003, REQ-MVP-004; DEC-P-05, DEC-P-08, DEC-P-10, DEC-P-16, DEC-P-20, DEC-P-21, DEC-P-22, DEC-P-23; CS-02, CS-06; T-02.
**Comportamento atual:** NÃO EXISTE. **Mudança esperada:** geração idempotente sob demanda ou agendada, com janela exata e identidade do perfil preservada.
**Critérios de aceite:** quantidade correta aparece uma vez; chamada repetida não duplica; turno noturno mantém identidade; ativo gera futuro; inativo não; nenhuma ronda recebe operador antecipadamente.
**Fora de escopo:** assumir, leitura, distribuição por operador, tolerância após término.
**Persistência:** novas tabelas de ocorrência de turno/ronda, chaves naturais ou constraints idempotentes e índices. **API:** consulta/geração interna a refinar. **Interface:** somente preparação/admin se necessária. **Segurança:** geração protegida e consulta por sessão.
**Testes obrigatórios:** Testcontainers, relógio controlado, idempotência e cruzamento de meia-noite. **Evidência de conclusão:** um turno produz exatamente N rondas.
**Dados sintéticos mínimos:** usar somente as ocorrências fictícias necessárias para comprovar a geração idempotente das rondas previstas.
**Dependências:** depende de MVP-ISSUE-013, MVP-ISSUE-025; desbloqueia 027–030 e 046; pode ser feita em paralelo com 019–020; bloqueio técnico: T-02 aprovada; ADR antes da implementação: não.
**Objetivo de aprendizagem:** recorrência idempotente e modelagem temporal. **Perguntas para a sessão de aprendizagem:** Quem cria as previstas? O que impede duplicação? Qual data as identifica?
**Definition of Done da Issue:** [ ] geração [ ] idempotência [ ] testes temporais [ ] docs [ ] PR revisável [ ] sessão possível.

#### MVP-ISSUE-027 — Reativar perfil somente para turnos futuros

**Grupo:** 3. Preparação da ronda.

**Problema:** perfil desativado não possui retorno controlado à recorrência.
**Resultado esperado:** reativação volta a disponibilizar somente turnos futuros e não recompõe períodos desativados.
**Contexto:** DEC-P-27 preserva a lacuna real da desativação sem criar histórico fictício.
**Rastreabilidade:** REQ-MVP-003, REQ-MVP-008, REQ-MVP-011; DEC-P-24, DEC-P-27; CS-02, CS-08, CS-10.
**Comportamento atual:** a Issue 013 desativa e revoga sessões; a Issue 026 gera turnos apenas para perfis ativos. **Mudança esperada:** ação administrativa reativa o perfil e a recorrência considera apenas janelas futuras.
**Critérios de aceite:** nenhum turno do período inativo é criado; próximo futuro é gerado uma vez; histórico anterior permanece; repetição da ação é segura.
**Fora de escopo:** renomear, excluir, backfill ou recalcular turnos.
**Persistência:** atualiza estado/vigência sem apagar histórico. **API:** ação administrativa focada. **Interface:** controle ativo/inativo e confirmação. **Segurança:** somente admin e CSRF.
**Testes obrigatórios:** relógio controlado, PostgreSQL e HTTP para período inativo e futuro. **Evidência de conclusão:** linha temporal mostra lacuna preservada e primeiro turno futuro.
**Dependências:** depende de MVP-ISSUE-013, MVP-ISSUE-026; desbloqueia aceite do ciclo do perfil; pode ser feita em paralelo com 028–030; bloqueio técnico: nenhum; ADR antes da implementação: não.
**Objetivo de aprendizagem:** recorrência temporal sem backfill. **Perguntas para a sessão de aprendizagem:** Qual instante separa passado e futuro? Por que não repor turnos? O retry cria algo?
**Definition of Done da Issue:** [ ] reativação [ ] sem retroatividade [ ] idempotência [ ] histórico preservado [ ] testes [ ] docs [ ] PR pequena.

#### MVP-ISSUE-028 — Renomear perfil preservando nome histórico

**Grupo:** 3. Preparação da ronda.

**Problema:** mudar o nome do perfil pode alterar a identificação de turnos passados.
**Resultado esperado:** novos turnos usam o novo nome e turnos já gerados preservam o nome congelado.
**Contexto:** DEC-P-28 torna a nomenclatura histórica parte do contexto do turno.
**Rastreabilidade:** REQ-MVP-003, REQ-MVP-008; DEC-P-11, DEC-P-28; CS-02, CS-08.
**Comportamento atual:** o perfil e turnos gerados ainda não possuem fluxo de renomeação comprovado. **Mudança esperada:** atualizar nome cadastral para futuras gerações sem modificar snapshots existentes.
**Critérios de aceite:** turno anterior mostra nome antigo; turno posterior mostra novo; id do perfil permanece; edição não altera meta/horário histórico.
**Fora de escopo:** reativar, excluir, renomear turnos passados.
**Persistência:** atualiza perfil e preserva snapshot de turno. **API:** ação administrativa focada. **Interface:** formulário/lista mínimos. **Segurança:** somente admin e CSRF.
**Testes obrigatórios:** integração temporal, HTTP/Security e consulta de histórico. **Evidência de conclusão:** dois turnos exibem nomes correspondentes às respectivas gerações.
**Dependências:** depende de MVP-ISSUE-024, MVP-ISSUE-026; desbloqueia histórico e aceite; pode ser feita em paralelo com 027 e 029; bloqueio técnico: T-02 aprovada; ADR antes da implementação: não.
**Objetivo de aprendizagem:** snapshot de nome e identidade estável. **Perguntas para a sessão de aprendizagem:** Por que o nome é copiado? O perfil continua o mesmo? Qual teste evita reescrita?
**Definition of Done da Issue:** [ ] renomeação [ ] nomes históricos [ ] testes [ ] docs [ ] PR pequena [ ] sessão possível.

#### MVP-ISSUE-029 — Excluir perfil somente quando nunca utilizado

**Grupo:** 3. Preparação da ronda.

**Problema:** exclusão física pode destruir turnos, sessões ou registros históricos.
**Resultado esperado:** perfil sem turnos ou registros vinculados pode ser excluído; perfil utilizado recebe conflito e deve ser desativado.
**Contexto:** DEC-P-29 preserva histórico e evita cascade destrutivo.
**Rastreabilidade:** REQ-MVP-003, REQ-MVP-008, REQ-MVP-011; DEC-P-24, DEC-P-29; CS-02, CS-08, CS-10.
**Comportamento atual:** não existe exclusão protegida por uso histórico. **Mudança esperada:** verificar relacionamentos e aplicar FKs restritivas; permitir exclusão apenas quando nunca utilizado.
**Critérios de aceite:** perfil nunca usado é excluído; perfil com turno/sessão/registro aplicável retorna 409; nenhum histórico é removido; interface orienta desativar.
**Fora de escopo:** cascade, apagar turnos, reescrever histórico ou anonimizar.
**Persistência:** FKs sem cascade destrutivo e consulta/constraint de uso. **API:** ação administrativa e conflito estável. **Interface:** confirmação e orientação. **Segurança:** somente admin e CSRF.
**Testes obrigatórios:** PostgreSQL usado/não usado, HTTP/Security e preservação das relações. **Evidência de conclusão:** tentativa negada deixa turno e histórico intactos.
**Dependências:** depende de MVP-ISSUE-026; desbloqueia integridade dos perfis; pode ser feita em paralelo com 027–028; bloqueio técnico: nenhum; ADR antes da implementação: não.
**Objetivo de aprendizagem:** integridade referencial aplicada ao ciclo de vida. **Perguntas para a sessão de aprendizagem:** O que significa nunca utilizado? Qual FK bloqueia? Por que a alternativa é desativar?
**Definition of Done da Issue:** [ ] exclusão restrita [ ] 409 [ ] sem cascade [ ] testes [ ] docs [ ] PR pequena [ ] sessão possível.

### Grupo 4. Iniciar e visualizar execução

#### MVP-ISSUE-030 — Mostrar o turno atual e suas rondas disponíveis

**Grupo:** 4. Iniciar e visualizar execução.

**Problema:** após o login compartilhado não há tela operacional.
**Resultado esperado:** a equipe vê perfil, janela, meta coletiva e rondas com estado conceitual distinto.
**Contexto:** primeiro marco visual do domínio de turno e ronda.
**Rastreabilidade:** REQ-MVP-001, REQ-MVP-003, REQ-MVP-004, REQ-MVP-010; DEC-P-05, DEC-P-06, DEC-P-07, DEC-P-20, DEC-P-21, DEC-P-22, DEC-P-23; CS-01, CS-02, CS-06.
**Comportamento atual:** NÃO EXISTE. **Mudança esperada:** endpoint e página mesma origem apresentam a ocorrência correta, inclusive noturna, sem autoria inventada.
**Critérios de aceite:** sessão válida vê somente seu contexto; `PREVISTA` é descrita como não assumida; fora da janela não permite ação; viewport desktop/móvel é utilizável.
**Fora de escopo:** assumir, leitura, painel de design completo, login individual.
**Persistência:** nenhuma além de 026. **API:** consulta do contexto atual. **Interface:** tela funcional de turno/rondas. **Segurança:** sessão do perfil e escape de conteúdo.
**Testes obrigatórios:** Service temporal, MockMvc/Security e interface responsiva básica. **Evidência de conclusão:** tela em turno diurno/noturno.
**Dependências:** depende de MVP-ISSUE-011, MVP-ISSUE-026; desbloqueia 032 e interação inicial; pode ser feita em paralelo com 027–029 e 031; bloqueio técnico: nenhum; ADR antes da implementação: não.
**Objetivo de aprendizagem:** corte vertical backend/interface. **Perguntas para a sessão de aprendizagem:** Como achar o turno atual? O que a sessão filtra? Por que `PREVISTA` não é pendente?
**Definition of Done da Issue:** [ ] consulta [ ] tela [ ] segurança [ ] testes [ ] docs [ ] PR revisável [ ] sessão possível.

#### MVP-ISSUE-031 — Implementar o snapshot de contexto da ronda conforme T-02

**Grupo:** 4. Iniciar e visualizar execução.

**Problema:** a decisão T-02 aprovada ainda não existe no modelo persistente nem nos contratos.
**Resultado esperado:** turnos e execuções passam a preservar exatamente os campos e o momento definidos na Issue 024.
**Contexto:** esta Issue implementa a arquitetura revisada sem rediscutir quais dados congelar.
**Rastreabilidade:** REQ-MVP-003, REQ-MVP-004, REQ-MVP-005, REQ-MVP-006, REQ-MVP-008; DEC-P-11, DEC-P-28, DEC-P-35; CS-02, CS-03, CS-05, CS-08; T-02; ADR aprovada na MVP-ISSUE-024; ADR-0005 relacionada.
**Comportamento atual:** programação e pontos existem, mas o contexto aprovado não é congelado. **Mudança esperada:** criar migration, entidades/relacionamentos, Services e contratos necessários para materializar o snapshot aprovado.
**Critérios de aceite:** alterar cadastro não muda execução existente; nova execução usa dados novos; todos os campos aprovados são relidos; duplicação não excede o desenho; evolução desde schema anterior passa.
**Fora de escopo:** escolher novos campos, event sourcing, copiar dados não aprovados, primeira medição.
**Persistência:** exige nova migration Flyway e preservação histórica; V1 permanece intacta. **API:** expõe o contexto congelado nos contratos definidos. **Interface:** mostra contexto original quando aplicável. **Segurança:** respeita sessões e não aceita autoria arbitrária.
**Testes obrigatórios:** migration desde versões anteriores, Repository/Service, HTTP e cenários antes/depois. **Evidência de conclusão:** duas execuções separadas por mudança cadastral exibem contextos corretos.
**Dependências:** depende de MVP-ISSUE-007, MVP-ISSUE-021, MVP-ISSUE-024, MVP-ISSUE-025, MVP-ISSUE-026; desbloqueia 032, 034–038 e 051; pode ser feita em paralelo com 027–030; bloqueio técnico: decisão T-02 da 024; ADR antes da implementação: já aprovada na 024.
**Objetivo de aprendizagem:** materializar snapshot com JPA/Flyway. **Perguntas para a sessão de aprendizagem:** Qual tabela guarda cada campo? Como a migration evolui? Que teste prova isolamento do cadastro atual?
**Definition of Done da Issue:** [ ] T-02 respeitada [ ] migration [ ] JPA/Service/API [ ] histórico testado [ ] docs [ ] PR pequena [ ] sessão possível.

#### MVP-ISSUE-032 — Permitir que um operador assuma uma ronda prevista

**Grupo:** 4. Iniciar e visualizar execução.

**Problema:** ronda prevista não pode se tornar uma execução com autor.
**Resultado esperado:** durante a janela, operador com PIN confirmado assume uma `PREVISTA`, que passa a `EM_ANDAMENTO` com autoria e horário do servidor.
**Contexto:** qualquer operador ativo do turno pode pegar a próxima ronda; depois de assumida, a autoria não é transferível.
**Rastreabilidade:** REQ-MVP-001, REQ-MVP-004; DEC-P-04, DEC-P-06, DEC-P-09, DEC-P-23, DEC-P-35; CS-01, CS-02, CS-06.
**Comportamento atual:** NÃO EXISTE. **Mudança esperada:** comando transacional valida sessão, PIN, janela e estado; registra id/nome histórico do autor.
**Critérios de aceite:** ativa e prevista pode ser assumida até o instante anterior ao fim; autor/horário ficam gravados; sem PIN/sessão falha; segunda tentativa recebe conflito; nomes técnicos não são impostos como enum sem refinamento.
**Fora de escopo:** transferir, leitura, retomar, concluir.
**Persistência:** autor, snapshot de nome, início e estado; constraints/FKs. **API:** contratos afetados conforme esta Issue. **Interface:** botão de assumir e confirmação por PIN. **Segurança:** sessão do turno, identidade ativa, CSRF e autoria.
**Testes obrigatórios:** domínio, PostgreSQL, HTTP/Security e interface. **Evidência de conclusão:** tela muda de prevista para em andamento e mostra autor.
**Dependências:** depende de MVP-ISSUE-015, MVP-ISSUE-030, MVP-ISSUE-031; desbloqueia 019–020, 033, 035 e 044; pode ser feita em paralelo com 027–029; bloqueio técnico: nenhum; ADR antes da implementação: não.
**Objetivo de aprendizagem:** comando transacional e autoria. **Perguntas para a sessão de aprendizagem:** O que é autenticado pelo turno? O que o PIN assina? Por que congelar o nome?
**Definition of Done da Issue:** [ ] assunção [ ] autoria [ ] testes [ ] tela [ ] docs [ ] PR revisável [ ] sessão possível.

#### MVP-ISSUE-033 — Resolver concorrência ao assumir a mesma ronda

**Grupo:** 4. Iniciar e visualizar execução.

**Problema:** dois operadores podem tentar assumir a mesma `PREVISTA` simultaneamente.
**Resultado esperado:** somente uma tentativa vence; a outra recebe conflito estável e nenhuma autoria dupla é gravada.
**Contexto:** garante estado íntegro antes de ampliar o fluxo.
**Rastreabilidade:** REQ-MVP-004, REQ-MVP-011; DEC-P-04, DEC-P-09; CS-02, CS-10; T-03 relacionada.
**Comportamento atual:** NÃO EXISTE. **Mudança esperada:** lock otimista, atualização condicional ou mecanismo equivalente definido e testado.
**Critérios de aceite:** duas requisições concorrentes resultam em um sucesso e um 409; vencedor é íntegro; repetição não transfere autoria; erro usa contrato comum.
**Fora de escopo:** idempotência de leituras, fila distribuída, transferência.
**Persistência:** versão/constraint ou update condicional; possível índice. **API:** contratos afetados conforme esta Issue. **Interface:** conflito orienta recarregar. **Segurança:** ambas exigem sessão/PIN válidos.
**Testes obrigatórios:** concorrência real com PostgreSQL e HTTP focado. **Evidência de conclusão:** teste repetível mostra um vencedor.
**Dependências:** depende de MVP-ISSUE-032; desbloqueia 034–035 e 047; pode ser feita em paralelo com 27–29; bloqueio técnico: nenhum; concorrência local; ADR antes da implementação: não.
**Objetivo de aprendizagem:** atomicidade e lock otimista. **Perguntas para a sessão de aprendizagem:** Onde ocorre a disputa? Por que 409? O que impede transferência?
**Definition of Done da Issue:** [ ] disputa segura [ ] teste real [ ] erro estável [ ] docs [ ] PR revisável [ ] sessão possível.

### Grupo 5. Primeiro resultado de coleta

#### MVP-ISSUE-034 — Refinar o modelo mínimo de medição T-05

**Grupo:** 5. Primeiro resultado de coleta.

**Problema:** ADR-0005 ainda não fecha decimal, precisão/escala, exclusividade de resultado, unidade, fonte e tempos.
**Resultado esperado:** T-05 refina e aprova a ADR-0005 para o menor resultado persistível, sem decidir T-03.
**Contexto:** o modelo precisa se relacionar ao snapshot e delimitar conceitualmente futuras revisões antes da primeira leitura.
**Rastreabilidade:** REQ-MVP-005, REQ-MVP-006, REQ-MVP-009; DEC-P-03, DEC-P-15; CS-03, CS-04, CS-05, CS-08; T-05; ADR-0005.
**Comportamento atual:** existe proposta documental; NÃO EXISTE implementação de resultado. **Mudança esperada:** definir decimal, precisão/escala, valor versus ausência, unidade, fonte, horários, relação com snapshot e fronteira conceitual da revisão.
**Critérios de aceite:** invariantes e exemplos válidos/inválidos estão definidos; zero é valor; ausência não carrega número; horários têm semântica; revisão está delimitada sem protocolo de T-03.
**Fora de escopo:** idempotência, chave de operação, versão vigente, concorrência, migration ou código.
**Persistência:** não altera banco. **API:** não cria endpoint; documenta o contrato futuro. **Interface:** exemplos de formulário podem ser documentados sem implementação. **Segurança:** nenhuma mudança funcional.
**Testes obrigatórios:** revisão de exemplos de medição, ausência, zero, unidade, fonte e horários contra T-02. **Evidência de conclusão:** ADR-0005 refinada e aprovada com matriz de invariantes.
**Dependências:** depende de MVP-ISSUE-031; desbloqueia 035–040; pode ser feita em paralelo com 027–030; bloqueio técnico: T-05; ADR antes da implementação: sim, refinamento da ADR-0005.
**Objetivo de aprendizagem:** modelagem de valor, tempo e exclusividade. **Perguntas para a sessão de aprendizagem:** Por que usar decimal? Quem fornece unidade? Como ausência difere de zero? Qual é a fronteira da revisão?
**Definition of Done da Issue:** [ ] T-05 decidida [ ] ADR-0005 aprovada [ ] invariantes/exemplos [ ] relação T-02 [ ] T-03 fora do escopo [ ] nenhum código [ ] sessão realizada.

#### MVP-ISSUE-035 — Salvar e reler uma medição válida

**Grupo:** 5. Primeiro resultado de coleta.

**Problema:** uma ronda em andamento não aceita qualquer resultado.
**Resultado esperado:** operador autor registra um valor válido para um ponto; o dado persiste e reaparece na tela.
**Contexto:** menor corte vertical da coleta: persistência + regra + API + teste + interface mínima.
**Rastreabilidade:** REQ-MVP-004, REQ-MVP-005, REQ-MVP-010; DEC-P-04, DEC-P-09, DEC-P-15; CS-02, CS-03; ADR-0005 refinada.
**Comportamento atual:** NÃO EXISTE. **Mudança esperada:** comando exige execução ativa, ponto do snapshot e autor correto; zero é aceito como valor.
**Critérios de aceite:** decimal válido salva e relê; zero salva como zero; vazio/formato inválido não; ponto alheio falha; outra identidade não registra na ronda assumida.
**Fora de escopo:** ausência, condição, ocorrência, correção, idempotência ampla.
**Persistência:** nova migration conforme ADR, FKs e precisão. **API:** contrato de criar/consultar resultado mínimo. **Interface:** campo e confirmação no ponto. **Segurança:** sessão, PIN/autoria e CSRF.
**Testes obrigatórios:** unidade, Repository/Testcontainers, MockMvc/Security e interface. **Evidência de conclusão:** salvar→recarregar→ver mesmo valor.
**Dados sintéticos mínimos:** usar valor manual sintético, identificado como fictício e sem faixa industrial, apenas para salvar e reler a primeira medição.
**Dependências:** depende de MVP-ISSUE-032, MVP-ISSUE-034; desbloqueia 036–040 e 044–045; pode ser feita em paralelo com 027–029; bloqueio técnico: decisão T-05/ADR-0005; ADR antes da implementação: já aprovada na 034.
**Objetivo de aprendizagem:** corte vertical e transação JPA. **Perguntas para a sessão de aprendizagem:** Que camada valida o ponto? Como zero é preservado? O que prova persistência?
**Definition of Done da Issue:** [ ] salvar/reler [ ] testes [ ] interface [ ] autoria [ ] docs [ ] PR revisável [ ] sessão possível.

#### MVP-ISSUE-036 — Registrar unidade, fonte e horários da medição

**Grupo:** 5. Primeiro resultado de coleta.

**Problema:** o valor isolado não pode ser interpretado nem auditado.
**Resultado esperado:** leitura preserva unidade do snapshot, fonte e horários definidos na ADR, exibindo-os na releitura.
**Contexto:** fornece contexto mínimo sem assumir unidades reais da planta.
**Rastreabilidade:** REQ-MVP-005; DEC-P-01, DEC-P-11, DEC-P-15; CS-03; ADR-0005.
**Comportamento atual:** 035 terá somente o núcleo do valor. **Mudança esperada:** contexto obrigatório/derivado é persistido, com horário do servidor confiável.
**Critérios de aceite:** unidade não é aceita arbitrariamente do cliente; fonte válida é registrada; horário do servidor existe; eventual horário informado segue regra; consulta mostra o contexto original.
**Fora de escopo:** conversão de unidades, calibração, fuso de piloto, IoT.
**Persistência:** evolução focada da tabela; índices apenas se consulta exigir. **API:** contratos afetados conforme esta Issue. **Interface:** campos acordados e exibição mínima. **Segurança:** cliente não falsifica autor/horário servidor.
**Testes obrigatórios:** domínio/HTTP, persistência e relógio controlado. **Evidência de conclusão:** resposta e banco demonstram contexto.
**Dependências:** depende de MVP-ISSUE-035; desbloqueia 037, 051 e 053; pode ser feita em paralelo com 038; bloqueio técnico: T-05 aprovada; ADR antes da implementação: não.
**Objetivo de aprendizagem:** dados derivados e tempo confiável. **Perguntas para a sessão de aprendizagem:** Quem escolhe unidade? Qual horário é auditável? Para que serve fonte?
**Definition of Done da Issue:** [ ] contexto [ ] testes [ ] contrato/docs [ ] sem unidade inventada [ ] PR revisável [ ] sessão possível.

#### MVP-ISSUE-037 — Registrar ausência justificada sem valor numérico

**Grupo:** 5. Primeiro resultado de coleta.

**Problema:** ponto sem leitura não pode receber resultado válido sem inventar zero.
**Resultado esperado:** operador registra ausência com motivo obrigatório; banco e domínio impedem coexistência com medição.
**Contexto:** ausência justificada permite conclusão, enquanto ponto sem resultado continua pendente.
**Rastreabilidade:** REQ-MVP-005, REQ-MVP-010; DEC-P-03; CS-04; ADR-0005.
**Comportamento atual:** somente medição após 035. **Mudança esperada:** resultado exclusivo `medição OU ausência`, com zero preservado como medição.
**Critérios de aceite:** ausência com motivo salva e relê; sem motivo falha; valor+ausência falha; nem valor nem ausência não cria resultado; zero não vira ausência.
**Fora de escopo:** concluir a ronda, catálogo industrial de motivos, ocorrência automática.
**Persistência:** nova migration/constraint `CHECK` ou modelo equivalente; histórico futuro preservável. **API:** contratos afetados conforme esta Issue. **Interface:** alternativa clara entre medir e justificar ausência. **Segurança:** autoria da ronda e CSRF.
**Testes obrigatórios:** domínio, constraint PostgreSQL, HTTP e interface. **Evidência de conclusão:** quatro casos da exclusividade demonstrados.
**Dependências:** depende de MVP-ISSUE-035, MVP-ISSUE-036; desbloqueia 045 e 051; pode ser feita em paralelo com 038–039; bloqueio técnico: T-05 aprovada; ADR antes da implementação: não.
**Objetivo de aprendizagem:** invariante XOR e constraint. **Perguntas para a sessão de aprendizagem:** Por que ausência não é zero? Onde garantir exclusividade? O que permanece pendente?
**Definition of Done da Issue:** [ ] ausência [ ] XOR no domínio/banco [ ] testes [ ] tela [ ] docs [ ] PR revisável [ ] sessão possível.

#### MVP-ISSUE-038 — Registrar condição observada separada do resultado

**Grupo:** 5. Primeiro resultado de coleta.

**Problema:** cadastro legado e leitura numérica não representam corretamente a condição observada durante a ronda.
**Resultado esperado:** operador pode registrar condição do equipamento/ponto no contexto da coleta sem alterar o cadastro nem criar ocorrência.
**Contexto:** fecha a separação Equipment × condição observada × medição/ausência × ocorrência.
**Rastreabilidade:** REQ-MVP-006; DEC-P-15; CS-05; T-04, T-05; seções 11.2 e 16.2.
**Comportamento atual:** após 007, cadastro não afirma condição; após 037, resultado existe sem campo próprio de condição. **Mudança esperada:** condição contextual com vocabulário fictício/refinado, ligada à ronda.
**Critérios de aceite:** condição é salva e relida no ponto/equipamento; não muda Equipment; pode coexistir com resultado conforme ADR; texto livre não vira ocorrência; valores desconhecidos falham.
**Fora de escopo:** alarme, manutenção, inferência automática, catálogo real da planta.
**Persistência:** migration focada ou campo no contexto conforme ADR; preservação histórica. **API:** contratos afetados conforme esta Issue. **Interface:** seletor/exibição mínimos. **Segurança:** autoria e sessão.
**Testes obrigatórios:** domínio, persistência, HTTP e interface. **Evidência de conclusão:** condição aparece após releitura sem mudar cadastro.
**Dependências:** depende de MVP-ISSUE-007, MVP-ISSUE-034, MVP-ISSUE-035; desbloqueia 048 e 051; pode ser feita em paralelo com 036–037; bloqueio técnico: T-04 e T-05 aprovadas; ADR antes da implementação: não.
**Objetivo de aprendizagem:** separação de conceitos de domínio. **Perguntas para a sessão de aprendizagem:** Por que condição não pertence ao cadastro? Quando vira ocorrência? O que o snapshot preserva?
**Definition of Done da Issue:** [ ] condição separada [ ] testes [ ] interface [ ] docs [ ] PR revisável [ ] sessão possível.

### Grupo 6. Gravação confiável

#### MVP-ISSUE-039 — Decidir e documentar gravação confiável T-03

**Grupo:** 6. Gravação confiável.

**Problema:** retry, revisões e concorrência ainda não compartilham uma semântica técnica aprovada.
**Resultado esperado:** T-03 fecha somente o necessário para idempotência e revisões antes de qualquer protocolo ser implementado.
**Contexto:** a decisão deve impedir duplicação e sobrescrita sem introduzir auditoria genérica ou event sourcing.
**Rastreabilidade:** REQ-MVP-005, REQ-MVP-009, REQ-MVP-011; DEC-P-13; CS-03, CS-08, CS-10; T-03; seção 15.3/15.6.
**Comportamento atual:** a primeira leitura existe, mas não possui protocolo aprovado de retry/revisão concorrente. **Mudança esperada:** definir identidade da intenção, mesma chave+mesmo conteúdo, mesma chave+conteúdo diferente, versão vigente, relação gravação/revisão/concorrência e limite da auditoria.
**Critérios de aceite:** semânticas e conflitos estão exemplificados; responsabilidades por agregado são delimitadas; ADR é aprovada se duradoura; decisão não tenta resolver toda concorrência da aplicação.
**Fora de escopo:** implementar idempotência, migration, revisão, locks ou ocorrência.
**Persistência:** não altera banco. **API:** não cria endpoint; documenta requisitos do contrato. **Interface:** nenhuma mudança visível. **Segurança:** ameaças de replay e autoria são registradas sem implementação.
**Testes obrigatórios:** revisão de cenários de retry, colisão de chave, versão obsoleta e autoria. **Evidência de conclusão:** ADR/refinamento T-03 aprovado com tabela de resultados esperados.
**Dependências:** depende de MVP-ISSUE-034, MVP-ISSUE-035; desbloqueia 040–043 e 047; pode ser feita em paralelo com 044–046; bloqueio técnico: T-03; ADR antes da implementação: sim, se a decisão for duradoura.
**Objetivo de aprendizagem:** idempotência, versão e concorrência como decisões distintas. **Perguntas para a sessão de aprendizagem:** O que identifica a intenção? Quando retry devolve o mesmo resultado? Quando retorna 409? O que é versão vigente?
**Definition of Done da Issue:** [ ] T-03 decidida [ ] semânticas exemplificadas [ ] limite de auditoria [ ] ADR aprovada quando aplicável [ ] nenhum código [ ] sessão realizada.

#### MVP-ISSUE-040 — Tornar a gravação de resultado idempotente conforme T-03

**Grupo:** 6. Gravação confiável.

**Problema:** duplo clique ou retry pode duplicar um resultado confirmado.
**Resultado esperado:** a mesma intenção repetida produz o efeito aprovado em T-03 e conteúdo conflitante recebe resposta estável.
**Contexto:** implementa apenas a idempotência da gravação; revisões e outros conflitos ficam nas Issues seguintes.
**Rastreabilidade:** REQ-MVP-005, REQ-MVP-011; CS-03, CS-10; T-03; ADR aprovada na MVP-ISSUE-039.
**Comportamento atual:** a primeira leitura persiste sem protocolo idempotente. **Mudança esperada:** aplicar chave/identidade de intenção, constraint e resposta exatamente conforme T-03.
**Critérios de aceite:** mesma chave+mesmo conteúdo não duplica; mesma chave+conteúdo diferente segue o conflito aprovado; requisições simultâneas deixam um efeito; falha fica visível.
**Fora de escopo:** revisão concorrente, locks de todos os agregados, fila, mensageria ou idempotência global.
**Persistência:** exige constraint/índice e metadado aprovados; nova migration focada. **API:** implementa o cabeçalho/campo e respostas aprovados. **Interface:** reutiliza a chave no retry sem ocultar falha. **Segurança:** chave não substitui sessão nem autoria.
**Testes obrigatórios:** PostgreSQL concorrente, HTTP e retry pela interface. **Evidência de conclusão:** contagem permanece uma após repetição e a resposta é recuperável.
**Dependências:** depende de MVP-ISSUE-035, MVP-ISSUE-039; desbloqueia 041, 045 e 047; pode ser feita em paralelo com 044–046; bloqueio técnico: decisão T-03 da 039; ADR antes da implementação: já aprovada na 039.
**Objetivo de aprendizagem:** implementar idempotência com constraint real. **Perguntas para a sessão de aprendizagem:** Onde a chave é criada? O banco fecha qual corrida? Retry difere de correção como?
**Definition of Done da Issue:** [ ] T-03 respeitada [ ] migration/constraint [ ] retry seguro [ ] conflito testado [ ] interface [ ] docs [ ] PR pequena.

#### MVP-ISSUE-041 — Corrigir o próprio registro com motivo e revisão

**Grupo:** 6. Gravação confiável.

**Problema:** erro de digitação só pode ser sobrescrito, perdendo o original.
**Resultado esperado:** autor cria nova revisão de sua medição, ausência ou condição, com motivo obrigatório; original permanece.
**Contexto:** histórico proporcional, sem event sourcing.
**Rastreabilidade:** REQ-MVP-009, REQ-MVP-010; DEC-P-13; CS-08; T-03.
**Comportamento atual:** resultado é imutável após criação. **Mudança esperada:** revisão vigente aponta para cadeia/registro original e guarda autor, nome histórico, motivo e horário.
**Critérios de aceite:** autor corrige com PIN; outro operador é negado; motivo vazio falha; consulta vigente muda; original permanece acessível; XOR continua válido.
**Fora de escopo:** correção administrativa, apagar revisão, event sourcing.
**Persistência:** nova migration para revisões e índice da vigente; preservação integral. **API:** contratos afetados conforme esta Issue. **Interface:** ação “corrigir” e formulário mínimo. **Segurança:** sessão, PIN e autorização por autoria.
**Testes obrigatórios:** domínio, PostgreSQL, HTTP/Security e interface. **Evidência de conclusão:** antes/original/depois demonstrados.
**Dependências:** depende de MVP-ISSUE-015, MVP-ISSUE-040; desbloqueia 042–043 e 053; pode ser feita em paralelo com 044; bloqueio técnico: T-03 aprovada; ADR antes da implementação: não.
**Objetivo de aprendizagem:** auditoria por revisões. **Perguntas para a sessão de aprendizagem:** O que é vigente? Por que não UPDATE destrutivo? Quem pode corrigir?
**Definition of Done da Issue:** [ ] revisão [ ] motivo [ ] autorização [ ] testes históricos [ ] docs [ ] PR revisável [ ] sessão possível.

#### MVP-ISSUE-042 — Permitir correção administrativa com autoria preservada

**Grupo:** 6. Gravação confiável.

**Problema:** administrador não consegue corrigir registro de qualquer operador sem apagar quem fez a leitura original.
**Resultado esperado:** admin cria revisão com motivo obrigatório e identidade administrativa; autoria original permanece visível.
**Contexto:** completa DEC-P-13 mantendo as duas responsabilidades distintas.
**Rastreabilidade:** REQ-MVP-001, REQ-MVP-009, REQ-MVP-010; DEC-P-13; CS-01, CS-08.
**Comportamento atual:** somente autor corrige após 041. **Mudança esperada:** caminho administrativo autorizado cria revisão, nunca reatribui original.
**Critérios de aceite:** admin corrige qualquer resultado; sessão de turno sem PIN autor não usa esse caminho; motivo obrigatório; consulta distingue autor original e revisor admin.
**Fora de escopo:** apagar, impersonar operador, gestão multiadmin completa.
**Persistência:** reutiliza revisões; ator administrativo discriminado sem segredo. **API:** contratos afetados conforme esta Issue. **Interface:** ação em consulta administrativa. **Segurança:** papel admin e CSRF.
**Testes obrigatórios:** autorização, persistência e HTTP/interface. **Evidência de conclusão:** linha do tempo mostra ambos os atores.
**Dependências:** depende de MVP-ISSUE-009, MVP-ISSUE-041; desbloqueia 043 e 053; pode ser feita em paralelo com 044; bloqueio técnico: nenhum; ADR antes da implementação: não.
**Objetivo de aprendizagem:** autorização por papel e trilha de auditoria. **Perguntas para a sessão de aprendizagem:** Quem é o autor original? Como representar revisor admin? Por que motivo é obrigatório?
**Definition of Done da Issue:** [ ] correção admin [ ] autoria preservada [ ] testes [ ] docs [ ] PR revisável [ ] sessão possível.

#### MVP-ISSUE-043 — Evitar revisões concorrentes silenciosas

**Grupo:** 6. Gravação confiável.

**Problema:** dois formulários podem corrigir a mesma revisão vigente e perder intenção.
**Resultado esperado:** somente uma revisão baseada na versão atual é aceita; a outra recebe conflito e pode recarregar.
**Contexto:** fecha confiabilidade do histórico sem bloquear alterações legítimas sequenciais.
**Rastreabilidade:** REQ-MVP-009, REQ-MVP-011; DEC-P-13; CS-08, CS-10; T-03.
**Comportamento atual:** 041/042 não asseguram conflito de edição. **Mudança esperada:** versão esperada/lock otimista protege a revisão vigente.
**Critérios de aceite:** duas correções concorrentes geram um sucesso e um 409; nenhuma revisão some; após recarregar, nova correção pode ser feita.
**Fora de escopo:** merge automático de valores, lock pessimista global.
**Persistência:** versão ou constraint da revisão vigente; índice conforme consulta. **API:** contratos afetados conforme esta Issue. **Interface:** versão enviada e mensagem para recarregar. **Segurança:** autorizações de 041/042 permanecem.
**Testes obrigatórios:** concorrência PostgreSQL e HTTP. **Evidência de conclusão:** teste reproduz um vencedor sem perda.
**Dependências:** depende de MVP-ISSUE-041, MVP-ISSUE-042; desbloqueia 051 e 053; pode ser feita em paralelo com 044–047; bloqueio técnico: T-03 aprovada; ADR antes da implementação: não.
**Objetivo de aprendizagem:** lost update e lock otimista. **Perguntas para a sessão de aprendizagem:** O que é uma versão obsoleta? Por que não escolher o último silenciosamente? Qual dado permanece?
**Definition of Done da Issue:** [ ] conflito [ ] teste concorrente [ ] UX de recarga [ ] docs [ ] PR revisável [ ] sessão possível.

### Grupo 7. Progresso e desfecho

#### MVP-ISSUE-044 — Deixar a ronda pendente e retomá-la pelo mesmo operador

**Grupo:** 7. Progresso e desfecho.

**Problema:** execução iniciada não pode ser pausada para continuidade no mesmo turno.
**Resultado esperado:** autor muda `EM_ANDAMENTO` para `PENDENTE` e, durante a janela, retoma para `EM_ANDAMENTO`; outro operador não pode assumir nem retomar.
**Contexto:** preserva a continuidade compartilhada e a autoria pessoal aprovada no cenário fictício.
**Rastreabilidade:** REQ-MVP-004, REQ-MVP-010; DEC-P-04, DEC-P-10; CS-06.
**Comportamento atual:** execução permanece em andamento. **Mudança esperada:** transições transacionais com horário/autor, sem transferir propriedade.
**Critérios de aceite:** autor pausa/retoma com PIN; outro operador recebe 403/409 conforme contrato; prevista continua conceito distinto; após fim não retoma.
**Fora de escopo:** transferência, reabertura pós-turno, conclusão.
**Persistência:** estado e eventos mínimos de transição; histórico preservado. **API:** contratos afetados conforme esta Issue. **Interface:** ações e indicação clara de responsável. **Segurança:** sessão/PIN/autoria.
**Testes obrigatórios:** domínio, PostgreSQL, HTTP/Security e interface. **Evidência de conclusão:** ciclo andamento→pendente→andamento.
**Dependências:** depende de MVP-ISSUE-032, MVP-ISSUE-035; desbloqueia 045–047 e 052; pode ser feita em paralelo com 039–043; bloqueio técnico: nenhum; ADR antes da implementação: não.
**Objetivo de aprendizagem:** máquina de estados e autorização por proprietário. **Perguntas para a sessão de aprendizagem:** Pendente significa o quê? Quem pode retomar? Por que não transferir?
**Definition of Done da Issue:** [ ] transições [ ] autoria [ ] testes [ ] tela [ ] docs [ ] PR revisável [ ] sessão possível.

#### MVP-ISSUE-045 — Concluir ronda somente com todos os pontos resolvidos

**Grupo:** 7. Progresso e desfecho.

**Problema:** não existe desfecho nem validação de completude.
**Resultado esperado:** autor conclui dentro do turno quando cada ponto tem medição ou ausência justificada vigente; ponto sem resultado bloqueia.
**Contexto:** DEC-P-03 distingue ausência válida de pendência real.
**Rastreabilidade:** REQ-MVP-004, REQ-MVP-005, REQ-MVP-010; DEC-P-03, DEC-P-04, DEC-P-22; CS-04, CS-06.
**Comportamento atual:** NÃO EXISTE. **Mudança esperada:** regra agrega resultados vigentes do snapshot e fecha estado/horário de forma atômica.
**Critérios de aceite:** todos medidos conclui; combinação com ausências justificadas conclui; ponto sem resultado lista pendência e não fecha; não autor falha; concluída não aceita nova coleta comum.
**Fora de escopo:** reabrir concluída, tolerância, aprovação do admin.
**Persistência:** término/estado e possível evento; preserva resultados. **API:** contratos afetados conforme esta Issue. **Interface:** botão, resumo e bloqueios. **Segurança:** autoria/PIN e CSRF.
**Testes obrigatórios:** domínio, integração, HTTP e interface. **Evidência de conclusão:** três cenários de conclusão.
**Dependências:** depende de MVP-ISSUE-037, MVP-ISSUE-040, MVP-ISSUE-044; desbloqueia 046–047 e 051; pode ser feita em paralelo com 41–43; bloqueio técnico: nenhum; ADR antes da implementação: não.
**Objetivo de aprendizagem:** invariante de agregado. **Perguntas para a sessão de aprendizagem:** O que conta como resolvido? Por que usar revisão vigente? O que muda ao concluir?
**Definition of Done da Issue:** [ ] completude [ ] conclusão [ ] testes [ ] UX [ ] docs [ ] PR revisável [ ] sessão possível.

#### MVP-ISSUE-046 — Encerrar execuções incompletas no horário exato do turno

**Grupo:** 7. Progresso e desfecho.

**Problema:** previstas, em andamento ou pendentes podem sobreviver além do término configurado.
**Resultado esperado:** no fim exato, execuções incompletas tornam-se conceitualmente `NAO_CONCLUIDA_FIM_TURNO`, preservando dados e bloqueando novas ações.
**Contexto:** aplica DEC-P-10, DEC-P-22 também a turno que atravessa meia-noite.
**Rastreabilidade:** REQ-MVP-003, REQ-MVP-004, REQ-MVP-010, REQ-MVP-011; DEC-P-10, DEC-P-21, DEC-P-22, DEC-P-23; CS-06, CS-10.
**Comportamento atual:** NÃO EXISTE. **Mudança esperada:** fechamento automático idempotente e defesa síncrona em qualquer comando posterior; nomes técnicos ainda refináveis.
**Critérios de aceite:** três estados incompletos fecham; concluída não muda; dados/autoria ficam; no instante final nova ação é negada; retry do fechamento não duplica eventos.
**Fora de escopo:** tolerância, retomar no turno seguinte, transferir, notificações.
**Persistência:** estado/fim/motivo e índice para fechamento; sem apagar. **API:** contratos afetados conforme esta Issue. **Interface:** estado final legível. **Segurança:** job não contorna regras; requisição tardia bloqueada.
**Testes obrigatórios:** relógio controlado, concorrência fronteira, PostgreSQL e turno noturno. **Evidência de conclusão:** teste no instante limite e tela final.
**Dependências:** depende de MVP-ISSUE-026, MVP-ISSUE-044, MVP-ISSUE-045; desbloqueia 027–029, 047 e 051–052; pode ser feita em paralelo com 041–043; bloqueio técnico: estratégia temporal local; ADR antes da implementação: condicional somente se criar infraestrutura transversal.
**Objetivo de aprendizagem:** tempo de domínio e fechamento idempotente. **Perguntas para a sessão de aprendizagem:** Por que job sozinho não basta? O que ocorre exatamente no término? Quais estados fecham?
**Definition of Done da Issue:** [ ] fechamento [ ] defesa síncrona [ ] testes de fronteira [ ] docs [ ] PR revisável [ ] sessão possível.

#### MVP-ISSUE-047 — Padronizar conflitos das transições da ronda

**Grupo:** 7. Progresso e desfecho.

**Problema:** retries e comandos fora de estado podem gerar respostas inconsistentes.
**Resultado esperado:** assumir, pausar, retomar e concluir distinguem repetição segura, estado incompatível, autoria e turno encerrado.
**Contexto:** consolida contrato sem criar mega-handler antecipado.
**Rastreabilidade:** REQ-MVP-004, REQ-MVP-011; DEC-P-03, DEC-P-04, DEC-P-10, DEC-P-22, DEC-P-23; CS-06, CS-10.
**Comportamento atual:** transições 032/044–046 possuem erros locais. **Mudança esperada:** códigos ProblemDetail estáveis e interface orienta recarregar ou corrige a ação.
**Critérios de aceite:** cada conflito relevante tem status/código documentado; retry não corrompe; ação proibida não retorna 500; interface apresenta próximo passo simples.
**Fora de escopo:** catálogo global de todos os erros, reabertura, compensação.
**Persistência:** nenhuma nova salvo ajuste de versão. **API:** contratos afetados conforme esta Issue. **Interface:** contrato de conflitos de ronda. **Segurança:** 401/403 não vazam existência indevida.
**Testes obrigatórios:** tabela de transições por HTTP e casos de segurança. **Evidência de conclusão:** contrato e suíte parametrizada.
**Dependências:** depende de MVP-ISSUE-033, MVP-ISSUE-044, MVP-ISSUE-045, MVP-ISSUE-046; desbloqueia 048 e 051; pode ser feita em paralelo com 041–43; bloqueio técnico: T-03 informa retries; ADR antes da implementação: não.
**Objetivo de aprendizagem:** semântica HTTP e máquina de estados. **Perguntas para a sessão de aprendizagem:** Quando usar 409? Retry pode ser sucesso? Como evitar vazamento em 403?
**Definition of Done da Issue:** [ ] matriz de erros [ ] testes [ ] UX [ ] docs [ ] PR revisável [ ] sessão possível.

### Grupo 8. Ocorrências

#### MVP-ISSUE-048 — Criar ocorrência separada da leitura

**Grupo:** 8. Ocorrências.

**Problema:** um acontecimento que exige acompanhamento não possui registro próprio.
**Resultado esperado:** operador identificado cria ocorrência ligada ao contexto da ronda/equipamento, com descrição, autoria e horário, sem alterar o resultado.
**Contexto:** ocorrência não é observação simples nem ausência.
**Rastreabilidade:** REQ-MVP-007, REQ-MVP-010; DEC-P-09, DEC-P-12; CS-07.
**Comportamento atual:** NÃO EXISTE. **Mudança esperada:** agregado/evento de ocorrência independente, inicialmente aberto.
**Critérios de aceite:** PIN válido cria; descrição vazia falha; vínculo/contexto reaparece; leitura/condição permanecem; autor histórico é preservado.
**Fora de escopo:** acompanhamento, resolução, prioridade industrial, anexos.
**Persistência:** nova migration, FKs, estado aberto e autoria snapshot. **API:** contratos afetados conforme esta Issue. **Interface:** formulário próprio e confirmação. **Segurança:** sessão/PIN/CSRF.
**Testes obrigatórios:** domínio, PostgreSQL, HTTP/Security e interface. **Evidência de conclusão:** ocorrência aberta aparece separada do ponto.
**Dependências:** depende de MVP-ISSUE-015, MVP-ISSUE-038, MVP-ISSUE-047; desbloqueia 049–053; pode ser feita em paralelo com 041–043; bloqueio técnico: nenhum; ADR antes da implementação: não.
**Objetivo de aprendizagem:** fronteira de agregado. **Perguntas para a sessão de aprendizagem:** Por que não é leitura? Que contexto deve ficar? Quem é autor?
**Definition of Done da Issue:** [ ] criação [ ] separação [ ] testes [ ] tela [ ] docs [ ] PR revisável [ ] sessão possível.

#### MVP-ISSUE-049 — Registrar acompanhamento por outro operador

**Grupo:** 8. Ocorrências.

**Problema:** ocorrência aberta não aceita continuidade por outro integrante.
**Resultado esperado:** qualquer operador ativo, identificado por PIN na sessão do turno, adiciona acompanhamento com autor e horário próprios.
**Contexto:** continuidade é coletiva, mas cada ação mantém autoria individual.
**Rastreabilidade:** REQ-MVP-001, REQ-MVP-007, REQ-MVP-010; DEC-P-12, DEC-P-35; CS-01, CS-07.
**Comportamento atual:** somente criação em 048. **Mudança esperada:** evento de acompanhamento append-only, sem substituir descrição original.
**Critérios de aceite:** outro operador adiciona; criador também; inativo/PIN inválido não; consulta ordena eventos; nomes históricos permanecem.
**Fora de escopo:** resolver, editar/apagar evento, chat e notificações.
**Persistência:** nova tabela/evento e índices por ocorrência/tempo; preservação de histórico. **API:** contratos afetados conforme esta Issue. **Interface:** formulário/lista de acompanhamentos. **Segurança:** PIN por ação e CSRF.
**Testes obrigatórios:** autorização, persistência, ordenação e interface. **Evidência de conclusão:** dois autores na mesma linha do tempo.
**Dependências:** depende de MVP-ISSUE-048; desbloqueia 050 e 053; pode ser feita em paralelo com 051; bloqueio técnico: nenhum; ADR antes da implementação: não.
**Objetivo de aprendizagem:** histórico append-only e autoria por evento. **Perguntas para a sessão de aprendizagem:** Por que cada evento tem autor? O original muda? Como ordenar empates?
**Definition of Done da Issue:** [ ] acompanhamento [ ] autoria [ ] testes [ ] tela [ ] docs [ ] PR revisável [ ] sessão possível.

#### MVP-ISSUE-050 — Resolver ocorrência com evento histórico

**Grupo:** 8. Ocorrências.

**Problema:** ocorrência não possui desfecho rastreável.
**Resultado esperado:** qualquer operador ativo identificado registra resolução; estado vigente fecha, preservando criador, acompanhamentos e resolvedor.
**Contexto:** DEC-P-12 permite resolução por pessoa diferente.
**Rastreabilidade:** REQ-MVP-001, REQ-MVP-007, REQ-MVP-010, REQ-MVP-011; DEC-P-12; CS-01, CS-07, CS-10.
**Comportamento atual:** ocorrência permanece aberta. **Mudança esperada:** resolução transacional com texto/motivo definido, autor e horário; repetição/conflito explícitos.
**Critérios de aceite:** outro operador resolve; linha do tempo mantém todos; segunda resolução não sobrescreve; PIN inválido falha; consulta mostra estado fechado.
**Fora de escopo:** reabrir, SLA, aprovação administrativa, apagar.
**Persistência:** evento/estado de resolução, constraint contra dupla resolução. **API:** contratos afetados conforme esta Issue. **Interface:** ação e confirmação. **Segurança:** sessão/PIN e concorrência.
**Testes obrigatórios:** integração, concorrência, HTTP/Security e interface. **Evidência de conclusão:** ocorrência criada por A e resolvida por B.
**Dependências:** depende de MVP-ISSUE-049; desbloqueia 051–053; pode ser feita em paralelo com nenhuma; bloqueio técnico: nenhum; ADR antes da implementação: não.
**Objetivo de aprendizagem:** transição com histórico e concorrência. **Perguntas para a sessão de aprendizagem:** Quem pode resolver? O que impede dupla resolução? Qual autoria fica visível?
**Definition of Done da Issue:** [ ] resolução [ ] histórico [ ] concorrência [ ] testes [ ] docs [ ] PR revisável [ ] sessão possível.

### Grupo 9. Consulta e continuidade

#### MVP-ISSUE-051 — Consultar histórico detalhado de rondas

**Grupo:** 9. Consulta e continuidade.

**Problema:** dados persistidos não formam uma consulta operacional compreensível.
**Resultado esperado:** sessão autorizada filtra/lista rondas e abre detalhe com turno, estado, autor, snapshot, resultados vigentes e ocorrências.
**Contexto:** outro usuário precisa verificar o que aconteceu sem reconstruir tabelas.
**Rastreabilidade:** REQ-MVP-008, REQ-MVP-010; DEC-P-10, DEC-P-11, DEC-P-28, DEC-P-35; CS-04, CS-05, CS-06, CS-07, CS-08.
**Comportamento atual:** consultas fragmentadas. **Mudança esperada:** projeção de histórico com paginação/filtros mínimos definidos pelo Documento Mestre.
**Critérios de aceite:** concluída e não concluída aparecem; detalhe diferencia medição/ausência/condição/ocorrência; usa nomes/contexto históricos; acesso não autorizado é negado.
**Fora de escopo:** BI, exportação, busca industrial avançada, event sourcing.
**Persistência:** índices apenas comprovadamente necessários; sem duplicar fonte. **API:** contratos afetados conforme esta Issue. **Interface:** lista e detalhe responsivos. **Segurança:** escopo de sessão/admin e dados mínimos.
**Testes obrigatórios:** Repository/projeção, HTTP/Security e interface. **Evidência de conclusão:** conjunto de rondas consultável ponta a ponta.
**Dependências:** depende de MVP-ISSUE-031, MVP-ISSUE-043, MVP-ISSUE-046, MVP-ISSUE-050; desbloqueia 052–059; pode ser feita em paralelo com nenhuma; bloqueio técnico: T-02/T-03 aprovadas; ADR antes da implementação: não.
**Objetivo de aprendizagem:** consultas/projeções e paginação. **Perguntas para a sessão de aprendizagem:** O que é dado vigente? Qual contexto é histórico? Onde filtrar?
**Definition of Done da Issue:** [ ] lista/detalhe [ ] filtros mínimos [ ] testes [ ] tela [ ] docs [ ] PR revisável [ ] sessão possível.

#### MVP-ISSUE-052 — Mostrar continuidade do turno em uma visão operacional

**Grupo:** 9. Consulta e continuidade.

**Problema:** o próximo operador não vê rapidamente pendências, rondas encerradas e ocorrências abertas.
**Resultado esperado:** uma visão resume a ocorrência atual/recente do perfil, sem permitir transferência de ronda pendente.
**Contexto:** transforma o histórico em continuidade prática.
**Rastreabilidade:** REQ-MVP-004, REQ-MVP-007, REQ-MVP-008, REQ-MVP-010; DEC-P-04, DEC-P-10, DEC-P-12; CS-06, CS-07.
**Comportamento atual:** histórico detalhado exige abrir itens. **Mudança esperada:** painel mínimo com contagens, responsável, motivo de não conclusão e ocorrências abertas.
**Critérios de aceite:** pendente mostra autor e só ele pode retomar; fim de turno aparece fechado; ocorrência aberta oferece acompanhar/resolver com novo PIN; dados atualizam ao recarregar.
**Fora de escopo:** tempo real via WebSocket, notificações, transferência, dashboard gerencial.
**Persistência:** nenhuma nova salvo índice comprovado. **API:** contratos afetados conforme esta Issue. **Interface:** projeção e tela responsiva. **Segurança:** sessão e autorização por ação.
**Testes obrigatórios:** projeção, HTTP/Security e interface desktop/móvel. **Evidência de conclusão:** cenário de troca de operador demonstrado.
**Dependências:** depende de MVP-ISSUE-044, MVP-ISSUE-046, MVP-ISSUE-051; desbloqueia 053 e 055; pode ser feita em paralelo com 54; bloqueio técnico: nenhum; ADR antes da implementação: não.
**Objetivo de aprendizagem:** read model orientado à tarefa. **Perguntas para a sessão de aprendizagem:** O que o próximo operador precisa ver? Por que ver não autoriza retomar? Como evitar N+1?
**Definition of Done da Issue:** [ ] resumo [ ] regras de ação [ ] testes [ ] responsividade [ ] docs [ ] PR revisável [ ] sessão possível.

#### MVP-ISSUE-053 — Exibir revisões e eventos em linha do tempo

**Grupo:** 9. Consulta e continuidade.

**Problema:** original, correções e eventos de ocorrência existem, mas não são auditáveis em uma sequência clara.
**Resultado esperado:** detalhe mostra revisão vigente e permite abrir histórico com autor, papel, motivo e horário; ocorrência mostra criação, acompanhamentos e resolução.
**Contexto:** comprova preservação sem implementar event sourcing.
**Rastreabilidade:** REQ-MVP-008, REQ-MVP-009, REQ-MVP-010; DEC-P-12, DEC-P-13, DEC-P-28, DEC-P-35; CS-07, CS-08.
**Comportamento atual:** 051 mostra sobretudo o estado vigente. **Mudança esperada:** projeções ordenadas e rótulos que distinguem original, revisão e evento.
**Critérios de aceite:** original nunca some; vigente é inequívoca; autores históricos não mudam após renomeação; admin e operador são distinguidos; ordem é determinística.
**Fora de escopo:** desfazer, editar histórico, event sourcing, assinatura digital.
**Persistência:** usa revisões/eventos existentes; índice se medido necessário. **API:** contratos afetados conforme esta Issue. **Interface:** detalhe/linha do tempo. **Segurança:** consulta autorizada; segredos nunca aparecem.
**Testes obrigatórios:** projeção, ordenação, HTTP/Security e interface. **Evidência de conclusão:** história completa de leitura corrigida e ocorrência resolvida.
**Dependências:** depende de MVP-ISSUE-043, MVP-ISSUE-050, MVP-ISSUE-051; desbloqueia 054–055 e 059; pode ser feita em paralelo com nenhuma; bloqueio técnico: nenhum; ADR antes da implementação: não.
**Objetivo de aprendizagem:** trilha de auditoria e projeção temporal. **Perguntas para a sessão de aprendizagem:** O que é evento e o que é revisão? Como escolher vigente? Por que ordenar por id além do horário?
**Definition of Done da Issue:** [ ] linhas do tempo [ ] testes [ ] tela [ ] nomes preservados [ ] docs [ ] PR revisável [ ] sessão possível.

### Grupo 10. Demonstração reproduzível

#### MVP-ISSUE-054 — Preparar o conjunto fictício reproduzível da demonstração

**Grupo:** 10. Demonstração reproduzível.

**Problema:** fixtures sintéticas incrementais existem para testes isolados, mas ainda não formam um cenário final único, idempotente e reproduzível.
**Resultado esperado:** consolidar as fixtures anteriores em um conjunto final idempotente com dois compressores, um recipiente, pontos aprovados, perfis, operadores e histórico claramente fictício.
**Contexto:** materializa DEC-P-01, DEC-P-02, DEC-P-14, DEC-P-15 sem alegar configuração real da planta.
**Rastreabilidade:** REQ-MVP-002, REQ-MVP-003, REQ-MVP-012; DEC-P-01, DEC-P-02, DEC-P-14, DEC-P-15; CS-02, CS-12.
**Comportamento atual:** existem apenas dados criados por testes/API. **Mudança esperada:** seed/perfil de demo documentado, repetível e separado de produção.
**Critérios de aceite:** executar duas vezes não duplica; três equipamentos e pontos previstos aparecem; nomes exibem caráter fictício; credenciais de demo não entram no Git como segredo real.
**Fora de escopo:** dados reais, substituir os dados mínimos de desenvolvimento por uma função de seed em cada Issue, catálogo industrial, restauração ou ambiente produtivo.
**Persistência:** insere dados via mecanismo escolhido; não altera V1; IDs não são pressupostos. **API:** contratos afetados conforme esta Issue. **Interface:** usa os contratos existentes. **Segurança:** credenciais locais tratadas como demonstração e documentadas fora de segredo real.
**Testes obrigatórios:** idempotência e smoke da preparação. **Evidência de conclusão:** inventário após duas execuções.
**Dependências:** depende de MVP-ISSUE-020, MVP-ISSUE-029, MVP-ISSUE-053; desbloqueia 055–059; pode ser feita em paralelo com nenhuma; bloqueio técnico: nenhum; ADR antes da implementação: não.
**Objetivo de aprendizagem:** dados de referência e ambientes. **Perguntas para a sessão de aprendizagem:** Por que seed deve ser idempotente? Como marcar dado fictício? Onde guardar credencial local?
**Definition of Done da Issue:** [ ] seed [ ] idempotência [ ] inventário [ ] docs [ ] PR revisável [ ] sessão possível.

#### MVP-ISSUE-055 — Consolidar a experiência responsiva e os erros do fluxo completo

**Grupo:** 10. Demonstração reproduzível.

**Problema:** telas incrementais podem ficar inconsistentes ou inutilizáveis no viewport móvel.
**Resultado esperado:** navegação principal, formulários, estados de carregamento e erros críticos funcionam em Chrome desktop e viewport móvel.
**Contexto:** é consolidação baseada em telas reais, não uma implementação tardia de todo frontend.
**Rastreabilidade:** REQ-MVP-001, REQ-MVP-003, REQ-MVP-004, REQ-MVP-005, REQ-MVP-007, REQ-MVP-008, REQ-MVP-009, REQ-MVP-010, REQ-MVP-011; DEC-P-43; CS-01, CS-02, CS-03, CS-04, CS-05, CS-06, CS-07, CS-08, CS-10.
**Comportamento atual:** cada corte possui interface mínima. **Mudança esperada:** padrões visuais/erro coerentes, foco/teclado básicos e nenhuma ação essencial escondida.
**Critérios de aceite:** fluxo login→ronda→leitura→ocorrência→histórico funciona nos dois viewports; erros preservam entrada segura; sem overflow impeditivo; ações destrutivas/terminais são claras.
**Fora de escopo:** design system completo, app nativo, PWA, testes em celular físico, acessibilidade certificada.
**Persistência:** nenhuma. **API:** não cria contrato sem necessidade. **Interface:** consolidação responsiva. **Segurança:** CSRF continua ativo; mensagens não expõem detalhes.
**Testes obrigatórios:** smoke de interface nos viewports definidos e regressão HTTP relevante. **Evidência de conclusão:** roteiro/capturas de ambos.
**Dependências:** depende de MVP-ISSUE-052, MVP-ISSUE-054; desbloqueia 056 e 059; pode ser feita em paralelo com nenhuma; bloqueio técnico: nenhum; ADR antes da implementação: não.
**Objetivo de aprendizagem:** integração frontend/backend e responsividade. **Perguntas para a sessão de aprendizagem:** Qual fluxo prova a MVP? Como erro mantém contexto? O que o viewport móvel revela?
**Definition of Done da Issue:** [ ] fluxo nos dois viewports [ ] erros coerentes [ ] smoke [ ] docs [ ] PR revisável [ ] sessão possível.

#### MVP-ISSUE-056 — Documentar e validar a execução local reproduzível

**Grupo:** 10. Demonstração reproduzível.

**Problema:** outra sessão pode não saber preparar banco, iniciar aplicação e abrir a demo.
**Resultado esperado:** um roteiro único parte dos pré-requisitos, inicia o ambiente e executa smoke sem passos implícitos.
**Contexto:** a demonstração precisa ser repetível antes de backup e fechamento.
**Rastreabilidade:** REQ-MVP-010, REQ-MVP-011, REQ-MVP-012; DEC-P-01, DEC-P-43, DEC-P-45; CS-10, CS-12; ADR-0002, ADR-0003.
**Comportamento atual:** README cobre base técnica, não o fluxo completo futuro. **Mudança esperada:** comandos e ordem atualizados conforme artefatos reais, preservando documentos históricos.
**Critérios de aceite:** checkout limpo configurado inicia com Java/PostgreSQL suportados; migrations aplicam; seed funciona; URL e credenciais locais são claras; smoke percorre o caminho principal.
**Fora de escopo:** deploy, CI nova sem necessidade, cloud, instalação automática de ferramentas.
**Persistência:** nenhuma mudança funcional. **API:** nenhuma mudança funcional. **Interface:** nenhuma mudança funcional. **Segurança:** nenhuma funcionalidade nova; segredos reais ausentes.
**Testes obrigatórios:** execução literal do roteiro em ambiente limpo viável e suíte existente. **Evidência de conclusão:** registro conciso de comandos/resultados.
**Dependências:** depende de MVP-ISSUE-054, MVP-ISSUE-055; desbloqueia 057–059; pode ser feita em paralelo com nenhuma; bloqueio técnico: nenhum; ADR antes da implementação: não.
**Objetivo de aprendizagem:** reprodutibilidade e documentação operacional. **Perguntas para a sessão de aprendizagem:** Quais pré-requisitos são reais? Como provar migration? O que o smoke cobre?
**Definition of Done da Issue:** [ ] roteiro executado [ ] docs atuais [ ] sem segredo [ ] evidência [ ] PR documental revisável [ ] sessão possível.

#### MVP-ISSUE-057 — Gerar e verificar backup local da demonstração

**Grupo:** 10. Demonstração reproduzível.

**Problema:** não há artefato recuperável do estado demonstrativo.
**Resultado esperado:** procedimento local gera backup fora do Git, registra checksum e valida que o arquivo é legível e não contém segredo inesperado.
**Contexto:** DEC-P-45 exige backup até o encerramento da demonstração.
**Rastreabilidade:** REQ-MVP-012; DEC-P-45; CS-11, CS-12; seção 18.2.
**Comportamento atual:** NÃO EXISTE para a MVP. **Mudança esperada:** comando/procedimento de backup com destino ignorado e inventário do conteúdo esperado.
**Critérios de aceite:** arquivo não vazio; formato reconhecido pela ferramenta; checksum registrado; caminho está fora do versionamento; inspeção não encontra credencial que não deveria estar nos dados.
**Fora de escopo:** upload, nuvem, criptografia empresarial, retenção pós-demo.
**Persistência:** lê o banco e cria artefato local; não muda schema. **API:** contratos afetados conforme esta Issue. **Interface:** nenhuma. **Segurança:** inspeção de dados sensíveis e exclusão do Git.
**Testes obrigatórios:** verificação do arquivo e checksum; restauração fica em 058. **Evidência de conclusão:** metadados do backup, não o conteúdo sensível.
**Dependências:** depende de MVP-ISSUE-056; desbloqueia 058–059; pode ser feita em paralelo com nenhuma; bloqueio técnico: nenhum; ADR antes da implementação: não.
**Objetivo de aprendizagem:** backup, checksum e limites de evidência. **Perguntas para a sessão de aprendizagem:** O que o checksum prova? Por que fora do Git? Por que arquivo criado ainda não prova recuperação?
**Definition of Done da Issue:** [ ] backup [ ] checksum [ ] inspeção [ ] ignorado pelo Git [ ] docs [ ] sessão possível.

#### MVP-ISSUE-058 — Restaurar a demonstração em banco separado

**Grupo:** 10. Demonstração reproduzível.

**Problema:** um backup não testado pode ser inutilizável.
**Resultado esperado:** backup de 057 restaura em banco separado; aplicação consulta os registros e o ambiente original não é alterado.
**Contexto:** fornece evidência direta de recuperabilidade.
**Rastreabilidade:** REQ-MVP-012; DEC-P-45; CS-11.
**Comportamento atual:** backup apenas verificado estruturalmente. **Mudança esperada:** procedimento seguro de restauração e conferência de contagens/fluxo.
**Critérios de aceite:** destino é confirmado como separado; restore termina sem erro; três equipamentos, configurações e histórico esperado existem; smoke de leitura funciona; banco fonte permanece intacto.
**Fora de escopo:** disaster recovery produtivo, automação de nuvem, apagar bancos.
**Persistência:** cria banco/volume separado controlado; nenhuma exclusão. **API:** contratos afetados conforme esta Issue. **Interface:** smoke somente. **Segurança:** não divulgar credenciais nem sobrescrever fonte.
**Testes obrigatórios:** restauração real e consultas de integridade. **Evidência de conclusão:** checksum de origem, saída do restore e contagens não sensíveis.
**Dependências:** depende de MVP-ISSUE-057; desbloqueia 059; pode ser feita em paralelo com nenhuma; bloqueio técnico: nenhum; ADR antes da implementação: não.
**Objetivo de aprendizagem:** restauração e prova de backup. **Perguntas para a sessão de aprendizagem:** Como provar isolamento? Que contagens validam? Por que smoke após restore?
**Definition of Done da Issue:** [ ] destino separado [ ] restore [ ] integridade [ ] fonte preservada [ ] docs [ ] sessão possível.

### Grupo 11. Avaliação e fechamento

#### MVP-ISSUE-059 — Executar os critérios de sucesso e fechar a baseline entregue

**Grupo:** 11. Avaliação e fechamento.

**Problema:** funcionalidades isoladas não provam que a MVP completa atende à baseline.
**Resultado esperado:** responsável executa a verificação funcional, registra evidências dos CS-01 a CS-12 e atualiza a documentação final sem alegar avaliação de terceiros.
**Contexto:** DEC-P-42 reserva a avaliação de compreensão por outra pessoa para depois da demonstração inicial.
**Rastreabilidade:** REQ-MVP-001 a REQ-MVP-012; DEC-P-01 a DEC-P-45; CS-01 a CS-12; todas as ADRs aprovadas ou refinadas.
**Comportamento atual:** evidências parciais por Issue. **Mudança esperada:** roteiro integrado, resultados, desvios e estado final dos documentos.
**Critérios de aceite:** cada CS possui aprovado/reprovado e evidência; requisitos têm caminho executado; Chrome desktop/móvel são usados; backup restaurado; limitações fictícias aparecem; CS-09 não afirma compreensão por terceiros.
**Fora de escopo:** corrigir falhas nesta mesma Issue, piloto real, pesquisa com terceiros, implementação nova. Falhas geram Issue pequena para revisão.
**Persistência:** nenhuma mudança funcional. **API:** nenhuma mudança funcional. **Interface:** nenhuma mudança funcional. **Segurança:** nenhuma mudança funcional; somente dados de teste/evidência controlados.
**Testes obrigatórios:** suítes acumuladas e roteiro ponta a ponta; não duplicar testes sem motivo. **Evidência de conclusão:** relatório de aceite ligado à EPIC.
**Dependências:** depende de MVP-ISSUE-055, MVP-ISSUE-058; desbloqueia conclusão da EPIC; pode ser feita em paralelo com nenhuma; bloqueio técnico: todas as decisões aplicáveis devem estar aprovadas; ADR antes da implementação: não.
**Objetivo de aprendizagem:** aceite, evidência e diferença entre teste técnico e compreensão. **Perguntas para a sessão de aprendizagem:** Qual evidência cobre cada CS? O que um teste automatizado não prova? Como registrar falha sem expandir esta Issue?
**Definition of Done da Issue:** [ ] CS avaliados [ ] matriz atualizada [ ] evidências [ ] limitações registradas [ ] nenhuma alegação indevida [ ] EPIC revisável.

## F. Marcos executáveis

| Marco | Issues concluídas | O que passa a ser visível/demonstrável |
|---|---|---|
| A — Equipment consistente | 001–007 | Cadastro valida tamanho, normaliza código, trata duplicidade e aplica T-04 já decidida sem presumir condição física. |
| B — Primeiro acesso e identidade | 008–018 | Após a sessão arquitetural T-01, administrador e turno entram por fluxos distintos; PIN, bloqueio e recuperação podem ser demonstrados. |
| C — Turno e rondas na tela | 021–030, com 024 antes de 025–030 | Fixtures fictícias mínimas permitem configurar horários, gerar rondas previstas e visualizar o turno antes da MVP completa. |
| D — Primeira ronda e primeira coleta | 019–020, 031–038 | Snapshot T-02 é materializado; operador assume e salva/relê valor sintético, ausência e condição; autoria já suporta renomeação/exclusão segura. |
| E — Execução confiável e encerrável | 039–047 | Após a sessão T-03, retry não duplica, correções preservam original e ronda pausa, retoma, conclui ou fecha no fim do turno. |
| F — Ocorrências e continuidade | 048–053 | Outro operador acompanha/responde ocorrência; histórico e linhas do tempo permitem continuidade. |
| G — Demonstração aceita | 054–059 | Dataset final consolidado, fluxo responsivo, execução reproduzível, backup restaurado e CS-01 a CS-12 avaliados. |

Os marcos C e D usam dados sintéticos pequenos produzidos pelas próprias Issues. O usuário começa a interagir com turno, ronda e primeira leitura antes da consolidação do dataset na MVP-ISSUE-054.

## G. Caminho de aprendizagem

```text
Fonte de verdade e contrato HTTP (001–005)
  → sessão de arquitetura T-04 (006)
  → evolução de domínio e migration compatível (007)
  → sessão de arquitetura T-01 (008)
  → Spring Security, sessão, CSRF e login (009–015)
  → política de PIN e ciclo de identidade (016–020)
  → JPA/Flyway, pontos, intervalos e programação (021–023)
  → sessão de arquitetura T-02 e snapshots (024–031)
  → autoria, ronda prevista e concorrência de assunção (032–033)
  → sessão T-05 / ADR-0005 e primeira leitura (034–038)
  → sessão de arquitetura T-03 (039)
  → idempotência, revisões e concorrência (040–043)
  → máquina de estados e fechamento temporal (044–047)
  → ocorrências e autoria por evento (048–050)
  → projeções, paginação e histórico (051–053)
  → dataset fictício consolidado, UX e reprodutibilidade (054–056)
  → backup, restauração, aceite e evidência (057–059)
```

Cada Issue arquitetural termina em revisão e aprendizagem antes da Issue que implementa sua decisão. T-05 refina a ADR-0005; T-01, T-02, T-03 e T-04 registram ADR quando a decisão duradoura exigir.

## H. Matriz de rastreabilidade

| Issue | REQ | DEC-P | CS | ADR/refinamento | Dependências |
|---|---|---|---|---|---|
| MVP-ISSUE-001 | REQ-MVP-001–REQ-MVP-012 | DEC-P-01–DEC-P-45 | — | ADR-0004 | — |
| MVP-ISSUE-002 | REQ-MVP-002, REQ-MVP-011 | — | CS-02, CS-10 | ADR-0002, ADR-0004 | MVP-ISSUE-001 |
| MVP-ISSUE-003 | REQ-MVP-002, REQ-MVP-011 | — | CS-10 | — | MVP-ISSUE-002 |
| MVP-ISSUE-004 | REQ-MVP-002 | DEC-P-44 | CS-02 | ADR-0004 | MVP-ISSUE-003 |
| MVP-ISSUE-005 | REQ-MVP-002, REQ-MVP-011 | DEC-P-44 | CS-02, CS-10 | ADR-0002, ADR-0004 | MVP-ISSUE-004 |
| MVP-ISSUE-006 | REQ-MVP-002, REQ-MVP-006 | — | CS-05 | T-04 / nova ADR | MVP-ISSUE-005 |
| MVP-ISSUE-007 | REQ-MVP-002, REQ-MVP-006, REQ-MVP-011 | — | CS-05, CS-10 | T-04 aprovada | MVP-ISSUE-006 |
| MVP-ISSUE-008 | REQ-MVP-001, REQ-MVP-010, REQ-MVP-011 | DEC-P-07, DEC-P-18, DEC-P-25, DEC-P-26 | CS-01, CS-10, CS-12 | T-01 / ADR se duradoura | MVP-ISSUE-003, MVP-ISSUE-005 |
| MVP-ISSUE-009 | REQ-MVP-001, REQ-MVP-010, REQ-MVP-011 | — | CS-01, CS-10, CS-12 | T-01 aprovada | MVP-ISSUE-008 |
| MVP-ISSUE-010 | REQ-MVP-001, REQ-MVP-003 | DEC-P-07, DEC-P-20, DEC-P-30, DEC-P-31, DEC-P-32 | CS-01 | — | MVP-ISSUE-009 |
| MVP-ISSUE-011 | REQ-MVP-001, REQ-MVP-010 | DEC-P-07, DEC-P-18, DEC-P-31 | CS-01 | T-01 | MVP-ISSUE-010 |
| MVP-ISSUE-012 | REQ-MVP-001, REQ-MVP-011 | DEC-P-25, DEC-P-32 | CS-01, CS-10 | — | MVP-ISSUE-011 |
| MVP-ISSUE-013 | REQ-MVP-001, REQ-MVP-003, REQ-MVP-011 | DEC-P-24, DEC-P-26 | CS-01, CS-10 | T-01 | MVP-ISSUE-011 |
| MVP-ISSUE-014 | REQ-MVP-001, REQ-MVP-009, REQ-MVP-011 | DEC-P-07, DEC-P-09, DEC-P-19, DEC-P-37, DEC-P-38 | CS-01, CS-08, CS-10 | — | MVP-ISSUE-009 |
| MVP-ISSUE-015 | REQ-MVP-001, REQ-MVP-004, REQ-MVP-007, REQ-MVP-009 | DEC-P-07, DEC-P-09, DEC-P-41 | CS-01, CS-02, CS-07, CS-08 | — | MVP-ISSUE-011, MVP-ISSUE-014 |
| MVP-ISSUE-016 | REQ-MVP-001, REQ-MVP-011 | DEC-P-19, DEC-P-39 | CS-01, CS-10 | refinamento produto/técnico | MVP-ISSUE-015 |
| MVP-ISSUE-017 | REQ-MVP-001, REQ-MVP-011 | DEC-P-39, DEC-P-40 | CS-01, CS-10 | — | MVP-ISSUE-016 |
| MVP-ISSUE-018 | REQ-MVP-001, REQ-MVP-011 | DEC-P-33, DEC-P-34 | CS-01, CS-10 | — | MVP-ISSUE-015, MVP-ISSUE-016 |
| MVP-ISSUE-019 | REQ-MVP-001, REQ-MVP-008, REQ-MVP-009 | DEC-P-35 | CS-01, CS-08 | — | MVP-ISSUE-015, MVP-ISSUE-032 |
| MVP-ISSUE-020 | REQ-MVP-001, REQ-MVP-008, REQ-MVP-009, REQ-MVP-011 | DEC-P-33, DEC-P-36 | CS-01, CS-08, CS-10 | — | MVP-ISSUE-019, MVP-ISSUE-032 |
| MVP-ISSUE-021 | REQ-MVP-002, REQ-MVP-003 | DEC-P-02, DEC-P-14, DEC-P-15 | CS-02 | ADR-0001 | MVP-ISSUE-005 |
| MVP-ISSUE-022 | REQ-MVP-003 | DEC-P-05, DEC-P-06, DEC-P-08, DEC-P-16, DEC-P-20, DEC-P-21 | CS-02 | — | MVP-ISSUE-010 |
| MVP-ISSUE-023 | REQ-MVP-003, REQ-MVP-011 | DEC-P-17, DEC-P-21 | CS-02, CS-10 | — | MVP-ISSUE-022 |
| MVP-ISSUE-024 | REQ-MVP-003, REQ-MVP-004, REQ-MVP-005, REQ-MVP-006, REQ-MVP-008 | DEC-P-11, DEC-P-28, DEC-P-35 | CS-02, CS-03, CS-05, CS-08 | T-02 / nova ADR | MVP-ISSUE-007, MVP-ISSUE-021, MVP-ISSUE-023 |
| MVP-ISSUE-025 | REQ-MVP-003, REQ-MVP-004, REQ-MVP-008 | DEC-P-11 | CS-02, CS-06, CS-08 | T-02 aprovada | MVP-ISSUE-023, MVP-ISSUE-024 |
| MVP-ISSUE-026 | REQ-MVP-003, REQ-MVP-004 | DEC-P-05, DEC-P-08, DEC-P-10, DEC-P-16, DEC-P-20, DEC-P-21, DEC-P-22, DEC-P-23 | CS-02, CS-06 | T-02 | MVP-ISSUE-013, MVP-ISSUE-025 |
| MVP-ISSUE-027 | REQ-MVP-003, REQ-MVP-008, REQ-MVP-011 | DEC-P-24, DEC-P-27 | CS-02, CS-08, CS-10 | — | MVP-ISSUE-013, MVP-ISSUE-026 |
| MVP-ISSUE-028 | REQ-MVP-003, REQ-MVP-008 | DEC-P-11, DEC-P-28 | CS-02, CS-08 | — | MVP-ISSUE-024, MVP-ISSUE-026 |
| MVP-ISSUE-029 | REQ-MVP-003, REQ-MVP-008, REQ-MVP-011 | DEC-P-24, DEC-P-29 | CS-02, CS-08, CS-10 | — | MVP-ISSUE-026 |
| MVP-ISSUE-030 | REQ-MVP-001, REQ-MVP-003, REQ-MVP-004, REQ-MVP-010 | DEC-P-05, DEC-P-06, DEC-P-07, DEC-P-20, DEC-P-21, DEC-P-22, DEC-P-23 | CS-01, CS-02, CS-06 | — | MVP-ISSUE-011, MVP-ISSUE-026 |
| MVP-ISSUE-031 | REQ-MVP-003, REQ-MVP-004, REQ-MVP-005, REQ-MVP-006, REQ-MVP-008 | DEC-P-11, DEC-P-28, DEC-P-35 | CS-02, CS-03, CS-05, CS-08 | T-02 aprovada | MVP-ISSUE-007, MVP-ISSUE-021, MVP-ISSUE-024, MVP-ISSUE-025, MVP-ISSUE-026 |
| MVP-ISSUE-032 | REQ-MVP-001, REQ-MVP-004 | DEC-P-04, DEC-P-06, DEC-P-09, DEC-P-23, DEC-P-35 | CS-01, CS-02, CS-06 | — | MVP-ISSUE-015, MVP-ISSUE-030, MVP-ISSUE-031 |
| MVP-ISSUE-033 | REQ-MVP-004, REQ-MVP-011 | DEC-P-04, DEC-P-09 | CS-02, CS-10 | T-03 | MVP-ISSUE-032 |
| MVP-ISSUE-034 | REQ-MVP-005, REQ-MVP-006, REQ-MVP-009 | DEC-P-03, DEC-P-15 | CS-03, CS-04, CS-05, CS-08 | T-05 / refinar ADR-0005 | MVP-ISSUE-031 |
| MVP-ISSUE-035 | REQ-MVP-004, REQ-MVP-005, REQ-MVP-010 | DEC-P-04, DEC-P-09, DEC-P-15 | CS-02, CS-03 | ADR-0005 | MVP-ISSUE-032, MVP-ISSUE-034 |
| MVP-ISSUE-036 | REQ-MVP-005 | DEC-P-01, DEC-P-11, DEC-P-15 | CS-03 | ADR-0005 | MVP-ISSUE-035 |
| MVP-ISSUE-037 | REQ-MVP-005, REQ-MVP-010 | DEC-P-03 | CS-04 | ADR-0005 | MVP-ISSUE-035, MVP-ISSUE-036 |
| MVP-ISSUE-038 | REQ-MVP-006 | DEC-P-15 | CS-05 | T-04, T-05 | MVP-ISSUE-007, MVP-ISSUE-034, MVP-ISSUE-035 |
| MVP-ISSUE-039 | REQ-MVP-005, REQ-MVP-009, REQ-MVP-011 | DEC-P-13 | CS-03, CS-08, CS-10 | T-03 / ADR se duradoura | MVP-ISSUE-034, MVP-ISSUE-035 |
| MVP-ISSUE-040 | REQ-MVP-005, REQ-MVP-011 | — | CS-03, CS-10 | T-03 aprovada | MVP-ISSUE-035, MVP-ISSUE-039 |
| MVP-ISSUE-041 | REQ-MVP-009, REQ-MVP-010 | DEC-P-13 | CS-08 | T-03 | MVP-ISSUE-015, MVP-ISSUE-040 |
| MVP-ISSUE-042 | REQ-MVP-001, REQ-MVP-009, REQ-MVP-010 | DEC-P-13 | CS-01, CS-08 | — | MVP-ISSUE-009, MVP-ISSUE-041 |
| MVP-ISSUE-043 | REQ-MVP-009, REQ-MVP-011 | DEC-P-13 | CS-08, CS-10 | T-03 | MVP-ISSUE-041, MVP-ISSUE-042 |
| MVP-ISSUE-044 | REQ-MVP-004, REQ-MVP-010 | DEC-P-04, DEC-P-10 | CS-06 | — | MVP-ISSUE-032, MVP-ISSUE-035 |
| MVP-ISSUE-045 | REQ-MVP-004, REQ-MVP-005, REQ-MVP-010 | DEC-P-03, DEC-P-04, DEC-P-22 | CS-04, CS-06 | — | MVP-ISSUE-037, MVP-ISSUE-040, MVP-ISSUE-044 |
| MVP-ISSUE-046 | REQ-MVP-003, REQ-MVP-004, REQ-MVP-010, REQ-MVP-011 | DEC-P-10, DEC-P-21, DEC-P-22, DEC-P-23 | CS-06, CS-10 | — | MVP-ISSUE-026, MVP-ISSUE-044, MVP-ISSUE-045 |
| MVP-ISSUE-047 | REQ-MVP-004, REQ-MVP-011 | DEC-P-03, DEC-P-04, DEC-P-10, DEC-P-22, DEC-P-23 | CS-06, CS-10 | — | MVP-ISSUE-033, MVP-ISSUE-044, MVP-ISSUE-045, MVP-ISSUE-046 |
| MVP-ISSUE-048 | REQ-MVP-007, REQ-MVP-010 | DEC-P-09, DEC-P-12 | CS-07 | — | MVP-ISSUE-015, MVP-ISSUE-038, MVP-ISSUE-047 |
| MVP-ISSUE-049 | REQ-MVP-001, REQ-MVP-007, REQ-MVP-010 | DEC-P-12, DEC-P-35 | CS-01, CS-07 | — | MVP-ISSUE-048 |
| MVP-ISSUE-050 | REQ-MVP-001, REQ-MVP-007, REQ-MVP-010, REQ-MVP-011 | DEC-P-12 | CS-01, CS-07, CS-10 | — | MVP-ISSUE-049 |
| MVP-ISSUE-051 | REQ-MVP-008, REQ-MVP-010 | DEC-P-10, DEC-P-11, DEC-P-28, DEC-P-35 | CS-04, CS-05, CS-06, CS-07, CS-08 | — | MVP-ISSUE-031, MVP-ISSUE-043, MVP-ISSUE-046, MVP-ISSUE-050 |
| MVP-ISSUE-052 | REQ-MVP-004, REQ-MVP-007, REQ-MVP-008, REQ-MVP-010 | DEC-P-04, DEC-P-10, DEC-P-12 | CS-06, CS-07 | — | MVP-ISSUE-044, MVP-ISSUE-046, MVP-ISSUE-051 |
| MVP-ISSUE-053 | REQ-MVP-008, REQ-MVP-009, REQ-MVP-010 | DEC-P-12, DEC-P-13, DEC-P-28, DEC-P-35 | CS-07, CS-08 | — | MVP-ISSUE-043, MVP-ISSUE-050, MVP-ISSUE-051 |
| MVP-ISSUE-054 | REQ-MVP-002, REQ-MVP-003, REQ-MVP-012 | DEC-P-01, DEC-P-02, DEC-P-14, DEC-P-15 | CS-02, CS-12 | — | MVP-ISSUE-020, MVP-ISSUE-029, MVP-ISSUE-053 |
| MVP-ISSUE-055 | REQ-MVP-001, REQ-MVP-003, REQ-MVP-004, REQ-MVP-005, REQ-MVP-007, REQ-MVP-008, REQ-MVP-009, REQ-MVP-010, REQ-MVP-011 | DEC-P-43 | CS-01, CS-02, CS-03, CS-04, CS-05, CS-06, CS-07, CS-08, CS-10 | — | MVP-ISSUE-052, MVP-ISSUE-054 |
| MVP-ISSUE-056 | REQ-MVP-010, REQ-MVP-011, REQ-MVP-012 | DEC-P-01, DEC-P-43, DEC-P-45 | CS-10, CS-12 | ADR-0002, ADR-0003 | MVP-ISSUE-054, MVP-ISSUE-055 |
| MVP-ISSUE-057 | REQ-MVP-012 | DEC-P-45 | CS-11, CS-12 | — | MVP-ISSUE-056 |
| MVP-ISSUE-058 | REQ-MVP-012 | DEC-P-45 | CS-11 | — | MVP-ISSUE-057 |
| MVP-ISSUE-059 | REQ-MVP-001–REQ-MVP-012 | DEC-P-01–DEC-P-45 | CS-01–CS-12 | T-01–T-05 e ADRs aprovadas | MVP-ISSUE-055, MVP-ISSUE-058 |

## I. Verificação de cobertura

### Requisitos sem Issue

Nenhum. REQ-MVP-001 a REQ-MVP-012 possuem incremento funcional e caminho de validação.

### Critérios de sucesso sem caminho de implementação ou validação

Nenhum. CS-01 a CS-12 aparecem em Issues funcionais e são reexecutados na MVP-ISSUE-059. Para CS-09, a evidência registra a verificação funcional pelo próprio responsável; a avaliação de compreensão por outra pessoa permanece como não realizada, conforme DEC-P-42.

### Decisões de produto ignoradas

Nenhuma. DEC-P-01 a DEC-P-45 continuam na baseline e aparecem somente onde afetam comportamento ou aceite. Nenhuma DEC-P nova foi criada.

### Funcionalidades adicionadas sem origem no Documento Mestre

Nenhuma. Fixtures sintéticas mínimas são dados de teste/demonstração das próprias Issues; não constituem funções novas. O dataset final permanece na MVP-ISSUE-054. Offline, PWA, IoT, IA, frontend separado, microserviços, login individual e escala 6x2 completa permanecem fora.

### Resultado deste refinamento

- **Quantidade anterior:** 52 Issues.
- **Quantidade nova:** 59 Issues.
- **Issues adicionadas por separação arquitetural:** novas Issues de decisão MVP-ISSUE-006 (T-04), MVP-ISSUE-008 (T-01), MVP-ISSUE-024 (T-02) e MVP-ISSUE-039 (T-03), seguidas respectivamente pelas implementações MVP-ISSUE-007, MVP-ISSUE-009, MVP-ISSUE-031 e MVP-ISSUE-040.
- **Issues divididas por granularidade:** antiga MVP-ISSUE-017 em MVP-ISSUE-019/020; antiga MVP-ISSUE-022 em MVP-ISSUE-027/028/029.
- **Regra removida por não estar aprovada:** reset das tentativas de PIN após sucesso não foi assumido; permanece refinamento pendente da futura MVP-ISSUE-016.
- **Decisões novas criadas:** nenhuma.
- **Código alterado:** nenhum.
- **Issues reais do GitHub criadas:** nenhuma.

## J. Riscos e refinamentos técnicos antes da implementação

| Tema | Onde resolver | Tratamento exigido antes de implementar |
|---|---|---|
| T-04 — `Equipment.status` legado | decisão 006 → implementação 007 | Aprovar significado, coluna/nulabilidade, linhas antigas, DTO/API e relação com condição; depois implementar sem reabrir a decisão. |
| T-01 — mesma origem, sessão, CSRF e revogação | decisão 008 → implementação 009 e acessos 010–015 | Aprovar arquitetura e ADR quando duradoura; só então configurar Spring Security e os logins. |
| Política de acumulação de PIN | refinamento bloqueador da 016 | Responder se sucesso antes do quinto erro zera falhas ou se a contagem permanece até outra condição. Não há DEC-P nova nem comportamento presumido. |
| T-02 — contexto congelado | decisão 024 → implementação 025/031 e uso 032/051 | Aprovar momento, campos, vigência, nomes históricos e limite de duplicação antes de migrations/snapshot. |
| T-05 — medição | decisão 034 → implementação 035–038 | Refinar ADR-0005 apenas para decimal, precisão/escala, XOR, unidade, fonte, horários, snapshot e fronteira de revisão. |
| T-03 — retry, revisão e concorrência | decisão 039 → implementação 040–043 | Aprovar identidade da intenção, semântica de chave, versão vigente e limite de auditoria antes da idempotência/revisões. |
| Fechamento no instante exato | 046 | Refinar relógio/fuso, agendamento e defesa síncrona; ADR somente se introduzir infraestrutura transversal. |
| Dados fictícios incrementais | 021/022/026/035; consolidação 054 | Usar somente exemplos mínimos, nunca dados reais; não criar um seed funcional por Issue; consolidar uma vez na 054. |
| Endpoints e migrations | em cada Issue de implementação | Definir nomes durante o refinamento; não reservar números Flyway e nunca editar V1. |
| ADR-0004 e contexto datado | 001 | Corrigir o fato atual sem reescrever documentos históricos nem refazer `EquipmentResponse`. |
| Lacunas do piloto real | fora deste backlog | Unidades oficiais, faixas, procedimentos, conectividade e validação por terceiros permanecem para etapa posterior. |

Este documento é planejamento. Depois da revisão final, cada Issue real deverá ser criada e aprovada individualmente antes da implementação. Nenhum identificador provisório autoriza código, branch, PR, merge ou push.

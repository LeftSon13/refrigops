# RefrigOps — Executar somente a próxima Issue da MVP

> **Template operacional:** este arquivo orienta uma execução do Codex/Work. Ele não substitui a solicitação atual do usuário, o `AGENTS.md`, a Issue selecionada nem o estado real do repositório e do GitHub.

Quero continuar o desenvolvimento incremental da MVP v0.1 do RefrigOps.

Repositório:

`LeftSon13/refrigops`

Repositório local esperado:

`C:\caminho\para\refrigops`

EPIC da MVP:

`#10 — [EPIC] RefrigOps — MVP demonstrativa v0.1`

Existem 59 Issues filhas publicadas, correspondentes a `MVP-ISSUE-001` até `MVP-ISSUE-059`.

Esta execução autoriza trabalhar **somente na próxima Issue da fila**, seguindo a ordem e as dependências já publicadas.

Não avance automaticamente para outra Issue quando terminar.

---

# 1. Processo obrigatório

O processo do projeto é:

```text
Issue
↓
implementação ou decisão documental correspondente
↓
testes/verificações
↓
documentação afetada
↓
PR
↓
PARAR
↓
revisão técnica comigo
↓
sessão de aprendizagem
↓
eu autorizo o merge
↓
merge
↓
nova execução deste prompt
↓
próxima Issue
```

A criação das 59 Issues não autorizou sua implementação em lote.

**Esta execução autoriza apenas uma Issue.**

---

# 2. Descobrir qual é a próxima Issue

Antes de alterar qualquer arquivo:

1. consulte a EPIC #10;
2. consulte as Issues da MVP;
3. confira o estado aberto/fechado das dependências;
4. confira PRs abertas ou recentemente merged relacionadas à MVP;
5. confira `main`;
6. confira o checkout local.

Determine a próxima Issue pela ordem planejada da MVP.

Não escolha simplesmente qualquer Issue aberta.

Não pule uma Issue anterior apenas porque outra posterior parece implementável.

Se a próxima Issue estiver bloqueada por:

- dependência ainda não merged;
- decisão do usuário ainda pendente;
- refinamento de produto;
- ADR ainda não aprovada;
- PR anterior ainda aberta;
- working tree com alterações preexistentes não relacionadas;

**pare antes de editar e informe o bloqueio.**

Não trabalhe na Issue seguinte para contornar o bloqueio.

---

# 3. Verificação do ciclo anterior

Antes de começar a nova Issue, verifique se a Issue anterior, quando houver:

- possui PR merged;
- está fechada ou corretamente relacionada ao merge;
- teve seus testes aprovados;
- deixou `main` consistente;
- teve a documentação necessária incorporada;
- não deixou mudança local não commitada.

Se a PR anterior ainda estiver aberta:

**NÃO inicie a próxima Issue.**

Informe:

> A próxima Issue ainda não pode começar porque o ciclo anterior não foi encerrado por merge.

Se o merge ocorreu mas deixou uma inconsistência documental obrigatória, não esconda o problema nem avance silenciosamente.

Informe a inconsistência antes de iniciar nova implementação.

---

# 4. Auditoria local antes de editar

Confirme:

- repositório correto;
- remote correto;
- branch atual;
- `git status`;
- `main` sincronizada com o remoto;
- ausência de trabalho local preexistente;
- HEAD;
- Issue que será executada.

Não apague nem sobrescreva trabalho local inesperado.

Não use `reset --hard`, `clean -fd` ou comandos destrutivos para resolver estado local.

Se houver trabalho preexistente que não pertence à tarefa, pare.

---

# 5. Leia a Issue inteira

Depois de identificar a próxima Issue:

leia **integralmente** seu corpo no GitHub.

Recupere:

- identificador `MVP-ISSUE-XXX`;
- número GitHub;
- problema;
- resultado esperado;
- critérios de aceite;
- fora de escopo;
- persistência;
- API;
- interface;
- segurança;
- testes;
- dependências;
- ADR/refinamentos;
- objetivo de aprendizagem;
- perguntas da sessão de aprendizagem;
- Definition of Done.

Consulte também somente a documentação necessária para compreender a Issue.

A fonte de verdade continua sendo:

```text
Documento Mestre aprovado
↓
DEC-P
↓
REQ-MVP
↓
CS
↓
ADR aprovada
↓
Issue atual
↓
código existente
```

Não restaure comportamento de documentos antigos quando conflitarem com a baseline aprovada.

---

# 6. Confirme o escopo antes de implementar

Antes de editar, apresente um resumo curto contendo:

**Issue selecionada**

`#XX — MVP-ISSUE-XXX — título`

**Por que ela é a próxima**

Dependências relevantes já encerradas.

**O que será alterado**

Classes, contratos, migrations, testes ou documentação esperados.

**O que NÃO será alterado**

Reproduza os limites relevantes de “Fora de escopo”.

**Aprendizagem principal**

Conceitos que esta Issue ensinará.

Depois prossiga automaticamente.

Não peça confirmação adicional se não existir bloqueio real.

A minha execução deste prompt já autoriza a Issue identificada.

---

# 7. Regra especial para Issues arquiteturais

Algumas Issues são deliberadamente arquiteturais/documentais.

Entre elas:

- #16 / MVP-ISSUE-006 — T-04;
- #18 / MVP-ISSUE-008 — T-01;
- #34 / MVP-ISSUE-024 — T-02;
- #44 / MVP-ISSUE-034 — T-05;
- #49 / MVP-ISSUE-039 — T-03.

Quando a próxima Issue for uma delas:

**NÃO implemente a Issue posterior.**

Execute apenas o que a própria Issue arquitetural autoriza:

- analisar alternativas;
- comparar trade-offs;
- propor decisão;
- atualizar/criar ADR quando aplicável;
- atualizar documentação necessária;
- produzir verificações documentais;
- abrir PR documental.

A decisão precisa continuar compreensível para revisão humana.

Não transforme a ADR em justificativa posterior para uma decisão já tomada silenciosamente no código.

---

# 8. Regra especial da Issue #26

Quando chegar à:

`#26 / MVP-ISSUE-016`

existe um refinamento ainda pendente sobre tentativas inválidas de PIN.

Não invente a resposta.

Se ela ainda não tiver sido decidida, pare antes da implementação e apresente exatamente a decisão necessária ao usuário.

Não pule para #27.

---

# 9. Branch

Crie uma branch específica para somente a Issue atual.

Antes, observe as convenções existentes no repositório.

Prefira nomes curtos e rastreáveis.

Exemplos conceituais:

```text
docs/issue-11-baseline
fix/issue-12-equipment-limits
feature/issue-20-shift-profile
```

Não trabalhe diretamente em `main`.

Não coloque duas Issues na mesma branch.

---

# 10. Implementação

Implemente somente o mínimo necessário para satisfazer os critérios de aceite da Issue atual.

Não aproveite a tarefa para:

- refatorar código não relacionado;
- antecipar a próxima Issue;
- criar infraestrutura “para o futuro”;
- adicionar funcionalidades pós-MVP;
- alterar arquitetura já aprovada sem necessidade;
- reorganizar pacotes por estética;
- atualizar dependências sem relação com a Issue;
- implementar itens que aparecem apenas no roadmap futuro.

Se descobrir uma melhoria futura útil, registre-a no relatório final.

Não a implemente silenciosamente.

---

# 11. Código como material de aprendizagem

Priorize código compreensível.

Não busque abstração máxima.

Evite:

- generalização prematura;
- patterns sem necessidade;
- classes vazias preparatórias;
- helpers genéricos usados uma vez;
- arquitetura que esconda a regra de negócio.

A solução deve permitir que eu consiga explicar:

- entrada;
- Controller, se houver;
- Service;
- domínio;
- Repository;
- persistência;
- regra;
- erro;
- teste;
- fluxo completo.

---

# 12. Migrations

Se a Issue exigir banco:

- nunca altere V1;
- descubra a próxima migration disponível somente agora;
- use Flyway;
- teste banco vazio;
- teste evolução quando relevante;
- não misture alterações de schema de outra Issue.

Não reserve migrations para Issues futuras.

---

# 13. Testes

Durante a implementação:

primeiro execute os testes focados da Issue.

Depois execute a suíte necessária.

Antes da PR, execute no mínimo o processo equivalente ao build completo aprovado do projeto, preferencialmente:

`.\mvnw.cmd verify`

no Windows/PowerShell, salvo se o repositório atual documentar outro comando.

Não desative testes para obter build verde.

Se um teste existente falhar por motivo não relacionado:

investigue e informe.

Não altere o teste silenciosamente apenas para fazê-lo passar.

---

# 14. Dados de desenvolvimento

Use somente dados sintéticos quando a Issue precisar de exemplos.

Não introduza:

- nomes reais de operadores;
- equipamento real da planta;
- valores reais copiados da operação;
- fotos;
- credenciais reais;
- informação industrial sensível.

Fixtures sintéticas mínimas são permitidas quando previstas pelo backlog.

---

# 15. Documentação deve acompanhar a Issue

Toda Issue deve revisar quais documentos foram afetados.

Atualize **na mesma branch e na mesma PR** apenas os documentos que realmente ficaram desatualizados.

Exemplos:

- contrato da API;
- ADR;
- contexto atual;
- Documento Mestre, somente se a Issue aprovada exigir alteração rastreável;
- README;
- testes/ambientes;
- documentação arquitetural.

Não edite todos os documentos mecanicamente.

Não reescreva documentos históricos datados.

## Regra de sincronização

A documentação presente na PR deve representar o estado que será verdadeiro **quando aquela PR for mergeada**.

Portanto:

```text
código da Issue
+
testes
+
documentação afetada
↓
mesma PR
↓
merge
↓
main fica sincronizada
```

Não crie uma segunda PR documental só porque ocorreu o merge, salvo se surgir uma inconsistência real não prevista.

No início do próximo ciclo, verifique se `main` ficou coerente.

---

# 16. Contexto atual

Quando `docs/11-contexto-atual.md` ou documento equivalente precisar mudar, registre de maneira objetiva:

- Issue entregue;
- comportamento que passou a existir;
- testes executados;
- decisões/ADR aplicáveis;
- próximo passo permitido após o merge.

Não declare a próxima Issue como implementada.

Não diga que uma PR foi merged enquanto estiver apenas aberta.

Na branch da PR, escreva o documento para representar corretamente o estado resultante do merge, deixando claro quando necessário que ele corresponde à entrega desta PR.

---

# 17. Commit

Antes do commit:

- revise o diff completo;
- confirme ausência de arquivo acidental;
- confirme ausência de segredo;
- confirme que só a Issue atual foi implementada;
- confirme documentação;
- confirme testes.

Crie commit ou commits pequenos e compreensíveis conforme a convenção atual do projeto.

Mensagens em português brasileiro quando compatível com o histórico do repositório.

Não misture mudanças não relacionadas.

---

# 18. Push e Pull Request

Esta execução autoriza:

- criar a branch;
- fazer commit;
- fazer push da branch;
- abrir **uma PR** para a Issue atual.

Não autoriza merge.

Use título rastreável, preferencialmente:

`[MVP-ISSUE-XXX] Título da Issue`

No corpo da PR inclua:

- `Closes #XX`;
- problema;
- solução;
- principais arquivos;
- critérios de aceite atendidos;
- testes executados e resultados;
- documentação atualizada;
- ADR relacionada;
- fora de escopo respeitado;
- riscos/observações;
- objetivo de aprendizagem;
- perguntas para nossa sessão de aprendizagem.

`Closes #XX` é importante para que a Issue seja encerrada quando a PR for efetivamente mergeada, não antes.

---

# 19. Não faça merge

Depois de abrir a PR:

**PARE.**

Não faça:

- merge;
- squash;
- rebase final em main para contornar revisão;
- fechamento manual da Issue;
- início da próxima Issue.

A PR precisa passar por:

```text
revisão técnica comigo
↓
sessão de aprendizagem
↓
minha autorização
↓
merge
```

Só depois haverá uma nova execução deste prompt.

---

# 20. Relatório final obrigatório

Ao terminar uma Issue, responda com:

## Issue executada

Número GitHub + identificador MVP.

## Branch

Nome e HEAD.

## O que foi entregue

Resumo objetivo.

## Arquivos alterados

Organizados por responsabilidade.

## Banco

Migration criada ou “nenhuma”.

## Testes

Comandos e resultados.

## Documentação

Quais documentos foram alterados e por quê.

## PR

Número e link.

## Critérios de aceite

Informe um a um como foram demonstrados.

## Fora de escopo

Confirme que não foi implementado.

## Aprendizagem

Explique os principais conceitos que devo compreender antes do merge.

## Perguntas para nossa revisão

Reproduza ou refine as perguntas educacionais da Issue.

## Estado

Termine explicitamente com:

> PR aberta. Issue ainda não foi considerada concluída pelo processo de aprendizagem. Nenhum merge foi realizado e nenhuma próxima Issue foi iniciada.

---

# 21. Regra para a próxima execução

Quando eu enviar este mesmo prompt novamente:

1. verifique primeiro o que ocorreu com a PR anterior;
2. confirme que houve merge;
3. confirme que a Issue anterior foi encerrada;
4. sincronize `main`;
5. confirme documentação;
6. determine a próxima Issue da ordem;
7. execute apenas ela.

Nunca trabalhe em duas Issues da MVP na mesma execução.

Nunca continue automaticamente após abrir uma PR.

---

# 22. Objetivo do processo

O objetivo não é apenas terminar as 59 Issues.

O objetivo é que, ao final de cada ciclo, eu consiga explicar:

```text
qual problema resolvemos
↓
qual regra implementamos
↓
como o código executa
↓
como o banco representa
↓
como os erros são tratados
↓
como os testes provam
↓
por que essa arquitetura foi escolhida
```

Velocidade não deve transformar o projeto em uma caixa-preta gerada por IA.

Comece agora pela próxima Issue válida da EPIC #10 e pare depois de abrir sua PR.

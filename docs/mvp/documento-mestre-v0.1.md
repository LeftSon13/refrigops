# RefrigOps — Documento Mestre da MVP v0.1

**Data:** 07/09/2026.
**Estado:** documentação da MVP aprovada; não cria Issues nem autoriza implementação por si só.
**Primeira entrega:** demonstração funcional com dados fictícios.
**Tipos incluídos:** compressores e recipientes.
**Checkpoint técnico:** auditoria concluída nesta conversa sobre um checkpoint pré-rewrite (equivalente saneado: `76b7ca4316419dd66dc902b5b3271fce57fd9c19`).

## Como usar este documento

Esta é a baseline documental aprovada da MVP. Ela define produto, escopo e regras funcionais, mas não descreve funcionalidades já implementadas nem autoriza implementação automática. Propostas técnicas e nomes candidatos continuam sujeitos ao refinamento e à aprovação da Issue correspondente.

- **CONFIRMADO — USUÁRIO:** decisão tomada expressamente nesta etapa.
- **CONFIRMADO — CHECKPOINT:** resultado da auditoria anterior, aceita como base desta tarefa.
- **CONFIRMADO — DOCUMENTAÇÃO:** relato ou princípio registrado nos documentos existentes; não equivale a comprovação técnica da planta.
- **PROPOSTA PARA MVP:** detalhe ainda aberto para refinamento técnico na Issue correspondente; não substitui as decisões de produto aprovadas.
- **VALIDAÇÃO OPERACIONAL NECESSÁRIA:** exige operador, supervisão ou profissional técnico responsável.

As decisões de produto e o escopo da MVP estão aprovados. Propostas técnicas explicitamente abertas permanecem candidatas para refinamento na Issue correspondente, e validações operacionais continuam destinadas a um futuro piloto real. Nomes em inglês são candidatos para implementação; não são enums aprovados. Não foram criados código de produção, migrations, Issues, branches ou ADRs.

## 1. Visão e definição da primeira MVP

**A primeira MVP aprovada é uma aplicação web responsiva em que uma equipe acessa o perfil compartilhado de seu turno, um operador se identifica quando uma ação exige autoria individual, executa rondas manuais por compressores e recipientes fictícios, registra leituras ou ausências justificadas, registra ocorrências, conclui ou deixa uma ronda pendente para retomá-la durante o turno e permite a consulta do histórico e da continuidade.**

**CONFIRMADO — USUÁRIO:** começar por demonstração com dados fictícios; incluir recipientes junto dos compressores na primeira ronda.

A demonstração inicial valida o comportamento do software por verificação funcional do próprio responsável. A compreensão por outra pessoa só poderá ser declarada quando houver uma avaliação posterior com esse participante. A demonstração não comprova adoção por operadores reais, compatibilidade com dispositivos da planta ou melhoria operacional; essas conclusões exigirão um piloto posterior autorizado.

O RefrigOps continua sendo apoio informacional. Nenhum botão, estado, ocorrência ou encerramento de ronda comanda, libera ou bloqueia equipamentos.

## 2. Problema e processo atual

**CENÁRIO PÚBLICO ABSTRAÍDO:** a demonstração parte de um cenário inteiramente fictício de coleta manual. A ordem do roteiro, frequência, origem das medições e tratamento de ausências são configuráveis e não representam o processo de uma instalação específica.

Leituras e continuidade entre turnos são conceitos distintos. No cenário fictício, uma ocorrência pode receber eventos posteriores de acompanhamento ou resolução sem apagar o registro original.

**Problema recortado:** recuperar, de forma compreensível, o que foi observado em uma ronda, o que não foi coletado e por quê, quem registrou e quais ocorrências continuam abertas.

**Parte digitalizada:** identificação, percurso demonstrativo, resultados de coleta, ocorrências relacionadas, encerramento, consulta e correções rastreáveis. A MVP não reproduz documentos internos reais, nem substitui comunicação e procedimentos oficiais.

Fundamentos públicos: [documento mestre do produto](../00-documento-mestre-produto.md), [riscos e limites](../05-riscos-seguranca-e-limites.md) e [guia de revisão e lacunas](../12-guia-revisao-e-lacunas.md).

## 3. Usuários, responsabilidades e identidade

### 3.1 Usuários da demonstração

- **Operadores A, B e C fictícios:** não precisam de login individual para o uso cotidiano. A equipe entra pelo perfil compartilhado do turno; qualquer operador pode assumir uma ronda ainda não iniciada e se identifica quando a ação exige autoria. Quem inicia torna-se autor daquela execução, registra leituras, ausências e ocorrências e pode retomá-la enquanto estiver pendente no turno.
- **Na avaliação de continuidade:** A pode atuar como autor e B como consulente, sem transformar essas funções em atribuições fixas; B também pode acrescentar acompanhamento a uma ocorrência aberta.
- **Administrador da demonstração:** possui acesso administrativo próprio e prepara identidades de operadores, o pequeno cadastro e a quantidade configurável de perfis de turno, cada um com sua programação reutilizável de horários e meta coletiva de rondas; não representa autoridade para intervenções industriais.

A mesma pessoa pode interpretar A e B para verificar o fluxo e a atribuição de autoria. Isso não testa resistência a alguém escolher a identidade de outra pessoa e não substitui uma avaliação de usabilidade com outro participante.

### 3.2 Conceitos distintos

| Conceito | Pergunta que responde | Definição mínima da MVP |
|---|---|---|
| Autenticação | Qual acesso está aberto? | Login administrativo individual ou acesso pelo perfil compartilhado do turno, ambos com sessão |
| Autorização | Esse acesso pode executar esta ação? | Separação entre administração e operação do turno; regras sobre a execução |
| Autoria | Qual pessoa realizou a ação? | Operador seleciona seu nome e confirma um PIN pessoal curto |
| Perfil de acesso | Quais responsabilidades a sessão possui? | `SHIFT` e `ADMIN`, nomes candidatos; operador não precisa ser uma conta de login |
| Turno | Qual período limita a meta e a retomada das rondas? | Janela identificada pelo perfil do turno, com início, término e quantidade coletiva configurados pelo administrador; pode terminar no dia seguinte |

**CONFIRMADO — USUÁRIO (DEC-P-07, DEC-P-09, DEC-P-18, DEC-P-19, DEC-P-25, DEC-P-32 e DEC-P-41):** a equipe usa um login do turno, em vez de cada operador manter um login individual. Ao assumir ou retomar uma ronda, e em outra ação que exija autoria fora dela, o operador seleciona sua identidade em uma lista de nomes ativos e confirma um PIN pessoal curto. Leituras rotineiras realizadas na ronda herdam o autor já confirmado. O perfil do turno pode aparecer como opção já registrada na tela inicial, mas toda nova sessão exige a senha compartilhada do perfil. Somente o administrador define ou altera essa senha e a repassa à equipe por meio externo ao sistema; não há senha temporária nem troca obrigatória no primeiro acesso. Administrador cria ou redefine PIN; cinco tentativas inválidas bloqueiam a identificação até nova redefinição administrativa.

Permissões da MVP: a sessão do turno consulta o histórico da demonstração e permite assumir uma ronda ainda não iniciada. **CONFIRMADO — USUÁRIO (DEC-P-13):** a identidade confirmada por PIN como autora corrige apenas seus próprios registros de coleta com motivo; o administrador, no acesso administrativo próprio, pode corrigir registro de qualquer autor, também com motivo e histórico preservado. **CONFIRMADO — USUÁRIO (DEC-P-12):** qualquer operador no perfil do turno pode, com nome e PIN, acrescentar acompanhamento ou resolver ocorrência de outro operador. A resolução cria novo evento com autor, horário e descrição, preservando o acontecimento original. **CONFIRMADO — USUÁRIO (DEC-P-05, DEC-P-06, DEC-P-08, DEC-P-24, DEC-P-26 e DEC-P-27):** administrador prepara o cadastro e configura uma programação reutilizável com horário inicial, horário final e quantidade coletiva de rondas de cada turno, sem criar manualmente cada ronda. Pode desativar temporariamente um perfil de turno: ele deixa de disponibilizar novos turnos e invalida imediatamente suas sessões abertas, enquanto o histórico continua consultável. Ao reativá-lo, volta a disponibilizar somente turnos futuros, sem recriar os períodos em que estava desativado.

Não haverá cadastro público, recuperação por e-mail, permissões configuráveis por tela ou integração corporativa de identidade. Acesso administrativo e perfis de turno serão provisionados em preparação controlada, com segredos fornecidos fora dos fontes. Ao desabilitar um acesso ou uma identidade de operador, preservar referências históricas e negar seu uso em novas ações.

Correção de leituras e observações de terceiros é restrita ao administrador por DEC-P-13. Resolução de ocorrências de terceiros é permitida a qualquer operador identificado por PIN, conforme DEC-P-12.

## 4. Hipótese e resultado observável

**Hipótese:** separar valor coletado, ausência justificada e ocorrência permite reconstruir uma ronda sem depender da memória de quem a realizou.

A documentação apoia essa hipótese, com duas ressalvas: existem campos diferentes por controlador e os acontecimentos do turno têm identidade própria. Por isso, um formulário único com um campo de texto genérico não é suficiente.

Resultado da demonstração:

1. A registra uma execução com equipamento, ponto, unidade, fonte, autoria e horários identificáveis.
2. Uma ausência não aparece como zero ou como valor reaproveitado.
3. Uma ocorrência pode continuar aberta após o fim da ronda.
4. B localiza o registro, distingue medição de ausência e identifica o que continua aberto.
5. Uma correção preserva o registro anterior e explica quem corrigiu, quando e por quê.

**Sucesso funcional:** os cenários funcionais da seção 19 passam, e o CS-09 registra o resultado conforme a DEC-P-42, sem alegar compreensão por terceiros.

**Sucesso da hipótese de compreensão:** outra pessoa consegue responder às perguntas da seção 19 usando a interface, sem explicação oral do autor sobre cada registro.

**Falha da hipótese:** a pessoa confunde cadastro com estado observado, ausência com leitura, encerramento de ronda com resolução de ocorrência, ou não consegue reconstruir o histórico. A implementação pode estar tecnicamente correta e ainda falhar nessa avaliação.

**CONFIRMADO — USUÁRIO (DEC-P-42):** nesta MVP, o próprio responsável realiza a verificação funcional. A avaliação de compreensão por outra pessoa fica fora da demonstração inicial; enquanto ela não ocorrer, registrar "verificação funcional concluída; compreensão por outra pessoa não validada".

## 5. Dentro da MVP

| Capacidade | Por que precisa existir | Quem/quando | Resultado |
|---|---|---|---|
| Acesso administrativo e por turno | Separar configuração de operação cotidiana | Administrador ou equipe antes do uso | Sessão com capacidade correspondente; autoria individual tratada separadamente |
| Cadastro mínimo do piloto | Identificar os objetos da coleta | Administrador na preparação | Compressores e recipientes fictícios identificados |
| Perfis e programação diária de turno | Adaptar quantos turnos existem, seus horários e metas sem alterar código | Administrador na preparação e quando precisar alterar | Quantidade configurável de perfis, cada um com horários e meta coletiva; recorrência diária sem sobreposição por perfil; horário pode atravessar a meia-noite; alterações só alcançam turnos futuros |
| Roteiro e pontos aplicáveis | Evitar os mesmos campos para todos | Administrador prepara; operador percorre | Ordem e formulário adequados a cada equipamento |
| Iniciar e retomar ronda pendente | Dar contexto e continuidade dentro do turno | Operador no início ou retorno à página | Execução identificada e progresso salvo recuperado pelo mesmo operador |
| Registrar leitura ou ausência | Eliminar ambiguidade do vazio | Operador em cada ponto | Resultado persistido ou indicação explícita de pendência |
| Observação contextual | Explicar um resultado específico | Operador durante coleta | Nota vinculada à leitura ou condição observada |
| Ocorrência própria | Separar acontecimento de número | Operador durante ronda | Registro independente com acompanhamento |
| Concluir ou deixar pendente | Explicitar o progresso | Autor durante o turno | Conclusão ou pausa com resultados e pendências visíveis |
| Histórico e continuidade simples | Permitir consulta por outro operador ou pelo administrador | A/B depois da coleta | Rondas, resultados e ocorrências abertas consultáveis |
| Correção rastreável | Corrigir sem apagar o passado | Autor ou administrador | Nova revisão e motivo, com versão anterior acessível |
| Execução e recuperação documentadas | Reproduzir a demonstração | Responsável técnico | Ambiente inicializável e restauração demonstrada |

Configuração será pequena: um roteiro demonstrativo, uma lista curta de pontos por equipamento e quantidade configurável de perfis de turno. Cada perfil possui programação reutilizável com horário inicial, horário final e quantidade coletiva de rondas. Prever um formulário administrativo simples de preparação e alteração, sem exigir que o administrador crie cada ocorrência de turno ou cada ronda. A programação deve informar a meta à equipe e não pode depender de número fixo de turnos, de rondas ou de leitor previamente designado no código. O formato mínimo desse formulário será definido na Issue correspondente.

Não é necessário entregar CRUD completo de todos os conceitos. **CONFIRMADO — USUÁRIO (DEC-P-11):** alteração de programação só afeta ocorrências futuras do turno; turno já iniciado e execuções existentes preservam horário, meta e contexto originais.

## 6. Fora da MVP

- Integração automática com IHM, PLC, rede industrial ou máquinas.
- Coleta automática, sensores novos, IoT e atualização em tempo real.
- Comandos, setpoints, reconhecimento de alarmes, bloqueio ou liberação de equipamento.
- Cálculo de não condensáveis, percentual de ar, conversões automáticas e comparação com limites industriais.
- Cloud como requisito funcional, infraestrutura corporativa ou deploy público obrigatório.
- IA, manutenção preditiva, eficiência energética e ganhos financeiros estimados.
- Dashboards sofisticados, séries temporais, relatórios avançados e exportações amplas.
- Notificações e agendamento genérico de tarefas. A aplicação automática da programação recorrente dos turnos e de sua quantidade de rondas permanece dentro da MVP.
- Aplicativo mobile nativo, instalação como PWA e sincronização offline.
- Gestão completa de manutenção, ordens de serviço, estoque ou planejamento de equipes.
- Integrações corporativas, multiempresa e múltiplas plantas.
- Gestão completa de escalas, troca de responsável no meio da execução e aceite formal da passagem. O contexto mínimo de turno necessário à meta e à retomada das rondas permanece dentro da MVP.
- Gestão de restrições operacionais com vigência e autorização.
- Anexos, fotos, áudio e importação dos cadernos reais.
- Condensadores, bombas e generalização para todo o inventário na experiência da primeira ronda. Os tipos já existentes na API não serão removidos por causa desse recorte.

Se uma dessas capacidades se tornar necessária para validar o fluxo escolhido, revisar explicitamente o recorte e substituir ou adiar outro resultado. Não incorporá-la silenciosamente.

## 7. Piloto demonstrativo e limite do piloto real

### 7.1 Baseline aprovada da demonstração

| Item | Definição |
|---|---|
| Finalidade | **CONFIRMADO — USUÁRIO:** demonstração com dados fictícios primeiro |
| Tipos | **CONFIRMADO — USUÁRIO:** compressores e recipientes |
| Área | Proposta: uma área fictícia, sem correspondência afirmada com sala real |
| Quantidade | **CONFIRMADO — USUÁRIO:** dois compressores fictícios e um recipiente fictício |
| Diferenças de coleta | **CONFIRMADO — USUÁRIO:** um ponto fictício de pressão em cada equipamento e um ponto adicional de frequência em um compressor |
| Acessos e identidades | **CONFIRMADO — USUÁRIO:** perfil compartilhado por turno, acesso administrativo próprio e identidades fictícias de operadores confirmadas por PIN curto para autoria; credenciais não entram nos exemplos versionados |
| Dispositivo | **DECISÃO DO PRODUTO:** Chrome em computador local e verificação em viewport móvel; telefone físico fica fora da demonstração inicial |
| Conectividade | Online; acesso a uma instância local. Teste em telefone físico só se for escolhido e houver rede de teste autorizada |
| Dados | Sintéticos, identificados na interface; sem fotos, tags reais ou documentos industriais |
| Turnos e rondas | **CONFIRMADO — USUÁRIO:** administrador configura uma vez, e pode alterar, a programação de horários e quantidade coletiva de rondas; o sistema disponibiliza as ocorrências sem criação manual; um perfil pode começar em um dia e terminar no seguinte, mantendo sua identidade de turno; qualquer operador no perfil do turno pode assumir uma ronda livre após se identificar |
| Duração | Sessão de verificação funcional, sem prazo máximo inventado |
| Participantes | **DEC-P-42:** o próprio responsável realiza a verificação funcional; avaliação por outra pessoa fica fora da demonstração inicial |

A seleção dos equipamentos visa provar heterogeneidade de pontos, não simular fielmente a instrumentação de uma planta. Não é obrigatório adicionar múltiplas famílias de controlador como entidades.

### 7.2 Dados de demonstração

**CONFIRMADO — USUÁRIO (DEC-P-15):** a composição possui três pontos fictícios de pressão, um em cada equipamento, e um ponto fictício adicional de frequência em somente um compressor. Rótulos, unidades e valores do conjunto demonstrativo serão explicitamente identificados como exemplos, sem associação a limites seguros ou à instalação real. Os exemplos não serão derivados de registros, imagens ou dados reais.

Os dados deverão permitir exercitar: valor zero quando digitado intencionalmente, leitura numérica, campo não aplicável, impossibilidade de coleta, equipamento observado parado, ocorrência aberta e correção. Zero nunca será gerado a partir de ausência.

### 7.3 O que fica pendente para uso real

**VALIDAÇÃO OPERACIONAL NECESSÁRIA:** a demonstração suporta vários operadores e perfis configuráveis, sem predeterminar o leitor de cada ronda. Nenhuma quantidade real de pessoas, escala, duração, horário ou composição de turno é documentada publicamente; essas informações exigem validação privada antes de eventual piloto.

Sala, quantidade real de equipamentos, operadores, duração, periodicidade, dispositivos, autorização dos dados, fonte oficial de cada ponto, unidade e convenção de pressão, procedimentos, conectividade, suporte, retenção e objetivos de recuperação exigem decisões próprias. Nenhuma dessas informações é inferida da demonstração.

## 8. Fluxo principal e exceções

```text
Preparação: acesso administrativo + perfis de turno + identidades fictícias + equipamentos + pontos + roteiro
  ↓
Administrador configura uma programação diária reutilizável com início, término e quantidade coletiva de rondas
  ↓
Equipe escolhe o perfil do turno já registrado, informa sua senha compartilhada e consulta a ocorrência e a meta atuais
  ↓
Operador seleciona seu nome, confirma o PIN e assume uma ronda livre ou retoma a ronda pendente atribuída a essa identidade
  ↓
Percorre os compressores na ordem configurada
  ├── observa a condição do equipamento, quando houver registro
  ├── informa valor/unidade/fonte/horário ou ausência com motivo
  ├── salva e verifica confirmação do servidor
  └── abre registro de ocorrência separado, se necessário
  ↓
Percorre o(s) recipiente(s), com seus próprios pontos e fontes
  ↓
Revisa resultados medidos, ausências e pontos pendentes
  ├── conclui: todos os pontos estão medidos ou têm ausência explicitamente justificada
  └── deixa pendente: preserva o que foi salvo para retomada no mesmo turno
  ↓
Histórico fica disponível para perfis autorizados
  ↓
Operador B consulta resultados e ocorrências ainda abertas
  ↓
Acrescenta acompanhamento/resolução de ocorrência, quando cabível
```

A consulta de tabela de pressão equivalente, presente no relato da rota real, é conscientemente excluída desta demonstração. Incluir recipientes significa registrar dados e contexto, não implementar aquele cálculo.

Regras e exceções da MVP:

- Fechar a página ou perder a conexão não conclui automaticamente a ronda. Ao retornar durante o mesmo turno, recuperar somente o que o servidor confirmou.
- Salvar um ponto não depende de preencher toda a ronda.
- **CONFIRMADO — USUÁRIO (DEC-P-04):** ao deixar a ronda pendente, os pontos restantes continuam identificados como pendentes e o mesmo operador pode retomar a execução durante o próprio turno. Não gerar medições nem justificativas fictícias.
- **CONFIRMADO — USUÁRIO (DEC-P-10 e DEC-P-22):** a ronda pendente não pode ser transferida para outro operador. No horário de término configurado do turno, sem tolerância, ela é encerrada como não concluída por fim de turno, preserva tudo que foi salvo e não pode ser retomada no turno seguinte.
- Navegar fora da ordem sugerida é permitido na proposta. Registrar o horário real; não reordenar ou reescrever o roteiro histórico. Validar essa flexibilidade antes de uso real.
- Equipamento indisponível não é removido da execução já iniciada; registrar condição e/ou motivo de ausência.

## 9. Requisitos funcionais rastreáveis

| ID | Resultado exigido na MVP | Aceite resumido |
|---|---|---|
| REQ-MVP-001 | Acesso, identificação e permissões | Sem sessão válida não lê/grava dados; administração e turno têm capacidades distintas; assumir/retomar ronda e outra ação autoral exigem operador selecionado e PIN válido; o backend não aceita autoria arbitrária enviada como texto livre |
| REQ-MVP-002 | Cadastro consistente | Compressores e recipientes fictícios; conflito 409 e tamanho inválido 400; sem estado físico presumido |
| REQ-MVP-003 | Roteiro, pontos e programação do turno | Administrador configura quantos perfis de turno existem e, em cada um, nome, horários e quantidade coletiva numa programação diária reutilizável; aceita término no dia seguinte preservando o perfil de turno; pode renomear o perfil sem alterar o nome congelado nos turnos históricos, excluí-lo somente sem turnos ou registros vinculados, desativá-lo para impedir novos turnos e invalidar imediatamente suas sessões abertas, ou reativá-lo somente para turnos futuros sem repor períodos passados; o sistema bloqueia sobreposição para o mesmo perfil, disponibiliza turnos/rondas futuros sem criação manual e preserva o contexto de turnos já iniciados e o histórico |
| REQ-MVP-004 | Execução da ronda | Iniciar uma ronda livre em qualquer momento até o horário final, deixar pendente, retomar pelo mesmo operador durante o turno, concluir ou encerrar automaticamente como não concluída no horário final configurado, sem tolerância |
| REQ-MVP-005 | Coleta contextualizada | Cada resultado contém valor ou ausência, unidade/fonte aplicáveis, horários e autoria |
| REQ-MVP-006 | Condição observada separada | Cadastro ativo, observação operacional e situação da coleta não se sobrescrevem |
| REQ-MVP-007 | Ocorrência e continuidade | Acontecimento separado de leitura; qualquer operador identificado por PIN pode acompanhar ou resolver ocorrência de outro, preservando o original e autoria de cada evento |
| REQ-MVP-008 | Histórico consultável | B recupera execução, resultados, contexto e ocorrências abertas; filtros mínimos por período/equipamento |
| REQ-MVP-009 | Correção rastreável | Autor corrige apenas o próprio registro; administrador pode corrigir qualquer um; correção exige motivo e preserva original, revisão, autor e horário |
| REQ-MVP-010 | Interface do percurso | Login, coleta, desfecho e consulta funcionam em tela larga e viewport móvel |
| REQ-MVP-011 | Falhas e repetição compreensíveis | Erro não aparece como sucesso; repetição de envio não cria leitura/ocorrência duplicada |
| REQ-MVP-012 | Demonstração reproduzível e recuperável | Preparar ambiente, executar cenários e restaurar backup sem alterar banco de desenvolvimento |

São grupos de requisitos, não doze Issues prontas. Um requisito pode precisar de vários incrementos pequenos; uma mudança trivial não exige um identificador adicional.

## 10. Requisitos não funcionais

- **Integridade:** cada resposta de sucesso de gravação corresponde a dado persistido; sem preenchimento automático de valores.
- **Usabilidade:** unidade próxima ao campo, progresso visível, erros junto da entrada e confirmação explícita do que foi salvo.
- **Compreensão:** texto "demonstração — dados fictícios" e horários visíveis; nenhuma cor ou expressão declara segurança física.
- **Compatibilidade:** navegador-alvo e dispositivo serão registrados antes da validação da interface; não assumir funcionamento em todos os aparelhos.
- **Desempenho:** registrar tempos observados nas ações centrais. Um limite quantitativo de aceitação depende do dispositivo e da avaliação; não prometer tempo sem medida.
- **Persistência:** mesma base para coleta e consulta; testes isolados em Testcontainers.
- **Manutenção:** mudanças pequenas, contratos testados e documentação atualizada quando o comportamento mudar.
- **Acesso:** autenticação real para administrador e perfil do turno, com credenciais fictícias; autoria individual é confirmada por nome e PIN curto nas ações exigidas; nenhum bypass de segurança só para obter testes verdes.
- **Disponibilidade:** conexão online necessária para confirmar gravação. Não prometer trabalho offline.
- **Rastreabilidade:** preservar autoria e revisões dos dados operacionais; logs técnicos não substituem esse histórico.

## 11. Regras de domínio

### 11.1 Separação das dimensões de estado

| Conceito | Significado | Representação proposta |
|---|---|---|
| Equipamento cadastrado ativo | Pertence ao inventário usado pelo sistema | `Equipment.active`; não indica funcionamento |
| Equipamento ligado/parado | Condição relatada num instante | Observação associada à execução, autor, fonte e horário |
| Em manutenção | Condição relatada; não é autorização nem ordem de serviço | Observação própria, com vocabulário a validar |
| Sem leitura | Ausência de valor; sozinha não explica motivo | Ponto pendente ou ausência explicitamente registrada |
| Não aplicável | A variável não cabe naquele equipamento/contexto | Motivo de ausência, sem valor numérico |
| Não realizado | A coleta não foi efetuada | Ausência deliberadamente justificada, diferente de pendente sem resposta |
| Condição observada | Registro situado no tempo | Histórico de observações; não atualiza automaticamente cadastro |

**CONFIRMADO — DOCUMENTAÇÃO:** esses conceitos precisam ser separados. Os nomes definitivos de estados e a aplicabilidade de manutenção/evacuação dependem de validação operacional.

Proposta mínima de vocabulário de observação para exercício: "em funcionamento", "parado" e "condição não determinada", com nota opcional. "Manutenção" e "evacuado" não recebem transições automáticas; sua introdução como opção estruturada exige decisão registrada. É possível relatar a situação em uma ocorrência fictícia sem transformá-la em regra universal.

### 11.2 Ronda

Uma ronda é uma execução manual de um roteiro conhecido. **CONFIRMADO — USUÁRIO (DEC-P-05, DEC-P-06, DEC-P-08, DEC-P-16 e DEC-P-17):** o administrador configura uma programação diária reutilizável com início, término e quantidade coletiva de rondas do turno. A quantidade não fica fixa no software, o administrador não cria cada ronda manualmente e cada ronda não possui leitor predeterminado: qualquer operador usando o perfil do turno pode se identificar e assumir uma ainda não iniciada. Para o mesmo perfil de turno, programações diárias não podem ter horários sobrepostos. A demonstração não calcula a escala 6x2; horários individuais ou espaçamento entre as rondas ainda não estão aprovados.

No início, a execução recebe a lista de equipamentos/pontos e cópias dos rótulos, unidades, fontes e ordem relevantes para interpretar o histórico. Mudanças posteriores no cadastro não alteram essa lista.

Proposta: um autor por execução; não admitir troca de autor nem coautoria na v0.1. A identificação ao assumir a ronda fixa seu autor. A interface compartilhada apresenta a meta do turno, as rondas ainda livres, as concluídas e as pendentes com seus autores antes de permitir iniciar outra execução.

**CONFIRMADO — USUÁRIO (DEC-P-03):** para concluir, todos os pontos esperados precisam estar medidos ou ter ausência explicitamente justificada. Um ponto ainda pendente bloqueia a conclusão. A interface deve exibir a quantidade de ausências e seus motivos; "concluída" não significa "todas as leituras realizadas". Para um piloto real, essa regra ainda depende de validação operacional.

**CONFIRMADO — USUÁRIO (DEC-P-04, DEC-P-10, DEC-P-22 e DEC-P-23):** o operador pode iniciar uma ronda livre em qualquer momento até o horário final configurado, inclusive próximo dele, e pode deixar sua ronda pendente e retomá-la durante todo o próprio turno. A ação registra autor e horário do servidor; o que não foi preenchido permanece identificável, sem gerar medições ou razões fictícias. No horário final configurado, sem período de tolerância, uma ronda ainda pendente é encerrada automaticamente como não concluída por fim de turno, preserva os resultados e ausências já registrados e não pode ser retomada nem transferida.

### 11.3 Leituras, pontos e ausências

Um ponto descreve o que pode ser registrado para um equipamento: identificador estável, rótulo, unidade explícita, origem esperada, referência da fonte e posição no roteiro. Pontos de compressores e recipientes podem ser diferentes. Não há catálogo universal de todas as siglas de controladores.

Cada resultado de coleta preserva:

- execução e item/ponto a que pertence;
- equipamento e contexto copiado no início;
- valor bruto decimal **ou** ausência explícita com motivo;
- unidade e fonte originais quando houver medição;
- horário observado informado pela pessoa e horário de recebimento definido pelo servidor;
- operador identificado como autor, observação contextual opcional e revisão;
- classificação de origem do registro como manual;
- condição observada relacionada, quando houver.

Proposta de origens: transcrição manual de controlador local e leitura manual de instrumento local. Na demonstração ambas simulam fontes, com dados fictícios. Não há sensor conectado.

Regra central: resultado medido exige valor, unidade, fonte e instante observado; ausência exige motivo e não pode carregar um número. Ausência não recebe `measuredAt` inventado: preserva o instante relatado da tentativa, quando conhecido, e sempre o horário de registro.

Os motivos candidatos são não aplicável, não foi possível medir, equipamento parado e não realizado com justificativa. Equipamento parado só justifica a ausência de um ponto quando essa relação fizer sentido para ele; não zerar ou dispensar todos os campos automaticamente.

`scheduledAt` pode existir como referência opcional da execução se o cenário precisar; não é preenchido por inferência da hora atual. `observedAt/measuredAt` representa a observação; `recordedAt` representa recebimento no servidor. Digitação posterior não muda a natureza da observação: atraso é informação temporal, não um estado incompatível com "medida".

Valores serão decimais, com precisão/escala escolhidas na Issue conforme o conjunto de demonstração. Não introduzir arredondamento silencioso nem faixas industriais. Limites técnicos de representação e validação de formato devem ser explícitos; não são limites seguros da grandeza.

### 11.4 Observação, ocorrência e pendência

- **Leitura:** valor observado num ponto e instante.
- **Observação contextual:** explicação ligada àquela coleta ou condição; não possui acompanhamento independente.
- **Ocorrência:** acontecimento que precisa ser consultado e, possivelmente, acompanhado além da leitura.
- **Mudança de estado observada:** novo relato temporal de condição; não comando ou transição automática da máquina.
- **Pendência para continuidade:** ocorrência ainda aberta ou acompanhamento nela registrado; não tarefa agendada com prazo e responsável.

Ocorrência mínima: identificador, equipamento do piloto, execução de origem, texto do acontecimento, momento relatado, momento do registro, autor e situação. Na v0.1, criação começa dentro de uma ronda. **CONFIRMADO — USUÁRIO (DEC-P-12):** qualquer operador que confirme nome e PIN no perfil do turno pode acompanhá-la ou resolvê-la depois, com novo evento de autoria, horário e descrição.

Não misturar acontecimento, ação e resolução em sobrescrita do mesmo texto. Acompanhamentos são registros adicionais ligados à ocorrência. Marcar como resolvida exige nome, PIN, descrição do desfecho e autor registrado; não conclui uma ronda nem atesta condição segura.

### 11.5 Histórico e correção

Depois de uma gravação confirmada, uma alteração de leitura ou observação relevante cria uma revisão com referência ao registro anterior, novo conteúdo, motivo, autor e horário do servidor. **CONFIRMADO — USUÁRIO (DEC-P-13):** o autor só corrige seus próprios registros após confirmar nome e PIN; administrador em seu acesso próprio pode corrigir registro de qualquer autor, sempre com motivo. O horário original observado pode ser corrigido, mas ambos os valores ficam preservados.

Não oferecer exclusão física de execuções, resultados ou ocorrências pela interface. Registro indevido pode ser invalidado com motivo e permanecer consultável. Uma medição invalidada não satisfaz a conclusão de uma ronda aberta sem uma substituição válida ou ausência explícita.

Correção posterior ao encerramento não reabre a execução nem altera silenciosamente seu desfecho original. A tela mostra o encerramento ocorrido, a revisão vigente e o histórico de correções. Se a correção retirar a evidência de um ponto, mostrar isso claramente.

Preferir registros de revisão ligados ao original e um ponteiro para a versão vigente, com controle de concorrência. Não implementar event sourcing, trilha genérica para qualquer entidade ou infraestrutura de eventos distribuídos.

## 12. Modelo conceitual e conceitos dispensados

```text
Perfil do turno (acesso compartilhado) + identidade do operador (autoria)
  └── permitem executar → RoundExecution (uma ronda realizada)
                   ├── usa contexto de → Roteiro configurado
                   ├── contém → Itens da execução (ordem/contexto preservados)
                   │                ├── referenciam → Equipment
                   │                ├── derivam de → MeasurementPoint aplicável
                   │                └── recebem → Resultado de coleta
                   │                                 ├── medição OU ausência
                   │                                 └── revisões/correções
                   ├── registra → Condição observada por equipamento
                   └── origina → Occurrence
                                      └── acompanhamentos/resolução

Visão de continuidade = consulta de execuções + resultados + ocorrências abertas
```

Cada resultado pertence a um item e uma execução; seus identificadores de equipamento/ponto devem corresponder ao contexto daquela execução. Uma ocorrência da ronda só referencia equipamento incluído nela. Acompanhamento posterior continua ligado à ocorrência original.

| Conceito | Responsabilidade e dados principais | Relações | Ciclo de vida proposto |
|---|---|---|---|
| Equipment | Identidade física lógica: ID, código, nome, tipo, localização, ativo | Pontos e itens históricos | Cadastrar; manter identidade; retirar de roteiros futuros sem apagar histórico |
| Perfil de acesso | Credencial protegida, tipo administrativo ou turno e habilitação | Sessão e permissões; perfil de turno ligado à programação | Administrador provisiona com senha obrigatória e a altera; autenticar e desabilitar preservando histórico; sem senha temporária ou troca obrigatória; ao desabilitar, invalidar imediatamente sessões abertas do perfil |
| Identidade do operador | Pessoa exibida para atribuir ações, com PIN pessoal curto protegido e sem login individual obrigatório | Autor de execuções e registros | Administrador cria ativa com PIN obrigatório, renomeia, redefine PIN, bloqueia manualmente ou reativa preservando autoria; operador não altera o próprio PIN; redefinição de PIN reativa identidade bloqueada; exclui somente se nunca usada; cinco erros bloqueiam; confirmar por PIN em ação autoral e desabilitar preservando autoria |
| Programação de turno | Nome, horários e quantidade coletiva com recorrência diária configurados pelo administrador, com habilitação temporária do perfil | Perfil de turno e ocorrências futuras | Criar ativo com senha compartilhada obrigatória, renomear e alterar sem sobreposição por perfil; excluir somente sem turnos ou registros vinculados; permitir término no dia seguinte; ativar/desativar perfil para novas ocorrências e invalidar suas sessões abertas ao desativar; ao reativar, gerar somente turnos futuros; mudança não altera turnos já iniciados |
| Turno | Janela e meta coletiva configuradas, identificada pelo perfil mesmo se atravessar a meia-noite, com nome do perfil congelado | Operadores participantes, configuração administrativa e execuções | Preparar com início/término; manter ativo; ao fim, encerrar rondas pendentes como não concluídas |
| Roteiro | Área demonstrativa e sequência de equipamentos/pontos | Configuração usada para iniciar execuções | Preparar e alterar para novas execuções |
| MeasurementPoint | Definição de leitura aplicável, unidade e fonte | Um equipamento; itens derivados | Configurar; desabilitar para novas execuções |
| RoundExecution | Uma execução, autor, turno, início, término e situação | Itens, observações e ocorrências | Prevista antes de ser assumida; em andamento; pendente após ser deixada para retomada; concluída; não concluída por fim de turno |
| Item da execução | Contexto congelado do ponto esperado | Execução, equipamento e definição original | Criado no início; recebe resultados sem perder contexto |
| Resultado/Measurement | Valor ou ausência, horários e autoria | Item e revisões | Salvar; corrigir ou invalidar com histórico |
| Condição observada | Relato temporal do equipamento | Execução, equipamento e autor | Registrar; corrigir preservando anterior |
| Occurrence | Acontecimento e situação de acompanhamento | Equipamento, execução e autores | Abrir; acompanhar; resolver |
| Revisão/acompanhamento | Preservar evolução com motivo/autor/horário | Registro anterior ou ocorrência | Acrescentar; nunca apagar o original pela interface |

Esses conceitos pertencem ao modelo proposto; ainda não estão aprovados como entidades JPA ou tabelas individuais.

Decisões de simplificação:

- **Operator:** com login compartilhado por turno, a identidade do operador precisa ser separada da credencial de acesso. **CONFIRMADO — USUÁRIO (DEC-P-09 e DEC-P-19):** a confirmação mínima é seleção do nome e PIN pessoal curto; administrador cria ou redefine o PIN; após cinco erros, a identificação fica bloqueada até redefinição administrativa. Não há login individual completo no uso cotidiano.
- **Round e RoundExecution:** usar `RoundExecution` para o fato realizado. O que é reutilizável é o roteiro; não duplicar duas entidades que representem a mesma execução.
- **Shift/Turno:** contexto necessário para limitar a retomada e receber início, término e quantidade coletiva da programação diária vigente quando foi gerado. **CONFIRMADO — USUÁRIO (DEC-P-11, DEC-P-16, DEC-P-17 e DEC-P-21):** mudança posterior da programação só gera novos turnos; a recorrência é diária, sem sobreposição por perfil, não calcula a escala 6x2 e aceita que o término seja no dia seguinte. Nesse caso, o turno preserva seu perfil configurado — por exemplo, continua sendo o 3º turno. Não reescreve turno ou execução já iniciados. O acesso é pelo perfil do turno e não predetermina o autor de cada execução.
- **ShiftHandover:** visão de continuidade derivada; sem duplicar resultados numa tabela de resumo.
- **EquipmentStatus:** o enum atual é legado do cadastro; não governa estado da coleta nem transições operacionais.
- **Área/planta:** rótulo de uma configuração demonstrativa; não criar hierarquia de plantas/salas.

## 13. Estados e ciclos de vida

Os nomes de estados abaixo são candidatos de implementação a refinar na Issue correspondente; o ciclo de vida descrito preserva as regras funcionais aprovadas:

```text
Ronda: PREVISTA
          ├── assumir por qualquer operador identificado → EM_ANDAMENTO
          └── fim do turno → NAO_CONCLUIDA_FIM_TURNO

       EM_ANDAMENTO
          ├── concluir → CONCLUIDA
          ├── deixar para retomada → PENDENTE
          └── fim do turno → NAO_CONCLUIDA_FIM_TURNO

       PENDENTE
          ├── retomar pelo mesmo operador → EM_ANDAMENTO
          └── fim do turno → NAO_CONCLUIDA_FIM_TURNO

Ponto: PENDENTE → resultado MEDIDO
              └→ resultado AUSENCIA_JUSTIFICADA

Ocorrência: ABERTA → RESOLVIDA
               └→ acompanhamento (continua ABERTA)

Registro salvo: revisão original → revisão de correção/invalidação
```

Regras funcionais do ciclo; os nomes técnicos dos estados permanecem candidatos:

- Para uma ronda, `PREVISTA` significa que ela ainda não foi assumida; `PENDENTE` significa que já foi iniciada e deixada para retomada.
- Para um ponto, `PENDENTE` significa que ainda não existe resultado vigente válido. O contexto do ponto evita confusão com a situação da ronda.
- Uma ronda `PREVISTA` pode ser assumida por qualquer operador participante do turno; depois de iniciada, sua autoria não muda e somente o mesmo operador pode retomá-la.
- Não aplicável e não realizado são motivos distintos de ausência, não estados da máquina.
- Concluir ou deixar uma ronda pendente não resolve ocorrências.
- Qualquer operador identificado por PIN pode acompanhar ou resolver ocorrência de outro; a resolução preserva o evento original e registra autor, horário e descrição do desfecho.
- Resolver ocorrência não altera `Equipment.active` nem uma observação passada.
- Fechar navegador ou expirar sessão mantém o estado persistido da execução.
- Repetir um encerramento já confirmado não cria novo encerramento; uma transição incompatível retorna conflito.
- Pela DEC-P-10 e pela DEC-P-22, uma ronda incompleta que esteja `PREVISTA`, `EM_ANDAMENTO` ou `PENDENTE` é encerrada exatamente no horário final configurado, sem tolerância, como `NAO_CONCLUIDA_FIM_TURNO`. Uma ronda concluída ou encerrada por fim de turno não é reaberta. Ocorrência resolvida também não é reaberta na primeira versão. Novo acontecimento pode ser registrado em uma ronda posterior.

## 14. Interface, continuidade e conectividade

### 14.1 Telas necessárias

1. **Login:** identificação e erro simples, com indicação de demonstração.
2. **Início:** visualizar a meta configurada do turno, iniciar uma ronda prevista, retomar a própria ronda pendente e acessar histórico/continuidade.
3. **Ronda/equipamento:** progresso, ordem sugerida, campos aplicáveis, unidade/fonte, condição observada e situação de salvamento por ponto.
4. **Ocorrência:** formulário separado e detalhe com acompanhamentos; pode abrir a partir da ronda.
5. **Revisão do progresso:** medições, ausências e pendências; concluir ou deixar pendente com regras visíveis.
6. **Histórico/detalhe:** filtros curtos, contexto da execução, autoria, horários e revisões.
7. **Continuidade:** meta do turno, rondas concluídas, rondas pendentes do operador, rondas não concluídas por fim de turno e ocorrências abertas, inclusive as mais antigas ainda não resolvidas.
8. **Preparação administrativa:** acessos, identidades de operadores e cadastro mínimo necessário, sem gerenciamento amplo de planta.

Histórico e continuidade podem ser abas da mesma tela. Correção pode ser ação no detalhe; não exige um módulo isolado.

Não exibir "equipamentos indisponíveis agora" a partir de observação antiga. Mostrar "condição relatada em [horário], por [autor]" e ocorrências associadas. O resumo não confirma ciência, execução de tarefas ou autorização operacional.

### 14.2 Requisitos de interação

Usar rótulos em português, campos grandes o suficiente para toque, foco/teclado utilizáveis e contraste verificável. Evitar digitação repetitiva de unidade e fonte quando já conhecidas no ponto. Permitir observação livre sem impor checklist industrial não aprovado.

Não pré-preencher valores numéricos com leitura anterior ou valor típico. Horário sugerido pela interface só pode representar "observado agora" mediante confirmação; entrada tardia permite indicar o horário relatado. Valor anterior, se algum dia mostrado, precisa ficar separado da entrada nova.

O botão de salvar informa sucesso apenas após confirmação. Mostrar "não salvo" quando houver falha. O desfecho exige revisão, sem encerrar por navegação acidental.

### 14.3 Online, falha breve e offline

| Modo | Decisão proposta |
|---|---|
| Online | Obrigatório para carregar dados atualizados e confirmar gravação |
| Tolerância breve | Manter o formulário não enviado na página enquanto possível; exibir falha; permitir reconciliação/reenvio |
| Offline completo | Fora da MVP; sem banco local, fila durável ou sincronização automática |

Não prometer preservar uma entrada não enviada após fechar/recarregar o navegador. Ao perder a resposta de um envio, consultar o resultado confirmado ou reenviar a mesma operação identificada; não criar outra leitura silenciosamente.

A Issue de gravação definirá uma chave de operação para reconhecer repetição de envio, limitada ao caso de uso, e uma versão esperada para impedir sobrescrita concorrente. A mesma chave com conteúdo diferente deve ser rejeitada. Não é necessário construir um serviço geral de sincronização.

Antes de um piloto real, validar conectividade nos locais de coleta. Se online não for viável, reconsiderar o escopo; não usar a demonstração para declarar esse risco resolvido.

## 15. Arquitetura mínima e decisões técnicas

### 15.1 Fundação aproveitada

**CONFIRMADO — CHECKPOINT:** existem Java 21, Spring Boot, Spring MVC, Service, JPA, PostgreSQL, Flyway, DTOs de entrada/saída e testes com Testcontainers. A auditoria registrou seis testes aprovados, build funcional e HTTP validado. Não foram repetidos nesta tarefa.

Uma checagem pontual em 07/09/2026 confirmou o mesmo HEAD e working tree limpa. O DTO `EquipmentResponse` já foi integrado. As referências antigas que ainda o propõem não justificam reconstruí-lo.

```text
Navegador — interface responsiva, dados fictícios
          │ mesma origem
          ▼
Spring Boot
├── recursos da interface
├── Spring Security: acesso administrativo/turno, sessão e permissões
├── Controllers REST + DTOs + validação
├── Services: casos de uso e transações necessárias
└── Repositories JPA
          │
          ▼
PostgreSQL — uma base por ambiente
          ▲
Flyway — evolução versionada

Testes: Spring + PostgreSQL descartável/Testcontainers
Recuperação: backup lógico e restauração em banco separado
```

**Proposta técnica:** interface em HTML/CSS e JavaScript modular, servida pela própria aplicação. Consome a API existente e evita um segundo serviço de frontend. Um framework frontend não é requisito deste fluxo; se escolhido por motivo de aprendizagem, registrar o custo e a decisão antes de implementar a interface.

**Proposta técnica ajustada à DEC-P-07, DEC-P-09, DEC-P-18 e DEC-P-25:** autenticação por perfil/senha com Spring Security e sessão no servidor: um acesso administrativo próprio e acessos compartilhados de turno. A tela inicial pode listar perfis de turno já registrados para escolha rápida, mas toda nova sessão exige a senha compartilhada; perfil listado não equivale a sessão autenticada. O administrador pode alterar a senha compartilhada a qualquer momento; novos acessos exigem a nova senha, enquanto sessões já abertas seguem até logout ou expiração. Dentro da sessão do turno, a autoria individual é confirmada por nome e PIN curto antes de assumir/retomar ronda ou praticar outra ação autoral fora dela. Usar cookie de sessão, logout e proteção CSRF; não introduzir JWT, Redis ou provedor externo para um único backend nesta demonstração. Armazenar hash de senha e PIN por mecanismo apropriado, nunca texto puro. Expiração de sessão pede novo acesso e não apaga dados persistidos.

Spring fornece autenticação por usuário/senha e suporte a CSRF em aplicações Servlet; a adoção de sessão e mesma origem é uma escolha proporcional a este projeto, não uma exigência universal. Fontes: [autenticação Spring Security](https://docs.spring.io/spring-security/reference/7.0/servlet/authentication/passwords/index.html) e [CSRF](https://docs.spring.io/spring-security/reference/7.0/servlet/exploits/csrf.html).

Não adicionar Kafka, RabbitMQ, microsserviços, CQRS, event sourcing, Kubernetes ou múltiplos bancos. Não existe demanda demonstrada para esses componentes.

### 15.2 Organização do backend

Continuar com a organização existente durante a v0.1, introduzindo nomes de domínio claros:

```text
controller/ e controller/dto/ → contratos HTTP dos casos novos
service/                     → casos de uso de execução, coleta e ocorrência
domain/equipment/            → domínio existente
domain/round/                → roteiro, execução e itens
domain/measurement/          → pontos, resultados e revisões
domain/occurrence/           → acontecimentos e acompanhamentos
domain/access/ ou domain/user/ → perfis de acesso e identidades de operadores
config/ ou security/         → configuração de acesso, quando necessária
```

Esses caminhos são uma proposta de organização, não uma ordem para criar todos os pacotes antecipadamente. Só criar classes com responsabilidade concreta na Issue. Não mover Equipment por estética; uma organização completa por feature pode ser reavaliada se navegar entre domínios se tornar difícil.

Controller cuida de HTTP; Service aplica autorização sobre o objeto, regras e atomicidade; Repository persiste. Regras críticas não ficam apenas no JavaScript. O Service continua sem depender de DTO HTTP, seguindo a escolha existente.

### 15.3 Transações e concorrência

| Caso de uso | O que precisa ser atômico | Direção |
|---|---|---|
| Iniciar execução | Execução, autor e itens/contexto inicial | Transação curta no Service |
| Salvar resultado | Resultado, autoria, identificação da operação e versão vigente | Transação curta por gravação; sem duplicar repetição |
| Corrigir resultado | Verificar autor ou administrador, criar revisão e atualizar referência vigente | Uma transação, preservando original |
| Registrar ocorrência | Ocorrência e registro inicial de autoria | Uma transação |
| Acompanhar/resolver ocorrência | Evento de acompanhamento e eventual mudança de situação | Uma transação com controle de versão |
| Concluir/deixar pendente/retomar | Validar situação, turno e autoria | Transação protegida contra coleta ou mudança concorrente |
| Encerrar turno | Encerrar rondas pendentes como não concluídas, uma única vez | Transação protegida contra retomada/coleta concorrente |

Não manter uma transação aberta enquanto o operador percorre equipamentos. Leituras anteriores permanecem salvas mesmo que uma ação posterior falhe. Uma ocorrência aberta não precisa fazer parte da transação de salvar a leitura, pois são ações independentes.

Todas as gravações numa execução devem verificar que ela ainda está aberta. A verificação e a escrita precisam estar protegidas contra encerramento concorrente. Proposta simples: bloquear a mesma linha de execução nas transações curtas de gravação e encerramento; usar versão esperada para correções concorrentes. A estratégia exata deve ser demonstrada em teste, inclusive quando o timeout deixa o cliente sem saber se houve commit.

`@Transactional` provavelmente fará sentido nesses métodos de Service. Não aplicar indiscriminadamente em Controllers nem pressupor que a anotação, sozinha, resolve concorrência ou chamadas internas que não passam pelo mecanismo transacional do Spring. Fonte técnica: [transações declarativas Spring](https://docs.spring.io/spring-framework/reference/data-access/transaction/declarative/annotations.html).

### 15.4 Tratamento dos problemas já comprovados

| Achado do checkpoint | Comportamento esperado proposto | Limite/decisão |
|---|---|---|
| Código duplicado retorna 500 | 409, código de erro estável e indicação do campo `code` | Constraint única permanece como defesa contra concorrência |
| Textos excedem colunas | 400 antes de persistir; informar máximo do campo | `code` 50, `name` 100, `location` 100, conforme V1 |
| Espaços são preservados | **DEC-P-44:** remover espaços nas extremidades do código antes da unicidade | Aplicar a novos cadastros; não renomear dados existentes automaticamente |
| Caixa do código | **DEC-P-44:** preservar caixa; unicidade é sensível à caixa após `trim()` | Não inferir padrão industrial de maiúsculas/minúsculas |
| Formato do código | Não impor regex de tags da planta | Formato definitivo permanece pendente |
| Cadastro atribui STOPPED | Não criar observação física no cadastro | Ver proposta de transição abaixo |

Não mapear toda `DataIntegrityViolationException` para duplicidade: conflito de código deve ser identificado especificamente. Uma violação inesperada de integridade pode representar defeito interno.

Antes de normalizar códigos já existentes, identificar colisões e decidir a reconciliação. Não renomear automaticamente dados ou substituir uma migração aplicada. A demonstração usará exemplos sintéticos novos, sem manipular o banco persistente existente.

**Proposta para o estado inicial:** `Equipment.active=true` continua significando inclusão no cadastro. A condição operacional inicial será "sem observação registrada" e não será criada como leitura. O `status` atual deve ser tratado como campo legado do cadastro, sem origem temporal. Proposta de transição: preservar valores existentes com essa classificação, tornar o campo legado opcional para novos cadastros e não atribuir `STOPPED` automaticamente; a interface operacional consulta observações separadas. Isso exige migration e revisão explícita do contrato/testes de Equipment. Não reclassificar valores antigos como medições, nem inventar horários. A estratégia definitiva depende de aprovação da decisão técnica T-04.

O POST de Equipment atualmente retorna 200. O plano não mistura automaticamente sua alteração para 201 com correção de duplicidade ou tamanho. Novos contratos definirão status e localização de recurso na Issue correspondente.

### 15.5 Contrato mínimo de erro

Usar `ProblemDetail` do Spring MVC, com `type`, `title`, `status`, `detail` e `instance`, acrescentando apenas `code` estável e `fieldErrors` quando cabível. Mensagens ao usuário em português, sem SQL, senha, token, stack trace ou valor sensível devolvido no erro.

| Situação | Status proposto | Exemplo de código |
|---|---:|---|
| Corpo/formato/tamanho inválido | 400 | `VALIDATION_ERROR` |
| Sessão ausente ou expirada | 401 | `AUTHENTICATION_REQUIRED` |
| Perfil sem permissão | 403 | `ACCESS_DENIED` |
| Recurso inexistente | 404 | `RESOURCE_NOT_FOUND` |
| Código duplicado | 409 | `EQUIPMENT_CODE_CONFLICT` |
| Ronda encerrada, revisão obsoleta ou transição incompatível | 409 | `STATE_CONFLICT` / `VERSION_CONFLICT` |
| Falha interna inesperada | 500 | `INTERNAL_ERROR` |

"Operação não permitida" por perfil é 403; impossibilidade causada pelo estado atual é 409. API não devolve página HTML de login quando o frontend espera JSON. Erros da cadeia de segurança precisam seguir o mesmo formato dos Controllers. CSRF inválido é 403 com instrução de recuperar sessão/token, sem sugerir desativar a proteção.

Um tratamento central simples é suficiente. `ProblemDetail` é suportado pelo Spring MVC; a seleção dos códigos acima é proposta do RefrigOps. Fonte: [respostas de erro Spring](https://docs.spring.io/spring-framework/reference/web/webmvc/mvc-ann-rest-exceptions.html).

### 15.6 Decisões que exigirão ADR

| Referência provisória | Decisão duradoura | Tratamento proposto |
|---|---|---|
| T-01 | Uma aplicação, interface na mesma origem e autenticação por sessão | Nova ADR quando aprovada; comparar complexidade de alternativa com frontend separado |
| T-02 | Roteiro configurado e contexto preservado por execução | Nova ADR para não reinterpretar registros após mudanças cadastrais |
| T-03 | Revisões explícitas, repetição segura e concorrência | Nova ADR proporcional, explicando atomicidade e rejeição de sobrescrita |
| T-04 | Separação de cadastro e condição observada; transição do status legado | Nova ADR com efeitos sobre schema e contrato |
| T-05 | Semântica de valor, ausência, fonte e horários | Refinar a ADR-0005 existente após aprovação; não criar duplicata |

Não numerar novas ADRs antes de conferir o índice na futura branch. ADRs 0001–0003 permanecem como base. O status atrasado da ADR-0004 deve ser corrigido numa atualização documental autorizada; seu DTO já existe. Contrato de erro, tamanho de campo e nomes triviais podem ficar no contrato/Issue sem ADR própria.

## 16. Persistência e evolução com Flyway

Manter PostgreSQL e `ddl-auto=validate`. Não editar V1. Cada incremento com alteração persistente recebe migration nova, teste em banco vazio e, quando aplicável, teste de atualização do estado anterior.

Sequência conceitual, sem reservar números:

1. **Acesso, identidade e adequações de Equipment:** perfil administrativo, perfis de turno e identidades de operadores; tratamento do campo de estado legado depois da decisão T-04. Podem ser migrations separadas em Issues distintas.
2. **Configuração da coleta:** pontos por equipamento, roteiro simples, ordem e programação reutilizável dos turnos; sem dados industriais reais versionados.
3. **Execução:** ocorrência do turno, rondas previstas, operador identificado como autor, desfecho e itens com contexto preservado.
4. **Resultados e revisões:** valor/ausência, horários, autoria, revisão e proteção contra repetição.
5. **Condição observada:** registro por equipamento e execução, conforme regra aprovada.
6. **Ocorrências e acompanhamentos:** acontecimento, situação e eventos ligados ao original.

Essa sequência começa na próxima versão livre depois de V1; não significa obrigatoriamente seis migrations. Numerar e nomear somente no trabalho de cada Issue, sem colisões entre branches.

Regras persistentes a detalhar nas respectivas Issues:

- FKs para autor, execução, equipamento/ponto e revisão original; impedir remoções que destruam histórico.
- Garantia de um resultado lógico vigente por item e referência única para operação repetida no escopo escolhido.
- Exclusão mútua entre valor medido e ausência justificada; campos obrigatórios definidos por caso.
- Horários como instantes persistidos com suporte a fuso, apresentados no fuso escolhido; não guardar hora local ambígua como única evidência.
- Valor decimal com escala explícita; unidade e rótulo originais preservados na execução.
- Índices iniciais para consultas realmente previstas: execuções por período/autor, resultados por execução/equipamento, ocorrências por situação/equipamento.
- Dados de demonstração separados de migrations estruturais e habilitados somente no ambiente de demonstração, com IDs/nomes fictícios. Provisionamento de acessos não inclui senha de uso real no Git.

Relações JPA não devem serializar automaticamente toda a árvore no JSON. Continuar usando DTOs e consultas orientadas às telas; evitar carregar todo o histórico para mostrar uma lista.

## 17. Segurança, ambiente e recuperação

### 17.1 Segurança mínima da demonstração

- Perfil administrativo ou de turno autenticado e ações autorizadas no backend. A autoria individual de ações exigidas usa identidade selecionada e PIN confirmado; o histórico registra essa atribuição e não a apresenta como autenticação por login individual.
- Senhas dos perfis de acesso e PINs de operadores com hash apropriado; administrador cria ou redefine PIN; após cinco tentativas inválidas, bloquear a identificação até redefinição administrativa. Credenciais de ambiente ficam fora dos fontes de produção, em variável ou configuração local protegida, sem valores de segredo em documentação.
- Adicionar proteção para arquivos de ambiente ao `.gitignore` numa futura Issue; não presumir que variável de ambiente evita vazamento em log.
- Cookie de sessão `HttpOnly`; `Secure` com HTTPS e política `SameSite` adequada. Demonstração HTTP estritamente em loopback deve ser configuração local explícita; acesso por outros dispositivos exige avaliar transporte e rede.
- CSRF habilitado para autenticação por cookie. Mesma origem reduz necessidade de CORS; se houver exceção de desenvolvimento, permitir só origens explícitas, sem wildcard com credenciais.
- PostgreSQL sem exposição externa desnecessária; acesso de aplicação com privilégios mínimos definidos para o ambiente. Não usar o usuário de testes como especificação de produção.
- Validação de entrada no backend e mensagens sem detalhes internos. Renderizar texto de ocorrência como texto, não HTML executável.
- Logs técnicos sem corpos integrais de requisição, senhas, cookies ou valores operacionais. Identificadores mínimos para diagnóstico não substituem a revisão de retenção/acesso.
- Revisar avisos atuais das dependências antes de disponibilizar o ambiente. Versões e aplicabilidade devem ser reconfirmadas na Issue; não trocar bibliotecas silenciosamente nesta etapa.
- Indicação persistente de que dados são fictícios. Não publicar ou tornar o repositório público como parte deste planejamento.

As credenciais previsíveis do Compose foram identificadas como desenvolvimento. Isso não comprova vazamento de produção. O requisito é separar seu uso de qualquer ambiente real e evitar reutilização indevida.

### 17.2 Backup e recuperação proporcionais

**DECISÃO DO PRODUTO:** backup lógico do banco da demonstração, com ferramenta PostgreSQL compatível, em formato restaurável, acompanhado de data, versão da aplicação/schema e verificação de integridade do arquivo. O responsável pelo projeto guarda uma cópia local fora do volume do banco e fora do Git até o encerramento da demonstração e verifica sucesso e acesso ao arquivo.

Executar ao menos um exercício de restauração em banco separado: recriar os requisitos de acesso, restaurar, iniciar a versão compatível da aplicação e verificar a meta configurada de um turno, uma execução concluída, uma execução pendente retomável, uma execução não concluída por fim de turno, uma correção e uma ocorrência com acompanhamentos. Nunca restaurar sobre banco existente como parte automática da demonstração.

`pg_dump` gera backup lógico e formatos próprios podem ser restaurados por `pg_restore`. O backup de uma base não inclui automaticamente papéis globais; a preparação do ambiente precisa documentar esse requisito separadamente, sem publicar credenciais. Fonte: [backup lógico PostgreSQL 17](https://www.postgresql.org/docs/17/backup-dump.html).

Para a demonstração, proposta: backup ao fim da sessão de avaliação e antes de mudança de schema que afete os registros de evidência. Isso não promete recuperação de tudo o que foi feito depois do último backup. Responsável, destino e duração da retenção precisam ser definidos antes do exercício.

### 17.3 Porta de entrada para futuro uso real

O encerramento da demonstração não autoriza coleta industrial. Para piloto real, definir área, usuários, dados permitidos, pontos/unidades validados, segurança de rede, HTTPS, privacidade, suporte, fallback e procedimento de recuperação. Definir perda máxima tolerada de dados e tempo de recuperação; só então escolher frequência/retencão de backup e verificar se o mecanismo proposto atende.

Essa etapa é uma futura decisão de produto e operação, não escopo oculto que impede concluir a demonstração com dados fictícios.

## 18. Estratégia de testes

Preservar a suíte existente e seu isolamento. Não estabelecer percentual arbitrário de cobertura. Testes devem demonstrar regras que poderiam falhar.

| Nível | Comportamentos relevantes |
|---|---|
| Unitário | Desfechos permitidos, ponto pendente, valor versus ausência, motivo obrigatório, correção, confirmação por PIN, bloqueio após cinco erros e regras de autoria quando isoláveis |
| Integração PostgreSQL/Testcontainers | Migrations, FKs, precisão decimal, revisões persistidas, unicidade, rollback e concorrência entre coleta/encerramento |
| HTTP | DTOs, erros 400/401/403/404/409/500, autenticação do perfil, confirmação de PIN para autoria, CSRF, autorização por objeto e resposta JSON consistente |
| Interface e fluxo completo | Equipe entra pelo perfil do turno; A se identifica e registra por dois tipos de equipamento; B consulta; falha de rede não se parece com sucesso; campo não aplicável não recebe zero |
| Recuperação | Restaurar banco separado e recuperar registros, autoria e revisões pela aplicação |

Casos especialmente importantes:

1. Comprovar formato/limites de cada texto separadamente e conflito de código sob concorrência.
2. Verificar todos os campos de um mesmo objeto da resposta, sem asserções que possam combinar objetos diferentes.
3. Recarregar o estado persistido ao testar revisões e relacionamentos, não depender somente do cache JPA.
4. Impedir autoria por texto livre, identificador inexistente ou PIN inválido; bloquear a identificação após cinco erros e exigir redefinição administrativa; depois de assumir a ronda, preservar o autor confirmado.
5. Testar encerramento concorrente com salvamento: nenhuma leitura entra após a execução ter sido encerrada.
6. Testar resposta perdida e repetição com mesma chave: sem duplicação; conteúdo divergente gera conflito.
7. Confirmar que campo em branco, zero digitado e ausência justificada são resultados distintos.
8. Alterar configuração para execuções futuras e provar que o histórico anterior não muda.
9. Tentar criar ou alterar uma programação para sobrepor outra do mesmo perfil e confirmar conflito sem criar turno ambíguo.

Durante a implementação: teste focado por Issue; suíte integrada antes de PR; verificação de interface nos incrementos visíveis. Um único fluxo grande ponta a ponta não substitui testes das regras.

## 19. Critérios objetivos de sucesso

Todos os critérios abaixo são propostos para aprovação. Sua execução será registrada posteriormente; nenhum está declarado como aprovado nesta tarefa documental.

| Critério | Evidência esperada |
|---|---|
| CS-01 — Acesso e identificação | Administrador e equipe entram por acessos distintos; perfil de turno listado ainda exige senha em nova sessão; sem sessão não acessam dados; A seleciona sua identidade e confirma o PIN antes de uma ação autoral; cinco erros bloqueiam até redefinição administrativa; tentativa não autorizada é recusada |
| CS-02 — Roteiro misto | No perfil do turno, A se identifica e assume uma execução que contém ao menos um compressor e um recipiente com pontos distintos |
| CS-03 — Coleta | Uma leitura salva reaparece com valor, unidade, fonte, equipamento, autor e horários corretos |
| CS-04 — Ausência | A justifica uma ausência; B vê o motivo e nenhum valor artificial |
| CS-05 — Estado | B distingue cadastro ativo de condição observada em um horário |
| CS-06 — Progresso e desfecho | Conclusão aceita pontos medidos e ausências justificadas, bloqueia qualquer ponto pendente e exibe as ausências; o mesmo operador deixa uma ronda pendente e a retoma durante o turno sem perder resultados; ao fim do turno, pendência vira não concluída e não pode ser retomada |
| CS-07 — Continuidade | Ocorrência aberta continua visível depois do fim da ronda; outro operador confirma nome e PIN, registra acompanhamento ou resolução própria e preserva o acontecimento original |
| CS-08 — Correção | Autor corrige apenas o próprio registro e administrador corrige outro quando necessário; original e revisão são recuperáveis, com motivo/autor/horário; concorrência não sobrescreve silenciosamente |
| CS-09 — Compreensão | Nesta MVP, o próprio responsável executa verificação funcional; avaliação de compreensão por outra pessoa fica registrada como não realizada |
| CS-10 — Falha | Falha de envio fica visível; repetição não duplica; nova consulta recupera os registros confirmados |
| CS-11 — Recuperação | Banco restaurado reproduz os exemplos de execução, correção e ocorrência |
| CS-12 — Reprodutibilidade | Ambiente é iniciado pelo procedimento documentado e a suíte passa sem apontar ao banco persistente de desenvolvimento |

Perguntas para a avaliação de compreensão:

- Quem fez a ronda e quando ela começou/terminou?
- Qual ponto foi medido e de qual fonte veio o valor?
- O que não foi medido, e qual foi a justificativa?
- Qual condição foi relatada e em que horário, sem presumir que ainda seja atual?
- O que continua aberto para acompanhamento?
- O que foi corrigido e qual era o dado anterior?

Registrar respostas, dificuldades, erros, pedidos de ajuda e tempo observado. Para CS-09, a demonstração registra verificação funcional pelo próprio responsável e não declara compreensão por outra pessoa sem essa avaliação. Quando ela ocorrer, nenhuma explicação oral do autor deve ser necessária para localizar e interpretar os registros previstos. Se houver dificuldade, revisar o fluxo e repetir o cenário alterado, sem esconder o resultado anterior.

Não fixar duração máxima da ronda nem percentuais de ganho sem uma referência. Critérios de ganho operacional ficam para pesquisa/piloto real.

## 20. Definition of Done da MVP v0.1

Poderemos declarar **"MVP demonstrativa v0.1 concluída"** quando:

- [ ] O usuário tiver revisado e aprovado o recorte, regras necessárias e decisões técnicas das Issues implementadas.
- [ ] REQ-MVP-001 a REQ-MVP-012 estiverem implementados, ou houver alteração explícita e rastreável do escopo antes do aceite.
- [ ] Compressores e recipientes participarem do mesmo fluxo demonstrativo.
- [ ] Os critérios CS-01 a CS-12 tiverem evidência registrada; CS-09 deve declarar se houve apenas verificação funcional ou avaliação por outra pessoa.
- [ ] Testes relevantes passarem, sem testes desativados para obter sucesso.
- [ ] Schema novo e evolução a partir do checkpoint estiverem testados, com V1 preservada.
- [ ] Interface móvel e desktop-alvo permitirem coleta, desfecho e consulta sem perda silenciosa.
- [ ] Segurança mínima e contratos de erro estiverem implementados e verificados.
- [ ] Nenhum dado industrial real for necessário para demonstrar o produto.
- [ ] Aplicação puder ser iniciada conforme documentação e os registros sobreviverem à reinicialização normal.
- [ ] Backup e restauração isolada tiverem sido demonstrados.
- [ ] Documento Mestre, contrato da API, ADRs aplicáveis e contexto atual refletirem a entrega.
- [ ] Requisitos implementados estiverem ligados às Issues/PRs e testes correspondentes.
- [ ] Revisão técnica e sessão de aprendizagem tiverem ocorrido; o usuário conseguir explicar o fluxo implementado.
- [ ] Limites conhecidos e decisões adiadas estiverem registrados sem serem apresentados como recursos prontos.

"MVP demonstrativa concluída por verificação funcional" e "compreensão validada por outra pessoa" são marcos diferentes. Pela DEC-P-42, a demonstração inicial pode concluir o primeiro; o segundo só pode ser registrado após a avaliação posterior correspondente. Nenhum deles significa "validado para operação industrial".

## 21. Matriz de riscos

Probabilidades são avaliações qualitativas de planejamento, sem medição estatística. Dados sintéticos reduzem consequências operacionais, mas não eliminam riscos de modelagem.

| Risco | Probabilidade | Impacto | Mitigação |
|---|---|---|---|
| Regra proposta ser confundida com prática aprovada | Relevante pela quantidade de hipóteses | Alto no uso real | Classificações explícitas e aprovação antes da Issue de domínio |
| Unidade ou fonte incorreta | Não medida; há lacunas documentais | Alto no uso real | Dados fictícios; unidade/fonte visíveis; sem conversão e sem transposição para planta |
| Confundir ativo, observado e coleta | Relevante pelo status legado | Alto para compreensão | Dimensões separadas, horários e CS-05 |
| Interface exigir explicação constante | Não medida | Alto para a hipótese | Avaliação por outra pessoa e revisão baseada em erros |
| Conectividade insuficiente | Não medida no local real | Alto para coleta online | Demonstração local; teste específico antes do piloto; offline fora do escopo |
| Falha de rede gerar duplicação | Possível | Médio/alto para integridade | Operação identificada, reconciliação e testes de repetição |
| Gravação competir com encerramento | Possível | Alto para integridade | Transações curtas protegidas e teste concorrente |
| Escopo crescer para manutenção e escala 6x2 completa | Relevante na documentação ampla | Alto para prazo/compreensão | Implementar apenas o turno mínimo exigido pelo caso de uso; composição automática da escala fica fora |
| Acesso indevido | Presente no checkpoint; será tratado | Alto | Identidade, autorização de objeto e teste negativo |
| Dados reais entrarem no demo/log/Git | Não medida | Alto | Exemplos sintéticos, revisão de dados e logs, sem importação automática |
| Perda de registros | Possível | Alto para continuidade da demonstração | Backup fora do volume, restauração e responsável definido |
| Demonstração ser usada como prova de valor industrial | Possível | Alto para decisão de produto | Separar aceite funcional de piloto operacional |

## 22. Decisões pendentes e condições de prontidão

### 22.1 Decisões já tomadas nesta etapa

| ID | Decisão | Evidência |
|---|---|---|
| DEC-P-01 | Demonstração com dados fictícios primeiro | Resposta explícita do usuário em 07/09/2026 |
| DEC-P-02 | Incluir compressores e recipientes na primeira ronda | Resposta explícita do usuário em 07/09/2026 |
| DEC-P-03 | Permitir concluir a ronda com ausências justificadas; ponto pendente bloqueia a conclusão | Resposta explícita do usuário em 07/09/2026 |
| DEC-P-04 | Permitir ao mesmo operador retomar uma ronda pendente durante todo o próprio turno | Resposta explícita do usuário em 07/09/2026 |
| DEC-P-05 | Administrador configura quantas rondas o turno deve realizar coletivamente; a quantidade não é fixa | Resposta explícita do usuário em 07/09/2026 |
| DEC-P-06 | Administrador configura início e término do turno; nenhuma ronda tem leitor predeterminado e qualquer operador participante pode assumir uma livre | Resposta explícita do usuário em 07/09/2026 |
| DEC-P-07 | Operação usa login compartilhado do turno; o operador se identifica quando uma ação exige autoria individual | Resposta explícita do usuário em 07/09/2026 |
| DEC-P-08 | Administrador configura uma programação reutilizável de horários e quantidade de rondas, que pode ser alterada sem criar cada turno/ronda manualmente | Resposta explícita do usuário em 07/09/2026 |
| DEC-P-09 | Operador seleciona seu nome e confirma PIN pessoal curto ao assumir/retomar ronda ou realizar outra ação autoral fora dela | Resposta explícita do usuário em 07/09/2026 |
| DEC-P-10 | Ao término do turno, ronda pendente é encerrada como não concluída por fim de turno, preservando registros e sem retomada ou transferência | Resposta explícita do usuário em 07/09/2026 |
| DEC-P-11 | Alteração da programação só afeta turnos futuros; turno iniciado e suas execuções preservam horário, meta e contexto originais | Resposta explícita do usuário em 07/09/2026 |
| DEC-P-12 | Qualquer operador identificado por nome e PIN pode acompanhar ou resolver ocorrência de outro, criando evento próprio sem apagar o original | Resposta explícita do usuário em 07/09/2026 |
| DEC-P-13 | Operador corrige apenas seus próprios registros de coleta; administrador pode corrigir qualquer registro, sempre com motivo e histórico preservado | Resposta explícita do usuário em 07/09/2026 |
| DEC-P-14 | Demonstração utiliza dois compressores fictícios e um recipiente fictício | Resposta explícita do usuário em 07/09/2026 |
| DEC-P-15 | Demonstração usa um ponto fictício de pressão por equipamento e um ponto adicional de frequência em um compressor | Resposta explícita do usuário em 07/09/2026 |
| DEC-P-16 | Programação de turnos da demonstração tem recorrência diária; não calcula automaticamente a escala 6x2 | Resposta explícita do usuário em 07/09/2026 |
| DEC-P-17 | Programações diárias do mesmo perfil de turno não podem ter horários sobrepostos | Resposta explícita do usuário em 07/09/2026 |
| DEC-P-18 | Perfil de turno pode aparecer na tela inicial, mas toda nova sessão exige a senha compartilhada | Resposta explícita do usuário em 07/09/2026 |
| DEC-P-19 | Administrador cria ou redefine PIN de operador; cinco tentativas inválidas bloqueiam a identificação até redefinição administrativa | Resposta explícita do usuário em 07/09/2026 |
| DEC-P-20 | Administrador configura quantos perfis de turno existem e, para cada um, início, término e quantidade coletiva de rondas | Resposta explícita do usuário em 07/09/2026 |
| DEC-P-21 | Um perfil de turno pode terminar no dia seguinte e preserva sua identidade configurada, como o 3º turno | Resposta explícita do usuário em 07/09/2026 |
| DEC-P-22 | Ronda pendente é encerrada exatamente no horário final configurado do turno, sem tolerância | Resposta explícita do usuário em 07/09/2026 |
| DEC-P-23 | Operador pode iniciar uma ronda livre até o horário final configurado, mesmo que reste pouco tempo de turno | Resposta explícita do usuário em 07/09/2026 |
| DEC-P-24 | Administrador pode desativar temporariamente um perfil de turno, impedindo novos turnos e preservando o histórico | Resposta explícita do usuário em 07/09/2026 |
| DEC-P-25 | Administrador pode alterar a senha compartilhada do perfil de turno; sessões abertas seguem até logout ou expiração | Resposta explícita do usuário em 07/09/2026 |
| DEC-P-26 | Desativar um perfil de turno invalida imediatamente suas sessões abertas | Resposta explícita do usuário em 07/09/2026 |
| DEC-P-27 | Reativar perfil de turno volta a gerar somente turnos futuros, sem recriar períodos passados | Resposta explícita do usuário em 07/09/2026 |
| DEC-P-28 | Administrador pode renomear perfil de turno; turnos históricos preservam o nome existente quando foram gerados | Resposta explícita do usuário em 07/09/2026 |
| DEC-P-29 | Administrador só pode excluir definitivamente perfil de turno sem turnos ou registros vinculados; nos demais casos, usa desativação | Resposta explícita do usuário em 07/09/2026 |
| DEC-P-30 | Novo perfil de turno é criado ativo por padrão | Resposta explícita do usuário em 07/09/2026 |
| DEC-P-31 | Criação de perfil de turno exige definição de senha compartilhada | Resposta explícita do usuário em 07/09/2026 |
| DEC-P-32 | Somente administrador define ou altera senha compartilhada e a repassa externamente ao turno; não há senha temporária nem troca obrigatória na MVP | Resposta explícita do usuário em 07/09/2026 |
| DEC-P-33 | Administrador pode bloquear manualmente identidade de operador, preservando sua autoria histórica | Resposta explícita do usuário em 07/09/2026 |
| DEC-P-34 | Administrador pode reativar identidade de operador bloqueada, sem recriá-la e preservando seu histórico | Resposta explícita do usuário em 07/09/2026 |
| DEC-P-35 | Administrador pode renomear identidade de operador; registros históricos preservam o nome existente quando foram realizados | Resposta explícita do usuário em 07/09/2026 |
| DEC-P-36 | Administrador só pode excluir definitivamente identidade de operador sem ações registradas; nos demais casos, usa bloqueio | Resposta explícita do usuário em 07/09/2026 |
| DEC-P-37 | Nova identidade de operador é criada ativa por padrão | Resposta explícita do usuário em 07/09/2026 |
| DEC-P-38 | Criação de identidade de operador exige definição de PIN pessoal | Resposta explícita do usuário em 07/09/2026 |
| DEC-P-39 | Redefinição de PIN de identidade bloqueada a reativa automaticamente, preservando histórico | Resposta explícita do usuário em 07/09/2026 |
| DEC-P-40 | Operador não altera o próprio PIN na MVP; criação e redefinição ficam somente com administrador | Recomendação aceita pelo usuário em 07/09/2026 |
| DEC-P-41 | Operador seleciona sua identidade em lista de nomes ativos antes de informar o PIN | Recomendação aceita pelo usuário em 07/09/2026 |
| DEC-P-42 | CS-09 registra verificação funcional pelo próprio responsável; avaliação de compreensão por outra pessoa fica fora da demonstração inicial | Recomendação adotada para finalizar a documentação em 07/09/2026 |
| DEC-P-43 | Validar interface no Chrome de computador local e em viewport móvel; telefone físico não entra na demonstração inicial | Recomendação adotada para finalizar a documentação em 07/09/2026 |
| DEC-P-44 | Código de equipamento remove espaços externos e é único após essa normalização | Recomendação adotada para finalizar a documentação em 07/09/2026 |
| DEC-P-45 | Responsável pelo projeto mantém backup local fora do Git até o encerramento da demonstração | Recomendação adotada para finalizar a documentação em 07/09/2026 |

DEC-P-01 e DEC-P-02 substituem a hipótese anterior de começar somente com compressores ou assumir piloto real imediato. DEC-P-03 confirma a regra proposta de conclusão para a demonstração; sua adoção em piloto real ainda exige validação operacional. DEC-P-04 substitui a proposta de interrupção terminal. DEC-P-05 substitui qualquer quantidade fixa de rondas. DEC-P-06 confirma horários administráveis e uma meta coletiva, sem leitor fixo por ronda. DEC-P-07 substitui login individual obrigatório de cada operador por acesso compartilhado do turno e identificação separada para autoria. DEC-P-08 substitui a criação manual de cada turno/ronda por programação reutilizável e editável. DEC-P-09 confirma nome e PIN curto para a autoria individual nas ações exigidas. DEC-P-10 encerra a ronda pendente quando o turno termina, sem transferência ou reabertura. DEC-P-11 torna a programação versionada na prática: alterações geram apenas turnos futuros. DEC-P-12 permite continuidade de ocorrências por outro operador com nome e PIN, preservando cada evento. DEC-P-13 limita correções de coleta do operador ao próprio autor e reserva correções de terceiros ao administrador. DEC-P-14 confirma o conjunto demonstrativo de dois compressores e um recipiente. DEC-P-15 confirma os quatro pontos sintéticos, com pressão nos três equipamentos e frequência em um compressor. DEC-P-16 fixa recorrência diária sem cálculo da escala 6x2. DEC-P-17 impede sobreposição de horários para o mesmo perfil de turno. DEC-P-18 permite listar perfis de turno na entrada, exigindo senha compartilhada em cada nova sessão. DEC-P-19 centraliza criação e redefinição de PIN no administrador e bloqueia a identificação após cinco erros. DEC-P-20 torna a quantidade de perfis de turno, seus horários e metas configurável pelo administrador. DEC-P-21 permite que um turno atravesse a meia-noite sem mudar sua identidade de perfil. DEC-P-22 fixa o encerramento de ronda pendente no horário final configurado, sem tolerância. DEC-P-23 permite iniciar uma ronda livre até o horário final configurado. DEC-P-24 permite desativar temporariamente um perfil de turno, preservando seu histórico. DEC-P-25 permite alterar a senha compartilhada, sem interromper sessões já abertas. DEC-P-26 invalida imediatamente sessões abertas ao desativar o perfil de turno. DEC-P-27 retoma a geração somente para turnos futuros ao reativar um perfil. DEC-P-28 permite renomear perfil de turno, preservando seu nome nos turnos históricos. DEC-P-29 restringe exclusão definitiva a perfil sem turnos ou registros vinculados. DEC-P-30 cria novo perfil ativo por padrão. DEC-P-31 exige senha compartilhada na criação de perfil. DEC-P-32 reserva a definição e distribuição externa da senha compartilhada ao administrador, sem senha temporária. DEC-P-33 permite bloqueio manual de identidade de operador, com autoria histórica preservada. DEC-P-34 permite reativar identidade bloqueada sem perder histórico. DEC-P-35 permite renomear identidade de operador, preservando seu nome nos registros históricos. DEC-P-36 restringe exclusão definitiva de identidade a quem não possui ações registradas. DEC-P-37 cria nova identidade ativa por padrão. DEC-P-38 exige PIN pessoal na criação de identidade. DEC-P-39 redefine PIN e reativa automaticamente identidade bloqueada. DEC-P-40 mantém criação e redefinição de PIN somente com administrador. DEC-P-41 fixa a seleção da identidade em uma lista de nomes ativos antes do PIN. DEC-P-42 limita a demonstração inicial à verificação funcional pelo responsável, sem alegar compreensão por terceiros. DEC-P-43 define a validação no Chrome local e em viewport móvel. DEC-P-44 define `trim()` externo e unicidade do código normalizado. DEC-P-45 define responsável, destino e retenção do backup da demonstração. Essas escolhas não exigem um mecanismo completo de escala 6x2.

### 22.2 Produto

| Decisão | Por que importa | Evidência disponível | Quem decide |
|---|---|---|---|
| Pessoa avaliadora e sessão | Fechada em DEC-P-42 | Verificação funcional pelo responsável; não alegar avaliação por outra pessoa | Usuário |
| Navegador, viewport e telefone físico | Fechada em DEC-P-43 | Chrome local e viewport móvel; sem telefone físico inicial | Usuário |
| Normalização e unicidade de código | Fechada em DEC-P-44 | `trim()` externo e unicidade do código normalizado | Usuário |
| Responsável/destino/retenção do backup demo | Fechada em DEC-P-45 | Responsável guarda cópia local fora do Git até o encerramento da demonstração | Usuário e responsável técnico |

### 22.3 Operação — não bloquearão o software fictício se seus exemplos forem aprovados

| Decisão | Por que importa | Evidência disponível | Quem decide |
|---|---|---|---|
| Configuração de área, roteiro, equipe e frequência para uso real | Define piloto posterior | Não documentada publicamente; exige validação privada e autorizada | Usuário, operadores e supervisão |
| Pontos, unidades, origens e convenções de pressão | Evita interpretação física incorreta | Modelo público abstrato; detalhes reais exigem validação privada | Profissional responsável e operação |
| Estados de equipamento e aplicabilidade | Evita máquina de estados inventada | Exemplos sintéticos; sem transições operacionais aprovadas | Operação e responsável técnico |
| Significado da pendência e resolução | Não confundir informação com autorização | Problema genérico de continuidade entre turnos, sem processo real documentado | Operadores e supervisão |
| Dados permitidos, acesso e retenção reais | Protege pessoas e instalação | Limites documentados; autorização não determinada | Responsáveis pelos dados e instalação |
| Conectividade, fallback e recuperação reais | Decide viabilidade do piloto | Ainda não medidos/contratados | TI/OT, operação e responsável pelo produto |

### 22.4 Técnica

| Decisão | Por que importa | Evidência disponível | Quem decide |
|---|---|---|---|
| T-01 — mesma origem, sessões de administrador/turno e PIN de autoria, JS modular | Limita componentes e estratégia de segurança | Backend MVC aproveitável; login compartilhado e PIN curto aprovados | Usuário com revisão técnica |
| T-02 — contexto preservado no início | Garante significado histórico | Cadastro pode evoluir; medições contextualizadas documentadas | Usuário com revisão técnica |
| T-03 — revisões, repetição e concorrência | Evita perda/duplicação de registros | Histórico exigido; comportamento ainda inexistente | Usuário com revisão técnica |
| T-04 — transição de `Equipment.status` | Corrige semântica sem inventar passado | Padrão STOPPED comprovado | Usuário com revisão técnica |
| T-05 — resultado/ausência e horários | Base do modelo persistente | ADR-0005 proposta e documentos 03/04 | Usuário; validação operacional para dados reais |
| Precisão decimal e formato de horário | Evita truncamento e ambiguidade | Não há leituras implementadas | Revisão técnica na Issue dos pontos/resultados |

Não é necessário resolver todas as dúvidas sobre a planta para preparar uma demonstração fictícia. As decisões técnicas que mudem contrato, ciclo de vida ou estrutura devem ser refinadas na Issue correspondente. A aprovação deste Documento Mestre não autoriza implementação automática: cada incremento depende de uma Issue pequena, revisada e aprovada antes da implementação.

## 23. Evolução pós-MVP

Primeiro executar a verificação funcional da demonstração. Quando houver avaliação posterior por outra pessoa, uma falha da hipótese de compreensão deve levar à correção do fluxo antes de adicionar funcionalidades relacionadas.

Se houver intenção de uso real, abrir uma etapa de preparação de piloto: descoberta com operadores, validação técnica dos pontos, autorização de dados, dispositivo/conectividade e recuperação. O piloto é uma nova decisão, não deploy automático da demonstração.

Depois, conforme evidência: filtros melhores, comparações históricas, escopo maior de equipamentos, evolução da passagem de turno e eventual tratamento de indisponibilidade. Integrações industriais, telemetria, IA e manutenção preditiva permanecem visão futura com critérios próprios; não entram por consequência de já existir um banco de leituras.

## 24. Mapa preliminar de incrementos e aprendizagem

Os grupos abaixo ordenam resultados e dependências. **Não são Issues criadas, nem autorização para implementar um módulo inteiro em um único PR.** Levar uma pequena parte da interface junto de cada fluxo, em vez de deixar toda a experiência para o final.

| Grupo | Resultado demonstrável e possíveis cortes pequenos | Requisitos | Aprendizagem |
|---|---|---|---|
| 0. Revisão e contexto | Aprovar Documento Mestre; corrigir checkpoint documental em trabalho autorizado; registrar decisões aplicáveis | Base de todos | Descoberta, escopo e ADR |
| 1. Contrato de Equipment | Primeiro tamanho; depois duplicidade, normalização DEC-P-44 e estado legado | 002, 006, 011 | DTO, Bean Validation, constraints e exceções |
| 2. Acesso e identidade mínimos | Acesso administrativo e do turno; identidade do operador e PIN; depois autorização por objeto | 001 | Spring Security, sessão, CSRF, acesso compartilhado e autoria |
| 3. Preparação da ronda | Pontos de dois tipos; depois perfis de turno e programação diária reutilizável, com mudanças aplicadas somente a turnos futuros | 003 | Modelagem, JPA e Flyway |
| 4. Iniciar e visualizar execução | Exibir meta compartilhada; operador assume ronda livre; criar execução com contexto; retomar pendente do mesmo autor | 004, 010 | Relações, transação e frontend/API |
| 5. Primeiro resultado de coleta | Salvar e reler uma medição; depois ausência justificada e condição observada | 005, 006, 010 | Decimais, horários e invariantes |
| 6. Gravação confiável | Repetição segura e correção com revisão; casos de concorrência | 009, 011 | Idempotência limitada, concorrência e histórico |
| 7. Progresso e desfecho | Deixar pendente e retomar no turno; concluir com validação; encerrar pendência como não concluída no fim do turno | 004, 008 | Ciclo de vida e atomicidade |
| 8. Ocorrência | Abrir registro separado; depois acompanhamento e resolução | 007 | Relações, autoria e atualização consistente |
| 9. Consulta e continuidade | B consulta execução; depois resumo de ocorrências abertas e revisões | 008, 009, 010 | Consultas, DTOs de leitura e usabilidade |
| 10. Demonstração reproduzível | Ajustes de interface/erros, procedimento de execução, backup e restauração | 010, 011, 012 | Docker, recuperação e validação ponta a ponta |
| 11. Avaliação e fechamento | Executar CS-01 a CS-12, corrigir problemas observados e revisar DoD | Todos | Teste de hipótese, revisão e comunicação técnica |

Segurança, testes e documentação acompanham os incrementos. Não esperar o grupo 10 para proteger dados ou o grupo 11 para testar regras. Os cortes dentro de cada grupo podem ser reorganizados por dependência, preservando um resultado pequeno por PR.

## 25. Rastreabilidade e processo de implementação

```text
REQ-MVP-xxx
  ↓
DEC/ADR quando há escolha duradoura
  ↓
Issue pequena, aprovada e com um resultado observável
  ↓
implementação pelo Codex → testes → PR → revisão técnica
  → sessão de aprendizagem → compreensão pelo usuário
  → merge autorizado → próxima Issue
```

Exemplos de ligação futura, sem números de Issues ou PRs inventados:

| Requisito | Decisão | Incremento candidato | Evidência futura |
|---|---|---|---|
| REQ-MVP-002 | DEC-P-44 | Tratar duplicidade após `trim()` | Teste HTTP 409 + teste de constraint |
| REQ-MVP-005 | T-05 / ADR-0005 refinada | Registrar ausência sem número | Teste de exclusão valor/ausência + tela |
| REQ-MVP-009 | T-03 | Corrigir resultado com motivo | Original e revisão consultáveis após recarga |
| REQ-MVP-008 | Resumo derivado | Consultar continuidade como B | Teste e avaliação CS-07/CS-09 |

A futura Issue deve informar problema, requisito, comportamento atual, mudança esperada, critérios verificáveis, fora de escopo, estratégia de teste, decisão pendente que a bloqueia e objetivo de aprendizagem. Caminhos exatos e nomes de testes serão ligados quando existirem.

Processo solicitado:

```text
Issue aprovada → implementação pelo Codex → testes → PR
  → revisão técnica → sessão de aprendizagem
  → compreensão pelo usuário → merge autorizado → próxima Issue
```

Na sessão de aprendizagem, o usuário deverá poder explicar: entrada, responsabilidade de cada camada, regra preservada, persistência, falha tratada e evidência de teste. Não considerar merge autorizado só porque os testes passaram.

Este Documento Mestre é a baseline aprovada da MVP demonstrativa v0.1. A Issue #11 / MVP-ISSUE-001 incorporou seu conteúdo ao repositório e alinhou o contexto e os links, sem implementar funcionalidades.

## 26. Evidências, referências e validação desta entrega

### Fontes de produto e domínio

- [Documento mestre do produto](../00-documento-mestre-produto.md): visão ampla e MVP anteriormente provisória.
- [Contexto operacional](../01-contexto-operacional.md): rota, compressores, recipientes e instrumentos.
- [Regras de domínio](../03-regras-negocio-e-dominio.md): separação de estados, horários e conceitos de ocorrência.
- [Medições, unidades e fontes](../04-medicoes-unidades-e-fontes.md): preservação de contexto e lacunas técnicas.
- [Riscos e limites](../05-riscos-seguranca-e-limites.md): limites informacionais e condições para piloto.
- [Roadmap](../06-mvp-roadmap-e-criterios.md): ronda manual, histórico e resumo de passagem.
- [Testes e operação](../07-testes-ambientes-e-operacao.md): isolamento e evolução do schema.
- [Contrato de Equipment](../09-contrato-api-atual.md): API existente a preservar/evoluir conscientemente.
- [ADR-0005](../adr/0005-medicoes-com-contexto.md): proposta existente a refinar.

### Checkpoint e autoridade das decisões

A auditoria desta conversa é o checkpoint técnico pedido pelo usuário. Seus resultados são evidência histórica identificada: seis testes aprovados, build/HTTP validados e problemas de duplicidade/tamanho/estado. Logs gerados ficam em `target/` e podem ser removidos por um futuro clean; não são a única fonte deste documento.

As decisões DEC-P-01 a DEC-P-45 preservam, em cada linha do registro, sua origem como resposta explícita ou recomendação adotada. Propostas técnicas ainda abertas não se tornam aprovadas apenas pela aprovação desta baseline.

Não se repetiu a auditoria completa. Não foram executados novos testes de aplicação, criados bancos, alteradas migrations ou implementadas funcionalidades. A validação desta entrega é documental: consistência de escopo, cobertura dos tópicos pedidos, ligação entre requisitos/aceite/incrementos, referências de arquivos e preservação do checkout.

### Ordem de consulta da baseline

1. Seções 1, 5, 6 e 7: o produto e os limites correspondem ao que você quer demonstrar?
2. Seções 8, 11, 12 e 13: a ronda, as ausências e as ocorrências estão compreensíveis?
3. Seções 15, 16 e 17: a arquitetura e os compromissos técnicos são proporcionais?
4. Seções 19, 20 e 22: o que comprova conclusão e o que ainda depende de refinamento técnico ou validação operacional?
5. Seções 24 e 25: os incrementos permitirão revisão e aprendizagem antes de cada merge?

# Regras de negócio e domínio

## 1. Escopo

Este documento separa:

- comportamento confirmado no código;
- decisões de modelagem;
- hipóteses de domínio;
- regras que ainda exigem validação operacional.
> Nota editorial de saneamento histórico: referências a uma operação específica foram abstraídas. Esta nota não representa uma decisão tomada na data original do documento.

## 2. Glossário provisório

Equipamento, ronda, leitura e ocorrência são conceitos de modelagem distintos; suas regras exigem validação.

## 3. Agregado atual: Equipment

**[CONFIRMADO — REPOSITÓRIO]**

Campos:

```text
id
code
name
type
status
active
location
```

### Identidade

- `id` é gerado pelo banco;
- `code` é obrigatório e único no banco;
- ainda não existe validação de formato do código no Java.

### Tipo

Valores confirmados:

```text
COMPRESSOR
RECEIVER
CONDENSER
```

### Estado

Valores confirmados:

```text
RUNNING
STOPPED
MAINTENANCE
EVACUATED
DEACTIVATED
```

**[PENDENTE]** Esses estados foram modelados, mas ainda não existe definição formal de:

- significado de cada um;
- quem pode alterá-los;
- transições permitidas;
- diferença entre `DEACTIVATED` e `active = false`;
- aplicação de `EVACUATED` somente a recipientes ou a qualquer equipamento;
- registro do histórico de estado.
- critérios gerais de entrada e saída de manutenção e possibilidade de retorno à operação.

### Ativo no cadastro × estado operacional

Proposta abstrata: distinguir participação no cadastro, estado operacional e atribuição de serviço, sem estabelecer estados de equipamentos reais.

## 4. Regras confirmadas na criação

**[CONFIRMADO — REPOSITÓRIO]**

Ao criar equipamento:

- `code` não pode ser nulo, vazio ou somente espaços;
- `name` não pode ser nulo, vazio ou somente espaços;
- `type` deve ser informado;
- `location` não pode ser nula, vazia ou somente espaços;
- `status` é definido como `STOPPED` pelo Service;
- `active` é definido como `true` pelo Service;
- o equipamento é persistido;
- código duplicado viola a restrição única do banco.

## 5. Regras ainda não implementadas

**[PENDENTE]**

- normalização de espaços;
- tamanho máximo validado na API;
- formato de `code`;
- tratamento amigável de código duplicado;
- alteração de dados cadastrais;
- desativação sem apagar histórico;
- busca por código;
- paginação e ordenação;
- estados e transições;
- auditoria de alterações;
- associação a sala/local estruturado.

## 6. Localização

**[CONFIRMADO — REPOSITÓRIO]** `location` é texto livre.

**[HIPÓTESE]** A localização pode evoluir para entidade ou value object quando houver regras próprias ou necessidade de normalização.

## 7. Ronda — modelo provisório

**[HIPÓTESE]** Um modelo inicial pode conter:

```text
Round
├── id
├── area/location
├── scheduledAt
├── startedAt
├── finishedAt
├── operator
├── status
├── entries
└── notes
```

Possíveis estados:

```text
PLANNED → IN_PROGRESS → COMPLETED
                    └→ INTERRUPTED
```

Nenhum desses nomes ou fluxos está aprovado.
```text
scheduledAt → horário previsto na grade
measuredAt  → horário real da observação
recordedAt  → horário em que o dado entrou no sistema
```

A distinção de horários é uma proposta de modelagem, sem reproduzir uma grade operacional.

Perguntas obrigatórias antes de implementar:

- a ronda é definida por sala, roteiro, turno ou horário?
- pode haver mais de um operador?
- uma ronda pode ser concluída parcialmente?
- quem pode corrigir uma leitura?
- como funciona atraso ou impossibilidade de acesso?
- existe assinatura ou conferência?
- o que é obrigatório e o que é opcional?

## 8. Leitura — modelo provisório

**[DECISÃO DE MODELAGEM PROPOSTA]** Nunca armazenar apenas um número quando sua interpretação depende de contexto.

Uma leitura futura pode precisar de:

```text
Measurement
├── id
├── equipmentId / measurementPointId
├── measuredAt
├── recordedAt
├── recordedBy
├── rawValue
├── rawUnit
├── normalizedValue
├── normalizedUnit
├── origin
├── instrument
├── quality/status
├── calculationMethod/version
└── notes
```

Origens possíveis, ainda não aprovadas:

```text
MANUAL_LOCAL_INSTRUMENT
MANUAL_HMI
SENSOR_IMPORT
CALCULATED
LEGACY_FORM
```

Motivos candidatos de ausência de leitura, ainda sujeitos a validação:

```text
MEASURED
EQUIPMENT_STOPPED
NOT_APPLICABLE
COULD_NOT_MEASURE
NOT_PERFORMED
RECORDED_LATE
```

Os nomes e transições precisam ser traduzidos e validados. O requisito é semântico: compressor desligado e leitura não realizada não podem continuar indistinguíveis.
### Setpoint
**[PENDENTE]** Identificar:

- variável controlada;
- unidade;
- origem do valor;
- quem pode alterá-lo;
- validade temporal;
- se é configuração fixa ou muda com o regime operacional.

**[DECISÃO DE MODELAGEM PROPOSTA]** Setpoint é referência/configuração, não leitura do operador. Se puder mudar, deve ter período de vigência para permitir interpretar corretamente o histórico.
**[DECISÃO DE MODELAGEM PROPOSTA]** Uma futura ronda não deve usar uma lista rígida e idêntica de campos para todos os compressores. Cada equipamento poderá referenciar um perfil de coleta com pontos aplicáveis, rótulo usado pelo operador, sigla bruta da IHM e unidade esperada.

O mapeamento entre sigla bruta e conceito canônico só poderá ser considerado válido depois de confirmação por manual, documentação técnica autorizada ou profissional experiente.

Campos futuros candidatos no cadastro, ainda não aprovados:

```text
manufacturer
controllerFamily
applicationOrService
compressionStageOrRegime
hasVariableFrequencyDrive
measurementProfile
```

### Atribuições e vigência

Conceitos candidatos, ainda não aprovados:

```text
OperatingRegime
EquipmentRegimeAssignment
validFrom / validUntil
assignmentReason
assignedService
```

O nome de regime, sua temperatura de referência e a atribuição do equipamento são conceitos distintos.
## 9. Ocorrências e anomalias

**[HIPÓTESE]** Uma ocorrência deve ser separada de uma leitura fora de faixa. Pode registrar observação qualitativa, severidade provisória, equipamento relacionado, horário, responsável e ação tomada.

**[PENDENTE]** Antes de modelar, mapear:

- categorias reais;
- quem classifica severidade;
- diferença entre ocorrência, alarme, defeito e ordem de manutenção;
- integrações com processos existentes;
- exigências de retenção e auditoria.
**[DECISÃO DE MODELAGEM]** Medição periódica, ocorrência, atividade realizada e pendência de turno não devem ser representadas como um único tipo de registro.

**[DECISÃO DE MODELAGEM]** O formulário digital de leitura não deve receber campos livres de ocorrência apenas por conveniência. A ligação entre uma leitura e uma ocorrência deve ser explícita, preservando os dois registros como conceitos separados.
**[DECISÃO DE MODELAGEM]** Resolver uma pendência deve acrescentar um novo evento relacionado ao registro anterior e identificar o problema resolvido. O sistema deve preservar o texto e o estado históricos em vez de sobrescrever silenciosamente a anotação original.
**[DECISÃO DE MODELAGEM]** Anotação, tarefa futura e restrição operacional não devem ser o mesmo conceito. Uma tarefa pode precisar de turno responsável, data e estado. Uma restrição pode precisar de origem, vigência e confirmação de ciência. Esses campos são candidatos de descoberta, não autorização para implementar controle de equipamento.
## 10. Passagem de turno
**[HIPÓTESE]** A futura passagem de turno deve ser uma visão derivada de ocorrências, equipamentos indisponíveis, rondas pendentes e observações, evitando duplicação manual. Antes de definir campos obrigatórios, é necessário validar com outros operadores e supervisão quais informações realmente precisam atravessar todos os turnos.
**[HIPÓTESE]** Uma futura confirmação de ciência pode reduzir ambiguidade, mas não comprova execução e não substitui procedimentos ou comunicação verbal exigida.

## 11. Não condensáveis

**[DECISÃO]** Não existe regra de negócio aprovada para calcular percentual real de ar.
Qualquer feature relacionada exige:

- método técnico aprovado;
- pontos de medição definidos;
- unidades normalizadas;
- pressão absoluta/manométrica esclarecida;
- temperatura independente e representativa;
- condições de equilíbrio conhecidas;
- validação com engenharia e procedimento da planta;
- linguagem de interface que não induza certeza falsa.

## 12. Eventos de domínio futuros

**[HIPÓTESE]** Eventos que podem se tornar relevantes:

```text
EquipmentRegistered
EquipmentStatusChanged
RoundStarted
MeasurementRecorded
AnomalyReported
RoundCompleted
ShiftHandoverAcknowledged
```

Não implementar event sourcing ou mensageria apenas por essa lista. Os nomes ajudam a compreender comportamentos, não prescrevem arquitetura.

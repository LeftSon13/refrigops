# Regras de negócio e domínio

## 1. Escopo e fronteira pública

Este documento separa:

- comportamento confirmado no código;
- decisões aprovadas da MVP;
- propostas de modelagem;
- validações necessárias antes de um piloto real.

O conteúdo operacional é abstrato. Exemplos concretos pertencem ao cenário fictício `DEMO` e não representam ativos, pessoas, horários, instrumentos ou procedimentos de uma instalação.

## 2. Glossário público

| Termo | Significado atual | Evidência |
|---|---|---|
| Equipamento | Objeto cadastrado no RefrigOps | Código atual |
| Compressor | Tipo de equipamento | Enum atual |
| Recipiente / `RECEIVER` | Tipo de equipamento | Enum atual |
| Condensador / `CONDENSER` | Tipo de equipamento | Enum atual |
| Ronda | Execução manual de um roteiro configurado | Baseline da MVP |
| Ponto de medição | Definição do que pode ser coletado | Baseline da MVP |
| Medição | Valor registrado com unidade, origem, horário e autoria | ADR-0005 e baseline |
| Ausência | Resultado explícito sem valor numérico | Baseline da MVP |
| Ocorrência | Registro separado de uma leitura | Baseline da MVP |
| Continuidade | Visão de rondas e ocorrências relevantes entre turnos | Baseline da MVP |

## 3. Agregado atual: `Equipment`

**[CONFIRMADO — REPOSITÓRIO]**

Campos atuais:

```text
id
code
name
type
status
active
location
```

### 3.1 Identidade

- `id` é gerado pelo banco;
- `code` é obrigatório e único no banco;
- normalização e respostas para conflito pertencem às Issues específicas do backlog.

### 3.2 Tipos

```text
COMPRESSOR
RECEIVER
CONDENSER
```

### 3.3 Estados atuais

```text
RUNNING
STOPPED
MAINTENANCE
EVACUATED
DEACTIVATED
```

Esses valores existem no código, mas sua presença não comprova um fluxo operacional aprovado. Significados, permissões, transições e histórico de estado ainda exigem decisão própria.

### 3.4 Cadastro, condição e coleta

O modelo deve evitar misturar três dimensões:

```text
active           → disponibilidade cadastral
operatingStatus  → condição observada em determinado contexto
collectionStatus → resultado da tentativa de coleta
```

Os nomes de campos futuros são ilustrativos. O princípio é separar cadastro, observação e resultado de coleta.

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

Conforme o backlog da MVP:

- validação de tamanhos máximos na fronteira HTTP;
- normalização de espaços externos;
- tratamento padronizado de erro e conflito;
- transição aprovada de estado;
- autenticação e autorização;
- perfis de turno e identidades de operador;
- roteiro, rondas e pontos de medição;
- medições, ausências e condições observadas;
- ocorrências, acompanhamento, resolução e revisões;
- histórico funcional e backup da demonstração.

Planejamento documental não equivale a comportamento entregue.

## 6. Localização

**[CONFIRMADO — REPOSITÓRIO]** `location` é texto livre.

No cenário público, use somente `Área Demonstrativa A`. Uma estrutura de áreas reais dependeria de requisitos autorizados e não deve ser inferida da demonstração.

## 7. Ronda e contexto congelado

A MVP diferencia:

- configuração reutilizável do perfil de turno;
- ocorrência concreta de um turno;
- roteiro configurado;
- execução de ronda assumida por um operador;
- itens de coleta preservados no contexto da execução.

Horários e metas configurados posteriormente não devem reescrever uma execução já existente. A decisão estrutural detalhada pertence à sessão T-02 e à Issue arquitetural correspondente.

Instantes conceitualmente distintos:

```text
scheduledAt → instante previsto
measuredAt  → instante informado para a observação
recordedAt  → instante em que o sistema confirmou o registro
```

Esses campos não autorizam inferir horários ou frequência de uma operação real.

## 8. Medição e ausência

Uma medição não deve ser armazenada como número sem contexto. O modelo candidato inclui:

```text
Measurement
├── measurementPointId
├── rawValue
├── rawUnit
├── origin
├── measuredAt
├── recordedAt
├── recordedBy
├── quality/status
└── calculationMethod/version, quando houver
```

Regras preservadas da MVP:

- medição possui valor; ausência não possui valor artificial;
- zero precisa ter sido informado intencionalmente;
- unidade e origem permanecem visíveis;
- ponto não aplicável é diferente de coleta não realizada;
- correção cria revisão e preserva o original;
- valores da demonstração são sintéticos.

Precisão, escala decimal, exclusividade entre valor e ausência, snapshots e fronteira de revisão serão refinados em T-05.

## 9. Fontes de dados

Origens genéricas podem ser representadas como:

```text
MANUAL_LOCAL_INSTRUMENT
MANUAL_LOCAL_INTERFACE
AUTHORIZED_IMPORT
CALCULATED
```

Na demonstração, essas origens são apenas rótulos simulados. Não existe sensor, controlador ou integração industrial conectada.

Um valor calculado deve manter entradas, regra e versão. Um valor convertido deve preservar também o valor e a unidade originais.

## 10. Ocorrências e continuidade

Medição, condição observada, ocorrência e atividade não são o mesmo conceito.

Uma ocorrência deve possuir identidade e histórico próprios. Acompanhamento ou resolução acrescenta um evento com autoria e horário; não sobrescreve silenciosamente o registro original.

A continuidade entre turnos é uma projeção informativa de execuções e ocorrências. Ela não representa comando, autorização, ordem de manutenção ou confirmação de segurança.

## 11. Não condensáveis

Não existe regra de negócio aprovada para calcular percentual real de gases não condensáveis.

Qualquer funcionalidade futura exigiria, fora da MVP:

- método técnico aprovado;
- pontos e instrumentos definidos;
- unidades e convenções de pressão esclarecidas;
- temperatura independentemente medida e representativa;
- condições físicas conhecidas;
- validação por engenharia e procedimentos autorizados;
- linguagem que não apresente estimativa como medição certificada.

## 12. Eventos de domínio futuros

Nomes conceituais que podem ajudar a discutir comportamentos:

```text
EquipmentRegistered
EquipmentStatusChanged
RoundStarted
MeasurementRecorded
OccurrenceReported
RoundCompleted
ShiftContinuityViewed
```

Essa lista não prescreve event sourcing, mensageria ou microsserviços.

## 13. Validação privada antes de uso real

Inventário, áreas, estados, pontos, unidades, instrumentos, horários, pessoas, procedimentos e permissões de uma instalação só podem ser definidos em ambiente privado e autorizado. Nada disso é derivado dos exemplos `DEMO`.

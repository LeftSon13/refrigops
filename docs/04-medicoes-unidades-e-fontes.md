# Medições, unidades e fontes

## 1. Objetivo

Definir princípios públicos e genéricos para representar medições no RefrigOps sem reproduzir instrumentos, telas, valores ou procedimentos de uma instalação real.

## 2. Regra central

Um número isolado não é uma medição suficientemente rastreável.

```text
medição = valor + unidade + origem + ponto + instante + autoria + qualidade
```

Quando houver conversão ou cálculo, devem permanecer disponíveis também as entradas, a regra e sua versão.

## 3. Fontes genéricas

Uma coleta pode declarar uma origem conceitual:

| Origem | Significado público |
|---|---|
| Instrumento local | Valor digitado após leitura presencial de um instrumento |
| Interface local | Valor digitado após leitura de uma interface |
| Importação autorizada | Valor recebido de fonte integrada e identificada |
| Cálculo | Valor derivado por regra registrada |

Na MVP, todas as origens são simuladas. Não existe conexão com sensores, controladores ou redes industriais.

## 4. Cenário sintético

Exemplo exclusivamente demonstrativo:

```text
equipamento: DEMO-COMP-01
ponto: PRESSAO_DEMO_01
valor: 12,34
unidade: unidade_demo
origem: MANUAL_LOCAL_INSTRUMENT
autor: Operador A
qualidade: INFORMADO_PARA_DEMONSTRACAO
```

O valor e a unidade não representam leitura, limite seguro, setpoint ou faixa operacional. Servem somente para testar persistência, exibição e rastreabilidade.

## 5. Ausência não é zero

Resultados possíveis precisam permanecer semanticamente distintos:

```text
MEASURED
NOT_APPLICABLE
EQUIPMENT_UNAVAILABLE
COULD_NOT_MEASURE
NOT_PERFORMED
```

- `MEASURED` exige valor e contexto;
- ausência justificada não recebe valor numérico;
- zero só é válido quando digitado intencionalmente;
- estado cadastral do equipamento não substitui o resultado da coleta.

Os nomes definitivos serão refinados na Issue técnica correspondente.

## 6. Unidades e conversões

O sistema deve:

- preservar a unidade informada;
- impedir comparação silenciosa de unidades incompatíveis;
- registrar unidade normalizada quando houver conversão;
- versionar a regra usada;
- exibir arredondamento e precisão de maneira consistente;
- não assumir unidade com base apenas no nome de uma tela ou campo.

Pressão absoluta e pressão manométrica são referências diferentes. Qualquer comparação depende de identificar explicitamente qual convenção foi usada.

## 7. Temperatura e grandezas derivadas

Uma escala derivada não constitui necessariamente uma segunda medição independente. O modelo deve distinguir:

- valor fisicamente medido;
- valor convertido;
- valor calculado;
- valor estimado;
- valor apenas informado por fonte externa.

Essa distinção evita apresentar transformação matemática como observação independente.

## 8. Qualidade e incerteza

Estados candidatos de qualidade:

```text
RAW
VALIDATED
ESTIMATED
CONVERTED
CALCULATED
QUESTIONABLE
REJECTED
```

Eles são vocabulário de análise, não implementação aprovada. A interface deve explicar seu significado antes de adotá-los.

## 9. Correção e histórico

Uma correção deve:

- preservar o registro original;
- identificar autor e horário;
- exigir motivo;
- criar nova revisão;
- deixar inequívoco qual valor está vigente.

Não se deve editar silenciosamente uma medição histórica.

## 10. Não condensáveis

A MVP não calcula percentual de gases não condensáveis.

Pressão, temperatura, composição, equilíbrio e ponto de coleta precisam de definição técnica antes de qualquer método. O software não deve converter uma diferença numérica em diagnóstico, limite seguro ou recomendação operacional sem validação de profissionais responsáveis.

## 11. Modelo candidato

```text
MeasurementPoint
├── id
├── equipmentId
├── name
├── quantityType
├── expectedUnit
├── originType
└── active

MeasurementResult
├── measurementPointId
├── rawValue ou absenceReason
├── rawUnit
├── origin
├── measuredAt
├── recordedAt
├── recordedBy
├── quality
└── revision
```

Valor e ausência são mutuamente exclusivos. Precisão, escala, invariantes e snapshots serão refinados em T-05 e na ADR-0005.

## 12. Validação privada necessária

Antes de um piloto real, profissionais autorizados devem validar:

- nome e significado de cada ponto;
- grandeza e unidade oficiais;
- referência absoluta ou manométrica, quando aplicável;
- origem e método de obtenção;
- localização e representatividade do instrumento;
- calibração e rastreabilidade;
- arredondamento e precisão;
- permissões, retenção e finalidade do dado.

Essas informações não são publicadas nem inferidas do cenário fictício.

# ADR-0005 — Tratar medições com valor, unidade e origem

- Status: Proposta de domínio

## Contexto

A investigação operacional revelou dados em bar e kgf/cm², escalas derivadas de temperatura, instrumentos locais, IHM, PT100 e valores calculados. Um número isolado não permite comparação confiável.

## Decisão proposta

Quando o módulo de leituras for criado, a medição deve preservar ao menos:

- valor bruto;
- unidade bruta;
- origem;
- instante;
- ponto/instrumento;
- autoria do registro;
- conversão ou cálculo, quando houver;
- indicador de qualidade/incerteza.

## Consequências positivas

- rastreabilidade;
- conversões auditáveis;
- menor risco de comparar grandezas incompatíveis;
- dados manuais e automáticos distinguíveis;
- suporte a correções futuras.

## Consequências negativas

- modelo mais rico;
- interface precisa coletar contexto sem sobrecarregar o operador;
- exige catálogo de unidades e origens;
- demanda política de qualidade.

## Alternativas não adotadas

- armazenar `double value` sem unidade;
- normalizar e descartar o valor original;
- tratar cálculo como se fosse sensor;
- aceitar unidade implícita por tela.

## Validação necessária

Observar o primeiro fluxo de ronda, inventariar medições e testar se o contexto pode ser capturado sem aumentar excessivamente o trabalho do operador.

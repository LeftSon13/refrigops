# Medições, unidades e fontes

> Nota editorial de saneamento histórico: referências a uma operação específica foram abstraídas. Esta nota não representa uma decisão tomada na data original do documento.

## 1. Objetivo

Impedir que números de origens e unidades diferentes sejam comparados ou apresentados como equivalentes sem contexto.

## 2. Regra central

**[DECISÃO]** Toda medição futura deve preservar, quando aplicável:

- valor original;
- unidade original;
- fonte;
- instrumento ou tag;
- instante da medição;
- instante do registro;
- responsável pelo registro;
- conversão aplicada;
- método e versão de cálculo;
- qualidade, observação ou incerteza.

## 3. Fontes e rastreabilidade

Distinguir instrumento local, interface de equipamento, importação e cálculo. Um mapeamento entre rótulo de origem e conceito de domínio exige validação; nenhuma associação com ativo, fabricante ou instalação é mantida nesta síntese.

## 4. Pressão e temperatura

Valores de grandezas diferentes não são intercambiáveis. Não inferir medição independente a partir de uma indicação convertida sem verificar sua origem e o método utilizado.

## 5. Escala de NH₃ do manômetro

**[DECISÃO DE INTERPRETAÇÃO]** A escala em °C do manômetro não representa um segundo sensor. Ela converte a pressão medida para temperatura de saturação correspondente à NH₃.

```text
pressão medida
     ├── escala de pressão
     └── escala equivalente de temperatura NH3
```

Logo, pressão e temperatura lidas do mesmo ponteiro não são duas observações independentes para validar uma condição termodinâmica.

## 6. Unidades

Registrar unidade original e convenção adotada. Conversões precisam de método explícito, rastreabilidade e validação técnica. Esta síntese não fornece valores operacionais nem recomendações de ajuste.

## 7. Pressão manométrica e absoluta

**[PENDENTE CRÍTICO]** Confirmar se cada fonte representa:

- pressão manométrica, relativa à atmosfera;
- pressão absoluta;
- outra convenção interna do sistema.

Comparações termodinâmicas podem exigir pressão absoluta. O software não deve assumir conversão sem conhecer altitude/pressão atmosférica, convenção da tabela e instrumento.

## 8. Limites de cálculos

Fórmulas e exemplos associados à operação específica foram suprimidos. Uma estimativa não comprova composição nem substitui medição certificada.

## 9. Qualidade da evidência

Uma indicação derivada de outra grandeza não constitui evidência independente. Qualquer inferência exige validação das fontes e premissas; não há procedimento operacional prescrito aqui.

## 10. Não condensáveis — hipótese física

**[HIPÓTESE TÉCNICA]** Diferenças entre pressão real do lado de alta e pressão de saturação esperada para uma temperatura independentemente medida podem indicar condição que merece investigação.

Isso depende de:

- ponto e instante das medições;
- equilíbrio térmico;
- representatividade da temperatura;
- unidades e convenção de pressão;
- composição;
- erro dos instrumentos;
- condição operacional;
- procedimento técnico adotado.

Não converter essa hipótese em regra operacional sem validação especializada.

## 11. Proposta de representação de uma medição

Campos candidatos do modelo, sem instâncias operacionais: `rawValue`, `rawUnit`, `normalizedValue`, `normalizedUnit`, `origin`, `measuredAt`, `recordedAt`, `instrument`, `sourceReference`, `qualityStatus`, `calculationMethod` e `notes`. Os nomes não constituem contrato implementado ou aprovado.

## 12. Estados de qualidade possíveis

**[HIPÓTESE]**

```text
OBSERVED
UNVERIFIED
SUSPECT
ESTIMATED
CALCULATED
INVALIDATED
```

Os nomes precisam ser traduzidos para a linguagem da operação e definidos antes do uso.

## 13. Perguntas pendentes

Qual é a grandeza? Qual unidade e convenção foram usadas? A indicação foi medida, digitada, importada ou calculada? Qual método e qual fonte autorizada sustentam a interpretação? Essas perguntas não são respostas sobre uma instalação.

## 14. Síntese editorial

Referências, classificações e associações operacionais específicas foram retiradas. Preservam-se somente distinções conceituais e a necessidade de validação técnica.


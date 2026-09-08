# Aprendizados abstratos da descoberta operacional

## Finalidade

Este é o único registro público derivado da etapa inicial de descoberta operacional. Ele preserva aprendizados úteis ao produto sem reproduzir documentos, fotografias, inventário, layout, pessoas, horários, equipamentos, instrumentos, configurações, valores ou procedimentos de uma instalação real.

Os materiais brutos que originaram essas conclusões são privados e não fazem parte do repositório.

## Classificação

- Tipo: síntese pública abstrata;
- Dados: conceitos genéricos e cenário fictício;
- Correspondência com instalação real: nenhuma afirmada;
- Uso: descoberta de produto, modelagem e planejamento da MVP.

## Aprendizados publicáveis

### Ronda

- rondas podem combinar inspeção presencial e registro de pontos;
- equipamentos diferentes podem exigir conjuntos diferentes de pontos;
- frequência e ordem devem ser configuráveis;
- horário previsto, horário observado e horário registrado são conceitos distintos;
- o sistema deve permitir explicar por que um ponto não recebeu valor.

### Medição

- um valor precisa de unidade, origem, ponto, horário e autoria;
- unidade implícita aumenta o risco de interpretação incorreta;
- pressão absoluta e manométrica não são equivalentes;
- valor medido, convertido, calculado e estimado devem ser distinguidos;
- ausência de leitura não pode ser transformada em zero;
- correções precisam preservar o registro anterior.

### Equipamento

- cadastro ativo não significa que o equipamento esteja operando em determinado instante;
- condição observada não deve sobrescrever silenciosamente o cadastro;
- aplicabilidade de um ponto pode variar por tipo ou configuração;
- códigos públicos da demonstração usam somente o prefixo `DEMO-`.

### Ocorrência e continuidade

- ocorrência é diferente de leitura;
- acompanhamento e resolução acrescentam eventos ao histórico;
- informação destinada à continuidade precisa de autoria, horário e estado;
- uma visão entre turnos é informativa e não constitui ordem ou autorização operacional.

### Ergonomia

- reduzir digitação não autoriza preencher valores automaticamente;
- erros de envio devem ficar visíveis;
- repetição de requisição não deve duplicar o registro;
- viewport móvel e desktop devem preservar clareza de unidade, origem e estado.

## Cenário fictício comum

```text
Área Demonstrativa A
├── DEMO-COMP-01
├── DEMO-COMP-02
└── DEMO-REC-01

Operadores: Operador A, Operador B e Operador C
Perfis: Turno A e Turno B
```

Pontos, valores, unidades, horários e ocorrências usados em testes serão explicitamente sintéticos. Eles não serão derivados nem calibrados para imitar dados de uma instalação.

## Método para futuras pesquisas públicas

Uma pesquisa pública pode registrar:

1. problema abstrato;
2. fonte pública consultada;
3. interpretação provisória;
4. o que a fonte não prova;
5. impacto possível no software;
6. validação ainda necessária.

Nomes de fabricantes ou produtos só devem aparecer quando forem indispensáveis para discutir documentação pública e não estiverem associados a uma configuração, fotografia ou ocorrência local.

## O que permanece privado

- entrevistas e relatos ligados a uma instalação;
- fotografias e associações entre imagem e ativo;
- inventários e códigos reais;
- áreas, rotas, regimes, serviços e topologia;
- modelos e configurações locais;
- valores, setpoints e referências observadas;
- composição e horários de equipes;
- documentos, formulários e procedimentos internos;
- ocorrências, intervenções e fornecedores associados a casos concretos.

## Limite de segurança

O RefrigOps não substitui instrumentos certificados, procedimentos, intertravamentos ou profissionais habilitados. A documentação pública não define faixas seguras, alarmes, procedimentos de intervenção ou métodos certificados de diagnóstico.

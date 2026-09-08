# Contexto operacional público e abstrato

## 1. Finalidade

Este documento traduz aprendizados profissionais do domínio de refrigeração industrial em problemas genéricos de software. Ele não descreve uma empresa, unidade ou instalação específica.

A fronteira pública do RefrigOps é:

```text
experiência profissional
        ↓
aprendizado abstrato do domínio
        ↓
problema de software
        ↓
cenário demonstrativo inteiramente fictício
```

Materiais brutos de descoberta, fotografias, inventários, rotas, valores, procedimentos e relatos ligados a instalações reais são mantidos fora do repositório público.

## 2. Contexto genérico

Em uma operação industrial, uma ronda pode combinar:

- inspeção presencial;
- pontos de medição aplicáveis a equipamentos diferentes;
- valores lidos em instrumentos ou interfaces locais;
- observações qualitativas;
- indicação explícita de impossibilidade ou ausência de coleta;
- registro de ocorrências;
- continuidade de informação entre equipes.

Esses elementos são conhecimento geral do problema. Quantidade de áreas, equipamentos, pessoas, horários, pontos e fontes deve ser configurável e não é inferida de uma instalação real.

## 3. Papel da ronda presencial

O RefrigOps parte da hipótese de que a coleta digital pode melhorar rastreabilidade, sem substituir a inspeção física nem a percepção do operador.

Uma execução de ronda precisa permitir distinguir:

```text
valor medido
≠ ponto não aplicável
≠ equipamento indisponível
≠ impossibilidade de coleta
≠ coleta não realizada
```

Ausência nunca deve ser convertida silenciosamente em zero. Zero só representa um valor quando for digitado intencionalmente e válido para o ponto.

## 4. Cenário público da demonstração

O conjunto demonstrativo é inteiramente sintético:

```text
Área Demonstrativa A
├── DEMO-COMP-01 — Compressor Fictício 01
├── DEMO-COMP-02 — Compressor Fictício 02
└── DEMO-REC-01  — Recipiente Fictício 01
```

Os pontos fictícios existem apenas para provar heterogeneidade de formulários:

- um ponto sintético de pressão em cada equipamento;
- um ponto sintético adicional de frequência em `DEMO-COMP-01`;
- unidades, valores e ocorrências explicitamente marcados como demonstração;
- nenhuma correspondência com ativos, layout, regime, instrumento ou valor real.

## 5. Fontes e contexto da medição

Uma medição deve preservar, quando aplicável:

- valor bruto;
- unidade;
- origem;
- ponto de medição;
- instante observado;
- instante registrado;
- autoria;
- condição de qualidade;
- método e versão, se houver conversão ou cálculo.

Fontes genéricas podem incluir digitação de uma leitura vista em instrumento local, digitação de uma leitura vista em interface local, importação autorizada ou cálculo rastreável. Na MVP, todas são simuladas; não há integração com equipamentos.

## 6. Cadastro, disponibilidade e condição observada

São conceitos diferentes:

```text
cadastro ativo
condição operacional em determinado instante
resultado da tentativa de coleta
```

Um equipamento cadastrado pode não estar disponível para determinada ronda. Essa situação não autoriza o sistema a comandar, liberar ou bloquear o ativo.

## 7. Ocorrências e continuidade

Medição, observação, ocorrência e acompanhamento não devem ser reduzidos a um único campo de texto.

Uma ocorrência fictícia pode permanecer aberta depois do fim da ronda e receber novos eventos com autoria e horário. Resolver ou acompanhar acrescenta histórico; não apaga o registro original.

A visão de continuidade ajuda a equipe a localizar rondas incompletas e ocorrências abertas. Ela é informativa e não equivale a ordem de serviço, procedimento, autorização ou confirmação de segurança.

## 8. Ergonomia

A interface deve:

- reduzir digitação repetitiva;
- funcionar em viewport móvel e desktop;
- deixar unidade e origem visíveis;
- evitar preenchimento automático de valores;
- registrar falhas de envio sem duplicar dados;
- permitir retomada conforme as regras fictícias da MVP.

Dispositivo, conectividade, uso de luvas, restrições de acesso e condições ambientais de um piloto real dependem de avaliação privada e autorizada.

## 9. Limites de segurança

O RefrigOps é sistema de apoio e registro. Ele não:

- comanda equipamentos, válvulas ou controladores;
- altera setpoints;
- reconhece alarmes;
- substitui procedimentos ou intertravamentos;
- apresenta exemplos sintéticos como faixa segura;
- transforma cálculo experimental em medição certificada.

Amônia, pressão, temperatura, não condensáveis e intervenções operacionais exigem documentação técnica e profissionais responsáveis.

## 10. Validação necessária antes de um piloto

Somente em ambiente privado e autorizado devem ser definidos:

- áreas e roteiro reais;
- inventário e pontos de medição;
- horários, frequência e composição das equipes;
- unidades, convenções e origem oficial de cada dado;
- instrumentos, calibração e aplicabilidade;
- procedimentos, permissões e responsabilidades;
- conectividade, retenção, backup e suporte;
- dados que podem ou não sair da instalação.

Nenhuma dessas informações faz parte do cenário público.

# Documento mestre do produto — RefrigOps

> Nota editorial de saneamento histórico: referências a uma operação específica foram abstraídas. Esta nota não representa uma decisão tomada na data original do documento.

## 1. Resumo executivo histórico

O RefrigOps foi concebido como sistema de apoio para organizar registros e contexto em refrigeração industrial. Esta versão histórica expressava hipóteses iniciais de produto; não descreve uma instalação, pessoa ou organização.

## 2. Origem abstrata da ideia

Aprendizados profissionais motivaram problemas genéricos de rastreabilidade, fragmentação de registros e continuidade da informação. As fontes, locais, ativos, fornecedores, práticas e relatos que deram origem a esses aprendizados foram retirados editorialmente.

## 3. Problema de produto

A hipótese histórica preservada é que informações distribuídas entre registros e pessoas podem perder contexto e dificultar consulta posterior. O produto buscava estruturar histórico sem transformar essa hipótese em procedimento operacional, diagnóstico ou descrição de uma operação real.

## 4. Usuários e interessados

### 4.1 Usuário principal provisório

**[HIPÓTESE]** Operador de refrigeração industrial que executa rondas, observa equipamentos, registra leituras e participa da passagem de turno.

Necessidades prováveis:

- registrar informações rapidamente;
- usar linguagem próxima da operação;
- evitar digitação repetitiva;
- enxergar o que precisa ser verificado;
- registrar anomalias e observações não numéricas;
- consultar histórico sem depender de procurar papéis;
- usar uma interface clara mesmo com diferentes níveis de familiaridade digital.

### 4.2 Outros interessados provisórios

**[HIPÓTESE]**

- liderança/supervisão operacional;
- manutenção mecânica, elétrica e de instrumentação;
- engenharia;
- segurança do trabalho e gestão de riscos;
- administração interessada em confiabilidade, custo e desempenho;
- desenvolvedores e mantenedores do RefrigOps.

As necessidades desses grupos ainda não foram validadas formalmente.

## 5. Evolução da visão

### 5.1 Primeira visão

**[HISTÓRICO — CONVERSA]**

A ideia inicial era próxima de uma plataforma de monitoramento industrial:

```text
equipamentos → telemetria → banco → histórico → análise → manutenção preventiva
```

Também apareceram hipóteses sobre integração com IHM, dados quase em tempo real, sensores, nuvem e cálculo de ganhos financeiros.

### 5.2 Limitações identificadas

**[PENDENTE]** Ainda não estão confirmados:

- protocolos e interfaces disponíveis;
- permissões para integração;
- separação e políticas da rede industrial;
- qualidade e significado de cada tag;
- segurança para saída de dados;
- custos de instrumentação e infraestrutura;
- responsabilidades decorrentes de recomendações automáticas.

### 5.3 Visão amadurecida

**[DECISÃO]** O RefrigOps deve evoluir gradualmente:

```text
registro operacional estruturado
        ↓
histórico confiável
        ↓
domínio compreendido
        ↓
análises explicáveis
        ↓
integrações validadas
        ↓
telemetria futura
```

O sistema pode gerar valor mesmo antes de qualquer automação industrial.

## 6. Proposta de valor provisória

**[HIPÓTESE]**

> Ajudar equipes de refrigeração industrial a registrar, contextualizar e consultar informações operacionais de forma simples e rastreável, preservando a percepção humana e preparando uma base confiável para análises e integrações futuras.

Essa proposta precisa ser validada com usuários além do próprio autor do projeto.

## 7. Princípios do produto

**[DECISÃO]**

> O software deve se adaptar à operação; a operação não deve se adaptar ao software.

1. **Apoiar, não substituir, o operador.**
2. **A operação tem prioridade sobre o registro.** A equipe não deve realizar ações artificiais apenas para alimentar o sistema.
3. **Dados precisam de contexto.** Um número sem unidade, origem, horário, instrumento e condição pode induzir a erro.
4. **Distinguir medição de cálculo.** Valores medidos, digitados, importados, convertidos e estimados não são equivalentes.
5. **Segurança antes de automação.** Integrações e alertas exigem validação proporcional ao risco.
6. **Evolução pequena e verificável.** Cada incremento deve produzir comportamento compreensível.
7. **Linguagem operacional.** Códigos técnicos podem existir, mas a interface deve ser compreensível para quem trabalha na planta.
8. **Histórico auditável.** Deve ser possível explicar quando, como e por quem um dado entrou no sistema.
9. **Acessibilidade prática.** Interfaces devem considerar diferentes idades, experiências digitais, iluminação, luvas, pressa e contexto móvel. Esses fatores ainda precisam ser pesquisados.
10. **Lacuna é melhor que dado inventado.** Uma leitura não realizada deve permanecer identificável como ausência, sem ser convertida silenciosamente em medição.
11. **Rapidez sem falsificar.** Opções rápidas podem reduzir digitação de estados e observações recorrentes, mas o sistema não deve preencher automaticamente um número como se tivesse sido medido.
12. **A inspeção física e a percepção do operador permanecem essenciais.** Telemetria futura complementa a ronda; não substitui observação local nem procedimentos.
13. **Integrações industriais começam como somente leitura.** Qualquer ampliação depende de validação de engenharia, segurança e autorização.

## 8. Objetivos do produto

### 8.1 Objetivos próximos

- consolidar um cadastro confiável de equipamentos;
- definir contrato de API separado da persistência;
- estabelecer base técnica testável e documentada;
- modelar o primeiro fluxo operacional real antes de construir uma interface ampla.

### 8.2 Objetivos de médio prazo

- digitalizar rondas;
- registrar leituras com unidade e origem;
- registrar observações e ocorrências;
- apoiar passagem de turno;
- consultar histórico por equipamento, período e tipo de evento.

**[DECISÃO DE PRODUTO]** A interface mobile será priorizada para executar rondas e coletar informações no local. A interface desktop será priorizada para histórico, visualização, comparação, simulações analíticas e passagem de turno. As duas interfaces devem compartilhar a mesma base de dados e regras, não constituir sistemas independentes.

### 8.3 Visão de longo prazo

- tendências e visualizações;
- alertas explicáveis;
- integração validada com fontes automáticas;
- análise de confiabilidade e manutenção;
- indicadores operacionais e energéticos validados;
- experiência móvel e web adequada a diferentes papéis.

## 9. Não objetivos atuais

**[DECISÃO]**

- comandar equipamentos ou alterar setpoints;
- substituir PLC, IHM, intertravamentos ou sistemas instrumentados de segurança;
- autorizar purgas ou intervenções;
- diagnosticar condição segura somente por fórmula;
- calcular “percentual real de ar” por método não validado;
- criar microsserviços, nuvem complexa ou front-end amplo sem necessidade;
- reproduzir integralmente formulários legados sem investigar o significado dos campos.

## 10. MVP provisório

**[HIPÓTESE]** O MVP de produto ainda não está formalmente aprovado. Uma proposta coerente com a evolução discutida seria:

1. cadastro e consulta de equipamentos;
2. criação de uma ronda manual para uma área piloto;
3. roteiro de verificações e leituras;
4. registro de valor, unidade, fonte e observação;
5. encerramento da ronda com autoria e horário;
6. consulta de histórico;
7. registro de ocorrência/anomalia durante a ronda;
8. resumo útil para passagem de turno.

O recorte deve ser validado com uma sala, um turno e um grupo pequeno de equipamentos antes de generalização.

## 11. Valor de portfólio e aprendizado

**[CONFIRMADO — CONTEXTO DO USUÁRIO]**

O RefrigOps também é um projeto pessoal de aprendizado e portfólio. Deve demonstrar:

- entendimento de um problema real;
- descoberta e modelagem de domínio;
- Java e Spring;
- banco de dados e migrations;
- API e validação;
- testes isolados;
- Git, Issues, branches, commits, PRs e review;
- documentação de decisões;
- cuidado com segurança e limites de responsabilidade.

O valor do portfólio não vem da quantidade de tecnologias, mas da coerência entre problema, decisão, implementação e evidência.

## 12. Indicadores de sucesso provisórios

**[HIPÓTESE]** Indicadores possíveis, ainda sem linha de base:

- percentual de rondas registradas completamente;
- tempo médio para registrar uma ronda;
- redução de campos sem unidade/origem;
- quantidade de ocorrências recuperáveis no histórico;
- facilidade de localizar informações de um equipamento;
- qualidade percebida da passagem de turno;
- adesão dos operadores;
- redução de retrabalho de transcrição;
- confiabilidade dos testes e frequência de regressões.

Nenhum indicador operacional ou financeiro deve ser prometido sem dados de base.

## 13. Estado técnico histórico

**[HISTÓRICO — REPOSITÓRIO]**

O último checkpoint registrado contém uma API de equipamentos com persistência, listagem, criação e validação. O projeto usa Java 21, Spring Boot, PostgreSQL, Flyway, Testcontainers, JUnit e MockMvc.

O snapshot técnico da aplicação permanece baseado no merge `938480a`. Este documento preserva o histórico; branch, testes e ambiente mais recentes devem ser consultados em [`11-contexto-atual.md`](11-contexto-atual.md).

## 14. Próxima decisão recomendada

Depois da auditoria do repositório e da revisão desta documentação, a próxima Issue técnica recomendada é separar a entidade `Equipment` do DTO de resposta HTTP.

Antes disso, o projeto deve decidir se a prioridade imediata é:

1. consolidar a base técnica de Equipment; ou
2. iniciar descoberta formal do primeiro fluxo de ronda.

As duas linhas são válidas, mas não devem crescer simultaneamente sem prioridade explícita.

## 15. Critérios para considerar este documento revisado

- origem da ideia reconhecida pelo usuário;
- problema operacional descrito sem exageros;
- usuários e interessados corrigidos;
- princípios aprovados;
- MVP explicitamente aceito, alterado ou rejeitado;
- não objetivos aprovados;
- riscos e hipóteses claramente marcados;
- nenhuma informação sensível da planta exposta;
- links para documentos especializados atualizados.

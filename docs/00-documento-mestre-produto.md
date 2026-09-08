# Documento mestre do produto — RefrigOps

## 1. Resumo executivo

**[CONTEXTO PROFISSIONAL ABSTRAÍDO]**

O RefrigOps é uma proposta de sistema de apoio à operação de refrigeração industrial com amônia R717. A ideia foi inspirada por problemas profissionais comuns: dados podem ficar distribuídos entre registros manuais, interfaces e instrumentos locais, comunicação entre equipes e conhecimento informal.

O produto pretende criar um histórico operacional estruturado, rastreável e útil, começando por capacidades pequenas que independem de integração automática. A automação, telemetria e análise avançada são possibilidades futuras, não pré-requisitos do primeiro produto.

## 2. Origem da ideia

**[CONTEXTO PROFISSIONAL ABSTRAÍDO]**

A origem do RefrigOps não foi apenas uma necessidade acadêmica de usar Spring Boot. Experiências profissionais inspiraram o problema, mas a documentação pública registra somente conclusões abstratas e cenários sintéticos.

Em refrigeração industrial podem existir diferentes áreas, equipamentos, instrumentos locais e sistemas de supervisão. Rondas podem exigir inspeção física e coleta de informações em pontos distintos.

Durante uma ronda, o operador pode observar números e condições qualitativas, como:

- condição de sucção e formação de gelo;
- temperatura percebida e condições térmicas;
- nível e temperatura de óleo;
- condição de filtros;
- alarmes;
- ruídos;
- comportamento geral da sala e dos equipamentos.

Isso consolidou um princípio do produto:

> A instrumentação pode ajudar o operador, mas não substitui a percepção operacional.

## 3. Problema percebido

**[CONTEXTO PROFISSIONAL ABSTRAÍDO]**

O problema não é apenas “digitalizar uma planilha”. O cenário combina:

```text
informação no papel
        +
informação na IHM
        +
instrumentos locais
        +
percepção do operador
        +
conhecimento informal da equipe
        +
ocorrências e passagem de turno
```

O efeito percebido é que informações relevantes podem não se transformar em histórico estruturado, pesquisável e comparável. Isso dificulta reconstruir o que aconteceu, perceber tendências, explicar ocorrências e transmitir contexto entre turnos.

Em processos de continuidade baseados em texto livre, itens com responsável ou vigência futura podem perder visibilidade. Uma demonstração fictícia pode representar autoria, acompanhamento e vigência sem afirmar como determinada instalação trabalha.

Isso mostra que o problema não é somente guardar texto: informações com responsável ou validade futura precisam reaparecer no momento correto e permitir acompanhamento. Qualquer apoio digital permanece informativo e não substitui autorização, procedimento nem controle de segurança da instalação.

Registros manuais podem exigir transcrição posterior, criando risco genérico de atraso, retrabalho e erro. A MVP demonstrará um fluxo digital fictício, sem documentar processos internos de uma instalação.

Um histórico estruturado também pode apoiar investigações técnicas sobre o comportamento passado de um equipamento. Qualquer exemplo público dessa finalidade usará empresas, pessoas, equipamentos e datas inteiramente fictícios.

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

## 10. Histórico da proposta de MVP

**[HISTÓRICO — DOCUMENTAÇÃO]** Antes da aprovação da baseline v0.1, uma proposta coerente com a evolução discutida era:

1. cadastro e consulta de equipamentos;
2. criação de uma ronda manual para uma área piloto;
3. roteiro de verificações e leituras;
4. registro de valor, unidade, fonte e observação;
5. encerramento da ronda com autoria e horário;
6. consulta de histórico;
7. registro de ocorrência/anomalia durante a ronda;
8. resumo útil para passagem de turno.

Esse recorte foi sucedido pela [baseline aprovada da MVP demonstrativa v0.1](mvp/documento-mestre-v0.1.md). A baseline específica governa o escopo atual; este trecho permanece para preservar a evolução da decisão.

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

O snapshot técnico atual está no merge `c8a2802`, que integrou `EquipmentResponse` pela PR #9. Branch, testes e ambiente mais recentes devem ser consultados em [`11-contexto-atual.md`](11-contexto-atual.md).

## 14. Continuidade aprovada

A separação entre a entidade `Equipment` e o DTO de resposta HTTP foi implementada pela Issue #8 e integrada pela PR #9.

O recorte e a ordem atuais estão definidos na [baseline da MVP](mvp/documento-mestre-v0.1.md), na [EPIC e backlog](mvp/epic-e-backlog-v0.1.md) e nas Issues publicadas sob a EPIC #10.

Cada Issue deve ser executada isoladamente, respeitando dependências, revisão técnica, sessão de aprendizagem e autorização de merge antes do próximo incremento.

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

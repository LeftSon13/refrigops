# MVP, roadmap e critérios de passagem

## 1. Por que este roadmap não é uma lista de features

O RefrigOps ainda está descobrindo o domínio. O roadmap usa **resultados e critérios de passagem**, evitando construir muitas funções desconectadas.

## 2. Estado atual: fundação técnica

**[CONFIRMADO — REPOSITÓRIO]**

Entregue:

- projeto Spring Boot;
- PostgreSQL de desenvolvimento em Docker;
- migration de `equipment`;
- entidade e Repository;
- Service;
- API de listagem e criação;
- padrões `STOPPED` e `active = true` na criação;
- isolamento de testes com Testcontainers;
- Bean Validation no POST;
- fluxo Git com Issues, branches, PRs e review.

Ainda não entregue:

- contrato de resposta separado;
- operações completas de equipamento;
- autenticação;
- interface de usuário;
- ronda;
- leituras;
- ocorrências;
- telemetria;
- implantação.

## 3. Etapa 1 — consolidar Equipment

### Resultado

Ter uma API de equipamento pequena, explícita e testada, que não exponha detalhes de persistência.

### Incrementos sugeridos

1. `EquipmentResponse` para criação e listagem.
2. Conteúdo JSON testado.
3. Decisão separada sobre 201 Created.
4. Tratamento de código duplicado.
5. Busca por ID ou código.
6. Atualização/desativação, somente depois de definir regras de estado.

### Critério de passagem

- contrato HTTP documentado;
- entidade não exposta diretamente;
- erros principais definidos;
- regras atuais testadas;
- estados revisados com o usuário;
- nenhuma operação de exclusão física sem decisão explícita.

## 4. Etapa 2 — descoberta do primeiro fluxo operacional

### Resultado

Descrever e validar uma ronda real de uma área piloto.

### Atividades

- acompanhar ou reconstruir uma ronda completa;
- inventariar campos, instrumentos e unidades;
- relacionar cada equipamento à família de controlador e aos campos aplicáveis;
- mapear exceções e observações qualitativas;
- entender passagem de turno;
- distinguir anotação histórica, tarefa futura, pendência e restrição com vigência;
- validar como o turno responsável toma ciência, executa, adia ou resolve um item;
- identificar usuário, dispositivo e conectividade;
- desenhar fluxo e protótipo simples;
- revisar com operadores.

### Critério de passagem

- área piloto escolhida;
- início e fim da ronda definidos;
- campos obrigatórios e opcionais conhecidos;
- unidades e fontes mapeadas;
- diferenças entre os perfis de IHM representadas sem obrigar campos inexistentes;
- tratamento de interrupção conhecido;
- wireframe testado com ao menos usuários representativos;
- riscos de segurança e privacidade revisados.

## 5. Etapa 3 — MVP de ronda manual

### Resultado

Registrar uma ronda piloto do início ao fim e consultar seu histórico.

### Escopo provisório

- iniciar ronda;
- selecionar/confirmar operador e área;
- percorrer pontos definidos;
- registrar leitura e observação;
- carregar um roteiro e um perfil de campos adequados ao equipamento;
- oferecer opções rápidas para estados e observações recorrentes, preservando entrada livre;
- registrar anomalia;
- concluir ou interromper;
- consultar histórico;
- gerar resumo de passagem de turno.

Antes de transformar o resumo em lista de tarefas ou restrições, validar com operadores e supervisão autoria, responsabilidade, vigência, reagendamento e confirmação de ciência. A aplicação não deve comandar, bloquear ou liberar equipamentos.

Direção de interface confirmada com o autor do projeto:

```text
mobile  → coleta durante a ronda
desktop → histórico, visualização, comparação, simulações e passagem de turno
```

As duas experiências devem compartilhar os mesmos dados. A primeira entrega pode priorizar mobile sem criar dois backends ou duas fontes de verdade.

### Critério de passagem

- teste de comportamento e integração;
- dados de exemplo anonimizados;
- autoria e timestamps;
- unidades explícitas;
- correções auditáveis;
- piloto controlado com fallback;
- feedback registrado;
- nenhuma dependência para segurança da planta;
- nenhuma função de partida, parada, alteração de setpoint ou reconhecimento de alarme;
- ausência, equipamento parado e medição real claramente diferenciados;
- horário previsto, medido e registrado preservados;
- finalidade da frequência de ronda validada com supervisão/técnicos;
- processo de transcrição atual para o computador compreendido;
- nenhum valor numérico sugerido ou anterior salvo automaticamente como nova medição;
- esforço e tempo de digitação avaliados com operadores no piloto.

## 6. Etapa 4 — qualidade de dados e análise

### Resultado

Transformar histórico confiável em consulta e tendência, sem diagnóstico automático prematuro.

Possibilidades:

- filtros por equipamento, área e período;
- séries temporais;
- comparações com contexto;
- registros incompletos ou suspeitos;
- relatórios de ocorrências;
- indicadores de aderência à ronda;
- exportação autorizada.

### Critério de passagem

- base de dados suficiente;
- definições de indicadores aprovadas;
- unidade e origem preservadas;
- gráficos compreendidos pelos usuários;
- limitações visíveis.

## 7. Etapa 5 — integração e telemetria

### Resultado

Importar dados de uma fonte automática aprovada, somente leitura e com significado conhecido.

### Pré-condições

- autorização TI/OT;
- protocolo e arquitetura aprovados;
- catálogo de tags;
- unidade, escala, frequência e qualidade definidas;
- separação de redes e credenciais;
- comportamento de indisponibilidade;
- observabilidade;
- reconciliação entre dado automático e manual.

### Critério de passagem

- piloto não crítico;
- validação de dados contra instrumento/fonte;
- falhas simuladas;
- segurança revisada;
- rollback/fallback;
- responsabilidade de manutenção definida.

## 8. Investigação paralela — não condensáveis

**[DECISÃO]** Pesquisa de domínio, não feature pronta.

Saídas necessárias antes de qualquer implementação:

- procedimento oficial;
- fonte técnica;
- pontos de medição;
- unidades e tipo de pressão;
- instrumento de temperatura independente;
- condições de aplicação;
- revisão por profissional responsável;
- forma correta de comunicar incerteza.

## 9. Backlog técnico não priorizado

- DTO de resposta;
- 201 Created;
- erros padronizados;
- duplicidade;
- Service tests;
- testes MVC mais focados;
- CI no GitHub;
- atualização de equipamento;
- histórico de estados;
- OpenAPI;
- autenticação e perfis;
- observabilidade;
- backup e restauração;
- front-end/mobile;
- internacionalização/unidades;
- auditoria.

Não transformar esta lista em várias Issues antes de escolher o próximo resultado.

## 10. Critério de prontidão para qualquer Issue

- problema descrito;
- comportamento atual conhecido;
- resultado desejado único;
- critérios de aceite verificáveis;
- riscos e dados envolvidos;
- arquivos/camadas prováveis;
- estratégia de teste;
- itens fora do escopo;
- aprovação do usuário.

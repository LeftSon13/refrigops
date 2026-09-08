# Guia de revisão e lacunas

## 1. Como revisar sem precisar ler tudo de uma vez

### Primeira rodada — produto

Leia:

1. `README.md`;
2. `docs/00-documento-mestre-produto.md`;
3. este guia.

Confirme se a história e a visão representam o que você quer construir.

### Segunda rodada — operação

Leia:

1. `docs/01-contexto-operacional.md`;
2. `docs/04-medicoes-unidades-e-fontes.md`;
3. `docs/05-riscos-seguranca-e-limites.md`.

Marque qualquer afirmação sobre a planta que esteja errada, incompleta ou sensível.

### Terceira rodada — software

Leia:

1. `docs/02-arquitetura-atual.md`;
2. `docs/03-regras-negocio-e-dominio.md`;
3. `docs/07-testes-ambientes-e-operacao.md`;
4. `docs/11-contexto-atual.md`.

### Quarta rodada — futuro

Leia:

1. `docs/06-mvp-roadmap-e-criterios.md`;
2. `docs/adr/`;
3. `CONTRIBUTING.md` e `AGENTS.md`.

## 2. Perguntas essenciais sobre produto

- Quem você imagina usando a primeira versão?
- O principal problema é ronda, passagem de turno, histórico, ocorrência ou outro?
- Qual seria o menor resultado útil em uma área piloto?
- O projeto será apenas portfólio/aprendizado ou pretende ser usado na operação?
- Há autorização para conversar com outros operadores?
- Qual problema faria alguém realmente abrir o sistema todos os dias?
- O que você não quer que o RefrigOps se torne?

## 3. Perguntas essenciais sobre a operação

- Como o início, o término, a frequência e a ordem de uma ronda devem ser configurados?
- Quais conjuntos de pontos podem variar entre tipos de equipamento?
- Quais observações qualitativas são relevantes para o fluxo informacional?
- Como distinguir valor medido, ponto não aplicável, impossibilidade de coleta e coleta não realizada?
- Como representar autoria, continuidade e correção sem apagar o histórico?
- Quais registros são oficiais, quais são auxiliares e quem pode validá-los?

### Aprendizados abstratos de descoberta

- equipamentos diferentes podem possuir conjuntos diferentes de pontos de medição;
- o roteiro e a frequência devem ser configuráveis, sem assumir quantidade ou ordem fixa;
- um campo ausente precisa distinguir indisponibilidade, não aplicabilidade e coleta não realizada;
- cadastro, disponibilidade e condição observada são conceitos diferentes;
- valor, unidade, origem e horário precisam permanecer associados;
- autoria e continuidade entre turnos precisam ser explícitas e rastreáveis;
- ocorrências devem ser registradas separadamente das leituras;
- informações de instrumentos e controladores exigem validação privada antes de eventual piloto;
- a interface deve reduzir esforço de digitação sem inventar ou preencher automaticamente valores;
- a documentação pública usa somente abstrações e dados sintéticos, sem inventário, layout ou procedimento de uma instalação.

### Perguntas futuras para validação privada

- Qual frequência e ordem de ronda precisam ser configuradas?
- Qual é a unidade e a origem oficial de cada ponto?
- A grandeza utiliza referência absoluta ou manométrica?
- Como cada fonte distingue valor instantâneo de valor histórico?
- Qual processo valida calibração, aplicabilidade e rastreabilidade?
- Quem pode criar, corrigir, acompanhar e encerrar cada tipo de registro?
- Como uma ausência é justificada sem ser convertida em zero?
- Como itens futuros permanecem visíveis sem transformar informação em comando operacional?
- Quais dados podem ser usados em um piloto autorizado e quais devem permanecer privados?
- Quais manuais públicos sustentam os conceitos genéricos sem comprovar uma configuração local?

## 4. Equipamentos e estados

- Os exemplos públicos usam somente códigos fictícios como `DEMO-COMP-01`, `DEMO-COMP-02` e `DEMO-REC-01`?
- `RECEIVER` é o termo certo para todos os recipientes?
- Bombas precisam entrar em `EquipmentType`?
- Quais outros tipos existem?
- `RUNNING`, `STOPPED`, `MAINTENANCE`, `EVACUATED` e `DEACTIVATED` são suficientes?
- `EVACUATED` se aplica apenas a recipientes?
- Qual a diferença entre `DEACTIVATED` e `active = false`?
- É permitido apagar equipamento ou somente desativar?
- Precisamos guardar histórico de estado?
- Quais atributos genéricos pertencem ao cadastro, sem reproduzir inventário ou configuração de uma instalação?

## 5. Medições

- Qual é a fonte oficial da tabela NH₃?
- Ela usa pressão absoluta ou manométrica?
- Qual é a unidade e a origem oficial de cada ponto?
- Como a fonte informa sua referência de pressão?
- O que significa tecnicamente cada rótulo usado no modelo público?
- O valor é medido, digitado, importado, convertido ou calculado?
- Qual processo autorizado valida o ponto de coleta e a calibração?
- As leituras são simultâneas?
- Qual arredondamento é usado?
- Qual documentação técnica autorizada é necessária antes de modelar não condensáveis?

## 6. Usuários e UX

- Celular pessoal, corporativo, tablet ou computador?
- Existe internet/Wi-Fi nas salas?
- Precisa funcionar offline?
- Operadores usam luvas?
- Autenticação individual é viável?
- Dispositivos são compartilhados?
- Tamanho de fonte e contraste necessários?
- Qual tempo máximo aceitável para registrar cada ponto?
- Áudio/foto podem ser usados ou são proibidos?

## 7. Segurança, privacidade e organização

- Quais informações da planta podem aparecer em um repositório público?
- O repositório continuará público?
- O nome da empresa/local deve ser omitido?
- Fotos e tags são sensíveis?
- Dados de operadores podem ser armazenados?
- Quem pode visualizar, corrigir e exportar registros?
- Há política de retenção?
- Há responsáveis de TI/OT para futuras integrações?

## 8. Software e arquitetura

- Os campos públicos atuais de `EquipmentResponse` permanecem adequados à evolução da API?
- Qual dívida do contrato de Equipment deve ser tratada no próximo incremento aprovado?
- Criação deve retornar 200 ou 201?
- Localização permanecerá texto?
- É desejável CI agora?
- O README deve ensinar a subir aplicação e banco?
- A aplicação será apenas backend por enquanto?
- Qual estratégia de autenticação será considerada futuramente?

## 9. Informações possivelmente ausentes

- objetivos comerciais;
- nome e identidade visual;
- licença do repositório;
- público fora da planta atual;
- concorrentes e sistemas já usados;
- normas e procedimentos aplicáveis;
- inventário completo de equipamentos;
- diagramas de processo e instrumentação autorizados;
- estrutura de turnos;
- fluxo de manutenção;
- modelo de implantação;
- requisitos offline;
- backup, retenção e auditoria;
- governança dos dados;
- critérios de sucesso mensuráveis.

## 10. O que não foi incluído propositalmente

- nomes da empresa e pessoas;
- dados industriais detalhados;
- endereços de rede e tags completas;
- fórmula apresentada como verdade para “% de ar”;
- limites de segurança;
- procedimentos de purga;
- arquitetura de produção inventada;
- cronograma e custo sem evidência;
- personas fictícias detalhadas.

## 11. Resultado esperado da revisão

Ao final, classifique cada documento:

```text
APROVADO
APROVADO COM CORREÇÕES
PRECISA DE MAIS DESCOBERTA
NÃO DEVE ENTRAR NO REPOSITÓRIO
```

Depois consolidaremos somente o que estiver suficientemente claro.

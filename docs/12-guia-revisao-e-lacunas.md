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

- Quantas salas existem e como são chamadas?
- Como começa e termina uma ronda?
- Qual periodicidade?
- Existe roteiro por turno?
- Quais campos são registrados atualmente?
- Quais observações qualitativas são importantes?
- O que acontece quando uma leitura não pode ser feita?
- Como se registra equipamento parado, evacuado ou em manutenção?
- Como funciona a passagem de turno?
- Quais registros são oficiais e quais são informais?

> Nota editorial de saneamento histórico: referências a uma operação específica foram abstraídas. Esta nota não representa uma decisão tomada na data original do documento.

### Revisão abstrata da descoberta

As respostas e perguntas refinadas ligadas a uma operação específica foram suprimidas. Para a revisão de domínio, separar cadastro, estado, leitura, ocorrência e pendência; verificar unidade, origem, temporalidade e responsabilidade sem reconstruir uma instalação.

Essa síntese não resolve as perguntas originais nem introduz decisões posteriores de produto.

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

- O próximo passo continuará sendo `EquipmentResponse`?
- Quais campos do Equipment são públicos?
- Criação deve retornar 200 ou 201?
- Localização permanecerá texto?
- É desejável CI agora?
- O README deve ensinar a subir aplicação e banco?
- A aplicação será apenas backend por enquanto?
- Qual estratégia de autenticação será considerada futuramente?

## Evidências ainda necessárias

Separar hipóteses de confirmações e obter validação autorizada para regras que não possam ser inferidas do software. Não anexar materiais operacionais privados à documentação pública.

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

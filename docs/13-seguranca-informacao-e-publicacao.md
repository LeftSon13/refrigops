# Segurança da informação e publicação

## Finalidade e alcance

Esta política orienta desenvolvedores, revisores, agentes de IA e futuros
contribuidores antes de registrar ou publicar conteúdo do RefrigOps. Ela se
aplica a código, documentação, Issues, Pull Requests, commits, mensagens,
imagens e demais artefatos associados ao projeto.

O objetivo é permitir que o aprendizado profissional contribua com o produto
sem expor segredos, dados pessoais, informações de terceiros ou o contexto de
uma operação específica. Este documento é uma regra prática de governança do
repositório, não um parecer jurídico nem uma descrição de políticas internas de
qualquer empresa.

> A experiência profissional pode inspirar problemas e requisitos do RefrigOps,
> mas uma instalação real não deve ser reconstruível a partir do repositório
> público.

```text
experiência profissional real
        ↓
aprendizado abstrato
        ↓
regra genérica de produto
        ↓
cenário fictício DEMO
        ↓
documentação/código público
```

Ter acesso a uma informação não equivale a ter autorização para publicá-la.

## Classificação obrigatória da informação

Todo conteúdo deve ser classificado antes de commit ou publicação. Em caso de
dúvida entre duas categorias, aplique a mais restritiva e solicite revisão.

### Categoria 1 — conhecimento genérico e publicável

Abrange conhecimento que não caracteriza uma instalação, pessoa ou organização
específica, por exemplo:

- conceitos comuns de refrigeração industrial;
- conceitos genéricos de rondas, medições e ocorrências;
- arquitetura de software, Java, Spring e PostgreSQL;
- princípios genéricos de segurança.

**Tratamento:** pode ser publicado, desde que não ganhe especificidade por sua
combinação com outros dados do repositório.

### Categoria 2 — cenário fictício e publicável

Abrange conteúdo criado exclusivamente para demonstração, desenvolvimento ou
teste, sem correspondência afirmada com uma instalação real, por exemplo:

- `DEMO-COMP-01` e `DEMO-COMP-02`;
- `DEMO-REC-01`;
- Área Demonstrativa A;
- Operador A;
- Turno A;
- valores explicitamente identificados como sintéticos.

**Tratamento:** pode ser publicado quando sua natureza fictícia estiver clara e
o conjunto não reproduzir indiretamente uma operação real.

### Categoria 3 — contexto operacional específico

Abrange informação que descreve, localiza, identifica ou ajuda a reconstruir
uma operação real, por exemplo:

- tags, inventário, layout, salas, áreas, rotas ou topologia;
- regimes associados a equipamentos, modelos locais e configurações;
- procedimentos e documentos internos;
- horários e composição de equipe;
- ocorrências, intervenções, fotografias e valores observados;
- associações entre equipamentos, pessoas, locais, fornecedores e eventos.

**Tratamento:** manter privado, transformar em aprendizado abstrato ou substituir
por cenário fictício. Mascaramento superficial, como retirar apenas o nome da
empresa ou trocar parte de uma tag, não torna o conteúdo publicável. A
transformação precisa eliminar a correspondência que permita reconhecer ou
reconstruir a instalação.

### Categoria 4 — segredo ou dado inadequado

Abrange conteúdo cuja exposição exige interrupção imediata do fluxo, por
exemplo:

- senha, token, chave, credencial ou segredo de terceiros;
- arquivo `.env` preenchido;
- dado pessoal;
- endereço ou IP interno;
- caminho pessoal;
- qualquer informação cuja publicação não esteja autorizada.

**Tratamento:** parar antes de commit ou push, não copiar o conteúdo para canais
públicos e solicitar revisão. Quando já tiver havido exposição, seguir o
[procedimento para incidente](#procedimento-para-incidente-de-publicação).

## Conhecimento profissional e contexto específico

Conhecimento profissional é a conclusão geral que pode orientar um problema de
produto. Contexto operacional específico é a evidência concreta de onde, como,
quando, por quem ou com quais ativos aquela conclusão surgiu.

Uma informação não se torna publicável apenas porque não contém o nome da
empresa, não possui senha ou parece tecnicamente comum. Dados isoladamente
inofensivos podem, quando correlacionados, revelar uma instalação. A revisão
deve considerar tanto cada item quanto a combinação entre tags, modelos,
layout, horários, valores, imagens, histórico de commits e fontes externas.

## Exemplos e dados de demonstração

Exemplos ligados ao domínio devem usar o namespace `DEMO-*` e nomes claramente
fictícios, como:

```text
Área Demonstrativa A
├── DEMO-COMP-01
├── DEMO-COMP-02
└── DEMO-REC-01

Operador A
Turno A
```

Não reutilize tags reais como “exemplo”. Valores sintéticos devem ser marcados
como fictícios e não podem ser apresentados como leitura real, setpoint, limite,
faixa segura ou valor recomendado. O prefixo `DEMO-*` ajuda a identificar a
finalidade do dado, mas não corrige um conjunto que tenha sido copiado de uma
instalação real.

## Segredos e configuração de ambiente

- segredos nunca devem ser versionados, nem mesmo temporariamente;
- `.env` é local, deve permanecer fora do Git e não deve ser anexado a Issue,
  PR, log ou mensagem;
- `.env.example` pode conter somente nomes de variáveis, instruções seguras e
  placeholders sem valor utilizável;
- não use senha previsível como fallback nem valor real como “exemplo”;
- prefira variáveis de ambiente, por exemplo:

```properties
spring.datasource.password=${REFRIGOPS_DB_PASSWORD}
```

Uma credencial publicada deve ser considerada comprometida quando aplicável.
Remover seu valor do arquivo atual não revoga o segredo e não o apaga do
histórico; é preciso proteger ou rotacionar a credencial pelo canal apropriado.

## Pesquisa e descoberta operacional

Materiais brutos derivados de descoberta operacional permanecem fora do
repositório público quando contêm detalhes específicos. Isso inclui:

- entrevistas e relatos brutos;
- fotografias e screenshots operacionais;
- inventários, mapas, telas e diagramas reais;
- transcrições de documentos internos;
- procedimentos, logs, dumps e exports específicos.

O repositório público deve guardar apenas fontes públicas adequadamente
referenciadas ou sínteses como
[Aprendizados abstratos da descoberta operacional](pesquisa/aprendizados-abstratos-descoberta-operacional.md).

```text
observação privada
        ↓
conclusão abstrata
        ↓
documentação pública
```

Não copie o material de origem para “explicar” a síntese. Registre o que foi
aprendido, o que a evidência não prova e o que ainda precisa de validação.

## Fotos, documentos e outros artefatos

Antes de versionar fotos, screenshots, PDFs, planilhas, logs, dumps ou exports,
verifique o conteúdo visível e seus metadados. Procure:

- identificação de equipamento, pessoa, empresa ou terceiro;
- tela interna, endereço, IP, credencial ou caminho local;
- localização e metadados de autoria ou geolocalização;
- valor operacional, configuração, inventário ou procedimento;
- combinações que permitam correlacionar o artefato a uma operação real.

Imagens operacionais reais não devem entrar no repositório público. Recortar,
desfocar ou remover metadados isoladamente não basta quando o contexto ainda
pode ser reconhecido.

## Snapshot atual e histórico Git

```text
apagar arquivo no commit atual
≠
remover arquivo da história
```

Um commit que remove ou sanitiza conteúdo corrige apenas o snapshot atual. O
conteúdo pode continuar acessível em commits, branches, tags, forks, caches,
clones ou artefatos anteriores.

Se informação inadequada tiver sido publicada:

1. interrompa novas publicações e corrija primeiro o snapshot atual;
2. considere o segredo comprometido e proteja ou rotacione-o quando aplicável;
3. avalie separadamente a exposição e a necessidade de reescrever o histórico;
4. obtenha decisão explícita antes de alterar história compartilhada.

Não execute `git filter-repo`, BFG, force-push ou procedimento equivalente
automaticamente. Reescrever histórico é uma ação extraordinária, potencialmente
disruptiva, que exige autorização, plano de coordenação e validação próprios.

## Checklist antes de commit ou push

- [ ] há somente informação genérica/publicável ou cenário fictício `DEMO-*`;
- [ ] não há segredo, token, chave ou credencial;
- [ ] não há `.env` nem configuração local preenchida;
- [ ] não há dado pessoal, caminho pessoal ou informação de terceiro inadequada;
- [ ] não há tag, inventário, layout, configuração ou procedimento real;
- [ ] não há valor operacional real nem exemplo apresentado como limite seguro;
- [ ] não há imagem ou artefato operacional;
- [ ] não há referência que exponha documento ou material privado;
- [ ] exemplos do domínio usam `DEMO-*` e declaram que são fictícios;
- [ ] a combinação do conteúdo não permite reconstruir uma instalação real;
- [ ] arquivos novos foram lidos explicitamente;
- [ ] `git diff` e `git status` foram revisados;
- [ ] o staging foi revisado novamente antes do commit;
- [ ] o conteúdo foi reclassificado antes do push.

O checklist complementa as validações técnicas descritas no
[guia de contribuição](../CONTRIBUTING.md); ele não transfere a responsabilidade
de revisão para uma ferramenta automática.

## Regra específica para agentes de IA

> Agentes de IA não devem presumir autorização de publicação apenas porque
> tiveram acesso a uma informação durante uma conversa, arquivo local ou etapa
> de descoberta.

Antes de propor ou executar commit, push, criação de Issue/PR ou outra publicação
pública, o agente deve:

1. classificar o conteúdo nas categorias desta política;
2. procurar informações das categorias 3 e 4, inclusive por correlação;
3. transformar a categoria 3 em aprendizado abstrato ou cenário fictício;
4. bloquear a categoria 4 e evitar reproduzi-la na explicação do problema;
5. revisar arquivos novos, diff, staging e destino da publicação;
6. pedir autorização explícita quando houver dúvida ou ação extraordinária.

O agente deve trabalhar com o mínimo de informação necessário, não usar conteúdo
privado para enriquecer exemplos e nunca tratar acesso local como consentimento
para divulgação.

## Procedimento para incidente de publicação

```text
conteúdo inadequado identificado
        ↓
interromper publicação
        ↓
classificar sem ampliar a exposição
        ↓
proteger/rotacionar se for segredo
        ↓
sanitizar o snapshot atual
        ↓
avaliar histórico, cópias e alcance
        ↓
revisar a correção
        ↓
publicar a correção autorizada
```

Durante o incidente:

1. não reproduza o conteúdo sensível em Issue, PR, commit ou canal público;
2. preserve apenas a evidência mínima em local privado e autorizado;
3. acione o responsável pelo segredo, dado ou repositório;
4. separe revogação/rotação, correção do snapshot e eventual reescrita de
   histórico como decisões próprias;
5. confirme a remoção no estado atual e revise os destinos já publicados;
6. registre publicamente somente uma descrição sanitizada da correção.

A política foi formalizada após uma revisão preventiva da documentação e das
configurações do projeto. Detalhes sensíveis do evento que motivar uma revisão
não devem ser incorporados ao histórico público.

## Responsabilidade de revisão

Quem prepara o conteúdo realiza a primeira classificação. Quem revisa confirma
o resultado e observa correlações. A autorização de commit ou push não autoriza
automaticamente publicar informação que viole esta política; diante de dúvida,
o fluxo deve parar até que exista uma decisão explícita.

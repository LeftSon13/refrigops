# Como contribuir com o RefrigOps

## Princípios

O projeto tem duas finalidades simultâneas:

1. construir um produto coerente com uma operação industrial real;
2. criar um histórico de aprendizado e portfólio compreensível.

Por isso, clareza, testes e explicação das decisões importam tanto quanto velocidade.

## Antes de começar

Confirme:

```powershell
git status --short --branch
git branch --show-current
git log --oneline --decorate -n 15
```

Para iniciar uma nova feature, a `main` deve estar limpa e sincronizada. Verifique a Issue e seus critérios de aceite antes de criar a branch.

## Fluxo recomendado

```text
1. Issue em português brasileiro
2. branch criada a partir da main atualizada
3. teste ou reprodução do comportamento atual
4. implementação mínima
5. testes focados
6. suíte completa
7. revisão do diff
8. commits por responsabilidade
9. push e PR
10. code review
11. merge
12. sincronização e limpeza das branches
```

## Branches

Padrões históricos:

```text
feature/equipment-persistence
feature/testcontainers-integration
feature/equipment-validation
```

Use nomes curtos, em inglês e relacionados à Issue. Uma branch deve representar um resultado coeso.

## Commits

Prefixos usados:

```text
feat:     nova capacidade
fix:      correção de comportamento
test:     testes
refactor: mudança interna sem alterar comportamento esperado
build:    dependências e construção
docs:     documentação
chore:    manutenção
```

Commits devem contar a evolução da solução. Evite “misc changes”, arquivos sem relação e commits gigantes.

## Antes de adicionar ao staging

```powershell
git status
git diff --check
git diff
```

Para arquivos novos, leia o conteúdo explicitamente, pois o `git diff` comum não mostra arquivos não rastreados.

## Antes do commit

```powershell
git status
git diff --cached --check
git diff --cached
```

Confirme que o staging contém somente a responsabilidade do commit.

## Testes

- execute o teste mais próximo da mudança durante o desenvolvimento;
- antes do PR, execute a suíte completa;
- testes que usam Spring devem permanecer isolados do banco de desenvolvimento;
- não declare `BUILD SUCCESS` sem ter executado a validação indicada;
- registre no PR o comando e o resultado real;
- não desative `contextLoads`.

## Pull Request

O PR deve incluir:

- contexto e problema;
- objetivo;
- relação com a Issue usando `Closes #N`, quando apropriado;
- implementação realizada;
- motivo das decisões;
- alternativas relevantes não usadas;
- validações executadas e resultados;
- riscos ou pendências fora do escopo;
- aprendizados da etapa.

Faça code review na aba de arquivos antes do merge. O histórico adotou merge normal para preservar commits com responsabilidades úteis; não use squash automaticamente.

## Encerramento

Após o merge:

1. atualizar a `main` local;
2. confirmar histórico e testes quando necessário;
3. remover referências remotas obsoletas;
4. apagar branch local somente após provar que foi integrada;
5. atualizar `docs/11-contexto-atual.md` se o checkpoint mudou.

## Segurança e dados

- não incluir credenciais, tokens, arquivos `.env`, dados pessoais ou informações industriais sensíveis;
- usar exemplos fictícios ou anonimizados;
- não conectar testes ao banco de desenvolvimento ou produção;
- não publicar repositórios, imagens, logs ou dados sem autorização.

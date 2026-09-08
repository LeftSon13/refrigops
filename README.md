> Nota editorial de saneamento histórico: referências a uma operação específica foram abstraídas. Esta nota não representa uma decisão tomada na data original do documento.

> Exemplos JSON substituídos editorialmente por dados fictícios DEMO, sem correspondência com uma instalação real.

# RefrigOps

> Fundação documental de um sistema de apoio à refrigeração industrial.

## Aviso de confiabilidade

Esta documentação registra auditorias realizadas em datas específicas. Informações voláteis sobre Git, testes, ambiente e integrações devem ser revalidadas no checkout real antes de novas alterações.

Use estas classificações ao ler ou atualizar os documentos:

- **[CONFIRMADO — REPOSITÓRIO]**: verificado diretamente no checkout atual;
- **[CONFIRMADO — CONTEXTO OPERACIONAL]**: relato do usuário sobre a operação real;
- **[HISTÓRICO — REPOSITÓRIO]**: verificado anteriormente, mas ainda não reconfirmado;
- **[HISTÓRICO — CONVERSA]**: recuperado das conversas;
- **[DECISÃO]**: escolha deliberada de produto, arquitetura ou processo;
- **[HIPÓTESE]**: explicação ou proposta que precisa de validação;
- **[PENDENTE]**: pergunta ainda sem resposta suficiente.

## O que é o RefrigOps

O RefrigOps é um projeto de sistema de apoio à operação de refrigeração industrial com amônia R717, inspirado em necessidades genéricas de registro e rastreabilidade.

Sua proposta não é substituir o operador nem comandar a planta. A visão é organizar o contexto operacional, criar histórico confiável e permitir uma evolução segura de registros manuais para análises e integrações futuras.

## Estado histórico resumido

**[HISTÓRICO — REPOSITÓRIO]**

- Java 21 e Spring Boot 4.0.7;
- PostgreSQL 17, Flyway e Spring Data JPA;
- Testcontainers com PostgreSQL temporário;
- API inicial de equipamentos;
- validação HTTP no cadastro;
- último merge conhecido: PR #5, commit `938480a`;
- última suíte conhecida: 5 testes passando;
- próxima Issue recomendada, ainda não criada no último checkpoint: **Desacoplar respostas da API da entidade Equipment**.

Consulte [docs/11-contexto-atual.md](docs/11-contexto-atual.md) antes de qualquer trabalho.

## Pré-requisitos atuais

**[CONFIRMADO — REPOSITÓRIO]**

- Java 21;
- Docker Desktop;
- Git;
- PowerShell no Windows;
- Maven Wrapper incluído no projeto.

## Executar localmente

O PostgreSQL e a aplicação usam a variável `REFRIGOPS_DB_PASSWORD`. Crie o
arquivo local a partir do exemplo e substitua o placeholder por uma senha
própria, sem compartilhar ou versionar o valor:

```powershell
Copy-Item .env.example .env
```

O Docker Compose lê `.env` automaticamente para interpolar a variável. Uma
aplicação Spring Boot iniciada diretamente no host não lê esse arquivo por si
só. Antes de iniciar a aplicação, carregue a mesma senha apenas na sessão atual
do PowerShell, usando uma entrada que não exibe os caracteres nem grava o valor
no histórico:

```powershell
$senhaSegura = Read-Host 'Senha local do PostgreSQL' -AsSecureString
$credencialLocal = [pscredential]::new('refrigops', $senhaSegura)
$env:REFRIGOPS_DB_PASSWORD = $credencialLocal.GetNetworkCredential().Password
Remove-Variable senhaSegura, credencialLocal

docker compose up -d postgres
.\mvnw.cmd spring-boot:run
```

A aplicação usa PostgreSQL em `localhost:5433` e, por padrão, inicia o servidor HTTP na porta `8080`.

Ao encerrar o trabalho, remova a variável da sessão atual:

```powershell
Remove-Item Env:REFRIGOPS_DB_PASSWORD
```

Em um volume PostgreSQL já inicializado, alterar `POSTGRES_PASSWORD` não muda a
senha do usuário existente. Preserve o volume e atualize a senha explicitamente
com `psql`; nunca use `docker compose down -v` para fazer essa rotação.

Com o container ativo, abra o `psql` e use o prompt interativo, que não exibe a
senha digitada ou colada:

```powershell
docker exec -it refrigops-postgres psql -U refrigops -d refrigops
```

```text
\password refrigops
\q
```

Endpoints atuais:

```text
GET  http://localhost:8080/api/equipment
POST http://localhost:8080/api/equipment
```

Exemplo de criação:

```json
{
  "code": "DEMO-COMP-01",
  "name": "Compressor Fictício 01",
  "type": "COMPRESSOR",
  "location": "Área Demonstrativa A"
}
```

Não use dados reais ou sensíveis em ambientes de estudo.

## Executar testes

Com Docker Desktop disponível:

```powershell
.\mvnw.cmd clean test
```

Os testes integrados criam um PostgreSQL temporário com Testcontainers. Eles não devem usar o banco persistente de desenvolvimento.

## Mapa da documentação

- [Documento mestre do produto](docs/00-documento-mestre-produto.md)
- [Contexto operacional](docs/01-contexto-operacional.md)
- [Arquitetura atual](docs/02-arquitetura-atual.md)
- [Regras de negócio e domínio](docs/03-regras-negocio-e-dominio.md)
- [Medições, unidades e fontes](docs/04-medicoes-unidades-e-fontes.md)
- [Riscos, segurança e limites](docs/05-riscos-seguranca-e-limites.md)
- [MVP, roadmap e critérios de passagem](docs/06-mvp-roadmap-e-criterios.md)
- [Testes, ambientes e operação](docs/07-testes-ambientes-e-operacao.md)
- [Histórico de decisões e aprendizados](docs/08-historico-decisoes-e-aprendizados.md)
- [Contrato atual da API](docs/09-contrato-api-atual.md)
- [Contexto atual](docs/11-contexto-atual.md)
- [Guia de revisão e lacunas](docs/12-guia-revisao-e-lacunas.md)
- [Decisões arquiteturais](docs/adr/README.md)
- [Histórias de usuário](docs/historias-usuario/README.md)
- [Registro de evidências e pesquisa](docs/pesquisa/README.md)

## Para um novo assistente

Antes de agir:

1. leia `AGENTS.md` por inteiro;
2. leia este `README.md`;
3. leia `docs/11-contexto-atual.md`;
4. abra somente os documentos relacionados à tarefa;
5. inspecione o repositório real;
6. separe evidência atual de memória histórica;
7. apresente diagnóstico e plano antes de alterações.

## Estado deste pacote

Esta fundação documental não significa que todas as regras de domínio estejam aprovadas nem que o roadmap esteja priorizado. Pendências, bloqueios e o último checkpoint auditado ficam centralizados em [docs/11-contexto-atual.md](docs/11-contexto-atual.md).

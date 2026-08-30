# US-002 — Listar equipamentos

- Estado: implementada historicamente; cobertura a ampliar

## História

Como usuário do sistema, quero consultar os equipamentos cadastrados para reconhecer os ativos disponíveis no RefrigOps.

## Critérios confirmados no código

- `GET /api/equipment` consulta todos os registros;
- a resposta atual é `List<Equipment>`;
- não há filtro, paginação ou ordenação explícita.

## Pendências

- definir usuário/perfil;
- definir campos públicos;
- definir ordenação;
- definir paginação;
- definir filtro por ativo, tipo e localização;
- criar testes de conteúdo JSON;
- decidir como representar lista vazia.

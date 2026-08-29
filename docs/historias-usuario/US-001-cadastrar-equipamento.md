# US-001 — Cadastrar equipamento

- Estado: implementada historicamente; revisar contrato

## História

Como pessoa responsável pela configuração do sistema, quero cadastrar um equipamento com código, nome, tipo e localização para identificá-lo nas funcionalidades futuras.

## Critérios confirmados

- código, nome e localização não aceitam valor em branco;
- tipo é obrigatório;
- equipamento é criado com status `STOPPED`;
- equipamento é criado ativo;
- código é único no banco;
- requisição válida retorna sucesso;
- requisição com campos obrigatórios inválidos retorna 400.

## Pendências

- definir perfil autorizado;
- definir 200 ou 201;
- definir resposta pública;
- definir erro de duplicidade;
- validar formato do código;
- definir limites de tamanho na API.

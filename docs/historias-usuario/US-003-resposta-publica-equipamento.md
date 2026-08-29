# US-003 — Receber representação pública de equipamento

- Estado: proposta
- Issue sugerida: Desacoplar respostas da API da entidade Equipment

## História

Como consumidor da API, quero receber uma representação pública e estável de equipamento para que mudanças internas de persistência não alterem o contrato HTTP sem intenção.

## Critérios de aceite sugeridos

- criar `EquipmentResponse`;
- POST não retornar `Equipment`;
- GET não retornar `List<Equipment>`;
- decidir campos públicos antes da implementação;
- manter a entidade internamente;
- testar conteúdo JSON;
- manter testes atuais passando.

## Fora do escopo sugerido

- 201 Created;
- tratamento global de erros;
- biblioteca automática de mapeamento;
- alteração de migration;
- novas regras de equipamento.

## Decisões pendentes

- campos expostos;
- local do mapeamento;
- uso de método estático, mapper simples ou outra abordagem;
- nomes JSON;
- compatibilidade futura.

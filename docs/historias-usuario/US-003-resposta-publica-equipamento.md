# US-003 — Receber representação pública de equipamento

- Estado: implementada na branch `feature/equipment-response`, aguardando revisão
- Issue: #8 — Desacoplar respostas da API da entidade Equipment

## História

Como consumidor da API, quero receber uma representação pública e estável de equipamento para que mudanças internas de persistência não alterem o contrato HTTP sem intenção.

## Critérios de aceite implementados

- criar `EquipmentResponse`;
- POST não retornar `Equipment`;
- GET não retornar `List<Equipment>`;
- preservar os campos públicos `id`, `code`, `name`, `type`, `status`, `active` e `location`;
- manter a entidade internamente;
- testar conteúdo JSON;
- manter testes atuais passando.

## Fora do escopo sugerido

- 201 Created;
- tratamento global de erros;
- biblioteca automática de mapeamento;
- alteração de migration;
- novas regras de equipamento.

## Decisões desta entrega

- os campos e nomes JSON atuais foram preservados;
- o mapeamento explícito ocorre na fronteira HTTP por `EquipmentResponse.from(Equipment)`;
- nenhum mapper framework foi introduzido;
- o Service continua retornando `Equipment` e não depende de DTO HTTP.

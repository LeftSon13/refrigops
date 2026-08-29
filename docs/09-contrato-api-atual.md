> Nota editorial de saneamento histórico: referências a uma operação específica foram abstraídas. Esta nota não representa uma decisão tomada na data original do documento.

> Exemplos JSON substituídos editorialmente por dados fictícios DEMO, sem correspondência com uma instalação real.

# Contrato atual da API

> Estado confirmado no código em 2026-08-29. Este documento descreve o comportamento atual, inclusive dívidas conhecidas; não significa que todo o contrato esteja aprovado como definitivo.

## Base

```text
/api/equipment
```

Não há versionamento de API, autenticação ou OpenAPI confirmados.

## Listar equipamentos

```http
GET /api/equipment
```

### Resposta atual

HTTP 200 com array da entidade `Equipment` serializada.

Exemplo inferido do modelo:

```json
[
  {
    "id": 1,
    "code": "DEMO-COMP-01",
    "name": "Compressor Fictício 01",
    "type": "COMPRESSOR",
    "status": "STOPPED",
    "active": true,
    "location": "Área Demonstrativa A"
  }
]
```

**[PENDENTE]** Não há teste confirmando conteúdo JSON ou ordenação da listagem.

## Criar equipamento

```http
POST /api/equipment
Content-Type: application/json
```

### Corpo

```json
{
  "code": "DEMO-COMP-01",
  "name": "Compressor Fictício 01",
  "type": "COMPRESSOR",
  "location": "Área Demonstrativa A"
}
```

### Validações

| Campo | Regra atual |
|---|---|
| `code` | `@NotBlank` |
| `name` | `@NotBlank` |
| `type` | `@NotNull` e enum válido na desserialização |
| `location` | `@NotBlank` |

### Resposta válida atual

HTTP 200 com a entidade salva:

```json
{
  "id": 1,
  "code": "DEMO-COMP-01",
  "name": "Compressor Fictício 01",
  "type": "COMPRESSOR",
  "status": "STOPPED",
  "active": true,
  "location": "Área Demonstrativa A"
}
```

### Resposta inválida atual

HTTP 400 pelo tratamento padrão do Spring para Bean Validation ou corpo incompatível.

O formato exato do erro ainda não é contrato documentado nem coberto por asserção.

### Código duplicado

Existe restrição única no banco, mas a API não define resposta padronizada. O comportamento deve ser investigado antes de documentar status específico.

## Estados e tipos aceitos

Tipos:

```text
COMPRESSOR
RECEIVER
CONDENSER
```

Estados retornáveis:

```text
RUNNING
STOPPED
MAINTENANCE
EVACUATED
DEACTIVATED
```

Na criação, o estado é sempre `STOPPED` e `active` é sempre `true`.

## Dívida principal

A API expõe a entidade JPA. A proposta é introduzir `EquipmentResponse` e transformar os exemplos deste documento em contrato testado.

## Decisões separadas

Não misturar automaticamente:

- DTO de resposta;
- 201 Created;
- `Location` header;
- tratamento global de erros;
- conflito 409;
- versionamento;
- OpenAPI;
- autenticação.

Cada decisão deve ter Issue e critérios próprios ou ser agrupada conscientemente.

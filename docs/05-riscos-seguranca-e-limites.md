# Riscos, segurança e limites

## 1. Posicionamento

O RefrigOps trabalha com informações de um processo industrial que utiliza amônia e equipamentos pressurizados. Erros de interpretação podem ter consequências graves.

**[DECISÃO]** O sistema é de apoio informacional. Não é camada de controle, proteção ou autorização operacional.

## 2. Limites de responsabilidade do produto

O RefrigOps não deve:

- comandar compressores, bombas, ventiladores ou válvulas;
- alterar setpoints;
- substituir PLC, IHM, intertravamentos ou sistemas instrumentados de segurança;
- recomendar abertura de válvulas, purga ou intervenção física sem processo de engenharia aprovado;
- declarar condição segura apenas por cálculo;
- substituir instrumentos calibrados;
- substituir inspeções e procedimentos;
- ocultar a origem e incerteza de um dado;
- apresentar “% de ar” como medição real enquanto o método não estiver validado.

## 3. Riscos do domínio

### Interpretação errada de unidades

Comparar bar com kgf/cm², ou pressão absoluta com manométrica, pode gerar conclusões incorretas.

### Temperatura não independente

Usar a escala de NH₃ do mesmo manômetro como temperatura medida cria dependência circular.

### Sensor mal representativo

Um PT100 externo pode medir superfície/ambiente e não o fluido de interesse.

### Fórmula não validada

Uma conta que reproduz um formulário não é automaticamente um método aceito de diagnóstico.

### Dados manuais

Digitação, arredondamento, memória e adaptação de valores podem introduzir erro.

### Automação prematura

Integrar tags sem entender origem, escala, qualidade e segurança amplia a velocidade do erro.

## 4. Riscos de software

- ausência atual de autenticação e autorização;
- contratos HTTP acoplados à entidade;
- falta de tratamento padronizado de erro;
- ausência de auditoria de alterações;
- ausência de política de retenção e backup documentada;
- credenciais locais simples no repositório;
- possibilidade de dados reais aparecerem em logs, testes ou screenshots;
- dependência de Docker para testes de integração;
- risco de documentação ficar desatualizada.

## 5. Segurança da informação

Antes de qualquer implantação, classificar:

- dados pessoais dos operadores;
- horários e escalas;
- informações de planta e equipamentos;
- topologia, tags, endereços e protocolos;
- logs e ocorrências;
- credenciais;
- backups;
- dados enviados para serviços externos.

**[DECISÃO]** Não registrar no Git:

- senhas reais;
- tokens;
- arquivos `.env` de ambiente real;
- diagramas ou tags sensíveis sem autorização;
- dados pessoais;
- fotos ou logs industriais sem revisão.

## 6. Separação de redes e integração

**[PENDENTE CRÍTICO]** Qualquer integração com rede industrial deve ser analisada por responsáveis de TI/OT e segurança.

Questões mínimas:

- fonte somente leitura?
- protocolo e gateway aprovados?
- segmentação de rede?
- impacto de indisponibilidade?
- taxa de coleta?
- credenciais e rotação?
- armazenamento e transmissão?
- resposta a incidentes?
- responsabilidade por qualidade das tags?

## 7. Alertas

**[DECISÃO PROPOSTA]** Todo alerta futuro deve possuir:

- nome claro;
- regra e versão;
- dados de entrada e origem;
- unidade;
- horário;
- limiar aprovado;
- responsável pela aprovação;
- orientação que não conflite com procedimentos;
- possibilidade de reconhecer falso positivo;
- histórico auditável.

Alertas não devem ser criados apenas porque um gráfico “parece diferente”.

## 8. Privacidade e pessoas

Se o sistema registrar operador, turno, correções e tempos, deve evitar uso punitivo não transparente e coletar somente o necessário.

**[PENDENTE]** Definir:

- fundamento e finalidade do tratamento;
- perfis de acesso;
- retenção;
- transparência;
- correção de registro;
- exportação e exclusão quando aplicável;
- regras organizacionais e legais atuais.

## 9. Ambientes

**[DECISÃO]** Separar:

```text
desenvolvimento
testes automatizados
homologação/piloto
produção
```

Testes automatizados não podem usar banco de desenvolvimento ou produção. Piloto operacional deve ter plano de fallback e não se tornar dependência de segurança.

## 10. Critérios antes de piloto

- objetivo e escopo aprovados;
- usuários e fluxo observados;
- revisão de segurança e privacidade;
- dados permitidos definidos;
- autenticação e perfis mínimos;
- backup e recuperação testados;
- funcionamento offline/indisponibilidade considerado;
- alertas fora de escopo ou formalmente aprovados;
- treinamento e suporte;
- canal para incidentes e correções;
- aceite explícito de que o sistema não substitui controles industriais.

## 11. Matriz inicial de riscos

| Risco | Probabilidade atual | Impacto | Tratamento inicial |
|---|---|---|---|
| Unidade incorreta | relevante | alto | unidade explícita e conversão versionada |
| Dado sem origem | relevante | médio/alto | tornar origem parte do modelo |
| Regra física inválida | relevante | alto | validação especializada e linguagem cautelosa |
| Exposição de dado industrial | desconhecida | alto | classificação e mínimo privilégio |
| Teste alterar banco real | reduzida | alto | manter Testcontainers |
| Falha de autenticação | certa no estado atual | alto em implantação | não implantar antes de segurança |
| Documentação desatualizada | relevante | médio | contexto atual e auditoria por tarefa |
| Interface rejeitada por operadores | desconhecida | alto para adoção | piloto e pesquisa de usabilidade |

Probabilidades são qualitativas e precisam de revisão.

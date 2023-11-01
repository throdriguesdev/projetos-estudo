# Descrição do Código ABAP

Este é um programa ABAP que realiza uma consulta e exibição de dados de tabelas relacionadas. O programa tem uma interface de seleção que permite ao usuário escolher entre duas opções: "Usando INNER JOIN" (p_type = 'I') ou "USANDO FOR ALL ENTRIES" (p_type diferente de 'I'). O programa se conecta às seguintes tabelas:

- `vbak`: Tabela de cabeçalho de pedidos de vendas.
- `vbap`: Tabela de itens de pedidos de vendas.
- `makt`: Tabela de textos de materiais.

## Opções de Consulta

O programa oferece duas opções de consulta:

### Usando INNER JOIN

Se o usuário selecionar "Usando INNER JOIN" (p_type = 'I'), o programa realiza uma consulta usando INNER JOIN nas tabelas `vbak`, `vbap` e `makt`. Ele seleciona os campos de interesse dessas tabelas e armazena os resultados na tabela interna `it_output`.

### USANDO FOR ALL ENTRIES

Se o usuário selecionar "USANDO FOR ALL ENTRIES" (p_type diferente de 'I'), o programa primeiro seleciona todos os registros da tabela `vbak` e os armazena na tabela interna `it_vbak`. Em seguida, ele seleciona os registros correspondentes da tabela `vbap` usando a cláusula "FOR ALL ENTRIES IN" com base no campo `vbeln`. Da mesma forma, ele seleciona os registros correspondentes da tabela `makt` com base no campo `matnr`.



## Código ABAP



# Código ABAP para Criação de Pedido de Vendas

## Visão Geral

Este código ABAP é uma implementação que cria uma entrada de Pedido de Vendas em um sistema SAP. Ele utiliza a função de BAPI (Business Application Programming Interface) para essa finalidade.
## Parâmetros

O código começa declarando diversos parâmetros que devem ser preenchidos pelo usuário antes de executar o programa. Esses parâmetros incluem informações como o tipo de documento, organização de vendas, canal de distribuição, divisão, clientes vendedor e de entrega, material, quantidade, planta e categoria de item.

## Declarações de Dados

O código declara estruturas de dados para armazenar informações relacionadas à entrada do Pedido de Vendas, como dados do cabeçalho, parceiros de negócios, itens do pedido de vendas, dados de retorno e tabelas de programação de entrega.

## Preenchimento de Dados

Os parâmetros fornecidos pelo usuário são usados para preencher as estruturas de dados relevantes. Isso inclui os dados do cabeçalho, parceiros de negócios, itens do pedido de vendas e tabelas de programação de entrega. As estruturas de dados são preparadas para serem usadas como entrada para a BAPI de criação de Pedido de Vendas.


## Preenchimento de Programação de Entrega

O código também preenche tabelas relacionadas à programação de entrega, indicando quantidades e datas planejadas para entrega.

#### CÓDIGO




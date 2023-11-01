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
REPORT ZABAP22R_CONSULTA1
PARAMETERS: p_auart TYPE auart OBLIGATORY.
PARAMETERS: p_vkorg TYPE vkorg OBLIGATORY.
PARAMETERS: p_vtweg TYPE vtweg OBLIGATORY.
PARAMETERS: p_spart TYPE vtweg OBLIGATORY.

PARAMETERS: p_sold TYPE kunnr OBLIGATORY.
PARAMETERS: p_ship TYPE kunnr OBLIGATORY.

*ITEM
PARAMETERS: p_matnr TYPE matnr OBLIGATORY.
PARAMETERS: p_menge TYPE kwmeng OBLIGATORY.
PARAMETERS: p_plant TYPE werks_d OBLIGATORY.
PARAMETERS: p_itcat TYPE pstyv   OBLIGATORY.

* DATA DECLARATIONS.
DATA: v_vbeln LIKE vbak-vbeln.
DATA: header LIKE bapisdhead1.
DATA: headerx LIKE bapisdhead1x.
DATA: item    LIKE bapisditem OCCURS 0 WITH HEADER LINE.
DATA: itemx   LIKE bapisditemx OCCURS 0 WITH HEADER LINE.
DATA: partner LIKE bapipartnr  OCCURS 0 WITH HEADER LINE.
DATA: return  LIKE bapiret2    OCCURS 0 WITH HEADER LINE.
DATA: lt_schedules_inx   TYPE STANDARD TABLE OF bapischdlx
                         WITH HEADER LINE.
DATA: lt_schedules_in    TYPE STANDARD TABLE OF bapischdl
                         WITH HEADER LINE.




* HEADER DATA
header-doc_type = p_auart.
headerx-doc_type = 'X'.

header-sales_org = p_vkorg.
headerx-sales_org = 'X'.

header-distr_chan  = p_vtweg.
headerx-distr_chan = 'X'.

header-division = p_spart.
headerx-division = 'X'.

headerx-updateflag = 'I'.

* PARTNER DATA
partner-partn_role = 'AG'.
partner-partn_numb = p_sold.
APPEND partner.

partner-partn_role = 'WE'.
partner-partn_numb = p_ship.
APPEND partner.

* ITEM DATA
itemx-updateflag = 'I'.

item-itm_number = '000010'.
itemx-itm_number = 'X'.


item-material = p_matnr.
itemx-material = 'X'.

item-plant    = p_plant.
itemx-plant   = 'X'.

item-target_qty = p_menge.
itemx-target_qty = 'X'.

item-target_qu = 'EA'.
itemx-target_qu = 'X'.

item-item_categ = p_itcat.
itemx-item_categ = 'X'.

APPEND item.
APPEND itemx.

*   Fill schedule lines
lt_schedules_in-itm_number = '000010'.
lt_schedules_in-sched_line = '0001'.
lt_schedules_in-req_qty    = p_menge.
APPEND lt_schedules_in.

*   Fill schedule line flags
lt_schedules_inx-itm_number  = '000010'.
lt_schedules_inx-sched_line  = '0001'.
lt_schedules_inx-updateflag  = 'X'.
lt_schedules_inx-req_qty     = 'X'.
APPEND lt_



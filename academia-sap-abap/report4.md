

### Objetivo
O objetivo deste código é buscar informações sobre materiais no sistema SAP com base em um determinado tipo de material (MTART) e número de material (MATNR) fornecidos pelo usuário.

### Estrutura do Programa
O código começa definindo um relatório ABAP (REPORT ZABAP_22R_AULA_08), que é uma unidade básica de execução no SAP. Em seguida, declara parâmetros que o usuário pode fornecer:
- `P_MTART`: É o tipo de material que o usuário deseja pesquisar.
- `P_MATNR`: É o número de material específico que o usuário deseja pesquisar.

### Início da Execução
A seção `START-OF-SELECTION` indica o início da parte executável do código

- Faz uma consulta à tabela `MARA`, que armazena informações sobre materiais no sistema SAP.
- Utiliza a cláusula `INTO TABLE` para armazenar os resultados da consulta em uma tabela interna chamada `T_MARA`.
- Aplica uma condição `WHERE` para buscar apenas os registros da tabela `MARA` em que o campo `MTART` é igual ao valor fornecido em `P_MTART`.

### Verificação e Saída
Após a consulta, o código verifica se a pesquisa retornou algum resultado com a condição `IF SY-SUBRC IS INITIAL`. Se a condição for verdadeira (ou seja, registros foram encontrados), ele exibe o número de material `P_MATNR`. Caso contrário, ele mostra a mensagem 'NÃO ENCONTRADO'.

### Resumo
Em resumo, este código ABAP permite ao usuário buscar informações sobre materiais no sistema SAP com base em um tipo de material e número de material especificados. Ele consulta a tabela `MARA`, verifica se há correspondências e exibe os resultados ou uma mensagem de 'NÃO ENCONTRADO'.




REPORT ZABAP_22R_REPORT4
*TABLES: MARA.
PARAMETERS: P_MTART TYPE MARA-MTART.
PARAMETERS P_MATNR TYPE MARA-MATNR.
START-OF-SELECTION.


*SELECT-OPTIONS S _MATNR FOR MARA-MATNR.
*SELECIONAR VARIOS

DATA V_MTART TYPE MARA-MTART.


SELECT  MATNR, MTART
  FROM MARA
INTO TABLE @DATA(T_MARA)
  WHERE MTART EQ @P_MTART.
  IF SY-SUBRC IS INITIAL.
    WRITE p_matnr.
    ELSE.
      WRITE ' NAO ENCONTRADO'.
    ENDIF.


####

Report ZABAP_22R_AULA_08.
TYPES: BEGIN OF TY_MARA,
      MATNR TYPE MARA-MATNR,
      MTART TYPE MARA-MTART,
      END OF TY_MARA.
    
DATA T_MARA2 TYPE TABLE OF  TY_MARA.
      DATA WA_MARA TYPE  TY_MARA.


SELECT  MATNR,MTART 
  FROM MARA
INTO TABLE @DATA(T_MARA)
  WHERE MTART EQ @P_MTART.
  IF SY-SUBRC IS INITIAL.
    LOOP AT T_MARA INTO WA_MARA.
      WRITE / WA-MARA-MATNR.
      WRITE WA_MARA-MTART.
      ENDLOOP.
    ELSE.
      WRITE ' NAO ENCONTRADO'.
    ENDIF
	

    ####


DATA: BEGIN OF itab OCCURS 0,
        matnr TYPE matnr,  " Certifique-se de que este tipo é válido no seu sistema
      END OF itab.

SELECT-OPTIONS: s_matnr FOR itab-matnr.

START-OF-SELECTION.

  SELECT matnr INTO TABLE itab FROM mara WHERE matnr IN s_matnr.

  LOOP AT itab.
    WRITE: / itab-matnr.
  ENDLOOP.



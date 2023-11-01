## Explicação do Código ABAP

Este é um exemplo simples de código ABAP que lida com seleção de registros de uma tabela e exibição de resultados.

### Objetivo
O objetivo deste código é selecionar registros da tabela MAKT com base nas opções de seleção fornecidas pelo usuário e exibir os resultados na tela.

### Estrutura do Programa
O código começa definindo um relatório ABAP  e declarações de tipos de dados. Aqui está o que cada parte faz:

- `TY_MAKT`: Define uma estrutura de dados que representa os campos da tabela MAKT.
- `IT_MAKT`: É uma tabela interna que será usada para armazenar os resultados da seleção.
- `WA_MAKT`: É uma linha da tabela interna `IT_MAKT`.

Em seguida, o código define duas opções de seleção:
- `S_MATNR`: Permite ao usuário fornecer uma lista de números de material.
- `S_MAKTX`: Permite ao usuário fornecer uma lista de descrições de material.
- `P_SPRAS`: Parâmetro obrigatório que especifica o idioma a ser usado.

### Início da Execução
A seção `START-OF-SELECTION` indica o início da parte executável do código. Aqui, o programa faz o seguinte:

- Executa uma consulta à tabela `MAKT` para selecionar registros com base nas condições especificadas:
  - O número de material (`MATNR`) deve estar na lista fornecida em `S_MATNR`.
  - A descrição de material (`MAKTX`) deve estar na lista fornecida em `S_MAKTX`.
  - O idioma (`SPRAS`) deve ser igual ao idioma especificado em `P_SPRAS`.
- Os registros correspondentes são armazenados na tabela interna `IT_MAKT`.

### Exibição dos Resultados
Após a seleção dos registros, o código usa um loop (`LOOP AT`) para percorrer a tabela interna `IT_MAKT`. Para cada registro, ele exibe o número de material (`MATNR`) e a descrição de material (`MAKTX`) na tela.

### Resumo
Em resumo, este código ABAP permite ao usuário buscar registros na tabela `MAKT` com base em números de material, descrições de material e idioma. Ele armazena os resultados na tabela interna e exibe esses resultados na tela.

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
	
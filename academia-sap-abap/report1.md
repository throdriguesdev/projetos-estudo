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

## Exibição de Resultados

Independentemente da opção selecionada, o programa finaliza com um loop sobre a tabela `it_output` e exibe os dados em um formato tabular, alternando as cores das linhas para tornar a saída mais legível.

## Código ABAP

Aqui está o código ABAP correspondente:

```abap
REPORT ZABAP22R_CONSULTA1

TYPES: BEGIN OF ty_output,
         vbeln TYPE vbak-vbeln,
         erdat TYPE vbak-erdat,
         ernam TYPE vbak-ernam,
         posnr TYPE vbap-posnr,
         matnr TYPE vbap-matnr,
         maktx TYPE makt-maktx,
       END OF ty_output.

DATA: it_output TYPE TABLE OF ty_output,
      wa_output TYPE ty_output.

DATA: it_vbak TYPE TABLE OF vbak,
      wa_vbak TYPE vbak.

DATA: it_vbap TYPE TABLE OF vbap,
      wa_vbap TYPE vbap.

DATA: it_makt TYPE TABLE OF makt,
      wa_makt TYPE makt.

PARAMETERS: p_type TYPE c RADIOBUTTON GROUP g1,
            p_all  TYPE c RADIOBUTTON GROUP g1.

START-OF-SELECTION.

  SELECT * FROM vbak INTO TABLE it_vbak.
  IF p_type = 'I'. "Usando INNER JOIN 
    SELECT a~vbeln  a~erdat a~ernam
           b~posnr b~matnr c~maktx
      INTO TABLE it_output
      FROM vbak AS a
           INNER JOIN vbap AS b ON a~vbeln = b~vbeln
           LEFT JOIN makt AS c ON b~matnr = c~matnr.
  ELSE. "USANDO FOR ALL ENTRIES
    SELECT * FROM vbap INTO TABLE it_vbap FOR ALL ENTRIES IN it_vbak
      WHERE vbeln = it_vbak-vbeln.

    SELECT * FROM makt INTO TABLE it_makt FOR ALL ENTRIES IN it_vbap
      WHERE matnr = it_vbap-matnr.

    LOOP AT it_vbak INTO wa_vbak.
      LOOP AT it_vbap INTO wa_vbap WHERE vbeln = wa_vbak-vbeln.
        READ TABLE it_makt INTO wa_makt WITH KEY matnr = wa_vbap-matnr.

        MOVE-CORRESPONDING wa_vbak TO wa_output.
        MOVE-CORRESPONDING wa_vbap TO wa_output.
        MOVE-CORRESPONDING wa_makt TO wa_output.

        APPEND wa_output TO it_output.
      ENDLOOP.
    ENDLOOP.

  ENDIF.

END-OF-SELECTION.

  LOOP AT it_output INTO wa_output.
    IF sy-tabix MOD 2 = 0.
      WRITE:/ wa_output-vbeln COLOR COL_BACKGROUND,
              wa_output-posnr COLOR COL_BACKGROUND,
              wa_output-matnr COLOR COL_BACKGROUND,
              wa_output-maktx COLOR COL_BACKGROUND,
              wa_output-erdat COLOR COL_BACKGROUND,
              wa_output-ernam COLOR COL_BACKGROUND.
    ELSE.
      WRITE:/ wa_output-vbeln COLOR COL_POSITIVE,
              wa_output-posnr COLOR COL_POSITIVE,
              wa_output-matnr COLOR COL_POSITIVE,
              wa_output-maktx COLOR COL_POSITIVE,
              wa_output-erdat COLOR COL_POSITIVE,
              wa_output-ernam COLOR COL_POSITIVE.
    ENDIF.
  ENDLOOP.
## Relatório de Seleção de Documentos de Venda
Este relatório ABAP, identificado como `Z_VENDA_DOCUMENTO`, é utilizado para selecionar informações relacionadas a documentos de venda. Ele extrai dados de tabelas principais, como `VBAK`, `VBAP`, e `MAKT`, relacionando-as com base em critérios fornecidos pelo usuário.

### Estruturas de Dados
O relatório utiliza a seguinte estrutura de dados:
- `ty_output`: Estrutura que armazena informações combinadas dos documentos de venda.
  - `vbeln`: Número do documento de venda (tabela VBAK).
  - `vbelv`: Data de expedicao do documento (tabela VBAK).
  - `posnr`: Número da posição do item (tabela VBAP).
  - `matnr`: Número do material (tabela VBAP).
  - `maktx`: Descrição do material (tabela MAKT).
  - `erdat`: Data de criação do documento (tabela VBAK).
  - `ernam`: Nome do criador do documento (tabela VBAK).

### Parâmetros de Entrada
- `p_type`: Seleção de INNER JOIN ou FOR ALL ENTRIES.
- `p_all`: Opção para selecionar todos os registros.

### Funcionamento
1. O relatório começa selecionando todos os registros da tabela `VBAK` (documentos de venda).

2. Com base na seleção feita pelo usuário, ele executa uma das duas ações:
   - **Inner Join (p_type = 'I')**: Realiza um INNER JOIN entre as tabelas `VBAK`, `VBAP`, e `MAKT`, combinando informações relevantes.
   - **For All Entries (p_type ≠ 'I')**: Seleciona registros da tabela `VBAP` com base na tabela `VBAK` e, em seguida, seleciona informações da tabela `MAKT`.

3. Os resultados combinados são armazenados na tabela interna `it_output`.

4. Os dados são exibidos na tela, com cores alternadas para melhor legibilidade.
#### REPORT

REPORT Z_VENDA_DOCUMENTO.

TYPES: BEGIN OF ty_output,
         vbeln TYPE vbak-vbeln,
         vbelv TYPE vbak-vbelv,
         posnr TYPE vbap-posnr,
         matnr TYPE vbap-matnr,
         maktx TYPE makt-maktx,
         erdat TYPE vbak-erdat,
         ernam TYPE vbak-ernam,
       END OF ty_output.

DATA: it_output TYPE TABLE OF ty_output,
      wa_output TYPE ty_output.

DATA: it_vbak TYPE TABLE OF vbak,
      wa_vbak TYPE vbak.

DATA: it_vbap TYPE TABLE OF vbap,
      wa_vbap TYPE vbap.

DATA: it_makt TYPE TABLE OF makt,
      wa_makt TYPE makt.

PARAMETERS: p_type TYPE c RADIOBUTTON GROUP g1 DEFAULT 'I' USER-COMMAND sel,
            p_all  TYPE c RADIOBUTTON GROUP g1.

START-OF-SELECTION.

  SELECT * FROM vbak INTO TABLE it_vbak.

  IF p_type = 'I'. "Usando INNER JOIN
    SELECT a~vbeln a~vbelv a~erdat a~ernam
           b~posnr b~matnr c~maktx
      INTO TABLE it_output
      FROM vbak AS a
           INNER JOIN vbap AS b ON a~vbeln = b~vbeln
           LEFT JOIN makt AS c ON b~matnr = c~matnr.
  ELSE. "Usando FOR ALL ENTRIES
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
              wa_output-vbelv COLOR COL_BACKGROUND,
              wa_output-posnr COLOR COL_BACKGROUND,
              wa_output-matnr COLOR COL_BACKGROUND,
              wa_output-maktx COLOR COL_BACKGROUND,
              wa_output-erdat COLOR COL_BACKGROUND,
              wa_output-ernam COLOR COL_BACKGROUND.
    ELSE.
      WRITE:/ wa_output-vbeln COLOR COL_POSITIVE,
              wa_output-vbelv COLOR COL_POSITIVE,
              wa_output-posnr COLOR COL_POSITIVE,
              wa_output-matnr COLOR COL_POSITIVE,
              wa_output-maktx COLOR COL_POSITIVE,
              wa_output-erdat COLOR COL_POSITIVE,
              wa_output-ernam COLOR COL_POSITIVE.
    ENDIF.
  ENDLOOP.


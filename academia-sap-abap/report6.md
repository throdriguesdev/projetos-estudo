## Seleção de Materiais
Este relatório ABAP é responsável por selecionar registros de três tabelas: MARA, MAKT e T134T. Ele permite ao usuário especificar números de material (MATNR) e tipos de material (MTART) como critérios de seleção. Em seguida, ele exibe as informações correspondentes na tela.

### Tabelas Usadas
- MARA: Tabela principal de materiais com informações gerais.
- MAKT: Tabela de descrições de materiais.
- T134T: Tabela de descrições de tipos de materiais.

### Seleção de Registros
Os critérios de seleção são:
- Números de material (MATNR) fornecidos na seleção `s_matnr`.
- Tipos de material (MTART) fornecidos na seleção `s_mtart`.

### Inner Joins
O código faz uso de junções internas (INNER JOIN) para combinar as três tabelas com base em chaves relacionadas. Ele garante que apenas registros correspondentes sejam selecionados.

### Resultados
Os resultados da seleção são armazenados na tabela interna `result` e, em seguida, exibidos na tela. Os campos exibidos incluem:
- MATNR: Número do material da tabela MARA.
- MTART: Tipo do material da tabela MARA.
- MAKTX: Texto do material da tabela MAKT.
- MTBEZ: Texto do tipo de material da tabela T134T.

Este relatório é útil para extrair informações sobre materiais e seus tipos correspondentes do sistema SAP.

## Observações
Certifique-se de fornecer os critérios de seleção (MATNR e MTART) para obter resultados específicos.

#####

REPORT ZSELECAO_MATERIAL.

* Declaração das tabelas de seleção
TABLES: MARA, MAKT, T134T.

* Declaração das variáveis para seleção
SELECT-OPTIONS: s_matnr FOR MARA-MATNR,       " Numero do Material
                s_mtart FOR MARA-MTART.      " Tipo do Material

START-OF-SELECTION.

  SELECT A~MATNR A~MTART B~MAKTX C~MTBEZ
    INTO TABLE @DATA(result)
    FROM MARA AS A
    INNER JOIN MAKT AS B ON A~MATNR = B~MATNR
    INNER JOIN T134T AS C ON A~MTART = C~MTART
    WHERE A~MATNR IN @s_matnr
    AND A~MTART IN @s_mtart
    AND B~SPRAS = SY-LANGU.

  LOOP AT result INTO DATA(line).
    WRITE: / line-MATNR, 'NUMERO DO MATERIAL TABELA MARA',
           line-MTART, 'TIPO DO MATERIAL TABELA MARA',
           line-MAKTX, 'TEXTO DO MATERIAL TABELA MAKT',
           line-MTBEZ, 'TEXTO DO TIPO DO MATERIAL TABELA T134T'.
  ENDLOOP.


## Calculadora ABAP Simples

### Visão Geral
Este é um programa ABAP simples que atua como uma calculadora básica. Ele permite que você realize várias operações matemáticas com dois valores de entrada, como soma, subtração, multiplicação e divisão, bem como algumas operações adicionais, como raiz quadrada, cálculo de resto da divisão, verificar se um número é par ou ímpar. Você pode selecionar a operação desejada na tela de seleção.

### Como Usar
1. Execute o programa ABAP `ZABAP22R_EXEC03` no sistema SAP.
2. Na tela de seleção, insira dois valores numéricos em `P_VALOR1` e `P_VALOR2`.
3. Selecione a operação desejada nos botões de opção na tela de seleção.
4. Clique em "Executar" para calcular o resultado.
5. O resultado da operação selecionada será exibido na tela.

### Funcionalidades
- Soma
- Subtração
- Multiplicação
- Divisão
- Raiz Quadrada
- Resto da Divisão
- Verificação de Número Par
- Verificação de Número Ímpar

### Notas
- Este programa demonstra como criar uma interface simples de seleção e realizar cálculos básicos em ABAP.
- É uma ótima maneira de entender como as operações condicionais funcionam em ABAP.
- O código pode ser utilizado como referência para projetos ABAP mais avançados.

#### CÓDIGO
REPORT ZABAP22R_EXEC03.
SELECTION-SCREEN BEGIN OF BLOCK b01 WITH FRAME.
PARAMETERS: P_VALOR1 TYPE i.
SELECTION-SCREEN END of block b01.

SELECTION-SCREEN BEGIN OF BLOCK b02 WITH FRAME.
PARAMETERS: P_VALOR2 TYPE i VISIBLE LENGTH 10.
SELECTION-SCREEN END OF BLOCK b02.

SELECTION-SCREEN BEGIN OF BLOCK b03 WITH FRAME.
PARAMETERS: P_SOMA type flag RADIOBUTTON GROUP gp1,
            P_SUB  type flag RADIOBUTTON GROUP gp1,
            P_MLT  type flag RADIOBUTTON GROUP gp1,
            P_DIV  type flag RADIOBUTTON GROUP gp1,
            P_RAIZ type flag RADIOBUTTON GROUP gp1,
            P_RESTO type flag RADIOBUTTON GROUP gp1,
            P_IMPAR type flag RADIOBUTTON GROUP gp1,
            P_PAR type flag RADIOBUTTON GROUP gp1.
SELECTION-SCREEN END of BLOCK b03.



SELECTION-SCREEN BEGIN OF BLOCK b04 WITH FRAME.
  PARAMETERS: p_VERM type flag RADIOBUTTON GROUP gp2,
              p_AZUL type flag RADIOBUTTON GROUP gp2.
  SELECTION-SCREEN END of block b04.

AT SELECTION-SCREEN.
  IF ( P_PAR IS NOT INITIAL OR P_IMPAR IS NOT INITIAL OR P_RAIZ IS NOT INITIAL ) AND ( P_VALOR2 IS NOT INITIAL ).
    CLEAR P_VALOR2.
    MESSAGE 'Apenas o primeiro valor será considerado para as opções PAR, ÍMPAR e RAIZ' TYPE 'I'.
  ENDIF.

START-OF-SELECTION.
DATA: v_RES_SOMA type p DECIMALS 2,
      v_RES_SUB type p DECIMALS 2,
      v_RES_MLT type p DECIMALS 2,
      v_RES_DIV type p DECIMALS 2,
      V_RES TYPE P DECIMALS 2,
      V_RES_RAIZ type p decimals 2,
      V_RES_RESTO type i,
      V_RES_IMPAR type string VALUE ''.

IF P_SOMA IS NOT INITIAL.
  V_RES_SOMA = P_VALOR1 + P_VALOR2.
  WRITE: / 'RESULTADO DA SOMA: ', V_RES_SOMA.
ELSEIF P_SUB IS NOT INITIAL.
  V_RES_SUB = P_VALOR1 - P_VALOR2.
  WRITE: / 'RESULTADO DA SUBTRAÇÃO: ', V_RES_SUB.
ELSEIF P_DIV IS NOT INITIAL.
  V_RES_DIV = P_VALOR1 / P_VALOR2.
  WRITE: / 'RESULTADO DA DIVISÃO: ', V_RES_DIV.
ELSEIF P_MLT IS NOT INITIAL.
  V_RES_MLT = P_VALOR1 * p_valor2.
  WRITE: / 'RESULTADO DA MULTIPLICAÇÃO: ', V_RES_MLT.
ELSEIF p_RAIZ IS NOT INITIAL.
  V_RES_RAIZ = sqrt( P_VALOR1 ).
  WRITE: / 'RESULTADO DA RAIZ:', V_RES_RAIZ.
ELSEIF P_RESTO IS NOT INITIAL.
  V_RES_RESTO = P_VALOR1 MOD P_VALOR2.
  WRITE: / 'RESTO DA DIVISÃO: ', V_RES_RESTO.

ELSEIF P_PAR IS NOT INITIAL.
  IF P_VALOR1 MOD 2 = 0.
    V_RES_IMPAR = 'VERDADEIRO'. " O número é par e o usuário selecionou par.
  ELSE.
    V_RES_IMPAR = 'FALSO'. " O número é ímpar, mas o usuário selecionou par.
  ENDIF.
  WRITE: / 'O NÚMERO É PAR? ', V_RES_IMPAR.

ELSEIF P_IMPAR IS NOT INITIAL.
  IF P_VALOR1 MOD 2 <> 0.
    V_RES_IMPAR = 'VERDADEIRO'. " O número é ímpar e o usuário selecionou ímpar.
  ELSE.
    V_RES_IMPAR = 'FALSO'. " O número é par, mas o usuário selecionou ímpar.
  ENDIF.
  WRITE: / 'O NÚMERO É ÍMPAR? ', V_RES_IMPAR.
ENDIF.
    
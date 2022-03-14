/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphicdesign;

import javax.swing.*;
import javax.swing.text.DefaultEditorKit;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

import static graphicdesign.operatorsID.DEFAULT;

enum operatorsID {DEFAULT, PLUS, MINUS, MULTIPLY, DIVIDE, FACTORIAL, SQRT, POWER, MODULO}

/**
 *
 * @author xsafar26
 * @author xskrab12
 * @author xkocia19
 * @author xcafou01
 */
public class MainJFrame extends javax.swing.JFrame {
    /**
     * Creates new form MainJFrame
     */
    private double value1 = 0.0;
    private double value2 = 0.0;
    private boolean operatorSet = false;
    private boolean decimalVal1 = false;
    private boolean decimalVal2 = false;
    private boolean negative = false;
    private int digitsVal1 = 0;
    private int digitsVal2 = 0;
    private operatorsID operatorID = DEFAULT;
    public MainJFrame() {
        initComponents();
        setLocationRelativeTo(null);
        jText.setText("   ");
    }
    public String Round(double value){
        int counter = 0;
        DecimalFormat df = new DecimalFormat("#.#");
        String tmp = new BigDecimal(value).setScale(getScale(value), RoundingMode.HALF_UP).toPlainString();
        StringBuilder sb = new StringBuilder(tmp);
        while(sb.charAt(sb.length()-1) == '0'){
            sb.deleteCharAt(sb.length()-1);
            counter++;
        }
        if(sb.charAt(sb.length()-1)=='.'){
            sb.deleteCharAt(sb.length()-1);
        }
        return sb.toString();
    }
    public static int getScale(double value){
        BigDecimal bd = new BigDecimal(value);
        if (bd.intValue() == 0) {
            return 15;
        }
        int scale = 15 - (int)Math.log10(bd.intValue());
        return scale;
    }
    public void DeleteScreen(){
        jText.setText("   ");
        value1 = 0.0;
        value2 = 0.0;
        operatorID = DEFAULT;
        operatorSet = false;
        decimalVal1 = false;
        decimalVal2 = false;
        digitsVal1 = 0;
        digitsVal2 = 0;
    }
    public void printf(String x){
        String y = jText.getText();
        StringBuilder sb = new StringBuilder(y);
        sb.append(x);
        jText.setText(sb.toString());
    }
    public void printOperator(String x){
        String y = jText.getText();
        StringBuilder sb = new StringBuilder(y);
        if(y.charAt(y.length()-1) == ' '){
            if(operatorSet){
                sb.deleteCharAt(y.length()-1);
                sb.deleteCharAt(y.length()-2);
                sb.deleteCharAt(y.length()-3);
            }else if (x.equals("-")){
                sb.deleteCharAt(y.length()-1);
                sb.deleteCharAt(y.length()-2);
                sb.deleteCharAt(y.length()-3);
                sb.append(" " + x + " ");
                jText.setText(sb.toString());
                negative = true;
                return;
            }else{
                return;
            }
        }
        sb.append("\n" + x + " ");
        jText.setText(sb.toString());
    }
    public void BackSpace(){
        String y = jText.getText();
        StringBuilder sb = new StringBuilder(y);
            if(y.length() != 3){
                char[] operators = {'+','-','*','/', '%'};
                char[] numbers = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
                char c = y.charAt(y.length()-2);
                for (char operator : operators) {
                    if (c == operator) {
                        jText.replaceRange(null, y.length() - 3, y.length());
                        operatorID = DEFAULT;
                        operatorSet = false;
                        break;
                    }
                }
                c = y.charAt(y.length()-1);
                for (char number : numbers) {
                    if (c == number) {
                        if(!operatorSet){
                            if (!decimalVal1) {
                                value1 -= ((double)c-'0');
                                value1 /= 10.0;
                            } else {
                                value1 -= (double)(c-'0')/Math.pow(10.0, digitsVal1--);
                            }
                        }else{
                            if (!decimalVal2) {
                                value2 -= ((double)c-'0');
                                value2 /= 10;
                            } else {
                                value2 -= (double)(c-'0')/Math.pow(10.0, digitsVal2--);
                            }
                        }
                        jText.replaceRange(null, y.length() - 1, y.length());
                        break;
                    }
                }
                if(c == ','){
                    if(!operatorSet){
                        decimalVal1 = false;
                    }else{
                        decimalVal2 = false;
                    }
                    jText.replaceRange(null, y.length() - 1, y.length());
                }
            }else if(y.charAt(y.length()-2) == '-'){
                jText.setText("   ");
                return;
            }
    }
    public void KeyTracker(char c){
        char[] numbers = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
        for (char number : numbers) {
            if (c == number) {
                printf(Character.toString(c));
                countValue(((double)c-'0'));
                break;
            }
        }
        switch (c) {
            case '+':
                printOperator(Character.toString(c));
                operatorSet = true;
                operatorID = operatorsID.PLUS;
                break;
            case '-':
                printOperator(Character.toString(c));
                if(!negative){
                operatorSet = true;
                operatorID = operatorsID.MINUS;
                }
                break;
            case '*':
                printOperator(Character.toString(c));
                operatorSet = true;
                operatorID = operatorsID.MULTIPLY;
                break;
            case '/':
                printOperator("÷");
                operatorSet = true;
                operatorID = operatorsID.DIVIDE;
                break;
            case '%':
                printOperator(Character.toString(c));
                operatorSet = true;
                operatorID = operatorsID.MODULO;
                break;
            case 'c':
            case 'C':
            case KeyEvent.VK_DELETE:
                DeleteScreen();
                break;
            case '!':
                //fucknce fac
                break;
            case '=':
            case KeyEvent.VK_ENTER:
                Equals();
                break;
            case KeyEvent.VK_BACK_SPACE:
                BackSpace();
                break;
            case ',':
            case '.':
                printf(",");
                if(!operatorSet){
                    decimalVal1 = true;
                }else{
                    decimalVal2 = true;
                }
                break;
            default:
                break;
        }
    }
    public void countValue(double x) {
        if(!operatorSet){
            if (!decimalVal1) {
                value1 = value1 * 10.0 + x;
            } else {
                value1 += x/Math.pow(10, ++digitsVal1);
            }
            System.out.println(value1);
        }else{
            if (!decimalVal2) {
                value2 = value2 * 10.0 + x;
            } else {
                value2 += x/Math.pow(10.0, ++digitsVal2);
            }
            System.out.println(value2);
        }
    }
    public void Equals(){
        String output = "";
        if(negative){
            value1 *= -1;
        }
        switch(operatorID){
            case PLUS:
                //funkce plus
                value1 += value2; //jen kvůli testování (bude nahrazeno funkcí z knihovny)
                value2 = 0;
                if(digitsVal1 > digitsVal2){
                    digitsVal2 = 0;
                    decimalVal2 = false;
                    operatorID = DEFAULT;
                    operatorSet = false;
                }else{
                    digitsVal1 = digitsVal2;
                    digitsVal2 = 0;
                    decimalVal2 = false;
                    operatorID = DEFAULT;
                    operatorSet = false;
                }
                System.out.println(value1);
                output = Round(value1);
                output = output.replace(".", ",");
                System.out.println(output);
                break;
            case MINUS:
                //funkce minus
                value1 -= value2; //jen kvůli testování
                output = String.format("%7.8f", value1);
                break;
            case DIVIDE:
                value1 /= value2; //jen kvůli testování
                output = String.format("%7.8f", value1);
                //funkce plus
                break;
            case MULTIPLY:
                value1 *= value2; //jen kvůli testování
                output = String.format("%7.8f", value1);
                //funkce multiplay
                break;
            case MODULO:
                //funkce modulo
                value1 %= value2; //jen kvůli testování
                output = String.format("%7.8f", value1);
                break;
            default:
                return;
        }
        jText.setText("   " + output);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btnInfo = new javax.swing.JButton();
        DarkMode = new javax.swing.JToggleButton();
        btnC = new javax.swing.JButton();
        btnBraL = new javax.swing.JButton();
        btnBraR = new javax.swing.JButton();
        brnMod = new javax.swing.JButton();
        btnExp = new javax.swing.JButton();
        btnSqrt = new javax.swing.JButton();
        btnFac = new javax.swing.JButton();
        btnDiv = new javax.swing.JButton();
        btnMul = new javax.swing.JButton();
        btn9 = new javax.swing.JButton();
        btn8 = new javax.swing.JButton();
        btn7 = new javax.swing.JButton();
        btn4 = new javax.swing.JButton();
        btn1 = new javax.swing.JButton();
        btn0 = new javax.swing.JButton();
        btn5 = new javax.swing.JButton();
        btn6 = new javax.swing.JButton();
        btnMinus = new javax.swing.JButton();
        btnPlus = new javax.swing.JButton();
        btnEQ = new javax.swing.JButton();
        btn3 = new javax.swing.JButton();
        btn2 = new javax.swing.JButton();
        btnDec = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jText = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Kalkulačka");
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));

        btnInfo.setText("INFO");
        btnInfo.setFocusable(false);
        btnInfo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInfoActionPerformed(evt);
            }
        });

        DarkMode.setText("Dark Mode");
        DarkMode.setFocusable(false);
        DarkMode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DarkModeActionPerformed(evt);
            }
        });

        btnC.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnC.setText("C");
        btnC.setFocusable(false);
        btnC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCActionPerformed(evt);
            }
        });

        btnBraL.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnBraL.setText("(");
        btnBraL.setFocusable(false);
        btnBraL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBraLActionPerformed(evt);
            }
        });

        btnBraR.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnBraR.setText(")");
        btnBraR.setFocusable(false);
        btnBraR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBraRActionPerformed(evt);
            }
        });

        brnMod.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        brnMod.setText("%");
        brnMod.setFocusable(false);
        brnMod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                brnModActionPerformed(evt);
            }
        });

        btnExp.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnExp.setText("^");
        btnExp.setFocusable(false);
        btnExp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExpActionPerformed(evt);
            }
        });

        btnSqrt.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnSqrt.setText("√");
        btnSqrt.setFocusable(false);
        btnSqrt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSqrtActionPerformed(evt);
            }
        });

        btnFac.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnFac.setText("!");
        btnFac.setFocusable(false);
        btnFac.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFacActionPerformed(evt);
            }
        });

        btnDiv.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnDiv.setText("÷");
        btnDiv.setFocusable(false);
        btnDiv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDivActionPerformed(evt);
            }
        });

        btnMul.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnMul.setText("*");
        btnMul.setFocusable(false);
        btnMul.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMulActionPerformed(evt);
            }
        });

        btn9.setBackground(new java.awt.Color(210, 210, 210));
        btn9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btn9.setText("9");
        btn9.setFocusable(false);
        btn9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn9ActionPerformed(evt);
            }
        });

        btn8.setBackground(new java.awt.Color(210, 210, 210));
        btn8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btn8.setText("8");
        btn8.setFocusable(false);
        btn8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn8ActionPerformed(evt);
            }
        });

        btn7.setBackground(new java.awt.Color(210, 210, 210));
        btn7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btn7.setText("7");
        btn7.setFocusable(false);
        btn7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn7ActionPerformed(evt);
            }
        });

        btn4.setBackground(new java.awt.Color(210, 210, 210));
        btn4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btn4.setText("4");
        btn4.setFocusable(false);
        btn4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn4ActionPerformed(evt);
            }
        });

        btn1.setBackground(new java.awt.Color(210, 210, 210));
        btn1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btn1.setText("1");
        btn1.setFocusable(false);
        btn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn1ActionPerformed(evt);
            }
        });

        btn0.setBackground(new java.awt.Color(210, 210, 210));
        btn0.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btn0.setText("0");
        btn0.setFocusable(false);
        btn0.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn0ActionPerformed(evt);
            }
        });

        btn5.setBackground(new java.awt.Color(210, 210, 210));
        btn5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btn5.setText("5");
        btn5.setFocusable(false);
        btn5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn5ActionPerformed(evt);
            }
        });

        btn6.setBackground(new java.awt.Color(210, 210, 210));
        btn6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btn6.setText("6");
        btn6.setFocusable(false);
        btn6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn6ActionPerformed(evt);
            }
        });

        btnMinus.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnMinus.setText("-");
        btnMinus.setFocusable(false);
        btnMinus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMinusActionPerformed(evt);
            }
        });

        btnPlus.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnPlus.setText("+");
        btnPlus.setFocusable(false);
        btnPlus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPlusActionPerformed(evt);
            }
        });

        btnEQ.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnEQ.setText("=");
        btnEQ.setFocusable(false);
        btnEQ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEQActionPerformed(evt);
            }
        });

        btn3.setBackground(new java.awt.Color(210, 210, 210));
        btn3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btn3.setText("3");
        btn3.setFocusable(false);
        btn3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn3ActionPerformed(evt);
            }
        });

        btn2.setBackground(new java.awt.Color(210, 210, 210));
        btn2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btn2.setText("2");
        btn2.setFocusable(false);
        btn2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn2ActionPerformed(evt);
            }
        });

        btnDec.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnDec.setText(",");
        btnDec.setFocusable(false);
        btnDec.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDecActionPerformed(evt);
            }
        });

        jText.setEditable(false);
        jText.setColumns(12);
        jText.setFont(new java.awt.Font("Quicksand", 0, 18)); // NOI18N
        jText.setRows(2);
        jText.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextKeyTyped(evt);
            }
        });
        Action beep = jText.getActionMap().get(DefaultEditorKit.deletePrevCharAction);
        beep.setEnabled(false);
        jScrollPane1.setViewportView(jText);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(btnInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(DarkMode, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnC, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnBraL, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnBraR, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(brnMod, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnExp, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnSqrt, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnFac, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnDiv, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(btn7, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn8, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn9, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnMul, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btn4, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn5, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn6, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnMinus, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btn0, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnDec, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnEQ, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btn1, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn2, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn3, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnPlus, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnInfo)
                    .addComponent(DarkMode))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnC)
                    .addComponent(btnBraL)
                    .addComponent(btnBraR)
                    .addComponent(brnMod))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnExp)
                    .addComponent(btnSqrt)
                    .addComponent(btnFac)
                    .addComponent(btnDiv))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnMul)
                    .addComponent(btn9, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn8)
                    .addComponent(btn7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn6)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btn4)
                        .addComponent(btn5)
                        .addComponent(btnMinus)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn1)
                    .addComponent(btnPlus)
                    .addComponent(btn3)
                    .addComponent(btn2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn0)
                    .addComponent(btnEQ)
                    .addComponent(btnDec))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn0ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn0ActionPerformed
        printf("0");
        countValue(0.0);
    }//GEN-LAST:event_btn0ActionPerformed

    private void btn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn1ActionPerformed
        printf("1");
        countValue(1.0);
    }//GEN-LAST:event_btn1ActionPerformed

    private void btn2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn2ActionPerformed
        printf("2");
        countValue(2.0);
    }//GEN-LAST:event_btn2ActionPerformed

    private void btn3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn3ActionPerformed
        printf("3");
        countValue(3.0);
    }//GEN-LAST:event_btn3ActionPerformed

    private void btn4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn4ActionPerformed
        printf("4");
        countValue(4.0);
    }//GEN-LAST:event_btn4ActionPerformed

    private void btn5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn5ActionPerformed
        printf("5");
        countValue(5.0);
    }//GEN-LAST:event_btn5ActionPerformed

    private void btn6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn6ActionPerformed
        printf("6");
        countValue(6.0);
    }//GEN-LAST:event_btn6ActionPerformed

    private void btn7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn7ActionPerformed
        printf("7");
        countValue(7.0);
    }//GEN-LAST:event_btn7ActionPerformed

    private void btn8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn8ActionPerformed
        printf("8");
        countValue(8.0);
    }//GEN-LAST:event_btn8ActionPerformed

    private void btn9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn9ActionPerformed
        printf("9");
        countValue(9.0);
    }//GEN-LAST:event_btn9ActionPerformed

    private void btnPlusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPlusActionPerformed
        printOperator("+");
        operatorSet = true;
        operatorID = operatorsID.PLUS;
        System.out.println(operatorID);
    }//GEN-LAST:event_btnPlusActionPerformed

    private void btnMinusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMinusActionPerformed
        printOperator("-");
        if(!negative){
            operatorID = operatorsID.MINUS;
            operatorSet = true;
        }
    }//GEN-LAST:event_btnMinusActionPerformed

    private void btnMulActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMulActionPerformed
        printOperator("*");
        operatorSet = true;
        operatorID = operatorsID.MULTIPLY;
    }//GEN-LAST:event_btnMulActionPerformed

    private void btnDivActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDivActionPerformed
        printOperator("÷");
        operatorSet = true;
        operatorID = operatorsID.DIVIDE;
    }//GEN-LAST:event_btnDivActionPerformed

    private void btnEQActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEQActionPerformed
        Equals();
    }//GEN-LAST:event_btnEQActionPerformed

    private void btnDecActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDecActionPerformed
        printf(",");
        if(operatorSet){
            decimalVal2 = true;
        }else{
            decimalVal1 = true;
        }

    }//GEN-LAST:event_btnDecActionPerformed

    private void btnFacActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFacActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnFacActionPerformed

    private void btnSqrtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSqrtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSqrtActionPerformed

    private void btnExpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExpActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnExpActionPerformed

    private void brnModActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_brnModActionPerformed
        printOperator("%");
    }//GEN-LAST:event_brnModActionPerformed

    private void btnBraRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBraRActionPerformed
        printf(")");
    }//GEN-LAST:event_btnBraRActionPerformed

    private void btnBraLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBraLActionPerformed
       printf("(");
    }//GEN-LAST:event_btnBraLActionPerformed

    private void btnCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCActionPerformed
        DeleteScreen();
    }//GEN-LAST:event_btnCActionPerformed

    private void DarkModeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DarkModeActionPerformed
        if(DarkMode.isSelected()){
            jPanel1.setBackground(new java.awt.Color(51, 51, 51));
            jText.setBackground(new java.awt.Color(0, 0, 0)); 
            jText.setForeground(new java.awt.Color(255, 255, 255));
            btn0.setBackground(new java.awt.Color(45, 45, 45));
            btn0.setForeground(new java.awt.Color(255, 255, 255));
            btn1.setBackground(new java.awt.Color(45, 45, 45));
            btn1.setForeground(new java.awt.Color(255, 255, 255));
            btn2.setBackground(new java.awt.Color(45, 45, 45));
            btn2.setForeground(new java.awt.Color(255, 255, 255));
            btn3.setBackground(new java.awt.Color(45, 45, 45));
            btn3.setForeground(new java.awt.Color(255, 255, 255));
            btn4.setBackground(new java.awt.Color(45, 45, 45));
            btn4.setForeground(new java.awt.Color(255, 255, 255));
            btn5.setBackground(new java.awt.Color(45, 45, 45));
            btn5.setForeground(new java.awt.Color(255, 255, 255));
            btn6.setBackground(new java.awt.Color(45, 45, 45));
            btn6.setForeground(new java.awt.Color(255, 255, 255));
            btn7.setBackground(new java.awt.Color(45, 45, 45));
            btn7.setForeground(new java.awt.Color(255, 255, 255));
            btn8.setBackground(new java.awt.Color(45, 45, 45));
            btn8.setForeground(new java.awt.Color(255, 255, 255));
            btn9.setBackground(new java.awt.Color(45, 45, 45));
            btn9.setForeground(new java.awt.Color(255, 255, 255));
            btnDec.setBackground(new java.awt.Color(15, 15, 15));
            btnDec.setForeground(new java.awt.Color(255, 255, 255));
            btnEQ.setBackground(new java.awt.Color(15, 15, 15));
            btnEQ.setForeground(new java.awt.Color(255, 255, 255));
            btnPlus.setBackground(new java.awt.Color(15, 15, 15));
            btnPlus.setForeground(new java.awt.Color(255, 255, 255));
            btnMinus.setBackground(new java.awt.Color(15, 15, 15));
            btnMinus.setForeground(new java.awt.Color(255, 255, 255));
            btnMul.setBackground(new java.awt.Color(15, 15, 15));
            btnMul.setForeground(new java.awt.Color(255, 255, 255));
            btnDiv.setBackground(new java.awt.Color(15, 15, 15));
            btnDiv.setForeground(new java.awt.Color(255, 255, 255));
            btnFac.setBackground(new java.awt.Color(15, 15, 15));
            btnFac.setForeground(new java.awt.Color(255, 255, 255));
            btnBraR.setBackground(new java.awt.Color(15, 15, 15));
            btnBraR.setForeground(new java.awt.Color(255, 255, 255));
            btnBraL.setBackground(new java.awt.Color(15, 15, 15));
            btnBraL.setForeground(new java.awt.Color(255, 255, 255));
            btnExp.setBackground(new java.awt.Color(15, 15, 15));
            btnExp.setForeground(new java.awt.Color(255, 255, 255));
            btnSqrt.setBackground(new java.awt.Color(15, 15, 15));
            btnSqrt.setForeground(new java.awt.Color(255, 255, 255));
            btnInfo.setBackground(new java.awt.Color(15, 15, 15));
            btnInfo.setForeground(new java.awt.Color(255, 255, 255));
            DarkMode.setBackground(new java.awt.Color(15, 15, 15));
            DarkMode.setForeground(new java.awt.Color(255, 255, 255));
            DarkMode.setText("Light Mode");
            btnC.setBackground(new java.awt.Color(15, 15, 15));
            btnC.setForeground(new java.awt.Color(255, 255, 255));
            brnMod.setBackground(new java.awt.Color(15, 15, 15));
            brnMod.setForeground(new java.awt.Color(255, 255, 255));
            
        }else{
            jPanel1.setBackground(new java.awt.Color(204, 204, 204));
            jText.setBackground(new java.awt.Color(255,255,255)); //wtf
            jText.setForeground(new java.awt.Color(0,0,0));
            btn0.setBackground(new java.awt.Color(210, 210, 210));
            btn0.setForeground(new java.awt.Color(0,0,0));
            btn1.setBackground(new java.awt.Color(210, 210, 210));
            btn1.setForeground(new java.awt.Color(0,0,0));
            btn2.setBackground(new java.awt.Color(210, 210, 210));
            btn2.setForeground(new java.awt.Color(0,0,0));
            btn3.setBackground(new java.awt.Color(210, 210, 210));
            btn3.setForeground(new java.awt.Color(0,0,0));
            btn4.setBackground(new java.awt.Color(210, 210, 210));
            btn4.setForeground(new java.awt.Color(0,0,0));
            btn5.setBackground(new java.awt.Color(210, 210, 210));
            btn5.setForeground(new java.awt.Color(0,0,0));
            btn6.setBackground(new java.awt.Color(210, 210, 210));
            btn6.setForeground(new java.awt.Color(0,0,0));
            btn7.setBackground(new java.awt.Color(210, 210, 210));
            btn7.setForeground(new java.awt.Color(0,0,0));
            btn8.setBackground(new java.awt.Color(210, 210, 210));
            btn8.setForeground(new java.awt.Color(0,0,0));
            btn9.setBackground(new java.awt.Color(210, 210, 210));
            btn9.setForeground(new java.awt.Color(0,0,0));
            btnDec.setBackground(new java.awt.Color(180, 180, 180));
            btnDec.setForeground(new java.awt.Color(0,0,0));
            btnEQ.setBackground(new java.awt.Color(180, 180, 180));
            btnEQ.setForeground(new java.awt.Color(0,0,0));
            btnPlus.setBackground(new java.awt.Color(180, 180, 180));
            btnPlus.setForeground(new java.awt.Color(0,0,0));
            btnMinus.setBackground(new java.awt.Color(180, 180, 180));
            btnMinus.setForeground(new java.awt.Color(0,0,0));
            btnMul.setBackground(new java.awt.Color(180, 180, 180));
            btnMul.setForeground(new java.awt.Color(0,0,0));
            btnDiv.setBackground(new java.awt.Color(180, 180, 180));
            btnDiv.setForeground(new java.awt.Color(0,0,0));
            btnFac.setBackground(new java.awt.Color(180, 180, 180));
            btnFac.setForeground(new java.awt.Color(0,0,0));
            btnBraR.setBackground(new java.awt.Color(180, 180, 180));
            btnBraR.setForeground(new java.awt.Color(0,0,0));
            btnBraL.setBackground(new java.awt.Color(180, 180, 180));
            btnBraL.setForeground(new java.awt.Color(0,0,0));
            btnExp.setBackground(new java.awt.Color(180, 180, 180));
            btnExp.setForeground(new java.awt.Color(0,0,0));
            btnSqrt.setBackground(new java.awt.Color(180, 180, 180));
            btnSqrt.setForeground(new java.awt.Color(0,0,0));
            btnInfo.setBackground(new java.awt.Color(180, 180, 180));
            btnInfo.setForeground(new java.awt.Color(0,0,0));
            DarkMode.setBackground(new java.awt.Color(180, 180, 180));
            DarkMode.setForeground(new java.awt.Color(0,0,0));
            DarkMode.setText("Dark Mode");
            btnC.setBackground(new java.awt.Color(180, 180, 180));
            btnC.setForeground(new java.awt.Color(0,0,0));
            brnMod.setBackground(new java.awt.Color(180, 180, 180));
            brnMod.setForeground(new java.awt.Color(0,0,0));
            
        }
    }//GEN-LAST:event_DarkModeActionPerformed

    private void btnInfoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInfoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnInfoActionPerformed
    private void jTextKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnDivActionPerformed
        KeyTracker(evt.getKeyChar());
    }//GEN-LAST:event_btnDivActionPerformed
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainJFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton DarkMode;
    private javax.swing.JButton brnMod;
    private javax.swing.JButton btn0;
    private javax.swing.JButton btn1;
    private javax.swing.JButton btn2;
    private javax.swing.JButton btn3;
    private javax.swing.JButton btn4;
    private javax.swing.JButton btn5;
    private javax.swing.JButton btn6;
    private javax.swing.JButton btn7;
    private javax.swing.JButton btn8;
    private javax.swing.JButton btn9;
    private javax.swing.JButton btnBraL;
    private javax.swing.JButton btnBraR;
    private javax.swing.JButton btnC;
    private javax.swing.JButton btnDec;
    private javax.swing.JButton btnDiv;
    private javax.swing.JButton btnEQ;
    private javax.swing.JButton btnExp;
    private javax.swing.JButton btnFac;
    private javax.swing.JButton btnInfo;
    private javax.swing.JButton btnMinus;
    private javax.swing.JButton btnMul;
    private javax.swing.JButton btnPlus;
    private javax.swing.JButton btnSqrt;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jText;
    // End of variables declaration//GEN-END:variables
}

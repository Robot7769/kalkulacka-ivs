/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphicdesign;

import library.MathLib; //Matematická knihovna

import javax.swing.*;
import javax.swing.text.DefaultEditorKit;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.math.RoundingMode;

import static graphicdesign.operatorsID.*;

enum operatorsID {DEFAULT, PLUS, MINUS, MULTIPLY, DIVIDE, FACTORIAL, POWER, SQRT, NPOWER, NSQRT, MODULO, SIN, COS, TAN}

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
    private MathLib mathLib = new MathLib(); //Objekt matematické knihovny
    private double value1 = 0.0;
    private double value2 = 0.0;
    private boolean operatorSet = false;
    private boolean decimalVal1 = false;
    private boolean decimalVal2 = false;
    private boolean negative1 = false;
    private boolean negative2 = false;
    private int digitsVal1 = 0;
    private int digitsVal2 = 0;
    private operatorsID operatorID = DEFAULT;

    public MainJFrame() {
        initComponents();
        setLocationRelativeTo(null);
        jText.setText("   ");
    }
    public String Round(double value){
        String tmp = new BigDecimal(value).setScale(getScale(value), RoundingMode.HALF_UP).toPlainString();
        StringBuilder sb = new StringBuilder(tmp);
        while(sb.charAt(sb.length()-1) == '0'){
            sb.deleteCharAt(sb.length()-1);
        }
        if(sb.charAt(sb.length()-1)=='.'){
            sb.deleteCharAt(sb.length()-1);
        }
        if(sb.charAt(0) == '-'){
            sb.deleteCharAt(0);
            negative1 = true;
        }else{
            negative1 = false;
        } 
        return sb.toString();
    }
    public static int getScale(double value){
        BigDecimal bd = new BigDecimal(value);
        if (bd.intValue() == 0) {
            return 10;
        }
        int scale = 10 - (int)Math.log10(bd.intValue());
        if(scale < 0){
            return 0;
        }
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
        negative1 = false;
        negative2 = false;
    }
    public void printf(String x){
        String y = jText.getText();
        StringBuilder sb = new StringBuilder(y);
        if(sb.charAt(y.length()-1) == 'π'){
            printOperator("*");
            operatorID = MULTIPLY;
            operatorSet = true;
            y = jText.getText();
            StringBuilder sb2 = new StringBuilder(y);
            sb2.append(x);
            jText.setText(sb2.toString());
            return;
        }
        sb.append(x);
        jText.setText(sb.toString());
    }
    public void printOperator(String x){
        String y = jText.getText();
        StringBuilder sb = new StringBuilder(y);
        if(operatorSet) {
            Equals();
            String z = jText.getText();
            StringBuilder sb2 = new StringBuilder(z);
            sb2.append("\n" + x + " ");
            jText.setText(sb2.toString());
            return;
        }
        if(y.charAt(y.length()-1) == ' '){
            if(y.length() == 3){
                if(x.equals(" -")){
                    jText.setText(" - ");
                    negative1 = true;
                    return;
                }
                sb.append("0");
            }else{
                return;
            }
        }else if(y.charAt(y.length()-1) == ','){
            sb.append("0");
        }
        sb.append("\n" + x + " ");
        jText.setText(sb.toString());
    }
    public void printDec(){
        if(operatorSet){
            if(decimalVal2){
                return;
            }
            decimalVal2 = true;
        }else{
            if(decimalVal1){
                return;
            }
            decimalVal1 = true;
        }
        String y = jText.getText();
        StringBuilder sb = new StringBuilder(y);
        if(sb.charAt(sb.length()-1) == ' '){
            sb.append('0');
        }
        sb.append(',');
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
                        sb.deleteCharAt(y.length()-1);
                        sb.deleteCharAt(y.length()-2);
                        sb.deleteCharAt(y.length()-3);
                        if(c=='-'){
                            sb.deleteCharAt(y.length()-4);
                        }
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
                                value1 -= (double)(c-'0')/mathLib.nPow(10.0, digitsVal1--);
                            }
                        }else{
                            if (!decimalVal2) {
                                value2 -= ((double)c-'0');
                                value2 /= 10;
                            } else {
                                value2 -= (double)(c-'0')/mathLib.nPow(10.0, digitsVal2--);
                            }
                        }
                        sb.deleteCharAt(y.length()-1);
                        break;
                    }
                }
                if(c == 'π'){
                    if(!operatorSet){
                        value1 -= (3.14159265358979323);
                    }else{
                        value2 -= (3.14159265358979323);
                    }
                    sb.deleteCharAt(y.length()-1);
                }
                if(c == ','){
                    if(!operatorSet){
                        decimalVal1 = false;
                    }else{
                        decimalVal2 = false;
                    }
                    sb.deleteCharAt(y.length()-1);
                }
                if(c == '-'){
                    negative2 = false;
                    sb.deleteCharAt(y.length()-1);
                }
            }else if(y.charAt(y.length()-2) == '-'){
                jText.setText("   ");
                return;
            }
            jText.setText(sb.toString());
    }
    public void KeyTracker(char c){
        switch (c) {
            case '1':
                btn1.doClick();
                break;
            case '2':
                btn2.doClick();
                break;
            case '3':
                btn3.doClick();
                break;
            case '4':
                btn4.doClick();
                break;
            case '5':
                btn5.doClick();
                break;
            case '6':
                btn6.doClick();
                break;
            case '7':
                btn7.doClick();
                break;
            case '8':
                btn8.doClick();
                break;
            case '9':
                btn9.doClick();
                break;
            case '0':
                btn0.doClick();
                break;
            case '+':
                btnPlus.doClick();
                break;
            case '-':
                btnMinus.doClick();
                break;
            case '*':
                btnMul.doClick();
                break;
            case '/':
                btnDiv.doClick();
                break;
            case '%':
                btnMod.doClick();
                break;
            case 'c':
            case 'C':
            case KeyEvent.VK_DELETE:
                btnC.doClick();
                break;
            case '!':
                btnFac.doClick();
                break;
            case '=':
            case KeyEvent.VK_ENTER:
                btnEQ.doClick();
                break;
            case KeyEvent.VK_BACK_SPACE:
                btnBack.doClick();
                break;
            case ',':
            case '.':
                btnDec.doClick();
                break;
            case 'd':
            case 'D':
                DarkMode.doClick();
                break;
            case 'i':
            case 'I':
                btnInfo.doClick();
                break;
            case 'h': //vypis záznam
                StringBuilder debug = new StringBuilder();
                debug.append("OperatorID: " + operatorID + "\n");
                debug.append("OperatorSet: " + operatorSet + "\n");
                debug.append("Negative1: " + negative1 + "\n");
                debug.append("Negative2: " + negative2 + "\n");
                debug.append("Value1: " + value1 + "\n");
                debug.append("Value2: " + value2 + "\n");
                debug.append("DigitsVal1: " + digitsVal1 + "\n");
                debug.append("DigitsVal2: " + digitsVal2 + "\n");
                debug.append("DecimalVal1: " + decimalVal1 + "\n");
                debug.append("DecimalVal2: " + decimalVal2 + "\n");
                JOptionPane.showMessageDialog(null, debug.toString(), "DEBUG", JOptionPane.PLAIN_MESSAGE);
                break;
            case 'p': //feature (odendat) !!ANO
                btnPi.doClick();
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
                value1 += x/mathLib.nPow(10, ++digitsVal1);
            }
        }else{
            if (!decimalVal2) {
                value2 = value2 * 10.0 + x;
            } else {
                value2 += x/mathLib.nPow(10.0, ++digitsVal2);
            }
        }
    }
    public void Equals(){
        String output = "";
        if(negative1){
            value1 *= -1;
        }
        if(negative2){
            value2 *= -1;
        }
        switch(operatorID){
            case PLUS:
                //funkce plus
                value1 = mathLib.plus(value1, value2);
                output = Alignment(value1);
                break;
            case MINUS:
                //funkce minus
                value1 = mathLib.minus(value1, value2);
                output = Alignment(value1);
                break;
            case DIVIDE:
                //funkce divide
                value1 = mathLib.div(value1, value2);
                output = Alignment(value1);
                break;
            case MULTIPLY:
                //funkce multiplay
                value1 = mathLib.mul(value1, value2);
                output = Alignment(value1);
                break;
            case MODULO:
                //funkce modulo
                value1 = mathLib.mod(value1, value2); //jen kvůli testování
                output = Alignment(value1);
                break;
            case FACTORIAL:
                //funkce faktorial
                int tmp = (int) value1;
                if(mathLib.mod(value1, tmp) != 0){
                    JOptionPane.showMessageDialog(null, "Faktoriál je definovanán pouze nad celými čísly", "ERROR", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                try{
                    value1 = mathLib.fact(tmp);
                }catch(Exception e){
                    String error = e.toString().substring(' ' - 1);
                    JOptionPane.showMessageDialog(null, error, "ERROR", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                output = Alignment(value1);
                //přidání funkce pro zapsání velkých čísel
                break;
            case POWER:
                //TODO upravit
                value1 = mathLib.pow(value1);
                output = Alignment(value1);
                break;
            case SQRT:
                //TODO upravit
                try{
                    value1 = mathLib.sqrt(value1);
                }catch(Exception e){
                    String error = e.toString().substring(' ' - 1);
                    JOptionPane.showMessageDialog(null, error, "ERROR", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                output = Alignment(value1);
                break;
            case NPOWER:
                //TODO upravit
                int tmp1 = (int) value2;
                if(mathLib.mod(tmp1, value2) != 0){
                    JOptionPane.showMessageDialog(null, "n musí být celé číslo", "ERROR", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                try{
                    value1 = mathLib.nPow(value1, tmp1);
                }catch(Exception e){
                    String error = e.toString().substring(' ' - 1);
                    JOptionPane.showMessageDialog(null, error, "ERROR", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                output = Alignment(value1);
                break;
            case NSQRT:
                //TODO upravit
                int tmp2 = (int) value2;
                if(mathLib.mod(tmp2, value2) != 0){
                    JOptionPane.showMessageDialog(null, "n musí být celé nenulové číslo", "ERROR", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                try{
                    value1 = mathLib.nPow(value1, tmp2);
                }catch(Exception e){
                    String error = e.toString().substring(' ' - 1);
                    JOptionPane.showMessageDialog(null, error, "ERROR", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                output = Alignment(value1);
                break;
            case SIN:
                value1 = mathLib.sin(value1);
                output = Alignment(value1);
                break;
            case COS:
                value1 = mathLib.cos(value1);
                output = Alignment(value1);
                break;
            case TAN:
                value1 = mathLib.tan(value1);
                output = Alignment(value1);
                break;
            default:
                return;
        }
        if(negative1){
            jText.setText(" - " + output);
        }else{
            jText.setText("   " + output);
        }
        negative2 = false;
    }
    public String Alignment(double value){
        value2 = 0;
        digitsVal2 = 0;
        decimalVal2 = false;
        operatorID = DEFAULT;
        operatorSet = false;
        System.out.println("Číslo před zaokrouhlením: " + value1);
        String tmp = Round(value1);
        tmp = tmp.replace(".", ",");
        System.out.println("Číslo po zaokrouhlením: " + tmp);
        return tmp;
    }
    public void Pi(){
        if(operatorSet){
           if(value2 == 0){
                value2 = 3.1415926535897932384;
           }else{
               printOperator("*");
               operatorID = MULTIPLY;
               operatorSet = true;
               value2 = 3.1415926535897932384;
           }
        }else{
            if(value1 == 0){
                if(jText.getText().length() == 3){
                    value1 = 3.14159265358979323;
                }else{
                    printOperator("*");
                    operatorID = MULTIPLY;
                    operatorSet = true;
                    value2 = 3.1415926535897932384;
                }

            }else{
                printOperator("*");
                operatorID = MULTIPLY;
                operatorSet = true;
                value2 = 3.1415926535897932384;
            }
        }
        printf("π");
    }
    public void Negation(){
        String text = jText.getText();
        StringBuilder sb = new StringBuilder(text);
        if(!operatorSet){
            negative1 = !negative1;
            if(negative1){
                sb.deleteCharAt(1);
                sb.replace(1, 1, "-");
            }else{
                sb.deleteCharAt(1);
                sb.replace(1, 1, " ");
            }
        }else{
            negative2 = !negative2;
            if(negative2) {
                for (int i = text.length()-1; i >= 0; i--) {
                    if (sb.charAt(i) == ' ') {
                        sb.replace(i + 1, i + 1, "-");
                        break;
                    }
                }
            }else{
                for (int i = text.length()-1; i >= 0; i--) {
                    if (sb.charAt(i) == '-') {
                        sb.deleteCharAt(i);
                        break;
                    }
                }
            }
        }
        jText.setText(sb.toString());
    }
    public void DarkMode(){
        java.awt.Color BLACK = new java.awt.Color(0,0,0);
        java.awt.Color WHITE = new java.awt.Color(255,255,255);
        java.awt.Color GRAY15 = new java.awt.Color(15,15,15);
        java.awt.Color GRAY45 = new java.awt.Color(45,45,45);
        java.awt.Color GRAY180 = new java.awt.Color(180,180,180);
        java.awt.Color GRAY210 = new java.awt.Color(210,210,210);
        if(DarkMode.isSelected()){
            jPanel1.setBackground(new java.awt.Color(51, 51, 51));
            jText.setBackground(BLACK);
            jText.setForeground(WHITE);
            btn0.setBackground(GRAY45);
            btn0.setForeground(WHITE);
            btn1.setBackground(GRAY45);
            btn1.setForeground(WHITE);
            btn2.setBackground(GRAY45);
            btn2.setForeground(WHITE);
            btn3.setBackground(GRAY45);
            btn3.setForeground(WHITE);
            btn4.setBackground(GRAY45);
            btn4.setForeground(WHITE);
            btn5.setBackground(GRAY45);
            btn5.setForeground(WHITE);
            btn6.setBackground(GRAY45);
            btn6.setForeground(WHITE);
            btn7.setBackground(GRAY45);
            btn7.setForeground(WHITE);
            btn8.setBackground(GRAY45);
            btn8.setForeground(WHITE);
            btn9.setBackground(GRAY45);
            btn9.setForeground(WHITE);
            btnDec.setBackground(GRAY15);
            btnDec.setForeground(WHITE);
            btnEQ.setBackground(GRAY15);
            btnEQ.setForeground(WHITE);
            btnPlus.setBackground(GRAY15);
            btnPlus.setForeground(WHITE);
            btnMinus.setBackground(GRAY15);
            btnMinus.setForeground(WHITE);
            btnMul.setBackground(GRAY15);
            btnMul.setForeground(WHITE);
            btnDiv.setBackground(GRAY15);
            btnDiv.setForeground(WHITE);
            btnFac.setBackground(GRAY15);
            btnFac.setForeground(WHITE);
            btnBack.setBackground(GRAY15);
            btnBack.setForeground(WHITE);
            btnSqrtN.setBackground(GRAY15);
            btnSqrtN.setForeground(WHITE);
            btnExp.setBackground(GRAY15);
            btnExp.setForeground(WHITE);
            btnSqrt.setBackground(GRAY15);
            btnSqrt.setForeground(WHITE);
            btnInfo.setBackground(GRAY15);
            btnInfo.setForeground(WHITE);
            DarkMode.setBackground(GRAY15);
            DarkMode.setForeground(WHITE);
            DarkMode.setText("Light Mode");
            btnC.setBackground(GRAY15);
            btnC.setForeground(WHITE);
            btnMod.setBackground(GRAY15);
            btnMod.setForeground(WHITE);
            btnExpN.setBackground(GRAY15);
            btnExpN.setForeground(WHITE);
            btnPlusMinus.setBackground(GRAY15);
            btnPlusMinus.setForeground(WHITE);
            btnCos.setBackground(GRAY15);
            btnCos.setForeground(WHITE);
            btnSin.setBackground(GRAY15);
            btnSin.setForeground(WHITE);
            btnTan.setBackground(GRAY15);
            btnTan.setForeground(WHITE);
            btnPi.setBackground(GRAY15);
            btnPi.setForeground(WHITE);

        }else{
            jPanel1.setBackground(new java.awt.Color(204, 204, 204));
            jText.setBackground(WHITE);
            jText.setForeground(BLACK);
            btn0.setBackground(GRAY210);
            btn0.setForeground(BLACK);
            btn1.setBackground(GRAY210);
            btn1.setForeground(BLACK);
            btn2.setBackground(GRAY210);
            btn2.setForeground(BLACK);
            btn3.setBackground(GRAY210);
            btn3.setForeground(BLACK);
            btn4.setBackground(GRAY210);
            btn4.setForeground(BLACK);
            btn5.setBackground(GRAY210);
            btn5.setForeground(BLACK);
            btn6.setBackground(GRAY210);
            btn6.setForeground(BLACK);
            btn7.setBackground(GRAY210);
            btn7.setForeground(BLACK);
            btn8.setBackground(GRAY210);
            btn8.setForeground(BLACK);
            btn9.setBackground(GRAY210);
            btn9.setForeground(BLACK);
            btnDec.setBackground(GRAY180);
            btnDec.setForeground(BLACK);
            btnEQ.setBackground(GRAY180);
            btnEQ.setForeground(BLACK);
            btnPlus.setBackground(GRAY180);
            btnPlus.setForeground(BLACK);
            btnMinus.setBackground(GRAY180);
            btnMinus.setForeground(BLACK);
            btnMul.setBackground(GRAY180);
            btnMul.setForeground(BLACK);
            btnDiv.setBackground(GRAY180);
            btnDiv.setForeground(BLACK);
            btnFac.setBackground(GRAY180);
            btnFac.setForeground(BLACK);
            btnBack.setBackground(GRAY180);
            btnBack.setForeground(BLACK);
            btnSqrtN.setBackground(GRAY180);
            btnSqrtN.setForeground(BLACK);
            btnExp.setBackground(GRAY180);
            btnExp.setForeground(BLACK);
            btnSqrt.setBackground(GRAY180);
            btnSqrt.setForeground(BLACK);
            btnInfo.setBackground(GRAY180);
            btnInfo.setForeground(BLACK);
            DarkMode.setBackground(GRAY180);
            DarkMode.setForeground(BLACK);
            DarkMode.setText("Dark Mode");
            btnC.setBackground(GRAY180);
            btnC.setForeground(BLACK);
            btnMod.setBackground(GRAY180);
            btnMod.setForeground(BLACK);
            btnExpN.setBackground(GRAY180);
            btnExpN.setForeground(BLACK);
            btnPlusMinus.setBackground(GRAY180);
            btnPlusMinus.setForeground(BLACK);
            btnCos.setBackground(GRAY180);
            btnCos.setForeground(BLACK);
            btnSin.setBackground(GRAY180);
            btnSin.setForeground(BLACK);
            btnTan.setBackground(GRAY180);
            btnTan.setForeground(BLACK);
            btnPi.setBackground(GRAY180);
            btnPi.setForeground(BLACK);

        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btnInfo = new javax.swing.JButton();
        DarkMode = new javax.swing.JToggleButton();
        btnC = new javax.swing.JButton();
        btnSqrtN = new javax.swing.JButton();
        btnBack = new javax.swing.JButton();
        btnMod = new javax.swing.JButton();
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
        btnPi = new javax.swing.JButton();
        btnExpN = new javax.swing.JButton();
        btnTan = new javax.swing.JButton();
        btnSin = new javax.swing.JButton();
        btnCos = new javax.swing.JButton();
        btnPlusMinus = new javax.swing.JButton();

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
        DarkMode.setFocusTraversalKeysEnabled(true);
        DarkMode.setFocusable(false);
        DarkMode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DarkModeActionPerformed(evt);
            }
        });

        btnC.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnC.setText("AC");
        btnC.setFocusable(false);
        btnC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCActionPerformed(evt);
            }
        });

        btnSqrtN.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnSqrtN.setText("n√");
        btnSqrtN.setFocusable(false);
        btnSqrtN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSqrtNActionPerformed(evt);
            }
        });

        btnBack.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnBack.setText("←");
        btnBack.setFocusable(false);
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        btnMod.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnMod.setText("%");
        btnMod.setFocusable(false);
        btnMod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModActionPerformed(evt);
            }
        });

        btnExp.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnExp.setText("x²");
        btnExp.setFocusable(false);
        btnExp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExpActionPerformed(evt);
            }
        });

        btnSqrt.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnSqrt.setText("√‾");
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
        jScrollPane1.setViewportView(jText);

        Action beep = jText.getActionMap().get(DefaultEditorKit.deletePrevCharAction);
        Action beep2 = jText.getActionMap().get(DefaultEditorKit.deleteNextCharAction);
        beep.setEnabled(false);
        beep2.setEnabled(false);
        jScrollPane1.setViewportView(jText);

        btnPi.setFont(new java.awt.Font("Tamil MN", 0, 15)); // NOI18N
        btnPi.setText("π");
        btnPi.setFocusable(false);
        btnPi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPiActionPerformed(evt);
            }
        });

        btnExpN.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnExpN.setText("xʸ");
        btnExpN.setFocusable(false);
        btnExpN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExpNActionPerformed(evt);
            }
        });

        btnTan.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        btnTan.setText("tan");
        btnTan.setFocusable(false);
        btnTan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTanActionPerformed(evt);
            }
        });

        btnSin.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        btnSin.setText("sin");
        btnSin.setFocusable(false);
        btnSin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSinActionPerformed(evt);
            }
        });

        btnCos.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btnCos.setText("cos");
        btnCos.setFocusable(false);
        btnCos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCosActionPerformed(evt);
            }
        });

        btnPlusMinus.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnPlusMinus.setText("+/-");
        btnPlusMinus.setFocusable(false);
        btnPlusMinus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPlusMinusActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(btnExpN, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnTan, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnSin, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnCos, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnPlusMinus, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btnC, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, 0)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btn0, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(btnDec, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(btnEQ, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btn7, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(btn8, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(btn9, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(btnMul, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btn4, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(btn5, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(btn6, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(btnMinus, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btn1, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(btn2, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(btn3, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(btnPlus, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btnExp, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(btnSqrt, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(btnSqrtN, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(btnDiv, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btnPi, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(btnFac, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(btnMod, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(btnInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(DarkMode, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(DarkMode, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnC, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPi, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnFac, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnMod, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnDiv, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSqrtN, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSqrt, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnExp, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnExpN, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnTan, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn7, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn8, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn9, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnMul, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnCos, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn4, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn5, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn6, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnMinus, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSin, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn2, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn3, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPlus, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnPlusMinus, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn0, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDec, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEQ, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
        if(jText.getText().length() != 3){
        operatorSet = true;
        operatorID = operatorsID.PLUS;
        }
    }//GEN-LAST:event_btnPlusActionPerformed

    private void btnMinusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMinusActionPerformed
        printOperator(" -");
        operatorID = operatorsID.MINUS;
        operatorSet = true;
    }//GEN-LAST:event_btnMinusActionPerformed

    private void btnMulActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMulActionPerformed
        printOperator("*");
        if(jText.getText().length() != 3) {
            operatorSet = true;
            operatorID = MULTIPLY;
        }
    }//GEN-LAST:event_btnMulActionPerformed

    private void btnDivActionPerformed(java.awt.event.ActionEvent evt) {                                       
        printOperator("÷");
        if(jText.getText().length() != 3) {
            operatorSet = true;
            operatorID = operatorsID.DIVIDE;
        }
    }                                      

    private void btnEQActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEQActionPerformed
        Equals();
    }//GEN-LAST:event_btnEQActionPerformed

    private void btnDecActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDecActionPerformed
        printDec();
    }//GEN-LAST:event_btnDecActionPerformed

    private void btnFacActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFacActionPerformed
        if(operatorSet){
        Equals();
        }
        operatorID = FACTORIAL;
        Equals();
    }//GEN-LAST:event_btnFacActionPerformed

    private void btnSqrtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSqrtActionPerformed
        if(operatorSet){
            Equals();
        }
        operatorID = SQRT;
        Equals();
    }//GEN-LAST:event_btnSqrtActionPerformed

    private void btnExpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExpActionPerformed
        if(operatorSet){
            Equals();
        }
        operatorID = POWER;
        Equals();
    }//GEN-LAST:event_btnExpActionPerformed

    private void btnModActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_brnModActionPerformed
        printOperator("%");
        if(jText.getText().length() != 3) {
            operatorSet = true;
            operatorID = operatorsID.MODULO;
        }
    }//GEN-LAST:event_brnModActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        BackSpace();
    }//GEN-LAST:event_btnBackActionPerformed

    private void btnSqrtNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSqrtNActionPerformed
        if(operatorSet){
            Equals();
        }
        operatorID = NSQRT;
        Equals();
    }//GEN-LAST:event_btnSqrtNActionPerformed

    private void btnCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCActionPerformed
        DeleteScreen();
    }//GEN-LAST:event_btnCActionPerformed

    private void DarkModeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DarkModeActionPerformed
        DarkMode();
    }//GEN-LAST:event_DarkModeActionPerformed

    private void btnInfoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInfoActionPerformed
        StringBuilder INFO = new StringBuilder();
        INFO.append("Hi\n");
        INFO.append("Hi\n");
        INFO.append("Hi\n");
        INFO.append("Hi\n");
        INFO.append("Hi\n");
        INFO.append("Hi\n");
        INFO.append("Hi\n");
        JOptionPane.showMessageDialog(null, INFO.toString(), "INFO", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_btnInfoActionPerformed
    private void jTextKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnDivActionPerformed
        KeyTracker(evt.getKeyChar());
    }//GEN-LAST:event_btnDivActionPerformed

    private void btnPiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPiActionPerformed
        Pi();
    }//GEN-LAST:event_btnPiActionPerformed

    private void btnExpNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExpNActionPerformed
        if(operatorSet){
            Equals();
        }
        operatorID = NPOWER;
        Equals();
    }//GEN-LAST:event_btnExpNActionPerformed

    private void btnTanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTanActionPerformed
        if(operatorSet){
            Equals();
        }
        operatorID = TAN;
        Equals();
    }//GEN-LAST:event_btnTanActionPerformed

    private void btnSinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSinActionPerformed
        if(operatorSet){
            Equals();
        }
        operatorID = SIN;
        Equals();
    }//GEN-LAST:event_btnSinActionPerformed

    private void btnCosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCosActionPerformed
        if(operatorSet){
            Equals();
        }
        operatorID = COS;
        Equals();
    }//GEN-LAST:event_btnCosActionPerformed

    private void btnPlusMinusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPlusMinusActionPerformed
        Negation();
    }//GEN-LAST:event_btnPlusMinusActionPerformed
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
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnC;
    private javax.swing.JButton btnCos;
    private javax.swing.JButton btnDec;
    private javax.swing.JButton btnDiv;
    private javax.swing.JButton btnEQ;
    private javax.swing.JButton btnExp;
    private javax.swing.JButton btnExpN;
    private javax.swing.JButton btnFac;
    private javax.swing.JButton btnInfo;
    private javax.swing.JButton btnMinus;
    private javax.swing.JButton btnMod;
    private javax.swing.JButton btnMul;
    private javax.swing.JButton btnPi;
    private javax.swing.JButton btnPlus;
    private javax.swing.JButton btnPlusMinus;
    private javax.swing.JButton btnSin;
    private javax.swing.JButton btnSqrt;
    private javax.swing.JButton btnSqrtN;
    private javax.swing.JButton btnTan;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jText;
    // End of variables declaration//GEN-END:variables
}
package org.example;

import org.example.PolynomData.Monom;
import org.example.PolynomData.Polinom;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.Math.abs;

public class CalculatorForm extends JDialog {

    private JPanel CalculatorPanel;
    private JLabel firstlabel;
    private JLabel secondlabel;
    private JTextField textField1;
    private JTextField textField2;
    private JButton addButton;
    private JButton multiplicateButton;
    private JButton substractButton;
    private JButton divideButton;
    private JButton derivativeButton;
    private JButton integralButton;
    private JButton clearButton;
    private JLabel resultlabel;
    private JLabel rest;

    public JLabel errorLabel;
    private JLabel reminderlabel;

    public static Polinom transform(String polinom1) {
        Map<Integer, Integer> map = new HashMap<>();
        Polinom polinom = new Polinom(map);
        Pattern pattern = Pattern.compile("([+-]?[^-+]+)");
        Matcher matcher = pattern.matcher(polinom1);
        while (matcher.find()) {
            int coef = 0;
            int exp = 0;
            int ok1 = 0;///verifica daca am trecut de caracterl x
            int ok2 = 0;///verifica daca am trecut de caraceru ^
           // System.out.println(matcher.group(1));
                for (int i = 0; i < matcher.group(1).length(); i++) {
                    char c = matcher.group(1).charAt(i);
                    if (Character.isDigit(c) && ok1 == 0 && ok2 == 0) {
                        if (matcher.group(1).charAt(0) == '-') {
                            coef = abs(coef) * 10 + Character.getNumericValue(c);
                            coef = -coef;
                        } else
                            coef = coef * 10 + Character.getNumericValue(c);
                    } else if (i == 1 && c == 'x' && matcher.group(1).charAt(0) == '-' && coef == 0) {
                        coef = -1;
                        ok1 = 1;
                    } else if (c == 'x' && coef == 0) {
                        coef = 1;
                        ok1 = 1;
                    } else if (c == 'x')
                        ok1 = 1;
                    else if (c == '^') {
                        ok2 = 1;
                    } else if (ok1 == 1 && ok2 == 1 && Character.isDigit(c)) {
                        exp = exp * 10 + Character.getNumericValue(c);
                    }

                }
                if (ok1 == 0 && coef == 0)
                    coef = 1;
                if (ok1 == 1 && ok2 == 0)
                    exp = 1;
               // System.out.println(coef + " " + exp);
                Monom monom = new Monom(coef, exp);
                polinom.addMonom(monom);

        }
        return polinom;
    }

    public CalculatorForm(JFrame parent)
    {
        super(parent);
        setTitle("CalculatorForm");
        setContentPane(CalculatorPanel);
        setSize(new Dimension(700, 500));
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setModal(true);
        setLocationRelativeTo(parent);



        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Map<Integer, Integer> map = new HashMap<>();
                Map<Integer, Integer> map1 = new HashMap<>();
                Polinom polinom = new Polinom(map);
                Polinom polinom1 = new Polinom(map1);
                polinom = transform(textField1.getText());
                polinom1 = transform(textField2.getText());
                Map<Integer, Integer> map2 = new HashMap<>();
                Polinom polinom2;
                polinom2 = Polinom.add(polinom, polinom1);
                resultlabel.setText(polinom2.toString());
               /* for(Map.Entry<Integer, Integer> entry: polinom2.getPoli().entrySet())
                {
                    System.out.println("exponent=" + entry.getKey() + "; " +
                            "coeficient=" + entry.getValue());

                }*/
            }
        });

        substractButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Map<Integer, Integer> map = new HashMap<>();
                Map<Integer, Integer> map1 = new HashMap<>();
                Polinom polinom = new Polinom(map);
                Polinom polinom1 = new Polinom(map1);
                polinom = transform(textField1.getText());
                polinom1 = transform(textField2.getText());
                Map<Integer, Integer> map2 = new HashMap<>();
                Polinom polinom2;
                polinom2 = Polinom.sub(polinom, polinom1);
                resultlabel.setText(polinom2.toString());
            }
        });

        multiplicateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Map<Integer, Integer> map = new HashMap<>();
                Map<Integer, Integer> map1 = new HashMap<>();
                Polinom polinom = new Polinom(map);
                Polinom polinom1 = new Polinom(map1);
                polinom = transform(textField1.getText());
                polinom1 = transform(textField2.getText());
                Map<Integer, Integer> map2 = new HashMap<>();
                Polinom polinom2;
                polinom2 = Polinom.mul(polinom, polinom1);
                resultlabel.setText(polinom2.toString());
            }
        });

        derivativeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Map<Integer, Integer> map = new HashMap<>();
                Polinom polinom = new Polinom(map);
                polinom = transform(textField1.getText());
                Map<Integer, Integer> map2 = new HashMap<>();
                Polinom polinom2;
                polinom2 = Polinom.deriv(polinom);
                resultlabel.setText(polinom2.toString());
            }
        });
        integralButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {Map<Integer, Integer> map = new HashMap<>();
                Polinom polinom = new Polinom(map);
                polinom = transform(textField1.getText());
                Map<Integer, Integer> map2 = new HashMap<>();
                Polinom polinom2;
                polinom2 = Polinom.integral(polinom);
                resultlabel.setText(polinom2.toString());
            }
        });

        divideButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Map<Integer, Integer> map = new HashMap<>();
                Map<Integer, Integer> map1 = new HashMap<>();
                Polinom polinom = new Polinom(map);
                Polinom polinom1 = new Polinom(map1);
                polinom = transform(textField1.getText());
                polinom1 = transform(textField2.getText());
                Map<Integer, Integer> map2 = new HashMap<>();
                Polinom polinom2;
                polinom2 = Polinom.divide(polinom, polinom1);
                resultlabel.setText(polinom2.toString());
                rest.setText(polinom.toString());
            }
        });
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textField1.setText("");
                textField2.setText("");
                resultlabel.setText("");
                rest.setText("");
            }
        });
        setVisible(true);
    }

    public static void main(String[] args) {

        CalculatorForm calculatorForm = new CalculatorForm(null);
    }
}

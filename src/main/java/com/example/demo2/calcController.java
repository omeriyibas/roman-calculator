package com.example.demo2;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.text.DecimalFormat;

public class calcController {
    @FXML private Pane titlePane;
    @FXML private ImageView btnMinimize, btnClose;
    @FXML private Label lblResult;

    private String value="";


    private double x, y;
    private double num1 = 0;
    private String operator = "+";

    private int convert_roman(String number){ // string olarak gelen roman sayısının karaterlere ayırıp ilgili sayıyı ekleyerek sonucu buluyor
        int len = number.length();
        number = number + " ";
        int result = 0;
        for (int i = 0; i < len; i++) {
            char ch   = number.charAt(i);
            char next_char = number.charAt(i+1);

            if (ch == 'M') {
                result += 1000;
            } else if (ch == 'C') {
                if (next_char == 'M') {
                    result += 900;
                    i++;
                } else if (next_char == 'D') {
                    result += 400;
                    i++;
                } else {
                    result += 100;
                }
            } else if (ch == 'D') {
                result += 500;
            } else if (ch == 'X') {
                if (next_char == 'C') {
                    result += 90;
                    i++;
                } else if (next_char == 'L') {
                    result += 40;
                    i++;
                } else {
                    result += 10;
                }
            } else if (ch == 'L') {
                result += 50;
            } else if (ch == 'I') {
                if (next_char == 'X') {
                    result += 9;
                    i++;
                } else if (next_char == 'V') {
                    result += 4;
                    i++;
                } else {
                    result++;
                }
            } else { // if (ch == 'V')
                result += 5;
            }
        }
        return result;
    }

    private String integerToRoman(double num) { // sayı dizisindeki sayıları sayı sıfırlanan kadar çıkar, her çıkan sayayı karşılık gelen karakteri ekşe

        System.out.println("Integer: " + num);
        int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] romanLiterals = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

        StringBuilder roman = new StringBuilder();

        for (int i = 0; i < values.length; i++) {
            while (num >= values[i]) {
                num -= values[i];
                roman.append(romanLiterals[i]);
            }
        }

        return roman.toString();
    }

    public void init(Stage stage) {
        titlePane.setOnMousePressed(mouseEvent -> {
            x = mouseEvent.getSceneX();
            y = mouseEvent.getSceneY();
        });
        titlePane.setOnMouseDragged(mouseEvent -> {
            stage.setX(mouseEvent.getScreenX()-x);
            stage.setY(mouseEvent.getScreenY()-y);
        });

        btnClose.setOnMouseClicked(mouseEvent -> stage.close());
        btnMinimize.setOnMouseClicked(mouseEvent -> stage.setIconified(true));
    }

    private int getAfterPoint(double num){ // noktadan sonraki sayıyı bul
        String str_num=String.valueOf(num);
        String after_decimal=str_num.split("")[2];
        System.out.println(after_decimal);
        int result=Integer.parseInt(after_decimal);
        return result;
    }

    @FXML
    void onNumberClicked(MouseEvent event) { // sayılardan birine tıklanınca tıklanan değeri al
        value += ((Pane) event.getSource()).getId().replace("btn", "");
        System.out.println(value);
        lblResult.setText(value);
    }



    @FXML
    void onSymbolClicked(MouseEvent event) {
        String symbol = ((Pane)event.getSource()).getId().replace("btn",""); // hangi sembol olduğun kontrolü
        if(symbol.equals("Equals")) {
            double num2 = convert_roman(value);
            switch (operator) { // aritmatik işlemlerden birne tuşlanmışsa
                case "+" : lblResult.setText(integerToRoman(num1+num2) + "");
                case "-" : lblResult.setText(integerToRoman(num1-num2) + "");
                case "*" : lblResult.setText(integerToRoman(num1*num2) + "");
                case "/" : lblResult.setText(integerToRoman(num1/num2) + "." + integerToRoman(getAfterPoint(num1/num2)) + "");
            }
            operator = ".";
            value="";
        }
        else if(symbol.equals("Clear")) {
            lblResult.setText("");
            operator = ".";
            value="";
        }
        else {
            switch (symbol) { // sonraki işlem için operator değerini belirle
                case "Plus" : operator = "+";
                case "Minus" : operator = "-";
                case "Multiply" : operator = "*";
                case "Divide" : operator = "/";
            }
            num1 = convert_roman(value);
            System.out.println(num1);
            lblResult.setText("");
            value="";

        }
    }
}

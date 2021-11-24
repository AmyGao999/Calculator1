package com.newtech.data.calculator1.Service;

import java.util.Stack;

public class Calculator {
    private String expr;
    public String getExpr() {
        return expr;
    }
    public void setExpr(String expr) {
        this.expr = expr;
    }

    public static String calculate(String s) {
        //corner case
        if (s == null || s.length() == 0) return "Invalid Input";

        //using two stacks to store numbers and Operator
        Stack<Float> nums = new Stack<>();
        Stack<Character> ops = new Stack<>();

        //flag used to handle the two operator together
        int flag = 1;

        //loop to find out all the numbers and operators
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            //if the character is a letter, it is invalid input
            if (Character.isLetter(c)) {
                return "Invalid Input";
            }
            //if the character is space, just skip
            if (c == ' ') continue;

            //if the character is a digit, need to get the number
            if (Character.isDigit(c)) {
                int j = findValue(s, i);
                float number = (float) Integer.parseInt(s.substring(i, j + 1));
                i = j;
                nums.push(flag * number);
                flag = 1;
            } else if (s.charAt(i) == '.'){
                //if the character is '.', need to combine the integer and decimal together
                i += 1;
                int k = findValue(s,i);
                float dec = (float)(Integer.parseInt(s.substring(i, k +1))/Math.pow(10,k-i+1));
                //no integer part
                if (i-1 == 0 || !Character.isDigit(s.charAt(i-2))) {
                    nums.push(flag * dec);
                    flag = 1;
                } else {//with integer part
                    float topNum = nums.pop();
                    if (topNum > 0) {
                        nums.push(topNum + dec);
                    } else {
                        nums.push(nums.pop()-dec);
                    }
                }
                i = k;

            } else if (c == '(') {
                ops.push(c);
            } else if (c == ')') {
                while (!ops.isEmpty() && ops.peek() != '(') {
                    nums.push(operate(ops.pop(),nums.pop(), nums.pop()));
                }
                ops.pop();
            } else if (isOperator(c)){
                //if"-" appears at the first position or after "(", just push 0 into the num stack to make the calculation easier
                if (i == 0 && c == '-' || s.charAt(i-1) == '(' && c == '-') {
                    nums.push(0f);
                }
                if (i>0 && isOperator(s.charAt((i-1)))) { //to handle more than one operator
                    if(c == '-') {
                        flag = -1;
                        continue;
                    }
                    if (i+1 < s.length() && isOperator(s.charAt(i+1))) {
                        return "Syntax Error";
                    }
                }
                //to calculate the value if current operator has higher priority than which in the operator stack.
                while(!ops.isEmpty() && hasPriority(c, ops.peek())) {
                    nums.push(operate(ops.pop(), nums.pop(), nums.pop()));
                }
                ops.push(c);
            }
        }

        while(!ops.isEmpty()) {
            nums.push(operate(ops.pop(), nums.pop(), nums.pop()));
        }

        float res = nums.pop();
        return (int)res == res ? Integer.toString((int)res) : Float.toString(res);
    }

    private static boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }

    private static boolean hasPriority(char c1, char c2) {
        if(c2 == '(' || c2 == ')') return false;
        return !((c1 == '*' || c1 == '/') && (c2 == '+' || c2 == '-'));
    }
    private static float operate(char c, float i1, float i2) {
        switch(c) {
            case '+' : return i2 + i1;
            case '-' : return i2 - i1;
            case '*' : return i2 * i1;
            case '/' : return i2 / i1;
        }
        return 0;
    }

    private static int findValue(String s, int i) {
        while (i + 1 < s.length() && Character.isDigit(s.charAt(i + 1))) {
            i++;
        }
        return i;
    }
}

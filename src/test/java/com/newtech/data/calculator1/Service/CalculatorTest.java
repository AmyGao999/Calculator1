package com.newtech.data.calculator1.Service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {
    @Test
    public void test1(){
        String s = "1+2";
        String res = Calculator.calculate(s);
        assertEquals("3", res);
    }

    @Test
    public void test2(){
        String s = "4*5/2";
        String res = Calculator.calculate(s);
        assertEquals("10", res);
    }

    @Test
    public void test3(){
        String s = "-.32        /.5";
        String res = Calculator.calculate(s);
        assertEquals("-0.64", res);
    }

    @Test
    public void test4(){
        String s = "-5+-8--11*2";
        String res = Calculator.calculate(s);
        assertEquals("9", res);
    }

    @Test
    public void test5(){
        String s = "(4-2)*3.5";
        String res = Calculator.calculate(s);
        assertEquals("7", res);
    }

    @Test
    public void test6(){
        String s = "2+-+-4";
        String res = Calculator.calculate(s);
        assertEquals("Syntax Error", res);
    }

    @Test
    public void test7(){
        String s = "19 + cinnamon";
        String res = Calculator.calculate(s);
        assertEquals("Invalid Input", res);
    }

}
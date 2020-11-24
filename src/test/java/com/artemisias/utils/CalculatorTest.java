package com.artemisias.utils;

import org.junit.Test;

import static org.junit.Assert.*;

public class CalculatorTest {

    @Test
    public void calculate() {
    }

    @Test
    public void calculateUsingSwitch() {
    }

    @Test
    public void testCalculateUsingSwitch() {
    }

    @Test
    public void testCalculate() {
        Calculator calculator = new Calculator();
        int result = calculator.calculate(3, 4, Operator.valueOf("ADD"));
        assertEquals(7, result);
    }
    @Test
    public void testCalculate2() {
        Calculator calculator = new Calculator();
        int result = calculator.calculate(3, 4, (a,b)->a+b);
        assertEquals(7, result);
    }
}
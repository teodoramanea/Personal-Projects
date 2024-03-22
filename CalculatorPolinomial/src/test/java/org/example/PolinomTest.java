package org.example;

import org.example.PolynomData.Polinom;
import org.junit.jupiter.api.Assertions;

import static org.junit.jupiter.api.Assertions.*;

class PolinomTest {

    @org.junit.jupiter.api.Test
    void add() {
        assertAll(
                () -> Assertions.assertEquals("+7x+3x^2",Polinom.add(CalculatorForm.transform("3x^2+5x"),CalculatorForm.transform("2x")).toString()),
                () -> Assertions.assertEquals("+246+2x+54x^2",Polinom.add(CalculatorForm.transform("2x+56"),CalculatorForm.transform("54x^2+190")).toString()),
                () -> Assertions.assertEquals("-24x+x^2+x^3",Polinom.add(CalculatorForm.transform("x^2+30x^3+25x"),CalculatorForm.transform("-29x^3-49x")).toString())

        );
    }

    @org.junit.jupiter.api.Test
    void sub() {
        assertAll(
                () -> Assertions.assertEquals("+3x+3x^2",Polinom.sub(CalculatorForm.transform("3x^2+5x"),CalculatorForm.transform("2x")).toString()),
                () -> Assertions.assertEquals("-134+2x-54x^2",Polinom.sub(CalculatorForm.transform("2x+56"),CalculatorForm.transform("54x^2+190")).toString()),
                () -> Assertions.assertEquals("+74x+x^2+59x^3",Polinom.sub(CalculatorForm.transform("x^2+30x^3+25x"),CalculatorForm.transform("-29x^3-49x")).toString())
        );
    }

    @org.junit.jupiter.api.Test
    void mul() {
        assertAll(
                () -> Assertions.assertEquals("+9+6x+x^2", Polinom.mul(CalculatorForm.transform("x+3"),CalculatorForm.transform("x+3")).toString()),
                () -> Assertions.assertEquals("+9x+x^2",Polinom.mul(CalculatorForm.transform("x"),CalculatorForm.transform("x+9")).toString()),
                () -> Assertions.assertEquals("+25x^2+10x^3+15x^4",Polinom.mul(CalculatorForm.transform("5x+2x^2+3x^3"),CalculatorForm.transform("5x")).toString())

        );
    }

    @org.junit.jupiter.api.Test
    void deriv() {
        assertAll(
                () -> Assertions.assertEquals("+2x",Polinom.deriv(CalculatorForm.transform("x^2")).toString()),
                () -> Assertions.assertEquals("-14+18x",Polinom.deriv(CalculatorForm.transform("9x^2-14x-70")).toString()),
                () -> Assertions.assertEquals("-30+54x+250x^4",Polinom.deriv(CalculatorForm.transform("27x^2-30x+50x^5")).toString())


        );

    }

    @org.junit.jupiter.api.Test
    void integral() {
        assertAll(
                () -> Assertions.assertEquals("+3x+1/2*4x^2",Polinom.integral(CalculatorForm.transform("4x+3")).toString()),
                () -> Assertions.assertEquals("+1/2*16x^2 1/4*(-24x)^4",Polinom.integral(CalculatorForm.transform("16x-24x^3")).toString()),
                () -> Assertions.assertEquals("+1/2*20x^2+1/3*9x^3+1/4*8x^4",Polinom.integral(CalculatorForm.transform("20x+9x^2+8x^3")).toString())

        );
    }

    @org.junit.jupiter.api.Test
    void divide() {
        assertAll(
                () -> Assertions.assertEquals("-2+x",Polinom.divide(CalculatorForm.transform("x^3-2x^2+6x-5"),CalculatorForm.transform("x^2-1")).toString()),
                () -> Assertions.assertEquals("+1+x^2",Polinom.divide(CalculatorForm.transform("x^3-2x^2+x-3"),CalculatorForm.transform("x-2")).toString()),
                () -> Assertions.assertEquals("+8+4x+3x^2",Polinom.divide(CalculatorForm.transform("3x^4-5x^3+2x^2-7x+4"),CalculatorForm.transform("x^2-3x+2")).toString())

        );
    }
}
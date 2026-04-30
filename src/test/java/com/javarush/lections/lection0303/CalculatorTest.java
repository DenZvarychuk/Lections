package com.javarush.lections.lection0303;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class CalculatorTest {
    Calculator calc = new Calculator();

    // parametric test
    public static Stream<Arguments> sourceAddTest() {
        return Stream.of(
                Arguments.of(1,1,2),
                Arguments.of(2,2,4)
        );
    }

    @Test
    void addShouldReturnSumFor2AndMinus1(){
        int actual = calc.add(1,1);
        int expected = 2;
        assertEquals(expected, actual);
    }

    @Test
    void addShouldReturnSumForNegativeNumbers(){
        int actual = calc.add(-1,-1);
        int expected = -2;
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @MethodSource("sourceAddTest")
    // for simple values
    //@CsvSource({"1,1,2", "-1,-1,-2", "0,0,0"})
    void testAdd(int a, int b, int expected) {
        int actual = calc.add(a, b);
        assertEquals(expected, actual);
    }

    @Test
     void divShouldThrowExceptionForDivisionByZero(){
        IllegalArgumentException exception =
                assertThrows(IllegalArgumentException.class, () -> calc.div(1, 0));
        assertEquals("Division by zero is not allowed", exception.getMessage());
    }

    @Test
    void divShouldNotThrowExceptionForDivisionByZero(){
        assertDoesNotThrow(() -> calc.div(1, 1));
    }
}

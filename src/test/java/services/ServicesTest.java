package services;

import static org.junit.jupiter.api.Assertions.*;

class ServicesTest {

    @org.junit.jupiter.api.Test
    void calcPlusTest() {
        double first, second;
        double max = 100000;
        double min = -100000;
        for (int i = 0; i < 100000; i++) {
            first = (Math.random() * ((max - min) + 1)) + min;
            second = (Math.random() * ((max - min) + 1)) + min;
            assertEquals(first + second, Double.parseDouble(Services.calc(first, "+", second)));
        }
    }

    @org.junit.jupiter.api.Test
    void calcDivTestDivisionByZero() {
        int first = 5;
        int second = 0;
        assertEquals("Division by zero!", Services.calc(first, "/", second));
    }

    @org.junit.jupiter.api.Test
    void calcTestWrongFormat() {
        int first = 6;
        int second = 12;
        assertEquals("Input has wrong format!", Services.calc(first, "wrong_format", second));
    }

    @org.junit.jupiter.api.Test
    void calcMinusTest() {
        double first, second;
        double max = 100000;
        double min = -100000;
        for (int i = 0; i < 100000; i++) {
            first = (Math.random() * ((max - min) + 1)) + min;
            second = (Math.random() * ((max - min) + 1)) + min;
            assertEquals(first - second, Double.parseDouble(Services.calc(first, "-", second)));
        }
    }

    @org.junit.jupiter.api.Test
    void calcMulTest() {
        double first, second;
        double max = 100000;
        double min = -100000;
        for (int i = 0; i < 100000; i++) {
            first = (Math.random() * ((max - min) + 1)) + min;
            second = (Math.random() * ((max - min) + 1)) + min;
            assertEquals(first * second, Double.parseDouble(Services.calc(first, "*", second)));
        }
    }

    @org.junit.jupiter.api.Test
    void calcDivTest() {
        double first, second;
        double max = 100000;
        double min = 1;
        for (int i = 0; i < 100000; i++) {
            first = (Math.random() * ((max - min) + 1)) + min;
            second = (Math.random() * ((max - min) + 1)) + min;
            assertEquals(first / second, Double.parseDouble(Services.calc(first, "/", second)));
        }
    }

    @org.junit.jupiter.api.Test
    void convertNumberSystemDecBinaryTest() {
        for (int i = 0; i < 100000; i++) {
            assertEquals(Integer.toBinaryString(i), Services.convertNumberSystem(String.valueOf(i), 10, 2));
        }
    }

    @org.junit.jupiter.api.Test
    void convertNumberSystemBinaryHexTest() {
        for (int i = 0; i < 1000; i++) {
            assertEquals(Integer.toHexString(i), Services.convertNumberSystem(Integer.toBinaryString(i), 2, 16));
        }
    }
}
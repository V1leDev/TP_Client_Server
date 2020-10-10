package services;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ServicesTest {

    // Normal Test
    @Test
    void calcPlusNormalInputTest() {
        // Case both numbers positive
        assertEquals(15, Double.parseDouble(Services.calc(5, "+", 10)));
        // Case first number negative and second number positive
        assertEquals(5, Double.parseDouble(Services.calc(-5, "+", 10)));
        // Case second number negative and first number positive
        assertEquals(-5, Double.parseDouble(Services.calc(5, "+", -10)));
        // Case both numbers negative
        assertEquals(-15, Double.parseDouble(Services.calc(-5, "+", -10)));
    }

    // Normal Test
    @Test
    void calcMinusNormalInputTest() {
        // Case both numbers positive
        assertEquals(200, Double.parseDouble(Services.calc(350, "-", 150)));
        // Case first number negative and second number positive
        assertEquals(-500, Double.parseDouble(Services.calc(-350, "-", 150)));
        // Case second number negative and first number positive
        assertEquals(500, Double.parseDouble(Services.calc(350, "-", -150)));
        // Case both numbers negative
        assertEquals(-200, Double.parseDouble(Services.calc(-350, "-", -150)));
    }

    // Normal Test
    @Test
    void calcMulNormalInputTest() {
        // Case both numbers positive
        assertEquals(100, Double.parseDouble(Services.calc(25, "*", 4)));
        // Case first number negative and second number positive
        assertEquals(-100, Double.parseDouble(Services.calc(-25, "*", 4)));
        // Case second number negative and first number positive
        assertEquals(-100, Double.parseDouble(Services.calc(25, "*", -4)));
        // Case both numbers negative
        assertEquals(100, Double.parseDouble(Services.calc(-25, "*", -4)));
    }

    // Normal Test
    @Test
    void calcDivNormalInputTest() {
        // Case both numbers positive
        assertEquals(500, Double.parseDouble(Services.calc(2000, "/", 4)));
        // Case first number negative and second number positive
        assertEquals(-500, Double.parseDouble(Services.calc(-2000, "/", 4)));
        // Case second number negative and first number positive
        assertEquals(-500, Double.parseDouble(Services.calc(2000, "/", -4)));
        // Case both numbers negative
        assertEquals(500, Double.parseDouble(Services.calc(-2000, "/", -4)));
    }

    // Wrong input test
    @Test
    void calcDivisionByZeroTest() {
        assertEquals("Division by zero!", Services.calc(5, "/", 0));
    }

    // Edge case test
    @Test
    void calcZeroDividedByNumberTest(){
        assertEquals(0, Double.parseDouble(Services.calc(0, "/", 100)));
    }

    // Edge case test
    @Test
    void calcPiDividedByETest(){
        assertEquals(Math.PI / Math.E, Double.parseDouble(Services.calc(Math.PI, "/", Math.E)));
    }

    // Wrong input test
    @Test
    void calcWrongFormatTest() {
        assertEquals("Input has wrong format!", Services.calc(6, "wrong_format", 12));
    }

    // Normal test
    @Test
    void convertNumberSystemDecBinaryTest() {
        for (int i = 0; i < 100000; i++) {
            assertEquals(Integer.toBinaryString(i), Services.convertNumberSystem(String.valueOf(i), 10, 2));
        }
    }

    // Normal test
    @Test
    void convertNumberSystemBinaryHexTest() {
        for (int i = 0; i < 1000; i++) {
            assertEquals(Integer.toHexString(i), Services.convertNumberSystem(Integer.toBinaryString(i), 2, 16));
        }
    }
}
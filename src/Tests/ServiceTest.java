package Tests;

import Server.Service;
import org.junit.jupiter.api.Assertions;

import static org.junit.jupiter.api.Assertions.*;

class ServiceTest {

    @org.junit.jupiter.api.Test
    void calcPlusTest() {
        double first, second;
        double max = 10000000;
        double min = -10000000;
        for (int i = 0; i < 10000000; i++) {
            first = (Math.random() * ((max - min) + 1)) + min;
            second = (Math.random() * ((max - min) + 1)) + min;
            Assertions.assertEquals(first + second, Service.calc(first, "+", second));
        }
    }

    @org.junit.jupiter.api.Test
    void calcMinusTest() {
        double first, second;
        double max = 10000000;
        double min = -10000000;
        for (int i = 0; i < 10000000; i++) {
            first = (Math.random() * ((max - min) + 1)) + min;
            second = (Math.random() * ((max - min) + 1)) + min;
            assertEquals(first - second, Service.calc(first, "-", second));
        }
    }

    @org.junit.jupiter.api.Test
    void calcMulTest() {
        double first, second;
        double max = 10000000;
        double min = -10000000;
        for (int i = 0; i < 10000000; i++) {
            first = (Math.random() * ((max - min) + 1)) + min;
            second = (Math.random() * ((max - min) + 1)) + min;
            assertEquals(first * second, Service.calc(first, "*", second));
        }
    }

    @org.junit.jupiter.api.Test
    void calcDivTest() {
        double first, second;
        double max = 10000000;
        double min = -10000000;
        for (int i = 0; i < 10000000; i++) {
            first = (Math.random() * ((max - min) + 1)) + min;
            second = (Math.random() * ((max - min) + 1)) + min;
            assertEquals(first / second, Service.calc(first, "/", second));
        }
    }

    @org.junit.jupiter.api.Test
    void convertNumberSystemDecBinaryTest() {
        for (int i = 0; i < 100000; i++) {
            assertEquals(Integer.toBinaryString(i), Service.convertNumberSystem(i, 10, 2));
        }
    }

    @org.junit.jupiter.api.Test
    void convertNumberSystemBinaryHexTest() {
        for (int i = 0; i < 1000; i++) {
            assertEquals(Integer.toHexString(i), Service.convertNumberSystem(Integer.parseInt(Integer.toBinaryString(i)), 2, 16));
        }
    }
}
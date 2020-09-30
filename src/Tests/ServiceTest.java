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
    void toBinaryTest() {
        for (int i = 0; i < 100000000; i++) {
            assertEquals(Integer.toBinaryString(i), Service.toBinary(i));
        }
    }

    @org.junit.jupiter.api.Test
    void toHexTest() {
        for (int i = 0; i < 100000000; i++) {
            assertEquals(Integer.toHexString(i), Service.toHex(i));
        }
    }

    @org.junit.jupiter.api.Test
    void toOctTest() {
        for (int i = 0; i < 100000000; i++) {
            assertEquals(Integer.toOctalString(i), Service.toOct(i));
        }
    }
}
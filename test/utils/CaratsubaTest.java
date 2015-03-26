/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static utils.Util.*;

/**
 *
 * @author andrey
 */
public class CaratsubaTest {

    public CaratsubaTest() {
    }

    @Before
    public void setUp() {
    }

    // тесты написаны только для важных методов
    @Test
    public void testNormalize() {
        int[] n = {49, 84, 106, 60, 25};
        String result = intArrayToRevertString(normalize(n));
        assertEquals("321489", result);

        int[] n1 = {-3, 4};
        result = intArrayToRevertString(normalize(n1));
        assertEquals("37", result);
    }

    @Test
    public void testDoMultiple() {
        int[] a;
        int[] b;
        String result;
        System.out.println("doMultiple");
        Caratsuba instance = new Caratsuba(3);

        a = stringToIntRevertArray("949927861734");
        b = stringToIntRevertArray("960331514894");
        result = intArrayToRevertString(normalize(instance.doMultiple(a, b)));
        assertEquals("912245662499030393666196", result);

        a = stringToIntRevertArray("12345");
        b = stringToIntRevertArray("123");
        result = intArrayToRevertString(normalize(instance.doMultiple(a, b)));
        assertEquals("1518435", result);

        a = stringToIntRevertArray("123");
        b = stringToIntRevertArray("12345");
        result = intArrayToRevertString(normalize(instance.doMultiple(a, b)));
        assertEquals("1518435", result);

        a = stringToIntRevertArray("123456789");
        b = stringToIntRevertArray("15241578750190521");
        result = intArrayToRevertString(normalize(instance.doMultiple(a, b)));
        assertEquals("1881676371789154860897069", result);
    }

    @Test
    public void testDoMultipleOld() {
        int[] a;
        int[] b;
        String result;
        System.out.println("testDoMultipleOld");

        a = stringToIntRevertArray("1234567");
        b = stringToIntRevertArray("1234567");
        result = intArrayToRevertString(normalize(multipleOld(a, b)));
        assertEquals("1524155677489", result);

        a = stringToIntRevertArray("12345");
        b = stringToIntRevertArray("123");
        result = intArrayToRevertString(normalize(multipleOld(a, b)));
        assertEquals("1518435", result);

        a = stringToIntRevertArray("123");
        b = stringToIntRevertArray("12345");
        result = intArrayToRevertString(normalize(multipleOld(a, b)));
        assertEquals("1518435", result);

        a = stringToIntRevertArray("123456789");
        b = stringToIntRevertArray("15241578750190521");
        result = intArrayToRevertString(normalize(multipleOld(a, b)));
        assertEquals("1881676371789154860897069", result);
    }
}
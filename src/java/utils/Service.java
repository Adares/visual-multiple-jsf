/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.math.BigInteger;
import static utils.Util.*;

public class Service {

    public static void main(String[] args) {
        long lBegin;
        long lEnd;
        // пусть у нас есть 2 числа
        String s1 = generateString(500);
        String s2 = generateString(500);

        System.out.println(s1.length());

        // получим обратные массивы
        int[] num1 = stringToIntRevertArray(s1);
        int[] num2 = stringToIntRevertArray(s2);

        lBegin = System.currentTimeMillis();
        int[] result1 = normalize(multipleOld(num1, num2));
        lEnd = System.currentTimeMillis();
        long lTimeOld = lEnd - lBegin;


        Caratsuba c = new Caratsuba(75);
        lBegin = System.currentTimeMillis();
        int[] result2 = normalize(c.doMultiple(num1, num2));
        lEnd = System.currentTimeMillis();
        long lTimeCaratsuba = lEnd - lBegin;

        BigInteger b1 = new BigInteger(s1);
        BigInteger b2 = new BigInteger(s2);
        lBegin = System.currentTimeMillis();
        BigInteger result3 = b1.multiply(b2);
        lEnd = System.currentTimeMillis();
        long lTimeBigInteger = lEnd - lBegin;


        printRevertArray("old ", result1);
        printRevertArray("cara", result2);
        System.out.println("big  " + result3);

        System.out.println("old  " + lTimeOld + "ms");
        System.out.println("cara " + lTimeCaratsuba + "ms");
        System.out.println("big  " + lTimeBigInteger + "ms");



    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import static utils.Util.*;

public class Caratsuba {

    private int MAX_LEN;

    public Caratsuba(int n) {
        this.MAX_LEN = n;
    }

    private int[] getSomePart(int[] a, int k, int n) {
        int[] result = new int[n - k];

        for (int i = 0; (i < n - k) && (i + k < a.length); i++) {
            result[i] = a[i + k];
        }
        return result;
    }

    public int[] doMultiple(int[] a, int[] b) {
        int n = Math.max(a.length, b.length);

        int k = n / 2;

        if (k < MAX_LEN) {
            return multipleOld(a, b);
        }

        int[] result = new int[n * 2 + 2];

        int[] a2 = getSomePart(a, 0, k);
        int[] a1 = getSomePart(a, k, n);

        int[] b2 = getSomePart(b, 0, k);
        int[] b1 = getSomePart(b, k, n);


        // найдем сумму частей первого числа
        int[] aSumAA = sumArrayEx(a1, a2);

        // найдем сумму частей второго числа
        int[] aSumBB = sumArrayEx(b1, b2);

        // произведение сумм частей
        int[] aMultSum = doMultiple(aSumAA, aSumBB);

        // произмедение младших частей
        int[] aMultA1B1 = doMultiple(a1, b1);

        // старших
        int[] aMultA2B2 = doMultiple(a2, b2);

        // промежуточное значение
        subArrayEx(aMultSum, (aMultA1B1));
        subArrayEx(aMultSum, (aMultA2B2));

        // сбросим первое слагаемое
        addArrayEx(result, aMultA1B1, k * 2);

        // прибавим второе слагаемое с необходимым сдвигом
        addArrayEx(result, aMultSum, k);

        // прибавим третье слагаемое с необходимым сдвигом
        addArrayEx(result, aMultA2B2, 0);
        return result;
    }

    // суммирование двух массивов со сдвигом shift
    private void addArrayEx(int[] a, int[] b, int shift) {
        int n = 0;
        // заполним до тех кто больше

        while (n < b.length) {
            a[n + shift] += b[n];
            n++;
        }
    }

    // вычитание массивов
    // возвращает ненормализованное число
    // а>b
    private void subArrayEx(int[] a, int[] b) {
        int n = 0;
//n < a.length && 
        while (n < b.length) {
            a[n] -= b[n];
            n++;
        }
    }

    // суммирование массивов
    private int[] sumArrayEx(int[] a, int[] b) {
        // нужно определить кто больше и в зависимости от этого поступать по-разному
        int[] result = new int[Math.max(a.length, b.length)];

        int n = 0;
        while (n < a.length && n < b.length) {
            result[n] = a[n] + b[n];
            n++;
        }

        // в случае разных длин массивов добъем старшие разряды
        while (n < a.length) {
            result[n] = a[n];
            n++;
        }

        // в случае разных длин массивов добъем старшие разряды
        while (n < b.length) {
            result[n] = b[n];
            n++;
        }

        return result;
    }
}

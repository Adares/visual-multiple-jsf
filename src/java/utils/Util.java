/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author andrey
 */
public class Util {

    // нормализует {10,15,20} в {0, 6, 3, 2,}
    // {-3,4} в {7,3}
    // тут реверсивная запись
    public static int[] normalize(int[] arr) {
        // использую List потому что трудно предугадать результирующий размер
        List<Integer> l = new ArrayList<>();

        int iOver = 0;
        int iOverMain = 0;
        for (int i = 0; i < arr.length; i++) {
            int iValue = arr[i] + iOver;
            if (iValue < 0) {
                iOverMain = Math.abs(iValue / 10) + 1;
                iValue += iOverMain * 10;
            }
            l.add(iValue % 10);
            iOver = iValue / 10 - iOverMain;
            iOverMain = 0;
        }

        // если iOver>0
        while (iOver > 0) {
            l.add(iOver % 10);
            iOver = iOver / 10;
        }

        // откусить ведущие нули, если они есть
        while (l.size() > 0 && l.get(l.size() - 1) == 0) {
            l.remove(l.size() - 1);
        }

        Integer[] result = l.toArray(new Integer[0]);
        return toPrimitive(result);
    }

    // реверс массива {1,2,3} <==> {3,2,1}
    public static int[] revertArray(int[] arr) {
        int[] aRes = new int[arr.length];

        for (int i = 0; i < arr.length; i++) {
            aRes[arr.length - 1 - i] = arr[i];
        }
        return aRes;
    }

    // суммирование массивов
    // возвращает ненормализованное число
    public static int[] sumArray(int[] a, int[] b) {
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

    public static int[] multipleOld(int[] a, int[] b) {
        int[] result = new int[a.length + b.length];

        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < b.length; j++) {
                result[i + j] += a[i] * b[j];
            }
        }

        return normalize(result);
    }

    public static void printArray(int[] a) {
        System.out.println(Arrays.toString(a));
    }

    public static void main(String[] args) {
    }

    public static int[] stringToIntRevertArray(String s) {
        int[] result = new int[s.length()];

        for (int i = 0; i < s.length(); i++) {
            // charAt ???
            result[s.length() - i - 1] = Integer.parseInt(s.substring(i, i + 1));
        }
        return result;
    }

    public static String intArrayToRevertString(int[] n) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < n.length; i++) {
            sb.append(n[n.length - i - 1]);
        }

        // удаление старших нулей
        if (sb.charAt(0) == '0') {
            sb.deleteCharAt(0);
        }

        return sb.toString();
    }

    public static int[] toPrimitive(Integer[] IntegerArray) {

        int[] result = new int[IntegerArray.length];
        for (int i = 0; i < IntegerArray.length; i++) {
            result[i] = IntegerArray[i].intValue();
        }
        return result;
    }

    public static void printRevertArray(String s, int[] n) {
        // Arrays.toSting
        System.out.print(s + " ");
        for (int i = 0; i < n.length; i++) {
            System.out.print(n[n.length - 1 - i]);
        }
        System.out.println();
    }

    public static String generateString(int n) {
        StringBuilder s = new StringBuilder(n);
        for (int i = 0; i < n; i++) {
            s.append((int) (Math.random() * 10));
        }

        return s.toString();
    }

    public static String printArray(String[][] s) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < s.length; i++) {
            for (int j = 0; j < s[i].length; j++) {
                sb.append(String.format("%4s", s[i][j]));
            }

            sb.append("\n");

            //добавим псевдоразделители
            if (i == 1 || i == s.length - 3 || i == s.length - 2) {
                for (int j = 0; j < s[i].length; j++) {
                    sb.append(String.format("%4s", "----"));
                }
                sb.append("\n");
            }

        }
        return sb.toString();
    }
}

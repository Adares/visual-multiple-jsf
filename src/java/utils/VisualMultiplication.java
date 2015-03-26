/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import static utils.Util.*;

/**
 *
 * @author andrey
 */
public class VisualMultiplication {

    // первое и второе записанное наоборот
    public int[] a;
    public int[] b;
    public int[][] someResult; // промежуточные результаты
    public int[] result;       // итого не нормированный
    public int[] normResult;   // итого нормированный
    public String[][] sResult;

    public VisualMultiplication(int[] a, int[] b) {
        this.a = a;
        this.b = b;

        someResult = new int[b.length][a.length];
        result = new int[a.length + b.length];
        normResult = new int[a.length + b.length];
    }

    public String[][] doMultipleEx() {
        // заполним матрицу промежуточных результатов
        // размерность массива равно наименьшей длине входящих массивов

        for (int i = 0; i < b.length; i++) {
            for (int j = 0; j < a.length; j++) {
                someResult[i][j] = b[i] * a[j];
            }
        }
        // получили ненормированный someResult
        // получим ненормированный result

        for (int i = 0; i < result.length; i++) {
            for (int j = i; j >= 0 && j > i - a.length; j--) {
                if (j >= someResult.length) {
                    continue;
                }
                result[i] += someResult[j][i - j];
            }
        }

        normResult = normalize(result);

        // сбросим все в массив строк
        allToChar();

        return sResult;
    }

    // скидываем одномерный массив в двумерный
    // тут мы автоматически переворачиваем реверсивные массивы
    // можно задуматься о быстродействии и копированнии массивов напрямую
    private void oneToDouble(int iIndex, int[] aOne, String[][] aDouble) {
        for (int i = 0; i < aOne.length; i++) {
            aDouble[iIndex][aDouble[iIndex].length - 1 - i] = Integer.toString(aOne[i]);
        }
    }

    private void allToChar() {
        sResult = new String[4 + someResult.length][result.length];
        for (int i = 0; i < sResult.length; i++) {
            for (int j = 0; j < sResult[i].length; j++) {
                sResult[i][j] = "";
            }
        }


        int iIndex = 0;
        oneToDouble(iIndex, a, sResult);

        iIndex++;
        oneToDouble(iIndex, b, sResult);

        // далее SomeResult с хитрым сдвигом
        iIndex++;
        for (int i = 0; i < someResult.length; i++) {
            for (int j = 0; j < someResult[i].length; j++) {
                sResult[i + iIndex][sResult[i].length - 1 - j - i] = Integer.toString(someResult[i][j]);
            }
        }

        // далее ненормированный result
        iIndex += someResult.length;
        oneToDouble(iIndex, result, sResult);

        // далее нормированный result
        iIndex++;
        oneToDouble(iIndex, normResult, sResult);
    }

    public static void main(String[] args) {
        int[] m1 = {5, 5, 5, 5, 5};
        int[] m2 = {5};

        VisualMultiplication m = new VisualMultiplication(m1, m2);
        m.doMultipleEx();
    }
}

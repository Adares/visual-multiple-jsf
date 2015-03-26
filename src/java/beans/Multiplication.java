/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.math.BigInteger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import utils.Caratsuba;
import static utils.Util.multipleOld;
import static utils.Util.normalize;
import static utils.Util.stringToIntRevertArray;

@Named
@RequestScoped
public class Multiplication {

    private String s1;     //строковое представление числа 1 
    private String s2;     //строковое представление числа 2 
    private String result; //строковое представление результата
    
    // дополнительные поля
    private String resultMultipleOld;
    private String resultBigInteger;
    private String info;

    public String getResultMultipleOld() {
        return resultMultipleOld;
    }

    public String getResultBigInteger() {
        return resultBigInteger;
    }

    public String getInfo() {
        return info;
    }

    public String getS1() {
        return s1;
    }

    public void setS1(String s1) {
        this.s1 = s1;
    }

    public String getS2() {
        return s2;
    }

    public void setS2(String s2) {
        this.s2 = s2;
    }

    public String getResult() {
        return result;
    }

    // генерирование случайных чисел заданной длины
    public void generate() {
        this.s1 = utils.Util.generateString(2000);
        this.s2 = utils.Util.generateString(2000);
    }

    // вычисление и анализ
    public void calculate() {
        // здесь мы должны вычислить и вывести все необходимое           
        long lBegin;

        // получим обратные массивы
        int[] num1 = stringToIntRevertArray(s1);
        int[] num2 = stringToIntRevertArray(s2);

        // умножение методом Карацубы
        Caratsuba c = new Caratsuba(50);
        lBegin = System.currentTimeMillis();
        int[] resultIntCaratsuba = normalize(c.doMultiple(num1, num2));
        long lTimeCaratsuba = System.currentTimeMillis() - lBegin;

        // обычное умножение
        lBegin = System.currentTimeMillis();
        int[] resultIntMultipleOld = normalize(multipleOld(num1, num2));
        long lTimeMultipleOld = System.currentTimeMillis() - lBegin;

        // умножение с помощью BigInteger
        BigInteger b1 = new BigInteger(s1);
        BigInteger b2 = new BigInteger(s2);
        lBegin = System.currentTimeMillis();
        BigInteger resultIntBigInteger = b1.multiply(b2);
        long lTimeBigInteger = System.currentTimeMillis() - lBegin;

        this.result = utils.Util.intArrayToRevertString(resultIntCaratsuba);
        this.resultMultipleOld = utils.Util.intArrayToRevertString(resultIntMultipleOld);
        this.resultBigInteger = resultIntBigInteger.toString();

        StringBuilder sb = new StringBuilder();
        if (!resultMultipleOld.equals(this.result)) {
            sb.append("Ошибка! Результаты разные\n\n");
        } else {
            sb.append("Результат проверен\n\n");
        }

        sb.append("Время выполнения, ms\n");
        sb.append("Школьное умножение ").append(lTimeMultipleOld).append("\n");
        sb.append("Методом Карацубы  ").append(lTimeCaratsuba).append("\n");
        sb.append("BigInteger  ").append(lTimeBigInteger).append("\n");
        this.info = sb.toString();
    }
}

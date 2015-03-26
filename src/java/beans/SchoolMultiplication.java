/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import static utils.Util.*;
import utils.VisualMultiplication;

@Named
@RequestScoped
public class SchoolMultiplication {

    private String s1;     //строковое представление числа 1 
    private String s2;     //строковое представление числа 2 
    private String result; //строковое представление результата

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
        s1 = utils.Util.generateString(10);
        s2 = utils.Util.generateString(10);
    }

    // вычисление и анализ
    public void calculate() {
        VisualMultiplication vm = new VisualMultiplication(stringToIntRevertArray(s1), stringToIntRevertArray(s2));
        String[][] s = vm.doMultipleEx();
        result = printArray(s);
    }
}

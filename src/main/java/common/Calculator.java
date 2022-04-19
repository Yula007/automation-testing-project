package common;

/**
 * The type Calculator.
 */
public class Calculator {

    /**
     * Calc integer.
     *
     * @param firstOperator  the first operator
     * @param secondOperator the second operator
     * @param action         the action
     * @return the integer
     */
    public Integer calc(int firstOperator, int secondOperator, String action){

     try {
         switch (action){
             case "+": return firstOperator + secondOperator;
             case "-": return firstOperator - secondOperator;
             case "*": return firstOperator * secondOperator;
             case "/": return firstOperator / secondOperator;
             default: return -1;
         }
     }catch(Exception e){
         return 0;

        }
    }
}

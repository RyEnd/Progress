/*
 * A very simple arithmatic calculator. Degraded by it's poor conversion to
 * double and no further handling.
 */
package com.tsg.mavenproject1;

/**
 *
 * @author ryanbmolnar@gmail.com
 */
public class SimpleCalculatorLogic {
    public double calculate(String calcOperator, int calcOperand1, int calcOperand2)
    {
        double calcAnswer = 0;
        
        switch (calcOperator) {
            case "*":
                calcAnswer = calcOperand1 * calcOperand2;
                break;
            case "/":
                calcAnswer = calcOperand1 / calcOperand2;
                break;
            case "+":
                calcAnswer = calcOperand1 + calcOperand2;
                break;
            case "-":
                calcAnswer = calcOperand1 - calcOperand2;
                break;
                
        }
        return calcAnswer;
    }
}
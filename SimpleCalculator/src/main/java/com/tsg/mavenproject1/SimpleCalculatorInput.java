package com.tsg.mavenproject1;

/**
 *
 * @author ryanbmolnar@gmail.com
 */
public class SimpleCalculatorInput {
    private static int operand1, operand2; 
    private static double answer;
    private static String operator;    
    
    public static void getUserInput()
    {
        boolean isTrue = true;
        operator = "";
        //our hardcoded min and max values
        final int MIN_VALUE = -100;
        final int MAX_VALUE = 100;
        
        //import our logic and IO classes
        SimpleCalculatorLogic scl = new SimpleCalculatorLogic();
        ConsoleIO fido = new ConsoleIO();
        
        while (isTrue == true)
        { 
            operator = fido.getString("Please enter the operation you would like to perform (add(+), subtract(-), multiply(*), divide(/), or 'quit'.\n");
            
            if ("quit".equals(operator))
            {
                fido.printString("Thank you for playing.");
                isTrue = false;
            } else
            {
                //ask for two bound ints, returns the solution to the equation via the logic object
                operator = fido.validateOperation(operator);
                operand1 = fido.getInt("Please enter the first operand (between -100 and 100) that you would like to operate on!", MIN_VALUE, MAX_VALUE);
                operand2 = fido.getInt("Please enter the second operand (between -100 and 100) that you would like to operate on!", MIN_VALUE, MAX_VALUE);
                answer = scl.calculate(operator, operand1, operand2);
                fido.printString("The final equation is " + operand1 + " " + operator + " " + operand2 + " = " + answer);
            }
        }
    }
}
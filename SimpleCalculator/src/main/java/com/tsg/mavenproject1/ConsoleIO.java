/**
 * A simple IO class who's object may assist in the processing and validation
 * of console system input in the form of bound and unbound integers, floats
 * and doubles, as w ell as strings and operators (*-/+).
 */

package com.tsg.mavenproject1;

import java.util.Scanner;

/**
 *
 * @author ryanbmolnar@gmail.com
 */
class ConsoleIO {

    Scanner sc = new Scanner(System.in);

    public String validateOperation(String operator){
        boolean exception = true;
        while (exception){
            if (operator.equals("+")||operator.equals("-")||operator.equals("/")||operator.equals("*")){
                exception = false;
            } else {
                System.out.println("This is not a valid operation. \nPlease enter a valid operator: (add(+), subtract(-), divide(/), or multiply(*))");
                operator = sc.nextLine();
                exception = true;
            }
        }
        return operator;
    }
    
    public int getInt(String prompt) {
        int userInt = 0;
        boolean exception = true;
        
        while (exception) {
            System.out.println(prompt);
            String input = sc.nextLine();
            try {
                userInt = Integer.parseInt(input);
                exception = false;
            } catch (Exception ex) {
                System.out.println("That wasn't a number, please type an integer.");
                exception = true;
            }
        }
        return userInt;
    }

    public int getInt(String prompt, int min, int max) {
        int userInt = 0;
        boolean exception = true;
        
        while (exception) {
            System.out.println(prompt);
            String input = sc.nextLine();
            
            try{
                userInt = Integer.parseInt(input);
                try{
                    testIntForRange(min, max, userInt);
                    exception = false;
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                    exception = true;
                }
            } catch (Exception ex) {
                System.out.println("That wasn't a number, please type an integer.");
                exception = true;
            }
        }

        return userInt;
    }
            
    public void testIntForRange(int min, int max, int userInput) throws Exception {
        if (userInput < min) {
            throw new Exception("The number you entered is less than " + min + ".");
        } else if (userInput > max) {
            throw new Exception("The number you entered is larger than " + max + ".");
        }
    }

    public float getFloat(String prompt) {
        float userFloat = 0;
        boolean exception = true;
        
        while(exception){
            System.out.println(prompt);
            String input = sc.nextLine();
            try{
                userFloat = Float.parseFloat(input);
                exception = false;
            } catch (Exception ex){
                System.out.println("That wasn't a number, please type an integer.");
                exception = true;
            }
        }
        return userFloat;
    }

    public float getFloat(String prompt, float min, float max) {
        float userFloat = 0;
        boolean exception = true;
        
        while (exception) {
            System.out.println(prompt);
            String input = sc.nextLine();
            
            try{
                userFloat = Float.parseFloat(input);
                try{
                    testFloatForRange(min, max, userFloat);
                    exception = false;
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                    exception = true;
                }
            } catch (Exception ex) {
                System.out.println("That wasn't a number, please type an integer.");
                exception = true;
            }
        }

        return userFloat;
    }
    
    public void testFloatForRange(float min, float max, float userInput) throws Exception {
        if (userInput < min) {
            throw new Exception("The number you entered is less than " + min + ".");
        } else if (userInput > max) {
            throw new Exception("The number you entered is larger than " + max + ".");
        }
    }
    
    public double getAndReturnDouble(String prompt) {
        double userDouble = 0;
        boolean exception = true;
        
        while(exception){
            System.out.println(prompt);
            String input = sc.nextLine();
            try{
                userDouble = Double.parseDouble(input);
                exception = false;
            } catch (Exception ex){
                System.out.println("That wasn't a number, please type an integer.");
                exception = true;
            }
        }
        return userDouble;
    }

    public double getDouble(String prompt, double min, double max) {
        double userDouble = 0;
        boolean exception = true;
        
        while (exception) {
            System.out.println(prompt);
            String input = sc.nextLine();
            
            try{
                userDouble = Double.parseDouble(input);
                try{
                    testDoubleForRange(min, max, userDouble);
                    exception = false;
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                    exception = true;
                }
            } catch (Exception ex) {
                System.out.println("That wasn't a number, please type an integer.");
                exception = true;
            }
        }

        return userDouble;
    }

    public void testDoubleForRange(double min, double max, double userInput) throws Exception {
        if (userInput < min) {
            throw new Exception("The number you entered is less than " + min + ".");
        } else if (userInput > max) {
            throw new Exception("The number you entered is larger than " + max + ".");
        }
    }
    
    public void printString(String prompt) {
        System.out.println(prompt);
    }
    
    public String getString(String prompt) {
        System.out.println(prompt);
        return sc.nextLine();
    }
}

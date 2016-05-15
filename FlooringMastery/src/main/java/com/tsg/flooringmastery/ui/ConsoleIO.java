package com.tsg.flooringmastery.ui;

import java.util.Scanner;

/**
 *
 * @author ryanbmolnar@gmail.com
 */

public class ConsoleIO {

    Scanner sc = new Scanner(System.in);

    public int getAndReturnInt(String prompt) {
        int userInt;
        Scanner sc = new Scanner(System.in);
        System.out.println(prompt);
        userInt = sc.nextInt();
        return userInt;
    }

    public int getAndReturnBoundInt(String prompt, int min, int max) throws Exception {
        boolean valid = false;
        String userBoundInput;
        int finalUserInput;

        do {
            System.out.println(prompt);
            userBoundInput = sc.nextLine();
            newTestForRange(min, max, userBoundInput);
            if ((getIsValidResponse()) == false) {
                System.out.println(getPrompt());
                valid = false;
            } else {
                valid = true;
            }
        } while (valid == false);

        finalUserInput = Integer.parseInt(userBoundInput);
        return finalUserInput;
    }

    public String getAndReturnString(String prompt) {
        String userString;
        Scanner sc = new Scanner(System.in);
        System.out.println(prompt);
        userString = sc.nextLine();
        return userString;
    }

    public Float getAndReturnFloat(String prompt) {
        Float userFloat;
        Scanner sc = new Scanner(System.in);
        System.out.println(prompt);
        userFloat = sc.nextFloat();
        return userFloat;
    }

    public Float getAndReturnBoundFloat(String prompt, float min, float max) {
        Float userBoundFloat;
        do {
            Scanner sc = new Scanner(System.in);
            System.out.println(prompt);
            userBoundFloat = sc.nextFloat();
        } while (userBoundFloat > max || userBoundFloat < min);
        return userBoundFloat;
    }

    public Double getAndReturnDouble(String prompt) {
        Double userDouble;
        Scanner sc = new Scanner(System.in);
        System.out.println(prompt);
        userDouble = sc.nextDouble();
        return userDouble;
    }

    public Double getAndReturnBoundDouble(String prompt, double min, double max) {
        Double userBoundDouble;
        do {
            Scanner sc = new Scanner(System.in);
            System.out.println(prompt);
            userBoundDouble = sc.nextDouble();
        } while (userBoundDouble > max || userBoundDouble < min);
        return userBoundDouble;
    }

    public void printString(String prompt) {
        System.out.println(prompt);
    }
    private boolean validResponse = false;
    private String prompt = "";

    public boolean getIsValidResponse() {
        return validResponse;
    }

    public void setValid(boolean valid) {
        this.validResponse = validResponse;
    }

    public String getPrompt() {
        return prompt;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    public void newTestForRange(int min, int max, String userInput) throws Exception {
        boolean exception = false;
        int userInput2;
        if (min > max) {
            throw new Exception("Slow down, that's too much.");
        }
        try {
            Integer.parseInt(userInput);
        } catch (Exception e) {
            exception = true;
        }

        if (exception == true) {
            validResponse = false;
            prompt = "Your entry was not an acceptable number. Try again.";
        } else {
            userInput2 = Integer.parseInt(userInput);
            if (userInput2 < min) {
                validResponse = false;
                prompt = "The number you entered is out of bounds: Too small. ";
            } else if (userInput2 > max) {
                validResponse = false;
                prompt = "The number you entered is out of bounds: Too large.";
            } else {
                validResponse = true;
                prompt = "";
            }
        }
    }
}

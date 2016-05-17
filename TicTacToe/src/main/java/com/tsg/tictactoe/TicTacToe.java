/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsg.tictactoe;

import java.util.Scanner;

/**
 *
 * @author ryanbmolnar@gmail.com
 */
public class TicTacToe {

    private static char[][] board = new char[3][3];

    public static void main(String[] args) {
        initBoard();
        System.out.println("Welcome to TicTacToe!");
        displayBoard();
        playGame();
        playAgain();
    }

    public static void initBoard() {
        // fills up the board with blanks
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                board[r][c] = ' ';
            }
        }
    }

    public static void displayBoard() {
        System.out.println("  0  " + board[0][0] + "|" + board[0][1] + "|" + board[0][2]);
        System.out.println("    --+-+--");
        System.out.println("  1  " + board[1][0] + "|" + board[1][1] + "|" + board[1][2]);
        System.out.println("    --+-+--");
        System.out.println("  2  " + board[2][0] + "|" + board[2][1] + "|" + board[2][2]);
        System.out.println("     0 1 2 ");
    }

    public static void displayBoard2() {
        for (int r = 0; r < 3; r++) {
            System.out.print("\t" + r + " ");
            for (int c = 0; c < 3; c++) {
                System.out.print(board[r][c] + " ");
            }
            System.out.println();
        }
        System.out.println("\t  0 1 2 ");
    }

    private static void playGame() {
        int moves = 0;

        do {
            if (moves % 2 == 0) {
                playerOne();
                displayBoard();
            } else {
                playerTwo();
                displayBoard();
            }
            moves++;
        } while (((board[0][0] != ' ' && ((board[0][0] == board[1][0] && board[1][0] == board[2][0]) || (board[0][0] == board[0][1] && board[0][1] == board[0][2]) || (board[0][0] == board[1][1] && board[1][1] == board[2][2]))) || (board[0][1] != ' ' && (board[0][1] == board[1][1] && board[1][1] == board[2][1])) || (board[0][2] != ' ' && ((board[0][2] == board[1][1] && board[1][1] == board[2][0]) || (board[0][2] == board[1][2] && board[1][2] == board[2][2]))) || (board[1][2] != ' ' && (board[1][2] == board[1][1] && board[1][1] == board[1][0])) || (board[2][2] != ' ' && (board[2][2] == board[2][1] && board[2][1] == board[2][0]))) == false && moves < 9);

        if (((moves == 9) && ((board[0][0] != ' ' && ((board[0][0] == board[1][0] && board[1][0] == board[2][0]) || (board[0][0] == board[0][1] && board[0][1] == board[0][2]) || (board[0][0] == board[1][1] && board[1][1] == board[2][2]))) || (board[0][1] != ' ' && (board[0][1] == board[1][1] && board[1][1] == board[2][1])) || (board[0][2] != ' ' && ((board[0][2] == board[1][1] && board[1][1] == board[2][0]) || (board[0][2] == board[1][2] && board[1][2] == board[2][2]))) || (board[1][2] != ' ' && (board[1][2] == board[1][1] && board[1][1] == board[1][0])) || (board[2][2] != ' ' && (board[2][2] == board[2][1] && board[2][1] == board[2][0]))) == false)) {
            System.out.println("Ya tied, that sucks.");
        } else if (moves % 2 == 0) {
            System.out.println("Player two wins!");
        } else {
            System.out.println("Player one wins!");
        }
    }

    private static void playerOne() {

        System.out.println("Player One, you are up! You are placing X's!");

        int[] coordArray = pickCoords();

        board[coordArray[0]][coordArray[1]] = 'X';
    }

    private static void playerTwo() {

        System.out.println("Player Two, you are up! You are placing O's");
        System.out.println("Select the X coordinate for where you would like to play.");

        int[] coordArray = pickCoords();

        board[coordArray[0]][coordArray[1]] = 'O';
    }

    private static void playAgain() {
        Scanner sc = new Scanner(System.in);
        String userInput;

        System.out.println("Would you like to play again? Y/N");
        userInput = sc.nextLine();

        if (userInput.equalsIgnoreCase("y")) {
            initBoard();
            displayBoard();
            playGame();
            playAgain();
        } else {
            System.out.println("Thanks for playing!");
        }
    }

    private static int[] pickCoords() {
        int xcoord;
        int ycoord;
        boolean answer = false;
        Scanner sc = new Scanner(System.in);

        do {
            System.out.println("Select the X coordinate for where you would like to play.");

            do {
                xcoord = sc.nextInt();
            } while ((2 < xcoord || xcoord < 0));

            System.out.println("Now select the Y coordinate for where you would like to play.");

            do {
                ycoord = sc.nextInt();
            } while ((2 < ycoord || ycoord < 0));

            if (board[xcoord][ycoord] != ' ') {
                System.out.println("Yo dude, that spot is already taken, try again.");
            } else {
                answer = true;
            }
        } while (!answer);

        int[] coordArray = {xcoord, ycoord};
        return coordArray;
    }
}

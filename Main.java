import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        // Variable to represent the size of our tic tac toe board
        int n = 3;

        // nxn array that represents our tic tac toe board
        char[][] board = new char[n][n];

        // Initialize our board with dashes (empty positions)
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = '-';
            }
        }

        // Create a Scanner for asking the players for their names
        Scanner in = new Scanner(System.in);
        System.out.println("Tic Tac Toe is ready for play!\n");
        System.out.print("What is your name, player 1: ");
        String p1 = in.nextLine();
        System.out.print("What is your name, player 2: ");
        String p2 = in.nextLine();

        // Create a player1 boolean that is true if it is player 1's turn and false if it is player 2's turn
        boolean player1 = true;

        // Create a gameEnded boolean and use it as the condition in the while loop
        boolean gameEnded = false;
        while (!gameEnded) {

            // Draw the board
            drawBoard(board);

            // Print whose turn it is
            if (player1) {
                System.out.println(p1 + "'s Turn (x):");
            } else {
                System.out.println(p2 + "'s Turn (o):");
            }

            // Create a char variable that stores either 'x' or 'o' based on what player's turn it is
            char c = '-';
            if (player1) {
                c = 'x';
            } else {
                c = 'o';
            }

            // Create row and col variables which represent indexes that correspond to a position on our board
            int row = 0;
            int col = 0;

            // Only break out of the while loop once the user enters a valid position
            while (true) {

                // Ask the user for what position they want to place their x or o
                System.out.print("Enter a row number: ");
                row = in.nextInt();
                System.out.print("Enter a column number: ");
                col = in.nextInt();

                in.nextLine(); // Consume newline left-over

                // Check if the row and col are outside of the board
                if (row < 0 || col < 0 || row >= n || col >= n) {
                    System.out.println("This position is off the bounds of the board! Try again.");
                } else if (board[row][col] != '-') {
                    System.out.println("Someone has already made a move at this position! Try again.");
                } else {
                    break;
                }
            }

            // Set the position on the board at row, col to c
            board[row][col] = c;

            // Check to see if either player has won
            char winner = playerHasWon(board);
            if (winner == 'x') {
                System.out.println(p1 + " has won!");
                gameEnded = true;
            } else if (winner == 'o') {
                System.out.println(p2 + " has won!");
                gameEnded = true;
            } else {
                // If neither player has won, check for a tie
                if (boardIsFull(board)) {
                    System.out.println("It's a tie!");
                    gameEnded = true;
                } else {
                    // Switch turns
                    player1 = !player1;
                }
            }
        }

        // Draw the board at the end of the game
        drawBoard(board);
    }

    // Function to draw the tic tac toe board
    public static void drawBoard(char[][] board) {
        System.out.println("Board:");
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    // Function to check if someone has won and return the winning char
    public static char playerHasWon(char[][] board) {
        // Check each row
        for (int i = 0; i < board.length; i++) {
            if (board[i][0] != '-' && board[i][0] == board[i][1] && board[i][1] == board[i][2]) {
                return board[i][0];
            }
        }
        // Check each column
        for (int j = 0; j < board[0].length; j++) {
            if (board[0][j] != '-' && board[0][j] == board[1][j] && board[1][j] == board[2][j]) {
                return board[0][j];
            }
        }
        // Check diagonals
        if (board[0][0] != '-' && board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
            return board[0][0];
        }
        if (board[0][2] != '-' && board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
            return board[0][2];
        }
        // Return null character if no winner
        return '\0';
    }

    // Function to check if all positions on the board have been filled
    public static boolean boardIsFull(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == '-') {
                    return false;
                }
            }
        }
        return true;
    }
}

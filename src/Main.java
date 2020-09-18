import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Main {
    public static void printField(char[][] field) {
        System.out.println("---------");
        for (int i = 0; i < 3; i++) {
            System.out.printf(
                    "| %c %c %c |%n",
                    field[i][0],
                    field[i][1],
                    field[i][2]
            );
        }
        System.out.println("---------");
    }

    public static int getCountThreeInRow(char[][] field, char c) {
        int count = 0;
        int row = 0, column = 0, diagonal = 0, revDiagonal = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (field[i][j] == c) row++;
                if (field[j][i] == c) column++;
            }
            if (row == 3) count++;
            if (column == 3) count++;
            row = 0; column = 0;
            if (field[i][i] == c) diagonal++;
            if (field[i][2-i] == c) revDiagonal++;
        }
        if (diagonal == 3) count++;
        if (revDiagonal == 3) count++;
        return count;
    }

    public static boolean checkCountXO(char[][] field) {
        int x = 0, o = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (field[i][j] == 'X') x++;
                else if (field[i][j] == 'O') o++;
            }
        }
        return Math.abs(x - o) > 1;
    }

    public static char[][] convertToMatrix(String state) {
        char[][] field = new char[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                field[i][j] = state.charAt(i*3+j);
            }
        }
        return field;
    }

    public static boolean checkImpossibility(char[][] field, int countO, int countX) {
        return (countO > 0 && countX  > 0) || checkCountXO(field);
    }

    public static boolean hasEmptyCell(String state) {
        return IntStream.range(0, state.length()).anyMatch(i -> state.charAt(i) == '_');
    }

    public static boolean checkDraw(String state, int countO, int countX) {
        return !hasEmptyCell(state) && countO == 0 && countX == 0;
    }

    public static boolean checkGameNotFinished(String state, int countO, int countX) {
        return hasEmptyCell(state) && countO == 0 && countX == 0;
    }

    public static void checkState(String state, char[][] field) {
        int countX = getCountThreeInRow(field, 'X');
        int countO = getCountThreeInRow(field, 'O');

        if (checkImpossibility(field, countX, countO)) System.out.println("Impossible");
        else if (checkDraw(state, countX, countO)) System.out.println("Draw");
        else if (checkGameNotFinished(state, countX, countO)) System.out.println("Game not finished");
        else if (countX == 1) System.out.println("X wins");
        else if (countO == 1) System.out.println("O wins");
    }

    public static char[][] enterCell(char[][] field, int col, int row) {
        return field;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter cells: ");
        String state = scanner.nextLine();

        state = state.toUpperCase();
        if (state.length() < 9) state = state + "_".repeat(9 - state.length());
        char[][] field = convertToMatrix(state);

        printField(field);

        while (true) {
            try {
                System.out.print("Enter the coordinates: ");
                int col = scanner.nextInt();
                int row = scanner.nextInt();
                if (col > 3 || row > 3 || col < 0 || row < 0) {
                    System.out.println("Coordinates should be from 1 to 3!");
                } else if (field[3-row][col - 1] != '_') {
                    System.out.println("This cell is occupied! Choose another one!");
                } else {
                    field[3-row][col-1] = 'X';
                    break;
                }
            } catch (InputMismatchException err) {
                System.out.println("You should enter numbers!");
                scanner.nextLine();
            }
        }

        printField(field);

    }
}

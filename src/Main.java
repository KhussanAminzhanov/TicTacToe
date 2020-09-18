import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;

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

    public static boolean hasEmptyCell(char[][] field) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (field[i][j] == ' ') return true;
            }
        }
        return false;
    }

    public static boolean checkDraw(char[][] field, int countO, int countX) {
        return !hasEmptyCell(field) && countO == 0 && countX == 0;
    }

    public static boolean checkGameNotFinished(char[][] field, int countO, int countX) {
        return hasEmptyCell(field) && countO == 0 && countX == 0;
    }

    public static String checkField(char[][] field) {
        int countX = getCountThreeInRow(field, 'X');
        int countO = getCountThreeInRow(field, 'O');

        if (checkImpossibility(field, countX, countO)) return "Impossible";//System.out.println("Impossible");
        else if (checkDraw(field, countX, countO)) return "Draw"; //System.out.println("Draw");
        else if (checkGameNotFinished(field, countX, countO)) return "Game not finished"; //System.out.println("Game not finished");
        else if (countX == 1) return "X wins"; //System.out.println("X wins");
        else if (countO == 1) return "O wins"; //System.out.println("O wins");
        return "";
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String state;
        char[][] field = convertToMatrix(" ".repeat(9));
        char move = 'O';
        printField(field);
        while (hasEmptyCell(field)) {
            try {
                System.out.print("Enter the coordinates: ");
                int col = scanner.nextInt();
                int row = scanner.nextInt();
                if (col > 3 || row > 3 || col < 0 || row < 0) {
                    System.out.println("Coordinates should be from 1 to 3!");
                } else if (field[3-row][col - 1] != ' ') {
                    System.out.println("This cell is occupied! Choose another one!");
                } else {
                    move = move == 'O' ? 'X' : 'O';
                    field[3-row][col-1] = move;
                    state = checkField(field);
                    printField(field);
                    if (Objects.equals(state, "X wins") || Objects.equals(state, "O wins")) break;
                }
            } catch (InputMismatchException err) {
                System.out.println("You should enter numbers!");
                scanner.nextLine();
            }
        }

        System.out.println(checkField(field));
    }
}

import java.util.Scanner;
import java.util.stream.IntStream;

public class Main {
    public static void printField(String field) {
        System.out.println("---------");
        for (int i = 0; i < 3; i++) {
            System.out.printf(
                    "| %c %c %c |%n",
                    field.charAt(i*3),
                    field.charAt(1+(i*3)),
                    field.charAt(2+(i*3))
            );
        }
        System.out.println("---------");
    }

    public static int[] getCountThreeInRow(char[][] field, char c) {
        // count[0] = row
        // count[1] = column
        // count[2] = diagonal
        int[] count = new int[3];
        int row = 0, column = 0, diagonal = 0, revDiagonal = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (field[i][j] == c) row++;
                if (field[j][i] == c) column++;
            }
            if (row == 3) count[0]++;
            if (column == 3) count[1]++;
            row = 0; column = 0;
            if (field[i][i] == c) diagonal++;
            if (field[i][2-i] == c) revDiagonal++;
        }
        if (diagonal == 3) count[2]++;
        if (revDiagonal == 3) count[2]++;
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

    public static int getSum(int[] arr) {
        int sum = 0;
        for (int j : arr) sum += j;
        return sum;
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

    public static void checkState(String state) {
        state = state.toUpperCase();
        if (state.length() < 9) state = state + "_".repeat(9 - state.length());
        char[][] field = convertToMatrix(state);
        int countX = getSum(getCountThreeInRow(field, 'X'));
        int countO = getSum(getCountThreeInRow(field, 'O'));

        if (checkImpossibility(field, countX, countO)) System.out.println("Impossible");
        else if (checkDraw(state, countX, countO)) System.out.println("Draw");
        else if (checkGameNotFinished(state, countX, countO)) System.out.println("Game not finished");
        else if (countX == 1) System.out.println("X wins");
        else if (countO == 1) System.out.println("O wins");
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter cells: ");
        String state = input.nextLine();
        printField(state);
        checkState(state);
    }
}

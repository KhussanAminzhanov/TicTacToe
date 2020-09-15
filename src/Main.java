import java.util.Scanner;

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
                if (field[i][j] == 'x') x++;
                else if (field[i][j] == 'o') o++;
            }
        }
        return Math.abs(x - o) >= 2;
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

    public static boolean checkImpossibility(char[][] field) {
        int countX = getSum(getCountThreeInRow(field, 'x'));
        int countO = getSum(getCountThreeInRow(field, 'o'));
        return (countO < 1 && countX  < 1) || checkCountXO(field);
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter cells: ");
        String state = input.nextLine();
        if (state.length() < 9) state = state + ".".repeat(9 - state.length());
        printField(state);
        char[][] field = convertToMatrix(state);
        System.out.println(checkCountXO(field));
    }
}

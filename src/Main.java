import java.util.Scanner;

public class Main {
    public static void printField(String field) {
        System.out.println("---------");
        for (int i = 0; i < 3; i++) {
            System.out.println(
                    String.format(
                        "| %c %c %c |",
                        field.charAt(i*3),
                        field.charAt(1+(i*3)),
                        field.charAt(2+(i*3))
                    )
            );
        }
        System.out.println("---------");
    }

    public static boolean checkCountXO(char[][] field) {
        int x = 0, o = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (field[i][j] == 'x' || field[i][j] == 'X') x++;
                else if (field[i][j] == 'o' || field[i][j] == 'O') o++;
            }
        }
        return Math.abs(x - o) >= 2;
    }

    public static boolean checkRowsAndColumnsImp(char[][] field) {
        return false;
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

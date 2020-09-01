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

    public static boolean checkImpossibility(String field) {
        int[] arr = new int[2];
        // count numbers of 'X' and 'O'
        for (int i = 0; i < field.length(); i++) {
            if (field.charAt(i) == 'O') arr[0]++;
            else if (field.charAt(i) == 'X') arr[1]++;
        }
        return Math.abs(arr[0] - arr[1]) >= 2;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter cells: ");
        String state = input.nextLine();
        printField(state);
    }
}

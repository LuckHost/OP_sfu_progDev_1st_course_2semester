import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello!\n" +
        "The matrix program welcomes you\n" +
        "Enter the size of the matrix: ");

        Integer rows = getCorrectInput("Please enter the number of rows");
        Integer columns = getCorrectInput("And columns too");


        //ArrayList<Integer> tasksNumbers = Arrays.asList(0, 1);
        Integer taskSelected = getCorrectInput(new ArrayList<>(Arrays.asList(0, 1)), 
        new String("Please select the matrix input method\n" + 
        "0 - manually\n" + 
        "1 - randomized matrix "));

        int[][] randomizedMatrix = generateMatrix(rows, columns);

        System.out.println(rows.toString() + columns.toString() + taskSelected.toString());
        for (int[] is : randomizedMatrix) {
            for (int is2 : is) {
                System.out.println(is2);
            }
        }
        
    }

    // Функция перегрузки
    public static int getCorrectInput(String message) throws Exception {
        return getCorrectInput(new ArrayList<Integer>(), message);
    }

    public static int getCorrectInput(ArrayList<Integer> correctValues, String message) {
        System.out.println(message);
        Scanner scanner = new Scanner(System.in);
        while (true) {
            if(scanner.hasNextInt()) {
                int result = scanner.nextInt();
                if(correctValues.contains(result) || correctValues.isEmpty()){
                    return result;
                }
                System.out.println("The input is incorrect. Please try again");
                scanner = new Scanner(System.in);
            }
            else{
                System.out.println("Try again");
                scanner = new Scanner(System.in);
            }
        }
    }

    public static int[][] generateMatrix(int rows, int columns) {
        int[][] matrix = new int[rows][columns];
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < columns; j++){
                matrix[i][j] = (int) (Math.random() * 99);
            }
        }
        return matrix;

    }
}

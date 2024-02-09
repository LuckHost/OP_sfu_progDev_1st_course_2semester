import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Comparator;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello!\n" +
        "The matrix program welcomes you\n" +
        "Enter the size of the matrix: ");

        Integer rows = Math.abs(getCorrectInput("Please enter the number of rows"));
        Integer columns = Math.abs(getCorrectInput("And columns too"));

        Integer taskSelected = getCorrectInput(new ArrayList<>(Arrays.asList(0, 1)), 
            new String("Please select the matrix input method\n" + 
            "0 - manually\n" + 
            "1 - randomized matrix "));

        int[][] matrix = null;
        if(taskSelected == 1){
            matrix = generateMatrix(rows, columns);
        } else {
            matrix = inputMatrix(rows, columns);
        }

        System.out.println("\nEntered matrix:");
        matrixPrint(matrix);
        matrix = matrixModificator(rows, columns, matrix);
        System.out.println("\nModificated matrix:");
        matrixPrint(matrix);
        
    }

    public static void matrixPrint(int[][] matrix) {
        for (int[] is : matrix) {
            System.out.println();
            for (int is2 : is) {
                System.out.print(is2 + " ");
            }
        }
        System.out.println();
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
            } else {
                System.out.println("Try again");
                scanner = new Scanner(System.in);
            }
        }
    }

    public static int[][] inputMatrix(int rows, int columns) {
        int[][] matrix = new int[rows][columns];
        for(int i = 0; i < rows; i++) {
            System.out.println("Enter the line number " + (i+1) + "separated by a space");
            Scanner scanner = new Scanner(System.in);
            int[] rowOfNumbers = new int[columns];
            while (true) {
                try {
                    String inputLine = scanner.nextLine();
                    String[] numbers = inputLine.split(" ");

                    // Проверка на то, чтоб значений не было больше, чем колонок в строке
                    if(numbers.length > columns){
                        System.out.println("There are too many values,\n" + 
                        "there should be no more than the number of columns");
                        scanner = new Scanner(System.in);
                        continue;
                    }

                    // Добавляем во временный массив значения из строки
                    for(int j = 0; j < numbers.length; j++) {
                        rowOfNumbers[j] = Integer.parseInt(numbers[j]);
                    }

                    // Нужно для того, чтоб ввести в пустые ячейки нули
                    for(int j = numbers.length; j < columns; j++) {
                        rowOfNumbers[j] = 0;
                    }
                    break;
                }
                catch(NumberFormatException e) {
                    System.out.println("Input error, please try again");
                }
            }
            matrix[i] = rowOfNumbers;
        }
        return matrix;
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

    public static int[][] matrixModificator(int rows, int columns, int[][] matrix) {
        int[][] sortedMatrix = new int[rows][columns + 1];
        for(int i = 0; i < rows; i++){
            int rowsSumm = 0;
            for(int j = 0; j < columns; j++){
                rowsSumm += Math.abs(matrix[i][j]);
                sortedMatrix[i][j] = matrix[i][j];
            }
            sortedMatrix[i][columns] = rowsSumm;
        }
        // Скопировано с
        // https://stackoverflow.com/questions/15452429/java-arrays-sort-2d-array
        java.util.Arrays.sort(sortedMatrix, Comparator.comparingInt(o -> o[columns]));
        int[][] rotatedMatrix = new int[rows][columns + 1];
        for(int i = 0; i < rows; i++){
            rotatedMatrix[i] = sortedMatrix[rows - i - 1];
        }
        return rotatedMatrix;
    }
}

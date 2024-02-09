/*
 * Выполнил Ходыкин Александр КИ23-17/2Б
 * Ссылка на удаленный репозиторий:
 * https://github.com/LuckHost/OP_sfu_progDev_1st_course_2semester
 * 
 * Номер варианта: 7
 * 
 * Задание:
 * Входные данные: целочисленная матрица. Результат работы алгоритма: 
 * матрица, полученная из входной, путем упорядочивания строк в порядке убывания суммы модулей их элементов, 
 * а также дополнением матрицы новым столбцом с суммой модулей элементов каждой строки.
 */

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello!\n" +
        "The matrix program welcomes you\n" +
        "Enter the size of the matrix: ");
        while (true) {
            Integer rows = Math.abs(getCorrectInput("Please enter the number of rows"));
            Integer columns = Math.abs(getCorrectInput("And columns too"));

            Integer taskSelected = getCorrectInput(new int[]{0,1}, 
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

            taskSelected = getCorrectInput(new int[]{0,1}, 
                new String("Enter the task:\n" +
                "0 - continue\n" +
                "1 - exit "));
            if(taskSelected == 1){
                break;
            } else {
                continue;
            } 
        }   
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
        return getCorrectInput(new int[]{}, message);
    }

    public static int getCorrectInput(int[] correctValues, String message) {
        System.out.println(message);
        Scanner scanner = new Scanner(System.in);
        while (true) {
            if(scanner.hasNextInt()) {
                int result = scanner.nextInt();
                if(contains(correctValues, result) || correctValues.length == 0){
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

    // Замена спискам и их функциям посика элементов
    private static boolean contains(int[] correctValues, int result) {
        for (int i : correctValues) {
            if (i == result){
                return true;
            }
        }
        return false;
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

        // Я нашел метод не относящийся к спискам
        // https://stackoverflow.com/questions/15452429/java-arrays-sort-2d-array
        // В задании не говорилось про Arrays, значит я мог сделать так:
        // java.util.Arrays.sort(sortedMatrix, Comparator.comparingInt(o -> o[columns]));
        // После чего просто отразить матрицу по вретикали
        // Но все же я решил реализовать сортировку пузырьком

        return matrixBubleSort(rows, columns, sortedMatrix);
    }

    public static int[][] matrixBubleSort(int rows, int columns, int[][] matrix) {
        for(int j = 0; j < rows - 1; j++) {
            for(int i = 0; i < rows - 1; i++) {
                if(matrix[i][columns] < matrix[i+1][columns]) {
                    int temp = matrix[i][columns];
                    matrix[i][columns] = matrix[i+1][columns];
                    matrix[i+1][columns] = temp;
                }
            }
        }
        return matrix;
    }
}

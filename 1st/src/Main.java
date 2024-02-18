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
    /*
     * Главная функция программы
     * 
     * Приветствует пользователя, потом запускает цикл программы
     * 
     */
    public static void main(String[] args) throws Exception {
        System.out.println("Hello!\n" +
        "The matrix program welcomes you\n" +
        "Enter the size of the matrix: ");
        int[][] matrix = null;
        int[][] modificatedMatrix = null;
        while (true) {
            Integer taskSelected = getCorrectInput(new int[]{0, 1, 2, 3}, 
                new String("Enter the task:\n" +
                "0 - input the matrix\n" +
                "1 - modificate the matrix\n" +
                "2 - output the result\n" + 
                "3 - exit"), false);
            if(taskSelected == 3){
                break;
            }
            
            if(taskSelected == 0){
                modificatedMatrix = null;
                matrix = null;
                matrix = inputMatrix();
                System.out.println("\nEntered matrix:");
                matrixPrint(matrix);
            }
            if(taskSelected == 1){
                if(matrix == null) {
                    System.out.println("\nThe matrix has not been entered yet");
                    continue;
                }
                modificatedMatrix = matrixModificator(matrix);
            }
            if(taskSelected == 2) {
                if(modificatedMatrix == null) {
                    System.out.println("\nThe matrix has not been modificated yet");
                    continue;
                }
                System.out.println("\nEntered matrix:");
                matrixPrint(matrix);
                System.out.println("\nModificated matrix:");
                matrixPrint(modificatedMatrix);
            }
        }   
    }

    /*
     * Принимает у пользователя матрицу
     * 
     * Позваоляет выбрать метод ввода:
     * Ручной или сгенерировать случайным образом
     */
    public static int[][] inputMatrix() throws Exception {
        Integer rows = getCorrectInput("Please enter the number of rows", true);
        Integer columns = getCorrectInput("And columns too", true);
    
        Integer taskSelected = getCorrectInput(new int[]{0,1}, 
        new String("Please select the matrix input method\n" + 
        "0 - manually\n" + 
        "1 - randomized matrix "), false);

        int[][] matrix = null;
            if(taskSelected == 1){
                matrix = generateMatrix(rows, columns);
            } else {
                matrix = inputMatrix(rows, columns);
            }
        return matrix;
    }

    /*
     * Выводит полученную на входе матрицу
     */
    public static void matrixPrint(int[][] matrix) {
        for (int[] is : matrix) {
            System.out.println();
            for (int is2 : is) {
                System.out.print(is2 + " ");
            }
        }
        System.out.println();
    }

    /*
     * Функция перегрузки для функции ввода переменных
     * 
     * Принимет сообщение, которое надо отправить
     * пользователю перед вводом данных
     * 
     * Возвращает значение функции ввода, в котрую
     * отправляет сообщение и пустой массив
     * 
     */
    public static int getCorrectInput(String message, boolean necessarilyPositive) throws Exception {
        return getCorrectInput(new int[]{}, message, necessarilyPositive);
    }

    /*
     * Функция корректного ввода
     * Просит пользователя ввести значение
     * до тех пор, пока оно не будет корректным 
     * 
     * Получет на вход область допустимых
     * значений в виде массива и строку сообщения
     * для пользователя
     * 
     * Возвращает число, введенное пользователем
     */
    public static int getCorrectInput(int[] correctValues, String message, boolean necessarilyPositive) {
        System.out.println(message);
        Scanner scanner = new Scanner(System.in);
        while (true) {
            if(scanner.hasNextInt()) {
                int result = scanner.nextInt();
                if(contains(correctValues, result) || correctValues.length == 0){
                    if(!necessarilyPositive){
                        return result;
                    }
                    else{
                        if(result>0){
                            return result;
                        }
                    }
                }
                System.out.println("The input is incorrect. Please try again");
                scanner = new Scanner(System.in);
            } else {
                System.out.println("Try again");
                scanner = new Scanner(System.in);
            }
        }
    }

    /* 
     * Замена методу списков с одноименным
     * названием.
     * 
     * Получает на вход массив int[] и целочисленное
     * значение
     * 
     * Возвращает true, если это значение есть
     * в массиве и false, если его нет
     */ 
    private static boolean contains(int[] correctValues, int result) {
        for (int i : correctValues) {
            if (i == result){
                return true;
            }
        }
        return false;
    }

    /*
     * Функция ввода матрицы вручную
     * Пользователь по очереди вводит строки матрицы,
     * после чего они собираются в одну матрицу и
     * возвращаются функцией
     * 
     * Получает на вход количество строк и столбцов
     * 
     * Возвращает матрицу, введенную пользователем
     */
    public static int[][] inputMatrix(int rows, int columns) {
        int[][] matrix = new int[rows][columns];
        for(int i = 0; i < rows; i++) {
            System.out.println("Enter the line number " + (i+1) + " separated by a space");
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

    /*
     * Герератор матриц
     * 
     * Принимает на вход количество строк
     * и столбцов матрицы
     * 
     * Возвращает рандомно сгенерированную матрицу
     */
    public static int[][] generateMatrix(int rows, int columns) {
        int[][] matrix = new int[rows][columns];
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < columns; j++){
                matrix[i][j] = (int) (Math.random() * 99);
            }
        }
        return matrix;
    }

    /*
     * Модификатор матриц
     * Добавлает в новый стобец значения
     * суммы модулей элементов строки
     * 
     * Принимает на вход количество строк, столбцов
     * и саму матрицу
     * 
     * Возвращает матрицу с новым стоцом, отсортированную
     * по этому столбцу по убыванию
     */
    public static int[][] matrixModificator(int[][] matrix) {
        int rows = matrix.length;
        int columns = matrix[0].length;
        
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
        // Прошу указать, можно ли было использовать метод Arrays

        return matrixBubleSort(rows, columns, sortedMatrix);
    }

    /*
     * Сортировка пузырьком
     * Сортирует матрицу по последнему столбцу
     * по убыванию
     * 
     * Принимает на вход количество строк, столбцов
     * и саму матрицу
     * 
     * Возвращает отсортированную матрицу
     */

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

/* 
 * Выполнил Ходыкин Александр КИ23-17/2Б
 * Ссылка на удаленный репозиторий:
 * https://github.com/LuckHost/OP_sfu_progDev_1st_course_2semester
 * 
 * Номер варианта: 7
 * 
 * Задание:
 * Входные данные:  Необходимо реализовать класс "Студент", 
 * в том числе придумать поля (атрибуты) для него: 
 * числовые (целое и вещественное, не менее двух) и текстовые (не менее двух). 
 * Все поля класса должны быть закрытыми, необходимо реализовать методы доступа. 
 * В классе должны быть реализованы конструктор по умолчанию и с параметрами.
*/

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
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
    * Просто взял из прошлой лабы,
    * на производительность сильно не влияет, так что
    * можно оставить все как есть
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
}

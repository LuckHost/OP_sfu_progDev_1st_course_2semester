/* 
 * Выполнил Ходыкин Александр КИ23-17/2Б
 * Ссылка на удаленный репозиторий:
 * https://github.com/LuckHost/OP_sfu_progDev_1st_course_2semester
 * 
 * Номер варианта: 7
 * 
 * Задание:
 * Необходимо описать интерфейс, содержащий одну функцию
 *
 * int analyse(String str);
 * Данный интерфейс производит анализ строки по интересующему критерию. 
 * Необходимо реализовать два класса для этого интерфейса. 
 * Первый класс должен возвращать количество строчных символов в строке, 
 * а второй − возвращать количество заглавных символов в строке. Необходимо, 
 * чтобы приложение запросило у пользователя строку и выдало результаты вычисления для обоих классов. 
 * В строке должны быть только символы латинского алфавита, символов кириллицы быть не должно.
*/

import java.util.Scanner;

public class App {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    System.out.print("Enter a string: ");
    String input = scanner.nextLine();

    StringAnalyser lowercaseAnalyser = new LowercaseAnalyser();
    StringAnalyser uppercaseAnalyser = new UppercaseAnalyser();

    int lowercaseCount = lowercaseAnalyser.analyse(input);
    int uppercaseCount = uppercaseAnalyser.analyse(input);

    System.out.println("Number of lowercase characters: " + lowercaseCount);
    System.out.println("Number of uppercase characters: " + uppercaseCount);

    scanner.close();  
  }
}

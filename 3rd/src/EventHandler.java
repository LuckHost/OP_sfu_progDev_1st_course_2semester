/* 
 * Выполнил Ходыкин Александр КИ23-17/2Б
 * Ссылка на удаленный репозиторий:
 * https://github.com/LuckHost/OP_sfu_progDev_1st_course_2semester
 * 
 * Номер варианта: 7
 * 
 * Задание:
 * Входные данные: Для каждого варианта имеется набор из четырех сущностей. Необходимо выстроить иерархию наследования. 
 * В каждом классе (базовом и производных) должно быть  не менее одного числового и одного строкового поля. 
 * При вводе числовых параметров обязательна проверка на число и на диапазон (даже если число может быть любое, проверку необходимо реализовать).
*/

import java.util.ArrayList;
import java.util.Scanner;

public class EventHandler {
  /*
   * Главная функция программы
   * 
   * Приветствует пользователя, потом запускает цикл программы
   */
  public static void main(String[] args) throws Exception {
    System.out.println("Hello!\n" +
    "The vehicle data storage " +
    "program welcomes you\n");

    ArrayList<Vhencle> vhencleBase = new ArrayList<Vhencle>();
    boolean working = true;

    while (working) {
    Integer taskSelected = getCorrectInput(0, 4, 
      new String("Enter the task:\n" +
      "0 - Add new object\n" +
      "1 - Delete object by index\n" + 
      "2 - Print all objects to the console\n" +
      "3 - Compare two objects\n" + 
      "4 - exit"));
      switch (taskSelected) {
        case 0:
          Vhencle newObject = createNewObject();
          vhencleBase.add(newObject);
          break;
        case 1:
          printAlIbjects(vhencleBase);
          int indexToDel = getCorrectInput(0, vhencleBase.size(), "Choose the object to delete (write a number)");
          vhencleBase.remove(indexToDel);
        case 2:
          printAlIbjects(vhencleBase);
          break;
        case 3:
          compareObjects(vhencleBase);
          break;
        case 4:
          working = false;
          break;
        default:
          // getCorrectInput не позволит добраться сюда
          break;
      }
    }
  }

  /*
   * Создает новый объект, спрашивая у юзера, что именно он хочет создать
   * 
   * Ничего не принимает на вход
   * 
   * Возвращает созданный объект
   */
  public static Vhencle createNewObject() throws Exception {
    Integer typeSelected = getCorrectInput(0, 5, 
      new String("Enter the type of vhencle:\n" +
      "0 - Just a vhencle\n" +
      "1 - Car\n" +
      "2 - Train\n" +
      "3 - Express\n"));

    String assemblyCountry = getCorrectStrInput("Enter the country of assembly");
    int serialNumber =  getCorrectInput("Enter the serial number");
    switch (typeSelected) {
      case 0:
        return new Vhencle(assemblyCountry, serialNumber);
      case 1:
        String newBrand = getCorrectStrInput("Enter the car brand");
        int newProdYear = getCorrectInput("Enter the year of production");
        return new Car(assemblyCountry, serialNumber, newBrand, newProdYear);
      case 2:
        String newPurpose = getCorrectStrInput("Enter the train purpose");
        int newMaxSpeed = getCorrectInput("Enter the train maximum speed value");
        return new Train(assemblyCountry, serialNumber, newPurpose, newMaxSpeed);
      case 3:
        newPurpose = getCorrectStrInput("Enter the train purpose");
        newMaxSpeed = getCorrectInput("Enter the train maximum speed value");
        String newExpressway = getCorrectStrInput("Enter the main expressway");
        int newServiceYear  = getCorrectInput("Enter the train last service year");
        return new Express(assemblyCountry, serialNumber, newPurpose, newMaxSpeed, newExpressway, newServiceYear);
      default:
        return new Vhencle();   
    }
  }

  /*
   * Выводит в консоль все объекты
   * 
   * Принимает базу объектов 
   */
  public static void printAlIbjects(ArrayList<Vhencle> vhencleBase) {
    int counter = 0;
    for (Vhencle vhencle : vhencleBase) {
      String obj = (counter + " | " + vhencle.toString()).toString();
      System.out.println(obj);
      counter++;
    }
  }

  /*
   * Сравнивает объекты
   * 
   * Принимает базу объектов
   * 
   */
  public static void compareObjects(ArrayList<Vhencle> vhencleBase) {
    if(vhencleBase.size() < 2) {
      System.out.println("There are less than 2 objects in the list");
      return;
    }
    printAlIbjects(vhencleBase);
    int indexToCompare_1 = getCorrectInput(0, vhencleBase.size(), "Choose the firts object to compare (write a number)");
    int indexToCompare_2 = getCorrectInput(0, vhencleBase.size(), "Choose the second object to compare (write a number)");

    if(indexToCompare_1 == indexToCompare_2) {
      System.out.println("You entered the same indexes");
      return;
    }

    var firstObject = vhencleBase.get(indexToCompare_1);
    var secondObject = vhencleBase.get(indexToCompare_2);

    if(firstObject.getCountry().equals(secondObject.getCountry())) {
      System.out.println("The objects have the same assembly country");
    }
    if(firstObject.getSerNumb() == secondObject.getSerNumb()) {
      System.out.println("The objects have the same serial number");
    }
    String firstObjectClass = firstObject.getClass().toString();
    String secondObjectClass = secondObject.getClass().toString();
    if(firstObjectClass == secondObjectClass) {
      System.out.println("The objects have the same class");
      // Ввиду запрещения использования рефлексии я пришел к такому громосткому решению
      switch (firstObjectClass) {
        case "class Vhencle":
          break;
        case "class Car":
          if(((Car) firstObject).getBrand().equals(((Car) secondObject).getBrand())) {
            System.out.println("The objects have the same brand");
          }
          if(((Car) firstObject).getProdYear() == ((Car) secondObject).getProdYear()) {
            System.out.println("The objects have the same year of production");
          }
        case "class Train":
          if(((Train) firstObject).getTrainPurp().equals(((Train) secondObject).getTrainPurp())) {
            System.out.println("The objects have the same train purpose");
          }
          if(((Train) firstObject).getMaxSpeed() == ((Train) secondObject).getMaxSpeed()) {
            System.out.println("The objects have the same train maximum speed");
          }
        case "class Express":
          if(((Train) firstObject).getTrainPurp().equals(((Train) secondObject).getTrainPurp())) {
            System.out.println("The objects have the same train purpose");
          }
          if(((Express) firstObject).getMaxSpeed() == ((Express) secondObject).getMaxSpeed()) {
            System.out.println("The objects have the same train maximum speed");
          }
          if(((Express) firstObject).getExpressway().equals(((Express) secondObject).getExpressway())) {
            System.out.println("The objects have the same expressway");
          }
          if(((Express) firstObject).getMaxSpeed() == ((Express) secondObject).getMaxSpeed()) {
            System.out.println("The objects have the same last year of service");
          }
        default:
          break;
      }
    }
    // Позволяет сравнить объекты, если один из них поезд, а другой - экспресс
    if((firstObjectClass.equals("class Train") && secondObjectClass.equals("class Express")) || 
    (firstObjectClass.equals("class Express") && secondObjectClass.equals("class Train"))) {
      if(((Train) firstObject).getTrainPurp().equals(((Train) secondObject).getTrainPurp())) {
        System.out.println("The objects have the same train purpose");
      }
      if(((Train) firstObject).getMaxSpeed() == ((Train) secondObject).getMaxSpeed()) {
        System.out.println("The objects have the same train maximum speed");
      }
    }
    System.out.println("The comparison is over");
  }
  
  /*
   * Функция импорта корректного строкового значения
   * 
   * Принимает сообщение, которое надо выдать пользователю
   * 
   * Возвращает строку без спец. символов 
   */
  public static String getCorrectStrInput(String message) {
    System.out.println(message);
    Scanner scanner = new Scanner(System.in);
    String output = null;
    String[] wrongValues = new String[]{"@", "#", "$", "%", "^", "&", "*", "+", "="};
    Boolean cntnue = true;
    while (cntnue) {
      output = scanner.nextLine();
      for (String wrongValue : wrongValues) {
        if(output.indexOf(wrongValue) != -1){
          System.out.println("The input has forbidden characters. Please try again");
          scanner = new Scanner(System.in);
          cntnue = true;
          break;
        } else {
          cntnue = false;
        }
      }
    }
    return output;
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
  public static int getCorrectInput(String message) throws Exception {
    return getCorrectInput(0, 0, message);
  }

  /*
  * Функция корректного ввода
  * Просит пользователя ввести значение
  * до тех пор, пока оно не будет корректным 
  * 
  * Получет на вход область допустимых
  * значений в виде начала и конца массива 
  * и строку сообщения
  * для пользователя
  * 
  * Возвращает число, введенное пользователем
  */
  public static int getCorrectInput(int crctVlsStart, int crctVlsEnd, String message) {
    System.out.println(message);
    Scanner scanner = new Scanner(System.in);
    while (true) {
      if(scanner.hasNextInt()) {
        int result = scanner.nextInt();
        if(((result >= crctVlsStart && result <= crctVlsEnd) || crctVlsStart == crctVlsEnd) && result >= 0) {
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
}
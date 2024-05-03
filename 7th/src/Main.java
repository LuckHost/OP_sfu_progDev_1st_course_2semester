/* 
 * Выполнил Ходыкин Александр КИ23-17/2Б
 * Ссылка на удаленный репозиторий:
 * https://github.com/LuckHost/OP_sfu_progDev_1st_course_2semester
 * 
 * Номер варианта: 7
 * 
 * Задание:
 * Входные данные: Необходимо реализовать класс "Студент", 
 * в том числе придумать поля (атрибуты) для него: 
 * числовые (целое и вещественное, не менее двух) и текстовые (не менее двух). 
 * Все поля класса должны быть закрытыми, необходимо реализовать методы доступа. 
 * В классе должны быть реализованы конструктор по умолчанию и с параметрами.
*/

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
  /*
   * Главная функция программы
   * 
   * Приветствует пользователя, потом запускает цикл программы
   * 
   */
  public static void main(String[] args) throws Throwable {
    System.out.println("Hello!\n" +
    "The student information preservation " +
    "program program welcomes you\n");
    ArrayList<Student> studentsBase = new ArrayList<Student>();
    boolean working = true;
    while (working) {
      Integer taskSelected = getCorrectInput(0, 14, 
        new String("Enter the task:\n" +
        "0 - Add new student with empty values\n" +
        "1 - Add new student and enter the values\n" +
        "2 - Edit student values by index\n" +
        "3 - Print all students to the console\n" + 
        "4 - Sort the list by field\n" + 
        "5 - Create stream from list\n" + 
        "6 - Filter by average rating\n" + 
        "7 - Show a list with removed duplicates\n" + 
        "8 - Print the sum of all average ratings\n" + 
        "9 - Find student by name (Optional example)\n" +
        "10 - Group by study fields and count\n" + 
        "11 - Demonstrate summary statistics of average rating\n" + 
        "12 - Save data to a file\n" + 
        "13 - Load data from a file\n" +  
        "14 - exit"));
      switch (taskSelected) {
        case 0:
          Student newStudent = new Student();
          studentsBase.add(newStudent);
          break;
        case 1:
          String name = getCorrectStrInput(new String(
            "Enter the student's name: "));
          String fieldOfStudy = getCorrectStrInput(new String(
            "Enter the student's field of study: "));
          Integer yearOfAdmission = getCorrectInput(new String(
            "Enter the student's year of admission: "));

          // Обработчик исключения InvalidIntInputException,
          // которое было повторно создано в Students.java
          try {
            newStudent = new Student(name, fieldOfStudy, yearOfAdmission);
            studentsBase.add(newStudent);
          } catch (InvalidIntInputException e) {
            System.out.println(e.getMessage());
          }
          break;
        case 2:
          if(studentsBase.size() == 0) {
            System.out.println(new String("There is no students to change now"));
            break;
          }
          printAllStudents(studentsBase);
          changeStudentsField(studentsBase);
          break;
        case 3:
          if(studentsBase.size() == 0) {
            System.out.println(new String("There is no students to print now"));
            break;
          }
          printAllStudents(studentsBase);
          break;
        case 4:
          if(studentsBase.size() == 0) {
            System.out.println(new String("There is no students to sort now"));
            break;
          }
          sortStudentBase(studentsBase);
          break;
        case 5:
          if(studentsBase.size() == 0) {
            System.out.println(new String("There is no students to create stream "));
            break;
          }
          createStreamFromList(studentsBase);
          break;
        case 6:
          if(studentsBase.size() == 0) {
            System.out.println(new String("There is no students to filter"));
            break;
          }
          filterByCriteria(studentsBase);
          break;
        case 7:
          if(studentsBase.size() == 0) {
            System.out.println(new String("There is no students to remove"));
            break;
          }
          removeDuplicates(studentsBase);
          break;
        case 8:
          if(studentsBase.size() == 0) {
            System.out.println(new String("There is no students to accumulate"));
            break;
          }
          accumulateOperation(studentsBase);
          break;
        case 9:
          if(studentsBase.size() == 0) {
            System.out.println(new String("There is no students to demonstrate"));
            break;
          }
          demonstrateOptional(studentsBase);
          break;
        case 10:
          if(studentsBase.size() == 0) {
            System.out.println(new String("There is no students to group"));
            break;
          }
          groupByFieldAndCount(studentsBase);
          break;
        case 11:
          if(studentsBase.size() == 0) {
            System.out.println(new String("There is no students to demonstrate"));
            break;
          }
          demonstrateSummaryStatistics(studentsBase);
          break;
        case 12:
          if(studentsBase.size() == 0) {
            System.out.println(new String("There is no students to save"));
            break;
          }
          saveDataToFile(studentsBase);
          break;
        case 13:
          studentsBase = loadDataFromFile();
          break;
        case 14:
          working = false;
          break;
        default:
          // getCorrectInput не позволит добраться сюда
          break;
      }
    }
  }

  /*
   * Выводит в консоль всех студентов
   * 
   * Принимает базу студентов 
   */
  public static void printAllStudents(List<Student> studentsBase) {
    int counter = 0;
    for (Student student : studentsBase) {
      System.out.println(counter + " | " + student.toString());
      counter++;
    }
    
  }

  /*
   * Сортирует массив студентов по любому полю
   * 
   * Принимает массив студентов
   * 
   * Источник "вдохновения":
   * https://stackoverflow.com/questions/15452429/java-arrays-sort-2d-array
   * 
   */
  public static void sortStudentBase(ArrayList<Student> studentsBase) {
    Integer fieldSelected = getCorrectInput(0, 4, 
      new String("Enter the student's field you want to change:\n" +
      "0 - Name\n" +
      "1 - Field of study\n" +
      "2 - Average rating\n" +
      "3 - Year of admission\n" +
      "4 - Course\n"));
    switch (fieldSelected) {
      case 0:
        Collections.sort(studentsBase, new Comparator<Student>() {
          public int compare(Student o1, Student o2) {
            return - o2.getName().compareTo(o1.getName());
            }
          });
        break;
      case 1:
        Collections.sort(studentsBase, new Comparator<Student>() {
          public int compare(Student o1, Student o2) {
            return - o2.getStudyField().compareTo(o1.getStudyField());
            }
          });
        break;
      case 2:
        Collections.sort(studentsBase, new Comparator<Student>() {
          public int compare(Student o1, Student o2) {
            return - o2.getAverageRating().compareTo(o1.getAverageRating());
            }
        });
        break;
      case 3:
        Collections.sort(studentsBase, new Comparator<Student>() {
          public int compare(Student o1, Student o2) {
            return - o2.getYearOfAdmission().compareTo(o1.getYearOfAdmission());
            }
          });
        break;
      case 4:
        Collections.sort(studentsBase, new Comparator<Student>() {
          public int compare(Student o1, Student o2) {
            return - o2.getCourse().compareTo(o1.getCourse());
            }
          });
        break;
      default:
        // getCorrectInput не позволит добраться сюда
        break;
    }
  }

  /*
   * Изменяет любое данное поле
   * любого студента в базе
   * Принимает базу студентов
   */
  public static void changeStudentsField(ArrayList<Student> studentsBase) throws 
  InvalidStringInputException, InvalidIntInputException {
    Integer userId = getCorrectInput(0, 3, 
      new String("Enter the student's number you want to change: "));
    Student stdToChange = studentsBase.get(userId);
    Integer fieldSelected = getCorrectInput(0, 5, 
      new String("Enter the student's field you want to change:\n" +
      "0 - Name\n" +
      "1 - Field of study\n" +
      "2 - Average rating\n" +
      "3 - Year of admission\n"));
    switch (fieldSelected) {
      case 0:
        String name = getCorrectStrInput(new String(
          "Enter the student's name: "));
        System.out.println(stdToChange.setName(name));
        break;
      case 1:
        String fieldOfStudy = getCorrectStrInput(new String(
          "Enter the student's field of study: "));
        System.out.println(stdToChange.setStudyField(fieldOfStudy));
        break;
      case 2:
        float averageRating = getCorrectFloatInput(new String(
          "Enter the student's average rating: "));
        System.out.println(stdToChange.setAverageRating(averageRating));
        break;
      case 3:
        Integer yearOfAdmission = getCorrectInput(2013, 2023, 
        new String("Enter the student's year of admission: "));
        System.out.println(stdToChange.setYearOfAdmission(yearOfAdmission));
        break;
      default:
        // getCorrectInput не позволит добраться сюда
        break;
    }
  }

// Метод для создания потока из списка объектов и вывода их на экран
  public static void createStreamFromList(ArrayList<Student> studentsBase) {
    Stream<Student> studentStream = studentsBase.stream();
    studentStream.forEach(System.out::println);
  }

  // Метод для фильтрации объектов по заданному признаку, введенному пользователем
  public static void filterByCriteria(ArrayList<Student> studentsBase) {
    Scanner scanner = new Scanner(System.in);
    System.out.println("Enter the average rating to filter by:\n" + 
    "(only students with a lower rating will be displayed)");
    float criteria = scanner.nextFloat();
    List<Student> filteredStudents = studentsBase.stream()
        .filter(student -> student.getAverageRating() < criteria)
        .collect(Collectors.toList());
    filteredStudents.forEach(System.out::println);
  }

  // Метод для удаления дубликатов из списка
  public static void removeDuplicates(ArrayList<Student> studentsBase) {
    List<Student> uniqueStudents = studentsBase.stream()
        .distinct()
        .collect(Collectors.toList());
    printAllStudents(uniqueStudents);
  }

  // Метод для демонстрации операции сведения с накоплением (сумма всех средних оценок)
  public static void accumulateOperation(ArrayList<Student> studentsBase) {
    Double sum = studentsBase.stream()
          .mapToDouble(student -> student.getAverageRating())
          .sum();
    System.out.println("Sum of all average ratings: " + sum);
  }

  // Метод для демонстрации работы с типом Optional
  public static void demonstrateOptional(ArrayList<Student> studentsBase) {
    Scanner scanner = new Scanner(System.in);
    System.out.println("Enter the name of the student you want to find: ");
    String name = scanner.nextLine();
    Optional<Student> firstEvenStudent = studentsBase.stream()
      .filter(n -> n.getName().equals(name)).findFirst();
    if (firstEvenStudent.isPresent()) {
      System.out.println("The first match found: " + 
        firstEvenStudent.get().toString());
    } else {
      System.out.println("Student is not present");
    }
  }

  // Метод для группировки объектов по некоторому полю (fieldOfStudy)
  // и вывода числа элементов в группе
  public static void groupByFieldAndCount(ArrayList<Student> studentsBase) {
    studentsBase.stream()
            .collect(Collectors.groupingBy(Student::getStudyField, Collectors.counting()))
            .forEach((field, count) -> System.out.println(field + ": " + count));
    long countByFieldOfStudy = studentsBase.stream()
        .collect(Collectors.groupingBy(Student::getStudyField, Collectors.counting()))
        .entrySet().stream()
        .peek(entry -> System.out.println(entry.getKey() + ": " + entry.getValue()))
        .count();
    System.out.println("Total groups: " + countByFieldOfStudy);
  }

  // Метод для демонстрации работы с SummaryStatistics
  public static void demonstrateSummaryStatistics(ArrayList<Student> studentsBase) {
    double averageRatingAverage = studentsBase.stream()
        .mapToDouble(Student::getAverageRating)
        .summaryStatistics()
        .getAverage();
    System.out.println("Average rating of all students: " + averageRatingAverage);
  }

  // Метод для загрузки данных из файла
  public static ArrayList<Student> loadDataFromFile() throws InputMismatchException, 
  InvalidIntInputException, InvalidStringInputException, Exception {
    String fileName = getCorrectStrInput("Enter the file name: ");
    ArrayList<Student> studentsBase = new ArrayList<>();
    try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
      String line;
      while ((line = reader.readLine()) != null) {
        String[] parts = line.split(",");
        String name = parts[0];
        String studyField = parts[1];
        int yearOfAdmission = Integer.parseInt(parts[2]);
        float averageRating = Float.parseFloat(parts[3]);
        studentsBase.add(new Student(name, studyField, yearOfAdmission, averageRating));
      }
    } catch(Exception e) {
      System.out.println("Error loading data from the file");;
    }
    return studentsBase;
  }

  // Метод для сохранения данных в файл
  public static void saveDataToFile(ArrayList<Student> studentsBase) throws IOException {
    String fileName = getCorrectStrInput("Enter the file name: ");
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
      studentsBase.forEach(student -> {
          try {
              writer.write(student.getName() + "," + 
              student.getStudyField() + "," + 
              student.getYearOfAdmission() + "," +
              student.getAverageRating());
              writer.newLine();
          } catch (IOException e) {
              System.out.println("Error saving the file");
          }
      });
  }
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
      if (scanner.hasNextLine()) output = scanner.nextLine();
      else continue;
      
      for (String wrongValue : wrongValues) {
        if(output.indexOf(wrongValue) != -1){
          System.out.println("The input has forbidden characters. Please try again");
          continue;
        } else {
          cntnue = false;
          break;
        }
      }
    }
    return output;
  }

  /*
   * Функция импорта корректного значения типа float
   * 
   * Принимает сообщение, которое надо выдать пользователю
   * 
   * Возвращает значение float 
   */
  public static float getCorrectFloatInput(String message) {
    System.out.println(message);
    Scanner scanner = new Scanner(System.in);
    while (true) {
      if(scanner.hasNextFloat()) {
        float result = scanner.nextFloat();
        if(result >= 0 && result <= 5) {
          return result;
        }
        System.out.println("The input is incorrect. Please try again");
        scanner = new Scanner(System.in);
      } else {
        scanner = new Scanner(System.in);
        System.out.println("Try again");
      }
    }
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
      scanner = new Scanner(System.in);
      if(scanner.hasNextInt()) {
        int result = Integer.parseInt(scanner.nextLine());
        if((result >= crctVlsStart && result <= crctVlsEnd) || crctVlsStart == crctVlsEnd) {
          return result;
        }
        System.out.println("The input is incorrect. Please try again");
        
      } else {
        System.out.println("Try again");
      }
    }
  }
}

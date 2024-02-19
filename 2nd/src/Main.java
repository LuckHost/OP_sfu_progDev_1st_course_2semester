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

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
        "The student information preservation " +
        "program program welcomes you\n");
        ArrayList<Student> studentsBase = new ArrayList<Student>();
        boolean working = true;
        while (working) {
            Integer taskSelected = getCorrectInput(0, 5, 
                new String("Enter the task:\n" +
                "0 - Add new student with empty values\n" +
                "1 - Add new student and enter the values\n" +
                "2 - Edit student values by index\n" +
                "3 - Print all students to the console\n" + 
                "4 - Sort the list by field\n" + 
                "5 - exit"));
            switch (taskSelected) {
                case 0:
                    Student newStudent = new Student();
                    studentsBase.add(newStudent);
                    break;
                case 1:
                    String name = getCorrectStrInput(new String("Enter the student's name: "));
                    String fieldOfStudy = getCorrectStrInput(new String("Enter the student's field of study: "));
                    float averageRating = getCorrectFloatInput(new String("Enter the student's average rating: "));
                    // максимальный срок обуения - 11 лет
                    // значит и минимальный год поступления - 2013
                    Integer yearOfAdmission = getCorrectInput(2013, 2023, 
                    new String("Enter the student's year of admission: "));

                    newStudent = new Student(name, fieldOfStudy, averageRating, yearOfAdmission);
                    studentsBase.add(newStudent);
                    break;
                case 2:
                    changeStudentsField(studentsBase);
                    break;
                case 3:
                    for (Student student : studentsBase) {
                        System.out.println(student.getName() + " " 
                        + student.getStudyField() + " "
                        + student.getAverageRating() + " " 
                        + student.getYearOfAdmission() + " " 
                        + student.getCourse());
                    }
                    break;
                case 4:
                    sortStudentBase(studentsBase);
                    break;
                case 5:
                    working = false;
                    break;
            }
        }
    }

    public static void sortStudentBase(ArrayList<Student> studentsBase) {
        Integer fieldSelected = getCorrectInput(0, 4, 
            new String("Enter the student's field you want to change:\n" +
            "0 - Name\n" +
            "1 - Field of study\n" +
            "2 - Average rating\n" +
            "3 - Year of admission\n" +
            "3 - Course\n"));
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
                break;
        }
    }

    public static void changeStudentsField(ArrayList<Student> studentsBase) {
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
                String name = getCorrectStrInput(new String("Enter the student's name: "));
                stdToChange.setName(name);
                break;
            case 1:
                String fieldOfStudy = getCorrectStrInput(new String("Enter the student's field of study: "));
                stdToChange.setStudyField(fieldOfStudy);
                break;
            case 2:
                float averageRating = getCorrectFloatInput(new String("Enter the student's average rating: "));
                stdToChange.setAverageRating(averageRating);
                break;
            case 3:
                Integer yearOfAdmission = getCorrectInput(2013, 2023, 
                new String("Enter the student's year of admission: "));
                stdToChange.setYearOfAdmission(yearOfAdmission);
                break;
        }
        
    }

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

    public static float getCorrectFloatInput(String message) {
        System.out.println(message);
        Scanner scanner = new Scanner(System.in);
        while (true) {
            if(scanner.hasNextFloat()) {
                float result = scanner.nextFloat();
                if(result >= 0 && result <= 5){
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
                if((result >= crctVlsStart && result <= crctVlsEnd) || crctVlsStart == crctVlsEnd) {
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

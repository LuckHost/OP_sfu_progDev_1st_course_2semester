import java.util.logging.Logger;
import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;

public class Student {
  private String name = "No name";
  private String fieldOfStudy = "Not specified";
  private Float averageRating = 0f;
  private Integer yearOfAdmission = 2023;

  /*
   * Ниже находятся функции
   * геттеры и сеттеры для каждого
   * из аргументов класса
   */
  public String setName(String newName) throws 
  InvalidStringInputException {
    if(newName.matches(".*\\d.*")) {
      throw new InvalidStringInputException(
        "The name string must not have numbers");
    }
    name = newName;
    return "The name has been successfully set";
  }


  public String getName() {
    return this.name;
  }

  public String setStudyField(String newStudyField) throws 
  InvalidStringInputException {
    if(newStudyField.matches(".*\\d.*")) {
      throw new InvalidStringInputException(
        "The study field must not have numbers");
    }
    fieldOfStudy = newStudyField;
    return "The study field has been successfully set";
  }

  public String getStudyField() {
    return fieldOfStudy;
  }

  public String setAverageRating(float newAverageRating) throws 
  InvalidIntInputException {
    // средняя оценка не может быть больше 5 и меньше 1
    if(newAverageRating > 5 
    || newAverageRating < 1) {
      throw new InvalidIntInputException("The number is not between 1 and 5, "+
      "the average rating is not set");
    }
    averageRating = newAverageRating;
    return "The average rating field has been successfully set";
  }

  public Float getAverageRating() {
    return averageRating;
  }

  public String setYearOfAdmission(Integer newYearOfAdmission) throws 
  InvalidIntInputException {
    // максимальный срок обуения - 11 лет
    // значит и минимальный год поступления - 2013
    if(newYearOfAdmission > 2023 
    || newYearOfAdmission < 2013) {
      throw new InvalidIntInputException(
        "The year is not included in the range of 2013 and 2023 years, " + 
        "the admission year field is not set");
    }
    yearOfAdmission = newYearOfAdmission;
    return "The year of admission field has been successfully set";
  }

  public Integer getYearOfAdmission() {
    return yearOfAdmission;
  }

  /*
   * Та самая "Уникальная" функция
   * Высчитывает курс, на которм находится студент
   */
  public Integer getCourse() {
    return 2024 - yearOfAdmission;
  }

  public String toString() {
    return new String(name + " " + 
    fieldOfStudy + " " + averageRating + " " + yearOfAdmission);
  }

  @Override
  public boolean equals(Object other) {
    if (other == null) return false;
    if (other == this) return true;
    if (other.getClass() != getClass()) return false;
    Student student = (Student) other;
    return (name.equals(student.name) && fieldOfStudy.equals(student.fieldOfStudy) &&
      averageRating.equals(student.averageRating) && 
      yearOfAdmission.equals(student.yearOfAdmission));
  }

  @Override
    public int hashCode() {
        return Objects.hashCode(averageRating);
    }

  /*
   * Конструктор класса
   * 
   * Перехватывает 
   * InvalidStringInputException
   * и
   * InvalidIntInputException
   * 
   */
  
  public Student(String newName, String newStudyField, 
  Integer newYearOfAdmission) throws InvalidIntInputException, 
  InputMismatchException, InvalidStringInputException, Exception {
    Scanner scanner = null;
    float result = 0f;
    try {
      // Для примера подавленного исключения, а также 
      // для применения "утверждения" создан импорт числа здесь
      System.out.println("Enter the student's average rating: ");
      scanner = new Scanner(System.in);
      result = scanner.nextFloat();
      assert result > 0;

      System.out.println(setName(newName));
      System.out.println(setStudyField(newStudyField));
      System.out.println(setYearOfAdmission(newYearOfAdmission));
    } catch (InvalidStringInputException e) {
      // Простой перехват собственного исключения
      // Здесь новому студенту, в зависимости от ошибки,
      // присваивается "No name" либо "Not specified",
      // после чего он успешно добавляется в массив, 
      // если остальные поля корректны
      System.out.println(e.getMessage());
    } catch (InvalidIntInputException e) {
      // Повторное генерирование, обрабатывается в Main.java
      // Новый студент не создается
      Exception se = new InvalidIntInputException(e.getMessage());
      se.initCause(e);
      throw se;
    } catch (InputMismatchException e) {
      // Простой перехват базовго исключения
      System.out.println("An error occurred when entering the value.");
    } finally {
      // подавленное исключение 
      try {
        System.out.println(setAverageRating(result));
      } catch (Exception e) {
        System.out.println("Failed to create a new student. " + 
        "Check the entered data.");
        // пример логгирования
        Logger.getGlobal().info("A new suppressed exception has been created");
        Exception se = new InvalidIntInputException(e.getMessage());
        se.initCause(e);
        throw se;
      }
    }
  }
  public Student() {
  }

  public Student(String newName, String newStudyField, 
  Integer newYearOfAdmission, float averageRating) 
  throws InvalidStringInputException, InvalidIntInputException {
    name = newName;
    averageRating = averageRating;
    fieldOfStudy = newStudyField;
    yearOfAdmission = newYearOfAdmission;
  } 
}

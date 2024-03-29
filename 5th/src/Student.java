import javax.management.StringValueExp;

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
  public String setName(String newName) throws InvalidStringInputException {
		if(newName.matches("\\d+")) {
			throw new InvalidStringInputException("The string contains numbers, " +
      "the student has not been added to the database");
		}
    name = newName;
		return "The name has been successfully set";
  }

  public String getName() {
    return name;
  }

  public String setStudyField(String newStudyField) throws InvalidStringInputException {
		if(newStudyField.matches("\\d+")) {
			throw new InvalidStringInputException("The string contains numbers, " +
      "the student has not been added to the database");
		}
    fieldOfStudy = newStudyField;
		return "The study field has been successfully set";
  }

  public String getStudyField() {
    return fieldOfStudy;
  }

  public String setAverageRating(float newAverageRating) throws InvalidIntInputException {
		// средняя оценка не может быть больше 5 и меньше 1
    if(newAverageRating > 5 
		|| newAverageRating < 1) {
			throw new InvalidIntInputException("The number is not between 1 and 5, "+
      "the average rating is not set");
		}
    averageRating = newAverageRating;
		return "The averge rating field has been successfully set";
  }

  public Float getAverageRating() {
    return averageRating;
  }

  public String setYearOfAdmission(Integer newYearOfAdmission) throws InvalidIntInputException {
		// максимальный срок обуения - 11 лет
    // значит и минимальный год поступления - 2013
		if(newYearOfAdmission > 2023 
		|| newYearOfAdmission < 2013) {
			throw new InvalidIntInputException("The year is not included in the range of 2013 and 2023 years, the admission year field is not set");
		}
    yearOfAdmission = newYearOfAdmission;
		return "The averge year of admission field has been successfully set";
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
  float newAverageRating, Integer newYearOfAdmission) throws Throwable {
    try {
      System.out.println(setName(newName));
      System.out.println(setStudyField(newStudyField));
      System.out.println(setAverageRating(newAverageRating));
      System.out.println(setYearOfAdmission(newYearOfAdmission));
    } catch (InvalidStringInputException e) {
      // Простой перехват
      System.out.println(e.getMessage());
    } catch (InvalidIntInputException e) {
      // Повторное генерирование, обрабатывается в Main.java
      Throwable se = new InvalidIntInputException(e.getMessage());
      se.initCause(e);
      throw se;
    }
  }
  public Student() {
  }
}

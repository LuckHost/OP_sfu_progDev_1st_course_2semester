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
  public String setName(String newName) {
		if(newName.matches("\\d+")) {
			return "The string contains numbers, the name is not set";
		}
    name = newName;
		return "The name has been successfully set";
  }

  public String getName() {
    return name;
  }

  public String setStudyField(String newStudyField) {
		if(newStudyField.matches("\\d+")) {
			return "The string contains numbers, the study field is not set";
		}
    fieldOfStudy = newStudyField;
		return "The study field has been successfully set";
  }

  public String getStudyField() {
    return fieldOfStudy;
  }

  public String setAverageRating(float newAverageRating) {
		// средняя оценка не может быть больше 5 и меньше 1
    if(newAverageRating > 5 
		|| newAverageRating < 1) {
			return "The number is not between 1 and 5, the average rating is not set";
		}
    averageRating = newAverageRating;
		return "The averge rating field has been successfully set";
  }

  public Float getAverageRating() {
    return averageRating;
  }

  public String setYearOfAdmission(Integer newYearOfAdmission) {
		// максимальный срок обуения - 11 лет
    // значит и минимальный год поступления - 2013
		if(newYearOfAdmission > 2023 
		|| newYearOfAdmission < 2013) {
			return "The year is not included in the range of 2013 and 2023 years, the admission year field is not set";
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
   */
  public Student(String newName, String newStudyField,
  float newAverageRating, Integer newYearOfAdmission) {
    System.out.println(setName(newName));
    System.out.println(setStudyField(newStudyField));
    System.out.println(setAverageRating(newAverageRating));
    System.out.println(setYearOfAdmission(newYearOfAdmission));
  }

  public Student() {
  }
}

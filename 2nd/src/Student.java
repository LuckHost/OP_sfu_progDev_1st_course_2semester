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
    public void setName(String newName) {
        name = newName;
    }

    public String getName() {
        return name;
    }

    public void setStudyField(String newStudyField) {
        fieldOfStudy = newStudyField;
    }
    
    public String getStudyField() {
        return fieldOfStudy;
    }

    public void setAverageRating(float newAverageRating) {
        averageRating = newAverageRating;
    }
    
    public Float getAverageRating() {
        return averageRating;
    }

    public void setYearOfAdmission(Integer newYearOfAdmission) {
        yearOfAdmission = newYearOfAdmission;
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
    float newAverageRating, Integer newYearOfAdmission){
        setName(newName);
        setStudyField(newStudyField);
        setAverageRating(newAverageRating);
        setYearOfAdmission(newYearOfAdmission);
    }

    public Student() {
    }
}

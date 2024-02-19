public class Student {
    private String name = "Без имени";
    private String fieldOfStudy = "Не указано";
    private float averageRating = 4.5f;
    private Integer yearOfAdmission = 2023;

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
    
    public float getAverageRating() {
        return averageRating;
    }

    public void setYearOfAdmission(Integer newYearOfAdmission) {
        yearOfAdmission = newYearOfAdmission;
    }
    
    public Integer getYearOfAdmission() {
        return yearOfAdmission;
    }

    public Integer getCourse() {
        return 2024 - yearOfAdmission;
    }

    public void Student(String newName, String newStudyField, 
    float newAverageRating, Integer newYearOfAdmission){
        setName(newName);
        setStudyField(newStudyField);
        setAverageRating(newAverageRating);
        setYearOfAdmission(newYearOfAdmission);
    }
}

import java.util.Objects;

public class Express extends Train {
  private String mainExpressway = "М11 «Нева»";
  private int yearOfService = 1900;

  public String setExpressway(String newExpressway) {
    mainExpressway = newExpressway;
    return "The expressway was been successfully set";
  }

  public String getExpressway() {
    return mainExpressway;
  }

  public String setServiceYear(int newServiceYear) {
    if(newServiceYear <= yearOfService) {
      return "the year of service cannot be less than " +
      "the year of the last service, the base value has been set";
    }
    if(newServiceYear > 2024) {
      return "The maximum possible year is 2024, the base value has been set";
    }
    yearOfService = newServiceYear;
    return "The maximum speed was been successfully set";
  }

  public int getServiceYear() {
    return yearOfService;
  }

  // Сравнивает данный и собственый объект
  public boolean equals(Object otherObject) {
    if(otherObject instanceof Train && 
    super.equals(otherObject)) return true;
    if (this == otherObject) return true;
    if (otherObject == null) return false;
    if (getClass() != otherObject.getClass()) return false;
    if (! (otherObject instanceof Car)) return false;
    Express other = (Express) otherObject;
    return yearOfService == other.getServiceYear()
    && mainExpressway.equals(other.getExpressway());
  }

  // Функция, возвращающая объект в виде строки
  public String toString() {
    return new String(super.toString() + " " + 
    mainExpressway + " " + Integer.toString(yearOfService));
  }

  // Возвращает hash код
  @Override
  public int hashCode() {
    return Objects.hash(yearOfService, mainExpressway);
  }

  // Конструктор
  public Express(String assembCountry, int serNumber, 
    String newPurpose, int newMaxSpeed,
    String newExpressway, int newServiceYear) {
    super(assembCountry, serNumber, newPurpose, newMaxSpeed);
    System.out.println(setExpressway(newExpressway));
    System.out.println(setServiceYear(newServiceYear));
  }
}

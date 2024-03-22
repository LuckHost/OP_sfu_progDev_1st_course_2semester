import java.util.Objects;

public class Vhencle {
  private String assemblyCountry = "is not set";
  private int serialNumber = 0;

  /*
   * Ниже представлены геттеры и сеттеры
   */
  public String setCountry(String newCountry) {
    if(newCountry.matches("\\d+")) {
      return "The string contains numbers, the country is not set";
    }
    assemblyCountry = newCountry;
    return "The country was been successfully set";
  }

  public String getCountry() {
    return assemblyCountry;
  }

  public String setSerNumb(int newSerNumb) {
    if(newSerNumb == 0) {
      return "The serial number can't be empty, the base value has been set";
    }
    serialNumber = newSerNumb;
    return "The serial number was been successfully set";
  }

  public int getSerNumb() {
    return serialNumber;
  }

  // Функция, возвращающая объект в виде строки
  public String toString() {
    return new String(assemblyCountry + " " + Integer.toString(serialNumber));
  }

  // Сравнивает данный и собственый объект
  public boolean equals(Object otherObject) {
    if (this == otherObject) return true;
    if (otherObject == null) return false;
    if (getClass() != otherObject.getClass()) return false;
    if (! (otherObject instanceof Vhencle)) return false;
    Vhencle other = (Vhencle) otherObject;
    return serialNumber == other.serialNumber
    && assemblyCountry.equals(other.assemblyCountry);
  }

  // Возвращает hash код
  @Override
  public int hashCode() {
    return Objects.hash(serialNumber, assemblyCountry);
  }

  // Конструктор
  public Vhencle(String assembCountry, int serNumber) {
    System.out.println(setCountry(assembCountry));
    System.out.println(setSerNumb(serNumber));
  }

  public Vhencle() {
    this("Germany", 1);
  }
}
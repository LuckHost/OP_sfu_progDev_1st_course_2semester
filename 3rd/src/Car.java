import java.util.Objects;

public class Car extends Vhencle {
  private String brand = "";
  private int yearOfProduction = 2023;

  public String setBrand(String newBrand) {
    if(newBrand.matches("\\d+")) {
      return "The string contains numbers, the brand is not set";
    }
    brand = newBrand;
    return "The brand was been successfully set";
  }

  public String getBrand() {
    return brand;
  }

  public String setProdYear(int newProdYear) {
    if(newProdYear < 1886 || newProdYear > 2024) {
      return "The year of production can't be more " +
      "than 1886 and less than 2024, the base value has been set";
    }
    yearOfProduction = newProdYear;
    return "The year of production was been successfully set";
  }

  public int getProdYear() {
    return yearOfProduction;
  }

  // Функция, возвращающая объект в виде строки
  public String toString() {
    return new String(super.toString() + " " + 
    brand + " " + Integer.toString(yearOfProduction));
  }

  // Сравнивает данный и собственый объект
  public boolean equals(Object otherObject) {
    if(otherObject instanceof Vhencle && super.equals(otherObject)) return true;
    if (this == otherObject) return true;
    if (otherObject == null) return false;
    if (getClass() != otherObject.getClass()) return false;
    if (! (otherObject instanceof Car)) return false;
    Car other = (Car) otherObject;
    return yearOfProduction == other.getProdYear()
    && brand.equals(other.getBrand());
  }

  // Возвращает hash код
  @Override
  public int hashCode() {
    return Objects.hash(yearOfProduction, brand);
  }

  // Конструктор
  public Car(String assembCountry, int serNumber, 
  String newBrand, int newProdYear) {
    super(assembCountry, serNumber);
    System.out.println(setBrand(newBrand));
    System.out.println(setProdYear(newProdYear));
  }

  public Car() {
    this("Germany", 1, 
    "Audi", 2023);
  }
}
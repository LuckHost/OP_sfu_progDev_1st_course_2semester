import java.util.Objects;

public class Plane extends Vhencle {
  private String engineType = "";
  private int horsepower = 800;

  public String setEngineType(String newEngineType) {
    if(newEngineType.matches("\\d+")) {
      return "The string contains numbers, the engine type is not set";
    }
    engineType = newEngineType;
    return "The engine type was been successfully set";
  }

  public String getEngineType() {
    return engineType;
  }

  public String setHorsepower(int newHorsepower) {
    if(newHorsepower < 700) {
      return "The horsepower can't be " +
      "less than 700, the base value has been set";
    }
    horsepower = newHorsepower;
    return "The horsepower was been successfully set";
  }

  public int getHorsepower() {
    return horsepower;
  }

  // Функция, возвращающая объект в виде строки
  public String toString() {
    return new String(super.toString() + " " + 
    engineType + " " + Integer.toString(horsepower));
  }

  // Сравнивает данный и собственый объект
  public boolean equals(Object otherObject) {
    if(otherObject instanceof Vhencle && super.equals(otherObject)) return true;
    if (this == otherObject) return true;
    if (otherObject == null) return false;
    if (getClass() != otherObject.getClass()) return false;
    if (! (otherObject instanceof Plane)) return false;
    Plane other = (Plane) otherObject;
    return horsepower == other.getHorsepower()
    && engineType.equals(other.getEngineType());
  }

  // Возвращает hash код
  @Override
  public int hashCode() {
    return Objects.hash(horsepower, engineType);
  }

  // Конструктор
  public Plane(String assembCountry, int serNumber,
  String newEngineType, int newHorsepower) {
    super(assembCountry, serNumber);
    System.out.println(setHorsepower(newHorsepower));
    System.out.println(setEngineType(newEngineType));
  }

  public Plane() {
    this("Germany", 1,
    "Screw", 1000);
  }
}

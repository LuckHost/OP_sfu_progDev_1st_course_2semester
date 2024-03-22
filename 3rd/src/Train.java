import java.util.Objects;

public class Train extends Vhencle {
  private String trainPurpose = "Грузовой";
  private int maxSpeed = 0;

  public String setTrainPurp(String newPurpose) {
    if(newPurpose.matches("\\d+")) {
      return "The string contains numbers, the base value has been set";
    }
    trainPurpose = newPurpose;
    return "The train purpose was been successfully set";
  }

  public String getTrainPurp() {
    return trainPurpose;
  }

  public String setMaxSpeed(int newMaxSpeed) {
    if(newMaxSpeed == 0) {
      return "The maximum speed number can't be empty, the number is not set";
    }
    if(newMaxSpeed > 603) {
      return "The maximum possible speed is 603, the number is not set";
    }
    maxSpeed = newMaxSpeed;
    return "The maximum speed was been successfully set";
  }

  public int getMaxSpeed() {
    return maxSpeed;
  }

  // Функция, возвращающая объект в виде строки
  public String toString() {
    return new String(super.toString() + " " + 
    trainPurpose + " " + Integer.toString(maxSpeed));
  }

  // Сравнивает данный и собственый объект
  public boolean equals(Object otherObject) {
    if(otherObject instanceof Vhencle && super.equals(otherObject)) return true;
    if (this == otherObject) return true;
    if (otherObject == null) return false;
    if (getClass() != otherObject.getClass()) return false;
    if (! (otherObject instanceof Car)) return false;
    Train other = (Train) otherObject;
    return maxSpeed == other.getMaxSpeed()
    && trainPurpose.equals(other.getTrainPurp());
  }

  // Возвращает hash код
  @Override
  public int hashCode() {
    return Objects.hash(maxSpeed, trainPurpose);
  }

  // Конструктор
  public Train(String assembCountry, int serNumber, 
  String newPurpose, int newMaxSpeed) {
    super(assembCountry, serNumber);
    System.out.println(setTrainPurp(newPurpose));
    System.out.println(setMaxSpeed(newMaxSpeed));
  }

  public Train() {
    this("Russia", 
    1, "Cargo", 
    80);
  }
}

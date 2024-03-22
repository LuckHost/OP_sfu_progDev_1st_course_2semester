/*
 * Класс, который возвращает 
 * количество заглавных символов в строке
 */
public class UppercaseAnalyser implements StringAnalyser {
  @Override
  public int analyse(String str) {
    int count = 0;
    for (int i = 0; i < str.length(); i++) {
      char c = str.charAt(i);
      if (Character.isUpperCase(c)) {
        count++;
      }
    }
    return count;
  }
}
/*
 * Класс, который возвращает 
 * количество строчных символов в строке
 */
public class LowercaseAnalyser implements StringAnalyser {
  @Override
  public int analyse(String str) {
    StringAnalyser analyse = (strng) -> {
      int count = 0;
      for (int i = 0; i < str.length(); i++) {
        char c = str.charAt(i);
        if (Character.isLowerCase(c)) {
          count++;
        }
      }
      return count;
    };
    return analyse.analyse(str);
  }
}

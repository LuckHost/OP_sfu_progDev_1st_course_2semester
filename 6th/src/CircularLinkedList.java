/**
 * Кольцевой однонаправленный список.
 */
public class CircularLinkedList<T> {
  private Node<T> head;
  private Node<T> tail;
  private Node<T> current;

  /**
   * Проверяет, пуст ли список.
   *
   * @return true, если список пуст, в противном случае false.
   */
  public boolean isEmpty() {
    if(head == null) {
      System.out.println("The list is empty");
      return head == null;
    }
    return false;
  }

  /*
   * Устанавливает указатель в начало списка.
   */
  public void resetPointer() {
    current = head;
  }

  /*
   * Добавляет элемент за указателем.
   *
   * @param data данные элемента.
   */
  public void addAfterCurrent(T data) {
    if (head == null) {
      Node<T> newNode = new Node<>(data);
      newNode.next = newNode;
      head = newNode;
      tail = newNode;
      current = newNode;
    } else {
      Node<T> newNode = new Node<>(data);
      newNode.next = current.next;
      current.next = newNode;
      if (current == tail)
        tail = newNode;
    }
  }

  /*
   * Удаляет элемент за указателем.
   */
  public void deleteAfterCurrent() {
    if (!isEmpty()) {
      if (current.next == current) {
        head = null;
        tail = null;
        current = null;
      } else {
        if (current.next == tail)
          tail = current;
        current.next = current.next.next;
      }
    }
  }

  /*
   * Просматривает элемент за указателем.
   *
   * @return данные элемента за указателем.
   */
  public T peekCurrent() {
    if (current != null)
      return current.next.data;
    else
      return null;
  }

  /*
   * Перемещает указатель вправо.
   */
  public void movePointerRight() {
    if (current != null) {
      current = current.next;
    } else {
      System.out.println("The list is too short.");
    }
  }

  /*
   * Меняет значения конца списка и элемента за указателем.
   */
  public void swapEndAndCurrent() {
    if (!isEmpty() && current != null) {
      if(current == current.next) {
        System.out.println("The list is too short.");
        return;
      }
      T temp = tail.data;
      tail.data = current.next.data;
      current.next.data = temp;
    }
  }

  /*
   * Меняет значения начала списка и элемента за указателем.
   */
  public void swapStartAndCurrent() {
    if (!isEmpty() && current != null) {
      if(current == current.next) {
        System.out.println("The list is too short.");
        return;
      }
      T temp = head.data;
      head.data = current.next.data;
      current.next.data = temp;
    }
  }

  /*
   * Выводит список на экран.
   */
  public void display() {
    if (!isEmpty()) {
      Node<T> current = head;
      do {
        System.out.print(current.data + " ");
        current = current.next;
      } while (current != head);
      System.out.println();
    }
  }

  /**
   * Вспомогательный класс для представления узла списка.
   */
  private static class Node<T> {
    private T data;
    private Node<T> next;

    public Node(T data) {
      this.data = data;
      this.next = null;
    }
  }
}

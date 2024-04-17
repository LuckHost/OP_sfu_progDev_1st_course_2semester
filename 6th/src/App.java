import java.util.InputMismatchException;
import java.util.Scanner;

public class App {
  public static void main(String[] args) {
    int dataTypeChoice = chooseListType();
    CircularLinkedList<?> list;
    if(dataTypeChoice == 1) {
      list = new CircularLinkedList<Integer>();
    } else {
      list = new CircularLinkedList<String>();
    }

    Scanner scanner = new Scanner(System.in);
    int choice = 0;

    while (true) {
      System.out.println("\nChoose an operation:");
      System.out.println("1. Check if the list is empty/not empty");
      System.out.println("2. Set the pointer to the beginning of the list");
      System.out.println("3. Add an element after the pointer");
      System.out.println("4. Delete the element after the pointer");
      System.out.println("5. Peek at the element after the pointer");
      System.out.println("6. Move the pointer to the right");
      System.out.println("7. Swap the values of the end of the list and the element after the pointer");
      System.out.println("8. Swap the values of the start of the list and the element after the pointer");
      System.out.println("9. Display the list");
      System.out.println("0. Exit");

      try {
        choice = scanner.nextInt();
      } catch(InputMismatchException e) {
        System.out.println("Invalid input. Please try again.");
        scanner = new Scanner(System.in);
        continue;
      }
      
      switch (choice) {
      case 1:
        if(!list.isEmpty()) {
          System.out.println("The list is not empty");
        }
        break;
      case 2:
        list.resetPointer();
        break;
      case 3:
        System.out.println("Enter the value of the element to add:");
        if (dataTypeChoice == 1) {
          try{
            int elementToAdd = scanner.nextInt();
            ((CircularLinkedList<Integer>) list).addAfterCurrent(elementToAdd);
          } catch(InputMismatchException e) {
            System.out.println("Invalid input.");
            scanner = new Scanner(System.in);
          }
        } else if (dataTypeChoice == 2) {
          scanner.nextLine();
          String elementToAdd = scanner.nextLine();
          ((CircularLinkedList<String>) list).addAfterCurrent(elementToAdd);
        }
        break;
      case 4:
        list.deleteAfterCurrent();
        break;
      case 5:
        System.out.println("The element after the pointer: " + list.peekCurrent());
        break;
      case 6:
        list.movePointerRight();
        break;
      case 7:
        list.swapEndAndCurrent();
        break;
      case 8:
        list.swapStartAndCurrent();
        break;
      case 9:
        System.out.println("List:");
        list.display();
        break;
      case 0:
        System.out.println("Exiting the program.");
        scanner.close();
        return;
      default:
        System.out.println("Invalid choice. Please try again.");
      }
    }
  }

  public static int chooseListType() { 
    Scanner scanner = new Scanner(System.in);

    System.out.println("Choose the data type for the list:");
    System.out.println("1. Integer");
    System.out.println("2. String");

    int dataTypeChoice = 0;
    while(true) {
      try {
        dataTypeChoice = scanner.nextInt();
      } catch(InputMismatchException e) {
        System.out.println("Invalid input. Try again..");
        scanner = new Scanner(System.in);
        continue;
      }
      switch (dataTypeChoice) {
        case 1:
          return 1;
        case 2:
          return 2;
        default:
          System.out.println("Invalid choice. Try again..");
      }
    }  
  }
}

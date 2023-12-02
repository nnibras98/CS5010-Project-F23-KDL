package textBasedController;

import java.util.Scanner;

/**
 * main.
 * 
 * @param args argument.
 */
public class Main {

  /**
   * main.
   * 
   * @param args argument.
   */
  public static void main(String[] args) {
    // Initialize the world
    Scanner scanner = new Scanner(System.in);
    System.out.println("Please provide the path to the World file");
    String inputString = scanner.nextLine();
    System.out.println("");
    
    // Create the game controller and start the game
    GameController gameController = new GameController(inputString);
    gameController.startGame();
    
    scanner.close();

  }

}

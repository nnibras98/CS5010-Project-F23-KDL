package controller;


/**
 * main.
 * 
 * @param args argument.
 */
public class Main {

  public static void main(String[] args) {
    // Initialize the world
//  Scanner scanner = new Scanner(System.in);
  System.out.println("Please provide the path to the World file");
  String str = new String("C:\\CS5010 WorkSpace\\Game-Development-CS5010-Project\\res\\mansion.txt");
//String inputString = scanner.nextLine();
  System.out.println("");

  // Create the game controller and start the game
  GameController gameController = new GameController(str);
  gameController.startGame();

  }

}

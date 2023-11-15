package controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import model.ComputerPlayer;
import model.HumanPlayer;
//import model.Image;
import model.Item;
import model.Pet;
import model.Player;
import model.Room;
import model.TargetCharacter;
import model.WorldImpl;

/**
 * game controller.
 */

public class TestController {

  private WorldImpl world;
//  private List<HumanPlayer> hp;
//  private List<ComputerPlayer> bot;
  private String file;
  
  /**
   * constructor.
   * @param file file.
   */

  public TestController(String file) {
    this.file= file;
    this.world = new WorldImpl(new File(file));
//    this.hp = new ArrayList<>();
//    this.bot = new ArrayList<>();

  }
  
  /**
   * start game.
   */
  
  public void startGame() {
    // Print game information and rules
    System.out.println("Welcome to the Game: " + world.getWorldName());
    System.out.println("The target character is: " + world.getTargetCharacter().getName());
    System.out.println("The target character's pet's name is: " + world.getPet().getName());

    // Create human player
    Player humanPlayer = new HumanPlayer("John", 1, 1, world); // Replace "John" with the desired player name
    world.addPlayer(humanPlayer);
    System.out.println("Human player created: " + humanPlayer.getName());

    // Create computer player
    Player computerPlayer = new ComputerPlayer("ComputerBot", 0, 8, world); // Replace "ComputerBot" with the desired player name
    world.addPlayer(computerPlayer);
    System.out.println("Computer player created: " + computerPlayer.getName());

    // Execute the commands to move the pet and target character
    Pet pet = world.getPet();
    CommandInterface petMoveCommand = new PetMove(pet);

    TargetCharacter lucky = world.getTargetCharacter();
    CommandInterface tcMove = new TargetCharacterMove(lucky);
    
    CommandInterface hLookAround = new PlayerLookAround(humanPlayer);
    
    CommandInterface cLookAround = new PlayerLookAround(computerPlayer);
    
    CommandInterface hMove = new PlayerMove(humanPlayer);
    
    CommandInterface cMove = new PlayerMove(computerPlayer);
    
    CommandInterface hPickUp = new PlayerPickUpItem(humanPlayer);
    
    CommandInterface cPickUp = new PlayerPickUpItem(computerPlayer);
    
    CommandInterface  hDrop = new PlayerDropItem(humanPlayer);
    
    CommandInterface hKill  = new PlayerKillAttempt(humanPlayer);


    petMoveCommand.execute();
    tcMove.execute();
    System.out.println("Pet position: " + pet.getPetPosition());
    System.out.println("Target character position: " + lucky.getCharacterPositionIndex());

    petMoveCommand.execute();
    System.out.println("Pet position: " + pet.getPetPosition());
    System.out.println("Target character position: " + lucky.getCharacterPositionIndex());

    petMoveCommand.execute();
    tcMove.execute();
    System.out.println("Pet position: " + pet.getPetPosition());
    System.out.println("Target character position: " + lucky.getCharacterPositionIndex());

    // Make players look around once more
    System.out.println("Human player looking around:");
    hLookAround.execute();

    System.out.println("Computer player looking around:");
    cLookAround.execute();
    
//    //make human player move
//    System.out.println("Human player Room Number:" + humanPlayer.getCurrentRoomIndex());
//    System.out.println("Human player move:");
//    hMove.execute();
//    System.out.println("Human player Room Number:" + humanPlayer.getCurrentRoomIndex());
//    
//    //make human player move again
//    System.out.println("Human player Room Number:" + humanPlayer.getCurrentRoomIndex());
//    System.out.println("Human player move:");
//    hMove.execute();
//    System.out.println("Human player Room Number:" + humanPlayer.getCurrentRoomIndex());
    
//    //make computer player move
//    System.out.println("Computer player Room Number:" + computerPlayer.getCurrentRoomIndex());
//    System.out.println("Computer player move:");
//    cMove.execute();
//    System.out.println("Computer player Room Number:" + computerPlayer.getCurrentRoomIndex());
//    
//    //make computer player move again
//    System.out.println("Computer player Room Number:" + computerPlayer.getCurrentRoomIndex());
//    System.out.println("Computer player move:");
//    cMove.execute();
//    System.out.println("Computer player Room Number:" + computerPlayer.getCurrentRoomIndex());
    
//    hPickUp.execute();
//    hPickUp.execute();
//    hPickUp.execute();
    
//    cPickUp.execute();
    
//    hDrop.execute();
    
    hPickUp.execute();
    
    hKill.execute();
    
    
}




  /**
   * main.
   * 
   * @param args argument.
   */
  public static void main(String[] args) {
    // Initialize the world
    Scanner scanner = new Scanner(System.in);
    System.out.println("Please provide the path to the World file");
    String str = new String("C:\\CS5010 WorkSpace\\kill-doctor-lucky\\res\\mansion.txt");
//    String inputString = scanner.nextLine();

    // Create the game controller and start the game
    TestController gameController = new TestController(str);
    gameController.startGame();
  }
}
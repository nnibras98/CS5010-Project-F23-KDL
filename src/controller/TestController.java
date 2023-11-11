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
  private List<HumanPlayer> hp;
  private List<ComputerPlayer> bot;
  private String file;
  
  /**
   * constructor.
   * @param file file.
   */

  public TestController(String file) {
    this.file= file;
    this.world = new WorldImpl(new File(file));
    this.hp = new ArrayList<>();
    this.bot = new ArrayList<>();

  }
  
  /**
   * start game.
   */
  
  public void startGame() {

    
        // Print game information and rules
        System.out.println("Welcome to the Game: " + world.getWorldName());
        System.out.println("The target character is : " + world.getTargetCharacter().getName());
        System.out.println("The target character's pet's name is : " + world.getPet().getName());
        
        
        // Execute the commands to move the pet
        Pet pet = world.getPet();
        PetMove petMoveCommand = new PetMove(pet);
        
        TargetCharacter lucky = world.getTargetCharacter();
        TargetCharacterMove tcMove = new TargetCharacterMove(lucky);
        
        
        petMoveCommand.execute();
        tcMove.execute();
        System.out.println(pet.getPetPosition());
        System.out.println(lucky.getCharacterPositionIndex());
        
        petMoveCommand.execute();
        System.out.println(pet.getPetPosition());
        System.out.println(lucky.getCharacterPositionIndex());
        
        petMoveCommand.execute();
        tcMove.execute();
        System.out.println(pet.getPetPosition());
        System.out.println(lucky.getCharacterPositionIndex());// Execute the command to initiate the pet's movement


        

      
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
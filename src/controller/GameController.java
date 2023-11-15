package controller;

import java.io.File;
import java.util.List;
import java.util.Scanner;

import model.Item;
import model.Player;
import model.Room;
import model.WorldImpl;

public class GameController {
  private final GameFacade gameFacade;
  private final Scanner scanner;
  private final WorldImpl world;
  private final GameSetupHandler setupHandler;
  private String file;

  public GameController(String file) {
    
    this.file= file;
    this.world = new WorldImpl(new File(file));
    this.gameFacade = new GameFacade(world);
    this.scanner = new Scanner(System.in);
    this.setupHandler = new GameSetupHandler(gameFacade, scanner);
  }

  public void startGame() {

    setupHandler.showIntro();

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
//  String inputString = scanner.nextLine();
    System.out.println("");

    // Create the game controller and start the game
    GameController gameController = new GameController(str);
    gameController.startGame();
  }
}
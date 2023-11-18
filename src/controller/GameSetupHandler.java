package controller;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

import model.ComputerPlayer;
import model.HumanPlayer;
import model.Item;
import model.MapImageCreation;
import model.ModelFacade;
import model.Pet;
import model.Player;
import model.Room;
import model.TargetCharacter;
import model.WorldImpl;
import model.WorldInterface;

public class GameSetupHandler {
  private final ControllerFacade gameFacade;
  private final ModelFacade modelFacade;
  private final Scanner scanner;
  private WorldInterface world;

  public GameSetupHandler(ControllerFacade gameFacade, ModelFacade modelFacade, Scanner scanner) {
    this.gameFacade = gameFacade;
    this.modelFacade = modelFacade;
    this.scanner = scanner;
    this.world = modelFacade.getWorld();
  }

  public void showIntro() {
    // Display welcome message and general info about the world
    System.out.println("Welcome to " + world.getWorldName());
    System.out.println(
        "Your target character's name is " + world.getTargetCharacter().getName());
    System.out.println("The target character starts with "
        + world.getTargetCharacter().getHealth() + " health points");
    System.out.println("The target character has a special pet named as "
        + world.getPet().getName());
    System.out.println("");

    // Prompt user for rules
    System.out.print("Do you want to see the game info and rules? (y/n): ");
    String input1 = scanner.nextLine().toLowerCase();
    System.out.println("");

    if (input1.equals("y")) {
      System.out
          .println("There are " + gameFacade.getWorld().getNumRooms() + "room(s) in the game");
      printRoomInfo(world);
      System.out
          .println("There are " + gameFacade.getWorld().getNumItems() + "item(s) in the game");
      printItemInfo(world);
      printGameRules();
    }

    // Prompt user for seeing the map
    System.out.print("Do you want to see the map? (y/n): ");
    String input2 = scanner.nextLine().toLowerCase();
    System.out.println("");

    if (input2.equals("y")) {

      new MapImageCreation(world);

    }

  }

  private void printRoomInfo(WorldInterface world) {
    // Get all rooms in the world
    List<Room> allRooms = world.getRooms();

    System.out.println("");

    if (!allRooms.isEmpty()) {
      System.out.println("All Rooms in the World:");

      // Display information for each room
      for (Room room : allRooms) {
        System.out.println("Index: " + room.getIndex() + " Name: " + room.getName());
      }
    } else {
      System.out.println("No rooms in the world.");
    }

    System.out.println("");
  }

  private void printItemInfo(WorldInterface world) {
    // Get all items in the world
    List<Item> allItems = world.getItems();

    System.out.println("");

    if (!allItems.isEmpty()) {
      System.out.println("All Items in the World:");

      // Display information for each item
      for (Item item : allItems) {
        int roomIndex = item.getRoomIndex();
        String roomName = world.getRoomByIndex(roomIndex).getName();

        System.out.println("Item: " + item.getName() + " found in " + roomName);
      }
    } else {
      System.out.println("No items in the world.");
    }

    System.out.println("");
  }

  private void printGameRules() {

    System.out.println("");

    System.out.println("Game Rules:");

    // Rules for player actions
    System.out.println(
        "1. A Human-controlled player can move from the space they are currently in to a neighboring space. This represents a turn.");
    System.out.println(
        "2. A Human-controlled player can pick up an item in the world found in the same space, removing it from the world. Limited by carry capacity. This represents a turn.");
    System.out.println(
        "3. A Human-controlled player can look around, to see information about visible spaces. This represents a turn.");
    System.out.println(
        "4. A Human-controlled player can make an attemp to kill the target character. This represents a turn.");
    System.out.println(
        "5. A Computer-controlled player randomly chooses an action but always chooses to make an attempt to kill(if possible as per the game rule mentioned in point 8). This represents a turn.");

    // Rules for making attempts on the target character's life
    System.out.println(
        "6. Computer-controlled players attempt using the item in their inventory that does the most damage if unseen.");
    System.out.println(
        "7. A player with no items can poke the target character's eye for 1 point of damage.");
    System.out.println(
        "8. A player can only make an attemp on target character if they are in the same room, the pet is not present in that room, and if the last turn is not a look around. ");

    // Rules for target character and pet
    System.out.println(
        "9. The target character moves around the world during every turn of the game and so does the pet.");

    // Results of attempts
    System.out.println(
        "10. If an attack is seen by another player, it is stopped, and no damage is done.");
    System.out.println(
        "11. Unseen attacks are always successful, reducing health points of target character based on the item used and removing that item from the world.");

    // End of game conditions
    System.out.println("12. The game ends when:");
    System.out
        .println("    a. A player successfully kills the target character, winning the game.");
    System.out.println(
        "    b. The maximum number of turns is reached, and the target character escapes, hence nobody wins.");
    System.out.println("");
    System.out.println("");
  }

  public void playerCreation() {
    // Prompt user for game setup details
    System.out.println("Let's set up the game by adding players!");

    // Prompt for the number of computer players
    System.out.print("Enter the number of computer players: ");
    int numComputerPlayers = scanner.nextInt();
    try {
    scanner.nextLine(); // Consume the newline character
    }
    catch (InputMismatchException e)
    {
      System.out.println("Invalid input. Please enter a valid input.");
    }

    // Prompt for the number of human players
    System.out.print("Enter the number of human players: ");
    int numHumanPlayers = scanner.nextInt();
    scanner.nextLine(); // Consume the newline character

    System.out.println("");

    // Create players based on user input
    // Adjust this based on your player creation logic
    for (int i = 0; i < numComputerPlayers; i++) {
      // Create computer player and add to the world
      int randomEntryRoomIndex = (int) (Math.random() * gameFacade.getWorld().getNumRooms()); // Random
                                                                                              // entry
                                                                                              // room
                                                                                              // index
      int randomCarryingCapacity = (int) (Math.random() * 50); // Random carrying capacity

      Player computerPlayer = new ComputerPlayer("Computer Player " + (i + 1),
          randomCarryingCapacity, randomEntryRoomIndex, world);
      world.addPlayer(computerPlayer);
    }

    for (int i = 0; i < numHumanPlayers; i++) {
      // Prompt for human player details
      System.out.println("Enter details for Human Player " + (i + 1));

      // Prompt for player name
      System.out.print("Enter player name: ");
      String playerName = scanner.nextLine();

      // Prompt for entry room
      System.out.print("Enter entry room index: ");
      int entryRoomIndex = scanner.nextInt();
      scanner.nextLine(); // Consume the newline character

      // Prompt for carrying capacity
      System.out.print("Enter carrying capacity: ");
      int carryingCapacity = scanner.nextInt();
      scanner.nextLine(); // Consume the newline character

      // Create human player with specified details and add to the world
      Player humanPlayer = new HumanPlayer(playerName, carryingCapacity, entryRoomIndex,
          world);
      world.addPlayer(humanPlayer);

      System.out.println("");
    }

    // Prompt user for seeing the map
    System.out.print("Do you want to see the player info? (y/n): ");
    String input = scanner.nextLine().toLowerCase();
    System.out.println("");

    if (input.equals("y")) {

      viewPlayerInfo();

    }

  }

  private void viewPlayerInfo() {
    // Get the list of players from the world
    List<Player> players = world.getPlayers();

    if (!players.isEmpty()) {
      System.out.println("Player Information:");

      // Display information for each player
      for (Player player : players) {
        System.out.println("Player Name: " + player.getName());
        System.out.println("Entry Room: " + player.getCurrentRoomIndex());
        System.out.println("Carrying Capacity: " + player.getMaxCarryCapacity());

        if (player instanceof HumanPlayer) {
          System.out.println("Player Type: Human Player");
        } else if (player instanceof ComputerPlayer) {
          System.out.println("Player Type: Computer Player");
        }

        System.out.println("");
      }
    } else {
      System.out.println("No players in the world.");
    }
  }

  public void takeTurn() {
    // Prompt user for the maximum number of turns
    System.out.print("Enter the maximum number of turns: ");
    int maxTurns = scanner.nextInt();

    // Get the list of players from the world
    List<Player> players = world.getPlayers();

    // Game loop
    for (int turn = 1; turn <= maxTurns; turn++) {
      System.out.println("\n--- Turn " + turn + " ---");

      // Iterate through each player and take their turn
      for (Player player : players) {
        System.out.println("\nPlayer: " + player.getName());

        // Human player actions
        if (player instanceof HumanPlayer) {
          takeHumanPlayerTurn((HumanPlayer) player);
        }

        // Computer player actions
        else if (player instanceof ComputerPlayer) {
          takeComputerPlayerTurn((ComputerPlayer) player);
        }

        // Display game state after each turn
        displayGameState();

        // Check winning condition after each turn
        if (world.getTargetCharacter().getHealth() <= 0) {
          System.out.println("\nPlayer " + player.getName() + " wins!");
          return;
        }
      }
    }

    // If all turns are done and the target's health is not zero
    System.out.println("\nThe target character escapes! No one wins.");
  }

  private void takeHumanPlayerTurn(HumanPlayer humanPlayer) {
    // Display options for human player
    System.out.println("Options for Human Player:");
    System.out.println("1. Look Around");
    System.out.println("2. Pick Up Item");
    System.out.println("3. Move");
    
    // Check if human player and pet are in the same room
    boolean sameRoomPet =  (humanPlayer.getCurrentRoomIndex() == world
        .getPet().getPetPosition());
    
    // Check if human player and target are in the same room
    boolean sameRoomTarget = (humanPlayer.getCurrentRoomIndex() == world
        .getTargetCharacter().getCharacterPositionIndex());

    // Check if the human player and target character are in the same room with no pet
    if (!sameRoomPet && sameRoomTarget) {
      System.out.println("4. Kill Attempt");
    }
    
    // Check if the human player and target character are in the same room with pet
    if (sameRoomPet && sameRoomTarget) {
      System.out.println("4. KILL ATTEMPT!?");
    }

    // Prompt user for action choice
    System.out.print("Enter your choice (1-4): ");
    int choice = scanner.nextInt();

    switch (choice) {
      case 1:
        gameFacade.playerLookAround(humanPlayer);
        break;
      case 2:
        gameFacade.playerPickUpItem(humanPlayer);
        break;
      case 3:
        gameFacade.playerMove(humanPlayer);
        break;
      case 4:
        if (!sameRoomPet && sameRoomTarget) {
          gameFacade.playerKillAttempt(humanPlayer);
        } else {
          System.out.println("Attack failed! Leo the cat was in the same room.");
        }
        break;
      default:
        System.out.println("Invalid choice. Skipping turn.");
        break;
    }

    // make dr lucky and his pet move after each turn
    gameFacade.TargetCharacterMove();
    gameFacade.PetMove();
  }

  private void takeComputerPlayerTurn(ComputerPlayer computerPlayer) {
    // Display options for computer player
    System.out.println("Options for Computer Player:");
    System.out.println("1. Look Around");
    System.out.println("2. Pick Up Item");
    System.out.println("3. Move");

    // Check if computer player and target are in the same room
    boolean sameRoomTarget = (computerPlayer.getCurrentRoomIndex() == world
        .getTargetCharacter().getCharacterPositionIndex());
   

    if (sameRoomTarget) {
      // If in the same room, display "Kill Attempt" option
      System.out.println("4. Kill Attempt");
    }

    // Computer player chooses randomly
    int randomChoice;
    if (sameRoomTarget) {
      // If in the same room, restrict random choice to 4
      randomChoice = 4;
    } else {
      // Otherwise, include "Kill Attempt" in random choice
      randomChoice = ThreadLocalRandom.current().nextInt(1, 4);
    }

    System.out.println("Computer Player chooses: " + randomChoice);

    switch (randomChoice) {
      case 1:
        gameFacade.playerLookAround(computerPlayer);
        break;
      case 2:
        gameFacade.playerPickUpItem(computerPlayer);
        break;
      case 3:
        gameFacade.playerMove(computerPlayer);
        break;
      case 4:

        gameFacade.playerKillAttempt(computerPlayer);
        break;
      default:
        System.out.println("Invalid choice for Computer Player. Skipping turn.");
        break;
    }

    // make dr lucky and his pet move after each turn
    gameFacade.TargetCharacterMove();
    gameFacade.PetMove();
  }

  private void displayGameState() {
    System.out.println("\nCurrent Game State:");

    // Display player positions and inventory
    List<Player> players = world.getPlayers();
    for (Player player : players) {
      StringBuilder inventoryString = new StringBuilder();
      for (Item item : player.getInventory()) {
          inventoryString.append(item.getName()).append(", ");
      }

      // Remove the trailing comma and space
      if (inventoryString.length() > 0) {
          inventoryString.setLength(inventoryString.length() - 2);
      }

      System.out.println(player.getName() + " - Room: " + player.getCurrentRoomIndex()
              + ", Inventory: " + inventoryString.toString());
  }


    // Display target character's position and health
    TargetCharacter targetCharacter = world.getTargetCharacter();
    System.out.println("Target Character - Room: " + targetCharacter.getCharacterPositionIndex()
        + ", Health: " + targetCharacter.getHealth());

    // Display pet's position
    Pet pet = world.getPet();
    System.out.println("Pet - Room: " + pet.getPetPosition());
  }

}

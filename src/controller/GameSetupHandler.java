package controller;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import model.ComputerPlayer;
import model.HumanPlayer;
import model.Item;
import model.MapImageCreation;
import model.Player;
import model.Room;
import model.WorldImpl;

public class GameSetupHandler {
  private final GameFacade gameFacade;
  private final Scanner scanner;

  public GameSetupHandler(GameFacade gameFacade, Scanner scanner) {
    this.gameFacade = gameFacade;
    this.scanner = scanner;
  }
  
  public void showIntro() {
    // Display welcome message and general info about the world
    System.out.println("Welcome to " + gameFacade.getWorld().getWorldName());
    System.out.println("Your target character's name is " + gameFacade.getWorld().getTargetCharacter().getName());
    System.out.println("The target character starts with " + gameFacade.getWorld().getTargetCharacter().getHealth()
        + " health points");
    System.out
        .println("The target character has a special pet named as " + gameFacade.getWorld().getPet().getName());
    System.out.println("");
    
    // Prompt user for rules
    System.out.print("Do you want to see the game info and rules? (y/n): ");
    String input1 = scanner.nextLine().toLowerCase();
    System.out.println("");
    
    if (input1.equals("y")) {
      System.out.println("There are " + gameFacade.getWorld().getNumRooms() + "room(s) in the game");
      printRoomInfo(gameFacade.getWorld());
      System.out.println("There are " + gameFacade.getWorld().getNumItems() + "item(s) in the game");
      printItemInfo(gameFacade.getWorld());
      printGameRules();
  }
    
    // Prompt user for seeing the map
    System.out.print("Do you want to see the map? (y/n): ");
    String input2 = scanner.nextLine().toLowerCase();
    System.out.println("");
    
    if (input2.equals("y")) {
      
      new MapImageCreation(gameFacade.getWorld());
      
  }

  }
  


  private void printRoomInfo(WorldImpl world) {
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

  private void printItemInfo(WorldImpl world) {
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
    scanner.nextLine(); // Consume the newline character

    // Prompt for the number of human players
    System.out.print("Enter the number of human players: ");
    int numHumanPlayers = scanner.nextInt();
    scanner.nextLine(); // Consume the newline character
    
    System.out.println("");

 // Create players based on user input
 // Adjust this based on your player creation logic
 for (int i = 0; i < numComputerPlayers; i++) {
     // Create computer player and add to the world
     int randomEntryRoomIndex = (int) (Math.random() * gameFacade.getWorld().getNumRooms()); // Random entry room index
     int randomCarryingCapacity = (int) (Math.random() * 50); // Random carrying capacity

     Player computerPlayer = new ComputerPlayer("Computer Player " + (i + 1), randomCarryingCapacity, randomEntryRoomIndex, gameFacade.getWorld());
     gameFacade.getWorld().addPlayer(computerPlayer);
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
        Player humanPlayer = new HumanPlayer(playerName, carryingCapacity, entryRoomIndex, gameFacade.getWorld());
        gameFacade.getWorld().addPlayer(humanPlayer);
        
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
    List<Player> players = gameFacade.getWorld().getPlayers();

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



  public void takeTurns() {
//TODO
  }

}

package model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

/**
 * Player Class.
 */
public abstract class Player {

  private final String name;
  private final int maxCarryCapacity;
  private int currentRoomIndex;
  private List<Item> inventory;
  private final WorldInterface world;
  private boolean lookAroundUsedLastTurn;
  private int damage;
  private Scanner scanner;

  /**
   * Player Constructor.
   * 
   * @param nameIn             name.
   * @param maxCarryCapacityIn max capacity.
   * @param currentRoomIndexIn current room.
   * @param worldIn            the world.
   */

  public Player(String nameIn, int maxCarryCapacityIn, int currentRoomIndexIn,
      WorldInterface worldIn) {

    this.name = nameIn;
    this.maxCarryCapacity = maxCarryCapacityIn;
    this.currentRoomIndex = currentRoomIndexIn; // Initialize the player in a starting room
    this.inventory = new ArrayList<>();
    this.lookAroundUsedLastTurn = false;
    this.world = worldIn;
    this.scanner = new Scanner(System.in);

  }

  public String getName() {
    return name;
  }

  public int getCurrentRoomIndex() {
    return currentRoomIndex;
  }

  public void setCurrentRoomIndex(int currentRoomIndexIn) {
    this.currentRoomIndex = currentRoomIndexIn;
  }

  public int getMaxCarryCapacity() {
    return maxCarryCapacity;
  }

  public List<Item> getInventory() {
    return inventory;
  }

  public void setInventory(List<Item> inventoryIn) {
    this.inventory = inventoryIn;
  }

  protected void setLookAroundUsedLastTurn(boolean usedLastTurn) {
    lookAroundUsedLastTurn = usedLastTurn;
  }

  /**
   * Adds the specified item to the player's inventory and prints the updated
   * inventory.
   *
   * @param itemToAdd The item to be added to the inventory.
   */
  public void addToInventory(Item itemToAdd) {
    inventory.add(itemToAdd);
    System.out.println("Added " + itemToAdd.getName() + " to your inventory.");
    printInventory();
  }

  /**
   * Removes an item from the player's inventory.
   * 
   * @param item The item to be removed.
   */
  public void removeFromInventory(Item item) {
    inventory.remove(item);
    System.out.println("Removed " + item.getName() + " from inventory.");
    printInventory();
  }

  /**
   * Prints the player's inventory.
   */
  public void printInventory() {
    System.out.println("Your Inventory:");
    for (Item item : inventory) {
      System.out.println("- " + item.getName());
    }
    System.out.println("End of Inventory");
  }

  /**
   * logic for look around.
   */
  public void lookAround() {

    // Get the current room of the player
    Room currentRoom = world.getRoomByIndex(currentRoomIndex);

    if (currentRoom != null) {
      // Get neighbors of the current room
      List<Room> neighbors = world.getNeighborsByIndex(currentRoom.getIndex());

      // Print information about neighbors
      System.out.println(
          "Neighboring room(s) of " + world.getRoomByIndex(currentRoomIndex).getName() + "-");
      for (Room neighbor : neighbors) {
        System.out.println(neighbor.getName());
      }

      setLookAroundUsedLastTurn(true);

    } else {
      System.out.println("Player is not in a valid room.");
    }

  }

  /**
   * logic for kill attempt.
   */
  public void killAttempt() {

    // Unseen attacks are successful also cat should not not be present in the room.

    if (canMakeAttempt()) {

      doDamage();
      world.getTargetCharacter().takeDamage(damage);
      System.out.println("Attempt successful! " + world.getTargetCharacter().getName() + " loses "
          + damage + " health point(s), the current health is: "
          + world.getTargetCharacter().getHealth());

      setLookAroundUsedLastTurn(false);

    } else {
      // Seen attacks are automatically stopped
      System.out.println("Attempt could not be made. No damage done.");
    }

  }

  /**
   * Checks if the player can make an attempt on the target character's life.
   *
   * @return True if the player can make an attempt, false otherwise.
   */
  private boolean canMakeAttempt() {

    // Check if the cat is in the room
    boolean catInRoom = world.getPet().getPetPosition() == currentRoomIndex;

    // Check if there are other players in the room
    boolean otherPlayersInRoom = world.getPlayers().stream().filter(player -> player != this)
        .anyMatch(player -> player.getCurrentRoomIndex() == currentRoomIndex);

    // Check if the previous turn used lookAround
    boolean previousTurnLookAround = islookAroundUsedLastTurn();

    // Implement additional conditions based on your game rules
    // For example, you may want to add more checks depending on the game state

    return !catInRoom && !otherPlayersInRoom && !previousTurnLookAround;
  }

  private boolean islookAroundUsedLastTurn() {
    return lookAroundUsedLastTurn;
  }

  /**
   * Computes the damage inflicted by the player's attempt.
   *
   * @return The amount of damage.
   */
  private void doDamage() {
    if (getInventory().isEmpty()) {
      // Reduce the health of the target character by 1
      System.out.println("Poked the target character. Health reduced by 1");
      damage = 1;
    } else {
      if (this instanceof ComputerPlayer) {
        // If the current player is a ComputerPlayer, automatically choose the item with
        // the highest damage
        Item chosenItem = getInventory().stream().max(Comparator.comparingInt(Item::getDamage))
            .orElse(null);

        if (chosenItem != null) {
          System.out.println("Used " + chosenItem.getName() + " in the attempt.");
          damage = chosenItem.getDamage();
          world.removeItemFromRoom(currentRoomIndex, chosenItem);
          removeFromInventory(chosenItem);
        } else {
          System.out.println("No valid item found. No damage done.");
          damage = 0;
        }
      } else {
        // If the current player is a HumanPlayer, prompt the player to choose an item
        System.out.println("Choose an item to use in the attempt:");
        for (int i = 0; i < getInventory().size(); i++) {
          System.out.println((i + 1) + ". " + getInventory().get(i).getName() + " , "
              + "Hit point(s) for this item: " + getInventory().get(i).getDamage());
        }

        int choice = scanner.nextInt();
        if (choice >= 1 && choice <= getInventory().size()) {
          // Return the damage value based on the chosen item
          Item chosenItem = getInventory().get(choice - 1);
          System.out.println("Used " + chosenItem.getName() + " in the attempt.");
          damage = chosenItem.getDamage();
          world.removeItemFromRoom(currentRoomIndex, chosenItem);
        } else {
          System.out.println("Invalid choice. No damage done.");
          damage = 0;
        }
      }
    }
  }

  //
  public abstract void move();

  //
  public abstract void pickUp();

  //
  public abstract void drop();

}

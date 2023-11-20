package model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

/**
 * Abstract class representing a player in the game.
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
   * Constructs a new Player instance.
   *
   * @param nameIn             The name of the player.
   * @param maxCarryCapacityIn The maximum carrying capacity of the player.
   * @param currentRoomIndexIn The initial room index where the player is placed.
   * @param worldIn            The world interface representing the game world.
   */
  public Player(String nameIn, int maxCarryCapacityIn, int currentRoomIndexIn,
      WorldInterface worldIn) {
    this.name = nameIn;
    this.maxCarryCapacity = maxCarryCapacityIn;
    this.currentRoomIndex = currentRoomIndexIn;
    this.inventory = new ArrayList<>();
    this.lookAroundUsedLastTurn = false;
    this.world = worldIn;
    this.scanner = new Scanner(System.in);
  }

  /**
   * Gets the name of the player.
   *
   * @return The name of the player.
   */
  public String getName() {
    return name;
  }

  /**
   * Gets the current room index where the player is located.
   *
   * @return The current room index.
   */
  public int getCurrentRoomIndex() {
    return currentRoomIndex;
  }

  /**
   * Sets the current room index of the player.
   *
   * @param currentRoomIndexIn The new current room index.
   */
  public void setCurrentRoomIndex(int currentRoomIndexIn) {
    this.currentRoomIndex = currentRoomIndexIn;
  }

  /**
   * Gets the maximum carrying capacity of the player.
   *
   * @return The maximum carrying capacity.
   */
  public int getMaxCarryCapacity() {
    return maxCarryCapacity;
  }

  /**
   * Gets the inventory of the player.
   *
   * @return The player's inventory.
   */
  public List<Item> getInventory() {
    return inventory;
  }

  /**
   * Sets the inventory of the player.
   *
   * @param inventoryIn The new inventory of the player.
   */
  public void setInventory(List<Item> inventoryIn) {
    this.inventory = inventoryIn;
  }

  /**
   * Sets whether the player used "lookAround" in the last turn.
   *
   * @param usedLastTurn True if "lookAround" was used, false otherwise.
   */
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
   * Performs the "lookAround" action, printing information about neighboring
   * rooms.
   */
  public void lookAround() {
    Room currentRoom = world.getRoomByIndex(currentRoomIndex);

    if (currentRoom != null) {
      List<Room> neighbors = world.getNeighborsByIndex(currentRoom.getIndex());

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
   * Performs the "killAttempt" action, attempting to inflict damage on the target
   * character.
   */
  public void killAttempt() {
    if (canMakeAttempt()) {
      doDamage();
      world.getTargetCharacter().takeDamage(damage);
      System.out.println("Attempt successful! " + world.getTargetCharacter().getName() + " loses "
          + damage + " health point(s), the current health is: "
          + world.getTargetCharacter().getHealth());

      setLookAroundUsedLastTurn(false);
    } else {
      System.out.println("Attempt could not be made. No damage done.");
    }
  }

  /**
   * Checks if the player can make an attempt on the target character's life.
   *
   * @return True if the player can make an attempt, false otherwise.
   */
  private boolean canMakeAttempt() {
    boolean catInRoom = world.getPet().getPetPosition() == currentRoomIndex;
    boolean otherPlayersInRoom = world.getPlayers().stream().filter(player -> player != this)
        .anyMatch(player -> player.getCurrentRoomIndex() == currentRoomIndex);
    boolean previousTurnLookAround = isLookAroundUsedLastTurn();

    return !catInRoom && !otherPlayersInRoom && !previousTurnLookAround;
  }

  /**
   * Checks if the "lookAround" action was used in the last turn.
   *
   * @return True if "lookAround" was used, false otherwise.
   */
  private boolean isLookAroundUsedLastTurn() {
    return lookAroundUsedLastTurn;
  }

  /**
   * Computes the damage inflicted by the player's attempt.
   */
  private void doDamage() {
    if (getInventory().isEmpty()) {
      System.out.println("Poked the target character. Health reduced by 1");
      damage = 1;
    } else {
      if (this instanceof ComputerPlayer) {
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
        System.out.println("Choose an item to use in the attempt:");
        for (int i = 0; i < getInventory().size(); i++) {
          System.out.println((i + 1) + ". " + getInventory().get(i).getName() + " , "
              + "Hit point(s) for this item: " + getInventory().get(i).getDamage());
        }

        int choice = scanner.nextInt();
        if (choice >= 1 && choice <= getInventory().size()) {
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

  /**
   * Abstract method for moving the player.
   */
  public abstract void move();

  /**
   * Abstract method for picking up an item.
   */
  public abstract void pickUp();

  /**
   * Abstract method for dropping an item.
   */
  public abstract void drop();

}

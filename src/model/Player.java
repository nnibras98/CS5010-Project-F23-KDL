package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Player Class.
 */
public abstract class Player {

  private final String name;
  private final int maxCarryCapacity;
  private int currentRoomIndex;
  private List<Item> inventory;
  private final WorldImpl world;

  /**
   * Player Constructor.
   * 
   * @param nameIn             name.
   * @param maxCarryCapacityIn max capacity.
   * @param currentRoomIndexIn current room.
   */

  public Player(String nameIn, int maxCarryCapacityIn, int currentRoomIndexIn, WorldImpl worldIn) {

    this.name = nameIn;
    this.maxCarryCapacity = maxCarryCapacityIn;
    this.currentRoomIndex = currentRoomIndexIn; // Initialize the player in a starting room
    this.inventory = new ArrayList<>();
    this.world = worldIn;

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
  
  /**
   * Adds the specified item to the player's inventory and prints the updated inventory.
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


  //
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
    } else {
      System.out.println("Player is not in a valid room.");
    }

  }
  
  //
  public abstract void move();
  
  //
  public abstract void pickUp();

  public abstract void drop();

}

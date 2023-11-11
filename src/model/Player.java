package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Player Class.
 */
public abstract class Player {

  private final String name;
  private final int maxCarryCapacity;
  private int currentRoomIndex;
  private List<Item> inventory;

  /**
   * Player Constructor.
   * 
   * @param nameIn             name.
   * @param maxCarryCapacityIn max capacity.
   * @param currentRoomIndexIn current room.
   */

  public Player(String nameIn, int maxCarryCapacityIn, int currentRoomIndexIn) {

    this.name = nameIn;
    this.maxCarryCapacity = maxCarryCapacityIn;
    this.currentRoomIndex = currentRoomIndexIn; // Initialize the player in a starting room
    this.inventory = new ArrayList<>();

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

}

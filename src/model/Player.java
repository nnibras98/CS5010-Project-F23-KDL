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
  private boolean lookAroundUsedLastTurn;

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
    this.lookAroundUsedLastTurn = false;
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
  
  protected void setLookAroundUsedLastTurn(boolean usedLastTurn) {
    lookAroundUsedLastTurn = usedLastTurn;
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
      
      setLookAroundUsedLastTurn(true);
      
    } else {
      System.out.println("Player is not in a valid room.");
    }

  }
  
  /**
   * Attempts to poke the target character, reducing their health by 1.
   * This action can only be performed when the player's inventory is empty.
   */
  public void pokeTarget() {
      if (getInventory().isEmpty()) {
          // Reduce the health of the target character by 1
          world.getTargetCharacter().takeDamage(1);
          System.out.println("Poked the target character. Health reduced by 1, the current health is " 
          + world.getTargetCharacter().getHealth());
      } else {
          System.out.println("Cannot poke the target character with items in the inventory.");
      }
  }

  
  //
  public void killAttempt() {
    
    // Unseen attacks are successful also cat should not not be present in the room. 

    if (canMakeAttempt()) {

      int damage = computeDamage();
      world.getTargetCharacter().takeDamage(damage);
      System.out.println("Attempt successful! " + world.getTargetCharacter().getName() + " loses "
          + damage + " hit point, the current health is " + world.getTargetCharacter().getHealth());

      removeItemUsedInAttempt();
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
    
      // Check Inventory
      boolean inventoryEmpty = getInventory().isEmpty();
        
      // Check if the cat is in the room
      boolean catInRoom = world.getPet().getPetPosition() == currentRoomIndex;

      // Check if there are other players in the room
      boolean otherPlayersInRoom = world.getPlayers().stream()
              .filter(player -> player != this)
              .anyMatch(player -> player.getCurrentRoomIndex() == currentRoomIndex);

      // Check if the previous turn used lookAround
      boolean previousTurnLookAround = islookAroundUsedLastTurn();

      // Implement additional conditions based on your game rules
      // For example, you may want to add more checks depending on the game state

      return !inventoryEmpty && !catInRoom && !otherPlayersInRoom && !previousTurnLookAround;
  }


  private boolean islookAroundUsedLastTurn() {
    return lookAroundUsedLastTurn;
  }

  /**
   * Computes the damage inflicted by the player's attempt.
   *
   * @return The amount of damage.
   */
  private int computeDamage() {
    // Implement logic to compute damage based on the item used in the attempt
    // You can check the player's inventory for the item used
    // Return the appropriate damage value
    return 1; // Placeholder, replace with actual logic
  }

  /**
   * Removes the item used in the attempt from the player's inventory.
   */
  private void removeItemUsedInAttempt() {
    // Implement logic to remove the item used in the attempt from the player's
    // inventory
    // You can iterate through the player's inventory and remove the relevant item
    // You may need to adjust this based on your item and inventory implementation
  }
  
  //
  public abstract void move();
  
  //
  public abstract void pickUp();
  
  //
  public abstract void drop();


  


}

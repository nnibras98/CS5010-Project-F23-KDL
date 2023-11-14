package model;

import java.util.List;
import java.util.Scanner;

public class HumanPlayer extends Player {

  int currentRoomIndex;
  int maxCarryLimit;
  WorldImpl world;
  Scanner scanner;
  private boolean lookAroundUsedLastTurn;

  /**
   * Human Player Constructor.
   * 
   * @param nameIn             name.
   * @param maxCarryLimitIn    max capacity.
   * @param currentRoomIndexIn current room.
   * @param worldIn            world.
   */
  public HumanPlayer(String nameIn, int maxCarryLimitIn, int currentRoomIndexIn,
      WorldImpl worldIn) {
    super(nameIn, maxCarryLimitIn, currentRoomIndexIn, worldIn);
    this.currentRoomIndex = currentRoomIndexIn;
    this.maxCarryLimit = maxCarryLimitIn;
    this.world = worldIn;
    this.scanner = new Scanner(System.in);

  }

  public int getCurrentRoomIndex() {
    return currentRoomIndex;
  }

  public void setCurrentRoomIndex(int currentRoomIndex) {
    this.currentRoomIndex = currentRoomIndex;
  }

  @Override
  public void move() {

    // Get the current room of the player
    Room currentRoom = world.getRoomByIndex(currentRoomIndex);

    if (currentRoom != null) {
      // Get neighbors of the current room
      List<Room> neighbors = world.getNeighborsByIndex(currentRoom.getIndex());

      if (!neighbors.isEmpty()) { // Check if there are neighbors

        System.out.println("Choose a room to move to:");
        for (int i = 0; i < neighbors.size(); i++) {
          System.out.println((i + 1) + ". " + neighbors.get(i).getName());
        }

        int choice = scanner.nextInt(); // Read user input

        if (choice >= 1 && choice <= neighbors.size()) {
          // Move to the selected room
          Room selectedRoom = neighbors.get(choice - 1);
          System.out.println("Moved to " + selectedRoom.getName());
          // Implement the logic to update player's position or perform other actions
          super.setCurrentRoomIndex(selectedRoom.getIndex());
          this.setCurrentRoomIndex(selectedRoom.getIndex());

        } else {
          System.out.println("Invalid choice. Please choose a valid room.");
        }

      } else {
        System.out.println("No neighboring rooms to move to.");
      }
    }
  }

  /**
   * Allows the player to pick up an item in the current room, adding it to their
   * inventory.
   */
  @Override
  public void pickUp() {
    // Get the current room of the player
    Room currentRoom = world.getRoomByIndex(currentRoomIndex);

    if (currentRoom != null) {
      // Get the items in the current room
      List<Item> itemsInRoom = world.getItemsByRoomIndex(currentRoom.getIndex());

      if (!itemsInRoom.isEmpty()) { // Check if there are items in the room

        System.out.println("Items available to pick up:");
        for (int i = 0; i < itemsInRoom.size(); i++) {
          System.out.println((i + 1) + ". " + itemsInRoom.get(i).getName());
        }

        System.out.println("Choose an item to pick up:");
        int choice = scanner.nextInt(); // Read user input

        if (choice >= 1 && choice <= itemsInRoom.size()) {
          // Pick up the selected item
          Item selectedItem = itemsInRoom.get(choice - 1);

          // Check if player's inventory can accommodate the item
          if (super.getInventory().size() <= maxCarryLimit) {
            System.out.println("Picked up " + selectedItem.getName());
            // Implement the logic to update player's inventory or perform other actions
            super.addToInventory(selectedItem);
            // Remove the picked-up item from the room
            world.removeItemFromRoom(currentRoom.getIndex(), selectedItem);
          } else {
            System.out.println(
                super.getName() + " have reached maximum capacity. Cannot pick up more items.");
          }

        } else {
          System.out.println("Invalid choice. Please choose a valid item.");
        }

      } else {
        System.out.println("No items available to pick up in the room.");
      }
    }
  }

  /**
   * Allows the player to drop an item from their inventory in the current room.
   */
  @Override
  public void drop() {
    // Get the current room of the player
    Room currentRoom = world.getRoomByIndex(currentRoomIndex);

    if (currentRoom != null) {
      // Get the items in the player's inventory
      List<Item> playerInventory = super.getInventory();

      if (!playerInventory.isEmpty()) { // Check if the player has items in the inventory

        System.out.println("Items in your inventory:");
        for (int i = 0; i < playerInventory.size(); i++) {
          System.out.println((i + 1) + ". " + playerInventory.get(i).getName());
        }

        System.out.println("Choose an item to drop:");
        int choice = scanner.nextInt(); // Read user input

        if (choice >= 1 && choice <= playerInventory.size()) {
          // Drop the selected item
          Item droppedItem = playerInventory.get(choice - 1);
          System.out.println("Dropped " + droppedItem.getName());

          // Implement the logic to update player's inventory or perform other actions
          super.removeFromInventory(droppedItem);

          // Add the dropped item to the current room
          world.addItemToRoom(currentRoom.getIndex(), droppedItem);

        } else {
          System.out.println("Invalid choice. Please choose a valid item.");
        }

      } else {
        System.out.println("No items in your inventory to drop.");
      }
    }
  }

  @Override
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
    // TODO Auto-generated method stub
    return false;
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

}

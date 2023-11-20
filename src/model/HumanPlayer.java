package model;

import java.util.List;
import java.util.Scanner;

/**
 * Represents a human player in the game.
 * This class extends the base Player class and includes logic for human player actions.
 */
public class HumanPlayer extends Player {

  private int currentRoomIndex;
  private final int maxCarryLimit;
  private final WorldInterface world;
  private Scanner scanner;

  /**
   * Constructs a new HumanPlayer instance.
   *
   * @param nameIn             The name of the human player.
   * @param maxCarryLimitIn    The maximum capacity of the player's inventory.
   * @param currentRoomIndexIn The initial index of the current room.
   * @param worldIn            The world in which the player exists.
   */
  public HumanPlayer(String nameIn, int maxCarryLimitIn, int currentRoomIndexIn,
      WorldInterface worldIn) {
    super(nameIn, maxCarryLimitIn, currentRoomIndexIn, worldIn);
    this.currentRoomIndex = currentRoomIndexIn;
    this.maxCarryLimit = maxCarryLimitIn;
    this.world = worldIn;
    this.scanner = new Scanner(System.in);
  }

  /**
   * Gets the current room index of the human player.
   *
   * @return The current room index.
   */
  public int getCurrentRoomIndex() {
    return currentRoomIndex;
  }

  /**
   * Sets the current room index of the human player.
   *
   * @param currentRoomIndexIn The new current room index.
   */
  public void setCurrentRoomIndex(int currentRoomIndexIn) {
    this.currentRoomIndex = currentRoomIndexIn;
  }

  /**
   * Overrides the move method to implement the logic for the human player's movement.
   * The human player chooses a neighboring room to move into via user input.
   */
  @Override
  public void move() {
    Room currentRoom = world.getRoomByIndex(currentRoomIndex);

    if (currentRoom != null) {
      List<Room> neighbors = world.getNeighborsByIndex(currentRoom.getIndex());

      if (!neighbors.isEmpty()) {
        System.out.println("Choose a room to move to:");
        for (int i = 0; i < neighbors.size(); i++) {
          System.out.println((i + 1) + ". " + neighbors.get(i).getName());
        }

        int choice = scanner.nextInt();

        if (choice >= 1 && choice <= neighbors.size()) {
          Room selectedRoom = neighbors.get(choice - 1);
          System.out.println("Moved to " + selectedRoom.getName());
          super.setCurrentRoomIndex(selectedRoom.getIndex());
          this.setCurrentRoomIndex(selectedRoom.getIndex());
          setLookAroundUsedLastTurn(false);
        } else {
          System.out.println("Invalid choice. Please choose a valid room.");
        }
      } else {
        System.out.println("No neighboring rooms to move to.");
      }
    }
  }

  /**
   * Overrides the pickUp method to implement the logic for the human player picking up an item.
   * The human player chooses an item in the current room to pick up via user input.
   */
  @Override
  public void pickUp() {
    Room currentRoom = world.getRoomByIndex(currentRoomIndex);

    if (currentRoom != null) {
      List<Item> itemsInRoom = world.getItemsByRoomIndex(currentRoom.getIndex());

      if (!itemsInRoom.isEmpty()) {
        System.out.println("Items available to pick up:");
        for (int i = 0; i < itemsInRoom.size(); i++) {
          System.out.println((i + 1) + ". " + itemsInRoom.get(i).getName());
        }

        System.out.println("Choose an item to pick up:");
        int choice = scanner.nextInt();

        if (choice >= 1 && choice <= itemsInRoom.size()) {
          Item selectedItem = itemsInRoom.get(choice - 1);

          if (super.getInventory().size() <= maxCarryLimit) {
            System.out.println("Picked up " + selectedItem.getName());
            super.addToInventory(selectedItem);
            world.removeItemFromRoom(currentRoom.getIndex(), selectedItem);
            setLookAroundUsedLastTurn(false);
          } else {
            System.out.println(super.getName() + " has reached maximum capacity. "
                + "Cannot pick up more items.");
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
   * Overrides the drop method to implement the logic for the human player dropping an item.
   * The human player chooses an item from their inventory 
   * to drop in the current room via user input.
   */
  @Override
  public void drop() {
    Room currentRoom = world.getRoomByIndex(currentRoomIndex);

    if (currentRoom != null) {
      List<Item> playerInventory = super.getInventory();

      if (!playerInventory.isEmpty()) {
        System.out.println("Items in your inventory:");
        for (int i = 0; i < playerInventory.size(); i++) {
          System.out.println((i + 1) + ". " + playerInventory.get(i).getName());
        }

        System.out.println("Choose an item to drop:");
        int choice = scanner.nextInt();

        if (choice >= 1 && choice <= playerInventory.size()) {
          Item droppedItem = playerInventory.get(choice - 1);
          System.out.println("Dropped " + droppedItem.getName());

          super.removeFromInventory(droppedItem);
          world.addItemToRoom(currentRoom.getIndex(), droppedItem);
          setLookAroundUsedLastTurn(false);
        } else {
          System.out.println("Invalid choice. Please choose a valid item.");
        }
      } else {
        System.out.println("No items in your inventory to drop.");
      }
    }
  }
}

package model;

import java.util.List;

/**
 * Represents a computer player in the game.
 * This class extends the base Player class and includes logic for computer player actions.
 */
public class ComputerPlayer extends Player {

  private int currentRoomIndex;
  private final int maxCarryLimit;
  private final WorldInterface world;

  /**
   * Constructs a new ComputerPlayer instance.
   *
   * @param nameIn             The name of the computer player.
   * @param maxCarryLimitIn    The maximum capacity of the player's inventory.
   * @param currentRoomIndexIn The initial index of the current room.
   * @param worldIn            The world in which the player exists.
   */
  public ComputerPlayer(String nameIn, int maxCarryLimitIn, int currentRoomIndexIn,
      WorldInterface worldIn) {
    super(nameIn, maxCarryLimitIn, currentRoomIndexIn, worldIn);
    this.currentRoomIndex = currentRoomIndexIn;
    this.maxCarryLimit = maxCarryLimitIn;
    this.world = worldIn;
  }

  /**
   * Gets the current room index of the computer player.
   *
   * @return The current room index.
   */
  public int getCurrentRoomIndex() {
    return currentRoomIndex;
  }

  /**
   * Sets the current room index of the computer player.
   *
   * @param currentRoomIndexIn The new current room index.
   */
  public void setCurrentRoomIndex(int currentRoomIndexIn) {
    this.currentRoomIndex = currentRoomIndexIn;
  }

  /**
   * Overrides the move method to implement the logic for the computer player's movement.
   * The computer player randomly chooses a neighboring room to move into.
   */
  @Override
  public void move() {
    Room currentRoom = world.getRoomByIndex(currentRoomIndex);

    if (currentRoom != null) {
      List<Room> neighbors = world.getNeighborsByIndex(currentRoom.getIndex());
      
      // Randomly choose a room to move to
      int choice = (int) (Math.random() * neighbors.size()) + 1;

      if (choice >= 1 && choice <= neighbors.size()) {
        Room selectedRoom = neighbors.get(choice - 1);
        super.setCurrentRoomIndex(selectedRoom.getIndex());
        this.setCurrentRoomIndex(selectedRoom.getIndex());
        setLookAroundUsedLastTurn(false);
      }
    }
  }

  /**
   * Overrides the pickUp method to implement the logic for the computer player picking up an item.
   * The computer player randomly chooses an item in the current room to pick up.
   */
  @Override
  public void pickUp() {
    Room currentRoom = world.getRoomByIndex(currentRoomIndex);

    if (currentRoom != null) {
      List<Item> itemsInRoom = world.getItemsByRoomIndex(currentRoom.getIndex());

      if (!itemsInRoom.isEmpty()) {
        int choice = (int) (Math.random() * itemsInRoom.size()) + 1;

        if (choice >= 1 && choice <= itemsInRoom.size()) {
          Item selectedItem = itemsInRoom.get(choice - 1);

          if (super.getInventory().size() <= maxCarryLimit) {
            super.addToInventory(selectedItem);
            world.removeItemFromRoom(currentRoom.getIndex(), selectedItem);
            setLookAroundUsedLastTurn(false);
          } else {
            System.out.println(super.getName() + " has reached maximum capacity. "
                + "Cannot pick up more items.");
          }
        }
      }
    }
  }

  /**
   * Overrides the drop method to implement the logic for the computer player dropping an item.
   * The computer player randomly chooses an item from its inventory to drop in the current room.
   */
  @Override
  public void drop() {
    Room currentRoom = world.getRoomByIndex(currentRoomIndex);

    if (currentRoom != null) {
      List<Item> playerInventory = super.getInventory();

      if (!playerInventory.isEmpty()) {
        int choice = (int) (Math.random() * playerInventory.size()) + 1;

        if (choice >= 1 && choice <= playerInventory.size()) {
          Item droppedItem = playerInventory.get(choice - 1);
          super.removeFromInventory(droppedItem);
          world.addItemToRoom(currentRoom.getIndex(), droppedItem);
          setLookAroundUsedLastTurn(false);
        }
      }
    }
  }
}

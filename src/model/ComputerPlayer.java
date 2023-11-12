package model;

import java.util.List;

public class ComputerPlayer extends Player {
  
  int currentRoomIndex;
  int maxCarryLimit;
  WorldImpl world;
  
  /**
   * Computer Player Constructor.
   * 
   * @param nameIn             name.
   * @param maxCarryLimitIn    max capacity.
   * @param currentRoomIndexIn current room.
   * @param worldIn            world.
   */
  public ComputerPlayer(String nameIn, int maxCarryLimitIn, int currentRoomIndexIn, WorldImpl worldIn) {
    super(nameIn, maxCarryLimitIn, currentRoomIndexIn, worldIn);
    this.currentRoomIndex = currentRoomIndexIn;
    this.maxCarryLimit = maxCarryLimitIn;
    this.world = worldIn;

  }
  
  @Override
  public void move() {

    // Get the current room of the player
    Room currentRoom = world.getRoomByIndex(currentRoomIndex);

    if (currentRoom != null) {
      // Get neighbors of the current room
      List<Room> neighbors = world.getNeighborsByIndex(currentRoom.getIndex());


      System.out.println("Choosing a room to move to:");
      for (int i = 0; i < neighbors.size(); i++) {
        System.out.println((i + 1) + ". " + neighbors.get(i).getName());
      }
      
      int choice = (int) (Math.random() * neighbors.size()) + 1;
      
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
    }
  }

  public int getCurrentRoomIndex() {
    return currentRoomIndex;
  }

  public void setCurrentRoomIndex(int currentRoomIndex) {
    this.currentRoomIndex = currentRoomIndex;
  }

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
              int choice = (int) (Math.random() * itemsInRoom.size()) + 1;

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
                    System.out.println(super.getName() + " have reached maximum capacity. Cannot pick up more items.");
                }

            } else {
                System.out.println("Invalid choice. Please choose a valid item.");
            }

        } else {
            System.out.println("No items available to pick up in the room.");
        }
    }
}

  @Override
  public void drop() {
    // TODO Auto-generated method stub
    
  }
  
  
}

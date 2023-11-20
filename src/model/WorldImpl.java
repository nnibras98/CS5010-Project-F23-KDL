package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of the WorldInterface for handling world creation and updating.
 */
public class WorldImpl implements WorldInterface {

  private TargetCharacter targetCharacter;
  private Pet pet;
  private List<Room> rooms;
  private List<Item> items;
  private List<Player> players;
  private int numRows;
  private int numColumns;
  private int numRooms;
  private String worldName;
  private boolean end;

  /**
   * Constructs a WorldImpl object by reading information from a text file.
   * 
   * @param file The file containing world information.
   */
  public WorldImpl(File file) {
    this.rooms = new ArrayList<>(); // Initialize the rooms list
    this.items = new ArrayList<>(); // Initialize the items list
    this.players = new ArrayList<>(); // Initialized here but will be updated in controller.
    this.end = false;
    parseWorldFromFile(file);
  }

  /**
   * Gets the number of rows in the world.
   * 
   * @return The number of rows.
   */
  public int getNumRows() {
    return numRows;
  }

  /**
   * Gets the number of columns in the world.
   * 
   * @return The number of columns.
   */
  public int getNumCols() {
    return numColumns;
  }

  /**
   * Checks if the game has ended.
   * 
   * @return True if the game has ended, false otherwise.
   */
  public boolean getIsEnd() {
    return end;
  }

  /**
   * Gets the target character in the world.
   * 
   * @return The target character.
   */
  public TargetCharacter getTargetCharacter() {
    return targetCharacter;
  }

  /**
   * Sets the target character in the world.
   * 
   * @param targetCharacterIn The target character to set.
   */
  public void setTargetCharacter(TargetCharacter targetCharacterIn) {
    this.targetCharacter = targetCharacterIn;
  }

  /**
   * Gets the pet in the world.
   * 
   * @return The pet.
   */
  public Pet getPet() {
    return pet;
  }

  /**
   * Sets the pet in the world.
   * 
   * @param petIn The pet to set.
   */
  public void setPet(Pet petIn) {
    this.pet = petIn;
  }

  /**
   * Gets the list of rooms in the world.
   * 
   * @return The list of rooms.
   */
  public List<Room> getRooms() {
    return rooms;
  }

  /**
   * Sets the list of rooms in the world.
   * 
   * @param roomsIn The list of rooms to set.
   */
  public void setRooms(List<Room> roomsIn) {
    this.rooms = roomsIn;
  }

  /**
   * Gets the list of items in the world.
   * 
   * @return The list of items.
   */
  public List<Item> getItems() {
    return items;
  }

  /**
   * Sets the list of items in the world.
   * 
   * @param itemsIn The list of items to set.
   */
  public void setItems(List<Item> itemsIn) {
    this.items = itemsIn;
  }

  /**
   * Gets the name of the world.
   * 
   * @return The name of the world.
   */
  public String getWorldName() {
    return worldName;
  }

  /**
   * Sets the name of the world.
   * 
   * @param worldNameIn The name to set for the world.
   */
  public void setWorldName(String worldNameIn) {
    this.worldName = worldNameIn;
  }

  /**
   * Gets the number of rooms in the world.
   * 
   * @return The number of rooms.
   */
  public int getNumRooms() {
    return rooms.size();
  }

  /**
   * Gets the number of items in the world.
   * 
   * @return The number of items.
   */
  public int getNumItems() {
    return items.size();
  }

  /**
   * Gets the list of players in the world.
   * 
   * @return The list of players.
   */
  public List<Player> getPlayers() {
    return players;
  }

  /**
   * Sets the list of players in the world.
   * 
   * @param playersIn The list of players to set.
   */
  public void setPlayers(List<Player> playersIn) {
    this.players = playersIn;
  }

  /**
   * Adds a player to the world.
   * 
   * @param player The player to add.
   */
  public void addPlayer(Player player) {
    players.add(player);
  }

  /**
   * Parses world information from a file and initializes the world.
   * 
   * @param file The file containing world information.
   */
  @Override
  public void parseWorldFromFile(File file) {
    try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
      // Read the first line to get the world description
      String worldDescription = reader.readLine();
      String[] worldDescriptionParts = worldDescription.split(" ");
      if (worldDescriptionParts.length >= 5) {
        this.numRows = Integer.parseInt(worldDescriptionParts[0]);
        this.numColumns = Integer.parseInt(worldDescriptionParts[1]);
        this.worldName = worldDescriptionParts[2] + " " + worldDescriptionParts[3] + " "
            + worldDescriptionParts[4];

      }

      // Read Target Character Description
      String targetCharacterDescription = reader.readLine();
      String[] targetCharacterDescriptionParts = targetCharacterDescription.split(" ");

      // Read the line with the pet name
      String petDescription = reader.readLine();
      String[] petDescriptionParts = petDescription.split(" ");
      String petName = petDescriptionParts[0] + " " + petDescriptionParts[1] + " "
          + petDescriptionParts[2];

      // Read the line with the number of rooms
      this.numRooms = Integer.parseInt(reader.readLine());

      // Initialize targetCharacter after getting numRooms
      if (targetCharacterDescriptionParts.length == 3) {

        this.targetCharacter = new TargetCharacter(
            Integer.parseInt(targetCharacterDescriptionParts[0]),
            targetCharacterDescriptionParts[1] + " " + targetCharacterDescriptionParts[2],
            numRooms);
      }

      // Parse the rooms
      for (int i = 0; i < numRooms; i++) {
        String roomInfo = reader.readLine();
        String[] roomInfoParts = roomInfo.split(" ");
        if (roomInfoParts.length >= 5) {
          int upperLeftRow = Integer.parseInt(roomInfoParts[0]);
          int upperLeftColumn = Integer.parseInt(roomInfoParts[1]);
          int lowerRightRow = Integer.parseInt(roomInfoParts[2]);
          int lowerRightColumn = Integer.parseInt(roomInfoParts[3]);
          int index = i;

          // Combine the remaining parts to form the room name
          StringBuilder roomNameBuilder = new StringBuilder();
          for (int j = 4; j < roomInfoParts.length; j++) {
            roomNameBuilder.append(roomInfoParts[j]);
            if (j < roomInfoParts.length - 1) {
              roomNameBuilder.append(" "); // Add space between words
            }
          }
          String roomName = roomNameBuilder.toString();

          Room room = new Room(upperLeftRow, upperLeftColumn, lowerRightRow, lowerRightColumn,
              roomName, index);
          rooms.add(room);

        }
      }

      // Initialize the pet after getting room information
      if (petDescriptionParts.length == 3) {

        this.pet = new Pet(petName, rooms.get(0), rooms);
      }

      // Read the line with the number of items
      int numItems = Integer.parseInt(reader.readLine());

      // Parse the items
      for (int i = 0; i < numItems; i++) {
        // Read the line containing item information
        String itemInfo = reader.readLine();

        // Split the item information into parts
        String[] itemInfoParts = itemInfo.split(" ");

        // Check if there are enough parts to process
        if (itemInfoParts.length >= 3) {
          StringBuilder itemNameBuilder = new StringBuilder();
          for (int j = 2; j < itemInfoParts.length; j++) {
            itemNameBuilder.append(itemInfoParts[j]);
            if (j < itemInfoParts.length - 1) {
              itemNameBuilder.append(" ");
            }
          }
          String itemName = itemNameBuilder.toString();

          int roomIndex = Integer.parseInt(itemInfoParts[0]);
          int damage = Integer.parseInt(itemInfoParts[1]);

          // Create the Item object with the parsed values
          Item item = new Item(roomIndex, damage, itemName);

          // Add the item to the list
          items.add(item);
        }
      }

      end = true;

    } catch (IOException e) {
      // Handle exceptions
      System.err.println("An error occurred while reading the file");

    }
  }

  /**
   * Method to get Room by using index.
   * 
   * @param index The position of the room.
   * @return The room or null depending on the validity of the index.
   */
  public Room getRoomByIndex(int index) {
    for (Room room : rooms) {
      if (room.getIndex() == index) {
        return room;
      }
    }

    return null;
  }

  /**
   * Method to get neighbors of a room by using its index.
   * 
   * @param index The position of the room.
   * @return The list of neighboring rooms or an empty list if the room is not found.
   */
  public List<Room> getNeighborsByIndex(int index) {
    Room currentRoom = getRoomByIndex(index);

    if (currentRoom != null) {
      return currentRoom.getNeighbors(rooms);
    } else {
      return new ArrayList<>(); // Room not found, return an empty list
    }
  }

  /**
   * Retrieves the items present in the specified room.
   * 
   * @param roomIndex The index of the room to retrieve items from.
   * @return A list of items in the specified room.
   */
  public List<Item> getItemsByRoomIndex(int roomIndex) {
    List<Item> itemsInRoom = new ArrayList<>();

    // Iterate through the items and find those in the specified room
    for (Item item : items) {
      if (item.getRoomIndex() == roomIndex) {
        itemsInRoom.add(item);
      }
    }

    return itemsInRoom;
  }

  /**
   * Removes the specified item from the room with the given index.
   * 
   * @param roomIndex    The index of the room from which to remove the item.
   * @param itemToRemove The item to be removed from the room.
   */
  public void removeItemFromRoom(int roomIndex, Item itemToRemove) {
    // Find the room with the specified index
    Room targetRoom = rooms.stream().filter(room -> room.getIndex() == roomIndex).findFirst()
        .orElse(null);

    if (targetRoom != null) {
      // Remove the item from the room
      items.removeIf(item -> item.getRoomIndex() == roomIndex && item.equals(itemToRemove));
    }
  }

  /**
   * Adds an item to the room with the given index.
   * 
   * @param roomIndex The index of the room to which the item should be added.
   * @param item      The item to be added to the room.
   */
  public void addItemToRoom(int roomIndex, Item item) {
    // Find the room with the specified index
    Room targetRoom = rooms.stream().filter(room -> room.getIndex() == roomIndex).findFirst()
        .orElse(null);

    if (targetRoom != null) {
      // Add the item to the room
      Item newItem = new Item(item.getRoomIndex(), item.getDamage(), item.getName());
      items.add(newItem);
    }
  }
}

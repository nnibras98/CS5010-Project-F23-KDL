package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Facade class providing access to essential methods in the model.
 */
public class ModelFacade {

  private final WorldInterface world;

  /**
   * Constructs a new ModelFacade instance.
   *
   * @param worldIn The WorldInterface implementation.
   */
  public ModelFacade(WorldInterface worldIn) {
    this.world = worldIn;
  }

  /**
   * Get the world.
   *
   * @return The world.
   */
  public WorldInterface getWorld() {
    return world;
  }

  /**
   * Get the name of the world.
   *
   * @return The name of the world.
   */
  public String getWorldName() {
    return new String(world.getWorldName());
  }

  /**
   * Get the target character.
   *
   * @return The target character.
   */
  public TargetCharacter getTargetCharacter() {
    return world.getTargetCharacter();
  }

  /**
   * Get the name of the target character.
   *
   * @return The name of the target character.
   */
  public String getTargetCharacterName() {
    return new String(world.getTargetCharacter().getName());
  }

  /**
   * Get the health of the target character.
   *
   * @return The health of the target character.
   */
  public int getTargetCharacterHealth() {
    return world.getTargetCharacter().getHealth();
  }

  /**
   * Get the position of the target character.
   *
   * @return The position of the target character.
   */
  public int getTargetCharacterPosition() {
    return world.getTargetCharacter().getCharacterPositionIndex();
  }

  /**
   * Get the pet.
   *
   * @return The pet.
   */
  public Pet getPet() {
    return world.getPet();
  }

  /**
   * Get the name of the pet.
   *
   * @return The name of the pet.
   */
  public String getPetName() {
    return new String(world.getPet().getName());
  }

  /**
   * Get the position of the pet.
   *
   * @return The position of the pet.
   */
  public int getPetPosition() {
    return world.getPet().getPetPosition();
  }

  /**
   * Get the number of rooms.
   *
   * @return The number of rooms.
   */
  public int getNumRooms() {
    return world.getNumRooms();
  }

  /**
   * Get the number of items.
   *
   * @return The number of items.
   */
  public int getNumItems() {
    return world.getNumItems();
  }

  /**
   * Get all rooms.
   *
   * @return All rooms.
   */
  public List<Room> getAllRooms() {
    List<Room> copiedRooms = new ArrayList<>(world.getRooms());
    return copiedRooms;
  }

  /**
   * Get a room by index.
   *
   * @param index The index of the room.
   * @return The room.
   */
  public Room getRoomByRoomIndex(int index) {
    Room originalRoom = world.getRoomByIndex(index);

    Room copiedRoom = new Room(originalRoom.getUpperLeftRow(), originalRoom.getUpperLeftColumn(),
        originalRoom.getLowerRightRow(), originalRoom.getLowerRightColumn(), originalRoom.getName(),
        originalRoom.getIndex());

    return copiedRoom;
  }

  /**
   * Get the name of a room by index.
   *
   * @param index The index of the room.
   * @return The name of the room.
   */
  public String getRoomNameByRoomIndex(int index) {
    String roomName = world.getRoomByIndex(index).getName();
    return new String(roomName);
  }

  /**
   * Get all items.
   *
   * @return All items.
   */
  public List<Item> getAllItems() {
    List<Item> copiedItems = new ArrayList<>(world.getItems());
    return copiedItems;
  }

  /**
   * Get all players.
   *
   * @return All players.
   */
  public List<Player> getAllPlayers() {
    List<Player> copiedPlayers = new ArrayList<>(world.getPlayers());
    return copiedPlayers;
  }
}

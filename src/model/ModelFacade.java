package model;

import java.util.ArrayList;
import java.util.List;

/**
 * a class for accessing all the essential methods from the model.
 */
public class ModelFacade {

  private final WorldInterface world;

  public ModelFacade(WorldInterface worldIn) {
    this.world = worldIn;
  }
  
  /**
   * get world.
   * @return the world.
   */
  public WorldInterface getWorld() {

    return world;

  }
  
  /**
   * get world name.
   * @return the world name.
   */
  public String getWorldName() {

    return new String(world.getWorldName());

  }

  public TargetCharacter getTargetCharacter() {
    return world.getTargetCharacter();
  }
  
  /**
   * get the name of target.
   * @return the target name.
   */
  public String getTargetCharacterName() {

    return new String(world.getTargetCharacter().getName());

  }
  
  /**
   * get target health.
   * @return the target health.
   */
  public int getTargetCharacterHealth() {

    return (world.getTargetCharacter().getHealth());

  }

  public int getTargetCharacterPosition() {

    return world.getTargetCharacter().getCharacterPositionIndex();
  }

  public Pet getPet() {
    return world.getPet();
  }
  
  /**
   * get the name of the pet.
   * @return pet name.
   */
  public String getPetName() {

    return new String(world.getPet().getName());

  }

  public int getPetPosition() {

    return world.getPet().getPetPosition();
  }
  
  /**
   * get the number of rooms.
   * @return num of rooms.
   */
  public int getNumRooms() {

    return world.getNumRooms();

  }
  
  /**
   * get the number of items.
   * @return num of items.
   */
  public int getNumItems() {

    return world.getNumItems();

  }
  
  /**
   * get all rooms.
   * @return all rooms.
   */
  public List<Room> getAllRooms() {

    List<Room> copiedRooms = new ArrayList<>(world.getRooms());
    return copiedRooms;
  }
  
  /**
   * room by index.
   * @param index of the room.
   * @return the room.
   */
  public Room getRoomByRoomIndex(int index) {
    Room originalRoom = world.getRoomByIndex(index);

    Room copiedRoom = new Room(originalRoom.getUpperLeftRow(), originalRoom.getUpperLeftColumn(),
        originalRoom.getLowerRightRow(), originalRoom.getLowerRightColumn(), originalRoom.getName(),
        originalRoom.getIndex());

    return copiedRoom;
  }
  
  /**
   * room name by index.
   * @param index of the room.
   * @return the room name.
   */
  public String getRoomNameByRoomIndex(int index) {
    String roomName = world.getRoomByIndex(index).getName();

    return new String(roomName);
  }
  
  /**
   * all the items.
   * @return all the items.
   */
  public List<Item> getAllItems() {

    List<Item> copiedItems = new ArrayList<>(world.getItems());
    return copiedItems;
  }
  
  /**
   * all the players.
   * @return all the players.
   */
  public List<Player> getAllPlayers() {

    List<Player> copiedPlayers = new ArrayList<>(world.getPlayers());

    return copiedPlayers;
  }

}

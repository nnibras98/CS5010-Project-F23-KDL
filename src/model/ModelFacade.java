package model;

import java.util.ArrayList;
import java.util.List;

public class ModelFacade {

  private final WorldInterface world;

  public ModelFacade(WorldInterface world) {
    this.world = world;
  }

  public WorldInterface getWorld() {

    return world;

  }

  public String getWorldName() {

    return new String(world.getWorldName());

  }

  public TargetCharacter getTargetCharacter() {
    return world.getTargetCharacter();
  }

  public String getTargetCharacterName() {

    return new String(world.getTargetCharacter().getName());

  }

  public int getTargetCharacterHealth() {

    return (world.getTargetCharacter().getHealth());

  }

  public int getTargetCharacterPosition() {

    return world.getTargetCharacter().getCharacterPositionIndex();
  }

  public Pet getPet() {
    return world.getPet();
  }

  public String getPetName() {

    return new String(world.getPet().getName());

  }

  public int getPetPosition() {

    return world.getPet().getPetPosition();
  }

  public int getNumRooms() {

    return world.getNumRooms();

  }

  public int getNumItems() {

    return world.getNumItems();

  }

  public List<Room> getAllRooms() {

    List<Room> copiedRooms = new ArrayList<>(world.getRooms());
    return copiedRooms;
  }

  public Room getRoomByRoomIndex(int index) {
    Room originalRoom = world.getRoomByIndex(index);

    Room copiedRoom = new Room(originalRoom.getUpperLeftRow(), originalRoom.getUpperLeftColumn(),
        originalRoom.getLowerRightRow(), originalRoom.getLowerRightColumn(), originalRoom.getName(),
        originalRoom.getIndex());

    return copiedRoom;
  }

  public String getRoomNameByRoomIndex(int index) {
    String roomName = world.getRoomByIndex(index).getName();

    return new String(roomName);
  }

  public List<Item> getAllItems() {

    List<Item> copiedItems = new ArrayList<>(world.getItems());
    return copiedItems;
  }

  public List<Player> getAllPlayers() {

    List<Player> copiedPlayers = new ArrayList<>(world.getPlayers());

    return copiedPlayers;
  }

}

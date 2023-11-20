package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a room in the game world.
 */
public class Room {

  private final int upperLeftRow;
  private final int upperLeftColumn;
  private final int lowerRightRow;
  private final int lowerRightColumn;
  private final int index;
  private final String name;

  /**
   * Constructs a new Room instance.
   *
   * @param upperLeftRowIn     The upper-left row coordinate of the room.
   * @param upperLeftColumnIn  The upper-left column coordinate of the room.
   * @param lowerRightRowIn    The lower-right row coordinate of the room.
   * @param lowerRightColumnIn The lower-right column coordinate of the room.
   * @param nameIn             The name of the room.
   * @param indexIn            The placement index of the room.
   */
  public Room(int upperLeftRowIn, int upperLeftColumnIn, int lowerRightRowIn,
      int lowerRightColumnIn, String nameIn, int indexIn) {

    this.upperLeftRow = upperLeftRowIn;
    this.upperLeftColumn = upperLeftColumnIn;
    this.lowerRightRow = lowerRightRowIn;
    this.lowerRightColumn = lowerRightColumnIn;
    this.name = nameIn;
    this.index = indexIn;
  }

  /**
   * Gets the upper-left row coordinate of the room.
   *
   * @return The upper-left row coordinate.
   */
  public int getUpperLeftRow() {
    return upperLeftRow;
  }

  /**
   * Gets the upper-left column coordinate of the room.
   *
   * @return The upper-left column coordinate.
   */
  public int getUpperLeftColumn() {
    return upperLeftColumn;
  }

  /**
   * Gets the lower-right row coordinate of the room.
   *
   * @return The lower-right row coordinate.
   */
  public int getLowerRightRow() {
    return lowerRightRow;
  }

  /**
   * Gets the lower-right column coordinate of the room.
   *
   * @return The lower-right column coordinate.
   */
  public int getLowerRightColumn() {
    return lowerRightColumn;
  }

  /**
   * Gets the name of the room.
   *
   * @return The name of the room.
   */
  public String getName() {
    return name;
  }

  /**
   * Gets the placement index of the room.
   *
   * @return The placement index of the room.
   */
  public int getIndex() {
    return index;
  }

  /**
   * Gets a list of neighboring rooms based on the provided list of all rooms.
   *
   * @param allRooms The list of all rooms in the game.
   * @return A list of neighboring rooms.
   */
  public List<Room> getNeighbors(List<Room> allRooms) {
    List<Room> neighbors = new ArrayList<>();

    for (Room otherRoom : allRooms) {
      if (areNeighbors(otherRoom)) {
        neighbors.add(otherRoom);
      }
    }

    return neighbors;
  }

  /**
   * Checks whether two rooms are neighbors or not.
   *
   * @param other The other room to check for neighbor relationship.
   * @return True if the rooms are neighbors, false otherwise.
   */
  public boolean areNeighbors(Room other) {
    // Check if rooms are next to each other horizontally
    boolean isHorizontalNeighbor = (this.upperLeftRow <= other.lowerRightRow
        && this.lowerRightRow >= other.upperLeftRow
        && (this.lowerRightColumn + 1 == other.upperLeftColumn
            || this.upperLeftColumn - 1 == other.lowerRightColumn));
    // Check if rooms are next to each other vertically
    boolean isVerticalNeighbor = (this.upperLeftColumn <= other.lowerRightColumn
        && this.lowerRightColumn >= other.upperLeftColumn
        && (this.lowerRightRow + 1 == other.upperLeftRow
            || this.upperLeftRow - 1 == other.lowerRightRow));
    return isHorizontalNeighbor || isVerticalNeighbor;
  }

}

package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Room class.
 */

public class Room {

  private int upperLeftRow;
  private int upperLeftColumn;
  private int lowerRightRow;
  private int lowerRightColumn;
  private int index;
  private String name;

  /**
   * constructor for room.
   * 
   * @param upperLeftRowIn              walls of the room.
   * @param upperLeftColumnIn           walls of the room.
   * @param lowerRightRowIn             walls of the room.
   * @param lowerRightColumnumnumnumnIn walls of the room.
   * @param nameIn                      name of the room.
   * @param indexIn                     placement of the room.
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

  public int getUpperLeftRow() {
    return upperLeftRow;
  }

  public int getUpperLeftColumn() {
    return upperLeftColumn;
  }

  public int getLowerRightRow() {
    return lowerRightRow;
  }

  public int getLowerRightColumn() {
    return lowerRightColumn;
  }

  public String getName() {
    return name;
  }

  public int getIndex() {
    return index;
  }

  /**
   * get neighboring rooms by giving list of all rooms.
   * 
   * @param allRooms list of all the rooms
   * @return all the neighbors
   */
  // Get a list of all neighboring rooms
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
   * checks whether two rooms are neighbors or not.
   * 
   * @param otherRoom takes the other room
   * @return true or false
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

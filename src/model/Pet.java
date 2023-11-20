package model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

/**
 * Class representing a pet and its movement in the game.
 */
public class Pet {

  private final String name;
  private int petPosition;
  private Room currentRoom;
  private Set<Room> visitedRooms;
  private final List<Room> allRooms;
  private Stack<Room> stack;
  
  /**
   * Constructs a new Pet instance.
   *
   * @param nameIn        The name of the pet.
   * @param currentRoomIn The initial room where the pet is placed.
   * @param allRoomsIn    List of all rooms in the game.
   */
  public Pet(String nameIn, Room currentRoomIn, List<Room> allRoomsIn) {
    this.name = nameIn;
    this.currentRoom = currentRoomIn;
    this.allRooms = allRoomsIn;
    this.visitedRooms = new HashSet<>();
    this.stack = new Stack<>();
    this.petPosition = 0;
  }

  /**
   * Gets the name of the pet.
   *
   * @return The name of the pet.
   */
  public String getName() {
    return name;
  }

  /**
   * Gets the current position of the pet.
   *
   * @return The current position of the pet.
   */
  public int getPetPosition() {
    return petPosition;
  }

  /**
   * Sets the position of the pet.
   *
   * @param petPositionIn The new position of the pet.
   */
  public void setPetPosition(int petPositionIn) {
    this.petPosition = petPositionIn;
  }

  /**
   * Gets the current room where the pet is located.
   *
   * @return The current room of the pet.
   */
  public Room getCurrentRoom() {
    return currentRoom;
  }

  /**
   * Sets the current room of the pet.
   *
   * @param currentRoomIn The new current room of the pet.
   */
  public void setCurrentRoom(Room currentRoomIn) {
    this.currentRoom = currentRoomIn;
  }

  /**
   * Gets the set of visited rooms by the pet.
   *
   * @return The set of visited rooms.
   */
  public Set<Room> getVisitedRooms() {
    return visitedRooms;
  }

  /**
   * Sets the set of visited rooms by the pet.
   *
   * @param visitedRoomsIn The new set of visited rooms.
   */
  public void setVisitedRooms(Set<Room> visitedRoomsIn) {
    this.visitedRooms = visitedRoomsIn;
  }

  /**
   * Gets the list of all rooms in the game.
   *
   * @return The list of all rooms.
   */
  public List<Room> getAllRooms() {
    return allRooms;
  }

  /**
   * Performs DFS movement of the pet in the game.
   */
  public void move() {
    // If all rooms have been visited, start over from the first room
    if (visitedRooms.size() == allRooms.size()) {
      visitedRooms.clear();
      stack.clear();
      currentRoom = allRooms.get(0);
      petPosition = currentRoom.getIndex();
    }

    // If the stack is empty or all neighboring rooms have been visited, reset the
    // visited status
    if (stack.isEmpty() || allNeighborsVisited(currentRoom)) {
      visitedRooms.clear();
      stack.push(currentRoom);
    }

    visitedRooms.add(currentRoom);

    // Find the first unvisited neighboring room and move to it
    Room nextRoom = getFirstUnvisitedNeighbor(currentRoom);
    if (nextRoom != null) {
      stack.push(nextRoom);
      currentRoom = nextRoom;
      petPosition = currentRoom.getIndex();
    }
  }

  private boolean allNeighborsVisited(Room room) {
    List<Room> neighboringRooms = room.getNeighbors(allRooms);
    for (Room neighbor : neighboringRooms) {
      if (!visitedRooms.contains(neighbor)) {
        return false;
      }
    }
    return true;
  }

  private Room getFirstUnvisitedNeighbor(Room room) {
    List<Room> neighboringRooms = room.getNeighbors(allRooms);
    for (Room neighbor : neighboringRooms) {
      if (!visitedRooms.contains(neighbor)) {
        return neighbor;
      }
    }
    return null;
  }

}

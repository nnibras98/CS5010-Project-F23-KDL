package model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

/**
 * pet creation class.
 */
public class Pet {

  private final String name;
  private int petPosition;
  private Room currentRoom;
  private Set<Room> visitedRooms;
  private final List<Room> allRooms;
  private Stack<Room> stack;
  
  /**
   * constructor for pet creation.
   * @param nameIn name of the pet
   * @param currentRoomIn entry room.
   * @param allRoomsIn list of all rooms.
   */
  public Pet(String nameIn, Room currentRoomIn, List<Room> allRoomsIn) {

    this.name = nameIn;
    this.currentRoom = currentRoomIn;
    this.allRooms = allRoomsIn;
    this.visitedRooms = new HashSet<>();
    this.stack = new Stack<>();
    this.petPosition = 0;

  }

  public String getName() {
    return name;
  }

  public int getPetPosition() {
    return petPosition;
  }

  public void setPetPosition(int petPositionIn) {
    this.petPosition = petPositionIn;
  }

  public Room getCurrentRoom() {
    return currentRoom;
  }

  public void setCurrentRoom(Room currentRoomIn) {
    this.currentRoom = currentRoomIn;
  }

  public Set<Room> getVisitedRooms() {
    return visitedRooms;
  }

  public void setVisitedRooms(Set<Room> visitedRoomsIn) {
    this.visitedRooms = visitedRoomsIn;
  }

  public List<Room> getAllRooms() {
    return allRooms;
  }

  /**
   *  DFS movement of the pet.
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

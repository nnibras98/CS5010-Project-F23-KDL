package model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Pet {
  
  private final String name;
  private int petPosition;
  private Room currentRoom;
  private Set<Room> visitedRooms;
  private final List<Room> allRooms;
  
  public Pet(String nameIn, Room currentRoomIn, List<Room> allRoomsIn) {
    
    this.name = nameIn;
    this.currentRoom = currentRoomIn;
    this.allRooms = allRoomsIn;
    this.visitedRooms = new HashSet<>();
    this.petPosition = 0;
    
  }

  public String getName() {
    return name;
  }

  public int getPetPosition() {
    return petPosition;
  }

  public void setPetPosition(int petPosition) {
    this.petPosition = petPosition;
  }
  
  public Room getCurrentRoom() {
    return currentRoom;
  }

  public void setCurrentRoom(Room currentRoom) {
    this.currentRoom = currentRoom;
  }

  public Set<Room> getVisitedRooms() {
    return visitedRooms;
  }

  public void setVisitedRooms(Set<Room> visitedRooms) {
    this.visitedRooms = visitedRooms;
  }

  public List<Room> getAllRooms() {
    return allRooms;
  }
  
  //DFS implementation of move.
  public void move() {
    visitedRooms.add(currentRoom);
    
    // Get a list of all neighboring rooms
    List<Room> neighboringRooms = currentRoom.getNeighbors(allRooms);

    // Find the first unvisited neighboring room
    Room nextRoom = null;
    for (Room neighbor : neighboringRooms) {
        if (!visitedRooms.contains(neighbor)) {
            nextRoom = neighbor;
            break;
        }
    }

    // If an unvisited neighboring room is found, move to it
    if (nextRoom != null) {
        currentRoom = nextRoom;
        petPosition = currentRoom.getIndex();
    }
}



}

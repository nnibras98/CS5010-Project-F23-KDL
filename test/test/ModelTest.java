package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import model.HumanPlayer;
import model.Item;
import model.Pet;
import model.Player;
import model.Room;
import model.TargetCharacter;
import model.WorldImpl;

public class ModelTest {

  private WorldImpl world;

  @Before
  public void setUp() {
    // Use a sample text file for testing
    File file = new File("C:\\\\CS5010 WorkSpace\\\\Game-Development-CS5010-Project\\\\res\\\\mansion.txt");
    world = new WorldImpl(file);
  }

  @Test
  public void testGetNumRows() {
    assertEquals(36, world.getNumRows());
  }

  @Test
  public void testGetNumCols() {
    assertEquals(30, world.getNumCols());
  }

  @Test
  public void testGetTargetCharacter() {
    assertNotNull(world.getTargetCharacter());
  }

  @Test
  public void testGetPet() {
    assertNotNull(world.getPet());
  }

  @Test
  public void testGetRooms() {
    assertFalse(world.getRooms().isEmpty());
  }

  @Test
  public void testGetItems() {
    assertFalse(world.getItems().isEmpty());
  }

  @Test
  public void testGetWorldName() {
    assertEquals("Doctor Lucky's Mansion", world.getWorldName());
  }

  @Test
  public void testGetNumRooms() {
    assertEquals(21, world.getNumRooms());
  }

  @Test
  public void testGetNumItems() {
    assertEquals(20, world.getNumItems());
  }

  @Test
  public void testGetPlayers() {
    assertNotNull(world.getPlayers());
  }

  @Test
  public void testAddPlayer() {
    Player player = new HumanPlayer("Nafi", 1, 10, world);
    world.addPlayer(player);
    assertTrue(world.getPlayers().contains(player));
  }

  @Test
  public void testParseWorldFromFile() {
    assertTrue(world.getIsEnd());
  }

  @Test
  public void testGetRoomByIndex() {
    assertNotNull(world.getRoomByIndex(0));
    assertNull(world.getRoomByIndex(100)); // Non-existent room
  }

  @Test
  public void testGetNeighborsByIndex() {
    List<Room> neighbors = world.getNeighborsByIndex(0);
    assertFalse(neighbors.isEmpty());
  }

  @Test
  public void testGetItemsByRoomIndex() {
    List<Item> items = world.getItemsByRoomIndex(0);
    assertFalse(items.isEmpty());
  }

  @Test
  public void testRemoveItemFromRoom() {
    Item itemToRemove = new Item(0, 10, "TestItem");
    world.removeItemFromRoom(0, itemToRemove);
    assertFalse(world.getItems().contains(itemToRemove));
  }

  
  private TargetCharacter targetCharacter;

  @Before
  public void setUp2() {
    targetCharacter = new TargetCharacter(100, "TestTarget", 3);
  }

  @Test
  public void testGetHealth() {
    assertEquals(100, targetCharacter.getHealth());
  }

  @Test
  public void testSetHealth() {
    targetCharacter.setHealth(80);
    assertEquals(80, targetCharacter.getHealth());
  }

  @Test
  public void testGetName() {
    assertEquals("TestTarget", targetCharacter.getName());
  }

  @Test
  public void testSetName() {
    targetCharacter.setName("NewTargetName");
    assertEquals("NewTargetName", targetCharacter.getName());
  }

  @Test
  public void testGetCharacterPositionIndex() {
    assertEquals(0, targetCharacter.getCharacterPositionIndex());
  }

  @Test
  public void testGetTargetNumRooms() {
    assertEquals(3, targetCharacter.getNumRooms());
  }

  @Test
  public void testMove() {
    // Move the character within the valid range
    targetCharacter.move();
    assertEquals(1, targetCharacter.getCharacterPositionIndex());

    // Move the character to the last room and then one more time
    targetCharacter.move();
    targetCharacter.move();
    targetCharacter.move(); // Character should wrap around to the first room
    assertEquals(1, targetCharacter.getCharacterPositionIndex());
  }

  @Test
  public void testTakeDamage() {
    targetCharacter.takeDamage(20);
    assertEquals(80, targetCharacter.getHealth());
  }
  
  private Room room;

  @Before
  public void setUp3() {
    room = new Room(0, 0, 2, 2, "TestRoom", 0);
  }

  @Test
  public void testGetUpperLeftRow() {
    assertEquals(0, room.getUpperLeftRow());
  }

  @Test
  public void testGetUpperLeftColumn() {
    assertEquals(0, room.getUpperLeftColumn());
  }

  @Test
  public void testGetLowerRightRow() {
    assertEquals(2, room.getLowerRightRow());
  }

  @Test
  public void testGetLowerRightColumn() {
    assertEquals(2, room.getLowerRightColumn());
  }

  @Test
  public void testGetName2() {
    assertEquals("TestRoom", room.getName());
  }

  @Test
  public void testGetIndex() {
    assertEquals(0, room.getIndex());
  }

  @Test
  public void testGetNeighbors() {
    List<Room> allRooms = new ArrayList<>();
    Room neighbor1 = new Room(0, 3, 2, 4, "Neighbor1", 1);
    Room neighbor2 = new Room(3, 0, 4, 2, "Neighbor2", 2);
    Room notNeighbor = new Room(5, 5, 6, 6, "NotNeighbor", 3);

    allRooms.add(room);
    allRooms.add(neighbor1);
    allRooms.add(neighbor2);
    allRooms.add(notNeighbor);

    List<Room> neighbors = room.getNeighbors(allRooms);

    assertTrue(neighbors.contains(neighbor1));
    assertTrue(neighbors.contains(neighbor2));
    assertFalse(neighbors.contains(notNeighbor));
  }

  @Test
  public void testAreNeighbors() {
    Room neighborHorizontal = new Room(0, 3, 2, 4, "NeighborHorizontal", 1);
    Room neighborVertical = new Room(3, 0, 4, 2, "NeighborVertical", 2);
    Room notNeighbor = new Room(5, 5, 6, 6, "NotNeighbor", 3);

    assertTrue(room.areNeighbors(neighborHorizontal));
    assertTrue(room.areNeighbors(neighborVertical));
    assertFalse(room.areNeighbors(notNeighbor));
  }
  
  
  private Pet testPet;
  private List<Room> testRooms;

  @Before
  public void setUp4() {
      
      testRooms = world.getRooms();
      
      testPet = new Pet("TestPet", testRooms.get(0), testRooms);
  }

  @Test
  public void testConstructor() {
      assertEquals("TestPet", testPet.getName());
      assertEquals(0, testPet.getPetPosition());
      assertEquals(testRooms.get(0), testPet.getCurrentRoom());
      assertTrue(testPet.getVisitedRooms().isEmpty());
      assertEquals(testRooms, testPet.getAllRooms());
  }

  @Test
  public void testSetPetPosition() {
      testPet.setPetPosition(1);
      assertEquals(1, testPet.getPetPosition());
  }

  @Test
  public void testSetCurrentRoom()  {
      Room newRoom = testRooms.get(5);
      testPet.setCurrentRoom(newRoom);
      assertEquals(newRoom, testPet.getCurrentRoom());
  }

  @Test
  public void testSetVisitedRooms() {
      Room room1 = testRooms.get(0);
      Room room2 = testRooms.get(4);
      Set<Room> visitedRooms = new HashSet<>(Arrays.asList(room1, room2));

      testPet.setVisitedRooms(visitedRooms);
      assertEquals(visitedRooms, testPet.getVisitedRooms());
  }

  @Test
  public void testMove2() {
      Room room1 = testRooms.get(0);
      Room room2 = testRooms.get(1);
      Room room3 = testRooms.get(3);

      List<Room> rooms = Arrays.asList(room1, room2, room3);
      Pet pet = new Pet("TestPet", room1, rooms);

      // Perform moves
      pet.move(); // Move 1
      assertEquals(1, pet.getPetPosition());
      assertTrue(pet.getVisitedRooms().contains(room1));

      pet.move(); // Move 2
      assertEquals(3, pet.getPetPosition());
      assertTrue(pet.getVisitedRooms().contains(room2));

      pet.move(); // Move 3
      assertEquals(0, pet.getPetPosition()); // Should wrap around to the first room
      assertTrue(pet.getVisitedRooms().contains(room3));

      pet.move(); // Move 4
      assertEquals(1, pet.getPetPosition());
      assertTrue(pet.getVisitedRooms().contains(room1));

  }

  

}

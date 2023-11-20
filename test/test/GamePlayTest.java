package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.io.PrintStream;
import model.HumanPlayer;
import model.Player;
import model.WorldImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * tests for game play.
 */
public class GamePlayTest {

  private final InputStream originalSystemIn = System.in;
  private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
  private WorldImpl world;
  
  /**
   * set up.
   */
  @Before
  public void setUp() {
    System.setIn(new ByteArrayInputStream("".getBytes()));
    File file = new File(
        "C:\\\\CS5010 WorkSpace\\\\Game-Development-CS5010-Project\\\\res\\\\mansion.txt");
    this.world = new WorldImpl(file);
  }
  
  /**
   * break down.
   */
  @After
  public void tearDown() {
    // Restore the original System.in after the test
    System.setIn(originalSystemIn);
    System.setOut(new PrintStream(outContent));
  }

  @Test
  public void testMove() {
    String simulatedInput = "3\n";
    System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

    String simulatedInput2 = "3\n";
    System.setIn(new ByteArrayInputStream(simulatedInput2.getBytes()));

    Player player = new HumanPlayer("Nafi", 1, 1, world);
    world.addPlayer(player);
    player.move();

    assertEquals(18, player.getCurrentRoomIndex());
  }

  @Test
  public void testPickUp() {
    String simulatedInput = "2\n";
    System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

    String simulatedInput2 = "1\n";
    System.setIn(new ByteArrayInputStream(simulatedInput2.getBytes()));

    Player player = new HumanPlayer("Nafi", 2, 8, world);
    world.addPlayer(player);
    player.pickUp();

    assertEquals("Crepe Pan", player.getInventory().get(0).getName());
  }

  @Test
  public void testLookAround() {
    String simulatedInput = "1\n"; // simulate the user entering "1" followed by Enter
    System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

    Player player = new HumanPlayer("Nafi", 2, 8, world);
    world.addPlayer(player);
    player.lookAround();

    String expectedOutput = "Neighboring room(s) of Kitchen-\n" + "Dining Hall\r\n" + "Parlor\r\n"
        + "Wine Cellar";

    assertTrue(outContent.toString().contains(expectedOutput));
  }

  @Test
  public void testKillAttemptSuccessful() {
    // Set up the player and the world for a successful kill attempt
    Player player = new HumanPlayer("Nafi", 2, 0, world);

    world.getTargetCharacter().setHealth(1);

    // Set the player's world
    world.addPlayer(player);

    // Call the killAttempt method
    player.killAttempt();

    // Verify that the correct information is printed to the console
    assertTrue(outContent.toString()
        .contains(("Attempt successful! " + world.getTargetCharacter().getName() + " loses " + "1"
            + " health point(s), the current health is: "
            + world.getTargetCharacter().getHealth())));
  }

  @Test
  public void testKillAttemptNotSuccessful() {
    // Set up the player and the world for a successful kill attempt
    Player player = new HumanPlayer("Nafi", 2, 1, world);

    world.getTargetCharacter().setHealth(5);

    // Set the player's world
    world.addPlayer(player);

    // Call the killAttempt method
    player.killAttempt();

    // Verify that the correct information is printed to the console
    assertTrue(outContent.toString().contains("Attempt could not be made. No damage done."));
  }
  
  @Test
  public void testTargetMove() {

    world.getTargetCharacter().move();
    world.getTargetCharacter().move();

    // Verify that the correct information is printed to the console
    assertEquals(2, world.getTargetCharacter().getCharacterPositionIndex());
  }
  
  @Test
  public void testPetDfsMove() {

    world.getPet().move();

    // Verify that the correct information is printed to the console
    assertEquals(1, world.getPet().getPetPosition());
    
    world.getPet().move();

    // Verify that the correct information is printed to the console
    assertEquals(3, world.getPet().getPetPosition());
    
    
  }

}

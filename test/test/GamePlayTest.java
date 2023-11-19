package test;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import model.ComputerPlayer;
import model.HumanPlayer;
import model.Player;
import model.WorldImpl;

public class GamePlayTest {

  private WorldImpl world;

  @Before
  public void setUp() {
    // Use a sample text file for testing
    File file = new File("C:\\\\CS5010 WorkSpace\\\\Game-Development-CS5010-Project\\\\res\\\\mansion.txt");
    world = new WorldImpl(file);
    Player hp1 = new HumanPlayer("Nafi", 10, 0, world);
    Player hp2 = new HumanPlayer("Nibras", 5, 5, world);
    Player hp3 = new HumanPlayer("Jack", 7, 10, world);
    Player hp4 = new HumanPlayer("Thomas", 2, 15, world);
    
    Player cp1 = new ComputerPlayer("CP1", 10, 1, world);
    Player cp2 = new ComputerPlayer("CP2", 5, 5, world);
    Player cp3 = new ComputerPlayer("CP3", 7, 20, world);
    Player cp4 = new ComputerPlayer("CP4", 2, 16, world);
  }
  
  

}

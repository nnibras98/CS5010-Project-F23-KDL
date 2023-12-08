package guiBasedController;

import java.io.File;

import model.ComputerPlayer;
import model.HumanPlayer;
import model.Player;
import model.WorldImpl;
import model.WorldInterface;
import view.GameGUI;
import view.WorldPanel;

public class GameController {
  private WorldInterface model;
  private GameGUI view;

  public GameController(String modelFile, GameGUI view) {
    this.model = new WorldImpl(new File(modelFile));
    this.view = view;
  }

  public void startGame() {
    // Any additional setup logic before starting the game
    // ...

    // Show the initial game screen

  }
  
  private WorldPanel getWorldPanel() {
    
    return view.getWorldPanel();
    
  }

  public void addHumanPlayer(String name, int maxCarryCapacity, int currentRoomIndex) {
    Player humanPlayer = new HumanPlayer(name, maxCarryCapacity, currentRoomIndex, model);
    model.addPlayer(humanPlayer);
  }

  public void addComputerPlayer(String name, int maxCarryCapacity, int currentRoomIndex) {
    Player computerPlayer = new ComputerPlayer(name, maxCarryCapacity, currentRoomIndex, model);
    model.addPlayer(computerPlayer);
  }
  

  public WorldInterface getModel() {

    return model;
  }

}

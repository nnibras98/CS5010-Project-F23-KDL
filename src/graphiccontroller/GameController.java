package graphiccontroller;

import java.io.File;
import java.util.List;
import model.ComputerPlayer;
import model.HumanPlayer;
import model.Player;
import model.WorldImpl;
import model.WorldInterface;
import view.GameGui;

/**
 * Constructs a GameController instance.
 */
public class GameController {
  private WorldInterface model;
  private GameGui view;
  private ControllerFacade controlFacade;
  private int used;

  /**
   * Constructs a GameController instance.
   *
   * @param modelFile The file containing the initial game state.
   * @param viewIn      The graphical user interface for the game.
   */
  public GameController(String modelFile, GameGui viewIn) {
    this.model = new WorldImpl(new File(modelFile));
    this.view = view;
    this.controlFacade = new ControllerFacade(model);
    used = 0;
  }

  /**
   * Starts the game and performs any necessary setup logic.
   */
  public void startGame() {
    // Any additional setup logic before starting the game
    // ...

    // Show the initial game screen
  }

  /**
   * Manages the game turns, including player actions and win/lose conditions.
   */
  public void takeTurn() {
    // Prompt user for the maximum number of turns
    int maxTurns = view.getMaxTurns();

    // Get the list of players from the world
    List<Player> players = model.getPlayers();

    // Game loop
    for (int turn = 1; turn <= maxTurns; turn++) {
      // Iterate through each player and take their turn
      for (Player player : players) {

        // Human player actions
        if (player instanceof HumanPlayer) {
          takeHumanPlayerTurn((HumanPlayer) player);
        } else if (player instanceof ComputerPlayer) {
          takeComputerPlayerTurn((ComputerPlayer) player);
        }

        // Display game state after each turn
        // displayGameState();

        // Check winning condition after each turn
        if (model.getTargetCharacter().getHealth() <= 0) {
          return;
        }
      }
    }

    // If all turns are done and the target's health is not zero
    System.out.println("\nThe target character escapes! No one wins.");
  }

  /**
   * Handles the turn for a human player.
   *
   * @param humanPlayer The human player taking the turn.
   */
  private void takeHumanPlayerTurn(HumanPlayer humanPlayer) {
    // ...
  }

  /**
   * Handles the turn for a computer player.
   *
   * @param computerPlayer The computer player taking the turn.
   */
  private void takeComputerPlayerTurn(ComputerPlayer computerPlayer) {
    // ...
  }

  /**
   * Repaints the world panel to reflect any changes in the game state.
   */
  private void repaintWorldPanel() {
    view.getWorldPanel().repaint();
  }

  /**
   * Adds a human player to the game.
   *
   * @param name             The name of the human player.
   * @param maxCarryCapacity The maximum carrying capacity of the human player.
   * @param currentRoomIndex The initial room index of the human player.
   */
  public void addHumanPlayer(String name, int maxCarryCapacity, int currentRoomIndex) {
    Player humanPlayer = new HumanPlayer(name, maxCarryCapacity, currentRoomIndex, model);
    model.addPlayer(humanPlayer);
  }

  /**
   * Adds a computer player to the game.
   *
   * @param name             The name of the computer player.
   * @param maxCarryCapacity The maximum carrying capacity of the computer player.
   * @param currentRoomIndex The initial room index of the computer player.
   */
  public void addComputerPlayer(String name, int maxCarryCapacity, int currentRoomIndex) {
    Player computerPlayer = new ComputerPlayer(name, maxCarryCapacity, currentRoomIndex, model);
    model.addPlayer(computerPlayer);
  }

  /**
   * Moves the specified player to a new room.
   *
   * @param player The player to move.
   */
  public void movePlayer(Player player) {
    controlFacade.playerMove(player);
    used = 0;
    repaintWorldPanel();
  }

  /**
   * Allows the specified player to pick up an item in their current room.
   *
   * @param player The player picking up the item.
   */
  public void pickUpPlayer(Player player) {
    controlFacade.playerPickUpItem(player);
    used = 0;
    repaintWorldPanel();
  }

  /**
   * Allows the specified player to attempt to kill the target character.
   *
   * @param player The player attempting to kill.
   */
  public void attemptPlayer(Player player) {
    controlFacade.playerKillAttempt(player);
    used = 0;
    repaintWorldPanel();
  }

  /**
   * Sets the "used" flag to indicate that the player has looked around in the
   * current turn.
   */
  public void lookAround() {
    used = 1;
  }

  /**
   * Gets the value of the "used" flag.
   *
   * @return The value of the "used" flag.
   */
  public int getUsed() {
    return used;
  }

  /**
   * Moves the pet in the game.
   */
  public void movePet() {
    controlFacade.petMove();
    repaintWorldPanel();
  }

  /**
   * Moves the target character in the game.
   */
  public void moveTargetCharacter() {
    controlFacade.targetCharacterMove();
    repaintWorldPanel();
  }

  /**
   * Retrieves the game model.
   *
   * @return The game model.
   */
  public WorldInterface getModel() {
    return model;
  }

}

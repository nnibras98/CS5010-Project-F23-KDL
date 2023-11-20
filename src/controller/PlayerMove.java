package controller;

import model.Player;

/**
 * Player move handler.
 */
public class PlayerMove implements CommandInterface {

  private Player player;

  /**
   * Constructs a new PlayerMove instance with the specified player.
   *
   * @param playerIn The player to be moved.
   */
  public PlayerMove(Player playerIn) {
    this.player = playerIn;
  }

  /**
   * Executes the logic to move the player.
   */
  @Override
  public void execute() {
    // Implement the logic to move the player
    player.move();
  }

}

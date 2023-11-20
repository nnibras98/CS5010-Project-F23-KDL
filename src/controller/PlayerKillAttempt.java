package controller;

import model.Player;

/**
 * Player kill attempt handler.
 */
public class PlayerKillAttempt implements CommandInterface {

  private Player player;

  /**
   * Constructs a new PlayerKillAttempt instance with the specified player.
   *
   * @param playerIn The player involved in the kill attempt.
   */
  public PlayerKillAttempt(Player playerIn) {
    this.player = playerIn;
  }

  /**
   * Executes the logic for a player kill attempt.
   */
  @Override
  public void execute() {
    // Implement the logic for the kill attempt
    player.killAttempt();
  }

}

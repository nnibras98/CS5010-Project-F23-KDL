package controller;

import model.Player;

/**
 * kill attempt handler.
 */
public class PlayerKillAttempt implements CommandInterface {
  private Player player;

  public PlayerKillAttempt(Player playerIn) {
    this.player = playerIn;
  }

  @Override
  public void execute() {
    // Implement the logic to move the target character
    player.killAttempt();
  }
}
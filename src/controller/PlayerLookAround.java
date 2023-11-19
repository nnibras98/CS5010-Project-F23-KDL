package controller;

import model.Player;

/**
 * look around handler.
 */
public class PlayerLookAround implements CommandInterface {
  private Player player;

  public PlayerLookAround(Player playerIn) {
    this.player = playerIn;
  }

  @Override
  public void execute() {
    // Implement the logic to move the target character
    player.lookAround();
  }
}
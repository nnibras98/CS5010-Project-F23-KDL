package controller;

import model.Player;

/**
 * player move handler.
 */
public class PlayerMove implements CommandInterface {

  private Player player;

  public PlayerMove(Player playerIn) {
    this.player = playerIn;
  }

  @Override
  public void execute() {
    // Implement the logic to move the target character
    player.move();
  }

}

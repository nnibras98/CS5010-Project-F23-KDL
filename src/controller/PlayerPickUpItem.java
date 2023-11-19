package controller;

import model.Player;

/**
 * pick up handler.
 */
public class PlayerPickUpItem implements CommandInterface {

  private Player player;

  public PlayerPickUpItem(Player playerIn) {
    this.player = playerIn;
  }

  @Override
  public void execute() {
    // Implement the logic to move the target character
    player.pickUp();
  }

}
package controller;

import model.Player;

public class PlayerDropItem implements CommandInterface {
  
  private Player player;

  public PlayerDropItem(Player playerIn) {
      this.player = playerIn;
  }

  @Override
  public void execute() {
      // Implement the logic to move the target character
      player.drop();
  }

}
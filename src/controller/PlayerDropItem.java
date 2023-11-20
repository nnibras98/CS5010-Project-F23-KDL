package controller;

import model.Player;

/**
 * Drop item handler.
 */
public class PlayerDropItem implements CommandInterface {

  private Player player;

  /**
   * Constructs a new PlayerDropItem instance with the specified player.
   *
   * @param playerIn The player who will drop the item.
   */
  public PlayerDropItem(Player playerIn) {
    this.player = playerIn;
  }

  /**
   * Executes the logic to make the player drop an item.
   */
  @Override
  public void execute() {
    // Implement the logic to drop the item
    player.drop();
  }

}

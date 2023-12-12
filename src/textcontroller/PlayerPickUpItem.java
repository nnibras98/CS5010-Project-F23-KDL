package textcontroller;

import model.Player;

/**
 * Player pick up item handler.
 */
public class PlayerPickUpItem implements CommandInterface {

  private Player player;

  /**
   * Constructs a new PlayerPickUpItem instance with the specified player.
   *
   * @param playerIn The player who will pick up an item.
   */
  public PlayerPickUpItem(Player playerIn) {
    this.player = playerIn;
  }

  /**
   * Executes the logic to make the player pick up an item.
   */
  @Override
  public void execute() {
    // Implement the logic to pick up the item
    player.pickUp();
  }

}

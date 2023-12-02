package textBasedController;

import model.Player;

/**
 * Player look around handler.
 */
public class PlayerLookAround implements CommandInterface {
  private Player player;

  /**
   * Constructs a new PlayerLookAround instance with the specified player.
   *
   * @param playerIn The player who will look around.
   */
  public PlayerLookAround(Player playerIn) {
    this.player = playerIn;
  }

  /**
   * Executes the logic to make the player look around.
   */
  @Override
  public void execute() {
    // Implement the logic to look around
    player.lookAround();
  }
}

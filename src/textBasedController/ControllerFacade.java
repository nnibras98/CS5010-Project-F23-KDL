package textBasedController;

import model.Player;
import model.WorldInterface;

/**
 * Brings all the controller methods in one place.
 */
public class ControllerFacade {

  private final WorldInterface world;

  /**
   * constructor.
   * @param worldIn the world.
   */
  public ControllerFacade(WorldInterface worldIn) {
    this.world = worldIn;
  }
  
  /**
   * world getter.
   * @return world.
   */
  public WorldInterface getWorld() {
    return world;
  }

  /**
   * player action generic.
   * @param player player type.
   * @param commandType interface.
   */
  public void playerAction(Player player, CommandInterface commandType) {

    commandType.execute();

  }
  
  /**
   * player move.
   * @param currentPlayer take in player.
   */
  public void playerMove(Player currentPlayer) {

    CommandInterface playerMove = new PlayerMove(currentPlayer);
    playerMove.execute();
  }

  /**
   * player look around.
   * @param currentPlayer take in player.
   */
  public void playerLookAround(Player currentPlayer) {

    CommandInterface playerLookAround = new PlayerLookAround(currentPlayer);
    playerLookAround.execute();
  }

  /**
   * player pick up item.
   * @param currentPlayer take in player.
   */
  public void playerPickUpItem(Player currentPlayer) {

    CommandInterface playerPickUp = new PlayerPickUpItem(currentPlayer);
    playerPickUp.execute();
  }

  /**
   * player drop item.
   * @param currentPlayer take in player.
   */
  public void playerDropItem(Player currentPlayer) {

    CommandInterface playerDrop = new PlayerDropItem(currentPlayer);
    playerDrop.execute();
  }

  /**
   * player kill attempt.
   * @param currentPlayer take in player.
   */
  public void playerKillAttempt(Player currentPlayer) {

    CommandInterface playerKillAttempt = new PlayerKillAttempt(currentPlayer);
    playerKillAttempt.execute();
  }

  /**
   * pet move.
   */
  public void petMove() {
    CommandInterface petMove = new PetMove(world.getPet());
    petMove.execute();
  }

  /**
   * target move.
   */
  public void targetCharacterMove() {

    CommandInterface targetCharacterMove = new TargetCharacterMove(world.getTargetCharacter());
    targetCharacterMove.execute();

  }

}

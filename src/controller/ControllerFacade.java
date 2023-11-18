package controller;

import model.Player;
import model.WorldInterface;

public class ControllerFacade {
  
  private final WorldInterface world;
  

  public ControllerFacade(WorldInterface world) {
    this.world = world;
  }
  
  
  
  public WorldInterface getWorld() {
    return world;
  }



  //just added this to make it more modular
  public void playerAction(Player player, CommandInterface commandType) {
    
    commandType.execute();
    
  }

  public void playerMove(Player currentPlayer) {
    
    CommandInterface pMove = new PlayerMove(currentPlayer);
    pMove.execute();
  }
  
  public void playerLookAround(Player currentPlayer) {
    
    CommandInterface pLookAround = new PlayerLookAround(currentPlayer);
    pLookAround.execute();
  }

  public void playerPickUpItem(Player currentPlayer) {
    
    CommandInterface pPickUp = new PlayerPickUpItem(currentPlayer);
    pPickUp.execute();
  }

  public void playerDropItem(Player currentPlayer) {
    
    CommandInterface pDrop = new PlayerDropItem(currentPlayer);
    pDrop.execute();
  }

  public void playerKillAttempt(Player currentPlayer) {
    
    CommandInterface pKillAttempt = new PlayerKillAttempt(currentPlayer);
    pKillAttempt.execute();
  }

  public void PetMove() {
    CommandInterface petMove = new PetMove(world.getPet());
    petMove.execute();
  }

  public void TargetCharacterMove() {

    CommandInterface targetCharacterMove = new TargetCharacterMove(world.getTargetCharacter());
    targetCharacterMove.execute();

  }

}

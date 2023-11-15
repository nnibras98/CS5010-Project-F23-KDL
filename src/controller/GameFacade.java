package controller;

import model.Pet;
import model.Player;
import model.TargetCharacter;
import model.WorldImpl;

public class GameFacade {
  private final WorldImpl world;
  private final Pet pet;
  private final TargetCharacter targetCharacter;

  public GameFacade(WorldImpl world) {
    this.world = world;
    this.pet = world.getPet();
    this.targetCharacter = world.getTargetCharacter();
  }
  
  
  
  public WorldImpl getWorld() {
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
    CommandInterface petMove = new PetMove(pet);
    petMove.execute();
  }

  public void TargetCharacterMove() {

    CommandInterface targetCharacterMove = new TargetCharacterMove(targetCharacter);
    targetCharacterMove.execute();

  }

}

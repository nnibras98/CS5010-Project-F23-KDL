package controller;

import model.TargetCharacter;

/**
 * target move.
 */
public class TargetCharacterMove implements CommandInterface {
  private TargetCharacter targetCharacter;

  public TargetCharacterMove(TargetCharacter targetCharacterIn) {
    this.targetCharacter = targetCharacterIn;
  }

  @Override
  public void execute() {
    // Implement the logic to move the target character
    targetCharacter.move();
  }
}

package textcontroller;

import model.TargetCharacter;

/**
 * Target character move handler.
 */
public class TargetCharacterMove implements CommandInterface {
  private TargetCharacter targetCharacter;

  /**
   * Constructs a new TargetCharacterMove instance with the specified target character.
   *
   * @param targetCharacterIn The target character to be moved.
   */
  public TargetCharacterMove(TargetCharacter targetCharacterIn) {
    this.targetCharacter = targetCharacterIn;
  }

  /**
   * Executes the logic to move the target character.
   */
  @Override
  public void execute() {
    // Implement the logic to move the target character
    targetCharacter.move();
  }
}

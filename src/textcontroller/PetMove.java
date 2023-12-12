package textcontroller;

import model.Pet;

/**
 * Pet movement handler.
 */
public class PetMove implements CommandInterface {

  private Pet pet;

  /**
   * Constructs a new PetMove instance with the specified pet.
   *
   * @param petIn The pet to be moved.
   */
  public PetMove(Pet petIn) {
    this.pet = petIn;
  }

  /**
   * Executes the movement logic for the pet.
   */
  @Override
  public void execute() {
    // Implement the logic to move the pet
    pet.move();
  }

}

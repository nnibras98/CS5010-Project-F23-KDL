package controller;

import model.Pet;

public class PetMove implements CommandInterface {

  private Pet pet;

  public PetMove(Pet petIn) {
      this.pet = petIn;
  }

  @Override
  public void execute() {
      // Implement the logic to move the pet
      pet.move();
  }

}

package model;

/**
 * Helper class to build target character.
 */

public class TargetCharacter {
  private int health;
  private String name;
  private int characterPositionIndex;
  private int numRooms;
  
  /**
   * Constructor for target character.
   * @param healthIn health of target.
   * @param nameIn name of target.
   */

  public TargetCharacter(int healthIn, String nameIn, int numRoomsIn) {
    this.health = healthIn;
    this.name = nameIn;
    this.numRooms = numRoomsIn;
    this.characterPositionIndex = 0;
  }

  public int getHealth() {
    return health;
  }

  public void setHealth(int healthIn) {
    this.health = healthIn;
  }

  public String getName() {
    return name;
  }

  public void setName(String nameIn) {
    this.name = nameIn;
  }

  public int getCharacterPositionIndex() {
    return characterPositionIndex;
  }
  
  /**
   * moves character forward by 1 room.
   */
  
  public void move() {
    
    if (characterPositionIndex > numRooms - 1 ) {
      
      characterPositionIndex = 0;
    }

    characterPositionIndex += 1;
    
    System.out.println("Doctor Lucky has moved to - " + characterPositionIndex);
  }
  
  /**
   * reduces the health by damage.
   * @param damage - this is the damage caused.
   */
  public void takeDamage(int damage) {
    
    if (health < 0 ) {
     
      isDead();
     
    }

    health -= damage;
    
  }
  
  /**
   * Handles the signal when the health reaches 0.
   */
  private boolean isDead() {
      // Implement the logic to handle the signal when the health reaches 0
      return true;
  }

}

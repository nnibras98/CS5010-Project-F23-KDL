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
  }

}
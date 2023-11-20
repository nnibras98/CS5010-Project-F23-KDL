package model;

/**
 * Represents a target character in the game world.
 */
public class TargetCharacter {
  private int health;
  private final String name;
  private int characterPositionIndex;
  private final int numRooms;

  /**
   * Constructs a new TargetCharacter instance.
   *
   * @param healthIn            The initial health of the target character.
   * @param nameIn              The name of the target character.
   * @param numRoomsIn          The total number of rooms in the game.
   */
  public TargetCharacter(int healthIn, String nameIn, int numRoomsIn) {
    this.health = healthIn;
    this.name = nameIn;
    this.numRooms = numRoomsIn;
    this.characterPositionIndex = 0;
  }

  /**
   * Gets the current health of the target character.
   *
   * @return The current health value.
   */
  public int getHealth() {
    return health;
  }

  /**
   * Sets the health of the target character.
   *
   * @param healthIn The new health value to set.
   */
  public void setHealth(int healthIn) {
    this.health = healthIn;
  }

  /**
   * Gets the name of the target character.
   *
   * @return The name of the target character.
   */
  public String getName() {
    return name;
  }

  /**
   * Gets the current position index of the target character.
   *
   * @return The current position index.
   */
  public int getCharacterPositionIndex() {
    return characterPositionIndex;
  }

  /**
   * Gets the total number of rooms in the game.
   *
   * @return The total number of rooms.
   */
  public int getNumRooms() {
    return numRooms;
  }

  /**
   * Moves the character forward by 1 room, wrapping around if necessary.
   */
  public void move() {
    if (characterPositionIndex > numRooms - 1) {
      characterPositionIndex = 0;
    }
    characterPositionIndex += 1;
  }

  /**
   * Reduces the health of the target character by the specified damage amount.
   *
   * @param damage The amount of damage to be applied.
   */
  public void takeDamage(int damage) {
    int newHealth = health - damage;
    setHealth(newHealth);
  }
}

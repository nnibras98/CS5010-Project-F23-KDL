package model;

/**
 * Represents an item in the game.
 * This class is a helper class for the World class to generate items in the game world.
 */
public class Item {

  private int roomIndex;
  private final int damage;
  private final String name;

  /**
   * Constructs a new Item instance.
   *
   * @param roomIndexIn The position of the item in a room.
   * @param damageIn    The health reduction value applied to the target when using the item.
   * @param nameIn      The name of the item.
   */
  public Item(int roomIndexIn, int damageIn, String nameIn) {
    this.roomIndex = roomIndexIn;
    this.damage = damageIn;
    this.name = nameIn;
  }

  /**
   * Gets the room index where the item is located.
   *
   * @return The room index.
   */
  public int getRoomIndex() {
    return roomIndex;
  }

  /**
   * Gets the damage value associated with the item.
   *
   * @return The damage value.
   */
  public int getDamage() {
    return damage;
  }

  /**
   * Gets the name of the item.
   *
   * @return The name of the item.
   */
  public String getName() {
    return name;
  }
}

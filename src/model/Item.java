package model;

/**
 * This class acts as a helper class for World class to generate items list.
 */
public class Item {

  private int roomIndex;
  private int damage;
  private String name;
  /**
   * Item constructor.
   * @param roomIndexIn position of the room.
   * @param damageIn health of target reduced by damage value.
   * @param nameIn name of the item
   */
  
  public Item(int roomIndexIn, int damageIn, String nameIn) {

    this.roomIndex = roomIndexIn;
    this.damage = damageIn;
    this.name = nameIn;
  }

  public int getRoomIndex() {
    return roomIndex;
  }

  public int getDamage() {
    return damage;
  }

  public String getName() {
    return name;
  }
}
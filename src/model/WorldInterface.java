package model;

import java.io.File;
import java.util.List;

/**
 * Interface representing the world in the game.
 */
public interface WorldInterface {

    /**
     * Gets the number of rows in the world.
     *
     * @return The number of rows.
     */
    int getNumRows();

    /**
     * Gets the number of columns in the world.
     *
     * @return The number of columns.
     */
    int getNumCols();

    /**
     * Gets the target character in the world.
     *
     * @return The target character.
     */
    TargetCharacter getTargetCharacter();

    /**
     * Sets the target character in the world.
     *
     * @param targetCharacter The target character to set.
     */
    void setTargetCharacter(TargetCharacter targetCharacter);

    /**
     * Gets the pet in the world.
     *
     * @return The pet.
     */
    Pet getPet();

    /**
     * Sets the pet in the world.
     *
     * @param pet The pet to set.
     */
    void setPet(Pet pet);

    /**
     * Gets the list of rooms in the world.
     *
     * @return The list of rooms.
     */
    List<Room> getRooms();

    /**
     * Sets the list of rooms in the world.
     *
     * @param rooms The list of rooms to set.
     */
    void setRooms(List<Room> rooms);

    /**
     * Gets the list of items in the world.
     *
     * @return The list of items.
     */
    List<Item> getItems();

    /**
     * Sets the list of items in the world.
     *
     * @param items The list of items to set.
     */
    void setItems(List<Item> items);

    /**
     * Gets the name of the world.
     *
     * @return The name of the world.
     */
    String getWorldName();

    /**
     * Sets the name of the world.
     *
     * @param worldName The name of the world to set.
     */
    void setWorldName(String worldName);

    /**
     * Gets the number of rooms in the world.
     *
     * @return The number of rooms.
     */
    int getNumRooms();

    /**
     * Gets the number of items in the world.
     *
     * @return The number of items.
     */
    int getNumItems();

    /**
     * Gets the list of players in the world.
     *
     * @return The list of players.
     */
    List<Player> getPlayers();

    /**
     * Sets the list of players in the world.
     *
     * @param players The list of players to set.
     */
    void setPlayers(List<Player> players);

    /**
     * Adds a player to the world.
     *
     * @param player The player to add.
     */
    void addPlayer(Player player);

    /**
     * Parses the world information from a file.
     *
     * @param file The file containing world information.
     */
    void parseWorldFromFile(File file);

    /**
     * Gets a room by its index.
     *
     * @param index The index of the room.
     * @return The room or null if not found.
     */
    Room getRoomByIndex(int index);

    /**
     * Gets neighbors of a room by its index.
     *
     * @param index The index of the room.
     * @return The list of neighboring rooms or an empty list if the room is not found.
     */
    List<Room> getNeighborsByIndex(int index);

    /**
     * Gets the items present in a specified room.
     *
     * @param roomIndex The index of the room.
     * @return The list of items in the specified room.
     */
    List<Item> getItemsByRoomIndex(int roomIndex);

    /**
     * Removes the specified item from the room with the given index.
     *
     * @param roomIndex    The index of the room from which to remove the item.
     * @param itemToRemove The item to be removed from the room.
     */
    void removeItemFromRoom(int roomIndex, Item itemToRemove);

    /**
     * Adds an item to the room with the given index.
     *
     * @param roomIndex The index of the room to which the item should be added.
     * @param item      The item to be added to the room.
     */
    void addItemToRoom(int roomIndex, Item item);
}
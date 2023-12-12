package view;

import javax.swing.JLabel;
import javax.swing.JPanel;
import model.Item;
import model.Player;

/**
 * The PlayerInfoPanel class represents a Swing JPanel that displays information about a player,
 * including their name and inventory.
 */
public class PlayerInfoPanel extends JPanel {

  private static final long serialVersionUID = 1L;
  private JLabel playerNameLabel;
  private JLabel inventoryLabel;

  /**
   * Constructs a PlayerInfoPanel, initializing and adding JLabels for player name and inventory.
   */
  public PlayerInfoPanel() {
    playerNameLabel = new JLabel("Player Name: ");
    inventoryLabel = new JLabel("Inventory: ");

    // Add the labels to the panel
    add(playerNameLabel);
    add(inventoryLabel);
  }

  /**
   * Updates the player information displayed in the side panel.
   *
   * @param player The player whose information should be displayed.
   */
  public void updatePlayerInfo(Player player) {
    // Update player name label
    playerNameLabel.setText("Player Name: " + player.getName());

    // Update inventory label
    StringBuilder inventoryText = new StringBuilder("Inventory: ");
    for (Item item : player.getInventory()) {
      inventoryText.append(item.getName()).append(", ");
    }
    inventoryLabel.setText(inventoryText.toString());
  }
}

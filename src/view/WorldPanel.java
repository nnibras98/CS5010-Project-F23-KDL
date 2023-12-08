package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.List;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import guiBasedController.GameController;
import model.HumanPlayer;
import model.Item;
import model.Pet;
import model.Player;
import model.Room;
import model.TargetCharacter;
import model.WorldInterface;

public class WorldPanel extends JPanel {

  private static final long serialVersionUID = 1L;
  private BufferedImage worldImage;
  private int mouseX;
  private int mouseY;
  private WorldInterface world;
  private Graphics2D g2d;

  public WorldPanel() {
    // ... existing initialization code ...

    // Add mouse listener to handle clicks
    addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();

        // Repaint the panel to update the display
        repaint();

        // Check for player click in your drawPlayers method
        drawPlayers(world, (Graphics2D) getGraphics());
      }
    });
  }

  public void initializeWorld(int numRows, int numCols, WorldInterface world) {
    int width = numCols * 30;
    int height = numRows * 30;
    worldImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    g2d = worldImage.createGraphics();
    g2d.setColor(Color.WHITE);
    g2d.fillRect(0, 0, width, height);
    this.world = world;
    drawRooms(world, g2d);
    drawTargetCharacter(world, g2d);
    drawPlayers(world, g2d);
  }
  
  public Graphics2D getGraphics() {
    
    return g2d;
  }

  private void drawRooms(WorldInterface world, Graphics2D g2d) {

    for (Room room : world.getRooms()) {
      int upperLeftRow = room.getUpperLeftRow();
      int upperLeftColumn = room.getUpperLeftColumn();
      int lowerRightRow = room.getLowerRightRow();
      int lowerRightColumn = room.getLowerRightColumn();

      int roomWidth = (lowerRightColumn - upperLeftColumn + 1) * 30;
      int roomHeight = (lowerRightRow - upperLeftRow + 1) * 30;

      g2d.setColor(Color.LIGHT_GRAY);
      g2d.fillRect(upperLeftColumn * 30, upperLeftRow * 30, roomWidth, roomHeight);

      g2d.setColor(Color.BLACK);
      g2d.drawRect(upperLeftColumn * 30, upperLeftRow * 30, roomWidth, roomHeight);

      g2d.setColor(Color.BLACK);
      g2d.drawString(room.getName(), (upperLeftColumn * 30) + 5, (upperLeftRow * 30) + 15);
    }
  }

  private void drawTargetCharacter(WorldInterface world, Graphics2D g2d) {
    TargetCharacter targetCharacter = world.getTargetCharacter();

    if (targetCharacter != null) {
      int targetCharacterPosition = targetCharacter.getCharacterPositionIndex();

      // Find the room associated with the target character position
      Room targetRoom = world.getRoomByIndex(targetCharacterPosition);

      if (targetRoom != null) {
        int upperLeftRow = targetRoom.getUpperLeftRow();
        int upperLeftColumn = targetRoom.getUpperLeftColumn();
        int lowerRightRow = targetRoom.getLowerRightRow();
        int lowerRightColumn = targetRoom.getLowerRightColumn();

        int roomWidth = (lowerRightColumn - upperLeftColumn + 1) * 30;
        int roomHeight = (lowerRightRow - upperLeftRow + 1) * 30;
        int targetX = (targetRoom.getUpperLeftColumn() * 30) + (roomWidth / 2) - 15; 
        int targetY = (targetRoom.getUpperLeftRow() * 30) + (roomHeight / 2) - 15; 
        int targetWidth = 30; 
        int targetHeight = 30;

        g2d.setColor(Color.BLUE); 
        g2d.fillRect(targetX, targetY, targetWidth, targetHeight);

        g2d.setColor(Color.WHITE);
        g2d.drawRect(targetX, targetY, targetWidth, targetHeight);
        g2d.drawString(targetCharacter.getName(), targetX + 5, targetY + 15);

      }
    }
  }

  private void drawPlayers(WorldInterface world, Graphics2D g2d) {
    List<Player> players = world.getPlayers();
    int count = 0;

    for (Player player : players) {
        int playerPosition = player.getCurrentRoomIndex();

        // Find the room associated with the player's position
        Room playerRoom = world.getRoomByIndex(playerPosition);

        if (playerRoom != null) {
            int upperLeftRow = playerRoom.getUpperLeftRow();
            int upperLeftColumn = playerRoom.getUpperLeftColumn();
            int lowerRightRow = playerRoom.getLowerRightRow();
            int lowerRightColumn = playerRoom.getLowerRightColumn();

            int Width = (lowerRightColumn - upperLeftColumn + 1) * 30;
            int Height = (lowerRightRow - upperLeftRow + 1) * 30;
            int playerXOffset = count * 5;
            int playerX = (playerRoom.getUpperLeftColumn() * 30) + (Width / 2) - playerXOffset; 
            int playerYOffset = count * 5; 
            int playerY = (playerRoom.getUpperLeftRow() * 30) + (Height / 2) - 15 + playerYOffset;

            int playerWidth = 30; 
            int playerHeight = 30; 

            // Set the color based on player type
            if (player instanceof HumanPlayer) {
                g2d.setColor(Color.GREEN);
            } else {
                g2d.setColor(Color.RED);
            }

            // Draw the player rectangle
            g2d.fillRect(playerX, playerY, playerWidth, playerHeight);

            g2d.setColor(Color.WHITE);
            g2d.drawRect(playerX, playerY, playerWidth, playerHeight);

            // Draw player name
            g2d.drawString(player.getName(), playerX + 5 * count, playerY + 15 * count);

            // You can customize the drawing of the player based on your requirements

            // Check if the player is clicked
            if (isPlayerClicked(playerX, playerY, playerWidth, playerHeight)) {
                // Handle the click event, e.g., show player information
                handlePlayerClick(player);
            }
        }

        count++;
    }
}


  private boolean isPlayerClicked(int playerX, int playerY, int playerWidth, int playerHeight) {
    // Assuming mouseX and mouseY are the coordinates of the mouse click event
    return mouseX >= playerX && mouseX <= (playerX + playerWidth) && mouseY >= playerY
        && mouseY <= (playerY + playerHeight);
  }

  private void handlePlayerClick(Player player) {
    // Get player information
    String playerName = player.getName();
    List<Item> inventory = player.getInventory(); // Replace with actual method
    int carryCapacity = player.getMaxCarryCapacity(); // Replace with actual method

    // Create a message to display player information including the inventory
    StringBuilder playerInfoMessage = new StringBuilder(
            "Player Information:\n\n" +
                    "Name: " + playerName + "\n" +
                    "Carry Capacity: " + carryCapacity + "\n\n" +
                    "Inventory:\n");

    for (Item item : inventory) {
        playerInfoMessage.append("- ").append(item.getName()).append("\n");
    }

    // Show the message using a dialog
    JOptionPane.showMessageDialog(this, playerInfoMessage.toString(), "Player Information", JOptionPane.INFORMATION_MESSAGE);
}
  
  private void drawPet(WorldInterface world, Graphics2D g2d) {
    
    Pet pet = world.getPet();

    if (pet != null) {
      int petPosition = pet.getPetPosition();

      // Find the room associated with the target character position
      Room petRoom = world.getRoomByIndex(petPosition);

      if (petRoom != null) {
        int upperLeftRow = petRoom.getUpperLeftRow();
        int upperLeftColumn = petRoom.getUpperLeftColumn();
        int lowerRightRow = petRoom.getLowerRightRow();
        int lowerRightColumn = petRoom.getLowerRightColumn();

        int roomWidth = (lowerRightColumn - upperLeftColumn + 1) * 30;
        int roomHeight = (lowerRightRow - upperLeftRow + 1) * 30;
        int targetX = (petRoom.getUpperLeftColumn() * 30) + (roomWidth / 2) ; 
        int targetY = (petRoom.getUpperLeftRow() * 30) + (roomHeight / 2) ; 
        int targetWidth = 30; 
        int targetHeight = 30;

        g2d.setColor(Color.ORANGE); 
        g2d.fillRect(targetX, targetY, targetWidth, targetHeight);

        g2d.setColor(Color.WHITE);
        g2d.drawRect(targetX, targetY, targetWidth, targetHeight);
        g2d.drawString(pet.getName(), targetX + 5, targetY + 15);

      }
    }
    
  }


  public void drawWorld() {
    repaint(); // Trigger a repaint to draw the world
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g;
    g2d.drawImage(worldImage, 0, 0, this);
  }
}

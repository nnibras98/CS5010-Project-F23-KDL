package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import model.HumanPlayer;
import model.Item;
import model.Pet;
import model.Player;
import model.Room;
import model.TargetCharacter;
import model.WorldInterface;

/**
 * The WorldPanel class represents a Swing panel for displaying
 *  the game world and handling user input through key events.
 */
public class WorldPanel extends JPanel implements KeyListener {

  private static final long serialVersionUID = 1L;
  private BufferedImage worldImage;
  private int mouseX;
  private int mouseY;
  private WorldInterface world;
  private Graphics2D g2d;
  private JButton backButton;
  private JButton exitButton;
  private PlayerInfoPanel playerInfoPanel;
  private int choice;
  private boolean isClicked;

  /**
   * The WorldPanel class represents a Swing panel for displaying the game world
   * and handling user input.
   */
  public WorldPanel() {

    isClicked = false;
    playerInfoPanel = new PlayerInfoPanel();
    // Add mouse listener to handle clicks
    addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();

        // Repaint the panel to update the display
        repaint();

        // Check for player click in drawPlayers method
        drawPlayers(world, (Graphics2D) getGraphics());

      }
    });

    // Add mouse listener to handle clicks
    addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();

        int clickedRoomIndex = getClickedRoomIndex(mouseX, mouseY);

        if (clickedRoomIndex != -1) {
          Room clickedRoom = world.getRoomByIndex(clickedRoomIndex);
          Room currentPlayerRoom = world
              .getRoomByIndex(world.getPlayers().get(0).getCurrentRoomIndex());

        }

        // Repaint the panel to update the display
        repaint();
      }
    });

    // Add the KeyListener to handle keyboard input
    addKeyListener(this);
    setFocusable(true);
    requestFocus();

    JScrollPane scrollPane = new JScrollPane(this);

    // Create and initialize buttons
    backButton = new JButton("Back");
    exitButton = new JButton("Exit");

    // Add action listeners to buttons
    backButton.addActionListener(e -> handleBackButton());
    exitButton.addActionListener(e -> handleExitButton());

    // Create a panel for holding the buttons
    JPanel buttonPanel = new JPanel();
    buttonPanel.add(backButton);
    buttonPanel.add(exitButton);

    // Set the layout of this panel to BorderLayout
    setLayout(new BorderLayout());

    // Add the button panel to the SOUTH region of the BorderLayout
    add(buttonPanel, BorderLayout.SOUTH);
  }

  /**
   * Method to update player information in the side panel.
   * @param player current.
   */
  public void updatePlayerInfo(Player player) {
    playerInfoPanel.updatePlayerInfo(player);
  }

  private void handleBackButton() {
    // Implement logic to go back to the main game screen
    // For example, call a method on your GameController or switch to a different
    // panel
  }

  private void handleExitButton() {
    // Implement logic to exit the game
    System.exit(0);
  }

  /**
   * Initializes the game world on the WorldPanel with the specified dimensions and initial state.
   * This method is responsible for setting up the initial graphical
   *  representation of the game world,
   * including rooms, the target character, and players.
   *
   * @param numRowsIn The number of rows in the game world.
   * @param numColsIn The number of columns in the game world.
   * @param worldIn   The WorldInterface representing the game world.
   */
  public void initializeWorld(int numRowsIn, int numColsIn, WorldInterface worldIn) {
    int width = numColsIn * 30;
    int height = numRowsIn * 30;
    worldImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    g2d = worldImage.createGraphics();
    g2d.setColor(Color.WHITE);
    g2d.fillRect(0, 0, width, height);
    this.world = worldIn;
    drawRooms(worldIn, g2d);
    drawTargetCharacter(worldIn, g2d);
    drawPlayers(worldIn, g2d);

    // Set the preferred size for the WorldPanel
    setPreferredSize(new Dimension(width, height));
  }
  
  /**
   * method to get graphic data.
   * @return graphics.
   */
  public Graphics2D getGraphics() {
    return g2d;
  }

  private void drawRooms(WorldInterface worldIn, Graphics2D g2dIn) {
    for (Room room : worldIn.getRooms()) {
      int upperLeftRow = room.getUpperLeftRow();
      int upperLeftColumn = room.getUpperLeftColumn();
      int lowerRightRow = room.getLowerRightRow();
      int lowerRightColumn = room.getLowerRightColumn();

      int roomWidth = (lowerRightColumn - upperLeftColumn + 1) * 30;
      int roomHeight = (lowerRightRow - upperLeftRow + 1) * 30;

      g2dIn.setColor(Color.LIGHT_GRAY);
      g2dIn.fillRect(upperLeftColumn * 30, upperLeftRow * 30, roomWidth, roomHeight);

      g2dIn.setColor(Color.BLACK);
      g2dIn.drawRect(upperLeftColumn * 30, upperLeftRow * 30, roomWidth, roomHeight);

      g2dIn.setColor(Color.BLACK);
      g2dIn.drawString(room.getName(), (upperLeftColumn * 30) + 5, (upperLeftRow * 30) + 15);
    }
  }

  private void drawTargetCharacter(WorldInterface worldIn, Graphics2D g2dIn) {
    TargetCharacter targetCharacter = worldIn.getTargetCharacter();

    if (targetCharacter != null) {
      int targetCharacterPosition = targetCharacter.getCharacterPositionIndex();

      Room targetRoom = worldIn.getRoomByIndex(targetCharacterPosition);

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

        g2dIn.setColor(Color.BLUE);
        g2dIn.fillRect(targetX, targetY, targetWidth, targetHeight);

        g2dIn.setColor(Color.BLACK);
        g2dIn.drawRect(targetX, targetY, targetWidth, targetHeight);
        g2dIn.drawString(targetCharacter.getName(), targetX + 5, targetY + 15);
      }
    }
  }

  private void drawPlayers(WorldInterface worldIn, Graphics2D g2dIn) {
    List<Player> players = worldIn.getPlayers();
    int count = 0;

    for (Player player : players) {
      int playerPosition = player.getCurrentRoomIndex();

      Room playerRoom = worldIn.getRoomByIndex(playerPosition);

      if (playerRoom != null) {
        int upperLeftRow = playerRoom.getUpperLeftRow();
        int upperLeftColumn = playerRoom.getUpperLeftColumn();
        int lowerRightRow = playerRoom.getLowerRightRow();
        int lowerRightColumn = playerRoom.getLowerRightColumn();

        int width = (lowerRightColumn - upperLeftColumn + 1) * 30;
        int height = (lowerRightRow - upperLeftRow + 1) * 30;
        int playerXoffset = count * 5;
        int playerX = (playerRoom.getUpperLeftColumn() * 30) + (width / 2) - playerXoffset;
        int playerYoffset = count * 5;
        int playerY = (playerRoom.getUpperLeftRow() * 30) + (height / 2) - 15 + playerYoffset;

        int playerWidth = 30;
        int playerHeight = 30;

        if (player instanceof HumanPlayer) {
          g2dIn.setColor(Color.GREEN);
        } else {
          g2dIn.setColor(Color.RED);
        }

        g2dIn.fillRect(playerX, playerY, playerWidth, playerHeight);

        g2dIn.setColor(Color.BLACK);
        g2dIn.drawRect(playerX, playerY, playerWidth, playerHeight);

        if (player instanceof HumanPlayer) {
          g2dIn.drawString(player.getName(), playerX + 5 * count, playerY + 15 * count);
        }

        if (isPlayerClicked(playerX, playerY, playerWidth, playerHeight)) {
          handlePlayerClick(player);
        }
      }

      count++;
    }
  }

  private boolean isPlayerClicked(int playerX, int playerY, int playerWidth, int playerHeight) {
    return mouseX >= playerX && mouseX <= (playerX + playerWidth) && mouseY >= playerY
        && mouseY <= (playerY + playerHeight);
  }

  private void handlePlayerClick(Player player) {
    String playerName = player.getName();
    List<Item> inventory = player.getInventory();
    int carryCapacity = player.getMaxCarryCapacity();

    StringBuilder playerInfoMessage = new StringBuilder("Player Information:\n\n" + "Name: "
        + playerName + "\n" + "Carry Capacity: " + carryCapacity + "\n\n" + "Inventory:\n");

    for (Item item : inventory) {
      playerInfoMessage.append("- ").append(item.getName()).append("\n");
    }

    JOptionPane.showMessageDialog(this, playerInfoMessage.toString(), "Player Information",
        JOptionPane.INFORMATION_MESSAGE);

    updatePlayerInfo(player);
  }

  private void drawPet(WorldInterface worldIn, Graphics2D g2dIn) {
    Pet pet = worldIn.getPet();

    if (pet != null) {
      int petPosition = pet.getPetPosition();

      Room petRoom = world.getRoomByIndex(petPosition);

      if (petRoom != null) {
        int upperLeftRow = petRoom.getUpperLeftRow();
        int upperLeftColumn = petRoom.getUpperLeftColumn();
        int lowerRightRow = petRoom.getLowerRightRow();
        int lowerRightColumn = petRoom.getLowerRightColumn();

        int roomWidth = (lowerRightColumn - upperLeftColumn + 1) * 30;
        int roomHeight = (lowerRightRow - upperLeftRow + 1) * 30;
        int targetX = (petRoom.getUpperLeftColumn() * 30) + (roomWidth / 2);
        int targetY = (petRoom.getUpperLeftRow() * 30) + (roomHeight / 2);
        int targetWidth = 30;
        int targetHeight = 30;

        g2dIn.setColor(Color.ORANGE);
        g2dIn.fillRect(targetX, targetY, targetWidth, targetHeight);

        g2dIn.setColor(Color.WHITE);
        g2dIn.drawRect(targetX, targetY, targetWidth, targetHeight);
        g2dIn.drawString(pet.getName(), targetX + 5, targetY + 15);
      }
    }
  }

  /**
   * refresh screen.
   */
  public void drawWorld() {
    repaint();
  }

  @Override
  protected void paintComponent(Graphics graphicsIn) {
    super.paintComponent(graphicsIn);
    Graphics2D g2dIn = (Graphics2D) graphicsIn;
    g2dIn.drawImage(worldImage, 0, 0, this);
  }

  @Override
  public void keyTyped(KeyEvent e) {
    // TODO Auto-generated method stub
  }

  @Override
  public void keyPressed(KeyEvent e) {
    char keyPressed = e.getKeyChar();

    if (Character.toLowerCase(keyPressed) == 'm') {
      isClicked = true;
      choice = 3;
    }

    if (Character.toLowerCase(keyPressed) == 'l') {
      choice = 1;
    }

    if (Character.toLowerCase(keyPressed) == 'a') {
      choice = 4;
    }

    if (Character.toLowerCase(keyPressed) == 'p') {
      choice = 2;
    }
  }

  @Override
  public void keyReleased(KeyEvent e) {
    // TODO Auto-generated method stub
  }

  private int getClickedRoomIndex(int mouseInx, int mouseIny) {
    for (Room room : world.getRooms()) {
      int upperLeftX = room.getUpperLeftColumn() * 30; // Assuming 30 is the width of each cell
      int upperLeftY = room.getUpperLeftRow() * 30; // Assuming 30 is the height of each cell
      int lowerRightX = (room.getLowerRightColumn() + 1) * 30;
      int lowerRightY = (room.getLowerRightRow() + 1) * 30;

      if (mouseInx >= upperLeftX && mouseIny <= lowerRightX && mouseIny >= upperLeftY
          && mouseIny <= lowerRightY) {
        return room.getIndex();
      }
    }

    // If no room is found, return -1 or handle accordingly
    return -1;
  }

  /**
   * Sets the game world for the WorldPanel.
   *
   * @param worldIn The WorldInterface representing the game world.
   */
  public void setWorld(WorldInterface worldIn) {
    this.world = worldIn;
  }

  /**
   * Retrieves the user's choice, typically obtained from user input.
   *
   * @return The user's choice.
   */
  public int getChoice() {
    return choice;
  }

}

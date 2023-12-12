package view;

import graphiccontroller.GameController;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileNameExtensionFilter;
import model.Player;

/**
 * The GameGUI class represents the graphical user interface for the game. It
 * extends JFrame and manages different screens for starting, rules, and the
 * game itself.
 */
public class GameGui extends JFrame {

  private static final long serialVersionUID = 1L;
  private JPanel startScreen;
  private JPanel gameScreen;
  private boolean filePathSet;
  private String filePath;
  private int numTurns;
  private JPanel rulesScreen;
  private GameController gameController;
  private List<Player> players;
  private WorldPanel worldPanel;

  /**
   * Constructs a GameGUI instance, initializing the start and rules screens.
   */
  public GameGui() {
    initializeStartScreen();
    initializeRulesScreen();
    createMenuBar();
    filePathSet = false;
    setLayout(new CardLayout());
    add(startScreen, "start");
    add(rulesScreen, "rules");
    worldPanel = new WorldPanel(); // Added this line
    add(worldPanel, "world"); // Added this line
  }

  /**
   * gets max turn.
   * 
   * @return number of turns.
   */
  public int getMaxTurns() {

    return numTurns;
  }

  private void createMenuBar() {
    JMenuBar menuBar = new JMenuBar();
    JMenu viewMenu = new JMenu("Change Mode");

    JMenuItem switchToText = new JMenuItem("Switch to Text Based Version");

    switchToText.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        switchToText();
      }
    });

    viewMenu.add(switchToText);

    menuBar.add(viewMenu);

    setJMenuBar(menuBar);
  }

  private void switchToText() {
    try {
      // Specify the path to your batch file
      String batchFilePath = "C:\\CS5010 WorkSpace\\Game-Development-CS5010-Project"
          + "\\res\\setup.bat";

      // Create a File object for the batch file
      File batchFile = new File(batchFilePath);

      // Check if the file exists
      if (batchFile.exists()) {
        // Use the Desktop class to open the file
        Desktop.getDesktop().open(batchFile);
      } else {
        JOptionPane.showMessageDialog(this, "Error: Batch file not found.");
      }
    } catch (IOException e) {
      e.printStackTrace();
      JOptionPane.showMessageDialog(this, "Error opening batch file: " + e.getMessage());
    }
  }

  private void centerFrameOnScreen() {
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    int x = (screenSize.width - getWidth()) / 2;
    int y = (screenSize.height - getHeight()) / 2;
    setLocation(x, y);
  }

  private void initializeStartScreen() {
    setTitle("");
    setSize(1200, 800);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLayout(new CardLayout());
    centerFrameOnScreen();

    startScreen = new JPanel(new BorderLayout());

    JPanel centerPanel = new JPanel();
    centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));

    JLabel welcomeLabel1 = new JLabel(
        "<html><div style='text-align: center; font-size: 20pt; margin:-20px 0 0 350px;'>"
            + "Welcome to Dr Lucky's Mansion</div></html>");
    JLabel weirdLogoLabel = new JLabel("<html><pre style='font-size: 15pt; margin:0 0 0 410px;'>"
        + "         \n" + "    _/\\_\n" + "   ( o.o )\n" + "    \\_^_/\n" + "   /     \\\n"
        + "  |  _ _  |\n" + "  | | | | |\n" + "  | | | | |\n" + "  |_|_|_|_|\n" + "</pre></html>");

    JLabel welcomeLabel2 = new JLabel(
        "<html><div style='text-align: center; font-size: 10pt; margin:40px 0 0 380px;'>"
            + "© Crafted at Northeastern University by Nafi Nibras</div></html>");

    welcomeLabel1.setAlignmentX(Component.CENTER_ALIGNMENT);
    weirdLogoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
    welcomeLabel2.setAlignmentX(Component.CENTER_ALIGNMENT);

    centerPanel.add(Box.createVerticalGlue()); // Add space at the top
    centerPanel.add(welcomeLabel1);
    centerPanel.add(Box.createVerticalStrut(20)); // Add space between labels
    centerPanel.add(weirdLogoLabel);
    centerPanel.add(Box.createVerticalStrut(20)); // Add space between labels
    centerPanel.add(welcomeLabel2);
    centerPanel.add(Box.createVerticalGlue()); // Add space at the bottom

    startScreen.add(centerPanel, BorderLayout.CENTER);

    JButton newGameButton = new JButton("New Game");
    JButton loadGameButton = new JButton("Load Game");
    JButton quitButton = new JButton("Quit");

    newGameButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        showGameSetup();
      }
    });

    loadGameButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        loadGame();
      }
    });

    quitButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        quitGame();
      }
    });

    JPanel buttonPanel = new JPanel(new GridLayout(1, 3, 10, 10));
    buttonPanel.add(newGameButton);
    buttonPanel.add(loadGameButton);
    buttonPanel.add(quitButton);

    startScreen.add(buttonPanel, BorderLayout.SOUTH);
    add(startScreen);

    setVisible(true);
  }

  private void showStartScreen() {
    CardLayout cardLayout = (CardLayout) getContentPane().getLayout();
    cardLayout.show(getContentPane(), "start");
  }

  private void initializeRulesScreen() {
    rulesScreen = new JPanel(new BorderLayout());

    JTextArea rulesTextArea = new JTextArea();
    rulesTextArea.setEditable(false);
    rulesTextArea.setLineWrap(true);
    rulesTextArea.setWrapStyleWord(true);
    rulesTextArea.setFont(new Font("Arial", Font.PLAIN, 16));

    // Append all the game rules to the JTextArea
    rulesTextArea.append("Game Rules:\n");
    rulesTextArea
        .append("1. A Human-controlled player can move from the space they are currently in "
            + "to a neighboring space. This represents a turn.\n");
    rulesTextArea.append(
        "2. A Human-controlled player can pick up an item in the world found in the same space, "
            + "removing it from the world. Limited by carry capacity. This represents a turn.\n");
    rulesTextArea.append(
        "3. A Human-controlled player can look around, to see information about visible spaces. "
            + "This represents a turn.\n");
    rulesTextArea
        .append("4. A Human-controlled player can make an attempt to kill the target character. "
            + "This represents a turn.\n");
    rulesTextArea.append(
        "5. A Computer-controlled player randomly chooses an action but always chooses to make an "
            + "attempt to kill (if possible as per the game rule mentioned in point 8)."
            + " This represents a turn.\n");
    rulesTextArea.append("6. Computer-controlled players attempt using the item in "
        + "their inventory that does the most " + "damage if unseen.\n");
    rulesTextArea.append(
        "7. A player with no items can poke the target character's eye for 1 point of damage.\n");
    rulesTextArea.append("8. A player can only make an attempt on the target "
        + "character if they are in the same room, "
        + "the pet is not present in that room, and if the last turn is not a look around.\n");
    rulesTextArea.append("9. The target character moves around the world during"
        + " every turn of the game and so does the pet.\n");
    rulesTextArea.append(
        "10. If an attack is seen by another player, it is stopped, and no damage is done.\n");
    rulesTextArea.append(
        "11. Unseen attacks are always successful, reducing health points of the target character "
            + "based on the item used and removing that item from the world.\n");
    rulesTextArea.append("12. The game ends when:\n");
    rulesTextArea
        .append("    a. A player successfully kills the target character, winning the game.\n");
    rulesTextArea.append("    b. The maximum number of turns is reached, "
        + "and the target character escapes, hence nobody wins.\n");

    JScrollPane scrollPane = new JScrollPane(rulesTextArea);
    rulesScreen.add(scrollPane, BorderLayout.CENTER);

    JButton startGameButton = new JButton("Start Game");
    startGameButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        showGameScreen();
      }
    });

    JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 10, 10));
    buttonPanel.add(startGameButton);

    rulesScreen.add(buttonPanel, BorderLayout.SOUTH);
    add(rulesScreen);

  }

  private void showRulesScreen() {

    CardLayout cardLayout = (CardLayout) getContentPane().getLayout();
    cardLayout.show(getContentPane(), "rules");

  }

  private void showGameSetup() {
    JFileChooser fileChooser = new JFileChooser();
    fileChooser.setDialogTitle("Select World Specification File");
    fileChooser.setFileFilter(new FileNameExtensionFilter("Text Files", "txt"));

    // Show the file chooser dialog
    int result = fileChooser.showOpenDialog(this);

    if (result == JFileChooser.APPROVE_OPTION) {
      // User selected a file
      filePath = fileChooser.getSelectedFile().getPath();
      filePathSet = true;

      String turnsInput = JOptionPane.showInputDialog(this, "Enter the number of turns:");

      if (turnsInput == null || turnsInput.trim().isEmpty()) {
        JOptionPane.showMessageDialog(this,
            "Error: Number of turns cannot be empty. Please try again.", "Error",
            JOptionPane.ERROR_MESSAGE);
      } else {
        try {
          numTurns = Integer.parseInt(turnsInput);
          JOptionPane.showMessageDialog(this,
              "Setting up a New Game!\nFile Path: " + filePath + "\nNumber of Turns: " + numTurns);
          gameController = new GameController(filePath, this);
          showPlayerCreationScreen();
        } catch (NumberFormatException e) {
          JOptionPane.showMessageDialog(this, "Error: Please enter a valid number for turns.",
              "Error", JOptionPane.ERROR_MESSAGE);
        }
      }
    } else {
      JOptionPane.showMessageDialog(this, "File selection canceled. Please try again.", "Error",
          JOptionPane.ERROR_MESSAGE);
    }
  }

  private void loadGame() {
    if (!filePathSet) {
      JOptionPane.showMessageDialog(this,
          "Error: No initial file path set. Please start a new game first.", "Error",
          JOptionPane.ERROR_MESSAGE);
      return;
    }

    JOptionPane.showMessageDialog(this, "Loading Game...");
    showGameScreen();
  }

  private void quitGame() {
    int option = JOptionPane.showConfirmDialog(this, "Are you sure you want to quit?");
    if (option == JOptionPane.YES_OPTION) {
      System.exit(0);
    }
  }

  private void showPlayerCreationScreen() {

    // Prompt the user for the number of human and computer players
    int numHumanPlayers = 0;
    int numComputerPlayers = 0;

    while (true) {
      try {
        numHumanPlayers = Integer
            .parseInt(JOptionPane.showInputDialog(this, "Enter the number of human players:"));
        numComputerPlayers = Integer
            .parseInt(JOptionPane.showInputDialog(this, "Enter the number of computer players:"));

        // Validate the input
        if (numHumanPlayers + numComputerPlayers <= 10 && numHumanPlayers >= 0
            && numComputerPlayers >= 0) {
          break;
        } else {
          JOptionPane.showMessageDialog(this,
              "Invalid input. Total players must be between 1 and 10, and the number "
                  + "of human and computer players must be non-negative.");
        }
      } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "Invalid input. Please enter a valid number.");
      }
    }

    // Now, run a loop to take player information input
    for (int i = 1; i <= numHumanPlayers; i++) {
      // Prompt the user for human player information (name, maxCarryCapacity,
      // currentRoomIndex)
      String playerName = JOptionPane.showInputDialog(this,
          "Enter the name for Human Player #" + i + ":");
      int maxCarryCapacity = Integer.parseInt(JOptionPane.showInputDialog(this,
          "Enter the max carry capacity for Human Player #" + i + ":"));
      int currentRoomIndex = Integer.parseInt(JOptionPane.showInputDialog(this,
          "Enter the initial room index for Human Player #" + i + ":"));

      // Add the human player to the game (you need to implement this method in your
      // World class)
      gameController.addHumanPlayer(playerName, maxCarryCapacity, currentRoomIndex);
    }

    // Create players based on user input
    for (int i = 0; i < numComputerPlayers; i++) {
      // Create computer player and add to the world
      int randomEntryRoomIndex = (int) (Math.random() * gameController.getModel().getNumRooms());
      int randomCarryingCapacity = (int) (Math.random() * 10); // Random carrying capacity

      gameController.addComputerPlayer("Computer Player " + (i + 1), randomCarryingCapacity,
          randomEntryRoomIndex);
    }

    showRulesScreen();

  }

  private void initializeGameScreen() {

    gameScreen = new JPanel(new BorderLayout());

    worldPanel.initializeWorld(gameController.getModel().getNumRows(),
        gameController.getModel().getNumCols(), gameController.getModel());
    worldPanel.drawWorld();
    worldPanel.setWorld(gameController.getModel());

  }

  private void showGameScreen() {
    // Initialize the game screen
    initializeGameScreen();

    // Add any other components or functionality specific to the game screen

    // Switch to the game screen
    CardLayout cardLayout = (CardLayout) getContentPane().getLayout();
    cardLayout.show(getContentPane(), "world");
  }

  /**
   * gives the panel.
   * 
   * @return the panel.
   */
  public WorldPanel getWorldPanel() {

    return worldPanel;
  }

  /**
   * gives the controller.
   * 
   * @return the controller.
   */
  public GameController getController() {

    return gameController;
  }

}

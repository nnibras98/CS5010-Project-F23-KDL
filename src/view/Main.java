package view;

import javax.swing.SwingUtilities;

/**
 * The Main class serves as the entry point for the game application.
 */
public class Main {

  /**
   * The main method, starting the game by invoking the GameGUI in the Event Dispatch Thread.
   *
   * @param args Command-line arguments (not used in this application).
   */
  public static void main(String[] args) {
    SwingUtilities.invokeLater(new Runnable() {
      /**
       * Runs the game by creating an instance of GameGUI in the Event Dispatch Thread.
       */
      public void run() {
        new GameGui();
      }
    });
  }

}


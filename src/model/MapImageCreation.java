package model;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import javax.imageio.ImageIO;

/**
 * Image Creation Class.
 */
public class MapImageCreation {

  /**
   * Takes in the world and forms the image.
   *
   * @param world World instance containing information about rooms, players, items, etc.
   * @param filename Output filename for the generated image.
   */
  public MapImageCreation(WorldImpl world) {
    try {
      // Create a BufferedImage for the world with white background
      int width = world.getNumCols() * 30;
      int height = world.getNumRows() * 30;
      BufferedImage worldImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
      Graphics2D g2d = worldImage.createGraphics();
      g2d.setColor(Color.WHITE);
      g2d.fillRect(0, 0, width, height);

      // Draw rooms
      drawRooms(world, g2d);

      // Save the world image
      ImageIO.write(worldImage, "png", new File("res/mansion.png"));
      openImageInPhotos("res/mansion.png");
    } catch (IOException e) {
      System.err.println("An error occurred while saving the image.");
      e.printStackTrace();
    }
  }

  /**
   * Draws rooms on the image.
   *
   * @param world World instance containing information about rooms.
   * @param g2d   Graphics2D object for drawing.
   */
  private void drawRooms(WorldImpl world, Graphics2D g2d) {
    for (Room room : world.getRooms()) {
      int upperLeftRow = room.getUpperLeftRow();
      int upperLeftColumn = room.getUpperLeftColumn();
      int lowerRightRow = room.getLowerRightRow();
      int lowerRightColumn = room.getLowerRightColumn();

      int roomWidth = (lowerRightColumn - upperLeftColumn + 1) * 30;
      int roomHeight = (lowerRightRow - upperLeftRow + 1) * 30;

      g2d.setColor(Color.GRAY);
      g2d.fillRect(upperLeftColumn * 30, upperLeftRow * 30, roomWidth, roomHeight);

      g2d.setColor(Color.BLACK);
      g2d.drawRect(upperLeftColumn * 30, upperLeftRow * 30, roomWidth, roomHeight);

      g2d.setColor(Color.BLACK);
      g2d.drawString(room.getName(), (upperLeftColumn * 30) + 5, (upperLeftRow * 30) + 15);
    }
  }


  /**
   * Opens the image in the default image viewer.
   *
   * @param filename Filename of the image to be opened.
   */
  public void openImageInPhotos(String filename) {
    // Open the image using the default associated application
    try {
      String[] commands = { "cmd.exe", "/c", "start", "\"DummyTitle\"", "\"" + filename + "\"" };
      Process p = Runtime.getRuntime().exec(commands);
      System.out.println("Map opened in default photo viewer");
    } catch (IOException e) {
      System.err.println("An error occurred while opening the image in Photos.");
      e.printStackTrace();
    }
  }
}

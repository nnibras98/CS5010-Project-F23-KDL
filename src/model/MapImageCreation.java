package model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * Image Creation Class.
 */

public class MapImageCreation {
  
  /**
   * takes in the file and forms the image.
   * @param inputString file
   */
  public MapImageCreation(String inputString) {

    try {

      // Read the text file with room coordinates and dimensions
      BufferedReader reader = new BufferedReader(new FileReader(inputString));

      // Parse the world description
      String worldDescription = reader.readLine();
      String[] worldDescriptionParts = worldDescription.split(" ");
      int numRows = Integer.parseInt(worldDescriptionParts[0]);
      int numColumns = Integer.parseInt(worldDescriptionParts[1]);

      // Create a BufferedImage for the mansion with white background
      int width = numColumns * 30;
      int height = numRows * 30;
      BufferedImage mansionImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
      Graphics2D g2d = mansionImage.createGraphics();
      g2d.setColor(Color.WHITE);
      g2d.fillRect(0, 0, width, height);

      // Parse the rooms
      for (int i = 0; i <= 22; i++) {
        String roomInfo = reader.readLine();
        String[] roomInfoParts = roomInfo.split(" ");
        if (roomInfoParts.length >= 5) {
          int upperLeftRow = Integer.parseInt(roomInfoParts[0]);
          int upperLeftColumn = Integer.parseInt(roomInfoParts[1]);
          int lowerRightRow = Integer.parseInt(roomInfoParts[2]);
          int lowerRightColumn = Integer.parseInt(roomInfoParts[3]);

          // Combine the remaining parts to form the room name
          StringBuilder roomNameBuilder = new StringBuilder();
          for (int j = 4; j < roomInfoParts.length; j++) {
            roomNameBuilder.append(roomInfoParts[j]);
            if (j < roomInfoParts.length - 1) {
              roomNameBuilder.append(" ");
            }
          }
          String roomName = roomNameBuilder.toString();

          int roomWidth = (lowerRightColumn - upperLeftColumn + 1) * 30;
          int roomHeight = (lowerRightRow - upperLeftRow + 1) * 30;

          // Set the fill color (
          g2d.setColor(Color.GRAY);
          g2d.fillRect(upperLeftColumn * 30, upperLeftRow * 30, roomWidth, roomHeight);

          // Set the border color
          g2d.setColor(Color.BLACK);

          // Draw the filled rectangle border
          g2d.drawRect(upperLeftColumn * 30, upperLeftRow * 30, roomWidth, roomHeight);

          // Room Name
          g2d.setColor(Color.BLACK); // Adjust the color as needed
          g2d.drawString(roomName, (upperLeftColumn * 30) + 5, (upperLeftRow * 30) + 15);
        }
      }

      // Save the mansion image
      ImageIO.write(mansionImage, "png", new File("res/mansion.png"));

      reader.close();

      System.out.println("Mansion image saved successfully.");
    } catch (IOException e) {
      System.err.println("An error occurred while saving the image.");
    }

  }
}

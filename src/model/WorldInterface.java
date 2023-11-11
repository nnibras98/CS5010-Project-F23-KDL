package model;

import java.io.File;

/**
 * Interface for creating and reading a World from a text file.
 */
public interface WorldInterface {

  /**
     * Parses the world specification from a text file.
     *
     * @param file The File containing the world specification.
     */
  void parseWorldFromFile(File file);

}
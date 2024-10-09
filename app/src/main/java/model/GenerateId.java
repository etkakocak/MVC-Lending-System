package model;

import java.util.Random;

/**
 * Generates random ID.
 */
public class GenerateId {
  private Random random = new Random();

  /**
   * Generates a new 6-character alphanumeric ID.
   */
  public String generateNewId() {
    String alphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    StringBuilder sb = new StringBuilder(6);

    for (int i = 0; i < 6; i++) {
      int index = random.nextInt(alphaNumericString.length());
      sb.append(alphaNumericString.charAt(index));
    }

    return sb.toString();
  }
}
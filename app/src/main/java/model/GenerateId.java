package model;

import java.util.Random;

/**
 * Generates random id.
 */
public class GenerateId {
  private Random random = new Random(); 

  /**
   * Generate a new id.
   */
  public String generateNewId() {
    String alphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    StringBuilder sb;
    String generatedId;

    sb = new StringBuilder(6);
    for (int i = 0; i < 6; i++) {
      int index = random.nextInt(alphaNumericString.length());
      sb.append(alphaNumericString.charAt(index));
    }
    generatedId = sb.toString();

    return generatedId;
  }
}

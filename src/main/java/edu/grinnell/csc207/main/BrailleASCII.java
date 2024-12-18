package edu.grinnell.csc207.main;

import java.io.PrintWriter;

import edu.grinnell.csc207.util.BrailleAsciiTables;

/**
 * Main class for making conversions between ascii, braille, and unicode.
 *
 * @author Alex Cyphers
 */
public class BrailleASCII {
  // +------+--------------------------------------------------------
  // | Main |
  // +------+

  /**
   * Main method to take command line arguments to convert between ascii, braille,
   * and unicode.
   *
   * @param args used for reading inputs.
   * @throws IllegalArgumentException if the input is invalid.
   */
  public static void main(String[] args) {
    PrintWriter pen = new PrintWriter(System.out, true);
    String target = "";

    if (args.length > 2) {
      throw new IllegalArgumentException("There should only be two command line parameters");
    } // if

    try {
      for (int i = 0; i < args.length; i++) {
        if (i == 0) {
          target = args[i];
        } else {
          if (target.equals("braille")) {
            char[] letters = args[i].toCharArray();
            for (int j = 0; j < letters.length; j++) {
              pen.print(BrailleAsciiTables.toBraille(letters[j]));
            } // for-loop
          } else if (target.equals("ascii")) {
            if (args[i].length() % 6 != 0) {
              throw new IllegalArgumentException("Bit string length must be a multiple of 6");
            } // if

            for (int j = 0; j < args[i].length(); j += 6) {
              String bits = args[i].substring(j, j + 6);
              pen.print(BrailleAsciiTables.toAscii(bits));
            } // for-loop
          } else if (target.equals("unicode")) {
            char[] letters = args[i].toCharArray();
            for (int j = 0; j < letters.length; j++) {
              String braille = BrailleAsciiTables.toBraille(letters[j]);
              pen.print(BrailleAsciiTables.toUnicode(braille));
            } // for-loop
          } else {
            throw new IllegalArgumentException("Invalid target: " + args[i]);
          } // if/else
        } // if/else
      } // for-loop
      pen.println();
    } catch (IllegalArgumentException e) {
      pen.println("Error: " + e.getMessage());
    } catch (IndexOutOfBoundsException e) {
      pen.println("\nError: " + e.getMessage());
    } catch (Exception e) {
      pen.println("Error: " + e.getMessage());
    } // try/catch
    pen.close();
  } // main(String[]
} // class BrailleASCII

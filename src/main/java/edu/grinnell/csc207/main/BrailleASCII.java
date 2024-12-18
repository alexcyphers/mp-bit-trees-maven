package edu.grinnell.csc207.main;

import java.io.PrintWriter;

import edu.grinnell.csc207.util.BrailleAsciiTables;

/**
 *
 */
public class BrailleASCII {
  // +------+--------------------------------------------------------
  // | Main |
  // +------+

  /**
   *
   */
  public static void main(String[] args) {
    PrintWriter pen = new PrintWriter(System.out, true);
    String target = "";

    try {
      for (int i = 0; i < args.length; i++) {
        String line = args[i];
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
              throw new Exception("Invalid Length");
            } // if

            for (int j = 0; j < args[i].length(); j+= 6) {
              String bits = args[i].substring(j, j + 6);
              pen.print(BrailleAsciiTables.toAscii(bits));
            } // for-loop
          } else if (target.equals("unicode")) {
            char[] letters = args[i].toCharArray();
            for (int j = 0; j < letters.length; j++) {
              String braille = BrailleAsciiTables.toBraille(letters[j]);
              String unicode = BrailleAsciiTables.toUnicode(braille);
              char h2u = (char) Integer.parseInt(unicode, 16);
              pen.print(h2u);
            } // for-loop
          }
        }
      }
    } catch (Exception e) {
      pen.print("Runtime Exception" + e.getMessage());
    } // try/catch
    pen.println();
    pen.close();
  } // main(String[]

} // class BrailleASCII

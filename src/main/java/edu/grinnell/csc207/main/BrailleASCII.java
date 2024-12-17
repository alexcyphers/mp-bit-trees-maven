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
    
    for (int i = 0; i < args.length; i++) {
      if (i == 0) {
        target = args[i];
      } else {
        char[] letters = args[i].toCharArray();
        if (target.equals("braille")) {
          for (int j = 0; j < letters.length; j++) {
            pen.print(BrailleAsciiTables.toBraille(letters[j]));
          }
        } else if (target.equals("ascii")) {
          for (int j = 0; j < letters.length; j++) {
            pen.print(BrailleAsciiTables.toBraille(letters[j]));
          }
        }
      }
    }
    pen.close();
  } // main(String[]

} // class BrailleASCII

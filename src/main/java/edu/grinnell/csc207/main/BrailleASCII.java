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
    if (args[0] == "braille") {
      char[] brailleArray = args[1].toCharArray();
      for (int i = 0; i < brailleArray.length; i++) {
        pen.print(BrailleAsciiTables.toBraille(brailleArray[i]));
      }
    } else if (args[0] == "unicode") {

    } else if (args[0] == "ascii") {

    } else {
      pen.println("Invalid translation tool");
    }
    pen.close();
  } // main(String[]

} // class BrailleASCII

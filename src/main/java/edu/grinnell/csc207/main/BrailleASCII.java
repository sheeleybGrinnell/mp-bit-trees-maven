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
      char[] unicodeArray = args[1].toCharArray();
      String[] brailleArray = new String[unicodeArray.length];
      for (int i = 0; i < unicodeArray.length; i++) {
        brailleArray[i] = BrailleAsciiTables.toBraille(unicodeArray[i]);
        pen.print(BrailleAsciiTables.toUnicode(brailleArray[i]));
      }
    } else if (args[0] == "ascii") {
      char[] asciiArray = args[1].toCharArray();
      String buffer = "";
      for (int i = 0; i < asciiArray.length; i++) {
        if (!((i % 6) == 0)) {
          buffer += asciiArray[i];
        } else {
          BrailleAsciiTables.toAscii(buffer);
          buffer = "";
          buffer += asciiArray[i];
        } //if/else
      } //for
    } else {
      pen.println("Invalid translation tool");
    } //if/else
    pen.close();
  } // main(String[]

} // class BrailleASCII

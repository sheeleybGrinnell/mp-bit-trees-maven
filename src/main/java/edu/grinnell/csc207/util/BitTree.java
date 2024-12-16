package edu.grinnell.csc207.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

/**
 * Trees intended to be used in storing mappings between fixed-length 
 * sequences of bits and corresponding values.
 *
 * @author Benjamin Sheeley
 */
public class BitTree {
  // +--------+------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * Where our bittree begins.
   */
  BitTreeInteriorNode root;

  /**
   * Size of our bittree.
   */
  int size;

  int bitSize;
  // +--------------+------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Sets our bitTree up, setting our root to null, and initializing our size.
   */
  public BitTree(int n) {
    this.size = 0;
    this.bitSize = n;
    this.root = new BitTreeInteriorNode();
  } // BitTree(int)

  // +---------------+-----------------------------------------------
  // | Local helpers |
  // +---------------+

  void set(String bits, String value, BitTreeNode node) {
    BitTreeInteriorNode newNode = (BitTreeInteriorNode) node;
    if (bits.length() < 2) {
      if (leftOrRight(bits)) {
        if (isNull(newNode.leftChild)) {
          newNode.leftChild = new BitTreeLeaf(value);
          size++;
        }
        newNode.leftChild.setCharacter(value);
      } else {
        if (isNull(newNode.rightChild)) {
          newNode.rightChild = new BitTreeLeaf(value);
          size++;
        }
        newNode.rightChild.setCharacter(value);
      } //if/else
    } else {
      if (leftOrRight(bits)) {
        if (isNull(newNode.leftChild)) {
          newNode.leftChild = new BitTreeInteriorNode();
        } //if
        set(bits.substring(1), value, newNode.leftChild);
      } else {
        if (isNull(newNode.rightChild)) {
          newNode.rightChild = new BitTreeInteriorNode();
        }
        set(bits.substring(1), value, newNode.rightChild);
      } //if/else
    } //if/else
    return;
  } //set(String, String, BitTreeNode)

  /**
   * Boolean that lets us see if we should go to the left or right node.
   * @param bits
   *  The string we are taking from.
   * @return
   *  True for left node, false for right.
   */
  boolean leftOrRight(String bits) {
    if (bits.startsWith("0")) {
      return true;
    } else if (bits.startsWith("1")){
      return false;
    } else {
      throw new IllegalArgumentException();
    }//if/else
  } //leftOrRight(String)

  boolean isNull(BitTreeNode node) {
    if (node == null) {
      return true;
    } else {
      return false;
    }
  }

  /**
   * Recursively gets the character from input bits.
   * @param bits
   *  The string of bits we use to traverse our bittree.
   * @param node
   *  The current node we find ourself traversing.
   * @return
   *  The character that we arrive at once we find ourself at a leaf.
   */
  String get(String bits, BitTreeNode node) {
    if (isNull(node)) {
      throw new NullPointerException();
    }
    if (node instanceof BitTreeLeaf) {
      return node.getCharacter();
    } else if (leftOrRight(bits)) {
      BitTreeInteriorNode newNode = (BitTreeInteriorNode) node;
      return get(bits.substring(1), newNode.leftChild);
    } else {
      BitTreeInteriorNode newNode = (BitTreeInteriorNode) node;
      return get(bits.substring(1), newNode.rightChild);
    } // if/else
  } // get(K, BSTNode<K, V>)

  /**
   * 
   * @param pen
   * @param node
   */
  void dump(PrintWriter pen, BitTreeNode node, String currentBuffer) {
    BitTreeInteriorNode newNode = (BitTreeInteriorNode) node;
    if (isNull(newNode)) {
      return;
    } else {
      if (newNode.leftChild instanceof BitTreeLeaf) {
        pen.println(currentBuffer + "," + newNode.leftChild.getCharacter());
        return;
      }
      if (newNode.rightChild instanceof BitTreeLeaf) {
        pen.println(currentBuffer + "," + newNode.rightChild.getCharacter());
        return;
      }
      currentBuffer += "0";
      dump(pen, newNode.leftChild, currentBuffer);
      currentBuffer = currentBuffer.substring(0, (currentBuffer.length() - 1));
      currentBuffer += "1";
      dump(pen, newNode.rightChild, currentBuffer);
      currentBuffer = currentBuffer.substring(0, (currentBuffer.length() - 1));
  } //if/else
  } //dump(PrintWriter, BitTreeNode, String, int)


  // +---------+-----------------------------------------------------
  // | Methods |
  // +---------+

  /**
   *
   */
  public void set(String bits, String value) {
    if (bits.length() != bitSize) {
      throw new IndexOutOfBoundsException();
    } //if
    set(bits, value, root);
  } // set(String, String)

  /**
   *
   */
  public String get(String bits) {
    if (bits == null) {
      throw new NullPointerException("null bits");
    } // if
    return get(bits, root);
  } // get(String, String)

  /**
   *
   */
  public void dump(PrintWriter pen) {
    dump(pen, root, "");
  } // dump(PrintWriter)

  /**
   *
   */
  public void load(InputStream source) {
    try {
      String buffer;
      String[] bufferStorage;
      BufferedReader reader = new BufferedReader(new InputStreamReader(source));
      while(reader.ready()) {
        buffer = reader.readLine();
        bufferStorage = buffer.split(",");
        set(bufferStorage[0], bufferStorage[1]);
      }
    } catch (IOException e) {
      System.err.println("IOException, please review file format");
    }
  } // load(InputStream)

} // class BitTree

package edu.grinnell.csc207.util;

public class BitTreeLeaf implements BitTreeNode {
  private String character;

  public BitTreeNode leftChild;

  public BitTreeNode rightChild;

  public BitTreeLeaf(String value) {
    character = value;
  }

  public String getCharacter() {
    return character;
  }

  public BitTreeInteriorNode getLeftChild() {
    return null;
  }

  public BitTreeInteriorNode getRightChild() {
    return null;
  }

  public void setLeftChild(BitTreeNode node) {
    return;
  }

  public void setRightChild(BitTreeNode node) {
    return;
  }

  public void setCharacter(String value) {
    this.character = value; 
  }
}

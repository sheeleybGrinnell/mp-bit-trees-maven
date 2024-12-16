package edu.grinnell.csc207.util;

public class BitTreeInteriorNode implements BitTreeNode {
  public BitTreeNode leftChild;

  public BitTreeNode rightChild;

  public BitTreeNode getLeftChild() {
    return leftChild;
  }

  public BitTreeNode getRightChild() {
    return rightChild;
  }

  public void setLeftChild(BitTreeNode node) {
    leftChild = node;
  }

  public void setRightChild(BitTreeNode node) {
    rightChild = node;
  }

  public String getCharacter() {
    return "you have called getcharacter on interior node";
  }

  public void setCharacter(String value) {
    return;
  }
}

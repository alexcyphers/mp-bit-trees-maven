package edu.grinnell.csc207.util;

/**
 * Class for a leaf node in the bit tree. They
 * store a value associated with a bit string.
 *
 * @author Alex Cyphers
 */
public class BitTreeLeaf implements BitTreeNode {

  /**
   * The value stored in the leaf node.
   */
  String value;

  /**
   * Constructor for a leaf node which
   * has a value.
   *
   * @param value the value stored in the leaf node.
   */
  public BitTreeLeaf(String value) {
    this.value = value;
  } // BitTreeLeaf(String)
} // class BitTreeLeaf


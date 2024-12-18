package edu.grinnell.csc207.util;


/**
 * Class for an interior node in a bit tree. It
 * contain references to the left and right child
 * nodes.
 *
 * @author Alex Cyphers
 */
public class BitTreeInteriorNode implements BitTreeNode {

  /**
   * Node of the left child.
   */
  BitTreeNode left;

  /**
   * Node of the right child.
   */
  BitTreeNode right;

  /**
   * Constructor for an interior node. It
   * is initialized empty and without children.
   */
  public BitTreeInteriorNode() {
    this.left = null;
    this.right = null;
  } // BitTreeInteriorNode()
} // class BitTreeInteriorNode

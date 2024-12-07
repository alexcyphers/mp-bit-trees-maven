package edu.grinnell.csc207.util;

import java.io.PrintWriter;

public class BitTreeInteriorNode implements BitTreeNode {

  BitTreeNode left;
  BitTreeNode right;

  public BitTreeInteriorNode() {
    this.left = null;
    this.right = null;
  }
}

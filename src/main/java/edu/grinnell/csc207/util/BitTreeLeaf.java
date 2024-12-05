package edu.grinnell.csc207.util;

import java.io.PrintWriter;

public class BitTreeLeaf implements BitTreeNode {


  private String value;

  public BitTreeLeaf(String value) {
    this.value = value;
  }
}

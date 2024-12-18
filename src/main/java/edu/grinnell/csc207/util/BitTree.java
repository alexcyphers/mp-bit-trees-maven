package edu.grinnell.csc207.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * Trees intended to be used in storing mappings between fixed-length
 * sequences of bits and corresponding values.
 *
 * @author Your Name Here
 */
public class BitTree {
  // +--------+------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * 
   */
  private int n;

  /**
   * 
   */
  private BitTreeNode root;
  // +--------------+------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   *
   */
  public BitTree(int n) {
    this.n = n;
    this.root = new BitTreeInteriorNode();
  } // BitTree(int)

  // +---------------+-----------------------------------------------
  // | Local helpers |
  // +---------------+

  /**
   *
   * @param bits
   */
  public void validate(String bits) {
    if (bits.length() < n) {
      throw new IndexOutOfBoundsException("Bits length is too short: " + bits);
    } else if (bits.length() > n) {
      throw new IndexOutOfBoundsException("Bits length is too long: " + bits);
    } // if/else-if

    for (int i = 0; i < bits.length(); i++) {
      char c = bits.charAt(i);
      if (c != '0' && c != '1') {
        throw new IllegalArgumentException("Invalid character: \"" + c + "\"");
      } // if
    } // for-loop
  } // validate(String)


  // +---------+-----------------------------------------------------
  // | Methods |
  // +---------+

  /**
   *
   * @param bits
   * @param value
   */
  public void set(String bits, String value) {
    validate(bits);
    BitTreeNode curr = root;

    for (int i = 0; i < bits.length(); i++) {
      BitTreeInteriorNode interiorNode = (BitTreeInteriorNode) curr;

      if (bits.charAt(i) == '0') {
        if (interiorNode.left == null) {
          if (i == (bits.length() - 1)) {
            interiorNode.left = new BitTreeLeaf(value);
          } else {
            interiorNode.left = new BitTreeInteriorNode();
          } // if/else
        } // if
        curr = interiorNode.left;
      } else {
        if (interiorNode.right == null) {
          if (i == (bits.length() - 1)) {
            interiorNode.right = new BitTreeLeaf(value);
          } else {
            interiorNode.right = new BitTreeInteriorNode();
          } // if/else
        } // if
        curr = interiorNode.right;
      } // if/else
    } // for-loop
    if (curr instanceof BitTreeLeaf) {
      ((BitTreeLeaf) curr).value = value;
    } // if
  } // set(String, String)



  /**
   *
   * @param bits
   * @return
   */
  public String get(String bits) {
    validate(bits);

    BitTreeNode curr = root;

    for (int i = 0; i < bits.length(); i++) {
      BitTreeInteriorNode interiorNode = (BitTreeInteriorNode) curr;

      if (bits.charAt(i) == '0') {
        curr = interiorNode.left;
      } else {
        curr = interiorNode.right;
      } // if/else

      if (curr == null) {
        throw new IndexOutOfBoundsException("Invalid Key: " + bits);
      } // if
    } // for-loop

    if (curr instanceof BitTreeLeaf) {
      return ((BitTreeLeaf) curr).value;
    } else {
      throw new IndexOutOfBoundsException("curr not a leaf");
    } // if/else
  } // get(String, String)


  /**
   *
   * @param pen
   */
  public void dump(PrintWriter pen) {
    dumpHelper((BitTreeInteriorNode) root, "", pen);
  } // dump(PrintWriter)

  public void dumpHelper(BitTreeNode value, String parent, PrintWriter pen) {
    if (value instanceof BitTreeLeaf) {
      pen.println(parent + "," + ((BitTreeLeaf) value).value);
    } else if (value instanceof BitTreeInteriorNode) {
      BitTreeInteriorNode node = (BitTreeInteriorNode) value;
      if (node.left != null) {
        dumpHelper(node.left, parent + "0", pen);
      } // if
      if (node.right != null) {
        dumpHelper(node.right, parent + "1", pen);
      } // if
    } // if/else
  } // dumpHelper(BitTreeNode, String, PrintWriter)


  /**
   *
   * @param source
   */
  public void load(InputStream source) {
    BufferedReader reader = new BufferedReader(new InputStreamReader(source));
    String line;
    try {
      while ((line = reader.readLine()) != null) {
        String[] parts = line.split(",");
        if (parts.length == 2) {
          set(parts[0], parts[1]);
        } // if
      } // while
    } catch (IOException e) {
      // We don't care if we can't close the stream.
    } // try/catch
  } // load(InputStream)
} // class BitTree

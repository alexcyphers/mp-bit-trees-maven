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
 * @author Alex Cyphers
 */
public class BitTree {
  // +--------+------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * The number of bits used for the bit tree.
   */
  private int numBits;

  /**
   * The root of the bit tree.
   */
  private BitTreeNode root;

  // +--------------+------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Constructor for a tree which stores mappings from strings of bits to strings.
   *
   * @param n number of bits stored in the tree.
   */
  public BitTree(int n) {
    this.numBits = n;
    this.root = new BitTreeInteriorNode();
  } // BitTree(int)

  // +---------------+-----------------------------------------------
  // | Local helpers |
  // +---------------+

  /**
   * Validates that the string of bits is the right size and
   * contains only 1s and 0s.
   *
   * @param bits the bits in the string.
   */
  public void validate(String bits) {
    if (bits == null) {
      throw new IllegalArgumentException("Bit string can't be null");
    } // if
    if (bits.length() != numBits) {
      throw new IndexOutOfBoundsException("Bits length must be: " + numBits);
    } // if
    for (int i = 0; i < bits.length(); i++) {
      char c = bits.charAt(i);
      if (c != '0' && c != '1') {
        throw new IllegalArgumentException("Invalid character in bit string: " + c);
      } // if
    } // for-loop
  } // validate(String)


  // +---------+-----------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * Follows the path through tree from the string of bits, adding
   * nodes when appropriate, and adds or replaces the value at the
   * end with the given value.
   *
   * @param bits the bit string to map through.
   * @param value the value associated with the string of bits.
   *
   * @throws IndexOutOfBoundsException if the string of bits is too short or long.
   */
  public void set(String bits, String value) {
    // Check if string of bits is valid.
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
   * Follows a path through the tree from the string of
   * bits to return the value associated with the bit
   * string.
   *
   * @param bits the string of bits to search.
   *
   * @return the value associated with the string of bits
   *
   * @throws IndexOutOfBoundsException
   *    if the bit string is invalid or no value in the tree is associated with the bits.
   */
  public String get(String bits) {
    // Check if string of bits is valid.
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
        throw new IndexOutOfBoundsException("No translation found for the bit string: " + bits);
      } // if
    } // for-loop

    if (curr instanceof BitTreeLeaf) {
      return ((BitTreeLeaf) curr).value;
    } else {
      throw new IndexOutOfBoundsException("No translation found for the bit string: " + bits);
    } // if/else
  } // get(String, String)


  /**
   * Prints out the contents of the tree in CSV format.
   *
   * @param pen used to print the contents of the tree.
   */
  public void dump(PrintWriter pen) {
    dumpHelper((BitTreeInteriorNode) root, "", pen);
  } // dump(PrintWriter)


  /**
   * Recursively traverses through the tree to print its contents
   * in CSV format.
   *
   * @param node current node being checked.
   * @param bits the bit string leading to the node.
   * @param pen used to print the contents of the tree.
   */
  public void dumpHelper(BitTreeNode node, String bits, PrintWriter pen) {
    if (node instanceof BitTreeLeaf) {
      pen.println(bits + "," + ((BitTreeLeaf) node).value);
    } else if (node instanceof BitTreeInteriorNode) {
      BitTreeInteriorNode interiorNode = (BitTreeInteriorNode) node;
      if (interiorNode.left != null) {
        dumpHelper(interiorNode.left, bits + "0", pen);
      } // if
      if (interiorNode.right != null) {
        dumpHelper(interiorNode.right, bits + "1", pen);
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

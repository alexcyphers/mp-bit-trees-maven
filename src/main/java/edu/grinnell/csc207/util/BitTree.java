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

  private int n;
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

  // +---------+-----------------------------------------------------
  // | Methods |
  // +---------+

  /**
   *
   */
  public void set(String bits, String value) {
    BitTreeNode curr = root;

    for (int i = 0; i < bits.length(); i++) {
      if(!(curr instanceof BitTreeInteriorNode)) {
        throw new IllegalStateException("Illegal state");
      }
      BitTreeInteriorNode interiorNode = (BitTreeInteriorNode) curr;

      if (bits.charAt(i) == '0') {
        if (interiorNode.left == null) {
          interiorNode.left = new BitTreeInteriorNode();
        } // if
        curr = interiorNode.left;
      } else {
        if (interiorNode.right == null) {
          interiorNode.right = new BitTreeInteriorNode();
        } // if
        curr = interiorNode.right;
      } // if/else


      
      //BitTreeInteriorNode node = (BitTreeInteriorNode) curr;

      // if (bits.substring(bits.length() - 1, bits.length()).equals("0")) {
      //   node.left = new BitTreeLeaf(value);
      // } else {
      //   node.right = new BitTreeLeaf(value);
      // } // if/else
    } // for-loop

    if (curr instanceof BitTreeLeaf) {
      ((BitTreeLeaf) curr).value = value;
    } else {
      BitTreeInteriorNode node = (BitTreeInteriorNode) curr;
      if (bits.substring(bits.length() - 1, bits.length()).equals("0")) {
        node.left = new BitTreeLeaf(value);
      } else {
        node.right = new BitTreeLeaf(value);
      } // if/else
    }
  } // set(String, String)



  /**
   *
   */
  public String get(String bits) {
    BitTreeNode curr = root;

    for (int i = 0; i < bits.length(); i++) {
      BitTreeInteriorNode interiorNode = (BitTreeInteriorNode) curr;

      if (bits.charAt(i) == '0') {
        curr = interiorNode.left;
      } else {
        curr = interiorNode.right;
      } // if/else
    } // for-loop
    return ((BitTreeLeaf) curr).value;
  } // get(String, String)

  /**
   *
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
      }
      if (node.right != null) {
        dumpHelper(node.right, parent + "1", pen);
      }
    }
  }


  /**
   * @throws IOException 
   *
   */
  public void load(InputStream source) throws IOException {
    BufferedReader reader = new BufferedReader(new InputStreamReader(source));
    String line;
    while ((line = reader.readLine()) != null) {
      String[] parts = line.split(",");
      if (parts.length == 2) {
        set(parts[0], parts[1]);
      }
    }
  } // load(InputStream)

} // class BitTree

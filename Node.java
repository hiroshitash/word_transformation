/*
 * Node.java
 *
 * Created on October 18, 2007, 2:42 AM
 *
 */

package word_transformation;

import java.util.Vector;

/**
 * Node encapsulates State information and supports tree operation.
 *
 * @author tashiro
 */
public class Node implements Comparable {
  
  protected final Node parent_node;
  protected Vector<Node> successors_nodes = null;
  
  protected final State state;
  protected final int g_n;
  protected final Heuristic heuristic;
  
  /** Creates a new instance of Root Node */
  public Node(State st, int lev, Heuristic h) {
    parent_node = null;
    state = st;
    g_n = lev;
    heuristic = h;
  }
  
  /** Creates a new instance of Child Nodes */
  public Node(Node p_node, State st, int lev, Heuristic h) {
    parent_node = p_node;
    state = st;
    g_n = lev;
    heuristic = h;
  }
  
  public boolean is_root_node() {
    return (parent_node == null);  // Only root node doesn't have parent node.
  }
  
  public void expand(Vector<String>[] dict) {
    successors_nodes = new Vector<Node>();
    Vector<State> suc_states = state.getSuccessors(dict);
    
    // Remove all the element in path from this node to root
    Node tmp_node = parent_node;
    while (tmp_node != null) {
      boolean flag_el_exist = true;
      while (flag_el_exist)  // remove all the duplicates
        flag_el_exist = suc_states.removeElement(tmp_node.state);
      tmp_node = tmp_node.parent_node;
    }

    /* change this part if different operators have different weights. */
    // Add the rest of elements as nodes.
    for (State one_suc_state: suc_states) {
      this.successors_nodes.add( new Node(this, one_suc_state, g_n + 1, heuristic) );
    }
    
  }
  
  public void shrink() {
    this.successors_nodes.clear();
    this.successors_nodes = null;
  }
  
  public boolean is_expanded() {
    return (successors_nodes != null);
  }
  
  public Node get_parent() {
    return parent_node;
  }
  
  public Vector<Node> get_succerssor() {
    return successors_nodes;
  }
  
  public State get_state() {
    return state;
  }
  
  public int get_fn() {
    return get_gn() + get_hn();
  }
  
  public int get_gn() {
    return g_n;
  }
  
  public int get_hn() {
    return heuristic.get_heuristic_estimate(this.state);
  }
  
  public boolean equals(Object o) {
    Node target = (Node) o;
    return ( this.state.equals(target.state) );
  }
  
  public int compareTo(Object o) {
    Node target = (Node) o;
    return this.get_fn() - target.get_fn();
  }
  
  public String toString() {
    String str = new String( (parent_node == null)?"Root Node: ": "Child Node: " );
    str += new String("f_n: " + get_fn() + "(g: " + get_gn() + " h: " +
          get_hn() + ")\tState: " + state + "\t");
    return str;
  }
}

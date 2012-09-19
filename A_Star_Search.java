/*
 * A_Star_Search.java
 *
 * Created on October 18, 2007, 9:24 PM
 *
 */

package word_transformation;

import java.util.Vector;
import java.util.PriorityQueue;

/**
 * A* Algorithm:
 *  1. Create a priority queue of search nodes (initially the
 *      start state). Priority is determined by the function f )
 *  2. While queue not empty and goal not found:
 *      get best state x from the queue.
 *      If x is not goal state:
 *        generate all possible children of x (and save path information with each node).
 *        Apply f to each new node and add to queue.
 *        Remove duplicates from queue (using f to pick the best).
 *
 *  @author tashiro
 */
public class A_Star_Search {
  
  private PriorityQueue<Node> queue;
  private static boolean VERBOSE = false;
  
  /** Creates a new instance of A_Star_Search */
  public A_Star_Search() {
    queue = new PriorityQueue<Node>();
  }
  
  public Vector<State> search_path(State initial_state, State goal_state, Vector<String>[] dict, Heuristic h) {
    
    if (VERBOSE)
      System.out.println("start A* Search.");
    
    // 1. Create a priority queue of search nodes (initially the
    //    start state). Priority is determined by the function f )
    queue.clear();
    Node root_node = new Node(initial_state, 0, h);
    queue.add(root_node);
    
    Node tmp_node = null;
    boolean found = false;
    
    
    // 2. While queue not empty and goal not found:
    while (queue.size() != 0 && found == false) {
      // get best state x from the queue.
      tmp_node = queue.remove();
      
      if ( tmp_node.get_state().equals(goal_state) ) {
        found = true;
        if (VERBOSE)
          System.out.println("goal reached: " + goal_state);
      } else {
        
        //    If x is not goal state:
        //      generate all possible children of x (and save path information with each node).
        //      Apply f to each new node and add to queue.
        //      Remove duplicates from queue (using f to pick the best).
        if (!tmp_node.is_expanded()) {
          tmp_node.expand(dict);
        }
        queue.addAll( tmp_node.get_succerssor() );
      }
    }
    
    if (VERBOSE)
      System.out.println("end A* search.");
    
    // Return the result as vector.
    Vector<State> result = new Vector<State>();
    Vector<State> result_reverse = new Vector<State>();
    
    while ( !tmp_node.is_root_node() ) {
      result.add( tmp_node.get_state() );
      tmp_node = tmp_node.get_parent();
    }
    result.add( tmp_node.get_state() );
    
    // reverse the result.
    for (int i = (result.size() - 1); i >=0; i--)
      result_reverse.add(result.get(i));
    
    return result_reverse;
  }
}

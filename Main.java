/*
 * Main.java
 *
 * Created on April 21, 2008, 1:59 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package word_transformation;

import java.util.Vector;

/**
 *
 * @author tashiro
 */
public class Main {
  
  public static final int NUM_INFINITY = Integer.MAX_VALUE;
  
  public static void main (String[] args) {

    Input_Handler in_handler = new Input_Handler(args);
    A_Star_Search alg = new A_Star_Search();
    Heuristic h = new Adm_Word_Trans_Heuristic(in_handler.get_goal_state());

    Vector<String>[] dict = in_handler.handle_file_input();
    
    State start_state = in_handler.get_start_state();
    State goal_state = in_handler.get_goal_state();
    
    String start_word = start_state.get_word();
    String goal_word = goal_state.get_word();
    
    if ( in_handler.check_input_error() ) 
      return;

    /* algorithm execution */
    Vector<State> result_path =
          alg.search_path(start_state, goal_state, dict, h);
    
    if ( result_path.lastElement().equals( in_handler.get_goal_state() ) ) {
      for (State st: result_path)
        System.out.println(st);
    } else
      System.out.println("No transformation found.");
    
    System.out.println();
  }
  
}

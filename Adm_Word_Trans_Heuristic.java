/*
 * Adm_Word_Trans_Heuristic.java
 *
 * Created on April 21, 2008, 2:07 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package word_transformation;

/**
 *
 * @author tashiro
 */
public class Adm_Word_Trans_Heuristic implements Heuristic {
  protected State goal;
  
  /** Creates a new instance of Adm_Word_Trans_Heuristic */
  public Adm_Word_Trans_Heuristic(State st_goal) {
    goal = st_goal;
  }
  
  public int get_heuristic_estimate(State target_st) {
    String target_word = target_st.get_word();
    String goal_word   = goal.get_word();
    int num_diff = 0;
    
    int len_goal_word = goal_word.length();
    if ( target_st.get_word().length() != len_goal_word)
      return Main.NUM_INFINITY;

    for (int i = 0; i < len_goal_word; i++)
      if ( target_word.charAt(i) != goal_word.charAt(i) )
        num_diff++;
    
    return num_diff;
  }
  
  /* add heuristic here for insertion and deletion if necessary */
}

/*
 * State.java
 *
 * Created on April 21, 2008, 1:56 PM
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
public class State {
  private final String word;  // State is immutable object.
  
  /** Creates a new instance of State */
  public State(String arg) {
    word = arg;
  }
  
  public Vector<State> getSuccessors(Vector<String>[] dict) {
    Vector<State> successors = new Vector<State>();
    
    int length = this.word.length();
    for (String target_word: dict[length]) {
      int num_diff = 0;
      for (int i = 0; i < length && num_diff <= 1; i++) {
        if ( word.charAt(i) != target_word.charAt(i) )
          num_diff++;
      }
      if (num_diff == 1)
        successors.add( new State(target_word) );
    }
    
    /* add code here for insertion and deletion if necessary */
    
    return successors;
  }
  
  public boolean equals(Object o) {
    State target = (State) o;    // Easier to catch error this way.
    
    return ( target.get_word() ).equals( this.get_word() );
  }
  
  public String get_word() {
    return word;
  }
  
  public String toString() {
    return this.get_word();
  }
}

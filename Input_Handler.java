/*
 * Input_Handler.java
 *
 * Created on April 21, 2008, 1:58 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package word_transformation;

import java.util.Vector;
import java.io.*;

/**
 *
 * @author tashiro
 */
public class Input_Handler {
  private State start, goal;
  private String filename;
  private Vector<String>[] dict;
  
  /** Creates a new instance of Input_Handler */
  public Input_Handler(String[] args) {
    filename = args[0];
    start = new State(args[1]);
    goal  = new State(args[2]);
  }
  
  public Vector<String>[] handle_file_input() {
    BufferedReader in = null;
    String line;
    Vector<String> tmp_container = new Vector<String>();
    int max_word_length = 0;
    
    try {
      in = new BufferedReader( new FileReader(filename) );
      
      int line_length;
      while ( ( line = in.readLine() ) != null ) {
        
        line = line.trim();
        // Skip empty lines
        if (line.length() == 0)
          continue;
        else {
          if ( !tmp_container.contains(line) ) // don't enter duplicate
            tmp_container.add(line);
          line_length = line.length();
          max_word_length = (max_word_length < line_length)? line_length: max_word_length;
        }
      }
    } catch (Exception ex) {
      ex.printStackTrace();
    } finally {
      try {
        if (in!= null)
          in.close();
      } catch (Exception ex) {
        ex.printStackTrace();
      }
    }
    
    dict = (Vector<String>[]) new Vector[(max_word_length + 1)];
    for (int i = 0; i <= max_word_length; i++)
      dict[i] = new Vector<String>();
    
    for (String word: tmp_container)
      dict[word.length()].add(word);
    
    return dict;
  }
  
  public State get_start_state() {
    return start;
  }
  
  public State get_goal_state() {
    return goal;
  }
  
  public boolean check_input_error() {
    String start_word = start.get_word();
    String goal_word = goal.get_word();
    
    boolean flag_error = ( !dict[start_word.length()].contains(start_word) ||
                           !dict[goal_word.length()].contains(goal_word) );
    if (flag_error) {
      System.out.println("Start or goal word not found in file.");
      System.out.println("\tstart: " + start_word + "\tgoal: " + goal_word);
    }
    
    return flag_error;
  }
  
}

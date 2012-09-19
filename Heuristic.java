/*
 * Heuristic.java
 *
 * Created on October 18, 2007, 4:10 PM
 *
 */

package word_transformation;

import java.util.Vector;

public interface Heuristic {
  public int get_heuristic_estimate(State st);
}


package gui;

import java.awt.geom.Line2D;
import java.util.List;
/**
 * Basic interface containing all methods for DigitizerDocuments.
 * This work complies with the JMU Honor Code.
 * @author Jerome Donfack
 */
public interface DigitizerDocument
{
  /**
   * Adds line to render to the screen.
   * @param start - Start point of line.
   * @param stop - End point of line.
   */
  void addLine(final double[] start, final double[] stop);
  
  /**
   * Returns the closest line in regards to a certain point.
   * @param point - The comparison point.
   * @return - The closest line to point.
   */
  Line2D.Double getClosest(final double[] point);
  
  /**
   * Get all store lines.
   * @return - List of lines.
   */
  List<Line2D.Double> getLines();
  
  /**
   * Removes line from list of lines.
   * @param line - Line to remove.
   */
  void removeLine(Line2D.Double line);
}

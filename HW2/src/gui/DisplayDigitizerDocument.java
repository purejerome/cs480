package gui;

import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.List;
import math.Vector;
/**
 * The model portion of the visual panel.
 * This work complies with the JMU Honor Code.
 * @author Jerome Donfack
 */
public class DisplayDigitizerDocument implements DigitizerDocument
{
  protected DigitizerPanel panel;
  protected List<Line2D.Double> lines = new ArrayList<>();
  
  /**
   * Initializes the DisplayDigitizerDocument.
   * @param panel - The panel this DisplayDigitizerDocument attaches to.
   */
  public DisplayDigitizerDocument (final DigitizerPanel panel) 
  {
    this.panel = panel;
  }
  
  @Override
  public void addLine(final double[] start, final double[] stop)
  {
    lines.add(new Line2D.Double(start[0], start[1], stop[0], stop[1]));
  }

  @Override
  public Line2D.Double getClosest(final double[] point)
  {
    double closestDist = Double.POSITIVE_INFINITY;
    Line2D.Double retLine = null;
    for(Line2D.Double line : lines)
    {
      double[] startCoords = {line.getX1(), line.getY1()};
      double[] endCoords = {line.getX2(), line.getY2()};
      double dist = Math.min(Vector.norm(Vector.minus(point, startCoords)), 
          Vector.norm(Vector.minus(point, endCoords)));
      if(dist < closestDist)
      {
        closestDist = dist;
        retLine = line;
      }
    }
    return retLine;
  }

  @Override
  public List<Line2D.Double> getLines()
  {
    return lines;
  }

  @Override
  public void removeLine(final Line2D.Double line)
  {
    lines.remove(line); 
  }

}

package gui;

import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.List;

import math.Vector;

public class DisplayDigitizerDocument implements DigitizerDocument
{
  protected DigitizerPanel panel;
  protected List<Line2D> lines = new ArrayList<>();
  
  public DisplayDigitizerDocument (final DigitizerPanel panel) 
  {
    this.panel = panel;
  }

  @Override
  public void addLine(double[] start, double[] stop)
  {
    lines.add(new Line2D.Double(start[0], start[1], stop[0], stop[1]));
  }

  @Override
  public Line2D getClosest(double[] point)
  {
    double closestDist = Double.POSITIVE_INFINITY;
    Line2D retLine = null;
    for(Line2D line : lines)
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
  public List<Line2D> getLines()
  {
    return lines;
  }

  @Override
  public void removeLine(Line2D line)
  {
   lines.remove(line); 
  }

}

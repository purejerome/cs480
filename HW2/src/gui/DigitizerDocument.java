package gui;

import java.awt.geom.Line2D;
import java.util.List;

public interface DigitizerDocument
{
  void addLine(final double[] start, final double[] stop);
  Line2D getClosest(final double[] point);
  List<Line2D> getLines();
  void removeLine(Line2D line);
}

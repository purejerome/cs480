package gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.util.List;

import javax.swing.JPanel;
/**
 * The view and controller portion of the visual panel.
 * This work complies with the JMU Honor Code.
 * @author Jerome Donfack
 */
public class DigitizerPanel extends JPanel implements MouseListener, MouseMotionListener
{
  public static final int ADD = 0;
  public static final int DELETE = 1;
  private static final long serialVersionUID = 1L;
  private BufferedImage ortho;
  private DigitizerDocument model;
  private int mode;
  private Line2D.Double currentLine;
  private double[] startCoords;
  private double[] endCoords;
  
  /**
   * Creates an instance of DigitizerPanel.
   * @param ortho - Image to add to the background of the panel.
   */
  public DigitizerPanel(final BufferedImage ortho)
  {
    this.ortho = ortho;
    this.createDefaultModel();
    this.addMouseListener(this);
    this.addMouseMotionListener(this);
  }
  
  /**
   * Attaches this view and controller to the model.
   * @return - The model used by this instance.
   */
  public DigitizerDocument createDefaultModel() 
  {
    model = new DisplayDigitizerDocument(this);
    return model;
  }
  
  /**
   * Get a list of lines on the screen.
   * @return - List of lines.
   */
  public List<Line2D.Double> getLines()
  {
    return model.getLines();
  }
  
  /**
   * Render the screen.
   * @param g - The graphics used to render the screen.
   */
  public void paint(final Graphics g)
  {
    Graphics2D g2= (Graphics2D)g;
    super.paint(g2);
    if(ortho != null)
    {
      g2.drawImage(ortho, null, 0, 0);
    }
    for(Line2D.Double line : this.getLines())
    {
      g2.setColor(Color.GREEN);
      g2.setStroke(new BasicStroke(2));
      g2.draw(line);
    }
    if(currentLine != null)
    {
      g2.setColor(Color.YELLOW);
      g2.setStroke(new BasicStroke(2));
      g2.draw(currentLine);
    }
  }
  
  /**
   * Sets mode to either add or delete.
   * @param mode - Mode to set to.
   */
  public void setMode(final int mode)
  {
    this.mode = mode;
  }

  @Override
  public void mouseDragged(final MouseEvent e)
  {
    endCoords = new double[]{e.getX(), e.getY()};
    if(mode == ADD)
    {
      currentLine.setLine(startCoords[0], startCoords[1], endCoords[0], endCoords[1]);
      this.repaint();
    }
  }

  @Override
  public void mouseMoved(final MouseEvent e)
  { 
  }

  @Override
  public void mouseClicked(final MouseEvent e)
  {
  }

  @Override
  public void mousePressed(final MouseEvent e)
  {
    startCoords = new double[]{e.getX(), e.getY()};
    endCoords = new double[]{e.getX(), e.getY()};
    if(mode == ADD)
    {
      currentLine = new Line2D.Double(startCoords[0], startCoords[1], endCoords[0], endCoords[1]);
      this.repaint();
    }
  }

  @Override
  public void mouseReleased(final MouseEvent e)
  {
    endCoords = new double[]{e.getX(), e.getY()};
    if(mode == ADD)
    {
      model.addLine(startCoords, endCoords);
      currentLine = null;
      this.repaint();
    }
    else
    {
      if(startCoords[0] == endCoords[0] && startCoords[1] == endCoords[1])
      {
        double x = e.getX();
        double y = e.getY();
        double[] clickPos = {x, y};
        Line2D.Double closestLine = model.getClosest(clickPos);
        model.removeLine(closestLine);
        this.repaint();
      }
    }
  }

  @Override
  public void mouseEntered(final MouseEvent e)
  {
  }

  @Override
  public void mouseExited(final MouseEvent e)
  {
  }
}

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

public class DigitizerPanel extends JPanel 
implements MouseListener, MouseMotionListener
{
  public static int ADD = 0;
  public static int DELETE = 1;
  private static final long serialVersionUID = 1L;
  private BufferedImage ortho;
  private DigitizerDocument model;
  private int mode;
  private Line2D currentLine; 
  
  public DigitizerPanel(final BufferedImage ortho)
  {
    this.ortho = ortho;
    this.createDefaultModel();
    this.addMouseListener(this);
    this.addMouseMotionListener(this);
  }
  
  public DigitizerDocument createDefaultModel() 
  {
    model = new DisplayDigitizerDocument(this);
    return model;
  }
  
  public List<Line2D> getLines()
  {
    return model.getLines();
  }
  
  public void paint(Graphics g)
  {
    Graphics2D g2= (Graphics2D)g;
    super.paint(g2);
    g2.drawImage(ortho, null, 0, 0);
    for(Line2D line : this.getLines())
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
  
  public void setMode(int mode)
  {
    this.mode = mode;
  }

  @Override
  public void mouseDragged(MouseEvent e)
  {
    if(mode == ADD)
    {
      currentLine.setLine(currentLine.getX1(), currentLine.getY1(), e.getX(), e.getY());
      this.repaint();
    }
  }

  @Override
  public void mouseMoved(MouseEvent e){ }

  @Override
  public void mouseClicked(MouseEvent e)
  {
    if(mode == DELETE)
    {
      double x = e.getX();
      double y = e.getY();
      double[] clickPos = {x, y};
      Line2D closestLine = model.getClosest(clickPos);
      model.removeLine(closestLine);
      this.repaint();
    }
  }

  @Override
  public void mousePressed(MouseEvent e)
  {
    if(mode == ADD)
    {
      currentLine = new Line2D.Double(e.getX(), e.getY(), e.getX(), e.getY());
      this.repaint();
    }
  }

  @Override
  public void mouseReleased(MouseEvent e)
  {
    if(mode == ADD)
    {
      double[] startCoords = {currentLine.getX1(), currentLine.getY1()};
      double[] endCoords = {currentLine.getX2(), currentLine.getY2()};
      currentLine = null;
      
      model.addLine(startCoords, endCoords);
      this.repaint();
    }
  }

  @Override
  public void mouseEntered(MouseEvent e){ }

  @Override
  public void mouseExited(MouseEvent e){ }
}

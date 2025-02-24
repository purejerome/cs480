package testing;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import org.junit.jupiter.api.Test;

import gui.DigitizerPanel;
import gui.DisplayDigitizerDocument;
import math.Vector;

class HW2Tests
{

  @Test
  void Vector_Dot_Test()
  {
    assertEquals(18.0, 
        Vector.dot(new double[]{3.0, 3.0}, new double[]{3.0, 3.0}));
    assertEquals(129.9, 
        Vector.dot(new double[]{20.2, 3.0}, new double[]{4.5, 13.0}), 0.1);
    assertEquals(111.0, 
        Vector.dot(new double[]{6.0, 7.0}, new double[]{8.0, 9.0}));
    assertEquals(2.0, 
        Vector.dot(new double[]{1.0, 1.0}, new double[]{1.0, 1.0}));
    assertEquals(0.0, 
        Vector.dot(new double[]{0.0, 0.0}, new double[]{0.0, 0.0}));
  }
  
  @Test
  void Vector_Minus_Test()
  {
    assertArrayEquals(new double[]{0.0, 0.0}, 
        Vector.minus(new double[]{1.0, 1.0}, new double[]{1.0, 1.0}));
    assertArrayEquals(new double[]{-2.5, -10.0}, 
        Vector.minus(new double[]{20.0, 3.0}, new double[]{22.5, 13.0}));
    assertArrayEquals(new double[]{14.0, 2.0}, 
        Vector.minus(new double[]{6.0, -7.0}, new double[]{-8.0, -9.0}));
    assertArrayEquals(new double[]{0.0, 0.0}, 
        Vector.minus(new double[]{0.0, 0.0}, new double[]{0.0, 0.0}));
    assertArrayEquals(new double[]{1101850.5, -185235.0}, 
        Vector.minus(new double[]{1123232.5, 2139219.0}, new double[]{21382.0, 2324454.0}));
  }
  
  @Test
  void Vector_Norm_Test()
  {
    assertEquals(Math.sqrt(2.0), 
        Vector.norm(new double[]{1.0, 1.0}));
    assertEquals(2.0, 
        Vector.norm(new double[]{Math.sqrt(2.0), Math.sqrt(2.0)}));
    assertEquals(3.0, 
        Vector.norm(new double[]{Math.sqrt(2.0), Math.sqrt(7.0)}), 1);
    assertEquals(0.0, 
        Vector.norm(new double[]{0.0, 0.0}));
    assertEquals(2416176.56205362, 
        Vector.norm(new double[]{1123232.5, 2139219.0}));
  }
  
  @Test
  void Vector_Normalize_Test()
  {
    assertArrayEquals(new double[]{0.0, 1.0}, 
        Vector.normalize(new double[]{0.0, 1.0}));
    assertArrayEquals(new double[]{1.0, 0.0}, 
        Vector.normalize(new double[]{1.0, 0.0}));
    assertArrayEquals(new double[]{1.0, 0.0}, 
        Vector.normalize(new double[]{14.0, 0.0}));
    assertArrayEquals(new double[]{0.0, 1.0}, 
        Vector.normalize(new double[]{0.0, 10000012932913.0}));
    assertArrayEquals(new double[]{0.368625720283123, 0.929577903322658}, 
        Vector.normalize(new double[]{11.5, 29.0}));
  }
  
  @Test
  void Vector_Perp_Test()
  {
    assertArrayEquals(new double[]{-1.0, 0.0}, 
        Vector.perp(new double[]{0.0, 1.0}));
    assertArrayEquals(new double[]{-0.0, 1.0}, 
        Vector.perp(new double[]{1.0, 0.0}));
    assertArrayEquals(new double[]{-0.0, 14.0}, 
        Vector.perp(new double[]{14.0, 0.0}));
    assertArrayEquals(new double[]{-10000012932913.0, 123430.923432}, 
        Vector.perp(new double[]{123430.923432, 10000012932913.0}));
    assertArrayEquals(new double[]{-29.0, 11.5}, 
        Vector.perp(new double[]{11.5, 29.0}));
  }
  
  @Test
  void Vector_Plus_Test()
  {
    assertArrayEquals(new double[]{2.0, 2.0}, 
        Vector.plus(new double[]{1.0, 1.0}, new double[]{1.0, 1.0}));
    assertArrayEquals(new double[]{42.5, 16.0}, 
        Vector.plus(new double[]{20.0, 3.0}, new double[]{22.5, 13.0}));
    assertArrayEquals(new double[]{-2.0, -16.0}, 
        Vector.plus(new double[]{6.0, -7.0}, new double[]{-8.0, -9.0}));
    assertArrayEquals(new double[]{0.0, 0.0}, 
        Vector.plus(new double[]{0.0, 0.0}, new double[]{0.0, 0.0}));
    assertArrayEquals(new double[]{1144614.5, 4463673.0}, 
        Vector.plus(new double[]{1123232.5, 2139219.0}, new double[]{21382.0, 2324454.0}));
  }
  
  @Test
  void Vector_Times_Test()
  {
    assertArrayEquals(new double[]{2.0, 2.0}, 
        Vector.times(2.0, new double[]{1.0, 1.0}));
    assertArrayEquals(new double[]{2.0, 2.0}, 
        Vector.times(new double[]{1.0, 1.0}, 2.0));
    
    assertArrayEquals(new double[]{157.5, 91.0}, 
        Vector.times(7.0, new double[]{22.5, 13.0}));
    assertArrayEquals(new double[]{157.5, 91.0}, 
        Vector.times(new double[]{22.5, 13.0}, 7.0));
    
    assertArrayEquals(new double[]{8.0, 9.0}, 
        Vector.times(-1.0, new double[]{-8.0, -9.0}));
    assertArrayEquals(new double[]{8.0, 9.0}, 
        Vector.times(new double[]{-8.0, -9.0}, -1.0));
   
    assertArrayEquals(new double[]{0.0, 0.0}, 
        Vector.times(0.0, new double[]{123.0, 1000.0}));
    assertArrayEquals(new double[]{0.0, 0.0}, 
        Vector.times(new double[]{123.0, 1000.0}, 0.0));
   
    assertArrayEquals(new double[]{25.0, 50.0}, 
        Vector.times(0.5, new double[]{50.0, 100.0}));
    assertArrayEquals(new double[]{25.0, 50.0}, 
        Vector.times(new double[]{50.0, 100.0}, 0.5));
  }
  
  @Test
  void Digitizer_Document_Test()
  {
    DisplayDigitizerDocument doc = new DisplayDigitizerDocument(null);
    ArrayList<double[]> rawStartPoints = new ArrayList<>();
    ArrayList<double[]> rawEndPoints = new ArrayList<>();
    
    rawStartPoints.add(new double[]{0.0, 0.0});
    rawStartPoints.add(new double[]{12.0, 6.0});
    rawStartPoints.add(new double[]{30.0, 32.5});
    
    rawEndPoints.add(new double[]{50.0, 0.0});
    rawEndPoints.add(new double[]{0.0, 6.0});
    rawEndPoints.add(new double[]{3.0, 23.5});
    
    for(int x = 0; x < 3; x ++)
    {
      doc.addLine(rawStartPoints.get(x), rawEndPoints.get(x));
    }
    
    for(int x = 0; x < 3; x ++)
    {
      assertEquals(rawStartPoints.get(x)[0],doc.getLines().get(x).getX1());
      assertEquals(rawEndPoints.get(x)[0],doc.getLines().get(x).getX2());
      assertEquals(rawStartPoints.get(x)[1],doc.getLines().get(x).getY1());
      assertEquals(rawEndPoints.get(x)[1],doc.getLines().get(x).getY2());
    }
    assertEquals(3, doc.getLines().size());
    
    doc.addLine(new double[]{18.0, 17.0}, new double[]{2.0, 40.0});
    assertEquals(18.0,doc.getLines().get(3).getX1());
    assertEquals(2.0,doc.getLines().get(3).getX2());
    assertEquals(17.0,doc.getLines().get(3).getY1());
    assertEquals(40.0,doc.getLines().get(3).getY2());
    
    assertEquals(4, doc.getLines().size());
    
    Line2D.Double removeLine = doc.getLines().get(3);
    doc.removeLine(removeLine);
    assertEquals(3, doc.getLines().size());
    
    for(int x = 0; x < 3; x ++)
    {
      assertNotEquals(18.0,doc.getLines().get(x).getX1());
      assertNotEquals(2.0,doc.getLines().get(x).getX2());
      assertNotEquals(17.0,doc.getLines().get(x).getY1());
      assertNotEquals(40.0,doc.getLines().get(x).getY2());
    }
    
    assertEquals(doc.getLines().get(0), doc.getClosest(new double[]{0.0, 0.0}));
    assertEquals(doc.getLines().get(2), doc.getClosest(new double[]{29.0, 29.0})); 
  }
  
  @Test
  void Digitizer_Panel_Test()
  {
    BufferedImage ortho;
    try
    {
      ortho = ImageIO.read(new File("orthophoto.png"));
    }
    catch (IOException io)
    {
      ortho = null;
    }
    
    DigitizerPanel panel = new DigitizerPanel(ortho);
    panel.setMode(DigitizerPanel.ADD);
    
    MouseEvent pressEvent = new MouseEvent(panel, MouseEvent.MOUSE_PRESSED, System.currentTimeMillis(), 0, 10, 10, 1, false);
    panel.mousePressed(pressEvent);
    
    MouseEvent dragEvent = new MouseEvent(panel, MouseEvent.MOUSE_DRAGGED, System.currentTimeMillis(), 0, 20, 20, 1, false);
    panel.mouseDragged(dragEvent);
    
    MouseEvent releaseEvent = new MouseEvent(panel, MouseEvent.MOUSE_RELEASED, System.currentTimeMillis(), 0, 20, 20, 1, false);
    panel.mouseReleased(releaseEvent);
    
    assertEquals(1, panel.getLines().size());
    
    panel.setMode(DigitizerPanel.DELETE);
    
    MouseEvent pressEvent2 = new MouseEvent(panel, MouseEvent.MOUSE_PRESSED, System.currentTimeMillis(), 0, 10, 10, 1, false);
    panel.mousePressed(pressEvent2);
    
    MouseEvent dragEvent2 = new MouseEvent(panel, MouseEvent.MOUSE_DRAGGED, System.currentTimeMillis(), 0, 20, 20, 1, false);
    panel.mouseDragged(dragEvent2);
    
    MouseEvent releaseEvent2 = new MouseEvent(panel, MouseEvent.MOUSE_RELEASED, System.currentTimeMillis(), 0, 20, 20, 1, false);
    panel.mouseReleased(releaseEvent2);
    
    assertEquals(1, panel.getLines().size());
    
    MouseEvent pressEvent3 = new MouseEvent(panel, MouseEvent.MOUSE_PRESSED, System.currentTimeMillis(), 0, 10, 10, 1, false);
    panel.mousePressed(pressEvent3);
    
    
    MouseEvent releaseEvent3 = new MouseEvent(panel, MouseEvent.MOUSE_RELEASED, System.currentTimeMillis(), 0, 10, 10, 1, false);
    panel.mouseReleased(releaseEvent3); 
    
    assertEquals(0, panel.getLines().size());
    
    panel.setMode(DigitizerPanel.ADD);
    
    MouseEvent pressEvent4 = new MouseEvent(panel, MouseEvent.MOUSE_PRESSED, System.currentTimeMillis(), 0, 10, 10, 1, false);
    panel.mousePressed(pressEvent4);
    
    MouseEvent dragEvent4 = new MouseEvent(panel, MouseEvent.MOUSE_DRAGGED, System.currentTimeMillis(), 0, 20, 20, 1, false);
    panel.mouseDragged(dragEvent4);
    
    MouseEvent releaseEvent4 = new MouseEvent(panel, MouseEvent.MOUSE_RELEASED, System.currentTimeMillis(), 0, 20, 20, 1, false);
    panel.mouseReleased(releaseEvent4);
    
    assertEquals(1, panel.getLines().size());
    
    panel.setMode(DigitizerPanel.DELETE);
    
    MouseEvent pressEvent5 = new MouseEvent(panel, MouseEvent.MOUSE_PRESSED, System.currentTimeMillis(), 0, 10, 10, 1, false);
    panel.mousePressed(pressEvent5);
    
    MouseEvent dragEvent5 = new MouseEvent(panel, MouseEvent.MOUSE_DRAGGED, System.currentTimeMillis(), 0, 10, 20, 1, false);
    panel.mouseDragged(dragEvent5);
    
    MouseEvent releaseEvent5 = new MouseEvent(panel, MouseEvent.MOUSE_RELEASED, System.currentTimeMillis(), 0, 10, 20, 1, false);
    panel.mouseReleased(releaseEvent5); 
    
    assertEquals(1, panel.getLines().size());
    
    MouseEvent pressEvent6 = new MouseEvent(panel, MouseEvent.MOUSE_PRESSED, System.currentTimeMillis(), 0, 10, 10, 1, false);
    panel.mousePressed(pressEvent6);
    
    MouseEvent dragEvent6 = new MouseEvent(panel, MouseEvent.MOUSE_DRAGGED, System.currentTimeMillis(), 0, 20, 10, 1, false);
    panel.mouseDragged(dragEvent6);
    
    MouseEvent releaseEvent6 = new MouseEvent(panel, MouseEvent.MOUSE_RELEASED, System.currentTimeMillis(), 0, 20, 10, 1, false);
    panel.mouseReleased(releaseEvent6); 
    
    assertEquals(1, panel.getLines().size());
    
    MouseEvent enterEvent = new MouseEvent(panel, MouseEvent.MOUSE_ENTERED, System.currentTimeMillis(), 0, 0, 0, 0, false);
    panel.mouseEntered(enterEvent); 
    
    assertEquals(1, panel.getLines().size());
    
    MouseEvent exitEvent = new MouseEvent(panel, MouseEvent.MOUSE_EXITED, System.currentTimeMillis(), 0, 0, 0, 0, false);
    panel.mouseExited(exitEvent); 
    
    assertEquals(1, panel.getLines().size());
    
    MouseEvent clickedEvent = new MouseEvent(panel, MouseEvent.MOUSE_CLICKED, System.currentTimeMillis(), 0, 0, 0, 0, false);
    panel.mouseClicked(clickedEvent); 
    
    assertEquals(1, panel.getLines().size());
    
    MouseEvent movedEvent = new MouseEvent(panel, MouseEvent.MOUSE_MOVED, System.currentTimeMillis(), 0, 0, 0, 0, false);
    panel.mouseMoved(movedEvent); 
    
    assertEquals(1, panel.getLines().size());
    
    JFrame frame = new JFrame();
    frame.add(panel);
    frame.setVisible(true);
    Graphics g2 = panel.getGraphics();
    
    assertDoesNotThrow(() ->{
      panel.paint(g2);
      frame.dispose();
    });
    
    panel.setMode(DigitizerPanel.ADD);
    MouseEvent pressEvent7 = new MouseEvent(panel, MouseEvent.MOUSE_PRESSED, System.currentTimeMillis(), 0, 10, 10, 1, false);
    panel.mousePressed(pressEvent7);
    
    MouseEvent dragEvent7 = new MouseEvent(panel, MouseEvent.MOUSE_DRAGGED, System.currentTimeMillis(), 0, 20, 10, 1, false);
    panel.mouseDragged(dragEvent7);
    
    assertDoesNotThrow(() ->{
      panel.paint(g2);
      frame.dispose();
    });
    
    DigitizerPanel panel2 = new DigitizerPanel(null);
    
    JFrame frame2 = new JFrame();
    frame2.add(panel2);
    frame2.setVisible(true);
    Graphics g22 = panel.getGraphics();
    
    assertDoesNotThrow(() ->{
      panel2.paint(g22);
      frame2.dispose();
    });
    
    
  }

}

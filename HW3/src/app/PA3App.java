package app;

import geography.*;
import gui.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.print.*;
import java.io.*;
import java.util.*;
import javax.swing.*;


// Need to disable (or remove) Undo


public class PA3App implements ActionListener, Runnable
{
  private JFileChooser fileChooser;
  private JFrame f;
  private CartographyPanel panel;

  public void actionPerformed(ActionEvent evt)
  {
    String ac = evt.getActionCommand();
    
//    if (ac.equals("Print"))
//    {
//      PrinterJob job = PrinterJob.getPrinterJob();
//      try
//      {
//        job.setPrintable(panel);
//        boolean shouldPrint = job.printDialog();
//        if (shouldPrint) job.print();
//      }
//      catch (Exception e)
//      {
//        JOptionPane.showMessageDialog(f, 
//            "Unable to print!",
//            "Error", JOptionPane.ERROR_MESSAGE);
//      }
//    }
//    
//    if (ac.equals("Undo"))
//    {
//      panel.handleDeselect();
//    }
    
    if (ac.equals("Open"))
    {
      int retval = fileChooser.showOpenDialog(f);
      if (retval == JFileChooser.APPROVE_OPTION) 
      {
        String name = fileChooser.getSelectedFile().getName();
        AbstractMapProjection proj;
        if (name.indexOf("world") >= 0) proj = new SinusoidalProjection();
        else proj = new ConicalEqualAreaProjection(-96.0, 37.5, 29.5, 45.5);
        try
        {
          FileInputStream in = new FileInputStream(name);
          GeographicShapesReader reader = new GeographicShapesReader(in, proj);
          CartographyDocument<GeographicShape> model = reader.read();
          panel.setModel(model);
        }
        catch (FileNotFoundException fnfe)
        {
          JOptionPane.showMessageDialog(f, 
              "Unable to open file!",
              "Error", JOptionPane.ERROR_MESSAGE);
        }
      }
    }
    
    if (ac.equals("Exit"))
    {
      f.dispose();
      System.exit(0);
    }
  }
  
  
  public void run()
  {
    f = new JFrame();
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    f.setSize(600, 600);

    fileChooser = new JFileChooser();
    
    try
    {

      InputStream in = new FileInputStream(new File("world-countries.geo"));
      AbstractMapProjection proj = new SinusoidalProjection();

      // InputStream in = new FileInputStream(new File("va-blocks.geo"));
      // InputStream in = new FileInputStream(new File("va-counties.geo"));
      // InputStream in = new FileInputStream(new File("usa48-counties.geo"));
      // InputStream in = new FileInputStream(new File("usa48-states.geo"));
      // MapProjection proj = new ConicalEqualAreaProjection(-96.0, 37.5, 29.5, 45.5);

      // InputStream in = new FileInputStream(new File("rockingham-streets.geo"));
      // MapProjection proj = new ConicalEqualAreaProjection(-96.0, 37.5, 29.5, 45.5);

      // InputStream in = new FileInputStream(new File("virginia-streets.geo"));
      // MapProjection proj = new ConicalEqualAreaProjection(-96.0, 37.5, 29.5, 45.5);

      GeographicShapesReader reader = new GeographicShapesReader(in, proj);
      // Map<String, GeographicShape> map = reader.read();
      CartographyDocument<GeographicShape> model = reader.read();

      // panel = new GeographicShapePanel(map, reader.getBounds());
      panel = new CartographyPanel<GeographicShape>(model, new GeographicShapeCartographer(Color.BLACK));

      JPanel cp = (JPanel) f.getContentPane();
      cp.setLayout(new BorderLayout());
      cp.add(panel, BorderLayout.CENTER);
      
      JMenuBar menuBar = new JMenuBar();
      JMenuItem item;
      JMenu menu;
      
      menu = new JMenu("File");
      menuBar.add(menu);
//      item = new JMenuItem("Print");
//      item.addActionListener(this);
//      menu.add(item);
      item = new JMenuItem("Open");
      item.addActionListener(this);
      menu.add(item);
      
      item = new JMenuItem("Exit");
      item.addActionListener(this);
      menu.add(item);
      
//      menu = new JMenu("Zoom");
//      item = new JMenuItem("Undo");
//      item.addActionListener(this);
//      menu.add(item);
//      menuBar.add(menu);
      
      f.setJMenuBar(menuBar);
      
      
      f.setVisible(true);
    }
    catch (IOException ioe)
    {
      JOptionPane.showMessageDialog(f, 
          ioe.toString(),
          "Error", JOptionPane.ERROR_MESSAGE);
    }
  }

}

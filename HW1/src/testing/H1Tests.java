package testing;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

import org.junit.jupiter.api.Test;

import gui.CompletingDocument;
import gui.CompletingField;
import text.WordFinder;

class H1Tests {
  private WordFinder autoFiller = new WordFinder("street-names.txt");
  
  @Test
  void Wrong_File_Path_Test()
  {
    ByteArrayOutputStream errStream = new ByteArrayOutputStream();
    PrintStream preErrorState = System.err;
    System.setErr(new PrintStream(errStream));
    
    new WordFinder("steet-names.txt");
    
    System.setErr(preErrorState);
    assertTrue(errStream.toString().contains("Error reading in the file"));
  }

	@Test
	  void Auto_Fill_Test()
	  {
	    String prefix = "10";
	    String resultString = autoFiller.find(prefix);
	    
	    assertTrue(resultString.startsWith(prefix));
	    assertNotEquals(resultString, prefix);
	    
	    prefix = "Ab";
      resultString = autoFiller.find(prefix);
      
      assertTrue(resultString.startsWith(prefix));
      assertNotEquals(resultString, prefix);
      
      prefix = "A";
      resultString = autoFiller.find(prefix);
      
      assertTrue(resultString.startsWith(prefix));
      assertEquals(resultString, prefix);
      
      prefix = "Ski";
      resultString = autoFiller.find(prefix);
      
      assertTrue(resultString.startsWith(prefix));
      assertNotEquals(resultString, prefix);
      
      prefix = "Bla";
      resultString = autoFiller.find(prefix);
      
      assertTrue(resultString.startsWith(prefix));
      assertNotEquals(resultString, prefix);
      
      
      prefix = "Sunny";
      resultString = autoFiller.find(prefix);
      
      assertTrue(resultString.startsWith(prefix));
      assertEquals(resultString, prefix);
      
      
      prefix = "To";
      resultString = autoFiller.find(prefix);
      
      assertTrue(resultString.startsWith(prefix));
      assertNotEquals(resultString, prefix);
      
      prefix = "Cha";
      resultString = autoFiller.find(prefix);
      
      assertTrue(resultString.startsWith(prefix));
      assertNotEquals(resultString, prefix);
      
      prefix = "Zyn";
      resultString = autoFiller.find(prefix);
      
      assertTrue(resultString.startsWith(prefix));
      assertNotEquals(resultString, prefix);
	  }
	
  @Test
  void Empty_Word_Test()
  {
    String prefix = "";
    String resultString = autoFiller.find(prefix);
    
    assertNull(resultString);
  }
	
	 @Test
   void Word_Not_In_Test()
   {
     String prefix = "10/asdawasd";
     String resultString = autoFiller.find(prefix);
     
     assertNull(resultString);
     
     prefix = "zz";
     resultString = autoFiller.find(prefix);
     
     assertNull(resultString);
     
     prefix = "+=mondaysamiright";
     resultString = autoFiller.find(prefix);
     
     assertNull(resultString);
     
     prefix = "zzyrgots lane USA";
     resultString = autoFiller.find(prefix);
     
     assertNull(resultString);
   }
	 
   @Test
   void Intergration_Test()
   {
     String prefix = "Me";
     CompletingField field = new CompletingField();
     
     field.setWordList("street-names.txt");
     field.setText(prefix);
     assertTrue(field.getText().contains(prefix));
     assertNotEquals(field.getText(), prefix);
     

     field.setText("");
     assertTrue(field.getText().isEmpty());
     
     prefix = "A";
     field.setText(prefix);
     assertEquals(field.getText(), prefix);
     
     prefix = "Amda";
     field.setText(prefix);
     assertEquals(field.getText(), prefix);
     field.select(1, 2);
     field.replaceSelection("");
     field.setCaretPosition(2);
     field.replaceSelection("d");
     assertTrue(field.getText().contains("Adda"));
     assertEquals(field.getText(), "Addams");
     
     prefix = "Do";
     field.setText(prefix);
     assertEquals(field.getText(), "Doak");
     field.select(1, 4);
     field.replaceSelection("a");
     field.setCaretPosition(2);
     assertTrue(field.getText().contains("Da"));
     assertEquals(field.getText(), "Da Boyz");
     
     prefix = "asand98392r2by473";
     field.setText(prefix);
     assertEquals(field.getText(), prefix);
     
   }
   
   @Test
   void No_AutoFill_Test() throws BadLocationException
   {
     CompletingField field;
     
     field = new CompletingField();
     field.setText("ZZZZZZ");
     assertEquals("ZZZZZZ", field.getText());
     
     field.setText("");
     assertEquals("", field.getText());
     
     field.setText("i likke pie");
     assertEquals("i likke pie", field.getText());
     
     field.setText("hunrgy sleep");
     assertEquals("hunrgy sleep", field.getText());
   }

}


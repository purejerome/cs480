package testing;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

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
	    
	    assertTrue(resultString.toLowerCase().startsWith(prefix.toLowerCase()));
	    
	    prefix = "ab";
      resultString = autoFiller.find(prefix);
      
      assertTrue(resultString.toLowerCase().startsWith(prefix.toLowerCase()));
      
      prefix = "AB";
      resultString = autoFiller.find(prefix);
      
      assertTrue(resultString.toLowerCase().startsWith(prefix.toLowerCase()));
      
      prefix = "ski";
      resultString = autoFiller.find(prefix);
      
      assertTrue(resultString.toLowerCase().startsWith(prefix.toLowerCase()));
      
      prefix = "bla";
      resultString = autoFiller.find(prefix);
      
      assertTrue(resultString.toLowerCase().startsWith(prefix.toLowerCase()));
      
      
      prefix = "sunny";
      resultString = autoFiller.find(prefix);
      
      assertTrue(resultString.toLowerCase().startsWith(prefix.toLowerCase()));
      
      
      prefix = "to";
      resultString = autoFiller.find(prefix);
      
      assertTrue(resultString.toLowerCase().startsWith(prefix.toLowerCase()));
      
      prefix = "cha";
      resultString = autoFiller.find(prefix);
      
      assertTrue(resultString.toLowerCase().startsWith(prefix.toLowerCase()));
      
      prefix = "zyn";
      resultString = autoFiller.find(prefix);
      
      assertTrue(resultString.toLowerCase().startsWith(prefix.toLowerCase()));
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
   }
	 
   @Test
   void Intergration_Test()
   {
     String prefix = "me";
     CompletingField field = new CompletingField();
     
     field.setWordList("street-names.txt");
     field.setText(prefix);
     assertTrue(field.getText().contains(prefix));

     field.setText("");
     assertTrue(field.getText().isEmpty());
     
     prefix = "a";
     field.setText(prefix);
     assertEquals(field.getText(), prefix);
     
     prefix = "asand98392r2by473";
     field.setText(prefix);
     assertEquals(field.getText(), prefix);
     
     
     System.out.println(field.getText());
//     assertDoesNotThrow(() ->{
//       field.setWordList("street-names.txt");
//     });
   }

}

//System.out.println(resultString);

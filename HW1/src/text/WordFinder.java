package text;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.TreeSet;

/**
 * A tree set that finds the word that is nearest to the prefix entered.
 * This work complies with the JMU Honor Code.
 * @author Jerome Donfack
 *
 */
public class WordFinder 
{
	private TreeSet<String> words = new TreeSet<>(String.CASE_INSENSITIVE_ORDER);
	
	/**
	 * Creates the file tree based off the word list entered.
	 * @param fileName The file that contains the word list to be made into a tree.
	 */
	public WordFinder(final String fileName) 
	{
	  try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) 
	  {
	    String line;
	    while ((line = reader.readLine()) != null) 
	    {
	      words.add(line.trim());
	    }
	  } 
	  catch (IOException e) 
	  {
	    System.err.println("Error reading in the file: " + e.getMessage());
    }
	}
	
	/**
	 * Finds the nearest word that is prefixed with a specific substring.
	 * @param prefix The substring that is used to search for words of matching prefix.
	 * @return The word that is found or null if no word is found.
	 */
	public String find(final String prefix) 
	{
		String match = words.ceiling(prefix);
		return (!prefix.isEmpty() && match != null 
		    && match.toLowerCase().startsWith(prefix.toLowerCase())) 
		    ? match : null;
	}
}

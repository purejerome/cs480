package gui;

import javax.swing.JTextField;
import javax.swing.text.Document;

/**
 * The visual portion of the CompletingText model.
 * This work complies with the JMU Honor Code.
 * @author Jerome Donfack
 *
 */
public class CompletingField extends JTextField
{
	private static final long serialVersionUID = 1L;
	private CompletingDocument document;
	
	/**
	 * Sets the model to the CompletingDcoument model.
	 * @return The CompletingDcoument model created.
	 */
	public Document createDefaultModel() 
	{
		document = new CompletingDocument(this);
		return document;
	}
	
	/**
	 * Sets the word list used for auto filling feature.
	 * @param fileName The word list needed for auto fill feature.
	 */
	public void setWordList(final String fileName) 
	{
	  document.setWordList(fileName);
	}
}

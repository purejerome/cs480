package gui;	

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Caret;
import javax.swing.text.PlainDocument;

import text.WordFinder;
/**
 * An auto completing model for the CompletingField view.
 * This work complies with the JMU Honor Code.
 * @author Jerome Donfack
 */

public class CompletingDocument extends PlainDocument
{
  private static final long serialVersionUID = 1L;
	private CompletingField field;
	private WordFinder finder;
	
	/**
	 * Constructs a CompletingDocument tied to a CompletingField.
	 * 
	 * @param field The CompletingField that is tied to this CompletingDocument.
	 */
	public CompletingDocument(final CompletingField field) 
	{
		this.field = field;
	}
	
	/**
	 * Function called while text is input, 
	 * auto completes the word if there is a word that matches it.
	 * 
	 * @param offset The current position in where the string will be entered.
	 * @param s String that was input.
	 * @param as The styling that comes with the string.
	 */
	public void insertString(final int offset, final String s, final AttributeSet as) 
	    throws BadLocationException
	{
		String newText;
		String compText;
		
		Caret caret = field.getCaret();
		compText = super.getText(0, offset) + s;
    newText = finder.find(compText);
    if(newText == null || newText.length() == compText.length())
    {
      newText = "";
    }
    else
    {
      newText = newText.substring(compText.length(), newText.length());
    }
    newText = compText + newText;
    super.remove(0, super.getLength());
    super.insertString(0, newText, as);
    
    field.setSelectionStart(offset + 1);
    field.setSelectionEnd(offset + (newText.length() - offset));
	}
	
	/**
	 * Sets the word list for the auto fill feature to use.
	 * @param fileName The file containing possible words to auto fill.
	 */
	public void setWordList(final String fileName) 
	{
		finder = new WordFinder(fileName);
	}
}

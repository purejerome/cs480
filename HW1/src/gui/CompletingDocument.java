package gui;	

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Caret;
import javax.swing.text.PlainDocument;

import text.WordFinder;

public class CompletingDocument extends PlainDocument{
	private static final long serialVersionUID = 1L;
	private CompletingField field;
	private WordFinder finder;
	
	public CompletingDocument(CompletingField field) 
	{
		this.field = field;
	}
	
	public void insertString(int offset, String s, AttributeSet as)
	{
		String newText;
		String compText;
		
		Caret caret = field.getCaret();
		
		try 
		{
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
//			if((offset + (newText.length() - offset)) - (offset + 1) != 0 )
//			{
//				caret.setVisible(false);
//			}
//			else
//			{
//				caret.setVisible(true);
//			}
		}
		catch(BadLocationException e)
		{
			System.out.println("Bad Location: " + e.getMessage());
		}
	}
	
	public void setWordList(String fileName) 
	{
		finder = new WordFinder(fileName);
	}
}

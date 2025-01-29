package gui;

import javax.swing.JTextField;
import javax.swing.text.Document;

public class CompletingField extends JTextField{
	private static final long serialVersionUID = 1L;
	private CompletingDocument document;
	
	public Document createDefaultModel() 
	{
		document = new CompletingDocument(this);
		return document;
	}
	
	public void setWordList(String fileName) 
	{
		if(document != null)
		{
			document.setWordList(fileName);
		}
	}
}

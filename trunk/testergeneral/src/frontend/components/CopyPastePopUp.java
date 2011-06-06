package frontend.components;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.text.DefaultEditorKit;
import javax.swing.text.JTextComponent;

public class CopyPastePopUp extends JPopupMenu{
	private JTextComponent text;
	
	public CopyPastePopUp(JTextComponent text)
	{
		super("Menú");
		
		this.text=text;
		
		// Copy
		Action copyAction = new AbstractAction(DefaultEditorKit.copyAction)   
        {   
            public void actionPerformed(ActionEvent e)   
            {   
            	CopyPastePopUp.this.text.copy();
            }   
        };   
        JMenuItem copyMenuItem = new JMenuItem(copyAction);

		copyMenuItem.setText("Copiar");
		this.add(copyMenuItem);

		// Paste
		Action copyPaste = new AbstractAction(DefaultEditorKit.pasteAction)   
        {   
            public void actionPerformed(ActionEvent e)   
            {   
            	CopyPastePopUp.this.text.paste();
            }   
        }; 
		JMenuItem pasteMenuItem = new JMenuItem(copyPaste);
		pasteMenuItem.setText("Pegar");
		this.add(pasteMenuItem);

		text.setComponentPopupMenu(this);
	}

}

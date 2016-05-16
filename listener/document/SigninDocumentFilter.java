package listener.document;

import java.awt.Font;
import java.awt.Color;

import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.DocumentFilter;


public final class SigninDocumentFilter extends DocumentFilter {
     private final static Font DEFAULT_FONT = new Font("times roman", Font.ITALIC, 14);
     private final static Font INSERT_FONT = new Font("times roman", Font.PLAIN, 14);
     private final JTextField fField; 
     private final String fDefaultText;
     private boolean fIsPassField;
     
     public SigninDocumentFilter(final JTextField aField, 
                                 final String aDefaultText) {
         this.fField = aField;
         this.fDefaultText = aDefaultText;
         fField.setFont(DEFAULT_FONT);
         changeAlpha(0.5f);
         if(aDefaultText.equals("Password"))  {
             setPasswordChar((char)0);
             fIsPassField = true;
         }
         fField.setText(fDefaultText);
     }
     
        private final void setPasswordChar(final char aChar) {
            final JPasswordField passField = (JPasswordField)fField;
            passField.setEchoChar(aChar);
        }
    
 
        @Override public final void replace(final FilterBypass fb, 
                                            final int offset,
                                            final int length,
                                            final String string, 
                                            final AttributeSet attr) 
                           throws BadLocationException {
  
            final Document doc = fb.getDocument();
            String fieldText = "";
            try {
                fieldText = doc.getText(0, doc.getLength());
                final boolean insertedText = fieldText.equals(fDefaultText);
                if(insertedText) {                    
                    fb.remove(0, fDefaultText.length());
                    if(fIsPassField)
                        setPasswordChar('*');
                    fField.setFont(INSERT_FONT);
                    changeAlpha(1.0f);                    
                }
            } catch (final BadLocationException e) {
                e.printStackTrace();
            } 
            super.replace(fb, offset, length, string, attr);
        }
            
        
        
        @Override public final void remove(FilterBypass fb, 
                                           int offset, 
                                           int length) {
            final Document doc = fb.getDocument();
            String fieldText = "";
            try {
                fieldText = doc.getText(0, doc.getLength());
                final boolean deletedDefaultText = fieldText.equals(fDefaultText);
                
                if (deletedDefaultText) {
                    fb.remove(0, fDefaultText.length());
                    if(fIsPassField)
                        setPasswordChar('*');
                    fField.setFont(INSERT_FONT);
                    changeAlpha(1.0f);
                    return;
                } 
                
                final boolean oneCharacterLeft = (fieldText.length() == 1);
                if (oneCharacterLeft) {
                    fb.remove(0, 1);
                    fField.setFont(DEFAULT_FONT);
                    changeAlpha(0.5f);
                    fField.setText(fDefaultText);
                    fField.setCaretPosition(0);
                    if(fIsPassField)
                        setPasswordChar((char)0);
                    return;
                }
                super.remove(fb, offset, length);    
            } catch(final BadLocationException e) {
                e.printStackTrace();
            }          
       }
 
        public final void changeAlpha(final float alpha) {changeAlpha((int)(alpha * 255));}
        
         
        public final void changeAlpha(int alpha) {
            alpha = alpha > 255 ? 255 : alpha < 0 ? 0 : alpha;

            final Color foreground = fField.getForeground();
            final int red = foreground.getRed();
            final int green = foreground.getGreen();
            final int blue = foreground.getBlue();

            final Color withAlpha = new Color(red, green, blue, alpha);
            fField.setForeground( withAlpha );
        }
}
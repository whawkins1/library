package document;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.DocumentFilter;

import utility.Utility;


public final class CurrencyDocumentFilter extends DocumentFilter {
    private final Pattern fPattern; 
    
    public CurrencyDocumentFilter() {
              fPattern = Pattern.compile("^[0-9\\.]*$");
    }
    
    
    
        @Override public final void insertString(final FilterBypass fb,
                final int offset, 
                final String text,
                final AttributeSet attr) 
                        throws BadLocationException {
                final StringBuilder sb = createStringBuilder(fb);
                sb.insert(offset, text);
                final String testString = sb.toString();
                
                final Matcher matcher = fPattern.matcher(testString);
                if(matcher.matches()) {
                    super.insertString(fb, offset, text, attr);
                    return;
                }
                Utility.showErrorMessage(null, "Input Must be a Number or Decimal");
          }
    
    
    
        @Override public final void replace(final FilterBypass fb, 
               final int offset,
               final int length,
               final String text, 
               final AttributeSet attr) 
                          throws BadLocationException {
            
                final StringBuilder sb = createStringBuilder(fb);
                sb.replace(offset, offset + length, text);
                final String testString = sb.toString();
                
                final Matcher matcher = fPattern.matcher(testString);
                if(matcher.matches()) {
                    super.replace(fb, offset, length, text, attr);
                    return;
                }
                Utility.showErrorMessage(null, "Input Must be a Number or Decimal.");
        }
    
        
    
        @Override public final void remove(final FilterBypass fb, 
                                           final int offset, 
                                           final int length) 
                      throws BadLocationException {
                final StringBuilder sb = createStringBuilder(fb);
                sb.delete(offset, offset + length);
                fb.remove(offset, length);
         }
    
        
        private final StringBuilder createStringBuilder(final FilterBypass aFB) 
                throws BadLocationException {
                 final Document doc = aFB.getDocument();
                 final StringBuilder sb = new StringBuilder();
                 sb.append(doc.getText(0, doc.getLength()));
                 return sb;
        }
}
package document;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.DocumentFilter;

import utility.Utility;

public final class NumbersLettersOnlyDocumentFilter extends DocumentFilter {
     private final int maxLength_;
     private boolean fNumbersOnly;
     private boolean fLettersOnly;
     private Pattern patternLetters;
     
     public NumbersLettersOnlyDocumentFilter(final int aMaxLength,
                                            final boolean aNumbersOnly,
                                            final boolean aLettersOnly) {
         this.maxLength_ = aMaxLength;
         this.fNumbersOnly = aNumbersOnly;
         this.fLettersOnly = aLettersOnly;
         if(fLettersOnly) 
             patternLetters = Pattern.compile("^[ A-z]+$");
     }

         @Override public final void insertString(final FilterBypass fb,
                                                  final int offset, 
                                                  final String text,
                                                  final AttributeSet attr) 
                                                          throws BadLocationException {
                 
                 final StringBuilder sb = createStringBuilder(fb);
                 sb.insert(offset, text);
                 final String testString = sb.toString();
                 
                 if(fNumbersOnly) {
                     if(validLength(fb, text)) {
                         if(isInteger(testString)) {
                             super.insertString(fb, offset, text, attr);
                         } else {
                             Utility.showErrorMessage(null, "Input Must be Numerical");
                         }
                     }
                 } else if(fLettersOnly) {
                     final Matcher matcher = patternLetters.matcher(testString) ;
                     if(matcher.matches()) {
                         super.insertString(fb, offset, text, attr);
                     } else {
                         Utility.showErrorMessage(null, "Input Must be a Letter or Space");
                     }
                         
                 }
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
             
             if(fNumbersOnly) {
                 if(validLength(fb, text)) {
                     if(isInteger(testString)) {
                          super.replace(fb, offset, length, text, attr);
                     } else {
                          Utility.showErrorMessage(null, "Input Must be Numerical");
                     }
                 }
             } else if(fLettersOnly) {
                 final boolean letterOrWS = isLetterOrWhiteSpace(testString);
                 if(letterOrWS) {
                     super.replace(fb, offset, length, text, attr);
                 } else {
                     Utility.showErrorMessage(null, "Input Must be a Letter or Space");
                 }
                     
             }
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
     
     
         private final boolean isLetterOrWhiteSpace(final String aText) {
             final Matcher matcher = patternLetters.matcher(aText);
             return matcher.matches();
             
         }
         
         
         private final boolean validLength(final FilterBypass aFB, final String aText) {
             final Document doc = aFB.getDocument();
             final boolean validLength = ((maxLength_ > 0) && 
                                         (doc.getLength() + aText.length() <= maxLength_));
             return validLength;
         }
         
         
         private final boolean isInteger(final String aText) {
             try {
                 Integer.parseInt(aText);
                 return true;
             } catch(final NumberFormatException e) {
                 return false;
             }
         }
}
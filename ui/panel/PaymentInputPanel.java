package ui.panel;

import java.awt.GridBagLayout;
import java.math.BigDecimal;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.AbstractDocument;

import document.CurrencyDocumentFilter;
import utility.Utility;

public final class PaymentInputPanel extends JPanel{
    private static final long serialVersionUID = 1L;
    private final JTextField fEmailTF;
    private final JTextField fPaymentTF;
    
    public PaymentInputPanel() {
        super(new GridBagLayout());
        add(new JLabel("Email: "), Utility.setConstraints(0, 0));
        fEmailTF = new JTextField(20);
        add(fEmailTF, Utility.setConstraints(1, 0));
        add(new JLabel("Payment: "), Utility.setConstraints(0, 1));
        fPaymentTF = new JTextField(20);
        final AbstractDocument document = (AbstractDocument)fPaymentTF.getDocument();
        document.setDocumentFilter(new CurrencyDocumentFilter());
        add(fPaymentTF, Utility.setConstraints(1, 1));
    }
    
        public final String getEmailInput() { return this.fEmailTF.getText().trim(); }
        
        
        public final BigDecimal getPaymentInput() { 
            final String inputText = this.fPaymentTF.getText().trim();
            final BigDecimal bd = new BigDecimal(inputText); 
            bd.setScale(2);
            
            return bd;
        }
}
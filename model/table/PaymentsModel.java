package model.table;

import java.util.List;

import handler.DataHandler;
import payment.Payment;

import javax.swing.table.AbstractTableModel;

public final class PaymentsModel extends AbstractTableModel {
    private static final long serialVersionUID = 1L;
    private static final String[] COLUMN_NAMES = {"Payment", "Date"};
    private static final int PAYMENT_DATE = 1;
    private static final int PAYMENT_TOTAL = 2;
    
    private List<Payment> fPaymentList;

    public PaymentsModel(final DataHandler aHandler, final String aEmail) {
         
        for(Payment p : aHandler.getPaymentList()) {
            final boolean isPayment = p.getEmail().equals(aEmail); 
            if(isPayment)
               fPaymentList.add(p);
        }
    }
    
        @Override public final int getColumnCount() {  return 2;  }
    
        
        @Override public final String getColumnName(final int aColumn) { return COLUMN_NAMES[aColumn]; }    
        
        
        @Override public final int getRowCount() { return fPaymentList.size(); }
    
        
        @Override public final Object getValueAt(final int aRow, final int aColumn) {
            final Payment payment = fPaymentList.get(aRow);
            
            switch(aColumn) {
                case PAYMENT_DATE:
                    return payment.getDate();
                case PAYMENT_TOTAL:
                    return payment.getPayment();    
                default:
                    return null;
            }
            
        }
}
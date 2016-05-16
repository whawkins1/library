package model.table;

import handler.DataHandler;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import payment.Payment;

public final class PaymentsAllModel extends AbstractTableModel {
    
        private static final long serialVersionUID = 1L;
        private static final String[] COLUMN_NAMES = {"Last", "First", "Payment", "Date"};
        private static final int LAST_NAME = 1;
        private static final int FIRST_NAME = 2;
        private static final int PAYMENT_DATE = 3;
        private static final int PAYMENT_TOTAL = 4;
        
        private List<Payment> fPaymentList;

        public PaymentsAllModel(final DataHandler aHandler) {
            fPaymentList = aHandler.getPaymentList(); 
        }
        
            @Override public final int getColumnCount() {  return 4;  }
        
            
            @Override public final String getColumnName(final int aColumn) { return COLUMN_NAMES[aColumn]; }    
            
            
            @Override public final int getRowCount() { return fPaymentList.size(); }
        
            
            @Override public final Object getValueAt(final int aRow, final int aColumn) {
                final Payment payment = fPaymentList.get(aRow);
                
                switch(aColumn) {
                    case LAST_NAME:
                        return payment.getLastName();
                    case FIRST_NAME:
                        return payment.getFirstName();
                    case PAYMENT_DATE:
                        return payment.getDate();
                    case PAYMENT_TOTAL:
                        return payment.getPayment();    
                    default:
                        return null;
                }
                
            }
    
}

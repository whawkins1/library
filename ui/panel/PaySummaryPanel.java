package ui.panel;

import handler.DataHandler;

import java.awt.Color;
import java.awt.GridBagLayout;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import java.util.Date;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JPanel;

import listener.action.PaymentConfirmListener;
import Personnel.Employee;
import Personnel.Student;
import utility.Utility;

public final class PaySummaryPanel extends JPanel {
    private static final long serialVersionUID = 1L;
    public PaySummaryPanel(final PaymentConfirmListener aPaymentConfirmListener,
                           final DataHandler aHandler,
                           final String aEmployeeEmail,
                           final JFrame aParent) {
        super(new GridBagLayout());
        setBackground(Color.white);
        createAddDate();
        createAddStudentNameInfo(aHandler, aPaymentConfirmListener);
        createAddPaymentInfo(aPaymentConfirmListener);
        createAddChangeInfo(aPaymentConfirmListener);
        createAddBalanceInfo(aPaymentConfirmListener);
        createAddEmployeeNameInfo(aHandler, aEmployeeEmail, aPaymentConfirmListener);
        
    }
    
        
       private final void createAddDate() {
           add(Utility.createLabelTitle("Date: "), Utility.setConstraints(0, 1));
           final Date input = new Date();
           final Instant instant = input.toInstant();
           final ZonedDateTime zdt = instant.atZone(ZoneId.systemDefault());
           final LocalDate date = zdt.toLocalDate();
           final String dateStr = date.format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
           add(Utility.createLabelTitle(dateStr), Utility.setConstraints(1, 1));
       }
    
       private final void createAddStudentNameInfo(final DataHandler aHandler, 
                                                  final PaymentConfirmListener aPaymentConfirmListener) {
            final String studentEmail = aPaymentConfirmListener.getStudentEmail();
            final HashMap<String, Student> studentMap = aHandler.getStudentMap();
            final Student s = studentMap.get(studentEmail);
            final String studentName = (s.getFirstName() + " " + s.getLastName());
            add(Utility.createLabelTitle("Student: "), Utility.setConstraints(0, 0));
            add(Utility.createLabelTitle(studentName), Utility.setConstraints(1, 0));
        }
        
        
        private final void createAddPaymentInfo(final PaymentConfirmListener aPaymentConfirmListener) {
            add(Utility.createLabelTitle("Payment: "), Utility.setConstraints(0, 2));
            final String payment = aPaymentConfirmListener.getPayment();
            add(Utility.createLabelTitle(payment), Utility.setConstraints(1, 2));
        }
        
        
        private final void createAddChangeInfo(final PaymentConfirmListener aPaymentConfirmListener) {
            add(Utility.createLabelTitle("Change: "), Utility.setConstraints(0, 3));
            final String change = aPaymentConfirmListener.getChange();
            add(Utility.createLabelTitle(change), Utility.setConstraints(1, 3));
        }
        
        
        private final void createAddBalanceInfo(final PaymentConfirmListener aPaymentConfirmListener) {
            add(Utility.createLabelTitle("Balance: "), Utility.setConstraints(0, 4));
            final String balance = aPaymentConfirmListener.getBalance();
            add(Utility.createLabelTitle(balance), Utility.setConstraints(1, 4));
        }

        
        private final void createAddEmployeeNameInfo(final DataHandler aHandler,
                                                     final String aEmail,
                                                     final PaymentConfirmListener aPaymentConfirmListener) {
            
            add(Utility.createLabelTitle("Employee: "), Utility.setConstraints(0, 5));
            final HashMap<String, Employee> employeeMap = aHandler.getEmployeeMap();
            final Employee e = employeeMap.get(aEmail);
            final String employeeName = (e.getFirstName() + " " + e.getLastName());
            add(Utility.createLabelTitle(employeeName), Utility.setConstraints(1, 5));
        }
}
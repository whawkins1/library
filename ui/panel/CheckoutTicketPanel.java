package ui.panel;

import java.awt.Color;
import java.awt.GridBagLayout;

import java.sql.Connection;

import java.time.LocalDate;

import javax.swing.JPanel;

import utility.Utility;

public final class CheckoutTicketPanel extends JPanel{
    
    
    private static final long serialVersionUID = 1L;
    public CheckoutTicketPanel(final int aCheckoutID, 
                               final String aCallNumber,
                               final Connection aConn) {
        super(new GridBagLayout());
        setBackground(Color.white);
        add(Utility.createLabelTitle("Checkout ID: "), Utility.setConstraints(0, 0));
        add(Utility.createLabelTitle(String.valueOf(aCheckoutID)), Utility.setConstraints(1, 0));
        add(Utility.createLabelTitle("Title: "), Utility.setConstraints(0, 1));
        final String title = Utility.getTitle(aCallNumber, aConn);
        add(Utility.createLabelTitle(title), Utility.setConstraints(1, 1));
        add(Utility.createLabelTitle("Checkout Date: "), Utility.setConstraints(0, 2));
        add(Utility.createLabelTitle(Utility.joinDates(LocalDate.now())), Utility.setConstraints(1, 2));
        add(Utility.createLabelTitle("Due Date: "), Utility.setConstraints(0, 3));
        final LocalDate returnDate = LocalDate.now().plusDays(45);
        add(Utility.createLabelTitle(Utility.joinDates(returnDate)), Utility.setConstraints(1, 3));
    }    
}
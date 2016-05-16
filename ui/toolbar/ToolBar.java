package ui.toolbar;

import handler.DataHandler;

import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;

import ui.button.CheckoutDisplayButton;
import ui.button.ReturnDisplayButton;
import ui.button.SearchDisplayButton;

public final class ToolBar extends JToolBar {
    private static final long serialVersionUID = 1L;
    
    public ToolBar(final JTabbedPane aTabPane,
                   final JFrame aFrame,
                   final int aIsAdmin,
                   final DataHandler aHandler,
                   final String aSigninEmail) {

        super(SwingConstants.HORIZONTAL);
        add(new CheckoutDisplayButton(aFrame, aSigninEmail));
        add(Box.createHorizontalStrut(5));
        add(new ReturnDisplayButton(aFrame));
        add(Box.createHorizontalStrut(5));
        addSeparator();
        /*add(Box.createHorizontalStrut(5));
        add(new CategoryOpenChooseButton (aFrame, aHandler) );
        add(Box.createHorizontalStrut(5));
        addSeparator();
        add(Box.createHorizontalStrut(5));
        add(new PaymentDisplayButton(aFrame, aSigninEmail, aHandler));
        addSeparator();
        add(Box.createHorizontalStrut(5));
        add(new OnHoldDisplayButton(aFrame));
        add(Box.createHorizontalStrut(10));
        add(new GoodStandingDisplayButton(aFrame));
        addSeparator();*/
        add(Box.createHorizontalStrut(5));
        add(new SearchDisplayButton(aFrame, aTabPane, aHandler));
        /*add(Box.createHorizontalStrut(5));
        add(new QuickFindOpenButton(aFrame, aIsAdmin, aHandler));
        add(Box.createHorizontalStrut(5));
        addSeparator();
        add(Box.createHorizontalStrut(5));
        add(new LogsButton(aFrame, aHandler));
        add(Box.createHorizontalStrut(5));
        addSeparator();
        add(Box.createHorizontalStrut(5));
        add(new SettingsDisplayButton(aFrame));
        add(Box.createHorizontalStrut(5));
        addSeparator();
        add(new SignoutButton(aFrame, aSigninEmail, aHandler));*/
    }
}
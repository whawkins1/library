package ui.button;

import javax.swing.JButton;

import listener.action.QuickFindActionListener;

public final class QuickFindButton  extends JButton {
    private static final long serialVersionUID = 1L;

    public QuickFindButton (final QuickFindActionListener aListener) {
        super("Find");
        addActionListener(aListener);
        setToolTipText("Find Any Personnel or Inventory");
    }
}
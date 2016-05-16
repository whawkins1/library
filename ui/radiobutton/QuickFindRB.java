package ui.radiobutton;

import javax.swing.JRadioButton;

import listener.item.QuickFindItemListener;

public final class QuickFindRB extends JRadioButton {
    private static final long serialVersionUID = 1L;

    public QuickFindRB(final QuickFindItemListener aListener,
                       final String aTitle) {
      super(aTitle);     
      addItemListener(aListener);
    }
}
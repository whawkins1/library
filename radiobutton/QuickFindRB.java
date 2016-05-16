package radiobutton;

import java.awt.event.ItemListener;
import javax.swing.JRadioButton;


public final class QuickFindRB extends JRadioButton {
    private static final long serialVersionUID = 1L;

    public QuickFindRB(final String aTitle, final ItemListener aListener) {
      super(aTitle);     
      addItemListener(aListener);
    }
}
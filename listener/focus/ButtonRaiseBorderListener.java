package listener.focus;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;

public final class ButtonRaiseBorderListener
                                    implements FocusListener {

        private final JButton fButton;
        public ButtonRaiseBorderListener(final JButton aButton) {
            this.fButton = aButton;
        }
        
           @Override public final void focusGained(final FocusEvent e) {
                fButton.setBorder(BorderFactory.createRaisedBevelBorder());
           }
    
           @Override public final void focusLost(final FocusEvent e) {
               fButton.setBorder(BorderFactory.createEmptyBorder());
               
           }
}

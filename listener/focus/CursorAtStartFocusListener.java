package listener.focus;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.JTextField;

public final class CursorAtStartFocusListener extends FocusAdapter {
    private final JTextField fTextField;
    public CursorAtStartFocusListener(final JTextField aTextField) {
        this.fTextField = aTextField;
    }
    
        @Override public final void focusGained(final FocusEvent e) {
            fTextField.setCaretPosition(0);
        }
}
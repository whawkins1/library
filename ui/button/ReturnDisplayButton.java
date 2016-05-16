package ui.button;

import java.awt.Image;
import java.awt.event.KeyEvent;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.KeyStroke;

import listener.action.ReturnListener;
import listener.focus.ButtonRaiseBorderListener;

public final class ReturnDisplayButton extends JButton {
    private static final long serialVersionUID = 1L;
    public ReturnDisplayButton(final JFrame aFrame) {
        super("Return");
        addActionListener(new ReturnListener(aFrame));
        setVerticalTextPosition(JButton.BOTTOM);
        setHorizontalTextPosition(JButton.CENTER);
        try {
            Image image = ImageIO.read(getClass().getResource("/images/return.png"));
            setIcon(new ImageIcon(image));
         } catch (final IOException e) {
              e.printStackTrace();
         }
        final InputMap im = getInputMap(WHEN_FOCUSED);
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false), "pressed");
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, true), "released");
        setToolTipText("Open Dialog for Return");    
        setFocusPainted(false);
        setBorder(BorderFactory.createEmptyBorder());
        addFocusListener(new ButtonRaiseBorderListener(this));
    }
}
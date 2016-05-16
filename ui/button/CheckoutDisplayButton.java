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

import listener.action.CheckoutListener;
import listener.focus.ButtonRaiseBorderListener;

public final class CheckoutDisplayButton extends JButton {
    private static final long serialVersionUID = 1L;

    public CheckoutDisplayButton(final JFrame aFrame, final String aSigninEmail) {
        super("Checkout");    
        addActionListener(new CheckoutListener(aFrame, aSigninEmail));
        setVerticalTextPosition(JButton.BOTTOM);
        setHorizontalTextPosition(JButton.CENTER);
        try {
           Image image = ImageIO.read(getClass().getResource("/images/checkout.png"));
           setIcon(new ImageIcon(image));
        } catch (final IOException e) {
             e.printStackTrace();
        }
        final InputMap im = getInputMap(WHEN_FOCUSED);
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false), "pressed");
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, true), "released");
        setFocusPainted(false);
        setBorder(BorderFactory.createEmptyBorder());
        setToolTipText("Open Dialog to Checkout");
        addFocusListener(new ButtonRaiseBorderListener(this));
    }  
}
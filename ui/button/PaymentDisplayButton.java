package ui.button;

import handler.DataHandler;

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

import listener.action.PaymentDisplayInputListener;
import listener.focus.ButtonRaiseBorderListener;

public final class PaymentDisplayButton extends JButton {
    private static final long serialVersionUID = 1L;
    
    public PaymentDisplayButton(final JFrame aFrame, 
                                final DataHandler aHandler,
                                final String aSigninEmail) {
        super("Payment");    
        addActionListener(new PaymentDisplayInputListener(aFrame, aSigninEmail, aHandler));
        setVerticalTextPosition(JButton.BOTTOM);
        setHorizontalTextPosition(JButton.CENTER);
        try {
           Image image = ImageIO.read(getClass().getResource("/images/payment.png"));
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
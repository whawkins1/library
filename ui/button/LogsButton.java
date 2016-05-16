package ui.button;

import java.awt.Image;
import java.awt.event.KeyEvent;
import java.io.IOException;

import handler.DataHandler;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.KeyStroke;

import listener.focus.ButtonRaiseBorderListener;
import listener.mouse.LogsButtonMouseListener;

public final class LogsButton extends JButton {
    private static final long serialVersionUID = 1L;

    public LogsButton(final JFrame aFrame, final DataHandler aHandler) {
        super("Logs");    
        setVerticalTextPosition(JButton.BOTTOM);
        setHorizontalTextPosition(JButton.CENTER);
        try {
            Image image = ImageIO.read(getClass().getResource("/images/logs.png"));
            setIcon(new ImageIcon(image));
         } catch (final IOException e) {
              e.printStackTrace();
         }
        final InputMap im = getInputMap(WHEN_FOCUSED);
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false), "pressed");
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, true), "released");
        setFocusPainted(false);
        setBorder(BorderFactory.createEmptyBorder());
        addMouseListener(new LogsButtonMouseListener(aFrame, aHandler));
        addFocusListener(new ButtonRaiseBorderListener(this));
    }
}
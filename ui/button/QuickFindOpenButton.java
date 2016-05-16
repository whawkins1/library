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
import ui.dialog.QuickFindDialog;

public final class QuickFindOpenButton extends JButton {
    private static final long serialVersionUID = 1L;

public QuickFindOpenButton (final JFrame aFrame, 
                            final int aIsAdmin,
                            final DataHandler aHandler) {
       super("Quick Find");
       setVerticalTextPosition(JButton.BOTTOM);
       setHorizontalTextPosition(JButton.CENTER);
       try {
           Image image = ImageIO.read(getClass().getResource("/images/quickfind.png"));
           setIcon(new ImageIcon(image));
        } catch (final IOException e) {
             e.printStackTrace();
        }
       final InputMap im = getInputMap(WHEN_FOCUSED);
       im.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false), "pressed");
       im.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, true), "released");
       setFocusPainted(false);
       setBorder(BorderFactory.createEmptyBorder());
       addActionListener( ae -> {new QuickFindDialog(aFrame, aHandler, aIsAdmin);});
       addFocusListener(new ButtonRaiseBorderListener(this));
   }
}
package ui.dialog;

import java.awt.BorderLayout;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;

import ui.panel.AboutInfoPanel;
import utility.Utility;

public final class AboutDialog extends JDialog {
    private static final long serialVersionUID = 1L;
    
    public AboutDialog(final JFrame aParent) {
        super(aParent, "About JLibrary", true);
        Utility.setCloseOperation(this);
        setIconImage(Utility.getSmallImage());
        BufferedImage image = null;
        try {
         image = ImageIO.read(getClass().getResource("/images/about.png"));
        } catch (final IOException e) {
          e.printStackTrace();
        }
        final ImageIcon icon = new ImageIcon(image);
        final JLabel iconLabel = new JLabel(icon);
        add(iconLabel, BorderLayout.WEST);
        add(new AboutInfoPanel(), BorderLayout.EAST);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        pack();
        setLocationRelativeTo(aParent);
        setVisible(true);  
    }
}

package ui.frame;

import handler.DataHandler;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.image.BufferedImage;

import java.io.IOException;

import javax.imageio.ImageIO;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JRootPane;
import javax.swing.border.Border;
import javax.swing.plaf.basic.BasicProgressBarUI;

import thread.DatabaseWorker;

public final class SplashScreen extends JFrame{
    private static final long serialVersionUID = 1L;
        private final JProgressBar pb_;
        
        public SplashScreen(final DataHandler aHandler, 
                            final int aIsAdmin,
                            final String aSigninEmail) {
            BufferedImage image = null;
            try {
             image = ImageIO.read(getClass().getResource("/images/libraryBig.png"));
            } catch (final IOException e) {
              e.printStackTrace();
            }
            final ImageIcon icon = new ImageIcon(image);
            final JLabel iconLabel = new JLabel();
            iconLabel.setIcon(icon);
            add(iconLabel, BorderLayout.CENTER);
            pb_ = new JProgressBar();
            pb_.setMinimum(0);
            pb_.setMaximum(100);
            pb_.setStringPainted(true);
            pb_.setBackground(Color.WHITE);
            pb_.setForeground(Color.BLACK);
            pb_.setUI(new BasicProgressBarUI() {
                protected Color getSelectionBackground() { return Color.BLACK;}
                protected Color getSelectionForeground() { return Color.WHITE;}
            });
            add(pb_,  BorderLayout.SOUTH);
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            setUndecorated(true);
            final JRootPane rp = getRootPane();           
            final Border border = (BorderFactory.createLineBorder(Color.BLACK, 3));
            rp.setBorder(border);
            pack();
            setLocationRelativeTo(null);
            setVisible(true);            
            DatabaseWorker dbWorker = new DatabaseWorker(this, aIsAdmin, aHandler, aSigninEmail);
            dbWorker.execute();
       }             
        
        
       public final void setProgressBarStatus(final int aProgressValue) { 
           pb_.setValue(aProgressValue);
       }
}
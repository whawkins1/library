package listener.mouse;

import handler.DataHandler;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;

import ui.popup.LogsPopup;

public final class LogsButtonMouseListener 
                              implements MouseListener {
    private final JFrame frame_;
    private final DataHandler handler_;
    
    public LogsButtonMouseListener(final JFrame aFrame, final DataHandler aHandler) {
        this.frame_ = aFrame;
        this.handler_ = aHandler;
    }

        @Override public void mouseClicked(MouseEvent e) {}
    
        @Override public void mouseEntered(MouseEvent e) {}
    
        @Override public void mouseExited(MouseEvent e) {}
    
        @Override public void mousePressed(final MouseEvent e) {
            final LogsPopup popup = new LogsPopup(frame_, handler_);
            popup.show(e.getComponent(), e.getX(), e.getY());
        }
    
        @Override public void mouseReleased(MouseEvent e) {}
}

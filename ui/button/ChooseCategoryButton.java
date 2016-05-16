package ui.button;

import handler.DataHandler;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;

import listener.action.ChooseCategoryBtnListener;

public final class ChooseCategoryButton extends JButton {
    private static final long serialVersionUID = 1L;
   
    public ChooseCategoryButton(final JDialog aDialog,
                                final JFrame aFrame,
                                final JComboBox<String> aCategoryCB, 
                                final JComboBox<String> aTypeCB,
                                final DataHandler aHandler) {
        super("OK");
        aDialog.getRootPane().setDefaultButton(this);
        addActionListener(new ChooseCategoryBtnListener(aFrame, 
                                                        aCategoryCB, 
                                                        aTypeCB,
                                                        aHandler
                                                        )
        );
    }
}
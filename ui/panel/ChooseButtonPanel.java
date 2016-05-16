package ui.panel;

import handler.DataHandler;

import java.awt.FlowLayout;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

import ui.button.ChooseCategoryButton;

public final class ChooseButtonPanel extends JPanel {
    private static final long serialVersionUID = 1L;

    public ChooseButtonPanel(final JDialog aDialog,  
                             final JFrame aFrame,
                             final JComboBox<String> aCategoryCB, 
                             final JComboBox<String> aTypeCB,
                             final DataHandler aHandler) {
        super(new FlowLayout(FlowLayout.CENTER));
      
        add(new ChooseCategoryButton(aDialog, 
                                     aFrame, 
                                     aCategoryCB, 
                                     aTypeCB, 
                                     aHandler
                                    )
        );
    }
}
package listener.item;


import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JTextField;

import listener.action.QuickFindActionListener;
import ui.button.QuickFindButton;
import ui.dialog.QuickFindDialog;
import ui.panel.QuickFindButtonPanel;
import ui.panel.QuickFindInputPanel;
import utility.Utility;

 public final class QuickFindItemListener 
                                     implements ItemListener{
    
    private final QuickFindInputPanel fPanel;
    private final QuickFindDialog fDialog;
    private QuickFindButton fButton;
    private final JTextField fTF;
    private final JLabel fLabelTitle;
    private final String fTitle;

    public QuickFindItemListener(final QuickFindInputPanel aPanel,
                                          final QuickFindDialog aDialog,
                                          final QuickFindActionListener aListener,
                                          final JTextField aTF,
                                          final JLabel aLabel,
                                          final String aTitle) {
                    this.fPanel = aPanel;
                    this.fDialog = aDialog;
                    this.fTF = aTF;
                    this.fLabelTitle = aLabel;
                    this.fTitle = aTitle;
                    this.fButton = new QuickFindButton(aListener); 
         }
    

        @Override public final void itemStateChanged(final ItemEvent evt) {           
              if (evt.getStateChange() == ItemEvent.SELECTED) {
                  if(fPanel.isSelected()) {
                      fLabelTitle.setText(fTitle);
                      fTF.setText("");
                      
                  } else {
                      fLabelTitle.setText(fTitle);
                      final JPanel panel = new JPanel(new GridBagLayout());
                      panel.add(fLabelTitle, Utility.setConstraints(0, 2));
                      panel.add(fTF, Utility.setConstraints(1, 2));
                      fPanel.add(panel, BorderLayout.CENTER);
                      fDialog.add(new QuickFindButtonPanel(fButton), BorderLayout.SOUTH);
                      final JRootPane rp = fDialog.getRootPane();
                      rp.setDefaultButton(fButton);
                      fDialog.setLocationRelativeTo(null);
                      fPanel.setIsSelected();
                  }
                  fDialog.revalidate();
                  fDialog.pack();
              } 
        }
 }
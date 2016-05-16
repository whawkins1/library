package ui.dialog;

import handler.DataHandler;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JRootPane;
import javax.swing.JTabbedPane;

import listener.action.SearchListener;
import ui.panel.SearchButtonPanel;
import ui.panel.SearchFieldsPanel;
import ui.panel.SearchPanel;
import utility.Utility;

public final class SearchDialog extends JDialog {
    private static final long serialVersionUID = 1L;
    
	public SearchDialog(final JFrame aParent, 
	                    final JTabbedPane aTabPane, 
	                    final DataHandler aHandler) {
	    
	    super(aParent, "Search", false);	   
	    Utility.setCloseOperation(this);
	    final SearchFieldsPanel searchFieldsPanel = new SearchFieldsPanel();
	    final SearchPanel searchPanel = new SearchPanel(searchFieldsPanel);
	    add(searchPanel, BorderLayout.NORTH);	    
	    add(searchFieldsPanel, BorderLayout.CENTER);
	    final SearchListener searchListener = new SearchListener(searchPanel, this, aHandler, searchFieldsPanel, aTabPane);
	    searchListener.setParent(aParent);
        final JButton button = Utility.createButton("Search", "SEARCH", searchListener);
	    final SearchButtonPanel buttonPanel = new SearchButtonPanel(button);
	    add(buttonPanel, BorderLayout.SOUTH);
	    final JRootPane rp = getRootPane();
	    rp.setDefaultButton(button);
	    pack();
	    setLocationRelativeTo(aParent);
	    setVisible(true);
	}        
}
package group;


import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;

public final class CheckoutButtonGroup extends ButtonGroup{
    private static final long serialVersionUID = 1L;
    
    public CheckoutButtonGroup(final JRadioButton aStudent, final JRadioButton aFaculty) {
        add(aStudent);
        add(aFaculty);
    }
    
    public CheckoutButtonGroup(final JRadioButton aBook, 
                               final JRadioButton aAudio,
                               final JRadioButton aVideo) {
        add(aBook);
        add(aAudio);
        add(aVideo);
    }
}
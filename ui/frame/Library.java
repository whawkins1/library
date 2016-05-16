package ui.frame;

import handler.DataHandler;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

import listener.window.SignOffWindowListener;
import ui.menubar.MenuBar;
import ui.tabpane.TabPane;
import ui.toolbar.ToolBar;
import utility.Utility;


public class Library extends JFrame {
    private static final long serialVersionUID = 1L;
    
    public Library(final DataHandler aHandler, 
                   final int aIsAdmin,
                   final String aSigninEmail)    {
        super("Library");       
        setJMenuBar(new MenuBar(this, aSigninEmail, aHandler, (aIsAdmin != 0)));
        final TabPane tabPane = new TabPane(aHandler, this);
        add(new ToolBar(tabPane, this, aIsAdmin, aHandler, aSigninEmail), BorderLayout.NORTH);
        add(tabPane, BorderLayout.CENTER);
        addWindowListener(new SignOffWindowListener(aSigninEmail, this));
        setIconImage(Utility.getSmallImage());
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        final Toolkit tk = Toolkit.getDefaultToolkit();
        final Dimension d = tk.getScreenSize();
        setPreferredSize(new Dimension((d.width / 2), (d.height / 2)));
        pack();
        setLocationRelativeTo(null);
        setVisible(true);	  
        
	    /* Search TextField faculty student
	     Calculate fine faculty student	
	     Show Students Overdue Books	    
	     Show Students On Hold	
	     ShowStudents Good Standing	    
	     search keyword books	
	     list books subject search	
	     sort author text field	
	     top ten books demand	
	     checkout	
	     return	
	     delete student, faculty, book	
	     call number	    
	     transaction id	    
	     member id	    
	     current fine	    
	     checkoutid
	     students 3 copies
	     faculty 10 copies
        

        findStandingHoldRS = statement2.executeQuery("SELECT Checkout_Students.student_id_ckout, DATE_ADD(Checkout_Students.student_ckoutdate, INTERVAL 1 MONTH), Checkout_Students.student_returndate 
                                                      FROM Checkout_Students");

        getMemberIDRS = statement3.executeQuery("SELECT Students.student_id, Students.num_books_ckout FROM Students");

        GregorianCalendar student_calendar_six_months = new GregorianCalendar();

        student_calendar_six_months.add(Calendar.MONTH, -6);


         GregorianCalendar today;

         today = new GregorianCalendar();

         Integer count = 0;


       Vector membersVector = new Vector();
       Vector membersCheckoutTotal = new Vector();



       getMemberIDRS.next();

       do{

             membersVector.addElement(student_id_hold_good = getMemberIDRS.getString(1));

             membersCheckoutTotal.addElement(getMemberIDRS.getInt(2));



       }while(getMemberIDRS.next());


       for(int i = 0; i < membersVector.size(); i++)
       {

            Integer checkoutTotal = (Integer)membersCheckoutTotal.elementAt(i);

            Integer overDueBookCount = 0;

            Integer countCheckoutSixMonths = 0;

            String holdID = (membersVector.elementAt(i)).toString();


             findStandingHoldRS.next();

             do{

                  boolean returnDateNull = false;


                  student_id_ckout = findStandingHoldRS.getString(1);




                  if(holdID.equalsIgnoreCase(student_id_ckout));
                  {

                      count++;

                      //System.out.println("Name " + student_id_hold_good);



                     java.sql.Date student_ckoutdate = findStandingHoldRS.getDate(2);
                     java.sql.Date student_returndate = findStandingHoldRS.getDate(3);

                      java.util.Date student_ckoutdate_convert = new java.util.Date(student_ckoutdate.getTime());

                     //if(student_returndate != null)
                     //{

                        // java.util.Date student_returndate_convert = new java.util.Date(student_returndate.getTime());
                     //}

                     if(findStandingHoldRS.wasNull())
                     {
                         returnDateNull = true;


                     }


                     GregorianCalendar student_calendar_good_standing;

                     student_calendar_good_standing = new GregorianCalendar();

                     student_calendar_good_standing.setTime(student_ckoutdate_convert);


                     if(returnDateNull && today.after(student_calendar_good_standing))
                     {

                         //System.out.println("today " + today.get(Calendar.MONTH) + "Day " + today.get(Calendar.DAY_OF_MONTH));
                         //System.out.println("Checkoutdate " + student_calendar_good_standing.get(Calendar.MONTH)+  "Day" + student_calendar_good_standing.get(Calendar.DAY_OF_MONTH));

                          overDueBookCount++;
                     }
                     else if( student_calendar_good_standing.after(student_calendar_six_months))
                     {

                              countCheckoutSixMonths++;
                     }



                  }

             }while(findStandingHoldRS.next());


             Float averageSixMonths = (float) countCheckoutSixMonths / 24;


             System.out.println("Average Six Months " + averageSixMonths);
             System.out.println("Count Checkout Six Months " + countCheckoutSixMonths);
             System.out.println("Over due books " + overDueBookCount);
             System.out.println("Checkout Total " + checkoutTotal);



             if(overDueBookCount == 3 &&  checkoutTotal == 3)
             {
                 statement = connection.prepareStatement("INSERT INTO Students_Hold(student_hold_id) VALUES(?)");

                 statement.clearParameters();
                 statement.setString(1, student_id_ckout);

                 statement.executeUpdate();

             }
             else if(overDueBookCount == 0 && averageSixMonths > 1)
             {
                 statement = connection.prepareStatement("INSERT INTO Students_Good_Standing(student_Good_id) VALUES(?)");

                 statement.clearParameters();
                 statement.setString(1, student_id_ckout);

                 statement.executeUpdate();
             }

             findStandingHoldRS.first();
*/
     }
}
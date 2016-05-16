package thread;

import handler.DataHandler;

import java.sql.Connection;
import java.sql.SQLException;

import java.util.List;

import javax.swing.SwingWorker;

import ui.frame.Library;
import ui.frame.SplashScreen;

import utility.Utility;

public final class DatabaseWorker extends SwingWorker<Void, Integer> {
    private final SplashScreen fSplashScreen;
    private final int fIsAdmin;
    private int fnTables;
    int curNumCalculations;
    private final DataHandler handler_;
    private final String fSigninEmail;
    
    public DatabaseWorker(final SplashScreen aSplashScreen, 
                          final int aIsAdmin,
                          final DataHandler aHandler,
                          final String aSigninEmail) {
        this.fIsAdmin = aIsAdmin;
        this.fSigninEmail = aSigninEmail;
        this.fSplashScreen = aSplashScreen;
       /* this.fnTables = ((fIsAdmin == 1) ? 16 : 11);*/
        this.fnTables = ((fIsAdmin == 1) ? 15 : 11);
        this.handler_ = aHandler;
        if(handler_.getCountryList() != null)
            fnTables = (fnTables - 3);
    }
    
        @Override protected final Void doInBackground() {
            try (final Connection conn = Utility.getConnection()) {
                 if(handler_.getCountryList() == null) {
                     handler_.setCountryList(conn);
                     publish(calculatePercentage());
                     Thread.sleep(35);
                 }
                 if(handler_.getStateList() == null) {
                     handler_.setStateList(conn);
                     publish(calculatePercentage());    
                     Thread.sleep(35);
                 }
                 if(handler_.getCityList() == null) {
                     handler_.setCityList(conn); 
                     publish(calculatePercentage());    
                     Thread.sleep(35);
                 }    
                 handler_.setAudioMap(conn);
                 publish(calculatePercentage());
                 Thread.sleep(35);
                 handler_.setBookMap(conn);     
                 publish(calculatePercentage()); 
                 Thread.sleep(35);
                 handler_.setStudentMap(conn);
                 publish(calculatePercentage());
                 Thread.sleep(35); 
                 handler_.setFacultyMap(conn);
                 publish(calculatePercentage());
                 Thread.sleep(35); 
                 handler_.setVideoMap(conn);
                 publish(calculatePercentage());
                 Thread.sleep(35);
                 handler_.setCheckoutMap(conn);
                 publish(calculatePercentage());
                 Thread.sleep(35);
                 handler_.setReturnedMap(conn);
                 publish(calculatePercentage());
                 Thread.sleep(35);
                 handler_.setOverdueList(conn);
                 publish(calculatePercentage());
                 Thread.sleep(35);
                 if (fIsAdmin == 1) {
                     handler_.setPaymentsList(conn);
                     publish(calculatePercentage());
                     Thread.sleep(35);
                     handler_.setEmployeeLogList(conn);
                     publish(calculatePercentage());
                     Thread.sleep(35);
                     handler_.setAllLogMap();
                     publish(calculatePercentage()); 
                     Thread.sleep(35);
                     handler_.setEmployeeMap(conn);
                     publish(calculatePercentage());
                     Thread.sleep(35);
                     /*handler_.setEmployeeActivityLogMap(conn);
                     publish(calculatePercentage());
                     Thread.sleep(35);*/
                 }
                 // TODO PROBLEM IN SET ALL LOG MAP EARLY EXIT
            } catch (final SQLException e) {
                Utility.showErrorMessage(null, "DataWorker: Error Accessing Database");
                e.printStackTrace();
            } catch (final InterruptedException e1) {
                e1.printStackTrace();
            }
            return ((Void)null);
        }
                
        
        @Override protected final void process(List<Integer> aChunks) {
            final int size = (aChunks.size() - 1);
            final int currentValue = aChunks.get(size);
            fSplashScreen.setProgressBarStatus(currentValue);
        }
                
        
        @Override protected final void done() {
            fSplashScreen.dispose();
            new Library(handler_, fIsAdmin, fSigninEmail);           
        }
                
        
        private final int calculatePercentage() {
            curNumCalculations++;
            final double percent = (100 * ((float)curNumCalculations/fnTables));
            final int intPercent = (int)(percent + 0.5); // add 0.5 to round
            return intPercent;
        }
}
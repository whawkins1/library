package ui.panel;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;

import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import ui.spinner.DaySpinner;
import ui.spinner.MonthSpinner;
import ui.spinner.YearSpinner;

import utility.Utility;


public final class HireDatePanel extends JPanel {
    private static final long serialVersionUID = 1L;
    private static YearSpinner fYearSpinner;
    private static MonthSpinner fMonthSpinner;
    private static DaySpinner fDaySpinner;
    
    public HireDatePanel(final String aHireDate) {
       super();
       //Configure Preset Dates
       //Current Date for Add
       final Calendar cal = Calendar.getInstance();
       cal.setTime(new Date());
       int year = 0;
       int month = 0;
       int day = 0;
       
       //Set Employee Hire Dates
       if(aHireDate != null) {
           final DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
           Date date = null;
           try {
               date = format.parse(aHireDate);
           } catch(final ParseException e) {
               Utility.showErrorMessage(null, "Error Parsing Hire Date.");
               e.printStackTrace();
           }
           cal.setTime(date);
       } else {
           cal.setTime(new Date());
       }
       year = cal.get(Calendar.YEAR);
       month = (cal.get(Calendar.MONTH) + 1); // Index starts at zero so add 1
       day = cal.get(Calendar.DAY_OF_MONTH);
       buildYearSpinner(year);
       buildMonthSpinner(month);
       buildDaySpinner(day);
    }
    
        private final void buildYearSpinner(final int aYear) {
            final int minYear =  (aYear - 1);
            final SpinnerNumberModel model = new SpinnerNumberModel(aYear, minYear, aYear, 1);
            fYearSpinner = new YearSpinner(model);
            add(fYearSpinner);
        }
        
        
        private final void buildMonthSpinner(final int aMonth) {
            final SpinnerNumberModel model = new SpinnerNumberModel(aMonth, 1, 12, 1);
            model.setValue(aMonth);
            fMonthSpinner = new MonthSpinner(model); 
            add(fMonthSpinner); 
        }
        
        
        private final void buildDaySpinner(final int aDay) {
            final SpinnerNumberModel model = new SpinnerNumberModel(aDay, 1, 31, 1);
            fDaySpinner = new DaySpinner(model);
            add(fDaySpinner);
        }
        
        
        public static void updateDate() {
            final SpinnerNumberModel monthModel = ((SpinnerNumberModel)fMonthSpinner.getModel());
            final Integer monthIntObj = ((Integer)monthModel.getValue());
            final int monthSelected = monthIntObj.intValue();
            
            final SpinnerNumberModel yearModel = ((SpinnerNumberModel)fYearSpinner.getModel());
            final Integer yearIntObj = ((Integer)yearModel.getValue());
            final int yearSelected = yearIntObj.intValue();
        
            final SpinnerNumberModel dayModel = ((SpinnerNumberModel)fDaySpinner.getModel());
            final int maxDaysInt = maxDaysInMonth(yearSelected, monthSelected);
            final Integer maxDays = new Integer(maxDaysInt);
            dayModel.setMaximum(maxDays);
            
            final Integer dayModelIntObj = ((Integer)dayModel.getValue());
            final int daySelected = (dayModelIntObj.intValue());
            
            if(daySelected > maxDaysInt)
                dayModel.setValue(maxDays);
        }
        
        
        private final static int maxDaysInMonth(final int aYear, final int aMonth) {
            final Calendar cal =  Calendar.getInstance();
            cal.set(Calendar.YEAR, aYear);
            cal.set(Calendar.MONTH, aMonth);
            final int max = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
            System.out.println(max);
            return max;
        }
        
        
        public final static String getHireDate() {
            final StringBuilder sb = new StringBuilder();
            appendDate(fYearSpinner, sb);
            appendDate(fMonthSpinner, sb);
            appendDate(fDaySpinner, sb);            
            
            return sb.toString();
        }
        
        
        private final static void appendDate(final JSpinner aSpinner, final StringBuilder aSB) {
            final String delimiter = "-";
            final String date = (aSpinner.getValue().toString());
            aSB.append(date);
            if(!(aSpinner instanceof DaySpinner))
                aSB.append(delimiter);
        }
}
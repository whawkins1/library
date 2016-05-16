package handler;

import inventory.Audio;
import inventory.Book;
import inventory.Video;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;

import payment.Payment;
import list.CheckoutLogMap;
import list.CityList;
import list.CountryList;
import list.EmployeeLogMap;
import list.OverdueList;
import list.PaymentsList;
import list.ReturnedLogMap;
import list.StateList;
import locations.City;
import locations.Country;
import locations.State;
import log.Log;
import map.AudioMap;
import map.BookMap;
import map.EmployeeActivityMap;
import map.EmployeeMap;
import map.FacultyMap;
import map.StudentMap;
import map.VideoMap;
import Personnel.Employee;
import Personnel.Faculty;
import Personnel.Student;

public final class DataHandler {
    private  HashMap<String, Book> bookMap_;
    private  HashMap<String, Student> fStudentMap;
    private  HashMap<String, Faculty> fFacultyMap;
    private  HashMap<String, Employee> fEmployeeMap;
    private  HashMap<String, Audio> fAudioMap;
    private  HashMap<String, Video> fVideoMap;
    private  List<Log> employeeLogMap_;
    private List<Payment> fPaymentList;
    private HashMap<String, Log> employeeActivityMap_;
    private  HashMap<Integer, Log> allLogMap_;
    private HashMap<Integer, Log> checkoutLogMap_;
    private HashMap<Integer, Log> returnedLogMap_;
    private List<Log> fOverdueList;
    private List<Country> countryList_;
    private List<State> stateList_;
    private List<City> cityList_;
    
    public DataHandler() {
        countryList_ = null;
        stateList_ = null;
        cityList_ = null;
    }
        
        public final HashMap<String, Book> getBookMap() { return this.bookMap_; }
        
        
        public final void setBookMap(final Connection aConn) { this.bookMap_ = new BookMap(aConn); }
        
        
        public final HashMap<String, Audio> getAudioMap() { return this.fAudioMap; }
        
        
        public final void setAudioMap(final Connection aConn) { this.fAudioMap = new AudioMap(aConn); }
        
        
        public final HashMap<String, Video> getVideoMap() { return this.fVideoMap; }
        
        
        public final void setVideoMap(final Connection aConn) { this.fVideoMap = new VideoMap(aConn); }
        
        
        public final HashMap<String, Student> getStudentMap() { return this.fStudentMap; }
        
        
        public final void setStudentMap(final Connection aConn) { this.fStudentMap = new StudentMap(aConn);}
        
        
        public final HashMap<String, Faculty> getFacultyMap() { return this.fFacultyMap; }
                
        
        public final void setFacultyMap(final Connection aConn) { this.fFacultyMap = new FacultyMap(aConn); }
                
        
        public final HashMap<String, Employee> getEmployeeMap() { return this.fEmployeeMap; }
        
        
        public final void setEmployeeMap(final Connection aConn) { this.fEmployeeMap = new EmployeeMap(aConn); }
        
        
        public final void setEmployeeMapStartup() { this.fEmployeeMap = new EmployeeMap(); }
        
        
        public final List<Log> getEmployeeLogList() { return this.employeeLogMap_; }
        
        
        public final void setEmployeeLogList(final Connection aConn) { this.employeeLogMap_ = new EmployeeLogMap(aConn); }
        
        
        public final void setEmployeeActivityLogMap(final Connection aConn) { employeeActivityMap_ = new EmployeeActivityMap(aConn); }
        
        
        public final HashMap<String, Log> getEmployeeActivityMap() { return this.employeeActivityMap_; }
        
        
        public final HashMap<Integer, Log> getAllLogMap() { return this.allLogMap_; }    
        
        
        public final void setAllLogMap() {
            this.allLogMap_ = new HashMap<Integer, Log>();
            final HashMap<Integer, Log> tmpMapCheckout = 
                                           new HashMap<Integer, Log>(checkoutLogMap_);
            tmpMapCheckout.keySet().removeAll(allLogMap_.keySet());
            final HashMap<Integer, Log> tmpMapReturned = 
                             new HashMap<Integer, Log>(returnedLogMap_);
            tmpMapReturned.keySet().removeAll(allLogMap_.keySet());
            this.allLogMap_.putAll(tmpMapCheckout);
            this.allLogMap_.putAll(tmpMapReturned);
        }

        
        public final HashMap<Integer, Log> getCheckoutMap() { return this.checkoutLogMap_; }
        
        
        public final void setCheckoutMap(final Connection aConn) { this.checkoutLogMap_ = new CheckoutLogMap(aConn); }
        
        
        public final void setReturnedMap(final Connection aConn) { this.returnedLogMap_ = new ReturnedLogMap(aConn); }
        
        
        public final HashMap<Integer, Log> getReturnedMap() { return this.returnedLogMap_; }
        
        
        public final List<Country> getCountryList() { return this.countryList_; }
        
        
        public final void setCountryList(final Connection aConn) { this.countryList_ = new CountryList(aConn); }
        
        
        public final List<State> getStateList() { return this.stateList_; }
        
        
        public final void setStateList(final Connection aConn) { this.stateList_ = new StateList(aConn); }
        
        
        public final List<City> getCityList() { return this.cityList_; }
        
        
        public final void setCityList(final Connection aConn) { this.cityList_ = new CityList(aConn); }
        
        
        public final void setOverdueList(final Connection aConn) { this.fOverdueList = new OverdueList(aConn); }
        
        
        public final List<Log> getOverdueList() { return this.fOverdueList; }
        
        
        public final void setPaymentsList(final Connection aConn) { this.fPaymentList = new PaymentsList(aConn); }
        
        public final List<Payment> getPaymentList() { return this.fPaymentList; }
}
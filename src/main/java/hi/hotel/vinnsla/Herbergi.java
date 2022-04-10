package hi.hotel.vinnsla;

import java.sql.SQLOutput;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class Herbergi {
    private String typa;
    private int id;
    private int hotelId;
    private ArrayList<ArrayList<LocalDate>> bookedDates = new ArrayList<>();

    public Herbergi(String typa, int id, int hotelId) {
        this.typa = typa;
        this.id = id;
        this.hotelId = hotelId;
        bookedDates = new ArrayList<>();
    }
    public void addDates(LocalDate newCheckin,  LocalDate newCheckout) {
        ArrayList<LocalDate> newDates = new ArrayList <> ();
        newDates.add(newCheckin);
        newDates.add(newCheckout);
        bookedDates.add(newDates);
    }

    public String getTypa() {
        return typa;
    }

    public int getId() {
        return id;
    }

    public ArrayList<ArrayList<LocalDate>> getBookedDates() {
        return bookedDates;
    }

    public boolean hasDateOpen(LocalDate Checkin,  LocalDate Checkout) {
        for(ArrayList h: bookedDates) {
            for(Object j: h) {
                System.out.println("new row");
                if(Checkin.isAfter((LocalDate)h.get(0)) && Checkin.isBefore((LocalDate)h.get(1))) {
                    System.out.println("CheckIn is at a booked time");
                    return false;
                }
                if(Checkout.isAfter((LocalDate)h.get(0)) && Checkout.isBefore((LocalDate)h.get(1))) {
                    System.out.println("CheckOut is at a booked time");
                    return false;
                }
                System.out.println(j.toString());
            }
        }
        return  true;
    }

    public int getHotelId() {
        return hotelId;
    }

}

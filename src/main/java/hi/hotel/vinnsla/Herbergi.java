package hi.hotel.vinnsla;

import java.sql.SQLOutput;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class Herbergi {
    private String typa;
    private int id;
    private int hotelId;
    private ArrayList<Bokun> bookedDates;

    public Herbergi(String typa, int id, int hotelId, ArrayList<Bokun> bookedDates) {
        this.typa = typa;
        this.id = id;
        this.hotelId = hotelId;
        this.bookedDates = bookedDates;
    }
    public void addBooking(Bokun bokun) {
       bookedDates.add(bokun);
    }

    public String getTypa() {
        return typa;
    }

    public int getId() {
        return id;
    }

    public ArrayList<Bokun>  getBookings() {
        return bookedDates;
    }

    public boolean hasDateOpen(LocalDate Checkin,  LocalDate Checkout) {
        for(Bokun bokun: bookedDates) {
                System.out.println("new row");
                if(Checkin.isAfter(bokun.getCheckIn()) && Checkin.isBefore(bokun.getCheckOut())) {
                    System.out.println(bokun.toString());
                    System.out.println("CheckIn is at a booked time");
                    return false;
                }
            if(Checkout.isAfter(bokun.getCheckIn()) && Checkout.isBefore(bokun.getCheckOut())) {
                System.out.println(bokun.toString());
                System.out.println("CheckIn is at a booked time");
                return false;
            }

        }
        return  true;
    }

    public int getHotelId() {
        return hotelId;
    }

}

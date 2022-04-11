package hi.hotel.vinnsla;

import java.time.LocalDate;
import java.util.ArrayList;

public class Herbergi {
    private String typa;
    private int id;
    private int hotelId;
    private ArrayList<Bokun> bookedDates;
    private int pricePerNight;
    private int spaceFor;

    public Herbergi(String typa, int id, int hotelId, ArrayList<Bokun> bookedDates, int pricePerNight, int spaceFor) {
        this.typa = typa;
        this.id = id;
        this.hotelId = hotelId;
        this.bookedDates = bookedDates;
        this.pricePerNight = pricePerNight;
        this.spaceFor = spaceFor;
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

    public int getPricePerNight() {
        return pricePerNight;
    }

    public int getSpaceFor() {
        return spaceFor;
    }

    public ArrayList<Bokun>  getBookings() {
        return bookedDates;
    }

    public boolean hasDateOpen(LocalDate Checkin,  LocalDate Checkout) {
        for(Bokun bokun: bookedDates) {
                if(Checkin.isAfter(bokun.getCheckIn()) && Checkin.isBefore(bokun.getCheckOut())) {
                    return false;
                }
            if(Checkout.isAfter(bokun.getCheckIn()) && Checkout.isBefore(bokun.getCheckOut())) {
                return false;
            }
            if(bokun.getCheckIn().isAfter(Checkin) && bokun.getCheckIn().isBefore(Checkout)) {
                return false;
            }

        }
        return  true;
    }

    public int getHotelId() {
        return hotelId;
    }

}

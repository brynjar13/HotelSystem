package hi.hotel.vinnsla;

import java.time.LocalDate;
import java.util.ArrayList;
/**********************************************************
 *
 *   Hópur: 2H
 *
 *   Lýsing: Klasi sem geymir upplýsingar um hvert herbergi
 *   í forritinu.
 *
 **********************************************************/
public class Room {
    private String typa;
    private int id;
    private int hotelId;
    private ArrayList<Booking> bookedDates;
    private int pricePerNight;
    private int spaceFor;

    public Room(String typa, int id, int hotelId, ArrayList<Booking> bookedDates, int pricePerNight, int spaceFor) {
        this.typa = typa;
        this.id = id;
        this.hotelId = hotelId;
        this.bookedDates = bookedDates;
        this.pricePerNight = pricePerNight;
        this.spaceFor = spaceFor;
    }

    /**
     * Bætir bókun við herbergi
     * @param booking
     */
    public void addBooking(Booking booking) {
       bookedDates.add(booking);
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

    @Override
    public String toString() {
        return "Herbergi{" +
                "typa='" + typa + '\'' +
                ", id=" + id +
                ", hotelId=" + hotelId +
                ", pricePerNight=" + pricePerNight +
                ", spaceFor=" + spaceFor +
                '}';
    }

    public int getSpaceFor() {
        return spaceFor;
    }

    public ArrayList<Booking>  getBookings() {
        return bookedDates;
    }

    /**
     * Fall sem finnur hvort herbergi sé bókað á völdum dagsetningum.
     * Skilar true ef það er ekki bókað á völdum dagsetningum, annars false
     * @param Checkin
     * @param Checkout
     * @return
     */
    public boolean hasDateOpen(LocalDate Checkin,  LocalDate Checkout) {
        for(Booking booking : bookedDates) {
                if(Checkin.isAfter(booking.getCheckIn()) && Checkin.isBefore(booking.getCheckOut())) {
                    return false;
                }
            if(Checkin.isEqual(booking.getCheckIn()) && Checkout.isEqual(booking.getCheckOut())) {
                return false;
            }
            if(booking.getCheckIn().isAfter(Checkin) && booking.getCheckIn().isBefore(Checkout)) {
                return false;
            }
            if(Checkin.isEqual(booking.getCheckIn()) && Checkout.isBefore(booking.getCheckOut())) {
                return false;
            }
            if(Checkin.isEqual(booking.getCheckIn()) && Checkout.isAfter(booking.getCheckOut())) {
                return false;
            }
            if(Checkin.isBefore(booking.getCheckIn()) && Checkout.isEqual(booking.getCheckOut())) {
                return false;
            }

        }
        return  true;
    }

    public int getHotelId() {
        return hotelId;
    }

}

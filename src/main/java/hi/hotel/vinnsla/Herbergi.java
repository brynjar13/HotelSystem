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

    /**
     * Bætir bókun við herbergi
     * @param bokun
     */
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

    public ArrayList<Bokun>  getBookings() {
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
        for(Bokun bokun: bookedDates) {
                if(Checkin.isAfter(bokun.getCheckIn()) && Checkin.isBefore(bokun.getCheckOut())) {
                    return false;
                }
            if(Checkin.isEqual(bokun.getCheckIn()) && Checkout.isEqual(bokun.getCheckOut())) {
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

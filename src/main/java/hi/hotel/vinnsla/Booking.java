package hi.hotel.vinnsla;

import java.time.LocalDate;
import java.util.UUID;
/**********************************************************
 *
 *   Hópur: 2H
 *
 *   Lýsing: Klasi sem geymir upplýsingar um hverja bókun
 *   í forritinu.
 *
 **********************************************************/
public class Booking {
    private int hotelId;
    private int herbergiId;
    private Person person;
    private LocalDate checkIn;
    private LocalDate checkOut;
    private UUID bookingnumber;


    public Booking(int hotelId, int herbergiId, Person person, LocalDate checkIn, LocalDate checkOut, UUID bookingnumber) {
        this.hotelId = hotelId;
        this.herbergiId = herbergiId;
        this.person = person;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        if(bookingnumber == null) {
            this.bookingnumber = UUID.randomUUID();
        }else {
            this.bookingnumber = bookingnumber;
        }

    }

    public int getHerbergi() {
        return herbergiId;
    }


    public UUID getBookingnumber() {
        return bookingnumber;
    }


    public LocalDate getCheckIn() {
        return checkIn;
    }


    public LocalDate getCheckOut() {
        return checkOut;
    }


    public int getHotelId() {
        return hotelId;
    }

    @Override
    public String toString() {
        return "Bokun{" +
                "hotelId=" + hotelId +
                ", herbergi=" + herbergiId +
                ", persona=" + person +
                ", checkIn=" + checkIn +
                ", checkOut=" + checkOut +
                '}';
    }
}

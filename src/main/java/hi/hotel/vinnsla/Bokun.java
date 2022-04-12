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
public class Bokun {
    private int hotelId;
    private int herbergiId;
    private Persona persona;
    private LocalDate checkIn;
    private LocalDate checkOut;
    private UUID bookingnumber;


    public Bokun(int hotelId,int herbergiId, Persona  persona, LocalDate checkIn, LocalDate checkOut, UUID bookingnumber) {
        this.hotelId = hotelId;
        this.herbergiId = herbergiId;
        this.persona = persona;
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

    public void setHerbergi(int herbergiId) {
        this.herbergiId = herbergiId;
    }

    public Persona getPersona() {
        return persona;
    }

    public UUID getBookingnumber() {
        return bookingnumber;
    }

    public void setPersona(Persona  persona) {
        this.persona = persona;
    }

    public LocalDate getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(LocalDate checkIn) {
        this.checkIn = checkIn;
    }

    public LocalDate getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(LocalDate checkOut) {
        this.checkOut = checkOut;
    }

    public int getHotelId() {
        return hotelId;
    }

    @Override
    public String toString() {
        return "Bokun{" +
                "hotelId=" + hotelId +
                ", herbergi=" + herbergiId +
                ", persona=" + persona +
                ", checkIn=" + checkIn +
                ", checkOut=" + checkOut +
                '}';
    }
}

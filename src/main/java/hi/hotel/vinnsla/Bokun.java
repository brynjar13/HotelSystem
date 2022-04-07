package hi.hotel.vinnsla;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;

public class Bokun {
    private int hotelId;
    private int herbergiId;
    private ArrayList<Persona> personur;
    private LocalDate checkIn;
    private LocalDate checkOut;
    private UUID bookingnumber;


    public Bokun(int hotelId,int herbergiId, ArrayList<Persona>  personur, LocalDate checkIn, LocalDate checkOut, UUID bookingnumber) {
        this.hotelId = hotelId;
        this.herbergiId = herbergiId;
        this.personur = personur;
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

    public ArrayList<Persona> getPersonur() {
        return personur;
    }

    public UUID getBookingnumber() {
        return bookingnumber;
    }

    public void setPersonur(ArrayList<Persona>  personur) {
        this.personur = personur;
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

    @Override
    public String toString() {
        return "Bokun{" +
                "hotelId=" + hotelId +
                ", herbergi=" + herbergiId +
                ", personur=" + personur +
                ", checkIn=" + checkIn +
                ", checkOut=" + checkOut +
                '}';
    }
}

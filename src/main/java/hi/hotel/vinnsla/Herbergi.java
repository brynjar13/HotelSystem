package hi.hotel.vinnsla;

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
    private void addDates(LocalDate newCheckin,  LocalDate newCheckout) {
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

    public int getHotelId() {
        return hotelId;
    }

}

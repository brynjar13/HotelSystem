package hi.hotel.vinnsla;

import java.util.ArrayList;
import java.util.Date;

public class Herbergi {
    private String typa;
    private int id;
    private ArrayList<ArrayList<Date>> bookedDates = new ArrayList<>();

    public Herbergi(String typa, int id) {
        this.typa = typa;
        this.id = id;
        bookedDates = new ArrayList<>();
    }
    private void addDates(Date newCheckin,  Date newCheckout) {
        ArrayList<Date> newDates = new ArrayList <> ();
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

    public ArrayList<ArrayList<Date>> getBookedDates() {
        return bookedDates;
    }
}

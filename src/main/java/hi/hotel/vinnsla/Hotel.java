package hi.hotel.vinnsla;

import java.util.ArrayList;
/**********************************************************
 *
 *   Hópur: 2H
 *
 *   Lýsing: Klasi sem geymir upplýsingar um hvert hótel
 *   í forritinu.
 *
 **********************************************************/

public class Hotel {
    private ArrayList<Herbergi> herbergi;
    private int id;
    private String name;
    private int numberOfRooms;
    private String town;
    private String area;
    private boolean breakfastIncluded;
    private String contactNumber;
    private String contactEmail;

    public Hotel(ArrayList<Herbergi> herbergi, int id, String name, int numberOfRooms, String town, String area, boolean breakfastIncluded, String contactnumber, String contactEmail) {
        this.herbergi = herbergi;
        this.id = id;
        this.name = name;
        this.numberOfRooms = numberOfRooms;
        this.town = town;
        this.area =area;
        this.breakfastIncluded = breakfastIncluded;
        this.contactNumber = contactnumber;
        this.contactEmail = contactEmail;
    }

    /**
     * Skilar true ef matur er innifalinn í verðinu á hótelinu, annars false
     * @return
     */
    public boolean isBreakfastIncluded() {
        return breakfastIncluded;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public Herbergi getHerbergi(int i) {
        return (herbergi.get(i));
    }

    public ArrayList<Herbergi> getHerbergis() {
        return herbergi;
    }

    public String getArea() {
        return area;
    }

    public String getTown() {
        return town;
    }

    public int getNumberOfRooms() {
        return numberOfRooms;
    }


    @Override
    public String toString() {
        return name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}

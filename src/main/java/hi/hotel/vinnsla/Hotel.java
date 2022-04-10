package hi.hotel.vinnsla;

import java.util.ArrayList;

public class Hotel {
    private ArrayList<Herbergi> herbergi;
    private int id;
    private String name;
    private int numberOfRooms;
    private String town;
    private String area;
    private boolean breakfastIncluded;

    public Hotel(ArrayList<Herbergi> herbergi, int id, String name, int numberOfRooms, String town, String area, boolean breakfastIncluded) {
        this.herbergi = herbergi;
        this.id = id;
        this.name = name;
        this.numberOfRooms = numberOfRooms;
        this.town = town;
        this.area =area;
        this.breakfastIncluded = breakfastIncluded;
    }

    public boolean isBreakfastIncluded() {
        return breakfastIncluded;
    }

    public Herbergi getHerbergi(int i) {
        return (herbergi.get(i));
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

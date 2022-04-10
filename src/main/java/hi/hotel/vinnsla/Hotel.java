package hi.hotel.vinnsla;

import java.util.ArrayList;

public class Hotel {
    private ArrayList<Herbergi> herbergi;
    // add Reviews;
    private int id;
    private String name;
    private int numberOfRooms;

    public Hotel(ArrayList<Herbergi> herbergi, int id, String name, int numberOfRooms) {
        this.herbergi = herbergi;
        this.id = id;
        this.name = name;
        this.numberOfRooms = numberOfRooms;
    }

    public Herbergi getHerbergi(int i) {
        return (herbergi.get(i));
    }

    public ArrayList<Herbergi> getHerbergis() {
        return herbergi;
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

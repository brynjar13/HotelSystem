package hi.hotel.database;


import hi.hotel.vinnsla.Herbergi;
import hi.hotel.vinnsla.Hotel;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class HotelFile {
    public ArrayList<Hotel> hotels = new ArrayList<>();
    public ArrayList<Herbergi> herbergis = new ArrayList<>();

    public HotelFile() {
        makeHotels();
    }

    private void makeRooms() {
        File file = new File("herbergi.txt");
        Scanner scanner = null;
        try {
            scanner = new Scanner(file).useDelimiter(";");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while (scanner.hasNext()) {
            String[] kol = scanner.next().split(",");
            herbergis.add(new Herbergi(kol[0], Integer.parseInt(kol[1]), Integer.parseInt(kol[2])));
        }
    }

    private void makeHotels() {
        makeRooms();
        File file = new File("Hotels.txt");
        Scanner scanner = null;
        try {
            scanner = new Scanner(file).useDelimiter(";");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while (scanner.hasNext()) {
            ArrayList<Herbergi> hotelherbergi= new ArrayList<>();
            String[] kol = scanner.next().split(",");
            for (int i = 0; i < herbergis.size(); i++) {
                    if (herbergis.get(i).getHotelId() == Integer.parseInt(kol[0])) {
                    hotelherbergi.add(herbergis.get(i));
                }
            }
            System.out.println(hotelherbergi.size());
            hotels.add(new Hotel(hotelherbergi ,Integer.parseInt(kol[0]),kol[1], Integer.parseInt(kol[2])));
        }
    }
}

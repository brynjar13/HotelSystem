package hi.hotel.database;


import hi.hotel.vinnsla.Booking;
import hi.hotel.vinnsla.Room;
import hi.hotel.vinnsla.Hotel;
import hi.hotel.vinnsla.Person;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;
/**********************************************************
 *
 *   Hópur: 2H
 *
 *   Lýsing: Klasi sem sækir allar upplýsingar um bókanir,
 *   gesti,hótel og herbergi úr gagnagrunni, og býr til
 *   instance af klösunum þeirra.
 *
 **********************************************************/
public class HotelFile {
    public ArrayList<Hotel> hotels = new ArrayList<>();
    public ArrayList<Room> rooms = new ArrayList<>();
    public ArrayList<Booking> bokanir = new ArrayList<>();
    public ArrayList<Person> personur = new ArrayList<>();

    public HotelFile() {
        makeHotels();
    }

    /**
     * Tekur upplýsingar úr Rooms.txt og býr til
     * instance af Herbergi klasanum fyrir hverja línu í skránni
     */
    private void makeRooms() {
        makeBookings();
        File file = new File("Rooms.txt");
        Scanner scanner = null;
        try {
            scanner = new Scanner(file).useDelimiter("\r\n");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while (scanner.hasNext()) {
            String[] kol = scanner.next().split(",");
            ArrayList<Booking> bokanirHerbergi= new ArrayList<>();

            for (int i = 0; i < bokanir.size(); i++) {
                if (bokanir.get(i).getHerbergi() == Integer.parseInt(kol[1])) {
                    bokanirHerbergi.add(bokanir.get(i));
                }
            }
            Room room = new Room(kol[0], Integer.parseInt(kol[1]), Integer.parseInt(kol[2]), bokanirHerbergi, Integer.parseInt(kol[3]), Integer.parseInt(kol[4]));
            rooms.add(room);
        }
    }
    /**
     * Tekur upplýsingar úr Hotels.txt og býr til
     * instance af Hotel klasanum fyrir hverja línu í skránni
     */
    private void makeHotels() {
        makeRooms();
        File file = new File("Hotels.txt");
        Scanner scanner = null;
        try {
            scanner = new Scanner(file).useDelimiter("\r\n");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while (scanner.hasNext()) {
            ArrayList<Room> hotelherbergi= new ArrayList<>();
            String[] kol = scanner.next().split(",");
            for (int i = 0; i < rooms.size(); i++) {
                    if (rooms.get(i).getHotelId() == Integer.parseInt(kol[0])) {
                    hotelherbergi.add(rooms.get(i));
                }
            }
            hotels.add(new Hotel(hotelherbergi ,Integer.parseInt(kol[0]),kol[1], Integer.parseInt(kol[2]), kol[3], kol[4], Boolean.parseBoolean(kol[5]), kol[6], kol[7]));
        }
    }
    /**
     * Tekur upplýsingar úr Bookings.txt og býr til
     * instance af Bokun klasanum fyrir hverja línu í skránni
     */
    public void makeBookings() {
        makePersons();
        File file = new File("Bookings.txt");
        Scanner scanner = null;
        try {
            scanner = new Scanner(file).useDelimiter("\r\n");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while (scanner.hasNext()) {
            String[] kol = scanner.next().split(",");
            Person personInBooking= null;
            LocalDate ci = LocalDate.parse(kol[3]);
            LocalDate co = LocalDate.parse(kol[4]);
            UUID bookingNum = UUID.fromString(kol[5]);
            for (int i = 0; i < personur.size(); i++) {
                if (personur.get(i).getBookingId().toString().equals(bookingNum.toString())) {
                    personInBooking = personur.get(i);
                }
            }
            bokanir.add(new Booking(Integer.parseInt(kol[0]), Integer.parseInt(kol[1]),personInBooking, ci,co,bookingNum));
        }
    }

    /**
     * Tekur upplýsingar úr Persons.txt og býr til
     * instance af Persona klasanum fyrir hverja línu í skránni
     */
    public void makePersons() {
        File file = new File("Persons.txt");
        Scanner scanner = null;
        try {
            scanner = new Scanner(file).useDelimiter("\r\n");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while (scanner.hasNext()) {
            String[] kol = scanner.next().split(",");
            if(kol.length != 4) {
                break;
            }
            personur.add(new Person(kol[0], kol[1], kol[2], UUID.fromString(kol[3])));
        }
    }
}

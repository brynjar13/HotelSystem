package hi.hotel.database;


import hi.hotel.vinnsla.Bokun;
import hi.hotel.vinnsla.Herbergi;
import hi.hotel.vinnsla.Hotel;
import hi.hotel.vinnsla.Persona;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;

public class HotelFile {
    public ArrayList<Hotel> hotels = new ArrayList<>();
    public ArrayList<Herbergi> herbergis = new ArrayList<>();
    public ArrayList<Bokun> bokanir = new ArrayList<>();
    public ArrayList<Persona> personur = new ArrayList<>();

    public HotelFile() {
        makeHotels();
    }

    private void makeRooms() {
        makeBookings();
        File file = new File("herbergi.txt");
        Scanner scanner = null;
        try {
            scanner = new Scanner(file).useDelimiter(";");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while (scanner.hasNext()) {
            String[] kol = scanner.next().split(",");
            ArrayList<Bokun> bokanirHerbergi= new ArrayList<>();

            for (int i = 0; i < bokanir.size(); i++) {
                if (bokanir.get(i).getHerbergi() == Integer.parseInt(kol[1])) {
                    bokanirHerbergi.add(bokanir.get(i));
                }
            }
            Herbergi herbergi = new Herbergi(kol[0], Integer.parseInt(kol[1]), Integer.parseInt(kol[2]), bokanirHerbergi);
            herbergis.add(herbergi);
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
    private void makeBookings() {
        makePersons();
        File file = new File("Bookings.txt");
        Scanner scanner = null;
        try {
            scanner = new Scanner(file).useDelimiter(";");
        } catch (
                FileNotFoundException e) {
            e.printStackTrace();
        }
        while (scanner.hasNext()) {
            String[] kol = scanner.next().split(",");
            ArrayList<Persona> personsInBooking= new ArrayList<>();
            LocalDate ci = LocalDate.parse(kol[3]);
            LocalDate co = LocalDate.parse(kol[4]);
            UUID bookingNum = UUID.fromString(kol[5]);
            for (int i = 0; i < personur.size(); i++) {
                if (personur.get(i).getBookingId().toString().equals(bookingNum.toString())) {
                    personsInBooking.add(personur.get(i));
                }
            }
            bokanir.add(new Bokun(Integer.parseInt(kol[0]), Integer.parseInt(kol[1]),personsInBooking, ci,co,bookingNum));
        }
    }
    public void makePersons() {
        File file = new File("Personur.txt");
        Scanner scanner = null;
        try {
            scanner = new Scanner(file).useDelimiter(";");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while (scanner.hasNext()) {
            String[] kol = scanner.next().split(",");
            if(kol.length != 4) {
                break;
            }
            personur.add(new Persona(kol[0], kol[1], kol[2],UUID.fromString(kol[3])));
        }
    }
}

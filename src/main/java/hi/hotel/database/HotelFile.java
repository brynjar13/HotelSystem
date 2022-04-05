package hi.hotel.database;


import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class HotelFile {
    // public ArrayList<Hotel> hotels = new ArrayList<>();

    public HotelFile() {
        File file = new File("Hotels.txt");
        Scanner scanner = null;
        try {
            scanner = new Scanner(file).useDelimiter(";");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while (scanner.hasNext()) {
            String[] kol = scanner.next().split(" ");
            // hotels.add(new Hotel(kol[0], kol[1]));
        }
    }
}

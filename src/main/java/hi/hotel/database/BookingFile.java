package hi.hotel.database;

import hi.hotel.vinnsla.Bokun;
import hi.hotel.vinnsla.Persona;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;

public class BookingFile {
    public ArrayList<Bokun> bokanir = new ArrayList<>();
    public ArrayList<Persona> personur = new ArrayList<>();
    public BookingFile() {
        makePersons();

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

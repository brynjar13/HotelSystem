package hi.hotel.vidmot;

import hi.hotel.database.BookingFile;
import hi.hotel.vinnsla.Bokun;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class BookingController implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        BookingFile bookingFile = new BookingFile();
        ArrayList<Bokun> bokanir = bookingFile.bokanir;
        System.out.println(bokanir);
        for(Bokun b:bokanir) {
            System.out.println(b);
        }
    }
}

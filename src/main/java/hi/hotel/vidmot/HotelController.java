package hi.hotel.vidmot;

import hi.hotel.database.HotelFile;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class HotelController implements Initializable {
    HotelFile hotelFile;
    // ArrayList<Hotel> hotels;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        hotelFile = new HotelFile();
        // hotels = hotelFile.hotels;
    }
}
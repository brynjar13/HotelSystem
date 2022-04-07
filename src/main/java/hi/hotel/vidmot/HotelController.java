package hi.hotel.vidmot;

import hi.hotel.database.BookingFile;
import hi.hotel.database.HotelFile;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import hi.hotel.vinnsla.Bokun;
import hi.hotel.vinnsla.Herbergi;
import hi.hotel.vinnsla.Hotel;
import hi.hotel.vinnsla.Persona;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class HotelController implements Initializable {
    HotelFile hotelFile;
    ArrayList<Hotel> hotels;

    @FXML
    private TextField textInput;
    @FXML
    private ListView hotelList;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        hotelFile = new HotelFile();
        hotels = hotelFile.hotels;
        BookingFile bookingFile = new BookingFile();
        ArrayList<Bokun> bokanir = bookingFile.bokanir;
        System.out.println(bokanir);
        for(Bokun b:bokanir) {
            System.out.println("hallo" + b);
        }
    }

    /**
     * Filterum hótel eftir inputtinu í textfieldinu
     * @param ActionEvent
     */
    public void search(ActionEvent ActionEvent) {
        ArrayList<Hotel> tempHotelList = new ArrayList<>();
        String input = textInput.getText();
        for(Hotel h: hotels) {
            if(h.getName().contains(input)) {
                tempHotelList.add(h);
            }
        }
        ObservableList<Hotel> oHotelList = FXCollections.observableArrayList(tempHotelList);
        hotelList.setItems(oHotelList);
    }
}
package hi.hotel.vidmot;

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
    // ArrayList<Hotel> hotels;

    @FXML
    private TextField textInput;
    @FXML
    private ListView hotelList;
    ArrayList<Hotel> Hotellist = new ArrayList<>();
    ObservableList<Hotel> t = FXCollections.observableArrayList();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        hotelFile = new HotelFile();
        // hotels = hotelFile.hotels;
        Herbergi h = new Herbergi("basic", 1);
        Herbergi l = new Herbergi("svíta", 2);
        ArrayList<Herbergi> herbergi = new ArrayList<>();
        herbergi.add(h);
        herbergi.add(l);
        Hotel testHotel = new Hotel(herbergi,1,"Hótel Edda", 4);
        Hotel testHotel2 = new Hotel(herbergi,2,"Hótel Borg", 4);
        Hotel testHotel3 = new Hotel(herbergi,3,"Motel h", 4);
        Hotel testHotel4 = new Hotel(herbergi,4,"Hótel e", 4);
        Hotellist.add(testHotel);
        Hotellist.add(testHotel2);
        Hotellist.add(testHotel3);
        Hotellist.add(testHotel4);
        ArrayList<Persona> persons = new ArrayList<>();
        Persona person = new Persona("Hermann", "hemmi@example.com", "123456");
        persons.add(person);
        LocalDate ci = LocalDate.of(2021,1,1);
        LocalDate co = LocalDate.of(2021,1,3);
        Bokun bok = new Bokun(testHotel.getId(), testHotel.getHerbergi(1), persons,ci,co );


    }

    /**
     * Filterum hótel eftir inputtinu í textfieldinu
     * @param ActionEvent
     */
    public void search(ActionEvent ActionEvent) {
        ArrayList<Hotel> tempHotelList = new ArrayList<>();
        String input = textInput.getText();
        for(Hotel h: Hotellist) {
            System.out.println(h);
            if(h.getName().contains(input)) {
                tempHotelList.add(h);
            }
        }
        ObservableList<Hotel> oHotelList = FXCollections.observableArrayList(tempHotelList);
        hotelList.setItems(oHotelList);
    }
}
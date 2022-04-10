package hi.hotel.vidmot;

import hi.hotel.database.BookingFile;
import hi.hotel.database.HotelFile;
import javafx.event.ActionEvent;
import javafx.fxml.*;
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
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.UUID;

public class HotelController implements Initializable {
    HotelFile hotelFile;
    ArrayList<Hotel> hotels;
    ArrayList<Bokun> bokanir;
    @FXML
    private Button searchBookingBtn;
    @FXML
    private TextField textInput;
    @FXML
    private TextField bookingSearch;
    @FXML
    private DatePicker checkInDate;
    @FXML
    private DatePicker checkOutDate;
    @FXML
    private ListView hotelList;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        hotelFile = new HotelFile();
        hotels = hotelFile.hotels;
        bokanir = hotelFile.bokanir;
        Herbergi herbergi = hotels.get(1).getHerbergi(1);
        // herbergi.addDates(LocalDate.of(2021,05,06),LocalDate.of(2021,05,10) );
        // herbergi.addDates(LocalDate.of(2021,07,06),LocalDate.of(2021,07,11) );

        // boolean l = hotels.get(1).getHerbergi(1).hasDateOpen(LocalDate.of(2021,05,03),LocalDate.of(2021,05,7));

    }

    /**
     * Filterum hótel eftir inputtinu í textfieldinu
     * @param ActionEvent
     */
    public void search(ActionEvent ActionEvent)  {
        LocalDate checkIn = checkInDate.getValue();
        LocalDate checkOut = checkOutDate.getValue();
        System.out.println(checkIn + " " + checkOut);
        ArrayList<Hotel> tempHotelList = new ArrayList<>();
        String input = textInput.getText();
        for(Hotel h: hotels) {
            if(h.getName().contains(input)) {
                for(int i = 0; i<(h.getNumberOfRooms()); i++) {
                    if (h.getHerbergi(i).hasDateOpen(checkIn, checkOut)) {
                        tempHotelList.add(h);
                    }
                }
            }
        }
        ObservableList<Hotel> oHotelList = FXCollections.observableArrayList(tempHotelList);
        hotelList.setItems(oHotelList);
    }
    public void makeBooking(ActionEvent ActionEvent) throws IOException {
        String bookingNumber = bookingSearch.getText();
        UUID bookingNrtoUUID;
        try {
            bookingNrtoUUID = UUID.fromString(bookingNumber);
        }
        catch (Exception e) {
            return;
        }

        for(Bokun b: bokanir) {
            if(b.getBookingnumber().equals(bookingNrtoUUID)) {
            }
        }
        FXMLLoader fxmlLoader = new FXMLLoader(HotelApplication.class.getResource("bokun.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 500, 500);
        BookingController sc =  fxmlLoader.getController();
        Stage stage = (Stage) searchBookingBtn.getScene().getWindow();
        stage.setTitle("Booking");
        stage.setScene(scene);
        stage.show();
    }
}
package hi.hotel.vidmot;

import hi.hotel.database.HotelFile;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import hi.hotel.vinnsla.Bokun;
import hi.hotel.vinnsla.Herbergi;
import hi.hotel.vinnsla.Hotel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
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
    private String hotelInfoName;
    private int hotelInfoId;
    private ArrayList<Herbergi> hotelInfoHerbergi;

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
    @FXML
    private ChoiceBox searchByTownChoiceBox;
    @FXML
    private ChoiceBox searchByAreaChoiceBox;
    @FXML
    private ChoiceBox breakfastChoiceBox;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        hotelFile = new HotelFile();
        hotels = hotelFile.hotels;
        bokanir = hotelFile.bokanir;
        ObservableList<String> choiceBoxTownItems = FXCollections.observableArrayList();
        ObservableList<String> choiceBoxAreaItems = FXCollections.observableArrayList();
        choiceBoxTownItems.add("");
        choiceBoxAreaItems.add("");
        for(Hotel hotel:hotels) {
            if(!choiceBoxTownItems.contains(hotel.getTown())) {
                choiceBoxTownItems.add(hotel.getTown());
            }
            if(!choiceBoxAreaItems.contains(hotel.getArea())) {
                choiceBoxAreaItems.add(hotel.getArea());
            }
        }

        searchByTownChoiceBox.setItems(choiceBoxTownItems);
        searchByAreaChoiceBox.setItems(choiceBoxAreaItems);
        breakfastChoiceBox.setItems(FXCollections.observableArrayList("Morgunmatur innifalinn", "Já", "Nei"));

    }

    /**
     * Filterum hótel eftir inputtinu í textfieldinu
     * @param ActionEvent
     */
    public void search(ActionEvent ActionEvent)  {
        LocalDate checkIn = checkInDate.getValue();
        LocalDate checkOut = checkOutDate.getValue();
        if(checkIn == null || checkOut == null) {
            return;
        }
        String townSelected = null;
        String areaSelected = null;
        if(searchByTownChoiceBox.getValue() != null && searchByTownChoiceBox.getValue() != "") {
            townSelected = searchByTownChoiceBox.getValue().toString();
        }
        if( searchByAreaChoiceBox.getValue() != null && searchByAreaChoiceBox.getValue() != "") {
            areaSelected = searchByAreaChoiceBox.getValue().toString();
        }
        ArrayList<Hotel> tempHotelList = new ArrayList<>();
        String input = textInput.getText();
        boolean hasOpenRoom = false;
        for(Hotel h: hotels) {
            if(h.getName().contains(input)) {
                if((townSelected!= null) &&!townSelected.equals(h.getTown())) {
                    continue;
                }
                if((areaSelected!= null) && (!areaSelected.equals(h.getArea()))) {
                    continue;
                }
                if(breakfastChoiceBox.getValue() != null && !breakfastChoiceBox.getValue().toString().equals("Morgunmatur innifalinn"))  {
                    boolean bool;
                    if(breakfastChoiceBox.getValue().toString().equals("Já")) {
                        bool = true;
                    }else {
                        bool = false;
                    }
                    if(bool != h.isBreakfastIncluded()) {
                        continue;
                    }
                }
                for(int i = 0; i<(h.getNumberOfRooms()); i++) {
                    if (h.getHerbergi(i).hasDateOpen(checkIn, checkOut)) {
                        hasOpenRoom = true;
                    }
                }
                if(hasOpenRoom) {
                    tempHotelList.add(h);
                }
                hasOpenRoom = false;
            }
        }
        ObservableList<Hotel> oHotelList = FXCollections.observableArrayList(tempHotelList);
        hotelList.setItems(oHotelList);
    }

    @FXML
    public void clickHotel(MouseEvent e) throws IOException {
        if (!e.getTarget().getClass().getName().toString().equals("com.sun.javafx.scene.control.LabeledText")) {
            e.consume();
            return;
        }
        Text hotel = (Text) e.getTarget();
        for (Hotel rightHotel :
                hotels) {
            if (rightHotel.getName().equals(hotel.getText())) {
                hotelInfoName = rightHotel.getName();
                hotelInfoId = rightHotel.getId();
                hotelInfoHerbergi = rightHotel.getHerbergis();
            }
        }
        FXMLLoader fxmlLoader = new FXMLLoader(HotelApplication.class.getResource("hotel.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 700, 700);
        HotelViewController hvc = fxmlLoader.getController();
        hvc.setHotelName(hotelInfoName);
        hvc.setHotelId(hotelInfoId);
        hvc.setHerbergis(hotelInfoHerbergi);
        Stage stage = (Stage) hotelList.getScene().getWindow();
        scene.setUserData(fxmlLoader.getController());
        stage.setTitle("Hotel");
        stage.setScene(scene);
        stage.show();
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
        sc.setBokunId(bookingNrtoUUID);
        stage.setTitle("Booking");
        stage.setScene(scene);
        stage.show();
    }
}
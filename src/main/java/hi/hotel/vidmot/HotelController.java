package hi.hotel.vidmot;

import hi.hotel.database.HotelFile;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import hi.hotel.vinnsla.Booking;
import hi.hotel.vinnsla.Room;
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
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.UUID;
/**********************************************************
 *
 *   Hópur: 2H
 *
 *   Lýsing: Controllerklasinn fyrir opnunarsíðu
 *   verkefnsins, tengt við frontpage.fxml.
 *
 **********************************************************/
public class HotelController implements Initializable {
    HotelFile hotelFile;
    ArrayList<Hotel> hotels;
    private String hotelInfoName;
    private int hotelInfoId;
    private ArrayList<Room> hotelInfoRoom;

    ArrayList<Booking> bokanir;
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
    @FXML
    private ChoiceBox fxRequiredSpace;
    @FXML
    private Label fxValidDates;

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
        breakfastChoiceBox.setItems(FXCollections.observableArrayList("", "Já", "Nei"));
        fxRequiredSpace.setItems(FXCollections.observableArrayList("",1,2,3,4));
        fxValidDates.setVisible(false);

    }

    /**
     * Filterum hótel eftir inputtinu í textfieldinu, og/eða inputtunum í choiceboxunum
     * Sýnum svo hótel sem uppfylla leitarskilyrðin
     * @param ActionEvent
     */
    public void search(ActionEvent ActionEvent)  {
        fxValidDates.setVisible(false);
        LocalDate checkIn = checkInDate.getValue();
        LocalDate checkOut = checkOutDate.getValue();
        boolean isValidDates = isValidDatesChosen();
        if(!isValidDates) {
            fxValidDates.setVisible(true);
            return;
        }
        String townSelected = null;
        String areaSelected = null;
        int spaceFor = 0;
        if(fxRequiredSpace.getValue() != null && fxRequiredSpace.getValue() != "") {
            spaceFor = (int) fxRequiredSpace.getValue();
        }
        if(searchByTownChoiceBox.getValue() != null && searchByTownChoiceBox.getValue() != "") {
            townSelected = searchByTownChoiceBox.getValue().toString();
        }
        if( searchByAreaChoiceBox.getValue() != null && searchByAreaChoiceBox.getValue() != "") {
            areaSelected = searchByAreaChoiceBox.getValue().toString();
        }
        ArrayList<Hotel> tempHotelList = new ArrayList<>();
        String input = textInput.getText();
        int availableRooms = 0;
        for(Hotel h: hotels) {
            if(h.getName().contains(input)) {
                if((townSelected!= null) &&!townSelected.equals(h.getTown())) {
                    continue;
                }
                if((areaSelected!= null) && (!areaSelected.equals(h.getArea()))) {
                    continue;
                }
                if(breakfastChoiceBox.getValue() != null && !breakfastChoiceBox.getValue().toString().equals(""))  {
                    boolean bool;
                    bool = breakfastChoiceBox.getValue().toString().equals("Já");
                    if(bool != h.isBreakfastIncluded()) {
                        continue;
                    }
                }
                for(int i = 0; i<(h.getNumberOfRooms()); i++) {
                    if (h.getHerbergi(i).hasDateOpen(checkIn, checkOut)) {
                        availableRooms++;
                    }
                    if(!(h.getHerbergi(i).getSpaceFor() >= spaceFor)) {
                        availableRooms--;
                    }
                }
                if(availableRooms >0) {
                    tempHotelList.add(h);
                }
                availableRooms = 0;
            }
        }
        ObservableList<Hotel> oHotelList = FXCollections.observableArrayList(tempHotelList);
        hotelList.setItems(oHotelList);
    }

    /**
     * Athugar hvort dagsetningar valdar í Datepickerunum séu gildar.
     * @return
     */
    public boolean isValidDatesChosen() {
        LocalDate checkIn = checkInDate.getValue();
        LocalDate checkOut = checkOutDate.getValue();
        if(checkIn == null || checkOut == null) {
            return false;
        }
        LocalDate cin = checkInDate.getValue();
        LocalDate cout = checkOutDate.getValue();
        long days= ChronoUnit.DAYS.between(cin,cout);
        if(days < 1) {
            return false;
        }
        return true;
    }
    /**
     * Fall sem tekur upplýsingar um hótel sem var smellt á og opnar annað view
     * þar sem hægt er að framkvæma bókun
     * @param e
     * @throws IOException
     */
    @FXML
    public void clickHotel(MouseEvent e) throws IOException {
        if (!e.getTarget().getClass().getName().equals("com.sun.javafx.scene.control.LabeledText")) {
            e.consume();
            return;
        }
        Text hotel = (Text) e.getTarget();
        Hotel hotelClicked = null;
        for (Hotel rightHotel :
                hotels) {
            if (rightHotel.getName().equals(hotel.getText())) {
                hotelInfoName = rightHotel.getName();
                hotelInfoId = rightHotel.getId();
                hotelInfoRoom = rightHotel.getHerbergis();
                hotelClicked = rightHotel;
            }
        }
        FXMLLoader fxmlLoader = new FXMLLoader(HotelApplication.class.getResource("hotel.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 700);
        HotelViewController hvc = fxmlLoader.getController();
        hvc.setHotelName(hotelInfoName);
        hvc.setHotelId(hotelInfoId);
        if(fxRequiredSpace.getValue() != null && fxRequiredSpace.getValue() != "") {
            hvc.setNumOfGuests((Integer) fxRequiredSpace.getValue());
        }
        hvc.setHerbergis(hotelInfoRoom, checkInDate.getValue(), checkOutDate.getValue());
        hvc.setHotel(hotelClicked);
        hvc.setDatesChosen(checkInDate.getValue(),checkOutDate.getValue());
        hvc.setHotelContactInfo(hotelClicked.getContactNumber(), hotelClicked.getContactEmail());
        if(hotelClicked.isBreakfastIncluded()) {
            hvc.setBreakfastIncluded("Já");
        }else {
            hvc.setBreakfastIncluded("Nei");
        }

        Stage stage = (Stage) hotelList.getScene().getWindow();
        scene.setUserData(fxmlLoader.getController());
        stage.setTitle("Hotel");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Fall sem tekur við bókunarnúmeri og opnar annað view með
     * bókuninni með því númeri, ef hún er til.
     * @throws IOException
     */
    public void searchForBooking() throws IOException {
        String bookingNumber = bookingSearch.getText();
        UUID bookingNrtoUUID;
        try {
            bookingNrtoUUID = UUID.fromString(bookingNumber);
        }
        catch (Exception e) {
            return;
        }

        for(Booking b: bokanir) {
            if(b.getBookingnumber().equals(bookingNrtoUUID)) {
                FXMLLoader fxmlLoader = new FXMLLoader(HotelApplication.class.getResource("bokun.fxml"));
                Scene scene = new Scene(fxmlLoader.load(), 700, 700);
                BookingController sc =  fxmlLoader.getController();
                Stage stage = (Stage) searchBookingBtn.getScene().getWindow();
                sc.setBokunId(bookingNrtoUUID);
                stage.setTitle("Booking");
                stage.setScene(scene);
                stage.show();
                return;
            }
        }

    }
}
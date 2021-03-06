package hi.hotel.vidmot;

import hi.hotel.database.HotelFile;
import hi.hotel.vinnsla.Booking;
import hi.hotel.vinnsla.Room;
import hi.hotel.vinnsla.Hotel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
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
 *   Lýsing: Controllerklasinn fyrir hótelview síðu
 *   verkefnsins, tengt við hotel.fxml.
 *
 **********************************************************/
public class HotelViewController implements Initializable {
    @FXML
    private Label fxPrice;
    @FXML
    private Label fxSpaceFor;
    @FXML
    private Label fxTotalCost;
    @FXML
    private Label fxCheckinChosen;
    @FXML
    private Label fxCheckoutChosen;
    @FXML
    private Label fxHotelName;
    @FXML
    private ImageView fxHotelImage;
    @FXML
    private TextField fxName;
    @FXML
    private TextField fxEmail;
    @FXML
    private  TextField fxKennitala;
    @FXML
    private Button fxBokun;
    @FXML
    private ChoiceBox fxHerbergi;
    @FXML
    private Label fxHotelPhonenumber;
    @FXML
    private Label fxHotelEmail;
    @FXML
    private Label fxnumOfGuests;
    @FXML
    private Label fxBreakfastIncluded;
    private int hotelId;
    private Hotel hotel;
    private ArrayList<Room> rooms;
    private HotelFile hotelFile = new HotelFile();
    private int numOfGuests;
    private LocalDate checkInCheck = null;
    private LocalDate checkOutCheck = null;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        fxBokun.disableProperty().bind(fxName.textProperty().isEmpty().or(fxEmail.textProperty().isEmpty().or(fxKennitala.textProperty().isEmpty().or(fxHerbergi.valueProperty().isNull()))));

    }

    public void setHotelName(String name) {
        fxHotelName.setText(name);
    }

    public void setHerbergis(ArrayList<Room> h, LocalDate checkIn, LocalDate checkOut) {
        this.rooms = h;
        ObservableList<String> oherbergiList = FXCollections.observableArrayList();
        for (Room room : h) {
            if(room.getSpaceFor() >= numOfGuests) {
                oherbergiList.add(room.getTypa());
            }
        }

        for (Hotel hotel: hotelFile.hotels) {
            if (hotel.getId() == hotelId) {
                for (Room r: hotel.getHerbergis()) {
                    if (!(r.hasDateOpen(checkIn, checkOut))) {
                        oherbergiList.remove(r.getTypa());
                    }
                }
            }
        }

        fxHerbergi.setItems(oherbergiList);
    }
    public void setHotelContactInfo(String number, String email) {
        fxHotelPhonenumber.setText(number);
        fxHotelEmail.setText(email);
    }
    public void setNumOfGuests(int numOfGuests) {
        fxnumOfGuests.setText(Integer.toString(numOfGuests));
        this.numOfGuests = numOfGuests;
    }

    public void setHotelId(int id) {
        hotelId = id;
        if (hotelId == 1) {
            Image hotelImage = new Image(HotelViewController.class.getResourceAsStream("myndir/francesca-saraco-_dS27XGgRyQ-unsplash.jpg"));
            fxHotelImage.setImage(hotelImage);
        }
    }
    public void setHotel(Hotel h) {
        hotel = h;
    }
    public void setDatesChosen(LocalDate checkIn, LocalDate checkOut) {
        fxCheckinChosen.setText(checkIn.toString());
        fxCheckoutChosen.setText(checkOut.toString());
        checkInCheck = checkIn;
        checkOutCheck = checkOut;
    }
    public void setPrice(int price) {
        fxPrice.setText(price+ " kr.");
    }
    public void setFxSpaceFor(int spaceFor) {
        fxSpaceFor.setText(Integer.toString(spaceFor));
    }
    public void setFxTotalCost() {
        LocalDate cin = LocalDate.parse(fxCheckinChosen.getText());
        LocalDate cout = LocalDate.parse(fxCheckoutChosen.getText());
        long days= ChronoUnit.DAYS.between(cin,cout);
        int totalCost = (int)days * Integer.parseInt(fxPrice.getText().replace(" kr.", ""));
        fxTotalCost.setText(totalCost+ " kr.");
    }
    public void setBreakfastIncluded(String included) {
        fxBreakfastIncluded.setText(included);
    }

    /**
     * Fall sem útfærir virkni á takka til að geta farið aftur í opnunarsíðu forritsins.
     * @param e
     * @throws IOException
     */
    @FXML
    private void backToHotelSelection(ActionEvent e) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HotelApplication.class.getResource("frontpage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900, 700);
        HotelController hc =  fxmlLoader.getController();
        Stage stage = (Stage) fxHotelName.getScene().getWindow();
        scene.setUserData(fxmlLoader.getController());
        stage.setTitle("Hotel");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Fall sem tekur við innskráðum upplýsingum á síðunni og býr til bókun
     * með þeim.
     * @param event
     * @throws IOException
     */
    public void geraBokun(ActionEvent event) throws IOException {
        UUID uuid = UUID.randomUUID();
        String name = fxName.getText();
        String email = fxEmail.getText();
        String kennitala = fxKennitala.getText();
        LocalDate checkin = LocalDate.parse(fxCheckinChosen.getText());
        LocalDate checkout = LocalDate.parse(fxCheckoutChosen.getText());
        int herbergiId = 0;

        String herbergiName = String.valueOf(fxHerbergi.getValue());
        for (Room h:
                rooms) {
            if (h.getTypa() == herbergiName) {
                Room room = h;
                herbergiId = room.getId();
            }
        }
        Booking newBooking = new Booking(hotelId, herbergiId,null, checkin, checkout, uuid);
        for (Room rh: rooms) {
            if (rh.getHotelId() == hotelId) {
                rh.addBooking(newBooking);
                break;
            }
        }
        writeBooking(hotelId, herbergiId, checkin, checkout, uuid);
        writePerson(name, email, kennitala, uuid);

        setPersonurInBooking();

        BookingDialog bd = new BookingDialog();
        bd.bokunMottekin(String.valueOf(uuid));

        backToHotelSelection(event);
    }

    /**
     * Skrifar upplýsingar um gest í Persons.txt, eftir að bókun er framkvæmd.
     * @param name
     * @param email
     * @param kennitala
     * @param uuid
     * @throws IOException
     */
    private void writePerson(String name, String email, String kennitala, UUID uuid) throws IOException {
        File file = new File("Persons.txt");
        FileWriter fileWriter = new FileWriter(file, true);
        BufferedWriter writer = new BufferedWriter(fileWriter);
        writer.newLine();
        writer.write(name + "," + email + "," + kennitala + "," + uuid);
        writer.close();
    }

    /**
     * Skrifar upplýsingar um bókun í Bookings.txt, eftir að bókun er framkvæmd.
     * @param hotelId
     * @param herbergiId
     * @param checkIn
     * @param checkOut
     * @param uuid
     * @throws IOException
     */
    private void writeBooking(int hotelId, int herbergiId, LocalDate checkIn, LocalDate checkOut, UUID uuid) throws IOException {
        File file = new File("Bookings.txt");
        FileWriter fileWriter = new FileWriter(file, true);
        BufferedWriter writer = new BufferedWriter(fileWriter);
        writer.newLine();
        writer.write(hotelId + "," + herbergiId + "," + "null," + checkIn + "," + checkOut + "," + uuid);
        writer.close();
    }

    /**
     * Bætir gest við nýgerða bókun.
     */
    private void setPersonurInBooking() {
        hotelFile.makeBookings();
    }

    /**
     * Sýnir upplýsingar um valið herbergi sem valið úr
     * Choiceboxinu með fxid: fxHerbergi
     * @param actionEvent
     */
    public void setRoomInfo(ActionEvent actionEvent) {
        for(Room room : hotel.getHerbergis()) {
            if(room.getTypa() == fxHerbergi.getValue()) {
                setPrice(room.getPricePerNight());
                setFxSpaceFor(room.getSpaceFor());
                setFxTotalCost();
            }
        }
    }
}

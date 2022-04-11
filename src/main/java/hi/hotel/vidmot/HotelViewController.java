package hi.hotel.vidmot;

import hi.hotel.database.HotelFile;
import hi.hotel.vinnsla.Bokun;
import hi.hotel.vinnsla.Herbergi;
import hi.hotel.vinnsla.Hotel;
import hi.hotel.vinnsla.Persona;
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
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.UUID;

public class HotelViewController implements Initializable {
    @FXML
    private Label fxHotelName;
    @FXML
    private ImageView fxHotelImage;
    @FXML
    private DatePicker fxCheckinDate;
    @FXML
    private DatePicker fxCheckoutDate;
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
    private int hotelId;
    private ArrayList<Herbergi> herbergis;
    private HotelFile hotelFile = new HotelFile();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        fxBokun.disableProperty().bind(fxCheckinDate.valueProperty().isNull().or(fxCheckoutDate.valueProperty().isNull().or(fxName.textProperty().isEmpty().or(fxEmail.textProperty().isEmpty().or(fxKennitala.textProperty().isEmpty().or(fxHerbergi.valueProperty().isNull()))))));
    }

    public void setHotelName(String name) {
        fxHotelName.setText(name);
    }

    public void setHerbergis(ArrayList<Herbergi> h) {
        this.herbergis = h;
        ObservableList<String> oherbergiList = FXCollections.observableArrayList();
        for (Herbergi herbergi:
             h) {
            oherbergiList.add(herbergi.getTypa());
        }

        fxHerbergi.setItems(oherbergiList);
    }

    public void setHotelId(int id) {
        hotelId = id;
        if (hotelId == 1) {
            Image hotelImage = new Image(HotelViewController.class.getResourceAsStream("myndir/francesca-saraco-_dS27XGgRyQ-unsplash.jpg"));
            fxHotelImage.setImage(hotelImage);
        }
    }

    @FXML
    private void backToHotelSelection(ActionEvent e) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HotelApplication.class.getResource("frontpage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 700, 700);
        HotelController hc =  fxmlLoader.getController();
        Stage stage = (Stage) fxHotelName.getScene().getWindow();
        scene.setUserData(fxmlLoader.getController());
        stage.setTitle("Hotel");
        stage.setScene(scene);
        stage.show();
    }


    public void geraBokun(ActionEvent event) throws IOException {
        UUID uuid = UUID.randomUUID();
        String name = fxName.getText();
        String email = fxEmail.getText();
        String kennitala = fxKennitala.getText();
        LocalDate checkin = fxCheckinDate.getValue();
        LocalDate checkout = fxCheckoutDate.getValue();
        int herbergiId = 0;

        String herbergiName = String.valueOf(fxHerbergi.getValue());
        for (Herbergi h:
             herbergis) {
            if (h.getTypa() == herbergiName) {
                Herbergi herbergi = h;
                herbergiId = herbergi.getId();
            }
        }
        writeBooking(hotelId, herbergiId, checkin, checkout, uuid);
        writePerson(name, email, kennitala, uuid);

        setPersonurInBooking();

        backToHotelSelection(event);
    }

    private void writePerson(String name, String email, String kennitala, UUID uuid) throws IOException {
        File file = new File("Personur.txt");
        FileWriter fileWriter = new FileWriter(file, true);
        BufferedWriter writer = new BufferedWriter(fileWriter);
        writer.newLine();
        writer.write(name + "," + email + "," + kennitala + "," + uuid);
        writer.close();
    }

    private void writeBooking(int hotelId, int herbergiId, LocalDate checkIn, LocalDate checkOut, UUID uuid) throws IOException {
        System.out.println("writing..");
        File file = new File("Bookings.txt");
        FileWriter fileWriter = new FileWriter(file, true);
        BufferedWriter writer = new BufferedWriter(fileWriter);
        writer.newLine();
        writer.write(hotelId + "," + herbergiId + "," + "null," + checkIn + "," + checkOut + "," + uuid);
        writer.close();
    }

    private void setPersonurInBooking() {
        hotelFile.makeBookings();
    }
}

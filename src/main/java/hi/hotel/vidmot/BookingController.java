package hi.hotel.vidmot;

import hi.hotel.database.HotelFile;
import hi.hotel.vinnsla.Bokun;
import hi.hotel.vinnsla.Herbergi;
import hi.hotel.vinnsla.Hotel;
import hi.hotel.vinnsla.Persona;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.UUID;
/**********************************************************
 *
 *   Hópur: 2H
 *
 *   Lýsing: Controllerklasinn fyrir bókunarsíðu forritsins,
 *   tengt við bokun.fxml.
 *
 **********************************************************/
public class BookingController implements Initializable {

    @FXML
    private Button fxEydaBokun;
    @FXML
    private Button fxBack;
    @FXML
    private Label fxBokunarNumer;
    @FXML
    private Label fxCheckin;
    @FXML
    private Label fxCheckout;
    @FXML
    private Label fxName;
    @FXML
    private Label fxEmail;
    @FXML
    private Label fxKennitala;
    @FXML
    private Label fxHerbergi;
    @FXML
    private Label fxMatur;
    @FXML
    private Label fxHotel;

    private UUID bokunId;
    private HotelFile hotelFile = new HotelFile();
    private ArrayList<Bokun> bokanir;
    private ArrayList<Persona> personas;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        bokanir = hotelFile.bokanir;
        personas = hotelFile.personur;
    }

    public void setBokunId(UUID uuid) {
        bokunId = uuid;
        fxBokunarNumer.setText("Bókun nr: " + String.valueOf(uuid));
        setThings();
    }

    private void setThings() {
        int heid = -1;
        int hid = -1;
        for (Bokun b: bokanir) {
            if (b.getBookingnumber().equals(bokunId)) {
                fxCheckin.setText(String.valueOf(b.getCheckIn()));
                fxCheckout.setText(String.valueOf(b.getCheckOut()));
                heid = b.getHerbergi();
                hid = b.getHotelId();
            }
        }
        for (Persona p: personas) {
            if (p.getBookingId().equals(bokunId)) {
                fxName.setText(p.getNafn());
                fxEmail.setText(p.getEmail());
                fxKennitala.setText(p.getKennitala());
            }
        }
        for (Herbergi h: hotelFile.herbergis) {
            if (h.getId() == hid) {
                fxHerbergi.setText(h.getTypa());
            }
        }
        for (Hotel h: hotelFile.hotels) {
            if (h.getId() == heid) {
                if (h.isBreakfastIncluded()) {
                    fxMatur.setText("Já");
                } else {
                    fxMatur.setText("Nei");
                }
                fxHotel.setText(h.getName());
            }
        }
    }


    public void eydaBokun(ActionEvent event) throws IOException {
        for (int i = 0; i<bokanir.size(); i++) {
            if (bokanir.get(i).getBookingnumber().equals(bokunId)) {
                bokanir.remove(i);
            }
        }
        for (int i = 0; i < personas.size(); i++) {
            if (personas.get(i).getBookingId().equals(bokunId)) {
                personas.remove(i);
            }
        }
        resetBokanir();
        resetPersonur();

        backToMenu(event);
    }

    private void resetBokanir() throws IOException {
        File file = new File("Bookings.txt");
        FileWriter fileWriter = new FileWriter(file);
        BufferedWriter writer = new BufferedWriter(fileWriter);
        int i = 0;
        for (Bokun bokun: bokanir) {
            writer.write(bokun.getHotelId() + "," + bokun.getHerbergi() + ",null," + bokun.getCheckIn() + "," + bokun.getCheckOut() + "," + bokun.getBookingnumber());
            i++;
            if (i < bokanir.size()) {
                writer.newLine();
            }
        }
        writer.close();
    }

    private void resetPersonur() throws IOException {
        File file = new File("Personur.txt");
        FileWriter fileWriter = new FileWriter(file);
        BufferedWriter writer = new BufferedWriter(fileWriter);
        int i = 0;
        for (Persona p: personas) {
            writer.write(p.getNafn() + "," + p.getEmail() + "," + p.getKennitala() + "," + p.getBookingId());
            i++;
            if (i < personas.size()) {
                writer.newLine();
            }
        }
        writer.close();
    }

    public void backToMenu(ActionEvent event) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(HotelApplication.class.getResource("frontpage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900, 700);
        HotelController hc =  fxmlLoader.getController();
        Stage stage = (Stage) fxEydaBokun.getScene().getWindow();
        scene.setUserData(fxmlLoader.getController());
        stage.setTitle("Hotel");
        stage.setScene(scene);
        stage.show();
    }
}

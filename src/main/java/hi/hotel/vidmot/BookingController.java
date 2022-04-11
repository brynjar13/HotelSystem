package hi.hotel.vidmot;

import hi.hotel.database.HotelFile;
import hi.hotel.vinnsla.Bokun;
import hi.hotel.vinnsla.Persona;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.UUID;

public class BookingController implements Initializable {

    @FXML
    private Button fxEydaBokun;
    @FXML
    private Button fxBack;
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
        Scene scene = new Scene(fxmlLoader.load(), 700, 700);
        HotelController hc =  fxmlLoader.getController();
        Stage stage = (Stage) fxEydaBokun.getScene().getWindow();
        scene.setUserData(fxmlLoader.getController());
        stage.setTitle("Hotel");
        stage.setScene(scene);
        stage.show();
    }
}

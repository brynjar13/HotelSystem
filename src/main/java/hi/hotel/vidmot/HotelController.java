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
import javafx.scene.control.skin.LabeledSkinBase;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class HotelController implements Initializable {
    HotelFile hotelFile;
    ArrayList<Hotel> hotels;
    private String hotelInfoName;
    private int hotelInfoId;

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
        for(Bokun b:bokanir) {
            System.out.println(b);
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

    public void clickHotel(MouseEvent e) throws IOException {
        if (!e.getTarget().getClass().getName().toString().equals("com.sun.javafx.scene.control.LabeledText")) {
            e.consume();
            return;
        }
        Text hotel = (Text) e.getTarget();
        for (Hotel rightHotel:
             hotels) {
            if (rightHotel.getName().equals(hotel.getText())) {
                hotelInfoName = rightHotel.getName();
                hotelInfoId = rightHotel.getId();
            }
        }
        FXMLLoader fxmlLoader = new FXMLLoader(HotelApplication.class.getResource("hotel.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 700, 700);
        HotelViewController hvc =  fxmlLoader.getController();
        hvc.setHotelName(hotelInfoName);
        hvc.setHotelId(hotelInfoId);
        Stage stage = (Stage) hotelList.getScene().getWindow();
        scene.setUserData(fxmlLoader.getController());
        stage.setTitle("Hotel");
        stage.setScene(scene);
        stage.show();
    }
}
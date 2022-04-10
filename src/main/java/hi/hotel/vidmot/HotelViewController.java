package hi.hotel.vidmot;

import hi.hotel.vinnsla.Herbergi;
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

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

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
}

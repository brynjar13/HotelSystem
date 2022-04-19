module hi.verkefni3.vidmot.verkefni1 {
    requires javafx.controls;
    requires javafx.fxml;


    opens hi.hotel.vidmot to javafx.fxml;
    exports hi.hotel.vidmot;
}
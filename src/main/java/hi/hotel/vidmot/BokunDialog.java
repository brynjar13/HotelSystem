package hi.hotel.vidmot;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;

import java.io.IOException;
import java.util.Optional;

public class BokunDialog extends DialogPane {
    @FXML
    private Label fxBokunarNumer;
    public BokunDialog() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("BokunDialog.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException ioException) {
            throw new RuntimeException(ioException);
        }
    }

    /**
     * Aðferð sem tékkar hvort leikmaður vill spila annan leik
     * @return Boolean
     */
    public boolean bokunMottekin(String bokunarNumer) {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setDialogPane(this);
        fxBokunarNumer.setText("Bókunarnúmerið þitt er: " + bokunarNumer);
        Optional<ButtonType> utkoma = dialog.showAndWait();

        if (utkoma.isPresent() && utkoma.get().getButtonData() == ButtonBar.ButtonData.OK_DONE) {
            return true;
        } else {
            return false;
        }
    }
}

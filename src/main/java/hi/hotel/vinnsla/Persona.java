package hi.hotel.vinnsla;

import java.util.UUID;
/**********************************************************
 *
 *   Hópur: 2H
 *
 *   Lýsing: Klasi sem geymir upplýsingar um hvern gest í bókun
 *   í forritinu.
 *
 **********************************************************/
public class Persona {
    private String nafn;
    private String email;
    private String kennitala;
    private UUID bookingId;

    public Persona(String nafn, String email, String kennitala, UUID bookingId) {
        this.nafn = nafn;
        this.email = email;
        this.kennitala = kennitala;
        this.bookingId = bookingId;
    }

    public String getNafn() {
        return nafn;
    }

    public String getEmail() {
        return email;
    }

    public UUID getBookingId() {
        return bookingId;
    }

    @Override
    public String toString() {
        return "Persona{" +
                "nafn='" + nafn + '\'' +
                ", email='" + email + '\'' +
                ", kennitala='" + kennitala + '\'' +
                '}';
    }

    public String getKennitala() {
        return kennitala;
    }
}

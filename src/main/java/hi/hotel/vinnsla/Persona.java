package hi.hotel.vinnsla;

import java.util.UUID;

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

    public String getKennitala() {
        return kennitala;
    }
}

package hi.hotel.vinnsla;

public class Persona {
    private String nafn;
    private String email;
    private String kennitala;

    public Persona(String nafn, String email, String kennitala) {
        this.nafn = nafn;
        this.email = email;
        this.kennitala = kennitala;
    }

    public String getNafn() {
        return nafn;
    }

    public String getEmail() {
        return email;
    }

    public String getKennitala() {
        return kennitala;
    }
}

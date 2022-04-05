package hi.hotel.vidmot;

public class Hotel {
    String name;
    String desc;
    public Hotel(String h, String p) {
        name = h;
        desc = p;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }
}

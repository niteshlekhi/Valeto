package lekhicomp.com.valeto.model;


public class SlotDetails {
    public int slot;
    public String car_no;
    public String phone;
    public String name;
    public String alt_phone;
    public String email;

    public SlotDetails() {
    }

    public SlotDetails(int slot, String car_no, String phone, String name, String alt_phone, String email) {
        this.slot = slot;
        this.car_no = car_no;
        this.phone = phone;
        this.name = name;
        this.alt_phone = alt_phone;
        this.email = email;
    }


    @Override
    public String toString() {
        return "SlotDetails{" +
                "slot=" + slot + '\n' +
                ", car_no='" + car_no + '\n' +
                ", phone='" + phone + '\n' +
                ", name='" + name + '\n' +
                ", alt_phone='" + alt_phone + '\n' +
                ", email='" + email + '\n' +
                '}';
    }
}

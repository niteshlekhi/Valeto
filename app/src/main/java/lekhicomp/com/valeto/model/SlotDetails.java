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

    public int getSlot() {
        return slot;
    }

    public void setSlot(int slot) {
        this.slot = slot;
    }

    public String getCar_no() {
        return car_no;
    }

    public void setCar_no(String car_no) {
        this.car_no = car_no;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlt_phone() {
        return alt_phone;
    }

    public void setAlt_phone(String alt_phone) {
        this.alt_phone = alt_phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
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

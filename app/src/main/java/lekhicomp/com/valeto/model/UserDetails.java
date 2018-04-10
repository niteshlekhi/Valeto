package lekhicomp.com.valeto.model;

/**
 * Created by admin on 22-Jan-18.
 */

public class UserDetails {
    public String name;
    public String phone;
    public String alt_phone;
    public String email;
    public int loyaltyPoints;

    public UserDetails() {
    }

    public UserDetails(String name, String phone, String alt_phone, String email, int loyaltyPoints) {
        this.name = name;
        this.phone = phone;
        this.alt_phone = alt_phone;
        this.email = email;
        this.loyaltyPoints = loyaltyPoints;
    }

    @Override
    public String toString() {
        return "UserDetails{" +
                "name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", alt_phone='" + alt_phone + '\'' +
                ", email='" + email + '\'' +
                ", loyaltyPoints=" + loyaltyPoints +
                '}';
    }
}

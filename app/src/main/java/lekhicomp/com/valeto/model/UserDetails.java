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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public int getLoyaltyPoints() {
        return loyaltyPoints;
    }

    public void setLoyaltyPoints(int loyaltyPoints) {
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

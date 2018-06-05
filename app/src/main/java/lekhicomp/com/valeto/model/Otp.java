package lekhicomp.com.valeto.model;

public class Otp {
    public String ph;
    public String otp;

    public Otp() {
    }

    public Otp(String ph, String otp) {
        this.ph = ph;
        this.otp = otp;
    }

    public String getPh() {
        return ph;
    }

    public void setPh(String ph) {
        this.ph = ph;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    @Override
    public String toString() {
        return "Otp{" +
                "ph='" + ph + '\'' +
                ", otp='" + otp + '\'' +
                '}';
    }
}


package satisisletmesi.Models;

public class Calisan extends Kullanici {
    private String calisan_no;
    private String maas;
    
    public Calisan() {}

    public Calisan(String isim, String soyisim, String sifre, String tc, 
            String telefon, String email, String adres, String calisan_no, String maas) {
        super(isim, soyisim, sifre, tc, telefon, email, adres);
        this.calisan_no = calisan_no;
        this.maas = maas;
    }

    public String getCalisan_no() {
        return calisan_no;
    }

    public void setCalisan_no(String calisan_no) {
        this.calisan_no = calisan_no;
    }

    public String getMaas() {
        return maas;
    }

    public void setMaas(String maas) {
        this.maas = maas;
    }
}

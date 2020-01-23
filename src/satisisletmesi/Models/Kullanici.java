package satisisletmesi.Models;

public abstract class Kullanici {
    private String isim;
    private String soyisim;
    private String sifre;
    private String tc;
    private String telefon;
    private String email;
    private String adres;
    
    public Kullanici() {}
 
    public Kullanici(String isim, String soyisim, String sifre, String tc, 
            String telefon, String email, String adres) {
        this.isim = isim;
        this.soyisim = soyisim;
        this.sifre = sifre;
        this.tc = tc;
        this.telefon = telefon;
        this.email = email;
        this.adres = adres;
    }

    public String getIsim() {
        return isim;
    }

    public void setIsim(String isim) {
        this.isim = isim;
    }

    public String getSoyisim() {
        return soyisim;
    }

    public void setSoyisim(String soyisim) {
        this.soyisim = soyisim;
    }

    public String getSifre() {
        return sifre;
    }

    public void setSifre(String sifre) {
        this.sifre = sifre;
    }

    public String getTc() {
        return tc;
    }

    public void setTc(String tc) {
        this.tc = tc;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }
    
}

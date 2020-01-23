package satisisletmesi.Models;

public class Musteri extends Kullanici {
    private String musteri_no;
    private String odeme_sekli;
    
    public Musteri() {}

    public Musteri(String isim, String soyisim, String sifre, String tc, 
            String telefon, String email, String adres, String musteri_no, String odeme_sekli) {
        super(isim, soyisim, sifre, tc, telefon, email, adres);
        this.musteri_no = musteri_no;
        this.odeme_sekli = odeme_sekli;
    }

    public String getMusteri_no() {
        return musteri_no;
    }

    public void setMusteri_no(String musteri_no) {
        this.musteri_no = musteri_no;
    }

    public String getOdeme_sekli() {
        return odeme_sekli;
    }

    public void setOdeme_sekli(String odeme_sekli) {
        this.odeme_sekli = odeme_sekli;
    }
    
}

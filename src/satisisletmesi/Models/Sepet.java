package satisisletmesi.Models;

public class Sepet {
    private String barkod_no;
    private String urun_adeti;
    private String urun_isim;
    private int toplam_fiyat;

    public Sepet() {}
    
    public Sepet(String barkod_no, String urun_adeti, String urun_isim, int toplam_fiyat) {
        this.barkod_no = barkod_no;
        this.urun_adeti = urun_adeti;
        this.urun_isim = urun_isim;
        this.toplam_fiyat = toplam_fiyat;
    }

    public String getBarkod_no() {
        return barkod_no;
    }

    public void setBarkod_no(String barkod_no) {
        this.barkod_no = barkod_no;
    }

    public String getUrun_adeti() {
        return urun_adeti;
    }

    public void setUrun_adeti(String urun_adeti) {
        this.urun_adeti = urun_adeti;
    }

    public String getUrun_isim() {
        return urun_isim;
    }

    public void setUrun_isim(String urun_isim) {
        this.urun_isim = urun_isim;
    }

    public int getToplam_fiyat() {
        return toplam_fiyat;
    }

    public void setToplam_fiyat(int toplam_fiyat) {
        this.toplam_fiyat = toplam_fiyat;
    }
    
    
}

package satisisletmesi.Models;

public class SiparisIstegi {
    private String siparis_no;
    private String barkod_no;
    private int urun_adeti;
    private int bu_urunun_toplam_fiyati;
    private String musteri_no;
    
    public SiparisIstegi() {}

    public SiparisIstegi(String siparis_no, String barkod_no, int urun_adeti, int bu_urunun_toplam_fiyati, String musteri_no) {
        this.siparis_no = siparis_no;
        this.barkod_no = barkod_no;
        this.urun_adeti = urun_adeti;
        this.bu_urunun_toplam_fiyati = bu_urunun_toplam_fiyati;
        this.musteri_no = musteri_no;
    }
    
    public String getSiparis_no() {
        return siparis_no;
    }

    public void setSiparis_no(String siparis_no) {
        this.siparis_no = siparis_no;
    }

    public String getBarkod_no() {
        return barkod_no;
    }

    public void setBarkod_no(String barkod_no) {
        this.barkod_no = barkod_no;
    }

    public int getUrun_adeti() {
        return urun_adeti;
    }

    public void setUrun_adeti(int urun_adeti) {
        this.urun_adeti = urun_adeti;
    }

    public int getBu_urunun_toplam_fiyati() {
        return bu_urunun_toplam_fiyati;
    }

    public void setBu_urunun_toplam_fiyati(int bu_urunun_toplam_fiyati) {
        this.bu_urunun_toplam_fiyati = bu_urunun_toplam_fiyati;
    }

    public String getMusteri_no() {
        return musteri_no;
    }

    public void setMusteri_no(String musteri_no) {
        this.musteri_no = musteri_no;
    }
    
}
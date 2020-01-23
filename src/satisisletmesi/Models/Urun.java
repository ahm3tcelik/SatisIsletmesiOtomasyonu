package satisisletmesi.Models;

public class Urun {
    private String barkod_no;
    private String isim;
    private int fiyat;
    private String stok_adeti;
    private String tedarikci_id;
    
    public Urun() {}

    public Urun(String barkod_no, String isim, int fiyat, String stok_adeti, String tedarikci_id) {
        this.barkod_no = barkod_no;
        this.isim = isim;
        this.fiyat = fiyat;
        this.stok_adeti = stok_adeti;
        this.tedarikci_id = tedarikci_id;
    }
    
    public String getBarkod_no() {
        return barkod_no;
    }

    public void setBarkod_no(String barkod_no) {
        this.barkod_no = barkod_no;
    }

    public String getIsim() {
        return isim;
    }

    public void setIsim(String isim) {
        this.isim = isim;
    }

    public int getFiyat() {
        return fiyat;
    }

    public void setFiyat(int fiyat) {
        this.fiyat = fiyat;
    }

    public String getStok_adeti() {
        return stok_adeti;
    }

    public void setStok_adeti(String stok_adeti) {
        this.stok_adeti = stok_adeti;
    }

    public String getTedarikci_id() {
        return tedarikci_id;
    }

    public void setTedarikci_id(String tedarikci_id) {
        this.tedarikci_id = tedarikci_id;
    }

    
}

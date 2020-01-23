package satisisletmesi.Models;

public class Siparis extends SiparisIstegi {
    private String onaylayan_personel_no;

    public Siparis() {}

    public Siparis(String onaylayan_personel_no, String siparis_no, String barkod_no, int urun_adeti, int bu_urunun_toplam_fiyati, String musteri_no) {
        super(siparis_no, barkod_no, urun_adeti, bu_urunun_toplam_fiyati, musteri_no);
        this.onaylayan_personel_no = onaylayan_personel_no;
    }
    
    public String getOnaylayan_personel_no() {
        return onaylayan_personel_no;
    }

    public void setOnaylayan_personel_no(String onaylayan_personel_no) {
        this.onaylayan_personel_no = onaylayan_personel_no;
    }
}
package satisisletmesi.Models;

public class UrunTalebi {
    private String barkod_no;
    private int adet;
    private String tedarikci_id;
    
    public UrunTalebi() {}

    public UrunTalebi(String barkod_no, int adet, String tedarikci_id) {
        this.barkod_no = barkod_no;
        this.adet = adet;
        this.tedarikci_id = tedarikci_id;
    }

    public String getBarkod_no() {
        return barkod_no;
    }

    public void setBarkod_no(String barkod_no) {
        this.barkod_no = barkod_no;
    }

    public int getAdet() {
        return adet;
    }

    public void setAdet(int adet) {
        this.adet = adet;
    }

    public String getTedarikci_id() {
        return tedarikci_id;
    }

    public void setTedarikci_id(String tedarikci_id) {
        this.tedarikci_id = tedarikci_id;
    }
}

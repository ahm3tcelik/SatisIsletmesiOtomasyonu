package satisisletmesi.Models;

public class Tedarikci {
    private String isim;
    private String tedarikci_id;
    private String teslimat_suresi;

    public Tedarikci() {}

    public Tedarikci(String isim, String tedarikci_id, String teslimat_suresi) {
        this.isim = isim;
        this.tedarikci_id = tedarikci_id;
        this.teslimat_suresi = teslimat_suresi;
    }

    public String getIsim() {
        return isim;
    }

    public void setIsim(String isim) {
        this.isim = isim;
    }

    public String getTedarikci_id() {
        return tedarikci_id;
    }

    public void setTedarikci_id(String tedarikci_id) {
        this.tedarikci_id = tedarikci_id;
    }

    public String getTeslimat_süresi() {
        return teslimat_suresi;
    }

    public void setTeslimat_süresi(String teslimat_suresi) {
        this.teslimat_suresi = teslimat_suresi;
    }
}

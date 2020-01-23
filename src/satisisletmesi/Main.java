package satisisletmesi;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import satisisletmesi.Dao.DatabaseIO;
import satisisletmesi.Models.Calisan;
import satisisletmesi.Models.Musteri;
import satisisletmesi.Models.Sepet;
import satisisletmesi.Models.Siparis;
import satisisletmesi.Models.SiparisIstegi;
import satisisletmesi.Models.Tedarikci;
import satisisletmesi.Models.Urun;
import satisisletmesi.Models.Yetkili;

public class Main {
    private static Sepet sepet2 = new Sepet();
    public static void main(String[] args) throws IOException {
        index();
    }
    
    public static void index() throws IOException {
        DatabaseIO dIO = new DatabaseIO();
        Scanner scan = new Scanner(System.in);
        String choosen = "0";
        System.out.println("___________");
        System.out.println("Hoşgeldiniz");
        System.out.println("1. Giriş Yap");
        System.out.println("2. Kayıt Ol");
        choosen = scan.nextLine();
        switch(choosen) {
            case "1": {
                System.out.println("Lütfen mail adresinizi giriniz : ");
                String email = scan.nextLine();
                System.out.println("Neredeyse az kaldı. Şimdi de şifrenizi giriniz : ");
                String sifre = scan.nextLine();
                if(email.contains(";") || sifre.contains(";")) {
                    System.out.println("Email veya şifre ';' içeremez.");
                    index();
                }
                else {
                    Object x = dIO.girisYap(email, sifre);
                    if(x == null) {
                        System.out.println("Giriş Bilgiler hatalı");
                        index();
                    }
                    else {
                        Class a = x.getClass();
                        switch(a.getSimpleName()) {
                            case "Musteri": {
                                Musteri musteri = (Musteri) x;
                                HomePage(musteri);
                                break;
                            }
                            case "Calisan": {
                                Calisan calisan = (Calisan) x;
                                HomePage(calisan);
                                break;
                            }
                            case "Yetkili": {
                                Yetkili yetkili = (Yetkili) x;
                                HomePage(yetkili);
                                break;
                            }
                            default:break;
                        }
                        break;
                    }
                }
                break;
            } 
            case "2": {
                System.out.println("Kayıt için gerekli bilgileri girmeniz gerekiyor!");
                System.out.print("İsim : ");
                String isim = scan.nextLine();
                System.out.print("\nSoyisim : ");
                String soyisim = scan.nextLine();
                System.out.print("\nSifre : ");
                String sifre = scan.nextLine();
                System.out.print("\nTC : ");
                String tc = scan.nextLine();
                System.out.print("\nTelefon : ");
                String telefon = scan.nextLine();
                System.out.print("\nEmail : ");
                String email = scan.nextLine();
                System.out.print("\nAdres : ");
                String adres = scan.nextLine();
                System.out.print("\nÖdeme Şekli : ");
                String odeme_sekli = scan.nextLine();
                dIO.musteriEkle(isim, soyisim, sifre, tc, telefon, email, adres, odeme_sekli);
                System.out.println("---------------------------");
                System.out.println("Şimdi giriş yapabilirsiniz!");
                index();
            }
            default:break;
        }
    }
    public static void HomePage(Musteri musteri) throws IOException{
        Scanner scanner = new Scanner(System.in);
        DatabaseIO dIO = new DatabaseIO();
        System.out.println("------------------------------------");
        System.out.println("Hoşgeldin : " + musteri.getIsim());
        System.out.println("1. Sepete Ürün Ekle");
        System.out.println("2. Sepeti Boşalt");
        System.out.println("3. Ürün Ara");
        System.out.println("4. Sipariş İsteği Oluştur");
        System.out.println("5. Önceki Siparişleri Listele");
        System.out.println("6. Çıkış Yap");
        System.out.println("------------------------------------");
        String choosen = scanner.nextLine();
        switch(choosen) {
            case "1": {
                int counter = 0;
                ArrayList<Urun> urunlerList = dIO.tumUrunler();
                System.out.println("----------------------------------------");
                System.out.println("# - Ürün İsmi - Ürün Fiyatı - Stok Adeti");
                for(Urun u : urunlerList) {
                    System.out.println("#" + (counter + 1) + " - " + u.getIsim() + " - " + u.getFiyat() + " - " + u.getStok_adeti());
                    counter++;
                }
                System.out.println("0. Geri dön");
                System.out.println("----------------------------------------");
                System.out.println("Sepete eklemek istediğiniz ürünün numarasını giriniz.");
                Scanner scan = new Scanner(System.in);
                int urun_no = scan.nextInt();
                if(urun_no == 0) HomePage(musteri);
                else if(urun_no > urunlerList.size() || urun_no < 1) {
                    System.out.println("Bu numaraya ait ürün bulunmamaktadır.");
                    System.out.println("0. Geri Dön");
                    scanner.nextLine();
                    HomePage(musteri);
                }
                else {
                    Urun urun = urunlerList.get(urun_no - 1);
                    System.out.println("Seçilen ürün : " +  urun.getIsim() + " - Fiyat : " + urun.getFiyat() + " - " + urun.getStok_adeti());
                    System.out.println("Ürün Adeti Giriniz  :  ");
                    int adet = scan.nextInt();
                    if(adet > Integer.valueOf(urun.getStok_adeti())) {
                        System.out.println("Stokta yeterli miktarda ürün bulunmadığından sepete " + urun.getStok_adeti() + " adet ürün eklenecek.");
                        adet = Integer.valueOf(urun.getStok_adeti());
                    }
                    sepet2.setBarkod_no(urun.getBarkod_no());
                    sepet2.setUrun_adeti(String.valueOf(adet));
                    sepet2.setUrun_isim(urun.getIsim());
                    sepet2.setToplam_fiyat(adet * urun.getFiyat());
                    System.out.println("-----------------------------------------------"); 
                    System.out.println("Sepete '" + sepet2.getUrun_isim() + "' ürünü eklendi");
                    System.out.println("0. Geri Dön");
                    System.out.println("-----------------------------------------------");
                    scanner.nextLine();
                    HomePage(musteri);
                }
                break;
            }
            case "2": {
                sepet2.setBarkod_no(null);
                System.out.println("Sepet temizlendi.");
                System.out.println("0. Geri Dön");
                scanner.nextLine();
                HomePage(musteri);
                break;
            }
            case "3": {
                System.out.println("--------------------------------------------");
                System.out.println("Lütfen ürün aramak için bir anahtar giriniz : ");
                String key = scanner.nextLine();
                System.out.println("Bu arama için sonuçlar : ");
                System.out.println("# - Ürün İsmi - Ürün Fiyatı - Stok Adeti");
                int counter = 0;
                for(Urun u : dIO.urunAra(key)) {
                    System.out.println("#" + (counter + 1) + " - " + u.getIsim() + " - " + u.getFiyat() + " - " + u.getStok_adeti());
                    counter++;
                }
                System.out.println("0. Geri Dön");
                System.out.println("--------------------------------------------");
                scanner.nextLine();
                HomePage(musteri);
                break;
            }
            case "4": {
                System.out.println(sepet2.getUrun_isim());
                if(sepet2.getBarkod_no() == null) {
                    System.out.println("Sepetiniz boş görünüyor.");
                    System.out.println("0. Geri Dön");
                    scanner.nextLine();
                    HomePage(musteri);
                }
                else {
                    System.out.println("--------------------------------------------------");
                    System.out.println("Sepetiniz : ");
                    System.out.println("#Barkod No - Ürün İsmi - Ürün Adeti - Toplam Fiyat");
                    System.out.println(sepet2.getBarkod_no() + " - " + sepet2.getUrun_isim() + " - " + sepet2.getUrun_adeti() + " - " + sepet2.getToplam_fiyat());
                    System.out.println("1. Sipariş İsteği Oluştur");
                    System.out.println("2. Geri Dön");
                    Scanner scan = new Scanner(System.in);
                    if(scan.nextInt() == 1) {
                        dIO.siparisIstegiOlustur(sepet2.getBarkod_no(), sepet2.getUrun_adeti(), sepet2.getToplam_fiyat(), musteri.getMusteri_no());
                        System.out.println("0. Geri Dön");
                        scanner.nextLine();
                        HomePage(musteri);
                    }
                    else HomePage(musteri);
                }
                break;
            }
            case "5": {
                System.out.println("-----------------------------------------");
                System.out.println("#Sipariş No - Barkod No - Ürün Adeti - Toplam Fiyat");
                for(Siparis s : dIO.gecmisSiparis(musteri.getMusteri_no())) {
                    System.out.println("#" + s.getSiparis_no() + " - " + s.getBarkod_no() + " - " + s.getUrun_adeti() + " - " + s.getBu_urunun_toplam_fiyati());
                }
                System.out.println("0. Geri dön");
                System.out.println("-----------------------------------------");
                scanner.nextLine();
                HomePage(musteri);
                break;
            }
            case "6": {
                System.out.println("Çıkış yapılıyor...");
                break;
            }
            default: HomePage(musteri);
        }
    }
    public static void HomePage(Calisan calisan) throws IOException {
        Scanner scanner = new Scanner(System.in);
        DatabaseIO dIO = new DatabaseIO();
        System.out.println("------------------------------------");
        System.out.println("Hoşgeldin : " + calisan.getIsim());
        System.out.println("1. Sipariş Onayı Ver");
        System.out.println("2. Stok Miktarı Azalmış Ürünler");
        System.out.println("3. Ürünün Stok Miktarını Güncelle");
        System.out.println("4. Tedarikçiden Ürün Talep Et");
        System.out.println("5. Onayladığın Siparişler");
        System.out.println("6. Çıkış Yap");
        System.out.println("------------------------------------");
        String choosen = scanner.nextLine();
        switch(choosen) {
            case "1": {
                System.out.println("-----------------------------------------");
                System.out.println("# - Sipariş No - Barkod No - Ürün Adeti - Toplam Fiyat");
                int counter = 0;
                ArrayList<SiparisIstegi> siparisIstList = dIO.tumSiparisIstekleri();
                for(SiparisIstegi s : siparisIstList) {
                    counter++;
                    System.out.println("#" + (counter) + " - " + s.getSiparis_no() + " - " + s.getBarkod_no() + " - " + s.getUrun_adeti() + " - " + s.getBu_urunun_toplam_fiyati());
                }
                Scanner scan = new Scanner(System.in);
                System.out.println("Onaylanacak Siparişin Sıra Numarasını Giriniz : ");
                int no = scan.nextInt();
                if(no > siparisIstList.size() || no < 1) {
                    System.out.println("Bu sıraya ait bir ürün bulunmuyor.");
                    System.out.println("0. Geri Dön");
                    scanner.nextLine();
                    HomePage(calisan);
                }
                else {
                    System.out.println("Seçilen Sipariş İsteği No : " + siparisIstList.get(no - 1).getSiparis_no());
                    System.out.println("1. Sipariş İsteğini Onayla");
                    System.out.println("2. Geri dön");
                    System.out.println("-----------------------------------------");
                    switch(scan.nextInt()) {
                        case 1: {
                            SiparisIstegi s = siparisIstList.get(no - 1);
                            dIO.stokGuncelle(s.getBarkod_no(), dIO.getUrunAdeti(s.getBarkod_no()) - s.getUrun_adeti());
                            dIO.siparisOlustur(s, calisan.getCalisan_no());
                            System.out.println("0. Geri Dön");
                            scanner.nextLine();
                            HomePage(calisan);
                            break;
                        }
                        default:HomePage(calisan);
                    }
                }
                break;
            }
            case "2": {
                Scanner scan = new Scanner(System.in);
                System.out.println("Stok miktarı kaçtan itibaren az olan ürünler listelensin?");
                int max = scan.nextInt();
                ArrayList<Urun> urunlerList = dIO.azalmisUrunler(max);
                if(urunlerList.size() < 1) System.out.println("Bu kriterlere uygun ürün bulunamadı.");
                else {
                    System.out.println("#Barkod No - Ürün İsmi  - Stok Adeti - Fiyat");
                    for(Urun u : urunlerList)
                        System.out.println("#" + u.getBarkod_no() + " - " + u.getIsim() + " - " + u.getStok_adeti() + " - " + u.getFiyat());
                }
                System.out.println("0. Geri Dön");
                scanner.nextLine();
                HomePage(calisan);
                break;
            }
            case "3": {
                int counter = 0;
                ArrayList<Urun> urunlerList = dIO.tumUrunler();
                System.out.println("----------------------------------------");
                System.out.println("# - Ürün İsmi - Ürün Fiyatı - Stok Adeti");
                for(Urun u : urunlerList) {
                    System.out.println("#" + (counter + 1) + " - " + u.getIsim() + " - " + u.getFiyat() + " - " + u.getStok_adeti());
                    counter++;
                }
                System.out.println("0. Geri dön");
                System.out.println("----------------------------------------");
                System.out.println("Stok adetini güncellemek istediğiniz ürünün numarasını giriniz.");
                Scanner scan = new Scanner(System.in);
                int urun_no = scan.nextInt();
                if(urun_no == 0) HomePage(calisan);
                else if(urun_no > urunlerList.size() || urun_no < 1) {
                    System.out.println("Bu numaraya ait ürün bulunmamaktadır.");
                    System.out.println("0. Geri Dön");
                    scanner.nextLine();
                    HomePage(calisan);
                }
                else {
                    Urun urun = urunlerList.get(urun_no - 1);
                    System.out.println("Seçilen ürün : " +  urun.getIsim() + " - Fiyat : " + urun.getFiyat() + " - " + urun.getStok_adeti());
                    System.out.println("Yeni stok adeti giriniz  :  ");
                    int yeniAdet = scan.nextInt();
                    dIO.stokGuncelle(urun.getBarkod_no(), yeniAdet);
                    System.out.println("-----------------------------------------------");
                    System.out.println("0. Geri Dön");
                    System.out.println("-----------------------------------------------");
                    scanner.nextLine();
                    HomePage(calisan);
                }
                break;
            }
            case "4": {
                int counter = 0;
                ArrayList<Urun> urunlerList = dIO.tumUrunler();
                System.out.println("----------------------------------------");
                System.out.println("# - Ürün İsmi - Ürün Fiyatı - Stok Adeti");
                for(Urun u : urunlerList) {
                    System.out.println("#" + (counter + 1) + " - " + u.getIsim() + " - " + u.getFiyat() + " - " + u.getStok_adeti());
                    counter++;
                }
                System.out.println("0. Geri dön");
                System.out.println("----------------------------------------");
                System.out.println("Talep etmek istediğiniz ürünün numarasını giriniz.");
                Scanner scan = new Scanner(System.in);
                int urun_no = scan.nextInt();
                if(urun_no == 0) HomePage(calisan);
                else if(urun_no > urunlerList.size() || urun_no < 1) {
                    System.out.println("Bu numaraya ait ürün bulunmamaktadır.");
                    System.out.println("0. Geri Dön");
                    scanner.nextLine();
                    HomePage(calisan);
                }
                else {
                    Urun urun = urunlerList.get(urun_no - 1);
                    System.out.print("Ürün adetini giriniz ");
                    int adet = scan.nextInt();
                    dIO.urunTalebiOlustur(urun.getBarkod_no(), 
                            Integer.valueOf(urun.getStok_adeti()), 
                            urun.getTedarikci_id());
                    System.out.println("0. Geri Dön");
                    scanner.nextLine();
                    HomePage(calisan);
                }
                break;
            }
            case "5": {
                System.out.println("#Sipariş No - Barkod No - Ürün Adeti - Toplam Fiyat - Müşteri No");
                ArrayList<Siparis> siparisList = dIO.siparisOnaylayan(calisan.getCalisan_no());
                if(siparisList.size() < 1) System.out.println("Onayladığınız sipariş bulunmuyor.");
                else {
                    for(Siparis s : dIO.siparisOnaylayan(calisan.getCalisan_no()))
                    System.out.println("#" + s.getSiparis_no() + " - " + s.getBarkod_no() + " - " + s.getUrun_adeti() + " - " + s.getBu_urunun_toplam_fiyati() + " - " + s.getMusteri_no());
                }
                System.out.println("-----------------------------------------------");
                System.out.println("0. Geri Dön");
                scanner.nextLine();
                HomePage(calisan);
                break;
            }
            case "6": {
                break;
            }
            default: HomePage(calisan);
        }
    }
    public static void HomePage(Yetkili yetkili) throws IOException {
        Scanner scanner = new Scanner(System.in);
        DatabaseIO dIO = new DatabaseIO();
        System.out.println("------------------------------------");
        System.out.println("Hoşgeldin : " + yetkili.getIsim());
        System.out.println("1. Sipariş Onayı Ver");
        System.out.println("2. Stok Miktarı Azalmış Ürünler");
        System.out.println("3. Ürünün Stok Miktarını Güncelle");
        System.out.println("4. Tedarikçiden Ürün Talep Et");
        System.out.println("5. Onayladığın Siparişler");
        System.out.println("6. Ürün Kaydı Yap");
        System.out.println("7. Tedarikçi Kaydı Yap");
        System.out.println("8. Çalışan Hesabı Oluştur");
        System.out.println("9. Yetkili Hesabı Oluştur");
        System.out.println("10. Çıkış Yap");
        System.out.println("------------------------------------");
        String choosen = scanner.nextLine();
        switch(choosen) {
            case "1": {
                System.out.println("-----------------------------------------");
                System.out.println("# - Sipariş No - Barkod No - Ürün Adeti - Toplam Fiyat");
                int counter = 0;
                ArrayList<SiparisIstegi> siparisIstList = dIO.tumSiparisIstekleri();
                for(SiparisIstegi s : siparisIstList) {
                    counter++;
                    System.out.println("#" + (counter) + " - " + s.getSiparis_no() + " - " + s.getBarkod_no() + " - " + s.getUrun_adeti() + " - " + s.getBu_urunun_toplam_fiyati());
                }
                Scanner scan = new Scanner(System.in);
                System.out.println("Onaylanacak Siparişin Sıra Numarasını Giriniz : ");
                int no = scan.nextInt();
                if(no > siparisIstList.size() || no < 1) {
                    System.out.println("Bu sıraya ait bir ürün bulunmuyor.");
                    System.out.println("0. Geri Dön");
                    scanner.nextLine();
                    HomePage(yetkili);
                }
                else {
                    System.out.println("Seçilen Sipariş İsteği No : " + siparisIstList.get(no - 1).getSiparis_no());
                    System.out.println("1. Sipariş İsteğini Onayla");
                    System.out.println("2. Geri dön");
                    System.out.println("-----------------------------------------");
                    switch(scan.nextInt()) {
                        case 1: {
                            SiparisIstegi s = siparisIstList.get(no - 1);
                            dIO.stokGuncelle(s.getBarkod_no(), dIO.getUrunAdeti(s.getBarkod_no()) - s.getUrun_adeti());
                            dIO.siparisOlustur(s, yetkili.getCalisan_no());
                            System.out.println("0. Geri Dön");
                            scanner.nextLine();
                            HomePage(yetkili);
                            break;
                        }
                        default:HomePage(yetkili);
                    }
                }
                break;
            }
            case "2": {
                Scanner scan = new Scanner(System.in);
                System.out.println("Stok miktarı kaçtan itibaren az olan ürünler listelensin?");
                int max = scan.nextInt();
                ArrayList<Urun> urunlerList = dIO.azalmisUrunler(max);
                if(urunlerList.size() < 1) System.out.println("Bu kriterlere uygun ürün bulunamadı.");
                else {
                    System.out.println("#Barkod No - Ürün İsmi  - Stok Adeti - Fiyat");
                    for(Urun u : urunlerList)
                        System.out.println("#" + u.getBarkod_no() + " - " + u.getIsim() + " - " + u.getStok_adeti() + " - " + u.getFiyat());
                }
                System.out.println("0. Geri Dön");
                scanner.nextLine();
                HomePage(yetkili);
                break;
            }
            case "3": {
                int counter = 0;
                ArrayList<Urun> urunlerList = dIO.tumUrunler();
                System.out.println("----------------------------------------");
                System.out.println("# - Ürün İsmi - Ürün Fiyatı - Stok Adeti");
                for(Urun u : urunlerList) {
                    System.out.println("#" + (counter + 1) + " - " + u.getIsim() + " - " + u.getFiyat() + " - " + u.getStok_adeti());
                    counter++;
                }
                System.out.println("0. Geri dön");
                System.out.println("----------------------------------------");
                System.out.println("Stok adetini güncellemek istediğiniz ürünün numarasını giriniz.");
                Scanner scan = new Scanner(System.in);
                int urun_no = scan.nextInt();
                if(urun_no == 0) HomePage(yetkili);
                else if(urun_no > urunlerList.size() || urun_no < 1) {
                    System.out.println("Bu numaraya ait ürün bulunmamaktadır.");
                    System.out.println("0. Geri Dön");
                    scanner.nextLine();
                    HomePage(yetkili);
                }
                else {
                    Urun urun = urunlerList.get(urun_no - 1);
                    System.out.println("Seçilen ürün : " +  urun.getIsim() + " - Fiyat : " + urun.getFiyat() + " - " + urun.getStok_adeti());
                    System.out.println("Yeni stok adeti giriniz  :  ");
                    int yeniAdet = scan.nextInt();
                    dIO.stokGuncelle(urun.getBarkod_no(), yeniAdet);
                    System.out.println("-----------------------------------------------");
                    System.out.println("0. Geri Dön");
                    System.out.println("-----------------------------------------------");
                    scanner.nextLine();
                    HomePage(yetkili);
                }
                break;
            }
            case "4": {
                int counter = 0;
                ArrayList<Urun> urunlerList = dIO.tumUrunler();
                System.out.println("----------------------------------------");
                System.out.println("# - Ürün İsmi - Ürün Fiyatı - Stok Adeti");
                for(Urun u : urunlerList) {
                    System.out.println("#" + (counter + 1) + " - " + u.getIsim() + " - " + u.getFiyat() + " - " + u.getStok_adeti());
                    counter++;
                }
                System.out.println("0. Geri dön");
                System.out.println("----------------------------------------");
                System.out.println("Talep etmek istediğiniz ürünün numarasını giriniz.");
                Scanner scan = new Scanner(System.in);
                int urun_no = scan.nextInt();
                if(urun_no == 0) HomePage(yetkili);
                else if(urun_no > urunlerList.size() || urun_no < 1) {
                    System.out.println("Bu numaraya ait ürün bulunmamaktadır.");
                    System.out.println("0. Geri Dön");
                    scanner.nextLine();
                    HomePage(yetkili);
                }
                else {
                    Urun urun = urunlerList.get(urun_no - 1);
                    System.out.print("Ürün adetini giriniz ");
                    int adet = scan.nextInt();
                    dIO.urunTalebiOlustur(urun.getBarkod_no(), 
                            Integer.valueOf(urun.getStok_adeti()), 
                            urun.getTedarikci_id());
                    System.out.println("0. Geri Dön");
                    scanner.nextLine();
                    HomePage(yetkili);
                }
                break;
            }
            case "5": {
                System.out.println("#Sipariş No - Barkod No - Ürün Adeti - Toplam Fiyat - Müşteri No");
                ArrayList<Siparis> siparisList = dIO.siparisOnaylayan(yetkili.getCalisan_no());
                if(siparisList.size() < 1) System.out.println("Onayladığınız sipariş bulunmuyor.");
                else {
                    for(Siparis s : dIO.siparisOnaylayan(yetkili.getCalisan_no()))
                    System.out.println("#" + s.getSiparis_no() + " - " + s.getBarkod_no() + " - " + s.getUrun_adeti() + " - " + s.getBu_urunun_toplam_fiyati() + " - " + s.getMusteri_no());
                }
                System.out.println("-----------------------------------------------");
                System.out.println("0. Geri Dön");
                scanner.nextLine();
                HomePage(yetkili);
                break;
            }
            case "6": {
                Scanner scan = new Scanner(System.in);
                System.out.println("Ürün kaydı için gerekli bilgileri girmeniz gerekiyor!");
                System.out.print("İsim : ");
                String isim = scanner.nextLine();
                System.out.print("\nFiyat : ");
                String fiyat = String.valueOf(scan.nextInt());
                System.out.print("\nStok Adeti : ");
                String stok_adeti = String.valueOf(scan.nextInt());
                System.out.println("# - Tedarikçi Id - Tedarikçi İsim - Teslimat Süresi (Gün)");
                ArrayList<Tedarikci> tedarikciList = dIO.tumTedarikciler();
                int counter = 0;
                for(Tedarikci t : tedarikciList) {
                    System.out.println("#" + (counter + 1) + " - " + t.getTedarikci_id() + " - " + t.getIsim() + " - " + t.getTeslimat_süresi());
                    counter++;
                }
                System.out.println("Bu ürün için bir tedarikçi seçiniz");
                int sira = scan.nextInt();
                if(sira < 1 || sira > tedarikciList.size()) {
                    System.out.println("Bu numaraya ait tedarikçi bulunmamaktadır.");
                    System.out.println("0. Geri Dön");
                    scanner.nextLine();
                    HomePage(yetkili);
                }
                else {
                    Tedarikci tedarikci = tedarikciList.get(sira - 1);
                    System.out.println("Seçilen tedarikçi " + tedarikci.getIsim());
                    System.out.println("1. Ürünü Ekle");
                    System.out.println("2. Geri Dön");
                    switch(scan.nextInt()) {
                        case 1: {
                            dIO.urunEkle(isim, fiyat, stok_adeti, tedarikci.getTedarikci_id());
                            System.out.println("0. Geri Dön");
                            scanner.nextLine();
                            HomePage(yetkili);
                            break;
                        }
                        case 2: {
                            HomePage(yetkili);
                        }
                        default:HomePage(yetkili);
                    }
                }
                break;
            }
            case "7": {
                Scanner scan = new Scanner(System.in);
                System.out.println("Tedarikçi kaydı için gerekli bilgileri girmeniz gerekiyor!");
                System.out.print("İsim : ");
                String isim = scanner.nextLine();
                System.out.print("\nTeslimat Süresi (Gün) : ");
                String teslimat_suresi = String.valueOf(scan.nextInt());
                dIO.tedarikciEkle(isim, teslimat_suresi);
                System.out.println("0. Geri Dön");
                scanner.nextLine();
                HomePage(yetkili);
                break;
            }
            case "8": {
                System.out.println("Çalışan kaydı için gerekli bilgileri girmeniz gerekiyor!");
                System.out.print("İsim : ");
                String isim = scanner.nextLine();
                System.out.print("\nSoyisim : ");
                String soyisim = scanner.nextLine();
                System.out.print("\nSifre : ");
                String sifre = scanner.nextLine();
                System.out.print("\nTC : ");
                String tc = scanner.nextLine();
                System.out.print("\nTelefon : ");
                String telefon = scanner.nextLine();
                System.out.print("\nEmail : ");
                String email = scanner.nextLine();
                System.out.print("\nAdres : ");
                String adres = scanner.nextLine();
                System.out.print("\nMaaş : ");
                String maas = scanner.nextLine();
                dIO.calisanEkle(isim, soyisim, sifre, tc, telefon, email, adres, maas);
                System.out.println("---------------------------");
                System.out.println("0. Geri Dön");
                scanner.nextLine();
                HomePage(yetkili);
                break;
            }
            case "9": {
                System.out.println("Yetkili kaydı için gerekli bilgileri girmeniz gerekiyor!");
                System.out.print("İsim : ");
                String isim = scanner.nextLine();
                System.out.print("\nSoyisim : ");
                String soyisim = scanner.nextLine();
                System.out.print("\nSifre : ");
                String sifre = scanner.nextLine();
                System.out.print("\nTC : ");
                String tc = scanner.nextLine();
                System.out.print("\nTelefon : ");
                String telefon = scanner.nextLine();
                System.out.print("\nEmail : ");
                String email = scanner.nextLine();
                System.out.print("\nAdres : ");
                String adres = scanner.nextLine();
                System.out.print("\nMaaş : ");
                String maas = scanner.nextLine();
                dIO.yetkiliEkle(isim, soyisim, sifre, tc, telefon, email, adres, maas);
                System.out.println("---------------------------");
                System.out.println("0. Geri Dön");
                scanner.nextLine();
                HomePage(yetkili);
                break;
            }
            case "10": {
                break;
            }
            default: HomePage(yetkili);
        }
    }
}

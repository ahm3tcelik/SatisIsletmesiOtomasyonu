package satisisletmesi.Dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import satisisletmesi.Models.Calisan;
import satisisletmesi.Models.Musteri;
import satisisletmesi.Models.Siparis;
import satisisletmesi.Models.SiparisIstegi;
import satisisletmesi.Models.Tedarikci;
import satisisletmesi.Models.Urun;
import satisisletmesi.Models.Yetkili;
import satisisletmesi.Utils;

public class DatabaseIO {
    
    public Object girisYap(String email, String sifre) throws IOException {
        
        ArrayList<File> fileList = new ArrayList<>();
        fileList.add(new File(Utils.musteriPath));
        fileList.add(new File(Utils.calisanPath));
        fileList.add(new File(Utils.yetkiliPath));
        int counter = 0;
        for(File f : fileList) {
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            String line = "";
            while((line = br.readLine()) != null) {
                String[] data = line.split(";");
                if(data[2].equals(sifre) && data[5].equals(email)) {
                    switch (counter) {
                        case 0: 
                            return new Musteri(data[0], data[1], data[2], data[3],
                                    data[4], data[5], data[6], data[7], data[8]);
                        case 1:
                            return new Calisan(data[0], data[1], data[2], data[3],
                                    data[4], data[5], data[6], data[7], data[8]);
                        case 2:
                            return new Yetkili(data[0], data[1], data[2], data[3],
                                    data[4], data[5], data[6], data[7], data[8]);
                        default:
                            break;
                    }
                }
            }
            br.close();
            counter++;
        }
        return null;
    }
    
    public ArrayList<Urun> tumUrunler() throws IOException {
        ArrayList<Urun> results = new ArrayList<>();   
        File f = new File(Utils.urunPath);
        FileReader fr = new FileReader(f);
        BufferedReader br = new BufferedReader(fr);
        String line = "";
        while((line = br.readLine()) != null) {
            if(line.isEmpty()) continue;
            Urun urun = new Urun();
            String[] data = line.split(";"); 
            urun.setBarkod_no(data[0]);
            urun.setIsim(data[1]);
            urun.setFiyat(Integer.valueOf(data[2]));
            urun.setStok_adeti(data[3]);
            urun.setTedarikci_id(data[4]);
            results.add(urun);
        }
        br.close();
        return results;
    }
    
    public ArrayList<Musteri> tumMusteriler() throws IOException {
        ArrayList<Musteri> results = new ArrayList<>();
        File f = new File(Utils.musteriPath);
        FileReader fr = new FileReader(f);
        BufferedReader br = new BufferedReader(fr);
        String line = "";
        while((line = br.readLine()) != null) {
            Musteri musteri = new Musteri();
            String[] data = line.split(";"); 
            musteri.setIsim(data[0]);
            musteri.setSoyisim(data[1]);
            musteri.setSifre(data[2]);
            musteri.setTc(data[3]);
            musteri.setTelefon(data[4]);
            musteri.setEmail(data[5]);
            musteri.setAdres(data[6]);
            musteri.setMusteri_no(data[7]);
            results.add(musteri);
        }
        br.close();
        return results;
    }
    
    public ArrayList<Urun> urunAra(String key) throws IOException {
        key = key.toLowerCase();
        ArrayList<Urun> result = new ArrayList<>();
        File f = new File(Utils.urunPath);
        FileReader fr = new FileReader(f);
        BufferedReader br = new BufferedReader(fr);
        String line = "";
        while((line = br.readLine()) != null) {
            String[] data = line.split(";"); 
            if(data[1].contains(key)) {
                Urun urun = new Urun();
                urun.setBarkod_no(data[0]);
                urun.setIsim(data[1]);
                urun.setFiyat(Integer.valueOf(data[2]));
                urun.setStok_adeti(data[3]);
                urun.setTedarikci_id(data[4]);
                result.add(urun);
            }
        }
        br.close();
        return result;
    }
    
    public ArrayList<Urun> azalmisUrunler(int max) throws IOException {
        ArrayList<Urun> results = new ArrayList<>();   
        File f = new File(Utils.urunPath);
        FileReader fr = new FileReader(f);
        BufferedReader br = new BufferedReader(fr);
        String line = "";
        while((line = br.readLine()) != null) {
            if(line.isEmpty()) continue;
            String[] data = line.split(";");
            if(Integer.valueOf(data[3]) <= max) {
                Urun urun = new Urun();
                urun.setBarkod_no(data[0]);
                urun.setIsim(data[1]);
                urun.setFiyat(Integer.valueOf(data[2]));
                urun.setStok_adeti(data[3]);
                urun.setTedarikci_id(data[4]);
                results.add(urun);
            }
        }
        br.close();
        return results;
    }
    
    public int getUrunAdeti(String barkod_no) throws IOException {
        File f = new File(Utils.urunPath);
        FileReader fr = new FileReader(f);
        BufferedReader br = new BufferedReader(fr);
        String line = "";
        while((line = br.readLine()) != null) {
            if(line.isEmpty()) continue;
            String[] data = line.split(";");
            if(data[0].equals(barkod_no)) {
                return Integer.valueOf(data[3]);
            }
        }
        br.close();
        return 0;
    }
    
    public ArrayList<Siparis> gecmisSiparis(String musteri_no) throws IOException {
        ArrayList<Siparis> results = new ArrayList<>();
        File f = new File(Utils.siparisPath);
        FileReader fr = new FileReader(f);
        BufferedReader br = new BufferedReader(fr);
        String line = "";
        while((line = br.readLine()) != null) {
           String[] data = line.split(";"); 
            if(data[4].equals(musteri_no)) {
                Siparis siparis= new Siparis();
                siparis.setSiparis_no(data[0]);
                siparis.setBarkod_no(data[1]);
                siparis.setBu_urunun_toplam_fiyati(Integer.valueOf(data[2]));
                siparis.setUrun_adeti(Integer.valueOf(data[3]));
                siparis.setMusteri_no(data[4]);
                results.add(siparis);
            }

        }
        br.close();
        return results;
    }
    public ArrayList<Tedarikci> tumTedarikciler() throws IOException {
        ArrayList<Tedarikci> results = new ArrayList<>();
        File f = new File(Utils.tedarikciPath);
        FileReader fr = new FileReader(f);
        BufferedReader br = new BufferedReader(fr);
        String line = "";
        while((line = br.readLine()) != null) {
            System.out.println("okundu");
            Tedarikci tedarikci = new Tedarikci();
            String[] data = line.split(";"); 
            tedarikci.setIsim(data[0]);
            tedarikci.setTedarikci_id(data[1]);
            tedarikci.setTeslimat_süresi(data[2]);
            results.add(tedarikci);
        }
        br.close();
        return results;
    }
    
    public ArrayList<SiparisIstegi> tumSiparisIstekleri() throws IOException {
        ArrayList<SiparisIstegi> results = new ArrayList<>();
        File f = new File(Utils.siparisIstegiPath);
        FileReader fr = new FileReader(f);
        BufferedReader br = new BufferedReader(fr);
        String line = "";
        while((line = br.readLine()) != null) {
           String[] data = line.split(";");
           SiparisIstegi siparisIstegi = new SiparisIstegi();
           siparisIstegi.setSiparis_no(data[0]);
           siparisIstegi.setBarkod_no(data[1]);
           siparisIstegi.setUrun_adeti(Integer.valueOf(data[2]));
           siparisIstegi.setBu_urunun_toplam_fiyati(Integer.valueOf(data[3]));
           siparisIstegi.setMusteri_no(data[4]);
           results.add(siparisIstegi);
        }
        br.close();
        return results;
    }
    
    public ArrayList<Siparis> siparisOnaylayan(String personel_no) throws IOException {
        ArrayList<Siparis> results = new ArrayList<>();
            File f = new File(Utils.siparisPath);
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            String line = "";
            while((line = br.readLine()) != null) {
            String[] data = line.split(";"); 
            if(data[5].equals(personel_no)) {
                Siparis siparis = new Siparis();
                siparis.setSiparis_no(data[0]);
                siparis.setBarkod_no(data[1]);
                siparis.setUrun_adeti(Integer.valueOf(data[2]));
                siparis.setBu_urunun_toplam_fiyati(Integer.valueOf(data[3]));
                siparis.setMusteri_no(data[4]);
                siparis.setOnaylayan_personel_no(data[5]);
                results.add(siparis);
            }
                
        }
        return results;
    }
    
    public void siparisIstegiOlustur(String barkod_no, String urun_adeti, 
            int bu_urunun_toplam_fiyati, String musteri_no) throws IOException {
        Random rd = new Random();
        String siparis_no = String.format("%08d",rd.nextInt(99999999));
        File f = new File(Utils.siparisIstegiPath);
        FileWriter fr = new FileWriter(f, true);
        BufferedWriter bw = new BufferedWriter(fr);
        String line = "\n" + siparis_no + ";" + barkod_no + ";" + 
                urun_adeti + ";" + bu_urunun_toplam_fiyati + ";" + 
                musteri_no;
        bw.write(line);
        bw.flush();
        bw.close();
        System.out.println("Sipariş isteği başarıyla oluşturuldu!");
    }
    
    public void siparisOlustur(SiparisIstegi siparisIstegi, String yetkili_no) throws IOException {
        File f = new File(Utils.siparisPath);
        FileWriter fr = new FileWriter(f, true);
        BufferedWriter bw = new BufferedWriter(fr);
        String line = "\n" + siparisIstegi.getSiparis_no() + ";" + siparisIstegi.getBarkod_no() + ";" + 
                siparisIstegi.getUrun_adeti() + ";" + siparisIstegi.getBu_urunun_toplam_fiyati() + ";" + 
                siparisIstegi.getMusteri_no() + ";" + yetkili_no;
        bw.write(line);
        bw.flush();
        bw.close();
        System.out.println("Sipariş isteği başarıyla onaylandı!");
    }
    
    public void urunTalebiOlustur(String tedarikci_id, int adet,String barkod_no) throws IOException {
        
        File f = new File(Utils.urunTalebiPath);
        FileWriter fw = new FileWriter(f, true);
        BufferedWriter bw = new BufferedWriter(fw);
        String line = "\n" + barkod_no + ";" + adet + ";" + tedarikci_id;
        bw.write(line);
        bw.flush();
        bw.close();
        System.out.println("Ürün talebi başarıyla oluşturuldu!");
    } 
    
    public void musteriEkle(String isim, String soyisim, String sifre, String tc, 
            String telefon, String email, String adres, String odeme_sekli) throws IOException {
        Random rd = new Random();
        String musteri_no = String.format("%06d",rd.nextInt(999999));
        while(musteri_no.charAt(0) == '7' || musteri_no.charAt(0) == '8' ||
                musteri_no.charAt(0) == '9') {
            musteri_no = String.format("%06d",rd.nextInt(999999));
        }
        
        File f = new File(Utils.musteriPath);
        if(!f.exists()) {
            f.createNewFile();
        }
        FileWriter fw = new FileWriter(f,true);
        BufferedWriter bw = new BufferedWriter(fw);
        String line = "\n" + isim + ";" + soyisim + ";" + sifre + ";" + tc + ";" +
                telefon + ";" + email + ";" + adres + ";" + musteri_no + ";" +
                odeme_sekli;
        bw.write(line);
        bw.flush();
        bw.close();
        System.out.println("***************");
        System.out.println("Kayıt başarılı!");
    }
    
    public void calisanEkle(String isim, String soyisim, String sifre, String tc, 
            String telefon, String email, String adres, String maas) throws IOException {
        Random rd = new Random();
        String calisan_no = String.format("8%05d",rd.nextInt(99999));
        
        File f = new File(Utils.calisanPath);
        if(!f.exists()) {
            f.createNewFile();
        }
        FileWriter fw = new FileWriter(f,true);
        BufferedWriter bw = new BufferedWriter(fw);
        String line = "\n" + isim + ";" + soyisim + ";" + sifre + ";" + tc + ";" +
                telefon + ";" + email + ";" + adres + ";" + calisan_no + ";" +
                maas;
        bw.write(line);
        bw.flush();
        bw.close();
        System.out.println("-----------------------------------------");
        System.out.println("Yeni çalışan hesabı oluşturuldu : " + isim + " "  + soyisim);
    }
    
    public void yetkiliEkle(String isim, String soyisim, String sifre, String tc, 
            String telefon, String email, String adres, String maas) throws IOException {
        Random rd = new Random();
        String calisan_no = String.format("9%05d",rd.nextInt(99999));
        File f = new File(Utils.yetkiliPath);
        if(!f.exists()) {
            f.createNewFile();
        }
        FileWriter fw = new FileWriter(f,true);
        BufferedWriter bw = new BufferedWriter(fw);
        String line = "\n" + isim + ";" + soyisim + ";" + sifre + ";" + tc + ";" +
                telefon + ";" + email + ";" + adres + ";" + calisan_no + ";" +
                maas;
        bw.write(line);
        bw.flush();
        bw.close();
        System.out.println("-------------------------------------------");
        System.out.println("Yeni yetkili hesabı oluşturuldu : " + isim + " "  + soyisim);
    }
    public void urunEkle(String isim, String fiyat, String stok_adeti, String tedarikci_id) throws IOException {
        Random rd = new Random();
        String barkod_no = String.format("%06d%07d", rd.nextInt(999999), rd.nextInt(9999999));
        File f = new File(Utils.urunPath);
        if(!f.exists())
            f.createNewFile();
        FileWriter fw = new FileWriter(f,true);
        BufferedWriter bw = new BufferedWriter(fw);
        String line = "\n" + barkod_no + ";" + isim + ";" + fiyat + ";" + 
                stok_adeti + ";" + tedarikci_id; 
        bw.write(line);
        bw.flush();
        bw.close();
        System.out.println("----------------------");
        System.out.println("Yeni ürün oluşturuldu");
    }
    
    public void tedarikciEkle(String isim,String teslimat_suresi) throws IOException {
        Random rd = new Random();
        String tedarikci_id = String.format("7%05d",rd.nextInt(99999));
        File f = new File(Utils.tedarikciPath);
        FileWriter fw = new FileWriter(f, true);
        BufferedWriter bw = new BufferedWriter(fw);
        String line = "\n" + isim + ";" + tedarikci_id + ";" + 
                teslimat_suresi ;
        bw.write(line);
        bw.flush();
        bw.close();
        System.out.println("Tedarikçi Kaydedildi!");
        
    } 
    public void stokGuncelle(String barkod_no, int yeniAdet) throws IOException {
        if(yeniAdet < 0) {
            System.out.println("Geçersiz sayı");
            return;
        } 
        Path path = Paths.get(Utils.urunPath);
        List<String> fileContent = new ArrayList<>(Files.readAllLines(path));
        
        for (int i = 0; i < fileContent.size(); i++) {
            System.out.println(fileContent.get(i));
            if(fileContent.get(i).isEmpty()) continue;
            String data[] = fileContent.get(i).split(";");
            if (data[0].equals(barkod_no)) {
                data[3] = String.valueOf(yeniAdet);
                fileContent.set(i, String.format("%s;%s;%s;%s;%s", data[0],
                        data[1], data[2], data[3], data[4]));
                System.out.println(fileContent.get(i));
                break;
            }
        }
        Files.write(path, fileContent);
        System.out.println("Bu ürünün yeni stok adeti " + yeniAdet);
    }
}

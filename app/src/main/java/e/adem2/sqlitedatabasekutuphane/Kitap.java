package e.adem2.sqlitedatabasekutuphane;

import java.io.Serializable;

public class Kitap implements Serializable {

    public int id;
    public String kitap_adi;
    public String barkod;

    public Kitap(){
    }

    public Kitap(String kitap_adi, String barkod) {
        this.id = id;
        this.kitap_adi = kitap_adi;
        this.barkod = barkod;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKitap_adi() {
        return kitap_adi;
    }

    public void setKitap_adi(String kitap_adi) {
        this.kitap_adi = kitap_adi;
    }

    public String getBarkod() {
        return barkod;
    }

    public void setBarkod(String barkod) {
        this.barkod = barkod;
    }
}

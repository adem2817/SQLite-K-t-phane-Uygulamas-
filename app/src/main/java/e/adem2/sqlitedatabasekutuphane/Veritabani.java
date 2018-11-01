package e.adem2.sqlitedatabasekutuphane;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class Veritabani extends SQLiteOpenHelper{

    public static final String VT_ADI ="ISMEK";
    public  static final String TABLO_ADI="kitaplar";

    public Veritabani(Context context, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, VT_ADI, factory, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //sql cümleciği tanımlandı
        String sqlCumlesi = "CREATE TABLE " + TABLO_ADI + "(id INTEGER PRIMARY KEY AUTOINCREMENT,barkod_no TEXT,kitap_ismi TEXT" + ")";

        //sql cümlesi database e gönderildi
        db.execSQL(sqlCumlesi);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    public void yeniKitapEkle(Kitap kitap){
        SQLiteDatabase db = this.getWritableDatabase();

        //veriler ContentValues nesnesi içinde tutuldu.daha düzenli bir yapı olması için
        //Hashmap gibi bir yapıya sahip
        ContentValues veriler = new ContentValues();
        //veriler.put("id",kitap.getId());
        veriler.put("barkod_no",kitap.getBarkod());
        veriler.put("kitap_ismi",kitap.getKitap_adi());

        db.insert(TABLO_ADI,null,veriler);
        db.close();

    }

    public List<Kitap> kitaplariGetir(){

        List<Kitap> kitaplar = new ArrayList<Kitap>();

        //SQL nesnesi oluşturuldu writable izni verildi
        SQLiteDatabase database = this.getWritableDatabase();

        /** 1. yöntem
         String sqlCumlesi = "SELECT * FROM " + TABLO_ADI;
         Cursor cursorNesnesi = database.rawQuery(sqlCumlesi,null);
         */

        //veritabanını cursor a atar
        Cursor cursor = database.query(TABLO_ADI,new String[] {"id","barkod_no","kitap_ismi"},null,
                null,null,null,null);

        //veritabanını cursor ile gezip kitaplar listesine atar
        while (cursor.moveToNext()){

            Kitap kitap = new Kitap();
            kitap.setBarkod(cursor.getString(1));
            kitap.setKitap_adi(cursor.getString(2));

            kitaplar.add(kitap);
        }
        return kitaplar;
    }

    public List<Kitap> araGetir(String isim){
        List<Kitap> kitaplar = new ArrayList<Kitap>();

        SQLiteDatabase database = this.getWritableDatabase();

        Cursor cursor = database.query(TABLO_ADI,new String[] {"id","barkod_no","kitap_ismi"},"kitap_ismi LIKE '%"+
                isim +"%'",null,null,null,null);

        while (cursor.moveToNext()){

            Kitap kitap = new Kitap();
            kitap.setBarkod(cursor.getString(1));
            kitap.setKitap_adi(cursor.getString(2));

            kitaplar.add(kitap);
        }
        return kitaplar;
    }
}

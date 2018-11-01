package e.adem2.sqlitedatabasekutuphane;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button kaydetButon,araButon;
    EditText kitapAdi,barkod,araEdit;
    ListView listView;
    String k_adi,b_no, kitap;

    Veritabani vt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        kaydetButon = findViewById(R.id.btn_kaydet);
        kitapAdi = findViewById(R.id.kitap_adi);
        barkod = findViewById(R.id.barkod_no);
        listView = findViewById(R.id.listView);
        araEdit = findViewById(R.id.edt_ara);
        //araButon = findViewById(R.id.btn_ara);

        vt = new Veritabani(getApplicationContext(),null,1);

        kaydetButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                k_adi = kitapAdi.getText().toString();
                b_no = barkod.getText().toString();

                Kitap kitap = new Kitap(k_adi,b_no);

                vt.yeniKitapEkle(kitap);
                Toast.makeText(MainActivity.this, "veritabanına kaydedildi", Toast.LENGTH_SHORT).show();

                kayitYenile();
            }
        });

        kayitYenile();


		//EditText anlık listener özelliği için bu metod kullanıldı. Bu sayede tuşa başılmasıyla birlikte arama yapılıyor
        araEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                kitap = araEdit.getText().toString();
                List<Kitap> veriler = vt.araGetir(kitap);
                OzelAdapter adaptor = new OzelAdapter(MainActivity.this,veriler);
                listView.setAdapter(adaptor);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        /*araButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kitap = araEdit.getText().toString();
                List<Kitap> veriler = vt.araGetir(kitap);
                OzelAdapter adaptor = new OzelAdapter(MainActivity.this,veriler);
                listView.setAdapter(adaptor);
            }
        });*/

    }



    public void kayitYenile(){
        List<Kitap> veriler = vt.kitaplariGetir();
        OzelAdapter adaptor = new OzelAdapter(MainActivity.this,veriler);
        listView.setAdapter(adaptor);

    }
}

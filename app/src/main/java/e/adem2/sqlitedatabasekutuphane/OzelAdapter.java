package e.adem2.sqlitedatabasekutuphane;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class OzelAdapter extends BaseAdapter{
    private LayoutInflater mInflater;
    private List<Kitap> mKitapListesi;

    public OzelAdapter(Activity activity, List<Kitap> kitaplar) {
        //XML'i alıp View'a çevirecek inflater'ı örnekleyelim
        mInflater = (LayoutInflater) activity.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
        //gösterilecek listeyi de alalım
        mKitapListesi = kitaplar;
    }

    @Override
    public int getCount() {
        return mKitapListesi.size();
    }

    @Override
    public Kitap getItem(int position) {
        //şöyle de olabilir: public Object getItem(int position)
        return mKitapListesi.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View satirView;

        satirView = mInflater.inflate(R.layout.row_item, null);

        TextView textView1 =(TextView) satirView.findViewById(R.id.barkod);
        TextView textView2 =(TextView) satirView.findViewById(R.id.kitap);

        Kitap kitap = mKitapListesi.get(position);

        textView1.setText(kitap.getBarkod());
        textView2.setText(kitap.getKitap_adi());


        return satirView;
    }
}

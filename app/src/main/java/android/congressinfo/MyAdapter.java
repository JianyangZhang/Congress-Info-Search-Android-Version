package android.congressinfo;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import com.squareup.picasso.Picasso;
import java.util.List;
import java.util.Map;

public class MyAdapter extends SimpleAdapter {

    public MyAdapter(Context context, List<? extends Map<String, ?>> data, int resource, String[] from, int[] to) {
        super(context, data, resource, from, to);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View v = super.getView(position, convertView, parent);
        ImageView img = (ImageView) v.getTag();
        if (img == null) {
            img = (ImageView) v.findViewById(R.id.imageView_legislators_1);
            v.setTag(img);
        }
        String url = (String) ((Map) getItem(position)).get("photo");
        Picasso.with(v.getContext()).load(url).fit().into(img);
        return v;
    }
}
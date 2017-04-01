package raspi.alphabetagamma.smarthome;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.List;
import java.util.Map;

/**
 * Created by shreyngd on 1/4/17.
 */

public class Myadapter extends SimpleAdapter {
    private Context ctx;


    /**
     * Constructor
     *
     * @param context  The context where the View associated with this SimpleAdapter is running
     * @param data     A List of Maps. Each entry in the List corresponds to one row in the list. The
     *                 Maps contain the data for each row, and should include all the entries specified in
     *                 "from"
     * @param resource Resource identifier of a view layout that defines the views for this list
     *                 item. The layout file should include at least those named views defined in "to"
     * @param from     A list of column names that will be added to the Map associated with each
     *                 item.
     * @param to       The views that should display column in the "from" parameter. These should all be
     *                 TextViews. The first N views in this list are given the values of the first N columns
     */
    public Myadapter(Context context, List<? extends Map<String, ?>> data, int resource, String[] from, int[] to) {
        super(context, data, resource, from, to);
        ctx=context;
    }

    public View getView(final int position, View convertView, ViewGroup parent){
        View v=super.getView(position,convertView,parent);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ctx,"onclick item",Toast.LENGTH_SHORT).show();
            }
        });

            Button onOff= (Button) v.findViewById(R.id.onoff);


        Button setTimer= (Button) v.findViewById(R.id.settime);

                onOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ctx,"onoff"+((Map)getItem(position)).get("name"),Toast.LENGTH_SHORT).show();

            }
        });

        setTimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ctx,"setTimer"+position,Toast.LENGTH_SHORT).show();
            }
        });
    return v;
    }

}

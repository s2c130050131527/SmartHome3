package raspi.alphabetagamma.smarthome;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;

import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Main2Activity extends AppCompatActivity implements LoadJSONTask.Listener{

    private ListView mListView;
    private final String URL="http://192.168.0.107/test.php";
    private List<HashMap<String, String>> mAndroidMapList ;

    private static final String KEY_NAME = "name";
    private static final String KEY_STATUS = "status";
    private Button checkStatus=null;
    private LoadJSONTask.Listener listener=this;


    private Context ctx=this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAndroidMapList = new ArrayList<>();
        checkStatus=(Button) findViewById(R.id.checkstatus);
        mListView = (ListView) findViewById(R.id.list_view);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("ISit", "onItemClick: "+id);
                Toast.makeText(Main2Activity.this, mAndroidMapList.get(position).get(KEY_NAME), Toast.LENGTH_LONG).show();
            }
        });
        new LoadJSONTask(ctx, listener).execute(URL);

        checkStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ctx,"List Updated",Toast.LENGTH_LONG).show();
                mAndroidMapList.clear();
                new LoadJSONTask(ctx, listener).execute(URL);

            }
        });


    }


    @Override
    public void onLoaded(List<DeviceStats> deviceStatsList) {

        for (DeviceStats deviceStats: deviceStatsList) {

            HashMap<String, String> map = new HashMap<>();

            map.put(KEY_NAME, deviceStats.getName());
            map.put(KEY_STATUS, deviceStats.getStatus());


            mAndroidMapList.add(map);
        }

        loadListView();

    }

    @Override
    public void onError() {
        Toast.makeText(this, "Error !", Toast.LENGTH_SHORT).show();
    }




    private void loadListView() {

        ListAdapter adapter = new Myadapter(Main2Activity.this, mAndroidMapList, R.layout.list_item,
                new String[] { KEY_NAME, KEY_STATUS },
                new int[] { R.id.name, R.id.status });

        mListView.setAdapter(adapter);

    }
}

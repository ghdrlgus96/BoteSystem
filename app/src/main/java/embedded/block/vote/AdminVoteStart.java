package embedded.block.vote;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class AdminVoteStart extends AppCompatActivity implements View.OnClickListener{

    Toolbar toolbar;
    private RequestQueue queue;
    public static Activity th;
    private JSONObject jsonObject = new JSONObject();
    private JSONArray jsonArray = new JSONArray();
    private ArrayList<AdminVoteItem> data = new ArrayList<AdminVoteItem>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_start);
        toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        final ListView listView = (ListView) findViewById(R.id.admin_start);
        th = this;

        final RequestQueue requestQueue = Volley.newRequestQueue(this);
        final String url = "http://203.249.127.32:65001/bote/vote/voteupdater/getlist/?userNum="+LoginActivity.Companion.getUserNum();


        queue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                String r = response;
                AdminVoteItem vote;
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                try {
                    jsonArray = new JSONArray(response);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        jsonObject = jsonArray.getJSONObject(i);
                        vote = new AdminVoteItem(jsonObject.getInt("voteNum"), jsonObject.getString("voteName"), jsonObject.getString("quitTime"));
                        if(jsonObject.getString("quitTime") == "null")
                         data.add(vote);
                    }

                    AdminVoteListAdapter adapter = new AdminVoteListAdapter(getApplicationContext(), R.layout.admin_start_item, data);
                    listView.setAdapter(adapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        stringRequest.setTag("MAIN");
        queue.add(stringRequest);




        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(),AdminVoteStartClicked.class);

                intent.putExtra("voteNum",data.get(position).getVoteNum());
                startActivity(intent);
            }
        });
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }



    @Override
    public void onClick(View v) {

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}

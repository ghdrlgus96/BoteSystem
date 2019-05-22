package embedded.block.vote;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class AdminVoteStart extends AppCompatActivity implements View.OnClickListener{


    private RequestQueue queue;
    public static Activity th;
    private JSONObject jsonObject = new JSONObject();
    private JSONArray jsonArray = new JSONArray();
    private ArrayList<AdminVoteItem> data = new ArrayList<AdminVoteItem>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_start);
        final ListView listView = (ListView) findViewById(R.id.admin_start);
        th = this;
        //여기다 투표목록받아와야함
        // 샘플 데이터
//        data = new ArrayList<>();
//
//        AdminVoteItem vote1 = new AdminVoteItem(1,"안녕");
//        AdminVoteItem vote2 = new AdminVoteItem(2,"잘가");
//
//        data.add(vote1);
//        data.add(vote2);
        //데이터

        final RequestQueue requestQueue = Volley.newRequestQueue(this);
        //int userNum = Integer.parseInt(LoginActivity.Companion.getUserNum());
        final String url = "http://203.249.127.32:65001/bote/vote/voteupdater/getlist/?userNum="+LoginActivity.Companion.getUserNum();


        queue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                String r = response;
                //String date;
                //Date voteTime;
                AdminVoteItem vote;
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                try {
                   // jsonObject = new JSONObject(response);
                  //  jsonArray = jsonObject.getJSONArray("RowDataPacket");
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

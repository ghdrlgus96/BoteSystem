package embedded.block.vote;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.security.KeyStore;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AdminVoteStartClicked extends AppCompatActivity implements View.OnClickListener {
    @Override
    public void onClick(View v) {

    }

    private int voteNum;
    private int selectTime = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_start_clicked);
        Spinner spinner = (Spinner) findViewById(R.id.spinnerStart);
        Button startBtn = (Button) findViewById(R.id.startBtn);

        final RequestQueue requestQueue = Volley.newRequestQueue(this);
        final String url = "http://203.249.127.32:65001/bote/voteupdater/votereaper";

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                System.out.print(parent.getItemAtPosition(position));
                String str = parent.getItemAtPosition(position).toString();
                System.out.print(str);
                str = str.substring(0,1);
                System.out.print(str);
                selectTime = Integer.parseInt(str);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });
        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Date date = new Date();
                Calendar cal = Calendar.getInstance();
                cal.setTime(date);
                cal.add(Calendar.DATE, selectTime);
                Date voteTime = cal.getTime();

                JSONObject requestJsonObject = new JSONObject();
                try {
                    requestJsonObject.put("voteNume", voteNum);
                    requestJsonObject.put("voteTime", voteTime);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Intent intent = new Intent(getApplicationContext(), StartPopupActivity.class);
                startActivity(intent);

//                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,
//                        url, requestJsonObject, new Response.Listener<JSONObject>() {
//                    @Override
//                    public void onResponse(JSONObject response) {
//
//                    }
//                }, new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        error.printStackTrace();
//                    }
//                });
//                try {
//                    requestQueue.add(jsonObjectRequest);
//                    Intent intent = new Intent(getApplicationContext(), StartPopupActivity.class);
//                    startActivity(intent);
//                }catch(Exception e){
//
//                }
            }

        });
    }

}
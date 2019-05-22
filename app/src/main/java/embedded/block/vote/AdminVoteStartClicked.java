package embedded.block.vote;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import com.android.volley.*;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class AdminVoteStartClicked extends AppCompatActivity implements View.OnClickListener {


    @Override
    public void onClick(View v) {

    }
    private String quitTime;
    private int voteNum;
    private int selectTime = 1;
    public static Activity AActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        voteNum = intent.getIntExtra("voteNum",9999);

        setContentView(R.layout.admin_start_clicked);
        Spinner spinner = (Spinner) findViewById(R.id.spinnerStart);
        Button startBtn = (Button) findViewById(R.id.startBtn);

        final RequestQueue requestQueue = Volley.newRequestQueue(this);
        AActivity = this;

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
                final Date time = cal.getTime();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                quitTime = sdf.format(time);
                final String url = "http://203.249.127.32:65009/bote/vote/voteupdater/votereaper/?voteNum="+voteNum+"&quitTime="+quitTime;
                StringRequest stringRequest = new StringRequest(Request.Method.GET,
                       url, new Response.Listener<String>() {
                    @Override
                   public void onResponse(String response) {

                    }
                }, new Response.ErrorListener() {
                   @Override
                    public void onErrorResponse(VolleyError error) {
                       error.printStackTrace();
                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError{
                        Map<String, String> params=new HashMap<>();
                        params.put("quitTime",quitTime);
                        params.put("voteNum",Integer.toString(voteNum));
                        return params;
                    }
                };
                try {
                    requestQueue.add(stringRequest);
                    Intent intent = new Intent(getApplicationContext(), StartPopupActivity.class);
                    startActivity(intent);


                }catch(Exception e){

                }

            }

        });
    }

}
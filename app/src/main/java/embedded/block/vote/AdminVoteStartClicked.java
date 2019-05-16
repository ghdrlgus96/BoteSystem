package embedded.block.vote;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class AdminVoteStartClicked extends AppCompatActivity {
    private int voteNum;
    private String voteName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.admin_start_clicked);

        final String[] day = {"1일","2일","3일","4일","5일","6일","7일"};



        Spinner spinner = (Spinner) findViewById(R.id.spinnerStart);
        ArrayAdapter adapter = new ArrayAdapter(
                getApplicationContext(),
                R.layout.spin,
                day);
        adapter.setDropDownViewResource(
                R.layout.spin_dropdown);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                day.charAt(0);
            }

            }
    }
}
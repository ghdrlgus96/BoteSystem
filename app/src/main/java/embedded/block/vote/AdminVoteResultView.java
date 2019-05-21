package embedded.block.vote;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class AdminVoteResultView extends AppCompatActivity implements View.OnClickListener {
    private ArrayList<AdminVoteResultItem> data = new ArrayList<AdminVoteResultItem>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //갓재님아 툴바 좀 합쳐주ㅕㅇ
        android.support.v7.app.ActionBar ab = getSupportActionBar();
        ab.setTitle("투표 결과 열람");
        ab.setDisplayHomeAsUpEnabled(true);

        //툴바끝
        setContentView(R.layout.admin_start);
        ListView listView = (ListView) findViewById(R.id.admin_start);

        AdminVoteResultItem vote1 = new AdminVoteResultItem(36,"신윤재");
        AdminVoteResultItem vote2 = new AdminVoteResultItem(24,"홍기현");
        AdminVoteResultItem vote3 = new AdminVoteResultItem(12,"오상엽");
        AdminVoteResultItem vote4 = new AdminVoteResultItem(6,"황재천");

        data.add(vote1);
        data.add(vote2);
        data.add(vote3);
        data.add(vote4);

        AdminVoteResultListAdapter adapter = new AdminVoteResultListAdapter(this,R.layout.admin_result_item,data);
        listView.setAdapter(adapter);

    }
//툴바이벤트처리
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.action_bar,menu);
        return  true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case android.R.id.home:{
                finish();
                return true;
            }
            case R.id.action_button:{
                //열람승인
                Toast.makeText(getApplicationContext(), "승인되었습니다.", Toast.LENGTH_SHORT).show();
            }

        }
        return super.onOptionsItemSelected(item);
    }
    //끝
    @Override
    public void onBackPressed(){
        super.onBackPressed();
    }

    @Override
    public void onClick(View v) {

    }

}

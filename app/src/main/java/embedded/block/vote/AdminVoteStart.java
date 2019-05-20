package embedded.block.vote;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class AdminVoteStart extends AppCompatActivity implements View.OnClickListener{

    private ArrayList<AdminVoteItem> data = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_start);
        ListView listView = (ListView) findViewById(R.id.admin_start);
        //여기다 투표목록받아와야함
        // 샘플 데이터
        data = new ArrayList<>();

        AdminVoteItem vote1 = new AdminVoteItem(1,"안녕");
        AdminVoteItem vote2 = new AdminVoteItem(2,"잘가");

        data.add(vote1);
        data.add(vote2);
        //데이터
        AdminVoteListAdapter adapter = new AdminVoteListAdapter(this, R.layout.admin_start_item, data);
        listView.setAdapter(adapter);

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
}

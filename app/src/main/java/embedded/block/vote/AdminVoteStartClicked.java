package embedded.block.vote;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class AdminVoteStartClicked extends AppCompatActivity {
    private int voteNum;
    private String voteName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.admin_start_clicked);

        Intent intent = getIntent();


    }
}
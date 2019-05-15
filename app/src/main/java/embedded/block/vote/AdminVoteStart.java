package embedded.block.vote;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class AdminVoteStart extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.test);

        Intent intent = getIntent();

        TextView textView = (TextView) findViewById(R.id.textView);
        textView.setText("하위");
    }
}

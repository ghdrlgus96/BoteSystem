package embedded.block.vote;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class StartPopupActivity extends Activity implements View.OnClickListener{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.start_popup);

        Button finishBtn = (Button) findViewById(R.id.finishBtn);

        finishBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                AdminVoteStartClicked temp = (AdminVoteStartClicked) AdminVoteStartClicked.AActivity;
                AdminVoteStart temp2 = (AdminVoteStart)AdminVoteStart.th;
                finish();
                temp.finish();
                temp2.recreate();
            }
        });

    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction()==MotionEvent.ACTION_OUTSIDE){
            return false;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        return;
    }

    @Override
    public void onClick(View v) {

    }
}

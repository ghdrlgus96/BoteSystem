package embedded.block.vote;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

public class StartPopupActivity extends Activity implements View.OnClickListener{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //타이틀바 없애기
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.start_popup);

        Button finishBtn = (Button) findViewById(R.id.finishBtn);
        //test
       // Intent intent = new Intent(this.getIntent());
      //  textView.setText(intent.getStringExtra("text"));
        //f
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
        //바깥레이어 클릭시 안닫히게
        if(event.getAction()==MotionEvent.ACTION_OUTSIDE){
            return false;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        //안드로이드 백버튼 막기
        return;
    }

    @Override
    public void onClick(View v) {

    }
}

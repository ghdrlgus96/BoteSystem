package embedded.block.vote

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_findpass.*

class FindpassActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_findpass)

        findPassBtn.setOnClickListener {
            if(findPassID.text.toString() == "아이디") {
                Toast.makeText(this, "아이디 입력하였습니다", Toast.LENGTH_LONG).show()
                findPassID.text = "비밀번호 찾기 질문: "
            }
            else {
                Toast.makeText(this, "비밀번호 인증 메시지를 입력하였습니다", Toast.LENGTH_LONG).show()
                finish()
            }
        }
    }
}

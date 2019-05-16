package embedded.block.vote

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        regIdButton.setOnClickListener {
            Toast.makeText(this, "아이디 중복 버튼 클릭", Toast.LENGTH_SHORT).show()
        }

        regNeedButton.setOnClickListener {
            Toast.makeText(this, "회원가입 신청 버튼 클릭", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}

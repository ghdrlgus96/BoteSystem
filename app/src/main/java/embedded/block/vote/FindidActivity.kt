package embedded.block.vote

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_findid.*

class FindidActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_findid)

        findBtn.setOnClickListener {
            findId(findIDNameText.text.toString(), findIDPhone.text.toString())
        }
    }

    fun findId(name: String?, phone: String?) {

    }
}

package embedded.block.vote

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.widget.EditText
import android.widget.RatingBar
import kotlinx.android.synthetic.main.votepage_alertdiaglog.*
import kotlinx.android.synthetic.main.voteresult_list.*

class VotepageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.votepage_alertdiaglog)

        //어댑터 생성 및 리스트뷰에 어댑터 설정
        var adapter = AlertListViewAdapter(this)
        alert_listview.adapter = adapter

        btn_Cancel.setOnClickListener {
            finish()
        }
        btn_Ok.setOnClickListener{
            val builder = AlertDialog.Builder(this)
            val dialogView = layoutInflater.inflate(R.layout.votepage_choicealert, null)
            //후보자이름 가져와서 붙여넣기


            builder.setView(dialogView)
                .setPositiveButton("OK") { dialogInterface, i ->
                    finish()
                }
                .setNegativeButton("CANCEL") { dialogInterface, i ->

                }
                .show()
        }
        }
    }

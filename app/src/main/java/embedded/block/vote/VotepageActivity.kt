package embedded.block.vote

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.EditText
import android.widget.RatingBar
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import embedded.block.vote.VoteListAdapter.Companion.arr_getList
import kotlinx.android.synthetic.main.votepage_alertdiaglog.*
import kotlinx.android.synthetic.main.voteresult_list.*
import org.json.JSONArray
import org.json.JSONObject
import java.util.HashMap

class VotepageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.votepage_alertdiaglog)

        var votenum = intent.getStringExtra("votenum")


        Log.d("ktext", votenum + "!")

        var json = JSONObject()
        json.put("voteNum", "null")
        var queue: RequestQueue = Volley.newRequestQueue(this)
        val request = object : StringRequest(
            Request.Method.GET, "http://203.249.127.32:65009/bote/vote/votestarter/getcandidate/?voteNum="+ votenum,
            Response.Listener { response ->
                run {
                    Log.d("ktext", response.toString())
                    val arr_getPage= JSONArray(response.toString())
                    var string = arr_getPage.toString()
                    AlertListViewAdapter.arr_getPage = JSONArray(string)

                    var adapter = AlertListViewAdapter(this)
                    alert_listview.adapter = adapter
                    //Log.d("ktext", arr_getList.getJSONObject(1).getString("voteNum").toString())
                }
            }, null
        ) {
            @Throws(AuthFailureError::class)
            override fun getHeaders(): MutableMap<String, String>? {
                val headers = HashMap<String, String>()
                headers.put("Content-Type", "application/json")
                return headers
            }
        }
        queue.add(request)
        //어댑터 생성 및 리스트뷰에 어댑터 설정

        //arr_getPage
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

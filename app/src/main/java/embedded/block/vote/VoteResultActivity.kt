package embedded.block.vote

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.voteresult_list.*
import org.json.JSONArray
import org.json.JSONObject
import java.util.HashMap

class VoteResultActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.voteresult_list)

        var resultVoteNum = intent.getStringExtra("votenum")

            var json = JSONObject()
            json.put("voteNum", "null")
            var queue: RequestQueue = Volley.newRequestQueue(this)
            val request = object : StringRequest(
                Request.Method.GET,
                "http://203.249.127.32:65009/bote/vote/voteresulter/voter/?voteNum=" + resultVoteNum,
                Response.Listener { response ->
                    run {
                        Log.d("ktext", response.toString())
                        val arr_ResultList = JSONArray(response.toString())
                        var string = arr_ResultList.toString()
                        VoteResultListAdapter.arr_ResultList = JSONArray(string)
                        Log.d("etest", "제헌확인" + string)
                        var adapter = VoteResultListAdapter(this)
                        voteresult_listview.adapter = adapter
                    }
                },
                null
            ) {
                @Throws(AuthFailureError::class)
                override fun getHeaders(): MutableMap<String, String>? {
                    val headers = HashMap<String, String>()
                    headers.put("Content-Type", "application/json")
                    return headers
                }
            }
            queue.add(request)

    }
    companion object {
        var voteNumber = ArrayList<String>()
    }
}

package embedded.block.vote

import android.app.Activity
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.votepage_alertdiaglog.*
import org.json.JSONArray
import org.json.JSONObject
import java.util.HashMap

class VotepageActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.votepage_alertdiaglog)
        var votenum = intent.getStringExtra("votenum")
        var quittime = intent.getStringExtra("quittime")

        var json = JSONObject()
        json.put("voteNum", "null")
        var queue: RequestQueue = Volley.newRequestQueue(this)
        val request = object : StringRequest(
            Request.Method.GET, LoginActivity.ipAdress+"65001/bote/vote/votestarter/getcandidate/?voteNum=" + votenum,
            Response.Listener { response ->
                run {
                    val arr_getPage = JSONArray(response.toString())
                    var string = arr_getPage.toString()
                    AlertListViewAdapter.arr_getPage = JSONArray(string)

                    var adapter = AlertListViewAdapter(this, quittime, votenum)
                    alert_listview.adapter = adapter
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
    }
 }

package embedded.block.vote

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.util.Log
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import embedded.block.vote.VoteListPagerAdapter
import embedded.block.vote.R
import kotlinx.android.synthetic.main.admin_start_item.*
import org.json.JSONArray
import org.json.JSONObject
import java.util.*
import java.text.SimpleDateFormat;

//(제헌)투표목록 화면 Mainactivity
class VoteListActivity : AppCompatActivity() {

    override fun onBackPressed() {
        voteNumber.clear()
        resultVoteNumber.clear()
        super.onBackPressed()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.votelist_main)

        val pagerAdapter = VoteListPagerAdapter(supportFragmentManager)
        val pager = findViewById<ViewPager>(R.id.Viewpage)

        var json = JSONObject()
        json.put("userNum", "null")
        var queue: RequestQueue = Volley.newRequestQueue(this)
        val request = object : StringRequest(
            Request.Method.GET,
            "http://203.249.127.32:65001/bote/vote/votestarter/getlist/?userNum=" + LoginActivity.userNum,
            Response.Listener { response ->
                run {
                    Log.d("ktext", response.toString())
                    val arr_getList = JSONArray(response.toString())
                    var string = arr_getList.toString()
                    VoteListAdapter.arr_getList = JSONArray(string)
                    //Log.d("ktext", arr_getList.getJSONObject(1).getString("voteNum").toString())
                    pager.adapter = pagerAdapter
                    pagerAdapter.notifyDataSetChanged()

                    for (i in 0..arr_getList.length() - 1)
                        voteNumber.add(arr_getList.getJSONObject(i).getString("voteNum").toString())

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

        var json2 = JSONObject()
        json2.put("userNum", "null")
        var queuet: RequestQueue = Volley.newRequestQueue(this)
        val requestt = object : StringRequest(
            Request.Method.GET,
            "http://203.249.127.32:65001/bote/vote/voteresulter/votergetlist/?userNum=" + LoginActivity.userNum,
            Response.Listener { response ->
                run {
                    Log.d("ptext", response.toString() + "qweqweqweqe")
                    val arr_getResultList = JSONArray(response.toString())

                    var string2 = arr_getResultList.toString()
                    VoteResultRecyclerAdapter.arr_getResultList = JSONArray(string2)
                    Log.d("ptext", arr_getResultList.getJSONObject(1).getString("voteNum").toString())

                    for (i in 0..arr_getResultList.length() - 1)
                        resultVoteNumber.add(arr_getResultList.getJSONObject(i).getString("voteNum").toString())
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
        queuet.add(requestt)

        val tab = findViewById<TabLayout>(R.id.tablayout_main)
        tab.setupWithViewPager(pager)

    }
    companion object {
        var voteNumber = ArrayList<String>()
        var resultVoteNumber = ArrayList<String>()
    }
}
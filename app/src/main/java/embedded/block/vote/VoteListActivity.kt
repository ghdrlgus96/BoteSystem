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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.votelist_main)

        val pagerAdapter = VoteListPagerAdapter(supportFragmentManager)
        val pager = findViewById<ViewPager>(R.id.Viewpage)
        pager.adapter = pagerAdapter
        pagerAdapter.notifyDataSetChanged()

        val tab = findViewById<TabLayout>(R.id.tablayout_main)
        tab.setupWithViewPager(pager)

            var json = JSONObject()
            json.put("userNum", "null")
            var queue: RequestQueue = Volley.newRequestQueue(this)
            val request = object : StringRequest(Request.Method.GET, "http://203.249.127.32:65009/bote/vote/votestarter/getlist/?userNum=" + LoginActivity.userNum,
                Response.Listener { response ->
                    run {
                        Log.d("ktext", response.toString())
                        val arr_getList = JSONArray(response.toString())
                        var string = arr_getList.toString()
                        VoteListAdapter.arr_getList = JSONArray(string)
                        //VoteResultRecyclerAdapter.arr_getResultList = JSONArray(string)

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



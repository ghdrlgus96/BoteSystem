package embedded.block.vote

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import embedded.block.vote.VoteListPagerAdapter
import embedded.block.vote.R
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

        val tab = findViewById<TabLayout>(R.id.tablayout_main)
        tab.setupWithViewPager(pager)
/*
            var json = JSONObject()
            json.put("userNum", "null")
            var queue: RequestQueue = Volley.newRequestQueue(this)
            val request = object : JsonObjectRequest(Request.Method.GET, "http://203.249.127.32:65001/bote/votestarter/getlist/?userNum=" + LoginActivity.userNum,json,
                Response.Listener { response ->
                    run {

                        var temp1 = response.getString("votename")
                        var tempList1 = temp1.split(",")

                        for (i in 0..tempList1.size - 1) {
                            voteName.add(tempList1.get(i))
                        }

                        /*
                        var quitTime = response.get("quittime") as ArrayList<Date>

                        var temp2 =  SimpleDateFormat("yyyyMMdd")
                        var quitTime2 = temp2.format(quitTime)
                        */



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


    companion object {
        var voteName = ArrayList<String>()
        var quitTime = ArrayList<Date>()
    }*/
    }
}


/*

val tab = findViewById<TabLayout>(R.id.tablayout_main)
tab.setupWithViewPager(pager)
*/

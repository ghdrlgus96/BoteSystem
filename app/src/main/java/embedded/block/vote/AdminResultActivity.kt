package embedded.block.vote

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.admin_result_list.*
import kotlinx.android.synthetic.main.admin_start.*
import org.json.JSONArray
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.HashMap
/*
class AdminResultActivity : AppCompatActivity() {
    override fun onBackPressed() {
        finish()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.admin_result_list)
        Log.d("etest", "dfsdfdsf" + intent.getIntExtra("position", 99))
        var queue: RequestQueue = Volley.newRequestQueue(this);
        val request = object : StringRequest(
            Request.Method.GET,
            "http://203.249.127.32:65001/bote/vote/voteresulter/admin/?voteNum=" + AdminResultAdapter.arr_getList.getJSONObject(intent.getIntExtra("position",0)).getInt("voteNum"),
            Response.Listener { response ->
                run {
                    var arr_getResult = JSONArray(response.toString())
                    AdminResultAdapter.arr_getResult = arr_getResult
                    var adapter = AdminResultAdapter(this)
                    listView_admin_result_list.adapter = adapter

                    button_admin_result_author.setOnClickListener { v: View? ->
                        var queue: RequestQueue = Volley.newRequestQueue(this);
                        val request = object : StringRequest(
                            Request.Method.GET,
                            "http://203.249.127.32:65001/bote/vote/voteupdater/openresult/?voteNum=" + AdminResultAdapter.arr_getList.getJSONObject(intent.getIntExtra("position",0)).getInt("voteNum"),
                            Response.Listener { response ->
                                run {
                                    Toast.makeText(this, "투표 결과 열람 승인 완료", Toast.LENGTH_SHORT).show()
                                    finish()
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
}
*/
class AdminResultActivity : AppCompatActivity() {
    var temp = JSONArray()
    var tempVoteNum: Int = 0
    var tempVoteCandidate = ArrayList<String>()
    var tempVoteScore = ArrayList<String>()
    var json = JSONObject()


    override fun onBackPressed() {
        finish()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.admin_result_list)

        var queue1: RequestQueue = Volley.newRequestQueue(this);
        val request1 = object : StringRequest(
            Request.Method.GET,
            "http://203.249.127.32:65009/vlock/votedeliver/resulter/?voteNum=" + AdminResultAdapter.arr_getList.getJSONObject(intent.getIntExtra("position",0)).getInt("voteNum"),
            Response.Listener { response ->
                run {
                    temp = JSONArray(response.toString())
                    tempVoteNum = AdminResultAdapter.arr_getList.getJSONObject(intent.getIntExtra("position",0)).getInt("voteNum")
                    tempVoteCandidate = ArrayList<String>()
                    tempVoteScore = ArrayList<String>()

                    for(i in 0..temp.length()- 1) {
                        tempVoteCandidate.add(temp.getJSONObject(i).getString("voteCandidate"))
                        tempVoteScore.add(temp.getJSONObject(i).getString("canScore"))
                    }

                    Log.d("testst", tempVoteScore.toString())

                    json.put("voteNum", tempVoteNum)
                    json.put("voteCandidate", tempVoteCandidate)
                    json.put("voteScore", tempVoteScore)
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
        queue1.add(request1)

        var queueq: RequestQueue = Volley.newRequestQueue(this);
        val requestq = object : JsonObjectRequest(
            Request.Method.GET, "http://203.249.127.32:65009/vlock/serverconnection/eli/?voteNum=" + AdminResultAdapter.arr_getList.getJSONObject(intent.getIntExtra("position",0)).getInt("voteNum"), json,
            Response.Listener { response ->
                run {

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
        queueq.add(requestq)

        var queueqq: RequestQueue = Volley.newRequestQueue(this);
        val requestqq = object : JsonObjectRequest(
            Request.Method.GET, "http://203.249.127.32:65010/vlock/serverconnection/eli/?voteNum=" + AdminResultAdapter.arr_getList.getJSONObject(intent.getIntExtra("position",0)).getInt("voteNum"), json,
            Response.Listener { response ->
                run {

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
        queueqq.add(requestqq)
        var queueqqq: RequestQueue = Volley.newRequestQueue(this);
        val requestqqq = object : JsonObjectRequest(
            Request.Method.GET, "http://203.249.127.32:65011/vlock/serverconnection/eli/?voteNum=" + AdminResultAdapter.arr_getList.getJSONObject(intent.getIntExtra("position",0)).getInt("voteNum"), json,
            Response.Listener { response ->
                run {

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
        queueqqq.add(requestqqq)
        var handler = Handler()
        handler.postDelayed(Runnable {
            var queue2: RequestQueue = Volley.newRequestQueue(this);
            val request2 = object : JsonObjectRequest(
                Request.Method.POST, "http://203.249.127.32:65001/vlock/votedeliver/index", json,
                Response.Listener { response ->
                    run {

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
            queue2.add(request2)
        }, 1000)


        var handler1 = Handler()
        handler1.postDelayed(Runnable {
            var queue: RequestQueue = Volley.newRequestQueue(this);
            val request = object : StringRequest(
                Request.Method.GET,
                "http://203.249.127.32:65001/bote/vote/voteresulter/admin/?voteNum=" + AdminResultAdapter.arr_getList.getJSONObject(intent.getIntExtra("position",0)).getInt("voteNum"),
                Response.Listener { response ->
                    run {
                        var arr_getResult = JSONArray(response.toString())
                        AdminResultAdapter.arr_getResult = arr_getResult
                        var adapter = AdminResultAdapter(this)
                        listView_admin_result_list.adapter = adapter

                        button_admin_result_author.setOnClickListener { v: View? ->
                            var queue: RequestQueue = Volley.newRequestQueue(this);
                            val request = object : StringRequest(
                                Request.Method.GET,
                                "http://203.249.127.32:65001/bote/vote/voteupdater/openresult/?voteNum=" + AdminResultAdapter.arr_getList.getJSONObject(intent.getIntExtra("position",0)).getInt("voteNum"),
                                Response.Listener { response ->
                                    run {
                                        Toast.makeText(this, "투표 결과 열람 승인 완료", Toast.LENGTH_SHORT).show()
                                        finish()
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
        }, 2000)
    }
}
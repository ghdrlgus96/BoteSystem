package embedded.block.vote

import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.admin_result_list.*
import org.json.JSONArray
import org.json.JSONObject
import java.util.HashMap

class AdminResultActivity : AppCompatActivity() {
    var temp = JSONArray()
    var tempVoteNum: Int = 0
    var tempVoteCandidate = ArrayList<String>()
    var tempVoteScore = ArrayList<String>()
    var check: Int = 0

    var temp1 = JSONArray()
    var tempVoteNum1: Int = 0
    var tempVoteCandidate1 = ArrayList<String>()
    var tempVoteScore1 = ArrayList<String>()
    var check1: Int = 0

    var temp2 = JSONArray()
    var tempVoteNum2: Int = 0
    var tempVoteCandidate2 = ArrayList<String>()
    var tempVoteScore2 = ArrayList<String>()
    var check2: Int = 0


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

                    for(i in 0..tempVoteScore.size-1)
                        check = check + tempVoteScore.get(i).toInt()
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



        var queue3: RequestQueue = Volley.newRequestQueue(this);
        val request3 = object : StringRequest(
            Request.Method.GET,
            "http://203.249.127.32:65010/vlock/votedeliver/resulter/?voteNum=" + AdminResultAdapter.arr_getList.getJSONObject(intent.getIntExtra("position",0)).getInt("voteNum"),
            Response.Listener { response ->
                run {
                    temp1 = JSONArray(response.toString())
                    tempVoteNum1 = AdminResultAdapter.arr_getList.getJSONObject(intent.getIntExtra("position",0)).getInt("voteNum")
                    tempVoteCandidate1 = ArrayList<String>()
                    tempVoteScore1 = ArrayList<String>()

                    for(i in 0..temp1.length()- 1) {
                        tempVoteCandidate1.add(temp1.getJSONObject(i).getString("voteCandidate"))
                        tempVoteScore1.add(temp1.getJSONObject(i).getString("canScore"))
                    }

                    for(i in 0..tempVoteScore.size-1)
                        check1 = check1 + tempVoteScore1.get(i).toInt()
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
        queue3.add(request3)



        var queue4: RequestQueue = Volley.newRequestQueue(this);
        val request4 = object : StringRequest(
            Request.Method.GET,
            "http://203.249.127.32:65011/vlock/votedeliver/resulter/?voteNum=" + AdminResultAdapter.arr_getList.getJSONObject(intent.getIntExtra("position",0)).getInt("voteNum"),
            Response.Listener { response ->
                run {
                    temp2 = JSONArray(response.toString())
                    tempVoteNum2 = AdminResultAdapter.arr_getList.getJSONObject(intent.getIntExtra("position",0)).getInt("voteNum")
                    tempVoteCandidate2 = ArrayList<String>()
                    tempVoteScore2 = ArrayList<String>()

                    for(i in 0..temp2.length()- 1) {
                        tempVoteCandidate2.add(temp2.getJSONObject(i).getString("voteCandidate"))
                        tempVoteScore2.add(temp2.getJSONObject(i).getString("canScore"))
                    }

                    for(i in 0..tempVoteScore.size-1)
                        check2 = check2 + tempVoteScore2.get(i).toInt()
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
        queue4.add(request4)










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

            if(check >= check1 && check >= check2) {
                json.put("voteNum", tempVoteNum)
                json.put("voteCandidate", tempVoteCandidate)
                json.put("voteScore", tempVoteScore)
            }
            else if(check1 >= check && check1 >= check2) {
                json.put("voteNum", tempVoteNum1)
                json.put("voteCandidate", tempVoteCandidate1)
                json.put("voteScore", tempVoteScore1)
            }
            else if(check2 >= check && check2 >= check1) {
                json.put("voteNum", tempVoteNum2)
                json.put("voteCandidate", tempVoteCandidate2)
                json.put("voteScore", tempVoteScore2)
            }

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
        }, 500)


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
        }, 1000)
    }
}
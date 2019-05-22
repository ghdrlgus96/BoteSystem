package embedded.block.vote

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.admin_result_list.*
import kotlinx.android.synthetic.main.admin_start.*
import org.json.JSONArray
import java.text.SimpleDateFormat
import java.util.HashMap

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
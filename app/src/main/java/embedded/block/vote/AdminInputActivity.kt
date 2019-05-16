package embedded.block.vote

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.admin_input_item.*
import kotlinx.android.synthetic.main.admin_input_select.view.*
import org.json.JSONArray
import org.json.JSONObject

class AdminInputActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.admin_input_item)

        var userNum = 3;
        var json = JSONObject()
        json.put("userNum", "null")
        var queue: RequestQueue = Volley.newRequestQueue(this);
        val request = object : StringRequest(
            Request.Method.GET,
            "http://203.249.127.32:65001/bote/vote/votemaker/getparticipation/?userNum=" + userNum,
            Response.Listener { response ->
                run {
                    var arr_getPart = JSONArray(response.toString())
                    AdminInputAdapter.arr_getParticipation = arr_getPart
                    Log.d("etest", AdminInputAdapter.arr_getParticipation.toString())
                    var adapter = AdminInputAdapter(this)
                    listview_admin_input_select.adapter = adapter
                    button_admin_input_select.setOnClickListener { v: View? ->
                        var arr = ArrayList<Int>()
                        for (i in 0..(adapter.count-1)) {

                            val view = listview_admin_input_select.getChildAt(i)
                            if (view.checkBox.isChecked == true){
                                arr.add(i)
                            }
                        }
                        setResult(0, intent.putExtra("key", arr))
                        finish()
                    }
                }
            },
            null
        ) {
            @Throws(AuthFailureError::class)
            override fun getHeaders(): MutableMap<String, String> {
                val headers = HashMap<String, String>()
                headers.put("Content-Type", "application/json")
                return headers
            }
        }
        queue.add(request)
        supportActionBar?.title = "참여자 선택"
    }
}
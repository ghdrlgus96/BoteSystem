package embedded.block.vote

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import android.widget.Toast
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.alert_list_item.view.*
import kotlinx.android.synthetic.main.list_item_shop.view.*
import org.json.JSONArray
import org.json.JSONObject
import java.util.HashMap

class AlertListViewAdapter(val context: Context, val quittime: String, val voteNum: String): BaseAdapter() {
    companion object {
        var arr_getPage = JSONArray()
    }
    /* 리스트뷰에서 보여줄 아이템(항목) 화면의 인플레이션을 위해 LayoutInflater 참조 */
    private val mInflater: LayoutInflater = LayoutInflater.from(context)


    override fun getCount(): Int {
        Log.d("ktext", "!!"+arr_getPage.length().toString())
        return arr_getPage.length()
    }

    override fun getItem(position: Int) = arr_getPage.getJSONObject(position).getString("voteCandidate")

    override fun getItemId(position: Int) = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View? {


        val view = mInflater.inflate(R.layout.alert_list_item, parent, false)

        view.findViewById<TextView>(R.id.item_can_name).text =
            getItem(position)

        view.setOnClickListener {
            Toast.makeText(context, it.item_can_name.text.toString(), Toast.LENGTH_LONG).show()
            var can_Name = it.item_can_name.text.toString()

            var json = JSONObject()
            json.put("canName", can_Name)
            json.put("quitTime", quittime)
            json.put("voteNum", voteNum)
            json.put("userNum", LoginActivity.userNum)

            Log.d("etest", "보내기실행")

            var queue: RequestQueue = Volley.newRequestQueue(context)
            val request = object : JsonObjectRequest(
                Request.Method.POST, "http://203.249.127.32:65009/vlock/index", json,
                Response.Listener { response ->
                    run {
                        Log.d("tetest", "65009")
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

            var queue1: RequestQueue = Volley.newRequestQueue(context)
            val request1 = object : JsonObjectRequest(
                Request.Method.POST, "http://203.249.127.32:65010/vlock/index", json,
                Response.Listener { response ->
                    run {
                        Log.d("tetest", "65010")
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
            queue1.add(request1)

            var queue2: RequestQueue = Volley.newRequestQueue(context)
            val request2 = object : JsonObjectRequest(
                Request.Method.POST, "http://203.249.127.32:65011/vlock/index", json,
                Response.Listener { response ->
                    run {
                        Log.d("tetest", "65011")
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



            val temp = context as Activity
            temp.finish()
        }

        return view
    }
}
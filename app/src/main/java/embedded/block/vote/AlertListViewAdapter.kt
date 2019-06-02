package embedded.block.vote

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Handler
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
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.alert_list_item.view.*
import org.json.JSONArray
import org.json.JSONObject
import java.util.HashMap

class AlertListViewAdapter(val context: Context, val quittime: String, val voteNum: String): BaseAdapter() {
    companion object {
        var arr_getPage = JSONArray()
        var result_main = JSONArray()
        var result_left = JSONArray()
        var result_right = JSONArray()
    }
    private val mInflater: LayoutInflater = LayoutInflater.from(context)


    override fun getCount(): Int {
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

            var json_bote = JSONObject()
            json_bote.put("voteNum", voteNum)
            json_bote.put("userNum", LoginActivity.userNum)

            var queue0: RequestQueue = Volley.newRequestQueue(context)
            val request0 = object : JsonObjectRequest(
                Request.Method.PUT, LoginActivity.ipAdress+"65001/bote/vote/votestarter/index", json_bote,
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
            queue0.add(request0)
            var queue: RequestQueue = Volley.newRequestQueue(context)
            val request = object : JsonObjectRequest(
                Request.Method.POST, LoginActivity.ipAdress+"65009/vlock/index", json,
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
            queue.add(request)

            var queue1: RequestQueue = Volley.newRequestQueue(context)
            val request1 = object : JsonObjectRequest(
                Request.Method.POST, LoginActivity.ipAdress+"65010/vlock/index", json,
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
            queue1.add(request1)

            var queue2: RequestQueue = Volley.newRequestQueue(context)
            val request2 = object : JsonObjectRequest(
                Request.Method.POST, LoginActivity.ipAdress+"65011/vlock/index", json,
                Response.Listener { response ->
                    run {

                    }
                }, null
            ) {
                override fun getHeaders(): MutableMap<String, String>? {
                    val headers = HashMap<String, String>()
                    headers.put("Content-Type", "application/json")
                    return headers
                }
            }
            queue2.add(request2)

            var handler = Handler()
            handler.postDelayed({
                var queue3: RequestQueue = Volley.newRequestQueue(context)
                val request3 = object : StringRequest(
                    Request.Method.GET,
                    LoginActivity.ipAdress+"65009/vlock/serverconnection/leftserver/?voteNum=" + voteNum,
                    Response.Listener { response ->
                        run {
                            var tmp_string = response.toString()
                            result_main = JSONArray(tmp_string)
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

                var queue4: RequestQueue = Volley.newRequestQueue(context)
                val request4 = object : StringRequest(
                    Request.Method.GET,
                    LoginActivity.ipAdress+"65010/vlock/serverconnection/leftserver/?voteNum=" + voteNum,
                    Response.Listener { response ->
                        run {
                            var tmp_string = response.toString()
                            result_left = JSONArray(tmp_string)
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


                var queue5: RequestQueue = Volley.newRequestQueue(context)
                val request5 = object : StringRequest(
                    Request.Method.GET,
                    LoginActivity.ipAdress+"65011/vlock/serverconnection/leftserver/?voteNum=" + voteNum,
                    Response.Listener { response ->
                        run {
                            var tmp_string = response.toString()
                            result_right = JSONArray(tmp_string)
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
                queue5.add(request5)

                for(i in 0..(VoteListAdapter.arr_getList.length()-1))
                {
                    if(VoteListAdapter.arr_getList.getJSONObject(i).getInt("voteNum").toString() == voteNum)
                        VoteListAdapter.arr_getList.remove(i)
                    FirstFragment.adapter?.notifyDataSetChanged()
                }
            }, 500)
            var homeIntent = Intent(context, VoteListActivity::class.java)
            homeIntent.addCategory(Intent.CATEGORY_HOME)
            homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            VoteListActivity.voteNumber.clear()
            VoteListActivity.resultVoteNumber.clear()
            context.startActivity(homeIntent)
        }

        return view
    }
}
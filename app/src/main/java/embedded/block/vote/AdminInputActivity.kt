package embedded.block.vote

import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.ListView
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
    override fun onBackPressed() {
        setResult(666, intent.putExtra("finish", "종료"))
        finish()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.admin_input_item)

        var json = JSONObject()
        json.put("userNum", "null")
        var queue: RequestQueue = Volley.newRequestQueue(this);
        val request = object : StringRequest(
            Request.Method.GET,
            "http://203.249.127.32:65001/bote/vote/votemaker/getparticipation/?userNum=" + LoginActivity.userNum,
            Response.Listener { response ->
                run {
                    val arr_getPart = JSONArray(response.toString())
                    var string = arr_getPart.toString()

                    AdminInputAdapter.arr_getParticipation = JSONArray(string)
                    var adapter = AdminInputAdapter(this)
                    listview_admin_input_select.adapter = adapter
                    check()
                    adapter.notifyDataSetChanged()


                    editText_admin_input_search.addTextChangedListener(object : TextWatcher {
                        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
                        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
                        override fun afterTextChanged(s: Editable?) {

                            var string2 = arr_getPart.toString()

                            AdminInputAdapter.arr_getParticipation = JSONArray(string2)
                            check()
                            adapter.notifyDataSetChanged()
                            var handler = Handler()
                            handler.postDelayed(Runnable {


                            var i = 0
                            var temp = 0
                            var temp2 = 0
                            var num =
                                findViewById<ListView>(R.id.listview_admin_input_select).lastVisiblePosition - findViewById<ListView>(
                                    R.id.listview_admin_input_select
                                ).firstVisiblePosition
                            while (true) {
                                if (i > num)
                                    break
                                if (!findViewById<ListView>(R.id.listview_admin_input_select).getChildAt(i + temp).textView_admin_input_item1.text.contains(
                                        editText_admin_input_search.text
                                    )
                                ) {
                                    temp++
                                    AdminInputAdapter.arr_getParticipation.remove(i)
                                    check()
                                    adapter.notifyDataSetChanged()
                                    num = num - 1
                                    findViewById<ListView>(R.id.listview_admin_input_select).lastVisiblePosition - findViewById<ListView>(
                                        R.id.listview_admin_input_select
                                    ).firstVisiblePosition
                                    i = temp2
                                } else {
                                    i++
                                    temp2++
                                }

                            }
                            }, 100)
                        }

                    })

                    button_admin_input_select.setOnClickListener { v: View? ->
                        var arr = ArrayList<Int>()
                        var arr_userNum = ArrayList<Int>()
                        for (i in 0..(adapter.count - 1)) {
                            val view = listview_admin_input_select.getChildAt(i)
                            if (view.checkBox.isChecked == true) {
                                arr.add(i)
                            }
                        }
                        setResult(0, intent.putExtra("userNum", arr))
                        finish()
                    }
                    button_admin_input_finish.setOnClickListener { v: View? ->

                        setResult(666, intent.putExtra("finish", "종료"))
                        finish()

                    }

                    button_admin_input_allselect.setOnClickListener { v: View? ->
                        var temp = true
                        for (i in 0..(adapter.count - 1)) {
                            val view = listview_admin_input_select.getChildAt(i)
                            if (view.checkBox.isChecked == false) {
                                break
                            }
                            if (i == (adapter.count - 1) && listview_admin_input_select.getChildAt(i).checkBox.isChecked == true)
                                for (i in 0..(adapter.count - 1)) {
                                    val view = listview_admin_input_select.getChildAt(i)
                                    view.checkBox.isChecked = false
                                    temp = false
                                }
                        }
                        if (temp == true) {
                            for (i in 0..(adapter.count - 1)) {
                                val view = listview_admin_input_select.getChildAt(i)
                                if (view.checkBox.isChecked == false) {
                                    view.checkBox.isChecked = true
                                }
                            }
                        }
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

    fun check() {
        for (i in (0..AdminInputAdapter.arr_getParticipation.length() - 1)) {
            for (j in (i..AdminInputAdapter.arr_getParticipation.length() - 1)) {
                if (i != j && AdminInputAdapter.arr_getParticipation.getJSONObject(i).getInt("userNum") ==
                    AdminInputAdapter.arr_getParticipation.getJSONObject(j).getInt("userNum")
                ) {
                    var inputstring =
                        AdminInputAdapter.arr_getParticipation.getJSONObject(i).getString("userClass")
                    var outstring =
                        ", " + AdminInputAdapter.arr_getParticipation.getJSONObject(j).getString("userClass")
                    AdminInputAdapter.arr_getParticipation.getJSONObject(i).remove("userClass")
                    AdminInputAdapter.arr_getParticipation.getJSONObject(i)
                        .put("userClass", inputstring + outstring)
                    AdminInputAdapter.arr_getParticipation.remove(j)
                }
            }
        }
    }
}

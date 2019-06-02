package embedded.block.vote

import android.app.AlertDialog
import android.content.Context
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
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.admin_stop_item.view.*
import org.json.JSONArray
import java.util.HashMap

class AdminStopAdapter(val context: Context): BaseAdapter() {
    companion object {
        var arr_getlistforStop = JSONArray()
    }
    private val mInflater: LayoutInflater = LayoutInflater.from(context)
    override fun getCount(): Int {
        return arr_getlistforStop.length()
    }

    override fun getItem(position: Int) = arr_getlistforStop.getJSONObject(position)

    override fun getItemId(position: Int) = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        val view = convertView ?: mInflater.inflate(R.layout.admin_stop_item, parent, false)

        view.findViewById<TextView>(R.id.textView_admin_stopName).text = getItem(position).getString("voteName")

        view.button_admin_stopButton.setOnClickListener { v: View? ->

            val alertDialogBuilder = AlertDialog.Builder(context)
            alertDialogBuilder.setTitle("투표 종료")
            alertDialogBuilder.setMessage("정말 투표를 종료하시겠습니까?")
            alertDialogBuilder.setCancelable(false)
            alertDialogBuilder.setPositiveButton("종료") { dialog, id ->
                var queue: RequestQueue = Volley.newRequestQueue(context);
                val request = object : StringRequest(
                    Request.Method.PUT,
                    LoginActivity.ipAdress+"65001/bote/vote/voteupdater/quitvote/?voteNum=" + getItem(position).getInt("voteNum"),
                    Response.Listener { response ->
                        run {
                            Toast.makeText(context, "중단 완료", Toast.LENGTH_SHORT).show()
                            arr_getlistforStop.remove(position)
                            this.notifyDataSetChanged()
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

            alertDialogBuilder.setNegativeButton("취소") { dialog, id ->
                dialog.dismiss()
            }

            alertDialogBuilder.show()
        }
        return view
    }
}
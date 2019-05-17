package embedded.block.vote

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_findid.*
import org.json.JSONObject
import java.util.HashMap
//test
class FindidActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_findid)

        findBtn.setOnClickListener {
            findId(findIDNameText.text.toString(), findIDPhoneText.text.toString())
        }
    }

    fun findId(name: String?, phone: String?) {
        var json = JSONObject()
        json.put("myname", name)
        json.put("myphone", phone)

        var queue: RequestQueue = Volley.newRequestQueue(this);
        val request = object : JsonObjectRequest(
            Request.Method.POST, "http://203.249.127.32:65001/bote/find/id", json,
            Response.Listener { response ->
                run {
                    if(response.getString("userid") == "undefind")
                        Toast.makeText(this, "정확한 정보를 입력해주세요", Toast.LENGTH_SHORT).show()
                    else
                        Toast.makeText(this, response.getString("userid"), Toast.LENGTH_SHORT).show()
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

package embedded.block.vote

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.test.*
import org.json.JSONObject


class LoginActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        testButton.setOnClickListener {
            test("http://203.249.127.32:65001/test", testEdit.text.toString())
        }
    }

    //get
    fun test(msg: String?, id: String?) {
        var json = JSONObject()
        json.put("myid", "null!")    //매개변수 넣기 위해 넣은거다 전혀 쓰잘대기 없다
        Log.d("embedded", json.toString())
        var queue: RequestQueue = Volley.newRequestQueue(this);
        val request = object : JsonObjectRequest(Request.Method.GET, msg + "/?myid=" + id, json,
            Response.Listener { response ->
                run {
                    Log.d("embedded", response.toString())
                    //json 뽑아 오는법
                    testText.text = response.getString("eee") + "   " + response.getString("ggg")
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
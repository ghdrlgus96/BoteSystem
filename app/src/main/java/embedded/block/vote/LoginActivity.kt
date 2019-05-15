package embedded.block.vote

import android.content.Intent
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
        //setTheme(android.R.style.Theme_NoDisplay)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        regBtn.setOnClickListener {
            var intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        loginBtn.setOnClickListener {

        }

        findID.setOnClickListener {
            var intent = Intent(this, FindidActivity::class.java)
            startActivity(intent)
        }

        findPass.setOnClickListener {
            var intent = Intent(this, FindpassActivity::class.java)
            startActivity(intent)
        }
    }

    //user 정보 저장합니다
    companion object {

    }

    //get
    /*
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
    */
}
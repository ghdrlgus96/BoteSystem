package embedded.block.vote

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
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
            login(myID.text.toString(), myPass.text.toString())
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

    //get
    fun login(myid: String?, mypass: String?) {
        var json = JSONObject()
        json.put("myid", "null!")
        Log.d("embedded", json.toString())
        var queue: RequestQueue = Volley.newRequestQueue(this);
        val request = object : JsonObjectRequest(Request.Method.GET, "http://203.249.127.32:65001/bote/login/?myid=" + myid + "&mypass=" + mypass, json,
            Response.Listener { response ->
                run {
                    Log.d("embedded", response.toString())
                    userId = response.getString("userid")
                    userPass = response.getString("userpass")
                    userName = response.getString("username")
                    userNum = response.getString("usernum")
                    userClass = null
                    userPhone = response.getString("userphone")
                    userAuthor = response.getString("userauthor")

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

    //user 정보 저장합니다
    companion object {
        var userId = ""
        var userPass = ""
        var userName = ""
        var userNum = ""
        var userClass: Array<String>? = null
        var userPhone = ""
        var userAuthor = ""
    }
}
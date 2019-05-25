package embedded.block.vote

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.Toast
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.app_bar_admin.*
import org.json.JSONObject
import java.util.*


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

    fun login(myid: String, mypass: String) {
        var json = JSONObject()
        json.put("myid", "null!")
        //Log.d("embedded", json.toString())
        var queue: RequestQueue = Volley.newRequestQueue(this);
        val request = object : JsonObjectRequest(Request.Method.GET, "http://203.249.127.32:65001/bote/login/?myid=" + myid + "&mypass=" + mypass, json,
            Response.Listener { response ->
                run {
                    userClass.clear()
                    if(response.getString("userauthor") != "undefind") {
                        var tmp = response.getString("userclassnum")
                        var tempList = tmp.split(",")

                        //로그아웃 시 userClass는 비워주자 아니면 자꾸 추가됨
                        for (i in 0..tempList.size - 1)
                            userClass.add(tempList.get(i))

                        userId = myid
                        userPass = mypass
                        userName = response.getString("username")
                        userNum = response.getString("usernum")
                        userPhone = response.getString("userphone")
                        userAuthor = response.getString("userauthor")

                        if(userAuthor == "1") {
                            var intent = Intent(this, AdminActivity::class.java)
                            startActivity(intent)
                        }
                        else if(userAuthor == "2") {
                            var intent = Intent(this, VoteListActivity::class.java)
                            startActivity(intent)
                        }
                        else if(userAuthor == "3") {
                            Toast.makeText(this, "회원 탈퇴한 정보입니다", Toast.LENGTH_SHORT).show()
                        }
                    }
                    else {
                        Toast.makeText(this, "아이디 혹은 비밀번호가 틀렸습니다", Toast.LENGTH_SHORT).show()
                    }
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
        var userClass = ArrayList<String>()
        var userPhone = ""
        var userAuthor = ""
    }
}
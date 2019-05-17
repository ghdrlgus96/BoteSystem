package embedded.block.vote

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_findpass.*
import org.json.JSONObject
import java.util.HashMap

class FindpassActivity : AppCompatActivity() {

    var tempID: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_findpass)

        findPassBtn.setOnClickListener {
            if(findPassID.text.toString() == "아이디") {
                findPassId(findPassIDText.text.toString())
                tempID = findPassIDText.text.toString()
                findPassIDText.setText("")
            }
            else {
                findPassword(findPassIDText.text.toString())
            }
        }
    }
//
    fun findPassId(id: String?) {
        var json = JSONObject()
        json.put("myid", id)

        var queue: RequestQueue = Volley.newRequestQueue(this);
        val request = object : JsonObjectRequest(
            Request.Method.GET, "http://203.249.127.32:65001/bote/find/password/getquestion/?myid=" + id, json,
            Response.Listener { response ->
                run {
                    if(response.getString("mypassquestion") == "undefind")
                        Toast.makeText(this, "정확한 정보를 입력해주세요", Toast.LENGTH_SHORT).show()
                    else {
                        findPassID.text = "비밀번호 찾기 질문: " + response.getString("mypassquestion")
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

    fun findPassword(passAnswer: String?) {
        var json = JSONObject()
        json.put("myid", tempID)
        json.put("myanswer", passAnswer)

        var queue: RequestQueue = Volley.newRequestQueue(this);
        val request = object : JsonObjectRequest(
            Request.Method.POST, "http://203.249.127.32:65001/bote/find/password/index", json,
            Response.Listener { response ->
                run {
                    if(response.getString("mypass") == "undefind")
                        Toast.makeText(this, "답변이 잘못되었습니다", Toast.LENGTH_SHORT).show()
                    else {
                        Toast.makeText(this, response.getString("mypass"), Toast.LENGTH_SHORT).show()
                        finish()
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
}

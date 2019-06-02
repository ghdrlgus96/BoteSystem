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
import kotlinx.android.synthetic.main.activity_elimination.*
import org.json.JSONObject
import java.util.HashMap

class EliminationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_elimination)

        eliminationButton.setOnClickListener {
            if(LoginActivity.userPass == eliminationPassText.text.toString() && eliminationPassText.text.toString() == eliminationPassCheckText.text.toString()) {
                elimination()
                setResult(0)
                finish()
            }
        }
    }

    fun elimination() {
        var json = JSONObject()
        json.put("mynum", LoginActivity.userNum)

        var queue: RequestQueue = Volley.newRequestQueue(this);
        val request = object : JsonObjectRequest(
            Request.Method.POST, LoginActivity.ipAdress+"65001/bote/accountmanager/elimination", json,
            Response.Listener { response ->
                run {
                    if(response.getString("result") == "fail")
                        Toast.makeText(this, "회원탈퇴 실패", Toast.LENGTH_SHORT).show()
                    else {
                        Toast.makeText(this, "회원탈퇴 성공", Toast.LENGTH_SHORT).show()
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
